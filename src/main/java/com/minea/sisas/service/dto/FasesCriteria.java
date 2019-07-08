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
 * Criteria class for the Fases entity. This class is used in FasesResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /fases?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FasesCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idFase;

    private StringFilter descricaoFase;

    public FasesCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdFase() {
        return idFase;
    }

    public void setIdFase(LongFilter idFase) {
        this.idFase = idFase;
    }

    public StringFilter getDescricaoFase() {
        return descricaoFase;
    }

    public void setDescricaoFase(StringFilter descricaoFase) {
        this.descricaoFase = descricaoFase;
    }

    @Override
    public String toString() {
        return "FasesCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idFase != null ? "idFase=" + idFase + ", " : "") +
                (descricaoFase != null ? "descricaoFase=" + descricaoFase + ", " : "") +
            "}";
    }

}
