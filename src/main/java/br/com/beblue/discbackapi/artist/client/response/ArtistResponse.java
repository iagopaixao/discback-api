package br.com.beblue.discbackapi.artist.client.response;

import br.com.beblue.discbackapi.genre.service.vo.GenreVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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