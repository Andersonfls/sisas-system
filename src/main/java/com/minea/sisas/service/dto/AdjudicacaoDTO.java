package com.minea.sisas.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Adjudicacao entity.
 */
public class AdjudicacaoDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idAdjudicacao;

    @NotNull
    @Size(max = 150)
    private String tipoConcurso;

    @NotNull
    private LocalDate dtLancamento;

    private LocalDate dtComunicaoAdjudicacao;

    private LocalDate dtPrestacaoGarantBoaExec;

    private LocalDate dtSubmissaoMinutContrato;

    private Long idProgramasProjectosId;

    private Long idSistemaAguaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAdjudicacao() {
        return idAdjudicacao;
    }

    public void setIdAdjudicacao(Long idAdjudicacao) {
        this.idAdjudicacao = idAdjudicacao;
    }

    public String getTipoConcurso() {
        return tipoConcurso;
    }

    public void setTipoConcurso(String tipoConcurso) {
        this.tipoConcurso = tipoConcurso;
    }

    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public LocalDate getDtComunicaoAdjudicacao() {
        return dtComunicaoAdjudicacao;
    }

    public void setDtComunicaoAdjudicacao(LocalDate dtComunicaoAdjudicacao) {
        this.dtComunicaoAdjudicacao = dtComunicaoAdjudicacao;
    }

    public LocalDate getDtPrestacaoGarantBoaExec() {
        return dtPrestacaoGarantBoaExec;
    }

    public void setDtPrestacaoGarantBoaExec(LocalDate dtPrestacaoGarantBoaExec) {
        this.dtPrestacaoGarantBoaExec = dtPrestacaoGarantBoaExec;
    }

    public LocalDate getDtSubmissaoMinutContrato() {
        return dtSubmissaoMinutContrato;
    }

    public void setDtSubmissaoMinutContrato(LocalDate dtSubmissaoMinutContrato) {
        this.dtSubmissaoMinutContrato = dtSubmissaoMinutContrato;
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

        AdjudicacaoDTO adjudicacaoDTO = (AdjudicacaoDTO) o;
        if(adjudicacaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adjudicacaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdjudicacaoDTO{" +
            "id=" + getId() +
            ", idAdjudicacao=" + getIdAdjudicacao() +
            ", tipoConcurso='" + getTipoConcurso() + "'" +
            ", dtLancamento='" + getDtLancamento() + "'" +
            ", dtComunicaoAdjudicacao='" + getDtComunicaoAdjudicacao() + "'" +
            ", dtPrestacaoGarantBoaExec='" + getDtPrestacaoGarantBoaExec() + "'" +
            ", dtSubmissaoMinutContrato='" + getDtSubmissaoMinutContrato() + "'" +
            "}";
    }
}
