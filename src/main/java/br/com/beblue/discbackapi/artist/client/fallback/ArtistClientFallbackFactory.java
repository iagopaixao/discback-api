package br.com.beblue.discbackapi.artist.client.fallback;

import br.com.beblue.discbackapi.artist.client.ArtistClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class ArtistClientFallbackFactory implements FallbackFactory<ArtistClient> {

  @Override
  public ArtistClient create(Throwable cause) {
    return new ArtistClientFallback(cause);
  }
}
