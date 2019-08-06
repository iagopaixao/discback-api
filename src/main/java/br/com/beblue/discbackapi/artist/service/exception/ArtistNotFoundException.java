package br.com.beblue.discbackapi.artist.service.exception;

import br.com.beblue.discbackapi.util.MessageUtils;
import br.com.beblue.discbackapi.util.Messages;

public class ArtistNotFoundException extends RuntimeException {

  public ArtistNotFoundException() {
    super();
  }

  public ArtistNotFoundException(final Messages msg, final Object... params) {
    super(MessageUtils.getMessage(msg.getValue(), params));
  }
}
