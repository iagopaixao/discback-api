package br.com.beblue.discbackapi.cashback;

import br.com.beblue.discbackapi.disc.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashBack {

  private Day day;

  private Genre genre;

  private BigDecimal price;
}
