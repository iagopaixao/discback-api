package br.com.beblue.discbackapi.spotify.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyAuthenticationResponse {

  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("token_type")
  private String tokenType;

  @JsonProperty("expires_in")
  private Integer expiresIn;
}
