package br.com.beblue.discbackapi.artist.service.mapper;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import static br.com.beblue.discbackapi.artist.mock.ArtistMockFactory.artistResponse;
import static org.hamcrest.Matchers.*;

public class ArtistMapperTest {

  @Test
  public void shouldReturnArtists_whenToEntityIsCalled() {
    final var result = ArtistMapper.toEntity(Set.of(artistResponse()));

    Assert.assertNotNull(result);
    Assert.assertThat(result, is(not(empty())));
  }
}
