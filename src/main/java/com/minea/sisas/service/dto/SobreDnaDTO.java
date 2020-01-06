package com.minea.sisas.service.dto;


import com.minea.sisas.domain.Situacao;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the SobreDna entity.
 */
public class SobreDnaDTO implements Serializable {

    private Long id;

    @Size(max = 150)
    private String tituloSobreDna;

    @Size(max = 2500)
    private String textoSobreDna;

    @Size(max = 100)
    private String resumoTextoSobreDna;

    private Situacao situacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTituloSobreDna() {
        return tituloSobreDna;
    }

    public void setTituloSobreDna(String tituloSobreDna) {
        this.tituloSobreDna = tituloSobreDna;
    }

    public String getTextoSobreDna() {
        return textoSobreDna;
    }

    public void setTextoSobreDna(String textoSobreDna) {
        this.textoSobreDna = textoSobreDna;
    }

    public String getResumoTextoSobreDna() {
        return resumoTextoSobreDna;
    }

    public void setResumoTextoSobreDna(String resumoTextoSobreDna) {
        this.resumoTextoSobreDna = resumoTextoSobreDna;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SobreDnaDTO sobreDnaDTO = (SobreDnaDTO) o;
        if(sobreDnaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sobreDnaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SobreDnaDTO{" +
            "id=" + getId() +
            ", tituloSobreDna='" + getTituloSobreDna() + "'" +
            ", textoSobreDna='" + getTextoSobreDna() + "'" +
            ", resumoTextoSobreDna='" + getResumoTextoSobreDna() + "'" +
            "}";
    }
}
