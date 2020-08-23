package com.minea.sisas.service.dto;


import com.minea.sisas.domain.ProgramasProjectos;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Concepcao entity.
 */
public class ConcepcaoDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 150)
    private String tipoConcurso;

    private LocalDate dtLancamento;

    private LocalDate dtUltimaAlteracao;

    private LocalDate dtElaboracaoCon;

    private LocalDate dtAprovacaoCon;

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

    public LocalDate getDtElaboracaoCon() {
        return dtElaboracaoCon;
    }

    public void setDtElaboracaoCon(LocalDate dtElaboracaoCon) {
        this.dtElaboracaoCon = dtElaboracaoCon;
    }

    public LocalDate getDtAprovacaoCon() {
        return dtAprovacaoCon;
    }

    public void setDtAprovacaoCon(LocalDate dtAprovacaoCon) {
        this.dtAprovacaoCon = dtAprovacaoCon;
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

        ConcepcaoDTO concepcaoDTO = (ConcepcaoDTO) o;
        if(concepcaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), concepcaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConcepcaoDTO{" +
            "id=" + getId() +
            ", tipoConcurso='" + getTipoConcurso() + "'" +
            ", dtLancamento='" + getDtLancamento() + "'" +
            ", dtUltimaAlteracao='" + getDtUltimaAlteracao() + "'" +
            ", dtElaboracaoCon='" + getDtElaboracaoCon() + "'" +
            ", dtAprovacaoCon='" + getDtAprovacaoCon() + "'" +
            "}";
    }
}
