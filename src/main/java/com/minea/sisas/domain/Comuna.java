package com.minea.sisas.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Comuna.
 */
@Entity
@Table(name = "comuna")
public class Comuna implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comuna", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "nm_comuna", length = 40, nullable = false)
    private String nmComuna;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_municipio")
    private Municipio municipio;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmComuna() {
        return nmComuna;
    }

    public Comuna nmComuna(String nmComuna) {
        this.nmComuna = nmComuna;
        return this;
    }

    public void setNmComuna(String nmComuna) {
        this.nmComuna = nmComuna;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public Comuna municipio(Municipio municipio) {
        this.municipio = municipio;
        return this;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comuna comuna = (Comuna) o;
        if (comuna.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), comuna.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Comuna{" +
            "id=" + getId() +
            ", nmComuna='" + getNmComuna() + "'" +
            "}";
    }
}
