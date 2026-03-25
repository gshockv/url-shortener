package io.gshockv.shrtr.app.port;

import io.gshockv.shrtr.domain.ShortLink;
import io.gshockv.shrtr.domain.ShortLinkPage;

public interface ShortLinksRepository {
  ShortLinkPage<ShortLink> getPagedShortLinks(int page, int pageSize);

  ShortLink createShortLink(String link);
  void saveShortLink(ShortLink shortLink);
  String getOriginalUrlById(Integer id);
  String getShortCodeById(Integer id);
  void deleteShortLink(Integer id);
}
