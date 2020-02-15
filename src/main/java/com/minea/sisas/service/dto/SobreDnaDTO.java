package com.minea.sisas.service.dto;


import javax.persistence.Column;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the SobreDna entity.
 */
public class SobreDnaDTO implements Serializable {

    private Long id;

    @Size(max = 150)
    private String tituloSobreDna;

    @Size(max = 2500)
    private String textoSobreDna;

    @Size(max = 100)
    private String resumoTextoSobreDna;

    private Long tipoPagina;

    private String email;

    private String telemovel;

    private String endereco;

    private String descricao;

    private String primeiro_paragrafo;

    private String segundo_paragrafo;

    private String terceiro_paragrafo;

    private String quarto_paragrafo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTituloSobreDna() {
        return tituloSobreDna;
    }

    public void setTituloSobreDna(String tituloSobreDna) {
        this.tituloSobreDna = tituloSobreDna;
    }

    public String getTextoSobreDna() {
        return textoSobreDna;
    }

    public void setTextoSobreDna(String textoSobreDna) {
        this.textoSobreDna = textoSobreDna;
    }

    public String getResumoTextoSobreDna() {
        return resumoTextoSobreDna;
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

    public String getPrimeiro_paragrafo() {
        return primeiro_paragrafo;
    }

    public void setPrimeiro_paragrafo(String primeiro_paragrafo) {
        this.primeiro_paragrafo = primeiro_paragrafo;
    }

    public String getSegundo_paragrafo() {
        return segundo_paragrafo;
    }

    public void setSegundo_paragrafo(String segundo_paragrafo) {
        this.segundo_paragrafo = segundo_paragrafo;
    }

    public String getTerceiro_paragrafo() {
        return terceiro_paragrafo;
    }

    public void setTerceiro_paragrafo(String terceiro_paragrafo) {
        this.terceiro_paragrafo = terceiro_paragrafo;
    }

    public String getQuarto_paragrafo() {
        return quarto_paragrafo;
    }

    public void setQuarto_paragrafo(String quarto_paragrafo) {
        this.quarto_paragrafo = quarto_paragrafo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SobreDnaDTO sobreDnaDTO = (SobreDnaDTO) o;
        if(sobreDnaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sobreDnaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SobreDnaDTO{" +
            "id=" + getId() +
            ", tituloSobreDna='" + getTituloSobreDna() + "'" +
            ", textoSobreDna='" + getTextoSobreDna() + "'" +
            ", resumoTextoSobreDna='" + getResumoTextoSobreDna() + "'" +
            "}";
    }
}
