package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Comuna;
import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.service.dto.ProgramasProjectosDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-07T19:37:02+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class ProgramasProjectosMapperImpl implements ProgramasProjectosMapper {

    @Autowired
    private ComunaMapper comunaMapper;

    @Override
    public List<ProgramasProjectos> toEntity(List<ProgramasProjectosDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ProgramasProjectos> list = new ArrayList<ProgramasProjectos>( arg0.size() );
        for ( ProgramasProjectosDTO programasProjectosDTO : arg0 ) {
            list.add( toEntity( programasProjectosDTO ) );
        }

        return list;
    }

    @Override
    public List<ProgramasProjectosDTO> toDto(List<ProgramasProjectos> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ProgramasProjectosDTO> list = new ArrayList<ProgramasProjectosDTO>( arg0.size() );
        for ( ProgramasProjectos programasProjectos : arg0 ) {
            list.add( toDto( programasProjectos ) );
        }

        return list;
    }

    @Override
    public ProgramasProjectosDTO toDto(ProgramasProjectos programasProjectos) {
        if ( programasProjectos == null ) {
            return null;
        }

        ProgramasProjectosDTO programasProjectosDTO = new ProgramasProjectosDTO();

        Long id = programasProjectosIdComunaId( programasProjectos );
        if ( id != null ) {
            programasProjectosDTO.setIdComunaId( id );
        }
        programasProjectosDTO.setId( programasProjectos.getId() );
        programasProjectosDTO.setIdProgramasProjectos( programasProjectos.getIdProgramasProjectos() );
        programasProjectosDTO.setDtLancamento( programasProjectos.getDtLancamento() );
        programasProjectosDTO.setDtUltimaAlteracao( programasProjectos.getDtUltimaAlteracao() );
        programasProjectosDTO.setIdUsuario( programasProjectos.getIdUsuario() );
        programasProjectosDTO.setNmDesignacaoProjeto( programasProjectos.getNmDesignacaoProjeto() );
        programasProjectosDTO.setNmDescricaoProjeto( programasProjectos.getNmDescricaoProjeto() );
        programasProjectosDTO.setIdSaaAssociado( programasProjectos.getIdSaaAssociado() );
        programasProjectosDTO.setTipoFinanciamento( programasProjectos.getTipoFinanciamento() );
        programasProjectosDTO.setEspecialidade( programasProjectos.getEspecialidade() );

        return programasProjectosDTO;
    }

    @Override
    public ProgramasProjectos toEntity(ProgramasProjectosDTO programasProjectosDTO) {
        if ( programasProjectosDTO == null ) {
            return null;
        }

        ProgramasProjectos programasProjectos = new ProgramasProjectos();

        programasProjectos.setIdComuna( comunaMapper.fromId( programasProjectosDTO.getIdComunaId() ) );
        programasProjectos.setId( programasProjectosDTO.getId() );
        programasProjectos.setIdProgramasProjectos( programasProjectosDTO.getIdProgramasProjectos() );
        programasProjectos.setDtLancamento( programasProjectosDTO.getDtLancamento() );
        programasProjectos.setDtUltimaAlteracao( programasProjectosDTO.getDtUltimaAlteracao() );
        programasProjectos.setIdUsuario( programasProjectosDTO.getIdUsuario() );
        programasProjectos.setNmDesignacaoProjeto( programasProjectosDTO.getNmDesignacaoProjeto() );
        programasProjectos.setNmDescricaoProjeto( programasProjectosDTO.getNmDescricaoProjeto() );
        programasProjectos.setIdSaaAssociado( programasProjectosDTO.getIdSaaAssociado() );
        programasProjectos.setTipoFinanciamento( programasProjectosDTO.getTipoFinanciamento() );
        programasProjectos.setEspecialidade( programasProjectosDTO.getEspecialidade() );

        return programasProjectos;
    }

    private Long programasProjectosIdComunaId(ProgramasProjectos programasProjectos) {
        if ( programasProjectos == null ) {
            return null;
        }
        Comuna idComuna = programasProjectos.getIdComuna();
        if ( idComuna == null ) {
            return null;
        }
        Long id = idComuna.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
