package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.IndicadorProducaoLog;
import com.minea.sisas.service.dto.IndicadorProducaoLogDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-08T00:56:44+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class IndicadorProducaoLogMapperImpl implements IndicadorProducaoLogMapper {

    @Override
    public List<IndicadorProducaoLog> toEntity(List<IndicadorProducaoLogDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<IndicadorProducaoLog> list = new ArrayList<IndicadorProducaoLog>( arg0.size() );
        for ( IndicadorProducaoLogDTO indicadorProducaoLogDTO : arg0 ) {
            list.add( toEntity( indicadorProducaoLogDTO ) );
        }

        return list;
    }

    @Override
    public List<IndicadorProducaoLogDTO> toDto(List<IndicadorProducaoLog> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<IndicadorProducaoLogDTO> list = new ArrayList<IndicadorProducaoLogDTO>( arg0.size() );
        for ( IndicadorProducaoLog indicadorProducaoLog : arg0 ) {
            list.add( toDto( indicadorProducaoLog ) );
        }

        return list;
    }

    @Override
    public IndicadorProducaoLogDTO toDto(IndicadorProducaoLog indicadorProducaoLog) {
        if ( indicadorProducaoLog == null ) {
            return null;
        }

        IndicadorProducaoLogDTO indicadorProducaoLogDTO = new IndicadorProducaoLogDTO();

        indicadorProducaoLogDTO.setIdIndicadorProducaoId( indicadorProducaoLog.getIdIndicadorProducao() );
        indicadorProducaoLogDTO.setId( indicadorProducaoLog.getId() );
        indicadorProducaoLogDTO.setAcao( indicadorProducaoLog.getAcao() );
        indicadorProducaoLogDTO.setIdUsuario( indicadorProducaoLog.getIdUsuario() );
        indicadorProducaoLogDTO.setLog( indicadorProducaoLog.getLog() );
        indicadorProducaoLogDTO.setDtLog( indicadorProducaoLog.getDtLog() );

        return indicadorProducaoLogDTO;
    }

    @Override
    public IndicadorProducaoLog toEntity(IndicadorProducaoLogDTO indicadorProducaoLogDTO) {
        if ( indicadorProducaoLogDTO == null ) {
            return null;
        }

        IndicadorProducaoLog indicadorProducaoLog = new IndicadorProducaoLog();

        indicadorProducaoLog.setIdIndicadorProducao( indicadorProducaoLogDTO.getIdIndicadorProducaoId() );
        indicadorProducaoLog.setId( indicadorProducaoLogDTO.getId() );
        indicadorProducaoLog.setAcao( indicadorProducaoLogDTO.getAcao() );
        indicadorProducaoLog.setIdUsuario( indicadorProducaoLogDTO.getIdUsuario() );
        indicadorProducaoLog.setLog( indicadorProducaoLogDTO.getLog() );
        indicadorProducaoLog.setDtLog( indicadorProducaoLogDTO.getDtLog() );

        return indicadorProducaoLog;
    }
}
