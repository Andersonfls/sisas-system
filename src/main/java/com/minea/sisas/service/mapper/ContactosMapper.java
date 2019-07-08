package com.minea.sisas.service.mapper;

import com.minea.sisas.service.dto.ContactosDTO;

import com.minea.sisas.domain.Contactos;
import org.mapstruct.*;

/**
 * Mapper for the entity Contactos and its DTO ContactosDTO.
 */
@Mapper(componentModel = "spring", uses = {SituacaoMapper.class})
public interface ContactosMapper extends EntityMapper<ContactosDTO, Contactos> {

    @Mapping(source = "idSituacao.id", target = "idSituacaoId")
    ContactosDTO toDto(Contactos contactos);

    @Mapping(source = "idSituacaoId", target = "idSituacao")
    @Mapping(target = "inicios", ignore = true)
    Contactos toEntity(ContactosDTO contactosDTO);

    default Contactos fromId(Long id) {
        if (id == null) {
            return null;
        }
        Contactos contactos = new Contactos();
        contactos.setId(id);
        return contactos;
    }
}
