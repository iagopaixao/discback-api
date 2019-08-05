package br.com.beblue.discbackapi.sale.service.mapper;

import br.com.beblue.discbackapi.mapstruct.EntityMapper;
import br.com.beblue.discbackapi.sale.domain.Sale;
import br.com.beblue.discbackapi.sale.response.SaleResponse;
import br.com.beblue.discbackapi.sale.service.vo.SaleVO;
import org.mapstruct.Mapper;
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
public interface SaleMapper extends EntityMapper<SaleVO, Sale> {

  SaleResponse toResponse(Sale sale);
}
