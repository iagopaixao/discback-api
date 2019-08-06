package br.com.beblue.discbackapi.disc.mock;

import br.com.beblue.discbackapi.disc.domain.Disc;
import br.com.beblue.discbackapi.disc.service.vo.DiscVO;
import org.hamcrest.Factory;

import static br.com.beblue.discbackapi.artist.mock.ArtistMockFactory.artist;
import static br.com.beblue.discbackapi.artist.mock.ArtistMockFactory.artistVO;
import static java.util.Collections.singleton;
import static org.apache.commons.lang3.math.NumberUtils.createBigDecimal;

public final class DiscMockFactory {

  @Factory
  public static Disc disc() {
    return Disc.builder()
        .spotifyId("77ZUbcdoU5KCPHNUl8bgQy")
        .artists(singleton(artist()))
        .name("Shut Up Lets Dance (Vol. II)")
        .value(createBigDecimal("12.50"))
        .build();
  }

  @Factory
  public static DiscVO discVO() {
    return DiscVO.builder()
        .artists(singleton(artistVO()))
        .name(disc().getName())
        .value(disc().getValue())
        .build();
  }
}
