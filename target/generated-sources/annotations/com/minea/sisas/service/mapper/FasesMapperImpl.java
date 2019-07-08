package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Fases;
import com.minea.sisas.service.dto.FasesDTO;
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
public class FasesMapperImpl implements FasesMapper {

    @Override
    public Fases toEntity(FasesDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Fases fases = new Fases();

        fases.setId( dto.getId() );
        fases.setDescricaoFase( dto.getDescricaoFase() );

        return fases;
    }

    @Override
    public FasesDTO toDto(Fases entity) {
        if ( entity == null ) {
            return null;
        }

        FasesDTO fasesDTO = new FasesDTO();

        fasesDTO.setId( entity.getId() );
        fasesDTO.setDescricaoFase( entity.getDescricaoFase() );

        return fasesDTO;
    }

    @Override
    public List<Fases> toEntity(List<FasesDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Fases> list = new ArrayList<Fases>( dtoList.size() );
        for ( FasesDTO fasesDTO : dtoList ) {
            list.add( toEntity( fasesDTO ) );
        }

        return list;
    }

    @Override
    public List<FasesDTO> toDto(List<Fases> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<FasesDTO> list = new ArrayList<FasesDTO>( entityList.size() );
        for ( Fases fases : entityList ) {
            list.add( toDto( fases ) );
        }

        return list;
    }
}
