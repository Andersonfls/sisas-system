package com.minea.sisas.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Concepcao.
 */
@Entity
@Table(name = "concepcao")
public class Concepcao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_concepcao", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 150)
    @Column(name = "tipo_concurso", length = 150, nullable = false)
    private String tipoConcurso;

    @NotNull
    @Column(name = "dt_lancamento", nullable = false)
    private LocalDate dtLancamento;

    @Column(name = "dt_ultima_alteracao")
    private LocalDate dtUltimaAlteracao;

    @Column(name = "dt_elaboracao_con")
    private LocalDate dtElaboracaoCon;

    @Column(name = "dt_aprovacao_con")
    private LocalDate dtAprovacaoCon;

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

    public Concepcao tipoConcurso(String tipoConcurso) {
        this.tipoConcurso = tipoConcurso;
        return this;
    }

    public void setTipoConcurso(String tipoConcurso) {
        this.tipoConcurso = tipoConcurso;
    }

    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public Concepcao dtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
        return this;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public LocalDate getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }

    public Concepcao dtUltimaAlteracao(LocalDate dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
        return this;
    }

    public void setDtUltimaAlteracao(LocalDate dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }

    public LocalDate getDtElaboracaoCon() {
        return dtElaboracaoCon;
    }

    public Concepcao dtElaboracaoCon(LocalDate dtElaboracaoCon) {
        this.dtElaboracaoCon = dtElaboracaoCon;
        return this;
    }

    public void setDtElaboracaoCon(LocalDate dtElaboracaoCon) {
        this.dtElaboracaoCon = dtElaboracaoCon;
    }

    public LocalDate getDtAprovacaoCon() {
        return dtAprovacaoCon;
    }

    public Concepcao dtAprovacaoCon(LocalDate dtAprovacaoCon) {
        this.dtAprovacaoCon = dtAprovacaoCon;
        return this;
    }

    public void setDtAprovacaoCon(LocalDate dtAprovacaoCon) {
        this.dtAprovacaoCon = dtAprovacaoCon;
    }

    public ProgramasProjectos getIdProgramasProjectos() {
        return idProgramasProjectos;
    }

    public Concepcao idProgramasProjectos(ProgramasProjectos programasProjectos) {
        this.idProgramasProjectos = programasProjectos;
        return this;
    }

    public void setIdProgramasProjectos(ProgramasProjectos programasProjectos) {
        this.idProgramasProjectos = programasProjectos;
    }

    public SistemaAgua getIdSistemaAgua() {
        return idSistemaAgua;
    }

    public Concepcao idSistemaAgua(SistemaAgua sistemaAgua) {
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
        Concepcao concepcao = (Concepcao) o;
        if (concepcao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), concepcao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Concepcao{" +
            "id=" + getId() +
            ", tipoConcurso='" + getTipoConcurso() + "'" +
            ", dtLancamento='" + getDtLancamento() + "'" +
            ", dtUltimaAlteracao='" + getDtUltimaAlteracao() + "'" +
            ", dtElaboracaoCon='" + getDtElaboracaoCon() + "'" +
            ", dtAprovacaoCon='" + getDtAprovacaoCon() + "'" +
            "}";
    }
}
