package com.minea.sisas.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.BigDecimalFilter;

import io.github.jhipster.service.filter.LocalDateFilter;



/**
 * Criteria class for the Execucao entity. This class is used in ExecucaoResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /execucaos?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ExecucaoCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idExecucao;

    private StringFilter tipoEmpreitada;

    private LocalDateFilter dtLancamento;

    private LocalDateFilter dtPeridoReferencia;

    private LocalDateFilter dtFimReferencia;

    private BigDecimalFilter valorFacturadoPeriodo;

    private LocalDateFilter dtFactura;

    private StringFilter numFactura;

    private BigDecimalFilter txCambio;

    private StringFilter constrangimento;

    private BigDecimalFilter valorPagoPeriodo;

    private LongFilter idSituacaoId;

    private LongFilter idProgramasProjectosId;

    private LongFilter idSistemaAguaId;

    private LongFilter idContratoId;

    public ExecucaoCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdExecucao() {
        return idExecucao;
    }

    public void setIdExecucao(LongFilter idExecucao) {
        this.idExecucao = idExecucao;
    }

    public StringFilter getTipoEmpreitada() {
        return tipoEmpreitada;
    }

    public void setTipoEmpreitada(StringFilter tipoEmpreitada) {
        this.tipoEmpreitada = tipoEmpreitada;
    }

    public LocalDateFilter getDtLancamento() {
        return dtLancamento;
    }

    public void setDtLancamento(LocalDateFilter dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public LocalDateFilter getDtPeridoReferencia() {
        return dtPeridoReferencia;
    }

    public void setDtPeridoReferencia(LocalDateFilter dtPeridoReferencia) {
        this.dtPeridoReferencia = dtPeridoReferencia;
    }

    public LocalDateFilter getDtFimReferencia() {
        return dtFimReferencia;
    }

    public void setDtFimReferencia(LocalDateFilter dtFimReferencia) {
        this.dtFimReferencia = dtFimReferencia;
    }

    public BigDecimalFilter getValorFacturadoPeriodo() {
        return valorFacturadoPeriodo;
    }

    public void setValorFacturadoPeriodo(BigDecimalFilter valorFacturadoPeriodo) {
        this.valorFacturadoPeriodo = valorFacturadoPeriodo;
    }

    public LocalDateFilter getDtFactura() {
        return dtFactura;
    }

    public void setDtFactura(LocalDateFilter dtFactura) {
        this.dtFactura = dtFactura;
    }

    public StringFilter getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(StringFilter numFactura) {
        this.numFactura = numFactura;
    }

    public BigDecimalFilter getTxCambio() {
        return txCambio;
    }

    public void setTxCambio(BigDecimalFilter txCambio) {
        this.txCambio = txCambio;
    }

    public StringFilter getConstrangimento() {
        return constrangimento;
    }

    public void setConstrangimento(StringFilter constrangimento) {
        this.constrangimento = constrangimento;
    }

    public BigDecimalFilter getValorPagoPeriodo() {
        return valorPagoPeriodo;
    }

    public void setValorPagoPeriodo(BigDecimalFilter valorPagoPeriodo) {
        this.valorPagoPeriodo = valorPagoPeriodo;
    }

    public LongFilter getIdSituacaoId() {
        return idSituacaoId;
    }

    public void setIdSituacaoId(LongFilter idSituacaoId) {
        this.idSituacaoId = idSituacaoId;
    }

    public LongFilter getIdProgramasProjectosId() {
        return idProgramasProjectosId;
    }

    public void setIdProgramasProjectosId(LongFilter idProgramasProjectosId) {
        this.idProgramasProjectosId = idProgramasProjectosId;
    }

    public LongFilter getIdSistemaAguaId() {
        return idSistemaAguaId;
    }

    public void setIdSistemaAguaId(LongFilter idSistemaAguaId) {
        this.idSistemaAguaId = idSistemaAguaId;
    }

    public LongFilter getIdContratoId() {
        return idContratoId;
    }

    public void setIdContratoId(LongFilter idContratoId) {
        this.idContratoId = idContratoId;
    }

    @Override
    public String toString() {
        return "ExecucaoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idExecucao != null ? "idExecucao=" + idExecucao + ", " : "") +
                (tipoEmpreitada != null ? "tipoEmpreitada=" + tipoEmpreitada + ", " : "") +
                (dtLancamento != null ? "dtLancamento=" + dtLancamento + ", " : "") +
                (dtPeridoReferencia != null ? "dtPeridoReferencia=" + dtPeridoReferencia + ", " : "") +
                (dtFimReferencia != null ? "dtFimReferencia=" + dtFimReferencia + ", " : "") +
                (valorFacturadoPeriodo != null ? "valorFacturadoPeriodo=" + valorFacturadoPeriodo + ", " : "") +
                (dtFactura != null ? "dtFactura=" + dtFactura + ", " : "") +
                (numFactura != null ? "numFactura=" + numFactura + ", " : "") +
                (txCambio != null ? "txCambio=" + txCambio + ", " : "") +
                (constrangimento != null ? "constrangimento=" + constrangimento + ", " : "") +
                (valorPagoPeriodo != null ? "valorPagoPeriodo=" + valorPagoPeriodo + ", " : "") +
                (idSituacaoId != null ? "situacao=" + idSituacaoId + ", " : "") +
                (idProgramasProjectosId != null ? "idProgramasProjectosId=" + idProgramasProjectosId + ", " : "") +
                (idSistemaAguaId != null ? "idSistemaAguaId=" + idSistemaAguaId + ", " : "") +
                (idContratoId != null ? "idContratoId=" + idContratoId + ", " : "") +
            "}";
    }

}
