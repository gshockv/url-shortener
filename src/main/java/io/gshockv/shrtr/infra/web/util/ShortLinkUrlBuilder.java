package io.gshockv.shrtr.infra.web.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class ShortLinkUrlBuilder {
  private static final String REQUEST_HANDLER_URL_PART = "r";

  private static final String X_FORWARDED_PROTO_HEADER = "X-Forwarded-Proto";
  private static final String X_FORWARDED_HOST_HEADER = "X-Forwarded-Host";
  private static final String SCHEMA_HOST_DELIMITER = "://";
  private static final String PORT_DELIMITER = ":";

  public String buildShortLinkRef(HttpServletRequest request, String shortCode) {
    return format("%s/%s/%s", getApplicationBaseUrl(request), REQUEST_HANDLER_URL_PART, shortCode);
  }

  private String getApplicationBaseUrl(HttpServletRequest request) {
    String scheme = request.getHeader(X_FORWARDED_PROTO_HEADER);
    String host = request.getHeader(X_FORWARDED_HOST_HEADER);

    if (scheme == null) scheme = request.getScheme();
    if (host == null) host = request.getServerName();

    int serverPort = request.getServerPort();

    var sb = new StringBuilder();
    sb.append(scheme)
      .append(SCHEMA_HOST_DELIMITER)
      .append(host);

    if (serverPort > 0) {
      sb.append(PORT_DELIMITER)
        .append(serverPort);
    }

    return sb.toString();
  }
}
