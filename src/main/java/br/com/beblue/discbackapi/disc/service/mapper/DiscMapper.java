package br.com.beblue.discbackapi.disc.service.mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import br.com.beblue.discbackapi.artist.client.response.AlbumArtistResponse;
import br.com.beblue.discbackapi.artist.service.mapper.ArtistMapper;
import br.com.beblue.discbackapi.disc.domain.Disc;
import br.com.beblue.discbackapi.disc.service.vo.DiscVO;
import br.com.beblue.discbackapi.genre.service.mapper.GenreMapper;
import br.com.beblue.discbackapi.mapstruct.EntityMapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang.math.RandomUtils;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = CONSTRUCTOR,
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValueCheckStrategy = ALWAYS,
    nullValuePropertyMappingStrategy = IGNORE,
    uses = {ArtistMapper.class, GenreMapper.class})
public interface DiscMapper extends EntityMapper<DiscVO, Disc> {

  default List<Disc> toEntityFrom(List<AlbumArtistResponse> albums) {
    return albums
        .parallelStream()
        .map(
            album ->
                Disc.builder()
                    .name(album.getName())
                    .spotifyId(album.getId())
                    .value(BigDecimal.valueOf(RandomUtils.nextInt(55)))
                    .artists(ArtistMapper.toEntity(album.getArtists()))
                    .build())
        .collect(Collectors.toList());
  }
}
