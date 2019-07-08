package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.SobreDna;
import com.minea.sisas.service.dto.SobreDnaDTO;
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
public class SobreDnaMapperImpl implements SobreDnaMapper {

    @Override
    public List<SobreDna> toEntity(List<SobreDnaDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<SobreDna> list = new ArrayList<SobreDna>( arg0.size() );
        for ( SobreDnaDTO sobreDnaDTO : arg0 ) {
            list.add( toEntity( sobreDnaDTO ) );
        }

        return list;
    }

    @Override
    public List<SobreDnaDTO> toDto(List<SobreDna> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<SobreDnaDTO> list = new ArrayList<SobreDnaDTO>( arg0.size() );
        for ( SobreDna sobreDna : arg0 ) {
            list.add( toDto( sobreDna ) );
        }

        return list;
    }

    @Override
    public SobreDnaDTO toDto(SobreDna sobreDna) {
        if ( sobreDna == null ) {
            return null;
        }

        SobreDnaDTO sobreDnaDTO = new SobreDnaDTO();

        sobreDnaDTO.setId( sobreDna.getId() );
        sobreDnaDTO.setTituloSobreDna( sobreDna.getTituloSobreDna() );
        sobreDnaDTO.setTextoSobreDna( sobreDna.getTextoSobreDna() );
        sobreDnaDTO.setResumoTextoSobreDna( sobreDna.getResumoTextoSobreDna() );
        sobreDnaDTO.setSituacao( sobreDna.getSituacao() );

        return sobreDnaDTO;
    }

    @Override
    public SobreDna toEntity(SobreDnaDTO sobreDnaDTO) {
        if ( sobreDnaDTO == null ) {
            return null;
        }

        SobreDna sobreDna = new SobreDna();

        sobreDna.setId( sobreDnaDTO.getId() );
        sobreDna.setTituloSobreDna( sobreDnaDTO.getTituloSobreDna() );
        sobreDna.setTextoSobreDna( sobreDnaDTO.getTextoSobreDna() );
        sobreDna.setResumoTextoSobreDna( sobreDnaDTO.getResumoTextoSobreDna() );
        sobreDna.setSituacao( sobreDnaDTO.getSituacao() );

        return sobreDna;
    }
}
