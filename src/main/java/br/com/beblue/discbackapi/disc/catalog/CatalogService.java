package br.com.beblue.discbackapi.disc.catalog;

import br.com.beblue.discbackapi.artist.client.response.AlbumResponse;
import br.com.beblue.discbackapi.artist.service.ArtistService;
import br.com.beblue.discbackapi.common.Genre;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogService {

  private final ArtistService artistService;

  @Async
  public ConcurrentMap<String, List<AlbumResponse>> retrieveDiscCatalog() {
    final var catalogRef = new Object() {
      final ConcurrentMap<String, List<AlbumResponse>> catalog = new ConcurrentHashMap<>();
    };

    Stream.of(Genre.values())
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
