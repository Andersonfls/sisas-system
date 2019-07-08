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
 * Criteria class for the SistemaAgua entity. This class is used in SistemaAguaResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /sistema-aguas?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SistemaAguaCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idSistemaAgua;

    private LongFilter idUsuario;

    private StringFilter nmInqueridor;

    private LocalDateFilter dtLancamento;

    private LocalDateFilter dtUltimaAlteracao;

    private StringFilter nmLocalidade;

    private LongFilter qtdPopulacaoActual;

    private LongFilter qtdCasasLocalidade;

    private StringFilter nmTpComunaAldeia;

    private StringFilter nmTpArea;

    private LongFilter possuiSistemaAgua;

    private StringFilter nmSistemaAgua;

    private StringFilter nmFonteAgua;

    private BigDecimalFilter latitude;

    private BigDecimalFilter longitude;

    private BigDecimalFilter altitude;

    private StringFilter nmTpFonte;

    private StringFilter nmFonteAguaUtilizada;

    private StringFilter nmTipoBomba;

    private LongFilter qtdCasasAguaLigada;

    private LongFilter qtdChafarisesFuncionando;

    private LongFilter qtdContadoresLigados;

    private LongFilter qtdBebedouros;

    private LongFilter qtdHabitantesAcessoServicoAgua;

    private LongFilter anoConstrucaoSistema;

    private StringFilter nmTpAvariaSistema;

    private StringFilter causaAvariaSistema;

    private StringFilter statusResolucao;

    private StringFilter tempoServicoDisponivel;

    private BigDecimalFilter qtdDiametroCondutaAdutoraAguaBruta;

    private BigDecimalFilter qtdComprimentoCondutaAdutoraAguaBruta;

    private BigDecimalFilter qtdDiametroCondutaAdutoraAguaTratada;

    private BigDecimalFilter qtdComprimentoCondutaAdutoraAguaTratada;

    private StringFilter descMaterialUtilizadoCondutas;

    private LongFilter qtdReservatoriosApoiados;

    private LongFilter qtdCapacidadeReservatoriosApoiados;

    private LongFilter qtdReservatoriosElevados;

    private LongFilter qtdCapacidadeReservatoriosElevados;

    private BigDecimalFilter alturaReservatoriosElevados;

    private StringFilter nmTpTratamentoAgua;

    private StringFilter nmTpTratamentoPadraoUtilizado;

    private StringFilter nmTpTratamentoBasicoUtilizado;

    private StringFilter existeAvariaSistemaTratamento;

    private StringFilter existeMotivoAusenciaTratamento;

    private StringFilter nmEquipamentosComAvaria;

    private LongFilter caudalDoSistema;

    private BigDecimalFilter qtdConsumoPercaptaLitrosHomemDia;

    private BigDecimalFilter qtdDotacaoPercapta;

    private BigDecimalFilter qtdDiariaHorasServicoSistema;

    private StringFilter esquema;

    private StringFilter nmModeloBombaManualUtilizada;

    private StringFilter nmTpBombaEnergia;

    private LongFilter idSituacaoId;

    private LongFilter idComunaId;

    private LongFilter adjudicacaoId;

    private LongFilter concepcaoId;

    private LongFilter concursoId;

    private LongFilter contratoId;

    private LongFilter empreitadaId;

    private LongFilter execucaoId;

    private LongFilter indicadorProducaoId;

    private LongFilter sistemaAguaLogId;

    public SistemaAguaCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdSistemaAgua() {
        return idSistemaAgua;
    }

    public void setIdSistemaAgua(LongFilter idSistemaAgua) {
        this.idSistemaAgua = idSistemaAgua;
    }

    public LongFilter getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(LongFilter idUsuario) {
        this.idUsuario = idUsuario;
    }

    public StringFilter getNmInqueridor() {
        return nmInqueridor;
    }

    public void setNmInqueridor(StringFilter nmInqueridor) {
        this.nmInqueridor = nmInqueridor;
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

    public StringFilter getNmLocalidade() {
        return nmLocalidade;
    }

    public void setNmLocalidade(StringFilter nmLocalidade) {
        this.nmLocalidade = nmLocalidade;
    }

    public LongFilter getQtdPopulacaoActual() {
        return qtdPopulacaoActual;
    }

    public void setQtdPopulacaoActual(LongFilter qtdPopulacaoActual) {
        this.qtdPopulacaoActual = qtdPopulacaoActual;
    }

    public LongFilter getQtdCasasLocalidade() {
        return qtdCasasLocalidade;
    }

    public void setQtdCasasLocalidade(LongFilter qtdCasasLocalidade) {
        this.qtdCasasLocalidade = qtdCasasLocalidade;
    }

    public StringFilter getNmTpComunaAldeia() {
        return nmTpComunaAldeia;
    }

    public void setNmTpComunaAldeia(StringFilter nmTpComunaAldeia) {
        this.nmTpComunaAldeia = nmTpComunaAldeia;
    }

    public StringFilter getNmTpArea() {
        return nmTpArea;
    }

    public void setNmTpArea(StringFilter nmTpArea) {
        this.nmTpArea = nmTpArea;
    }

    public LongFilter getPossuiSistemaAgua() {
        return possuiSistemaAgua;
    }

    public void setPossuiSistemaAgua(LongFilter possuiSistemaAgua) {
        this.possuiSistemaAgua = possuiSistemaAgua;
    }

    public StringFilter getNmSistemaAgua() {
        return nmSistemaAgua;
    }

    public void setNmSistemaAgua(StringFilter nmSistemaAgua) {
        this.nmSistemaAgua = nmSistemaAgua;
    }

    public StringFilter getNmFonteAgua() {
        return nmFonteAgua;
    }

    public void setNmFonteAgua(StringFilter nmFonteAgua) {
        this.nmFonteAgua = nmFonteAgua;
    }

    public BigDecimalFilter getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimalFilter latitude) {
        this.latitude = latitude;
    }

    public BigDecimalFilter getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimalFilter longitude) {
        this.longitude = longitude;
    }

    public BigDecimalFilter getAltitude() {
        return altitude;
    }

    public void setAltitude(BigDecimalFilter altitude) {
        this.altitude = altitude;
    }

    public StringFilter getNmTpFonte() {
        return nmTpFonte;
    }

    public void setNmTpFonte(StringFilter nmTpFonte) {
        this.nmTpFonte = nmTpFonte;
    }

    public StringFilter getNmFonteAguaUtilizada() {
        return nmFonteAguaUtilizada;
    }

    public void setNmFonteAguaUtilizada(StringFilter nmFonteAguaUtilizada) {
        this.nmFonteAguaUtilizada = nmFonteAguaUtilizada;
    }

    public StringFilter getNmTipoBomba() {
        return nmTipoBomba;
    }

    public void setNmTipoBomba(StringFilter nmTipoBomba) {
        this.nmTipoBomba = nmTipoBomba;
    }

    public LongFilter getQtdCasasAguaLigada() {
        return qtdCasasAguaLigada;
    }

    public void setQtdCasasAguaLigada(LongFilter qtdCasasAguaLigada) {
        this.qtdCasasAguaLigada = qtdCasasAguaLigada;
    }

    public LongFilter getQtdChafarisesFuncionando() {
        return qtdChafarisesFuncionando;
    }

    public void setQtdChafarisesFuncionando(LongFilter qtdChafarisesFuncionando) {
        this.qtdChafarisesFuncionando = qtdChafarisesFuncionando;
    }

    public LongFilter getQtdContadoresLigados() {
        return qtdContadoresLigados;
    }

    public void setQtdContadoresLigados(LongFilter qtdContadoresLigados) {
        this.qtdContadoresLigados = qtdContadoresLigados;
    }

    public LongFilter getQtdBebedouros() {
        return qtdBebedouros;
    }

    public void setQtdBebedouros(LongFilter qtdBebedouros) {
        this.qtdBebedouros = qtdBebedouros;
    }

    public LongFilter getQtdHabitantesAcessoServicoAgua() {
        return qtdHabitantesAcessoServicoAgua;
    }

    public void setQtdHabitantesAcessoServicoAgua(LongFilter qtdHabitantesAcessoServicoAgua) {
        this.qtdHabitantesAcessoServicoAgua = qtdHabitantesAcessoServicoAgua;
    }

    public LongFilter getAnoConstrucaoSistema() {
        return anoConstrucaoSistema;
    }

    public void setAnoConstrucaoSistema(LongFilter anoConstrucaoSistema) {
        this.anoConstrucaoSistema = anoConstrucaoSistema;
    }

    public StringFilter getNmTpAvariaSistema() {
        return nmTpAvariaSistema;
    }

    public void setNmTpAvariaSistema(StringFilter nmTpAvariaSistema) {
        this.nmTpAvariaSistema = nmTpAvariaSistema;
    }

    public StringFilter getCausaAvariaSistema() {
        return causaAvariaSistema;
    }

    public void setCausaAvariaSistema(StringFilter causaAvariaSistema) {
        this.causaAvariaSistema = causaAvariaSistema;
    }

    public StringFilter getStatusResolucao() {
        return statusResolucao;
    }

    public void setStatusResolucao(StringFilter statusResolucao) {
        this.statusResolucao = statusResolucao;
    }

    public StringFilter getTempoServicoDisponivel() {
        return tempoServicoDisponivel;
    }

    public void setTempoServicoDisponivel(StringFilter tempoServicoDisponivel) {
        this.tempoServicoDisponivel = tempoServicoDisponivel;
    }

    public BigDecimalFilter getQtdDiametroCondutaAdutoraAguaBruta() {
        return qtdDiametroCondutaAdutoraAguaBruta;
    }

    public void setQtdDiametroCondutaAdutoraAguaBruta(BigDecimalFilter qtdDiametroCondutaAdutoraAguaBruta) {
        this.qtdDiametroCondutaAdutoraAguaBruta = qtdDiametroCondutaAdutoraAguaBruta;
    }

    public BigDecimalFilter getQtdComprimentoCondutaAdutoraAguaBruta() {
        return qtdComprimentoCondutaAdutoraAguaBruta;
    }

    public void setQtdComprimentoCondutaAdutoraAguaBruta(BigDecimalFilter qtdComprimentoCondutaAdutoraAguaBruta) {
        this.qtdComprimentoCondutaAdutoraAguaBruta = qtdComprimentoCondutaAdutoraAguaBruta;
    }

    public BigDecimalFilter getQtdDiametroCondutaAdutoraAguaTratada() {
        return qtdDiametroCondutaAdutoraAguaTratada;
    }

    public void setQtdDiametroCondutaAdutoraAguaTratada(BigDecimalFilter qtdDiametroCondutaAdutoraAguaTratada) {
        this.qtdDiametroCondutaAdutoraAguaTratada = qtdDiametroCondutaAdutoraAguaTratada;
    }

    public BigDecimalFilter getQtdComprimentoCondutaAdutoraAguaTratada() {
        return qtdComprimentoCondutaAdutoraAguaTratada;
    }

    public void setQtdComprimentoCondutaAdutoraAguaTratada(BigDecimalFilter qtdComprimentoCondutaAdutoraAguaTratada) {
        this.qtdComprimentoCondutaAdutoraAguaTratada = qtdComprimentoCondutaAdutoraAguaTratada;
    }

    public StringFilter getDescMaterialUtilizadoCondutas() {
        return descMaterialUtilizadoCondutas;
    }

    public void setDescMaterialUtilizadoCondutas(StringFilter descMaterialUtilizadoCondutas) {
        this.descMaterialUtilizadoCondutas = descMaterialUtilizadoCondutas;
    }

    public LongFilter getQtdReservatoriosApoiados() {
        return qtdReservatoriosApoiados;
    }

    public void setQtdReservatoriosApoiados(LongFilter qtdReservatoriosApoiados) {
        this.qtdReservatoriosApoiados = qtdReservatoriosApoiados;
    }

    public LongFilter getQtdCapacidadeReservatoriosApoiados() {
        return qtdCapacidadeReservatoriosApoiados;
    }

    public void setQtdCapacidadeReservatoriosApoiados(LongFilter qtdCapacidadeReservatoriosApoiados) {
        this.qtdCapacidadeReservatoriosApoiados = qtdCapacidadeReservatoriosApoiados;
    }

    public LongFilter getQtdReservatoriosElevados() {
        return qtdReservatoriosElevados;
    }

    public void setQtdReservatoriosElevados(LongFilter qtdReservatoriosElevados) {
        this.qtdReservatoriosElevados = qtdReservatoriosElevados;
    }

    public LongFilter getQtdCapacidadeReservatoriosElevados() {
        return qtdCapacidadeReservatoriosElevados;
    }

    public void setQtdCapacidadeReservatoriosElevados(LongFilter qtdCapacidadeReservatoriosElevados) {
        this.qtdCapacidadeReservatoriosElevados = qtdCapacidadeReservatoriosElevados;
    }

    public BigDecimalFilter getAlturaReservatoriosElevados() {
        return alturaReservatoriosElevados;
    }

    public void setAlturaReservatoriosElevados(BigDecimalFilter alturaReservatoriosElevados) {
        this.alturaReservatoriosElevados = alturaReservatoriosElevados;
    }

    public StringFilter getNmTpTratamentoAgua() {
        return nmTpTratamentoAgua;
    }

    public void setNmTpTratamentoAgua(StringFilter nmTpTratamentoAgua) {
        this.nmTpTratamentoAgua = nmTpTratamentoAgua;
    }

    public StringFilter getNmTpTratamentoPadraoUtilizado() {
        return nmTpTratamentoPadraoUtilizado;
    }

    public void setNmTpTratamentoPadraoUtilizado(StringFilter nmTpTratamentoPadraoUtilizado) {
        this.nmTpTratamentoPadraoUtilizado = nmTpTratamentoPadraoUtilizado;
    }

    public StringFilter getNmTpTratamentoBasicoUtilizado() {
        return nmTpTratamentoBasicoUtilizado;
    }

    public void setNmTpTratamentoBasicoUtilizado(StringFilter nmTpTratamentoBasicoUtilizado) {
        this.nmTpTratamentoBasicoUtilizado = nmTpTratamentoBasicoUtilizado;
    }

    public StringFilter getExisteAvariaSistemaTratamento() {
        return existeAvariaSistemaTratamento;
    }

    public void setExisteAvariaSistemaTratamento(StringFilter existeAvariaSistemaTratamento) {
        this.existeAvariaSistemaTratamento = existeAvariaSistemaTratamento;
    }

    public StringFilter getExisteMotivoAusenciaTratamento() {
        return existeMotivoAusenciaTratamento;
    }

    public void setExisteMotivoAusenciaTratamento(StringFilter existeMotivoAusenciaTratamento) {
        this.existeMotivoAusenciaTratamento = existeMotivoAusenciaTratamento;
    }

    public StringFilter getNmEquipamentosComAvaria() {
        return nmEquipamentosComAvaria;
    }

    public void setNmEquipamentosComAvaria(StringFilter nmEquipamentosComAvaria) {
        this.nmEquipamentosComAvaria = nmEquipamentosComAvaria;
    }

    public LongFilter getCaudalDoSistema() {
        return caudalDoSistema;
    }

    public void setCaudalDoSistema(LongFilter caudalDoSistema) {
        this.caudalDoSistema = caudalDoSistema;
    }

    public BigDecimalFilter getQtdConsumoPercaptaLitrosHomemDia() {
        return qtdConsumoPercaptaLitrosHomemDia;
    }

    public void setQtdConsumoPercaptaLitrosHomemDia(BigDecimalFilter qtdConsumoPercaptaLitrosHomemDia) {
        this.qtdConsumoPercaptaLitrosHomemDia = qtdConsumoPercaptaLitrosHomemDia;
    }

    public BigDecimalFilter getQtdDotacaoPercapta() {
        return qtdDotacaoPercapta;
    }

    public void setQtdDotacaoPercapta(BigDecimalFilter qtdDotacaoPercapta) {
        this.qtdDotacaoPercapta = qtdDotacaoPercapta;
    }

    public BigDecimalFilter getQtdDiariaHorasServicoSistema() {
        return qtdDiariaHorasServicoSistema;
    }

    public void setQtdDiariaHorasServicoSistema(BigDecimalFilter qtdDiariaHorasServicoSistema) {
        this.qtdDiariaHorasServicoSistema = qtdDiariaHorasServicoSistema;
    }

    public StringFilter getEsquema() {
        return esquema;
    }

    public void setEsquema(StringFilter esquema) {
        this.esquema = esquema;
    }

    public StringFilter getNmModeloBombaManualUtilizada() {
        return nmModeloBombaManualUtilizada;
    }

    public void setNmModeloBombaManualUtilizada(StringFilter nmModeloBombaManualUtilizada) {
        this.nmModeloBombaManualUtilizada = nmModeloBombaManualUtilizada;
    }

    public StringFilter getNmTpBombaEnergia() {
        return nmTpBombaEnergia;
    }

    public void setNmTpBombaEnergia(StringFilter nmTpBombaEnergia) {
        this.nmTpBombaEnergia = nmTpBombaEnergia;
    }

    public LongFilter getIdSituacaoId() {
        return idSituacaoId;
    }

    public void setIdSituacaoId(LongFilter idSituacaoId) {
        this.idSituacaoId = idSituacaoId;
    }

    public LongFilter getIdComunaId() {
        return idComunaId;
    }

    public void setIdComunaId(LongFilter idComunaId) {
        this.idComunaId = idComunaId;
    }

    public LongFilter getAdjudicacaoId() {
        return adjudicacaoId;
    }

    public void setAdjudicacaoId(LongFilter adjudicacaoId) {
        this.adjudicacaoId = adjudicacaoId;
    }

    public LongFilter getConcepcaoId() {
        return concepcaoId;
    }

    public void setConcepcaoId(LongFilter concepcaoId) {
        this.concepcaoId = concepcaoId;
    }

    public LongFilter getConcursoId() {
        return concursoId;
    }

    public void setConcursoId(LongFilter concursoId) {
        this.concursoId = concursoId;
    }

    public LongFilter getContratoId() {
        return contratoId;
    }

    public void setContratoId(LongFilter contratoId) {
        this.contratoId = contratoId;
    }

    public LongFilter getEmpreitadaId() {
        return empreitadaId;
    }

    public void setEmpreitadaId(LongFilter empreitadaId) {
        this.empreitadaId = empreitadaId;
    }

    public LongFilter getExecucaoId() {
        return execucaoId;
    }

    public void setExecucaoId(LongFilter execucaoId) {
        this.execucaoId = execucaoId;
    }

    public LongFilter getIndicadorProducaoId() {
        return indicadorProducaoId;
    }

    public void setIndicadorProducaoId(LongFilter indicadorProducaoId) {
        this.indicadorProducaoId = indicadorProducaoId;
    }

    public LongFilter getSistemaAguaLogId() {
        return sistemaAguaLogId;
    }

    public void setSistemaAguaLogId(LongFilter sistemaAguaLogId) {
        this.sistemaAguaLogId = sistemaAguaLogId;
    }

    @Override
    public String toString() {
        return "SistemaAguaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idSistemaAgua != null ? "idSistemaAgua=" + idSistemaAgua + ", " : "") +
                (idUsuario != null ? "idUsuario=" + idUsuario + ", " : "") +
                (nmInqueridor != null ? "nmInqueridor=" + nmInqueridor + ", " : "") +
                (dtLancamento != null ? "dtLancamento=" + dtLancamento + ", " : "") +
                (dtUltimaAlteracao != null ? "dtUltimaAlteracao=" + dtUltimaAlteracao + ", " : "") +
                (nmLocalidade != null ? "nmLocalidade=" + nmLocalidade + ", " : "") +
                (qtdPopulacaoActual != null ? "qtdPopulacaoActual=" + qtdPopulacaoActual + ", " : "") +
                (qtdCasasLocalidade != null ? "qtdCasasLocalidade=" + qtdCasasLocalidade + ", " : "") +
                (nmTpComunaAldeia != null ? "nmTpComunaAldeia=" + nmTpComunaAldeia + ", " : "") +
                (nmTpArea != null ? "nmTpArea=" + nmTpArea + ", " : "") +
                (possuiSistemaAgua != null ? "possuiSistemaAgua=" + possuiSistemaAgua + ", " : "") +
                (nmSistemaAgua != null ? "nmSistemaAgua=" + nmSistemaAgua + ", " : "") +
                (nmFonteAgua != null ? "nmFonteAgua=" + nmFonteAgua + ", " : "") +
                (latitude != null ? "latitude=" + latitude + ", " : "") +
                (longitude != null ? "longitude=" + longitude + ", " : "") +
                (altitude != null ? "altitude=" + altitude + ", " : "") +
                (nmTpFonte != null ? "nmTpFonte=" + nmTpFonte + ", " : "") +
                (nmFonteAguaUtilizada != null ? "nmFonteAguaUtilizada=" + nmFonteAguaUtilizada + ", " : "") +
                (nmTipoBomba != null ? "nmTipoBomba=" + nmTipoBomba + ", " : "") +
                (qtdCasasAguaLigada != null ? "qtdCasasAguaLigada=" + qtdCasasAguaLigada + ", " : "") +
                (qtdChafarisesFuncionando != null ? "qtdChafarisesFuncionando=" + qtdChafarisesFuncionando + ", " : "") +
                (qtdContadoresLigados != null ? "qtdContadoresLigados=" + qtdContadoresLigados + ", " : "") +
                (qtdBebedouros != null ? "qtdBebedouros=" + qtdBebedouros + ", " : "") +
                (qtdHabitantesAcessoServicoAgua != null ? "qtdHabitantesAcessoServicoAgua=" + qtdHabitantesAcessoServicoAgua + ", " : "") +
                (anoConstrucaoSistema != null ? "anoConstrucaoSistema=" + anoConstrucaoSistema + ", " : "") +
                (nmTpAvariaSistema != null ? "nmTpAvariaSistema=" + nmTpAvariaSistema + ", " : "") +
                (causaAvariaSistema != null ? "causaAvariaSistema=" + causaAvariaSistema + ", " : "") +
                (statusResolucao != null ? "statusResolucao=" + statusResolucao + ", " : "") +
                (tempoServicoDisponivel != null ? "tempoServicoDisponivel=" + tempoServicoDisponivel + ", " : "") +
                (qtdDiametroCondutaAdutoraAguaBruta != null ? "qtdDiametroCondutaAdutoraAguaBruta=" + qtdDiametroCondutaAdutoraAguaBruta + ", " : "") +
                (qtdComprimentoCondutaAdutoraAguaBruta != null ? "qtdComprimentoCondutaAdutoraAguaBruta=" + qtdComprimentoCondutaAdutoraAguaBruta + ", " : "") +
                (qtdDiametroCondutaAdutoraAguaTratada != null ? "qtdDiametroCondutaAdutoraAguaTratada=" + qtdDiametroCondutaAdutoraAguaTratada + ", " : "") +
                (qtdComprimentoCondutaAdutoraAguaTratada != null ? "qtdComprimentoCondutaAdutoraAguaTratada=" + qtdComprimentoCondutaAdutoraAguaTratada + ", " : "") +
                (descMaterialUtilizadoCondutas != null ? "descMaterialUtilizadoCondutas=" + descMaterialUtilizadoCondutas + ", " : "") +
                (qtdReservatoriosApoiados != null ? "qtdReservatoriosApoiados=" + qtdReservatoriosApoiados + ", " : "") +
                (qtdCapacidadeReservatoriosApoiados != null ? "qtdCapacidadeReservatoriosApoiados=" + qtdCapacidadeReservatoriosApoiados + ", " : "") +
                (qtdReservatoriosElevados != null ? "qtdReservatoriosElevados=" + qtdReservatoriosElevados + ", " : "") +
                (qtdCapacidadeReservatoriosElevados != null ? "qtdCapacidadeReservatoriosElevados=" + qtdCapacidadeReservatoriosElevados + ", " : "") +
                (alturaReservatoriosElevados != null ? "alturaReservatoriosElevados=" + alturaReservatoriosElevados + ", " : "") +
                (nmTpTratamentoAgua != null ? "nmTpTratamentoAgua=" + nmTpTratamentoAgua + ", " : "") +
                (nmTpTratamentoPadraoUtilizado != null ? "nmTpTratamentoPadraoUtilizado=" + nmTpTratamentoPadraoUtilizado + ", " : "") +
                (nmTpTratamentoBasicoUtilizado != null ? "nmTpTratamentoBasicoUtilizado=" + nmTpTratamentoBasicoUtilizado + ", " : "") +
                (existeAvariaSistemaTratamento != null ? "existeAvariaSistemaTratamento=" + existeAvariaSistemaTratamento + ", " : "") +
                (existeMotivoAusenciaTratamento != null ? "existeMotivoAusenciaTratamento=" + existeMotivoAusenciaTratamento + ", " : "") +
                (nmEquipamentosComAvaria != null ? "nmEquipamentosComAvaria=" + nmEquipamentosComAvaria + ", " : "") +
                (caudalDoSistema != null ? "caudalDoSistema=" + caudalDoSistema + ", " : "") +
                (qtdConsumoPercaptaLitrosHomemDia != null ? "qtdConsumoPercaptaLitrosHomemDia=" + qtdConsumoPercaptaLitrosHomemDia + ", " : "") +
                (qtdDotacaoPercapta != null ? "qtdDotacaoPercapta=" + qtdDotacaoPercapta + ", " : "") +
                (qtdDiariaHorasServicoSistema != null ? "qtdDiariaHorasServicoSistema=" + qtdDiariaHorasServicoSistema + ", " : "") +
                (esquema != null ? "esquema=" + esquema + ", " : "") +
                (nmModeloBombaManualUtilizada != null ? "nmModeloBombaManualUtilizada=" + nmModeloBombaManualUtilizada + ", " : "") +
                (nmTpBombaEnergia != null ? "nmTpBombaEnergia=" + nmTpBombaEnergia + ", " : "") +
                (idSituacaoId != null ? "situacao=" + idSituacaoId + ", " : "") +
                (idComunaId != null ? "comuna=" + idComunaId + ", " : "") +
                (adjudicacaoId != null ? "adjudicacaoId=" + adjudicacaoId + ", " : "") +
                (concepcaoId != null ? "concepcaoId=" + concepcaoId + ", " : "") +
                (concursoId != null ? "concursoId=" + concursoId + ", " : "") +
                (contratoId != null ? "contratoId=" + contratoId + ", " : "") +
                (empreitadaId != null ? "empreitadaId=" + empreitadaId + ", " : "") +
                (execucaoId != null ? "execucaoId=" + execucaoId + ", " : "") +
                (indicadorProducaoId != null ? "indicadorProducaoId=" + indicadorProducaoId + ", " : "") +
                (sistemaAguaLogId != null ? "sistemaAguaLogId=" + sistemaAguaLogId + ", " : "") +
            "}";
    }

}
