package com.minea.sisas.service.mapper;

import com.minea.sisas.domain.IndicadorProducao;
import com.minea.sisas.service.dto.IndicadorProducaoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-08T00:09:11+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class IndicadorProducaoMapperImpl implements IndicadorProducaoMapper {

    @Override
    public List<IndicadorProducao> toEntity(List<IndicadorProducaoDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<IndicadorProducao> list = new ArrayList<IndicadorProducao>( arg0.size() );
        for ( IndicadorProducaoDTO indicadorProducaoDTO : arg0 ) {
            list.add( toEntity( indicadorProducaoDTO ) );
        }

        return list;
    }

    @Override
    public List<IndicadorProducaoDTO> toDto(List<IndicadorProducao> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<IndicadorProducaoDTO> list = new ArrayList<IndicadorProducaoDTO>( arg0.size() );
        for ( IndicadorProducao indicadorProducao : arg0 ) {
            list.add( toDto( indicadorProducao ) );
        }

        return list;
    }

    @Override
    public IndicadorProducaoDTO toDto(IndicadorProducao indicadorProducao) {
        if ( indicadorProducao == null ) {
            return null;
        }

        IndicadorProducaoDTO indicadorProducaoDTO = new IndicadorProducaoDTO();

        indicadorProducaoDTO.setIdSituacaoId( indicadorProducao.getIdSituacao() );
        indicadorProducaoDTO.setIdSistemaAguaId( indicadorProducao.getIdSistemaAgua() );
        indicadorProducaoDTO.setIdComunaId( indicadorProducao.getIdComuna() );
        indicadorProducaoDTO.setId( indicadorProducao.getId() );
        indicadorProducaoDTO.setIdUsuario( indicadorProducao.getIdUsuario() );
        indicadorProducaoDTO.setDtLancamento( indicadorProducao.getDtLancamento() );
        indicadorProducaoDTO.setDtUltimaAlteracao( indicadorProducao.getDtUltimaAlteracao() );
        indicadorProducaoDTO.setQtdPopulacaoCobertaInfraestrutura( indicadorProducao.getQtdPopulacaoCobertaInfraestrutura() );
        indicadorProducaoDTO.setQtdFontanariosChafarisesOperacionais( indicadorProducao.getQtdFontanariosChafarisesOperacionais() );
        indicadorProducaoDTO.setQtdMediaHorasDistribuicaoDiaria( indicadorProducao.getQtdMediaHorasDistribuicaoDiaria() );
        indicadorProducaoDTO.setQtdMediaHorasParagem( indicadorProducao.getQtdMediaHorasParagem() );
        indicadorProducaoDTO.setQtdMediaHorasInterrupcaoFaltaEnergia( indicadorProducao.getQtdMediaHorasInterrupcaoFaltaEnergia() );
        indicadorProducaoDTO.setQtdVolumeAguaCaptada( indicadorProducao.getQtdVolumeAguaCaptada() );
        indicadorProducaoDTO.setQtdVolumeAguaTratada( indicadorProducao.getQtdVolumeAguaTratada() );
        indicadorProducaoDTO.setQtdVolumeAguaDistribuida( indicadorProducao.getQtdVolumeAguaDistribuida() );
        indicadorProducaoDTO.setQtdConsumoEnergia( indicadorProducao.getQtdConsumoEnergia() );
        indicadorProducaoDTO.setQtdConsumoCombustivel( indicadorProducao.getQtdConsumoCombustivel() );
        indicadorProducaoDTO.setQtdConsumoHipocloritroCalcio( indicadorProducao.getQtdConsumoHipocloritroCalcio() );
        indicadorProducaoDTO.setQtdConsumoSulfatoAluminio( indicadorProducao.getQtdConsumoSulfatoAluminio() );
        indicadorProducaoDTO.setQtdConsumoHidroxidoCalcio( indicadorProducao.getQtdConsumoHidroxidoCalcio() );
        indicadorProducaoDTO.setQtdReparoCaptacaoEtas( indicadorProducao.getQtdReparoCaptacaoEtas() );
        indicadorProducaoDTO.setQtdReparoAdutoras( indicadorProducao.getQtdReparoAdutoras() );
        indicadorProducaoDTO.setQtdReparoRedeDistribuicao( indicadorProducao.getQtdReparoRedeDistribuicao() );
        indicadorProducaoDTO.setQtdReparoRamais( indicadorProducao.getQtdReparoRamais() );
        indicadorProducaoDTO.setQtdManutencaoCurativa( indicadorProducao.getQtdManutencaoCurativa() );
        indicadorProducaoDTO.setQtdManutencaoPreventiva( indicadorProducao.getQtdManutencaoPreventiva() );
        indicadorProducaoDTO.setQtdManutencaoVerificadoSolicitado( indicadorProducao.getQtdManutencaoVerificadoSolicitado() );
        indicadorProducaoDTO.setQtdReservatorioLavado( indicadorProducao.getQtdReservatorioLavado() );
        indicadorProducaoDTO.setQtdFuncionarios( indicadorProducao.getQtdFuncionarios() );
        indicadorProducaoDTO.setQtdFuncionariosEfectivos( indicadorProducao.getQtdFuncionariosEfectivos() );
        indicadorProducaoDTO.setQtdFuncionariosContratados( indicadorProducao.getQtdFuncionariosContratados() );
        indicadorProducaoDTO.setQtdFuncionariosOutrasEntidades( indicadorProducao.getQtdFuncionariosOutrasEntidades() );
        indicadorProducaoDTO.setQtdNovasLigacoesNovosContratos( indicadorProducao.getQtdNovasLigacoesNovosContratos() );
        indicadorProducaoDTO.setQtdNovasLigacoesDomesticasNovosContratos( indicadorProducao.getQtdNovasLigacoesDomesticasNovosContratos() );
        indicadorProducaoDTO.setQtdLigacoesIlegaisRegularizadas( indicadorProducao.getQtdLigacoesIlegaisRegularizadas() );
        indicadorProducaoDTO.setQtdLigacoesFechadas( indicadorProducao.getQtdLigacoesFechadas() );
        indicadorProducaoDTO.setQtdCortes( indicadorProducao.getQtdCortes() );
        indicadorProducaoDTO.setQtdReligacoes( indicadorProducao.getQtdReligacoes() );
        indicadorProducaoDTO.setQtdLigacoesActivas( indicadorProducao.getQtdLigacoesActivas() );
        indicadorProducaoDTO.setQtdLigacoesDomesticasActivas( indicadorProducao.getQtdLigacoesDomesticasActivas() );
        indicadorProducaoDTO.setQtdLigacoesFacturadasBaseLeiturasReais( indicadorProducao.getQtdLigacoesFacturadasBaseLeiturasReais() );
        indicadorProducaoDTO.setQtdLigacoesFacturadasBaseEstimativasAvenca( indicadorProducao.getQtdLigacoesFacturadasBaseEstimativasAvenca() );
        indicadorProducaoDTO.setQtdVolumeAguaFacturada( indicadorProducao.getQtdVolumeAguaFacturada() );
        indicadorProducaoDTO.setQtdVolumeTotalFacturadaLigacoesDomesticas( indicadorProducao.getQtdVolumeTotalFacturadaLigacoesDomesticas() );
        indicadorProducaoDTO.setQtdVolumeFacturadoBaseLeituraReais( indicadorProducao.getQtdVolumeFacturadoBaseLeituraReais() );
        indicadorProducaoDTO.setVlrTotalFacturado( indicadorProducao.getVlrTotalFacturado() );
        indicadorProducaoDTO.setVlrFacturasCanceladasNotasCreditos( indicadorProducao.getVlrFacturasCanceladasNotasCreditos() );
        indicadorProducaoDTO.setVlrRealFacturado( indicadorProducao.getVlrRealFacturado() );
        indicadorProducaoDTO.setVlrTotalCobrado( indicadorProducao.getVlrTotalCobrado() );
        indicadorProducaoDTO.setQtdReclamacoes( indicadorProducao.getQtdReclamacoes() );
        indicadorProducaoDTO.setQtdReclamacoesRespondidasMenorIgualCincoDias( indicadorProducao.getQtdReclamacoesRespondidasMenorIgualCincoDias() );
        indicadorProducaoDTO.setQtdReclamacoesRespondidasMaisCincoMenosVinteDias( indicadorProducao.getQtdReclamacoesRespondidasMaisCincoMenosVinteDias() );
        indicadorProducaoDTO.setQtdReclamacoesRespondidasMaiorIgualVinteDias( indicadorProducao.getQtdReclamacoesRespondidasMaiorIgualVinteDias() );
        indicadorProducaoDTO.setVlrCustoPessoal( indicadorProducao.getVlrCustoPessoal() );
        indicadorProducaoDTO.setVlrCustoFse( indicadorProducao.getVlrCustoFse() );
        indicadorProducaoDTO.setVlrCustoEnergia( indicadorProducao.getVlrCustoEnergia() );
        indicadorProducaoDTO.setVlrCustoManutencao( indicadorProducao.getVlrCustoManutencao() );
        indicadorProducaoDTO.setVlrCustoReagentes( indicadorProducao.getVlrCustoReagentes() );
        indicadorProducaoDTO.setVlrCustoDestinoFinalLamas( indicadorProducao.getVlrCustoDestinoFinalLamas() );
        indicadorProducaoDTO.setVlrCustoOperacionaisOpex( indicadorProducao.getVlrCustoOperacionaisOpex() );
        indicadorProducaoDTO.setVlrCustoAmortizaAnualInvestOpCapex( indicadorProducao.getVlrCustoAmortizaAnualInvestOpCapex() );
        indicadorProducaoDTO.setVlrCustoTotaisCapexOpex( indicadorProducao.getVlrCustoTotaisCapexOpex() );
        indicadorProducaoDTO.setQtdCaptacoes( indicadorProducao.getQtdCaptacoes() );
        indicadorProducaoDTO.setQtdEtas( indicadorProducao.getQtdEtas() );
        indicadorProducaoDTO.setQtdReservatorios( indicadorProducao.getQtdReservatorios() );
        indicadorProducaoDTO.setQtdEstacoesElevatorias( indicadorProducao.getQtdEstacoesElevatorias() );
        indicadorProducaoDTO.setQtdComprimentoAdutoras( indicadorProducao.getQtdComprimentoAdutoras() );
        indicadorProducaoDTO.setQtdComprimentoRedes( indicadorProducao.getQtdComprimentoRedes() );
        indicadorProducaoDTO.setQtdComprimentoRamais( indicadorProducao.getQtdComprimentoRamais() );
        indicadorProducaoDTO.setQtdAcoesFormacaoMoPlaneadas( indicadorProducao.getQtdAcoesFormacaoMoPlaneadas() );
        indicadorProducaoDTO.setQtdAcoesFormacaoMmsPlaneadas( indicadorProducao.getQtdAcoesFormacaoMmsPlaneadas() );
        indicadorProducaoDTO.setQtdAcoesFormacaoCmpPlaneadas( indicadorProducao.getQtdAcoesFormacaoCmpPlaneadas() );
        indicadorProducaoDTO.setQtdAcoesFormacaoSoftwareFornecidosPlanejadas( indicadorProducao.getQtdAcoesFormacaoSoftwareFornecidosPlanejadas() );
        indicadorProducaoDTO.setQtdAcoesFormacaoMoRealizadas( indicadorProducao.getQtdAcoesFormacaoMoRealizadas() );
        indicadorProducaoDTO.setQtdAcoesFormacaoMmsRealizadas( indicadorProducao.getQtdAcoesFormacaoMmsRealizadas() );
        indicadorProducaoDTO.setQtdAcoesFormacaoCmpRealizadas( indicadorProducao.getQtdAcoesFormacaoCmpRealizadas() );
        indicadorProducaoDTO.setQtdAcoesFormacaoSoftwareFornecidosRealizadas( indicadorProducao.getQtdAcoesFormacaoSoftwareFornecidosRealizadas() );
        indicadorProducaoDTO.setQtdAcoesFormacaoRealizadas( indicadorProducao.getQtdAcoesFormacaoRealizadas() );
        indicadorProducaoDTO.setQtdManuaisMoPrevistos( indicadorProducao.getQtdManuaisMoPrevistos() );
        indicadorProducaoDTO.setQtdManuaisMmsPrevistos( indicadorProducao.getQtdManuaisMmsPrevistos() );
        indicadorProducaoDTO.setQtdManuaisCmpPrevistos( indicadorProducao.getQtdManuaisCmpPrevistos() );
        indicadorProducaoDTO.setQtdManuaisPrevistos( indicadorProducao.getQtdManuaisPrevistos() );
        indicadorProducaoDTO.setQtdAcoesManuaisMoRealizadas( indicadorProducao.getQtdAcoesManuaisMoRealizadas() );
        indicadorProducaoDTO.setQtdManuaisMmsRealizadas( indicadorProducao.getQtdManuaisMmsRealizadas() );
        indicadorProducaoDTO.setQtdManuaisCmpRealizadas( indicadorProducao.getQtdManuaisCmpRealizadas() );
        indicadorProducaoDTO.setQtdManuaisRealizados( indicadorProducao.getQtdManuaisRealizados() );

        return indicadorProducaoDTO;
    }

    @Override
    public IndicadorProducao toEntity(IndicadorProducaoDTO indicadorProducaoDTO) {
        if ( indicadorProducaoDTO == null ) {
            return null;
        }

        IndicadorProducao indicadorProducao = new IndicadorProducao();

        indicadorProducao.setIdSistemaAgua( indicadorProducaoDTO.getIdSistemaAguaId() );
        indicadorProducao.setIdSituacao( indicadorProducaoDTO.getIdSituacaoId() );
        indicadorProducao.setIdComuna( indicadorProducaoDTO.getIdComunaId() );
        indicadorProducao.setId( indicadorProducaoDTO.getId() );
        indicadorProducao.setIdUsuario( indicadorProducaoDTO.getIdUsuario() );
        indicadorProducao.setDtLancamento( indicadorProducaoDTO.getDtLancamento() );
        indicadorProducao.setDtUltimaAlteracao( indicadorProducaoDTO.getDtUltimaAlteracao() );
        indicadorProducao.setQtdPopulacaoCobertaInfraestrutura( indicadorProducaoDTO.getQtdPopulacaoCobertaInfraestrutura() );
        indicadorProducao.setQtdFontanariosChafarisesOperacionais( indicadorProducaoDTO.getQtdFontanariosChafarisesOperacionais() );
        indicadorProducao.setQtdMediaHorasDistribuicaoDiaria( indicadorProducaoDTO.getQtdMediaHorasDistribuicaoDiaria() );
        indicadorProducao.setQtdMediaHorasParagem( indicadorProducaoDTO.getQtdMediaHorasParagem() );
        indicadorProducao.setQtdMediaHorasInterrupcaoFaltaEnergia( indicadorProducaoDTO.getQtdMediaHorasInterrupcaoFaltaEnergia() );
        indicadorProducao.setQtdVolumeAguaCaptada( indicadorProducaoDTO.getQtdVolumeAguaCaptada() );
        indicadorProducao.setQtdVolumeAguaTratada( indicadorProducaoDTO.getQtdVolumeAguaTratada() );
        indicadorProducao.setQtdVolumeAguaDistribuida( indicadorProducaoDTO.getQtdVolumeAguaDistribuida() );
        indicadorProducao.setQtdConsumoEnergia( indicadorProducaoDTO.getQtdConsumoEnergia() );
        indicadorProducao.setQtdConsumoCombustivel( indicadorProducaoDTO.getQtdConsumoCombustivel() );
        indicadorProducao.setQtdConsumoHipocloritroCalcio( indicadorProducaoDTO.getQtdConsumoHipocloritroCalcio() );
        indicadorProducao.setQtdConsumoSulfatoAluminio( indicadorProducaoDTO.getQtdConsumoSulfatoAluminio() );
        indicadorProducao.setQtdConsumoHidroxidoCalcio( indicadorProducaoDTO.getQtdConsumoHidroxidoCalcio() );
        indicadorProducao.setQtdReparoCaptacaoEtas( indicadorProducaoDTO.getQtdReparoCaptacaoEtas() );
        indicadorProducao.setQtdReparoAdutoras( indicadorProducaoDTO.getQtdReparoAdutoras() );
        indicadorProducao.setQtdReparoRedeDistribuicao( indicadorProducaoDTO.getQtdReparoRedeDistribuicao() );
        indicadorProducao.setQtdReparoRamais( indicadorProducaoDTO.getQtdReparoRamais() );
        indicadorProducao.setQtdManutencaoCurativa( indicadorProducaoDTO.getQtdManutencaoCurativa() );
        indicadorProducao.setQtdManutencaoPreventiva( indicadorProducaoDTO.getQtdManutencaoPreventiva() );
        indicadorProducao.setQtdManutencaoVerificadoSolicitado( indicadorProducaoDTO.getQtdManutencaoVerificadoSolicitado() );
        indicadorProducao.setQtdReservatorioLavado( indicadorProducaoDTO.getQtdReservatorioLavado() );
        indicadorProducao.setQtdFuncionarios( indicadorProducaoDTO.getQtdFuncionarios() );
        indicadorProducao.setQtdFuncionariosEfectivos( indicadorProducaoDTO.getQtdFuncionariosEfectivos() );
        indicadorProducao.setQtdFuncionariosContratados( indicadorProducaoDTO.getQtdFuncionariosContratados() );
        indicadorProducao.setQtdFuncionariosOutrasEntidades( indicadorProducaoDTO.getQtdFuncionariosOutrasEntidades() );
        indicadorProducao.setQtdNovasLigacoesNovosContratos( indicadorProducaoDTO.getQtdNovasLigacoesNovosContratos() );
        indicadorProducao.setQtdNovasLigacoesDomesticasNovosContratos( indicadorProducaoDTO.getQtdNovasLigacoesDomesticasNovosContratos() );
        indicadorProducao.setQtdLigacoesIlegaisRegularizadas( indicadorProducaoDTO.getQtdLigacoesIlegaisRegularizadas() );
        indicadorProducao.setQtdLigacoesFechadas( indicadorProducaoDTO.getQtdLigacoesFechadas() );
        indicadorProducao.setQtdCortes( indicadorProducaoDTO.getQtdCortes() );
        indicadorProducao.setQtdReligacoes( indicadorProducaoDTO.getQtdReligacoes() );
        indicadorProducao.setQtdLigacoesActivas( indicadorProducaoDTO.getQtdLigacoesActivas() );
        indicadorProducao.setQtdLigacoesDomesticasActivas( indicadorProducaoDTO.getQtdLigacoesDomesticasActivas() );
        indicadorProducao.setQtdLigacoesFacturadasBaseLeiturasReais( indicadorProducaoDTO.getQtdLigacoesFacturadasBaseLeiturasReais() );
        indicadorProducao.setQtdLigacoesFacturadasBaseEstimativasAvenca( indicadorProducaoDTO.getQtdLigacoesFacturadasBaseEstimativasAvenca() );
        indicadorProducao.setQtdVolumeAguaFacturada( indicadorProducaoDTO.getQtdVolumeAguaFacturada() );
        indicadorProducao.setQtdVolumeTotalFacturadaLigacoesDomesticas( indicadorProducaoDTO.getQtdVolumeTotalFacturadaLigacoesDomesticas() );
        indicadorProducao.setQtdVolumeFacturadoBaseLeituraReais( indicadorProducaoDTO.getQtdVolumeFacturadoBaseLeituraReais() );
        indicadorProducao.setVlrTotalFacturado( indicadorProducaoDTO.getVlrTotalFacturado() );
        indicadorProducao.setVlrFacturasCanceladasNotasCreditos( indicadorProducaoDTO.getVlrFacturasCanceladasNotasCreditos() );
        indicadorProducao.setVlrRealFacturado( indicadorProducaoDTO.getVlrRealFacturado() );
        indicadorProducao.setVlrTotalCobrado( indicadorProducaoDTO.getVlrTotalCobrado() );
        indicadorProducao.setQtdReclamacoes( indicadorProducaoDTO.getQtdReclamacoes() );
        indicadorProducao.setQtdReclamacoesRespondidasMenorIgualCincoDias( indicadorProducaoDTO.getQtdReclamacoesRespondidasMenorIgualCincoDias() );
        indicadorProducao.setQtdReclamacoesRespondidasMaisCincoMenosVinteDias( indicadorProducaoDTO.getQtdReclamacoesRespondidasMaisCincoMenosVinteDias() );
        indicadorProducao.setQtdReclamacoesRespondidasMaiorIgualVinteDias( indicadorProducaoDTO.getQtdReclamacoesRespondidasMaiorIgualVinteDias() );
        indicadorProducao.setVlrCustoPessoal( indicadorProducaoDTO.getVlrCustoPessoal() );
        indicadorProducao.setVlrCustoFse( indicadorProducaoDTO.getVlrCustoFse() );
        indicadorProducao.setVlrCustoEnergia( indicadorProducaoDTO.getVlrCustoEnergia() );
        indicadorProducao.setVlrCustoManutencao( indicadorProducaoDTO.getVlrCustoManutencao() );
        indicadorProducao.setVlrCustoReagentes( indicadorProducaoDTO.getVlrCustoReagentes() );
        indicadorProducao.setVlrCustoDestinoFinalLamas( indicadorProducaoDTO.getVlrCustoDestinoFinalLamas() );
        indicadorProducao.setVlrCustoOperacionaisOpex( indicadorProducaoDTO.getVlrCustoOperacionaisOpex() );
        indicadorProducao.setVlrCustoAmortizaAnualInvestOpCapex( indicadorProducaoDTO.getVlrCustoAmortizaAnualInvestOpCapex() );
        indicadorProducao.setVlrCustoTotaisCapexOpex( indicadorProducaoDTO.getVlrCustoTotaisCapexOpex() );
        indicadorProducao.setQtdCaptacoes( indicadorProducaoDTO.getQtdCaptacoes() );
        indicadorProducao.setQtdEtas( indicadorProducaoDTO.getQtdEtas() );
        indicadorProducao.setQtdReservatorios( indicadorProducaoDTO.getQtdReservatorios() );
        indicadorProducao.setQtdEstacoesElevatorias( indicadorProducaoDTO.getQtdEstacoesElevatorias() );
        indicadorProducao.setQtdComprimentoAdutoras( indicadorProducaoDTO.getQtdComprimentoAdutoras() );
        indicadorProducao.setQtdComprimentoRedes( indicadorProducaoDTO.getQtdComprimentoRedes() );
        indicadorProducao.setQtdComprimentoRamais( indicadorProducaoDTO.getQtdComprimentoRamais() );
        indicadorProducao.setQtdAcoesFormacaoMoPlaneadas( indicadorProducaoDTO.getQtdAcoesFormacaoMoPlaneadas() );
        indicadorProducao.setQtdAcoesFormacaoMmsPlaneadas( indicadorProducaoDTO.getQtdAcoesFormacaoMmsPlaneadas() );
        indicadorProducao.setQtdAcoesFormacaoCmpPlaneadas( indicadorProducaoDTO.getQtdAcoesFormacaoCmpPlaneadas() );
        indicadorProducao.setQtdAcoesFormacaoSoftwareFornecidosPlanejadas( indicadorProducaoDTO.getQtdAcoesFormacaoSoftwareFornecidosPlanejadas() );
        indicadorProducao.setQtdAcoesFormacaoMoRealizadas( indicadorProducaoDTO.getQtdAcoesFormacaoMoRealizadas() );
        indicadorProducao.setQtdAcoesFormacaoMmsRealizadas( indicadorProducaoDTO.getQtdAcoesFormacaoMmsRealizadas() );
        indicadorProducao.setQtdAcoesFormacaoCmpRealizadas( indicadorProducaoDTO.getQtdAcoesFormacaoCmpRealizadas() );
        indicadorProducao.setQtdAcoesFormacaoSoftwareFornecidosRealizadas( indicadorProducaoDTO.getQtdAcoesFormacaoSoftwareFornecidosRealizadas() );
        indicadorProducao.setQtdAcoesFormacaoRealizadas( indicadorProducaoDTO.getQtdAcoesFormacaoRealizadas() );
        indicadorProducao.setQtdManuaisMoPrevistos( indicadorProducaoDTO.getQtdManuaisMoPrevistos() );
        indicadorProducao.setQtdManuaisMmsPrevistos( indicadorProducaoDTO.getQtdManuaisMmsPrevistos() );
        indicadorProducao.setQtdManuaisCmpPrevistos( indicadorProducaoDTO.getQtdManuaisCmpPrevistos() );
        indicadorProducao.setQtdManuaisPrevistos( indicadorProducaoDTO.getQtdManuaisPrevistos() );
        indicadorProducao.setQtdAcoesManuaisMoRealizadas( indicadorProducaoDTO.getQtdAcoesManuaisMoRealizadas() );
        indicadorProducao.setQtdManuaisMmsRealizadas( indicadorProducaoDTO.getQtdManuaisMmsRealizadas() );
        indicadorProducao.setQtdManuaisCmpRealizadas( indicadorProducaoDTO.getQtdManuaisCmpRealizadas() );
        indicadorProducao.setQtdManuaisRealizados( indicadorProducaoDTO.getQtdManuaisRealizados() );

        return indicadorProducao;
    }
}
