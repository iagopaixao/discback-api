package br.com.beblue.discbackapi.artist.service.mapper;

import br.com.beblue.discbackapi.artist.domain.Artist;
import br.com.beblue.discbackapi.artist.service.vo.ArtistVO;
import br.com.beblue.discbackapi.mapstruct.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL;

@Mapper(
    componentModel = "spring",
    injectionStrategy = CONSTRUCTOR,
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValueCheckStrategy = ALWAYS,
    nullValuePropertyMappingStrategy = SET_TO_NULL
)
public interface ArtistMapper extends EntityMapper<ArtistVO, Artist> {}
