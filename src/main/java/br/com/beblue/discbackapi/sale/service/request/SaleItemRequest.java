package br.com.beblue.discbackapi.sale.service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleItemRequest {

  @NotEmpty
  private Long discId;

  @NotEmpty
  private BigDecimal value;

  @Min(1)
  private Integer quantity;
}
