package com.minea.sisas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * A TipoObra.
 */
@Entity
@Table(name = "tipo_obra")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TipoObra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "prazo")
    private Integer prazo;

    @OneToMany(mappedBy = "tipoObra")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Obra> obras = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    private Midia midia;

    public TipoObra() {
    }

    public TipoObra(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    /*-----------------ID---------------------*/
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
    public TipoObra nome(String nome) {
        this.nome = nome;
        return this;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    /*--------------------------------------------------*/

    /*----------------------Prazo----------------------------*/
    public Integer getPrazo() {
        return prazo;
    }
    public TipoObra prazo(Integer prazo) {
        this.prazo = prazo;
        return this;
    }
    public void setPrazo(Integer prazo) {
        this.prazo = prazo;
    }
    /*-------------------------------------------------------*/

    /*-------------------------Status----------------------------*/
    public Boolean getStatus() {
        return status;
    }
    public TipoObra status(Boolean status) {
        this.status = status;
        return this;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    /*-----------------------------------------------------------*/

    /*--------------Receber chave estrangeira--------------*/
    public Midia getMidia() {
        return midia;
    }
    public TipoObra midia(Midia midia) {
        this.midia = midia;
        return this;
    }
    public void setMidia(Midia midia) {
        this.midia = midia;
    }
    /*-----------------------------------------------------*/

    /*---------------Enviar chave estrangeira------------------*/
    public Set<Obra> obras() {
        return obras;
    }
    public TipoObra obras(Set<Obra> obras) {
        this.obras = obras;
        return this;
    }
    public TipoObra addObra(Obra obra) {
        this.obras.add(obra);
        obra.setTipoObra(this);
        return this;
    }
    public TipoObra removeObra(Obra obra) {
        this.obras.remove(obra);
        obra.setTipoObra(null);
        return this;
    }
    public void setObras(Set<Obra> obras) {
        this.obras = obras;
    }
    /*---------------------------------------------------------*/


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TipoObra tipoObra = (TipoObra) o;
        if (tipoObra.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipoObra.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TipoObra{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", prazo='" + getPrazo() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
