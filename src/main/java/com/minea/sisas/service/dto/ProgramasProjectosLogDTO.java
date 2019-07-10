package com.minea.sisas.service.dto;


import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.domain.User;

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
    @Size(max = 50)
    private String acao;

    @NotNull
    private User usuario;

    @NotNull
    @Size(max = 3500)
    private String log;

    @NotNull
    private LocalDate dtLog;

    private ProgramasProjectos programasProjectos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
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

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public ProgramasProjectos getProgramasProjectos() {
        return programasProjectos;
    }

    public void setProgramasProjectos(ProgramasProjectos programasProjectos) {
        this.programasProjectos = programasProjectos;
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
            ", acao='" + getAcao() + "'" +
            ", usuario=" + getUsuario() +
            ", log='" + getLog() + "'" +
            ", dtLog='" + getDtLog() + "'" +
            "}";
    }
}
