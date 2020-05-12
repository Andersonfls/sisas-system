package com.minea.sisas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * @Author Anderson Soares - @git/Andersonfls
 **/
@Entity
@Table(name = "provincia")
public class Provincia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_provincia", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "nm_provincia", length = 30, nullable = false)
    private String nmProvincia;

    @OneToMany(mappedBy = "provincia")
    @JsonIgnore
    private Set<Municipio> municipios = new HashSet<>();

    @Column(name = "populacao", nullable = false)
    private Long populacao;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmProvincia() {
        return nmProvincia;
    }

    public Provincia nmProvincia(String nmProvincia) {
        this.nmProvincia = nmProvincia;
        return this;
    }

    public void setNmProvincia(String nmProvincia) {
        this.nmProvincia = nmProvincia;
    }

    public Set<Municipio> getMunicipios() {
        return municipios;
    }

    public Provincia municipios(Set<Municipio> municipios) {
        this.municipios = municipios;
        return this;
    }

    public Provincia addMunicipio(Municipio municipio) {
        this.municipios.add(municipio);
        municipio.setProvincia(this);
        return this;
    }

    public Provincia removeMunicipio(Municipio municipio) {
        this.municipios.remove(municipio);
        municipio.setProvincia(null);
        return this;
    }

    public void setMunicipios(Set<Municipio> municipios) {
        this.municipios = municipios;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public Long getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Long populacao) {
        this.populacao = populacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Provincia provincia = (Provincia) o;
        if (provincia.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), provincia.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Provincia{" +
            "id=" + getId() +
            ", nmProvincia='" + getNmProvincia() + "'" +
            "}";
    }
}
