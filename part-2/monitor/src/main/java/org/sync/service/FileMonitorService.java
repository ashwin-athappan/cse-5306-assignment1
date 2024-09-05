package org.sync.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.sync.proto.FileContent;
import org.sync.utils.UtilitiesService;

import java.nio.file.*;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class FileMonitorService {

    private final WatchKey watchKey;
    private final Path rootDirectory;
    private final FileSyncService fileSyncService;
    private final UtilitiesService utilitiesService;
    private final DirectoryDiffService directoryDiffService;

    @PostConstruct
    public void init() {
        log.info("init::Checking last checkpoint");
        directoryDiffService.compare();
    }

    /**
     * @description This is a monitor service that monitors the client directory
     * Changes can be of 4 types:
     * * CREATE
     * * DELETE
     * * MODIFY
     * * RENAME
     */
    @Bean
    @Async
    @Scheduled(initialDelay = 10000, fixedDelay = 15000)
    public void monitor() {
        log.info("monitor::Monitor is Running");
        List<WatchEvent<?>> watchEvents = watchKey.pollEvents();

        if (watchEvents.size() == 2) {
            WatchEvent<?> first = watchEvents.get(0);
            WatchEvent<?> second = watchEvents.get(1);

            if (first.kind() == StandardWatchEventKinds.ENTRY_CREATE && second.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                log.info("monitor::Renamed {} to {}", second.context(), first.context());
                String oldName = second.context().toString();
                String newName = first.context().toString();
                fileSyncService.renameFile(oldName, newName);
                String oldPath = rootDirectory.toAbsolutePath() + "/" + oldName;
                String newPath = rootDirectory.toAbsolutePath() + "/" + newName;
                directoryDiffService.renameFile(oldPath, newPath);
            } else {
                for (WatchEvent<?> watchEvent : watchEvents) {
                    handleEvents(watchEvent);
                }
            }
        } else {
            for (WatchEvent<?> watchEvent : watchEvents) {
                handleEvents(watchEvent);
            }
        }

        boolean valid = watchKey.reset();
        if (!valid) {
            log.warn("monitor::Watcher is no longer valid. Directory may have been deleted.");
        }
        log.info("monitor::Monitor Stopped");
    }

    /**
     * @description This function handles the events captured by the WatchService
     *
     * @see java.nio.file.WatchService
     *
     * @param watchEvent This is the event captured by the WatchService
     */
    public void handleEvents(WatchEvent<?> watchEvent) {
        log.info("handleEvents::WatchKey received {}", watchEvent.kind());

        if (Files.isDirectory(Paths.get(rootDirectory.toAbsolutePath() + "/" + watchEvent.context()))) {
            log.info("handleEvents::{} is a Directory", watchEvent.context());
        } else {
            if (watchEvent.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                log.info("handleEvents::Creating {}", watchEvent.context());
                String fileName = watchEvent.context().toString();
                String path = rootDirectory.toAbsolutePath() + "/" + watchEvent.context();
                // Get File Contents
                FileContent fileContent = utilitiesService.getFileContent(fileName);
                fileSyncService.createFile(fileName, fileContent);
                directoryDiffService.addOrModifyFile(path);
            }

            if (watchEvent.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                log.info("handleEvents::Deleting {}", watchEvent.context());
                String path = rootDirectory.toAbsolutePath() + "/" + watchEvent.context();
                fileSyncService.deleteFile(watchEvent.context().toString());
                directoryDiffService.deleteFile(path);
            }

            if (watchEvent.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                log.info("handleEvents::Updating {}", watchEvent.context());
                String fileName = watchEvent.context().toString();
                String path = rootDirectory.toAbsolutePath() + "/" + watchEvent.context();
                // Get File Contents
                FileContent fileContent = utilitiesService.getFileContent(fileName);
                fileSyncService.modifyFile(fileName, fileContent);
                directoryDiffService.addOrModifyFile(path);
            }
        }
    }
}