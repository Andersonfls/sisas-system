package com.minea.sisas.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A SobreDna.
 */
@Entity
@Table(name = "sobre_dna")
public class SobreDna implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sobre_dna", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 150)
    @Column(name = "titulo_sobre_dna", length = 150)
    private String tituloSobreDna;

    @NotNull
    @Size(max = 2500)
    @Column(name = "texto_sobre_dna", length = 2500)
    private String textoSobreDna;

    @NotNull
    @Size(max = 100)
    @Column(name = "resumo_texto_sobre_dna", length = 100)
    private String resumoTextoSobreDna;

    @Column(name = "id_tipo_pagina")
    private Long tipoPagina;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "telemovel", length = 100)
    private String telemovel;

    @Column(name = "endereco", length = 100)
    private String endereco;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "primeiro_paragrafo")
    private String primeiroParagrafo;

    @Column(name = "segundo_paragrafo")
    private String segundoParagrafo;

    @Column(name = "terceiro_paragrafo")
    private String terceiroParagrafo;

    @Column(name = "quarto_paragrafo")
    private String quartoParagrafo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTituloSobreDna() {
        return tituloSobreDna;
    }

    public SobreDna tituloSobreDna(String tituloSobreDna) {
        this.tituloSobreDna = tituloSobreDna;
        return this;
    }

    public void setTituloSobreDna(String tituloSobreDna) {
        this.tituloSobreDna = tituloSobreDna;
    }

    public String getTextoSobreDna() {
        return textoSobreDna;
    }

    public SobreDna textoSobreDna(String textoSobreDna) {
        this.textoSobreDna = textoSobreDna;
        return this;
    }

    public void setTextoSobreDna(String textoSobreDna) {
        this.textoSobreDna = textoSobreDna;
    }

    public String getResumoTextoSobreDna() {
        return resumoTextoSobreDna;
    }

    public SobreDna resumoTextoSobreDna(String resumoTextoSobreDna) {
        this.resumoTextoSobreDna = resumoTextoSobreDna;
        return this;
    }

    public void setResumoTextoSobreDna(String resumoTextoSobreDna) {
        this.resumoTextoSobreDna = resumoTextoSobreDna;
    }

    public Long getTipoPagina() {
        return tipoPagina;
    }

    public void setTipoPagina(Long tipoPagina) {
        this.tipoPagina = tipoPagina;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrimeiroParagrafo() {
        return primeiroParagrafo;
    }

    public void setPrimeiroParagrafo(String primeiroParagrafo) {
        this.primeiroParagrafo = primeiroParagrafo;
    }

    public String getSegundoParagrafo() {
        return segundoParagrafo;
    }

    public void setSegundoParagrafo(String segundoParagrafo) {
        this.segundoParagrafo = segundoParagrafo;
    }

    public String getTerceiroParagrafo() {
        return terceiroParagrafo;
    }

    public void setTerceiroParagrafo(String terceiroParagrafo) {
        this.terceiroParagrafo = terceiroParagrafo;
    }

    public String getQuartoParagrafo() {
        return quartoParagrafo;
    }

    public void setQuartoParagrafo(String quartoParagrafo) {
        this.quartoParagrafo = quartoParagrafo;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SobreDna sobreDna = (SobreDna) o;
        if (sobreDna.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sobreDna.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SobreDna{" +
            "id=" + id +
            ", tituloSobreDna='" + tituloSobreDna + '\'' +
            ", textoSobreDna='" + textoSobreDna + '\'' +
            ", resumoTextoSobreDna='" + resumoTextoSobreDna + '\'' +
            ", tipoPagina=" + tipoPagina +
            ", email='" + email + '\'' +
            ", telemovel='" + telemovel + '\'' +
            ", endereco='" + endereco + '\'' +
            ", descricao='" + descricao + '\'' +
            ", primeiroParagrafo='" + primeiroParagrafo + '\'' +
            ", segundoParagrafo='" + segundoParagrafo + '\'' +
            ", terceiroParagrafo='" + terceiroParagrafo + '\'' +
            ", quartoParagrafo='" + quartoParagrafo + '\'' +
            '}';
    }
}
