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

@Slf4j
@Service
public class FileSystemService {
    private ManagedChannel channel;
    private FileSystemGrpc.FileSystemStub asyncStub;

    @Getter
    @Setter
    private List<String> listOfFiles;

    @Getter
    @Setter
    private String response;

    @Getter
    @Setter
    private String error;

    public FileSystemService() {
        channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        asyncStub = FileSystemGrpc.newStub(channel);
        listOfFiles = null;
        error = "";
        response = "";
    }

    public void upload(String filePath) {
        FileContent fc = Utilities.getFileContent(filePath);
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        UploadRequest request = UploadRequest.newBuilder().setFileName(fileName).setFileContent(fc).build();
        asyncStub.upload(request, new StreamObserver<OpResponse>() {
            @Override
            public void onNext(OpResponse opResponse) {
                response = opResponse.getStatus();
            }

            @Override
            public void onError(Throwable throwable) {
                Status s = Status.fromThrowable(throwable);
                error = s.getDescription();
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    public void delete(String fileName) {
        DeleteRequest request = DeleteRequest.newBuilder().setFileName(fileName).build();
        asyncStub.delete(request, new StreamObserver<OpResponse>() {
            @Override
            public void onNext(OpResponse opResponse) {
                response = opResponse.getStatus();
            }

            @Override
            public void onError(Throwable throwable) {
                Status s = Status.fromThrowable(throwable);
                error = s.getDescription();
            }

            @Override
            public void onCompleted() {

            }
        });

    }

    public void rename(String oldName, String newName) {
        RenameRequest request = RenameRequest.newBuilder().setOldFileName(oldName).setNewFileName(newName).build();
        asyncStub.rename(request, new StreamObserver<OpResponse>() {
            @Override
            public void onNext(OpResponse opResponse) {
                response = opResponse.getStatus();
            }

            @Override
            public void onError(Throwable throwable) {
                Status s = Status.fromThrowable(throwable);
                error = s.getDescription();
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    public void getFiles() {
        asyncStub.getFiles(Empty.getDefaultInstance(), new StreamObserver<FilesList>() {
            @Override
            public void onNext(FilesList filesList) {
                List<String> files = new ArrayList<>();
                log.debug("LIST - {}", listOfFiles);
                int n = filesList.getFilesCount();
                for (int i = 0; i < n; i++) {
                    files.add(filesList.getFiles(i));
                }
                log.debug(Arrays.toString(files.toArray()));
                setListOfFiles(files);
            }

            @Override
            public void onError(Throwable throwable) {
                Status s = Status.fromThrowable(throwable);
                error = s.getDescription();
            }

            @Override
            public void onCompleted() {

            }
        });
    }

}
