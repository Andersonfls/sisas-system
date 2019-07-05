package com.minea.sisas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Midia.
 */
@Entity
@Table(name = "midia")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Midia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "midia")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TipoObra> tipoObras = new HashSet<>();

    /*----------------ID----------------------*/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    /*----------------------------------------*/

    /*----------------------Nome------------------------*/
    public String getNome() {
        return nome;
    }
    public Midia nome(String nome) {
        this.nome = nome;
        return this;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    /*--------------------------------------------------*/

    /*-------------------------Status----------------------------*/
    public Boolean getStatus() {
        return status;
    }
    public Midia status(Boolean status) {
        this.status = status;
        return this;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    /*-----------------------------------------------------------*/

    /*----------------------------Enviar chave Estrangeira-------------------------*/
    public Set<TipoObra> getTipoObras() {
        return tipoObras;
    }
    public Midia tipoObras(Set<TipoObra> tipoObras) {
        this.tipoObras = tipoObras;
        return this;
    }
    public Midia addTipoObra(TipoObra tipoObra) {
        this.tipoObras.add(tipoObra);
        tipoObra.setMidia(this);
        return this;
    }
    public Midia removeTipoObra(TipoObra tipoObra) {
        this.tipoObras.remove(tipoObra);
        tipoObra.setMidia(null);
        return this;
    }
    public void setTipoObras(Set<TipoObra> tipoObras) {
        this.tipoObras = tipoObras;
    }
    /*-----------------------------------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Midia midia = (Midia) o;
        if (midia.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), midia.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Midia{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
