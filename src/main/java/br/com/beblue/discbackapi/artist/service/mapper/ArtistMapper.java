package br.com.beblue.discbackapi.artist.service.mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import br.com.beblue.discbackapi.artist.client.response.ArtistResponse;
import br.com.beblue.discbackapi.artist.domain.Artist;
import br.com.beblue.discbackapi.artist.service.vo.ArtistVO;
import br.com.beblue.discbackapi.genre.service.mapper.GenreMapper;
import br.com.beblue.discbackapi.mapstruct.EntityMapper;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = CONSTRUCTOR,
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValueCheckStrategy = ALWAYS,
    nullValuePropertyMappingStrategy = IGNORE,
    uses = {GenreMapper.class}
)
public interface ArtistMapper extends EntityMapper<ArtistVO, Artist> {

  static Set<Artist> toEntity(Set<ArtistResponse> artists) {
    return artists
        .parallelStream()
        .map(
            artist ->
                Artist.builder()
                    .spotifyId(artist.getId())
                    .name(artist.getName())
                    .genres(GenreMapper.toEntity(artist.getGenres()))
                    .build())
        .collect(Collectors.toSet());
  }
}
