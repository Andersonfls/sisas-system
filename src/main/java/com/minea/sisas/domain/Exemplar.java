package com.minea.sisas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minea.sisas.domain.enumeration.StatusExemplar;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "exemplar")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Exemplar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rf_exemplar")
    private String rfExemplar;

    @Column(name = "br_exemplar")
    private String brExemplar;

    @Column(name = "qr_exemplar")
    private String qrExemplar;

    @Column(name = "nf_exemplar")
    private String nfExemplar;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private StatusExemplar status;

    @Column(name = "nome")
    private String nome;

    @Column(name = "nome_localizacao")
    private String nomeLocalizacao;

    @ManyToOne(optional = false)
    @NotNull
    private Obra obra;

    @ManyToOne(optional = false)
    @NotNull
    private Espaco espaco;

    @OneToMany(mappedBy = "exemplar")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Emprestimo> emprestimos = new HashSet<>();

    /*--------------------ID------------------*/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    /*----------------------------------------*/

    /*----------------------------RF_EXEMPLAR-----------------------------------*/
    public String getRfExemplar() {
        return rfExemplar;
    }
    public Exemplar rfExemplar(String rfExemplar) {
        this.rfExemplar = rfExemplar;
        return this;
    }
    public void setRfExemplar(String rfExemplar) {
        this.rfExemplar = rfExemplar;
    }
    /*--------------------------------------------------------------------------*/

    /*-------------------------------BR_EXEMPLAR--------------------------------*/
    public String getBrExemplar() {
        return brExemplar;
    }
    public Exemplar brExemplar(String brExemplar) {
        this.brExemplar = brExemplar;
        return this;
    }
    public void setBrExemplar(String brExemplar) {
        this.brExemplar = brExemplar;
    }
    /*--------------------------------------------------------------------------*/

    /*---------------------------QR_EXEMPLAR------------------------------------*/
    public String getQrExemplar() {
        return qrExemplar;
    }
    public Exemplar qrExemplar(String qrExemplar) {
        this.qrExemplar = qrExemplar;
        return this;
    }
    public void setQrExemplar(String qrExemplar) {
        this.qrExemplar = qrExemplar;
    }
    /*--------------------------------------------------------------------------*/

    /*-----------------------------NF_EXEMPLAR----------------------------------*/
    public String getNfExemplar() {
        return nfExemplar;
    }
    public Exemplar nfExemplar(String nfExemplar) {
        this.nfExemplar = nfExemplar;
        return this;
    }
    public void setNfExemplar(String nfExemplar) {
        this.nfExemplar = nfExemplar;
    }
    /*--------------------------------------------------------------------------*/

    /*---------------------Nome-------------------------*/
    public String getNome() {
        return nome;
    }
    public Exemplar nome(String nome) {
        this.nome = nome;
        return this;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    /*--------------------------------------------------*/

    /*--------------------------STATUS----------------------------------*/
    public StatusExemplar getStatus() {
        return status;
    }
    public Exemplar status(StatusExemplar status) {
        this.status = status;
        return this;
    }
    public void setStatus(StatusExemplar status) {
        this.status = status;
    }
    /*------------------------------------------------------------------*/

    /*------------------------------------NOMELOCALIZACAO-------------------------------------------*/
    public String getNomeLocalizacao() {
        return nomeLocalizacao;
    }
    public Exemplar nomeLocalizacao(String nomeLocalizacao) {
        this.nomeLocalizacao = nomeLocalizacao;
        return this;
    }
    public void setNomeLocalizacao(String nomeLocalizacao) {
        this.nomeLocalizacao = nomeLocalizacao;
    }
    /*----------------------------------------------------------------------------------------------*/

    /*--------Receber chave estrangeira Obra----------*/
    public Obra getObra() {
        return obra;
    }
    public Exemplar obra(Obra obra) {
        this.obra = obra;
        return this;
    }
    public void setObra(Obra obra) {
        this.obra = obra;
    }
    /*------------------------------------------------*/

    /*-----------Receber chave estrangeira Espaco---------------*/
    public Espaco getEspaco() {
        return espaco;
    }
    public Exemplar espaco(Espaco espaco) {
        this.espaco = espaco;
        return this;
    }
    public void setEspaco(Espaco espaco) {
        this.espaco = espaco;
    }
    /*----------------------------------------------------------*/

    /*---------------------Enviar chave Estrangeira para Emprestimo---------------------------*/
    public Set<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
    public Exemplar emprestimos(Set<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
        return this;
    }
    public Exemplar addEmprestimo(Emprestimo emprestimo) {
        this.emprestimos.add(emprestimo);
        emprestimo.setExemplar(this);
        return this;
    }
    public Exemplar removeEmprestimo(Emprestimo emprestimo) {
        this.emprestimos.remove(emprestimo);
        emprestimo.setExemplar(null);
        return this;
    }
    public void setEmprestimos(Set<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }
    /*---------------------------------------------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Exemplar exemplar = (Exemplar) o;
        if (exemplar.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), exemplar.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Espaco{" +
            "id=" + getId() +
            ", rfExemplar='" + getRfExemplar() + "'" +
            ", brExemplar='" + getBrExemplar() + "'" +
            ", qrExemplar='" + getQrExemplar() + "'" +
            ", nfExemplar='" + getNfExemplar() + "'" +
            ", status='" + getStatus() + "'" +
            ", nome='" + getNome() + "'" +
            ", nomeLocalizacao='" + getNomeLocalizacao() + "'" +
            "}";
    }
}
