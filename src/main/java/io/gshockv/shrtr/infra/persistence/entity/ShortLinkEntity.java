package io.gshockv.shrtr.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "short_urls", schema = "app")
@EntityListeners(AuditingEntityListener.class)
public class ShortLinkEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "original_url", nullable = false)
  private String originalUrl;

  @Column(name = "short_code", unique = true)
  private String shortCode;

  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;
}
