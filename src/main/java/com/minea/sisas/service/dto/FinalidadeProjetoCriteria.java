package com.minea.sisas.service.dto;

import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.io.Serializable;


/**
 * Criteria class for the FinalidadeProjeto entity. This class is used in FinalidadeProjetoResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /finalidade-projetos?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FinalidadeProjetoCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idFinalidadeProjeto;

    private StringFilter nmFinalidade;

    public FinalidadeProjetoCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdFinalidadeProjeto() {
        return idFinalidadeProjeto;
    }

    public void setIdFinalidadeProjeto(LongFilter idFinalidadeProjeto) {
        this.idFinalidadeProjeto = idFinalidadeProjeto;
    }

    public StringFilter getNmFinalidade() {
        return nmFinalidade;
    }

    public void setNmFinalidade(StringFilter nmFinalidade) {
        this.nmFinalidade = nmFinalidade;
    }

    @Override
    public String toString() {
        return "FinalidadeProjetoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idFinalidadeProjeto != null ? "idFinalidadeProjeto=" + idFinalidadeProjeto + ", " : "") +
                (nmFinalidade != null ? "nmFinalidade=" + nmFinalidade + ", " : "") +
            "}";
    }

}
