package com.minea.sisas.service.dto;


import com.minea.sisas.domain.Provincia;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Municipio entity.
 */
public class MunicipioDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idMunicipio;

    @NotNull
    @Size(max = 30)
    private String nmMunicipio;

    private Provincia provincia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Long idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getNmMunicipio() {
        return nmMunicipio;
    }

    public void setNmMunicipio(String nmMunicipio) {
        this.nmMunicipio = nmMunicipio;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MunicipioDTO municipioDTO = (MunicipioDTO) o;
        if(municipioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), municipioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MunicipioDTO{" +
            "id=" + getId() +
            ", idMunicipio=" + getIdMunicipio() +
            ", nmMunicipio='" + getNmMunicipio() + "'" +
            "}";
    }
}
