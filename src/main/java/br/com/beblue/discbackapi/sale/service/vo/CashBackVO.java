package br.com.beblue.discbackapi.sale.service.vo;

import br.com.beblue.discbackapi.genre.domain.Genre;
import lombok.*;

import java.math.BigDecimal;
import java.time.DayOfWeek;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class CashBackVO {

  private Long id;

  private DayOfWeek day;

  private Genre genre;

  private BigDecimal percentage;
}
