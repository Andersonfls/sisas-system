package com.minea.sisas.domain;


import javax.persistence.*;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * A FinalidadeProjeto.
 */
@Entity
@Table(name = "finalidade_projeto")
public class FinalidadeProjeto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_finalidade", nullable = false)
    private Long id;

    @Size(max = 45)
    @Column(name = "nm_finalidade")
    private String nmFinalidade;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmFinalidade() {
        return nmFinalidade;
    }

    public FinalidadeProjeto nmFinalidade(String nmFinalidade) {
        this.nmFinalidade = nmFinalidade;
        return this;
    }

    public void setNmFinalidade(String nmFinalidade) {
        this.nmFinalidade = nmFinalidade;
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
        FinalidadeProjeto finalidadeProjeto = (FinalidadeProjeto) o;
        if (finalidadeProjeto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), finalidadeProjeto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FinalidadeProjeto{" +
            "id=" + getId() +
            ", nmFinalidade='" + getNmFinalidade() + "'" +
            "}";
    }
}
