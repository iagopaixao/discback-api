package br.com.beblue.discbackapi.spotify.config;

import br.com.beblue.discbackapi.spotify.client.AuthenticationClient;
import br.com.beblue.discbackapi.spotify.fallback.AuthenticationClientFallbackFactory;
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

import static br.com.beblue.discbackapi.util.JacksonMapperUtils.OBJECT_MAPPER;
import static org.springframework.util.Base64Utils.encodeToString;

@Slf4j
@Configuration
public class SpotifyFeignConfig {

  @Value("${spotify.api.accounts.url}")
  private String apiUrl;

  @Value("${spotify.authentication.credentials}")
  private String credentials;

  @Bean(name = "authenticationClient")
  public AuthenticationClient authenticationClient() {
    return HystrixFeign.builder()
        .client(new Http2Client())
        .encoder(new JacksonEncoder(OBJECT_MAPPER))
        .decoder(new JacksonDecoder(OBJECT_MAPPER))
        .retryer(new Retryer.Default())
        .requestInterceptor(new SpotifyAuthenticationInterceptor())
        .target(
            new Target.HardCodedTarget<>(AuthenticationClient.class, apiUrl),
            new AuthenticationClientFallbackFactory()
        );
  }

  private class SpotifyAuthenticationInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
      template.header("Authorization", "Basic ".concat(encodeToString(credentials.getBytes())));
    }
  }
}
