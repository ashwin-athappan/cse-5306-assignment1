// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: fileSync.proto

package org.sync.proto;

public interface ModifyRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:org.sync.proto.ModifyRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string fileName = 1;</code>
   * @return The fileName.
   */
  java.lang.String getFileName();
  /**
   * <code>string fileName = 1;</code>
   * @return The bytes for fileName.
   */
  com.google.protobuf.ByteString
      getFileNameBytes();

  /**
   * <code>.org.sync.proto.FileContent file = 2;</code>
   * @return Whether the file field is set.
   */
  boolean hasFile();
  /**
   * <code>.org.sync.proto.FileContent file = 2;</code>
   * @return The file.
   */
  org.sync.proto.FileContent getFile();
  /**
   * <code>.org.sync.proto.FileContent file = 2;</code>
   */
  org.sync.proto.FileContentOrBuilder getFileOrBuilder();
}
