package br.com.beblue.discbackapi.disc.service.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static br.com.beblue.discbackapi.artist.mock.ArtistMockFactory.albumArtist;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DiscMapperTest {

  @Autowired private DiscMapper mapper;

  @Test
  public void shouldReturnDiscs_whenToEntityFromIsCalled() {
    final var result = mapper.toEntityFrom(List.of(albumArtist()));

    Assert.assertNotNull(result);
    Assert.assertThat(result, is(not(empty())));
  }
}
