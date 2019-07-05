package com.minea.sisas.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "emprestimo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Emprestimo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "data_prev")
    private LocalDate dataPrev;

    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    @Column(name = "data_emprestimo")
    private LocalDate dataEmprestimo;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(name = "multa")
    private Integer multa;

    @Column(name = "pago")
    private Boolean pago;

    @Column(name = "user_name")
    private String userName;

    @ManyToOne(optional = false)
    @NotNull
    private User user;

    @ManyToOne(optional = false)
    @NotNull
    private Exemplar exemplar;

    /*---------------ID-----------------------*/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    /*----------------------------------------*/

    /*--------------------------Status---------------------------*/
    public Boolean getStatus() {
        return status;
    }
    public Emprestimo status(Boolean status) {
        this.status = status;
        return this;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    /*-----------------------------------------------------------*/

    /*---------------------Data Devolução Prev-----------------------------*/
    public LocalDate getDataPrev() {
        return dataPrev;
    }
    public Emprestimo getDataPrev(LocalDate dataPrev) {
        this.dataPrev = dataPrev;
        return this;
    }
    public void setDataPrev(LocalDate dataPrev) {
        this.dataPrev = dataPrev;
    }
    /*---------------------------------------------------------------------*/

    /*---------------------------------------Data Empréstimo---------------------------------------*/
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    public Emprestimo dataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
        return this;
    }
    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    /*---------------------------------------------------------------------------------------------*/

    /*-------------------------------------Data Pagamento--------------------------------------*/
    public LocalDate getDataPagamento() {
        return dataPagamento;
    }
    public Emprestimo dataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
        return this;
    }
    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    /*-----------------------------------------------------------------------------------------*/

    /*-------------------------------------Data Devolução--------------------------------------*/
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
    public Emprestimo dataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
        return this;
    }
    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    /*-----------------------------------------------------------------------------------------*/

    /*---------------Valor Emprestimo Multa------------------*/
    public Integer getMulta() {
        return multa;
    }
    public Emprestimo multa(Integer multa) {
        this.multa = multa;
        return this;
    }
    public void setMulta(Integer multa) {
        this.multa = multa;
    }
    /*-------------------------------------------------------*/

    /*----------------Pagamento Multa--------------------*/
    public Boolean getPago() {
        return pago;
    }
    public Emprestimo pago(Boolean pago) {
        this.pago = pago;
        return this;
    }
    public void setPago(Boolean pago) {
        this.pago = pago;
    }
    /*---------------------------------------------------*/

    /*---------------User Name------------------*/
    public String getUserName() {
        return userName;
    }
    public Emprestimo userName(String userName) {
        this.userName = userName;
        return this;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /*-------------------------------------------------------*/

    /*--------Receber chave estrangeira User----------*/
    public User getUser() {
        return user;
    }
    public Emprestimo user(User user) {
        this.user = user;
        return this;
    }
    public void setUser(User user) {
        this.user = user;
    }
    /*------------------------------------------------*/

    /*--------------Receber chave estrangeira Exemplar--------------------*/
    public Exemplar getExemplar() {
        return exemplar;
    }
    public Emprestimo exemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
        return this;
    }
    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }
    /*--------------------------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Emprestimo emprestimo = (Emprestimo) o;
        if (emprestimo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), emprestimo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", dataPrev='" + getDataPrev() + "'" +
            ", dataDevolucao='" + getDataDevolucao() + "'" +
            ", dataEmprestimo='" + getDataEmprestimo() + "'" +
            ", dataPagamento='" + getDataPagamento() + "'" +
            ", multa='" + getMulta() + "'" +
            ", pago='" + getPago() + "'" +
            ", userName='" + getUserName() + "'" +
            "}";
    }

}
