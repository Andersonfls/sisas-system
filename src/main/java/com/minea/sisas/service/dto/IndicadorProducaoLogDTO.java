package com.minea.sisas.service.dto;


import com.minea.sisas.domain.IndicadorProducao;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the IndicadorProducaoLog entity.
 */
public class IndicadorProducaoLogDTO implements Serializable {

    private Long id;

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

    private IndicadorProducao idIndicadorProducaoId;

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

    public IndicadorProducao getIdIndicadorProducaoId() {
        return idIndicadorProducaoId;
    }

    public void setIdIndicadorProducaoId(IndicadorProducao indicadorProducaoId) {
        this.idIndicadorProducaoId = indicadorProducaoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IndicadorProducaoLogDTO indicadorProducaoLogDTO = (IndicadorProducaoLogDTO) o;
        if(indicadorProducaoLogDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), indicadorProducaoLogDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "IndicadorProducaoLogDTO{" +
            "id=" + getId() +
            ", acao='" + getAcao() + "'" +
            ", idUsuario=" + getIdUsuario() +
            ", log='" + getLog() + "'" +
            ", dtLog='" + getDtLog() + "'" +
            "}";
    }
}
