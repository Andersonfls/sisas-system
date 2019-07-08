package com.minea.sisas.service.dto;


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
    private Long idProgramasProjectos;

    @NotNull
    private LocalDate dtLancamento;

    private LocalDate dtUltimaAlteracao;

    @NotNull
    private Long idUsuario;

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

    @Size(max = 100)
    private String especialidade;

    private Long idComunaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProgramasProjectos() {
        return idProgramasProjectos;
    }

    public void setIdProgramasProjectos(Long idProgramasProjectos) {
        this.idProgramasProjectos = idProgramasProjectos;
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

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Long getIdComunaId() {
        return idComunaId;
    }

    public void setIdComunaId(Long comunaId) {
        this.idComunaId = comunaId;
    }

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
            ", idProgramasProjectos=" + getIdProgramasProjectos() +
            ", dtLancamento='" + getDtLancamento() + "'" +
            ", dtUltimaAlteracao='" + getDtUltimaAlteracao() + "'" +
            ", idUsuario=" + getIdUsuario() +
            ", nmDesignacaoProjeto='" + getNmDesignacaoProjeto() + "'" +
            ", nmDescricaoProjeto='" + getNmDescricaoProjeto() + "'" +
            ", idSaaAssociado=" + getIdSaaAssociado() +
            ", tipoFinanciamento='" + getTipoFinanciamento() + "'" +
            ", especialidade='" + getEspecialidade() + "'" +
            "}";
    }
}
