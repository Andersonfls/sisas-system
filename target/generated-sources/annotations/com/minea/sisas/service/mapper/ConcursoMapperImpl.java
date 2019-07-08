package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Concurso;
import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.service.dto.ConcursoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-07T15:41:48+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class ConcursoMapperImpl implements ConcursoMapper {

    @Override
    public List<Concurso> toEntity(List<ConcursoDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Concurso> list = new ArrayList<Concurso>( arg0.size() );
        for ( ConcursoDTO concursoDTO : arg0 ) {
            list.add( toEntity( concursoDTO ) );
        }

        return list;
    }

    @Override
    public List<ConcursoDTO> toDto(List<Concurso> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ConcursoDTO> list = new ArrayList<ConcursoDTO>( arg0.size() );
        for ( Concurso concurso : arg0 ) {
            list.add( toDto( concurso ) );
        }

        return list;
    }

    @Override
    public ConcursoDTO toDto(Concurso concurso) {
        if ( concurso == null ) {
            return null;
        }

        ConcursoDTO concursoDTO = new ConcursoDTO();

        Long id = concursoIdProgramasProjectosId( concurso );
        if ( id != null ) {
            concursoDTO.setIdProgramasProjectosId( id );
        }
        Long id1 = concursoIdSistemaAguaId( concurso );
        if ( id1 != null ) {
            concursoDTO.setIdSistemaAguaId( id1 );
        }
        concursoDTO.setId( concurso.getId() );
        concursoDTO.setTipoConcurso( concurso.getTipoConcurso() );
        concursoDTO.setDtLancamento( concurso.getDtLancamento() );
        concursoDTO.setDtUltimaAlteracao( concurso.getDtUltimaAlteracao() );
        concursoDTO.setDtEntregaProposta( concurso.getDtEntregaProposta() );
        concursoDTO.setDtAberturaProposta( concurso.getDtAberturaProposta() );
        concursoDTO.setDtConclusaoAvaliacaoRelPrel( concurso.getDtConclusaoAvaliacaoRelPrel() );
        concursoDTO.setDtNegociacao( concurso.getDtNegociacao() );
        concursoDTO.setDtAprovRelAvalFinal( concurso.getDtAprovRelAvalFinal() );

        return concursoDTO;
    }

    @Override
    public Concurso toEntity(ConcursoDTO concursoDTO) {
        if ( concursoDTO == null ) {
            return null;
        }

        Concurso concurso = new Concurso();

        concurso.setId( concursoDTO.getIdProgramasProjectosId() );
        concurso.setTipoConcurso( concursoDTO.getTipoConcurso() );
        concurso.setDtLancamento( concursoDTO.getDtLancamento() );
        concurso.setDtUltimaAlteracao( concursoDTO.getDtUltimaAlteracao() );
        concurso.setDtEntregaProposta( concursoDTO.getDtEntregaProposta() );
        concurso.setDtAberturaProposta( concursoDTO.getDtAberturaProposta() );
        concurso.setDtConclusaoAvaliacaoRelPrel( concursoDTO.getDtConclusaoAvaliacaoRelPrel() );
        concurso.setDtNegociacao( concursoDTO.getDtNegociacao() );
        concurso.setDtAprovRelAvalFinal( concursoDTO.getDtAprovRelAvalFinal() );

        return concurso;
    }

    private Long concursoIdProgramasProjectosId(Concurso concurso) {
        if ( concurso == null ) {
            return null;
        }
        ProgramasProjectos idProgramasProjectos = concurso.getIdProgramasProjectos();
        if ( idProgramasProjectos == null ) {
            return null;
        }
        Long id = idProgramasProjectos.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long concursoIdSistemaAguaId(Concurso concurso) {
        if ( concurso == null ) {
            return null;
        }
        SistemaAgua idSistemaAgua = concurso.getIdSistemaAgua();
        if ( idSistemaAgua == null ) {
            return null;
        }
        Long id = idSistemaAgua.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
