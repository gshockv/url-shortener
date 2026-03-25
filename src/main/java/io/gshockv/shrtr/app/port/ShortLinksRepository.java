package io.gshockv.shrtr.app.port;

import io.gshockv.shrtr.domain.ShortLink;

import java.util.List;

public interface ShortLinksRepository {
  ShortLink createShortLink(String link);
  void saveShortLink(ShortLink shortLink);
  String getOriginalUrlById(Integer id);

  List<ShortLink> findAllShortLinks();
}
