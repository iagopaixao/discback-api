package br.com.beblue.discbackapi.disc.catalog;

import br.com.beblue.discbackapi.artist.service.ArtistService;
import br.com.beblue.discbackapi.disc.service.DiscService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DiscCatalogConsumer {

  private final ArtistService artistService;

  private final DiscService discService;

  void populateDiscCatalog() {
    try {
      log.info("m=populateDiscCatalog status=initial message=populating disc catalog...");

      discService.saveCatalog(artistService.buildCatalog());

      log.info("m=populateDiscCatalog status=success message=process successfully finished.");
      // TODO: add specific exception
    } catch (RuntimeException e) {
      log.error("m=populateDiscCatalog status=error message=", e);
    }
  }
}
