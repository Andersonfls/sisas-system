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
 * Criteria class for the Contrato entity. This class is used in ContratoResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /contratoes?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ContratoCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idContrato;

    private StringFilter tipoEmpreitada;

    private LocalDateFilter dtLancamento;

    private StringFilter nmEmpresaAdjudicitaria;

    private BigDecimalFilter valorContrato;

    private LocalDateFilter dtAssinatura;

    private LocalDateFilter dtFinalizacaoProcessoHomologAprov;

    private StringFilter tipoMoeda;

    private BigDecimalFilter valorAdiantamento;

    private LocalDateFilter dtAdiantamento;

    private LocalDateFilter dtInicio;

    private BigDecimalFilter prazoExecucao;

    private LocalDateFilter dtRecepcaoProvisoria;

    private LocalDateFilter dtRecepcaoDefinitiva;

    private LocalDateFilter dtRecepcaoComicionamento;

    private StringFilter nmResposavelAntProjeto;

    private StringFilter nmResposavelProjeto;

    private LongFilter idProgramasProjectosId;

    private LongFilter idSistemaAguaId;

    private LongFilter empreitadaId;

    private LongFilter execucaoId;

    public ContratoCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(LongFilter idContrato) {
        this.idContrato = idContrato;
    }

    public StringFilter getTipoEmpreitada() {
        return tipoEmpreitada;
    }

    public void setTipoEmpreitada(StringFilter tipoEmpreitada) {
        this.tipoEmpreitada = tipoEmpreitada;
    }

    public LocalDateFilter getDtLancamento() {
        return dtLancamento;
    }

    public void setDtLancamento(LocalDateFilter dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public StringFilter getNmEmpresaAdjudicitaria() {
        return nmEmpresaAdjudicitaria;
    }

    public void setNmEmpresaAdjudicitaria(StringFilter nmEmpresaAdjudicitaria) {
        this.nmEmpresaAdjudicitaria = nmEmpresaAdjudicitaria;
    }

    public BigDecimalFilter getValorContrato() {
        return valorContrato;
    }

    public void setValorContrato(BigDecimalFilter valorContrato) {
        this.valorContrato = valorContrato;
    }

    public LocalDateFilter getDtAssinatura() {
        return dtAssinatura;
    }

    public void setDtAssinatura(LocalDateFilter dtAssinatura) {
        this.dtAssinatura = dtAssinatura;
    }

    public LocalDateFilter getDtFinalizacaoProcessoHomologAprov() {
        return dtFinalizacaoProcessoHomologAprov;
    }

    public void setDtFinalizacaoProcessoHomologAprov(LocalDateFilter dtFinalizacaoProcessoHomologAprov) {
        this.dtFinalizacaoProcessoHomologAprov = dtFinalizacaoProcessoHomologAprov;
    }

    public StringFilter getTipoMoeda() {
        return tipoMoeda;
    }

    public void setTipoMoeda(StringFilter tipoMoeda) {
        this.tipoMoeda = tipoMoeda;
    }

    public BigDecimalFilter getValorAdiantamento() {
        return valorAdiantamento;
    }

    public void setValorAdiantamento(BigDecimalFilter valorAdiantamento) {
        this.valorAdiantamento = valorAdiantamento;
    }

    public LocalDateFilter getDtAdiantamento() {
        return dtAdiantamento;
    }

    public void setDtAdiantamento(LocalDateFilter dtAdiantamento) {
        this.dtAdiantamento = dtAdiantamento;
    }

    public LocalDateFilter getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(LocalDateFilter dtInicio) {
        this.dtInicio = dtInicio;
    }

    public BigDecimalFilter getPrazoExecucao() {
        return prazoExecucao;
    }

    public void setPrazoExecucao(BigDecimalFilter prazoExecucao) {
        this.prazoExecucao = prazoExecucao;
    }

    public LocalDateFilter getDtRecepcaoProvisoria() {
        return dtRecepcaoProvisoria;
    }

    public void setDtRecepcaoProvisoria(LocalDateFilter dtRecepcaoProvisoria) {
        this.dtRecepcaoProvisoria = dtRecepcaoProvisoria;
    }

    public LocalDateFilter getDtRecepcaoDefinitiva() {
        return dtRecepcaoDefinitiva;
    }

    public void setDtRecepcaoDefinitiva(LocalDateFilter dtRecepcaoDefinitiva) {
        this.dtRecepcaoDefinitiva = dtRecepcaoDefinitiva;
    }

    public LocalDateFilter getDtRecepcaoComicionamento() {
        return dtRecepcaoComicionamento;
    }

    public void setDtRecepcaoComicionamento(LocalDateFilter dtRecepcaoComicionamento) {
        this.dtRecepcaoComicionamento = dtRecepcaoComicionamento;
    }

    public StringFilter getNmResposavelAntProjeto() {
        return nmResposavelAntProjeto;
    }

    public void setNmResposavelAntProjeto(StringFilter nmResposavelAntProjeto) {
        this.nmResposavelAntProjeto = nmResposavelAntProjeto;
    }

    public StringFilter getNmResposavelProjeto() {
        return nmResposavelProjeto;
    }

    public void setNmResposavelProjeto(StringFilter nmResposavelProjeto) {
        this.nmResposavelProjeto = nmResposavelProjeto;
    }

    public LongFilter getIdProgramasProjectosId() {
        return idProgramasProjectosId;
    }

    public void setIdProgramasProjectosId(LongFilter idProgramasProjectosId) {
        this.idProgramasProjectosId = idProgramasProjectosId;
    }

    public LongFilter getIdSistemaAguaId() {
        return idSistemaAguaId;
    }

    public void setIdSistemaAguaId(LongFilter idSistemaAguaId) {
        this.idSistemaAguaId = idSistemaAguaId;
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

    @Override
    public String toString() {
        return "ContratoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idContrato != null ? "idContrato=" + idContrato + ", " : "") +
                (tipoEmpreitada != null ? "tipoEmpreitada=" + tipoEmpreitada + ", " : "") +
                (dtLancamento != null ? "dtLancamento=" + dtLancamento + ", " : "") +
                (nmEmpresaAdjudicitaria != null ? "nmEmpresaAdjudicitaria=" + nmEmpresaAdjudicitaria + ", " : "") +
                (valorContrato != null ? "valorContrato=" + valorContrato + ", " : "") +
                (dtAssinatura != null ? "dtAssinatura=" + dtAssinatura + ", " : "") +
                (dtFinalizacaoProcessoHomologAprov != null ? "dtFinalizacaoProcessoHomologAprov=" + dtFinalizacaoProcessoHomologAprov + ", " : "") +
                (tipoMoeda != null ? "tipoMoeda=" + tipoMoeda + ", " : "") +
                (valorAdiantamento != null ? "valorAdiantamento=" + valorAdiantamento + ", " : "") +
                (dtAdiantamento != null ? "dtAdiantamento=" + dtAdiantamento + ", " : "") +
                (dtInicio != null ? "dtInicio=" + dtInicio + ", " : "") +
                (prazoExecucao != null ? "prazoExecucao=" + prazoExecucao + ", " : "") +
                (dtRecepcaoProvisoria != null ? "dtRecepcaoProvisoria=" + dtRecepcaoProvisoria + ", " : "") +
                (dtRecepcaoDefinitiva != null ? "dtRecepcaoDefinitiva=" + dtRecepcaoDefinitiva + ", " : "") +
                (dtRecepcaoComicionamento != null ? "dtRecepcaoComicionamento=" + dtRecepcaoComicionamento + ", " : "") +
                (nmResposavelAntProjeto != null ? "nmResposavelAntProjeto=" + nmResposavelAntProjeto + ", " : "") +
                (nmResposavelProjeto != null ? "nmResposavelProjeto=" + nmResposavelProjeto + ", " : "") +
                (idProgramasProjectosId != null ? "idProgramasProjectosId=" + idProgramasProjectosId + ", " : "") +
                (idSistemaAguaId != null ? "idSistemaAguaId=" + idSistemaAguaId + ", " : "") +
                (empreitadaId != null ? "empreitadaId=" + empreitadaId + ", " : "") +
                (execucaoId != null ? "execucaoId=" + execucaoId + ", " : "") +
            "}";
    }

}
