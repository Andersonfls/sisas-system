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
 * Criteria class for the Municipio entity. This class is used in MunicipioResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /municipios?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MunicipioCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idMunicipio;

    private StringFilter nmMunicipio;

    private LongFilter idProvinciaId;

    private LongFilter comunaId;

    private LongFilter municipiosAtendidosId;

    public MunicipioCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(LongFilter idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public StringFilter getNmMunicipio() {
        return nmMunicipio;
    }

    public void setNmMunicipio(StringFilter nmMunicipio) {
        this.nmMunicipio = nmMunicipio;
    }

    public LongFilter getIdProvinciaId() {
        return idProvinciaId;
    }

    public void setIdProvinciaId(LongFilter idProvinciaId) {
        this.idProvinciaId = idProvinciaId;
    }

    public LongFilter getComunaId() {
        return comunaId;
    }

    public void setComunaId(LongFilter comunaId) {
        this.comunaId = comunaId;
    }

    public LongFilter getMunicipiosAtendidosId() {
        return municipiosAtendidosId;
    }

    public void setMunicipiosAtendidosId(LongFilter municipiosAtendidosId) {
        this.municipiosAtendidosId = municipiosAtendidosId;
    }

    @Override
    public String toString() {
        return "MunicipioCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idMunicipio != null ? "idMunicipio=" + idMunicipio + ", " : "") +
                (nmMunicipio != null ? "nmMunicipio=" + nmMunicipio + ", " : "") +
                (idProvinciaId != null ? "idProvinciaId=" + idProvinciaId + ", " : "") +
                (comunaId != null ? "comunaId=" + comunaId + ", " : "") +
                (municipiosAtendidosId != null ? "municipiosAtendidosId=" + municipiosAtendidosId + ", " : "") +
            "}";
    }

}
