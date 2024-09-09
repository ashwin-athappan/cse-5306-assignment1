package org.fs.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fs.service.FileSystemService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@ShellComponent
@AllArgsConstructor
public class CommandLineController {

    private final FileSystemService fileSystemService;

    @ShellMethod(key = "/help")
    public String help() {
        StringBuilder builder = new StringBuilder();
        builder.append("Welcome to SpringBoot File System CLI\n");
        builder.append("Usage:\n");
        builder.append("\t/upload <file-path> - this command helps you upload file to the remote server\n");
        builder.append("\t/list               - this command gives you a list of files in the remote server\n");
        builder.append("\t/delete <file-name> - this command helps you delete a file in the remote server\n");
        builder.append("\t/rename <old-file-name> <new-file-name> - this command helps you rename a file in the remote server\n");
        return builder.toString();
    }

    @ShellMethod(key = "/upload", value = "upload a file")
    public String upload(
            @ShellOption(value = "",
                    help = "/upload <file-path> - this command helps you upload file to the remote server") String filePath) {

        try {
            if (filePath == null || filePath.isEmpty()) {
                throw new FileNotFoundException(filePath);
            }

            File file = new File(filePath);

            if (!file.exists()) {
                throw new FileNotFoundException(filePath);
            }

            fileSystemService.upload(filePath);

            String error = waitForResponse();
            if (error != null) return error;

        } catch (IOException e) {
            StringBuilder builder = new StringBuilder();
            if (e.toString().contains("FileNotFoundException")) {
                builder.append("File not found\n");
            } else {
                builder.append("Error: ").append(e.toString()).append("\n");
            }
            builder.append("Usage : /upload <file-path>\n");
            builder.append("<file-path> - should be the absolute path of the file to upload\n");
            builder.append("Make sure the file with the same name does not exist in the server\n");
            builder.append("Use /list to list all the files in the server\n");
            return builder.toString();
        }

        return "Received : " + filePath;
    }

    @ShellMethod(key = "/delete")
    public String delete(
            @ShellOption(value = "",
                    help = "/delete <file-name> - this command helps you delete a file from the server") String fileName) {
        try {
            fileSystemService.delete(fileName);

            String error = waitForResponse();
            if (error != null) return error;

        } catch (Exception e) {
            StringBuilder builder = new StringBuilder();
            builder.append("Error : ");
            builder.append(e.getMessage());
            builder.append("\nUsage : /delete <file-name>\n");
            builder.append("<file-name> must exist in the server and must be a file");
            builder.append("Use /list to list all the files in the server\n");
            return builder.toString();
        }

        return "Deleted " + fileName + " from the server";
    }

    @ShellMethod(key = "/rename")
    public String rename(
            @ShellOption(value = "",
                    help = "/rename <old-file-name> <new-file-name> - this command helps you rename a file in the server") String oldFileName,
            @ShellOption(value = "",
                    help = "/rename <old-file-name> <new-file-name> - this command helps you rename a file in the server") String newFileName) {

        try {
            fileSystemService.rename(oldFileName, newFileName);
        } catch (Exception e) {
            StringBuilder builder = new StringBuilder();
            builder.append("Error : ");
            builder.append(e.getMessage());
            builder.append("\nUsage : /rename <old-file-name> <new-file-name>\n");
            builder.append("<old-file-name> must exist in the server and must be a file");
            builder.append("Use /list to list all the files in the server\n");
            return builder.toString();
        }

        return "Renamed " + oldFileName + " to " + newFileName + " in the server";
    }

    @ShellMethod(key = "/list")
    public String list() {
        fileSystemService.getFiles();

        List<String> files = fileSystemService.getListOfFiles();

        /* Since the files are being fetched asynchronously
         * The while loop ensures to capture the updated list of files
         *
         * Here if the listOfFiles is null then the result has not arrived from the server yet
         */

        while (files == null) {
            log.debug("File list is null");
            if (!fileSystemService.getError().isEmpty()) {
                String error = fileSystemService.getError();
                fileSystemService.setError("");
                return "Error : " + error;
            }

            files = fileSystemService.getListOfFiles();
        }

        // Resetting the listOfFiles variable so we do not print the previously fetched result
        fileSystemService.setListOfFiles(null);

        // Preparing the list to be printed back to the CLI
        StringBuilder builder = new StringBuilder();
        builder.append("List of files\n");
        int i = 1;
        for (String file : files) {
            builder.append("\t").append(i++).append(". ").append(file).append("\n");
        }

        return builder.toString();
    }

    private String waitForResponse() {
        String response = fileSystemService.getResponse();

        while (response == null || response.isEmpty()) {

            if (!fileSystemService.getError().isEmpty()) {
                String error = fileSystemService.getError();
                fileSystemService.setError("");
                return "Error : " + error;
            }

            response = fileSystemService.getResponse();
            fileSystemService.setResponse("");
        }
        return response;
    }
}
