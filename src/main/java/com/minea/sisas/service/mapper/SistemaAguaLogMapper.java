package com.minea.sisas.service.mapper;

import com.minea.sisas.service.mapper.EntityMapper;
import com.minea.sisas.service.dto.SistemaAguaLogDTO;

import com.minea.sisas.domain.SistemaAguaLog;
import org.mapstruct.*;

/**
 * Mapper for the entity SistemaAguaLog and its DTO SistemaAguaLogDTO.
 */
@Mapper(componentModel = "spring", uses = {SistemaAguaMapper.class})
public interface SistemaAguaLogMapper extends EntityMapper<SistemaAguaLogDTO, SistemaAguaLog> {

    @Mapping(source = "idSistemaAgua", target = "idSistemaAguaId")
    SistemaAguaLogDTO toDto(SistemaAguaLog sistemaAguaLog);

    @Mapping(source = "idSistemaAguaId", target = "idSistemaAgua")
    SistemaAguaLog toEntity(SistemaAguaLogDTO sistemaAguaLogDTO);

    default SistemaAguaLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        SistemaAguaLog sistemaAguaLog = new SistemaAguaLog();
        sistemaAguaLog.setId(id);
        return sistemaAguaLog;
    }
}
