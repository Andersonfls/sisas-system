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
 * Criteria class for the PublicacaoLog entity. This class is used in PublicacaoLogResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /publicacao-logs?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PublicacaoLogCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idPublicacaoLog;

    private StringFilter acao;

    private LongFilter idUsuario;

    private StringFilter log;

    private LocalDateFilter dtLog;

    public PublicacaoLogCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdPublicacaoLog() {
        return idPublicacaoLog;
    }

    public void setIdPublicacaoLog(LongFilter idPublicacaoLog) {
        this.idPublicacaoLog = idPublicacaoLog;
    }

    public StringFilter getAcao() {
        return acao;
    }

    public void setAcao(StringFilter acao) {
        this.acao = acao;
    }

    public LongFilter getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(LongFilter idUsuario) {
        this.idUsuario = idUsuario;
    }

    public StringFilter getLog() {
        return log;
    }

    public void setLog(StringFilter log) {
        this.log = log;
    }

    public LocalDateFilter getDtLog() {
        return dtLog;
    }

    public void setDtLog(LocalDateFilter dtLog) {
        this.dtLog = dtLog;
    }

    @Override
    public String toString() {
        return "PublicacaoLogCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idPublicacaoLog != null ? "idPublicacaoLog=" + idPublicacaoLog + ", " : "") +
                (acao != null ? "acao=" + acao + ", " : "") +
                (idUsuario != null ? "idUsuario=" + idUsuario + ", " : "") +
                (log != null ? "log=" + log + ", " : "") +
                (dtLog != null ? "dtLog=" + dtLog + ", " : "") +
            "}";
    }

}
