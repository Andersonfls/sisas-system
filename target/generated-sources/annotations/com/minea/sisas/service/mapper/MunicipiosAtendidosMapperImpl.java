package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Municipio;
import com.minea.sisas.domain.MunicipiosAtendidos;
import com.minea.sisas.service.dto.MunicipiosAtendidosDTO;
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
public class MunicipiosAtendidosMapperImpl implements MunicipiosAtendidosMapper {

    @Override
    public List<MunicipiosAtendidos> toEntity(List<MunicipiosAtendidosDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<MunicipiosAtendidos> list = new ArrayList<MunicipiosAtendidos>( arg0.size() );
        for ( MunicipiosAtendidosDTO municipiosAtendidosDTO : arg0 ) {
            list.add( toEntity( municipiosAtendidosDTO ) );
        }

        return list;
    }

    @Override
    public List<MunicipiosAtendidosDTO> toDto(List<MunicipiosAtendidos> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<MunicipiosAtendidosDTO> list = new ArrayList<MunicipiosAtendidosDTO>( arg0.size() );
        for ( MunicipiosAtendidos municipiosAtendidos : arg0 ) {
            list.add( toDto( municipiosAtendidos ) );
        }

        return list;
    }

    @Override
    public MunicipiosAtendidosDTO toDto(MunicipiosAtendidos municipiosAtendidos) {
        if ( municipiosAtendidos == null ) {
            return null;
        }

        MunicipiosAtendidosDTO municipiosAtendidosDTO = new MunicipiosAtendidosDTO();

        Long id = municipiosAtendidosIdMunicipioId( municipiosAtendidos );
        if ( id != null ) {
            municipiosAtendidosDTO.setIdMunicipioId( id );
        }
        municipiosAtendidosDTO.setId( municipiosAtendidos.getId() );

        return municipiosAtendidosDTO;
    }

    @Override
    public MunicipiosAtendidos toEntity(MunicipiosAtendidosDTO municipiosAtendidosDTO) {
        if ( municipiosAtendidosDTO == null ) {
            return null;
        }

        MunicipiosAtendidos municipiosAtendidos = new MunicipiosAtendidos();

        municipiosAtendidos.setId( municipiosAtendidosDTO.getIdMunicipioId() );

        return municipiosAtendidos;
    }

    private Long municipiosAtendidosIdMunicipioId(MunicipiosAtendidos municipiosAtendidos) {
        if ( municipiosAtendidos == null ) {
            return null;
        }
        Municipio idMunicipio = municipiosAtendidos.getIdMunicipio();
        if ( idMunicipio == null ) {
            return null;
        }
        Long id = idMunicipio.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
