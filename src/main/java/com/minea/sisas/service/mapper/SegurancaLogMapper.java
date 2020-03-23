package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.SegurancaLog;
import com.minea.sisas.service.dto.SegurancaLogDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity SegurancaLog and its DTO SegurancaLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SegurancaLogMapper extends EntityMapper<SegurancaLogDTO, SegurancaLog> {

    default SegurancaLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        SegurancaLog segurancaLog = new SegurancaLog();
        segurancaLog.setId(id);
        return segurancaLog;
    }
}
