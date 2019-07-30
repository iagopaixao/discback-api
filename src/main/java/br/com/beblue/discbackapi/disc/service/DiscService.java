package br.com.beblue.discbackapi.disc.service;

import br.com.beblue.discbackapi.disc.service.response.DiscCatalogResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiscService {

  Page<DiscCatalogResponse> getCatalog(Pageable pageable);
}
