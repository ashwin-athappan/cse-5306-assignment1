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

    private String rootDirectory;

    @Bean
    public String getRootDirectory() {
        String path = Paths.get("").toAbsolutePath().toString();
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("win")) {
            if (path.substring(path.lastIndexOf("\\") + 1).equals("monitor")) {
                path = path + "\\src\\main\\resources\\files";
            } else {
                path = path + "\\monitor\\src\\main\\resources\\files";
            }
        } else {
            if (path.substring(path.lastIndexOf("/") + 1).equals("monitor")) {
                path = path + "/src/main/resources/files";
            } else {
                path = path + "/monitor/src/main/resources/files";
            }
        }
        return path;
    }

    @Bean
    public WatchKey getWatchService() {
        WatchKey watchKey;
        try {
            rootDirectory  = getRootDirectory();

            if (!Files.isDirectory(Paths.get(rootDirectory))) {
                throw new RuntimeException("incorrect monitoring folder: " + rootDirectory);
            }

            WatchService watchService = FileSystems.getDefault().newWatchService();

            watchKey = Paths.get(rootDirectory).register(watchService,
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
