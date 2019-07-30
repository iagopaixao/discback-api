package br.com.beblue.discbackapi.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashBackTable {

  private Day day;

  private Genre genre;

  private BigDecimal price;
}
