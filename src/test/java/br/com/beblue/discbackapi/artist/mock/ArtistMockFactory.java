package br.com.beblue.discbackapi.artist.mock;

import br.com.beblue.discbackapi.artist.client.response.*;
import br.com.beblue.discbackapi.artist.domain.Artist;
import br.com.beblue.discbackapi.artist.service.vo.ArtistVO;
import br.com.beblue.discbackapi.genre.service.vo.GenreVO;
import br.com.beblue.discbackapi.sale.service.vo.CashBackVO;
import org.hamcrest.Factory;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.apache.commons.lang3.math.NumberUtils.createBigDecimal;

public final class ArtistMockFactory {

  public static final String ARTIST_SPOTIFY_ID = "77ZUbcdoU5KCPHNUl8bgQy";

  public static final String LIMIT = "50";

  @Factory
  public static Artist artist() {
    return Artist.builder()
        .spotifyId(artistResponse().getId())
        .name(artistResponse().getName())
        .build();
  }

  @Factory
  public static ArtistVO artistVO() {
    return ArtistVO.builder()
        .spotifyId(artistResponse().getId())
        .name(artistResponse().getName())
        .genres(Set.of(genreVO()))
        .build();
  }

  @Factory
  public static ArtistWrapperResponse artistWrapper() {
    return ArtistWrapperResponse.builder().artistItem(artistItem()).build();
  }

  @Factory
  public static ArtistItemResponse artistItem() {
    return ArtistItemResponse.builder()
        .artists(Collections.singletonList(artistResponse()))
        .build();
  }

  @Factory
  public static ArtistResponse artistResponse() {
    return ArtistResponse.builder()
        .id(ARTIST_SPOTIFY_ID)
        .name("Djvan")
        .genres(Set.of(genreVO()))
        .build();
  }

  @Factory
  public static GenreVO genreVO() {
    return GenreVO.builder().name("MPB").cashBacks(List.of(cashBackVO())).build();
  }

  @Factory
  public static CashBackVO cashBackVO() {
    return CashBackVO.builder()
        .day(LocalDate.now().getDayOfWeek())
        .percentage(createBigDecimal("0.5"))
        .build();
  }

  @Factory
  public static AlbumArtistResponse albumArtist() {
    return AlbumArtistResponse.builder()
        .name("Flor de Lis")
        .artists(Set.of(artistResponse()))
        .build();
  }

  @Factory
  public static ItemResponse itemResponse() {
    return ItemResponse.builder().items(List.of(albumArtist())).build();
  }
}
