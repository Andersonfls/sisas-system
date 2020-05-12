package com.minea.sisas.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @Author Anderson Soares - @git/Andersonfls
 **/

@Entity
@Table(name = "entidade_gestora")
public class EntidadeGestora implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_entidade_gestora", nullable = false)
    private Long idEntidadeGestora;

    @NotNull
    @Size(max = 100)
    @Column(name = "nm_entidade_gestora", length = 100, nullable = false)
    private String nmEntidadeGestora;

    @NotNull
    @Column(name = "tp_forma_juridica", nullable = false)
    private Long tpFormaJuridica;

    @Column(name = "dt_constituicao")
    private LocalDate dtConstituicao;

    @NotNull
    @Size(max = 100)
    @Column(name = "endereco", length = 100, nullable = false)
    private String endereco;

    @Size(max = 80)
    @Column(name = "email", length = 80)
    private String email;

    @Size(max = 250)
    @Column(name = "contactos", length = 250)
    private String contactos;

    @NotNull
    @Column(name = "tp_modelo_gestao", nullable = false)
    private Long tpModeloGestao;

    @NotNull
    @Column(name = "num_recursos_humanos", nullable = false)
    private Long numRecursosHumanos;

    @Column(name = "num_populacao_area_atendimento", precision=10, scale=2)
    private BigDecimal numPopulacaoAreaAtendimento;

    @ManyToOne(optional = false)
    @NotNull
    private MunicipiosAtendidos idMunicipioAtendido;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEntidadeGestora() {
        return idEntidadeGestora;
    }

    public EntidadeGestora idEntidadeGestora(Long idEntidadeGestora) {
        this.idEntidadeGestora = idEntidadeGestora;
        return this;
    }

    public void setIdEntidadeGestora(Long idEntidadeGestora) {
        this.idEntidadeGestora = idEntidadeGestora;
    }

    public String getNmEntidadeGestora() {
        return nmEntidadeGestora;
    }

    public EntidadeGestora nmEntidadeGestora(String nmEntidadeGestora) {
        this.nmEntidadeGestora = nmEntidadeGestora;
        return this;
    }

    public void setNmEntidadeGestora(String nmEntidadeGestora) {
        this.nmEntidadeGestora = nmEntidadeGestora;
    }

    public Long getTpFormaJuridica() {
        return tpFormaJuridica;
    }

    public EntidadeGestora tpFormaJuridica(Long tpFormaJuridica) {
        this.tpFormaJuridica = tpFormaJuridica;
        return this;
    }

    public void setTpFormaJuridica(Long tpFormaJuridica) {
        this.tpFormaJuridica = tpFormaJuridica;
    }

    public LocalDate getDtConstituicao() {
        return dtConstituicao;
    }

    public EntidadeGestora dtConstituicao(LocalDate dtConstituicao) {
        this.dtConstituicao = dtConstituicao;
        return this;
    }

    public void setDtConstituicao(LocalDate dtConstituicao) {
        this.dtConstituicao = dtConstituicao;
    }

    public String getEndereco() {
        return endereco;
    }

    public EntidadeGestora endereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public EntidadeGestora email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactos() {
        return contactos;
    }

    public EntidadeGestora contactos(String contactos) {
        this.contactos = contactos;
        return this;
    }

    public void setContactos(String contactos) {
        this.contactos = contactos;
    }

    public Long getTpModeloGestao() {
        return tpModeloGestao;
    }

    public EntidadeGestora tpModeloGestao(Long tpModeloGestao) {
        this.tpModeloGestao = tpModeloGestao;
        return this;
    }

    public void setTpModeloGestao(Long tpModeloGestao) {
        this.tpModeloGestao = tpModeloGestao;
    }

    public Long getNumRecursosHumanos() {
        return numRecursosHumanos;
    }

    public EntidadeGestora numRecursosHumanos(Long numRecursosHumanos) {
        this.numRecursosHumanos = numRecursosHumanos;
        return this;
    }

    public void setNumRecursosHumanos(Long numRecursosHumanos) {
        this.numRecursosHumanos = numRecursosHumanos;
    }

    public BigDecimal getNumPopulacaoAreaAtendimento() {
        return numPopulacaoAreaAtendimento;
    }

    public EntidadeGestora numPopulacaoAreaAtendimento(BigDecimal numPopulacaoAreaAtendimento) {
        this.numPopulacaoAreaAtendimento = numPopulacaoAreaAtendimento;
        return this;
    }

    public void setNumPopulacaoAreaAtendimento(BigDecimal numPopulacaoAreaAtendimento) {
        this.numPopulacaoAreaAtendimento = numPopulacaoAreaAtendimento;
    }

    public MunicipiosAtendidos getIdMunicipioAtendido() {
        return idMunicipioAtendido;
    }

    public EntidadeGestora idMunicipioAtendido(MunicipiosAtendidos municipiosAtendidos) {
        this.idMunicipioAtendido = municipiosAtendidos;
        return this;
    }

    public void setIdMunicipioAtendido(MunicipiosAtendidos municipiosAtendidos) {
        this.idMunicipioAtendido = municipiosAtendidos;
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
        EntidadeGestora entidadeGestora = (EntidadeGestora) o;
        if (entidadeGestora.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entidadeGestora.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntidadeGestora{" +
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
