package com.minea.sisas.service.dto;

import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.io.Serializable;

/**
 * Criteria class for the Especialidades entity. This class is used in EspecialidadesResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /especialidadess?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EspecialidadesCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idEspecialidade;

    private StringFilter nmEspecialidade;

    public EspecialidadesCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(LongFilter idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public StringFilter getNmEspecialidade() {
        return nmEspecialidade;
    }

    public void setNmEspecialidade(StringFilter nmEspecialidade) {
        this.nmEspecialidade = nmEspecialidade;
    }

    @Override
    public String toString() {
        return "EspecialidadesCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (idEspecialidade != null ? "idEspecialidade=" + idEspecialidade + ", " : "") +
            (nmEspecialidade != null ? "nmEspecialidade=" + nmEspecialidade + ", " : "") +
            "}";
    }

}
