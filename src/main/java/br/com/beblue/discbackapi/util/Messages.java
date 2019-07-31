package br.com.beblue.discbackapi.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Messages {
  ALBUM_NOT_FOUND_ERROR("msg-error.album.not-find-error"),
  ARTIST_NOT_FOUND_ERROR("msg-error.artist.not-find-error"),
  SPOTIFY_AUTHENTICATION_ERROR("msg-error.spotify.authentication-error");

  private String value;
}
