package org.sync.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Configuration
public class ServerFileSyncConfig {

    @Bean
    public Path getRootDirectory() {
        return Paths.get(System.getProperty("user.dir") + "/server/src/main/resources/uploads");
    }
}
