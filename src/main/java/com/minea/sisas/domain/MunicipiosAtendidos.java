package com.minea.sisas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A MunicipiosAtendidos.
 */
@Entity
@Table(name = "municipios_atendidos")
public class MunicipiosAtendidos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_municipio_atendido", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    private Municipio idMunicipio;

    @OneToMany(mappedBy = "idMunicipioAtendido")
    @JsonIgnore
    private Set<EntidadeGestora> entidadeGestoras = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Municipio getIdMunicipio() {
        return idMunicipio;
    }

    public MunicipiosAtendidos idMunicipio(Municipio municipio) {
        this.idMunicipio = municipio;
        return this;
    }

    public void setIdMunicipio(Municipio municipio) {
        this.idMunicipio = municipio;
    }

    public Set<EntidadeGestora> getEntidadeGestoras() {
        return entidadeGestoras;
    }

    public MunicipiosAtendidos entidadeGestoras(Set<EntidadeGestora> entidadeGestoras) {
        this.entidadeGestoras = entidadeGestoras;
        return this;
    }

    public MunicipiosAtendidos addEntidadeGestora(EntidadeGestora entidadeGestora) {
        this.entidadeGestoras.add(entidadeGestora);
        entidadeGestora.setIdMunicipioAtendido(this);
        return this;
    }

    public MunicipiosAtendidos removeEntidadeGestora(EntidadeGestora entidadeGestora) {
        this.entidadeGestoras.remove(entidadeGestora);
        entidadeGestora.setIdMunicipioAtendido(null);
        return this;
    }

    public void setEntidadeGestoras(Set<EntidadeGestora> entidadeGestoras) {
        this.entidadeGestoras = entidadeGestoras;
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
        MunicipiosAtendidos municipiosAtendidos = (MunicipiosAtendidos) o;
        if (municipiosAtendidos.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), municipiosAtendidos.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MunicipiosAtendidos{" +
            "id=" + getId() +
            "}";
    }
}
