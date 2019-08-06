package br.com.beblue.discbackapi.disc.listener;

import br.com.beblue.discbackapi.artist.service.ArtistService;
import br.com.beblue.discbackapi.disc.service.DiscService;
import br.com.beblue.discbackapi.disc.service.exception.DiscCatalogProccessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DiscCatalogEventListenerProcessor {

  private final ArtistService artistService;

  private final DiscService discService;

  @EventListener
  public void process(@Lazy ContextRefreshedEvent e) {
    new DiscCatalogConsumer().populateDiscCatalog();
  }

  private class DiscCatalogConsumer {

    void populateDiscCatalog() {
      try {
        if (discService.isNotPopulatedCatalog()) {
          log.info("m=populateDiscCatalog status=initial message=populating genreId catalog...");

          discService.saveCatalog(artistService.buildCatalog());

          log.info("m=populateDiscCatalog status=success message=process successfully finished.");
        }
      } catch (DiscCatalogProccessException e) {
        log.error("m=populateDiscCatalog status=error message=", e);
      }
    }
  }
}
