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
 * Criteria class for the Comuna entity. This class is used in ComunaResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /comunas?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ComunaCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idComuna;

    private StringFilter nmComuna;

    private LongFilter municipio;

    public ComunaCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(LongFilter idComuna) {
        this.idComuna = idComuna;
    }

    public StringFilter getNmComuna() {
        return nmComuna;
    }

    public void setNmComuna(StringFilter nmComuna) {
        this.nmComuna = nmComuna;
    }

    public LongFilter getMunicipio() {
        return municipio;
    }

    public void setMunicipio(LongFilter municipio) {
        this.municipio = municipio;
    }

    @Override
    public String toString() {
        return "ComunaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idComuna != null ? "idComuna=" + idComuna + ", " : "") +
                (nmComuna != null ? "nmComuna=" + nmComuna + ", " : "") +
                (municipio != null ? "municipio=" + municipio + ", " : "") +
            "}";
    }

}
