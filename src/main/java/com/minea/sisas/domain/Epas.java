package com.minea.sisas.domain;


import javax.persistence.*;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Epas.
 */
@Entity
@Table(name = "epas")
public class Epas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_epas", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "nm_epas")
    private String nmEpas;

    @Size(max = 80)
    @Column(name = "nr_contribuinte")
    private String nrContribuinte;


    @ManyToOne
    @JoinColumn(name = "id_comuna")
    private Comuna comuna;

    @ManyToOne
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;

    @ManyToOne
    @JoinColumn(name = "id_municipio")
    private Municipio municipio;

    @Size(max = 100)
    @Column(name = "nm_localidade")
    private String nmLocalidade;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmEpas() {
        return nmEpas;
    }

    public Epas nmEpas(String nmEpas) {
        this.nmEpas = nmEpas;
        return this;
    }

    public void setNmEpas(String nmEpas) {
        this.nmEpas = nmEpas;
    }

    public String getNrContribuinte() {
        return nrContribuinte;
    }

    public Epas nrContribuinte(String nrContribuinte) {
        this.nrContribuinte = nrContribuinte;
        return this;
    }

    public void setNrContribuinte(String nrContribuinte) {
        this.nrContribuinte = nrContribuinte;
    }

    public Provincia getProvincia() {
        return provincia;
    }
    public Epas provincia(Provincia provincia) {
        this.provincia = provincia;
        return this;
    }
    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Municipio getMunicipio() {
        return municipio;
    }
    public Epas municipio(Municipio municipio) {
        this.municipio = municipio;
        return this;
    }
    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Comuna getComuna() {
        return comuna;
    }
    public Epas comuna(Comuna comuna) {
        this.comuna = comuna;
        return this;
    }
    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    public String getNmLocalidade() {
        return nmLocalidade;
    }

    public Epas nmLocalidade(String nmLocalidade) {
        this.nmLocalidade = nmLocalidade;
        return this;
    }

    public void setNmLocalidade(String nmLocalidade) {
        this.nmLocalidade = nmLocalidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Epas epas = (Epas) o;
        if (epas.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), epas.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Epas{" +
            "id=" + getId() +
            ", nmEpas='" + getNmEpas() + "'" +
            ", nrContribuinte='" + getNrContribuinte() + "'" +
            ", nmLocalidade='" + getNmLocalidade() + "'" +
            "}";
    }
}
