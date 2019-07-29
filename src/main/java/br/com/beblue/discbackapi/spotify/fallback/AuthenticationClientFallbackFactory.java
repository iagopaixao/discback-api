package br.com.beblue.discbackapi.spotify.fallback;

import br.com.beblue.discbackapi.spotify.client.AuthenticationClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationClientFallbackFactory implements FallbackFactory<AuthenticationClient> {

  @Override
  public AuthenticationClient create(Throwable cause) {
    return new AuthenticationClientFallback(cause);
  }
}
