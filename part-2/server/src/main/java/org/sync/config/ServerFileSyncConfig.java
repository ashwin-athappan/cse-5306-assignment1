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
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("win")) {
            if (path.substring(path.lastIndexOf("\\") + 1).equals("server")) {
                path = path + "\\src\\main\\resources\\uploads";
            } else {
                path = path + "\\server\\src\\main\\resources\\uploads";
            }
        } else {
            if (path.substring(path.lastIndexOf("/") + 1).equals("server")) {
                path = path + "/src/main/resources/uploads";
            } else {
                path = path + "/server/src/main/resources/uploads";
            }
        }
        return path;
    }
}
