package br.com.beblue.discbackapi.artist.client;

import br.com.beblue.discbackapi.artist.client.response.ItemResponse;
import br.com.beblue.discbackapi.artist.client.response.ArtistWrapperResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

@Headers({"Accept: application/json", "Content-Type: application/json"})
public interface ArtistClient {

  @RequestLine("GET /search?q=genre:{genreName}&type=artist&offset={offset}&limit={limit}")
  List<ArtistWrapperResponse> searchArtists(
      @Param("genreName") String genreName,
      @Param("offset") String offset,
      @Param("limit") String limit
  );

  @RequestLine("GET /artists/{id}/albums?offset={offset}&limit={limit}")
  List<ItemResponse> findAlbumsByArtist(
      @Param("id") String id, @Param("offset") String offset, @Param("limit") String limit
  );
}
