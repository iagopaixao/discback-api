package br.com.beblue.discbackapi.disc.service.exception;

import br.com.beblue.discbackapi.util.MessageUtils;
import br.com.beblue.discbackapi.util.Messages;

public class DiscNotFoundException extends RuntimeException {

  public DiscNotFoundException(final Messages msg, final Object... params) {
    super(MessageUtils.getMessage(msg.getValue(), params));
  }
}
