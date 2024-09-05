package org.sync.config;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.*;

@Slf4j
@Configuration
public class FileMonitorConfig {

    private Path rootDirectory;

    @Bean
    public Path getRootDirectory() {
        rootDirectory  = Paths.get(System.getProperty("user.dir") + "/monitor/src/main/resources/files");
        return rootDirectory;
    }

    @Bean
    public WatchKey getWatchService() {
        WatchKey watchKey;
        try {
            rootDirectory  = Paths.get(System.getProperty("user.dir") + "/monitor/src/main/resources/files");

            if (!Files.isDirectory(rootDirectory)) {
                throw new RuntimeException("incorrect monitoring folder: " + rootDirectory);
            }

            WatchService watchService = FileSystems.getDefault().newWatchService();

            watchKey = rootDirectory.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);
        } catch (IOException e) {
            log.error("Error initializing FileMonitorService: {}", e.getMessage());
            throw new RuntimeException(e);
        }

        return watchKey;
    }

}
