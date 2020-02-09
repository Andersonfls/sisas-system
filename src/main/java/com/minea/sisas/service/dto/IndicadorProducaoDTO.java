package com.minea.sisas.service.dto;


import com.minea.sisas.domain.*;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the IndicadorProducao entity.
 */
public class IndicadorProducaoDTO implements Serializable {

    private Long id;

    private Long idUsuario;

    @NotNull
    private LocalDate dtLancamento;

    private LocalDate dtUltimaAlteracao;

    @NotNull
    private BigDecimal qtdPopulacaoCobertaInfraestrutura;

    @NotNull
    private BigDecimal qtdFontanariosChafarisesOperacionais;

    @NotNull
    private BigDecimal qtdMediaHorasDistribuicaoDiaria;

    @NotNull
    private BigDecimal qtdMediaHorasParagem;

    @NotNull
    private BigDecimal qtdMediaHorasInterrupcaoFaltaEnergia;

    @NotNull
    private BigDecimal qtdVolumeAguaCaptada;

    @NotNull
    private BigDecimal qtdVolumeAguaTratada;

    @NotNull
    private BigDecimal qtdVolumeAguaDistribuida;

    @NotNull
    private BigDecimal qtdConsumoEnergia;

    @NotNull
    private BigDecimal qtdConsumoCombustivel;

    @NotNull
    private BigDecimal qtdConsumoHipocloritroCalcio;

    @NotNull
    private BigDecimal qtdConsumoSulfatoAluminio;

    @NotNull
    private BigDecimal qtdConsumoHidroxidoCalcio;

    @NotNull
    private Long qtdReparoCaptacaoEtas;

    @NotNull
    private Long qtdReparoAdutoras;

    @NotNull
    private Long qtdReparoRedeDistribuicao;

    @NotNull
    private Long qtdReparoRamais;

    @NotNull
    private Long qtdManutencaoCurativa;

    @NotNull
    private Long qtdManutencaoPreventiva;

    @NotNull
    private Long qtdManutencaoVerificadoSolicitado;

    @NotNull
    private Long qtdReservatorioLavado;

    @NotNull
    private Long qtdFuncionarios;

    @NotNull
    private Long qtdFuncionariosEfectivos;

    @NotNull
    private Long qtdFuncionariosContratados;

    @NotNull
    private Long qtdFuncionariosOutrasEntidades;

    @NotNull
    private Long qtdNovasLigacoesNovosContratos;

    @NotNull
    private Long qtdNovasLigacoesDomesticasNovosContratos;

    @NotNull
    private Long qtdLigacoesIlegaisRegularizadas;

    @NotNull
    private Long qtdLigacoesFechadas;

    @NotNull
    private Long qtdCortes;

    @NotNull
    private Long qtdReligacoes;

    @NotNull
    private Long qtdLigacoesActivas;

    @NotNull
    private Long qtdLigacoesDomesticasActivas;

    @NotNull
    private Long qtdLigacoesFacturadasBaseLeiturasReais;

    @NotNull
    private Long qtdLigacoesFacturadasBaseEstimativasAvenca;

    @NotNull
    private BigDecimal qtdVolumeAguaFacturada;

    @NotNull
    private BigDecimal qtdVolumeTotalFacturadaLigacoesDomesticas;

    @NotNull
    private BigDecimal qtdVolumeFacturadoBaseLeituraReais;

    @NotNull
    private BigDecimal vlrTotalFacturado;

    @NotNull
    private BigDecimal vlrFacturasCanceladasNotasCreditos;

    @NotNull
    private BigDecimal vlrRealFacturado;

    @NotNull
    private BigDecimal vlrTotalCobrado;

    @NotNull
    private Long qtdReclamacoes;

    private Long qtdReclamacoesRespondidasMenorIgualCincoDias;

    private Long qtdReclamacoesRespondidasMaisCincoMenosVinteDias;

    private Long qtdReclamacoesRespondidasMaiorIgualVinteDias;

    @NotNull
    private BigDecimal vlrCustoPessoal;

    @NotNull
    private BigDecimal vlrCustoFse;

    @NotNull
    private BigDecimal vlrCustoEnergia;

    @NotNull
    private BigDecimal vlrCustoManutencao;

    @NotNull
    private BigDecimal vlrCustoReagentes;

    @NotNull
    private BigDecimal vlrCustoDestinoFinalLamas;

    @NotNull
    private BigDecimal vlrCustoOperacionaisOpex;

    @NotNull
    private BigDecimal vlrCustoAmortizaAnualInvestOpCapex;

    private BigDecimal vlrCustoTotaisCapexOpex;

    @NotNull
    private Long qtdCaptacoes;

    @NotNull
    private Long qtdEtas;

    @NotNull
    private Long qtdReservatorios;

    @NotNull
    private Long qtdEstacoesElevatorias;

    @NotNull
    private BigDecimal qtdComprimentoAdutoras;

    @NotNull
    private BigDecimal qtdComprimentoRedes;

    @NotNull
    private BigDecimal qtdComprimentoRamais;

    @NotNull
    private Long qtdAcoesFormacaoMoPlaneadas;

    @NotNull
    private Long qtdAcoesFormacaoMmsPlaneadas;

    @NotNull
    private Long qtdAcoesFormacaoCmpPlaneadas;

    @NotNull
    private Long qtdAcoesFormacaoSoftwareFornecidosPlanejadas;

    private Long qtdAcoesFormacaoMoRealizadas;

    @NotNull
    private Long qtdAcoesFormacaoMmsRealizadas;

    @NotNull
    private Long qtdAcoesFormacaoCmpRealizadas;

    @NotNull
    private Long qtdAcoesFormacaoSoftwareFornecidosRealizadas;

    private Long qtdAcoesFormacaoRealizadas;

    @NotNull
    private Long qtdManuaisMoPrevistos;

    @NotNull
    private Long qtdManuaisMmsPrevistos;

    @NotNull
    private Long qtdManuaisCmpPrevistos;

    private Long qtdManuaisPrevistos;

    @NotNull
    private Long qtdAcoesManuaisMoRealizadas;

    @NotNull
    private Long qtdManuaisMmsRealizadas;

    @NotNull
    private Long qtdManuaisCmpRealizadas;

    @NotNull
    private Long qtdManuaisRealizados;

    private Situacao situacao;

    private SistemaAgua sistemaAgua;

    private Comuna comuna;

    private Provincia provincia;

    private Municipio municipio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public LocalDate getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }

    public void setDtUltimaAlteracao(LocalDate dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }

    public BigDecimal getQtdPopulacaoCobertaInfraestrutura() {
        return qtdPopulacaoCobertaInfraestrutura;
    }

    public void setQtdPopulacaoCobertaInfraestrutura(BigDecimal qtdPopulacaoCobertaInfraestrutura) {
        this.qtdPopulacaoCobertaInfraestrutura = qtdPopulacaoCobertaInfraestrutura;
    }

    public BigDecimal getQtdFontanariosChafarisesOperacionais() {
        return qtdFontanariosChafarisesOperacionais;
    }

    public void setQtdFontanariosChafarisesOperacionais(BigDecimal qtdFontanariosChafarisesOperacionais) {
        this.qtdFontanariosChafarisesOperacionais = qtdFontanariosChafarisesOperacionais;
    }

    public BigDecimal getQtdMediaHorasDistribuicaoDiaria() {
        return qtdMediaHorasDistribuicaoDiaria;
    }

    public void setQtdMediaHorasDistribuicaoDiaria(BigDecimal qtdMediaHorasDistribuicaoDiaria) {
        this.qtdMediaHorasDistribuicaoDiaria = qtdMediaHorasDistribuicaoDiaria;
    }

    public BigDecimal getQtdMediaHorasParagem() {
        return qtdMediaHorasParagem;
    }

    public void setQtdMediaHorasParagem(BigDecimal qtdMediaHorasParagem) {
        this.qtdMediaHorasParagem = qtdMediaHorasParagem;
    }

    public BigDecimal getQtdMediaHorasInterrupcaoFaltaEnergia() {
        return qtdMediaHorasInterrupcaoFaltaEnergia;
    }

    public void setQtdMediaHorasInterrupcaoFaltaEnergia(BigDecimal qtdMediaHorasInterrupcaoFaltaEnergia) {
        this.qtdMediaHorasInterrupcaoFaltaEnergia = qtdMediaHorasInterrupcaoFaltaEnergia;
    }

    public BigDecimal getQtdVolumeAguaCaptada() {
        return qtdVolumeAguaCaptada;
    }

    public void setQtdVolumeAguaCaptada(BigDecimal qtdVolumeAguaCaptada) {
        this.qtdVolumeAguaCaptada = qtdVolumeAguaCaptada;
    }

    public BigDecimal getQtdVolumeAguaTratada() {
        return qtdVolumeAguaTratada;
    }

    public void setQtdVolumeAguaTratada(BigDecimal qtdVolumeAguaTratada) {
        this.qtdVolumeAguaTratada = qtdVolumeAguaTratada;
    }

    public BigDecimal getQtdVolumeAguaDistribuida() {
        return qtdVolumeAguaDistribuida;
    }

    public void setQtdVolumeAguaDistribuida(BigDecimal qtdVolumeAguaDistribuida) {
        this.qtdVolumeAguaDistribuida = qtdVolumeAguaDistribuida;
    }

    public BigDecimal getQtdConsumoEnergia() {
        return qtdConsumoEnergia;
    }

    public void setQtdConsumoEnergia(BigDecimal qtdConsumoEnergia) {
        this.qtdConsumoEnergia = qtdConsumoEnergia;
    }

    public BigDecimal getQtdConsumoCombustivel() {
        return qtdConsumoCombustivel;
    }

    public void setQtdConsumoCombustivel(BigDecimal qtdConsumoCombustivel) {
        this.qtdConsumoCombustivel = qtdConsumoCombustivel;
    }

    public BigDecimal getQtdConsumoHipocloritroCalcio() {
        return qtdConsumoHipocloritroCalcio;
    }

    public void setQtdConsumoHipocloritroCalcio(BigDecimal qtdConsumoHipocloritroCalcio) {
        this.qtdConsumoHipocloritroCalcio = qtdConsumoHipocloritroCalcio;
    }

    public BigDecimal getQtdConsumoSulfatoAluminio() {
        return qtdConsumoSulfatoAluminio;
    }

    public void setQtdConsumoSulfatoAluminio(BigDecimal qtdConsumoSulfatoAluminio) {
        this.qtdConsumoSulfatoAluminio = qtdConsumoSulfatoAluminio;
    }

    public BigDecimal getQtdConsumoHidroxidoCalcio() {
        return qtdConsumoHidroxidoCalcio;
    }

    public void setQtdConsumoHidroxidoCalcio(BigDecimal qtdConsumoHidroxidoCalcio) {
        this.qtdConsumoHidroxidoCalcio = qtdConsumoHidroxidoCalcio;
    }

    public Long getQtdReparoCaptacaoEtas() {
        return qtdReparoCaptacaoEtas;
    }

    public void setQtdReparoCaptacaoEtas(Long qtdReparoCaptacaoEtas) {
        this.qtdReparoCaptacaoEtas = qtdReparoCaptacaoEtas;
    }

    public Long getQtdReparoAdutoras() {
        return qtdReparoAdutoras;
    }

    public void setQtdReparoAdutoras(Long qtdReparoAdutoras) {
        this.qtdReparoAdutoras = qtdReparoAdutoras;
    }

    public Long getQtdReparoRedeDistribuicao() {
        return qtdReparoRedeDistribuicao;
    }

    public void setQtdReparoRedeDistribuicao(Long qtdReparoRedeDistribuicao) {
        this.qtdReparoRedeDistribuicao = qtdReparoRedeDistribuicao;
    }

    public Long getQtdReparoRamais() {
        return qtdReparoRamais;
    }

    public void setQtdReparoRamais(Long qtdReparoRamais) {
        this.qtdReparoRamais = qtdReparoRamais;
    }

    public Long getQtdManutencaoCurativa() {
        return qtdManutencaoCurativa;
    }

    public void setQtdManutencaoCurativa(Long qtdManutencaoCurativa) {
        this.qtdManutencaoCurativa = qtdManutencaoCurativa;
    }

    public Long getQtdManutencaoPreventiva() {
        return qtdManutencaoPreventiva;
    }

    public void setQtdManutencaoPreventiva(Long qtdManutencaoPreventiva) {
        this.qtdManutencaoPreventiva = qtdManutencaoPreventiva;
    }

    public Long getQtdManutencaoVerificadoSolicitado() {
        return qtdManutencaoVerificadoSolicitado;
    }

    public void setQtdManutencaoVerificadoSolicitado(Long qtdManutencaoVerificadoSolicitado) {
        this.qtdManutencaoVerificadoSolicitado = qtdManutencaoVerificadoSolicitado;
    }

    public Long getQtdReservatorioLavado() {
        return qtdReservatorioLavado;
    }

    public void setQtdReservatorioLavado(Long qtdReservatorioLavado) {
        this.qtdReservatorioLavado = qtdReservatorioLavado;
    }

    public Long getQtdFuncionarios() {
        return qtdFuncionarios;
    }

    public void setQtdFuncionarios(Long qtdFuncionarios) {
        this.qtdFuncionarios = qtdFuncionarios;
    }

    public Long getQtdFuncionariosEfectivos() {
        return qtdFuncionariosEfectivos;
    }

    public void setQtdFuncionariosEfectivos(Long qtdFuncionariosEfectivos) {
        this.qtdFuncionariosEfectivos = qtdFuncionariosEfectivos;
    }

    public Long getQtdFuncionariosContratados() {
        return qtdFuncionariosContratados;
    }

    public void setQtdFuncionariosContratados(Long qtdFuncionariosContratados) {
        this.qtdFuncionariosContratados = qtdFuncionariosContratados;
    }

    public Long getQtdFuncionariosOutrasEntidades() {
        return qtdFuncionariosOutrasEntidades;
    }

    public void setQtdFuncionariosOutrasEntidades(Long qtdFuncionariosOutrasEntidades) {
        this.qtdFuncionariosOutrasEntidades = qtdFuncionariosOutrasEntidades;
    }

    public Long getQtdNovasLigacoesNovosContratos() {
        return qtdNovasLigacoesNovosContratos;
    }

    public void setQtdNovasLigacoesNovosContratos(Long qtdNovasLigacoesNovosContratos) {
        this.qtdNovasLigacoesNovosContratos = qtdNovasLigacoesNovosContratos;
    }

    public Long getQtdNovasLigacoesDomesticasNovosContratos() {
        return qtdNovasLigacoesDomesticasNovosContratos;
    }

    public void setQtdNovasLigacoesDomesticasNovosContratos(Long qtdNovasLigacoesDomesticasNovosContratos) {
        this.qtdNovasLigacoesDomesticasNovosContratos = qtdNovasLigacoesDomesticasNovosContratos;
    }

    public Long getQtdLigacoesIlegaisRegularizadas() {
        return qtdLigacoesIlegaisRegularizadas;
    }

    public void setQtdLigacoesIlegaisRegularizadas(Long qtdLigacoesIlegaisRegularizadas) {
        this.qtdLigacoesIlegaisRegularizadas = qtdLigacoesIlegaisRegularizadas;
    }

    public Long getQtdLigacoesFechadas() {
        return qtdLigacoesFechadas;
    }

    public void setQtdLigacoesFechadas(Long qtdLigacoesFechadas) {
        this.qtdLigacoesFechadas = qtdLigacoesFechadas;
    }

    public Long getQtdCortes() {
        return qtdCortes;
    }

    public void setQtdCortes(Long qtdCortes) {
        this.qtdCortes = qtdCortes;
    }

    public Long getQtdReligacoes() {
        return qtdReligacoes;
    }

    public void setQtdReligacoes(Long qtdReligacoes) {
        this.qtdReligacoes = qtdReligacoes;
    }

    public Long getQtdLigacoesActivas() {
        return qtdLigacoesActivas;
    }

    public void setQtdLigacoesActivas(Long qtdLigacoesActivas) {
        this.qtdLigacoesActivas = qtdLigacoesActivas;
    }

    public Long getQtdLigacoesDomesticasActivas() {
        return qtdLigacoesDomesticasActivas;
    }

    public void setQtdLigacoesDomesticasActivas(Long qtdLigacoesDomesticasActivas) {
        this.qtdLigacoesDomesticasActivas = qtdLigacoesDomesticasActivas;
    }

    public Long getQtdLigacoesFacturadasBaseLeiturasReais() {
        return qtdLigacoesFacturadasBaseLeiturasReais;
    }

    public void setQtdLigacoesFacturadasBaseLeiturasReais(Long qtdLigacoesFacturadasBaseLeiturasReais) {
        this.qtdLigacoesFacturadasBaseLeiturasReais = qtdLigacoesFacturadasBaseLeiturasReais;
    }

    public Long getQtdLigacoesFacturadasBaseEstimativasAvenca() {
        return qtdLigacoesFacturadasBaseEstimativasAvenca;
    }

    public void setQtdLigacoesFacturadasBaseEstimativasAvenca(Long qtdLigacoesFacturadasBaseEstimativasAvenca) {
        this.qtdLigacoesFacturadasBaseEstimativasAvenca = qtdLigacoesFacturadasBaseEstimativasAvenca;
    }

    public BigDecimal getQtdVolumeAguaFacturada() {
        return qtdVolumeAguaFacturada;
    }

    public void setQtdVolumeAguaFacturada(BigDecimal qtdVolumeAguaFacturada) {
        this.qtdVolumeAguaFacturada = qtdVolumeAguaFacturada;
    }

    public BigDecimal getQtdVolumeTotalFacturadaLigacoesDomesticas() {
        return qtdVolumeTotalFacturadaLigacoesDomesticas;
    }

    public void setQtdVolumeTotalFacturadaLigacoesDomesticas(BigDecimal qtdVolumeTotalFacturadaLigacoesDomesticas) {
        this.qtdVolumeTotalFacturadaLigacoesDomesticas = qtdVolumeTotalFacturadaLigacoesDomesticas;
    }

    public BigDecimal getQtdVolumeFacturadoBaseLeituraReais() {
        return qtdVolumeFacturadoBaseLeituraReais;
    }

    public void setQtdVolumeFacturadoBaseLeituraReais(BigDecimal qtdVolumeFacturadoBaseLeituraReais) {
        this.qtdVolumeFacturadoBaseLeituraReais = qtdVolumeFacturadoBaseLeituraReais;
    }

    public BigDecimal getVlrTotalFacturado() {
        return vlrTotalFacturado;
    }

    public void setVlrTotalFacturado(BigDecimal vlrTotalFacturado) {
        this.vlrTotalFacturado = vlrTotalFacturado;
    }

    public BigDecimal getVlrFacturasCanceladasNotasCreditos() {
        return vlrFacturasCanceladasNotasCreditos;
    }

    public void setVlrFacturasCanceladasNotasCreditos(BigDecimal vlrFacturasCanceladasNotasCreditos) {
        this.vlrFacturasCanceladasNotasCreditos = vlrFacturasCanceladasNotasCreditos;
    }

    public BigDecimal getVlrRealFacturado() {
        return vlrRealFacturado;
    }

    public void setVlrRealFacturado(BigDecimal vlrRealFacturado) {
        this.vlrRealFacturado = vlrRealFacturado;
    }

    public BigDecimal getVlrTotalCobrado() {
        return vlrTotalCobrado;
    }

    public void setVlrTotalCobrado(BigDecimal vlrTotalCobrado) {
        this.vlrTotalCobrado = vlrTotalCobrado;
    }

    public Long getQtdReclamacoes() {
        return qtdReclamacoes;
    }

    public void setQtdReclamacoes(Long qtdReclamacoes) {
        this.qtdReclamacoes = qtdReclamacoes;
    }

    public Long getQtdReclamacoesRespondidasMenorIgualCincoDias() {
        return qtdReclamacoesRespondidasMenorIgualCincoDias;
    }

    public void setQtdReclamacoesRespondidasMenorIgualCincoDias(Long qtdReclamacoesRespondidasMenorIgualCincoDias) {
        this.qtdReclamacoesRespondidasMenorIgualCincoDias = qtdReclamacoesRespondidasMenorIgualCincoDias;
    }

    public Long getQtdReclamacoesRespondidasMaisCincoMenosVinteDias() {
        return qtdReclamacoesRespondidasMaisCincoMenosVinteDias;
    }

    public void setQtdReclamacoesRespondidasMaisCincoMenosVinteDias(Long qtdReclamacoesRespondidasMaisCincoMenosVinteDias) {
        this.qtdReclamacoesRespondidasMaisCincoMenosVinteDias = qtdReclamacoesRespondidasMaisCincoMenosVinteDias;
    }

    public Long getQtdReclamacoesRespondidasMaiorIgualVinteDias() {
        return qtdReclamacoesRespondidasMaiorIgualVinteDias;
    }

    public void setQtdReclamacoesRespondidasMaiorIgualVinteDias(Long qtdReclamacoesRespondidasMaiorIgualVinteDias) {
        this.qtdReclamacoesRespondidasMaiorIgualVinteDias = qtdReclamacoesRespondidasMaiorIgualVinteDias;
    }

    public BigDecimal getVlrCustoPessoal() {
        return vlrCustoPessoal;
    }

    public void setVlrCustoPessoal(BigDecimal vlrCustoPessoal) {
        this.vlrCustoPessoal = vlrCustoPessoal;
    }

    public BigDecimal getVlrCustoFse() {
        return vlrCustoFse;
    }

    public void setVlrCustoFse(BigDecimal vlrCustoFse) {
        this.vlrCustoFse = vlrCustoFse;
    }

    public BigDecimal getVlrCustoEnergia() {
        return vlrCustoEnergia;
    }

    public void setVlrCustoEnergia(BigDecimal vlrCustoEnergia) {
        this.vlrCustoEnergia = vlrCustoEnergia;
    }

    public BigDecimal getVlrCustoManutencao() {
        return vlrCustoManutencao;
    }

    public void setVlrCustoManutencao(BigDecimal vlrCustoManutencao) {
        this.vlrCustoManutencao = vlrCustoManutencao;
    }

    public BigDecimal getVlrCustoReagentes() {
        return vlrCustoReagentes;
    }

    public void setVlrCustoReagentes(BigDecimal vlrCustoReagentes) {
        this.vlrCustoReagentes = vlrCustoReagentes;
    }

    public BigDecimal getVlrCustoDestinoFinalLamas() {
        return vlrCustoDestinoFinalLamas;
    }

    public void setVlrCustoDestinoFinalLamas(BigDecimal vlrCustoDestinoFinalLamas) {
        this.vlrCustoDestinoFinalLamas = vlrCustoDestinoFinalLamas;
    }

    public BigDecimal getVlrCustoOperacionaisOpex() {
        return vlrCustoOperacionaisOpex;
    }

    public void setVlrCustoOperacionaisOpex(BigDecimal vlrCustoOperacionaisOpex) {
        this.vlrCustoOperacionaisOpex = vlrCustoOperacionaisOpex;
    }

    public BigDecimal getVlrCustoAmortizaAnualInvestOpCapex() {
        return vlrCustoAmortizaAnualInvestOpCapex;
    }

    public void setVlrCustoAmortizaAnualInvestOpCapex(BigDecimal vlrCustoAmortizaAnualInvestOpCapex) {
        this.vlrCustoAmortizaAnualInvestOpCapex = vlrCustoAmortizaAnualInvestOpCapex;
    }

    public BigDecimal getVlrCustoTotaisCapexOpex() {
        return vlrCustoTotaisCapexOpex;
    }

    public void setVlrCustoTotaisCapexOpex(BigDecimal vlrCustoTotaisCapexOpex) {
        this.vlrCustoTotaisCapexOpex = vlrCustoTotaisCapexOpex;
    }

    public Long getQtdCaptacoes() {
        return qtdCaptacoes;
    }

    public void setQtdCaptacoes(Long qtdCaptacoes) {
        this.qtdCaptacoes = qtdCaptacoes;
    }

    public Long getQtdEtas() {
        return qtdEtas;
    }

    public void setQtdEtas(Long qtdEtas) {
        this.qtdEtas = qtdEtas;
    }

    public Long getQtdReservatorios() {
        return qtdReservatorios;
    }

    public void setQtdReservatorios(Long qtdReservatorios) {
        this.qtdReservatorios = qtdReservatorios;
    }

    public Long getQtdEstacoesElevatorias() {
        return qtdEstacoesElevatorias;
    }

    public void setQtdEstacoesElevatorias(Long qtdEstacoesElevatorias) {
        this.qtdEstacoesElevatorias = qtdEstacoesElevatorias;
    }

    public BigDecimal getQtdComprimentoAdutoras() {
        return qtdComprimentoAdutoras;
    }

    public void setQtdComprimentoAdutoras(BigDecimal qtdComprimentoAdutoras) {
        this.qtdComprimentoAdutoras = qtdComprimentoAdutoras;
    }

    public BigDecimal getQtdComprimentoRedes() {
        return qtdComprimentoRedes;
    }

    public void setQtdComprimentoRedes(BigDecimal qtdComprimentoRedes) {
        this.qtdComprimentoRedes = qtdComprimentoRedes;
    }

    public BigDecimal getQtdComprimentoRamais() {
        return qtdComprimentoRamais;
    }

    public void setQtdComprimentoRamais(BigDecimal qtdComprimentoRamais) {
        this.qtdComprimentoRamais = qtdComprimentoRamais;
    }

    public Long getQtdAcoesFormacaoMoPlaneadas() {
        return qtdAcoesFormacaoMoPlaneadas;
    }

    public void setQtdAcoesFormacaoMoPlaneadas(Long qtdAcoesFormacaoMoPlaneadas) {
        this.qtdAcoesFormacaoMoPlaneadas = qtdAcoesFormacaoMoPlaneadas;
    }

    public Long getQtdAcoesFormacaoMmsPlaneadas() {
        return qtdAcoesFormacaoMmsPlaneadas;
    }

    public void setQtdAcoesFormacaoMmsPlaneadas(Long qtdAcoesFormacaoMmsPlaneadas) {
        this.qtdAcoesFormacaoMmsPlaneadas = qtdAcoesFormacaoMmsPlaneadas;
    }

    public Long getQtdAcoesFormacaoCmpPlaneadas() {
        return qtdAcoesFormacaoCmpPlaneadas;
    }

    public void setQtdAcoesFormacaoCmpPlaneadas(Long qtdAcoesFormacaoCmpPlaneadas) {
        this.qtdAcoesFormacaoCmpPlaneadas = qtdAcoesFormacaoCmpPlaneadas;
    }

    public Long getQtdAcoesFormacaoSoftwareFornecidosPlanejadas() {
        return qtdAcoesFormacaoSoftwareFornecidosPlanejadas;
    }

    public void setQtdAcoesFormacaoSoftwareFornecidosPlanejadas(Long qtdAcoesFormacaoSoftwareFornecidosPlanejadas) {
        this.qtdAcoesFormacaoSoftwareFornecidosPlanejadas = qtdAcoesFormacaoSoftwareFornecidosPlanejadas;
    }

    public Long getQtdAcoesFormacaoMoRealizadas() {
        return qtdAcoesFormacaoMoRealizadas;
    }

    public void setQtdAcoesFormacaoMoRealizadas(Long qtdAcoesFormacaoMoRealizadas) {
        this.qtdAcoesFormacaoMoRealizadas = qtdAcoesFormacaoMoRealizadas;
    }

    public Long getQtdAcoesFormacaoMmsRealizadas() {
        return qtdAcoesFormacaoMmsRealizadas;
    }

    public void setQtdAcoesFormacaoMmsRealizadas(Long qtdAcoesFormacaoMmsRealizadas) {
        this.qtdAcoesFormacaoMmsRealizadas = qtdAcoesFormacaoMmsRealizadas;
    }

    public Long getQtdAcoesFormacaoCmpRealizadas() {
        return qtdAcoesFormacaoCmpRealizadas;
    }

    public void setQtdAcoesFormacaoCmpRealizadas(Long qtdAcoesFormacaoCmpRealizadas) {
        this.qtdAcoesFormacaoCmpRealizadas = qtdAcoesFormacaoCmpRealizadas;
    }

    public Long getQtdAcoesFormacaoSoftwareFornecidosRealizadas() {
        return qtdAcoesFormacaoSoftwareFornecidosRealizadas;
    }

    public void setQtdAcoesFormacaoSoftwareFornecidosRealizadas(Long qtdAcoesFormacaoSoftwareFornecidosRealizadas) {
        this.qtdAcoesFormacaoSoftwareFornecidosRealizadas = qtdAcoesFormacaoSoftwareFornecidosRealizadas;
    }

    public Long getQtdAcoesFormacaoRealizadas() {
        return qtdAcoesFormacaoRealizadas;
    }

    public void setQtdAcoesFormacaoRealizadas(Long qtdAcoesFormacaoRealizadas) {
        this.qtdAcoesFormacaoRealizadas = qtdAcoesFormacaoRealizadas;
    }

    public Long getQtdManuaisMoPrevistos() {
        return qtdManuaisMoPrevistos;
    }

    public void setQtdManuaisMoPrevistos(Long qtdManuaisMoPrevistos) {
        this.qtdManuaisMoPrevistos = qtdManuaisMoPrevistos;
    }

    public Long getQtdManuaisMmsPrevistos() {
        return qtdManuaisMmsPrevistos;
    }

    public void setQtdManuaisMmsPrevistos(Long qtdManuaisMmsPrevistos) {
        this.qtdManuaisMmsPrevistos = qtdManuaisMmsPrevistos;
    }

    public Long getQtdManuaisCmpPrevistos() {
        return qtdManuaisCmpPrevistos;
    }

    public void setQtdManuaisCmpPrevistos(Long qtdManuaisCmpPrevistos) {
        this.qtdManuaisCmpPrevistos = qtdManuaisCmpPrevistos;
    }

    public Long getQtdManuaisPrevistos() {
        return qtdManuaisPrevistos;
    }

    public void setQtdManuaisPrevistos(Long qtdManuaisPrevistos) {
        this.qtdManuaisPrevistos = qtdManuaisPrevistos;
    }

    public Long getQtdAcoesManuaisMoRealizadas() {
        return qtdAcoesManuaisMoRealizadas;
    }

    public void setQtdAcoesManuaisMoRealizadas(Long qtdAcoesManuaisMoRealizadas) {
        this.qtdAcoesManuaisMoRealizadas = qtdAcoesManuaisMoRealizadas;
    }

    public Long getQtdManuaisMmsRealizadas() {
        return qtdManuaisMmsRealizadas;
    }

    public void setQtdManuaisMmsRealizadas(Long qtdManuaisMmsRealizadas) {
        this.qtdManuaisMmsRealizadas = qtdManuaisMmsRealizadas;
    }

    public Long getQtdManuaisCmpRealizadas() {
        return qtdManuaisCmpRealizadas;
    }

    public void setQtdManuaisCmpRealizadas(Long qtdManuaisCmpRealizadas) {
        this.qtdManuaisCmpRealizadas = qtdManuaisCmpRealizadas;
    }

    public Long getQtdManuaisRealizados() {
        return qtdManuaisRealizados;
    }

    public void setQtdManuaisRealizados(Long qtdManuaisRealizados) {
        this.qtdManuaisRealizados = qtdManuaisRealizados;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacaoId) {
        this.situacao = situacaoId;
    }

    public SistemaAgua getSistemaAgua() {
        return sistemaAgua;
    }

    public void setSistemaAgua(SistemaAgua sistemaAguaId) {
        this.sistemaAgua = sistemaAguaId;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comunaId) {
        this.comuna = comunaId;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IndicadorProducaoDTO indicadorProducaoDTO = (IndicadorProducaoDTO) o;
        if(indicadorProducaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), indicadorProducaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "IndicadorProducaoDTO{" +
            "id=" + getId() +
            ", idUsuario=" + getIdUsuario() +
            ", dtLancamento='" + getDtLancamento() + "'" +
            ", dtUltimaAlteracao='" + getDtUltimaAlteracao() + "'" +
            ", qtdPopulacaoCobertaInfraestrutura=" + getQtdPopulacaoCobertaInfraestrutura() +
            ", qtdFontanariosChafarisesOperacionais=" + getQtdFontanariosChafarisesOperacionais() +
            ", qtdMediaHorasDistribuicaoDiaria=" + getQtdMediaHorasDistribuicaoDiaria() +
            ", qtdMediaHorasParagem=" + getQtdMediaHorasParagem() +
            ", qtdMediaHorasInterrupcaoFaltaEnergia=" + getQtdMediaHorasInterrupcaoFaltaEnergia() +
            ", qtdVolumeAguaCaptada=" + getQtdVolumeAguaCaptada() +
            ", qtdVolumeAguaTratada=" + getQtdVolumeAguaTratada() +
            ", qtdVolumeAguaDistribuida=" + getQtdVolumeAguaDistribuida() +
            ", qtdConsumoEnergia=" + getQtdConsumoEnergia() +
            ", qtdConsumoCombustivel=" + getQtdConsumoCombustivel() +
            ", qtdConsumoHipocloritroCalcio=" + getQtdConsumoHipocloritroCalcio() +
            ", qtdConsumoSulfatoAluminio=" + getQtdConsumoSulfatoAluminio() +
            ", qtdConsumoHidroxidoCalcio=" + getQtdConsumoHidroxidoCalcio() +
            ", qtdReparoCaptacaoEtas=" + getQtdReparoCaptacaoEtas() +
            ", qtdReparoAdutoras=" + getQtdReparoAdutoras() +
            ", qtdReparoRedeDistribuicao=" + getQtdReparoRedeDistribuicao() +
            ", qtdReparoRamais=" + getQtdReparoRamais() +
            ", qtdManutencaoCurativa=" + getQtdManutencaoCurativa() +
            ", qtdManutencaoPreventiva=" + getQtdManutencaoPreventiva() +
            ", qtdManutencaoVerificadoSolicitado=" + getQtdManutencaoVerificadoSolicitado() +
            ", qtdReservatorioLavado=" + getQtdReservatorioLavado() +
            ", qtdFuncionarios=" + getQtdFuncionarios() +
            ", qtdFuncionariosEfectivos=" + getQtdFuncionariosEfectivos() +
            ", qtdFuncionariosContratados=" + getQtdFuncionariosContratados() +
            ", qtdFuncionariosOutrasEntidades=" + getQtdFuncionariosOutrasEntidades() +
            ", qtdNovasLigacoesNovosContratos=" + getQtdNovasLigacoesNovosContratos() +
            ", qtdNovasLigacoesDomesticasNovosContratos=" + getQtdNovasLigacoesDomesticasNovosContratos() +
            ", qtdLigacoesIlegaisRegularizadas=" + getQtdLigacoesIlegaisRegularizadas() +
            ", qtdLigacoesFechadas=" + getQtdLigacoesFechadas() +
            ", qtdCortes=" + getQtdCortes() +
            ", qtdReligacoes=" + getQtdReligacoes() +
            ", qtdLigacoesActivas=" + getQtdLigacoesActivas() +
            ", qtdLigacoesDomesticasActivas=" + getQtdLigacoesDomesticasActivas() +
            ", qtdLigacoesFacturadasBaseLeiturasReais=" + getQtdLigacoesFacturadasBaseLeiturasReais() +
            ", qtdLigacoesFacturadasBaseEstimativasAvenca=" + getQtdLigacoesFacturadasBaseEstimativasAvenca() +
            ", qtdVolumeAguaFacturada=" + getQtdVolumeAguaFacturada() +
            ", qtdVolumeTotalFacturadaLigacoesDomesticas=" + getQtdVolumeTotalFacturadaLigacoesDomesticas() +
            ", qtdVolumeFacturadoBaseLeituraReais=" + getQtdVolumeFacturadoBaseLeituraReais() +
            ", vlrTotalFacturado=" + getVlrTotalFacturado() +
            ", vlrFacturasCanceladasNotasCreditos=" + getVlrFacturasCanceladasNotasCreditos() +
            ", vlrRealFacturado=" + getVlrRealFacturado() +
            ", vlrTotalCobrado=" + getVlrTotalCobrado() +
            ", qtdReclamacoes=" + getQtdReclamacoes() +
            ", qtdReclamacoesRespondidasMenorIgualCincoDias=" + getQtdReclamacoesRespondidasMenorIgualCincoDias() +
            ", qtdReclamacoesRespondidasMaisCincoMenosVinteDias=" + getQtdReclamacoesRespondidasMaisCincoMenosVinteDias() +
            ", qtdReclamacoesRespondidasMaiorIgualVinteDias=" + getQtdReclamacoesRespondidasMaiorIgualVinteDias() +
            ", vlrCustoPessoal=" + getVlrCustoPessoal() +
            ", vlrCustoFse=" + getVlrCustoFse() +
            ", vlrCustoEnergia=" + getVlrCustoEnergia() +
            ", vlrCustoManutencao=" + getVlrCustoManutencao() +
            ", vlrCustoReagentes=" + getVlrCustoReagentes() +
            ", vlrCustoDestinoFinalLamas=" + getVlrCustoDestinoFinalLamas() +
            ", vlrCustoOperacionaisOpex=" + getVlrCustoOperacionaisOpex() +
            ", vlrCustoAmortizaAnualInvestOpCapex=" + getVlrCustoAmortizaAnualInvestOpCapex() +
            ", vlrCustoTotaisCapexOpex=" + getVlrCustoTotaisCapexOpex() +
            ", qtdCaptacoes=" + getQtdCaptacoes() +
            ", qtdEtas=" + getQtdEtas() +
            ", qtdReservatorios=" + getQtdReservatorios() +
            ", qtdEstacoesElevatorias=" + getQtdEstacoesElevatorias() +
            ", qtdComprimentoAdutoras=" + getQtdComprimentoAdutoras() +
            ", qtdComprimentoRedes=" + getQtdComprimentoRedes() +
            ", qtdComprimentoRamais=" + getQtdComprimentoRamais() +
            ", qtdAcoesFormacaoMoPlaneadas=" + getQtdAcoesFormacaoMoPlaneadas() +
            ", qtdAcoesFormacaoMmsPlaneadas=" + getQtdAcoesFormacaoMmsPlaneadas() +
            ", qtdAcoesFormacaoCmpPlaneadas=" + getQtdAcoesFormacaoCmpPlaneadas() +
            ", qtdAcoesFormacaoSoftwareFornecidosPlanejadas=" + getQtdAcoesFormacaoSoftwareFornecidosPlanejadas() +
            ", qtdAcoesFormacaoMoRealizadas=" + getQtdAcoesFormacaoMoRealizadas() +
            ", qtdAcoesFormacaoMmsRealizadas=" + getQtdAcoesFormacaoMmsRealizadas() +
            ", qtdAcoesFormacaoCmpRealizadas=" + getQtdAcoesFormacaoCmpRealizadas() +
            ", qtdAcoesFormacaoSoftwareFornecidosRealizadas=" + getQtdAcoesFormacaoSoftwareFornecidosRealizadas() +
            ", qtdAcoesFormacaoRealizadas=" + getQtdAcoesFormacaoRealizadas() +
            ", qtdManuaisMoPrevistos=" + getQtdManuaisMoPrevistos() +
            ", qtdManuaisMmsPrevistos=" + getQtdManuaisMmsPrevistos() +
            ", qtdManuaisCmpPrevistos=" + getQtdManuaisCmpPrevistos() +
            ", qtdManuaisPrevistos=" + getQtdManuaisPrevistos() +
            ", qtdAcoesManuaisMoRealizadas=" + getQtdAcoesManuaisMoRealizadas() +
            ", qtdManuaisMmsRealizadas=" + getQtdManuaisMmsRealizadas() +
            ", qtdManuaisCmpRealizadas=" + getQtdManuaisCmpRealizadas() +
            ", qtdManuaisRealizados=" + getQtdManuaisRealizados() +
            "}";
    }
}
