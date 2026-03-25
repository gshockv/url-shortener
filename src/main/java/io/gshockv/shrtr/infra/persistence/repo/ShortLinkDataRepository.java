package io.gshockv.shrtr.infra.persistence.repo;

import io.gshockv.shrtr.infra.persistence.entity.ShortLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShortLinkDataRepository extends JpaRepository<ShortLinkEntity, Integer> {
  @Query(
    value = "SELECT sl.original_url FROM app.short_urls as sl WHERE sl.id=:id",
    nativeQuery = true
  )
  String getOriginalUrlById(Integer id);

  @Query(
    value = "SELECT sl.short_code FROM app.short_urls as sl WHERE sl.id=:id",
    nativeQuery = true
  )
  String getShortCodeById(Integer id);

}
