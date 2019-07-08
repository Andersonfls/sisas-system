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
 * Criteria class for the Contactos entity. This class is used in ContactosResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /contactos?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ContactosCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idContactos;

    private StringFilter nmContactos;

    private StringFilter textoContactos;

    private StringFilter resumoTextoContactos;

    private LocalDateFilter dtLancamento;

    private LocalDateFilter dtUltimaAlteracao;

    private LongFilter idSituacaoId;

    private LongFilter inicioId;

    public ContactosCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdContactos() {
        return idContactos;
    }

    public void setIdContactos(LongFilter idContactos) {
        this.idContactos = idContactos;
    }

    public StringFilter getNmContactos() {
        return nmContactos;
    }

    public void setNmContactos(StringFilter nmContactos) {
        this.nmContactos = nmContactos;
    }

    public StringFilter getTextoContactos() {
        return textoContactos;
    }

    public void setTextoContactos(StringFilter textoContactos) {
        this.textoContactos = textoContactos;
    }

    public StringFilter getResumoTextoContactos() {
        return resumoTextoContactos;
    }

    public void setResumoTextoContactos(StringFilter resumoTextoContactos) {
        this.resumoTextoContactos = resumoTextoContactos;
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

    public LongFilter getIdSituacaoId() {
        return idSituacaoId;
    }

    public void setIdSituacaoId(LongFilter idSituacaoId) {
        this.idSituacaoId = idSituacaoId;
    }

    public LongFilter getInicioId() {
        return inicioId;
    }

    public void setInicioId(LongFilter inicioId) {
        this.inicioId = inicioId;
    }

    @Override
    public String toString() {
        return "ContactosCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idContactos != null ? "idContactos=" + idContactos + ", " : "") +
                (nmContactos != null ? "nmContactos=" + nmContactos + ", " : "") +
                (textoContactos != null ? "textoContactos=" + textoContactos + ", " : "") +
                (resumoTextoContactos != null ? "resumoTextoContactos=" + resumoTextoContactos + ", " : "") +
                (dtLancamento != null ? "dtLancamento=" + dtLancamento + ", " : "") +
                (dtUltimaAlteracao != null ? "dtUltimaAlteracao=" + dtUltimaAlteracao + ", " : "") +
                (idSituacaoId != null ? "situacao=" + idSituacaoId + ", " : "") +
                (inicioId != null ? "inicioId=" + inicioId + ", " : "") +
            "}";
    }

}
