package io.gshockv.shrtr.infra.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.cache")
public record CacheProperties(
  Redis redis
) {
  record Redis(int ttlMinutes) {}
}
