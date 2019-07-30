package br.com.beblue.discbackapi.disc.service.mapper;

import br.com.beblue.discbackapi.disc.domain.Disc;
import br.com.beblue.discbackapi.disc.service.dto.DiscDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static br.com.beblue.discbackapi.disc.service.mock.DiscMockFactory.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ArtistMapperTest {

  @Autowired private DiscMapper mapper;

  @Test
  public void toDTO_whenIsCalled_shouldReturnCarEntity() {
    final var result = mapper.toEntity(discDTO());
    result.setAuditLog(null);

    assertThat(result, is(equalTo(disc())));
  }

  @Test
  public void toEntity_whenIsCalled_shouldReturnCarDTO() {
    final var result = mapper.toDto(disc());
    result.setAuditLog(null);

    assertThat(result, is(equalTo(discDTO())));
  }

  @Test
  public void shouldReturnCarEntityList_whenToDTOIsCalled() {
    final var result = mapper.toEntity(carDTOs());
    result.forEach(i -> i.setAuditLog(null));

    assertThat(result, is(equalTo(discs())));
  }

  @Test
  public void shouldReturnCarDTOList_whenToEntityIsCalled() {
    final var result = mapper.toDto(discs());
    result.forEach(i -> i.setAuditLog(null));

    assertThat(result, is(equalTo(carDTOs())));
  }

  @Test
  public void shouldReturnNull_whenToEntityIsCalled() {
    final DiscDTO dto = null;
    final List<DiscDTO> dtos = null;

    assertNull(mapper.toEntity(dto));
    assertNull(mapper.toEntity(dtos));
  }

  @Test
  public void shouldReturnNull_whenToDTOIsCalled() {
    final Disc disc = null;
    final List<Disc> discs = null;

    assertNull(mapper.toDto(disc));
    assertNull(mapper.toDto(discs));
  }
}
