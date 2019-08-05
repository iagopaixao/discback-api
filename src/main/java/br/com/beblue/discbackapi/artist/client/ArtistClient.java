package br.com.beblue.discbackapi.artist.client;

import br.com.beblue.discbackapi.artist.client.response.ArtistWrapperResponse;
import br.com.beblue.discbackapi.artist.client.response.ItemResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;

@Headers({"Accept: application/json", "Content-Type: application/json"})
public interface ArtistClient {

  @RequestLine("GET /search?q=genre:{genreName}&type=artist&limit=1")
  List<ArtistWrapperResponse> searchArtists(@Param("genreName") String genreName);

  @RequestLine("GET /artists/{id}/albums?limit={limit}")
  List<ItemResponse> findAlbumsByArtist(@Param("id") String id, @Param("limit") String limit);
}
