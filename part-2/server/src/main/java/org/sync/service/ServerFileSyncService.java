package org.sync.service;

import com.google.protobuf.ByteString;
import io.grpc.Metadata;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.sync.proto.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

@Slf4j
@GrpcService
@AllArgsConstructor
public class ServerFileSyncService extends FileSyncGrpc.FileSyncImplBase {

    private final String rootDirectory;

    /**
     * @description This is the grpc implementation for creating a file.
     *
     * @param request This is the request coming in from the client
     * @param responseObserver This is the observer sent by the client.
     *                         This client uses it so that it does not have to wait for the response.
     *                         This makes the process Asynchronous.
     *                         The response is sent back through the observer.
     */
    @Override
    public void create(CreateRequest request, StreamObserver<OperationResponse> responseObserver) {
        try {
            String fileName = request.getFileName();
            FileContent fileContent = request.getFile();
            ByteString byteStringContent = fileContent.getContent();
            byte[] content = byteStringContent.toByteArray();
            log.info("CREATE - File Received in Server : {}", fileName);
            File newFile = new File(rootDirectory + "/" + fileName);

            try (FileOutputStream fos = new FileOutputStream(rootDirectory + "/" + fileName)) {
                fos.write(content);
            }

            OperationResponse operationResponse = OperationResponse.newBuilder()
                    .setMessage("File Created in Server : " + fileName)
                    .setStatus(Status.SUCCESS).build();

            responseObserver.onNext(operationResponse);
            responseObserver.onCompleted();

        } catch (IOException e) {
            log.error("File Creation Failed", e);
            Metadata metadata = new Metadata();
            responseObserver.onError(io.grpc.Status.INTERNAL.withDescription("File Creation Failed").asRuntimeException(metadata));
        }
    }

    /**
     * @description This is the grpc implementation for deleting a file.
     *
     * @param request This is the request coming in from the client
     * @param responseObserver This is the observer sent by the client.
     *                         This client uses it so that it does not have to wait for the response.
     *                         This makes the process Asynchronous.
     *                         The response is sent back through the observer.
     */
    @Override
    public void delete(DeleteRequest request, StreamObserver<OperationResponse> responseObserver) {
        try {
            String fileName = request.getFileName();
            log.info("DELETE - File Received in Server : {}", fileName);
            File file = new File(rootDirectory + "/" + fileName);
            boolean exists = file.exists();
            boolean result = false;
            if (exists) {
                result = file.delete();
            } else {
                throw new FileNotFoundException("File Not Found");
            }

            OperationResponse operationResponse = OperationResponse.newBuilder()
                    .setMessage("File Deleted in Server : " + fileName)
                    .setStatus(Status.SUCCESS).build();

            if (result) {
                responseObserver.onNext(operationResponse);
                responseObserver.onCompleted();
            }

        } catch (IOException e) {
            log.error("File Deletion Failed", e);
            Metadata metadata = new Metadata();
            responseObserver.onError(io.grpc.Status.INTERNAL.withDescription("File does not exist in the server").asRuntimeException(metadata));
        }
    }

    /**
     * @description This is the grpc implementation for modifying a file.
     *
     * @param request This is the request coming in from the client
     * @param responseObserver This is the observer sent by the client.
     *                         This client uses it so that it does not have to wait for the response.
     *                         This makes the process Asynchronous.
     *                         The response is sent back through the observer.
     */
    @Override
    public void modify(ModifyRequest request, StreamObserver<OperationResponse> responseObserver) {
        try {
            String fileName = request.getFileName();
            FileContent fileContent = request.getFile();
            ByteString byteStringContent = fileContent.getContent();
            byte[] content = byteStringContent.toByteArray();
            log.info("MODIFY - File Received in Server : {}", fileName);

            boolean result;
            try (FileOutputStream fos = new FileOutputStream(rootDirectory + "/" + fileName)) {
                fos.write(content);
                result = true;
            }

            OperationResponse operationResponse = OperationResponse.newBuilder()
                    .setMessage("File Modified in Server : " + fileName)
                    .setStatus(Status.SUCCESS).build();

            if (result) {
                responseObserver.onNext(operationResponse);
                responseObserver.onCompleted();
            } else {
                Metadata metadata = new Metadata();
                responseObserver.onError(io.grpc.Status.INTERNAL.withDescription("File Modification Failed").asRuntimeException(metadata));
            }

        } catch (IOException e) {
            log.error("File Modification Failed", e);
            Metadata metadata = new Metadata();
            responseObserver.onError(io.grpc.Status.INTERNAL.withDescription("File Modification Failed").asRuntimeException(metadata));
        }
    }

    /**
     * @description This is the grpc implementation for renaming a file.
     *
     * @param request This is the request coming in from the client
     * @param responseObserver This is the observer sent by the client.
     *                         This client uses it so that it does not have to wait for the response.
     *                         This makes the process Asynchronous.
     *                         The response is sent back through the observer.
     */
    @Override
    public void rename(RenameRequest request, StreamObserver<OperationResponse> responseObserver) {
        try {
            String oldFileName = request.getOldFileName();
            String newFileName = request.getNewFileName();
            log.info("RENAME - Request received in Server - Renaming : {} to {}", oldFileName, newFileName);
            File oldFile = new File(rootDirectory + "/" + oldFileName);
            File newFile = new File(rootDirectory + "/" + newFileName);
            boolean result = oldFile.renameTo(newFile);

            OperationResponse operationResponse = OperationResponse.newBuilder()
                    .setMessage("Rename successful : " + oldFileName + " to " + newFileName)
                    .setStatus(Status.SUCCESS).build();

            if (result) {
                responseObserver.onNext(operationResponse);
                responseObserver.onCompleted();
            } else {
                Metadata metadata = new Metadata();
                responseObserver.onError(io.grpc.Status.INTERNAL.withDescription("File Rename Failed").asRuntimeException(metadata));
            }
        } catch (Exception e) {
            log.error("File does not exist in the server: ", e);
            Metadata metadata = new Metadata();
            responseObserver.onError(io.grpc.Status.INTERNAL.withDescription("File does not exist in the server").asRuntimeException(metadata));
        }
    }
}
