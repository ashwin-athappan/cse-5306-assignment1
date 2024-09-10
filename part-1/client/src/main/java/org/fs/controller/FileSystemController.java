package org.fs.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fs.service.FileSystemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class FileSystemController {

    private final FileSystemService fileSystemService;

    @GetMapping("/files")
    public String files() {

        return "files";
    }
}
