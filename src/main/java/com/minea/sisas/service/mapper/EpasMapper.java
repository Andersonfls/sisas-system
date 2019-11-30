package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Epas;
import com.minea.sisas.service.dto.EpasDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Epas and its DTO EpasDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EpasMapper extends EntityMapper<EpasDTO, Epas> {

    @Mapping(source = "comuna", target = "comuna")
    @Mapping(source = "provincia", target = "provincia")
    @Mapping(source = "municipio", target = "municipio")
    EpasDTO toDto(Epas epas);

    @Mapping(source = "comuna", target = "comuna")
    @Mapping(source = "provincia", target = "provincia")
    @Mapping(source = "municipio", target = "municipio")
    Epas toEntity(EpasDTO epasDTO);


    default Epas fromId(Long id) {
        if (id == null) {
            return null;
        }
        Epas epas = new Epas();
        epas.setId(id);
        return epas;
    }
}
