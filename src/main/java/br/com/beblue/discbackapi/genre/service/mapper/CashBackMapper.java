package br.com.beblue.discbackapi.genre.service.mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import br.com.beblue.discbackapi.mapstruct.EntityMapper;
import br.com.beblue.discbackapi.sale.domain.CashBack;
import br.com.beblue.discbackapi.sale.service.vo.CashBackVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = CONSTRUCTOR,
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValueCheckStrategy = ALWAYS,
    nullValuePropertyMappingStrategy = IGNORE,
    uses = GenreMapper.class)
public interface CashBackMapper extends EntityMapper<CashBackVO, CashBack> {}
