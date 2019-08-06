package br.com.beblue.discbackapi.spotify.client.config;

import static br.com.beblue.discbackapi.util.MapperUtils.OBJECT_MAPPER;
import static org.springframework.util.Base64Utils.encodeToString;

import br.com.beblue.discbackapi.spotify.client.SpotifyAuthenticationClient;
import br.com.beblue.discbackapi.spotify.client.fallback.SpotifyAuthenticationClientFallbackFactory;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Retryer;
import feign.Target;
import feign.http2client.Http2Client;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SpotifyFeignConfig {

  @Value("${spotify.api.accounts.url}")
  private String apiUrl;

  @Value("${spotify.authentication.credentials}")
  private String credentials;

  @Bean(name = "spotifyAuthenticationClient")
  public SpotifyAuthenticationClient spotifyAuthenticationClient() {
    return HystrixFeign.builder()
        .client(new Http2Client())
        .encoder(new JacksonEncoder(OBJECT_MAPPER))
        .decoder(new JacksonDecoder(OBJECT_MAPPER))
        .retryer(new Retryer.Default())
        .requestInterceptor(new SpotifyAuthenticationInterceptor())
        .target(
            new Target.HardCodedTarget<>(SpotifyAuthenticationClient.class, apiUrl),
            new SpotifyAuthenticationClientFallbackFactory()
        );
  }

  private class SpotifyAuthenticationInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
      template.header("Authorization", "Basic ".concat(encodeToString(credentials.getBytes())));
    }
  }
}
