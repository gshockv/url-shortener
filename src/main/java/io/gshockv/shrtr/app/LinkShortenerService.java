package io.gshockv.shrtr.app;

import io.gshockv.shrtr.app.port.LinkEncoder;
import io.gshockv.shrtr.app.port.ShortLinksRepository;
import io.gshockv.shrtr.app.port.ShortLinksCache;
import io.gshockv.shrtr.domain.ShortLink;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class LinkShortenerService {
  private final ShortLinksCache linksCache;
  private final ShortLinksRepository shortLinksRepository;
  private final LinkEncoder encoder;

  @Transactional
  public ShortLink makeLinkShorten(MakeLinkShortCommand command) {
    log.info("Shortening link: {}", command.initialLink());

    ShortLink shortLink = shortLinksRepository.createShortLink(command.initialLink());
    String shortCode = encoder.encodeLinkId(shortLink.getId());
    shortLink.setShortUrl(shortCode);

    linksCache.cacheOriginalUrl(shortCode, command.initialLink());

    shortLinksRepository.saveShortLink(shortLink);
    return shortLink;
  }

  @Transactional(readOnly = true)
  public String resolveLinkShortCode(String shortCode) {
    Optional<String> cachedUrl = linksCache.getOriginalUrl(shortCode);
    if (cachedUrl.isPresent()) {
      log.info("Found original URL in cache. Short code: {}, url: {}", shortCode, cachedUrl.get());
      return cachedUrl.get();
    }

    Integer linkId = encoder.decodeLinkCode(shortCode);
    String originalUrl = shortLinksRepository.getOriginalUrlById(linkId);
    log.info("Found original URL: {}", originalUrl);

    log.info("Caching code: {}, url: {}", shortCode, originalUrl);
    linksCache.cacheOriginalUrl(shortCode, originalUrl);

    return originalUrl;
  }

  public record MakeLinkShortCommand(
    String initialLink
  ) {}
}
