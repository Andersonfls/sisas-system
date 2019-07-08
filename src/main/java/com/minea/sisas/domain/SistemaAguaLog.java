package com.minea.sisas.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A SistemaAguaLog.
 */
@Entity
@Table(name = "sistema_agua_log")
public class SistemaAguaLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sistema_agua_log", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "acao", length = 50, nullable = false)
    private String acao;

    @NotNull
    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @NotNull
    @Size(max = 3500)
    @Column(name = "log", length = 3500, nullable = false)
    private String log;

    @NotNull
    @Column(name = "dt_log", nullable = false)
    private LocalDate dtLog;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_sistema_agua")
    private SistemaAgua idSistemaAgua;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcao() {
        return acao;
    }

    public SistemaAguaLog acao(String acao) {
        this.acao = acao;
        return this;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public SistemaAguaLog idUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLog() {
        return log;
    }

    public SistemaAguaLog log(String log) {
        this.log = log;
        return this;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public LocalDate getDtLog() {
        return dtLog;
    }

    public SistemaAguaLog dtLog(LocalDate dtLog) {
        this.dtLog = dtLog;
        return this;
    }

    public void setDtLog(LocalDate dtLog) {
        this.dtLog = dtLog;
    }

    public SistemaAgua getIdSistemaAgua() {
        return idSistemaAgua;
    }

    public SistemaAguaLog idSistemaAgua(SistemaAgua sistemaAgua) {
        this.idSistemaAgua = sistemaAgua;
        return this;
    }

    public void setIdSistemaAgua(SistemaAgua sistemaAgua) {
        this.idSistemaAgua = sistemaAgua;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SistemaAguaLog sistemaAguaLog = (SistemaAguaLog) o;
        if (sistemaAguaLog.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sistemaAguaLog.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SistemaAguaLog{" +
            "id=" + getId() +
            ", acao='" + getAcao() + "'" +
            ", idUsuario=" + getIdUsuario() +
            ", log='" + getLog() + "'" +
            ", dtLog='" + getDtLog() + "'" +
            "}";
    }
}
