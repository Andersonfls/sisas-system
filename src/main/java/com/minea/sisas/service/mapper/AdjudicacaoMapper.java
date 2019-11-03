package com.minea.sisas.service.mapper;

import com.minea.sisas.service.dto.AdjudicacaoDTO;

import com.minea.sisas.domain.Adjudicacao;
import org.mapstruct.*;

/**
 * Mapper for the entity Adjudicacao and its DTO AdjudicacaoDTO.
 */
@Mapper(componentModel = "spring", uses = {ProgramasProjectosMapper.class, SistemaAguaMapper.class})
public interface AdjudicacaoMapper extends EntityMapper<AdjudicacaoDTO, Adjudicacao> {

    @Mapping(source = "programasProjectos", target = "programasProjectos")
    @Mapping(source = "idSistemaAgua.id", target = "idSistemaAguaId")
    AdjudicacaoDTO toDto(Adjudicacao adjudicacao);

    //@Mapping(source = "idSistemaAguaId", target = "id")
    Adjudicacao toEntity(AdjudicacaoDTO adjudicacaoDTO);

    default Adjudicacao fromId(Long id) {
        if (id == null) {
            return null;
        }
        Adjudicacao adjudicacao = new Adjudicacao();
        adjudicacao.setId(id);
        return adjudicacao;
    }
}
