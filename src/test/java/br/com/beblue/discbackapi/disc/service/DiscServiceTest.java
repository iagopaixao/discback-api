package br.com.beblue.discbackapi.disc.service;

import br.com.beblue.discbackapi.artist.service.ArtistService;
import br.com.beblue.discbackapi.disc.repository.DiscRepository;
import br.com.beblue.discbackapi.disc.service.exception.DiscNotFoundException;
import br.com.beblue.discbackapi.disc.service.mapper.DiscMapper;
import br.com.beblue.discbackapi.genre.domain.EGenre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static br.com.beblue.discbackapi.artist.mock.ArtistMockFactory.albumArtist;
import static br.com.beblue.discbackapi.artist.mock.ArtistMockFactory.artist;
import static br.com.beblue.discbackapi.disc.mock.DiscMockFactory.disc;
import static br.com.beblue.discbackapi.disc.mock.DiscMockFactory.discVO;
import static java.util.Collections.singletonList;
import static org.apache.commons.lang3.math.NumberUtils.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DiscServiceTest {

  @Mock private DiscRepository repository;

  @Mock private ArtistService artistService;

  @Mock private DiscMapper mapper;

  @InjectMocks private DiscService service;

  @Test
  public void shouldReturnDiscCatalog_whenGetCatalogIsCalled() {
    final var disc = disc();
    final var discVO = discVO();
    final var discPage = new PageImpl<>(singletonList(disc));
    final var pageRequest = PageRequest.of(1, 2);

    when(repository.findAllByGenre(EGenre.MPB.name(), pageRequest)).thenReturn(discPage);
    when(mapper.toVO(disc)).thenReturn(discVO);

    final var result = service.getCatalog(EGenre.MPB.name(), pageRequest);

    assertThat(result, is(discPage.map(mapper::toVO)));
    verify(repository, times(INTEGER_ONE)).findAllByGenre(EGenre.MPB.name(), pageRequest);
    verify(mapper, times(INTEGER_TWO)).toVO(disc);
  }

  @Test
  public void shouldReturnOneDisc_whenGetByIdIsCalled() {
    final var disc = disc();
    final var discVO = discVO();

    when(mapper.toVO(disc)).thenReturn(discVO);
    when(repository.findById(LONG_ONE)).thenReturn(Optional.of(disc));

    final var result = service.getById(LONG_ONE);

    assertThat(result, is(discVO));
    verify(mapper, times(INTEGER_ONE)).toVO(disc);
    verify(repository, times(INTEGER_ONE)).findById(LONG_ONE);
  }

  @Test(expected = DiscNotFoundException.class)
  public void shouldThrowsDiscNotFoundException_whenGetByIdIsCalled() {
    when(repository.findById(LONG_ONE)).thenReturn(Optional.empty());

    service.getById(LONG_ONE);

    verify(repository, times(INTEGER_ONE)).findById(LONG_ONE);
  }

  @Test
  public void shouldReturnFalse_whenIsNotPopulatedCatalogIsCalled() {
    when(repository.count()).thenReturn(LONG_ONE);

    final var result = service.isNotPopulatedCatalog();

    assertFalse(result);
  }

  @Test
  public void shouldReturnTrue_whenIsNotPopulatedCatalogIsCalled() {
    when(repository.count()).thenReturn(LONG_ZERO);

    final var result = service.isNotPopulatedCatalog();

    assertTrue(result);
  }

  @Test
  public void shouldSaveCatalogSuccessfully_whenSaveCatalogIsCalled() {
    final var artists = singletonList(artist());
    final var discs = singletonList(disc());
    final var albumsArtist = singletonList(albumArtist());

    when(mapper.toEntityFrom(albumsArtist)).thenReturn(discs);
    when(artistService.saveAll(artists)).thenReturn(artists);
    when(repository.saveAll(discs)).thenReturn(discs);

    service.saveCatalog(albumsArtist);

    verify(mapper, times(INTEGER_ONE)).toEntityFrom(albumsArtist);
    verify(repository, times(INTEGER_ONE)).saveAll(discs);
  }
}
