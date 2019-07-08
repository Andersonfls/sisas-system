package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Contrato;
import com.minea.sisas.domain.Execucao;
import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.domain.Situacao;
import com.minea.sisas.service.dto.ExecucaoDTO;
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
public class ExecucaoMapperImpl implements ExecucaoMapper {

    @Override
    public List<Execucao> toEntity(List<ExecucaoDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Execucao> list = new ArrayList<Execucao>( arg0.size() );
        for ( ExecucaoDTO execucaoDTO : arg0 ) {
            list.add( toEntity( execucaoDTO ) );
        }

        return list;
    }

    @Override
    public List<ExecucaoDTO> toDto(List<Execucao> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ExecucaoDTO> list = new ArrayList<ExecucaoDTO>( arg0.size() );
        for ( Execucao execucao : arg0 ) {
            list.add( toDto( execucao ) );
        }

        return list;
    }

    @Override
    public ExecucaoDTO toDto(Execucao execucao) {
        if ( execucao == null ) {
            return null;
        }

        ExecucaoDTO execucaoDTO = new ExecucaoDTO();

        Long id = execucaoIdSituacaoId( execucao );
        if ( id != null ) {
            execucaoDTO.setIdSituacaoId( id );
        }
        Long id1 = execucaoIdProgramasProjectosId( execucao );
        if ( id1 != null ) {
            execucaoDTO.setIdProgramasProjectosId( id1 );
        }
        Long id2 = execucaoIdSistemaAguaId( execucao );
        if ( id2 != null ) {
            execucaoDTO.setIdSistemaAguaId( id2 );
        }
        Long id3 = execucaoIdContratoId( execucao );
        if ( id3 != null ) {
            execucaoDTO.setIdContratoId( id3 );
        }
        execucaoDTO.setId( execucao.getId() );
        execucaoDTO.setTipoEmpreitada( execucao.getTipoEmpreitada() );
        execucaoDTO.setDtLancamento( execucao.getDtLancamento() );
        execucaoDTO.setDtPeridoReferencia( execucao.getDtPeridoReferencia() );
        execucaoDTO.setDtFimReferencia( execucao.getDtFimReferencia() );
        execucaoDTO.setValorFacturadoPeriodo( execucao.getValorFacturadoPeriodo() );
        execucaoDTO.setDtFactura( execucao.getDtFactura() );
        execucaoDTO.setNumFactura( execucao.getNumFactura() );
        execucaoDTO.setTxCambio( execucao.getTxCambio() );
        execucaoDTO.setConstrangimento( execucao.getConstrangimento() );
        execucaoDTO.setValorPagoPeriodo( execucao.getValorPagoPeriodo() );

        return execucaoDTO;
    }

    @Override
    public Execucao toEntity(ExecucaoDTO execucaoDTO) {
        if ( execucaoDTO == null ) {
            return null;
        }

        Execucao execucao = new Execucao();

        execucao.setId( execucaoDTO.getIdSituacaoId() );
        execucao.setTipoEmpreitada( execucaoDTO.getTipoEmpreitada() );
        execucao.setDtLancamento( execucaoDTO.getDtLancamento() );
        execucao.setDtPeridoReferencia( execucaoDTO.getDtPeridoReferencia() );
        execucao.setDtFimReferencia( execucaoDTO.getDtFimReferencia() );
        execucao.setValorFacturadoPeriodo( execucaoDTO.getValorFacturadoPeriodo() );
        execucao.setDtFactura( execucaoDTO.getDtFactura() );
        execucao.setNumFactura( execucaoDTO.getNumFactura() );
        execucao.setTxCambio( execucaoDTO.getTxCambio() );
        execucao.setConstrangimento( execucaoDTO.getConstrangimento() );
        execucao.setValorPagoPeriodo( execucaoDTO.getValorPagoPeriodo() );

        return execucao;
    }

    private Long execucaoIdSituacaoId(Execucao execucao) {
        if ( execucao == null ) {
            return null;
        }
        Situacao idSituacao = execucao.getIdSituacao();
        if ( idSituacao == null ) {
            return null;
        }
        Long id = idSituacao.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long execucaoIdProgramasProjectosId(Execucao execucao) {
        if ( execucao == null ) {
            return null;
        }
        ProgramasProjectos idProgramasProjectos = execucao.getIdProgramasProjectos();
        if ( idProgramasProjectos == null ) {
            return null;
        }
        Long id = idProgramasProjectos.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long execucaoIdSistemaAguaId(Execucao execucao) {
        if ( execucao == null ) {
            return null;
        }
        SistemaAgua idSistemaAgua = execucao.getIdSistemaAgua();
        if ( idSistemaAgua == null ) {
            return null;
        }
        Long id = idSistemaAgua.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long execucaoIdContratoId(Execucao execucao) {
        if ( execucao == null ) {
            return null;
        }
        Contrato idContrato = execucao.getIdContrato();
        if ( idContrato == null ) {
            return null;
        }
        Long id = idContrato.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
