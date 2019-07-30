package br.com.beblue.discbackapi.exception;

import br.com.beblue.discbackapi.enumeration.Messages;
import br.com.beblue.discbackapi.util.MessageUtils;

public class ExternalErrorException extends RuntimeException {

  public ExternalErrorException() {
    super();
  }

  public ExternalErrorException(final Messages msg, final Object... params) {
    super(MessageUtils.getMessage(msg.getValue(), params));
  }
}
