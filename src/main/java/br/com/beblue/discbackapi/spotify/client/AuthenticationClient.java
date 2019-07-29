package br.com.beblue.discbackapi.spotify.client;

import br.com.beblue.discbackapi.spotify.response.AuthenticationResponse;
import feign.Body;
import feign.Headers;
import feign.RequestLine;

import java.util.Optional;

@Headers("Content-Type: application/x-www-form-urlencoded")
public interface AuthenticationClient {

  @RequestLine("POST /token")
  @Body("grant_type=client_credentials")
  Optional<AuthenticationResponse> getToken();
}
