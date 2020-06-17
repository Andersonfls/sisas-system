package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.IndicadorProducaoLog;
import com.minea.sisas.service.dto.IndicadorProducaoLogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity IndicadorProducaoLog and its DTO IndicadorProducaoLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface IndicadorProducaoLogMapper extends EntityMapper<IndicadorProducaoLogDTO, IndicadorProducaoLog> {

//    @Mapping(source = "idIndicadorProducao", target = "idIndicadorProducaoId")
    IndicadorProducaoLogDTO toDto(IndicadorProducaoLog indicadorProducaoLog);

//    @Mapping(source = "idIndicadorProducaoId", target = "idIndicadorProducao")
    IndicadorProducaoLog toEntity(IndicadorProducaoLogDTO indicadorProducaoLogDTO);

    default IndicadorProducaoLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        IndicadorProducaoLog indicadorProducaoLog = new IndicadorProducaoLog();
        indicadorProducaoLog.setId(id);
        return indicadorProducaoLog;
    }
}
