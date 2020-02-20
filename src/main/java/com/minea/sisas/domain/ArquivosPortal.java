package com.minea.sisas.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Banner.
 */
@Entity
@Table(name = "arquivos_portal")
public class ArquivosPortal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome_arquivo")
    private String nomeArquivo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_inclusao")
    private LocalDate dataInclusao;

    @Column(name = "data_alteracao")
    private LocalDate dataAlteracao;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "tipo_arquivo")
    private Integer tipoArquivo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isStatus() {
        return status;
    }

    public ArquivosPortal status(Boolean status) {
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

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public LocalDate getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDate dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public LocalDate getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDate dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Boolean getStatus() {
        return status;
    }

    public Integer getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(Integer tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public String toString() {
        return "ArquivosPortal{" +
            "id=" + id +
            ", nomeArquivo='" + nomeArquivo + '\'' +
            ", descricao='" + descricao + '\'' +
            ", dataInclusao=" + dataInclusao +
            ", dataAlteracao=" + dataAlteracao +
            ", status=" + status +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArquivosPortal noticiasPortal = (ArquivosPortal) o;
        if (noticiasPortal.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), noticiasPortal.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
