package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.SistemaAguaLog;
import com.minea.sisas.service.dto.SistemaAguaLogDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-07T23:23:36+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class SistemaAguaLogMapperImpl implements SistemaAguaLogMapper {

    @Override
    public List<SistemaAguaLog> toEntity(List<SistemaAguaLogDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<SistemaAguaLog> list = new ArrayList<SistemaAguaLog>( arg0.size() );
        for ( SistemaAguaLogDTO sistemaAguaLogDTO : arg0 ) {
            list.add( toEntity( sistemaAguaLogDTO ) );
        }

        return list;
    }

    @Override
    public List<SistemaAguaLogDTO> toDto(List<SistemaAguaLog> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<SistemaAguaLogDTO> list = new ArrayList<SistemaAguaLogDTO>( arg0.size() );
        for ( SistemaAguaLog sistemaAguaLog : arg0 ) {
            list.add( toDto( sistemaAguaLog ) );
        }

        return list;
    }

    @Override
    public SistemaAguaLogDTO toDto(SistemaAguaLog sistemaAguaLog) {
        if ( sistemaAguaLog == null ) {
            return null;
        }

        SistemaAguaLogDTO sistemaAguaLogDTO = new SistemaAguaLogDTO();

        sistemaAguaLogDTO.setIdSistemaAguaId( sistemaAguaLog.getIdSistemaAgua() );
        sistemaAguaLogDTO.setId( sistemaAguaLog.getId() );
        sistemaAguaLogDTO.setAcao( sistemaAguaLog.getAcao() );
        sistemaAguaLogDTO.setIdUsuario( sistemaAguaLog.getIdUsuario() );
        sistemaAguaLogDTO.setLog( sistemaAguaLog.getLog() );
        sistemaAguaLogDTO.setDtLog( sistemaAguaLog.getDtLog() );

        return sistemaAguaLogDTO;
    }

    @Override
    public SistemaAguaLog toEntity(SistemaAguaLogDTO sistemaAguaLogDTO) {
        if ( sistemaAguaLogDTO == null ) {
            return null;
        }

        SistemaAguaLog sistemaAguaLog = new SistemaAguaLog();

        sistemaAguaLog.setIdSistemaAgua( sistemaAguaLogDTO.getIdSistemaAguaId() );
        sistemaAguaLog.setId( sistemaAguaLogDTO.getId() );
        sistemaAguaLog.setAcao( sistemaAguaLogDTO.getAcao() );
        sistemaAguaLog.setIdUsuario( sistemaAguaLogDTO.getIdUsuario() );
        sistemaAguaLog.setLog( sistemaAguaLogDTO.getLog() );
        sistemaAguaLog.setDtLog( sistemaAguaLogDTO.getDtLog() );

        return sistemaAguaLog;
    }
}
