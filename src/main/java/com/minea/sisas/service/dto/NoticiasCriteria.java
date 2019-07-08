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
 * Criteria class for the Noticias entity. This class is used in NoticiasResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /noticias?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NoticiasCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idNoticias;

    private StringFilter tituloNoticias;

    private StringFilter textoNoticias;

    private StringFilter resumoTextoNoticias;

    private LongFilter idSituacaoId;

    private LongFilter inicioId;

    public NoticiasCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdNoticias() {
        return idNoticias;
    }

    public void setIdNoticias(LongFilter idNoticias) {
        this.idNoticias = idNoticias;
    }

    public StringFilter getTituloNoticias() {
        return tituloNoticias;
    }

    public void setTituloNoticias(StringFilter tituloNoticias) {
        this.tituloNoticias = tituloNoticias;
    }

    public StringFilter getTextoNoticias() {
        return textoNoticias;
    }

    public void setTextoNoticias(StringFilter textoNoticias) {
        this.textoNoticias = textoNoticias;
    }

    public StringFilter getResumoTextoNoticias() {
        return resumoTextoNoticias;
    }

    public void setResumoTextoNoticias(StringFilter resumoTextoNoticias) {
        this.resumoTextoNoticias = resumoTextoNoticias;
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
        return "NoticiasCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idNoticias != null ? "idNoticias=" + idNoticias + ", " : "") +
                (tituloNoticias != null ? "tituloNoticias=" + tituloNoticias + ", " : "") +
                (textoNoticias != null ? "textoNoticias=" + textoNoticias + ", " : "") +
                (resumoTextoNoticias != null ? "resumoTextoNoticias=" + resumoTextoNoticias + ", " : "") +
                (idSituacaoId != null ? "situacao=" + idSituacaoId + ", " : "") +
                (inicioId != null ? "inicioId=" + inicioId + ", " : "") +
            "}";
    }

}
