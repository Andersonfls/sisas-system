package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.Contrato;
import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.service.dto.ContratoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-07T15:41:47+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class ContratoMapperImpl implements ContratoMapper {

    @Autowired
    private ProgramasProjectosMapper programasProjectosMapper;
    @Autowired
    private SistemaAguaMapper sistemaAguaMapper;

    @Override
    public List<Contrato> toEntity(List<ContratoDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Contrato> list = new ArrayList<Contrato>( arg0.size() );
        for ( ContratoDTO contratoDTO : arg0 ) {
            list.add( toEntity( contratoDTO ) );
        }

        return list;
    }

    @Override
    public List<ContratoDTO> toDto(List<Contrato> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ContratoDTO> list = new ArrayList<ContratoDTO>( arg0.size() );
        for ( Contrato contrato : arg0 ) {
            list.add( toDto( contrato ) );
        }

        return list;
    }

    @Override
    public ContratoDTO toDto(Contrato contrato) {
        if ( contrato == null ) {
            return null;
        }

        ContratoDTO contratoDTO = new ContratoDTO();

        Long id = contratoIdProgramasProjectosId( contrato );
        if ( id != null ) {
            contratoDTO.setIdProgramasProjectosId( id );
        }
        Long id1 = contratoIdSistemaAguaId( contrato );
        if ( id1 != null ) {
            contratoDTO.setIdSistemaAguaId( id1 );
        }
        contratoDTO.setId( contrato.getId() );
        contratoDTO.setIdContrato( contrato.getIdContrato() );
        contratoDTO.setTipoEmpreitada( contrato.getTipoEmpreitada() );
        contratoDTO.setDtLancamento( contrato.getDtLancamento() );
        contratoDTO.setNmEmpresaAdjudicitaria( contrato.getNmEmpresaAdjudicitaria() );
        contratoDTO.setValorContrato( contrato.getValorContrato() );
        contratoDTO.setDtAssinatura( contrato.getDtAssinatura() );
        contratoDTO.setDtFinalizacaoProcessoHomologAprov( contrato.getDtFinalizacaoProcessoHomologAprov() );
        contratoDTO.setTipoMoeda( contrato.getTipoMoeda() );
        contratoDTO.setValorAdiantamento( contrato.getValorAdiantamento() );
        contratoDTO.setDtAdiantamento( contrato.getDtAdiantamento() );
        contratoDTO.setDtInicio( contrato.getDtInicio() );
        contratoDTO.setPrazoExecucao( contrato.getPrazoExecucao() );
        contratoDTO.setDtRecepcaoProvisoria( contrato.getDtRecepcaoProvisoria() );
        contratoDTO.setDtRecepcaoDefinitiva( contrato.getDtRecepcaoDefinitiva() );
        contratoDTO.setDtRecepcaoComicionamento( contrato.getDtRecepcaoComicionamento() );
        contratoDTO.setNmResposavelAntProjeto( contrato.getNmResposavelAntProjeto() );
        contratoDTO.setNmResposavelProjeto( contrato.getNmResposavelProjeto() );

        return contratoDTO;
    }

    @Override
    public Contrato toEntity(ContratoDTO contratoDTO) {
        if ( contratoDTO == null ) {
            return null;
        }

        Contrato contrato = new Contrato();

        contrato.setIdSistemaAgua( sistemaAguaMapper.fromId( contratoDTO.getIdSistemaAguaId() ) );
        contrato.setIdProgramasProjectos( programasProjectosMapper.fromId( contratoDTO.getIdProgramasProjectosId() ) );
        contrato.setId( contratoDTO.getId() );
        contrato.setIdContrato( contratoDTO.getIdContrato() );
        contrato.setTipoEmpreitada( contratoDTO.getTipoEmpreitada() );
        contrato.setDtLancamento( contratoDTO.getDtLancamento() );
        contrato.setNmEmpresaAdjudicitaria( contratoDTO.getNmEmpresaAdjudicitaria() );
        contrato.setValorContrato( contratoDTO.getValorContrato() );
        contrato.setDtAssinatura( contratoDTO.getDtAssinatura() );
        contrato.setDtFinalizacaoProcessoHomologAprov( contratoDTO.getDtFinalizacaoProcessoHomologAprov() );
        contrato.setTipoMoeda( contratoDTO.getTipoMoeda() );
        contrato.setValorAdiantamento( contratoDTO.getValorAdiantamento() );
        contrato.setDtAdiantamento( contratoDTO.getDtAdiantamento() );
        contrato.setDtInicio( contratoDTO.getDtInicio() );
        contrato.setPrazoExecucao( contratoDTO.getPrazoExecucao() );
        contrato.setDtRecepcaoProvisoria( contratoDTO.getDtRecepcaoProvisoria() );
        contrato.setDtRecepcaoDefinitiva( contratoDTO.getDtRecepcaoDefinitiva() );
        contrato.setDtRecepcaoComicionamento( contratoDTO.getDtRecepcaoComicionamento() );
        contrato.setNmResposavelAntProjeto( contratoDTO.getNmResposavelAntProjeto() );
        contrato.setNmResposavelProjeto( contratoDTO.getNmResposavelProjeto() );

        return contrato;
    }

    private Long contratoIdProgramasProjectosId(Contrato contrato) {
        if ( contrato == null ) {
            return null;
        }
        ProgramasProjectos idProgramasProjectos = contrato.getIdProgramasProjectos();
        if ( idProgramasProjectos == null ) {
            return null;
        }
        Long id = idProgramasProjectos.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long contratoIdSistemaAguaId(Contrato contrato) {
        if ( contrato == null ) {
            return null;
        }
        SistemaAgua idSistemaAgua = contrato.getIdSistemaAgua();
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
