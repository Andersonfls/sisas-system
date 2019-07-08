package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Adjudicacao;
import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.service.dto.AdjudicacaoDTO;
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
public class AdjudicacaoMapperImpl implements AdjudicacaoMapper {

    @Override
    public List<Adjudicacao> toEntity(List<AdjudicacaoDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Adjudicacao> list = new ArrayList<Adjudicacao>( arg0.size() );
        for ( AdjudicacaoDTO adjudicacaoDTO : arg0 ) {
            list.add( toEntity( adjudicacaoDTO ) );
        }

        return list;
    }

    @Override
    public List<AdjudicacaoDTO> toDto(List<Adjudicacao> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<AdjudicacaoDTO> list = new ArrayList<AdjudicacaoDTO>( arg0.size() );
        for ( Adjudicacao adjudicacao : arg0 ) {
            list.add( toDto( adjudicacao ) );
        }

        return list;
    }

    @Override
    public AdjudicacaoDTO toDto(Adjudicacao adjudicacao) {
        if ( adjudicacao == null ) {
            return null;
        }

        AdjudicacaoDTO adjudicacaoDTO = new AdjudicacaoDTO();

        Long id = adjudicacaoIdProgramasProjectosId( adjudicacao );
        if ( id != null ) {
            adjudicacaoDTO.setIdProgramasProjectosId( id );
        }
        Long id1 = adjudicacaoIdSistemaAguaId( adjudicacao );
        if ( id1 != null ) {
            adjudicacaoDTO.setIdSistemaAguaId( id1 );
        }
        adjudicacaoDTO.setId( adjudicacao.getId() );
        adjudicacaoDTO.setTipoConcurso( adjudicacao.getTipoConcurso() );
        adjudicacaoDTO.setDtLancamento( adjudicacao.getDtLancamento() );
        adjudicacaoDTO.setDtComunicaoAdjudicacao( adjudicacao.getDtComunicaoAdjudicacao() );
        adjudicacaoDTO.setDtPrestacaoGarantBoaExec( adjudicacao.getDtPrestacaoGarantBoaExec() );
        adjudicacaoDTO.setDtSubmissaoMinutContrato( adjudicacao.getDtSubmissaoMinutContrato() );

        return adjudicacaoDTO;
    }

    @Override
    public Adjudicacao toEntity(AdjudicacaoDTO adjudicacaoDTO) {
        if ( adjudicacaoDTO == null ) {
            return null;
        }

        Adjudicacao adjudicacao = new Adjudicacao();

        adjudicacao.setId( adjudicacaoDTO.getIdProgramasProjectosId() );
        adjudicacao.setTipoConcurso( adjudicacaoDTO.getTipoConcurso() );
        adjudicacao.setDtLancamento( adjudicacaoDTO.getDtLancamento() );
        adjudicacao.setDtComunicaoAdjudicacao( adjudicacaoDTO.getDtComunicaoAdjudicacao() );
        adjudicacao.setDtPrestacaoGarantBoaExec( adjudicacaoDTO.getDtPrestacaoGarantBoaExec() );
        adjudicacao.setDtSubmissaoMinutContrato( adjudicacaoDTO.getDtSubmissaoMinutContrato() );

        return adjudicacao;
    }

    private Long adjudicacaoIdProgramasProjectosId(Adjudicacao adjudicacao) {
        if ( adjudicacao == null ) {
            return null;
        }
        ProgramasProjectos idProgramasProjectos = adjudicacao.getIdProgramasProjectos();
        if ( idProgramasProjectos == null ) {
            return null;
        }
        Long id = idProgramasProjectos.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long adjudicacaoIdSistemaAguaId(Adjudicacao adjudicacao) {
        if ( adjudicacao == null ) {
            return null;
        }
        SistemaAgua idSistemaAgua = adjudicacao.getIdSistemaAgua();
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
