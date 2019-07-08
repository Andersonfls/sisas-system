package com.minea.sisas.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Contactos entity.
 */
public class ContactosDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idContactos;

    @NotNull
    @Size(max = 150)
    private String nmContactos;

    @NotNull
    @Size(max = 2500)
    private String textoContactos;

    @NotNull
    @Size(max = 100)
    private String resumoTextoContactos;

    @NotNull
    private LocalDate dtLancamento;

    private LocalDate dtUltimaAlteracao;

    private Long idSituacaoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdContactos() {
        return idContactos;
    }

    public void setIdContactos(Long idContactos) {
        this.idContactos = idContactos;
    }

    public String getNmContactos() {
        return nmContactos;
    }

    public void setNmContactos(String nmContactos) {
        this.nmContactos = nmContactos;
    }

    public String getTextoContactos() {
        return textoContactos;
    }

    public void setTextoContactos(String textoContactos) {
        this.textoContactos = textoContactos;
    }

    public String getResumoTextoContactos() {
        return resumoTextoContactos;
    }

    public void setResumoTextoContactos(String resumoTextoContactos) {
        this.resumoTextoContactos = resumoTextoContactos;
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

    public Long getIdSituacaoId() {
        return idSituacaoId;
    }

    public void setIdSituacaoId(Long situacaoId) {
        this.idSituacaoId = situacaoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContactosDTO contactosDTO = (ContactosDTO) o;
        if(contactosDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contactosDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContactosDTO{" +
            "id=" + getId() +
            ", idContactos=" + getIdContactos() +
            ", nmContactos='" + getNmContactos() + "'" +
            ", textoContactos='" + getTextoContactos() + "'" +
            ", resumoTextoContactos='" + getResumoTextoContactos() + "'" +
            ", dtLancamento='" + getDtLancamento() + "'" +
            ", dtUltimaAlteracao='" + getDtUltimaAlteracao() + "'" +
            "}";
    }
}
