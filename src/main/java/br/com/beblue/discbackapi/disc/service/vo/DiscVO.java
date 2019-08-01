package br.com.beblue.discbackapi.disc.service.vo;

import br.com.beblue.discbackapi.artist.domain.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class DiscVO {

  private Long id;

  private String spotifyId;

  private String name;

  private BigDecimal price;

  private Set<Artist> artists;
}
