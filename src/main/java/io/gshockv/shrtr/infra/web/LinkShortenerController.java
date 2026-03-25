package io.gshockv.shrtr.infra.web;

import io.gshockv.shrtr.app.LinkShortenerService;
import io.gshockv.shrtr.domain.ShortLink;
import io.gshockv.shrtr.infra.web.dto.ShortLinkRequest;
import io.gshockv.shrtr.infra.web.util.ShortLinkUrlBuilder;
import io.gshockv.shrtr.infra.web.util.LinkValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static io.gshockv.shrtr.infra.web.util.InternalPagesRoutes.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LinkShortenerController {
  private final LinkValidator linkValidator;
  private final LinkShortenerService shortenerService;
  private final ShortLinkUrlBuilder linkRefBuilder;

  @GetMapping("/")
  public String indexPage() {
    return INDEX;
  }

  @PostMapping("/shortLink")
  public String shortLink(HttpServletRequest httpRequest,
                          @ModelAttribute("shortLinkForm") ShortLinkRequest request,
                          Model model) {
    if (request.linkToShort() == null || request.linkToShort().isBlank()) {
      model.addAttribute("error", "URL is blank");
      return SHORTEN_LINK_ERROR_COMPONENT;
    }

    if (!linkValidator.linkValid(request.linkToShort())) {
      model.addAttribute("error", "URL has invalid format. Please check");
      return SHORTEN_LINK_ERROR_COMPONENT;
    }

    log.info("Shortening link: {}", request.linkToShort());

    var shorteningCommand = new LinkShortenerService.MakeLinkShortCommand(request.linkToShort());
    ShortLink shortLink = shortenerService.makeLinkShorten(shorteningCommand);

    String linkRef = linkRefBuilder.buildShortLinkRef(httpRequest, shortLink.getShortUrl());

    log.info("Shortened link: {}", linkRef);

    model.addAttribute("shortLink", linkRef);

    return SHORTEN_LINK_COMPONENT;
  }
}
