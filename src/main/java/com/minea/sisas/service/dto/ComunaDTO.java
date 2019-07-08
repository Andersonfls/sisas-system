package com.minea.sisas.service.dto;


import com.minea.sisas.domain.Municipio;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Comuna entity.
 */
public class ComunaDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 40)
    private String nmComuna;

    private Municipio municipio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmComuna() {
        return nmComuna;
    }

    public void setNmComuna(String nmComuna) {
        this.nmComuna = nmComuna;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ComunaDTO comunaDTO = (ComunaDTO) o;
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
        return "ComunaDTO{" +
            "id=" + getId() +
            ", nmComuna='" + getNmComuna() + "'" +
            "}";
    }
}
