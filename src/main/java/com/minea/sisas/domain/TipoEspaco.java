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
@Table(name = "tipo_espaco")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TipoEspaco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "id_tipo_espaco_pai")
    private Long idTipoEspacoPai;

    @Column(name = "nome_pai")
    private String nomePai;

    @OneToMany(mappedBy = "tipoEspaco")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Espaco> espacos = new HashSet<>();


    /*-----------------ID---------------------*/
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    /*----------------------------------------*/

    /*-----------------Nome-----------------------------*/
    public String getNome() { return nome; }
    public TipoEspaco nome(String nome) {
        this.nome = nome;
        return this;
    }
    public void setNome(String nome) { this.nome = nome; }
    /*--------------------------------------------------*/

    /*-----------------------Status------------------------------*/
    public Boolean getStatus() { return status; }
    public TipoEspaco status(Boolean status) {
        this.status = status;
        return this;
    }
    public void setStatus(Boolean status) { this.status = status; }
    /*-----------------------------------------------------------*/

    /*-------------------------------------Id_Espaco_Pai-----------------------------*/
    public Long getIdTipoEspacoPai() { return idTipoEspacoPai; }
    public TipoEspaco idTipoEspacoPai(Long idTipoEspacoPai) {
        this.idTipoEspacoPai = idTipoEspacoPai;
        return this;
    }
    public void setIdTipoEspacoPai(Long idTipoEspacoPai) {
        this.idTipoEspacoPai = idTipoEspacoPai;
    }
    /*-------------------------------------------------------------------------------*/

    /*-----------------------Nome Pai-------------------------------*/
    public String getNomePai() { return nomePai; }
    public TipoEspaco nomePai(String nomePai) {
        this.nomePai = nomePai;
        return this;
    }
    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }
    /*--------------------------------------------------------------*/

    /*--------------------Enviar chave estrangeira-----------------------*/
    public Set<Espaco> espacos() {
        return espacos;
    }
    public TipoEspaco obras(Set<Espaco> espacos) {
        this.espacos = espacos;
        return this;
    }
    public TipoEspaco addEspaco(Espaco espaco) {
        this.espacos.add(espaco);
        espaco.setTipoEspaco(this);
        return this;
    }
    public TipoEspaco removeEspaco(Espaco espaco) {
        this.espacos.remove(espaco);
        espaco.setTipoEspaco(null);
        return this;
    }
    public void setEspacos(Set<Espaco> espacos) {
        this.espacos = espacos;
    }
    /*-------------------------------------------------------------------*/


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TipoEspaco tipoEspaco = (TipoEspaco) o;
        if (tipoEspaco.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipoEspaco.getId());
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
            ", status='" + getStatus() + "'" +
            ", idTipoEspacoPai='" + getIdTipoEspacoPai() + "'" +
            ", nomePai='" + getNomePai() +
            "}";
    }

}
