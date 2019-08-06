package br.com.beblue.discbackapi.spotify.client.fallback;

import br.com.beblue.discbackapi.spotify.client.SpotifyAuthenticationClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class SpotifyAuthenticationClientFallbackFactory implements FallbackFactory<SpotifyAuthenticationClient> {

  @Override
  public SpotifyAuthenticationClient create(Throwable cause) {
    return new SpotifyAuthenticationClientFallback(cause);
  }
}
