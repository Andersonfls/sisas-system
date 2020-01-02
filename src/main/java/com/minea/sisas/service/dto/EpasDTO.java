package com.minea.sisas.service.dto;


import com.minea.sisas.domain.Comuna;
import com.minea.sisas.domain.Municipio;
import com.minea.sisas.domain.Provincia;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Epas entity.
 */
public class EpasDTO implements Serializable {

    private Long id;

    @Size(max = 100)
    private String nmEpas;

    @Size(max = 80)
    private String nrContribuinte;

    private Provincia provincia;

    private Municipio municipio;

    private Comuna comuna;

    @Size(max = 150)
    private String nmLocalidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmEpas() {
        return nmEpas;
    }

    public void setNmEpas(String nmEpas) {
        this.nmEpas = nmEpas;
    }

    public String getNrContribuinte() {
        return nrContribuinte;
    }

    public void setNrContribuinte(String nrContribuinte) {
        this.nrContribuinte = nrContribuinte;
    }

    public Provincia getProvincia() {
        return provincia;
    }
    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Municipio getMunicipio() {
        return municipio;
    }
    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Comuna getComuna() {
        return comuna;
    }
    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    public String getNmLocalidade() {
        return nmLocalidade;
    }

    public void setNmLocalidade(String nmLocalidade) {
        this.nmLocalidade = nmLocalidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EpasDTO epasDTO = (EpasDTO) o;
        if (epasDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), epasDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EpasDTO{" +
            "id=" + getId() +
            ", nmEpas='" + getNmEpas() + "'" +
            ", nrContribuinte='" + getNrContribuinte() + "'" +
            ", nmLocalidade='" + getNmLocalidade() + "'" +
            "}";
    }
}
