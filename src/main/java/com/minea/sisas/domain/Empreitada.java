package com.minea.sisas.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @Author Anderson Soares - @git/Andersonfls
 **/
@Entity
@Table(name = "empreitada")
public class Empreitada implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empreitada", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 150)
    @Column(name = "tipo_empreitada", length = 150, nullable = false)
    private String tipoEmpreitada;

    @NotNull
    @Column(name = "dt_lancamento", nullable = false)
    private LocalDate dtLancamento;

    @NotNull
    @Column(name = "num_capacidade_captacao", precision=10, scale=2, nullable = false)
    private BigDecimal numCapacidadeCaptacao;

    @NotNull
    @Column(name = "num_capacidade_captacao_eta", precision=10, scale=2, nullable = false)
    private BigDecimal numCapacidadeCaptacaoEta;

    @NotNull
    @Column(name = "num_extensao_cond_adut_mat", precision=10, scale=2, nullable = false)
    private BigDecimal numExtensaoCondAdutMat;

    @NotNull
    @Column(name = "num_caprmazenamento", precision=10, scale=2, nullable = false)
    private BigDecimal numCaprmazenamento;

    @NotNull
    @Column(name = "num_extensao_rede_mat", precision=10, scale=2, nullable = false)
    private BigDecimal numExtensaoRedeMat;

    @NotNull
    @Column(name = "num_ligacoes_domiciliares", precision=10, scale=2, nullable = false)
    private BigDecimal numLigacoesDomiciliares;

    @NotNull
    @Column(name = "num_ligacoes_torneira_quintal", precision=10, scale=2, nullable = false)
    private BigDecimal numLigacoesTorneiraQuintal;

    @NotNull
    @Column(name = "num_chafaris_novos", precision=10, scale=2, nullable = false)
    private BigDecimal numChafarisNovos;

    @NotNull
    @Column(name = "num_chafaris_reabilitar", precision=10, scale=2, nullable = false)
    private BigDecimal numChafarisReabilitar;

    @NotNull
    @Column(name = "num_capacidade_tratamento_eta", precision=10, scale=2, nullable = false)
    private BigDecimal numCapacidadeTratamentoEta;

    @NotNull
    @Column(name = "num_extensao_rede_material", precision=10, scale=2, nullable = false)
    private BigDecimal numExtensaoRedeMaterial;

    @NotNull
    @Column(name = "num_extensao_condutas_elel_mat", precision=10, scale=2, nullable = false)
    private BigDecimal numExtensaoCondutasElelMat;

    @NotNull
    @Column(name = "num_ligacoes", precision=10, scale=2, nullable = false)
    private BigDecimal numLigacoes;

    @NotNull
    @Column(name = "num_caixas_visitas", precision=10, scale=2, nullable = false)
    private BigDecimal numCaixasVisitas;

    @NotNull
    @Column(name = "num_estacoes_elevatorias", precision=10, scale=2, nullable = false)
    private BigDecimal numEstacoesElevatorias;

    @NotNull
    @Column(name = "num_latrinas", precision=10, scale=2, nullable = false)
    private BigDecimal numLatrinas;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "id_programas_projectos")
    private ProgramasProjectos idProgramasProjectos;

    @ManyToOne
    @JoinColumn(name = "id_sistema_agua")
    private SistemaAgua idSistemaAgua;

    @ManyToOne
    @JoinColumn(name = "id_contrato")
    private Contrato idContrato;

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

    public Empreitada tipoEmpreitada(String tipoEmpreitada) {
        this.tipoEmpreitada = tipoEmpreitada;
        return this;
    }

    public void setTipoEmpreitada(String tipoEmpreitada) {
        this.tipoEmpreitada = tipoEmpreitada;
    }

    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public Empreitada dtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
        return this;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public BigDecimal getNumCapacidadeCaptacao() {
        return numCapacidadeCaptacao;
    }

    public Empreitada numCapacidadeCaptacao(BigDecimal numCapacidadeCaptacao) {
        this.numCapacidadeCaptacao = numCapacidadeCaptacao;
        return this;
    }

    public void setNumCapacidadeCaptacao(BigDecimal numCapacidadeCaptacao) {
        this.numCapacidadeCaptacao = numCapacidadeCaptacao;
    }

    public BigDecimal getNumCapacidadeCaptacaoEta() {
        return numCapacidadeCaptacaoEta;
    }

    public Empreitada numCapacidadeCaptacaoEta(BigDecimal numCapacidadeCaptacaoEta) {
        this.numCapacidadeCaptacaoEta = numCapacidadeCaptacaoEta;
        return this;
    }

    public void setNumCapacidadeCaptacaoEta(BigDecimal numCapacidadeCaptacaoEta) {
        this.numCapacidadeCaptacaoEta = numCapacidadeCaptacaoEta;
    }

    public BigDecimal getNumExtensaoCondAdutMat() {
        return numExtensaoCondAdutMat;
    }

    public Empreitada numExtensaoCondAdutMat(BigDecimal numExtensaoCondAdutMat) {
        this.numExtensaoCondAdutMat = numExtensaoCondAdutMat;
        return this;
    }

    public void setNumExtensaoCondAdutMat(BigDecimal numExtensaoCondAdutMat) {
        this.numExtensaoCondAdutMat = numExtensaoCondAdutMat;
    }

    public BigDecimal getNumCaprmazenamento() {
        return numCaprmazenamento;
    }

    public Empreitada numCaprmazenamento(BigDecimal numCaprmazenamento) {
        this.numCaprmazenamento = numCaprmazenamento;
        return this;
    }

    public void setNumCaprmazenamento(BigDecimal numCaprmazenamento) {
        this.numCaprmazenamento = numCaprmazenamento;
    }

    public BigDecimal getNumExtensaoRedeMat() {
        return numExtensaoRedeMat;
    }

    public Empreitada numExtensaoRedeMat(BigDecimal numExtensaoRedeMat) {
        this.numExtensaoRedeMat = numExtensaoRedeMat;
        return this;
    }

    public void setNumExtensaoRedeMat(BigDecimal numExtensaoRedeMat) {
        this.numExtensaoRedeMat = numExtensaoRedeMat;
    }

    public BigDecimal getNumLigacoesDomiciliares() {
        return numLigacoesDomiciliares;
    }

    public Empreitada numLigacoesDomiciliares(BigDecimal numLigacoesDomiciliares) {
        this.numLigacoesDomiciliares = numLigacoesDomiciliares;
        return this;
    }

    public void setNumLigacoesDomiciliares(BigDecimal numLigacoesDomiciliares) {
        this.numLigacoesDomiciliares = numLigacoesDomiciliares;
    }

    public BigDecimal getNumLigacoesTorneiraQuintal() {
        return numLigacoesTorneiraQuintal;
    }

    public Empreitada numLigacoesTorneiraQuintal(BigDecimal numLigacoesTorneiraQuintal) {
        this.numLigacoesTorneiraQuintal = numLigacoesTorneiraQuintal;
        return this;
    }

    public void setNumLigacoesTorneiraQuintal(BigDecimal numLigacoesTorneiraQuintal) {
        this.numLigacoesTorneiraQuintal = numLigacoesTorneiraQuintal;
    }

    public BigDecimal getNumChafarisNovos() {
        return numChafarisNovos;
    }

    public Empreitada numChafarisNovos(BigDecimal numChafarisNovos) {
        this.numChafarisNovos = numChafarisNovos;
        return this;
    }

    public void setNumChafarisNovos(BigDecimal numChafarisNovos) {
        this.numChafarisNovos = numChafarisNovos;
    }

    public BigDecimal getNumChafarisReabilitar() {
        return numChafarisReabilitar;
    }

    public Empreitada numChafarisReabilitar(BigDecimal numChafarisReabilitar) {
        this.numChafarisReabilitar = numChafarisReabilitar;
        return this;
    }

    public void setNumChafarisReabilitar(BigDecimal numChafarisReabilitar) {
        this.numChafarisReabilitar = numChafarisReabilitar;
    }

    public BigDecimal getNumCapacidadeTratamentoEta() {
        return numCapacidadeTratamentoEta;
    }

    public Empreitada numCapacidadeTratamentoEta(BigDecimal numCapacidadeTratamentoEta) {
        this.numCapacidadeTratamentoEta = numCapacidadeTratamentoEta;
        return this;
    }

    public void setNumCapacidadeTratamentoEta(BigDecimal numCapacidadeTratamentoEta) {
        this.numCapacidadeTratamentoEta = numCapacidadeTratamentoEta;
    }

    public BigDecimal getNumExtensaoRedeMaterial() {
        return numExtensaoRedeMaterial;
    }

    public Empreitada numExtensaoRedeMaterial(BigDecimal numExtensaoRedeMaterial) {
        this.numExtensaoRedeMaterial = numExtensaoRedeMaterial;
        return this;
    }

    public void setNumExtensaoRedeMaterial(BigDecimal numExtensaoRedeMaterial) {
        this.numExtensaoRedeMaterial = numExtensaoRedeMaterial;
    }

    public BigDecimal getNumExtensaoCondutasElelMat() {
        return numExtensaoCondutasElelMat;
    }

    public Empreitada numExtensaoCondutasElelMat(BigDecimal numExtensaoCondutasElelMat) {
        this.numExtensaoCondutasElelMat = numExtensaoCondutasElelMat;
        return this;
    }

    public void setNumExtensaoCondutasElelMat(BigDecimal numExtensaoCondutasElelMat) {
        this.numExtensaoCondutasElelMat = numExtensaoCondutasElelMat;
    }

    public BigDecimal getNumLigacoes() {
        return numLigacoes;
    }

    public Empreitada numLigacoes(BigDecimal numLigacoes) {
        this.numLigacoes = numLigacoes;
        return this;
    }

    public void setNumLigacoes(BigDecimal numLigacoes) {
        this.numLigacoes = numLigacoes;
    }

    public BigDecimal getNumCaixasVisitas() {
        return numCaixasVisitas;
    }

    public Empreitada numCaixasVisitas(BigDecimal numCaixasVisitas) {
        this.numCaixasVisitas = numCaixasVisitas;
        return this;
    }

    public void setNumCaixasVisitas(BigDecimal numCaixasVisitas) {
        this.numCaixasVisitas = numCaixasVisitas;
    }

    public BigDecimal getNumEstacoesElevatorias() {
        return numEstacoesElevatorias;
    }

    public Empreitada numEstacoesElevatorias(BigDecimal numEstacoesElevatorias) {
        this.numEstacoesElevatorias = numEstacoesElevatorias;
        return this;
    }

    public void setNumEstacoesElevatorias(BigDecimal numEstacoesElevatorias) {
        this.numEstacoesElevatorias = numEstacoesElevatorias;
    }

    public BigDecimal getNumLatrinas() {
        return numLatrinas;
    }

    public Empreitada numLatrinas(BigDecimal numLatrinas) {
        this.numLatrinas = numLatrinas;
        return this;
    }

    public void setNumLatrinas(BigDecimal numLatrinas) {
        this.numLatrinas = numLatrinas;
    }

    public ProgramasProjectos getIdProgramasProjectos() {
        return idProgramasProjectos;
    }

    public Empreitada idProgramasProjectos(ProgramasProjectos programasProjectos) {
        this.idProgramasProjectos = programasProjectos;
        return this;
    }

    public void setIdProgramasProjectos(ProgramasProjectos programasProjectos) {
        this.idProgramasProjectos = programasProjectos;
    }

    public SistemaAgua getIdSistemaAgua() {
        return idSistemaAgua;
    }

    public Empreitada idSistemaAgua(SistemaAgua sistemaAgua) {
        this.idSistemaAgua = sistemaAgua;
        return this;
    }

    public void setIdSistemaAgua(SistemaAgua sistemaAgua) {
        this.idSistemaAgua = sistemaAgua;
    }

    public Contrato getIdContrato() {
        return idContrato;
    }

    public Empreitada idContrato(Contrato contrato) {
        this.idContrato = contrato;
        return this;
    }

    public void setIdContrato(Contrato contrato) {
        this.idContrato = contrato;
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
        Empreitada empreitada = (Empreitada) o;
        if (empreitada.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), empreitada.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Empreitada{" +
            "id=" + getId() +
            ", tipoEmpreitada='" + getTipoEmpreitada() + "'" +
            ", dtLancamento='" + getDtLancamento() + "'" +
            ", numCapacidadeCaptacao=" + getNumCapacidadeCaptacao() +
            ", numCapacidadeCaptacaoEta=" + getNumCapacidadeCaptacaoEta() +
            ", numExtensaoCondAdutMat=" + getNumExtensaoCondAdutMat() +
            ", numCaprmazenamento=" + getNumCaprmazenamento() +
            ", numExtensaoRedeMat=" + getNumExtensaoRedeMat() +
            ", numLigacoesDomiciliares=" + getNumLigacoesDomiciliares() +
            ", numLigacoesTorneiraQuintal=" + getNumLigacoesTorneiraQuintal() +
            ", numChafarisNovos=" + getNumChafarisNovos() +
            ", numChafarisReabilitar=" + getNumChafarisReabilitar() +
            ", numCapacidadeTratamentoEta=" + getNumCapacidadeTratamentoEta() +
            ", numExtensaoRedeMaterial=" + getNumExtensaoRedeMaterial() +
            ", numExtensaoCondutasElelMat=" + getNumExtensaoCondutasElelMat() +
            ", numLigacoes=" + getNumLigacoes() +
            ", numCaixasVisitas=" + getNumCaixasVisitas() +
            ", numEstacoesElevatorias=" + getNumEstacoesElevatorias() +
            ", numLatrinas=" + getNumLatrinas() +
            "}";
    }
}
