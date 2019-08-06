package br.com.beblue.discbackapi.artist.service;

import static br.com.beblue.discbackapi.util.MapperUtils.distinctBy;
import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

import br.com.beblue.discbackapi.artist.client.ArtistClient;
import br.com.beblue.discbackapi.artist.client.response.AlbumArtistResponse;
import br.com.beblue.discbackapi.artist.client.response.ArtistItemResponse;
import br.com.beblue.discbackapi.artist.client.response.ArtistResponse;
import br.com.beblue.discbackapi.artist.client.response.ArtistWrapperResponse;
import br.com.beblue.discbackapi.artist.domain.Artist;
import br.com.beblue.discbackapi.artist.repository.ArtistRepository;
import br.com.beblue.discbackapi.artist.service.exception.AlbumNotFoundException;
import br.com.beblue.discbackapi.artist.service.exception.ArtistNotFoundException;
import br.com.beblue.discbackapi.genre.service.GenreService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.SetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArtistService {

  private final ArtistClient client;

  private final ArtistRepository repository;

  private final GenreService genreService;

  @Transactional(propagation = REQUIRES_NEW, isolation = READ_COMMITTED)
  public List<Artist> saveAll(List<Artist> artists) {
    return repository.saveAll(artists);
  }

  public List<AlbumArtistResponse> buildCatalog() {
    return filterArtists().stream()
        .map(this::filterAlbums)
        .flatMap(List::stream)
        .collect(Collectors.toList());
  }

  private List<ArtistResponse> filterArtists() {
    final var artists = new ArrayList<ArtistResponse>();
    final var genres = genreService.getAll();

    genres.forEach(
        genre -> {
          final var artistResponse =
              client.searchArtists(genre.getName()).stream()
                  .map(ArtistWrapperResponse::getArtistItem)
                  .map(ArtistItemResponse::getArtists)
                  .collect(Collectors.toList())
                  .stream()
                  .flatMap(List::stream)
                  .findFirst()
                  .orElseThrow(ArtistNotFoundException::new);
          artistResponse.setGenres(SetUtils.hashSet(genre));
          artists.add(artistResponse);
        });
    return artists.stream().filter(distinctBy(ArtistResponse::getId)).collect(Collectors.toList());
  }

  private List<AlbumArtistResponse> filterAlbums(final ArtistResponse artist) {
    final var maxLimit = "50";

    final var items =
        client.findAlbumsByArtist(artist.getId(), maxLimit).stream()
            .findAny()
            .orElseThrow(AlbumNotFoundException::new)
            .getItems();
    items.forEach(album -> album.getArtists().forEach(a -> a.setGenres(artist.getGenres())));

    return items;
  }
}
