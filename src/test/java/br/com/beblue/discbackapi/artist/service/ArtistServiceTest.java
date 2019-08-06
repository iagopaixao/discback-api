package br.com.beblue.discbackapi.artist.service;

import br.com.beblue.discbackapi.artist.client.ArtistClient;
import br.com.beblue.discbackapi.artist.client.response.ArtistItemResponse;
import br.com.beblue.discbackapi.artist.client.response.ArtistWrapperResponse;
import br.com.beblue.discbackapi.artist.repository.ArtistRepository;
import br.com.beblue.discbackapi.artist.service.exception.AlbumNotFoundException;
import br.com.beblue.discbackapi.artist.service.exception.ArtistNotFoundException;
import br.com.beblue.discbackapi.genre.domain.EGenre;
import br.com.beblue.discbackapi.genre.service.GenreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static br.com.beblue.discbackapi.artist.mock.ArtistMockFactory.*;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ArtistServiceTest {

  @Mock private ArtistClient client;

  @Mock private ArtistRepository repository;

  @Mock private GenreService genreService;

  @InjectMocks private ArtistService service;

  @Test
  public void shouldReturnArtists_whenSaveAllCatalogIsCalled() {
    final var artists = singletonList(artist());
    when(repository.saveAll(artists)).thenReturn(artists);

    final var result = repository.saveAll(artists);

    assertThat(result, is(artists));
    verify(repository, times(INTEGER_ONE)).saveAll(artists);
  }

  @Test
  public void shouldReturnAlbumArtistResponse_whenBuildCatalogIsCalled() {
    when(genreService.getAll()).thenReturn(singletonList(genreVO()));
    when(client.searchArtists(EGenre.MPB.name())).thenReturn(singletonList(artistWrapper()));
    when(client.findAlbumsByArtist(ARTIST_SPOTIFY_ID, LIMIT))
        .thenReturn(singletonList(itemResponse()));

    final var response = service.buildCatalog();

    assertThat(response, contains(albumArtist()));
    verify(client, times(INTEGER_ONE)).searchArtists(EGenre.MPB.name());
    verify(client, times(INTEGER_ONE)).findAlbumsByArtist(ARTIST_SPOTIFY_ID, LIMIT);
  }

  @Test(expected = ArtistNotFoundException.class)
  public void shouldThrowsArtistNotFoundException_whenBuildCatalogIsCalled() {
    final var artistsWrapper =
        singletonList(
            ArtistWrapperResponse.builder()
                .artistItem(ArtistItemResponse.builder().artists(emptyList()).build())
                .build());

    when(genreService.getAll()).thenReturn(singletonList(genreVO()));
    when(client.searchArtists(EGenre.MPB.name())).thenReturn(artistsWrapper);

    service.buildCatalog();
    verify(client, times(INTEGER_ONE)).searchArtists(EGenre.MPB.name());
  }

  @Test(expected = AlbumNotFoundException.class)
  public void shouldThrowsAlbumNotFoundException_whenBuildCatalogIsCalled() {
    when(genreService.getAll()).thenReturn(singletonList(genreVO()));
    when(client.searchArtists(EGenre.MPB.name())).thenReturn(singletonList(artistWrapper()));
    when(client.findAlbumsByArtist(ARTIST_SPOTIFY_ID, LIMIT))
        .thenReturn(emptyList());

    service.buildCatalog();
    verify(client, times(INTEGER_ONE)).searchArtists(EGenre.MPB.name());
  }
}
