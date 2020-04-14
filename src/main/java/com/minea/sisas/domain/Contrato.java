package com.minea.sisas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Contrato.
 */
@Entity
@Table(name = "contrato")
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 150)
    @Column(name = "tipo_empreitada", length = 150, nullable = false)
    private String tipoEmpreitada;

    @NotNull
    @Column(name = "dt_lancamento", nullable = false)
    private LocalDate dtLancamento;

    @Size(max = 200)
    @Column(name = "nm_empresa_adjudicitaria", length = 200)
    private String nmEmpresaAdjudicitaria;

    @NotNull
    @Column(name = "valor_contrato", precision=10, scale=2, nullable = false)
    private BigDecimal valorContrato;

    @Column(name = "dt_assinatura")
    private LocalDate dtAssinatura;

    @Column(name = "dt_finalizacao_processo_homolog_aprov")
    private LocalDate dtFinalizacaoProcessoHomologAprov;

    @NotNull
    @Size(max = 150)
    @Column(name = "tipo_moeda", length = 150, nullable = false)
    private String tipoMoeda;

    @NotNull
    @Column(name = "valor_adiantamento", precision=10, scale=2, nullable = false)
    private BigDecimal valorAdiantamento;

    @Column(name = "dt_adiantamento")
    private LocalDate dtAdiantamento;

    @Column(name = "dt_inicio")
    private LocalDate dtInicio;

    @Column(name = "prazo_execucao", precision=10, scale=2)
    private BigDecimal prazoExecucao;

    @Column(name = "dt_recepcao_provisoria")
    private LocalDate dtRecepcaoProvisoria;

    @Column(name = "dt_recepcao_definitiva")
    private LocalDate dtRecepcaoDefinitiva;

    @Column(name = "dt_recepcao_comicionamento")
    private LocalDate dtRecepcaoComicionamento;

    @Size(max = 200)
    @Column(name = "nm_resposavel_ant_projeto", length = 200)
    private String nmResposavelAntProjeto;

    @Size(max = 200)
    @Column(name = "nm_resposavel_projeto", length = 200)
    private String nmResposavelProjeto;

    @ManyToOne()
    @JoinColumn(name = "id_programas_projectos")
    private ProgramasProjectos programasProjectos;

    @ManyToOne()
    @JoinColumn(name = "id_sistema_agua")
    private SistemaAgua idSistemaAgua;

    @OneToMany(mappedBy = "idContrato")
    @JsonIgnore
    private Set<Empreitada> empreitadas = new HashSet<>();

    @OneToMany(mappedBy = "idContrato")
    @JsonIgnore
    private Set<Execucao> execucaos = new HashSet<>();

    //NOVOS
    @NotNull
    @Size(max = 150)
    @Column(name = "tipo_concurso", length = 150, nullable = false)
    private String tipoConcurso;

    @Column(name = "dt_visto_tribunal_contas")
    private LocalDate dtVistoTribunalContas;

    @Column(name = "dt_pagamento_emolumentos")
    private LocalDate dtPagamentoEmolumentos;

    @Column(name = "dt_prazo_garantia_adit")
    private LocalDate dtPrazoGarantiaAditamento;

    @Column(name = "dt_prazos_vinculativos")
    private LocalDate dtPrazosVinculativos;

    @Column(name = "prazo_garantia_adiant", precision=10, scale=2)
    private BigDecimal prazoGarantiaAdiantamento;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoEmpreitada() {
        return tipoEmpreitada;
    }

    public Contrato tipoEmpreitada(String tipoEmpreitada) {
        this.tipoEmpreitada = tipoEmpreitada;
        return this;
    }

    public void setTipoEmpreitada(String tipoEmpreitada) {
        this.tipoEmpreitada = tipoEmpreitada;
    }

    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public Contrato dtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
        return this;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public String getNmEmpresaAdjudicitaria() {
        return nmEmpresaAdjudicitaria;
    }

    public Contrato nmEmpresaAdjudicitaria(String nmEmpresaAdjudicitaria) {
        this.nmEmpresaAdjudicitaria = nmEmpresaAdjudicitaria;
        return this;
    }

    public void setNmEmpresaAdjudicitaria(String nmEmpresaAdjudicitaria) {
        this.nmEmpresaAdjudicitaria = nmEmpresaAdjudicitaria;
    }

    public BigDecimal getValorContrato() {
        return valorContrato;
    }

    public Contrato valorContrato(BigDecimal valorContrato) {
        this.valorContrato = valorContrato;
        return this;
    }

    public void setValorContrato(BigDecimal valorContrato) {
        this.valorContrato = valorContrato;
    }

    public LocalDate getDtAssinatura() {
        return dtAssinatura;
    }

    public Contrato dtAssinatura(LocalDate dtAssinatura) {
        this.dtAssinatura = dtAssinatura;
        return this;
    }

    public void setDtAssinatura(LocalDate dtAssinatura) {
        this.dtAssinatura = dtAssinatura;
    }

    public LocalDate getDtFinalizacaoProcessoHomologAprov() {
        return dtFinalizacaoProcessoHomologAprov;
    }

    public Contrato dtFinalizacaoProcessoHomologAprov(LocalDate dtFinalizacaoProcessoHomologAprov) {
        this.dtFinalizacaoProcessoHomologAprov = dtFinalizacaoProcessoHomologAprov;
        return this;
    }

    public void setDtFinalizacaoProcessoHomologAprov(LocalDate dtFinalizacaoProcessoHomologAprov) {
        this.dtFinalizacaoProcessoHomologAprov = dtFinalizacaoProcessoHomologAprov;
    }

    public String getTipoMoeda() {
        return tipoMoeda;
    }

    public Contrato tipoMoeda(String tipoMoeda) {
        this.tipoMoeda = tipoMoeda;
        return this;
    }

    public void setTipoMoeda(String tipoMoeda) {
        this.tipoMoeda = tipoMoeda;
    }

    public BigDecimal getValorAdiantamento() {
        return valorAdiantamento;
    }

    public Contrato valorAdiantamento(BigDecimal valorAdiantamento) {
        this.valorAdiantamento = valorAdiantamento;
        return this;
    }

    public void setValorAdiantamento(BigDecimal valorAdiantamento) {
        this.valorAdiantamento = valorAdiantamento;
    }

    public LocalDate getDtAdiantamento() {
        return dtAdiantamento;
    }

    public Contrato dtAdiantamento(LocalDate dtAdiantamento) {
        this.dtAdiantamento = dtAdiantamento;
        return this;
    }

    public void setDtAdiantamento(LocalDate dtAdiantamento) {
        this.dtAdiantamento = dtAdiantamento;
    }

    public LocalDate getDtInicio() {
        return dtInicio;
    }

    public Contrato dtInicio(LocalDate dtInicio) {
        this.dtInicio = dtInicio;
        return this;
    }

    public void setDtInicio(LocalDate dtInicio) {
        this.dtInicio = dtInicio;
    }

    public BigDecimal getPrazoExecucao() {
        return prazoExecucao;
    }

    public Contrato prazoExecucao(BigDecimal prazoExecucao) {
        this.prazoExecucao = prazoExecucao;
        return this;
    }

    public void setPrazoExecucao(BigDecimal prazoExecucao) {
        this.prazoExecucao = prazoExecucao;
    }

    public LocalDate getDtRecepcaoProvisoria() {
        return dtRecepcaoProvisoria;
    }

    public Contrato dtRecepcaoProvisoria(LocalDate dtRecepcaoProvisoria) {
        this.dtRecepcaoProvisoria = dtRecepcaoProvisoria;
        return this;
    }

    public void setDtRecepcaoProvisoria(LocalDate dtRecepcaoProvisoria) {
        this.dtRecepcaoProvisoria = dtRecepcaoProvisoria;
    }

    public LocalDate getDtRecepcaoDefinitiva() {
        return dtRecepcaoDefinitiva;
    }

    public Contrato dtRecepcaoDefinitiva(LocalDate dtRecepcaoDefinitiva) {
        this.dtRecepcaoDefinitiva = dtRecepcaoDefinitiva;
        return this;
    }

    public void setDtRecepcaoDefinitiva(LocalDate dtRecepcaoDefinitiva) {
        this.dtRecepcaoDefinitiva = dtRecepcaoDefinitiva;
    }

    public LocalDate getDtRecepcaoComicionamento() {
        return dtRecepcaoComicionamento;
    }

    public Contrato dtRecepcaoComicionamento(LocalDate dtRecepcaoComicionamento) {
        this.dtRecepcaoComicionamento = dtRecepcaoComicionamento;
        return this;
    }

    public void setDtRecepcaoComicionamento(LocalDate dtRecepcaoComicionamento) {
        this.dtRecepcaoComicionamento = dtRecepcaoComicionamento;
    }

    public String getNmResposavelAntProjeto() {
        return nmResposavelAntProjeto;
    }

    public Contrato nmResposavelAntProjeto(String nmResposavelAntProjeto) {
        this.nmResposavelAntProjeto = nmResposavelAntProjeto;
        return this;
    }

    public void setNmResposavelAntProjeto(String nmResposavelAntProjeto) {
        this.nmResposavelAntProjeto = nmResposavelAntProjeto;
    }

    public String getNmResposavelProjeto() {
        return nmResposavelProjeto;
    }

    public Contrato nmResposavelProjeto(String nmResposavelProjeto) {
        this.nmResposavelProjeto = nmResposavelProjeto;
        return this;
    }

    public void setNmResposavelProjeto(String nmResposavelProjeto) {
        this.nmResposavelProjeto = nmResposavelProjeto;
    }

    public ProgramasProjectos getProgramasProjectos() {
        return programasProjectos;
    }

    public Contrato programasProjectos(ProgramasProjectos programasProjectos) {
        this.programasProjectos = programasProjectos;
        return this;
    }

    public void setProgramasProjectos(ProgramasProjectos programasProjectos) {
        this.programasProjectos = programasProjectos;
    }

    public SistemaAgua getIdSistemaAgua() {
        return idSistemaAgua;
    }

    public Contrato idSistemaAgua(SistemaAgua sistemaAgua) {
        this.idSistemaAgua = sistemaAgua;
        return this;
    }

    public void setIdSistemaAgua(SistemaAgua sistemaAgua) {
        this.idSistemaAgua = sistemaAgua;
    }

    public Set<Empreitada> getEmpreitadas() {
        return empreitadas;
    }

    public Contrato empreitadas(Set<Empreitada> empreitadas) {
        this.empreitadas = empreitadas;
        return this;
    }

    public Contrato addEmpreitada(Empreitada empreitada) {
        this.empreitadas.add(empreitada);
        empreitada.setIdContrato(this);
        return this;
    }

    public Contrato removeEmpreitada(Empreitada empreitada) {
        this.empreitadas.remove(empreitada);
        empreitada.setIdContrato(null);
        return this;
    }

    public void setEmpreitadas(Set<Empreitada> empreitadas) {
        this.empreitadas = empreitadas;
    }

    public Set<Execucao> getExecucaos() {
        return execucaos;
    }

    public Contrato execucaos(Set<Execucao> execucaos) {
        this.execucaos = execucaos;
        return this;
    }

    public Contrato addExecucao(Execucao execucao) {
        this.execucaos.add(execucao);
        execucao.setIdContrato(this);
        return this;
    }

    public Contrato removeExecucao(Execucao execucao) {
        this.execucaos.remove(execucao);
        execucao.setIdContrato(null);
        return this;
    }

    public void setExecucaos(Set<Execucao> execucaos) {
        this.execucaos = execucaos;
    }

    public String getTipoConcurso() {
        return tipoConcurso;
    }

    public void setTipoConcurso(String tipoConcurso) {
        this.tipoConcurso = tipoConcurso;
    }

    public LocalDate getDtVistoTribunalContas() {
        return dtVistoTribunalContas;
    }

    public void setDtVistoTribunalContas(LocalDate dtVistoTribunalContas) {
        this.dtVistoTribunalContas = dtVistoTribunalContas;
    }

    public LocalDate getDtPagamentoEmolumentos() {
        return dtPagamentoEmolumentos;
    }

    public void setDtPagamentoEmolumentos(LocalDate dtPagamentoEmolumentos) {
        this.dtPagamentoEmolumentos = dtPagamentoEmolumentos;
    }

    public LocalDate getDtPrazoGarantiaAditamento() {
        return dtPrazoGarantiaAditamento;
    }

    public void setDtPrazoGarantiaAditamento(LocalDate dtPrazoGarantiaAditamento) {
        this.dtPrazoGarantiaAditamento = dtPrazoGarantiaAditamento;
    }

    public LocalDate getDtPrazosVinculativos() {
        return dtPrazosVinculativos;
    }

    public void setDtPrazosVinculativos(LocalDate dtPrazosVinculativos) {
        this.dtPrazosVinculativos = dtPrazosVinculativos;
    }

    public BigDecimal getPrazoGarantiaAdiantamento() {
        return prazoGarantiaAdiantamento;
    }

    public void setPrazoGarantiaAdiantamento(BigDecimal prazoGarantiaAdiantamento) {
        this.prazoGarantiaAdiantamento = prazoGarantiaAdiantamento;
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
        Contrato contrato = (Contrato) o;
        if (contrato.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contrato.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Contrato{" +
            "id=" + getId() +
            ", tipoEmpreitada='" + getTipoEmpreitada() + "'" +
            ", dtLancamento='" + getDtLancamento() + "'" +
            ", nmEmpresaAdjudicitaria='" + getNmEmpresaAdjudicitaria() + "'" +
            ", valorContrato=" + getValorContrato() +
            ", dtAssinatura='" + getDtAssinatura() + "'" +
            ", dtFinalizacaoProcessoHomologAprov='" + getDtFinalizacaoProcessoHomologAprov() + "'" +
            ", tipoMoeda='" + getTipoMoeda() + "'" +
            ", valorAdiantamento=" + getValorAdiantamento() +
            ", dtAdiantamento='" + getDtAdiantamento() + "'" +
            ", dtInicio='" + getDtInicio() + "'" +
            ", prazoExecucao=" + getPrazoExecucao() +
            ", dtRecepcaoProvisoria='" + getDtRecepcaoProvisoria() + "'" +
            ", dtRecepcaoDefinitiva='" + getDtRecepcaoDefinitiva() + "'" +
            ", dtRecepcaoComicionamento='" + getDtRecepcaoComicionamento() + "'" +
            ", nmResposavelAntProjeto='" + getNmResposavelAntProjeto() + "'" +
            ", nmResposavelProjeto='" + getNmResposavelProjeto() + "'" +
            "}";
    }
}
