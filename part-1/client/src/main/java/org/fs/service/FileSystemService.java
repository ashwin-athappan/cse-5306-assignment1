package org.fs.service;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.fs.proto.*;
import org.fs.utilities.Utilities;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class FileSystemService {
    private ManagedChannel channel;
    private FileSystemGrpc.FileSystemStub asyncStub;

    public FileSystemService() {
        channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        asyncStub = FileSystemGrpc.newStub(channel);
    }

    public void upload(String filePath, CompletableFuture<List<String>> future) {
        FileContent fc = Utilities.getFileContent(filePath);
        String fileName = "";
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("win")) {
            fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
        } else {
            fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        }
        UploadRequest request = UploadRequest.newBuilder().setFileName(fileName).setFileContent(fc).build();
        asyncStub.upload(request, new StreamObserver<OpResponse>() {
            @Override
            public void onNext(OpResponse opResponse) {
                List<String> response = new ArrayList<>();
                response.add("SUCCESS");
                response.add(opResponse.getStatus());
                future.complete(response);
            }

            @Override
            public void onError(Throwable throwable) {
                Status s = Status.fromThrowable(throwable);
                List<String> errors = new ArrayList<>();
                errors.add("ERROR");
                errors.add(s.getDescription());
                future.complete(errors);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    public void delete(String fileName, CompletableFuture<List<String>> future) {
        DeleteRequest request = DeleteRequest.newBuilder().setFileName(fileName).build();
        asyncStub.delete(request, new StreamObserver<OpResponse>() {
            @Override
            public void onNext(OpResponse opResponse) {
                List<String> response = new ArrayList<>();
                response.add("SUCCESS");
                response.add(opResponse.getStatus());
                future.complete(response);
            }

            @Override
            public void onError(Throwable throwable) {
                Status s = Status.fromThrowable(throwable);
                List<String> errors = new ArrayList<>();
                errors.add("ERROR");
                errors.add(s.getDescription());
                future.complete(errors);
            }

            @Override
            public void onCompleted() {

            }
        });

    }

    public void rename(String oldName, String newName, CompletableFuture<List<String>> future) {
        RenameRequest request = RenameRequest.newBuilder().setOldFileName(oldName).setNewFileName(newName).build();
        asyncStub.rename(request, new StreamObserver<OpResponse>() {
            @Override
            public void onNext(OpResponse opResponse) {
                List<String> response = new ArrayList<>();
                response.add("SUCCESS");
                response.add(opResponse.getStatus());
                future.complete(response);
            }

            @Override
            public void onError(Throwable throwable) {
                Status s = Status.fromThrowable(throwable);
                List<String> errors = new ArrayList<>();
                errors.add("ERROR");
                errors.add(s.getDescription());
                future.complete(errors);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    public void getFiles(CompletableFuture<List<String>> future) {
        asyncStub.getFiles(Empty.getDefaultInstance(), new StreamObserver<FilesList>() {
            @Override
            public void onNext(FilesList filesList) {
                List<String> files = new ArrayList<>();
                int n = filesList.getFilesCount();
                for (int i = 0; i < n; i++) {
                    files.add(filesList.getFiles(i));
                }
                log.debug(Arrays.toString(files.toArray()));
                future.complete(files);
            }

            @Override
            public void onError(Throwable throwable) {
                Status s = Status.fromThrowable(throwable);
                List<String> errors = new ArrayList<>();
                errors.add("ERROR");
                errors.add(s.getDescription());
                future.complete(errors);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

}
