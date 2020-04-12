package com.minea.sisas.service.dto;


import com.minea.sisas.domain.*;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ProgramasProjectos entity.
 */
public class ProgramasProjectosDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate dtLancamento;

    private LocalDate dtUltimaAlteracao;

    private User usuario;

    @NotNull
    @Size(max = 250)
    private String nmDesignacaoProjeto;

    @NotNull
    @Size(max = 250)
    private String nmDescricaoProjeto;

    private Long idSaaAssociado;

    @NotNull
    @Size(max = 150)
    private String tipoFinanciamento;

    private Especialidades especialidade;

    @NotNull
    private Comuna comuna;

    @NotNull
    private Provincia provincia;

    @NotNull
    private Municipio municipio;

    @Size(max = 150)
    private String nmLocalidade;

    @Size(max = 150)
    private String finalidadeProjeto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public LocalDate getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }

    public void setDtUltimaAlteracao(LocalDate dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }

    public String getNmDesignacaoProjeto() {
        return nmDesignacaoProjeto;
    }

    public void setNmDesignacaoProjeto(String nmDesignacaoProjeto) {
        this.nmDesignacaoProjeto = nmDesignacaoProjeto;
    }

    public String getNmDescricaoProjeto() {
        return nmDescricaoProjeto;
    }

    public void setNmDescricaoProjeto(String nmDescricaoProjeto) {
        this.nmDescricaoProjeto = nmDescricaoProjeto;
    }

    public Long getIdSaaAssociado() {
        return idSaaAssociado;
    }

    public void setIdSaaAssociado(Long idSaaAssociado) {
        this.idSaaAssociado = idSaaAssociado;
    }

    public String getTipoFinanciamento() {
        return tipoFinanciamento;
    }

    public void setTipoFinanciamento(String tipoFinanciamento) {
        this.tipoFinanciamento = tipoFinanciamento;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Especialidades getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidades(Especialidades especialidade) {
        this.especialidade = especialidade;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    public Provincia getProvincia() { return provincia; }

    public void setProvincia(Provincia provincia) { this.provincia = provincia; }

    public Municipio getMunicipio() { return municipio; }

    public void setMunicipio(Municipio municipio) { this.municipio = municipio; }

    public String getNmLocalidade() {
        return nmLocalidade;
    }

    public void setNmLocalidade(String nmLocalidade) {
        this.nmLocalidade = nmLocalidade;
    }

    public String getFinalidadeProjeto() { return finalidadeProjeto; }

    public void setFinalidadeProjeto(String finalidadeProjeto) { this.finalidadeProjeto = finalidadeProjeto; }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProgramasProjectosDTO programasProjectosDTO = (ProgramasProjectosDTO) o;
        if(programasProjectosDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), programasProjectosDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProgramasProjectosDTO{" +
            "id=" + getId() +
            ", dtLancamento='" + getDtLancamento() + "'" +
            ", dtUltimaAlteracao='" + getDtUltimaAlteracao() + "'" +
            ", usuario=" + getUsuario() +
            ", nmDesignacaoProjeto='" + getNmDesignacaoProjeto() + "'" +
            ", nmDescricaoProjeto='" + getNmDescricaoProjeto() + "'" +
            ", idSaaAssociado=" + getIdSaaAssociado() +
            ", tipoFinanciamento='" + getTipoFinanciamento() + "'" +
            ", especialidades='" + getEspecialidade() + "'" +
            "}";
    }
}
