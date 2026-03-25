package io.gshockv.shrtr.app;

import io.gshockv.shrtr.app.port.ShortLinksRepository;
import io.gshockv.shrtr.domain.ShortLink;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class LinksDashboardService {
  private final ShortLinksRepository shortLinksRepository;

  public List<ShortLink> findAllShortLinks() {
    List<ShortLink> links = shortLinksRepository.findAllShortLinks();

    log.info("Found {} saved links", links.size());

    return links;
  }
}
