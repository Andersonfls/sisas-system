package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Municipio;
import com.minea.sisas.service.dto.MunicipioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Municipio and its DTO MunicipioDTO.
 */
@Mapper(componentModel = "spring", uses = {ProvinciaMapper.class})
public interface MunicipioMapper extends EntityMapper<MunicipioDTO, Municipio> {

    @Mapping(source = "provincia", target = "provincia")
    MunicipioDTO toDto(Municipio municipio);

    @Mapping(source = "provincia", target = "provincia")
    Municipio toEntity(MunicipioDTO municipioDTO);

    default Municipio fromId(Long id) {
        if (id == null) {
            return null;
        }
        Municipio municipio = new Municipio();
        municipio.setId(id);
        return municipio;
    }
}
