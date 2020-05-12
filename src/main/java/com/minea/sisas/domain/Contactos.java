package com.minea.sisas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * @Author Anderson Soares - @git/Andersonfls
 **/

@Entity
@Table(name = "contactos")
public class Contactos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_contactos", nullable = false)
    private Long idContactos;

    @NotNull
    @Size(max = 150)
    @Column(name = "nm_contactos", length = 150, nullable = false)
    private String nmContactos;

    @NotNull
    @Size(max = 2500)
    @Column(name = "texto_contactos", length = 2500, nullable = false)
    private String textoContactos;

    @NotNull
    @Size(max = 100)
    @Column(name = "resumo_texto_contactos", length = 100, nullable = false)
    private String resumoTextoContactos;

    @NotNull
    @Column(name = "dt_lancamento", nullable = false)
    private LocalDate dtLancamento;

    @Column(name = "dt_ultima_alteracao")
    private LocalDate dtUltimaAlteracao;

    @ManyToOne(optional = false)
    @NotNull
    private Situacao idSituacao;

    @OneToMany(mappedBy = "idContactos")
    @JsonIgnore
    private Set<Inicio> inicios = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdContactos() {
        return idContactos;
    }

    public Contactos idContactos(Long idContactos) {
        this.idContactos = idContactos;
        return this;
    }

    public void setIdContactos(Long idContactos) {
        this.idContactos = idContactos;
    }

    public String getNmContactos() {
        return nmContactos;
    }

    public Contactos nmContactos(String nmContactos) {
        this.nmContactos = nmContactos;
        return this;
    }

    public void setNmContactos(String nmContactos) {
        this.nmContactos = nmContactos;
    }

    public String getTextoContactos() {
        return textoContactos;
    }

    public Contactos textoContactos(String textoContactos) {
        this.textoContactos = textoContactos;
        return this;
    }

    public void setTextoContactos(String textoContactos) {
        this.textoContactos = textoContactos;
    }

    public String getResumoTextoContactos() {
        return resumoTextoContactos;
    }

    public Contactos resumoTextoContactos(String resumoTextoContactos) {
        this.resumoTextoContactos = resumoTextoContactos;
        return this;
    }

    public void setResumoTextoContactos(String resumoTextoContactos) {
        this.resumoTextoContactos = resumoTextoContactos;
    }

    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public Contactos dtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
        return this;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public LocalDate getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }

    public Contactos dtUltimaAlteracao(LocalDate dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
        return this;
    }

    public void setDtUltimaAlteracao(LocalDate dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }

    public Situacao getIdSituacao() {
        return idSituacao;
    }

    public Contactos idSituacao(Situacao situacao) {
        this.idSituacao = situacao;
        return this;
    }

    public void setIdSituacao(Situacao situacao) {
        this.idSituacao = situacao;
    }

    public Set<Inicio> getInicios() {
        return inicios;
    }

    public Contactos inicios(Set<Inicio> inicios) {
        this.inicios = inicios;
        return this;
    }

    public Contactos addInicio(Inicio inicio) {
        this.inicios.add(inicio);
        inicio.setIdContactos(this);
        return this;
    }

    public Contactos removeInicio(Inicio inicio) {
        this.inicios.remove(inicio);
        inicio.setIdContactos(null);
        return this;
    }

    public void setInicios(Set<Inicio> inicios) {
        this.inicios = inicios;
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
        Contactos contactos = (Contactos) o;
        if (contactos.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contactos.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Contactos{" +
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
