package org.fs.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.57.2)",
    comments = "Source: fileSystem.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class FileSystemGrpc {

  private FileSystemGrpc() {}

  public static final java.lang.String SERVICE_NAME = "org.fs.proto.FileSystem";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.fs.proto.UploadRequest,
      org.fs.proto.OpResponse> getUploadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "upload",
      requestType = org.fs.proto.UploadRequest.class,
      responseType = org.fs.proto.OpResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.fs.proto.UploadRequest,
      org.fs.proto.OpResponse> getUploadMethod() {
    io.grpc.MethodDescriptor<org.fs.proto.UploadRequest, org.fs.proto.OpResponse> getUploadMethod;
    if ((getUploadMethod = FileSystemGrpc.getUploadMethod) == null) {
      synchronized (FileSystemGrpc.class) {
        if ((getUploadMethod = FileSystemGrpc.getUploadMethod) == null) {
          FileSystemGrpc.getUploadMethod = getUploadMethod =
              io.grpc.MethodDescriptor.<org.fs.proto.UploadRequest, org.fs.proto.OpResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "upload"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.fs.proto.UploadRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.fs.proto.OpResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FileSystemMethodDescriptorSupplier("upload"))
              .build();
        }
      }
    }
    return getUploadMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.fs.proto.DeleteRequest,
      org.fs.proto.OpResponse> getDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "delete",
      requestType = org.fs.proto.DeleteRequest.class,
      responseType = org.fs.proto.OpResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.fs.proto.DeleteRequest,
      org.fs.proto.OpResponse> getDeleteMethod() {
    io.grpc.MethodDescriptor<org.fs.proto.DeleteRequest, org.fs.proto.OpResponse> getDeleteMethod;
    if ((getDeleteMethod = FileSystemGrpc.getDeleteMethod) == null) {
      synchronized (FileSystemGrpc.class) {
        if ((getDeleteMethod = FileSystemGrpc.getDeleteMethod) == null) {
          FileSystemGrpc.getDeleteMethod = getDeleteMethod =
              io.grpc.MethodDescriptor.<org.fs.proto.DeleteRequest, org.fs.proto.OpResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "delete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.fs.proto.DeleteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.fs.proto.OpResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FileSystemMethodDescriptorSupplier("delete"))
              .build();
        }
      }
    }
    return getDeleteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.fs.proto.RenameRequest,
      org.fs.proto.OpResponse> getRenameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rename",
      requestType = org.fs.proto.RenameRequest.class,
      responseType = org.fs.proto.OpResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.fs.proto.RenameRequest,
      org.fs.proto.OpResponse> getRenameMethod() {
    io.grpc.MethodDescriptor<org.fs.proto.RenameRequest, org.fs.proto.OpResponse> getRenameMethod;
    if ((getRenameMethod = FileSystemGrpc.getRenameMethod) == null) {
      synchronized (FileSystemGrpc.class) {
        if ((getRenameMethod = FileSystemGrpc.getRenameMethod) == null) {
          FileSystemGrpc.getRenameMethod = getRenameMethod =
              io.grpc.MethodDescriptor.<org.fs.proto.RenameRequest, org.fs.proto.OpResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "rename"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.fs.proto.RenameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.fs.proto.OpResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FileSystemMethodDescriptorSupplier("rename"))
              .build();
        }
      }
    }
    return getRenameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      org.fs.proto.FilesList> getGetFilesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getFiles",
      requestType = com.google.protobuf.Empty.class,
      responseType = org.fs.proto.FilesList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      org.fs.proto.FilesList> getGetFilesMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, org.fs.proto.FilesList> getGetFilesMethod;
    if ((getGetFilesMethod = FileSystemGrpc.getGetFilesMethod) == null) {
      synchronized (FileSystemGrpc.class) {
        if ((getGetFilesMethod = FileSystemGrpc.getGetFilesMethod) == null) {
          FileSystemGrpc.getGetFilesMethod = getGetFilesMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, org.fs.proto.FilesList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getFiles"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.fs.proto.FilesList.getDefaultInstance()))
              .setSchemaDescriptor(new FileSystemMethodDescriptorSupplier("getFiles"))
              .build();
        }
      }
    }
    return getGetFilesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FileSystemStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FileSystemStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FileSystemStub>() {
        @java.lang.Override
        public FileSystemStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FileSystemStub(channel, callOptions);
        }
      };
    return FileSystemStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FileSystemBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FileSystemBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FileSystemBlockingStub>() {
        @java.lang.Override
        public FileSystemBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FileSystemBlockingStub(channel, callOptions);
        }
      };
    return FileSystemBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FileSystemFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FileSystemFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FileSystemFutureStub>() {
        @java.lang.Override
        public FileSystemFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FileSystemFutureStub(channel, callOptions);
        }
      };
    return FileSystemFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void upload(org.fs.proto.UploadRequest request,
        io.grpc.stub.StreamObserver<org.fs.proto.OpResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUploadMethod(), responseObserver);
    }

    /**
     */
    default void delete(org.fs.proto.DeleteRequest request,
        io.grpc.stub.StreamObserver<org.fs.proto.OpResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteMethod(), responseObserver);
    }

    /**
     */
    default void rename(org.fs.proto.RenameRequest request,
        io.grpc.stub.StreamObserver<org.fs.proto.OpResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRenameMethod(), responseObserver);
    }

    /**
     */
    default void getFiles(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<org.fs.proto.FilesList> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetFilesMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service FileSystem.
   */
  public static abstract class FileSystemImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return FileSystemGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service FileSystem.
   */
  public static final class FileSystemStub
      extends io.grpc.stub.AbstractAsyncStub<FileSystemStub> {
    private FileSystemStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileSystemStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FileSystemStub(channel, callOptions);
    }

    /**
     */
    public void upload(org.fs.proto.UploadRequest request,
        io.grpc.stub.StreamObserver<org.fs.proto.OpResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUploadMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delete(org.fs.proto.DeleteRequest request,
        io.grpc.stub.StreamObserver<org.fs.proto.OpResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rename(org.fs.proto.RenameRequest request,
        io.grpc.stub.StreamObserver<org.fs.proto.OpResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRenameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getFiles(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<org.fs.proto.FilesList> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetFilesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service FileSystem.
   */
  public static final class FileSystemBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<FileSystemBlockingStub> {
    private FileSystemBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileSystemBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FileSystemBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.fs.proto.OpResponse upload(org.fs.proto.UploadRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUploadMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.fs.proto.OpResponse delete(org.fs.proto.DeleteRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.fs.proto.OpResponse rename(org.fs.proto.RenameRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRenameMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.fs.proto.FilesList getFiles(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetFilesMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service FileSystem.
   */
  public static final class FileSystemFutureStub
      extends io.grpc.stub.AbstractFutureStub<FileSystemFutureStub> {
    private FileSystemFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileSystemFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FileSystemFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.fs.proto.OpResponse> upload(
        org.fs.proto.UploadRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUploadMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.fs.proto.OpResponse> delete(
        org.fs.proto.DeleteRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.fs.proto.OpResponse> rename(
        org.fs.proto.RenameRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRenameMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.fs.proto.FilesList> getFiles(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetFilesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_UPLOAD = 0;
  private static final int METHODID_DELETE = 1;
  private static final int METHODID_RENAME = 2;
  private static final int METHODID_GET_FILES = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPLOAD:
          serviceImpl.upload((org.fs.proto.UploadRequest) request,
              (io.grpc.stub.StreamObserver<org.fs.proto.OpResponse>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((org.fs.proto.DeleteRequest) request,
              (io.grpc.stub.StreamObserver<org.fs.proto.OpResponse>) responseObserver);
          break;
        case METHODID_RENAME:
          serviceImpl.rename((org.fs.proto.RenameRequest) request,
              (io.grpc.stub.StreamObserver<org.fs.proto.OpResponse>) responseObserver);
          break;
        case METHODID_GET_FILES:
          serviceImpl.getFiles((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<org.fs.proto.FilesList>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getUploadMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.fs.proto.UploadRequest,
              org.fs.proto.OpResponse>(
                service, METHODID_UPLOAD)))
        .addMethod(
          getDeleteMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.fs.proto.DeleteRequest,
              org.fs.proto.OpResponse>(
                service, METHODID_DELETE)))
        .addMethod(
          getRenameMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.fs.proto.RenameRequest,
              org.fs.proto.OpResponse>(
                service, METHODID_RENAME)))
        .addMethod(
          getGetFilesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              org.fs.proto.FilesList>(
                service, METHODID_GET_FILES)))
        .build();
  }

  private static abstract class FileSystemBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FileSystemBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.fs.proto.FileSystemOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FileSystem");
    }
  }

  private static final class FileSystemFileDescriptorSupplier
      extends FileSystemBaseDescriptorSupplier {
    FileSystemFileDescriptorSupplier() {}
  }

  private static final class FileSystemMethodDescriptorSupplier
      extends FileSystemBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    FileSystemMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FileSystemGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FileSystemFileDescriptorSupplier())
              .addMethod(getUploadMethod())
              .addMethod(getDeleteMethod())
              .addMethod(getRenameMethod())
              .addMethod(getGetFilesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
