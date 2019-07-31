package br.com.beblue.discbackapi.artist.service;

import br.com.beblue.discbackapi.artist.client.ArtistClient;
import br.com.beblue.discbackapi.artist.client.response.AlbumResponse;
import br.com.beblue.discbackapi.artist.client.response.ArtistResponse;
import br.com.beblue.discbackapi.artist.repository.ArtistRepository;
import br.com.beblue.discbackapi.artist.service.exception.AlbumNotFoundException;
import br.com.beblue.discbackapi.artist.service.exception.ArtistNotFoundException;
import br.com.beblue.discbackapi.artist.service.mapper.ArtistMapper;
import br.com.beblue.discbackapi.disc.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.beblue.discbackapi.util.Messages.ALBUM_NOT_FOUND_ERROR;
import static br.com.beblue.discbackapi.util.Messages.ARTIST_NOT_FOUND_ERROR;
import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Service
@Transactional(isolation = READ_COMMITTED)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArtistService {

  private final ArtistRepository repository;

  private final ArtistMapper mapper;

  private final ArtistClient artistClient;

  private static final String MAX_LIMIT = "50";
  private static final String OFFSET = "1";

  public List<String> filterArtistIds(Genre genre) {
    return artistClient
        .searchArtists(genre.name(), OFFSET, MAX_LIMIT)
        .parallelStream()
        .findAny()
        .orElseThrow(() -> new ArtistNotFoundException(ARTIST_NOT_FOUND_ERROR))
        .getArtistItem()
        .getArtists()
        .parallelStream()
        .map(ArtistResponse::getId)
        .collect(Collectors.toList());
  }

  public List<AlbumResponse> filterAlbums(final String artistId) {
    return artistClient
        .findAlbumsByArtist(artistId, OFFSET, MAX_LIMIT)
        .parallelStream()
        .findAny()
        .orElseThrow(() -> new AlbumNotFoundException(ALBUM_NOT_FOUND_ERROR))
        .getItems();
  }
}
