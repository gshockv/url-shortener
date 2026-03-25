package io.gshockv.shrtr.infra.persistence;

import io.gshockv.shrtr.app.port.ShortLinksRepository;
import io.gshockv.shrtr.domain.ShortLink;
import io.gshockv.shrtr.domain.ShortLinkPage;
import io.gshockv.shrtr.infra.persistence.entity.ShortLinkEntity;
import io.gshockv.shrtr.infra.persistence.repo.ShortLinkDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ShortLinksRepositoryAdapter implements ShortLinksRepository {
  private static final String SORT_COLUMN = "createdAt";

  private final ShortLinkDataRepository dataRepository;

  @Override
  @Transactional(readOnly = true)
  public ShortLinkPage<ShortLink> getPagedShortLinks(int page, int pageSize) {
    Page<ShortLinkEntity> dataPage = dataRepository.findAll(
      PageRequest.of(page, pageSize, Sort.by(SORT_COLUMN).descending()));

    return ShortLinkPage.of(
      dataPage.get()
        .map(this::toDomain)
        .toList(),
      dataPage.getNumber(),
      dataPage.getTotalPages());
  }

  @Override
  @Transactional
  public ShortLink createShortLink(String link) {

    var linkEntity = new ShortLinkEntity();
    linkEntity.setOriginalUrl(link);
    ShortLinkEntity created = dataRepository.save(linkEntity);

    return toDomain(created);
  }

  @Override
  @Transactional
  public void saveShortLink(ShortLink shortLink) {
    var linkEntity = toEntity(shortLink);

    dataRepository.save(linkEntity);
  }

  @Override
  @Transactional(readOnly = true)
  public String getOriginalUrlById(Integer id) {
    return dataRepository.getOriginalUrlById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public String getShortCodeById(Integer id) {
    return dataRepository.getShortCodeById(id);
  }

  @Override
  @Transactional
  public void deleteShortLink(Integer id) {
    dataRepository.deleteById(id);
  }

  private ShortLink toDomain(final ShortLinkEntity entity) {
    return ShortLink.builder()
      .id(entity.getId())
      .originalUrl(entity.getOriginalUrl())
      .shortCode(entity.getShortCode())
      .createdAt(entity.getCreatedAt())
      .build();
  }

  private ShortLinkEntity toEntity(ShortLink model) {
    var entity = new ShortLinkEntity();
    entity.setId(model.getId());
    entity.setOriginalUrl(model.getOriginalUrl());
    entity.setShortCode(model.getShortUrl());
    entity.setCreatedAt(model.getCreatedAt());
    return entity;
  }
}
