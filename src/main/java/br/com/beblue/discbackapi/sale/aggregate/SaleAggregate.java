package br.com.beblue.discbackapi.sale.aggregate;

import br.com.beblue.discbackapi.sale.response.SaleResponse;
import br.com.beblue.discbackapi.sale.service.SaleService;
import br.com.beblue.discbackapi.sale.service.vo.SaleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SaleAggregate {

  private final SaleService service;

  public SaleResponse sell(SaleVO saleVO) {
    return service.save(saleVO.sell());
  }
}
