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
 * Criteria class for the Situacao entity. This class is used in SituacaoResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /situacaos?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SituacaoCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idSituacao;

    private StringFilter nmSituacao;

    private LongFilter contactosId;

    private LongFilter execucaoId;

    private LongFilter indicadorProducaoId;

    private LongFilter inicioId;

    private LongFilter noticiasId;

    private LongFilter projectosId;

    private LongFilter publicacaoId;

    private LongFilter sistemaAguaId;

    private LongFilter sobreDnaId;

    public SituacaoCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdSituacao() {
        return idSituacao;
    }

    public void setIdSituacao(LongFilter idSituacao) {
        this.idSituacao = idSituacao;
    }

    public StringFilter getNmSituacao() {
        return nmSituacao;
    }

    public void setNmSituacao(StringFilter nmSituacao) {
        this.nmSituacao = nmSituacao;
    }

    public LongFilter getContactosId() {
        return contactosId;
    }

    public void setContactosId(LongFilter contactosId) {
        this.contactosId = contactosId;
    }

    public LongFilter getExecucaoId() {
        return execucaoId;
    }

    public void setExecucaoId(LongFilter execucaoId) {
        this.execucaoId = execucaoId;
    }

    public LongFilter getIndicadorProducaoId() {
        return indicadorProducaoId;
    }

    public void setIndicadorProducaoId(LongFilter indicadorProducaoId) {
        this.indicadorProducaoId = indicadorProducaoId;
    }

    public LongFilter getInicioId() {
        return inicioId;
    }

    public void setInicioId(LongFilter inicioId) {
        this.inicioId = inicioId;
    }

    public LongFilter getNoticiasId() {
        return noticiasId;
    }

    public void setNoticiasId(LongFilter noticiasId) {
        this.noticiasId = noticiasId;
    }

    public LongFilter getProjectosId() {
        return projectosId;
    }

    public void setProjectosId(LongFilter projectosId) {
        this.projectosId = projectosId;
    }

    public LongFilter getPublicacaoId() {
        return publicacaoId;
    }

    public void setPublicacaoId(LongFilter publicacaoId) {
        this.publicacaoId = publicacaoId;
    }

    public LongFilter getSistemaAguaId() {
        return sistemaAguaId;
    }

    public void setSistemaAguaId(LongFilter sistemaAguaId) {
        this.sistemaAguaId = sistemaAguaId;
    }

    public LongFilter getSobreDnaId() {
        return sobreDnaId;
    }

    public void setSobreDnaId(LongFilter sobreDnaId) {
        this.sobreDnaId = sobreDnaId;
    }

    @Override
    public String toString() {
        return "SituacaoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idSituacao != null ? "idSituacao=" + idSituacao + ", " : "") +
                (nmSituacao != null ? "nmSituacao=" + nmSituacao + ", " : "") +
                (contactosId != null ? "contactosId=" + contactosId + ", " : "") +
                (execucaoId != null ? "execucaoId=" + execucaoId + ", " : "") +
                (indicadorProducaoId != null ? "indicadorProducaoId=" + indicadorProducaoId + ", " : "") +
                (inicioId != null ? "inicioId=" + inicioId + ", " : "") +
                (noticiasId != null ? "noticiasId=" + noticiasId + ", " : "") +
                (projectosId != null ? "projectosId=" + projectosId + ", " : "") +
                (publicacaoId != null ? "publicacaoId=" + publicacaoId + ", " : "") +
                (sistemaAguaId != null ? "sistemaAguaId=" + sistemaAguaId + ", " : "") +
                (sobreDnaId != null ? "sobreDnaId=" + sobreDnaId + ", " : "") +
            "}";
    }

}
