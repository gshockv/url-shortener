package io.gshockv.shrtr.infra.persistence;

import io.gshockv.shrtr.app.port.ShortLinksRepository;
import io.gshockv.shrtr.domain.ShortLink;
import io.gshockv.shrtr.infra.persistence.entity.ShortLinkEntity;
import io.gshockv.shrtr.infra.persistence.repo.ShortLinkDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ShortLinksRepositoryAdapter implements ShortLinksRepository {
  private final ShortLinkDataRepository dataRepository;

  @Override
  public ShortLink createShortLink(String link) {

    var linkEntity = new ShortLinkEntity();
    linkEntity.setFullUrl(link);
    ShortLinkEntity created = dataRepository.save(linkEntity);

    return toDomain(created);
  }

  @Override
  public void saveShortLink(ShortLink shortLink) {
    var linkEntity = toEntity(shortLink);

    dataRepository.save(linkEntity);
  }

  @Override
  public String getOriginalUrlById(Integer id) {
    return dataRepository.getOriginalUrlById(id);
  }

  @Override
  public List<ShortLink> findAllShortLinks() {
    return dataRepository.findAll().stream()
      .map(this::toDomain)
      .toList();
  }

  private ShortLink toDomain(final ShortLinkEntity entity) {
    return ShortLink.builder()
      .id(entity.getId())
      .fullUrl(entity.getFullUrl())
      .shortUrl(entity.getShortUrl())
      .createdAt(entity.getCreatedAt())
      .build();
  }

  private ShortLinkEntity toEntity(ShortLink model) {
    var entity = new ShortLinkEntity();
    entity.setId(model.getId());
    entity.setFullUrl(model.getFullUrl());
    entity.setShortUrl(model.getShortUrl());
    entity.setCreatedAt(model.getCreatedAt());
    return entity;
  }
}
