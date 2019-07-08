package com.minea.sisas.service.mapper;

import com.minea.sisas.service.dto.EmpreitadaDTO;

import com.minea.sisas.domain.Empreitada;
import org.mapstruct.*;

/**
 * Mapper for the entity Empreitada and its DTO EmpreitadaDTO.
 */
@Mapper(componentModel = "spring", uses = {ProgramasProjectosMapper.class, SistemaAguaMapper.class, ContratoMapper.class})
public interface EmpreitadaMapper extends EntityMapper<EmpreitadaDTO, Empreitada> {

    @Mapping(source = "idProgramasProjectos.id", target = "idProgramasProjectosId")
    @Mapping(source = "idSistemaAgua.id", target = "idSistemaAguaId")
    @Mapping(source = "idContrato.id", target = "idContratoId")
    EmpreitadaDTO toDto(Empreitada empreitada);

    @Mapping(source = "idProgramasProjectosId", target = "idProgramasProjectos")
    @Mapping(source = "idSistemaAguaId", target = "idSistemaAgua")
    @Mapping(source = "idContratoId", target = "idContrato")
    Empreitada toEntity(EmpreitadaDTO empreitadaDTO);

    default Empreitada fromId(Long id) {
        if (id == null) {
            return null;
        }
        Empreitada empreitada = new Empreitada();
        empreitada.setId(id);
        return empreitada;
    }
}
