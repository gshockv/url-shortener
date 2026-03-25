package io.gshockv.shrtr.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ShortLink {
  private Integer id;
  private String originalUrl;
  private String shortCode;
  private String shortUrl;
  private LocalDateTime createdAt;
}
