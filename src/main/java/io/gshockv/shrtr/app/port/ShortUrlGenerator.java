package io.gshockv.shrtr.app.port;

@FunctionalInterface
public interface ShortUrlGenerator {
  String generate(String code);
}
