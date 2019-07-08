package com.minea.sisas.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Publicacao.
 */
@Entity
@Table(name = "publicacao")
public class Publicacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_publicacao", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 150)
    @Column(name = "titulo_publicacao", length = 150, nullable = false)
    private String tituloPublicacao;

    @NotNull
    @Size(max = 2500)
    @Column(name = "texto_publicacao", length = 2500, nullable = false)
    private String textoPublicacao;

    @NotNull
    @Size(max = 100)
    @Column(name = "resumo_texto_publicacao", length = 100, nullable = false)
    private String resumoTextoPublicacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_situacao")
    private Situacao situacao;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTituloPublicacao() {
        return tituloPublicacao;
    }

    public Publicacao tituloPublicacao(String tituloPublicacao) {
        this.tituloPublicacao = tituloPublicacao;
        return this;
    }

    public void setTituloPublicacao(String tituloPublicacao) {
        this.tituloPublicacao = tituloPublicacao;
    }

    public String getTextoPublicacao() {
        return textoPublicacao;
    }

    public Publicacao textoPublicacao(String textoPublicacao) {
        this.textoPublicacao = textoPublicacao;
        return this;
    }

    public void setTextoPublicacao(String textoPublicacao) {
        this.textoPublicacao = textoPublicacao;
    }

    public String getResumoTextoPublicacao() {
        return resumoTextoPublicacao;
    }

    public Publicacao resumoTextoPublicacao(String resumoTextoPublicacao) {
        this.resumoTextoPublicacao = resumoTextoPublicacao;
        return this;
    }

    public void setResumoTextoPublicacao(String resumoTextoPublicacao) {
        this.resumoTextoPublicacao = resumoTextoPublicacao;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public Publicacao idSituacao(Situacao situacao) {
        this.situacao = situacao;
        return this;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
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
        Publicacao publicacao = (Publicacao) o;
        if (publicacao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), publicacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Publicacao{" +
            "id=" + getId() +
            ", tituloPublicacao='" + getTituloPublicacao() + "'" +
            ", textoPublicacao='" + getTextoPublicacao() + "'" +
            ", resumoTextoPublicacao='" + getResumoTextoPublicacao() + "'" +
            "}";
    }
}
