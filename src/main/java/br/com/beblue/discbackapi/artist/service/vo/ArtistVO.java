package br.com.beblue.discbackapi.artist.service.vo;

import br.com.beblue.discbackapi.genre.service.vo.GenreVO;
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

  private Set<GenreVO> genres;
}
