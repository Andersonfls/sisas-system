package com.minea.sisas.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the OrigemFinanciamento entity.
 */
public class OrigemFinanciamentoDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idOrigemFinanciamento;

    @NotNull
    @Size(max = 200)
    private String descricaoOrigemFinanciamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOrigemFinanciamento() {
        return idOrigemFinanciamento;
    }

    public void setIdOrigemFinanciamento(Long idOrigemFinanciamento) {
        this.idOrigemFinanciamento = idOrigemFinanciamento;
    }

    public String getDescricaoOrigemFinanciamento() {
        return descricaoOrigemFinanciamento;
    }

    public void setDescricaoOrigemFinanciamento(String descricaoOrigemFinanciamento) {
        this.descricaoOrigemFinanciamento = descricaoOrigemFinanciamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrigemFinanciamentoDTO origemFinanciamentoDTO = (OrigemFinanciamentoDTO) o;
        if(origemFinanciamentoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), origemFinanciamentoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrigemFinanciamentoDTO{" +
            "id=" + getId() +
            ", idOrigemFinanciamento=" + getIdOrigemFinanciamento() +
            ", descricaoOrigemFinanciamento='" + getDescricaoOrigemFinanciamento() + "'" +
            "}";
    }
}
