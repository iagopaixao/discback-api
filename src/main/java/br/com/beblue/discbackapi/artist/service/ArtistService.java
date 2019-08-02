package br.com.beblue.discbackapi.artist.service;

import br.com.beblue.discbackapi.artist.client.ArtistClient;
import br.com.beblue.discbackapi.artist.client.response.AlbumArtistResponse;
import br.com.beblue.discbackapi.artist.client.response.ArtistResponse;
import br.com.beblue.discbackapi.artist.service.exception.AlbumNotFoundException;
import br.com.beblue.discbackapi.artist.service.exception.ArtistNotFoundException;
import br.com.beblue.discbackapi.disc.domain.MusicGenre;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.com.beblue.discbackapi.util.Messages.ALBUM_NOT_FOUND_ERROR;
import static br.com.beblue.discbackapi.util.Messages.ARTIST_NOT_FOUND_ERROR;
import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Service
@Transactional(isolation = READ_COMMITTED)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArtistService {

  private final ArtistClient artistClient;

  private static final String MAX_LIMIT = "50";

  private static final String OFFSET = "1";

  public List<AlbumArtistResponse> buildCatalog() {
    return filterArtistIds().stream()
        .map(this::filterAlbums)
        .flatMap(List::stream)
        .collect(Collectors.toList());
  }

  private List<String> filterArtistIds() {
    return Stream.of(MusicGenre.values())
        .map(
            genre ->
                artistClient.searchArtists(genre.name(), OFFSET, MAX_LIMIT).stream()
                    .findAny()
                    .orElseThrow(() -> new ArtistNotFoundException(ARTIST_NOT_FOUND_ERROR))
                    .getArtistItem()
                    .getArtists()
                    .stream()
                    .distinct()
                    .map(ArtistResponse::getId)
                    .findAny()
                    .orElseThrow()
        ).collect(Collectors.toList());
  }

  private List<AlbumArtistResponse> filterAlbums(final String artistId) {
    return artistClient.findAlbumsByArtist(artistId, OFFSET, MAX_LIMIT).stream()
        .findAny()
        .orElseThrow(() -> new AlbumNotFoundException(ALBUM_NOT_FOUND_ERROR))
        .getItems();
  }
}
