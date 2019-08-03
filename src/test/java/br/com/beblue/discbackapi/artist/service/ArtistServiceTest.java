package br.com.beblue.discbackapi.artist.service;

import br.com.beblue.discbackapi.artist.client.ArtistClient;
import br.com.beblue.discbackapi.artist.client.response.AlbumArtistResponse;
import br.com.beblue.discbackapi.genre.EGenre;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArtistServiceTest {

  @Mock private ArtistClient client;

  @InjectMocks private ArtistService service;

  @Before
  public void setup() {
    when(client.searchArtists(EGenre.MPB.name(), "1", "50")).thenReturn(Collections.emptyList());
    when(client.findAlbumsByArtist("ixasj13", "1", "50")).thenReturn(Collections.emptyList());
  }

  @Test
  public void shouldReturnAlbumArtistResponse_whenBuildCatalogIsCalled() {
    final var result = service.buildCatalog();


    assertThat(result, is(empty()));
  }
}
