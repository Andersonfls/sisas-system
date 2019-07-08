package com.minea.sisas.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Adjudicacao.
 */
@Entity
@Table(name = "adjudicacao")
public class Adjudicacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adjudicacao", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 150)
    @Column(name = "tipo_concurso", length = 150, nullable = false)
    private String tipoConcurso;

    @NotNull
    @Column(name = "dt_lancamento", nullable = false)
    private LocalDate dtLancamento;

    @Column(name = "dt_comunicao_adjudicacao")
    private LocalDate dtComunicaoAdjudicacao;

    @Column(name = "dt_prestacao_garant_boa_exec")
    private LocalDate dtPrestacaoGarantBoaExec;

    @Column(name = "dt_submissao_minut_contrato")
    private LocalDate dtSubmissaoMinutContrato;

    @ManyToOne(optional = false)
    @NotNull
    private ProgramasProjectos idProgramasProjectos;

    @ManyToOne
    private SistemaAgua idSistemaAgua;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoConcurso() {
        return tipoConcurso;
    }

    public Adjudicacao tipoConcurso(String tipoConcurso) {
        this.tipoConcurso = tipoConcurso;
        return this;
    }

    public void setTipoConcurso(String tipoConcurso) {
        this.tipoConcurso = tipoConcurso;
    }

    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public Adjudicacao dtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
        return this;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public LocalDate getDtComunicaoAdjudicacao() {
        return dtComunicaoAdjudicacao;
    }

    public Adjudicacao dtComunicaoAdjudicacao(LocalDate dtComunicaoAdjudicacao) {
        this.dtComunicaoAdjudicacao = dtComunicaoAdjudicacao;
        return this;
    }

    public void setDtComunicaoAdjudicacao(LocalDate dtComunicaoAdjudicacao) {
        this.dtComunicaoAdjudicacao = dtComunicaoAdjudicacao;
    }

    public LocalDate getDtPrestacaoGarantBoaExec() {
        return dtPrestacaoGarantBoaExec;
    }

    public Adjudicacao dtPrestacaoGarantBoaExec(LocalDate dtPrestacaoGarantBoaExec) {
        this.dtPrestacaoGarantBoaExec = dtPrestacaoGarantBoaExec;
        return this;
    }

    public void setDtPrestacaoGarantBoaExec(LocalDate dtPrestacaoGarantBoaExec) {
        this.dtPrestacaoGarantBoaExec = dtPrestacaoGarantBoaExec;
    }

    public LocalDate getDtSubmissaoMinutContrato() {
        return dtSubmissaoMinutContrato;
    }

    public Adjudicacao dtSubmissaoMinutContrato(LocalDate dtSubmissaoMinutContrato) {
        this.dtSubmissaoMinutContrato = dtSubmissaoMinutContrato;
        return this;
    }

    public void setDtSubmissaoMinutContrato(LocalDate dtSubmissaoMinutContrato) {
        this.dtSubmissaoMinutContrato = dtSubmissaoMinutContrato;
    }

    public ProgramasProjectos getIdProgramasProjectos() {
        return idProgramasProjectos;
    }

    public Adjudicacao idProgramasProjectos(ProgramasProjectos programasProjectos) {
        this.idProgramasProjectos = programasProjectos;
        return this;
    }

    public void setIdProgramasProjectos(ProgramasProjectos programasProjectos) {
        this.idProgramasProjectos = programasProjectos;
    }

    public SistemaAgua getIdSistemaAgua() {
        return idSistemaAgua;
    }

    public Adjudicacao idSistemaAgua(SistemaAgua sistemaAgua) {
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
        Adjudicacao adjudicacao = (Adjudicacao) o;
        if (adjudicacao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adjudicacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Adjudicacao{" +
            "id=" + getId() +
            ", tipoConcurso='" + getTipoConcurso() + "'" +
            ", dtLancamento='" + getDtLancamento() + "'" +
            ", dtComunicaoAdjudicacao='" + getDtComunicaoAdjudicacao() + "'" +
            ", dtPrestacaoGarantBoaExec='" + getDtPrestacaoGarantBoaExec() + "'" +
            ", dtSubmissaoMinutContrato='" + getDtSubmissaoMinutContrato() + "'" +
            "}";
    }
}
