package com.minea.sisas.service.dto;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the FinalidadeProjeto entity.
 */
public class FinalidadeProjetoDTO implements Serializable {

    private Long id;

    @Size(max = 45)
    private String nmFinalidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmFinalidade() {
        return nmFinalidade;
    }

    public void setNmFinalidade(String nmFinalidade) {
        this.nmFinalidade = nmFinalidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FinalidadeProjetoDTO finalidadeProjetoDTO = (FinalidadeProjetoDTO) o;
        if(finalidadeProjetoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), finalidadeProjetoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FinalidadeProjetoDTO{" +
            "id=" + getId() +
            ", nmFinalidade='" + getNmFinalidade() + "'" +
            "}";
    }
}
