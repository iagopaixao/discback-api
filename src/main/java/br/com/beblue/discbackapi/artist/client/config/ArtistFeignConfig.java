package br.com.beblue.discbackapi.artist.client.config;

import br.com.beblue.discbackapi.artist.client.ArtistClient;
import br.com.beblue.discbackapi.artist.client.fallback.ArtistClientFallbackFactory;
import br.com.beblue.discbackapi.exception.ExternalErrorException;
import br.com.beblue.discbackapi.spotify.client.AuthenticationClient;
import br.com.beblue.discbackapi.spotify.response.AuthenticationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.*;
import feign.http2client.Http2Client;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static br.com.beblue.discbackapi.enumeration.Messages.EXTERNAL_ERROR_EXCEPTION;
import static br.com.beblue.discbackapi.util.JacksonMapperUtils.OBJECT_MAPPER;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static org.springframework.http.HttpStatus.resolve;

@Slf4j
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArtistFeignConfig {

  private static final ConcurrentMap<String, Object> TOKEN_CACHE = new ConcurrentHashMap<>();

  private static final String ACCESS_TOKEN_KEY = "access_token";
  private static final String TOKEN_EXPIRES_IN_KEY = "expires_in";
  private static final String TOKEN_TYPE_KEY = "token_type";

  private final AuthenticationClient authenticationClient;

  @Value("${spotify.api.url}")
  private String apiUrl;

  @Bean(name = "artistClient")
  @DependsOn("authenticationClient")
  public ArtistClient artistClient() {
    return HystrixFeign.builder()
        .client(new Http2Client())
        .encoder(new JacksonEncoder(OBJECT_MAPPER))
        .decoder(new SpotifyAuthenticationDecoder(OBJECT_MAPPER))
        .retryer(new Retryer.Default())
        .requestInterceptor(new SpotifyAuthenticationInterceptor())
        .target(
            new Target.HardCodedTarget<>(ArtistClient.class, apiUrl),
            new ArtistClientFallbackFactory()
        );
  }

  private class SpotifyAuthenticationInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
      if (isNull(TOKEN_CACHE.get(ACCESS_TOKEN_KEY))) {
        retrieveToken();
      }
      template.header("Authorization", "Bearer " + TOKEN_CACHE.get(ACCESS_TOKEN_KEY));
    }
  }

  private class SpotifyAuthenticationDecoder extends JacksonDecoder {

    SpotifyAuthenticationDecoder(ObjectMapper mapper) {
      super(mapper);
    }

    @Override
    public Object decode(Response response, Type type) throws IOException {
      if (requireNonNull(resolve(response.status())).is4xxClientError()) {
        retrieveToken();
      }
      return super.decode(response, type);
    }
  }

  private void retrieveToken() {
    authenticationClient
        .getToken()
        .ifPresentOrElse(
           ArtistFeignConfig::storeTokenInCache,
           () -> {
              throw new ExternalErrorException(EXTERNAL_ERROR_EXCEPTION);
           }
        );
  }

  private static void storeTokenInCache(AuthenticationResponse response) {
    TOKEN_CACHE.put(ACCESS_TOKEN_KEY, response.getAccessToken());
    TOKEN_CACHE.put(TOKEN_TYPE_KEY, response.getTokenType());
    TOKEN_CACHE.put(TOKEN_EXPIRES_IN_KEY, response.getExpiresIn());
  }
}
