package br.com.beblue.discbackapi.disc.service.mapper;

import br.com.beblue.discbackapi.artist.client.response.AlbumArtistResponse;
import br.com.beblue.discbackapi.artist.service.mapper.ArtistMapper;
import br.com.beblue.discbackapi.disc.domain.Disc;
import br.com.beblue.discbackapi.disc.service.vo.DiscVO;
import br.com.beblue.discbackapi.mapstruct.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL;

@Mapper(
    componentModel = "spring",
    injectionStrategy = CONSTRUCTOR,
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValueCheckStrategy = ALWAYS,
    nullValuePropertyMappingStrategy = SET_TO_NULL,
    imports = ArtistMapper.class
)
public interface DiscMapper extends EntityMapper<DiscVO, Disc> {

  default List<Disc> toEntityFrom(ConcurrentMap<String, List<AlbumArtistResponse>> albumMap) {
    return albumMap.values().parallelStream()
        .flatMap(
            albums -> albums.parallelStream().map(
                album -> Disc.builder()
                    .spotifyId(album.getId())
                    .name(album.getName())
                    .price(BigDecimal.ONE)
                    .artists(ArtistMapper.toEntity(album.getArtists()))
                    .build()
            )
        ).collect(Collectors.toList());
  }
}
