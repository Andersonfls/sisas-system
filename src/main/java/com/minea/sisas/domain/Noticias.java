package com.minea.sisas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Noticias.
 */
@Entity
@Table(name = "noticias")
public class Noticias implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_noticias", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 150)
    @Column(name = "titulo_noticias", length = 150, nullable = false)
    private String tituloNoticias;

    @NotNull
    @Size(max = 2500)
    @Column(name = "texto_noticias", length = 2500, nullable = false)
    private String textoNoticias;

    @NotNull
    @Size(max = 100)
    @Column(name = "resumo_texto_noticias", length = 100, nullable = false)
    private String resumoTextoNoticias;

    @ManyToOne(optional = false)
    @NotNull
    private Situacao idSituacao;

    @OneToMany(mappedBy = "idNoticias")
    @JsonIgnore
    private Set<Inicio> inicios = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTituloNoticias() {
        return tituloNoticias;
    }

    public Noticias tituloNoticias(String tituloNoticias) {
        this.tituloNoticias = tituloNoticias;
        return this;
    }

    public void setTituloNoticias(String tituloNoticias) {
        this.tituloNoticias = tituloNoticias;
    }

    public String getTextoNoticias() {
        return textoNoticias;
    }

    public Noticias textoNoticias(String textoNoticias) {
        this.textoNoticias = textoNoticias;
        return this;
    }

    public void setTextoNoticias(String textoNoticias) {
        this.textoNoticias = textoNoticias;
    }

    public String getResumoTextoNoticias() {
        return resumoTextoNoticias;
    }

    public Noticias resumoTextoNoticias(String resumoTextoNoticias) {
        this.resumoTextoNoticias = resumoTextoNoticias;
        return this;
    }

    public void setResumoTextoNoticias(String resumoTextoNoticias) {
        this.resumoTextoNoticias = resumoTextoNoticias;
    }

    public Situacao getIdSituacao() {
        return idSituacao;
    }

    public Noticias idSituacao(Situacao situacao) {
        this.idSituacao = situacao;
        return this;
    }

    public void setIdSituacao(Situacao situacao) {
        this.idSituacao = situacao;
    }

    public Set<Inicio> getInicios() {
        return inicios;
    }

    public Noticias inicios(Set<Inicio> inicios) {
        this.inicios = inicios;
        return this;
    }

    public Noticias addInicio(Inicio inicio) {
        this.inicios.add(inicio);
        inicio.setIdNoticias(this);
        return this;
    }

    public Noticias removeInicio(Inicio inicio) {
        this.inicios.remove(inicio);
        inicio.setIdNoticias(null);
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
        Noticias noticias = (Noticias) o;
        if (noticias.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), noticias.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Noticias{" +
            "id=" + getId() +
            ", tituloNoticias='" + getTituloNoticias() + "'" +
            ", textoNoticias='" + getTextoNoticias() + "'" +
            ", resumoTextoNoticias='" + getResumoTextoNoticias() + "'" +
            "}";
    }
}
