package com.minea.sisas.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A OrigemFinanciamento.
 */
@Entity
@Table(name = "origem_financiamento")
public class OrigemFinanciamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_origem_financiamento", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(name = "descricao_origem_financiamento", length = 200, nullable = false)
    private String descricaoOrigemFinanciamento;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricaoOrigemFinanciamento() {
        return descricaoOrigemFinanciamento;
    }

    public OrigemFinanciamento descricaoOrigemFinanciamento(String descricaoOrigemFinanciamento) {
        this.descricaoOrigemFinanciamento = descricaoOrigemFinanciamento;
        return this;
    }

    public void setDescricaoOrigemFinanciamento(String descricaoOrigemFinanciamento) {
        this.descricaoOrigemFinanciamento = descricaoOrigemFinanciamento;
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
        OrigemFinanciamento origemFinanciamento = (OrigemFinanciamento) o;
        if (origemFinanciamento.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), origemFinanciamento.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrigemFinanciamento{" +
            "id=" + getId() +
            ", descricaoOrigemFinanciamento='" + getDescricaoOrigemFinanciamento() + "'" +
            "}";
    }
}
