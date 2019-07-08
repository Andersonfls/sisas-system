package com.minea.sisas.service.dto;


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
    private Long idConcepcao;

    @NotNull
    @Size(max = 150)
    private String tipoConcurso;

    @NotNull
    private LocalDate dtLancamento;

    private LocalDate dtUltimaAlteracao;

    private LocalDate dtElaboracaoCon;

    private LocalDate dtAprovacaoCon;

    private Long idProgramasProjectosId;

    private Long idSistemaAguaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdConcepcao() {
        return idConcepcao;
    }

    public void setIdConcepcao(Long idConcepcao) {
        this.idConcepcao = idConcepcao;
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
            ", idConcepcao=" + getIdConcepcao() +
            ", tipoConcurso='" + getTipoConcurso() + "'" +
            ", dtLancamento='" + getDtLancamento() + "'" +
            ", dtUltimaAlteracao='" + getDtUltimaAlteracao() + "'" +
            ", dtElaboracaoCon='" + getDtElaboracaoCon() + "'" +
            ", dtAprovacaoCon='" + getDtAprovacaoCon() + "'" +
            "}";
    }
}
