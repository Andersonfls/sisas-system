package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.FinalidadeProjeto;
import com.minea.sisas.service.dto.FinalidadeProjetoDTO;
import org.mapstruct.Mapper;
/**
 * Mapper for the entity FinalidadeProjeto and its DTO FinalidadeProjetoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FinalidadeProjetoMapper extends EntityMapper<FinalidadeProjetoDTO, FinalidadeProjeto> {

    default FinalidadeProjeto fromId(Long id) {
        if (id == null) {
            return null;
        }
        FinalidadeProjeto finalidadeProjeto = new FinalidadeProjeto();
        finalidadeProjeto.setId(id);
        return finalidadeProjeto;
    }
}
