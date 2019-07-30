package br.com.beblue.discbackapi.artist.service.mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;
import static org.mapstruct.ReportingPolicy.ERROR;

import br.com.beblue.discbackapi.artist.client.response.AlbumResponse;
import br.com.beblue.discbackapi.artist.domain.Artist;
import br.com.beblue.discbackapi.artist.service.dto.ArtistDTO;
import br.com.beblue.discbackapi.common.Genre;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
