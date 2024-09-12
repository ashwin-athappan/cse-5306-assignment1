package org.as.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.57.2)",
    comments = "Source: as.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AddSortGrpc {

  private AddSortGrpc() {}

  public static final java.lang.String SERVICE_NAME = "org.as.proto.AddSort";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.as.proto.AddRequest,
      org.as.proto.AddResponse> getAddMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "add",
      requestType = org.as.proto.AddRequest.class,
      responseType = org.as.proto.AddResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.as.proto.AddRequest,
      org.as.proto.AddResponse> getAddMethod() {
    io.grpc.MethodDescriptor<org.as.proto.AddRequest, org.as.proto.AddResponse> getAddMethod;
    if ((getAddMethod = AddSortGrpc.getAddMethod) == null) {
      synchronized (AddSortGrpc.class) {
        if ((getAddMethod = AddSortGrpc.getAddMethod) == null) {
          AddSortGrpc.getAddMethod = getAddMethod =
              io.grpc.MethodDescriptor.<org.as.proto.AddRequest, org.as.proto.AddResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "add"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.as.proto.AddRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.as.proto.AddResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AddSortMethodDescriptorSupplier("add"))
              .build();
        }
      }
    }
    return getAddMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.as.proto.SortRequest,
      org.as.proto.SortResponse> getSortMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sort",
      requestType = org.as.proto.SortRequest.class,
      responseType = org.as.proto.SortResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.as.proto.SortRequest,
      org.as.proto.SortResponse> getSortMethod() {
    io.grpc.MethodDescriptor<org.as.proto.SortRequest, org.as.proto.SortResponse> getSortMethod;
    if ((getSortMethod = AddSortGrpc.getSortMethod) == null) {
      synchronized (AddSortGrpc.class) {
        if ((getSortMethod = AddSortGrpc.getSortMethod) == null) {
          AddSortGrpc.getSortMethod = getSortMethod =
              io.grpc.MethodDescriptor.<org.as.proto.SortRequest, org.as.proto.SortResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sort"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.as.proto.SortRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.as.proto.SortResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AddSortMethodDescriptorSupplier("sort"))
              .build();
        }
      }
    }
    return getSortMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AddSortStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AddSortStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AddSortStub>() {
        @java.lang.Override
        public AddSortStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AddSortStub(channel, callOptions);
        }
      };
    return AddSortStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AddSortBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AddSortBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AddSortBlockingStub>() {
        @java.lang.Override
        public AddSortBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AddSortBlockingStub(channel, callOptions);
        }
      };
    return AddSortBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AddSortFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AddSortFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AddSortFutureStub>() {
        @java.lang.Override
        public AddSortFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AddSortFutureStub(channel, callOptions);
        }
      };
    return AddSortFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void add(org.as.proto.AddRequest request,
        io.grpc.stub.StreamObserver<org.as.proto.AddResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddMethod(), responseObserver);
    }

    /**
     */
    default void sort(org.as.proto.SortRequest request,
        io.grpc.stub.StreamObserver<org.as.proto.SortResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSortMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service AddSort.
   */
  public static abstract class AddSortImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AddSortGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service AddSort.
   */
  public static final class AddSortStub
      extends io.grpc.stub.AbstractAsyncStub<AddSortStub> {
    private AddSortStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AddSortStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AddSortStub(channel, callOptions);
    }

    /**
     */
    public void add(org.as.proto.AddRequest request,
        io.grpc.stub.StreamObserver<org.as.proto.AddResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sort(org.as.proto.SortRequest request,
        io.grpc.stub.StreamObserver<org.as.proto.SortResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSortMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service AddSort.
   */
  public static final class AddSortBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AddSortBlockingStub> {
    private AddSortBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AddSortBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AddSortBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.as.proto.AddResponse add(org.as.proto.AddRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.as.proto.SortResponse sort(org.as.proto.SortRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSortMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service AddSort.
   */
  public static final class AddSortFutureStub
      extends io.grpc.stub.AbstractFutureStub<AddSortFutureStub> {
    private AddSortFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AddSortFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AddSortFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.as.proto.AddResponse> add(
        org.as.proto.AddRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.as.proto.SortResponse> sort(
        org.as.proto.SortRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSortMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD = 0;
  private static final int METHODID_SORT = 1;

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
        case METHODID_ADD:
          serviceImpl.add((org.as.proto.AddRequest) request,
              (io.grpc.stub.StreamObserver<org.as.proto.AddResponse>) responseObserver);
          break;
        case METHODID_SORT:
          serviceImpl.sort((org.as.proto.SortRequest) request,
              (io.grpc.stub.StreamObserver<org.as.proto.SortResponse>) responseObserver);
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
          getAddMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.as.proto.AddRequest,
              org.as.proto.AddResponse>(
                service, METHODID_ADD)))
        .addMethod(
          getSortMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.as.proto.SortRequest,
              org.as.proto.SortResponse>(
                service, METHODID_SORT)))
        .build();
  }

  private static abstract class AddSortBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AddSortBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.as.proto.As.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AddSort");
    }
  }

  private static final class AddSortFileDescriptorSupplier
      extends AddSortBaseDescriptorSupplier {
    AddSortFileDescriptorSupplier() {}
  }

  private static final class AddSortMethodDescriptorSupplier
      extends AddSortBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    AddSortMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (AddSortGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AddSortFileDescriptorSupplier())
              .addMethod(getAddMethod())
              .addMethod(getSortMethod())
              .build();
        }
      }
    }
    return result;
  }
}
