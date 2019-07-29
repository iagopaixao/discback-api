package br.com.beblue.discbackapi.exception;

import br.com.beblue.discbackapi.enumeration.Messages;
import br.com.beblue.discbackapi.util.MessageUtils;

public class BusinessException extends RuntimeException {

  public BusinessException(final Messages msg, final Object... params) {
    super(MessageUtils.getMessage(msg.getValue(), params));
  }
}
