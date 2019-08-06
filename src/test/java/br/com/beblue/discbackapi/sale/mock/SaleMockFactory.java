package br.com.beblue.discbackapi.sale.mock;

import br.com.beblue.discbackapi.sale.domain.Sale;
import br.com.beblue.discbackapi.sale.domain.SaleItem;
import br.com.beblue.discbackapi.sale.service.request.SaleItemRequest;
import br.com.beblue.discbackapi.sale.service.vo.SaleItemVO;
import br.com.beblue.discbackapi.sale.service.vo.SaleVO;
import org.apache.commons.lang3.math.NumberUtils;
import org.hamcrest.Factory;

import static java.util.Collections.singletonList;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;

public final class SaleMockFactory {

  @Factory
  public static Sale sale() {
    return Sale.builder().saleItems(singletonList(saleItem())).build();
  }

  @Factory
  public static SaleVO saleVO() {
    return SaleVO.builder().saleItems(singletonList(saleItemVO())).build();
  }

  @Factory
  public static SaleItem saleItem() {
    return SaleItem.builder().build();
  }

  @Factory
  public static SaleItemVO saleItemVO() {
    return SaleItemVO.builder().build();
  }

  @Factory
  public static SaleItemRequest saleItemRequest() {
    return SaleItemRequest.builder()
        .discId(LONG_ONE)
        .quantity(2)
        .value(NumberUtils.createBigDecimal("23.50"))
        .build();
  }
}
