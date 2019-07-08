package com.minea.sisas.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Fornecedor.
 */
@Entity
@Table(name = "fornecedor")
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fornecedor", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "nm_fornecedor", length = 100, nullable = false)
    private String nmFornecedor;

    @NotNull
    @Size(max = 100)
    @Column(name = "num_contribuinte", length = 100, nullable = false)
    private String numContribuinte;

    @NotNull
    @Size(max = 250)
    @Column(name = "endereco", length = 250, nullable = false)
    private String endereco;

    @Size(max = 80)
    @Column(name = "email", length = 80)
    private String email;

    @Size(max = 100)
    @Column(name = "especialidade", length = 100)
    private String especialidade;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmFornecedor() {
        return nmFornecedor;
    }

    public Fornecedor nmFornecedor(String nmFornecedor) {
        this.nmFornecedor = nmFornecedor;
        return this;
    }

    public void setNmFornecedor(String nmFornecedor) {
        this.nmFornecedor = nmFornecedor;
    }

    public String getNumContribuinte() {
        return numContribuinte;
    }

    public Fornecedor numContribuinte(String numContribuinte) {
        this.numContribuinte = numContribuinte;
        return this;
    }

    public void setNumContribuinte(String numContribuinte) {
        this.numContribuinte = numContribuinte;
    }

    public String getEndereco() {
        return endereco;
    }

    public Fornecedor endereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public Fornecedor email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public Fornecedor especialidade(String especialidade) {
        this.especialidade = especialidade;
        return this;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
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
        Fornecedor fornecedor = (Fornecedor) o;
        if (fornecedor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fornecedor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
            "id=" + getId() +
            ", nmFornecedor='" + getNmFornecedor() + "'" +
            ", numContribuinte='" + getNumContribuinte() + "'" +
            ", endereco='" + getEndereco() + "'" +
            ", email='" + getEmail() + "'" +
            ", especialidade='" + getEspecialidade() + "'" +
            "}";
    }
}
