package com.minea.sisas.service.mapper;

import com.minea.sisas.service.mapper.EntityMapper;
import com.minea.sisas.service.dto.PublicacaoLogDTO;

import com.minea.sisas.domain.PublicacaoLog;
import org.mapstruct.*;

/**
 * Mapper for the entity PublicacaoLog and its DTO PublicacaoLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PublicacaoLogMapper extends EntityMapper<PublicacaoLogDTO, PublicacaoLog> {



    default PublicacaoLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        PublicacaoLog publicacaoLog = new PublicacaoLog();
        publicacaoLog.setId(id);
        return publicacaoLog;
    }
}
