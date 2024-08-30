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
    FileSyncGrpc.FileSyncBlockingStub stub;

    public FileSyncService() {
        channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        stub = FileSyncGrpc.newBlockingStub(channel);
    }

    public void createFile(String fileName) {
        CreateRequest request = CreateRequest.newBuilder().setFileName(fileName).build();
        log.info("Create Request Generated - {}", request);
        OperationResponse response = stub.create(request);
        log.info("Create Response - {}", response.getMessage());
    }

    public void deleteFile(String fileName) {
        DeleteRequest request = DeleteRequest.newBuilder().setFileName(fileName).build();
        log.info("Delete Request Generated - {}", request);
        OperationResponse response = stub.delete(request);
        log.info("Delete Response - {}", response.getMessage());
    }

    public void modifyFile(String fileName, FileContent fileContent) {
        ModifyRequest request = ModifyRequest.newBuilder().setFileName(fileName).setFile(fileContent).build();
        log.info("Modify Request Generated - {}", request);
        OperationResponse response = stub.modify(request);
        log.info("Modify Response - {}", response.getMessage());
    }
}
