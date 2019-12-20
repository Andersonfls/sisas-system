package com.minea.sisas.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A NoticiasPortal.
 */
@Entity
@Table(name = "noticias_portal")
public class NoticiasPortal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Lob
    @Column(name = "texto")
    private String texto;

    @Lob
    @Column(name = "imagem_capa")
    private byte[] imagemCapa;

    @Column(name = "imagem_capa_content_type")
    private String imagemCapaContentType;

    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @Column(name = "data_alteracao")
    private LocalDate dataAlteracao;

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

    public NoticiasPortal titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public NoticiasPortal texto(String texto) {
        this.texto = texto;
        return this;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public byte[] getImagemCapa() {
        return imagemCapa;
    }

    public NoticiasPortal imagemCapa(byte[] imagemCapa) {
        this.imagemCapa = imagemCapa;
        return this;
    }

    public void setImagemCapa(byte[] imagemCapa) {
        this.imagemCapa = imagemCapa;
    }

    public String getImagemCapaContentType() {
        return imagemCapaContentType;
    }

    public NoticiasPortal imagemCapaContentType(String imagemCapaContentType) {
        this.imagemCapaContentType = imagemCapaContentType;
        return this;
    }

    public void setImagemCapaContentType(String imagemCapaContentType) {
        this.imagemCapaContentType = imagemCapaContentType;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public NoticiasPortal dataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
        return this;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataAlteracao() {
        return dataAlteracao;
    }

    public NoticiasPortal dataAlteracao(LocalDate dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
        return this;
    }

    public void setDataAlteracao(LocalDate dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Boolean isStatus() {
        return status;
    }

    public NoticiasPortal status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
        NoticiasPortal noticiasPortal = (NoticiasPortal) o;
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
            ", texto='" + getTexto() + "'" +
            ", imagemCapa='" + getImagemCapa() + "'" +
            ", imagemCapaContentType='" + getImagemCapaContentType() + "'" +
            ", dataCriacao='" + getDataCriacao() + "'" +
            ", dataAlteracao='" + getDataAlteracao() + "'" +
            ", status='" + isStatus() + "'" +
            "}";
    }
}
