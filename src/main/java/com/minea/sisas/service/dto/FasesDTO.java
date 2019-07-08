package com.minea.sisas.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Fases entity.
 */
public class FasesDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idFase;

    @NotNull
    @Size(max = 200)
    private String descricaoFase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdFase() {
        return idFase;
    }

    public void setIdFase(Long idFase) {
        this.idFase = idFase;
    }

    public String getDescricaoFase() {
        return descricaoFase;
    }

    public void setDescricaoFase(String descricaoFase) {
        this.descricaoFase = descricaoFase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FasesDTO fasesDTO = (FasesDTO) o;
        if(fasesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fasesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FasesDTO{" +
            "id=" + getId() +
            ", idFase=" + getIdFase() +
            ", descricaoFase='" + getDescricaoFase() + "'" +
            "}";
    }
}
