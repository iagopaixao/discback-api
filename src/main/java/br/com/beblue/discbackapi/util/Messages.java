package br.com.beblue.discbackapi.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Messages {
  DISC_NOT_FOUND_ERROR("msg-error.entity.disc.not-found-error"),
  SALE_NOT_FOUND_ERROR("msg-error.entity.sale.not-found-error"),
  SPOTIFY_AUTHENTICATION_ERROR("msg-error.spotify.authentication-error");

  private String value;
}
