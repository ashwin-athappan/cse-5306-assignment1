package org.fs.config;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;

@Configuration
public class ServerConfig {
    @Autowired
    ServletContext context;

    @Bean
    public String getRootDirectory() {
        String path = Paths.get("").toAbsolutePath().toString();

        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("win")) {
            if (path.substring(path.lastIndexOf("\\") + 1).equals("server")) {
                path = path + "\\src\\main\\resources\\files";
            } else {
                path = path + "\\server\\src\\main\\resources\\files";
            }
        } else {
            if (path.substring(path.lastIndexOf("/") + 1).equals("server")) {
                path = path + "/src/main/resources/files";
            } else {
                path = path + "/server/src/main/resources/files";
            }
        }

        return path;
    }
}
