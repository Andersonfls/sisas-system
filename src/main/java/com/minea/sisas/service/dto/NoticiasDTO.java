package com.minea.sisas.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Noticias entity.
 */
public class NoticiasDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idNoticias;

    @NotNull
    @Size(max = 150)
    private String tituloNoticias;

    @NotNull
    @Size(max = 2500)
    private String textoNoticias;

    @NotNull
    @Size(max = 100)
    private String resumoTextoNoticias;

    private Long idSituacaoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdNoticias() {
        return idNoticias;
    }

    public void setIdNoticias(Long idNoticias) {
        this.idNoticias = idNoticias;
    }

    public String getTituloNoticias() {
        return tituloNoticias;
    }

    public void setTituloNoticias(String tituloNoticias) {
        this.tituloNoticias = tituloNoticias;
    }

    public String getTextoNoticias() {
        return textoNoticias;
    }

    public void setTextoNoticias(String textoNoticias) {
        this.textoNoticias = textoNoticias;
    }

    public String getResumoTextoNoticias() {
        return resumoTextoNoticias;
    }

    public void setResumoTextoNoticias(String resumoTextoNoticias) {
        this.resumoTextoNoticias = resumoTextoNoticias;
    }

    public Long getIdSituacaoId() {
        return idSituacaoId;
    }

    public void setIdSituacaoId(Long situacaoId) {
        this.idSituacaoId = situacaoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NoticiasDTO noticiasDTO = (NoticiasDTO) o;
        if(noticiasDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), noticiasDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NoticiasDTO{" +
            "id=" + getId() +
            ", idNoticias=" + getIdNoticias() +
            ", tituloNoticias='" + getTituloNoticias() + "'" +
            ", textoNoticias='" + getTextoNoticias() + "'" +
            ", resumoTextoNoticias='" + getResumoTextoNoticias() + "'" +
            "}";
    }
}
