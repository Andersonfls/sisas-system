package com.minea.sisas.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A ProgramasProjectos.
 */
@Entity
@Table(name = "programas_projectos")
public class ProgramasProjectos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_programas_projectos", nullable = false)
    private Long id;

    @Column(name = "dt_lancamento", nullable = false)
    private LocalDate dtLancamento;

    @Column(name = "dt_ultima_alteracao")
    private LocalDate dtUltimaAlteracao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario")
    private User usuario;

    @NotNull
    @Size(max = 250)
    @Column(name = "nm_designacao_projeto", length = 250, nullable = false)
    private String nmDesignacaoProjeto;

    @NotNull
    @Size(max = 250)
    @Column(name = "nm_descricao_projeto", length = 250, nullable = false)
    private String nmDescricaoProjeto;

    @Column(name = "id_saa_associado")
    private Long idSaaAssociado;

    @NotNull
    @Size(max = 150)
    @Column(name = "tipo_financiamento", length = 150, nullable = false)
    private String tipoFinanciamento;

    @ManyToOne
    @JoinColumn(name = "id_especialidade")
    private Especialidades especialidade;

    @ManyToOne
    @JoinColumn(name = "id_comuna")
    private Comuna comuna;

    @ManyToOne
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;

    @ManyToOne
    @JoinColumn(name = "id_municipio")
    private Municipio municipio;

    @Size(max = 150)
    @Column(name = "nm_localidade", length = 150)
    private String nmLocalidade;

    @Size(max = 150)
    @Column(name = "finalidade_projeto", length = 150)
    private String finalidadeProjeto;

    @Column(name = "associado_inquerito")
    private String associadoInquerito;

    @Column(name = "status")
    private Boolean status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public ProgramasProjectos dtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
        return this;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public LocalDate getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }

    public ProgramasProjectos dtUltimaAlteracao(LocalDate dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
        return this;
    }

    public void setDtUltimaAlteracao(LocalDate dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }

    public User getUsuario() {
        return usuario;
    }

    public ProgramasProjectos usuario(User usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public String getNmDesignacaoProjeto() {
        return nmDesignacaoProjeto;
    }

    public ProgramasProjectos nmDesignacaoProjeto(String nmDesignacaoProjeto) {
        this.nmDesignacaoProjeto = nmDesignacaoProjeto;
        return this;
    }

    public void setNmDesignacaoProjeto(String nmDesignacaoProjeto) {
        this.nmDesignacaoProjeto = nmDesignacaoProjeto;
    }

    public String getNmDescricaoProjeto() {
        return nmDescricaoProjeto;
    }

    public ProgramasProjectos nmDescricaoProjeto(String nmDescricaoProjeto) {
        this.nmDescricaoProjeto = nmDescricaoProjeto;
        return this;
    }

    public void setNmDescricaoProjeto(String nmDescricaoProjeto) {
        this.nmDescricaoProjeto = nmDescricaoProjeto;
    }

    public Long getIdSaaAssociado() {
        return idSaaAssociado;
    }

    public ProgramasProjectos idSaaAssociado(Long idSaaAssociado) {
        this.idSaaAssociado = idSaaAssociado;
        return this;
    }

    public void setIdSaaAssociado(Long idSaaAssociado) {
        this.idSaaAssociado = idSaaAssociado;
    }

    public String getTipoFinanciamento() {
        return tipoFinanciamento;
    }

    public ProgramasProjectos tipoFinanciamento(String tipoFinanciamento) {
        this.tipoFinanciamento = tipoFinanciamento;
        return this;
    }

    public void setTipoFinanciamento(String tipoFinanciamento) {
        this.tipoFinanciamento = tipoFinanciamento;
    }

    public Especialidades getEspecialidade() {
        return especialidade;
    }

    public ProgramasProjectos idEspecialidade(Especialidades especialidade) {
        this.especialidade = especialidade;
        return this;
    }

    public void setEspecialidade(Especialidades especialidade) {
        this.especialidade = especialidade;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public ProgramasProjectos idComuna(Comuna comuna) {
        this.comuna = comuna;
        return this;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public ProgramasProjectos idProvincia(Provincia provincia) {
        this.provincia = provincia;
        return this;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public ProgramasProjectos idMunicipio(Municipio municipio) {
        this.municipio = municipio;
        return this;
    }

    public Municipio setMunicipio() {
        return municipio;
    }

    public String getNmLocalidade() {
        return nmLocalidade;
    }

    public ProgramasProjectos nmLocalidade(String nmLocalidade) {
        this.nmLocalidade = nmLocalidade;
        return this;
    }

    public void setNmLocalidade(String nmLocalidade) {
        this.nmLocalidade = nmLocalidade;
    }

    public String getFinalidadeProjeto() {
        return finalidadeProjeto;
    }

    public ProgramasProjectos finalidadeProjeto(String finalidadeProjeto) {
        this.finalidadeProjeto = finalidadeProjeto;
        return this;
    }

    public void setFinalidadeProjeto(String finalidadeProjeto) {
        this.finalidadeProjeto = finalidadeProjeto;
    }

    public String getAssociadoInquerito() {
        return associadoInquerito;
    }

    public void setAssociadoInquerito(String associadoInquerito) {
        this.associadoInquerito = associadoInquerito;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
        ProgramasProjectos programasProjectos = (ProgramasProjectos) o;
        if (programasProjectos.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), programasProjectos.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProgramasProjectos{" +
            "id=" + getId() +
            ", dtLancamento='" + getDtLancamento() + "'" +
            ", dtUltimaAlteracao='" + getDtUltimaAlteracao() + "'" +
            ", usuario=" + getUsuario() +
            ", nmDesignacaoProjeto='" + getNmDesignacaoProjeto() + "'" +
            ", nmDescricaoProjeto='" + getNmDescricaoProjeto() + "'" +
            ", idSaaAssociado=" + getIdSaaAssociado() +
            ", tipoFinanciamento='" + getTipoFinanciamento() + "'" +
            ", especialidades='" + getEspecialidade() + "'" +
            "}";
    }
}
