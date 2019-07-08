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
 * Criteria class for the Provincia entity. This class is used in ProvinciaResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /provincias?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ProvinciaCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idProvincia;

    private StringFilter nmProvincia;

    private LongFilter municipioId;

    public ProvinciaCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(LongFilter idProvincia) {
        this.idProvincia = idProvincia;
    }

    public StringFilter getNmProvincia() {
        return nmProvincia;
    }

    public void setNmProvincia(StringFilter nmProvincia) {
        this.nmProvincia = nmProvincia;
    }

    public LongFilter getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(LongFilter municipioId) {
        this.municipioId = municipioId;
    }

    @Override
    public String toString() {
        return "ProvinciaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idProvincia != null ? "provincia=" + idProvincia + ", " : "") +
                (nmProvincia != null ? "nmProvincia=" + nmProvincia + ", " : "") +
                (municipioId != null ? "municipioId=" + municipioId + ", " : "") +
            "}";
    }

}
