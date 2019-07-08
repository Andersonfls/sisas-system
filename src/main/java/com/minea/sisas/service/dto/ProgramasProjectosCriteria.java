package com.minea.sisas.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;


import io.github.jhipster.service.filter.LocalDateFilter;



/**
 * Criteria class for the ProgramasProjectos entity. This class is used in ProgramasProjectosResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /programas-projectos?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ProgramasProjectosCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idProgramasProjectos;

    private LocalDateFilter dtLancamento;

    private LocalDateFilter dtUltimaAlteracao;

    private LongFilter idUsuario;

    private StringFilter nmDesignacaoProjeto;

    private StringFilter nmDescricaoProjeto;

    private LongFilter idSaaAssociado;

    private StringFilter tipoFinanciamento;

    private StringFilter especialidade;

    private LongFilter idComunaId;

    private LongFilter adjudicacaoId;

    private LongFilter concepcaoId;

    private LongFilter concursoId;

    private LongFilter contratoId;

    private LongFilter empreitadaId;

    private LongFilter execucaoId;

    private LongFilter programasProjectosLogId;

    public ProgramasProjectosCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdProgramasProjectos() {
        return idProgramasProjectos;
    }

    public void setIdProgramasProjectos(LongFilter idProgramasProjectos) {
        this.idProgramasProjectos = idProgramasProjectos;
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

    public LongFilter getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(LongFilter idUsuario) {
        this.idUsuario = idUsuario;
    }

    public StringFilter getNmDesignacaoProjeto() {
        return nmDesignacaoProjeto;
    }

    public void setNmDesignacaoProjeto(StringFilter nmDesignacaoProjeto) {
        this.nmDesignacaoProjeto = nmDesignacaoProjeto;
    }

    public StringFilter getNmDescricaoProjeto() {
        return nmDescricaoProjeto;
    }

    public void setNmDescricaoProjeto(StringFilter nmDescricaoProjeto) {
        this.nmDescricaoProjeto = nmDescricaoProjeto;
    }

    public LongFilter getIdSaaAssociado() {
        return idSaaAssociado;
    }

    public void setIdSaaAssociado(LongFilter idSaaAssociado) {
        this.idSaaAssociado = idSaaAssociado;
    }

    public StringFilter getTipoFinanciamento() {
        return tipoFinanciamento;
    }

    public void setTipoFinanciamento(StringFilter tipoFinanciamento) {
        this.tipoFinanciamento = tipoFinanciamento;
    }

    public StringFilter getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(StringFilter especialidade) {
        this.especialidade = especialidade;
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

    public LongFilter getProgramasProjectosLogId() {
        return programasProjectosLogId;
    }

    public void setProgramasProjectosLogId(LongFilter programasProjectosLogId) {
        this.programasProjectosLogId = programasProjectosLogId;
    }

    @Override
    public String toString() {
        return "ProgramasProjectosCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idProgramasProjectos != null ? "idProgramasProjectos=" + idProgramasProjectos + ", " : "") +
                (dtLancamento != null ? "dtLancamento=" + dtLancamento + ", " : "") +
                (dtUltimaAlteracao != null ? "dtUltimaAlteracao=" + dtUltimaAlteracao + ", " : "") +
                (idUsuario != null ? "idUsuario=" + idUsuario + ", " : "") +
                (nmDesignacaoProjeto != null ? "nmDesignacaoProjeto=" + nmDesignacaoProjeto + ", " : "") +
                (nmDescricaoProjeto != null ? "nmDescricaoProjeto=" + nmDescricaoProjeto + ", " : "") +
                (idSaaAssociado != null ? "idSaaAssociado=" + idSaaAssociado + ", " : "") +
                (tipoFinanciamento != null ? "tipoFinanciamento=" + tipoFinanciamento + ", " : "") +
                (especialidade != null ? "especialidade=" + especialidade + ", " : "") +
                (idComunaId != null ? "comuna=" + idComunaId + ", " : "") +
                (adjudicacaoId != null ? "adjudicacaoId=" + adjudicacaoId + ", " : "") +
                (concepcaoId != null ? "concepcaoId=" + concepcaoId + ", " : "") +
                (concursoId != null ? "concursoId=" + concursoId + ", " : "") +
                (contratoId != null ? "contratoId=" + contratoId + ", " : "") +
                (empreitadaId != null ? "empreitadaId=" + empreitadaId + ", " : "") +
                (execucaoId != null ? "execucaoId=" + execucaoId + ", " : "") +
                (programasProjectosLogId != null ? "programasProjectosLogId=" + programasProjectosLogId + ", " : "") +
            "}";
    }

}
