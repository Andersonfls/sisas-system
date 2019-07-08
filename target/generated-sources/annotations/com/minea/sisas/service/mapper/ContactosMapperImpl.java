package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Contactos;
import com.minea.sisas.domain.Situacao;
import com.minea.sisas.service.dto.ContactosDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-07T15:41:48+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class ContactosMapperImpl implements ContactosMapper {

    @Autowired
    private SituacaoMapper situacaoMapper;

    @Override
    public List<Contactos> toEntity(List<ContactosDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Contactos> list = new ArrayList<Contactos>( arg0.size() );
        for ( ContactosDTO contactosDTO : arg0 ) {
            list.add( toEntity( contactosDTO ) );
        }

        return list;
    }

    @Override
    public List<ContactosDTO> toDto(List<Contactos> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ContactosDTO> list = new ArrayList<ContactosDTO>( arg0.size() );
        for ( Contactos contactos : arg0 ) {
            list.add( toDto( contactos ) );
        }

        return list;
    }

    @Override
    public ContactosDTO toDto(Contactos contactos) {
        if ( contactos == null ) {
            return null;
        }

        ContactosDTO contactosDTO = new ContactosDTO();

        Long id = contactosIdSituacaoId( contactos );
        if ( id != null ) {
            contactosDTO.setIdSituacaoId( id );
        }
        contactosDTO.setId( contactos.getId() );
        contactosDTO.setIdContactos( contactos.getIdContactos() );
        contactosDTO.setNmContactos( contactos.getNmContactos() );
        contactosDTO.setTextoContactos( contactos.getTextoContactos() );
        contactosDTO.setResumoTextoContactos( contactos.getResumoTextoContactos() );
        contactosDTO.setDtLancamento( contactos.getDtLancamento() );
        contactosDTO.setDtUltimaAlteracao( contactos.getDtUltimaAlteracao() );

        return contactosDTO;
    }

    @Override
    public Contactos toEntity(ContactosDTO contactosDTO) {
        if ( contactosDTO == null ) {
            return null;
        }

        Contactos contactos = new Contactos();

        contactos.setIdSituacao( situacaoMapper.fromId( contactosDTO.getIdSituacaoId() ) );
        contactos.setId( contactosDTO.getId() );
        contactos.setIdContactos( contactosDTO.getIdContactos() );
        contactos.setNmContactos( contactosDTO.getNmContactos() );
        contactos.setTextoContactos( contactosDTO.getTextoContactos() );
        contactos.setResumoTextoContactos( contactosDTO.getResumoTextoContactos() );
        contactos.setDtLancamento( contactosDTO.getDtLancamento() );
        contactos.setDtUltimaAlteracao( contactosDTO.getDtUltimaAlteracao() );

        return contactos;
    }

    private Long contactosIdSituacaoId(Contactos contactos) {
        if ( contactos == null ) {
            return null;
        }
        Situacao idSituacao = contactos.getIdSituacao();
        if ( idSituacao == null ) {
            return null;
        }
        Long id = idSituacao.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
