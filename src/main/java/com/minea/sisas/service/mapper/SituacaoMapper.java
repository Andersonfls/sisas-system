package com.minea.sisas.service.mapper;

import com.minea.sisas.service.mapper.EntityMapper;
import com.minea.sisas.service.dto.SituacaoDTO;

import com.minea.sisas.domain.Situacao;
import org.mapstruct.*;

/**
 * Mapper for the entity Situacao and its DTO SituacaoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SituacaoMapper extends EntityMapper<SituacaoDTO, Situacao> {

    Situacao toEntity(SituacaoDTO situacaoDTO);

    default Situacao fromId(Long id) {
        if (id == null) {
            return null;
        }
        Situacao situacao = new Situacao();
        situacao.setId(id);
        return situacao;
    }
}
