package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.PublicacaoLog;
import com.minea.sisas.service.dto.PublicacaoLogDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-07T00:39:14+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class PublicacaoLogMapperImpl implements PublicacaoLogMapper {

    @Override
    public PublicacaoLog toEntity(PublicacaoLogDTO dto) {
        if ( dto == null ) {
            return null;
        }

        PublicacaoLog publicacaoLog = new PublicacaoLog();

        publicacaoLog.setId( dto.getId() );
        publicacaoLog.setAcao( dto.getAcao() );
        publicacaoLog.setIdUsuario( dto.getIdUsuario() );
        publicacaoLog.setLog( dto.getLog() );
        publicacaoLog.setDtLog( dto.getDtLog() );

        return publicacaoLog;
    }

    @Override
    public PublicacaoLogDTO toDto(PublicacaoLog entity) {
        if ( entity == null ) {
            return null;
        }

        PublicacaoLogDTO publicacaoLogDTO = new PublicacaoLogDTO();

        publicacaoLogDTO.setId( entity.getId() );
        publicacaoLogDTO.setAcao( entity.getAcao() );
        publicacaoLogDTO.setIdUsuario( entity.getIdUsuario() );
        publicacaoLogDTO.setLog( entity.getLog() );
        publicacaoLogDTO.setDtLog( entity.getDtLog() );

        return publicacaoLogDTO;
    }

    @Override
    public List<PublicacaoLog> toEntity(List<PublicacaoLogDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<PublicacaoLog> list = new ArrayList<PublicacaoLog>( dtoList.size() );
        for ( PublicacaoLogDTO publicacaoLogDTO : dtoList ) {
            list.add( toEntity( publicacaoLogDTO ) );
        }

        return list;
    }

    @Override
    public List<PublicacaoLogDTO> toDto(List<PublicacaoLog> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PublicacaoLogDTO> list = new ArrayList<PublicacaoLogDTO>( entityList.size() );
        for ( PublicacaoLog publicacaoLog : entityList ) {
            list.add( toDto( publicacaoLog ) );
        }

        return list;
    }
}
