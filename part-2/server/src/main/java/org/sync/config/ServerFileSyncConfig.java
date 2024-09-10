package org.sync.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class ServerFileSyncConfig {

    @Bean
    public String getRootDirectory() {
        String path = Paths.get("").toAbsolutePath().toString();
        if (path.substring(path.lastIndexOf("/") + 1).equals("server")) {
            path = path + "/src/main/resources/uploads";
        } else {
            path = path + "/monitor/src/main/resources/uploads";
        }
        return path;
    }
}
