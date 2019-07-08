package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Publicacao;
import com.minea.sisas.service.dto.PublicacaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Publicacao and its DTO PublicacaoDTO.
 */
@Mapper(componentModel = "spring", uses = {SituacaoMapper.class})
public interface PublicacaoMapper extends EntityMapper<PublicacaoDTO, Publicacao> {

    @Mapping(source = "situacao", target = "situacao")
    PublicacaoDTO toDto(Publicacao publicacao);

    @Mapping(source = "situacao", target = "situacao")
    Publicacao toEntity(PublicacaoDTO publicacaoDTO);

    default Publicacao fromId(Long id) {
        if (id == null) {
            return null;
        }
        Publicacao publicacao = new Publicacao();
        publicacao.setId(id);
        return publicacao;
    }
}
