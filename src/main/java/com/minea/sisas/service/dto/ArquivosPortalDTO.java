package com.minea.sisas.service.dto;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the Comuna entity.
 */
public class ArquivosPortalDTO implements Serializable {

    private Long id;

    private String nomeArquivo;

    private String descricao;

    private LocalDate dataInclusao;

    private LocalDate dataAlteracao;

    private Boolean status;

    private String uri;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ArquivosPortalDTO comunaDTO = (ArquivosPortalDTO) o;
        if(comunaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), comunaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ArquivosPortalDTO{" +
            "id=" + id +
            ", nomeArquivo='" + nomeArquivo + '\'' +
            ", descricao='" + descricao + '\'' +
            ", dataInclusao=" + dataInclusao +
            ", dataAlteracao=" + dataAlteracao +
            ", status=" + status +
            ", uri='" + uri + '\'' +
            '}';
    }
}
