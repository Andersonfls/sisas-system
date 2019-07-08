package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.service.dto.SistemaAguaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-08T20:24:25+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class SistemaAguaMapperImpl implements SistemaAguaMapper {

    @Override
    public List<SistemaAgua> toEntity(List<SistemaAguaDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<SistemaAgua> list = new ArrayList<SistemaAgua>( arg0.size() );
        for ( SistemaAguaDTO sistemaAguaDTO : arg0 ) {
            list.add( toEntity( sistemaAguaDTO ) );
        }

        return list;
    }

    @Override
    public List<SistemaAguaDTO> toDto(List<SistemaAgua> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<SistemaAguaDTO> list = new ArrayList<SistemaAguaDTO>( arg0.size() );
        for ( SistemaAgua sistemaAgua : arg0 ) {
            list.add( toDto( sistemaAgua ) );
        }

        return list;
    }

    @Override
    public SistemaAguaDTO toDto(SistemaAgua sistemaAgua) {
        if ( sistemaAgua == null ) {
            return null;
        }

        SistemaAguaDTO sistemaAguaDTO = new SistemaAguaDTO();

        sistemaAguaDTO.setComuna( sistemaAgua.getComuna() );
        sistemaAguaDTO.setSituacao( sistemaAgua.getSituacao() );
        sistemaAguaDTO.setIdUsuario( sistemaAgua.getIdUsuario() );
        sistemaAguaDTO.setId( sistemaAgua.getId() );
        sistemaAguaDTO.setNmInqueridor( sistemaAgua.getNmInqueridor() );
        sistemaAguaDTO.setDtLancamento( sistemaAgua.getDtLancamento() );
        sistemaAguaDTO.setDtUltimaAlteracao( sistemaAgua.getDtUltimaAlteracao() );
        sistemaAguaDTO.setNmLocalidade( sistemaAgua.getNmLocalidade() );
        sistemaAguaDTO.setQtdPopulacaoActual( sistemaAgua.getQtdPopulacaoActual() );
        sistemaAguaDTO.setQtdCasasLocalidade( sistemaAgua.getQtdCasasLocalidade() );
        sistemaAguaDTO.setNmTpComunaAldeia( sistemaAgua.getNmTpComunaAldeia() );
        sistemaAguaDTO.setNmTpArea( sistemaAgua.getNmTpArea() );
        sistemaAguaDTO.setPossuiSistemaAgua( sistemaAgua.getPossuiSistemaAgua() );
        sistemaAguaDTO.setNmSistemaAgua( sistemaAgua.getNmSistemaAgua() );
        sistemaAguaDTO.setNmFonteAgua( sistemaAgua.getNmFonteAgua() );
        sistemaAguaDTO.setLatitude( sistemaAgua.getLatitude() );
        sistemaAguaDTO.setLongitude( sistemaAgua.getLongitude() );
        sistemaAguaDTO.setAltitude( sistemaAgua.getAltitude() );
        sistemaAguaDTO.setNmTpFonte( sistemaAgua.getNmTpFonte() );
        sistemaAguaDTO.setNmFonteAguaUtilizada( sistemaAgua.getNmFonteAguaUtilizada() );
        sistemaAguaDTO.setNmTipoBomba( sistemaAgua.getNmTipoBomba() );
        sistemaAguaDTO.setQtdCasasAguaLigada( sistemaAgua.getQtdCasasAguaLigada() );
        sistemaAguaDTO.setQtdChafarisesFuncionando( sistemaAgua.getQtdChafarisesFuncionando() );
        sistemaAguaDTO.setQtdContadoresLigados( sistemaAgua.getQtdContadoresLigados() );
        sistemaAguaDTO.setQtdBebedouros( sistemaAgua.getQtdBebedouros() );
        sistemaAguaDTO.setQtdHabitantesAcessoServicoAgua( sistemaAgua.getQtdHabitantesAcessoServicoAgua() );
        sistemaAguaDTO.setAnoConstrucaoSistema( sistemaAgua.getAnoConstrucaoSistema() );
        sistemaAguaDTO.setNmTpAvariaSistema( sistemaAgua.getNmTpAvariaSistema() );
        sistemaAguaDTO.setCausaAvariaSistema( sistemaAgua.getCausaAvariaSistema() );
        sistemaAguaDTO.setStatusResolucao( sistemaAgua.getStatusResolucao() );
        sistemaAguaDTO.setTempoServicoDisponivel( sistemaAgua.getTempoServicoDisponivel() );
        sistemaAguaDTO.setQtdDiametroCondutaAdutoraAguaBruta( sistemaAgua.getQtdDiametroCondutaAdutoraAguaBruta() );
        sistemaAguaDTO.setQtdComprimentoCondutaAdutoraAguaBruta( sistemaAgua.getQtdComprimentoCondutaAdutoraAguaBruta() );
        sistemaAguaDTO.setQtdDiametroCondutaAdutoraAguaTratada( sistemaAgua.getQtdDiametroCondutaAdutoraAguaTratada() );
        sistemaAguaDTO.setQtdComprimentoCondutaAdutoraAguaTratada( sistemaAgua.getQtdComprimentoCondutaAdutoraAguaTratada() );
        sistemaAguaDTO.setDescMaterialUtilizadoCondutas( sistemaAgua.getDescMaterialUtilizadoCondutas() );
        sistemaAguaDTO.setQtdReservatoriosApoiados( sistemaAgua.getQtdReservatoriosApoiados() );
        sistemaAguaDTO.setQtdCapacidadeReservatoriosApoiados( sistemaAgua.getQtdCapacidadeReservatoriosApoiados() );
        sistemaAguaDTO.setQtdReservatoriosElevados( sistemaAgua.getQtdReservatoriosElevados() );
        sistemaAguaDTO.setQtdCapacidadeReservatoriosElevados( sistemaAgua.getQtdCapacidadeReservatoriosElevados() );
        sistemaAguaDTO.setAlturaReservatoriosElevados( sistemaAgua.getAlturaReservatoriosElevados() );
        sistemaAguaDTO.setNmTpTratamentoAgua( sistemaAgua.getNmTpTratamentoAgua() );
        sistemaAguaDTO.setNmTpTratamentoPadraoUtilizado( sistemaAgua.getNmTpTratamentoPadraoUtilizado() );
        sistemaAguaDTO.setNmTpTratamentoBasicoUtilizado( sistemaAgua.getNmTpTratamentoBasicoUtilizado() );
        sistemaAguaDTO.setExisteAvariaSistemaTratamento( sistemaAgua.getExisteAvariaSistemaTratamento() );
        sistemaAguaDTO.setExisteMotivoAusenciaTratamento( sistemaAgua.getExisteMotivoAusenciaTratamento() );
        sistemaAguaDTO.setNmEquipamentosComAvaria( sistemaAgua.getNmEquipamentosComAvaria() );
        sistemaAguaDTO.setCaudalDoSistema( sistemaAgua.getCaudalDoSistema() );
        sistemaAguaDTO.setQtdConsumoPercaptaLitrosHomemDia( sistemaAgua.getQtdConsumoPercaptaLitrosHomemDia() );
        sistemaAguaDTO.setQtdDotacaoPercapta( sistemaAgua.getQtdDotacaoPercapta() );
        sistemaAguaDTO.setQtdDiariaHorasServicoSistema( sistemaAgua.getQtdDiariaHorasServicoSistema() );
        sistemaAguaDTO.setEsquema( sistemaAgua.getEsquema() );
        sistemaAguaDTO.setNmModeloBombaManualUtilizada( sistemaAgua.getNmModeloBombaManualUtilizada() );
        sistemaAguaDTO.setNmTpBombaEnergia( sistemaAgua.getNmTpBombaEnergia() );

        return sistemaAguaDTO;
    }

    @Override
    public SistemaAgua toEntity(SistemaAguaDTO sistemaAguaDTO) {
        if ( sistemaAguaDTO == null ) {
            return null;
        }

        SistemaAgua sistemaAgua = new SistemaAgua();

        sistemaAgua.setSituacao( sistemaAguaDTO.getSituacao() );
        sistemaAgua.setIdUsuario( sistemaAguaDTO.getIdUsuario() );
        sistemaAgua.setId( sistemaAguaDTO.getId() );
        sistemaAgua.setNmInqueridor( sistemaAguaDTO.getNmInqueridor() );
        sistemaAgua.setDtLancamento( sistemaAguaDTO.getDtLancamento() );
        sistemaAgua.setDtUltimaAlteracao( sistemaAguaDTO.getDtUltimaAlteracao() );
        sistemaAgua.setNmLocalidade( sistemaAguaDTO.getNmLocalidade() );
        sistemaAgua.setQtdPopulacaoActual( sistemaAguaDTO.getQtdPopulacaoActual() );
        sistemaAgua.setQtdCasasLocalidade( sistemaAguaDTO.getQtdCasasLocalidade() );
        sistemaAgua.setNmTpComunaAldeia( sistemaAguaDTO.getNmTpComunaAldeia() );
        sistemaAgua.setNmTpArea( sistemaAguaDTO.getNmTpArea() );
        sistemaAgua.setPossuiSistemaAgua( sistemaAguaDTO.getPossuiSistemaAgua() );
        sistemaAgua.setNmSistemaAgua( sistemaAguaDTO.getNmSistemaAgua() );
        sistemaAgua.setNmFonteAgua( sistemaAguaDTO.getNmFonteAgua() );
        sistemaAgua.setLatitude( sistemaAguaDTO.getLatitude() );
        sistemaAgua.setLongitude( sistemaAguaDTO.getLongitude() );
        sistemaAgua.setAltitude( sistemaAguaDTO.getAltitude() );
        sistemaAgua.setNmTpFonte( sistemaAguaDTO.getNmTpFonte() );
        sistemaAgua.setNmFonteAguaUtilizada( sistemaAguaDTO.getNmFonteAguaUtilizada() );
        sistemaAgua.setNmTipoBomba( sistemaAguaDTO.getNmTipoBomba() );
        sistemaAgua.setQtdCasasAguaLigada( sistemaAguaDTO.getQtdCasasAguaLigada() );
        sistemaAgua.setQtdChafarisesFuncionando( sistemaAguaDTO.getQtdChafarisesFuncionando() );
        sistemaAgua.setQtdContadoresLigados( sistemaAguaDTO.getQtdContadoresLigados() );
        sistemaAgua.setQtdBebedouros( sistemaAguaDTO.getQtdBebedouros() );
        sistemaAgua.setQtdHabitantesAcessoServicoAgua( sistemaAguaDTO.getQtdHabitantesAcessoServicoAgua() );
        sistemaAgua.setAnoConstrucaoSistema( sistemaAguaDTO.getAnoConstrucaoSistema() );
        sistemaAgua.setNmTpAvariaSistema( sistemaAguaDTO.getNmTpAvariaSistema() );
        sistemaAgua.setCausaAvariaSistema( sistemaAguaDTO.getCausaAvariaSistema() );
        sistemaAgua.setStatusResolucao( sistemaAguaDTO.getStatusResolucao() );
        sistemaAgua.setTempoServicoDisponivel( sistemaAguaDTO.getTempoServicoDisponivel() );
        sistemaAgua.setQtdDiametroCondutaAdutoraAguaBruta( sistemaAguaDTO.getQtdDiametroCondutaAdutoraAguaBruta() );
        sistemaAgua.setQtdComprimentoCondutaAdutoraAguaBruta( sistemaAguaDTO.getQtdComprimentoCondutaAdutoraAguaBruta() );
        sistemaAgua.setQtdDiametroCondutaAdutoraAguaTratada( sistemaAguaDTO.getQtdDiametroCondutaAdutoraAguaTratada() );
        sistemaAgua.setQtdComprimentoCondutaAdutoraAguaTratada( sistemaAguaDTO.getQtdComprimentoCondutaAdutoraAguaTratada() );
        sistemaAgua.setDescMaterialUtilizadoCondutas( sistemaAguaDTO.getDescMaterialUtilizadoCondutas() );
        sistemaAgua.setQtdReservatoriosApoiados( sistemaAguaDTO.getQtdReservatoriosApoiados() );
        sistemaAgua.setQtdCapacidadeReservatoriosApoiados( sistemaAguaDTO.getQtdCapacidadeReservatoriosApoiados() );
        sistemaAgua.setQtdReservatoriosElevados( sistemaAguaDTO.getQtdReservatoriosElevados() );
        sistemaAgua.setQtdCapacidadeReservatoriosElevados( sistemaAguaDTO.getQtdCapacidadeReservatoriosElevados() );
        sistemaAgua.setAlturaReservatoriosElevados( sistemaAguaDTO.getAlturaReservatoriosElevados() );
        sistemaAgua.setNmTpTratamentoAgua( sistemaAguaDTO.getNmTpTratamentoAgua() );
        sistemaAgua.setNmTpTratamentoPadraoUtilizado( sistemaAguaDTO.getNmTpTratamentoPadraoUtilizado() );
        sistemaAgua.setNmTpTratamentoBasicoUtilizado( sistemaAguaDTO.getNmTpTratamentoBasicoUtilizado() );
        sistemaAgua.setExisteAvariaSistemaTratamento( sistemaAguaDTO.getExisteAvariaSistemaTratamento() );
        sistemaAgua.setExisteMotivoAusenciaTratamento( sistemaAguaDTO.getExisteMotivoAusenciaTratamento() );
        sistemaAgua.setNmEquipamentosComAvaria( sistemaAguaDTO.getNmEquipamentosComAvaria() );
        sistemaAgua.setCaudalDoSistema( sistemaAguaDTO.getCaudalDoSistema() );
        sistemaAgua.setQtdConsumoPercaptaLitrosHomemDia( sistemaAguaDTO.getQtdConsumoPercaptaLitrosHomemDia() );
        sistemaAgua.setQtdDotacaoPercapta( sistemaAguaDTO.getQtdDotacaoPercapta() );
        sistemaAgua.setQtdDiariaHorasServicoSistema( sistemaAguaDTO.getQtdDiariaHorasServicoSistema() );
        sistemaAgua.setEsquema( sistemaAguaDTO.getEsquema() );
        sistemaAgua.setNmModeloBombaManualUtilizada( sistemaAguaDTO.getNmModeloBombaManualUtilizada() );
        sistemaAgua.setNmTpBombaEnergia( sistemaAguaDTO.getNmTpBombaEnergia() );
        sistemaAgua.setComuna( sistemaAguaDTO.getComuna() );

        return sistemaAgua;
    }
}
