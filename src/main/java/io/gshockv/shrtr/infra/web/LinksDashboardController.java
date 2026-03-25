package io.gshockv.shrtr.infra.web;

import io.gshockv.shrtr.app.LinksDashboardService;
import io.gshockv.shrtr.domain.ShortLink;
import io.gshockv.shrtr.infra.web.dto.ShortLinkResponse;
import io.gshockv.shrtr.infra.web.util.ShortLinkUrlBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static io.gshockv.shrtr.infra.web.util.InternalPagesRoutes.DASHBOARD;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LinksDashboardController {
  private final LinksDashboardService dashboardService;
  private final ShortLinkUrlBuilder linkRefBuilder;

  @GetMapping("/dashboard")
  public String dashboardIndex(HttpServletRequest httpRequest,
                               Model model) {
    List<ShortLinkResponse> links = dashboardService.findAllShortLinks().stream()
      .map(link -> {
        return ShortLinkResponse.builder()
          .id(link.getId())
          .shortCode(link.getShortUrl())
          .originalUrl(link.getFullUrl())
          .shortUrl(linkRefBuilder.buildShortLinkRef(httpRequest, link.getShortUrl()))
          .createdAt(link.getCreatedAt())
          .build();
      })
      .toList();

    model.addAttribute("links", links);

    return DASHBOARD;
  }

}
