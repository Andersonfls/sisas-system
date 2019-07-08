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
 * Criteria class for the Concurso entity. This class is used in ConcursoResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /concursos?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ConcursoCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idConcurso;

    private StringFilter tipoConcurso;

    private LocalDateFilter dtLancamento;

    private LocalDateFilter dtUltimaAlteracao;

    private LocalDateFilter dtEntregaProposta;

    private LocalDateFilter dtAberturaProposta;

    private LocalDateFilter dtConclusaoAvaliacaoRelPrel;

    private LocalDateFilter dtNegociacao;

    private LocalDateFilter dtAprovRelAvalFinal;

    private LongFilter idProgramasProjectosId;

    private LongFilter idSistemaAguaId;

    public ConcursoCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(LongFilter idConcurso) {
        this.idConcurso = idConcurso;
    }

    public StringFilter getTipoConcurso() {
        return tipoConcurso;
    }

    public void setTipoConcurso(StringFilter tipoConcurso) {
        this.tipoConcurso = tipoConcurso;
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

    public LocalDateFilter getDtEntregaProposta() {
        return dtEntregaProposta;
    }

    public void setDtEntregaProposta(LocalDateFilter dtEntregaProposta) {
        this.dtEntregaProposta = dtEntregaProposta;
    }

    public LocalDateFilter getDtAberturaProposta() {
        return dtAberturaProposta;
    }

    public void setDtAberturaProposta(LocalDateFilter dtAberturaProposta) {
        this.dtAberturaProposta = dtAberturaProposta;
    }

    public LocalDateFilter getDtConclusaoAvaliacaoRelPrel() {
        return dtConclusaoAvaliacaoRelPrel;
    }

    public void setDtConclusaoAvaliacaoRelPrel(LocalDateFilter dtConclusaoAvaliacaoRelPrel) {
        this.dtConclusaoAvaliacaoRelPrel = dtConclusaoAvaliacaoRelPrel;
    }

    public LocalDateFilter getDtNegociacao() {
        return dtNegociacao;
    }

    public void setDtNegociacao(LocalDateFilter dtNegociacao) {
        this.dtNegociacao = dtNegociacao;
    }

    public LocalDateFilter getDtAprovRelAvalFinal() {
        return dtAprovRelAvalFinal;
    }

    public void setDtAprovRelAvalFinal(LocalDateFilter dtAprovRelAvalFinal) {
        this.dtAprovRelAvalFinal = dtAprovRelAvalFinal;
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

    @Override
    public String toString() {
        return "ConcursoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idConcurso != null ? "idConcurso=" + idConcurso + ", " : "") +
                (tipoConcurso != null ? "tipoConcurso=" + tipoConcurso + ", " : "") +
                (dtLancamento != null ? "dtLancamento=" + dtLancamento + ", " : "") +
                (dtUltimaAlteracao != null ? "dtUltimaAlteracao=" + dtUltimaAlteracao + ", " : "") +
                (dtEntregaProposta != null ? "dtEntregaProposta=" + dtEntregaProposta + ", " : "") +
                (dtAberturaProposta != null ? "dtAberturaProposta=" + dtAberturaProposta + ", " : "") +
                (dtConclusaoAvaliacaoRelPrel != null ? "dtConclusaoAvaliacaoRelPrel=" + dtConclusaoAvaliacaoRelPrel + ", " : "") +
                (dtNegociacao != null ? "dtNegociacao=" + dtNegociacao + ", " : "") +
                (dtAprovRelAvalFinal != null ? "dtAprovRelAvalFinal=" + dtAprovRelAvalFinal + ", " : "") +
                (idProgramasProjectosId != null ? "idProgramasProjectosId=" + idProgramasProjectosId + ", " : "") +
                (idSistemaAguaId != null ? "idSistemaAguaId=" + idSistemaAguaId + ", " : "") +
            "}";
    }

}
