package br.com.beblue.discbackapi.artist.service.vo;

import br.com.beblue.discbackapi.disc.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class ArtistVO {

  private Long id;

  private String spotifyId;

  private String name;

  private Set<Genre> genres;
}
