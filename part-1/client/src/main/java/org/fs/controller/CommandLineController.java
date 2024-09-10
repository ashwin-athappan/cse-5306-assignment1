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
import java.util.concurrent.ExecutionException;

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

    @ShellMethod(key = "/upload")
    public String upload(@ShellOption(arity = 1, value = "", help = "") String filePath) {
        log.info(filePath);
        String result = "";
        try {
            if (filePath == null || filePath.isEmpty()) {
                throw new FileNotFoundException(filePath);
            }

            File file = new File(filePath);

            if (!file.exists()) {
                throw new FileNotFoundException(filePath);
            }

            /* Since the files are being fetched asynchronously
             * The while loop ensures to capture the updated list of files
             *
             * Here if the listOfFiles is null then the result has not arrived from the server yet
             */
            CompletableFuture<List<String>> future = new CompletableFuture<>();

            fileSystemService.upload(filePath, future);

            List<String> response = future.get();
            if (response.get(0).equals("SUCCESS")) {
                result = response.get(1);
            } else if (response.get(0).equals("ERROR")) {
                throw new IOException(response.get(1));
            }


        } catch (IOException | InterruptedException | ExecutionException e) {
            StringBuilder builder = new StringBuilder();
            if (e.toString().contains("FileNotFoundException")) {
                builder.append("File not found\n");
            } else if (e.toString().contains("exists")) {
                builder.append(e.getMessage());
            }
            else {
                builder.append("Error: ").append(e).append("\n");
            }
            builder.append("Usage : /upload <file-path>\n");
            builder.append("<file-path> - should be the absolute path of the file to upload\n");
            builder.append("Make sure the file with the same name does not exist in the server\n");
            builder.append("Use /list to list all the files in the server\n");
            return builder.toString();
        }

        return result;
    }

    @ShellMethod(key = "/delete")
    public String delete(
            @ShellOption(value = "",
                    help = "/delete <file-name> - this command helps you delete a file from the server") String fileName) {
        String result = "";
        try {
            CompletableFuture<List<String>> future = new CompletableFuture<>();
            fileSystemService.delete(fileName, future);
            List<String> response = future.get();

            if (response.get(0).equals("SUCCESS")) {
                result = response.get(1);
            } else if (response.get(0).equals("ERROR")) {
                throw new IOException(response.get(1));
            }

        } catch (IOException | InterruptedException | ExecutionException e) {
            StringBuilder builder = new StringBuilder();
            builder.append("Error : ");
            builder.append(e.getMessage());
            builder.append("\nUsage : /delete <file-name>\n");
            builder.append("<file-name> must exist in the server and must be a file");
            builder.append("Use /list to list all the files in the server\n");
            return builder.toString();
        }

        return result;
    }

    @ShellMethod(value = "Rename files in the server", key = "/rename")
    public String rename(@ShellOption(value = "", help = "") String oldFileName, @ShellOption(value = " ", help = "") String newFileName) {

        String result = "";
        try {
            CompletableFuture<List<String>> future = new CompletableFuture<>();
            log.info("{} - {}", oldFileName, newFileName);
            fileSystemService.rename(oldFileName, newFileName, future);
            List<String> response = future.get();
            if (response.get(0).equals("SUCCESS")) {
                result = response.get(1);
            } else if (response.get(0).equals("ERROR")) {
                throw new IOException(response.get(1));
            }
        } catch (IOException | InterruptedException | ExecutionException e) {
            StringBuilder builder = new StringBuilder();
            builder.append("Error : ");
            builder.append(e.getMessage());
            builder.append("\nUsage : /rename <old-file-name> <new-file-name>\n");
            builder.append("<old-file-name> must exist in the server and must be a file");
            builder.append("Use /list to list all the files in the server\n");
            return builder.toString();
        }

        return result;
    }

    @ShellMethod(key = "/list")
    public String list() {
        CompletableFuture<List<String>> future = new CompletableFuture<>();
        StringBuilder builder = new StringBuilder();
        try {
            fileSystemService.getFiles(future);

            List<String> files = future.get();

            if (files.get(0).equals("ERROR")) {
                throw new IOException(files.get(1));
            }

            // Preparing the list to be printed back to the CLI
            builder.append("List of files\n");
            int i = 1;
            for (String file : files) {
                builder.append("\t").append(i++).append(". ").append(file).append("\n");
            }
        } catch (IOException | InterruptedException | ExecutionException e) {
            builder = new StringBuilder();
            builder.append("Error : ");
            builder.append(e.getMessage());
            return builder.toString();
        }

        return builder.toString();
    }
}
