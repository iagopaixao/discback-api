package br.com.beblue.discbackapi.disc.client.fallback;

import br.com.beblue.discbackapi.disc.client.DiscClient;
import br.com.beblue.discbackapi.disc.client.response.DiscResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static java.util.Collections.emptyList;

@Slf4j
@RequiredArgsConstructor
public class DiscClientFallback implements DiscClient {

  private final Throwable cause;

  @Override
  public List<DiscResponse> getDiscs() {
    log.error("m=getDiscs status=error message={}", cause.getMessage());
    return emptyList();
  }
}
