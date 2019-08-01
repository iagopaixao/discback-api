package br.com.beblue.discbackapi.artist.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.junit4.SpringRunner;

import static br.com.beblue.discbackapi.disc.domain.MusicGenre.MPB;
import static java.lang.String.valueOf;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableFeignClients(clients = ArtistClient.class)
public class ArtistClientIntegrationTest {

  private static final String OFF_SET = "1";
  private static final int LIMIT = 50;

  private @Autowired ArtistClient client;

  @Test
  public void shouldReturnArtists_whenSearchArtistsIsCalled() {
    final var response = client.searchArtists(MPB.name(), OFF_SET, valueOf(LIMIT));

    assertTrue(response.stream().findAny().isPresent());
    assertThat(response.stream().findAny().get().getArtistItem().getArtists(), hasSize(LIMIT));
  }

  @Test
  public void shouldReturnAlbums_whenFindAlbumsByArtistIsCalled() {
    final var artistId = "77ZUbcdoU5KCPHNUl8bgQy";

    final var response = client.findAlbumsByArtist(artistId, OFF_SET, valueOf(LIMIT));

    assertTrue(response.stream().findAny().isPresent());
    assertThat(response.stream().findAny().get().getItems(), hasSize(LIMIT));
  }
}
