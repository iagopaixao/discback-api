package br.com.beblue.discbackapi.sale.aggregate;

import br.com.beblue.discbackapi.disc.service.DiscService;
import br.com.beblue.discbackapi.disc.service.mapper.DiscMapper;
import br.com.beblue.discbackapi.sale.domain.Sale;
import br.com.beblue.discbackapi.sale.domain.SaleItem;
import br.com.beblue.discbackapi.sale.service.SaleService;
import br.com.beblue.discbackapi.sale.service.vo.SaleItemVO;
import br.com.beblue.discbackapi.sale.service.vo.SaleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SaleAggregate {

  private final DiscService discService;

  private final DiscMapper discMapper;

  private final SaleService service;

  public SaleVO sell(List<SaleItemVO> saleItems) {
    final var sale = Sale.builder().build();
    final var items =
        saleItems.stream()
            .map(
                item -> {
                  final var disc = discService.getById(item.getDiscId());

                  final var itemBuilder =
                      SaleItem.builder()
                          .disc(discMapper.toEntity(disc))
                          .quantity(item.getQuantity())
                          .sale(Sale.builder().build());

                  return itemBuilder.build();
                })
            .collect(Collectors.toList());
    sale.setSaleItems(items);

    return service.save(sale);
  }
}
