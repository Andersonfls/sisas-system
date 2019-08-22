package com.minea.sisas.service.mapper;

import com.minea.sisas.service.mapper.EntityMapper;
import com.minea.sisas.service.dto.SistemaAguaDTO;

import com.minea.sisas.domain.SistemaAgua;
import org.mapstruct.*;

/**
 * Mapper for the entity SistemaAgua and its DTO SistemaAguaDTO.
 */
@Mapper(componentModel = "spring", uses = {SituacaoMapper.class, ComunaMapper.class})
public interface SistemaAguaMapper extends EntityMapper<SistemaAguaDTO, SistemaAgua> {

    @Mapping(source = "situacao", target = "situacao")
    @Mapping(source = "comuna", target = "comuna")
    @Mapping(source = "provincia", target = "provincia")
    @Mapping(source = "municipio", target = "municipio")
    @Mapping(source = "idUsuario", target = "idUsuario")
    SistemaAguaDTO toDto(SistemaAgua sistemaAgua);

    @Mapping(source = "provincia", target = "provincia")
    @Mapping(source = "municipio", target = "municipio")
    @Mapping(source = "situacao", target = "situacao")
    @Mapping(source = "idUsuario", target = "idUsuario")
    SistemaAgua toEntity(SistemaAguaDTO sistemaAguaDTO);

    default SistemaAgua fromId(Long id) {
        if (id == null) {
            return null;
        }
        SistemaAgua sistemaAgua = new SistemaAgua();
        sistemaAgua.setId(id);
        return sistemaAgua;
    }
}
