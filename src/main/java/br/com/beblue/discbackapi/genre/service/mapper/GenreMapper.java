package br.com.beblue.discbackapi.genre.service.mapper;

import br.com.beblue.discbackapi.genre.domain.Genre;
import br.com.beblue.discbackapi.genre.service.vo.GenreVO;
import br.com.beblue.discbackapi.mapstruct.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
    componentModel = "spring",
    injectionStrategy = CONSTRUCTOR,
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValueCheckStrategy = ALWAYS,
    nullValuePropertyMappingStrategy = IGNORE)
public interface GenreMapper extends EntityMapper<GenreVO, Genre> {

  static Set<Genre> toEntity(Set<GenreVO> genres) {
    return genres
        .parallelStream()
        .map(g -> Genre.builder().id(g.getId()).name(g.getName()).build())
        .collect(Collectors.toSet());
  }
}
