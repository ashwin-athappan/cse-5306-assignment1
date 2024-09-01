package org.sync.service;

import com.google.protobuf.ByteString;
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

    private final Path rootDirectory;

    @Override
    public void create(CreateRequest request, StreamObserver<OperationResponse> responseObserver) {
        try {
            String fileName = request.getFileName();
            log.info("CREATE - File Received in Server : {}", fileName);

            File newFile = new File(rootDirectory.toString() + "/" + fileName);
            boolean result = newFile.createNewFile();

            OperationResponse operationResponse = OperationResponse.newBuilder()
                    .setMessage("File Created in Server : " + fileName)
                    .setStatus(Status.SUCCESS).build();

            if (result) {
                responseObserver.onNext(operationResponse);
                responseObserver.onCompleted();
            } else {
                responseObserver.onError(new Throwable("File Creation Failed"));
            }

        } catch (IOException e) {
            log.error("File Creation Failed", e);
            responseObserver.onError(new Throwable("File Creation Failed"));
        }
    }

    @Override
    public void delete(DeleteRequest request, StreamObserver<OperationResponse> responseObserver) {
        try {
            String fileName = request.getFileName();
            log.info("DELETE - File Received in Server : {}", fileName);

            File newFile = new File(rootDirectory.toString() + "/" + fileName);
            boolean exists = newFile.exists();
            boolean result = false;
            if (exists) {
                result = newFile.delete();
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
            log.error("File Creation Failed", e);
            responseObserver.onError(new Throwable("File does not exist in the server"));
        }
    }

    @Override
    public void modify(ModifyRequest request, StreamObserver<OperationResponse> responseObserver) {
        try {
            String fileName = request.getFileName();
            FileContent fileContent = request.getFile();
            ByteString byteStringContent = fileContent.getContent();
            byte[] content = byteStringContent.toByteArray();
            log.info("MODIFY - File Received in Server : {}", fileName);
            boolean result = false;
            try (FileOutputStream fos = new FileOutputStream(rootDirectory.toString() + "/" + fileName)) {
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
                responseObserver.onError(new Throwable("File Modification Failed"));
            }

        } catch (IOException e) {
            log.error("File Modification Failed", e);
            responseObserver.onError(new Throwable("File does not exist in the server"));
        }
    }

    @Override
    public void rename(RenameRequest request, StreamObserver<OperationResponse> responseObserver) {
        try {
            String oldFileName = request.getOldFileName();
            String newFileName = request.getNewFileName();
            log.info("RENAME - Request received in Server - Renaming : {} to {}", oldFileName, newFileName);
            File oldFile = new File(rootDirectory.toString() + "/" + oldFileName);
            File newFile = new File(rootDirectory.toString() + "/" + newFileName);
            boolean result = oldFile.renameTo(newFile);

            OperationResponse operationResponse = OperationResponse.newBuilder()
                    .setMessage("Rename successful : " + oldFileName + " to " + newFileName)
                    .setStatus(Status.SUCCESS).build();

            if (result) {
                responseObserver.onNext(operationResponse);
                responseObserver.onCompleted();
            } else {
                responseObserver.onError(new Throwable("File Rename Failed"));
            }
        } catch (Exception e) {
            log.error("File Rename Failed", e);
            responseObserver.onError(new Throwable("File does not exist in the server"));
        }
    }
}
