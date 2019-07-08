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
 * Criteria class for the Adjudicacao entity. This class is used in AdjudicacaoResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /adjudicacaos?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AdjudicacaoCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idAdjudicacao;

    private StringFilter tipoConcurso;

    private LocalDateFilter dtLancamento;

    private LocalDateFilter dtComunicaoAdjudicacao;

    private LocalDateFilter dtPrestacaoGarantBoaExec;

    private LocalDateFilter dtSubmissaoMinutContrato;

    private LongFilter idProgramasProjectosId;

    private LongFilter idSistemaAguaId;

    public AdjudicacaoCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdAdjudicacao() {
        return idAdjudicacao;
    }

    public void setIdAdjudicacao(LongFilter idAdjudicacao) {
        this.idAdjudicacao = idAdjudicacao;
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

    public LocalDateFilter getDtComunicaoAdjudicacao() {
        return dtComunicaoAdjudicacao;
    }

    public void setDtComunicaoAdjudicacao(LocalDateFilter dtComunicaoAdjudicacao) {
        this.dtComunicaoAdjudicacao = dtComunicaoAdjudicacao;
    }

    public LocalDateFilter getDtPrestacaoGarantBoaExec() {
        return dtPrestacaoGarantBoaExec;
    }

    public void setDtPrestacaoGarantBoaExec(LocalDateFilter dtPrestacaoGarantBoaExec) {
        this.dtPrestacaoGarantBoaExec = dtPrestacaoGarantBoaExec;
    }

    public LocalDateFilter getDtSubmissaoMinutContrato() {
        return dtSubmissaoMinutContrato;
    }

    public void setDtSubmissaoMinutContrato(LocalDateFilter dtSubmissaoMinutContrato) {
        this.dtSubmissaoMinutContrato = dtSubmissaoMinutContrato;
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
        return "AdjudicacaoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idAdjudicacao != null ? "idAdjudicacao=" + idAdjudicacao + ", " : "") +
                (tipoConcurso != null ? "tipoConcurso=" + tipoConcurso + ", " : "") +
                (dtLancamento != null ? "dtLancamento=" + dtLancamento + ", " : "") +
                (dtComunicaoAdjudicacao != null ? "dtComunicaoAdjudicacao=" + dtComunicaoAdjudicacao + ", " : "") +
                (dtPrestacaoGarantBoaExec != null ? "dtPrestacaoGarantBoaExec=" + dtPrestacaoGarantBoaExec + ", " : "") +
                (dtSubmissaoMinutContrato != null ? "dtSubmissaoMinutContrato=" + dtSubmissaoMinutContrato + ", " : "") +
                (idProgramasProjectosId != null ? "idProgramasProjectosId=" + idProgramasProjectosId + ", " : "") +
                (idSistemaAguaId != null ? "idSistemaAguaId=" + idSistemaAguaId + ", " : "") +
            "}";
    }

}
