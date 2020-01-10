package com.minea.sisas.service.dto;


import com.minea.sisas.domain.ProgramasProjectos;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Execucao entity.
 */
public class ExecucaoDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 150)
    private String tipoEmpreitada;

    @NotNull
    private LocalDate dtLancamento;

    @NotNull
    private LocalDate dtPeridoReferencia;

    @NotNull
    private LocalDate dtFimReferencia;

    @NotNull
    private BigDecimal valorFacturadoPeriodo;

    @NotNull
    private LocalDate dtFactura;

    @NotNull
    @Size(max = 50)
    private String numFactura;

    private BigDecimal txCambio;

    @Size(max = 200)
    private String constrangimento;

    @NotNull
    private BigDecimal valorPagoPeriodo;

    private Long idSituacaoId;

    private ProgramasProjectos idProgramasProjectosId;

    private Long idSistemaAguaId;

    private Long idContratoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getDtPeridoReferencia() {
        return dtPeridoReferencia;
    }

    public void setDtPeridoReferencia(LocalDate dtPeridoReferencia) {
        this.dtPeridoReferencia = dtPeridoReferencia;
    }

    public LocalDate getDtFimReferencia() {
        return dtFimReferencia;
    }

    public void setDtFimReferencia(LocalDate dtFimReferencia) {
        this.dtFimReferencia = dtFimReferencia;
    }

    public BigDecimal getValorFacturadoPeriodo() {
        return valorFacturadoPeriodo;
    }

    public void setValorFacturadoPeriodo(BigDecimal valorFacturadoPeriodo) {
        this.valorFacturadoPeriodo = valorFacturadoPeriodo;
    }

    public LocalDate getDtFactura() {
        return dtFactura;
    }

    public void setDtFactura(LocalDate dtFactura) {
        this.dtFactura = dtFactura;
    }

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    public BigDecimal getTxCambio() {
        return txCambio;
    }

    public void setTxCambio(BigDecimal txCambio) {
        this.txCambio = txCambio;
    }

    public String getConstrangimento() {
        return constrangimento;
    }

    public void setConstrangimento(String constrangimento) {
        this.constrangimento = constrangimento;
    }

    public BigDecimal getValorPagoPeriodo() {
        return valorPagoPeriodo;
    }

    public void setValorPagoPeriodo(BigDecimal valorPagoPeriodo) {
        this.valorPagoPeriodo = valorPagoPeriodo;
    }

    public Long getIdSituacaoId() {
        return idSituacaoId;
    }

    public void setIdSituacaoId(Long situacaoId) {
        this.idSituacaoId = situacaoId;
    }

    public ProgramasProjectos getIdProgramasProjectosId() {
        return idProgramasProjectosId;
    }

    public void setIdProgramasProjectosId(ProgramasProjectos programasProjectosId) {
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

        ExecucaoDTO execucaoDTO = (ExecucaoDTO) o;
        if(execucaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), execucaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ExecucaoDTO{" +
            "id=" + getId() +
            ", tipoEmpreitada='" + getTipoEmpreitada() + "'" +
            ", dtLancamento='" + getDtLancamento() + "'" +
            ", dtPeridoReferencia='" + getDtPeridoReferencia() + "'" +
            ", dtFimReferencia='" + getDtFimReferencia() + "'" +
            ", valorFacturadoPeriodo=" + getValorFacturadoPeriodo() +
            ", dtFactura='" + getDtFactura() + "'" +
            ", numFactura='" + getNumFactura() + "'" +
            ", txCambio=" + getTxCambio() +
            ", constrangimento='" + getConstrangimento() + "'" +
            ", valorPagoPeriodo=" + getValorPagoPeriodo() +
            "}";
    }
}
