package org.sync.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.sync.proto.*;

@Slf4j
@Service
public class FileSyncService {

    private final ManagedChannel channel;
    FileSyncGrpc.FileSyncStub asyncStub;

    public FileSyncService() {
        channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        asyncStub = FileSyncGrpc.newStub(channel);
    }

    /**
     * @description This function is a wrapper to call the server side implementation of createFile
     *
     * @param fileName This is name of the file to be created
     * @param fileContent This is the content of the file to be created
     */
    public void createFile(String fileName, FileContent fileContent) {
        CreateRequest request = CreateRequest.newBuilder().setFileName(fileName).setFile(fileContent).build();
        log.info("createFile::Create Request Generated - {}", request);
        asyncStub.create(request, new StreamObserver<OperationResponse>() {
            @Override
            public void onNext(OperationResponse response) {
                log.info("createFile::Create Response - {}", response.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("createFile::Create Failed - {}", throwable.getMessage());
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    /**
     * @description This function is a wrapper to call the server side implementation of deleteFile
     *
     * @param fileName This is name of the file to be deleted
     */
    public void deleteFile(String fileName) {
        DeleteRequest request = DeleteRequest.newBuilder().setFileName(fileName).build();
        log.info("deleteFile::Delete Request Generated - {}", request);
        asyncStub.delete(request, new StreamObserver<OperationResponse>() {

            @Override
            public void onNext(OperationResponse response) {
                log.info("deleteFile::Delete Response - {}", response.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("deleteFile::Delete Failed - {}", throwable.getMessage());
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    /**
     * @description This function is a wrapper to call the server side implementation of modifyFile
     *
     * @param fileName This is name of the file to be modified
     * @param fileContent This is the content of the file to be modified
     */
    public void modifyFile(String fileName, FileContent fileContent) {
        ModifyRequest request = ModifyRequest.newBuilder().setFileName(fileName).setFile(fileContent).build();
        log.info("modifyFile::Modify Request Generated");
        asyncStub.modify(request, new StreamObserver<OperationResponse>() {

            @Override
            public void onNext(OperationResponse response) {
                log.info("modifyFile::Modify Response - {}", response.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("modifyFile::Modify Failed - {}", throwable.getMessage());
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    /**
     * @description This function is a wrapper to call the server side implementation of renameFile
     *
     * @param oldFileName This is current name of the file in the server
     * @param newFileName This is the name that the file will be renamed to
     */
    public void renameFile(String oldFileName, String newFileName) {
        RenameRequest request = RenameRequest.newBuilder().setOldFileName(oldFileName).setNewFileName(newFileName).build();
        log.info("renameFile::Rename Request Generated - {}", request);
        asyncStub.rename(request, new StreamObserver<OperationResponse>() {
            @Override
            public void onNext(OperationResponse operationResponse) {
                log.info("renameFile::Rename Response - {}", operationResponse.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("renameFile::Rename Failed - {}", throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                log.info("renameFile::Rename Completed");
            }
        });
    }
}
