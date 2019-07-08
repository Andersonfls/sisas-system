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
 * Criteria class for the SobreDna entity. This class is used in SobreDnaResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /sobre-dnas?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SobreDnaCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idSobreDna;

    private StringFilter tituloSobreDna;

    private StringFilter textoSobreDna;

    private StringFilter resumoTextoSobreDna;

    private LongFilter idSituacaoId;

    private LongFilter inicioId;

    public SobreDnaCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdSobreDna() {
        return idSobreDna;
    }

    public void setIdSobreDna(LongFilter idSobreDna) {
        this.idSobreDna = idSobreDna;
    }

    public StringFilter getTituloSobreDna() {
        return tituloSobreDna;
    }

    public void setTituloSobreDna(StringFilter tituloSobreDna) {
        this.tituloSobreDna = tituloSobreDna;
    }

    public StringFilter getTextoSobreDna() {
        return textoSobreDna;
    }

    public void setTextoSobreDna(StringFilter textoSobreDna) {
        this.textoSobreDna = textoSobreDna;
    }

    public StringFilter getResumoTextoSobreDna() {
        return resumoTextoSobreDna;
    }

    public void setResumoTextoSobreDna(StringFilter resumoTextoSobreDna) {
        this.resumoTextoSobreDna = resumoTextoSobreDna;
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
        return "SobreDnaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idSobreDna != null ? "idSobreDna=" + idSobreDna + ", " : "") +
                (tituloSobreDna != null ? "tituloSobreDna=" + tituloSobreDna + ", " : "") +
                (textoSobreDna != null ? "textoSobreDna=" + textoSobreDna + ", " : "") +
                (resumoTextoSobreDna != null ? "resumoTextoSobreDna=" + resumoTextoSobreDna + ", " : "") +
                (idSituacaoId != null ? "situacao=" + idSituacaoId + ", " : "") +
                (inicioId != null ? "inicioId=" + inicioId + ", " : "") +
            "}";
    }

}
