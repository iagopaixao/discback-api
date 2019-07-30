package br.com.beblue.discbackapi.artist.service;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

import br.com.beblue.discbackapi.artist.client.ArtistClient;
import br.com.beblue.discbackapi.artist.client.response.AlbumResponse;
import br.com.beblue.discbackapi.artist.client.response.ArtistResponse;
import br.com.beblue.discbackapi.artist.repository.ArtistRepository;
import br.com.beblue.discbackapi.artist.service.mapper.ArtistMapper;
import br.com.beblue.discbackapi.common.Genre;
import br.com.beblue.discbackapi.exception.ExternalErrorException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        .orElseThrow(ExternalErrorException::new)
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
        .orElseThrow(ExternalErrorException::new)
        .getItems();
  }
}
