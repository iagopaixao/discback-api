package br.com.beblue.discbackapi.spotify.fallback;

import br.com.beblue.discbackapi.spotify.client.AuthenticationClient;
import br.com.beblue.discbackapi.spotify.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationClientFallback implements AuthenticationClient {

  private final Throwable cause;

  @Override
  public Optional<AuthenticationResponse> getToken() {
    log.error("m=getToken status=error message={}", cause.getCause());
    return Optional.empty();
  }
}
