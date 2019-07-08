package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.domain.ProgramasProjectosLog;
import com.minea.sisas.service.dto.ProgramasProjectosLogDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-07T15:41:49+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class ProgramasProjectosLogMapperImpl implements ProgramasProjectosLogMapper {

    @Override
    public List<ProgramasProjectosLog> toEntity(List<ProgramasProjectosLogDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ProgramasProjectosLog> list = new ArrayList<ProgramasProjectosLog>( arg0.size() );
        for ( ProgramasProjectosLogDTO programasProjectosLogDTO : arg0 ) {
            list.add( toEntity( programasProjectosLogDTO ) );
        }

        return list;
    }

    @Override
    public List<ProgramasProjectosLogDTO> toDto(List<ProgramasProjectosLog> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ProgramasProjectosLogDTO> list = new ArrayList<ProgramasProjectosLogDTO>( arg0.size() );
        for ( ProgramasProjectosLog programasProjectosLog : arg0 ) {
            list.add( toDto( programasProjectosLog ) );
        }

        return list;
    }

    @Override
    public ProgramasProjectosLogDTO toDto(ProgramasProjectosLog programasProjectosLog) {
        if ( programasProjectosLog == null ) {
            return null;
        }

        ProgramasProjectosLogDTO programasProjectosLogDTO = new ProgramasProjectosLogDTO();

        Long id = programasProjectosLogIdProgramasProjectosId( programasProjectosLog );
        if ( id != null ) {
            programasProjectosLogDTO.setIdProgramasProjectosId( id );
        }
        programasProjectosLogDTO.setId( programasProjectosLog.getId() );
        programasProjectosLogDTO.setAcao( programasProjectosLog.getAcao() );
        programasProjectosLogDTO.setIdUsuario( programasProjectosLog.getIdUsuario() );
        programasProjectosLogDTO.setLog( programasProjectosLog.getLog() );
        programasProjectosLogDTO.setDtLog( programasProjectosLog.getDtLog() );

        return programasProjectosLogDTO;
    }

    @Override
    public ProgramasProjectosLog toEntity(ProgramasProjectosLogDTO programasProjectosLogDTO) {
        if ( programasProjectosLogDTO == null ) {
            return null;
        }

        ProgramasProjectosLog programasProjectosLog = new ProgramasProjectosLog();

        programasProjectosLog.setId( programasProjectosLogDTO.getIdProgramasProjectosId() );
        programasProjectosLog.setAcao( programasProjectosLogDTO.getAcao() );
        programasProjectosLog.setIdUsuario( programasProjectosLogDTO.getIdUsuario() );
        programasProjectosLog.setLog( programasProjectosLogDTO.getLog() );
        programasProjectosLog.setDtLog( programasProjectosLogDTO.getDtLog() );

        return programasProjectosLog;
    }

    private Long programasProjectosLogIdProgramasProjectosId(ProgramasProjectosLog programasProjectosLog) {
        if ( programasProjectosLog == null ) {
            return null;
        }
        ProgramasProjectos idProgramasProjectos = programasProjectosLog.getIdProgramasProjectos();
        if ( idProgramasProjectos == null ) {
            return null;
        }
        Long id = idProgramasProjectos.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
