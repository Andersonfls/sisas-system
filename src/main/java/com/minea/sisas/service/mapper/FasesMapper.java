package com.minea.sisas.service.mapper;

import com.minea.sisas.service.mapper.EntityMapper;
import com.minea.sisas.service.dto.FasesDTO;

import com.minea.sisas.domain.Fases;
import org.mapstruct.*;

/**
 * Mapper for the entity Fases and its DTO FasesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FasesMapper extends EntityMapper<FasesDTO, Fases> {



    default Fases fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fases fases = new Fases();
        fases.setId(id);
        return fases;
    }
}
