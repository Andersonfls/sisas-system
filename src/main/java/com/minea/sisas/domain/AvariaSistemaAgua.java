
package com.minea.sisas.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A SistemaAgua.
 */
@Entity
@Table(name = "avaria_sistema_agua")
public class AvariaSistemaAgua implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avaria_sistema_agua", nullable = false)
    private Long id;

    @NotNull
    @JoinColumn(name = "id_sistema_agua")
    private Long idSistemaAgua;

    @Size(max = 50)
    @Column(name = "nm_tp_avaria_sistema", length = 50)
    private String nmTpAvariaSistema;

    @Size(max = 100)
    @Column(name = "causa_avaria_sistema", length = 100)
    private String causaAvariaSistema;

    @Size(max = 100)
    @Column(name = "status_resolucao", length = 100)
    private String statusResolucao;

    @Size(max = 100)
    @Column(name = "tempo_servico_disponivel", length = 100)
    private String tempoServicoDisponivel;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmTpAvariaSistema() {
        return nmTpAvariaSistema;
    }

    public AvariaSistemaAgua nmTpAvariaSistema(String nmTpAvariaSistema) {
        this.nmTpAvariaSistema = nmTpAvariaSistema;
        return this;
    }

    public void setNmTpAvariaSistema(String nmTpAvariaSistema) {
        this.nmTpAvariaSistema = nmTpAvariaSistema;
    }

    public String getCausaAvariaSistema() {
        return causaAvariaSistema;
    }

    public AvariaSistemaAgua causaAvariaSistema(String causaAvariaSistema) {
        this.causaAvariaSistema = causaAvariaSistema;
        return this;
    }

    public void setCausaAvariaSistema(String causaAvariaSistema) {
        this.causaAvariaSistema = causaAvariaSistema;
    }

    public String getStatusResolucao() {
        return statusResolucao;
    }

    public AvariaSistemaAgua statusResolucao(String statusResolucao) {
        this.statusResolucao = statusResolucao;
        return this;
    }

    public void setStatusResolucao(String statusResolucao) {
        this.statusResolucao = statusResolucao;
    }

    public String getTempoServicoDisponivel() {
        return tempoServicoDisponivel;
    }

    public AvariaSistemaAgua tempoServicoDisponivel(String tempoServicoDisponivel) {
        this.tempoServicoDisponivel = tempoServicoDisponivel;
        return this;
    }

    public void setTempoServicoDisponivel(String tempoServicoDisponivel) {
        this.tempoServicoDisponivel = tempoServicoDisponivel;
    }

    public Long getIdSistemaAgua() {
        return idSistemaAgua;
    }

    public void setIdSistemaAgua(Long idSistemaAgua) {
        this.idSistemaAgua = idSistemaAgua;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AvariaSistemaAgua sistemaAgua = (AvariaSistemaAgua) o;
        if (sistemaAgua.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sistemaAgua.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
