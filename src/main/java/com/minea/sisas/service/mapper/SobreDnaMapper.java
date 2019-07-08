package com.minea.sisas.service.mapper;

import com.minea.sisas.service.mapper.EntityMapper;
import com.minea.sisas.service.dto.SobreDnaDTO;

import com.minea.sisas.domain.SobreDna;
import org.mapstruct.*;

/**
 * Mapper for the entity SobreDna and its DTO SobreDnaDTO.
 */
@Mapper(componentModel = "spring", uses = {SituacaoMapper.class})
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
