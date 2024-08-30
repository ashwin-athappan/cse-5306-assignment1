package org.sync.service;

import com.google.protobuf.ByteString;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.sync.proto.FileContent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class FileMonitorService {

    private final WatchService watchService;
    private final Path rootDirectory;
    private final FileSyncService fileSyncService;

    /**
     * @description
     * This is a monitor service that monitors the client directory
     * Changes can be of 4 types:
     * * CREATE
     * * DELETE
     * * MODIFY
     * * RENAME
     */
    @Async
    @Bean
    public void monitor() {
        log.info("Monitor is Running");
        try {
            while (true) {
                log.info("Monitoring...");
                WatchKey key = watchService.take();

                List<WatchEvent<?>> watchEvents = key.pollEvents();

                if (watchEvents.size() == 2) {
                    WatchEvent<?> first = watchEvents.get(0);
                    WatchEvent<?> second = watchEvents.get(1);

                    if (first.kind() == StandardWatchEventKinds.ENTRY_CREATE && second.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                        log.info("Renamed {} to {}", second.context(), first.context());
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

                boolean valid = key.reset();
                if (!valid) {
                    log.warn("Watcher is no longer valid. Directory may have been deleted.");
                }
                log.info("Operation Complete");
            }
        } catch (InterruptedException e) {
            log.error("Error while monitoring files: {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
        log.info("Monitor has Stopped");
    }

    @PreDestroy
    public void stopMonitor() {
        log.info("Stopping monitor");
        if (watchService != null) {
            try {
                watchService.close();
            } catch (IOException e) {
                log.error("exception while closing the monitoring service");
            }
        }
    }

    public void handleEvents(WatchEvent<?> watchEvent) {
        log.info("WatchKey received {}", watchEvent.kind());

        if (Files.isDirectory(Paths.get(rootDirectory.toAbsolutePath() + "/" + watchEvent.context()))) {
            log.info("{} is a Directory", watchEvent.context());

            if (watchEvent.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                // Code to call create dir
            }

            if (watchEvent.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                // Code to call delete dir
            }

            if (watchEvent.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                // Code to update whatever was updated within the directory
            }

        } else {
            if (watchEvent.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                log.info("Created {}", watchEvent.context());
                fileSyncService.createFile(watchEvent.context().toString());
            }

            if (watchEvent.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                log.info("Deleted {}", watchEvent.context());
                fileSyncService.deleteFile(watchEvent.context().toString());
            }

            if (watchEvent.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                log.info("Updated {}", watchEvent.context());
                String fileName = watchEvent.context().toString();

                File modifiedFile = new File(rootDirectory.toString() + "/" + fileName);

                byte[] content = new byte[(int) modifiedFile.length()];
                try (FileInputStream fis = new FileInputStream(modifiedFile)) {
                    int result = fis.read(content);
                    log.info("Read result {}", result);
                } catch (IOException e) {
                    log.error("Error while reading file {}", modifiedFile.getAbsolutePath());
                }

                ByteString compatibleContent = ByteString.copyFrom(content);
                FileContent fileContent = FileContent.newBuilder().setContent(compatibleContent).build();
                fileSyncService.modifyFile(fileName, fileContent);
            }
        }
    }
}