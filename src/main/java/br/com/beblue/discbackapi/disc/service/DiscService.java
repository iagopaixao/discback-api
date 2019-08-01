package br.com.beblue.discbackapi.disc.service;

import br.com.beblue.discbackapi.artist.client.response.AlbumArtistResponse;
import br.com.beblue.discbackapi.disc.repository.DiscRepository;
import br.com.beblue.discbackapi.disc.service.mapper.DiscMapper;
import br.com.beblue.discbackapi.disc.service.vo.DiscVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DiscService {

  private final DiscRepository repository;

  private final DiscMapper mapper;

  @Transactional(readOnly = true)
  public Page<DiscVO> getCatalog(Pageable pageable) {
    return repository.findAll(pageable).map(mapper::toVO);
  }

  @Transactional
  public void saveCatalog(final ConcurrentMap<String, List<AlbumArtistResponse>> discs) {
    repository.saveAll(mapper.toEntityFrom(discs));
  }
}
