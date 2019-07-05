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
 * Obra.
 */
@Entity
@Table(name = "obra")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Obra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "codigo_cutter")
    private String codigoCutter;

    @Column(name = "paginas")
    private Integer paginas;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "assunto")
    private String assunto;

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "cidade_publicacao")
    private String cidadePublicacao;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "obra")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AutorObra> autorObras = new HashSet<>();

    @OneToMany(mappedBy = "obra")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Exemplar> exemplares = new HashSet<>();

    @OneToMany(mappedBy = "obra")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Reserva> reservas = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    private TipoObra tipoObra;

    @ManyToOne(optional = false)
    @NotNull
    private Editora editora;

    @Column(name = "nome_arquivo")
    private String nomeArquivo;

    @Column(name = "nome_diretorio")
    private String nomeDiretorio;

    @Column(name = "cdd_codigo")
    private String cddCodigo;

    @Column(name = "cdd_descricao")
    private String cddDescricao;

    @Column(name = "cdu_codigo")
    private String cduCodigo;

    @Column(name = "cdu_descricao")
    private String cduDescricao;

    public Obra() {
    }

    public Obra(Long id, String nome, String descricao, Boolean status, TipoObra tipoObra) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
        this.tipoObra = tipoObra;
    }

    /*-----------------ID---------------------*/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    /*----------------------------------------*/

    /*-----------------------Nome-----------------------*/
    public String getNome() {
        return nome;
    }
    public Obra nome(String nome) {
        this.nome = nome;
        return this;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    /*--------------------------------------------------*/

    /*------------------------------Descricao------------------------------*/
    public String getDescricao() {
        return descricao;
    }
    public Obra descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    /*----------------------------------------------------------------------*/

    /*------------------------------NomeDiretorio------------------------------*/
    public String getNomeDiretorio() {
        return nomeDiretorio;
    }
    public Obra nomeDiretorio(String nomeDiretorio) {
        this.nomeDiretorio = nomeDiretorio;
        return this;
    }
    public void setNomeDiretorio(String nomeDiretorio) {
        this.nomeDiretorio = nomeDiretorio;
    }
    /*----------------------------------------------------------------------*/

    /*------------------------------NomeArquivo------------------------------*/
    public String getNomeArquivo() {
        return nomeArquivo;
    }
    public Obra nomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        return this;
    }
    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
    /*----------------------------------------------------------------------*/

    /*------------------------------------CodigoCutter----------------------------------*/
    public String getCodigoCutter() {
        return codigoCutter;
    }
    public Obra codigoCutter(String codigoCutter) {
        this.codigoCutter = codigoCutter;
        return this;
    }
    public void setCodigoCutter(String codigoCutter) {
        this.codigoCutter = codigoCutter;
    }
    /*----------------------------------------------------------------------------------*/

    /*----------------------------Paginas----------------------------*/
    public Integer getPaginas() {
        return paginas;
    }
    public Obra paginas(Integer paginas) {
        this.paginas = paginas;
        return this;
    }
    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }
    /*---------------------------------------------------------------*/

    /*-----------------------ISBN-----------------------*/
    public String getIsbn() {
        return isbn;
    }
    public Obra isbn(String isbn) {
        this.isbn = isbn;
        return this;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    /*--------------------------------------------------*/

    /*-----------------------Assunto-----------------------*/
    public String getAssunto() {
        return assunto;
    }
    public Obra assunto(String assunto) {
        this.assunto = assunto;
        return this;
    }
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    /*--------------------------------------------------*/

    /*--------------------Ano------------------------*/
    public Integer getAno() {
        return ano;
    }
    public Obra ano(Integer ano) {
        this.ano = ano;
        return this;
    }
    public void setAno(Integer ano) {
        this.ano = ano;
    }
    /*-----------------------------------------------*/

    /*-------------------------------------CidadePublicacao---------------------------------------------*/
    public String getCidadePublicacao() {
        return cidadePublicacao;
    }
    public Obra cidadePublicacao(String cidadePublicacao) {
        this.cidadePublicacao = cidadePublicacao;
        return this;
    }
    public void setCidadePublicacao(String cidadePublicacao) {
        this.cidadePublicacao = cidadePublicacao;
    }
    /*--------------------------------------------------------------------------------------------------*/

    /*-------------------------Status----------------------------*/
    public Boolean getStatus() {
        return status;
    }
    public Obra status(Boolean status) {
        this.status = status;
        return this;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    /*-----------------------------------------------------------*/

    /*------------------------------CDD_CODIGO------------------------------*/
    public String getCddCodigo() {
        return cddCodigo;
    }
    public Obra cddCodigo(String cddCodigo) {
        this.cddCodigo = cddCodigo;
        return this;
    }
    public void setCddCodigo(String cddCodigo) {
        this.cddCodigo = cddCodigo;
    }
    /*----------------------------------------------------------------------*/

    /*-----------------------------------CDD_DESCRICAO------------------------------------------*/
    public String getCddDescricao() {
        return cddDescricao;
    }
    public Obra cddDescricao(String cddDescricao) {
        this.cddDescricao = cddDescricao;
        return this;
    }
    public void setCddDescricao(String cddDescricao) {
        this.cddDescricao = cddDescricao;
    }
    /*------------------------------------------------------------------------------------------*/

    /*------------------------------CDU_CODIGO------------------------------*/
    public String getCduCodigo() {
        return cduCodigo;
    }
    public Obra cduCodigo(String cduCodigo) {
        this.cduCodigo = cduCodigo;
        return this;
    }
    public void setCduCodigo(String cduCodigo) {
        this.cduCodigo = cduCodigo;
    }
    /*----------------------------------------------------------------------*/

    /*-----------------------------------CDU_DESCRICAO------------------------------------------*/
    public String getCduDescricao() {
        return cduDescricao;
    }
    public Obra cduDescricao(String cduDescricao) {
        this.cduDescricao = cduDescricao;
        return this;
    }
    public void setCduDescricao(String cduDescricao) {
        this.cduDescricao = cduDescricao;
    }
    /*------------------------------------------------------------------------------------------*/

    /*----------------Receber chave estrangeira Tipo Obra-----------------*/
    public TipoObra getTipoObra() {
        return tipoObra;
    }
    public Obra tipoObra(TipoObra tipoObra) {
        this.tipoObra = tipoObra;
        return this;
    }
    public void setTipoObra(TipoObra tipoObra) {
        this.tipoObra = tipoObra;
    }
    /*--------------------------------------------------------------------*/

    /*----------------Receber chave estrangeira Editora-----------------*/
    public Editora getEditora() {
        return editora;
    }
    public Obra editora(Editora editora) {
        this.editora = editora;
        return this;
    }
    public void setEditora(Editora editora) {
        this.editora = editora;
    }
    /*--------------------------------------------------------------------*/

    /*-------------------Enviar chave Estrangeira para AutorObras-----------------------*/
    public Set<AutorObra> getAutorObras() {
        return autorObras;
    }
    public Obra autorObras(Set<AutorObra> autorObras) {
        this.autorObras = autorObras;
        return this;
    }
    public Obra addAutorObra(AutorObra autorObra) {
        this.autorObras.add(autorObra);
        autorObra.setObra(this);
        return this;
    }
    public Obra removeAutorObra(AutorObra autorObra) {
        this.autorObras.remove(autorObra);
        autorObra.setObra(null);
        return this;
    }
    public void setAutorObras(Set<AutorObra> autorObras) {
        this.autorObras = autorObras;
    }
    /*----------------------------------------------------------------------------------*/

    /*-------------------Enviar chave Estrangeira para Exemplar---------------0--------*/
    public Set<Exemplar> getExemplares() {
        return exemplares;
    }
    public Obra exemplares(Set<Exemplar> exemplares) {
        this.exemplares = exemplares;
        return this;
    }
    public Obra addExemplar(Exemplar exemplar) {
        this.exemplares.add(exemplar);
        exemplar.setObra(this);
        return this;
    }
    public Obra removeExemplar(Exemplar exemplar) {
        this.exemplares.remove(exemplar);
        exemplar.setObra(null);
        return this;
    }
    public void setExemplares(Set<Exemplar> exemplares) {
        this.exemplares = exemplares;
    }
    /*---------------------------------------------------------------------------------*/

    /*--------------Enviar chave Estrangeira para Reserva---------------------*/
    public Set<Reserva> getReservas() {
        return reservas;
    }
    public Obra reservas(Set<Reserva> reservas) {
        this.reservas = reservas;
        return this;
    }
    public Obra addExemplar(Reserva reserva) {
        this.reservas.add(reserva);
        reserva.setObra(this);
        return this;
    }
    public Obra removeReserva(Reserva reserva) {
        this.reservas.remove(reserva);
        reserva.setObra(null);
        return this;
    }
    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }
    /*------------------------------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Obra obra = (Obra) o;
        if (obra.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), obra.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Obra{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", nomeArquivo='" + getNomeArquivo() + "'" +
            ", nomeDiretorio='" + getNomeDiretorio() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", codigoCutter='" + getCodigoCutter() + "'" +
            ", paginas='" + getPaginas() + "'" +
            ", cddCodigo='" + getCddCodigo() + "'" +
            ", cddDescricao='" + getCddDescricao() + "'" +
            ", cduCodigo='" + getCduCodigo() + "'" +
            ", cduDescricao='" + getCduDescricao() + "'" +
            ", paginas='" + getPaginas() + "'" +
            ", isbn='" + getIsbn() +  "'" +
            ", assunto='" + getAssunto() + "'" +
            ", ano='" + getAno() + "'" +
            ", cidadePublicacao='" + getCidadePublicacao() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
