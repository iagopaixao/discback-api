package br.com.beblue.discbackapi.sale.service;

import br.com.beblue.discbackapi.sale.domain.Sale;
import br.com.beblue.discbackapi.sale.repository.SaleRepository;
import br.com.beblue.discbackapi.sale.service.exception.SaleNotFoundException;
import br.com.beblue.discbackapi.sale.service.mapper.SaleMapper;
import br.com.beblue.discbackapi.sale.service.vo.SaleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static br.com.beblue.discbackapi.util.Messages.SALE_NOT_FOUND_ERROR;
import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SaleService {

  private final SaleRepository repository;

  private final SaleMapper mapper;

  @Transactional(readOnly = true)
  public Page<SaleVO> getSales(
      LocalDateTime initialDate, LocalDateTime endDate, Pageable pageable) {
    return repository.searchBy(initialDate, endDate, pageable).map(mapper::toVO);
  }

  @Transactional(readOnly = true)
  public SaleVO getById(long id) {
    return mapper.toVO(
        repository
            .findById(id)
            .orElseThrow(() -> new SaleNotFoundException(SALE_NOT_FOUND_ERROR, id)));
  }

  @Transactional(propagation = REQUIRED, isolation = READ_COMMITTED)
  public SaleVO save(Sale sale) {
    return mapper.toVO(repository.save(sale));
  }
}
