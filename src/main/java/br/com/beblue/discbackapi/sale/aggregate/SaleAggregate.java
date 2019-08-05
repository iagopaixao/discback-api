package br.com.beblue.discbackapi.sale.aggregate;

import static java.math.BigDecimal.ZERO;
import static java.util.Comparator.naturalOrder;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

import br.com.beblue.discbackapi.artist.service.vo.ArtistVO;
import br.com.beblue.discbackapi.disc.service.DiscService;
import br.com.beblue.discbackapi.disc.service.mapper.DiscMapper;
import br.com.beblue.discbackapi.disc.service.vo.DiscVO;
import br.com.beblue.discbackapi.genre.service.vo.GenreVO;
import br.com.beblue.discbackapi.sale.domain.Sale;
import br.com.beblue.discbackapi.sale.domain.SaleItem;
import br.com.beblue.discbackapi.sale.service.SaleService;
import br.com.beblue.discbackapi.sale.service.request.SaleItemRequest;
import br.com.beblue.discbackapi.sale.service.vo.CashBackVO;
import br.com.beblue.discbackapi.sale.service.vo.SaleVO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SaleAggregate {

  private final DiscService discService;

  private final DiscMapper discMapper;

  private final SaleService service;

  public SaleVO sell(List<SaleItemRequest> saleItems) {
    final var sale = Sale.builder().build();
    final var items =
        saleItems.stream().map(item -> buildItem(sale, item)).collect(Collectors.toList());
    sale.setSaleItems(items);

    return service.save(sale);
  }

  private SaleItem buildItem(Sale sale, SaleItemRequest request) {
    final var disc = discService.getById(request.getDiscId());
    final var item = SaleItem.builder().quantity(request.getQuantity()).sale(sale).build();
    calculate(item, disc);

    return item;
  }

  private void calculate(SaleItem item, DiscVO disc) {
    item.setValue(disc.getValue().multiply(new BigDecimal(item.getQuantity())));
    item.setCashBack(applyCashBack(disc));
    item.setDisc(discMapper.toEntity(disc));
  }

  private BigDecimal applyCashBack(DiscVO disc) {
    return collectGenres(disc).stream()
        .map(g -> calculateCashBack(g.getCashBacks()))
        .max(naturalOrder())
        .orElse(ZERO);
  }

  private Set<GenreVO> collectGenres(DiscVO disc) {
    return CollectionUtils.emptyIfNull(disc.getArtists()).stream()
        .map(ArtistVO::getGenres)
        .flatMap(Set::stream)
        .collect(Collectors.toSet());
  }

  private BigDecimal calculateCashBack(List<CashBackVO> cashBacks) {
    if (isEmpty(cashBacks)) {
      return ZERO;
    }
    return emptyIfNull(cashBacks).stream()
        .filter(c -> c.getDay().equals(LocalDateTime.now().getDayOfWeek()))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new)
        .getPercentage();
  }
}
