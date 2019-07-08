package com.minea.sisas.service.mapper;

import com.minea.sisas.service.dto.EntidadeGestoraDTO;

import com.minea.sisas.domain.EntidadeGestora;
import org.mapstruct.*;

/**
 * Mapper for the entity EntidadeGestora and its DTO EntidadeGestoraDTO.
 */
@Mapper(componentModel = "spring", uses = {MunicipiosAtendidosMapper.class})
public interface EntidadeGestoraMapper extends EntityMapper<EntidadeGestoraDTO, EntidadeGestora> {

    @Mapping(source = "idMunicipioAtendido.id", target = "idMunicipioAtendidoId")
    EntidadeGestoraDTO toDto(EntidadeGestora entidadeGestora);

    @Mapping(source = "idMunicipioAtendidoId", target = "idMunicipioAtendido")
    EntidadeGestora toEntity(EntidadeGestoraDTO entidadeGestoraDTO);

    default EntidadeGestora fromId(Long id) {
        if (id == null) {
            return null;
        }
        EntidadeGestora entidadeGestora = new EntidadeGestora();
        entidadeGestora.setId(id);
        return entidadeGestora;
    }
}
