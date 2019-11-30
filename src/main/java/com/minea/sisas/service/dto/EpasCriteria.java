package com.minea.sisas.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the Epas entity. This class is used in EpasResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /epass?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EpasCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idEpas;

    private StringFilter nmEpas;

    private StringFilter nrContribuinte;

    private LongFilter provincia;

    private LongFilter municipio;

    private LongFilter comuna;

    private LongFilter nmLocalidade;

    public EpasCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdEpas() {
        return idEpas;
    }

    public void setIdEpas(LongFilter idEpas) {
        this.idEpas = idEpas;
    }

    public StringFilter getNmEpas() {
        return nmEpas;
    }

    public void setNmEpas(StringFilter nmEpas) {
        this.nmEpas = nmEpas;
    }

    public StringFilter getNrContribuinte() {
        return nrContribuinte;
    }

    public void setNrContribuinte(StringFilter nrContribuinte) {
        this.nrContribuinte = nrContribuinte;
    }

    public LongFilter getProvincia() {
        return provincia;
    }

    public void setProvincia(LongFilter provincia) {
        this.provincia = provincia;
    }

    public LongFilter getMunicipio() {
        return municipio;
    }

    public void setMunicipio(LongFilter municipio) {
        this.municipio = municipio;
    }

    public LongFilter getComuna() {
        return comuna;
    }

    public void setComuna(LongFilter comuna) {
        this.comuna = comuna;
    }

    public LongFilter getNmLocalidade() {
        return nmLocalidade;
    }

    public void setNmLocalidade(LongFilter nmLocalidade) {
        this.nmLocalidade = nmLocalidade;
    }

    @Override
    public String toString() {
        return "EpasCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (idEpas != null ? "idEpas=" + idEpas + ", " : "") +
            (nmEpas != null ? "nmEpas=" + nmEpas + ", " : "") +
            (nrContribuinte != null ? "nrContribuinte=" + nrContribuinte + ", " : "") +
            (provincia != null ? "provincia=" + provincia + ", " : "") +
            (municipio != null ? "municipio=" + municipio + ", " : "") +
            (comuna != null ? "comuna=" + comuna + ", " : "") +
            (nmLocalidade != null ? "nmLocalidade=" + nmLocalidade + ", " : "") +
            "}";
    }

}
