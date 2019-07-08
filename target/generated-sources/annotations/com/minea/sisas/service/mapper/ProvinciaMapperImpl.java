package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Provincia;
import com.minea.sisas.service.dto.ProvinciaDTO;
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
public class ProvinciaMapperImpl implements ProvinciaMapper {

    @Override
    public ProvinciaDTO toDto(Provincia arg0) {
        if ( arg0 == null ) {
            return null;
        }

        ProvinciaDTO provinciaDTO = new ProvinciaDTO();

        provinciaDTO.setId( arg0.getId() );
        provinciaDTO.setNmProvincia( arg0.getNmProvincia() );

        return provinciaDTO;
    }

    @Override
    public List<Provincia> toEntity(List<ProvinciaDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Provincia> list = new ArrayList<Provincia>( arg0.size() );
        for ( ProvinciaDTO provinciaDTO : arg0 ) {
            list.add( toEntity( provinciaDTO ) );
        }

        return list;
    }

    @Override
    public List<ProvinciaDTO> toDto(List<Provincia> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ProvinciaDTO> list = new ArrayList<ProvinciaDTO>( arg0.size() );
        for ( Provincia provincia : arg0 ) {
            list.add( toDto( provincia ) );
        }

        return list;
    }

    @Override
    public Provincia toEntity(ProvinciaDTO provinciaDTO) {
        if ( provinciaDTO == null ) {
            return null;
        }

        Provincia provincia = new Provincia();

        provincia.setId( provinciaDTO.getId() );
        provincia.setNmProvincia( provinciaDTO.getNmProvincia() );

        return provincia;
    }
}
