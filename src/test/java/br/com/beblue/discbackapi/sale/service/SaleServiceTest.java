package br.com.beblue.discbackapi.sale.service;

import br.com.beblue.discbackapi.sale.repository.SaleRepository;
import br.com.beblue.discbackapi.sale.service.exception.SaleNotFoundException;
import br.com.beblue.discbackapi.sale.service.mapper.SaleMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.Optional;

import static br.com.beblue.discbackapi.sale.mock.SaleMockFactory.sale;
import static br.com.beblue.discbackapi.sale.mock.SaleMockFactory.saleVO;
import static java.util.Collections.singletonList;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SaleServiceTest {

  @Mock private SaleRepository repository;

  @Mock private SaleMapper mapper;

  @InjectMocks private SaleService service;

  @Test
  public void shouldReturnAllSales_whenGetAllIsCalled() {
    final var iniDate = LocalDateTime.now();
    final var endDate = LocalDateTime.now();
    final var pageRequest = PageRequest.of(1, 2);
    final var sales = new PageImpl<>(singletonList(sale()), pageRequest, singletonList(sale()).size());

    when(repository.searchBy(iniDate, endDate, pageRequest)).thenReturn(sales);

    final var result = service.getSales(iniDate, endDate, pageRequest);

    assertThat(result, is(sales.map(mapper::toVO)));
    verify(repository, times(INTEGER_ONE)).searchBy(iniDate, endDate, pageRequest);
  }

  @Test
  public void shouldReturnOneDisc_whenGetByIdIsCalled() {
    final var sale = sale();
    final var saleVO = saleVO();

    when(mapper.toVO(sale)).thenReturn(saleVO);
    when(repository.findById(LONG_ONE)).thenReturn(Optional.of(sale));

    final var result = service.getById(LONG_ONE);

    assertThat(result, Matchers.is(saleVO));
    verify(mapper, times(INTEGER_ONE)).toVO(sale);
    verify(repository, times(INTEGER_ONE)).findById(LONG_ONE);
  }

  @Test(expected = SaleNotFoundException.class)
  public void shouldThrowsDiscNotFoundException_whenGetByIdIsCalled() {
    when(repository.findById(LONG_ONE)).thenReturn(Optional.empty());

    service.getById(LONG_ONE);

    verify(repository, times(INTEGER_ONE)).findById(LONG_ONE);
  }

  @Test
  public void shouldSaleVO_whenSaveIsCalled() {
    final var sale = sale();
    final var saleVO = saleVO();

    when(mapper.toVO(sale)).thenReturn(saleVO);
    when(repository.save(sale)).thenReturn(sale);

    final var result = service.save(sale);

    assertThat(result, is(saleVO));
    verify(mapper, times(INTEGER_ONE)).toVO(sale);
    verify(repository, times(INTEGER_ONE)).save(sale);
  }
}
