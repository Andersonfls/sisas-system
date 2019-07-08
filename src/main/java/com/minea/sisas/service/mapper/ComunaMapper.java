package com.minea.sisas.service.mapper;

import com.minea.sisas.service.dto.ComunaDTO;

import com.minea.sisas.domain.Comuna;
import org.mapstruct.*;

/**
 * Mapper for the entity Comuna and its DTO ComunaDTO.
 */
@Mapper(componentModel = "spring", uses = {MunicipioMapper.class})
public interface ComunaMapper extends EntityMapper<ComunaDTO, Comuna> {

    @Mapping(source = "municipio", target = "municipio")
    ComunaDTO toDto(Comuna comuna);

    @Mapping(source = "municipio", target = "municipio")
    Comuna toEntity(ComunaDTO comunaDTO);

    default Comuna fromId(Long id) {
        if (id == null) {
            return null;
        }
        Comuna comuna = new Comuna();
        comuna.setId(id);
        return comuna;
    }
}
