package br.com.beblue.discbackapi.genre.service;

import br.com.beblue.discbackapi.genre.service.mapper.GenreMapper;
import br.com.beblue.discbackapi.genre.service.vo.GenreVO;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.Matchers.*;

public class GenreMapperTest {

  @Test
  public void shouldReturnGenres_whenToEntityIsCalled() {
    final var result = GenreMapper.toEntity(Set.of(GenreVO.builder().build()));

    Assert.assertNotNull(result);
    Assert.assertThat(result, is(not(empty())));
  }
}
