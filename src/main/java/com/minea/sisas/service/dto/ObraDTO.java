package com.minea.sisas.service.dto;

import com.minea.sisas.domain.AutorObra;
import com.minea.sisas.domain.TipoObra;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the Obra entity.
 */
public class ObraDTO implements Serializable {

    private Long id;

    private String nome;

    private Boolean status;

    private List<AutorObra> autorObras;

    private TipoObra tipoObra;

    /*------------------ID--------------------*/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    /*----------------------------------------*/

    /*-------------------Nome---------------------------*/
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    /*--------------------------------------------------*/

    /*---------------------------Status--------------------------*/
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    /*-----------------------------------------------------------*/

    /*------------------------------------AutorObra--------------------------------------*/
    public List<AutorObra> getAutorObras() {
        return autorObras;
    }
    public void setAutorObras(List<AutorObra> autorObras) {
        this.autorObras = autorObras;
    }
    /*-----------------------------------------------------------------------------------*/

    /*-----------------------------TipoObra-------------------------------*/
    public TipoObra getTipoObra() {
        return tipoObra;
    }
    public void setTipoObra(TipoObra tipoObra) {
        this.tipoObra = tipoObra;
    }
    /*--------------------------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ObraDTO obraDTO = (ObraDTO) o;
        if(obraDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), obraDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ObraDTO{" +
            "id=" + getId() +
            ", nome=" + getNome() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
