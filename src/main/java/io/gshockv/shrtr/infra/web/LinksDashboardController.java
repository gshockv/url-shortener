package io.gshockv.shrtr.infra.web;

import io.gshockv.shrtr.app.LinksDashboardService;
import io.gshockv.shrtr.domain.ShortLink;
import io.gshockv.shrtr.domain.ShortLinkPage;
import io.gshockv.shrtr.infra.web.util.ShortLinkUrlBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static io.gshockv.shrtr.infra.web.util.InternalPagesRoutes.DASHBOARD;
import static io.gshockv.shrtr.infra.web.util.InternalPagesRoutes.LINKS_TABLE_COMPONENT;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LinksDashboardController {
  private final LinksDashboardService dashboardService;
  private final ShortLinkUrlBuilder linkRefBuilder;

  @GetMapping("/dashboard")
  public String dashboardIndex(HttpServletRequest httpRequest,
                               @RequestParam(defaultValue = "0") int page,
                               Model model) {
    ShortLinkPage<ShortLink> dataPage = dashboardService.getPagedShortLinks(page,
      (code) -> linkRefBuilder.buildShortLinkRef(httpRequest, code));

    model.addAttribute("page", dataPage);

    return DASHBOARD;
  }

  @GetMapping("/links/table")
  public String linksTable(HttpServletRequest httpRequest,
                           @RequestParam(defaultValue = "0") int page,
                           Model model) {
    ShortLinkPage<ShortLink> dataPage = dashboardService.getPagedShortLinks(page,
      (code) -> linkRefBuilder.buildShortLinkRef(httpRequest, code));

    model.addAttribute("page", dataPage);

    return LINKS_TABLE_COMPONENT;
  }

  @DeleteMapping("/delete-short-link/{id}")
  @ResponseBody
  public void deleteShortLink(@PathVariable("id") Integer id) {
    log.info("Deleting link: {}...", id);

    dashboardService.deleteShortLink(id);
  }
}
