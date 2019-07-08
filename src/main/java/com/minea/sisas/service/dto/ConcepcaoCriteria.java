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
 * Criteria class for the Concepcao entity. This class is used in ConcepcaoResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /concepcaos?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ConcepcaoCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idConcepcao;

    private StringFilter tipoConcurso;

    private LocalDateFilter dtLancamento;

    private LocalDateFilter dtUltimaAlteracao;

    private LocalDateFilter dtElaboracaoCon;

    private LocalDateFilter dtAprovacaoCon;

    private LongFilter idProgramasProjectosId;

    private LongFilter idSistemaAguaId;

    public ConcepcaoCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdConcepcao() {
        return idConcepcao;
    }

    public void setIdConcepcao(LongFilter idConcepcao) {
        this.idConcepcao = idConcepcao;
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

    public LocalDateFilter getDtElaboracaoCon() {
        return dtElaboracaoCon;
    }

    public void setDtElaboracaoCon(LocalDateFilter dtElaboracaoCon) {
        this.dtElaboracaoCon = dtElaboracaoCon;
    }

    public LocalDateFilter getDtAprovacaoCon() {
        return dtAprovacaoCon;
    }

    public void setDtAprovacaoCon(LocalDateFilter dtAprovacaoCon) {
        this.dtAprovacaoCon = dtAprovacaoCon;
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
        return "ConcepcaoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idConcepcao != null ? "idConcepcao=" + idConcepcao + ", " : "") +
                (tipoConcurso != null ? "tipoConcurso=" + tipoConcurso + ", " : "") +
                (dtLancamento != null ? "dtLancamento=" + dtLancamento + ", " : "") +
                (dtUltimaAlteracao != null ? "dtUltimaAlteracao=" + dtUltimaAlteracao + ", " : "") +
                (dtElaboracaoCon != null ? "dtElaboracaoCon=" + dtElaboracaoCon + ", " : "") +
                (dtAprovacaoCon != null ? "dtAprovacaoCon=" + dtAprovacaoCon + ", " : "") +
                (idProgramasProjectosId != null ? "idProgramasProjectosId=" + idProgramasProjectosId + ", " : "") +
                (idSistemaAguaId != null ? "idSistemaAguaId=" + idSistemaAguaId + ", " : "") +
            "}";
    }

}
