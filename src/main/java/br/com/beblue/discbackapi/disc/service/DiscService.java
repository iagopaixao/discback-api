package br.com.beblue.discbackapi.disc.service;

import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;
import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

import br.com.beblue.discbackapi.artist.client.response.AlbumArtistResponse;
import br.com.beblue.discbackapi.disc.repository.DiscRepository;
import br.com.beblue.discbackapi.disc.service.mapper.DiscMapper;
import br.com.beblue.discbackapi.disc.service.vo.DiscVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DiscService {

  private final DiscRepository repository;

  private final DiscMapper mapper;

  @Transactional(readOnly = true)
  public Page<DiscVO> getCatalog(Pageable pageable) {
    return repository.findAll(pageable).map(mapper::toVO);
  }

  @Transactional(readOnly = true)
  public boolean isNotPopulatedCatalog() {
    return repository.count() < LONG_ONE;
  }

  @Transactional(propagation = REQUIRES_NEW, isolation = READ_COMMITTED)
  public void saveCatalog(List<AlbumArtistResponse> discs) {
    repository.saveAll(mapper.toEntityFrom(discs));
  }
}
