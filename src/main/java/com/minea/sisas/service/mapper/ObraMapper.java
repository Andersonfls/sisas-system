package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Obra;
import com.minea.sisas.service.dto.ObraDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity ItemAssinatura and its DTO ItemAssinaturaDTO.
 */
@Mapper(componentModel = "spring")
public interface ObraMapper extends EntityMapper<ObraDTO, Obra> {

    ObraDTO toDto(Obra obra);

    default Obra fromId(Long id) {
        if (id == null) {
            return null;
        }
        Obra obra = new Obra();
        obra.setId(id);
        return obra;
    }
}
