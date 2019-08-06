package br.com.beblue.discbackapi.artist.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistWrapperResponse {

  @JsonProperty("artists")
  private ArtistItemResponse artistItem;
}
