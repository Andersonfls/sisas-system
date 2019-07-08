package com.minea.sisas.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Projectos entity.
 */
public class ProjectosDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 150)
    private String nmProjectos;

    @NotNull
    @Size(max = 2500)
    private String textoProjectos;

    @NotNull
    @Size(max = 100)
    private String resumoTextoProjectos;

    private Long idSituacaoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmProjectos() {
        return nmProjectos;
    }

    public void setNmProjectos(String nmProjectos) {
        this.nmProjectos = nmProjectos;
    }

    public String getTextoProjectos() {
        return textoProjectos;
    }

    public void setTextoProjectos(String textoProjectos) {
        this.textoProjectos = textoProjectos;
    }

    public String getResumoTextoProjectos() {
        return resumoTextoProjectos;
    }

    public void setResumoTextoProjectos(String resumoTextoProjectos) {
        this.resumoTextoProjectos = resumoTextoProjectos;
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

        ProjectosDTO projectosDTO = (ProjectosDTO) o;
        if(projectosDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), projectosDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProjectosDTO{" +
            "id=" + getId() +
            ", nmProjectos='" + getNmProjectos() + "'" +
            ", textoProjectos='" + getTextoProjectos() + "'" +
            ", resumoTextoProjectos='" + getResumoTextoProjectos() + "'" +
            "}";
    }
}
