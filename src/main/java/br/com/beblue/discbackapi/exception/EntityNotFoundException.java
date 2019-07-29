package br.com.beblue.discbackapi.exception;

import br.com.beblue.discbackapi.enumeration.Messages;
import br.com.beblue.discbackapi.util.MessageUtils;

public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException(final Class clazz) {
    super(MessageUtils.getMessage(Messages.NOT_FIND_ENTITY.getValue(), clazz.getName()));
  }
}
