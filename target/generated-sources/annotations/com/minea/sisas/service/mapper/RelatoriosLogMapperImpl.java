package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.RelatoriosLog;
import com.minea.sisas.service.dto.RelatoriosLogDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-07T00:39:13+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class RelatoriosLogMapperImpl implements RelatoriosLogMapper {

    @Override
    public RelatoriosLog toEntity(RelatoriosLogDTO dto) {
        if ( dto == null ) {
            return null;
        }

        RelatoriosLog relatoriosLog = new RelatoriosLog();

        relatoriosLog.setId( dto.getId() );
        relatoriosLog.setAcao( dto.getAcao() );
        relatoriosLog.setIdUsuario( dto.getIdUsuario() );
        relatoriosLog.setLog( dto.getLog() );
        relatoriosLog.setDtLog( dto.getDtLog() );

        return relatoriosLog;
    }

    @Override
    public RelatoriosLogDTO toDto(RelatoriosLog entity) {
        if ( entity == null ) {
            return null;
        }

        RelatoriosLogDTO relatoriosLogDTO = new RelatoriosLogDTO();

        relatoriosLogDTO.setId( entity.getId() );
        relatoriosLogDTO.setAcao( entity.getAcao() );
        relatoriosLogDTO.setIdUsuario( entity.getIdUsuario() );
        relatoriosLogDTO.setLog( entity.getLog() );
        relatoriosLogDTO.setDtLog( entity.getDtLog() );

        return relatoriosLogDTO;
    }

    @Override
    public List<RelatoriosLog> toEntity(List<RelatoriosLogDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<RelatoriosLog> list = new ArrayList<RelatoriosLog>( dtoList.size() );
        for ( RelatoriosLogDTO relatoriosLogDTO : dtoList ) {
            list.add( toEntity( relatoriosLogDTO ) );
        }

        return list;
    }

    @Override
    public List<RelatoriosLogDTO> toDto(List<RelatoriosLog> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RelatoriosLogDTO> list = new ArrayList<RelatoriosLogDTO>( entityList.size() );
        for ( RelatoriosLog relatoriosLog : entityList ) {
            list.add( toDto( relatoriosLog ) );
        }

        return list;
    }
}
