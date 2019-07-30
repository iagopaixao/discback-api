package br.com.beblue.discbackapi.disc.service.impl;

import br.com.beblue.discbackapi.disc.repository.DiscRepository;
import br.com.beblue.discbackapi.disc.service.DiscService;
import br.com.beblue.discbackapi.disc.service.mapper.DiscMapper;
import br.com.beblue.discbackapi.disc.service.response.DiscCatalogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Service
@Transactional(isolation = READ_COMMITTED)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DiscServiceImpl implements DiscService {

  private final DiscRepository repository;

  private final DiscMapper mapper;

  @Override
  public Page<DiscCatalogResponse> getCatalog(Pageable pageable) {
    return null;
  }
}
