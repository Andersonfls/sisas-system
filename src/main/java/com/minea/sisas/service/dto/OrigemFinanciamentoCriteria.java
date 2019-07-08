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
 * Criteria class for the OrigemFinanciamento entity. This class is used in OrigemFinanciamentoResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /origem-financiamentos?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OrigemFinanciamentoCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idOrigemFinanciamento;

    private StringFilter descricaoOrigemFinanciamento;

    public OrigemFinanciamentoCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdOrigemFinanciamento() {
        return idOrigemFinanciamento;
    }

    public void setIdOrigemFinanciamento(LongFilter idOrigemFinanciamento) {
        this.idOrigemFinanciamento = idOrigemFinanciamento;
    }

    public StringFilter getDescricaoOrigemFinanciamento() {
        return descricaoOrigemFinanciamento;
    }

    public void setDescricaoOrigemFinanciamento(StringFilter descricaoOrigemFinanciamento) {
        this.descricaoOrigemFinanciamento = descricaoOrigemFinanciamento;
    }

    @Override
    public String toString() {
        return "OrigemFinanciamentoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idOrigemFinanciamento != null ? "idOrigemFinanciamento=" + idOrigemFinanciamento + ", " : "") +
                (descricaoOrigemFinanciamento != null ? "descricaoOrigemFinanciamento=" + descricaoOrigemFinanciamento + ", " : "") +
            "}";
    }

}
