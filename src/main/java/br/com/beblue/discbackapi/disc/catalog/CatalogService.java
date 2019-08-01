package br.com.beblue.discbackapi.disc.catalog;

import br.com.beblue.discbackapi.artist.client.response.AlbumResponse;
import br.com.beblue.discbackapi.artist.service.ArtistService;
import br.com.beblue.discbackapi.disc.domain.MusicGenre;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CatalogService {

  private final ArtistService artistService;

  //TODO: implements persistence
  public ConcurrentMap<String, List<AlbumResponse>> populateDiscCatalog() {
    final var catalogRef = new Object() {
      final ConcurrentMap<String, List<AlbumResponse>> catalog = new ConcurrentHashMap<>();
    };

    Stream.of(MusicGenre.values())
        .parallel()
        .forEach(
            genre ->
                artistService.filterArtistIds(genre)
                    .parallelStream()
                    .forEach(
                        id -> catalogRef.catalog.put(genre.name(), artistService.filterAlbums(id))
                    )
        );

    return catalogRef.catalog;
  }
}
