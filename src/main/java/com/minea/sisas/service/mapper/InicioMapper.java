package com.minea.sisas.service.mapper;

import com.minea.sisas.service.mapper.EntityMapper;
import com.minea.sisas.service.dto.InicioDTO;

import com.minea.sisas.domain.Inicio;
import org.mapstruct.*;

/**
 * Mapper for the entity Inicio and its DTO InicioDTO.
 */
@Mapper(componentModel = "spring", uses = {SituacaoMapper.class, SobreDnaMapper.class, NoticiasMapper.class, ProjectosMapper.class, PublicacaoMapper.class, ContactosMapper.class})
public interface InicioMapper extends EntityMapper<InicioDTO, Inicio> {

    @Mapping(source = "idSituacao.id", target = "idSituacaoId")
    @Mapping(source = "idSobreDna.id", target = "idSobreDnaId")
    @Mapping(source = "idNoticias.id", target = "idNoticiasId")
    @Mapping(source = "idProjectos.id", target = "idProjectosId")
    @Mapping(source = "idPublicacao.id", target = "idPublicacaoId")
    @Mapping(source = "idContactos.id", target = "idContactosId")
    InicioDTO toDto(Inicio inicio);

    @Mapping(source = "idContactosId", target = "id")
    Inicio toEntity(InicioDTO inicioDTO);

    default Inicio fromId(Long id) {
        if (id == null) {
            return null;
        }
        Inicio inicio = new Inicio();
        inicio.setId(id);
        return inicio;
    }
}
