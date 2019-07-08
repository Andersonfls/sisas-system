package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Municipio;
import com.minea.sisas.service.dto.MunicipioDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-07T20:34:39+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class MunicipioMapperImpl implements MunicipioMapper {

    @Override
    public List<Municipio> toEntity(List<MunicipioDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Municipio> list = new ArrayList<Municipio>( arg0.size() );
        for ( MunicipioDTO municipioDTO : arg0 ) {
            list.add( toEntity( municipioDTO ) );
        }

        return list;
    }

    @Override
    public List<MunicipioDTO> toDto(List<Municipio> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<MunicipioDTO> list = new ArrayList<MunicipioDTO>( arg0.size() );
        for ( Municipio municipio : arg0 ) {
            list.add( toDto( municipio ) );
        }

        return list;
    }

    @Override
    public MunicipioDTO toDto(Municipio municipio) {
        if ( municipio == null ) {
            return null;
        }

        MunicipioDTO municipioDTO = new MunicipioDTO();

        municipioDTO.setProvincia( municipio.getProvincia() );
        municipioDTO.setId( municipio.getId() );
        municipioDTO.setNmMunicipio( municipio.getNmMunicipio() );

        return municipioDTO;
    }

    @Override
    public Municipio toEntity(MunicipioDTO municipioDTO) {
        if ( municipioDTO == null ) {
            return null;
        }

        Municipio municipio = new Municipio();

        municipio.setId( municipioDTO.getId() );
        municipio.setNmMunicipio( municipioDTO.getNmMunicipio() );

        return municipio;
    }
}
