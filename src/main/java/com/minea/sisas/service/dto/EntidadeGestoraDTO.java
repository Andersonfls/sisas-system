package com.minea.sisas.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the EntidadeGestora entity.
 */
public class EntidadeGestoraDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idEntidadeGestora;

    @NotNull
    @Size(max = 100)
    private String nmEntidadeGestora;

    @NotNull
    private Long tpFormaJuridica;

    private LocalDate dtConstituicao;

    @NotNull
    @Size(max = 100)
    private String endereco;

    @Size(max = 80)
    private String email;

    @Size(max = 250)
    private String contactos;

    @NotNull
    private Long tpModeloGestao;

    @NotNull
    private Long numRecursosHumanos;

    private BigDecimal numPopulacaoAreaAtendimento;

    private Long idMunicipioAtendidoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEntidadeGestora() {
        return idEntidadeGestora;
    }

    public void setIdEntidadeGestora(Long idEntidadeGestora) {
        this.idEntidadeGestora = idEntidadeGestora;
    }

    public String getNmEntidadeGestora() {
        return nmEntidadeGestora;
    }

    public void setNmEntidadeGestora(String nmEntidadeGestora) {
        this.nmEntidadeGestora = nmEntidadeGestora;
    }

    public Long getTpFormaJuridica() {
        return tpFormaJuridica;
    }

    public void setTpFormaJuridica(Long tpFormaJuridica) {
        this.tpFormaJuridica = tpFormaJuridica;
    }

    public LocalDate getDtConstituicao() {
        return dtConstituicao;
    }

    public void setDtConstituicao(LocalDate dtConstituicao) {
        this.dtConstituicao = dtConstituicao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactos() {
        return contactos;
    }

    public void setContactos(String contactos) {
        this.contactos = contactos;
    }

    public Long getTpModeloGestao() {
        return tpModeloGestao;
    }

    public void setTpModeloGestao(Long tpModeloGestao) {
        this.tpModeloGestao = tpModeloGestao;
    }

    public Long getNumRecursosHumanos() {
        return numRecursosHumanos;
    }

    public void setNumRecursosHumanos(Long numRecursosHumanos) {
        this.numRecursosHumanos = numRecursosHumanos;
    }

    public BigDecimal getNumPopulacaoAreaAtendimento() {
        return numPopulacaoAreaAtendimento;
    }

    public void setNumPopulacaoAreaAtendimento(BigDecimal numPopulacaoAreaAtendimento) {
        this.numPopulacaoAreaAtendimento = numPopulacaoAreaAtendimento;
    }

    public Long getIdMunicipioAtendidoId() {
        return idMunicipioAtendidoId;
    }

    public void setIdMunicipioAtendidoId(Long municipiosAtendidosId) {
        this.idMunicipioAtendidoId = municipiosAtendidosId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntidadeGestoraDTO entidadeGestoraDTO = (EntidadeGestoraDTO) o;
        if(entidadeGestoraDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entidadeGestoraDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntidadeGestoraDTO{" +
            "id=" + getId() +
            ", idEntidadeGestora=" + getIdEntidadeGestora() +
            ", nmEntidadeGestora='" + getNmEntidadeGestora() + "'" +
            ", tpFormaJuridica=" + getTpFormaJuridica() +
            ", dtConstituicao='" + getDtConstituicao() + "'" +
            ", endereco='" + getEndereco() + "'" +
            ", email='" + getEmail() + "'" +
            ", contactos='" + getContactos() + "'" +
            ", tpModeloGestao=" + getTpModeloGestao() +
            ", numRecursosHumanos=" + getNumRecursosHumanos() +
            ", numPopulacaoAreaAtendimento=" + getNumPopulacaoAreaAtendimento() +
            "}";
    }
}
