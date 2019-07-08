package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Situacao;
import com.minea.sisas.service.dto.SituacaoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-07T00:39:15+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class SituacaoMapperImpl implements SituacaoMapper {

    @Override
    public SituacaoDTO toDto(Situacao entity) {
        if ( entity == null ) {
            return null;
        }

        SituacaoDTO situacaoDTO = new SituacaoDTO();

        situacaoDTO.setId( entity.getId() );
        situacaoDTO.setNmSituacao( entity.getNmSituacao() );

        return situacaoDTO;
    }

    @Override
    public List<Situacao> toEntity(List<SituacaoDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Situacao> list = new ArrayList<Situacao>( dtoList.size() );
        for ( SituacaoDTO situacaoDTO : dtoList ) {
            list.add( toEntity( situacaoDTO ) );
        }

        return list;
    }

    @Override
    public List<SituacaoDTO> toDto(List<Situacao> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SituacaoDTO> list = new ArrayList<SituacaoDTO>( entityList.size() );
        for ( Situacao situacao : entityList ) {
            list.add( toDto( situacao ) );
        }

        return list;
    }

    @Override
    public Situacao toEntity(SituacaoDTO situacaoDTO) {
        if ( situacaoDTO == null ) {
            return null;
        }

        Situacao situacao = new Situacao();

        situacao.setId( situacaoDTO.getId() );
        situacao.setNmSituacao( situacaoDTO.getNmSituacao() );

        return situacao;
    }
}
