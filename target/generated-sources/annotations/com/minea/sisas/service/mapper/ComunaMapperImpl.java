package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Comuna;
import com.minea.sisas.service.dto.ComunaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-07T20:34:38+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class ComunaMapperImpl implements ComunaMapper {

    @Override
    public List<Comuna> toEntity(List<ComunaDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Comuna> list = new ArrayList<Comuna>( arg0.size() );
        for ( ComunaDTO comunaDTO : arg0 ) {
            list.add( toEntity( comunaDTO ) );
        }

        return list;
    }

    @Override
    public List<ComunaDTO> toDto(List<Comuna> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ComunaDTO> list = new ArrayList<ComunaDTO>( arg0.size() );
        for ( Comuna comuna : arg0 ) {
            list.add( toDto( comuna ) );
        }

        return list;
    }

    @Override
    public ComunaDTO toDto(Comuna comuna) {
        if ( comuna == null ) {
            return null;
        }

        ComunaDTO comunaDTO = new ComunaDTO();

        comunaDTO.setMunicipio( comuna.getMunicipio() );
        comunaDTO.setId( comuna.getId() );
        comunaDTO.setNmComuna( comuna.getNmComuna() );

        return comunaDTO;
    }

    @Override
    public Comuna toEntity(ComunaDTO comunaDTO) {
        if ( comunaDTO == null ) {
            return null;
        }

        Comuna comuna = new Comuna();

        comuna.setMunicipio( comunaDTO.getMunicipio() );
        comuna.setId( comunaDTO.getId() );
        comuna.setNmComuna( comunaDTO.getNmComuna() );

        return comuna;
    }
}
