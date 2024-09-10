package org.sync.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.sync.proto.FileContent;
import org.sync.utils.UtilitiesService;

import java.io.*;
import java.nio.file.*;
import java.util.*;

@Slf4j
@Service
public class DirectoryDiffService {
    private final FileSyncService fileSyncService;
    private final UtilitiesService utilitiesService;

    private final HashMap<String, Long> checkpoint;
    private final String checkpointPath;
    private final String localFilesPath;

    public DirectoryDiffService(FileSyncService fileSyncService, UtilitiesService utilitiesService) {
        this.fileSyncService = fileSyncService;
        this.utilitiesService = utilitiesService;

        String path = Paths.get("").toAbsolutePath().toString();
        checkpointPath = utilitiesService.getOSSpecificPath("/diff/last_checkpoint.txt");
        localFilesPath = utilitiesService.getOSSpecificPath("/files");
        this.checkpoint = new HashMap<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(checkpointPath));
            for (String line : lines) {
                String[] split = line.split("\\|");
                if (!checkpoint.containsKey(split[0])) {
                    checkpoint.put(split[0], Long.parseLong(split[1]));
                }
            }
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @description This method compares stored cache to the current files.
     * It then detects the changes and performs the appropriate operation.
     * This is done to improve fault tolerance.
     */
    public void compare() {
        log.info("compare::Comparing");
        Map<String, Long> currentMap = new HashMap<>();
        Map<String, Long> createMap = new HashMap<>();
        List<String> deleteList = new ArrayList<>();
        Map<String, Long> modifyMap = new HashMap<>();
        File folder = new File(localFilesPath);
        File[] files = folder.listFiles();

        try {
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        if (!file.isHidden()) {
                            String path = file.getAbsolutePath();
                            long lastModifiedTime = utilitiesService.getLastModified(path);
                            currentMap.put(path, lastModifiedTime);
                        }
                    }
                }
            }

            for (Map.Entry<String, Long> entry : currentMap.entrySet()) {
                String path = entry.getKey();
                Long lastModifiedTime = entry.getValue();
                if (checkpoint.containsKey(path)) {
                    if (checkpoint.get(path) < lastModifiedTime) {
                        // Modified Scenario
                        log.info("compare::CHECKPOINT MODIFIED");
                        modifyMap.put(path, lastModifiedTime);
                    }
                } else {
                    // Create Scenario
                    checkpoint.put(path, lastModifiedTime);
                    log.info("compare::CHECKPOINT CREATE");
                    createMap.put(path, lastModifiedTime);
                }
            }

            for (Map.Entry<String, Long> entry : checkpoint.entrySet()) {
                String path = entry.getKey();
                if (!currentMap.containsKey(path)) {
                    // Delete Scenario
                    log.info("compare::CHECKPOINT DELETE");
                    deleteList.add(path);
                }
            }

            for (String path : deleteList) {
                log.info("compare::deleting {}", path);
                String fileName = path.substring(path.lastIndexOf("/") + 1);
                fileSyncService.deleteFile(fileName);
                checkpoint.remove(path);
            }

            for (Map.Entry<String, Long> entry : createMap.entrySet()) {
                String path = entry.getKey();
                Long lastModifiedTime = entry.getValue();
                log.info("compare::creating {}", path);
                String fileName = path.substring(path.lastIndexOf("/") + 1);
                FileContent fileContent = utilitiesService.getFileContent(fileName);
                fileSyncService.createFile(fileName, fileContent);
                checkpoint.put(path, lastModifiedTime);
            }

            for (Map.Entry<String, Long> entry : modifyMap.entrySet()) {
                String path = entry.getKey();
                Long lastModifiedTime = entry.getValue();
                log.info("compare::modifyList {}", path);
                String fileName = path.substring(path.lastIndexOf("/") + 1);
                FileContent fileContent = utilitiesService.getFileContent(fileName);
                fileSyncService.modifyFile(fileName, fileContent);
                checkpoint.put(path, lastModifiedTime);
            }

            updateCheckPoint();
        } catch (IOException e) {
            log.error("compare::Error occurred while comparing current files to last checkpoint : {}", e.getMessage());
        }
    }

    public void loadCheckpoint() {
        log.info("loadCheckpoint::Loading checkpoint");
        try {
            List<String> lines = Files.readAllLines(Paths.get(checkpointPath));
            for (String line : lines) {
                String[] split = line.split("\\|");
                if (!checkpoint.containsKey(split[0])) {
                    checkpoint.put(split[0], Long.parseLong(split[1]));
                }
            }
        } catch (IOException e) {
            log.error("loadCheckpoint::Error occurred while loading cache: {}", e.getMessage());
        }

    }

    public void updateCheckPoint() throws IOException {
        log.info("updateCheckPoint::Updating checkpoint");
        // Clear contents of the file
        PrintWriter writer = new PrintWriter(checkpointPath);
        writer.print("");
        writer.close();
        // Create new entries in the file
        for (Map.Entry<String, Long> entry : checkpoint.entrySet()) {
            String path = entry.getKey();
            String lastModifiedTime = entry.getValue().toString();
            log.info("updateCheckPoint::Adding {} to checkpoint file", path);
            FileWriter fileWriter = new FileWriter(checkpointPath, true);
            fileWriter.write(path + "|" + lastModifiedTime + "\n");
            fileWriter.close();
        }
    }

    public void updateCache(String path, long lastModifiedTime) {
        log.info("updateCache::Updating cache");
        if (!checkpoint.containsKey(path)) {
            log.info("updateCache::Adding {} to cache", path);
            checkpoint.put(path, lastModifiedTime);
        }
    }

    public void addOrModifyFile(String path) {
        log.info("addOrModifyFile::Adding file {} to checkpoint at {}", path, checkpointPath);
        try {
            long lastModifiedTime = utilitiesService.getLastModified(path);
            log.info("addOrModifyFile::Updating checkpoint and cache");
            updateCache(path, lastModifiedTime);
            updateCheckPoint();
        } catch (IOException e) {
            log.error("addOrModifyFile:: Error occurred {}", e.getMessage());
        }
    }

    public void deleteFile(String path) {
        log.info("deleteFile::Deleting file {}", path);
        try {
            checkpoint.remove(path);
            updateCheckPoint();
        } catch (IOException e) {
            log.error("deleteFile::Error occurred {}", e.getMessage());
        }
    }

    public void renameFile(String oldPath, String newPath) {
        log.info("renameFile::Renaming file {} to {}", oldPath, newPath);
        try {
            checkpoint.remove(oldPath);
            long lastModifiedTime = utilitiesService.getLastModified(newPath);
            updateCache(newPath, lastModifiedTime);
            updateCheckPoint();
        } catch (IOException e) {
            log.error("renameFile::Error occurred {}", e.getMessage());
        }
    }
}
