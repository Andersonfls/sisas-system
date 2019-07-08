package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Contactos;
import com.minea.sisas.domain.Inicio;
import com.minea.sisas.domain.Noticias;
import com.minea.sisas.domain.Projectos;
import com.minea.sisas.domain.Publicacao;
import com.minea.sisas.domain.Situacao;
import com.minea.sisas.domain.SobreDna;
import com.minea.sisas.service.dto.InicioDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-07T15:41:48+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class InicioMapperImpl implements InicioMapper {

    @Override
    public List<Inicio> toEntity(List<InicioDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Inicio> list = new ArrayList<Inicio>( arg0.size() );
        for ( InicioDTO inicioDTO : arg0 ) {
            list.add( toEntity( inicioDTO ) );
        }

        return list;
    }

    @Override
    public List<InicioDTO> toDto(List<Inicio> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<InicioDTO> list = new ArrayList<InicioDTO>( arg0.size() );
        for ( Inicio inicio : arg0 ) {
            list.add( toDto( inicio ) );
        }

        return list;
    }

    @Override
    public InicioDTO toDto(Inicio inicio) {
        if ( inicio == null ) {
            return null;
        }

        InicioDTO inicioDTO = new InicioDTO();

        Long id = inicioIdSituacaoId( inicio );
        if ( id != null ) {
            inicioDTO.setIdSituacaoId( id );
        }
        Long id1 = inicioIdSobreDnaId( inicio );
        if ( id1 != null ) {
            inicioDTO.setIdSobreDnaId( id1 );
        }
        Long id2 = inicioIdPublicacaoId( inicio );
        if ( id2 != null ) {
            inicioDTO.setIdPublicacaoId( id2 );
        }
        Long id3 = inicioIdContactosId( inicio );
        if ( id3 != null ) {
            inicioDTO.setIdContactosId( id3 );
        }
        Long id4 = inicioIdProjectosId( inicio );
        if ( id4 != null ) {
            inicioDTO.setIdProjectosId( id4 );
        }
        Long id5 = inicioIdNoticiasId( inicio );
        if ( id5 != null ) {
            inicioDTO.setIdNoticiasId( id5 );
        }
        inicioDTO.setId( inicio.getId() );
        inicioDTO.setDestaques( inicio.getDestaques() );
        inicioDTO.setUltimasNoticias( inicio.getUltimasNoticias() );
        inicioDTO.setPublicacoes( inicio.getPublicacoes() );
        inicioDTO.setUrl( inicio.getUrl() );
        inicioDTO.setAlt( inicio.getAlt() );

        return inicioDTO;
    }

    @Override
    public Inicio toEntity(InicioDTO inicioDTO) {
        if ( inicioDTO == null ) {
            return null;
        }

        Inicio inicio = new Inicio();

        inicio.setId( inicioDTO.getIdContactosId() );
        inicio.setDestaques( inicioDTO.getDestaques() );
        inicio.setUltimasNoticias( inicioDTO.getUltimasNoticias() );
        inicio.setPublicacoes( inicioDTO.getPublicacoes() );
        inicio.setUrl( inicioDTO.getUrl() );
        inicio.setAlt( inicioDTO.getAlt() );

        return inicio;
    }

    private Long inicioIdSituacaoId(Inicio inicio) {
        if ( inicio == null ) {
            return null;
        }
        Situacao idSituacao = inicio.getIdSituacao();
        if ( idSituacao == null ) {
            return null;
        }
        Long id = idSituacao.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long inicioIdSobreDnaId(Inicio inicio) {
        if ( inicio == null ) {
            return null;
        }
        SobreDna idSobreDna = inicio.getIdSobreDna();
        if ( idSobreDna == null ) {
            return null;
        }
        Long id = idSobreDna.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long inicioIdPublicacaoId(Inicio inicio) {
        if ( inicio == null ) {
            return null;
        }
        Publicacao idPublicacao = inicio.getIdPublicacao();
        if ( idPublicacao == null ) {
            return null;
        }
        Long id = idPublicacao.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long inicioIdContactosId(Inicio inicio) {
        if ( inicio == null ) {
            return null;
        }
        Contactos idContactos = inicio.getIdContactos();
        if ( idContactos == null ) {
            return null;
        }
        Long id = idContactos.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long inicioIdProjectosId(Inicio inicio) {
        if ( inicio == null ) {
            return null;
        }
        Projectos idProjectos = inicio.getIdProjectos();
        if ( idProjectos == null ) {
            return null;
        }
        Long id = idProjectos.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long inicioIdNoticiasId(Inicio inicio) {
        if ( inicio == null ) {
            return null;
        }
        Noticias idNoticias = inicio.getIdNoticias();
        if ( idNoticias == null ) {
            return null;
        }
        Long id = idNoticias.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
