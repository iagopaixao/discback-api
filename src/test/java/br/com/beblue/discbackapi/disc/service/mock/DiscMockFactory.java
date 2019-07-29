package br.com.beblue.discbackapi.disc.service.mock;

import br.com.beblue.discbackapi.disc.domain.Disc;
import br.com.beblue.discbackapi.disc.service.dto.DiscDTO;
import org.hamcrest.Factory;

import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;

public final class DiscMockFactory {

  public static final String URI = "/discs";

  private DiscMockFactory() {
    throw new IllegalStateException("Utility Class");
  }

  @Factory
  public static List<DiscDTO> carDTOs() {
    return Collections.singletonList(discDTO());
  }

  @Factory
  public static List<Disc> discs() {
    return Collections.singletonList(disc());
  }

  @Factory
  public static Disc disc() {
    final var car = Disc.builder().id(LONG_ONE).build();
    car.setAuditLog(null);

    return car;
  }

  @Factory
  public static DiscDTO discDTO() {
    return DiscDTO.builder().id(LONG_ONE).build();
  }
}
