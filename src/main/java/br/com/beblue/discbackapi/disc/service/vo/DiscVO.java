package br.com.beblue.discbackapi.disc.service.vo;

import br.com.beblue.discbackapi.artist.domain.Artist;
import br.com.beblue.discbackapi.disc.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscVO {

  private String id;

  private String name;

  private Genre genre;

  private BigDecimal price;

  private BigDecimal cashback;

  private List<Artist> artists;
}
