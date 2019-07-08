package com.minea.sisas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ProgramasProjectos.
 */
@Entity
@Table(name = "programas_projectos")
public class ProgramasProjectos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_programas_projectos", nullable = false)
    private Long idProgramasProjectos;

    @NotNull
    @Column(name = "dt_lancamento", nullable = false)
    private LocalDate dtLancamento;

    @Column(name = "dt_ultima_alteracao")
    private LocalDate dtUltimaAlteracao;

    @NotNull
    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @NotNull
    @Size(max = 250)
    @Column(name = "nm_designacao_projeto", length = 250, nullable = false)
    private String nmDesignacaoProjeto;

    @NotNull
    @Size(max = 250)
    @Column(name = "nm_descricao_projeto", length = 250, nullable = false)
    private String nmDescricaoProjeto;

    @Column(name = "id_saa_associado")
    private Long idSaaAssociado;

    @NotNull
    @Size(max = 150)
    @Column(name = "tipo_financiamento", length = 150, nullable = false)
    private String tipoFinanciamento;

    @Size(max = 100)
    @Column(name = "especialidade", length = 100)
    private String especialidade;

    @ManyToOne(optional = false)
    @NotNull
    private Comuna idComuna;

    @OneToMany(mappedBy = "idProgramasProjectos")
    @JsonIgnore
    private Set<Adjudicacao> adjudicacaos = new HashSet<>();

    @OneToMany(mappedBy = "idProgramasProjectos")
    @JsonIgnore
    private Set<Concepcao> concepcaos = new HashSet<>();

    @OneToMany(mappedBy = "idProgramasProjectos")
    @JsonIgnore
    private Set<Concurso> concursos = new HashSet<>();

    @OneToMany(mappedBy = "idProgramasProjectos")
    @JsonIgnore
    private Set<Contrato> contratoes = new HashSet<>();

    @OneToMany(mappedBy = "idProgramasProjectos")
    @JsonIgnore
    private Set<Empreitada> empreitadas = new HashSet<>();

    @OneToMany(mappedBy = "idProgramasProjectos")
    @JsonIgnore
    private Set<Execucao> execucaos = new HashSet<>();

    @OneToMany(mappedBy = "idProgramasProjectos")
    @JsonIgnore
    private Set<ProgramasProjectosLog> programasProjectosLogs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProgramasProjectos() {
        return idProgramasProjectos;
    }

    public ProgramasProjectos idProgramasProjectos(Long idProgramasProjectos) {
        this.idProgramasProjectos = idProgramasProjectos;
        return this;
    }

    public void setIdProgramasProjectos(Long idProgramasProjectos) {
        this.idProgramasProjectos = idProgramasProjectos;
    }

    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public ProgramasProjectos dtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
        return this;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public LocalDate getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }

    public ProgramasProjectos dtUltimaAlteracao(LocalDate dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
        return this;
    }

    public void setDtUltimaAlteracao(LocalDate dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public ProgramasProjectos idUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNmDesignacaoProjeto() {
        return nmDesignacaoProjeto;
    }

    public ProgramasProjectos nmDesignacaoProjeto(String nmDesignacaoProjeto) {
        this.nmDesignacaoProjeto = nmDesignacaoProjeto;
        return this;
    }

    public void setNmDesignacaoProjeto(String nmDesignacaoProjeto) {
        this.nmDesignacaoProjeto = nmDesignacaoProjeto;
    }

    public String getNmDescricaoProjeto() {
        return nmDescricaoProjeto;
    }

    public ProgramasProjectos nmDescricaoProjeto(String nmDescricaoProjeto) {
        this.nmDescricaoProjeto = nmDescricaoProjeto;
        return this;
    }

    public void setNmDescricaoProjeto(String nmDescricaoProjeto) {
        this.nmDescricaoProjeto = nmDescricaoProjeto;
    }

    public Long getIdSaaAssociado() {
        return idSaaAssociado;
    }

    public ProgramasProjectos idSaaAssociado(Long idSaaAssociado) {
        this.idSaaAssociado = idSaaAssociado;
        return this;
    }

    public void setIdSaaAssociado(Long idSaaAssociado) {
        this.idSaaAssociado = idSaaAssociado;
    }

    public String getTipoFinanciamento() {
        return tipoFinanciamento;
    }

    public ProgramasProjectos tipoFinanciamento(String tipoFinanciamento) {
        this.tipoFinanciamento = tipoFinanciamento;
        return this;
    }

    public void setTipoFinanciamento(String tipoFinanciamento) {
        this.tipoFinanciamento = tipoFinanciamento;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public ProgramasProjectos especialidade(String especialidade) {
        this.especialidade = especialidade;
        return this;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Comuna getIdComuna() {
        return idComuna;
    }

    public ProgramasProjectos idComuna(Comuna comuna) {
        this.idComuna = comuna;
        return this;
    }

    public void setIdComuna(Comuna comuna) {
        this.idComuna = comuna;
    }

    public Set<Adjudicacao> getAdjudicacaos() {
        return adjudicacaos;
    }

    public ProgramasProjectos adjudicacaos(Set<Adjudicacao> adjudicacaos) {
        this.adjudicacaos = adjudicacaos;
        return this;
    }

    public ProgramasProjectos addAdjudicacao(Adjudicacao adjudicacao) {
        this.adjudicacaos.add(adjudicacao);
        adjudicacao.setIdProgramasProjectos(this);
        return this;
    }

    public ProgramasProjectos removeAdjudicacao(Adjudicacao adjudicacao) {
        this.adjudicacaos.remove(adjudicacao);
        adjudicacao.setIdProgramasProjectos(null);
        return this;
    }

    public void setAdjudicacaos(Set<Adjudicacao> adjudicacaos) {
        this.adjudicacaos = adjudicacaos;
    }

    public Set<Concepcao> getConcepcaos() {
        return concepcaos;
    }

    public ProgramasProjectos concepcaos(Set<Concepcao> concepcaos) {
        this.concepcaos = concepcaos;
        return this;
    }

    public ProgramasProjectos addConcepcao(Concepcao concepcao) {
        this.concepcaos.add(concepcao);
        concepcao.setIdProgramasProjectos(this);
        return this;
    }

    public ProgramasProjectos removeConcepcao(Concepcao concepcao) {
        this.concepcaos.remove(concepcao);
        concepcao.setIdProgramasProjectos(null);
        return this;
    }

    public void setConcepcaos(Set<Concepcao> concepcaos) {
        this.concepcaos = concepcaos;
    }

    public Set<Concurso> getConcursos() {
        return concursos;
    }

    public ProgramasProjectos concursos(Set<Concurso> concursos) {
        this.concursos = concursos;
        return this;
    }

    public ProgramasProjectos addConcurso(Concurso concurso) {
        this.concursos.add(concurso);
        concurso.setIdProgramasProjectos(this);
        return this;
    }

    public ProgramasProjectos removeConcurso(Concurso concurso) {
        this.concursos.remove(concurso);
        concurso.setIdProgramasProjectos(null);
        return this;
    }

    public void setConcursos(Set<Concurso> concursos) {
        this.concursos = concursos;
    }

    public Set<Contrato> getContratoes() {
        return contratoes;
    }

    public ProgramasProjectos contratoes(Set<Contrato> contratoes) {
        this.contratoes = contratoes;
        return this;
    }

    public ProgramasProjectos addContrato(Contrato contrato) {
        this.contratoes.add(contrato);
        contrato.setIdProgramasProjectos(this);
        return this;
    }

    public ProgramasProjectos removeContrato(Contrato contrato) {
        this.contratoes.remove(contrato);
        contrato.setIdProgramasProjectos(null);
        return this;
    }

    public void setContratoes(Set<Contrato> contratoes) {
        this.contratoes = contratoes;
    }

    public Set<Empreitada> getEmpreitadas() {
        return empreitadas;
    }

    public ProgramasProjectos empreitadas(Set<Empreitada> empreitadas) {
        this.empreitadas = empreitadas;
        return this;
    }

    public ProgramasProjectos addEmpreitada(Empreitada empreitada) {
        this.empreitadas.add(empreitada);
        empreitada.setIdProgramasProjectos(this);
        return this;
    }

    public ProgramasProjectos removeEmpreitada(Empreitada empreitada) {
        this.empreitadas.remove(empreitada);
        empreitada.setIdProgramasProjectos(null);
        return this;
    }

    public void setEmpreitadas(Set<Empreitada> empreitadas) {
        this.empreitadas = empreitadas;
    }

    public Set<Execucao> getExecucaos() {
        return execucaos;
    }

    public ProgramasProjectos execucaos(Set<Execucao> execucaos) {
        this.execucaos = execucaos;
        return this;
    }

    public ProgramasProjectos addExecucao(Execucao execucao) {
        this.execucaos.add(execucao);
        execucao.setIdProgramasProjectos(this);
        return this;
    }

    public ProgramasProjectos removeExecucao(Execucao execucao) {
        this.execucaos.remove(execucao);
        execucao.setIdProgramasProjectos(null);
        return this;
    }

    public void setExecucaos(Set<Execucao> execucaos) {
        this.execucaos = execucaos;
    }

    public Set<ProgramasProjectosLog> getProgramasProjectosLogs() {
        return programasProjectosLogs;
    }

    public ProgramasProjectos programasProjectosLogs(Set<ProgramasProjectosLog> programasProjectosLogs) {
        this.programasProjectosLogs = programasProjectosLogs;
        return this;
    }

    public ProgramasProjectos addProgramasProjectosLog(ProgramasProjectosLog programasProjectosLog) {
        this.programasProjectosLogs.add(programasProjectosLog);
        programasProjectosLog.setIdProgramasProjectos(this);
        return this;
    }

    public ProgramasProjectos removeProgramasProjectosLog(ProgramasProjectosLog programasProjectosLog) {
        this.programasProjectosLogs.remove(programasProjectosLog);
        programasProjectosLog.setIdProgramasProjectos(null);
        return this;
    }

    public void setProgramasProjectosLogs(Set<ProgramasProjectosLog> programasProjectosLogs) {
        this.programasProjectosLogs = programasProjectosLogs;
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
        ProgramasProjectos programasProjectos = (ProgramasProjectos) o;
        if (programasProjectos.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), programasProjectos.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProgramasProjectos{" +
            "id=" + getId() +
            ", idProgramasProjectos=" + getIdProgramasProjectos() +
            ", dtLancamento='" + getDtLancamento() + "'" +
            ", dtUltimaAlteracao='" + getDtUltimaAlteracao() + "'" +
            ", idUsuario=" + getIdUsuario() +
            ", nmDesignacaoProjeto='" + getNmDesignacaoProjeto() + "'" +
            ", nmDescricaoProjeto='" + getNmDescricaoProjeto() + "'" +
            ", idSaaAssociado=" + getIdSaaAssociado() +
            ", tipoFinanciamento='" + getTipoFinanciamento() + "'" +
            ", especialidade='" + getEspecialidade() + "'" +
            "}";
    }
}
