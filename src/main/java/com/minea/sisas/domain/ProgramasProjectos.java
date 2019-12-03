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

    @NotNull
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

    @Size(max = 100)
    @Column(name = "especialidade", length = 100)
    private String especialidade;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_comuna")
    private Comuna comuna;

    //novos campos
    @ManyToOne
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;

    @ManyToOne
    @JoinColumn(name = "id_municipio")
    private Municipio municipio;

    @Column(name = "associado_inquerito")
    private Boolean associadoInquerito;

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

    public String getEspecialidade() {
        return especialidade;
    }

    public ProgramasProjectos especialidade(String especialidade) {
        this.especialidade = especialidade;
        return this;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public ProgramasProjectos comuna(Comuna comuna) {
        this.comuna = comuna;
        return this;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Boolean getAssociadoInquerito() {
        return associadoInquerito;
    }

    public void setAssociadoInquerito(Boolean associadoInquerito) {
        this.associadoInquerito = associadoInquerito;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
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
            ", especialidade='" + getEspecialidade() + "'" +
            "}";
    }
}
