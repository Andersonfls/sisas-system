package com.minea.sisas.service.dto;


import com.minea.sisas.domain.Situacao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Publicacao entity.
 */
public class PublicacaoDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 150)
    private String tituloPublicacao;

    @NotNull
    @Size(max = 2500)
    private String textoPublicacao;

    @NotNull
    @Size(max = 100)
    private String resumoTextoPublicacao;

    private Situacao situacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTituloPublicacao() {
        return tituloPublicacao;
    }

    public void setTituloPublicacao(String tituloPublicacao) {
        this.tituloPublicacao = tituloPublicacao;
    }

    public String getTextoPublicacao() {
        return textoPublicacao;
    }

    public void setTextoPublicacao(String textoPublicacao) {
        this.textoPublicacao = textoPublicacao;
    }

    public String getResumoTextoPublicacao() {
        return resumoTextoPublicacao;
    }

    public void setResumoTextoPublicacao(String resumoTextoPublicacao) {
        this.resumoTextoPublicacao = resumoTextoPublicacao;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PublicacaoDTO publicacaoDTO = (PublicacaoDTO) o;
        if(publicacaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), publicacaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PublicacaoDTO{" +
            "id=" + getId() +
            ", tituloPublicacao='" + getTituloPublicacao() + "'" +
            ", textoPublicacao='" + getTextoPublicacao() + "'" +
            ", resumoTextoPublicacao='" + getResumoTextoPublicacao() + "'" +
            "}";
    }
}
