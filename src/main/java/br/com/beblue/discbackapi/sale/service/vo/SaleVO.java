package br.com.beblue.discbackapi.sale.service.vo;

import br.com.beblue.discbackapi.audit.AuditDate;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class SaleVO {

  private Long id;

  @NotEmpty.List(@NotEmpty) private List<SaleItemVO> saleItems;

  private AuditDate auditDate;
}
