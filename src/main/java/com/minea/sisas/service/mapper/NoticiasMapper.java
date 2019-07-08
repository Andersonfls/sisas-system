package com.minea.sisas.service.mapper;

import com.minea.sisas.service.mapper.EntityMapper;
import com.minea.sisas.service.dto.NoticiasDTO;

import com.minea.sisas.domain.Noticias;
import org.mapstruct.*;

/**
 * Mapper for the entity Noticias and its DTO NoticiasDTO.
 */
@Mapper(componentModel = "spring", uses = {SituacaoMapper.class})
public interface NoticiasMapper extends EntityMapper<NoticiasDTO, Noticias> {

    @Mapping(source = "idSituacao.id", target = "idSituacaoId")
    NoticiasDTO toDto(Noticias noticias);

    @Mapping(source = "idSituacaoId", target = "id")
    @Mapping(target = "inicios", ignore = true)
    Noticias toEntity(NoticiasDTO noticiasDTO);

    default Noticias fromId(Long id) {
        if (id == null) {
            return null;
        }
        Noticias noticias = new Noticias();
        noticias.setId(id);
        return noticias;
    }
}
