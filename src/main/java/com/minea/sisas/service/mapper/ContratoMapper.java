package com.minea.sisas.service.mapper;

import com.minea.sisas.service.dto.ContratoDTO;

import com.minea.sisas.domain.Contrato;
import org.mapstruct.*;

/**
 * Mapper for the entity Contrato and its DTO ContratoDTO.
 */
@Mapper(componentModel = "spring", uses = {ProgramasProjectosMapper.class, SistemaAguaMapper.class})
public interface ContratoMapper extends EntityMapper<ContratoDTO, Contrato> {

    @Mapping(source = "programasProjectos", target = "programasProjectos")
    @Mapping(source = "idSistemaAgua.id", target = "idSistemaAguaId")
    ContratoDTO toDto(Contrato contrato);

    @Mapping(source = "idSistemaAguaId", target = "idSistemaAgua")
    @Mapping(target = "empreitadas", ignore = true)
    @Mapping(target = "execucaos", ignore = true)
    Contrato toEntity(ContratoDTO contratoDTO);

    default Contrato fromId(Long id) {
        if (id == null) {
            return null;
        }
        Contrato contrato = new Contrato();
        contrato.setId(id);
        return contrato;
    }
}
