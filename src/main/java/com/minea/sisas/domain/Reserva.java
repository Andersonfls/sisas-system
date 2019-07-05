package com.minea.sisas.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "reserva")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "data_reserva")
    private LocalDate dataReserva;

    @Column(name = "user_name")
    private String nomeUsuario;

    @Column(name = "obra_name")
    private String nomeObra;

    @Column(name = "exemplar_id")
    private Long exemplarId;

    @ManyToOne(optional = false)
    @NotNull
    private User user;

    @ManyToOne(optional = false)
    @NotNull
    private Obra obra;

    /*------------------ID--------------------*/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    /*----------------------------------------*/

    /*-------------------------Status----------------------------*/
    public Boolean getStatus() {
        return status;
    }
    public Reserva status(Boolean status) {
        this.status = status;
        return this;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    /*-----------------------------------------------------------*/

    /*--------------------------------Data Reserva-------------------------------------*/
    public LocalDate getDataReserva() {
        return dataReserva;
    }
    public Reserva dataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
        return this;
    }
    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }
    /*---------------------------------------------------------------------------------*/

    /*-------------------------------User Name------------------------------*/
    public String getNomeUsuario() {
        return nomeUsuario;
    }
    public Reserva nomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
        return this;
    }
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    /*----------------------------------------------------------------------*/

    /*-------------------------Obra Name------------------------------------*/
    public String getNomeObra() {
        return nomeObra;
    }
    public Reserva nomeObra(String nomeObra) {
        this.nomeObra = nomeObra;
        return this;
    }
    public void setNomeObra(String nomeObra) {
        this.nomeObra = nomeObra;
    }
    /*----------------------------------------------------------------------*/

    /*-------------------------Exemplar ID------------------------------------*/
    public Long getExemplarId() {
        return exemplarId;
    }
    public Reserva exemplarId(Long exemplarId) {
        this.exemplarId = exemplarId;
        return this;
    }
    public void setExemplarId(Long exemplarId) {
        this.exemplarId = exemplarId;
    }
    /*----------------------------------------------------------------------*/

    /*--------Receber chave estrangeira User----------*/
    public User getUser() {
        return user;
    }
    public Reserva user(User user) {
        this.user = user;
        return this;
    }
    public void setUser(User user) {
        this.user = user;
    }
    /*------------------------------------------------*/

    /*--------Receber chave estrangeira Obra----------*/
    public Obra getObra() {
        return obra;
    }
    public Reserva obra(Obra obra) {
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
        Reserva reserva = (Reserva) o;
        if (reserva.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reserva.getId());
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
            ", nomeUsuario='" + getNomeUsuario() + "'" +
            ", nomeObra='" + getNomeObra() + "'" +
            ", dataReserva='" + getDataReserva() + "'" +
            ", exemplarId='" + getExemplarId() + "'" +
            "}";
    }
}
