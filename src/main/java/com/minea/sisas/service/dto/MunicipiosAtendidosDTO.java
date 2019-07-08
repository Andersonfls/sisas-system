package com.minea.sisas.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MunicipiosAtendidos entity.
 */
public class MunicipiosAtendidosDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idMunicipioAtendido;

    private Long idMunicipioId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMunicipioAtendido() {
        return idMunicipioAtendido;
    }

    public void setIdMunicipioAtendido(Long idMunicipioAtendido) {
        this.idMunicipioAtendido = idMunicipioAtendido;
    }

    public Long getIdMunicipioId() {
        return idMunicipioId;
    }

    public void setIdMunicipioId(Long municipioId) {
        this.idMunicipioId = municipioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MunicipiosAtendidosDTO municipiosAtendidosDTO = (MunicipiosAtendidosDTO) o;
        if(municipiosAtendidosDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), municipiosAtendidosDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MunicipiosAtendidosDTO{" +
            "id=" + getId() +
            ", idMunicipioAtendido=" + getIdMunicipioAtendido() +
            "}";
    }
}
