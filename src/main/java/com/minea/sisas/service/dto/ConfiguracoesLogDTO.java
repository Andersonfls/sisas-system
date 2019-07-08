package com.minea.sisas.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ConfiguracoesLog entity.
 */
public class ConfiguracoesLogDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idConfiguracoesLog;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdConfiguracoesLog() {
        return idConfiguracoesLog;
    }

    public void setIdConfiguracoesLog(Long idConfiguracoesLog) {
        this.idConfiguracoesLog = idConfiguracoesLog;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConfiguracoesLogDTO configuracoesLogDTO = (ConfiguracoesLogDTO) o;
        if(configuracoesLogDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), configuracoesLogDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConfiguracoesLogDTO{" +
            "id=" + getId() +
            ", idConfiguracoesLog=" + getIdConfiguracoesLog() +
            ", acao='" + getAcao() + "'" +
            ", idUsuario=" + getIdUsuario() +
            ", log='" + getLog() + "'" +
            ", dtLog='" + getDtLog() + "'" +
            "}";
    }
}
