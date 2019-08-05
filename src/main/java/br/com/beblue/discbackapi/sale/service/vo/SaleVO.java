package br.com.beblue.discbackapi.sale.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class SaleVO {

  private Long id;

  @NotEmpty.List(@NotEmpty) private List<SaleItemVO> items;
}
