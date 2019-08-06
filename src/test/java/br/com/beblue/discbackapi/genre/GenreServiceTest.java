package br.com.beblue.discbackapi.genre;

import br.com.beblue.discbackapi.genre.domain.Genre;
import br.com.beblue.discbackapi.genre.repository.GenreRepository;
import br.com.beblue.discbackapi.genre.service.GenreService;
import br.com.beblue.discbackapi.genre.service.mapper.GenreMapper;
import br.com.beblue.discbackapi.genre.service.vo.GenreVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GenreServiceTest {

  @Mock private GenreRepository repository;

  @Mock private GenreMapper mapper;

  @InjectMocks private GenreService service;

  @Test
  public void shouldReturnAllGenres_whenGetAllIsCalled() {
    final var genres = Collections.singletonList(Genre.builder().build());
    final var genresVO = Collections.singletonList(GenreVO.builder().build());

    when(mapper.toVO(genres)).thenReturn(genresVO);
    when(repository.findAll()).thenReturn(genres);

    final var result = service.getAll();

    assertThat(result, is(genresVO));
    verify(mapper, times(INTEGER_ONE)).toVO(genres);
    verify(repository, times(INTEGER_ONE)).findAll();
  }
}
