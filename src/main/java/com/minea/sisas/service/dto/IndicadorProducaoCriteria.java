package com.minea.sisas.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.BigDecimalFilter;

import io.github.jhipster.service.filter.LocalDateFilter;



/**
 * Criteria class for the IndicadorProducao entity. This class is used in IndicadorProducaoResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /indicador-producaos?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class IndicadorProducaoCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idIndicadorProducao;

    private LongFilter idUsuario;

    private LocalDateFilter dtLancamento;

    private LocalDateFilter dtUltimaAlteracao;

    private BigDecimalFilter qtdPopulacaoCobertaInfraestrutura;

    private BigDecimalFilter qtdFontanariosChafarisesOperacionais;

    private BigDecimalFilter qtdMediaHorasDistribuicaoDiaria;

    private BigDecimalFilter qtdMediaHorasParagem;

    private BigDecimalFilter qtdMediaHorasInterrupcaoFaltaEnergia;

    private BigDecimalFilter qtdVolumeAguaCaptada;

    private BigDecimalFilter qtdVolumeAguaTratada;

    private BigDecimalFilter qtdVolumeAguaDistribuida;

    private BigDecimalFilter qtdConsumoEnergia;

    private BigDecimalFilter qtdConsumoCombustivel;

    private BigDecimalFilter qtdConsumoHipocloritroCalcio;

    private BigDecimalFilter qtdConsumoSulfatoAluminio;

    private BigDecimalFilter qtdConsumoHidroxidoCalcio;

    private LongFilter qtdReparoCaptacaoEtas;

    private LongFilter qtdReparoAdutoras;

    private LongFilter qtdReparoRedeDistribuicao;

    private LongFilter qtdReparoRamais;

    private LongFilter qtdManutencaoCurativa;

    private LongFilter qtdManutencaoPreventiva;

    private LongFilter qtdManutencaoVerificadoSolicitado;

    private LongFilter qtdReservatorioLavado;

    private LongFilter qtdFuncionarios;

    private LongFilter qtdFuncionariosEfectivos;

    private LongFilter qtdFuncionariosContratados;

    private LongFilter qtdFuncionariosOutrasEntidades;

    private LongFilter qtdNovasLigacoesNovosContratos;

    private LongFilter qtdNovasLigacoesDomesticasNovosContratos;

    private LongFilter qtdLigacoesIlegaisRegularizadas;

    private LongFilter qtdLigacoesFechadas;

    private LongFilter qtdCortes;

    private LongFilter qtdReligacoes;

    private LongFilter qtdLigacoesActivas;

    private LongFilter qtdLigacoesDomesticasActivas;

    private LongFilter qtdLigacoesFacturadasBaseLeiturasReais;

    private LongFilter qtdLigacoesFacturadasBaseEstimativasAvenca;

    private BigDecimalFilter qtdVolumeAguaFacturada;

    private BigDecimalFilter qtdVolumeTotalFacturadaLigacoesDomesticas;

    private BigDecimalFilter qtdVolumeFacturadoBaseLeituraReais;

    private BigDecimalFilter vlrTotalFacturado;

    private BigDecimalFilter vlrFacturasCanceladasNotasCreditos;

    private BigDecimalFilter vlrRealFacturado;

    private BigDecimalFilter vlrTotalCobrado;

    private LongFilter qtdReclamacoes;

    private LongFilter qtdReclamacoesRespondidasMenorIgualCincoDias;

    private LongFilter qtdReclamacoesRespondidasMaisCincoMenosVinteDias;

    private LongFilter qtdReclamacoesRespondidasMaiorIgualVinteDias;

    private BigDecimalFilter vlrCustoPessoal;

    private BigDecimalFilter vlrCustoFse;

    private BigDecimalFilter vlrCustoEnergia;

    private BigDecimalFilter vlrCustoManutencao;

    private BigDecimalFilter vlrCustoReagentes;

    private BigDecimalFilter vlrCustoDestinoFinalLamas;

    private BigDecimalFilter vlrCustoOperacionaisOpex;

    private BigDecimalFilter vlrCustoAmortizaAnualInvestOpCapex;

    private BigDecimalFilter vlrCustoTotaisCapexOpex;

    private LongFilter qtdCaptacoes;

    private LongFilter qtdEtas;

    private LongFilter qtdReservatorios;

    private LongFilter qtdEstacoesElevatorias;

    private BigDecimalFilter qtdComprimentoAdutoras;

    private BigDecimalFilter qtdComprimentoRedes;

    private BigDecimalFilter qtdComprimentoRamais;

    private LongFilter qtdAcoesFormacaoMoPlaneadas;

    private LongFilter qtdAcoesFormacaoMmsPlaneadas;

    private LongFilter qtdAcoesFormacaoCmpPlaneadas;

    private LongFilter qtdAcoesFormacaoSoftwareFornecidosPlanejadas;

    private LongFilter qtdAcoesFormacaoMoRealizadas;

    private LongFilter qtdAcoesFormacaoMmsRealizadas;

    private LongFilter qtdAcoesFormacaoCmpRealizadas;

    private LongFilter qtdAcoesFormacaoSoftwareFornecidosRealizadas;

    private LongFilter qtdAcoesFormacaoRealizadas;

    private LongFilter qtdManuaisMoPrevistos;

    private LongFilter qtdManuaisMmsPrevistos;

    private LongFilter qtdManuaisCmpPrevistos;

    private LongFilter qtdManuaisPrevistos;

    private LongFilter qtdAcoesManuaisMoRealizadas;

    private LongFilter qtdManuaisMmsRealizadas;

    private LongFilter qtdManuaisCmpRealizadas;

    private LongFilter qtdManuaisRealizados;

    private LongFilter idSituacaoId;

    private LongFilter idSistemaAguaId;

    private LongFilter idComunaId;

    private LongFilter indicadorProducaoLogId;

    public IndicadorProducaoCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdIndicadorProducao() {
        return idIndicadorProducao;
    }

    public void setIdIndicadorProducao(LongFilter idIndicadorProducao) {
        this.idIndicadorProducao = idIndicadorProducao;
    }

    public LongFilter getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(LongFilter idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDateFilter getDtLancamento() {
        return dtLancamento;
    }

    public void setDtLancamento(LocalDateFilter dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public LocalDateFilter getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }

    public void setDtUltimaAlteracao(LocalDateFilter dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }

    public BigDecimalFilter getQtdPopulacaoCobertaInfraestrutura() {
        return qtdPopulacaoCobertaInfraestrutura;
    }

    public void setQtdPopulacaoCobertaInfraestrutura(BigDecimalFilter qtdPopulacaoCobertaInfraestrutura) {
        this.qtdPopulacaoCobertaInfraestrutura = qtdPopulacaoCobertaInfraestrutura;
    }

    public BigDecimalFilter getQtdFontanariosChafarisesOperacionais() {
        return qtdFontanariosChafarisesOperacionais;
    }

    public void setQtdFontanariosChafarisesOperacionais(BigDecimalFilter qtdFontanariosChafarisesOperacionais) {
        this.qtdFontanariosChafarisesOperacionais = qtdFontanariosChafarisesOperacionais;
    }

    public BigDecimalFilter getQtdMediaHorasDistribuicaoDiaria() {
        return qtdMediaHorasDistribuicaoDiaria;
    }

    public void setQtdMediaHorasDistribuicaoDiaria(BigDecimalFilter qtdMediaHorasDistribuicaoDiaria) {
        this.qtdMediaHorasDistribuicaoDiaria = qtdMediaHorasDistribuicaoDiaria;
    }

    public BigDecimalFilter getQtdMediaHorasParagem() {
        return qtdMediaHorasParagem;
    }

    public void setQtdMediaHorasParagem(BigDecimalFilter qtdMediaHorasParagem) {
        this.qtdMediaHorasParagem = qtdMediaHorasParagem;
    }

    public BigDecimalFilter getQtdMediaHorasInterrupcaoFaltaEnergia() {
        return qtdMediaHorasInterrupcaoFaltaEnergia;
    }

    public void setQtdMediaHorasInterrupcaoFaltaEnergia(BigDecimalFilter qtdMediaHorasInterrupcaoFaltaEnergia) {
        this.qtdMediaHorasInterrupcaoFaltaEnergia = qtdMediaHorasInterrupcaoFaltaEnergia;
    }

    public BigDecimalFilter getQtdVolumeAguaCaptada() {
        return qtdVolumeAguaCaptada;
    }

    public void setQtdVolumeAguaCaptada(BigDecimalFilter qtdVolumeAguaCaptada) {
        this.qtdVolumeAguaCaptada = qtdVolumeAguaCaptada;
    }

    public BigDecimalFilter getQtdVolumeAguaTratada() {
        return qtdVolumeAguaTratada;
    }

    public void setQtdVolumeAguaTratada(BigDecimalFilter qtdVolumeAguaTratada) {
        this.qtdVolumeAguaTratada = qtdVolumeAguaTratada;
    }

    public BigDecimalFilter getQtdVolumeAguaDistribuida() {
        return qtdVolumeAguaDistribuida;
    }

    public void setQtdVolumeAguaDistribuida(BigDecimalFilter qtdVolumeAguaDistribuida) {
        this.qtdVolumeAguaDistribuida = qtdVolumeAguaDistribuida;
    }

    public BigDecimalFilter getQtdConsumoEnergia() {
        return qtdConsumoEnergia;
    }

    public void setQtdConsumoEnergia(BigDecimalFilter qtdConsumoEnergia) {
        this.qtdConsumoEnergia = qtdConsumoEnergia;
    }

    public BigDecimalFilter getQtdConsumoCombustivel() {
        return qtdConsumoCombustivel;
    }

    public void setQtdConsumoCombustivel(BigDecimalFilter qtdConsumoCombustivel) {
        this.qtdConsumoCombustivel = qtdConsumoCombustivel;
    }

    public BigDecimalFilter getQtdConsumoHipocloritroCalcio() {
        return qtdConsumoHipocloritroCalcio;
    }

    public void setQtdConsumoHipocloritroCalcio(BigDecimalFilter qtdConsumoHipocloritroCalcio) {
        this.qtdConsumoHipocloritroCalcio = qtdConsumoHipocloritroCalcio;
    }

    public BigDecimalFilter getQtdConsumoSulfatoAluminio() {
        return qtdConsumoSulfatoAluminio;
    }

    public void setQtdConsumoSulfatoAluminio(BigDecimalFilter qtdConsumoSulfatoAluminio) {
        this.qtdConsumoSulfatoAluminio = qtdConsumoSulfatoAluminio;
    }

    public BigDecimalFilter getQtdConsumoHidroxidoCalcio() {
        return qtdConsumoHidroxidoCalcio;
    }

    public void setQtdConsumoHidroxidoCalcio(BigDecimalFilter qtdConsumoHidroxidoCalcio) {
        this.qtdConsumoHidroxidoCalcio = qtdConsumoHidroxidoCalcio;
    }

    public LongFilter getQtdReparoCaptacaoEtas() {
        return qtdReparoCaptacaoEtas;
    }

    public void setQtdReparoCaptacaoEtas(LongFilter qtdReparoCaptacaoEtas) {
        this.qtdReparoCaptacaoEtas = qtdReparoCaptacaoEtas;
    }

    public LongFilter getQtdReparoAdutoras() {
        return qtdReparoAdutoras;
    }

    public void setQtdReparoAdutoras(LongFilter qtdReparoAdutoras) {
        this.qtdReparoAdutoras = qtdReparoAdutoras;
    }

    public LongFilter getQtdReparoRedeDistribuicao() {
        return qtdReparoRedeDistribuicao;
    }

    public void setQtdReparoRedeDistribuicao(LongFilter qtdReparoRedeDistribuicao) {
        this.qtdReparoRedeDistribuicao = qtdReparoRedeDistribuicao;
    }

    public LongFilter getQtdReparoRamais() {
        return qtdReparoRamais;
    }

    public void setQtdReparoRamais(LongFilter qtdReparoRamais) {
        this.qtdReparoRamais = qtdReparoRamais;
    }

    public LongFilter getQtdManutencaoCurativa() {
        return qtdManutencaoCurativa;
    }

    public void setQtdManutencaoCurativa(LongFilter qtdManutencaoCurativa) {
        this.qtdManutencaoCurativa = qtdManutencaoCurativa;
    }

    public LongFilter getQtdManutencaoPreventiva() {
        return qtdManutencaoPreventiva;
    }

    public void setQtdManutencaoPreventiva(LongFilter qtdManutencaoPreventiva) {
        this.qtdManutencaoPreventiva = qtdManutencaoPreventiva;
    }

    public LongFilter getQtdManutencaoVerificadoSolicitado() {
        return qtdManutencaoVerificadoSolicitado;
    }

    public void setQtdManutencaoVerificadoSolicitado(LongFilter qtdManutencaoVerificadoSolicitado) {
        this.qtdManutencaoVerificadoSolicitado = qtdManutencaoVerificadoSolicitado;
    }

    public LongFilter getQtdReservatorioLavado() {
        return qtdReservatorioLavado;
    }

    public void setQtdReservatorioLavado(LongFilter qtdReservatorioLavado) {
        this.qtdReservatorioLavado = qtdReservatorioLavado;
    }

    public LongFilter getQtdFuncionarios() {
        return qtdFuncionarios;
    }

    public void setQtdFuncionarios(LongFilter qtdFuncionarios) {
        this.qtdFuncionarios = qtdFuncionarios;
    }

    public LongFilter getQtdFuncionariosEfectivos() {
        return qtdFuncionariosEfectivos;
    }

    public void setQtdFuncionariosEfectivos(LongFilter qtdFuncionariosEfectivos) {
        this.qtdFuncionariosEfectivos = qtdFuncionariosEfectivos;
    }

    public LongFilter getQtdFuncionariosContratados() {
        return qtdFuncionariosContratados;
    }

    public void setQtdFuncionariosContratados(LongFilter qtdFuncionariosContratados) {
        this.qtdFuncionariosContratados = qtdFuncionariosContratados;
    }

    public LongFilter getQtdFuncionariosOutrasEntidades() {
        return qtdFuncionariosOutrasEntidades;
    }

    public void setQtdFuncionariosOutrasEntidades(LongFilter qtdFuncionariosOutrasEntidades) {
        this.qtdFuncionariosOutrasEntidades = qtdFuncionariosOutrasEntidades;
    }

    public LongFilter getQtdNovasLigacoesNovosContratos() {
        return qtdNovasLigacoesNovosContratos;
    }

    public void setQtdNovasLigacoesNovosContratos(LongFilter qtdNovasLigacoesNovosContratos) {
        this.qtdNovasLigacoesNovosContratos = qtdNovasLigacoesNovosContratos;
    }

    public LongFilter getQtdNovasLigacoesDomesticasNovosContratos() {
        return qtdNovasLigacoesDomesticasNovosContratos;
    }

    public void setQtdNovasLigacoesDomesticasNovosContratos(LongFilter qtdNovasLigacoesDomesticasNovosContratos) {
        this.qtdNovasLigacoesDomesticasNovosContratos = qtdNovasLigacoesDomesticasNovosContratos;
    }

    public LongFilter getQtdLigacoesIlegaisRegularizadas() {
        return qtdLigacoesIlegaisRegularizadas;
    }

    public void setQtdLigacoesIlegaisRegularizadas(LongFilter qtdLigacoesIlegaisRegularizadas) {
        this.qtdLigacoesIlegaisRegularizadas = qtdLigacoesIlegaisRegularizadas;
    }

    public LongFilter getQtdLigacoesFechadas() {
        return qtdLigacoesFechadas;
    }

    public void setQtdLigacoesFechadas(LongFilter qtdLigacoesFechadas) {
        this.qtdLigacoesFechadas = qtdLigacoesFechadas;
    }

    public LongFilter getQtdCortes() {
        return qtdCortes;
    }

    public void setQtdCortes(LongFilter qtdCortes) {
        this.qtdCortes = qtdCortes;
    }

    public LongFilter getQtdReligacoes() {
        return qtdReligacoes;
    }

    public void setQtdReligacoes(LongFilter qtdReligacoes) {
        this.qtdReligacoes = qtdReligacoes;
    }

    public LongFilter getQtdLigacoesActivas() {
        return qtdLigacoesActivas;
    }

    public void setQtdLigacoesActivas(LongFilter qtdLigacoesActivas) {
        this.qtdLigacoesActivas = qtdLigacoesActivas;
    }

    public LongFilter getQtdLigacoesDomesticasActivas() {
        return qtdLigacoesDomesticasActivas;
    }

    public void setQtdLigacoesDomesticasActivas(LongFilter qtdLigacoesDomesticasActivas) {
        this.qtdLigacoesDomesticasActivas = qtdLigacoesDomesticasActivas;
    }

    public LongFilter getQtdLigacoesFacturadasBaseLeiturasReais() {
        return qtdLigacoesFacturadasBaseLeiturasReais;
    }

    public void setQtdLigacoesFacturadasBaseLeiturasReais(LongFilter qtdLigacoesFacturadasBaseLeiturasReais) {
        this.qtdLigacoesFacturadasBaseLeiturasReais = qtdLigacoesFacturadasBaseLeiturasReais;
    }

    public LongFilter getQtdLigacoesFacturadasBaseEstimativasAvenca() {
        return qtdLigacoesFacturadasBaseEstimativasAvenca;
    }

    public void setQtdLigacoesFacturadasBaseEstimativasAvenca(LongFilter qtdLigacoesFacturadasBaseEstimativasAvenca) {
        this.qtdLigacoesFacturadasBaseEstimativasAvenca = qtdLigacoesFacturadasBaseEstimativasAvenca;
    }

    public BigDecimalFilter getQtdVolumeAguaFacturada() {
        return qtdVolumeAguaFacturada;
    }

    public void setQtdVolumeAguaFacturada(BigDecimalFilter qtdVolumeAguaFacturada) {
        this.qtdVolumeAguaFacturada = qtdVolumeAguaFacturada;
    }

    public BigDecimalFilter getQtdVolumeTotalFacturadaLigacoesDomesticas() {
        return qtdVolumeTotalFacturadaLigacoesDomesticas;
    }

    public void setQtdVolumeTotalFacturadaLigacoesDomesticas(BigDecimalFilter qtdVolumeTotalFacturadaLigacoesDomesticas) {
        this.qtdVolumeTotalFacturadaLigacoesDomesticas = qtdVolumeTotalFacturadaLigacoesDomesticas;
    }

    public BigDecimalFilter getQtdVolumeFacturadoBaseLeituraReais() {
        return qtdVolumeFacturadoBaseLeituraReais;
    }

    public void setQtdVolumeFacturadoBaseLeituraReais(BigDecimalFilter qtdVolumeFacturadoBaseLeituraReais) {
        this.qtdVolumeFacturadoBaseLeituraReais = qtdVolumeFacturadoBaseLeituraReais;
    }

    public BigDecimalFilter getVlrTotalFacturado() {
        return vlrTotalFacturado;
    }

    public void setVlrTotalFacturado(BigDecimalFilter vlrTotalFacturado) {
        this.vlrTotalFacturado = vlrTotalFacturado;
    }

    public BigDecimalFilter getVlrFacturasCanceladasNotasCreditos() {
        return vlrFacturasCanceladasNotasCreditos;
    }

    public void setVlrFacturasCanceladasNotasCreditos(BigDecimalFilter vlrFacturasCanceladasNotasCreditos) {
        this.vlrFacturasCanceladasNotasCreditos = vlrFacturasCanceladasNotasCreditos;
    }

    public BigDecimalFilter getVlrRealFacturado() {
        return vlrRealFacturado;
    }

    public void setVlrRealFacturado(BigDecimalFilter vlrRealFacturado) {
        this.vlrRealFacturado = vlrRealFacturado;
    }

    public BigDecimalFilter getVlrTotalCobrado() {
        return vlrTotalCobrado;
    }

    public void setVlrTotalCobrado(BigDecimalFilter vlrTotalCobrado) {
        this.vlrTotalCobrado = vlrTotalCobrado;
    }

    public LongFilter getQtdReclamacoes() {
        return qtdReclamacoes;
    }

    public void setQtdReclamacoes(LongFilter qtdReclamacoes) {
        this.qtdReclamacoes = qtdReclamacoes;
    }

    public LongFilter getQtdReclamacoesRespondidasMenorIgualCincoDias() {
        return qtdReclamacoesRespondidasMenorIgualCincoDias;
    }

    public void setQtdReclamacoesRespondidasMenorIgualCincoDias(LongFilter qtdReclamacoesRespondidasMenorIgualCincoDias) {
        this.qtdReclamacoesRespondidasMenorIgualCincoDias = qtdReclamacoesRespondidasMenorIgualCincoDias;
    }

    public LongFilter getQtdReclamacoesRespondidasMaisCincoMenosVinteDias() {
        return qtdReclamacoesRespondidasMaisCincoMenosVinteDias;
    }

    public void setQtdReclamacoesRespondidasMaisCincoMenosVinteDias(LongFilter qtdReclamacoesRespondidasMaisCincoMenosVinteDias) {
        this.qtdReclamacoesRespondidasMaisCincoMenosVinteDias = qtdReclamacoesRespondidasMaisCincoMenosVinteDias;
    }

    public LongFilter getQtdReclamacoesRespondidasMaiorIgualVinteDias() {
        return qtdReclamacoesRespondidasMaiorIgualVinteDias;
    }

    public void setQtdReclamacoesRespondidasMaiorIgualVinteDias(LongFilter qtdReclamacoesRespondidasMaiorIgualVinteDias) {
        this.qtdReclamacoesRespondidasMaiorIgualVinteDias = qtdReclamacoesRespondidasMaiorIgualVinteDias;
    }

    public BigDecimalFilter getVlrCustoPessoal() {
        return vlrCustoPessoal;
    }

    public void setVlrCustoPessoal(BigDecimalFilter vlrCustoPessoal) {
        this.vlrCustoPessoal = vlrCustoPessoal;
    }

    public BigDecimalFilter getVlrCustoFse() {
        return vlrCustoFse;
    }

    public void setVlrCustoFse(BigDecimalFilter vlrCustoFse) {
        this.vlrCustoFse = vlrCustoFse;
    }

    public BigDecimalFilter getVlrCustoEnergia() {
        return vlrCustoEnergia;
    }

    public void setVlrCustoEnergia(BigDecimalFilter vlrCustoEnergia) {
        this.vlrCustoEnergia = vlrCustoEnergia;
    }

    public BigDecimalFilter getVlrCustoManutencao() {
        return vlrCustoManutencao;
    }

    public void setVlrCustoManutencao(BigDecimalFilter vlrCustoManutencao) {
        this.vlrCustoManutencao = vlrCustoManutencao;
    }

    public BigDecimalFilter getVlrCustoReagentes() {
        return vlrCustoReagentes;
    }

    public void setVlrCustoReagentes(BigDecimalFilter vlrCustoReagentes) {
        this.vlrCustoReagentes = vlrCustoReagentes;
    }

    public BigDecimalFilter getVlrCustoDestinoFinalLamas() {
        return vlrCustoDestinoFinalLamas;
    }

    public void setVlrCustoDestinoFinalLamas(BigDecimalFilter vlrCustoDestinoFinalLamas) {
        this.vlrCustoDestinoFinalLamas = vlrCustoDestinoFinalLamas;
    }

    public BigDecimalFilter getVlrCustoOperacionaisOpex() {
        return vlrCustoOperacionaisOpex;
    }

    public void setVlrCustoOperacionaisOpex(BigDecimalFilter vlrCustoOperacionaisOpex) {
        this.vlrCustoOperacionaisOpex = vlrCustoOperacionaisOpex;
    }

    public BigDecimalFilter getVlrCustoAmortizaAnualInvestOpCapex() {
        return vlrCustoAmortizaAnualInvestOpCapex;
    }

    public void setVlrCustoAmortizaAnualInvestOpCapex(BigDecimalFilter vlrCustoAmortizaAnualInvestOpCapex) {
        this.vlrCustoAmortizaAnualInvestOpCapex = vlrCustoAmortizaAnualInvestOpCapex;
    }

    public BigDecimalFilter getVlrCustoTotaisCapexOpex() {
        return vlrCustoTotaisCapexOpex;
    }

    public void setVlrCustoTotaisCapexOpex(BigDecimalFilter vlrCustoTotaisCapexOpex) {
        this.vlrCustoTotaisCapexOpex = vlrCustoTotaisCapexOpex;
    }

    public LongFilter getQtdCaptacoes() {
        return qtdCaptacoes;
    }

    public void setQtdCaptacoes(LongFilter qtdCaptacoes) {
        this.qtdCaptacoes = qtdCaptacoes;
    }

    public LongFilter getQtdEtas() {
        return qtdEtas;
    }

    public void setQtdEtas(LongFilter qtdEtas) {
        this.qtdEtas = qtdEtas;
    }

    public LongFilter getQtdReservatorios() {
        return qtdReservatorios;
    }

    public void setQtdReservatorios(LongFilter qtdReservatorios) {
        this.qtdReservatorios = qtdReservatorios;
    }

    public LongFilter getQtdEstacoesElevatorias() {
        return qtdEstacoesElevatorias;
    }

    public void setQtdEstacoesElevatorias(LongFilter qtdEstacoesElevatorias) {
        this.qtdEstacoesElevatorias = qtdEstacoesElevatorias;
    }

    public BigDecimalFilter getQtdComprimentoAdutoras() {
        return qtdComprimentoAdutoras;
    }

    public void setQtdComprimentoAdutoras(BigDecimalFilter qtdComprimentoAdutoras) {
        this.qtdComprimentoAdutoras = qtdComprimentoAdutoras;
    }

    public BigDecimalFilter getQtdComprimentoRedes() {
        return qtdComprimentoRedes;
    }

    public void setQtdComprimentoRedes(BigDecimalFilter qtdComprimentoRedes) {
        this.qtdComprimentoRedes = qtdComprimentoRedes;
    }

    public BigDecimalFilter getQtdComprimentoRamais() {
        return qtdComprimentoRamais;
    }

    public void setQtdComprimentoRamais(BigDecimalFilter qtdComprimentoRamais) {
        this.qtdComprimentoRamais = qtdComprimentoRamais;
    }

    public LongFilter getQtdAcoesFormacaoMoPlaneadas() {
        return qtdAcoesFormacaoMoPlaneadas;
    }

    public void setQtdAcoesFormacaoMoPlaneadas(LongFilter qtdAcoesFormacaoMoPlaneadas) {
        this.qtdAcoesFormacaoMoPlaneadas = qtdAcoesFormacaoMoPlaneadas;
    }

    public LongFilter getQtdAcoesFormacaoMmsPlaneadas() {
        return qtdAcoesFormacaoMmsPlaneadas;
    }

    public void setQtdAcoesFormacaoMmsPlaneadas(LongFilter qtdAcoesFormacaoMmsPlaneadas) {
        this.qtdAcoesFormacaoMmsPlaneadas = qtdAcoesFormacaoMmsPlaneadas;
    }

    public LongFilter getQtdAcoesFormacaoCmpPlaneadas() {
        return qtdAcoesFormacaoCmpPlaneadas;
    }

    public void setQtdAcoesFormacaoCmpPlaneadas(LongFilter qtdAcoesFormacaoCmpPlaneadas) {
        this.qtdAcoesFormacaoCmpPlaneadas = qtdAcoesFormacaoCmpPlaneadas;
    }

    public LongFilter getQtdAcoesFormacaoSoftwareFornecidosPlanejadas() {
        return qtdAcoesFormacaoSoftwareFornecidosPlanejadas;
    }

    public void setQtdAcoesFormacaoSoftwareFornecidosPlanejadas(LongFilter qtdAcoesFormacaoSoftwareFornecidosPlanejadas) {
        this.qtdAcoesFormacaoSoftwareFornecidosPlanejadas = qtdAcoesFormacaoSoftwareFornecidosPlanejadas;
    }

    public LongFilter getQtdAcoesFormacaoMoRealizadas() {
        return qtdAcoesFormacaoMoRealizadas;
    }

    public void setQtdAcoesFormacaoMoRealizadas(LongFilter qtdAcoesFormacaoMoRealizadas) {
        this.qtdAcoesFormacaoMoRealizadas = qtdAcoesFormacaoMoRealizadas;
    }

    public LongFilter getQtdAcoesFormacaoMmsRealizadas() {
        return qtdAcoesFormacaoMmsRealizadas;
    }

    public void setQtdAcoesFormacaoMmsRealizadas(LongFilter qtdAcoesFormacaoMmsRealizadas) {
        this.qtdAcoesFormacaoMmsRealizadas = qtdAcoesFormacaoMmsRealizadas;
    }

    public LongFilter getQtdAcoesFormacaoCmpRealizadas() {
        return qtdAcoesFormacaoCmpRealizadas;
    }

    public void setQtdAcoesFormacaoCmpRealizadas(LongFilter qtdAcoesFormacaoCmpRealizadas) {
        this.qtdAcoesFormacaoCmpRealizadas = qtdAcoesFormacaoCmpRealizadas;
    }

    public LongFilter getQtdAcoesFormacaoSoftwareFornecidosRealizadas() {
        return qtdAcoesFormacaoSoftwareFornecidosRealizadas;
    }

    public void setQtdAcoesFormacaoSoftwareFornecidosRealizadas(LongFilter qtdAcoesFormacaoSoftwareFornecidosRealizadas) {
        this.qtdAcoesFormacaoSoftwareFornecidosRealizadas = qtdAcoesFormacaoSoftwareFornecidosRealizadas;
    }

    public LongFilter getQtdAcoesFormacaoRealizadas() {
        return qtdAcoesFormacaoRealizadas;
    }

    public void setQtdAcoesFormacaoRealizadas(LongFilter qtdAcoesFormacaoRealizadas) {
        this.qtdAcoesFormacaoRealizadas = qtdAcoesFormacaoRealizadas;
    }

    public LongFilter getQtdManuaisMoPrevistos() {
        return qtdManuaisMoPrevistos;
    }

    public void setQtdManuaisMoPrevistos(LongFilter qtdManuaisMoPrevistos) {
        this.qtdManuaisMoPrevistos = qtdManuaisMoPrevistos;
    }

    public LongFilter getQtdManuaisMmsPrevistos() {
        return qtdManuaisMmsPrevistos;
    }

    public void setQtdManuaisMmsPrevistos(LongFilter qtdManuaisMmsPrevistos) {
        this.qtdManuaisMmsPrevistos = qtdManuaisMmsPrevistos;
    }

    public LongFilter getQtdManuaisCmpPrevistos() {
        return qtdManuaisCmpPrevistos;
    }

    public void setQtdManuaisCmpPrevistos(LongFilter qtdManuaisCmpPrevistos) {
        this.qtdManuaisCmpPrevistos = qtdManuaisCmpPrevistos;
    }

    public LongFilter getQtdManuaisPrevistos() {
        return qtdManuaisPrevistos;
    }

    public void setQtdManuaisPrevistos(LongFilter qtdManuaisPrevistos) {
        this.qtdManuaisPrevistos = qtdManuaisPrevistos;
    }

    public LongFilter getQtdAcoesManuaisMoRealizadas() {
        return qtdAcoesManuaisMoRealizadas;
    }

    public void setQtdAcoesManuaisMoRealizadas(LongFilter qtdAcoesManuaisMoRealizadas) {
        this.qtdAcoesManuaisMoRealizadas = qtdAcoesManuaisMoRealizadas;
    }

    public LongFilter getQtdManuaisMmsRealizadas() {
        return qtdManuaisMmsRealizadas;
    }

    public void setQtdManuaisMmsRealizadas(LongFilter qtdManuaisMmsRealizadas) {
        this.qtdManuaisMmsRealizadas = qtdManuaisMmsRealizadas;
    }

    public LongFilter getQtdManuaisCmpRealizadas() {
        return qtdManuaisCmpRealizadas;
    }

    public void setQtdManuaisCmpRealizadas(LongFilter qtdManuaisCmpRealizadas) {
        this.qtdManuaisCmpRealizadas = qtdManuaisCmpRealizadas;
    }

    public LongFilter getQtdManuaisRealizados() {
        return qtdManuaisRealizados;
    }

    public void setQtdManuaisRealizados(LongFilter qtdManuaisRealizados) {
        this.qtdManuaisRealizados = qtdManuaisRealizados;
    }

    public LongFilter getIdSituacaoId() {
        return idSituacaoId;
    }

    public void setIdSituacaoId(LongFilter idSituacaoId) {
        this.idSituacaoId = idSituacaoId;
    }

    public LongFilter getIdSistemaAguaId() {
        return idSistemaAguaId;
    }

    public void setIdSistemaAguaId(LongFilter idSistemaAguaId) {
        this.idSistemaAguaId = idSistemaAguaId;
    }

    public LongFilter getIdComunaId() {
        return idComunaId;
    }

    public void setIdComunaId(LongFilter idComunaId) {
        this.idComunaId = idComunaId;
    }

    public LongFilter getIndicadorProducaoLogId() {
        return indicadorProducaoLogId;
    }

    public void setIndicadorProducaoLogId(LongFilter indicadorProducaoLogId) {
        this.indicadorProducaoLogId = indicadorProducaoLogId;
    }

    @Override
    public String toString() {
        return "IndicadorProducaoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idIndicadorProducao != null ? "idIndicadorProducao=" + idIndicadorProducao + ", " : "") +
                (idUsuario != null ? "idUsuario=" + idUsuario + ", " : "") +
                (dtLancamento != null ? "dtLancamento=" + dtLancamento + ", " : "") +
                (dtUltimaAlteracao != null ? "dtUltimaAlteracao=" + dtUltimaAlteracao + ", " : "") +
                (qtdPopulacaoCobertaInfraestrutura != null ? "qtdPopulacaoCobertaInfraestrutura=" + qtdPopulacaoCobertaInfraestrutura + ", " : "") +
                (qtdFontanariosChafarisesOperacionais != null ? "qtdFontanariosChafarisesOperacionais=" + qtdFontanariosChafarisesOperacionais + ", " : "") +
                (qtdMediaHorasDistribuicaoDiaria != null ? "qtdMediaHorasDistribuicaoDiaria=" + qtdMediaHorasDistribuicaoDiaria + ", " : "") +
                (qtdMediaHorasParagem != null ? "qtdMediaHorasParagem=" + qtdMediaHorasParagem + ", " : "") +
                (qtdMediaHorasInterrupcaoFaltaEnergia != null ? "qtdMediaHorasInterrupcaoFaltaEnergia=" + qtdMediaHorasInterrupcaoFaltaEnergia + ", " : "") +
                (qtdVolumeAguaCaptada != null ? "qtdVolumeAguaCaptada=" + qtdVolumeAguaCaptada + ", " : "") +
                (qtdVolumeAguaTratada != null ? "qtdVolumeAguaTratada=" + qtdVolumeAguaTratada + ", " : "") +
                (qtdVolumeAguaDistribuida != null ? "qtdVolumeAguaDistribuida=" + qtdVolumeAguaDistribuida + ", " : "") +
                (qtdConsumoEnergia != null ? "qtdConsumoEnergia=" + qtdConsumoEnergia + ", " : "") +
                (qtdConsumoCombustivel != null ? "qtdConsumoCombustivel=" + qtdConsumoCombustivel + ", " : "") +
                (qtdConsumoHipocloritroCalcio != null ? "qtdConsumoHipocloritroCalcio=" + qtdConsumoHipocloritroCalcio + ", " : "") +
                (qtdConsumoSulfatoAluminio != null ? "qtdConsumoSulfatoAluminio=" + qtdConsumoSulfatoAluminio + ", " : "") +
                (qtdConsumoHidroxidoCalcio != null ? "qtdConsumoHidroxidoCalcio=" + qtdConsumoHidroxidoCalcio + ", " : "") +
                (qtdReparoCaptacaoEtas != null ? "qtdReparoCaptacaoEtas=" + qtdReparoCaptacaoEtas + ", " : "") +
                (qtdReparoAdutoras != null ? "qtdReparoAdutoras=" + qtdReparoAdutoras + ", " : "") +
                (qtdReparoRedeDistribuicao != null ? "qtdReparoRedeDistribuicao=" + qtdReparoRedeDistribuicao + ", " : "") +
                (qtdReparoRamais != null ? "qtdReparoRamais=" + qtdReparoRamais + ", " : "") +
                (qtdManutencaoCurativa != null ? "qtdManutencaoCurativa=" + qtdManutencaoCurativa + ", " : "") +
                (qtdManutencaoPreventiva != null ? "qtdManutencaoPreventiva=" + qtdManutencaoPreventiva + ", " : "") +
                (qtdManutencaoVerificadoSolicitado != null ? "qtdManutencaoVerificadoSolicitado=" + qtdManutencaoVerificadoSolicitado + ", " : "") +
                (qtdReservatorioLavado != null ? "qtdReservatorioLavado=" + qtdReservatorioLavado + ", " : "") +
                (qtdFuncionarios != null ? "qtdFuncionarios=" + qtdFuncionarios + ", " : "") +
                (qtdFuncionariosEfectivos != null ? "qtdFuncionariosEfectivos=" + qtdFuncionariosEfectivos + ", " : "") +
                (qtdFuncionariosContratados != null ? "qtdFuncionariosContratados=" + qtdFuncionariosContratados + ", " : "") +
                (qtdFuncionariosOutrasEntidades != null ? "qtdFuncionariosOutrasEntidades=" + qtdFuncionariosOutrasEntidades + ", " : "") +
                (qtdNovasLigacoesNovosContratos != null ? "qtdNovasLigacoesNovosContratos=" + qtdNovasLigacoesNovosContratos + ", " : "") +
                (qtdNovasLigacoesDomesticasNovosContratos != null ? "qtdNovasLigacoesDomesticasNovosContratos=" + qtdNovasLigacoesDomesticasNovosContratos + ", " : "") +
                (qtdLigacoesIlegaisRegularizadas != null ? "qtdLigacoesIlegaisRegularizadas=" + qtdLigacoesIlegaisRegularizadas + ", " : "") +
                (qtdLigacoesFechadas != null ? "qtdLigacoesFechadas=" + qtdLigacoesFechadas + ", " : "") +
                (qtdCortes != null ? "qtdCortes=" + qtdCortes + ", " : "") +
                (qtdReligacoes != null ? "qtdReligacoes=" + qtdReligacoes + ", " : "") +
                (qtdLigacoesActivas != null ? "qtdLigacoesActivas=" + qtdLigacoesActivas + ", " : "") +
                (qtdLigacoesDomesticasActivas != null ? "qtdLigacoesDomesticasActivas=" + qtdLigacoesDomesticasActivas + ", " : "") +
                (qtdLigacoesFacturadasBaseLeiturasReais != null ? "qtdLigacoesFacturadasBaseLeiturasReais=" + qtdLigacoesFacturadasBaseLeiturasReais + ", " : "") +
                (qtdLigacoesFacturadasBaseEstimativasAvenca != null ? "qtdLigacoesFacturadasBaseEstimativasAvenca=" + qtdLigacoesFacturadasBaseEstimativasAvenca + ", " : "") +
                (qtdVolumeAguaFacturada != null ? "qtdVolumeAguaFacturada=" + qtdVolumeAguaFacturada + ", " : "") +
                (qtdVolumeTotalFacturadaLigacoesDomesticas != null ? "qtdVolumeTotalFacturadaLigacoesDomesticas=" + qtdVolumeTotalFacturadaLigacoesDomesticas + ", " : "") +
                (qtdVolumeFacturadoBaseLeituraReais != null ? "qtdVolumeFacturadoBaseLeituraReais=" + qtdVolumeFacturadoBaseLeituraReais + ", " : "") +
                (vlrTotalFacturado != null ? "vlrTotalFacturado=" + vlrTotalFacturado + ", " : "") +
                (vlrFacturasCanceladasNotasCreditos != null ? "vlrFacturasCanceladasNotasCreditos=" + vlrFacturasCanceladasNotasCreditos + ", " : "") +
                (vlrRealFacturado != null ? "vlrRealFacturado=" + vlrRealFacturado + ", " : "") +
                (vlrTotalCobrado != null ? "vlrTotalCobrado=" + vlrTotalCobrado + ", " : "") +
                (qtdReclamacoes != null ? "qtdReclamacoes=" + qtdReclamacoes + ", " : "") +
                (qtdReclamacoesRespondidasMenorIgualCincoDias != null ? "qtdReclamacoesRespondidasMenorIgualCincoDias=" + qtdReclamacoesRespondidasMenorIgualCincoDias + ", " : "") +
                (qtdReclamacoesRespondidasMaisCincoMenosVinteDias != null ? "qtdReclamacoesRespondidasMaisCincoMenosVinteDias=" + qtdReclamacoesRespondidasMaisCincoMenosVinteDias + ", " : "") +
                (qtdReclamacoesRespondidasMaiorIgualVinteDias != null ? "qtdReclamacoesRespondidasMaiorIgualVinteDias=" + qtdReclamacoesRespondidasMaiorIgualVinteDias + ", " : "") +
                (vlrCustoPessoal != null ? "vlrCustoPessoal=" + vlrCustoPessoal + ", " : "") +
                (vlrCustoFse != null ? "vlrCustoFse=" + vlrCustoFse + ", " : "") +
                (vlrCustoEnergia != null ? "vlrCustoEnergia=" + vlrCustoEnergia + ", " : "") +
                (vlrCustoManutencao != null ? "vlrCustoManutencao=" + vlrCustoManutencao + ", " : "") +
                (vlrCustoReagentes != null ? "vlrCustoReagentes=" + vlrCustoReagentes + ", " : "") +
                (vlrCustoDestinoFinalLamas != null ? "vlrCustoDestinoFinalLamas=" + vlrCustoDestinoFinalLamas + ", " : "") +
                (vlrCustoOperacionaisOpex != null ? "vlrCustoOperacionaisOpex=" + vlrCustoOperacionaisOpex + ", " : "") +
                (vlrCustoAmortizaAnualInvestOpCapex != null ? "vlrCustoAmortizaAnualInvestOpCapex=" + vlrCustoAmortizaAnualInvestOpCapex + ", " : "") +
                (vlrCustoTotaisCapexOpex != null ? "vlrCustoTotaisCapexOpex=" + vlrCustoTotaisCapexOpex + ", " : "") +
                (qtdCaptacoes != null ? "qtdCaptacoes=" + qtdCaptacoes + ", " : "") +
                (qtdEtas != null ? "qtdEtas=" + qtdEtas + ", " : "") +
                (qtdReservatorios != null ? "qtdReservatorios=" + qtdReservatorios + ", " : "") +
                (qtdEstacoesElevatorias != null ? "qtdEstacoesElevatorias=" + qtdEstacoesElevatorias + ", " : "") +
                (qtdComprimentoAdutoras != null ? "qtdComprimentoAdutoras=" + qtdComprimentoAdutoras + ", " : "") +
                (qtdComprimentoRedes != null ? "qtdComprimentoRedes=" + qtdComprimentoRedes + ", " : "") +
                (qtdComprimentoRamais != null ? "qtdComprimentoRamais=" + qtdComprimentoRamais + ", " : "") +
                (qtdAcoesFormacaoMoPlaneadas != null ? "qtdAcoesFormacaoMoPlaneadas=" + qtdAcoesFormacaoMoPlaneadas + ", " : "") +
                (qtdAcoesFormacaoMmsPlaneadas != null ? "qtdAcoesFormacaoMmsPlaneadas=" + qtdAcoesFormacaoMmsPlaneadas + ", " : "") +
                (qtdAcoesFormacaoCmpPlaneadas != null ? "qtdAcoesFormacaoCmpPlaneadas=" + qtdAcoesFormacaoCmpPlaneadas + ", " : "") +
                (qtdAcoesFormacaoSoftwareFornecidosPlanejadas != null ? "qtdAcoesFormacaoSoftwareFornecidosPlanejadas=" + qtdAcoesFormacaoSoftwareFornecidosPlanejadas + ", " : "") +
                (qtdAcoesFormacaoMoRealizadas != null ? "qtdAcoesFormacaoMoRealizadas=" + qtdAcoesFormacaoMoRealizadas + ", " : "") +
                (qtdAcoesFormacaoMmsRealizadas != null ? "qtdAcoesFormacaoMmsRealizadas=" + qtdAcoesFormacaoMmsRealizadas + ", " : "") +
                (qtdAcoesFormacaoCmpRealizadas != null ? "qtdAcoesFormacaoCmpRealizadas=" + qtdAcoesFormacaoCmpRealizadas + ", " : "") +
                (qtdAcoesFormacaoSoftwareFornecidosRealizadas != null ? "qtdAcoesFormacaoSoftwareFornecidosRealizadas=" + qtdAcoesFormacaoSoftwareFornecidosRealizadas + ", " : "") +
                (qtdAcoesFormacaoRealizadas != null ? "qtdAcoesFormacaoRealizadas=" + qtdAcoesFormacaoRealizadas + ", " : "") +
                (qtdManuaisMoPrevistos != null ? "qtdManuaisMoPrevistos=" + qtdManuaisMoPrevistos + ", " : "") +
                (qtdManuaisMmsPrevistos != null ? "qtdManuaisMmsPrevistos=" + qtdManuaisMmsPrevistos + ", " : "") +
                (qtdManuaisCmpPrevistos != null ? "qtdManuaisCmpPrevistos=" + qtdManuaisCmpPrevistos + ", " : "") +
                (qtdManuaisPrevistos != null ? "qtdManuaisPrevistos=" + qtdManuaisPrevistos + ", " : "") +
                (qtdAcoesManuaisMoRealizadas != null ? "qtdAcoesManuaisMoRealizadas=" + qtdAcoesManuaisMoRealizadas + ", " : "") +
                (qtdManuaisMmsRealizadas != null ? "qtdManuaisMmsRealizadas=" + qtdManuaisMmsRealizadas + ", " : "") +
                (qtdManuaisCmpRealizadas != null ? "qtdManuaisCmpRealizadas=" + qtdManuaisCmpRealizadas + ", " : "") +
                (qtdManuaisRealizados != null ? "qtdManuaisRealizados=" + qtdManuaisRealizados + ", " : "") +
                (idSituacaoId != null ? "situacao=" + idSituacaoId + ", " : "") +
                (idSistemaAguaId != null ? "idSistemaAguaId=" + idSistemaAguaId + ", " : "") +
                (idComunaId != null ? "comuna=" + idComunaId + ", " : "") +
                (indicadorProducaoLogId != null ? "indicadorProducaoLogId=" + indicadorProducaoLogId + ", " : "") +
            "}";
    }

}
