package com.minea.sisas.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Municipio.
 */
@Entity
@Table(name = "municipio")
public class Municipio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_municipio", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "nm_municipio", length = 30, nullable = false)
    private String nmMunicipio;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;

    @Column(name = "valor")
    private Long valor;

    @Column(name = "populacao", nullable = false)
    private Long populacao;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmMunicipio() {
        return nmMunicipio;
    }

    public Municipio nmMunicipio(String nmMunicipio) {
        this.nmMunicipio = nmMunicipio;
        return this;
    }

    public void setNmMunicipio(String nmMunicipio) {
        this.nmMunicipio = nmMunicipio;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public Municipio provincia(Provincia provincia) {
        this.provincia = provincia;
        return this;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

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
        Municipio municipio = (Municipio) o;
        if (municipio.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), municipio.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Municipio{" +
            "id=" + id +
            ", nmMunicipio='" + nmMunicipio + '\'' +
            ", provincia=" + provincia +
            ", valor=" + valor +
            '}';
    }
}
