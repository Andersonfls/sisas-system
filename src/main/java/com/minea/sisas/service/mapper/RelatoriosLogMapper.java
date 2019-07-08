package com.minea.sisas.service.mapper;

import com.minea.sisas.service.mapper.EntityMapper;
import com.minea.sisas.service.dto.RelatoriosLogDTO;

import com.minea.sisas.domain.RelatoriosLog;
import org.mapstruct.*;

/**
 * Mapper for the entity RelatoriosLog and its DTO RelatoriosLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RelatoriosLogMapper extends EntityMapper<RelatoriosLogDTO, RelatoriosLog> {



    default RelatoriosLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        RelatoriosLog relatoriosLog = new RelatoriosLog();
        relatoriosLog.setId(id);
        return relatoriosLog;
    }
}
