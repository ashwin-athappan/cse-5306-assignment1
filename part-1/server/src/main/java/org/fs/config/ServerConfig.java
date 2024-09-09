package org.fs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class ServerConfig {
    @Bean
    public Path getRootDirectory() {
        return Paths.get(System.getProperty("user.dir") + "/server/src/main/resources/files");
    }
}
