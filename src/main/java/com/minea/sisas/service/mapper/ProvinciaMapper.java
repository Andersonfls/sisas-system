package com.minea.sisas.service.mapper;

import com.minea.sisas.service.mapper.EntityMapper;
import com.minea.sisas.service.dto.ProvinciaDTO;

import com.minea.sisas.domain.Provincia;
import org.mapstruct.*;

/**
 * Mapper for the entity Provincia and its DTO ProvinciaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProvinciaMapper extends EntityMapper<ProvinciaDTO, Provincia> {


    @Mapping(target = "municipios", ignore = true)
    Provincia toEntity(ProvinciaDTO provinciaDTO);

    default Provincia fromId(Long id) {
        if (id == null) {
            return null;
        }
        Provincia provincia = new Provincia();
        provincia.setId(id);
        return provincia;
    }
}
