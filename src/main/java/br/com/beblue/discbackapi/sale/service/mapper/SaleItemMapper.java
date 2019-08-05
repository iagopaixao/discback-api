package br.com.beblue.discbackapi.sale.service.mapper;

import br.com.beblue.discbackapi.mapstruct.EntityMapper;
import br.com.beblue.discbackapi.sale.domain.SaleItem;
import br.com.beblue.discbackapi.sale.service.vo.SaleItemVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

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
public interface SaleItemMapper extends EntityMapper<SaleItemVO, SaleItem> {

  @Override
  @Mapping(target = "discId", source = "disc.id")
  SaleItemVO toVO(SaleItem entity);
}
