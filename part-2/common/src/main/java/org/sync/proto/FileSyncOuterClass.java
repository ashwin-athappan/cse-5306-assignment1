// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: fileSync.proto

package org.sync.proto;

public final class FileSyncOuterClass {
  private FileSyncOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_org_sync_proto_CreateRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_org_sync_proto_CreateRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_org_sync_proto_ModifyRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_org_sync_proto_ModifyRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_org_sync_proto_DeleteRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_org_sync_proto_DeleteRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_org_sync_proto_RenameRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_org_sync_proto_RenameRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_org_sync_proto_OperationResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_org_sync_proto_OperationResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_org_sync_proto_FileContent_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_org_sync_proto_FileContent_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016fileSync.proto\022\016org.sync.proto\"L\n\rCrea" +
      "teRequest\022\020\n\010fileName\030\001 \001(\t\022)\n\004file\030\002 \001(" +
      "\0132\033.org.sync.proto.FileContent\"L\n\rModify" +
      "Request\022\020\n\010fileName\030\001 \001(\t\022)\n\004file\030\002 \001(\0132" +
      "\033.org.sync.proto.FileContent\"!\n\rDeleteRe" +
      "quest\022\020\n\010fileName\030\001 \001(\t\"9\n\rRenameRequest" +
      "\022\023\n\013oldFileName\030\001 \001(\t\022\023\n\013newFileName\030\002 \001" +
      "(\t\"L\n\021OperationResponse\022\017\n\007message\030\001 \001(\t" +
      "\022&\n\006status\030\002 \001(\0162\026.org.sync.proto.Status" +
      "\"\036\n\013FileContent\022\017\n\007content\030\001 \001(\014*\"\n\006Stat" +
      "us\022\013\n\007SUCCESS\020\000\022\013\n\007FAILURE\020\0012\302\002\n\010FileSyn" +
      "c\022L\n\006create\022\035.org.sync.proto.CreateReque" +
      "st\032!.org.sync.proto.OperationResponse\"\000\022" +
      "L\n\006modify\022\035.org.sync.proto.ModifyRequest" +
      "\032!.org.sync.proto.OperationResponse\"\000\022L\n" +
      "\006delete\022\035.org.sync.proto.DeleteRequest\032!" +
      ".org.sync.proto.OperationResponse\"\000\022L\n\006r" +
      "ename\022\035.org.sync.proto.RenameRequest\032!.o" +
      "rg.sync.proto.OperationResponse\"\000B\022\n\016org" +
      ".sync.protoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_org_sync_proto_CreateRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_org_sync_proto_CreateRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_org_sync_proto_CreateRequest_descriptor,
        new java.lang.String[] { "FileName", "File", });
    internal_static_org_sync_proto_ModifyRequest_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_org_sync_proto_ModifyRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_org_sync_proto_ModifyRequest_descriptor,
        new java.lang.String[] { "FileName", "File", });
    internal_static_org_sync_proto_DeleteRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_org_sync_proto_DeleteRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_org_sync_proto_DeleteRequest_descriptor,
        new java.lang.String[] { "FileName", });
    internal_static_org_sync_proto_RenameRequest_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_org_sync_proto_RenameRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_org_sync_proto_RenameRequest_descriptor,
        new java.lang.String[] { "OldFileName", "NewFileName", });
    internal_static_org_sync_proto_OperationResponse_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_org_sync_proto_OperationResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_org_sync_proto_OperationResponse_descriptor,
        new java.lang.String[] { "Message", "Status", });
    internal_static_org_sync_proto_FileContent_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_org_sync_proto_FileContent_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_org_sync_proto_FileContent_descriptor,
        new java.lang.String[] { "Content", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
