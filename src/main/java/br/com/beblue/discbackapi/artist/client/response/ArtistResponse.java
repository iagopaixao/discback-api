package br.com.beblue.discbackapi.artist.client.response;

import br.com.beblue.discbackapi.genre.service.vo.GenreVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArtistResponse {

  private String id;

  private String name;

  @Setter
  @JsonIgnore
  private Set<GenreVO> genres;
}