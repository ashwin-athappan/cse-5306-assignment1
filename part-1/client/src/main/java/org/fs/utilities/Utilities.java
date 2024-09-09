package org.fs.utilities;

import com.google.protobuf.ByteString;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.fs.proto.FileContent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@UtilityClass
public class Utilities {

    public static FileContent getFileContent(String filePath) {
        File createdFile = new File(filePath);
        byte[] content = new byte[(int) createdFile.length()];
        try (FileInputStream fis = new FileInputStream(createdFile)) {
            int result = fis.read(content);
            log.debug("getFileContent::Read result {}", result);
        } catch (IOException e) {
            log.error("getFileContent::Error while reading file {}", createdFile.getAbsolutePath());
        }

        ByteString compatibleContent = ByteString.copyFrom(content);
        return FileContent.newBuilder().setContent(compatibleContent).build();
    }

    public static String getResponse(String message) {
        return "";
    }
}
