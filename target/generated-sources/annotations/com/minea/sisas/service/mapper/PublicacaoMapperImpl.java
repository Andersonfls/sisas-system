package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Publicacao;
import com.minea.sisas.service.dto.PublicacaoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-07T15:41:47+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class PublicacaoMapperImpl implements PublicacaoMapper {

    @Override
    public List<Publicacao> toEntity(List<PublicacaoDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Publicacao> list = new ArrayList<Publicacao>( arg0.size() );
        for ( PublicacaoDTO publicacaoDTO : arg0 ) {
            list.add( toEntity( publicacaoDTO ) );
        }

        return list;
    }

    @Override
    public List<PublicacaoDTO> toDto(List<Publicacao> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<PublicacaoDTO> list = new ArrayList<PublicacaoDTO>( arg0.size() );
        for ( Publicacao publicacao : arg0 ) {
            list.add( toDto( publicacao ) );
        }

        return list;
    }

    @Override
    public PublicacaoDTO toDto(Publicacao publicacao) {
        if ( publicacao == null ) {
            return null;
        }

        PublicacaoDTO publicacaoDTO = new PublicacaoDTO();

        publicacaoDTO.setSituacao( publicacao.getSituacao() );
        publicacaoDTO.setId( publicacao.getId() );
        publicacaoDTO.setTituloPublicacao( publicacao.getTituloPublicacao() );
        publicacaoDTO.setTextoPublicacao( publicacao.getTextoPublicacao() );
        publicacaoDTO.setResumoTextoPublicacao( publicacao.getResumoTextoPublicacao() );

        return publicacaoDTO;
    }

    @Override
    public Publicacao toEntity(PublicacaoDTO publicacaoDTO) {
        if ( publicacaoDTO == null ) {
            return null;
        }

        Publicacao publicacao = new Publicacao();

        publicacao.setSituacao( publicacaoDTO.getSituacao() );
        publicacao.setId( publicacaoDTO.getId() );
        publicacao.setTituloPublicacao( publicacaoDTO.getTituloPublicacao() );
        publicacao.setTextoPublicacao( publicacaoDTO.getTextoPublicacao() );
        publicacao.setResumoTextoPublicacao( publicacaoDTO.getResumoTextoPublicacao() );

        return publicacao;
    }
}
