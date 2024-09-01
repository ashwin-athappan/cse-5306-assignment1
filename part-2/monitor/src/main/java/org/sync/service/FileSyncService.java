package org.sync.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
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

    public void createFile(String fileName) {
        CreateRequest request = CreateRequest.newBuilder().setFileName(fileName).build();
        log.info("Create Request Generated - {}", request);
        asyncStub.create(request, new StreamObserver<OperationResponse>() {
            @Override
            public void onNext(OperationResponse response) {
                log.info("Create Response - {}", response.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("Create Failed - {}", throwable.getMessage());
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    public void deleteFile(String fileName) {
        DeleteRequest request = DeleteRequest.newBuilder().setFileName(fileName).build();
        log.info("Delete Request Generated - {}", request);
        asyncStub.delete(request, new StreamObserver<OperationResponse>() {

            @Override
            public void onNext(OperationResponse response) {
                log.info("Delete Response - {}", response.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("Delete Failed - {}", throwable.getMessage());
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    public void modifyFile(String fileName, FileContent fileContent) {
        ModifyRequest request = ModifyRequest.newBuilder().setFileName(fileName).setFile(fileContent).build();
        log.info("Modify Request Generated - {}", request);
        asyncStub.modify(request, new StreamObserver<OperationResponse>() {

            @Override
            public void onNext(OperationResponse response) {
                log.info("Modify Response - {}", response.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("Modify Failed - {}", throwable.getMessage());
            }

            @Override
            public void onCompleted() {

            }
        });
    }
}
