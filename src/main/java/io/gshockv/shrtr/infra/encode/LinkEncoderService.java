package io.gshockv.shrtr.infra.encode;

import io.gshockv.shrtr.app.port.LinkEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LinkEncoderService implements LinkEncoder {
  public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final int BASE = ALPHABET.length();

  @Override
  public String encodeLinkId(Integer linkId) {
    log.info("Encoding linkId: {}", linkId);

    if (linkId == 0) {
      return String.valueOf(ALPHABET.charAt(0));
    }

    var sb = new StringBuilder();
    while (linkId > 0) {
      sb.append(ALPHABET.charAt((int)(linkId % BASE)));
      linkId /= BASE;
    }

    var shortCode = sb.reverse().toString();

    log.info("Encoded short code is {}", shortCode);

    return shortCode;
  }

  @Override
  public Integer decodeLinkCode(String linkCode) {
    log.info("Decoding lonkCode: {}", linkCode);

    Integer result = 0;
    for (char c : linkCode.toCharArray()) {
      result = result * BASE + ALPHABET.indexOf(c);
    }
    return result;
  }
}
