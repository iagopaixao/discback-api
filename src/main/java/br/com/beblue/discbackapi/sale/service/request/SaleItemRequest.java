package br.com.beblue.discbackapi.sale.service.request;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
