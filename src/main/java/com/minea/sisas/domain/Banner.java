package com.minea.sisas.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @Author Anderson Soares - @git/Andersonfls
 **/
@Entity
@Table(name = "banner")
public class Banner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Lob
    @Column(name = "descricao")
    private String descricao;

    @Lob
    @Column(name = "imagem_banner")
    private byte[] imagemBanner;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "status")
    private Boolean status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Banner titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Boolean isStatus() {
        return status;
    }

    public Banner status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getImagemBanner() {
        return imagemBanner;
    }

    public void setImagemBanner(byte[] imagemBanner) {
        this.imagemBanner = imagemBanner;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
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
        Banner noticiasPortal = (Banner) o;
        if (noticiasPortal.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), noticiasPortal.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NoticiasPortal{" +
            "id=" + getId() +
            ", titulo='" + getTitulo() + "'" +
            ", status='" + isStatus() + "'" +
            "}";
    }
}
