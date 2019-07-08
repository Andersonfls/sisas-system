package com.minea.sisas.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Inicio.
 */
@Entity
@Table(name = "inicio")
public class Inicio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inicio", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "destaques", nullable = false)
    private Long destaques;

    @NotNull
    @Column(name = "ultimas_noticias", nullable = false)
    private Long ultimasNoticias;

    @NotNull
    @Column(name = "publicacoes", nullable = false)
    private Long publicacoes;

    @NotNull
    @Size(max = 500)
    @Column(name = "url", length = 500, nullable = false)
    private String url;

    @NotNull
    @Size(max = 150)
    @Column(name = "alt", length = 150, nullable = false)
    private String alt;

    @ManyToOne(optional = false)
    @NotNull
    private Situacao idSituacao;

    @ManyToOne(optional = false)
    @NotNull
    private SobreDna idSobreDna;

    @ManyToOne(optional = false)
    @NotNull
    private Noticias idNoticias;

    @ManyToOne(optional = false)
    @NotNull
    private Projectos idProjectos;

    @ManyToOne(optional = false)
    @NotNull
    private Publicacao idPublicacao;

    @ManyToOne(optional = false)
    @NotNull
    private Contactos idContactos;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDestaques() {
        return destaques;
    }

    public Inicio destaques(Long destaques) {
        this.destaques = destaques;
        return this;
    }

    public void setDestaques(Long destaques) {
        this.destaques = destaques;
    }

    public Long getUltimasNoticias() {
        return ultimasNoticias;
    }

    public Inicio ultimasNoticias(Long ultimasNoticias) {
        this.ultimasNoticias = ultimasNoticias;
        return this;
    }

    public void setUltimasNoticias(Long ultimasNoticias) {
        this.ultimasNoticias = ultimasNoticias;
    }

    public Long getPublicacoes() {
        return publicacoes;
    }

    public Inicio publicacoes(Long publicacoes) {
        this.publicacoes = publicacoes;
        return this;
    }

    public void setPublicacoes(Long publicacoes) {
        this.publicacoes = publicacoes;
    }

    public String getUrl() {
        return url;
    }

    public Inicio url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt() {
        return alt;
    }

    public Inicio alt(String alt) {
        this.alt = alt;
        return this;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Situacao getIdSituacao() {
        return idSituacao;
    }

    public Inicio idSituacao(Situacao situacao) {
        this.idSituacao = situacao;
        return this;
    }

    public void setIdSituacao(Situacao situacao) {
        this.idSituacao = situacao;
    }

    public SobreDna getIdSobreDna() {
        return idSobreDna;
    }

    public Inicio idSobreDna(SobreDna sobreDna) {
        this.idSobreDna = sobreDna;
        return this;
    }

    public void setIdSobreDna(SobreDna sobreDna) {
        this.idSobreDna = sobreDna;
    }

    public Noticias getIdNoticias() {
        return idNoticias;
    }

    public Inicio idNoticias(Noticias noticias) {
        this.idNoticias = noticias;
        return this;
    }

    public void setIdNoticias(Noticias noticias) {
        this.idNoticias = noticias;
    }

    public Projectos getIdProjectos() {
        return idProjectos;
    }

    public Inicio idProjectos(Projectos projectos) {
        this.idProjectos = projectos;
        return this;
    }

    public void setIdProjectos(Projectos projectos) {
        this.idProjectos = projectos;
    }

    public Publicacao getIdPublicacao() {
        return idPublicacao;
    }

    public Inicio idPublicacao(Publicacao publicacao) {
        this.idPublicacao = publicacao;
        return this;
    }

    public void setIdPublicacao(Publicacao publicacao) {
        this.idPublicacao = publicacao;
    }

    public Contactos getIdContactos() {
        return idContactos;
    }

    public Inicio idContactos(Contactos contactos) {
        this.idContactos = contactos;
        return this;
    }

    public void setIdContactos(Contactos contactos) {
        this.idContactos = contactos;
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
        Inicio inicio = (Inicio) o;
        if (inicio.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), inicio.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Inicio{" +
            "id=" + getId() +
            ", destaques=" + getDestaques() +
            ", ultimasNoticias=" + getUltimasNoticias() +
            ", publicacoes=" + getPublicacoes() +
            ", url='" + getUrl() + "'" +
            ", alt='" + getAlt() + "'" +
            "}";
    }
}
