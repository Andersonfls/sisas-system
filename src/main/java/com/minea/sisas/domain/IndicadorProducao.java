package com.minea.sisas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A IndicadorProducao.
 */
@Entity
@Table(name = "indicador_producao")
public class IndicadorProducao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_indicador_producao", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @NotNull
    @Column(name = "dt_lancamento", nullable = false)
    private LocalDate dtLancamento;

    @Column(name = "dt_ultima_alteracao")
    private LocalDate dtUltimaAlteracao;

    @NotNull
    @Column(name = "qtd_populacao_coberta_infraestrutura", precision=10, scale=2, nullable = false)
    private BigDecimal qtdPopulacaoCobertaInfraestrutura;

    @NotNull
    @Column(name = "qtd_fontanarios_chafarises_operacionais", precision=10, scale=2, nullable = false)
    private BigDecimal qtdFontanariosChafarisesOperacionais;

    @NotNull
    @Column(name = "qtd_media_horas_distribuicao_diaria", precision=10, scale=2, nullable = false)
    private BigDecimal qtdMediaHorasDistribuicaoDiaria;

    @NotNull
    @Column(name = "qtd_media_horas_paragem", precision=10, scale=2, nullable = false)
    private BigDecimal qtdMediaHorasParagem;

    @NotNull
    @Column(name = "qtd_media_horas_interrupcao_falta_energia", precision=10, scale=2, nullable = false)
    private BigDecimal qtdMediaHorasInterrupcaoFaltaEnergia;

    @NotNull
    @Column(name = "qtd_volume_agua_captada", precision=10, scale=2, nullable = false)
    private BigDecimal qtdVolumeAguaCaptada;

    @NotNull
    @Column(name = "qtd_volume_agua_tratada", precision=10, scale=2, nullable = false)
    private BigDecimal qtdVolumeAguaTratada;

    @NotNull
    @Column(name = "qtd_volume_agua_distribuida", precision=10, scale=2, nullable = false)
    private BigDecimal qtdVolumeAguaDistribuida;

    @NotNull
    @Column(name = "qtd_consumo_energia", precision=10, scale=2, nullable = false)
    private BigDecimal qtdConsumoEnergia;

    @NotNull
    @Column(name = "qtd_consumo_combustivel", precision=10, scale=2, nullable = false)
    private BigDecimal qtdConsumoCombustivel;

    @NotNull
    @Column(name = "qtd_consumo_hipocloritro_calcio", precision=10, scale=2, nullable = false)
    private BigDecimal qtdConsumoHipocloritroCalcio;

    @NotNull
    @Column(name = "qtd_consumo_sulfato_aluminio", precision=10, scale=2, nullable = false)
    private BigDecimal qtdConsumoSulfatoAluminio;

    @NotNull
    @Column(name = "qtd_consumo_hidroxido_calcio", precision=10, scale=2, nullable = false)
    private BigDecimal qtdConsumoHidroxidoCalcio;

    @NotNull
    @Column(name = "qtd_reparo_captacao_etas", nullable = false)
    private Long qtdReparoCaptacaoEtas;

    @NotNull
    @Column(name = "qtd_reparo_adutoras", nullable = false)
    private Long qtdReparoAdutoras;

    @NotNull
    @Column(name = "qtd_reparo_rede_distribuicao", nullable = false)
    private Long qtdReparoRedeDistribuicao;

    @NotNull
    @Column(name = "qtd_reparo_ramais", nullable = false)
    private Long qtdReparoRamais;

    @NotNull
    @Column(name = "qtd_manutencao_curativa", nullable = false)
    private Long qtdManutencaoCurativa;

    @NotNull
    @Column(name = "qtd_manutencao_preventiva", nullable = false)
    private Long qtdManutencaoPreventiva;

    @NotNull
    @Column(name = "qtd_manutencao_verificado_solicitado", nullable = false)
    private Long qtdManutencaoVerificadoSolicitado;

    @NotNull
    @Column(name = "qtd_reservatorio_lavado", nullable = false)
    private Long qtdReservatorioLavado;

    @NotNull
    @Column(name = "qtd_funcionarios", nullable = false)
    private Long qtdFuncionarios;

    @NotNull
    @Column(name = "qtd_funcionarios_efectivos", nullable = false)
    private Long qtdFuncionariosEfectivos;

    @NotNull
    @Column(name = "qtd_funcionarios_contratados", nullable = false)
    private Long qtdFuncionariosContratados;

    @NotNull
    @Column(name = "qtd_funcionarios_outras_entidades", nullable = false)
    private Long qtdFuncionariosOutrasEntidades;

    @NotNull
    @Column(name = "qtd_novas_ligacoes_novos_contratos", nullable = false)
    private Long qtdNovasLigacoesNovosContratos;

    @NotNull
    @Column(name = "qtd_novas_ligacoes_domesticas_novos_contratos", nullable = false)
    private Long qtdNovasLigacoesDomesticasNovosContratos;

    @NotNull
    @Column(name = "qtd_ligacoes_ilegais_regularizadas", nullable = false)
    private Long qtdLigacoesIlegaisRegularizadas;

    @NotNull
    @Column(name = "qtd_ligacoes_fechadas", nullable = false)
    private Long qtdLigacoesFechadas;

    @NotNull
    @Column(name = "qtd_cortes", nullable = false)
    private Long qtdCortes;

    @NotNull
    @Column(name = "qtd_religacoes", nullable = false)
    private Long qtdReligacoes;

    @NotNull
    @Column(name = "qtd_ligacoes_activas", nullable = false)
    private Long qtdLigacoesActivas;

    @NotNull
    @Column(name = "qtd_ligacoes_domesticas_activas", nullable = false)
    private Long qtdLigacoesDomesticasActivas;

    @NotNull
    @Column(name = "qtd_ligacoes_facturadas_base_leituras_reais", nullable = false)
    private Long qtdLigacoesFacturadasBaseLeiturasReais;

    @NotNull
    @Column(name = "qtd_ligacoes_facturadas_base_estimativas_avenca", nullable = false)
    private Long qtdLigacoesFacturadasBaseEstimativasAvenca;

    @NotNull
    @Column(name = "qtd_volume_agua_facturada", precision=10, scale=2, nullable = false)
    private BigDecimal qtdVolumeAguaFacturada;

    @NotNull
    @Column(name = "qtd_volume_total_facturada_ligacoes_domesticas", precision=10, scale=2, nullable = false)
    private BigDecimal qtdVolumeTotalFacturadaLigacoesDomesticas;

    @NotNull
    @Column(name = "qtd_volume_facturado_base_leitura_reais", precision=10, scale=2, nullable = false)
    private BigDecimal qtdVolumeFacturadoBaseLeituraReais;

    @NotNull
    @Column(name = "vlr_total_facturado", precision=10, scale=2, nullable = false)
    private BigDecimal vlrTotalFacturado;

    @NotNull
    @Column(name = "vlr_facturas_canceladas_notas_creditos", precision=10, scale=2, nullable = false)
    private BigDecimal vlrFacturasCanceladasNotasCreditos;

    @NotNull
    @Column(name = "vlr_real_facturado", precision=10, scale=2, nullable = false)
    private BigDecimal vlrRealFacturado;

    @NotNull
    @Column(name = "vlr_total_cobrado", precision=10, scale=2, nullable = false)
    private BigDecimal vlrTotalCobrado;

    @NotNull
    @Column(name = "qtd_reclamacoes", nullable = false)
    private Long qtdReclamacoes;

    @Column(name = "qtd_reclamacoes_respondidas_menor_igual_cinco_dias")
    private Long qtdReclamacoesRespondidasMenorIgualCincoDias;

    @Column(name = "qtd_reclamacoes_respondidas_mais_cinco_menos_vinte_dias")
    private Long qtdReclamacoesRespondidasMaisCincoMenosVinteDias;

    @Column(name = "qtd_reclamacoes_respondidas_maior_igual_vinte_dias")
    private Long qtdReclamacoesRespondidasMaiorIgualVinteDias;

    @NotNull
    @Column(name = "vlr_custo_pessoal", precision=10, scale=2, nullable = false)
    private BigDecimal vlrCustoPessoal;

    @NotNull
    @Column(name = "vlr_custo_fse", precision=10, scale=2, nullable = false)
    private BigDecimal vlrCustoFse;

    @NotNull
    @Column(name = "vlr_custo_energia", precision=10, scale=2, nullable = false)
    private BigDecimal vlrCustoEnergia;

    @NotNull
    @Column(name = "vlr_custo_manutencao", precision=10, scale=2, nullable = false)
    private BigDecimal vlrCustoManutencao;

    @NotNull
    @Column(name = "vlr_custo_reagentes", precision=10, scale=2, nullable = false)
    private BigDecimal vlrCustoReagentes;

    @NotNull
    @Column(name = "vlr_custo_destino_final_lamas", precision=10, scale=2, nullable = false)
    private BigDecimal vlrCustoDestinoFinalLamas;

    @NotNull
    @Column(name = "vlr_custo_operacionais_opex", precision=10, scale=2, nullable = false)
    private BigDecimal vlrCustoOperacionaisOpex;

    @NotNull
    @Column(name = "vlr_custo_amortiza_anual_invest_op_capex", precision=10, scale=2, nullable = false)
    private BigDecimal vlrCustoAmortizaAnualInvestOpCapex;

    @NotNull
    @Column(name = "vlr_custo_totais_capex_opex", precision=10, scale=2)
    private BigDecimal vlrCustoTotaisCapexOpex;

    @NotNull
    @Column(name = "qtd_captacoes", nullable = false)
    private Long qtdCaptacoes;

    @NotNull
    @Column(name = "qtd_etas", nullable = false)
    private Long qtdEtas;

    @NotNull
    @Column(name = "qtd_reservatorios", nullable = false)
    private Long qtdReservatorios;

    @NotNull
    @Column(name = "qtd_estacoes_elevatorias", nullable = false)
    private Long qtdEstacoesElevatorias;

    @NotNull
    @Column(name = "qtd_comprimento_adutoras", precision=10, scale=2, nullable = false)
    private BigDecimal qtdComprimentoAdutoras;

    @NotNull
    @Column(name = "qtd_comprimento_redes", precision=10, scale=2, nullable = false)
    private BigDecimal qtdComprimentoRedes;

    @NotNull
    @Column(name = "qtd_comprimento_ramais", precision=10, scale=2, nullable = false)
    private BigDecimal qtdComprimentoRamais;

    @NotNull
    @Column(name = "qtd_acoes_formacao_mo_planeadas", nullable = false)
    private Long qtdAcoesFormacaoMoPlaneadas;

    @NotNull
    @Column(name = "qtd_acoes_formacao_mms_planeadas", nullable = false)
    private Long qtdAcoesFormacaoMmsPlaneadas;

    @NotNull
    @Column(name = "qtd_acoes_formacao_cmp_planeadas", nullable = false)
    private Long qtdAcoesFormacaoCmpPlaneadas;

    @NotNull
    @Column(name = "qtd_acoes_formacao_software_fornecidos_planejadas", nullable = false)
    private Long qtdAcoesFormacaoSoftwareFornecidosPlanejadas;

    @NotNull
    @Column(name = "qtd_acoes_formacao_mo_realizadas", nullable = false)
    private Long qtdAcoesFormacaoMoRealizadas;

    @NotNull
    @Column(name = "qtd_acoes_formacao_mms_realizadas", nullable = false)
    private Long qtdAcoesFormacaoMmsRealizadas;

    @NotNull
    @Column(name = "qtd_acoes_formacao_cmp_realizadas", nullable = false)
    private Long qtdAcoesFormacaoCmpRealizadas;

    @NotNull
    @Column(name = "qtd_acoes_formacao_software_fornecidos_realizadas", nullable = false)
    private Long qtdAcoesFormacaoSoftwareFornecidosRealizadas;

    @NotNull
    @Column(name = "qtd_acoes_formacao_realizadas")
    private Long qtdAcoesFormacaoRealizadas;

    @NotNull
    @Column(name = "qtd_manuais_mo_previstos", nullable = false)
    private Long qtdManuaisMoPrevistos;

    @NotNull
    @Column(name = "qtd_manuais_mms_previstos", nullable = false)
    private Long qtdManuaisMmsPrevistos;

    @NotNull
    @Column(name = "qtd_manuais_cmp_previstos", nullable = false)
    private Long qtdManuaisCmpPrevistos;

    @NotNull
    @Column(name = "qtd_manuais_previstos")
    private Long qtdManuaisPrevistos;

    @NotNull
    @Column(name = "qtd_acoes_manuais_mo_realizadas", nullable = false)
    private Long qtdAcoesManuaisMoRealizadas;

    @NotNull
    @Column(name = "qtd_manuais_mms_realizadas", nullable = false)
    private Long qtdManuaisMmsRealizadas;

    @NotNull
    @Column(name = "qtd_manuais_cmp_realizadas", nullable = false)
    private Long qtdManuaisCmpRealizadas;

    @NotNull
    @Column(name = "qtd_manuais_realizados", nullable = false)
    private Long qtdManuaisRealizados;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_situacao")
    private Situacao situacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_sistema_agua")
    private SistemaAgua sistemaAgua;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_comuna")
    private Comuna comuna;

    @ManyToOne
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;

    @ManyToOne
    @JoinColumn(name = "id_municipio")
    private Municipio municipio;

    @Column(name = "status")
    private Boolean status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public IndicadorProducao idUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public IndicadorProducao dtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
        return this;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public LocalDate getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }

    public IndicadorProducao dtUltimaAlteracao(LocalDate dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
        return this;
    }

    public void setDtUltimaAlteracao(LocalDate dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }

    public BigDecimal getQtdPopulacaoCobertaInfraestrutura() {
        return qtdPopulacaoCobertaInfraestrutura;
    }

    public IndicadorProducao qtdPopulacaoCobertaInfraestrutura(BigDecimal qtdPopulacaoCobertaInfraestrutura) {
        this.qtdPopulacaoCobertaInfraestrutura = qtdPopulacaoCobertaInfraestrutura;
        return this;
    }

    public void setQtdPopulacaoCobertaInfraestrutura(BigDecimal qtdPopulacaoCobertaInfraestrutura) {
        this.qtdPopulacaoCobertaInfraestrutura = qtdPopulacaoCobertaInfraestrutura;
    }

    public BigDecimal getQtdFontanariosChafarisesOperacionais() {
        return qtdFontanariosChafarisesOperacionais;
    }

    public IndicadorProducao qtdFontanariosChafarisesOperacionais(BigDecimal qtdFontanariosChafarisesOperacionais) {
        this.qtdFontanariosChafarisesOperacionais = qtdFontanariosChafarisesOperacionais;
        return this;
    }

    public void setQtdFontanariosChafarisesOperacionais(BigDecimal qtdFontanariosChafarisesOperacionais) {
        this.qtdFontanariosChafarisesOperacionais = qtdFontanariosChafarisesOperacionais;
    }

    public BigDecimal getQtdMediaHorasDistribuicaoDiaria() {
        return qtdMediaHorasDistribuicaoDiaria;
    }

    public IndicadorProducao qtdMediaHorasDistribuicaoDiaria(BigDecimal qtdMediaHorasDistribuicaoDiaria) {
        this.qtdMediaHorasDistribuicaoDiaria = qtdMediaHorasDistribuicaoDiaria;
        return this;
    }

    public void setQtdMediaHorasDistribuicaoDiaria(BigDecimal qtdMediaHorasDistribuicaoDiaria) {
        this.qtdMediaHorasDistribuicaoDiaria = qtdMediaHorasDistribuicaoDiaria;
    }

    public BigDecimal getQtdMediaHorasParagem() {
        return qtdMediaHorasParagem;
    }

    public IndicadorProducao qtdMediaHorasParagem(BigDecimal qtdMediaHorasParagem) {
        this.qtdMediaHorasParagem = qtdMediaHorasParagem;
        return this;
    }

    public void setQtdMediaHorasParagem(BigDecimal qtdMediaHorasParagem) {
        this.qtdMediaHorasParagem = qtdMediaHorasParagem;
    }

    public BigDecimal getQtdMediaHorasInterrupcaoFaltaEnergia() {
        return qtdMediaHorasInterrupcaoFaltaEnergia;
    }

    public IndicadorProducao qtdMediaHorasInterrupcaoFaltaEnergia(BigDecimal qtdMediaHorasInterrupcaoFaltaEnergia) {
        this.qtdMediaHorasInterrupcaoFaltaEnergia = qtdMediaHorasInterrupcaoFaltaEnergia;
        return this;
    }

    public void setQtdMediaHorasInterrupcaoFaltaEnergia(BigDecimal qtdMediaHorasInterrupcaoFaltaEnergia) {
        this.qtdMediaHorasInterrupcaoFaltaEnergia = qtdMediaHorasInterrupcaoFaltaEnergia;
    }

    public BigDecimal getQtdVolumeAguaCaptada() {
        return qtdVolumeAguaCaptada;
    }

    public IndicadorProducao qtdVolumeAguaCaptada(BigDecimal qtdVolumeAguaCaptada) {
        this.qtdVolumeAguaCaptada = qtdVolumeAguaCaptada;
        return this;
    }

    public void setQtdVolumeAguaCaptada(BigDecimal qtdVolumeAguaCaptada) {
        this.qtdVolumeAguaCaptada = qtdVolumeAguaCaptada;
    }

    public BigDecimal getQtdVolumeAguaTratada() {
        return qtdVolumeAguaTratada;
    }

    public IndicadorProducao qtdVolumeAguaTratada(BigDecimal qtdVolumeAguaTratada) {
        this.qtdVolumeAguaTratada = qtdVolumeAguaTratada;
        return this;
    }

    public void setQtdVolumeAguaTratada(BigDecimal qtdVolumeAguaTratada) {
        this.qtdVolumeAguaTratada = qtdVolumeAguaTratada;
    }

    public BigDecimal getQtdVolumeAguaDistribuida() {
        return qtdVolumeAguaDistribuida;
    }

    public IndicadorProducao qtdVolumeAguaDistribuida(BigDecimal qtdVolumeAguaDistribuida) {
        this.qtdVolumeAguaDistribuida = qtdVolumeAguaDistribuida;
        return this;
    }

    public void setQtdVolumeAguaDistribuida(BigDecimal qtdVolumeAguaDistribuida) {
        this.qtdVolumeAguaDistribuida = qtdVolumeAguaDistribuida;
    }

    public BigDecimal getQtdConsumoEnergia() {
        return qtdConsumoEnergia;
    }

    public IndicadorProducao qtdConsumoEnergia(BigDecimal qtdConsumoEnergia) {
        this.qtdConsumoEnergia = qtdConsumoEnergia;
        return this;
    }

    public void setQtdConsumoEnergia(BigDecimal qtdConsumoEnergia) {
        this.qtdConsumoEnergia = qtdConsumoEnergia;
    }

    public BigDecimal getQtdConsumoCombustivel() {
        return qtdConsumoCombustivel;
    }

    public IndicadorProducao qtdConsumoCombustivel(BigDecimal qtdConsumoCombustivel) {
        this.qtdConsumoCombustivel = qtdConsumoCombustivel;
        return this;
    }

    public void setQtdConsumoCombustivel(BigDecimal qtdConsumoCombustivel) {
        this.qtdConsumoCombustivel = qtdConsumoCombustivel;
    }

    public BigDecimal getQtdConsumoHipocloritroCalcio() {
        return qtdConsumoHipocloritroCalcio;
    }

    public IndicadorProducao qtdConsumoHipocloritroCalcio(BigDecimal qtdConsumoHipocloritroCalcio) {
        this.qtdConsumoHipocloritroCalcio = qtdConsumoHipocloritroCalcio;
        return this;
    }

    public void setQtdConsumoHipocloritroCalcio(BigDecimal qtdConsumoHipocloritroCalcio) {
        this.qtdConsumoHipocloritroCalcio = qtdConsumoHipocloritroCalcio;
    }

    public BigDecimal getQtdConsumoSulfatoAluminio() {
        return qtdConsumoSulfatoAluminio;
    }

    public IndicadorProducao qtdConsumoSulfatoAluminio(BigDecimal qtdConsumoSulfatoAluminio) {
        this.qtdConsumoSulfatoAluminio = qtdConsumoSulfatoAluminio;
        return this;
    }

    public void setQtdConsumoSulfatoAluminio(BigDecimal qtdConsumoSulfatoAluminio) {
        this.qtdConsumoSulfatoAluminio = qtdConsumoSulfatoAluminio;
    }

    public BigDecimal getQtdConsumoHidroxidoCalcio() {
        return qtdConsumoHidroxidoCalcio;
    }

    public IndicadorProducao qtdConsumoHidroxidoCalcio(BigDecimal qtdConsumoHidroxidoCalcio) {
        this.qtdConsumoHidroxidoCalcio = qtdConsumoHidroxidoCalcio;
        return this;
    }

    public void setQtdConsumoHidroxidoCalcio(BigDecimal qtdConsumoHidroxidoCalcio) {
        this.qtdConsumoHidroxidoCalcio = qtdConsumoHidroxidoCalcio;
    }

    public Long getQtdReparoCaptacaoEtas() {
        return qtdReparoCaptacaoEtas;
    }

    public IndicadorProducao qtdReparoCaptacaoEtas(Long qtdReparoCaptacaoEtas) {
        this.qtdReparoCaptacaoEtas = qtdReparoCaptacaoEtas;
        return this;
    }

    public void setQtdReparoCaptacaoEtas(Long qtdReparoCaptacaoEtas) {
        this.qtdReparoCaptacaoEtas = qtdReparoCaptacaoEtas;
    }

    public Long getQtdReparoAdutoras() {
        return qtdReparoAdutoras;
    }

    public IndicadorProducao qtdReparoAdutoras(Long qtdReparoAdutoras) {
        this.qtdReparoAdutoras = qtdReparoAdutoras;
        return this;
    }

    public void setQtdReparoAdutoras(Long qtdReparoAdutoras) {
        this.qtdReparoAdutoras = qtdReparoAdutoras;
    }

    public Long getQtdReparoRedeDistribuicao() {
        return qtdReparoRedeDistribuicao;
    }

    public IndicadorProducao qtdReparoRedeDistribuicao(Long qtdReparoRedeDistribuicao) {
        this.qtdReparoRedeDistribuicao = qtdReparoRedeDistribuicao;
        return this;
    }

    public void setQtdReparoRedeDistribuicao(Long qtdReparoRedeDistribuicao) {
        this.qtdReparoRedeDistribuicao = qtdReparoRedeDistribuicao;
    }

    public Long getQtdReparoRamais() {
        return qtdReparoRamais;
    }

    public IndicadorProducao qtdReparoRamais(Long qtdReparoRamais) {
        this.qtdReparoRamais = qtdReparoRamais;
        return this;
    }

    public void setQtdReparoRamais(Long qtdReparoRamais) {
        this.qtdReparoRamais = qtdReparoRamais;
    }

    public Long getQtdManutencaoCurativa() {
        return qtdManutencaoCurativa;
    }

    public IndicadorProducao qtdManutencaoCurativa(Long qtdManutencaoCurativa) {
        this.qtdManutencaoCurativa = qtdManutencaoCurativa;
        return this;
    }

    public void setQtdManutencaoCurativa(Long qtdManutencaoCurativa) {
        this.qtdManutencaoCurativa = qtdManutencaoCurativa;
    }

    public Long getQtdManutencaoPreventiva() {
        return qtdManutencaoPreventiva;
    }

    public IndicadorProducao qtdManutencaoPreventiva(Long qtdManutencaoPreventiva) {
        this.qtdManutencaoPreventiva = qtdManutencaoPreventiva;
        return this;
    }

    public void setQtdManutencaoPreventiva(Long qtdManutencaoPreventiva) {
        this.qtdManutencaoPreventiva = qtdManutencaoPreventiva;
    }

    public Long getQtdManutencaoVerificadoSolicitado() {
        return qtdManutencaoVerificadoSolicitado;
    }

    public IndicadorProducao qtdManutencaoVerificadoSolicitado(Long qtdManutencaoVerificadoSolicitado) {
        this.qtdManutencaoVerificadoSolicitado = qtdManutencaoVerificadoSolicitado;
        return this;
    }

    public void setQtdManutencaoVerificadoSolicitado(Long qtdManutencaoVerificadoSolicitado) {
        this.qtdManutencaoVerificadoSolicitado = qtdManutencaoVerificadoSolicitado;
    }

    public Long getQtdReservatorioLavado() {
        return qtdReservatorioLavado;
    }

    public IndicadorProducao qtdReservatorioLavado(Long qtdReservatorioLavado) {
        this.qtdReservatorioLavado = qtdReservatorioLavado;
        return this;
    }

    public void setQtdReservatorioLavado(Long qtdReservatorioLavado) {
        this.qtdReservatorioLavado = qtdReservatorioLavado;
    }

    public Long getQtdFuncionarios() {
        return qtdFuncionarios;
    }

    public IndicadorProducao qtdFuncionarios(Long qtdFuncionarios) {
        this.qtdFuncionarios = qtdFuncionarios;
        return this;
    }

    public void setQtdFuncionarios(Long qtdFuncionarios) {
        this.qtdFuncionarios = qtdFuncionarios;
    }

    public Long getQtdFuncionariosEfectivos() {
        return qtdFuncionariosEfectivos;
    }

    public IndicadorProducao qtdFuncionariosEfectivos(Long qtdFuncionariosEfectivos) {
        this.qtdFuncionariosEfectivos = qtdFuncionariosEfectivos;
        return this;
    }

    public void setQtdFuncionariosEfectivos(Long qtdFuncionariosEfectivos) {
        this.qtdFuncionariosEfectivos = qtdFuncionariosEfectivos;
    }

    public Long getQtdFuncionariosContratados() {
        return qtdFuncionariosContratados;
    }

    public IndicadorProducao qtdFuncionariosContratados(Long qtdFuncionariosContratados) {
        this.qtdFuncionariosContratados = qtdFuncionariosContratados;
        return this;
    }

    public void setQtdFuncionariosContratados(Long qtdFuncionariosContratados) {
        this.qtdFuncionariosContratados = qtdFuncionariosContratados;
    }

    public Long getQtdFuncionariosOutrasEntidades() {
        return qtdFuncionariosOutrasEntidades;
    }

    public IndicadorProducao qtdFuncionariosOutrasEntidades(Long qtdFuncionariosOutrasEntidades) {
        this.qtdFuncionariosOutrasEntidades = qtdFuncionariosOutrasEntidades;
        return this;
    }

    public void setQtdFuncionariosOutrasEntidades(Long qtdFuncionariosOutrasEntidades) {
        this.qtdFuncionariosOutrasEntidades = qtdFuncionariosOutrasEntidades;
    }

    public Long getQtdNovasLigacoesNovosContratos() {
        return qtdNovasLigacoesNovosContratos;
    }

    public IndicadorProducao qtdNovasLigacoesNovosContratos(Long qtdNovasLigacoesNovosContratos) {
        this.qtdNovasLigacoesNovosContratos = qtdNovasLigacoesNovosContratos;
        return this;
    }

    public void setQtdNovasLigacoesNovosContratos(Long qtdNovasLigacoesNovosContratos) {
        this.qtdNovasLigacoesNovosContratos = qtdNovasLigacoesNovosContratos;
    }

    public Long getQtdNovasLigacoesDomesticasNovosContratos() {
        return qtdNovasLigacoesDomesticasNovosContratos;
    }

    public IndicadorProducao qtdNovasLigacoesDomesticasNovosContratos(Long qtdNovasLigacoesDomesticasNovosContratos) {
        this.qtdNovasLigacoesDomesticasNovosContratos = qtdNovasLigacoesDomesticasNovosContratos;
        return this;
    }

    public void setQtdNovasLigacoesDomesticasNovosContratos(Long qtdNovasLigacoesDomesticasNovosContratos) {
        this.qtdNovasLigacoesDomesticasNovosContratos = qtdNovasLigacoesDomesticasNovosContratos;
    }

    public Long getQtdLigacoesIlegaisRegularizadas() {
        return qtdLigacoesIlegaisRegularizadas;
    }

    public IndicadorProducao qtdLigacoesIlegaisRegularizadas(Long qtdLigacoesIlegaisRegularizadas) {
        this.qtdLigacoesIlegaisRegularizadas = qtdLigacoesIlegaisRegularizadas;
        return this;
    }

    public void setQtdLigacoesIlegaisRegularizadas(Long qtdLigacoesIlegaisRegularizadas) {
        this.qtdLigacoesIlegaisRegularizadas = qtdLigacoesIlegaisRegularizadas;
    }

    public Long getQtdLigacoesFechadas() {
        return qtdLigacoesFechadas;
    }

    public IndicadorProducao qtdLigacoesFechadas(Long qtdLigacoesFechadas) {
        this.qtdLigacoesFechadas = qtdLigacoesFechadas;
        return this;
    }

    public void setQtdLigacoesFechadas(Long qtdLigacoesFechadas) {
        this.qtdLigacoesFechadas = qtdLigacoesFechadas;
    }

    public Long getQtdCortes() {
        return qtdCortes;
    }

    public IndicadorProducao qtdCortes(Long qtdCortes) {
        this.qtdCortes = qtdCortes;
        return this;
    }

    public void setQtdCortes(Long qtdCortes) {
        this.qtdCortes = qtdCortes;
    }

    public Long getQtdReligacoes() {
        return qtdReligacoes;
    }

    public IndicadorProducao qtdReligacoes(Long qtdReligacoes) {
        this.qtdReligacoes = qtdReligacoes;
        return this;
    }

    public void setQtdReligacoes(Long qtdReligacoes) {
        this.qtdReligacoes = qtdReligacoes;
    }

    public Long getQtdLigacoesActivas() {
        return qtdLigacoesActivas;
    }

    public IndicadorProducao qtdLigacoesActivas(Long qtdLigacoesActivas) {
        this.qtdLigacoesActivas = qtdLigacoesActivas;
        return this;
    }

    public void setQtdLigacoesActivas(Long qtdLigacoesActivas) {
        this.qtdLigacoesActivas = qtdLigacoesActivas;
    }

    public Long getQtdLigacoesDomesticasActivas() {
        return qtdLigacoesDomesticasActivas;
    }

    public IndicadorProducao qtdLigacoesDomesticasActivas(Long qtdLigacoesDomesticasActivas) {
        this.qtdLigacoesDomesticasActivas = qtdLigacoesDomesticasActivas;
        return this;
    }

    public void setQtdLigacoesDomesticasActivas(Long qtdLigacoesDomesticasActivas) {
        this.qtdLigacoesDomesticasActivas = qtdLigacoesDomesticasActivas;
    }

    public Long getQtdLigacoesFacturadasBaseLeiturasReais() {
        return qtdLigacoesFacturadasBaseLeiturasReais;
    }

    public IndicadorProducao qtdLigacoesFacturadasBaseLeiturasReais(Long qtdLigacoesFacturadasBaseLeiturasReais) {
        this.qtdLigacoesFacturadasBaseLeiturasReais = qtdLigacoesFacturadasBaseLeiturasReais;
        return this;
    }

    public void setQtdLigacoesFacturadasBaseLeiturasReais(Long qtdLigacoesFacturadasBaseLeiturasReais) {
        this.qtdLigacoesFacturadasBaseLeiturasReais = qtdLigacoesFacturadasBaseLeiturasReais;
    }

    public Long getQtdLigacoesFacturadasBaseEstimativasAvenca() {
        return qtdLigacoesFacturadasBaseEstimativasAvenca;
    }

    public IndicadorProducao qtdLigacoesFacturadasBaseEstimativasAvenca(Long qtdLigacoesFacturadasBaseEstimativasAvenca) {
        this.qtdLigacoesFacturadasBaseEstimativasAvenca = qtdLigacoesFacturadasBaseEstimativasAvenca;
        return this;
    }

    public void setQtdLigacoesFacturadasBaseEstimativasAvenca(Long qtdLigacoesFacturadasBaseEstimativasAvenca) {
        this.qtdLigacoesFacturadasBaseEstimativasAvenca = qtdLigacoesFacturadasBaseEstimativasAvenca;
    }

    public BigDecimal getQtdVolumeAguaFacturada() {
        return qtdVolumeAguaFacturada;
    }

    public IndicadorProducao qtdVolumeAguaFacturada(BigDecimal qtdVolumeAguaFacturada) {
        this.qtdVolumeAguaFacturada = qtdVolumeAguaFacturada;
        return this;
    }

    public void setQtdVolumeAguaFacturada(BigDecimal qtdVolumeAguaFacturada) {
        this.qtdVolumeAguaFacturada = qtdVolumeAguaFacturada;
    }

    public BigDecimal getQtdVolumeTotalFacturadaLigacoesDomesticas() {
        return qtdVolumeTotalFacturadaLigacoesDomesticas;
    }

    public IndicadorProducao qtdVolumeTotalFacturadaLigacoesDomesticas(BigDecimal qtdVolumeTotalFacturadaLigacoesDomesticas) {
        this.qtdVolumeTotalFacturadaLigacoesDomesticas = qtdVolumeTotalFacturadaLigacoesDomesticas;
        return this;
    }

    public void setQtdVolumeTotalFacturadaLigacoesDomesticas(BigDecimal qtdVolumeTotalFacturadaLigacoesDomesticas) {
        this.qtdVolumeTotalFacturadaLigacoesDomesticas = qtdVolumeTotalFacturadaLigacoesDomesticas;
    }

    public BigDecimal getQtdVolumeFacturadoBaseLeituraReais() {
        return qtdVolumeFacturadoBaseLeituraReais;
    }

    public IndicadorProducao qtdVolumeFacturadoBaseLeituraReais(BigDecimal qtdVolumeFacturadoBaseLeituraReais) {
        this.qtdVolumeFacturadoBaseLeituraReais = qtdVolumeFacturadoBaseLeituraReais;
        return this;
    }

    public void setQtdVolumeFacturadoBaseLeituraReais(BigDecimal qtdVolumeFacturadoBaseLeituraReais) {
        this.qtdVolumeFacturadoBaseLeituraReais = qtdVolumeFacturadoBaseLeituraReais;
    }

    public BigDecimal getVlrTotalFacturado() {
        return vlrTotalFacturado;
    }

    public IndicadorProducao vlrTotalFacturado(BigDecimal vlrTotalFacturado) {
        this.vlrTotalFacturado = vlrTotalFacturado;
        return this;
    }

    public void setVlrTotalFacturado(BigDecimal vlrTotalFacturado) {
        this.vlrTotalFacturado = vlrTotalFacturado;
    }

    public BigDecimal getVlrFacturasCanceladasNotasCreditos() {
        return vlrFacturasCanceladasNotasCreditos;
    }

    public IndicadorProducao vlrFacturasCanceladasNotasCreditos(BigDecimal vlrFacturasCanceladasNotasCreditos) {
        this.vlrFacturasCanceladasNotasCreditos = vlrFacturasCanceladasNotasCreditos;
        return this;
    }

    public void setVlrFacturasCanceladasNotasCreditos(BigDecimal vlrFacturasCanceladasNotasCreditos) {
        this.vlrFacturasCanceladasNotasCreditos = vlrFacturasCanceladasNotasCreditos;
    }

    public BigDecimal getVlrRealFacturado() {
        return vlrRealFacturado;
    }

    public IndicadorProducao vlrRealFacturado(BigDecimal vlrRealFacturado) {
        this.vlrRealFacturado = vlrRealFacturado;
        return this;
    }

    public void setVlrRealFacturado(BigDecimal vlrRealFacturado) {
        this.vlrRealFacturado = vlrRealFacturado;
    }

    public BigDecimal getVlrTotalCobrado() {
        return vlrTotalCobrado;
    }

    public IndicadorProducao vlrTotalCobrado(BigDecimal vlrTotalCobrado) {
        this.vlrTotalCobrado = vlrTotalCobrado;
        return this;
    }

    public void setVlrTotalCobrado(BigDecimal vlrTotalCobrado) {
        this.vlrTotalCobrado = vlrTotalCobrado;
    }

    public Long getQtdReclamacoes() {
        return qtdReclamacoes;
    }

    public IndicadorProducao qtdReclamacoes(Long qtdReclamacoes) {
        this.qtdReclamacoes = qtdReclamacoes;
        return this;
    }

    public void setQtdReclamacoes(Long qtdReclamacoes) {
        this.qtdReclamacoes = qtdReclamacoes;
    }

    public Long getQtdReclamacoesRespondidasMenorIgualCincoDias() {
        return qtdReclamacoesRespondidasMenorIgualCincoDias;
    }

    public IndicadorProducao qtdReclamacoesRespondidasMenorIgualCincoDias(Long qtdReclamacoesRespondidasMenorIgualCincoDias) {
        this.qtdReclamacoesRespondidasMenorIgualCincoDias = qtdReclamacoesRespondidasMenorIgualCincoDias;
        return this;
    }

    public void setQtdReclamacoesRespondidasMenorIgualCincoDias(Long qtdReclamacoesRespondidasMenorIgualCincoDias) {
        this.qtdReclamacoesRespondidasMenorIgualCincoDias = qtdReclamacoesRespondidasMenorIgualCincoDias;
    }

    public Long getQtdReclamacoesRespondidasMaisCincoMenosVinteDias() {
        return qtdReclamacoesRespondidasMaisCincoMenosVinteDias;
    }

    public IndicadorProducao qtdReclamacoesRespondidasMaisCincoMenosVinteDias(Long qtdReclamacoesRespondidasMaisCincoMenosVinteDias) {
        this.qtdReclamacoesRespondidasMaisCincoMenosVinteDias = qtdReclamacoesRespondidasMaisCincoMenosVinteDias;
        return this;
    }

    public void setQtdReclamacoesRespondidasMaisCincoMenosVinteDias(Long qtdReclamacoesRespondidasMaisCincoMenosVinteDias) {
        this.qtdReclamacoesRespondidasMaisCincoMenosVinteDias = qtdReclamacoesRespondidasMaisCincoMenosVinteDias;
    }

    public Long getQtdReclamacoesRespondidasMaiorIgualVinteDias() {
        return qtdReclamacoesRespondidasMaiorIgualVinteDias;
    }

    public IndicadorProducao qtdReclamacoesRespondidasMaiorIgualVinteDias(Long qtdReclamacoesRespondidasMaiorIgualVinteDias) {
        this.qtdReclamacoesRespondidasMaiorIgualVinteDias = qtdReclamacoesRespondidasMaiorIgualVinteDias;
        return this;
    }

    public void setQtdReclamacoesRespondidasMaiorIgualVinteDias(Long qtdReclamacoesRespondidasMaiorIgualVinteDias) {
        this.qtdReclamacoesRespondidasMaiorIgualVinteDias = qtdReclamacoesRespondidasMaiorIgualVinteDias;
    }

    public BigDecimal getVlrCustoPessoal() {
        return vlrCustoPessoal;
    }

    public IndicadorProducao vlrCustoPessoal(BigDecimal vlrCustoPessoal) {
        this.vlrCustoPessoal = vlrCustoPessoal;
        return this;
    }

    public void setVlrCustoPessoal(BigDecimal vlrCustoPessoal) {
        this.vlrCustoPessoal = vlrCustoPessoal;
    }

    public BigDecimal getVlrCustoFse() {
        return vlrCustoFse;
    }

    public IndicadorProducao vlrCustoFse(BigDecimal vlrCustoFse) {
        this.vlrCustoFse = vlrCustoFse;
        return this;
    }

    public void setVlrCustoFse(BigDecimal vlrCustoFse) {
        this.vlrCustoFse = vlrCustoFse;
    }

    public BigDecimal getVlrCustoEnergia() {
        return vlrCustoEnergia;
    }

    public IndicadorProducao vlrCustoEnergia(BigDecimal vlrCustoEnergia) {
        this.vlrCustoEnergia = vlrCustoEnergia;
        return this;
    }

    public void setVlrCustoEnergia(BigDecimal vlrCustoEnergia) {
        this.vlrCustoEnergia = vlrCustoEnergia;
    }

    public BigDecimal getVlrCustoManutencao() {
        return vlrCustoManutencao;
    }

    public IndicadorProducao vlrCustoManutencao(BigDecimal vlrCustoManutencao) {
        this.vlrCustoManutencao = vlrCustoManutencao;
        return this;
    }

    public void setVlrCustoManutencao(BigDecimal vlrCustoManutencao) {
        this.vlrCustoManutencao = vlrCustoManutencao;
    }

    public BigDecimal getVlrCustoReagentes() {
        return vlrCustoReagentes;
    }

    public IndicadorProducao vlrCustoReagentes(BigDecimal vlrCustoReagentes) {
        this.vlrCustoReagentes = vlrCustoReagentes;
        return this;
    }

    public void setVlrCustoReagentes(BigDecimal vlrCustoReagentes) {
        this.vlrCustoReagentes = vlrCustoReagentes;
    }

    public BigDecimal getVlrCustoDestinoFinalLamas() {
        return vlrCustoDestinoFinalLamas;
    }

    public IndicadorProducao vlrCustoDestinoFinalLamas(BigDecimal vlrCustoDestinoFinalLamas) {
        this.vlrCustoDestinoFinalLamas = vlrCustoDestinoFinalLamas;
        return this;
    }

    public void setVlrCustoDestinoFinalLamas(BigDecimal vlrCustoDestinoFinalLamas) {
        this.vlrCustoDestinoFinalLamas = vlrCustoDestinoFinalLamas;
    }

    public BigDecimal getVlrCustoOperacionaisOpex() {
        return vlrCustoOperacionaisOpex;
    }

    public IndicadorProducao vlrCustoOperacionaisOpex(BigDecimal vlrCustoOperacionaisOpex) {
        this.vlrCustoOperacionaisOpex = vlrCustoOperacionaisOpex;
        return this;
    }

    public void setVlrCustoOperacionaisOpex(BigDecimal vlrCustoOperacionaisOpex) {
        this.vlrCustoOperacionaisOpex = vlrCustoOperacionaisOpex;
    }

    public BigDecimal getVlrCustoAmortizaAnualInvestOpCapex() {
        return vlrCustoAmortizaAnualInvestOpCapex;
    }

    public IndicadorProducao vlrCustoAmortizaAnualInvestOpCapex(BigDecimal vlrCustoAmortizaAnualInvestOpCapex) {
        this.vlrCustoAmortizaAnualInvestOpCapex = vlrCustoAmortizaAnualInvestOpCapex;
        return this;
    }

    public void setVlrCustoAmortizaAnualInvestOpCapex(BigDecimal vlrCustoAmortizaAnualInvestOpCapex) {
        this.vlrCustoAmortizaAnualInvestOpCapex = vlrCustoAmortizaAnualInvestOpCapex;
    }

    public BigDecimal getVlrCustoTotaisCapexOpex() {
        return vlrCustoTotaisCapexOpex;
    }

    public IndicadorProducao vlrCustoTotaisCapexOpex(BigDecimal vlrCustoTotaisCapexOpex) {
        this.vlrCustoTotaisCapexOpex = vlrCustoTotaisCapexOpex;
        return this;
    }

    public void setVlrCustoTotaisCapexOpex(BigDecimal vlrCustoTotaisCapexOpex) {
        this.vlrCustoTotaisCapexOpex = vlrCustoTotaisCapexOpex;
    }

    public Long getQtdCaptacoes() {
        return qtdCaptacoes;
    }

    public IndicadorProducao qtdCaptacoes(Long qtdCaptacoes) {
        this.qtdCaptacoes = qtdCaptacoes;
        return this;
    }

    public void setQtdCaptacoes(Long qtdCaptacoes) {
        this.qtdCaptacoes = qtdCaptacoes;
    }

    public Long getQtdEtas() {
        return qtdEtas;
    }

    public IndicadorProducao qtdEtas(Long qtdEtas) {
        this.qtdEtas = qtdEtas;
        return this;
    }

    public void setQtdEtas(Long qtdEtas) {
        this.qtdEtas = qtdEtas;
    }

    public Long getQtdReservatorios() {
        return qtdReservatorios;
    }

    public IndicadorProducao qtdReservatorios(Long qtdReservatorios) {
        this.qtdReservatorios = qtdReservatorios;
        return this;
    }

    public void setQtdReservatorios(Long qtdReservatorios) {
        this.qtdReservatorios = qtdReservatorios;
    }

    public Long getQtdEstacoesElevatorias() {
        return qtdEstacoesElevatorias;
    }

    public IndicadorProducao qtdEstacoesElevatorias(Long qtdEstacoesElevatorias) {
        this.qtdEstacoesElevatorias = qtdEstacoesElevatorias;
        return this;
    }

    public void setQtdEstacoesElevatorias(Long qtdEstacoesElevatorias) {
        this.qtdEstacoesElevatorias = qtdEstacoesElevatorias;
    }

    public BigDecimal getQtdComprimentoAdutoras() {
        return qtdComprimentoAdutoras;
    }

    public IndicadorProducao qtdComprimentoAdutoras(BigDecimal qtdComprimentoAdutoras) {
        this.qtdComprimentoAdutoras = qtdComprimentoAdutoras;
        return this;
    }

    public void setQtdComprimentoAdutoras(BigDecimal qtdComprimentoAdutoras) {
        this.qtdComprimentoAdutoras = qtdComprimentoAdutoras;
    }

    public BigDecimal getQtdComprimentoRedes() {
        return qtdComprimentoRedes;
    }

    public IndicadorProducao qtdComprimentoRedes(BigDecimal qtdComprimentoRedes) {
        this.qtdComprimentoRedes = qtdComprimentoRedes;
        return this;
    }

    public void setQtdComprimentoRedes(BigDecimal qtdComprimentoRedes) {
        this.qtdComprimentoRedes = qtdComprimentoRedes;
    }

    public BigDecimal getQtdComprimentoRamais() {
        return qtdComprimentoRamais;
    }

    public IndicadorProducao qtdComprimentoRamais(BigDecimal qtdComprimentoRamais) {
        this.qtdComprimentoRamais = qtdComprimentoRamais;
        return this;
    }

    public void setQtdComprimentoRamais(BigDecimal qtdComprimentoRamais) {
        this.qtdComprimentoRamais = qtdComprimentoRamais;
    }

    public Long getQtdAcoesFormacaoMoPlaneadas() {
        return qtdAcoesFormacaoMoPlaneadas;
    }

    public IndicadorProducao qtdAcoesFormacaoMoPlaneadas(Long qtdAcoesFormacaoMoPlaneadas) {
        this.qtdAcoesFormacaoMoPlaneadas = qtdAcoesFormacaoMoPlaneadas;
        return this;
    }

    public void setQtdAcoesFormacaoMoPlaneadas(Long qtdAcoesFormacaoMoPlaneadas) {
        this.qtdAcoesFormacaoMoPlaneadas = qtdAcoesFormacaoMoPlaneadas;
    }

    public Long getQtdAcoesFormacaoMmsPlaneadas() {
        return qtdAcoesFormacaoMmsPlaneadas;
    }

    public IndicadorProducao qtdAcoesFormacaoMmsPlaneadas(Long qtdAcoesFormacaoMmsPlaneadas) {
        this.qtdAcoesFormacaoMmsPlaneadas = qtdAcoesFormacaoMmsPlaneadas;
        return this;
    }

    public void setQtdAcoesFormacaoMmsPlaneadas(Long qtdAcoesFormacaoMmsPlaneadas) {
        this.qtdAcoesFormacaoMmsPlaneadas = qtdAcoesFormacaoMmsPlaneadas;
    }

    public Long getQtdAcoesFormacaoCmpPlaneadas() {
        return qtdAcoesFormacaoCmpPlaneadas;
    }

    public IndicadorProducao qtdAcoesFormacaoCmpPlaneadas(Long qtdAcoesFormacaoCmpPlaneadas) {
        this.qtdAcoesFormacaoCmpPlaneadas = qtdAcoesFormacaoCmpPlaneadas;
        return this;
    }

    public void setQtdAcoesFormacaoCmpPlaneadas(Long qtdAcoesFormacaoCmpPlaneadas) {
        this.qtdAcoesFormacaoCmpPlaneadas = qtdAcoesFormacaoCmpPlaneadas;
    }

    public Long getQtdAcoesFormacaoSoftwareFornecidosPlanejadas() {
        return qtdAcoesFormacaoSoftwareFornecidosPlanejadas;
    }

    public IndicadorProducao qtdAcoesFormacaoSoftwareFornecidosPlanejadas(Long qtdAcoesFormacaoSoftwareFornecidosPlanejadas) {
        this.qtdAcoesFormacaoSoftwareFornecidosPlanejadas = qtdAcoesFormacaoSoftwareFornecidosPlanejadas;
        return this;
    }

    public void setQtdAcoesFormacaoSoftwareFornecidosPlanejadas(Long qtdAcoesFormacaoSoftwareFornecidosPlanejadas) {
        this.qtdAcoesFormacaoSoftwareFornecidosPlanejadas = qtdAcoesFormacaoSoftwareFornecidosPlanejadas;
    }

    public Long getQtdAcoesFormacaoMoRealizadas() {
        return qtdAcoesFormacaoMoRealizadas;
    }

    public IndicadorProducao qtdAcoesFormacaoMoRealizadas(Long qtdAcoesFormacaoMoRealizadas) {
        this.qtdAcoesFormacaoMoRealizadas = qtdAcoesFormacaoMoRealizadas;
        return this;
    }

    public void setQtdAcoesFormacaoMoRealizadas(Long qtdAcoesFormacaoMoRealizadas) {
        this.qtdAcoesFormacaoMoRealizadas = qtdAcoesFormacaoMoRealizadas;
    }

    public Long getQtdAcoesFormacaoMmsRealizadas() {
        return qtdAcoesFormacaoMmsRealizadas;
    }

    public IndicadorProducao qtdAcoesFormacaoMmsRealizadas(Long qtdAcoesFormacaoMmsRealizadas) {
        this.qtdAcoesFormacaoMmsRealizadas = qtdAcoesFormacaoMmsRealizadas;
        return this;
    }

    public void setQtdAcoesFormacaoMmsRealizadas(Long qtdAcoesFormacaoMmsRealizadas) {
        this.qtdAcoesFormacaoMmsRealizadas = qtdAcoesFormacaoMmsRealizadas;
    }

    public Long getQtdAcoesFormacaoCmpRealizadas() {
        return qtdAcoesFormacaoCmpRealizadas;
    }

    public IndicadorProducao qtdAcoesFormacaoCmpRealizadas(Long qtdAcoesFormacaoCmpRealizadas) {
        this.qtdAcoesFormacaoCmpRealizadas = qtdAcoesFormacaoCmpRealizadas;
        return this;
    }

    public void setQtdAcoesFormacaoCmpRealizadas(Long qtdAcoesFormacaoCmpRealizadas) {
        this.qtdAcoesFormacaoCmpRealizadas = qtdAcoesFormacaoCmpRealizadas;
    }

    public Long getQtdAcoesFormacaoSoftwareFornecidosRealizadas() {
        return qtdAcoesFormacaoSoftwareFornecidosRealizadas;
    }

    public IndicadorProducao qtdAcoesFormacaoSoftwareFornecidosRealizadas(Long qtdAcoesFormacaoSoftwareFornecidosRealizadas) {
        this.qtdAcoesFormacaoSoftwareFornecidosRealizadas = qtdAcoesFormacaoSoftwareFornecidosRealizadas;
        return this;
    }

    public void setQtdAcoesFormacaoSoftwareFornecidosRealizadas(Long qtdAcoesFormacaoSoftwareFornecidosRealizadas) {
        this.qtdAcoesFormacaoSoftwareFornecidosRealizadas = qtdAcoesFormacaoSoftwareFornecidosRealizadas;
    }

    public Long getQtdAcoesFormacaoRealizadas() {
        return qtdAcoesFormacaoRealizadas;
    }

    public IndicadorProducao qtdAcoesFormacaoRealizadas(Long qtdAcoesFormacaoRealizadas) {
        this.qtdAcoesFormacaoRealizadas = qtdAcoesFormacaoRealizadas;
        return this;
    }

    public void setQtdAcoesFormacaoRealizadas(Long qtdAcoesFormacaoRealizadas) {
        this.qtdAcoesFormacaoRealizadas = qtdAcoesFormacaoRealizadas;
    }

    public Long getQtdManuaisMoPrevistos() {
        return qtdManuaisMoPrevistos;
    }

    public IndicadorProducao qtdManuaisMoPrevistos(Long qtdManuaisMoPrevistos) {
        this.qtdManuaisMoPrevistos = qtdManuaisMoPrevistos;
        return this;
    }

    public void setQtdManuaisMoPrevistos(Long qtdManuaisMoPrevistos) {
        this.qtdManuaisMoPrevistos = qtdManuaisMoPrevistos;
    }

    public Long getQtdManuaisMmsPrevistos() {
        return qtdManuaisMmsPrevistos;
    }

    public IndicadorProducao qtdManuaisMmsPrevistos(Long qtdManuaisMmsPrevistos) {
        this.qtdManuaisMmsPrevistos = qtdManuaisMmsPrevistos;
        return this;
    }

    public void setQtdManuaisMmsPrevistos(Long qtdManuaisMmsPrevistos) {
        this.qtdManuaisMmsPrevistos = qtdManuaisMmsPrevistos;
    }

    public Long getQtdManuaisCmpPrevistos() {
        return qtdManuaisCmpPrevistos;
    }

    public IndicadorProducao qtdManuaisCmpPrevistos(Long qtdManuaisCmpPrevistos) {
        this.qtdManuaisCmpPrevistos = qtdManuaisCmpPrevistos;
        return this;
    }

    public void setQtdManuaisCmpPrevistos(Long qtdManuaisCmpPrevistos) {
        this.qtdManuaisCmpPrevistos = qtdManuaisCmpPrevistos;
    }

    public Long getQtdManuaisPrevistos() {
        return qtdManuaisPrevistos;
    }

    public IndicadorProducao qtdManuaisPrevistos(Long qtdManuaisPrevistos) {
        this.qtdManuaisPrevistos = qtdManuaisPrevistos;
        return this;
    }

    public void setQtdManuaisPrevistos(Long qtdManuaisPrevistos) {
        this.qtdManuaisPrevistos = qtdManuaisPrevistos;
    }

    public Long getQtdAcoesManuaisMoRealizadas() {
        return qtdAcoesManuaisMoRealizadas;
    }

    public IndicadorProducao qtdAcoesManuaisMoRealizadas(Long qtdAcoesManuaisMoRealizadas) {
        this.qtdAcoesManuaisMoRealizadas = qtdAcoesManuaisMoRealizadas;
        return this;
    }

    public void setQtdAcoesManuaisMoRealizadas(Long qtdAcoesManuaisMoRealizadas) {
        this.qtdAcoesManuaisMoRealizadas = qtdAcoesManuaisMoRealizadas;
    }

    public Long getQtdManuaisMmsRealizadas() {
        return qtdManuaisMmsRealizadas;
    }

    public IndicadorProducao qtdManuaisMmsRealizadas(Long qtdManuaisMmsRealizadas) {
        this.qtdManuaisMmsRealizadas = qtdManuaisMmsRealizadas;
        return this;
    }

    public void setQtdManuaisMmsRealizadas(Long qtdManuaisMmsRealizadas) {
        this.qtdManuaisMmsRealizadas = qtdManuaisMmsRealizadas;
    }

    public Long getQtdManuaisCmpRealizadas() {
        return qtdManuaisCmpRealizadas;
    }

    public IndicadorProducao qtdManuaisCmpRealizadas(Long qtdManuaisCmpRealizadas) {
        this.qtdManuaisCmpRealizadas = qtdManuaisCmpRealizadas;
        return this;
    }

    public void setQtdManuaisCmpRealizadas(Long qtdManuaisCmpRealizadas) {
        this.qtdManuaisCmpRealizadas = qtdManuaisCmpRealizadas;
    }

    public Long getQtdManuaisRealizados() {
        return qtdManuaisRealizados;
    }

    public IndicadorProducao qtdManuaisRealizados(Long qtdManuaisRealizados) {
        this.qtdManuaisRealizados = qtdManuaisRealizados;
        return this;
    }

    public void setQtdManuaisRealizados(Long qtdManuaisRealizados) {
        this.qtdManuaisRealizados = qtdManuaisRealizados;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public IndicadorProducao idSituacao(Situacao situacao) {
        this.situacao = situacao;
        return this;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public SistemaAgua getSistemaAgua() {
        return sistemaAgua;
    }

    public IndicadorProducao idSistemaAgua(SistemaAgua sistemaAgua) {
        this.sistemaAgua = sistemaAgua;
        return this;
    }

    public void setSistemaAgua(SistemaAgua sistemaAgua) {
        this.sistemaAgua = sistemaAgua;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public IndicadorProducao idComuna(Comuna comuna) {
        this.comuna = comuna;
        return this;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IndicadorProducao indicadorProducao = (IndicadorProducao) o;
        if (indicadorProducao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), indicadorProducao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "IndicadorProducao{" +
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
