package br.com.beblue.discbackapi.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Messages {
  NOT_FIND_ENTITY("msg-error.not-find-entity"),
  EXTERNAL_ERROR_EXCEPTION("msg-error.external-error-exception");

  private String value;
}
