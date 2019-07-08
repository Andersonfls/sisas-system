package com.minea.sisas.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the RelatoriosLog entity.
 */
public class RelatoriosLogDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idRelatoriosLog;

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

    public Long getIdRelatoriosLog() {
        return idRelatoriosLog;
    }

    public void setIdRelatoriosLog(Long idRelatoriosLog) {
        this.idRelatoriosLog = idRelatoriosLog;
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

        RelatoriosLogDTO relatoriosLogDTO = (RelatoriosLogDTO) o;
        if(relatoriosLogDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), relatoriosLogDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RelatoriosLogDTO{" +
            "id=" + getId() +
            ", idRelatoriosLog=" + getIdRelatoriosLog() +
            ", acao='" + getAcao() + "'" +
            ", idUsuario=" + getIdUsuario() +
            ", log='" + getLog() + "'" +
            ", dtLog='" + getDtLog() + "'" +
            "}";
    }
}
