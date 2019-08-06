package br.com.beblue.discbackapi.artist.service;

import br.com.beblue.discbackapi.artist.client.ArtistClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ArtistServiceTest {

  @Mock private ArtistClient client;

  @InjectMocks private ArtistService service;

  @Before
  public void setup() {}

  @Test //TODO: complete tests
  public void shouldReturnAlbumArtistResponse_whenBuildCatalogIsCalled() {
    assert true;
  }
}
