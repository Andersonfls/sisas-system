package com.minea.sisas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "autor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Autor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "autor")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AutorObra> autorObras = new HashSet<>();

    /*------------------ID--------------------*/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    /*----------------------------------------*/

    /*----------------Nome------------------------------*/
    public String getNome() {
        return nome;
    }
    public Autor nome(String nome) {
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
    public Autor status(Boolean status) {
        this.status = status;
        return this;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    /*-----------------------------------------------------------*/

    /*--------------------------------Enviar chave Estrangeira--------------------------*/
    public Set<AutorObra> getAutorObras() {
        return autorObras;
    }
    public Autor autorObras(Set<AutorObra> autorObras) {
        this.autorObras = autorObras;
        return this;
    }
    public Autor addAutorObra(AutorObra autorObra) {
        this.autorObras.add(autorObra);
        autorObra.setAutor(this);
        return this;
    }
    public Autor removeAutorObra(AutorObra autorObra) {
        this.autorObras.remove(autorObra);
        autorObra.setAutor(null);
        return this;
    }
    public void setAutorObras(Set<AutorObra> autorObras) {
        this.autorObras = autorObras;
    }
    /*----------------------------------------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Autor autor = (Autor) o;
        if (autor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), autor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Autor{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }

}
