package br.com.beblue.discbackapi.spotify.client.fallback;

import br.com.beblue.discbackapi.spotify.client.SpotifyAuthenticationClient;
import br.com.beblue.discbackapi.spotify.client.response.SpotifyAuthenticationResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SpotifyAuthenticationClientFallback implements SpotifyAuthenticationClient {

  private final Throwable cause;

  @Override
  public Optional<SpotifyAuthenticationResponse> getToken() {
    log.error("m=getToken status=error cause=", cause);
    return Optional.empty();
  }
}
