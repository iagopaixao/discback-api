package br.com.beblue.discbackapi.disc.client.fallback;

import br.com.beblue.discbackapi.disc.client.DiscClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class DiscClientFallbackFactory implements FallbackFactory<DiscClient> {

  @Override
  public DiscClient create(Throwable cause) {
    return new DiscClientFallback(cause);
  }
}
