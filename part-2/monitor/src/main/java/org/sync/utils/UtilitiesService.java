package org.sync.utils;

import com.google.protobuf.ByteString;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.sync.proto.FileContent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

@Slf4j
@Service
@AllArgsConstructor
public class UtilitiesService {

    private final Path rootDirectory;

    public FileContent getFileContent(String fileName) {
        File createdFile = new File(rootDirectory.toString() + "/" + fileName);
        byte[] content = new byte[(int) createdFile.length()];
        try (FileInputStream fis = new FileInputStream(createdFile)) {
            int result = fis.read(content);
            log.info("getFileContent::Read result {}", result);
        } catch (IOException e) {
            log.error("getFileContent::Error while reading file {}", createdFile.getAbsolutePath());
        }

        ByteString compatibleContent = ByteString.copyFrom(content);
        return FileContent.newBuilder().setContent(compatibleContent).build();
    }

    public long getLastModified(String filePath) throws IOException {
        Path file = Paths.get(filePath);
        BasicFileAttributes attributes = Files.readAttributes(file, BasicFileAttributes.class);
        return attributes.lastModifiedTime().toMillis();
    }
}
