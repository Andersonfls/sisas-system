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

@Entity
@Table(name = "espaco")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Espaco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "codigo_espaco_pai")
    private Long codEspacoPai;

    @Column(name = "nome_espaco_pai")
    private String nomeEspacoPai;

    @Column(name = "rf_espaco")
    private String rfEspaco;

    @Column(name = "br_espaco")
    private String brEspaco;

    @Column(name = "qr_espaco")
    private String qrEspaco;

    @Column(name = "nf_espaco")
    private String nfEspaco;

    @ManyToOne(optional = false)
    @NotNull
    private TipoEspaco tipoEspaco;

    @OneToMany(mappedBy = "espaco")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Exemplar> exemplares = new HashSet<>();


    /*--------------------ID------------------*/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    /*----------------------------------------*/

    /*--------------------Nome--------------------------*/
    public String getNome() {
        return nome;
    }
    public Espaco nome(String nome) {
        this.nome = nome;
        return this;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    /*--------------------------------------------------*/

    /*--------------------Descricao--------------------------*/
    public String getDescricao() {
        return descricao;
    }
    public Espaco descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    /*--------------------------------------------------*/

    /*--------------------------STATUS---------------------------*/
    public Boolean getStatus() {
        return status;
    }
    public Espaco status(Boolean status) {
        this.status = status;
        return this;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    /*-----------------------------------------------------------*/

    /*--------------------------------Codigo_Espaco_pai----------------------------------*/
    public Long getCodEspacoPai() {
        return codEspacoPai;
    }
    public Espaco codEspacoPai(Long codEspacoPai) {
        this.codEspacoPai = codEspacoPai;
        return this;
    }
    public void setCodEspacoPai(Long codEspacoPai) {
        this.codEspacoPai = codEspacoPai;
    }
    /*-----------------------------------------------------------------------------------*/

    /*-------------------------------------Nome Espaco Pai----------------------------------*/
    public String getNomeEspacoPai() {
        return nomeEspacoPai;
    }
    public Espaco nomeEspacoPai(String nomeEspacoPai) {
        this.nomeEspacoPai = nomeEspacoPai;
        return this;
    }
    public void setNomeEspacoPai(String nomeEspacoPai) {
        this.nomeEspacoPai = nomeEspacoPai;
    }
    /*--------------------------------------------------------------------------------------*/

    /*-----------------------------------RF_ESPACO----------------------*/
    public String getRfEspaco() {
        return rfEspaco;
    }
    public Espaco rfEspaco(String rfEspaco) {
        this.rfEspaco = rfEspaco;
        return this;
    }
    public void setRfEspaco(String rfEspaco) {
        this.rfEspaco = rfEspaco;
    }
    /*------------------------------------------------------------------*/

    /*-------------------------BR_ESPACO--------------------------------*/
    public String getBrEspaco() {
        return brEspaco;
    }
    public Espaco brEspaco(String brEspaco) {
        this.brEspaco = brEspaco;
        return this;
    }
    public void setBrEspaco(String brEspaco) {
        this.brEspaco = brEspaco;
    }
    /*------------------------------------------------------------------*/

    /*-----------------------------QR_ESPACO----------------------------*/
    public String getQrEspaco() {
        return qrEspaco;
    }
    public Espaco qrEspaco(String qrEspaco) {
        this.qrEspaco = qrEspaco;
        return this;
    }
    public void setQrEspaco(String qrEspaco) {
        this.qrEspaco = qrEspaco;
    }
    /*------------------------------------------------------------------*/

    /*--------------------------NF_ESPACO-------------------------------*/
    public String getNfEspaco() {
        return nfEspaco;
    }
    public Espaco nfEspaco(String nfEspaco) {
        this.nfEspaco = nfEspaco;
        return this;
    }
    public void setNfEspaco(String nfEspaco) {
        this.nfEspaco = nfEspaco;
    }
    /*------------------------------------------------------------------*/

    /*-------------------------Receber chave estrangeira----------------------------*/
    public TipoEspaco getTipoEspaco() {
        return tipoEspaco;
    }
    public Espaco tipoEspaco(TipoEspaco tipoEspaco) {
        this.tipoEspaco = tipoEspaco;
        return this;
    }
    public void setTipoEspaco(TipoEspaco tipoEspaco) {
        this.tipoEspaco = tipoEspaco;
    }
    /*------------------------------------------------------------------------------*/

    /*-------------------Enviar chave Estrangeira para Exemplar-----------------------*/
    public Set<Exemplar> getExemplares() {
        return exemplares;
    }
    public Espaco exemplares(Set<Exemplar> exemplares) {
        this.exemplares = exemplares;
        return this;
    }
    public Espaco addExemplar(Exemplar exemplar) {
        this.exemplares.add(exemplar);
        exemplar.setEspaco(this);
        return this;
    }
    public Espaco removeExemplar(Exemplar exemplar) {
        this.exemplares.remove(exemplar);
        exemplar.setEspaco(null);
        return this;
    }
    public void setExemplares(Set<Exemplar> exemplares) {
        this.exemplares = exemplares;
    }
    /*---------------------------------------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Espaco espaco = (Espaco) o;
        if (espaco.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), espaco.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Espaco{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", status='" + getStatus() + "'" +
            ", codEspacoPai='" + getCodEspacoPai() + "'" +
            ", nomeEspacoPai='" + getNomeEspacoPai() + "'" +
            ", rfEspaco='" + getRfEspaco() + "'" +
            ", brEspaco='" + getBrEspaco() + "'" +
            ", qrEspaco='" + getQrEspaco() + "'" +
            ", nfEspaco='" + getRfEspaco() + "'" +
            "}";
    }
}
