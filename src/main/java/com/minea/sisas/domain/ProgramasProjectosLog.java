package com.minea.sisas.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A ProgramasProjectosLog.
 */
@Entity
@Table(name = "programas_projectos_log")
public class ProgramasProjectosLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_programas_projectos_log", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "acao", length = 50, nullable = false)
    private String acao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario")
    private User usuario;

    @NotNull
    @Size(max = 3500)
    @Column(name = "log", length = 3500, nullable = false)
    private String log;

    @NotNull
    @Column(name = "dt_log", nullable = false)
    private LocalDate dtLog;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_programas_projectos")
    private ProgramasProjectos programasProjectos;

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

    public ProgramasProjectosLog acao(String acao) {
        this.acao = acao;
        return this;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public User getUsuario() {
        return usuario;
    }

    public ProgramasProjectosLog usuario(User usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(User idUsuario) {
        this.usuario = idUsuario;
    }

    public String getLog() {
        return log;
    }

    public ProgramasProjectosLog log(String log) {
        this.log = log;
        return this;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public LocalDate getDtLog() {
        return dtLog;
    }

    public ProgramasProjectosLog dtLog(LocalDate dtLog) {
        this.dtLog = dtLog;
        return this;
    }

    public void setDtLog(LocalDate dtLog) {
        this.dtLog = dtLog;
    }

    public ProgramasProjectos getProgramasProjectos() {
        return programasProjectos;
    }

    public ProgramasProjectosLog programasProjectos(ProgramasProjectos programasProjectos) {
        this.programasProjectos = programasProjectos;
        return this;
    }

    public void setProgramasProjectos(ProgramasProjectos programasProjectos) {
        this.programasProjectos = programasProjectos;
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
        ProgramasProjectosLog programasProjectosLog = (ProgramasProjectosLog) o;
        if (programasProjectosLog.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), programasProjectosLog.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProgramasProjectosLog{" +
            "id=" + getId() +
            ", acao='" + getAcao() + "'" +
            ", idUsuario=" + getUsuario() +
            ", log='" + getLog() + "'" +
            ", dtLog='" + getDtLog() + "'" +
            "}";
    }
}
