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
 * Criteria class for the Fornecedor entity. This class is used in FornecedorResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /fornecedors?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FornecedorCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idFornecedor;

    private StringFilter nmFornecedor;

    private StringFilter numContribuinte;

    private StringFilter endereco;

    private StringFilter email;

    private LongFilter especialidades;

    private StringFilter contato;

    public FornecedorCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(LongFilter idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public StringFilter getNmFornecedor() {
        return nmFornecedor;
    }

    public void setNmFornecedor(StringFilter nmFornecedor) {
        this.nmFornecedor = nmFornecedor;
    }

    public StringFilter getNumContribuinte() {
        return numContribuinte;
    }

    public void setNumContribuinte(StringFilter numContribuinte) {
        this.numContribuinte = numContribuinte;
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

    public LongFilter getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(LongFilter especialidades) {
        this.especialidades = especialidades;
    }

    public StringFilter getContato() {
        return contato;
    }

    public void setContato(StringFilter contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return "FornecedorCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idFornecedor != null ? "idFornecedor=" + idFornecedor + ", " : "") +
                (nmFornecedor != null ? "nmFornecedor=" + nmFornecedor + ", " : "") +
                (numContribuinte != null ? "numContribuinte=" + numContribuinte + ", " : "") +
                (endereco != null ? "endereco=" + endereco + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (especialidades != null ? "especialidades=" + especialidades + ", " : "") +
                (contato != null ? "contato=" + contato + ", " : "") +

            "}";
    }

}
