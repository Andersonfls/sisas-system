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
@Table(name = "editora")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Editora implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nomeEditora;

    @OneToMany(mappedBy = "editora")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Obra> obras = new HashSet<>();

    /*------------------ID--------------------*/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    /*----------------------------------------*/

    /*---------------------------------Identificacao--------------------------------*/
    public String getNomeEditora() {
        return nomeEditora;
    }
    public Editora nomeEditora(String nomeEditora) {
        this.nomeEditora = nomeEditora;
        return this;
    }
    public void setNomeEditora(String nomeEditora) {
        this.nomeEditora = nomeEditora;
    }
    /*------------------------------------------------------------------------------*/

    /*---------------Enviar chave estrangeira------------------*/
    public Set<Obra> obras() {
        return obras;
    }
    public Editora obras(Set<Obra> obras) {
        this.obras = obras;
        return this;
    }
    public Editora addObra(Obra obra) {
        this.obras.add(obra);
        obra.setEditora(this);
        return this;
    }
    public Editora removeObra(Obra obra) {
        this.obras.remove(obra);
        obra.setEditora(null);
        return this;
    }
    public void setObras(Set<Obra> obras) {
        this.obras = obras;
    }
    /*---------------------------------------------------------*/

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Editora editora = (Editora) o;
        if (editora.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), editora.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Editora{" +
            "id=" + getId() +
            ", nomeEditoram='" + getNomeEditora() + "'" +
            "}";
    }
}
