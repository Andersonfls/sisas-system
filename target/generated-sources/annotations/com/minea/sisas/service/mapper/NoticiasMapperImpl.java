package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Noticias;
import com.minea.sisas.domain.Situacao;
import com.minea.sisas.service.dto.NoticiasDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-07T15:41:49+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class NoticiasMapperImpl implements NoticiasMapper {

    @Override
    public List<Noticias> toEntity(List<NoticiasDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Noticias> list = new ArrayList<Noticias>( arg0.size() );
        for ( NoticiasDTO noticiasDTO : arg0 ) {
            list.add( toEntity( noticiasDTO ) );
        }

        return list;
    }

    @Override
    public List<NoticiasDTO> toDto(List<Noticias> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<NoticiasDTO> list = new ArrayList<NoticiasDTO>( arg0.size() );
        for ( Noticias noticias : arg0 ) {
            list.add( toDto( noticias ) );
        }

        return list;
    }

    @Override
    public NoticiasDTO toDto(Noticias noticias) {
        if ( noticias == null ) {
            return null;
        }

        NoticiasDTO noticiasDTO = new NoticiasDTO();

        Long id = noticiasIdSituacaoId( noticias );
        if ( id != null ) {
            noticiasDTO.setIdSituacaoId( id );
        }
        noticiasDTO.setId( noticias.getId() );
        noticiasDTO.setTituloNoticias( noticias.getTituloNoticias() );
        noticiasDTO.setTextoNoticias( noticias.getTextoNoticias() );
        noticiasDTO.setResumoTextoNoticias( noticias.getResumoTextoNoticias() );

        return noticiasDTO;
    }

    @Override
    public Noticias toEntity(NoticiasDTO noticiasDTO) {
        if ( noticiasDTO == null ) {
            return null;
        }

        Noticias noticias = new Noticias();

        noticias.setId( noticiasDTO.getIdSituacaoId() );
        noticias.setTituloNoticias( noticiasDTO.getTituloNoticias() );
        noticias.setTextoNoticias( noticiasDTO.getTextoNoticias() );
        noticias.setResumoTextoNoticias( noticiasDTO.getResumoTextoNoticias() );

        return noticias;
    }

    private Long noticiasIdSituacaoId(Noticias noticias) {
        if ( noticias == null ) {
            return null;
        }
        Situacao idSituacao = noticias.getIdSituacao();
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
