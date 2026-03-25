package io.gshockv.shrtr.app;

import io.gshockv.shrtr.app.port.ShortLinksCache;
import io.gshockv.shrtr.app.port.ShortLinksRepository;
import io.gshockv.shrtr.app.port.ShortUrlGenerator;
import io.gshockv.shrtr.domain.ShortLink;
import io.gshockv.shrtr.domain.ShortLinkPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@RequiredArgsConstructor
public class LinksDashboardService {
  private final ShortLinksCache linksCache;
  private final ShortLinksRepository shortLinksRepository;

  public ShortLinkPage<ShortLink> getPagedShortLinks(int page, ShortUrlGenerator generator) {
    ShortLinkPage<ShortLink> dataPage = shortLinksRepository.getPagedShortLinks(page, ShortLinkPage.PAGE_SIZE);

    List<ShortLink> list = dataPage.getItems().stream()
      .map(link -> ShortLink.builder()
        .id(link.getId())
        .originalUrl(link.getOriginalUrl())
        .shortCode(link.getShortCode())
        .shortUrl(generator.generate(link.getShortCode()))
        .createdAt(link.getCreatedAt())
        .build())
      .toList();

    return ShortLinkPage.of(list, dataPage.getPage(), dataPage.getPagesCount());
  }

  public void deleteShortLink(Integer linkId) {
    String shortCode = shortLinksRepository.getShortCodeById(linkId);
    if (shortCode != null) {
      linksCache.removeCachedUrl(shortCode);
    }
    shortLinksRepository.deleteShortLink(linkId);
  }
}
