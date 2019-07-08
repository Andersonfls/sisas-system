package com.minea.sisas.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Fases.
 */
@Entity
@Table(name = "fases")
public class Fases implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fase", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(name = "descricao_fase", length = 200, nullable = false)
    private String descricaoFase;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricaoFase() {
        return descricaoFase;
    }

    public Fases descricaoFase(String descricaoFase) {
        this.descricaoFase = descricaoFase;
        return this;
    }

    public void setDescricaoFase(String descricaoFase) {
        this.descricaoFase = descricaoFase;
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
        Fases fases = (Fases) o;
        if (fases.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fases.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Fases{" +
            "id=" + getId() +
            ", descricaoFase='" + getDescricaoFase() + "'" +
            "}";
    }
}
