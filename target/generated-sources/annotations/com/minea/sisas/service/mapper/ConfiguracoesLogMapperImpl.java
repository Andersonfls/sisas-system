package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.ConfiguracoesLog;
import com.minea.sisas.service.dto.ConfiguracoesLogDTO;
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
public class ConfiguracoesLogMapperImpl implements ConfiguracoesLogMapper {

    @Override
    public ConfiguracoesLog toEntity(ConfiguracoesLogDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ConfiguracoesLog configuracoesLog = new ConfiguracoesLog();

        configuracoesLog.setId( dto.getId() );
        configuracoesLog.setAcao( dto.getAcao() );
        configuracoesLog.setIdUsuario( dto.getIdUsuario() );
        configuracoesLog.setLog( dto.getLog() );
        configuracoesLog.setDtLog( dto.getDtLog() );

        return configuracoesLog;
    }

    @Override
    public ConfiguracoesLogDTO toDto(ConfiguracoesLog entity) {
        if ( entity == null ) {
            return null;
        }

        ConfiguracoesLogDTO configuracoesLogDTO = new ConfiguracoesLogDTO();

        configuracoesLogDTO.setId( entity.getId() );
        configuracoesLogDTO.setAcao( entity.getAcao() );
        configuracoesLogDTO.setIdUsuario( entity.getIdUsuario() );
        configuracoesLogDTO.setLog( entity.getLog() );
        configuracoesLogDTO.setDtLog( entity.getDtLog() );

        return configuracoesLogDTO;
    }

    @Override
    public List<ConfiguracoesLog> toEntity(List<ConfiguracoesLogDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<ConfiguracoesLog> list = new ArrayList<ConfiguracoesLog>( dtoList.size() );
        for ( ConfiguracoesLogDTO configuracoesLogDTO : dtoList ) {
            list.add( toEntity( configuracoesLogDTO ) );
        }

        return list;
    }

    @Override
    public List<ConfiguracoesLogDTO> toDto(List<ConfiguracoesLog> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ConfiguracoesLogDTO> list = new ArrayList<ConfiguracoesLogDTO>( entityList.size() );
        for ( ConfiguracoesLog configuracoesLog : entityList ) {
            list.add( toDto( configuracoesLog ) );
        }

        return list;
    }
}
