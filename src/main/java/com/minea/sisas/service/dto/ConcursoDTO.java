package com.minea.sisas.service.dto;


import com.minea.sisas.domain.ProgramasProjectos;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Concurso entity.
 */
public class ConcursoDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 150)
    private String tipoConcurso;

    @NotNull
    private LocalDate dtLancamento;

    private LocalDate dtUltimaAlteracao;

    private LocalDate dtEntregaProposta;

    private LocalDate dtAberturaProposta;

    private LocalDate dtConclusaoAvaliacaoRelPrel;

    private LocalDate dtNegociacao;

    private LocalDate dtAprovRelAvalFinal;

    private ProgramasProjectos programasProjectos;

    private Long idSistemaAguaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }

    public void setDtUltimaAlteracao(LocalDate dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }

    public LocalDate getDtEntregaProposta() {
        return dtEntregaProposta;
    }

    public void setDtEntregaProposta(LocalDate dtEntregaProposta) {
        this.dtEntregaProposta = dtEntregaProposta;
    }

    public LocalDate getDtAberturaProposta() {
        return dtAberturaProposta;
    }

    public void setDtAberturaProposta(LocalDate dtAberturaProposta) {
        this.dtAberturaProposta = dtAberturaProposta;
    }

    public LocalDate getDtConclusaoAvaliacaoRelPrel() {
        return dtConclusaoAvaliacaoRelPrel;
    }

    public void setDtConclusaoAvaliacaoRelPrel(LocalDate dtConclusaoAvaliacaoRelPrel) {
        this.dtConclusaoAvaliacaoRelPrel = dtConclusaoAvaliacaoRelPrel;
    }

    public LocalDate getDtNegociacao() {
        return dtNegociacao;
    }

    public void setDtNegociacao(LocalDate dtNegociacao) {
        this.dtNegociacao = dtNegociacao;
    }

    public LocalDate getDtAprovRelAvalFinal() {
        return dtAprovRelAvalFinal;
    }

    public void setDtAprovRelAvalFinal(LocalDate dtAprovRelAvalFinal) {
        this.dtAprovRelAvalFinal = dtAprovRelAvalFinal;
    }

    public ProgramasProjectos getProgramasProjectos() {
        return programasProjectos;
    }

    public void setProgramasProjectos(ProgramasProjectos programasProjectos) {
        this.programasProjectos = programasProjectos;
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

        ConcursoDTO concursoDTO = (ConcursoDTO) o;
        if(concursoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), concursoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConcursoDTO{" +
            "id=" + getId() +
            ", tipoConcurso='" + getTipoConcurso() + "'" +
            ", dtLancamento='" + getDtLancamento() + "'" +
            ", dtUltimaAlteracao='" + getDtUltimaAlteracao() + "'" +
            ", dtEntregaProposta='" + getDtEntregaProposta() + "'" +
            ", dtAberturaProposta='" + getDtAberturaProposta() + "'" +
            ", dtConclusaoAvaliacaoRelPrel='" + getDtConclusaoAvaliacaoRelPrel() + "'" +
            ", dtNegociacao='" + getDtNegociacao() + "'" +
            ", dtAprovRelAvalFinal='" + getDtAprovRelAvalFinal() + "'" +
            "}";
    }
}
