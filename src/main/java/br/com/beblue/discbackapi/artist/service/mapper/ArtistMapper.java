package br.com.beblue.discbackapi.artist.service.mapper;

import br.com.beblue.discbackapi.artist.client.response.ArtistResponse;
import br.com.beblue.discbackapi.artist.domain.Artist;
import br.com.beblue.discbackapi.artist.service.vo.ArtistVO;
import br.com.beblue.discbackapi.disc.domain.Genre;
import br.com.beblue.discbackapi.disc.service.response.GenreResponse;
import br.com.beblue.discbackapi.mapstruct.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
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
    nullValuePropertyMappingStrategy = SET_TO_NULL)
public interface ArtistMapper extends EntityMapper<ArtistVO, Artist> {

  static Set<Artist> toEntity(Set<ArtistResponse> artists) {
    return artists
        .parallelStream()
        .map(
            artist ->
                Artist.builder()
                    .spotifyId(artist.getId())
                    .name(artist.getName())
//                    .genres(toGenreEntity(artist.getGenres()))
                    .build()
        ).collect(Collectors.toSet());
  }

  static Set<Genre> toGenreEntity(Set<GenreResponse> genres) {
    return genres
        .parallelStream()
        .map(g -> Genre.builder().name(g.getName()).build())
        .collect(Collectors.toSet());
  }
}
