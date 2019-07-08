package com.minea.sisas.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Situacao entity.
 */
public class SituacaoDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String nmSituacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmSituacao() {
        return nmSituacao;
    }

    public void setNmSituacao(String nmSituacao) {
        this.nmSituacao = nmSituacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SituacaoDTO situacaoDTO = (SituacaoDTO) o;
        if(situacaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), situacaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SituacaoDTO{" +
            "id=" + getId() +
            ", nmSituacao='" + getNmSituacao() + "'" +
            "}";
    }
}
