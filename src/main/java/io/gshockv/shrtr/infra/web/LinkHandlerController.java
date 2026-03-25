package io.gshockv.shrtr.infra.web;

import io.gshockv.shrtr.app.LinkShortenerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LinkHandlerController {

  private final LinkShortenerService linkShortenerService;

  @GetMapping("/r/{code}")
  public ResponseEntity<Void> handleLink(@PathVariable String code) {
    log.info("Handling short code: {}", code);

    String originalUrl = linkShortenerService.resolveLinkShortCode(code);

    return ResponseEntity
      .status(HttpStatus.FOUND)
      .location(URI.create(originalUrl))
      .build();
  }

}
