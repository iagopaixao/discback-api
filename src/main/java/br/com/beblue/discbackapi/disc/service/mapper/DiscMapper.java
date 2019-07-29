package br.com.beblue.discbackapi.disc.service.mapper;

import br.com.beblue.discbackapi.disc.domain.Disc;
import br.com.beblue.discbackapi.disc.service.dto.DiscDTO;
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
public interface DiscMapper extends EntityMapper<DiscDTO, Disc> {

  @Override
  @Mapping(target = "auditLog", ignore = true)
  Disc toEntity(DiscDTO dto);

  @Override
  @Mapping(target = "auditLog", ignore = true)
  DiscDTO toDto(Disc entity);
}
