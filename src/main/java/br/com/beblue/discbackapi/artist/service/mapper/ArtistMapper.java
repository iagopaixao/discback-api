package br.com.beblue.discbackapi.artist.service.mapper;

import br.com.beblue.discbackapi.artist.domain.Artist;
import br.com.beblue.discbackapi.artist.service.dto.ArtistDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;
import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(
    componentModel = "spring",
    injectionStrategy = CONSTRUCTOR,
    unmappedSourcePolicy = ERROR,
    unmappedTargetPolicy = ERROR,
    nullValueCheckStrategy = ALWAYS,
    nullValuePropertyMappingStrategy = IGNORE)
public interface ArtistMapper extends EntityMapper<ArtistDTO, Artist> {

  @Override
  @Mapping(target = "auditLog", ignore = true)
  Artist toEntity(ArtistDTO dto);

  @Override
  @Mapping(target = "auditLog", ignore = true)
  ArtistDTO toDto(Artist entity);
}
