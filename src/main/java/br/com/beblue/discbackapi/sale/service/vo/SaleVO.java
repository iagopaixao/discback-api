package br.com.beblue.discbackapi.sale.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class SaleVO {

  private Long id;

  @NotEmpty private List<String> discIds;

  @NotNull
  @DecimalMin("1.0")
  private BigDecimal value;

  @DecimalMin("1.0")
  private BigDecimal cashbackTotal;

  public SaleVO sell() {
    return SaleVO.builder()
        .discIds(discIds)
        .cashbackTotal(cashbackTotal.multiply(value))
        .value(value)
        .build();
  }
}
