package com.minea.sisas.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Contrato entity.
 */
public class ContratoDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idContrato;

    @NotNull
    @Size(max = 150)
    private String tipoEmpreitada;

    @NotNull
    private LocalDate dtLancamento;

    @Size(max = 200)
    private String nmEmpresaAdjudicitaria;

    @NotNull
    private BigDecimal valorContrato;

    private LocalDate dtAssinatura;

    private LocalDate dtFinalizacaoProcessoHomologAprov;

    @NotNull
    @Size(max = 150)
    private String tipoMoeda;

    @NotNull
    private BigDecimal valorAdiantamento;

    private LocalDate dtAdiantamento;

    private LocalDate dtInicio;

    private BigDecimal prazoExecucao;

    private LocalDate dtRecepcaoProvisoria;

    private LocalDate dtRecepcaoDefinitiva;

    private LocalDate dtRecepcaoComicionamento;

    @Size(max = 200)
    private String nmResposavelAntProjeto;

    @Size(max = 200)
    private String nmResposavelProjeto;

    private Long idProgramasProjectosId;

    private Long idSistemaAguaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Long idContrato) {
        this.idContrato = idContrato;
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

    public String getNmEmpresaAdjudicitaria() {
        return nmEmpresaAdjudicitaria;
    }

    public void setNmEmpresaAdjudicitaria(String nmEmpresaAdjudicitaria) {
        this.nmEmpresaAdjudicitaria = nmEmpresaAdjudicitaria;
    }

    public BigDecimal getValorContrato() {
        return valorContrato;
    }

    public void setValorContrato(BigDecimal valorContrato) {
        this.valorContrato = valorContrato;
    }

    public LocalDate getDtAssinatura() {
        return dtAssinatura;
    }

    public void setDtAssinatura(LocalDate dtAssinatura) {
        this.dtAssinatura = dtAssinatura;
    }

    public LocalDate getDtFinalizacaoProcessoHomologAprov() {
        return dtFinalizacaoProcessoHomologAprov;
    }

    public void setDtFinalizacaoProcessoHomologAprov(LocalDate dtFinalizacaoProcessoHomologAprov) {
        this.dtFinalizacaoProcessoHomologAprov = dtFinalizacaoProcessoHomologAprov;
    }

    public String getTipoMoeda() {
        return tipoMoeda;
    }

    public void setTipoMoeda(String tipoMoeda) {
        this.tipoMoeda = tipoMoeda;
    }

    public BigDecimal getValorAdiantamento() {
        return valorAdiantamento;
    }

    public void setValorAdiantamento(BigDecimal valorAdiantamento) {
        this.valorAdiantamento = valorAdiantamento;
    }

    public LocalDate getDtAdiantamento() {
        return dtAdiantamento;
    }

    public void setDtAdiantamento(LocalDate dtAdiantamento) {
        this.dtAdiantamento = dtAdiantamento;
    }

    public LocalDate getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(LocalDate dtInicio) {
        this.dtInicio = dtInicio;
    }

    public BigDecimal getPrazoExecucao() {
        return prazoExecucao;
    }

    public void setPrazoExecucao(BigDecimal prazoExecucao) {
        this.prazoExecucao = prazoExecucao;
    }

    public LocalDate getDtRecepcaoProvisoria() {
        return dtRecepcaoProvisoria;
    }

    public void setDtRecepcaoProvisoria(LocalDate dtRecepcaoProvisoria) {
        this.dtRecepcaoProvisoria = dtRecepcaoProvisoria;
    }

    public LocalDate getDtRecepcaoDefinitiva() {
        return dtRecepcaoDefinitiva;
    }

    public void setDtRecepcaoDefinitiva(LocalDate dtRecepcaoDefinitiva) {
        this.dtRecepcaoDefinitiva = dtRecepcaoDefinitiva;
    }

    public LocalDate getDtRecepcaoComicionamento() {
        return dtRecepcaoComicionamento;
    }

    public void setDtRecepcaoComicionamento(LocalDate dtRecepcaoComicionamento) {
        this.dtRecepcaoComicionamento = dtRecepcaoComicionamento;
    }

    public String getNmResposavelAntProjeto() {
        return nmResposavelAntProjeto;
    }

    public void setNmResposavelAntProjeto(String nmResposavelAntProjeto) {
        this.nmResposavelAntProjeto = nmResposavelAntProjeto;
    }

    public String getNmResposavelProjeto() {
        return nmResposavelProjeto;
    }

    public void setNmResposavelProjeto(String nmResposavelProjeto) {
        this.nmResposavelProjeto = nmResposavelProjeto;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContratoDTO contratoDTO = (ContratoDTO) o;
        if(contratoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contratoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContratoDTO{" +
            "id=" + getId() +
            ", idContrato=" + getIdContrato() +
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
