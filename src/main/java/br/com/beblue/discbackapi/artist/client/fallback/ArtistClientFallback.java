package br.com.beblue.discbackapi.artist.client.fallback;

import br.com.beblue.discbackapi.artist.client.ArtistClient;
import br.com.beblue.discbackapi.artist.client.response.ArtistWrapperResponse;
import br.com.beblue.discbackapi.artist.client.response.ItemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static java.util.Collections.emptyList;

@Slf4j
@RequiredArgsConstructor
public class ArtistClientFallback implements ArtistClient {

  private final Throwable cause;

  @Override
  public List<ArtistWrapperResponse> searchArtists(String id, String offset, String limit) {
    log.error("m=searchArtists status=error id={} cause={}", id, cause);
    return emptyList();
  }

  @Override
  public List<ItemResponse> findAlbumsByArtist(String id, String offset, String limit) {
    log.error("m=findAlbumsByArtist status=error id={} cause={}", id, cause);
    return emptyList();
  }
}
