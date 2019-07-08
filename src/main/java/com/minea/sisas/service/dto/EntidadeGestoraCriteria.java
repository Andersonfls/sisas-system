package com.minea.sisas.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.BigDecimalFilter;

import io.github.jhipster.service.filter.LocalDateFilter;



/**
 * Criteria class for the EntidadeGestora entity. This class is used in EntidadeGestoraResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /entidade-gestoras?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EntidadeGestoraCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idEntidadeGestora;

    private StringFilter nmEntidadeGestora;

    private LongFilter tpFormaJuridica;

    private LocalDateFilter dtConstituicao;

    private StringFilter endereco;

    private StringFilter email;

    private StringFilter contactos;

    private LongFilter tpModeloGestao;

    private LongFilter numRecursosHumanos;

    private BigDecimalFilter numPopulacaoAreaAtendimento;

    private LongFilter idMunicipioAtendidoId;

    public EntidadeGestoraCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdEntidadeGestora() {
        return idEntidadeGestora;
    }

    public void setIdEntidadeGestora(LongFilter idEntidadeGestora) {
        this.idEntidadeGestora = idEntidadeGestora;
    }

    public StringFilter getNmEntidadeGestora() {
        return nmEntidadeGestora;
    }

    public void setNmEntidadeGestora(StringFilter nmEntidadeGestora) {
        this.nmEntidadeGestora = nmEntidadeGestora;
    }

    public LongFilter getTpFormaJuridica() {
        return tpFormaJuridica;
    }

    public void setTpFormaJuridica(LongFilter tpFormaJuridica) {
        this.tpFormaJuridica = tpFormaJuridica;
    }

    public LocalDateFilter getDtConstituicao() {
        return dtConstituicao;
    }

    public void setDtConstituicao(LocalDateFilter dtConstituicao) {
        this.dtConstituicao = dtConstituicao;
    }

    public StringFilter getEndereco() {
        return endereco;
    }

    public void setEndereco(StringFilter endereco) {
        this.endereco = endereco;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getContactos() {
        return contactos;
    }

    public void setContactos(StringFilter contactos) {
        this.contactos = contactos;
    }

    public LongFilter getTpModeloGestao() {
        return tpModeloGestao;
    }

    public void setTpModeloGestao(LongFilter tpModeloGestao) {
        this.tpModeloGestao = tpModeloGestao;
    }

    public LongFilter getNumRecursosHumanos() {
        return numRecursosHumanos;
    }

    public void setNumRecursosHumanos(LongFilter numRecursosHumanos) {
        this.numRecursosHumanos = numRecursosHumanos;
    }

    public BigDecimalFilter getNumPopulacaoAreaAtendimento() {
        return numPopulacaoAreaAtendimento;
    }

    public void setNumPopulacaoAreaAtendimento(BigDecimalFilter numPopulacaoAreaAtendimento) {
        this.numPopulacaoAreaAtendimento = numPopulacaoAreaAtendimento;
    }

    public LongFilter getIdMunicipioAtendidoId() {
        return idMunicipioAtendidoId;
    }

    public void setIdMunicipioAtendidoId(LongFilter idMunicipioAtendidoId) {
        this.idMunicipioAtendidoId = idMunicipioAtendidoId;
    }

    @Override
    public String toString() {
        return "EntidadeGestoraCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idEntidadeGestora != null ? "idEntidadeGestora=" + idEntidadeGestora + ", " : "") +
                (nmEntidadeGestora != null ? "nmEntidadeGestora=" + nmEntidadeGestora + ", " : "") +
                (tpFormaJuridica != null ? "tpFormaJuridica=" + tpFormaJuridica + ", " : "") +
                (dtConstituicao != null ? "dtConstituicao=" + dtConstituicao + ", " : "") +
                (endereco != null ? "endereco=" + endereco + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (contactos != null ? "contactos=" + contactos + ", " : "") +
                (tpModeloGestao != null ? "tpModeloGestao=" + tpModeloGestao + ", " : "") +
                (numRecursosHumanos != null ? "numRecursosHumanos=" + numRecursosHumanos + ", " : "") +
                (numPopulacaoAreaAtendimento != null ? "numPopulacaoAreaAtendimento=" + numPopulacaoAreaAtendimento + ", " : "") +
                (idMunicipioAtendidoId != null ? "idMunicipioAtendidoId=" + idMunicipioAtendidoId + ", " : "") +
            "}";
    }

}
