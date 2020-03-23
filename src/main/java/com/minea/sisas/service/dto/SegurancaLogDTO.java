package com.minea.sisas.service.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the RelatoriosLog entity.
 */
public class SegurancaLogDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String acao;

    @NotNull
    private Long idUsuario;

    @NotNull
    private Long idUsuarioAlterado;

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

    public Long getIdUsuarioAlterado() {
        return idUsuarioAlterado;
    }

    public void setIdUsuarioAlterado(Long idUsuarioAlterado) {
        this.idUsuarioAlterado = idUsuarioAlterado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SegurancaLogDTO relatoriosLogDTO = (SegurancaLogDTO) o;
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
        return "SegurancaLogDTO{" +
            "id=" + id +
            ", acao='" + acao + '\'' +
            ", idUsuario=" + idUsuario +
            ", idUsuarioAlterado=" + idUsuarioAlterado +
            ", log='" + log + '\'' +
            ", dtLog=" + dtLog +
            '}';
    }
}
