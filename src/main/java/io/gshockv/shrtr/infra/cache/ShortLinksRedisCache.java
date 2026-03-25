package io.gshockv.shrtr.infra.cache;

import io.gshockv.shrtr.app.port.ShortLinksCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShortLinksRedisCache implements ShortLinksCache {
  private final RedisTemplate<String, String> redisTemplate;

  @Override
  public Optional<String> getOriginalUrl(String shortCode) {
    String cachedUrl = redisTemplate.opsForValue().get(shortCode);
    if (cachedUrl == null) {
      return Optional.empty();
    }
    return Optional.of(cachedUrl);
  }

  @Override
  public void cacheOriginalUrl(String shortCode, String originalUrl) {
    redisTemplate.opsForValue().set(shortCode, originalUrl);
  }

  @Override
  public void removeCachedUrl(String shortCode) {
    redisTemplate.delete(shortCode);
  }
}
