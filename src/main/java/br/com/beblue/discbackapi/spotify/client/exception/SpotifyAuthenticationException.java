package br.com.beblue.discbackapi.spotify.client.exception;

import br.com.beblue.discbackapi.util.MessageUtils;
import br.com.beblue.discbackapi.util.Messages;

public class SpotifyAuthenticationException extends RuntimeException {

  public SpotifyAuthenticationException(final Messages msg, final Object... params) {
    super(MessageUtils.getMessage(msg.getValue(), params));
  }
}