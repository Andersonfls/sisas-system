package com.minea.sisas.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Empreitada entity.
 */
public class EmpreitadaDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idEmpreitada;

    @NotNull
    @Size(max = 150)
    private String tipoEmpreitada;

    @NotNull
    private LocalDate dtLancamento;

    @NotNull
    private BigDecimal numCapacidadeCaptacao;

    @NotNull
    private BigDecimal numCapacidadeCaptacaoEta;

    @NotNull
    private BigDecimal numExtensaoCondAdutMat;

    @NotNull
    private BigDecimal numCaprmazenamento;

    @NotNull
    private BigDecimal numExtensaoRedeMat;

    @NotNull
    private BigDecimal numLigacoesDomiciliares;

    @NotNull
    private BigDecimal numLigacoesTorneiraQuintal;

    @NotNull
    private BigDecimal numChafarisNovos;

    @NotNull
    private BigDecimal numChafarisReabilitar;

    @NotNull
    private BigDecimal numCapacidadeTratamentoEta;

    @NotNull
    private BigDecimal numExtensaoRedeMaterial;

    @NotNull
    private BigDecimal numExtensaoCondutasElelMat;

    @NotNull
    private BigDecimal numLigacoes;

    @NotNull
    private BigDecimal numCaixasVisitas;

    @NotNull
    private BigDecimal numEstacoesElevatorias;

    @NotNull
    private BigDecimal numLatrinas;

    private Long idProgramasProjectosId;

    private Long idSistemaAguaId;

    private Long idContratoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEmpreitada() {
        return idEmpreitada;
    }

    public void setIdEmpreitada(Long idEmpreitada) {
        this.idEmpreitada = idEmpreitada;
    }

    public String getTipoEmpreitada() {
        return tipoEmpreitada;
    }

    public void setTipoEmpreitada(String tipoEmpreitada) {
        this.tipoEmpreitada = tipoEmpreitada;
    }

    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public BigDecimal getNumCapacidadeCaptacao() {
        return numCapacidadeCaptacao;
    }

    public void setNumCapacidadeCaptacao(BigDecimal numCapacidadeCaptacao) {
        this.numCapacidadeCaptacao = numCapacidadeCaptacao;
    }

    public BigDecimal getNumCapacidadeCaptacaoEta() {
        return numCapacidadeCaptacaoEta;
    }

    public void setNumCapacidadeCaptacaoEta(BigDecimal numCapacidadeCaptacaoEta) {
        this.numCapacidadeCaptacaoEta = numCapacidadeCaptacaoEta;
    }

    public BigDecimal getNumExtensaoCondAdutMat() {
        return numExtensaoCondAdutMat;
    }

    public void setNumExtensaoCondAdutMat(BigDecimal numExtensaoCondAdutMat) {
        this.numExtensaoCondAdutMat = numExtensaoCondAdutMat;
    }

    public BigDecimal getNumCaprmazenamento() {
        return numCaprmazenamento;
    }

    public void setNumCaprmazenamento(BigDecimal numCaprmazenamento) {
        this.numCaprmazenamento = numCaprmazenamento;
    }

    public BigDecimal getNumExtensaoRedeMat() {
        return numExtensaoRedeMat;
    }

    public void setNumExtensaoRedeMat(BigDecimal numExtensaoRedeMat) {
        this.numExtensaoRedeMat = numExtensaoRedeMat;
    }

    public BigDecimal getNumLigacoesDomiciliares() {
        return numLigacoesDomiciliares;
    }

    public void setNumLigacoesDomiciliares(BigDecimal numLigacoesDomiciliares) {
        this.numLigacoesDomiciliares = numLigacoesDomiciliares;
    }

    public BigDecimal getNumLigacoesTorneiraQuintal() {
        return numLigacoesTorneiraQuintal;
    }

    public void setNumLigacoesTorneiraQuintal(BigDecimal numLigacoesTorneiraQuintal) {
        this.numLigacoesTorneiraQuintal = numLigacoesTorneiraQuintal;
    }

    public BigDecimal getNumChafarisNovos() {
        return numChafarisNovos;
    }

    public void setNumChafarisNovos(BigDecimal numChafarisNovos) {
        this.numChafarisNovos = numChafarisNovos;
    }

    public BigDecimal getNumChafarisReabilitar() {
        return numChafarisReabilitar;
    }

    public void setNumChafarisReabilitar(BigDecimal numChafarisReabilitar) {
        this.numChafarisReabilitar = numChafarisReabilitar;
    }

    public BigDecimal getNumCapacidadeTratamentoEta() {
        return numCapacidadeTratamentoEta;
    }

    public void setNumCapacidadeTratamentoEta(BigDecimal numCapacidadeTratamentoEta) {
        this.numCapacidadeTratamentoEta = numCapacidadeTratamentoEta;
    }

    public BigDecimal getNumExtensaoRedeMaterial() {
        return numExtensaoRedeMaterial;
    }

    public void setNumExtensaoRedeMaterial(BigDecimal numExtensaoRedeMaterial) {
        this.numExtensaoRedeMaterial = numExtensaoRedeMaterial;
    }

    public BigDecimal getNumExtensaoCondutasElelMat() {
        return numExtensaoCondutasElelMat;
    }

    public void setNumExtensaoCondutasElelMat(BigDecimal numExtensaoCondutasElelMat) {
        this.numExtensaoCondutasElelMat = numExtensaoCondutasElelMat;
    }

    public BigDecimal getNumLigacoes() {
        return numLigacoes;
    }

    public void setNumLigacoes(BigDecimal numLigacoes) {
        this.numLigacoes = numLigacoes;
    }

    public BigDecimal getNumCaixasVisitas() {
        return numCaixasVisitas;
    }

    public void setNumCaixasVisitas(BigDecimal numCaixasVisitas) {
        this.numCaixasVisitas = numCaixasVisitas;
    }

    public BigDecimal getNumEstacoesElevatorias() {
        return numEstacoesElevatorias;
    }

    public void setNumEstacoesElevatorias(BigDecimal numEstacoesElevatorias) {
        this.numEstacoesElevatorias = numEstacoesElevatorias;
    }

    public BigDecimal getNumLatrinas() {
        return numLatrinas;
    }

    public void setNumLatrinas(BigDecimal numLatrinas) {
        this.numLatrinas = numLatrinas;
    }

    public Long getIdProgramasProjectosId() {
        return idProgramasProjectosId;
    }

    public void setIdProgramasProjectosId(Long programasProjectosId) {
        this.idProgramasProjectosId = programasProjectosId;
    }

    public Long getIdSistemaAguaId() {
        return idSistemaAguaId;
    }

    public void setIdSistemaAguaId(Long sistemaAguaId) {
        this.idSistemaAguaId = sistemaAguaId;
    }

    public Long getIdContratoId() {
        return idContratoId;
    }

    public void setIdContratoId(Long contratoId) {
        this.idContratoId = contratoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmpreitadaDTO empreitadaDTO = (EmpreitadaDTO) o;
        if(empreitadaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), empreitadaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmpreitadaDTO{" +
            "id=" + getId() +
            ", idEmpreitada=" + getIdEmpreitada() +
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
