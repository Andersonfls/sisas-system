package com.minea.sisas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
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
    @Column(name = "titulo_sobre_dna", length = 150, nullable = false)
    private String tituloSobreDna;

    @NotNull
    @Size(max = 2500)
    @Column(name = "texto_sobre_dna", length = 2500, nullable = false)
    private String textoSobreDna;

    @NotNull
    @Size(max = 100)
    @Column(name = "resumo_texto_sobre_dna", length = 100, nullable = false)
    private String resumoTextoSobreDna;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_situacao")
    private Situacao situacao;

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

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
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
            "id=" + getId() +
            ", tituloSobreDna='" + getTituloSobreDna() + "'" +
            ", textoSobreDna='" + getTextoSobreDna() + "'" +
            ", resumoTextoSobreDna='" + getResumoTextoSobreDna() + "'" +
            "}";
    }
}
