package com.minea.sisas.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ProgramasProjectosLog entity.
 */
public class ProgramasProjectosLogDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idProgramasProjectosLog;

    @NotNull
    @Size(max = 50)
    private String acao;

    @NotNull
    private Long idUsuario;

    @NotNull
    @Size(max = 3500)
    private String log;

    @NotNull
    private LocalDate dtLog;

    private Long idProgramasProjectosId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProgramasProjectosLog() {
        return idProgramasProjectosLog;
    }

    public void setIdProgramasProjectosLog(Long idProgramasProjectosLog) {
        this.idProgramasProjectosLog = idProgramasProjectosLog;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public LocalDate getDtLog() {
        return dtLog;
    }

    public void setDtLog(LocalDate dtLog) {
        this.dtLog = dtLog;
    }

    public Long getIdProgramasProjectosId() {
        return idProgramasProjectosId;
    }

    public void setIdProgramasProjectosId(Long programasProjectosId) {
        this.idProgramasProjectosId = programasProjectosId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProgramasProjectosLogDTO programasProjectosLogDTO = (ProgramasProjectosLogDTO) o;
        if(programasProjectosLogDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), programasProjectosLogDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProgramasProjectosLogDTO{" +
            "id=" + getId() +
            ", idProgramasProjectosLog=" + getIdProgramasProjectosLog() +
            ", acao='" + getAcao() + "'" +
            ", idUsuario=" + getIdUsuario() +
            ", log='" + getLog() + "'" +
            ", dtLog='" + getDtLog() + "'" +
            "}";
    }
}
