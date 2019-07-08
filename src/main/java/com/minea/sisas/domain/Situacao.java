package com.minea.sisas.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Situacao.
 */
@Entity
@Table(name = "situacao")
public class Situacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_situacao", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nm_situacao", length = 50, nullable = false)
    private String nmSituacao;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmSituacao() {
        return nmSituacao;
    }

    public Situacao nmSituacao(String nmSituacao) {
        this.nmSituacao = nmSituacao;
        return this;
    }

    public void setNmSituacao(String nmSituacao) {
        this.nmSituacao = nmSituacao;
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
        Situacao situacao = (Situacao) o;
        if (situacao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), situacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Situacao{" +
            "id=" + getId() +
            ", nmSituacao='" + getNmSituacao() + "'" +
            "}";
    }
}
