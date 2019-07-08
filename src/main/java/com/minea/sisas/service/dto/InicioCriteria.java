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
 * Criteria class for the Inicio entity. This class is used in InicioResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /inicios?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class InicioCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idInicio;

    private LongFilter destaques;

    private LongFilter ultimasNoticias;

    private LongFilter publicacoes;

    private StringFilter url;

    private StringFilter alt;

    private LongFilter idSituacaoId;

    private LongFilter idSobreDnaId;

    private LongFilter idNoticiasId;

    private LongFilter idProjectosId;

    private LongFilter idPublicacaoId;

    private LongFilter idContactosId;

    public InicioCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdInicio() {
        return idInicio;
    }

    public void setIdInicio(LongFilter idInicio) {
        this.idInicio = idInicio;
    }

    public LongFilter getDestaques() {
        return destaques;
    }

    public void setDestaques(LongFilter destaques) {
        this.destaques = destaques;
    }

    public LongFilter getUltimasNoticias() {
        return ultimasNoticias;
    }

    public void setUltimasNoticias(LongFilter ultimasNoticias) {
        this.ultimasNoticias = ultimasNoticias;
    }

    public LongFilter getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(LongFilter publicacoes) {
        this.publicacoes = publicacoes;
    }

    public StringFilter getUrl() {
        return url;
    }

    public void setUrl(StringFilter url) {
        this.url = url;
    }

    public StringFilter getAlt() {
        return alt;
    }

    public void setAlt(StringFilter alt) {
        this.alt = alt;
    }

    public LongFilter getIdSituacaoId() {
        return idSituacaoId;
    }

    public void setIdSituacaoId(LongFilter idSituacaoId) {
        this.idSituacaoId = idSituacaoId;
    }

    public LongFilter getIdSobreDnaId() {
        return idSobreDnaId;
    }

    public void setIdSobreDnaId(LongFilter idSobreDnaId) {
        this.idSobreDnaId = idSobreDnaId;
    }

    public LongFilter getIdNoticiasId() {
        return idNoticiasId;
    }

    public void setIdNoticiasId(LongFilter idNoticiasId) {
        this.idNoticiasId = idNoticiasId;
    }

    public LongFilter getIdProjectosId() {
        return idProjectosId;
    }

    public void setIdProjectosId(LongFilter idProjectosId) {
        this.idProjectosId = idProjectosId;
    }

    public LongFilter getIdPublicacaoId() {
        return idPublicacaoId;
    }

    public void setIdPublicacaoId(LongFilter idPublicacaoId) {
        this.idPublicacaoId = idPublicacaoId;
    }

    public LongFilter getIdContactosId() {
        return idContactosId;
    }

    public void setIdContactosId(LongFilter idContactosId) {
        this.idContactosId = idContactosId;
    }

    @Override
    public String toString() {
        return "InicioCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idInicio != null ? "idInicio=" + idInicio + ", " : "") +
                (destaques != null ? "destaques=" + destaques + ", " : "") +
                (ultimasNoticias != null ? "ultimasNoticias=" + ultimasNoticias + ", " : "") +
                (publicacoes != null ? "publicacoes=" + publicacoes + ", " : "") +
                (url != null ? "url=" + url + ", " : "") +
                (alt != null ? "alt=" + alt + ", " : "") +
                (idSituacaoId != null ? "situacao=" + idSituacaoId + ", " : "") +
                (idSobreDnaId != null ? "idSobreDnaId=" + idSobreDnaId + ", " : "") +
                (idNoticiasId != null ? "idNoticiasId=" + idNoticiasId + ", " : "") +
                (idProjectosId != null ? "idProjectosId=" + idProjectosId + ", " : "") +
                (idPublicacaoId != null ? "idPublicacaoId=" + idPublicacaoId + ", " : "") +
                (idContactosId != null ? "idContactosId=" + idContactosId + ", " : "") +
            "}";
    }

}
