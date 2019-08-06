package br.com.beblue.discbackapi.sale.service.exception;

import br.com.beblue.discbackapi.util.MessageUtils;
import br.com.beblue.discbackapi.util.Messages;

public class SaleNotFoundException extends RuntimeException {

  public SaleNotFoundException(final Messages msg, final Object... params) {
    super(MessageUtils.getMessage(msg.getValue(), params));
  }
}
