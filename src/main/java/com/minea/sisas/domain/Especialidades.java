package com.minea.sisas.domain;


import javax.persistence.*;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Especialidades.
 */
@Entity
@Table(name = "especialidades")
public class Especialidades implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidade", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "nm_especialidade")
    private String nmEspecialidade;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmEspecialidade() {
        return nmEspecialidade;
    }

    public Especialidades nmEspecialidade(String nmEspecialidade) {
        this.nmEspecialidade = nmEspecialidade;
        return this;
    }

    public void setNmEspecialidade(String nmEspecialidade) {
        this.nmEspecialidade = nmEspecialidade;
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
        Especialidades especialidades = (Especialidades) o;
        if (especialidades.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), especialidades.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Especialidades{" +
            "id=" + getId() +
            ", nmEspecialidade='" + getNmEspecialidade() + "'" +
            "}";
    }
}
