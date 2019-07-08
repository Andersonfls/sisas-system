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
 * Criteria class for the Empreitada entity. This class is used in EmpreitadaResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /empreitadas?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EmpreitadaCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter idEmpreitada;

    private StringFilter tipoEmpreitada;

    private LocalDateFilter dtLancamento;

    private BigDecimalFilter numCapacidadeCaptacao;

    private BigDecimalFilter numCapacidadeCaptacaoEta;

    private BigDecimalFilter numExtensaoCondAdutMat;

    private BigDecimalFilter numCaprmazenamento;

    private BigDecimalFilter numExtensaoRedeMat;

    private BigDecimalFilter numLigacoesDomiciliares;

    private BigDecimalFilter numLigacoesTorneiraQuintal;

    private BigDecimalFilter numChafarisNovos;

    private BigDecimalFilter numChafarisReabilitar;

    private BigDecimalFilter numCapacidadeTratamentoEta;

    private BigDecimalFilter numExtensaoRedeMaterial;

    private BigDecimalFilter numExtensaoCondutasElelMat;

    private BigDecimalFilter numLigacoes;

    private BigDecimalFilter numCaixasVisitas;

    private BigDecimalFilter numEstacoesElevatorias;

    private BigDecimalFilter numLatrinas;

    private LongFilter idProgramasProjectosId;

    private LongFilter idSistemaAguaId;

    private LongFilter idContratoId;

    public EmpreitadaCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdEmpreitada() {
        return idEmpreitada;
    }

    public void setIdEmpreitada(LongFilter idEmpreitada) {
        this.idEmpreitada = idEmpreitada;
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

    public BigDecimalFilter getNumCapacidadeCaptacao() {
        return numCapacidadeCaptacao;
    }

    public void setNumCapacidadeCaptacao(BigDecimalFilter numCapacidadeCaptacao) {
        this.numCapacidadeCaptacao = numCapacidadeCaptacao;
    }

    public BigDecimalFilter getNumCapacidadeCaptacaoEta() {
        return numCapacidadeCaptacaoEta;
    }

    public void setNumCapacidadeCaptacaoEta(BigDecimalFilter numCapacidadeCaptacaoEta) {
        this.numCapacidadeCaptacaoEta = numCapacidadeCaptacaoEta;
    }

    public BigDecimalFilter getNumExtensaoCondAdutMat() {
        return numExtensaoCondAdutMat;
    }

    public void setNumExtensaoCondAdutMat(BigDecimalFilter numExtensaoCondAdutMat) {
        this.numExtensaoCondAdutMat = numExtensaoCondAdutMat;
    }

    public BigDecimalFilter getNumCaprmazenamento() {
        return numCaprmazenamento;
    }

    public void setNumCaprmazenamento(BigDecimalFilter numCaprmazenamento) {
        this.numCaprmazenamento = numCaprmazenamento;
    }

    public BigDecimalFilter getNumExtensaoRedeMat() {
        return numExtensaoRedeMat;
    }

    public void setNumExtensaoRedeMat(BigDecimalFilter numExtensaoRedeMat) {
        this.numExtensaoRedeMat = numExtensaoRedeMat;
    }

    public BigDecimalFilter getNumLigacoesDomiciliares() {
        return numLigacoesDomiciliares;
    }

    public void setNumLigacoesDomiciliares(BigDecimalFilter numLigacoesDomiciliares) {
        this.numLigacoesDomiciliares = numLigacoesDomiciliares;
    }

    public BigDecimalFilter getNumLigacoesTorneiraQuintal() {
        return numLigacoesTorneiraQuintal;
    }

    public void setNumLigacoesTorneiraQuintal(BigDecimalFilter numLigacoesTorneiraQuintal) {
        this.numLigacoesTorneiraQuintal = numLigacoesTorneiraQuintal;
    }

    public BigDecimalFilter getNumChafarisNovos() {
        return numChafarisNovos;
    }

    public void setNumChafarisNovos(BigDecimalFilter numChafarisNovos) {
        this.numChafarisNovos = numChafarisNovos;
    }

    public BigDecimalFilter getNumChafarisReabilitar() {
        return numChafarisReabilitar;
    }

    public void setNumChafarisReabilitar(BigDecimalFilter numChafarisReabilitar) {
        this.numChafarisReabilitar = numChafarisReabilitar;
    }

    public BigDecimalFilter getNumCapacidadeTratamentoEta() {
        return numCapacidadeTratamentoEta;
    }

    public void setNumCapacidadeTratamentoEta(BigDecimalFilter numCapacidadeTratamentoEta) {
        this.numCapacidadeTratamentoEta = numCapacidadeTratamentoEta;
    }

    public BigDecimalFilter getNumExtensaoRedeMaterial() {
        return numExtensaoRedeMaterial;
    }

    public void setNumExtensaoRedeMaterial(BigDecimalFilter numExtensaoRedeMaterial) {
        this.numExtensaoRedeMaterial = numExtensaoRedeMaterial;
    }

    public BigDecimalFilter getNumExtensaoCondutasElelMat() {
        return numExtensaoCondutasElelMat;
    }

    public void setNumExtensaoCondutasElelMat(BigDecimalFilter numExtensaoCondutasElelMat) {
        this.numExtensaoCondutasElelMat = numExtensaoCondutasElelMat;
    }

    public BigDecimalFilter getNumLigacoes() {
        return numLigacoes;
    }

    public void setNumLigacoes(BigDecimalFilter numLigacoes) {
        this.numLigacoes = numLigacoes;
    }

    public BigDecimalFilter getNumCaixasVisitas() {
        return numCaixasVisitas;
    }

    public void setNumCaixasVisitas(BigDecimalFilter numCaixasVisitas) {
        this.numCaixasVisitas = numCaixasVisitas;
    }

    public BigDecimalFilter getNumEstacoesElevatorias() {
        return numEstacoesElevatorias;
    }

    public void setNumEstacoesElevatorias(BigDecimalFilter numEstacoesElevatorias) {
        this.numEstacoesElevatorias = numEstacoesElevatorias;
    }

    public BigDecimalFilter getNumLatrinas() {
        return numLatrinas;
    }

    public void setNumLatrinas(BigDecimalFilter numLatrinas) {
        this.numLatrinas = numLatrinas;
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
        return "EmpreitadaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idEmpreitada != null ? "idEmpreitada=" + idEmpreitada + ", " : "") +
                (tipoEmpreitada != null ? "tipoEmpreitada=" + tipoEmpreitada + ", " : "") +
                (dtLancamento != null ? "dtLancamento=" + dtLancamento + ", " : "") +
                (numCapacidadeCaptacao != null ? "numCapacidadeCaptacao=" + numCapacidadeCaptacao + ", " : "") +
                (numCapacidadeCaptacaoEta != null ? "numCapacidadeCaptacaoEta=" + numCapacidadeCaptacaoEta + ", " : "") +
                (numExtensaoCondAdutMat != null ? "numExtensaoCondAdutMat=" + numExtensaoCondAdutMat + ", " : "") +
                (numCaprmazenamento != null ? "numCaprmazenamento=" + numCaprmazenamento + ", " : "") +
                (numExtensaoRedeMat != null ? "numExtensaoRedeMat=" + numExtensaoRedeMat + ", " : "") +
                (numLigacoesDomiciliares != null ? "numLigacoesDomiciliares=" + numLigacoesDomiciliares + ", " : "") +
                (numLigacoesTorneiraQuintal != null ? "numLigacoesTorneiraQuintal=" + numLigacoesTorneiraQuintal + ", " : "") +
                (numChafarisNovos != null ? "numChafarisNovos=" + numChafarisNovos + ", " : "") +
                (numChafarisReabilitar != null ? "numChafarisReabilitar=" + numChafarisReabilitar + ", " : "") +
                (numCapacidadeTratamentoEta != null ? "numCapacidadeTratamentoEta=" + numCapacidadeTratamentoEta + ", " : "") +
                (numExtensaoRedeMaterial != null ? "numExtensaoRedeMaterial=" + numExtensaoRedeMaterial + ", " : "") +
                (numExtensaoCondutasElelMat != null ? "numExtensaoCondutasElelMat=" + numExtensaoCondutasElelMat + ", " : "") +
                (numLigacoes != null ? "numLigacoes=" + numLigacoes + ", " : "") +
                (numCaixasVisitas != null ? "numCaixasVisitas=" + numCaixasVisitas + ", " : "") +
                (numEstacoesElevatorias != null ? "numEstacoesElevatorias=" + numEstacoesElevatorias + ", " : "") +
                (numLatrinas != null ? "numLatrinas=" + numLatrinas + ", " : "") +
                (idProgramasProjectosId != null ? "idProgramasProjectosId=" + idProgramasProjectosId + ", " : "") +
                (idSistemaAguaId != null ? "idSistemaAguaId=" + idSistemaAguaId + ", " : "") +
                (idContratoId != null ? "idContratoId=" + idContratoId + ", " : "") +
            "}";
    }

}
