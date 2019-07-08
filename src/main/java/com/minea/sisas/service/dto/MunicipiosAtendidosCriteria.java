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
 * Criteria class for the MunicipiosAtendidos entity. This class is used in MunicipiosAtendidosResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /municipios-atendidos?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MunicipiosAtendidosCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idMunicipioAtendido;

    private LongFilter idMunicipioId;

    private LongFilter entidadeGestoraId;

    public MunicipiosAtendidosCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdMunicipioAtendido() {
        return idMunicipioAtendido;
    }

    public void setIdMunicipioAtendido(LongFilter idMunicipioAtendido) {
        this.idMunicipioAtendido = idMunicipioAtendido;
    }

    public LongFilter getIdMunicipioId() {
        return idMunicipioId;
    }

    public void setIdMunicipioId(LongFilter idMunicipioId) {
        this.idMunicipioId = idMunicipioId;
    }

    public LongFilter getEntidadeGestoraId() {
        return entidadeGestoraId;
    }

    public void setEntidadeGestoraId(LongFilter entidadeGestoraId) {
        this.entidadeGestoraId = entidadeGestoraId;
    }

    @Override
    public String toString() {
        return "MunicipiosAtendidosCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idMunicipioAtendido != null ? "idMunicipioAtendido=" + idMunicipioAtendido + ", " : "") +
                (idMunicipioId != null ? "idMunicipioId=" + idMunicipioId + ", " : "") +
                (entidadeGestoraId != null ? "entidadeGestoraId=" + entidadeGestoraId + ", " : "") +
            "}";
    }

}
