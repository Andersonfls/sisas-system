package com.minea.sisas.service.mapper;

import com.minea.sisas.service.dto.ConfiguracoesLogDTO;

import com.minea.sisas.domain.ConfiguracoesLog;
import org.mapstruct.*;

/**
 * Mapper for the entity ConfiguracoesLog and its DTO ConfiguracoesLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConfiguracoesLogMapper extends EntityMapper<ConfiguracoesLogDTO, ConfiguracoesLog> {



    default ConfiguracoesLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        ConfiguracoesLog configuracoesLog = new ConfiguracoesLog();
        configuracoesLog.setId(id);
        return configuracoesLog;
    }
}
