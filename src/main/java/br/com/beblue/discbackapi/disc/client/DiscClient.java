package br.com.beblue.discbackapi.disc.client;

import br.com.beblue.discbackapi.disc.client.response.DiscResponse;
import feign.Headers;
import feign.RequestLine;

import java.util.List;

@Headers({"Accept: application/json", "Content-Type: application/json"})
public interface DiscClient {

  @RequestLine("GET /discs")
  List<DiscResponse> getDiscs();
}
