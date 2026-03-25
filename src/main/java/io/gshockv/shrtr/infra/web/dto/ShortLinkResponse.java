package io.gshockv.shrtr.infra.web.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ShortLinkResponse {
  private Integer id;
  private String shortCode;
  private String shortUrl;
  private String originalUrl;
  private LocalDateTime createdAt;
}
