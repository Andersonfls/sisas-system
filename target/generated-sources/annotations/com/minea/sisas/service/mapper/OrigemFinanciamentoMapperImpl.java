package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.OrigemFinanciamento;
import com.minea.sisas.service.dto.OrigemFinanciamentoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-07T00:39:16+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class OrigemFinanciamentoMapperImpl implements OrigemFinanciamentoMapper {

    @Override
    public OrigemFinanciamento toEntity(OrigemFinanciamentoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        OrigemFinanciamento origemFinanciamento = new OrigemFinanciamento();

        origemFinanciamento.setId( dto.getId() );
        origemFinanciamento.setDescricaoOrigemFinanciamento( dto.getDescricaoOrigemFinanciamento() );

        return origemFinanciamento;
    }

    @Override
    public OrigemFinanciamentoDTO toDto(OrigemFinanciamento entity) {
        if ( entity == null ) {
            return null;
        }

        OrigemFinanciamentoDTO origemFinanciamentoDTO = new OrigemFinanciamentoDTO();

        origemFinanciamentoDTO.setId( entity.getId() );
        origemFinanciamentoDTO.setDescricaoOrigemFinanciamento( entity.getDescricaoOrigemFinanciamento() );

        return origemFinanciamentoDTO;
    }

    @Override
    public List<OrigemFinanciamento> toEntity(List<OrigemFinanciamentoDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<OrigemFinanciamento> list = new ArrayList<OrigemFinanciamento>( dtoList.size() );
        for ( OrigemFinanciamentoDTO origemFinanciamentoDTO : dtoList ) {
            list.add( toEntity( origemFinanciamentoDTO ) );
        }

        return list;
    }

    @Override
    public List<OrigemFinanciamentoDTO> toDto(List<OrigemFinanciamento> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<OrigemFinanciamentoDTO> list = new ArrayList<OrigemFinanciamentoDTO>( entityList.size() );
        for ( OrigemFinanciamento origemFinanciamento : entityList ) {
            list.add( toDto( origemFinanciamento ) );
        }

        return list;
    }
}
