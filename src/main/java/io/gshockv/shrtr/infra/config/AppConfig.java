package io.gshockv.shrtr.infra.config;

import io.gshockv.shrtr.app.LinkShortenerService;
import io.gshockv.shrtr.app.LinksDashboardService;
import io.gshockv.shrtr.app.port.LinkEncoder;
import io.gshockv.shrtr.app.port.ShortLinksRepository;
import io.gshockv.shrtr.app.port.ShortLinksCache;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
@EnableCaching
@ConfigurationPropertiesScan
public class AppConfig {

  @Bean
  public LinkShortenerService linkShortenerService(ShortLinksCache linksCache,
                                                   ShortLinksRepository shortLinksRepository,
                                                   LinkEncoder linkEncoder) {
    return new LinkShortenerService(linksCache, shortLinksRepository, linkEncoder);
  }

  @Bean
  public LinksDashboardService linksDashboardService(ShortLinksCache linksCache,
                                                     ShortLinksRepository shortLinksRepository) {
    return new LinksDashboardService(linksCache, shortLinksRepository);
  }
}
