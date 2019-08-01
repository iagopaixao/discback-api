package br.com.beblue.discbackapi.artist.client.response;

import br.com.beblue.discbackapi.disc.service.response.GenreResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArtistResponse {

  private String id;

  private String name;

  private Set<GenreResponse> genres;
}