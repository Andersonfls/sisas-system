package com.minea.sisas.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Provincia entity.
 */
public class ProvinciaDTO implements Comparable<ProvinciaDTO> {

    private Long id;

    @NotNull
    @Size(max = 30)
    private String nmProvincia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmProvincia() {
        return nmProvincia;
    }

    public void setNmProvincia(String nmProvincia) {
        this.nmProvincia = nmProvincia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProvinciaDTO provinciaDTO = (ProvinciaDTO) o;
        if(provinciaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), provinciaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProvinciaDTO{" +
            "id=" + getId() +
            ", nmProvincia='" + getNmProvincia() + "'" +
            "}";
    }

    @Override
    public int compareTo(ProvinciaDTO ob) {
        return nmProvincia.compareTo(ob.getNmProvincia());
    }
}
