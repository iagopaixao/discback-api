package br.com.beblue.discbackapi.disc.catalog;

import br.com.beblue.discbackapi.artist.client.response.AlbumArtistResponse;
import br.com.beblue.discbackapi.artist.service.ArtistService;
import br.com.beblue.discbackapi.disc.domain.MusicGenre;
import br.com.beblue.discbackapi.disc.service.DiscService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DiscCatalogConsumer {

  private final ArtistService artistService;

  private final DiscService discService;

  void populateDiscCatalog() {
    final var catalog = new ConcurrentHashMap<String, List<AlbumArtistResponse>>();

    try {
      log.info("m=populateDiscCatalog status=initial message=populating disc catalog...");

      Stream.of(MusicGenre.values()).forEach(
          genre -> artistService.filterArtistIds(genre).forEach(
              artistId ->
                  catalog.put(genre.name(), artistService.filterAlbums(artistId))
          )
      );
      discService.saveCatalog(catalog);

      log.info("m=populateDiscCatalog status=success message=process successfully finished.");
      //TODO: add specific exception
    } catch (RuntimeException e) {
      log.error("m=populateDiscCatalog status=error message=", e);
    }
  }
}
