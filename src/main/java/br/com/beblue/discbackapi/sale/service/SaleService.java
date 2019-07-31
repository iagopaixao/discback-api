package br.com.beblue.discbackapi.sale.service;

import br.com.beblue.discbackapi.sale.response.SaleResponse;
import br.com.beblue.discbackapi.sale.repository.SaleRepository;
import br.com.beblue.discbackapi.sale.service.mapper.SaleMapper;
import br.com.beblue.discbackapi.sale.service.vo.SaleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SaleService {

  private final SaleRepository repository;

  private final SaleMapper mapper;

  public SaleResponse save(SaleVO saleVO) {
    return mapper.toResponse(
        repository.save(mapper.toEntity(saleVO))
    );
  }
}
