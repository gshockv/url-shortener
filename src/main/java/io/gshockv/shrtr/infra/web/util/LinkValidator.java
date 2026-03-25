package io.gshockv.shrtr.infra.web.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URL;

@Slf4j
@Component
public class LinkValidator {
  public boolean linkValid(String link) {
    log.info("Validating {} is valid URL", link);

    try {
      new URL(link).toURI();
      log.info("Link {} is valid", link);
      return true;
    } catch (Exception e) {
      log.error("Link {} is invalid", link);
      return false;
    }
  }
}
