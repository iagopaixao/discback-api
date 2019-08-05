package br.com.beblue.discbackapi.disc.service;

import static br.com.beblue.discbackapi.util.JacksonMapperUtils.distinctBy;
import static br.com.beblue.discbackapi.util.Messages.DISC_NOT_FOUND_ERROR;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;
import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

import br.com.beblue.discbackapi.artist.client.response.AlbumArtistResponse;
import br.com.beblue.discbackapi.artist.domain.Artist;
import br.com.beblue.discbackapi.artist.service.ArtistService;
import br.com.beblue.discbackapi.disc.domain.Disc;
import br.com.beblue.discbackapi.disc.repository.DiscRepository;
import br.com.beblue.discbackapi.disc.service.exception.DiscNotFoundException;
import br.com.beblue.discbackapi.disc.service.mapper.DiscMapper;
import br.com.beblue.discbackapi.disc.service.vo.DiscVO;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
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

  private final ArtistService artistService;

  private final DiscMapper mapper;

  @Transactional(readOnly = true)
  public Page<DiscVO> getCatalog(final String genre, Pageable pageable) {
    return repository.findAllByGenre(genre, pageable)
        .map(mapper::toVO);
  }

  @Transactional(readOnly = true)
  public DiscVO getById(long id) {
    return mapper.toVO(
        repository
            .findById(id)
            .orElseThrow(() -> new DiscNotFoundException(DISC_NOT_FOUND_ERROR, id)));
  }

  @Transactional(readOnly = true)
  public boolean isNotPopulatedCatalog() {
    return repository.count() < LONG_ONE;
  }

  @Transactional(propagation = REQUIRES_NEW, isolation = READ_COMMITTED)
  public void saveCatalog(List<AlbumArtistResponse> albumArtists) {
    final var discs = mapper.toEntityFrom(albumArtists);
    normalize(discs, artistService.saveAll(extractArtists(discs)));

    repository.saveAll(discs);
  }

  private List<Artist> extractArtists(List<Disc> discs) {
    return discs.stream()
        .map(Disc::getArtists)
        .flatMap(Collection::stream)
        .filter(distinctBy(Artist::getSpotifyId))
        .collect(Collectors.toList());
  }

  private void normalize(List<Disc> discs, List<Artist> artists) {
    artists.forEach(a -> discs.forEach(d ->
        d.getArtists().forEach(ad -> {
          if (ad.getSpotifyId().equalsIgnoreCase(a.getSpotifyId())) {
            ad.setId(a.getId());
          }
        })
    ));
  }
}
