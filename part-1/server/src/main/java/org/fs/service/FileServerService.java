package org.fs.service;

import com.google.protobuf.ByteString;
import com.google.protobuf.Empty;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.ProtocolStringList;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.fs.proto.*;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;

@Slf4j
@GrpcService
@AllArgsConstructor
public class FileServerService extends FileSystemGrpc.FileSystemImplBase {
    private final Path rootDirectory;


    @Override
    public void upload(UploadRequest request, StreamObserver<OpResponse> responseObserver) {
        String fileName = request.getFileName();
        try {
            FileContent fileContent = request.getFileContent();
            ByteString byteStringContent = fileContent.getContent();
            byte[] content = byteStringContent.toByteArray();
            log.info("CREATE - File Received in Server : {}", fileName);
            File newFile = new File(rootDirectory.toString() + "/" + fileName);

            boolean result;

            if (newFile.exists()) {
                Metadata metadata = new Metadata();
                responseObserver.onError(Status.INTERNAL.withDescription("File " + fileName + " already exists").asRuntimeException(metadata));
                throw new FileAlreadyExistsException(fileName);
            }

            try (FileOutputStream fos = new FileOutputStream(rootDirectory.toString() + "/" + fileName)) {
                fos.write(content);
                result = true;
            }

            OpResponse opResponse = OpResponse.newBuilder()
                    .setStatus("File Created in Server : " + fileName)
                    .build();

            if (result) {
                responseObserver.onNext(opResponse);
                responseObserver.onCompleted();
            } else {
                responseObserver.onError(new Throwable("File Creation Failed"));
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void delete(DeleteRequest request, StreamObserver<OpResponse> responseObserver) {
        String fileName = request.getFileName();
        log.info("delete::File Received in Server : {}", fileName);
        try {
            File file = new File(rootDirectory.toString() + "/" + fileName);
            boolean exists = file.exists();
            boolean result = false;
            if (exists) {
                result = file.delete();
            } else {
                throw new FileNotFoundException("File Not Found");
            }

            OpResponse operationResponse = OpResponse.newBuilder()
                    .setStatus("File Deleted in Server : " + fileName)
                    .build();

            if (result) {
                responseObserver.onNext(operationResponse);
                responseObserver.onCompleted();
            }
        } catch (IOException e) {
            log.error("File Creation Failed", e);
            Metadata metadata = new Metadata();
            responseObserver.onError(io.grpc.Status.INTERNAL.withDescription("File does not exist in the server").asRuntimeException(metadata));
        }
        log.info("delete::Delete successful");
    }

    @Override
    public void rename(RenameRequest request, StreamObserver<OpResponse> responseObserver) {
        String oldFileName = request.getOldFileName();
        String newFileName = request.getNewFileName();
        log.info("rename::Rename file {} to {}", oldFileName, newFileName);
        File oldFile = new File(rootDirectory.toString() + "/" + oldFileName);
        File newFile = new File(rootDirectory.toString() + "/" + newFileName);
        boolean result = oldFile.renameTo(newFile);

        OpResponse operationResponse = OpResponse.newBuilder()
                .setStatus("Rename successful : " + oldFileName + " to " + newFileName)
                .build();

        if (result) {
            responseObserver.onNext(operationResponse);
            responseObserver.onCompleted();
        } else {
            Metadata metadata = new Metadata();
            responseObserver.onError(Status.INTERNAL.withDescription("File rename failed").asRuntimeException(metadata));
        }
        log.info("rename::Rename successful");
    }

    @Override
    public void getFiles(Empty request, StreamObserver<FilesList> responseObserver) {
        log.info("getFiles::Getting FilesList");
        try {
            File folder = new File(rootDirectory.toFile().getAbsolutePath());
            if (!folder.exists()) {
                throw new FileNotFoundException("Folder not found : " + rootDirectory.toFile().getAbsolutePath());
            }
            File[] files = folder.listFiles();
            FilesList.Builder builder = FilesList.newBuilder();
            assert files != null;
            for (File file: files) {
                String fileName = file.getName();
                builder.addFiles(fileName);
            }
            FilesList filesList = builder.build();
            responseObserver.onNext(filesList);
            responseObserver.onCompleted();
        } catch (IOException e) {
            Metadata metadata = new Metadata();
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException(metadata));
        }
        log.info("getFiles::FilesList returned successfully");
    }
}
