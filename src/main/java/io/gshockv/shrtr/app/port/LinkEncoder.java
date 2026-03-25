package io.gshockv.shrtr.app.port;

public interface LinkEncoder {
  String encodeLinkId(Integer linkId);
  Integer decodeLinkCode(String linkCode);
}
