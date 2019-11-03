package com.minea.sisas.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Concurso.
 */
@Entity
@Table(name = "concurso")
public class Concurso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_concurso", nullable = false)
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

    @Column(name = "dt_entrega_proposta")
    private LocalDate dtEntregaProposta;

    @Column(name = "dt_abertura_proposta")
    private LocalDate dtAberturaProposta;

    @Column(name = "dt_conclusao_avaliacao_rel_prel")
    private LocalDate dtConclusaoAvaliacaoRelPrel;

    @Column(name = "dt_negociacao")
    private LocalDate dtNegociacao;

    @Column(name = "dt_aprov_rel_aval_final")
    private LocalDate dtAprovRelAvalFinal;

    @ManyToOne()
    @JoinColumn(name = "id_programas_projectos")
    private ProgramasProjectos programasProjectos;

    @ManyToOne()
    @JoinColumn(name = "id_sistema_agua")
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

    public Concurso tipoConcurso(String tipoConcurso) {
        this.tipoConcurso = tipoConcurso;
        return this;
    }

    public void setTipoConcurso(String tipoConcurso) {
        this.tipoConcurso = tipoConcurso;
    }

    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public Concurso dtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
        return this;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public LocalDate getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }

    public Concurso dtUltimaAlteracao(LocalDate dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
        return this;
    }

    public void setDtUltimaAlteracao(LocalDate dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }

    public LocalDate getDtEntregaProposta() {
        return dtEntregaProposta;
    }

    public Concurso dtEntregaProposta(LocalDate dtEntregaProposta) {
        this.dtEntregaProposta = dtEntregaProposta;
        return this;
    }

    public void setDtEntregaProposta(LocalDate dtEntregaProposta) {
        this.dtEntregaProposta = dtEntregaProposta;
    }

    public LocalDate getDtAberturaProposta() {
        return dtAberturaProposta;
    }

    public Concurso dtAberturaProposta(LocalDate dtAberturaProposta) {
        this.dtAberturaProposta = dtAberturaProposta;
        return this;
    }

    public void setDtAberturaProposta(LocalDate dtAberturaProposta) {
        this.dtAberturaProposta = dtAberturaProposta;
    }

    public LocalDate getDtConclusaoAvaliacaoRelPrel() {
        return dtConclusaoAvaliacaoRelPrel;
    }

    public Concurso dtConclusaoAvaliacaoRelPrel(LocalDate dtConclusaoAvaliacaoRelPrel) {
        this.dtConclusaoAvaliacaoRelPrel = dtConclusaoAvaliacaoRelPrel;
        return this;
    }

    public void setDtConclusaoAvaliacaoRelPrel(LocalDate dtConclusaoAvaliacaoRelPrel) {
        this.dtConclusaoAvaliacaoRelPrel = dtConclusaoAvaliacaoRelPrel;
    }

    public LocalDate getDtNegociacao() {
        return dtNegociacao;
    }

    public Concurso dtNegociacao(LocalDate dtNegociacao) {
        this.dtNegociacao = dtNegociacao;
        return this;
    }

    public void setDtNegociacao(LocalDate dtNegociacao) {
        this.dtNegociacao = dtNegociacao;
    }

    public LocalDate getDtAprovRelAvalFinal() {
        return dtAprovRelAvalFinal;
    }

    public Concurso dtAprovRelAvalFinal(LocalDate dtAprovRelAvalFinal) {
        this.dtAprovRelAvalFinal = dtAprovRelAvalFinal;
        return this;
    }

    public void setDtAprovRelAvalFinal(LocalDate dtAprovRelAvalFinal) {
        this.dtAprovRelAvalFinal = dtAprovRelAvalFinal;
    }

    public ProgramasProjectos getProgramasProjectos() {
        return programasProjectos;
    }

    public Concurso programasProjectos(ProgramasProjectos programasProjectos) {
        this.programasProjectos = programasProjectos;
        return this;
    }

    public void setProgramasProjectos(ProgramasProjectos programasProjectos) {
        this.programasProjectos = programasProjectos;
    }

    public SistemaAgua getIdSistemaAgua() {
        return idSistemaAgua;
    }

    public Concurso idSistemaAgua(SistemaAgua sistemaAgua) {
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
        Concurso concurso = (Concurso) o;
        if (concurso.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), concurso.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Concurso{" +
            "id=" + getId() +
            ", tipoConcurso='" + getTipoConcurso() + "'" +
            ", dtLancamento='" + getDtLancamento() + "'" +
            ", dtUltimaAlteracao='" + getDtUltimaAlteracao() + "'" +
            ", dtEntregaProposta='" + getDtEntregaProposta() + "'" +
            ", dtAberturaProposta='" + getDtAberturaProposta() + "'" +
            ", dtConclusaoAvaliacaoRelPrel='" + getDtConclusaoAvaliacaoRelPrel() + "'" +
            ", dtNegociacao='" + getDtNegociacao() + "'" +
            ", dtAprovRelAvalFinal='" + getDtAprovRelAvalFinal() + "'" +
            "}";
    }
}
