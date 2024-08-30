package org.sync.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.57.2)",
    comments = "Source: fileSync.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class FileSyncGrpc {

  private FileSyncGrpc() {}

  public static final java.lang.String SERVICE_NAME = "org.sync.proto.FileSync";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.sync.proto.CreateRequest,
      org.sync.proto.OperationResponse> getCreateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "create",
      requestType = org.sync.proto.CreateRequest.class,
      responseType = org.sync.proto.OperationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.sync.proto.CreateRequest,
      org.sync.proto.OperationResponse> getCreateMethod() {
    io.grpc.MethodDescriptor<org.sync.proto.CreateRequest, org.sync.proto.OperationResponse> getCreateMethod;
    if ((getCreateMethod = FileSyncGrpc.getCreateMethod) == null) {
      synchronized (FileSyncGrpc.class) {
        if ((getCreateMethod = FileSyncGrpc.getCreateMethod) == null) {
          FileSyncGrpc.getCreateMethod = getCreateMethod =
              io.grpc.MethodDescriptor.<org.sync.proto.CreateRequest, org.sync.proto.OperationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "create"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.sync.proto.CreateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.sync.proto.OperationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FileSyncMethodDescriptorSupplier("create"))
              .build();
        }
      }
    }
    return getCreateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.sync.proto.ModifyRequest,
      org.sync.proto.OperationResponse> getModifyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "modify",
      requestType = org.sync.proto.ModifyRequest.class,
      responseType = org.sync.proto.OperationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.sync.proto.ModifyRequest,
      org.sync.proto.OperationResponse> getModifyMethod() {
    io.grpc.MethodDescriptor<org.sync.proto.ModifyRequest, org.sync.proto.OperationResponse> getModifyMethod;
    if ((getModifyMethod = FileSyncGrpc.getModifyMethod) == null) {
      synchronized (FileSyncGrpc.class) {
        if ((getModifyMethod = FileSyncGrpc.getModifyMethod) == null) {
          FileSyncGrpc.getModifyMethod = getModifyMethod =
              io.grpc.MethodDescriptor.<org.sync.proto.ModifyRequest, org.sync.proto.OperationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "modify"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.sync.proto.ModifyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.sync.proto.OperationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FileSyncMethodDescriptorSupplier("modify"))
              .build();
        }
      }
    }
    return getModifyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.sync.proto.DeleteRequest,
      org.sync.proto.OperationResponse> getDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "delete",
      requestType = org.sync.proto.DeleteRequest.class,
      responseType = org.sync.proto.OperationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.sync.proto.DeleteRequest,
      org.sync.proto.OperationResponse> getDeleteMethod() {
    io.grpc.MethodDescriptor<org.sync.proto.DeleteRequest, org.sync.proto.OperationResponse> getDeleteMethod;
    if ((getDeleteMethod = FileSyncGrpc.getDeleteMethod) == null) {
      synchronized (FileSyncGrpc.class) {
        if ((getDeleteMethod = FileSyncGrpc.getDeleteMethod) == null) {
          FileSyncGrpc.getDeleteMethod = getDeleteMethod =
              io.grpc.MethodDescriptor.<org.sync.proto.DeleteRequest, org.sync.proto.OperationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "delete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.sync.proto.DeleteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.sync.proto.OperationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FileSyncMethodDescriptorSupplier("delete"))
              .build();
        }
      }
    }
    return getDeleteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.sync.proto.RenameRequest,
      org.sync.proto.OperationResponse> getRenameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rename",
      requestType = org.sync.proto.RenameRequest.class,
      responseType = org.sync.proto.OperationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.sync.proto.RenameRequest,
      org.sync.proto.OperationResponse> getRenameMethod() {
    io.grpc.MethodDescriptor<org.sync.proto.RenameRequest, org.sync.proto.OperationResponse> getRenameMethod;
    if ((getRenameMethod = FileSyncGrpc.getRenameMethod) == null) {
      synchronized (FileSyncGrpc.class) {
        if ((getRenameMethod = FileSyncGrpc.getRenameMethod) == null) {
          FileSyncGrpc.getRenameMethod = getRenameMethod =
              io.grpc.MethodDescriptor.<org.sync.proto.RenameRequest, org.sync.proto.OperationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "rename"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.sync.proto.RenameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.sync.proto.OperationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FileSyncMethodDescriptorSupplier("rename"))
              .build();
        }
      }
    }
    return getRenameMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FileSyncStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FileSyncStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FileSyncStub>() {
        @java.lang.Override
        public FileSyncStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FileSyncStub(channel, callOptions);
        }
      };
    return FileSyncStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FileSyncBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FileSyncBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FileSyncBlockingStub>() {
        @java.lang.Override
        public FileSyncBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FileSyncBlockingStub(channel, callOptions);
        }
      };
    return FileSyncBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FileSyncFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FileSyncFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FileSyncFutureStub>() {
        @java.lang.Override
        public FileSyncFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FileSyncFutureStub(channel, callOptions);
        }
      };
    return FileSyncFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void create(org.sync.proto.CreateRequest request,
        io.grpc.stub.StreamObserver<org.sync.proto.OperationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateMethod(), responseObserver);
    }

    /**
     */
    default void modify(org.sync.proto.ModifyRequest request,
        io.grpc.stub.StreamObserver<org.sync.proto.OperationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getModifyMethod(), responseObserver);
    }

    /**
     */
    default void delete(org.sync.proto.DeleteRequest request,
        io.grpc.stub.StreamObserver<org.sync.proto.OperationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteMethod(), responseObserver);
    }

    /**
     */
    default void rename(org.sync.proto.RenameRequest request,
        io.grpc.stub.StreamObserver<org.sync.proto.OperationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRenameMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service FileSync.
   */
  public static abstract class FileSyncImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return FileSyncGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service FileSync.
   */
  public static final class FileSyncStub
      extends io.grpc.stub.AbstractAsyncStub<FileSyncStub> {
    private FileSyncStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileSyncStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FileSyncStub(channel, callOptions);
    }

    /**
     */
    public void create(org.sync.proto.CreateRequest request,
        io.grpc.stub.StreamObserver<org.sync.proto.OperationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void modify(org.sync.proto.ModifyRequest request,
        io.grpc.stub.StreamObserver<org.sync.proto.OperationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getModifyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delete(org.sync.proto.DeleteRequest request,
        io.grpc.stub.StreamObserver<org.sync.proto.OperationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rename(org.sync.proto.RenameRequest request,
        io.grpc.stub.StreamObserver<org.sync.proto.OperationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRenameMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service FileSync.
   */
  public static final class FileSyncBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<FileSyncBlockingStub> {
    private FileSyncBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileSyncBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FileSyncBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.sync.proto.OperationResponse create(org.sync.proto.CreateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.sync.proto.OperationResponse modify(org.sync.proto.ModifyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getModifyMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.sync.proto.OperationResponse delete(org.sync.proto.DeleteRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.sync.proto.OperationResponse rename(org.sync.proto.RenameRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRenameMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service FileSync.
   */
  public static final class FileSyncFutureStub
      extends io.grpc.stub.AbstractFutureStub<FileSyncFutureStub> {
    private FileSyncFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileSyncFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FileSyncFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.sync.proto.OperationResponse> create(
        org.sync.proto.CreateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.sync.proto.OperationResponse> modify(
        org.sync.proto.ModifyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getModifyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.sync.proto.OperationResponse> delete(
        org.sync.proto.DeleteRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.sync.proto.OperationResponse> rename(
        org.sync.proto.RenameRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRenameMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE = 0;
  private static final int METHODID_MODIFY = 1;
  private static final int METHODID_DELETE = 2;
  private static final int METHODID_RENAME = 3;

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
        case METHODID_CREATE:
          serviceImpl.create((org.sync.proto.CreateRequest) request,
              (io.grpc.stub.StreamObserver<org.sync.proto.OperationResponse>) responseObserver);
          break;
        case METHODID_MODIFY:
          serviceImpl.modify((org.sync.proto.ModifyRequest) request,
              (io.grpc.stub.StreamObserver<org.sync.proto.OperationResponse>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((org.sync.proto.DeleteRequest) request,
              (io.grpc.stub.StreamObserver<org.sync.proto.OperationResponse>) responseObserver);
          break;
        case METHODID_RENAME:
          serviceImpl.rename((org.sync.proto.RenameRequest) request,
              (io.grpc.stub.StreamObserver<org.sync.proto.OperationResponse>) responseObserver);
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
          getCreateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.sync.proto.CreateRequest,
              org.sync.proto.OperationResponse>(
                service, METHODID_CREATE)))
        .addMethod(
          getModifyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.sync.proto.ModifyRequest,
              org.sync.proto.OperationResponse>(
                service, METHODID_MODIFY)))
        .addMethod(
          getDeleteMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.sync.proto.DeleteRequest,
              org.sync.proto.OperationResponse>(
                service, METHODID_DELETE)))
        .addMethod(
          getRenameMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.sync.proto.RenameRequest,
              org.sync.proto.OperationResponse>(
                service, METHODID_RENAME)))
        .build();
  }

  private static abstract class FileSyncBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FileSyncBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.sync.proto.FileSyncOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FileSync");
    }
  }

  private static final class FileSyncFileDescriptorSupplier
      extends FileSyncBaseDescriptorSupplier {
    FileSyncFileDescriptorSupplier() {}
  }

  private static final class FileSyncMethodDescriptorSupplier
      extends FileSyncBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    FileSyncMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (FileSyncGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FileSyncFileDescriptorSupplier())
              .addMethod(getCreateMethod())
              .addMethod(getModifyMethod())
              .addMethod(getDeleteMethod())
              .addMethod(getRenameMethod())
              .build();
        }
      }
    }
    return result;
  }
}
