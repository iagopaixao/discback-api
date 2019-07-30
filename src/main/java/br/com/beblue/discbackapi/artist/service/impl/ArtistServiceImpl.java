package br.com.beblue.discbackapi.artist.service.impl;

import br.com.beblue.discbackapi.artist.client.ArtistClient;
import br.com.beblue.discbackapi.artist.client.response.ArtistItemResponse;
import br.com.beblue.discbackapi.artist.client.response.ArtistResponse;
import br.com.beblue.discbackapi.artist.repository.ArtistRepository;
import br.com.beblue.discbackapi.artist.service.ArtistService;
import br.com.beblue.discbackapi.artist.service.mapper.ArtistMapper;
import br.com.beblue.discbackapi.common.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Service
@Transactional(isolation = READ_COMMITTED)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArtistServiceImpl implements ArtistService {

  private final ArtistRepository repository;

  private final ArtistMapper mapper;

  private final ArtistClient artistClient;

  @Async
  public void populateCatalog() {
    Stream.of(Genre.values())
        .parallel()
        .forEach(
            g ->
                artistClient.searchArtists(g.name(), "1", "50").stream()
                    .findAny()
                    .ifPresent(
                        a ->
                            this.filterArtistIds(a.getArtistItem())
                                .parallelStream()
                                .forEach(
                                    id -> artistClient.findAlbumsByArtist(id, "1", "50")
                                )
                    )
        );
  }

  private List<String> filterArtistIds(final ArtistItemResponse artist) {
    return artist
        .getArtists()
        .parallelStream()
        .map(ArtistResponse::getId)
        .collect(Collectors.toList());
  }
}
