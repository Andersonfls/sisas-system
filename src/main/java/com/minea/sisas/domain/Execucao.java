package com.minea.sisas.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Execucao.
 */
@Entity
@Table(name = "execucao")
public class Execucao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_execucao", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 150)
    @Column(name = "tipo_empreitada", length = 150, nullable = false)
    private String tipoEmpreitada;

    @NotNull
    @Column(name = "dt_lancamento", nullable = false)
    private LocalDate dtLancamento;

    @NotNull
    @Column(name = "dt_perido_referencia", nullable = false)
    private LocalDate dtPeridoReferencia;

    @NotNull
    @Column(name = "dt_fim_referencia", nullable = false)
    private LocalDate dtFimReferencia;

    @NotNull
    @Column(name = "valor_facturado_periodo", precision=10, scale=2, nullable = false)
    private BigDecimal valorFacturadoPeriodo;

    @NotNull
    @Column(name = "dt_factura", nullable = false)
    private LocalDate dtFactura;

    @NotNull
    @Size(max = 50)
    @Column(name = "num_factura", length = 50, nullable = false)
    private String numFactura;

    @Column(name = "tx_cambio", precision=10, scale=2)
    private BigDecimal txCambio;

    @Size(max = 200)
    @Column(name = "constrangimento", length = 200)
    private String constrangimento;

    @NotNull
    @Column(name = "valor_pago_periodo", precision=10, scale=2, nullable = false)
    private BigDecimal valorPagoPeriodo;

    @ManyToOne(optional = false)
    @NotNull
    private Situacao idSituacao;

    @ManyToOne(optional = false)
    @NotNull
    private ProgramasProjectos idProgramasProjectos;

    @ManyToOne
    private SistemaAgua idSistemaAgua;

    @ManyToOne
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

    public Execucao tipoEmpreitada(String tipoEmpreitada) {
        this.tipoEmpreitada = tipoEmpreitada;
        return this;
    }

    public void setTipoEmpreitada(String tipoEmpreitada) {
        this.tipoEmpreitada = tipoEmpreitada;
    }

    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public Execucao dtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
        return this;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public LocalDate getDtPeridoReferencia() {
        return dtPeridoReferencia;
    }

    public Execucao dtPeridoReferencia(LocalDate dtPeridoReferencia) {
        this.dtPeridoReferencia = dtPeridoReferencia;
        return this;
    }

    public void setDtPeridoReferencia(LocalDate dtPeridoReferencia) {
        this.dtPeridoReferencia = dtPeridoReferencia;
    }

    public LocalDate getDtFimReferencia() {
        return dtFimReferencia;
    }

    public Execucao dtFimReferencia(LocalDate dtFimReferencia) {
        this.dtFimReferencia = dtFimReferencia;
        return this;
    }

    public void setDtFimReferencia(LocalDate dtFimReferencia) {
        this.dtFimReferencia = dtFimReferencia;
    }

    public BigDecimal getValorFacturadoPeriodo() {
        return valorFacturadoPeriodo;
    }

    public Execucao valorFacturadoPeriodo(BigDecimal valorFacturadoPeriodo) {
        this.valorFacturadoPeriodo = valorFacturadoPeriodo;
        return this;
    }

    public void setValorFacturadoPeriodo(BigDecimal valorFacturadoPeriodo) {
        this.valorFacturadoPeriodo = valorFacturadoPeriodo;
    }

    public LocalDate getDtFactura() {
        return dtFactura;
    }

    public Execucao dtFactura(LocalDate dtFactura) {
        this.dtFactura = dtFactura;
        return this;
    }

    public void setDtFactura(LocalDate dtFactura) {
        this.dtFactura = dtFactura;
    }

    public String getNumFactura() {
        return numFactura;
    }

    public Execucao numFactura(String numFactura) {
        this.numFactura = numFactura;
        return this;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    public BigDecimal getTxCambio() {
        return txCambio;
    }

    public Execucao txCambio(BigDecimal txCambio) {
        this.txCambio = txCambio;
        return this;
    }

    public void setTxCambio(BigDecimal txCambio) {
        this.txCambio = txCambio;
    }

    public String getConstrangimento() {
        return constrangimento;
    }

    public Execucao constrangimento(String constrangimento) {
        this.constrangimento = constrangimento;
        return this;
    }

    public void setConstrangimento(String constrangimento) {
        this.constrangimento = constrangimento;
    }

    public BigDecimal getValorPagoPeriodo() {
        return valorPagoPeriodo;
    }

    public Execucao valorPagoPeriodo(BigDecimal valorPagoPeriodo) {
        this.valorPagoPeriodo = valorPagoPeriodo;
        return this;
    }

    public void setValorPagoPeriodo(BigDecimal valorPagoPeriodo) {
        this.valorPagoPeriodo = valorPagoPeriodo;
    }

    public Situacao getIdSituacao() {
        return idSituacao;
    }

    public Execucao idSituacao(Situacao situacao) {
        this.idSituacao = situacao;
        return this;
    }

    public void setIdSituacao(Situacao situacao) {
        this.idSituacao = situacao;
    }

    public ProgramasProjectos getIdProgramasProjectos() {
        return idProgramasProjectos;
    }

    public Execucao idProgramasProjectos(ProgramasProjectos programasProjectos) {
        this.idProgramasProjectos = programasProjectos;
        return this;
    }

    public void setIdProgramasProjectos(ProgramasProjectos programasProjectos) {
        this.idProgramasProjectos = programasProjectos;
    }

    public SistemaAgua getIdSistemaAgua() {
        return idSistemaAgua;
    }

    public Execucao idSistemaAgua(SistemaAgua sistemaAgua) {
        this.idSistemaAgua = sistemaAgua;
        return this;
    }

    public void setIdSistemaAgua(SistemaAgua sistemaAgua) {
        this.idSistemaAgua = sistemaAgua;
    }

    public Contrato getIdContrato() {
        return idContrato;
    }

    public Execucao idContrato(Contrato contrato) {
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
        Execucao execucao = (Execucao) o;
        if (execucao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), execucao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Execucao{" +
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
