package com.minea.sisas.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;






/**
 * Criteria class for the Projectos entity. This class is used in ProjectosResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /projectos?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ProjectosCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idProjectos;

    private StringFilter nmProjectos;

    private StringFilter textoProjectos;

    private StringFilter resumoTextoProjectos;

    private LongFilter idSituacaoId;

    private LongFilter inicioId;

    public ProjectosCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdProjectos() {
        return idProjectos;
    }

    public void setIdProjectos(LongFilter idProjectos) {
        this.idProjectos = idProjectos;
    }

    public StringFilter getNmProjectos() {
        return nmProjectos;
    }

    public void setNmProjectos(StringFilter nmProjectos) {
        this.nmProjectos = nmProjectos;
    }

    public StringFilter getTextoProjectos() {
        return textoProjectos;
    }

    public void setTextoProjectos(StringFilter textoProjectos) {
        this.textoProjectos = textoProjectos;
    }

    public StringFilter getResumoTextoProjectos() {
        return resumoTextoProjectos;
    }

    public void setResumoTextoProjectos(StringFilter resumoTextoProjectos) {
        this.resumoTextoProjectos = resumoTextoProjectos;
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
        return "ProjectosCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idProjectos != null ? "idProjectos=" + idProjectos + ", " : "") +
                (nmProjectos != null ? "nmProjectos=" + nmProjectos + ", " : "") +
                (textoProjectos != null ? "textoProjectos=" + textoProjectos + ", " : "") +
                (resumoTextoProjectos != null ? "resumoTextoProjectos=" + resumoTextoProjectos + ", " : "") +
                (idSituacaoId != null ? "situacao=" + idSituacaoId + ", " : "") +
                (inicioId != null ? "inicioId=" + inicioId + ", " : "") +
            "}";
    }

}
