package br.com.beblue.discbackapi.artist.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Set;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlbumArtistResponse {

  private String id;

  private String name;

  private Set<ArtistResponse> artists;
}
