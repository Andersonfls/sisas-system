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
 * Criteria class for the Publicacao entity. This class is used in PublicacaoResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /publicacaos?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PublicacaoCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idPublicacao;

    private StringFilter tituloPublicacao;

    private StringFilter textoPublicacao;

    private StringFilter resumoTextoPublicacao;

    private LongFilter idSituacaoId;

    private LongFilter inicioId;

    public PublicacaoCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdPublicacao() {
        return idPublicacao;
    }

    public void setIdPublicacao(LongFilter idPublicacao) {
        this.idPublicacao = idPublicacao;
    }

    public StringFilter getTituloPublicacao() {
        return tituloPublicacao;
    }

    public void setTituloPublicacao(StringFilter tituloPublicacao) {
        this.tituloPublicacao = tituloPublicacao;
    }

    public StringFilter getTextoPublicacao() {
        return textoPublicacao;
    }

    public void setTextoPublicacao(StringFilter textoPublicacao) {
        this.textoPublicacao = textoPublicacao;
    }

    public StringFilter getResumoTextoPublicacao() {
        return resumoTextoPublicacao;
    }

    public void setResumoTextoPublicacao(StringFilter resumoTextoPublicacao) {
        this.resumoTextoPublicacao = resumoTextoPublicacao;
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
        return "PublicacaoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idPublicacao != null ? "idPublicacao=" + idPublicacao + ", " : "") +
                (tituloPublicacao != null ? "tituloPublicacao=" + tituloPublicacao + ", " : "") +
                (textoPublicacao != null ? "textoPublicacao=" + textoPublicacao + ", " : "") +
                (resumoTextoPublicacao != null ? "resumoTextoPublicacao=" + resumoTextoPublicacao + ", " : "") +
                (idSituacaoId != null ? "situacao=" + idSituacaoId + ", " : "") +
                (inicioId != null ? "inicioId=" + inicioId + ", " : "") +
            "}";
    }

}
