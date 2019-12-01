package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Especialidades;
import com.minea.sisas.service.dto.EspecialidadesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Especialidades and its DTO EspecialidadesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EspecialidadesMapper extends EntityMapper<EspecialidadesDTO, Especialidades> {

    default Especialidades fromId(Long id) {
        if (id == null) {
            return null;
        }
        Especialidades especialidades = new Especialidades();
        especialidades.setId(id);
        return especialidades;
    }
}
