package io.gshockv.shrtr.app.port;

import java.util.Optional;

public interface ShortLinksCache {
  Optional<String> getOriginalUrl(String shortCode);
  void cacheOriginalUrl(String shortCode, String originalUrl);
  void removeCachedUrl(String shortCode);
}
