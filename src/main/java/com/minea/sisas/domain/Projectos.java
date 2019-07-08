package com.minea.sisas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Projectos.
 */
@Entity
@Table(name = "projectos")
public class Projectos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_projectos", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 150)
    @Column(name = "nm_projectos", length = 150, nullable = false)
    private String nmProjectos;

    @NotNull
    @Size(max = 2500)
    @Column(name = "texto_projectos", length = 2500, nullable = false)
    private String textoProjectos;

    @NotNull
    @Size(max = 100)
    @Column(name = "resumo_texto_projectos", length = 100, nullable = false)
    private String resumoTextoProjectos;

    @ManyToOne(optional = false)
    @NotNull
    private Situacao idSituacao;

    @OneToMany(mappedBy = "idProjectos")
    @JsonIgnore
    private Set<Inicio> inicios = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmProjectos() {
        return nmProjectos;
    }

    public Projectos nmProjectos(String nmProjectos) {
        this.nmProjectos = nmProjectos;
        return this;
    }

    public void setNmProjectos(String nmProjectos) {
        this.nmProjectos = nmProjectos;
    }

    public String getTextoProjectos() {
        return textoProjectos;
    }

    public Projectos textoProjectos(String textoProjectos) {
        this.textoProjectos = textoProjectos;
        return this;
    }

    public void setTextoProjectos(String textoProjectos) {
        this.textoProjectos = textoProjectos;
    }

    public String getResumoTextoProjectos() {
        return resumoTextoProjectos;
    }

    public Projectos resumoTextoProjectos(String resumoTextoProjectos) {
        this.resumoTextoProjectos = resumoTextoProjectos;
        return this;
    }

    public void setResumoTextoProjectos(String resumoTextoProjectos) {
        this.resumoTextoProjectos = resumoTextoProjectos;
    }

    public Situacao getIdSituacao() {
        return idSituacao;
    }

    public Projectos idSituacao(Situacao situacao) {
        this.idSituacao = situacao;
        return this;
    }

    public void setIdSituacao(Situacao situacao) {
        this.idSituacao = situacao;
    }

    public Set<Inicio> getInicios() {
        return inicios;
    }

    public Projectos inicios(Set<Inicio> inicios) {
        this.inicios = inicios;
        return this;
    }

    public Projectos addInicio(Inicio inicio) {
        this.inicios.add(inicio);
        inicio.setIdProjectos(this);
        return this;
    }

    public Projectos removeInicio(Inicio inicio) {
        this.inicios.remove(inicio);
        inicio.setIdProjectos(null);
        return this;
    }

    public void setInicios(Set<Inicio> inicios) {
        this.inicios = inicios;
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
        Projectos projectos = (Projectos) o;
        if (projectos.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), projectos.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Projectos{" +
            "id=" + getId() +
            ", nmProjectos='" + getNmProjectos() + "'" +
            ", textoProjectos='" + getTextoProjectos() + "'" +
            ", resumoTextoProjectos='" + getResumoTextoProjectos() + "'" +
            "}";
    }
}
