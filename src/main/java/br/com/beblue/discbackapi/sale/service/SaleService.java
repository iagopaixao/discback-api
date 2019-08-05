package br.com.beblue.discbackapi.sale.service;

import br.com.beblue.discbackapi.disc.service.exception.DiscNotFoundException;
import br.com.beblue.discbackapi.sale.repository.SaleRepository;
import br.com.beblue.discbackapi.sale.response.SaleResponse;
import br.com.beblue.discbackapi.sale.service.mapper.SaleMapper;
import br.com.beblue.discbackapi.sale.service.vo.SaleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static br.com.beblue.discbackapi.util.Messages.SALE_NOT_FOUND_ERROR;
import static java.time.LocalDateTime.parse;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SaleService {

  private final SaleRepository repository;

  private final SaleMapper mapper;

  @Transactional(readOnly = true)
  public Page<SaleVO> getSales(LocalDate initialDate, LocalDate endDate, Pageable pageable) {
    return repository
        .searchBy(
            initialDate.atStartOfDay(),
            endDate.atStartOfDay(),
            pageable
        ).map(mapper::toVO);
  }

  @Transactional(readOnly = true)
  public SaleVO getById(long id) {
    return mapper.toVO(
        repository
            .findById(id)
            .orElseThrow(() -> new DiscNotFoundException(SALE_NOT_FOUND_ERROR, id)));
  }

  @Transactional(propagation = REQUIRES_NEW, isolation = READ_COMMITTED)
  public SaleResponse save(SaleVO saleVO) {
    return mapper.toResponse(repository.save(mapper.toEntity(saleVO)));
  }
}
