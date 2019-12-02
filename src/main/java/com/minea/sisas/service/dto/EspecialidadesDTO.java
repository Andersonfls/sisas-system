package com.minea.sisas.service.dto;


import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Especialidades entity.
 */
public class EspecialidadesDTO implements Serializable {

    private Long id;

    @Size(max = 100)
    private String nmEspecialidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmEspecialidade() {
        return nmEspecialidade;
    }

    public void setNmEspecialidade(String nmEspecialidade) {
        this.nmEspecialidade = nmEspecialidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EspecialidadesDTO especialidadesDTO = (EspecialidadesDTO) o;
        if (especialidadesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), especialidadesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EspecialidadesDTO{" +
            "id=" + getId() +
            ", nmEspecialidade='" + getNmEspecialidade() + "'" +
            "}";
    }
}
