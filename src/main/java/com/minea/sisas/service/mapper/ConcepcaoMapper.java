package com.minea.sisas.service.mapper;

import com.minea.sisas.service.dto.ConcepcaoDTO;

import com.minea.sisas.domain.Concepcao;
import org.mapstruct.*;

/**
 * Mapper for the entity Concepcao and its DTO ConcepcaoDTO.
 */
@Mapper(componentModel = "spring", uses = {ProgramasProjectosMapper.class, SistemaAguaMapper.class})
public interface ConcepcaoMapper extends EntityMapper<ConcepcaoDTO, Concepcao> {

    @Mapping(source = "idProgramasProjectos.id", target = "idProgramasProjectosId")
    @Mapping(source = "idSistemaAgua.id", target = "idSistemaAguaId")
    ConcepcaoDTO toDto(Concepcao concepcao);

    @Mapping(source = "idProgramasProjectosId", target = "id")
    //@Mapping(source = "idSistemaAguaId", target = "id")
    Concepcao toEntity(ConcepcaoDTO concepcaoDTO);

    default Concepcao fromId(Long id) {
        if (id == null) {
            return null;
        }
        Concepcao concepcao = new Concepcao();
        concepcao.setId(id);
        return concepcao;
    }
}
