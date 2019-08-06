package br.com.beblue.discbackapi.artist.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlbumArtistResponse {

  private String id;

  private String name;

  private Set<ArtistResponse> artists;
}
