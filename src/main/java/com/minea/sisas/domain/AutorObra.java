package com.minea.sisas.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "autor_obra")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AutorObra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    private Autor autor;

    @ManyToOne(optional = false)
    @NotNull
    private Obra obra;

    /*-------------------ID-------------------*/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    /*----------------------------------------*/

    /*--------------Receber chave estrangeira--------------*/
    public Autor getAutor() {
        return autor;
    }
    public AutorObra autor(Autor autor) {
        this.autor = autor;
        return this;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    /*-----------------------------------------------------*/

    /*-------------Receber chave estrangeira----------*/
    public Obra getObra() {
        return obra;
    }
    public AutorObra obra(Obra obra) {
        this.obra = obra;
        return this;
    }
    public void setObra(Obra obra) {
        this.obra = obra;
    }
    /*------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AutorObra autorObra = (AutorObra) o;
        if (autorObra.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), autorObra.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AutorObra{" +
            "id=" + getId() +
            "}";
    }

}
