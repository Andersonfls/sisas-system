package com.minea.sisas.service.mapper;

import com.minea.sisas.service.dto.SobreDnaDTO;

import com.minea.sisas.domain.SobreDna;

/**
 * Mapper for the entity SobreDna and its DTO SobreDnaDTO.
 */
public interface SobreDnaMapper extends EntityMapper<SobreDnaDTO, SobreDna> {

    SobreDnaDTO toDto(SobreDna sobreDna);

    SobreDna toEntity(SobreDnaDTO sobreDnaDTO);

    default SobreDna fromId(Long id) {
        if (id == null) {
            return null;
        }
        SobreDna sobreDna = new SobreDna();
        sobreDna.setId(id);
        return sobreDna;
    }
}
