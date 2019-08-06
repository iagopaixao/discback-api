package br.com.beblue.discbackapi.sale.service.vo;

import br.com.beblue.discbackapi.genre.domain.Genre;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class CashBackVO {

  private Long id;

  private DayOfWeek day;

  private Genre genre;

  private BigDecimal percentage;
}
