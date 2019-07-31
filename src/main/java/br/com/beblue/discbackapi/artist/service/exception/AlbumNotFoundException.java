package br.com.beblue.discbackapi.artist.service.exception;

import br.com.beblue.discbackapi.util.MessageUtils;
import br.com.beblue.discbackapi.util.Messages;

public class AlbumNotFoundException extends RuntimeException {

  public AlbumNotFoundException(final Messages msg, final Object... params) {
    super(MessageUtils.getMessage(msg.getValue(), params));
  }
}
