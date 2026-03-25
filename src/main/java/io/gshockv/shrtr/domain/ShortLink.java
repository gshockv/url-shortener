package io.gshockv.shrtr.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ShortLink {
  private Integer id;
  private String fullUrl;
  private String shortUrl;
  private LocalDateTime createdAt;
}
