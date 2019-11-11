package com.minea.sisas.service.dto;


import com.minea.sisas.domain.Comuna;
import com.minea.sisas.domain.Municipio;
import com.minea.sisas.domain.Provincia;
import com.minea.sisas.domain.Situacao;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the SistemaAgua entity.
 */
public class SistemaAguaDTO implements Serializable {

    private Long id;

    private Long idUsuario;

    @NotNull
    @Size(max = 150)
    private String nmInqueridor;

    @NotNull
    private LocalDate dtLancamento;

    private LocalDate dtUltimaAlteracao;

    @Size(max = 150)
    private String nmLocalidade;

    private Long qtdPopulacaoActual;

    private Long qtdCasasLocalidade;

    @Size(max = 20)
    private String nmTpComunaAldeia;

    @Size(max = 11)
    private String nmTpArea;

    private Long possuiSistemaAgua;

    @Size(max = 150)
    private String nmSistemaAgua;

    @Size(max = 150)
    private String nmFonteAgua;

    @Size(max = 60)
    private String latitude;

    @Size(max = 60)
    private String longitude;

    @Size(max = 60)
    private String altitude;

    @Size(max = 20)
    private String nmTpFonte;


    @Size(max = 20)
    private String nmFonteAguaUtilizada;

    @Size(max = 11)
    private String nmTipoBomba;

    private Long qtdCasasAguaLigada;

    private Long qtdChafarisesFuncionando;

    private Long qtdContadoresLigados;

    private Long qtdBebedouros;

    private Long qtdHabitantesAcessoServicoAgua;

    private Long anoConstrucaoSistema;

    @Size(max = 50)
    private String nmTpAvariaSistema;

    @Size(max = 100)
    private String causaAvariaSistema;

    @Size(max = 100)
    private String statusResolucao;

    @Size(max = 100)
    private String tempoServicoDisponivel;

    private BigDecimal qtdDiametroCondutaAdutoraAguaBruta;

    private BigDecimal qtdComprimentoCondutaAdutoraAguaBruta;

    private BigDecimal qtdDiametroCondutaAdutoraAguaTratada;

    private BigDecimal qtdComprimentoCondutaAdutoraAguaTratada;

    @Size(max = 150)
    private String descMaterialUtilizadoCondutas;

    private Long qtdReservatoriosApoiados;

    private Long qtdCapacidadeReservatoriosApoiados;

    private Long qtdReservatoriosElevados;

    private Long qtdCapacidadeReservatoriosElevados;

    private BigDecimal alturaReservatoriosElevados;

    @Size(max = 50)
    private String nmTpTratamentoAgua;

    @Size(max = 50)
    private String nmTpTratamentoPadraoUtilizado;

    @Size(max = 50)
    private String nmTpTratamentoBasicoUtilizado;

    @Size(max = 50)
    private String existeAvariaSistemaTratamento;

    @Size(max = 50)
    private String existeMotivoAusenciaTratamento;

    @Size(max = 50)
    private String nmEquipamentosComAvaria;

    private Long caudalDoSistema;

    private BigDecimal qtdConsumoPercaptaLitrosHomemDia;

    private BigDecimal qtdDotacaoPercapta;

    private BigDecimal qtdDiariaHorasServicoSistema;

    @Size(max = 50)
    private String esquema;

    @Size(max = 50)
    private String nmModeloBombaManualUtilizada;

    @Size(max = 50)
    private String nmTpBombaEnergia;

    @NotNull
    private Situacao situacao;

    @NotNull
    private Comuna comuna;

    @NotNull
    private Provincia provincia;

    @NotNull
    private Municipio municipio;

    private Integer qtdChafarisesExistentes;
    private String descMaterialUtilizadoCondutasObs;
    private String nmTpTratamentoPadraoUtilizadoObs;
    private String estadoFuncionamentoSistema;
    private String nmTpTratamentoBasicoUtilizadoObs;
    private String existeMotivoAusenciaTratamentoObs;
    private String nmEquipamentosComAvariaObs;
    private String nmModeloBombaManualUtilizadaObs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNmInqueridor() {
        return nmInqueridor;
    }

    public void setNmInqueridor(String nmInqueridor) {
        this.nmInqueridor = nmInqueridor;
    }

    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public LocalDate getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }

    public void setDtUltimaAlteracao(LocalDate dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }

    public String getNmLocalidade() {
        return nmLocalidade;
    }

    public void setNmLocalidade(String nmLocalidade) {
        this.nmLocalidade = nmLocalidade;
    }

    public Long getQtdPopulacaoActual() {
        return qtdPopulacaoActual;
    }

    public void setQtdPopulacaoActual(Long qtdPopulacaoActual) {
        this.qtdPopulacaoActual = qtdPopulacaoActual;
    }

    public Long getQtdCasasLocalidade() {
        return qtdCasasLocalidade;
    }

    public void setQtdCasasLocalidade(Long qtdCasasLocalidade) {
        this.qtdCasasLocalidade = qtdCasasLocalidade;
    }

    public String getNmTpComunaAldeia() {
        return nmTpComunaAldeia;
    }

    public void setNmTpComunaAldeia(String nmTpComunaAldeia) {
        this.nmTpComunaAldeia = nmTpComunaAldeia;
    }

    public String getNmTpArea() {
        return nmTpArea;
    }

    public void setNmTpArea(String nmTpArea) {
        this.nmTpArea = nmTpArea;
    }

    public Long getPossuiSistemaAgua() {
        return possuiSistemaAgua;
    }

    public void setPossuiSistemaAgua(Long possuiSistemaAgua) {
        this.possuiSistemaAgua = possuiSistemaAgua;
    }

    public String getNmSistemaAgua() {
        return nmSistemaAgua;
    }

    public void setNmSistemaAgua(String nmSistemaAgua) {
        this.nmSistemaAgua = nmSistemaAgua;
    }

    public String getNmFonteAgua() {
        return nmFonteAgua;
    }

    public void setNmFonteAgua(String nmFonteAgua) {
        this.nmFonteAgua = nmFonteAgua;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getNmTpFonte() {
        return nmTpFonte;
    }

    public void setNmTpFonte(String nmTpFonte) {
        this.nmTpFonte = nmTpFonte;
    }

    public String getNmFonteAguaUtilizada() {
        return nmFonteAguaUtilizada;
    }

    public void setNmFonteAguaUtilizada(String nmFonteAguaUtilizada) {
        this.nmFonteAguaUtilizada = nmFonteAguaUtilizada;
    }

    public String getNmTipoBomba() {
        return nmTipoBomba;
    }

    public void setNmTipoBomba(String nmTipoBomba) {
        this.nmTipoBomba = nmTipoBomba;
    }

    public Long getQtdCasasAguaLigada() {
        return qtdCasasAguaLigada;
    }

    public void setQtdCasasAguaLigada(Long qtdCasasAguaLigada) {
        this.qtdCasasAguaLigada = qtdCasasAguaLigada;
    }

    public Long getQtdChafarisesFuncionando() {
        return qtdChafarisesFuncionando;
    }

    public void setQtdChafarisesFuncionando(Long qtdChafarisesFuncionando) {
        this.qtdChafarisesFuncionando = qtdChafarisesFuncionando;
    }

    public Long getQtdContadoresLigados() {
        return qtdContadoresLigados;
    }

    public void setQtdContadoresLigados(Long qtdContadoresLigados) {
        this.qtdContadoresLigados = qtdContadoresLigados;
    }

    public Long getQtdBebedouros() {
        return qtdBebedouros;
    }

    public void setQtdBebedouros(Long qtdBebedouros) {
        this.qtdBebedouros = qtdBebedouros;
    }

    public Long getQtdHabitantesAcessoServicoAgua() {
        return qtdHabitantesAcessoServicoAgua;
    }

    public void setQtdHabitantesAcessoServicoAgua(Long qtdHabitantesAcessoServicoAgua) {
        this.qtdHabitantesAcessoServicoAgua = qtdHabitantesAcessoServicoAgua;
    }

    public Long getAnoConstrucaoSistema() {
        return anoConstrucaoSistema;
    }

    public void setAnoConstrucaoSistema(Long anoConstrucaoSistema) {
        this.anoConstrucaoSistema = anoConstrucaoSistema;
    }

    public String getNmTpAvariaSistema() {
        return nmTpAvariaSistema;
    }

    public void setNmTpAvariaSistema(String nmTpAvariaSistema) {
        this.nmTpAvariaSistema = nmTpAvariaSistema;
    }

    public String getCausaAvariaSistema() {
        return causaAvariaSistema;
    }

    public void setCausaAvariaSistema(String causaAvariaSistema) {
        this.causaAvariaSistema = causaAvariaSistema;
    }

    public String getStatusResolucao() {
        return statusResolucao;
    }

    public void setStatusResolucao(String statusResolucao) {
        this.statusResolucao = statusResolucao;
    }

    public String getTempoServicoDisponivel() {
        return tempoServicoDisponivel;
    }

    public void setTempoServicoDisponivel(String tempoServicoDisponivel) {
        this.tempoServicoDisponivel = tempoServicoDisponivel;
    }

    public BigDecimal getQtdDiametroCondutaAdutoraAguaBruta() {
        return qtdDiametroCondutaAdutoraAguaBruta;
    }

    public void setQtdDiametroCondutaAdutoraAguaBruta(BigDecimal qtdDiametroCondutaAdutoraAguaBruta) {
        this.qtdDiametroCondutaAdutoraAguaBruta = qtdDiametroCondutaAdutoraAguaBruta;
    }

    public BigDecimal getQtdComprimentoCondutaAdutoraAguaBruta() {
        return qtdComprimentoCondutaAdutoraAguaBruta;
    }

    public void setQtdComprimentoCondutaAdutoraAguaBruta(BigDecimal qtdComprimentoCondutaAdutoraAguaBruta) {
        this.qtdComprimentoCondutaAdutoraAguaBruta = qtdComprimentoCondutaAdutoraAguaBruta;
    }

    public BigDecimal getQtdDiametroCondutaAdutoraAguaTratada() {
        return qtdDiametroCondutaAdutoraAguaTratada;
    }

    public void setQtdDiametroCondutaAdutoraAguaTratada(BigDecimal qtdDiametroCondutaAdutoraAguaTratada) {
        this.qtdDiametroCondutaAdutoraAguaTratada = qtdDiametroCondutaAdutoraAguaTratada;
    }

    public BigDecimal getQtdComprimentoCondutaAdutoraAguaTratada() {
        return qtdComprimentoCondutaAdutoraAguaTratada;
    }

    public void setQtdComprimentoCondutaAdutoraAguaTratada(BigDecimal qtdComprimentoCondutaAdutoraAguaTratada) {
        this.qtdComprimentoCondutaAdutoraAguaTratada = qtdComprimentoCondutaAdutoraAguaTratada;
    }

    public String getDescMaterialUtilizadoCondutas() {
        return descMaterialUtilizadoCondutas;
    }

    public void setDescMaterialUtilizadoCondutas(String descMaterialUtilizadoCondutas) {
        this.descMaterialUtilizadoCondutas = descMaterialUtilizadoCondutas;
    }

    public Long getQtdReservatoriosApoiados() {
        return qtdReservatoriosApoiados;
    }

    public void setQtdReservatoriosApoiados(Long qtdReservatoriosApoiados) {
        this.qtdReservatoriosApoiados = qtdReservatoriosApoiados;
    }

    public Long getQtdCapacidadeReservatoriosApoiados() {
        return qtdCapacidadeReservatoriosApoiados;
    }

    public void setQtdCapacidadeReservatoriosApoiados(Long qtdCapacidadeReservatoriosApoiados) {
        this.qtdCapacidadeReservatoriosApoiados = qtdCapacidadeReservatoriosApoiados;
    }

    public Long getQtdReservatoriosElevados() {
        return qtdReservatoriosElevados;
    }

    public void setQtdReservatoriosElevados(Long qtdReservatoriosElevados) {
        this.qtdReservatoriosElevados = qtdReservatoriosElevados;
    }

    public Long getQtdCapacidadeReservatoriosElevados() {
        return qtdCapacidadeReservatoriosElevados;
    }

    public void setQtdCapacidadeReservatoriosElevados(Long qtdCapacidadeReservatoriosElevados) {
        this.qtdCapacidadeReservatoriosElevados = qtdCapacidadeReservatoriosElevados;
    }

    public BigDecimal getAlturaReservatoriosElevados() {
        return alturaReservatoriosElevados;
    }

    public void setAlturaReservatoriosElevados(BigDecimal alturaReservatoriosElevados) {
        this.alturaReservatoriosElevados = alturaReservatoriosElevados;
    }

    public String getNmTpTratamentoAgua() {
        return nmTpTratamentoAgua;
    }

    public void setNmTpTratamentoAgua(String nmTpTratamentoAgua) {
        this.nmTpTratamentoAgua = nmTpTratamentoAgua;
    }

    public String getNmTpTratamentoPadraoUtilizado() {
        return nmTpTratamentoPadraoUtilizado;
    }

    public void setNmTpTratamentoPadraoUtilizado(String nmTpTratamentoPadraoUtilizado) {
        this.nmTpTratamentoPadraoUtilizado = nmTpTratamentoPadraoUtilizado;
    }

    public String getNmTpTratamentoBasicoUtilizado() {
        return nmTpTratamentoBasicoUtilizado;
    }

    public void setNmTpTratamentoBasicoUtilizado(String nmTpTratamentoBasicoUtilizado) {
        this.nmTpTratamentoBasicoUtilizado = nmTpTratamentoBasicoUtilizado;
    }

    public String getExisteAvariaSistemaTratamento() {
        return existeAvariaSistemaTratamento;
    }

    public void setExisteAvariaSistemaTratamento(String existeAvariaSistemaTratamento) {
        this.existeAvariaSistemaTratamento = existeAvariaSistemaTratamento;
    }

    public String getExisteMotivoAusenciaTratamento() {
        return existeMotivoAusenciaTratamento;
    }

    public void setExisteMotivoAusenciaTratamento(String existeMotivoAusenciaTratamento) {
        this.existeMotivoAusenciaTratamento = existeMotivoAusenciaTratamento;
    }

    public String getNmEquipamentosComAvaria() {
        return nmEquipamentosComAvaria;
    }

    public void setNmEquipamentosComAvaria(String nmEquipamentosComAvaria) {
        this.nmEquipamentosComAvaria = nmEquipamentosComAvaria;
    }

    public Long getCaudalDoSistema() {
        return caudalDoSistema;
    }

    public void setCaudalDoSistema(Long caudalDoSistema) {
        this.caudalDoSistema = caudalDoSistema;
    }

    public BigDecimal getQtdConsumoPercaptaLitrosHomemDia() {
        return qtdConsumoPercaptaLitrosHomemDia;
    }

    public void setQtdConsumoPercaptaLitrosHomemDia(BigDecimal qtdConsumoPercaptaLitrosHomemDia) {
        this.qtdConsumoPercaptaLitrosHomemDia = qtdConsumoPercaptaLitrosHomemDia;
    }

    public BigDecimal getQtdDotacaoPercapta() {
        return qtdDotacaoPercapta;
    }

    public void setQtdDotacaoPercapta(BigDecimal qtdDotacaoPercapta) {
        this.qtdDotacaoPercapta = qtdDotacaoPercapta;
    }

    public BigDecimal getQtdDiariaHorasServicoSistema() {
        return qtdDiariaHorasServicoSistema;
    }

    public void setQtdDiariaHorasServicoSistema(BigDecimal qtdDiariaHorasServicoSistema) {
        this.qtdDiariaHorasServicoSistema = qtdDiariaHorasServicoSistema;
    }

    public String getEsquema() {
        return esquema;
    }

    public void setEsquema(String esquema) {
        this.esquema = esquema;
    }

    public String getNmModeloBombaManualUtilizada() {
        return nmModeloBombaManualUtilizada;
    }

    public void setNmModeloBombaManualUtilizada(String nmModeloBombaManualUtilizada) {
        this.nmModeloBombaManualUtilizada = nmModeloBombaManualUtilizada;
    }

    public String getNmTpBombaEnergia() {
        return nmTpBombaEnergia;
    }

    public void setNmTpBombaEnergia(String nmTpBombaEnergia) {
        this.nmTpBombaEnergia = nmTpBombaEnergia;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Integer getQtdChafarisesExistentes() {
        return qtdChafarisesExistentes;
    }

    public void setQtdChafarisesExistentes(Integer qtdChafarisesExistentes) {
        this.qtdChafarisesExistentes = qtdChafarisesExistentes;
    }

    public String getDescMaterialUtilizadoCondutasObs() {
        return descMaterialUtilizadoCondutasObs;
    }

    public void setDescMaterialUtilizadoCondutasObs(String descMaterialUtilizadoCondutasObs) {
        this.descMaterialUtilizadoCondutasObs = descMaterialUtilizadoCondutasObs;
    }

    public String getNmTpTratamentoPadraoUtilizadoObs() {
        return nmTpTratamentoPadraoUtilizadoObs;
    }

    public void setNmTpTratamentoPadraoUtilizadoObs(String nmTpTratamentoPadraoUtilizadoObs) {
        this.nmTpTratamentoPadraoUtilizadoObs = nmTpTratamentoPadraoUtilizadoObs;
    }

    public String getEstadoFuncionamentoSistema() {
        return estadoFuncionamentoSistema;
    }

    public void setEstadoFuncionamentoSistema(String estadoFuncionamentoSistema) {
        this.estadoFuncionamentoSistema = estadoFuncionamentoSistema;
    }

    public String getNmTpTratamentoBasicoUtilizadoObs() {
        return nmTpTratamentoBasicoUtilizadoObs;
    }

    public void setNmTpTratamentoBasicoUtilizadoObs(String nmTpTratamentoBasicoUtilizadoObs) {
        this.nmTpTratamentoBasicoUtilizadoObs = nmTpTratamentoBasicoUtilizadoObs;
    }

    public String getExisteMotivoAusenciaTratamentoObs() {
        return existeMotivoAusenciaTratamentoObs;
    }

    public void setExisteMotivoAusenciaTratamentoObs(String existeMotivoAusenciaTratamentoObs) {
        this.existeMotivoAusenciaTratamentoObs = existeMotivoAusenciaTratamentoObs;
    }

    public String getNmEquipamentosComAvariaObs() {
        return nmEquipamentosComAvariaObs;
    }

    public void setNmEquipamentosComAvariaObs(String nmEquipamentosComAvariaObs) {
        this.nmEquipamentosComAvariaObs = nmEquipamentosComAvariaObs;
    }

    public String getNmModeloBombaManualUtilizadaObs() {
        return nmModeloBombaManualUtilizadaObs;
    }

    public void setNmModeloBombaManualUtilizadaObs(String nmModeloBombaManualUtilizadaObs) {
        this.nmModeloBombaManualUtilizadaObs = nmModeloBombaManualUtilizadaObs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SistemaAguaDTO sistemaAguaDTO = (SistemaAguaDTO) o;
        if(sistemaAguaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sistemaAguaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SistemaAguaDTO{" +
            "id=" + getId() +
            ", idUsuario=" + getIdUsuario() +
            ", nmInqueridor='" + getNmInqueridor() + "'" +
            ", dtLancamento='" + getDtLancamento() + "'" +
            ", dtUltimaAlteracao='" + getDtUltimaAlteracao() + "'" +
            ", nmLocalidade='" + getNmLocalidade() + "'" +
            ", qtdPopulacaoActual=" + getQtdPopulacaoActual() +
            ", qtdCasasLocalidade=" + getQtdCasasLocalidade() +
            ", nmTpComunaAldeia='" + getNmTpComunaAldeia() + "'" +
            ", nmTpArea='" + getNmTpArea() + "'" +
            ", possuiSistemaAgua=" + getPossuiSistemaAgua() +
            ", nmSistemaAgua='" + getNmSistemaAgua() + "'" +
            ", nmFonteAgua='" + getNmFonteAgua() + "'" +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", altitude=" + getAltitude() +
            ", nmTpFonte='" + getNmTpFonte() + "'" +
            ", nmFonteAguaUtilizada='" + getNmFonteAguaUtilizada() + "'" +
            ", nmTipoBomba='" + getNmTipoBomba() + "'" +
            ", qtdCasasAguaLigada=" + getQtdCasasAguaLigada() +
            ", qtdChafarisesFuncionando=" + getQtdChafarisesFuncionando() +
            ", qtdContadoresLigados=" + getQtdContadoresLigados() +
            ", qtdBebedouros=" + getQtdBebedouros() +
            ", qtdHabitantesAcessoServicoAgua=" + getQtdHabitantesAcessoServicoAgua() +
            ", anoConstrucaoSistema=" + getAnoConstrucaoSistema() +
            ", nmTpAvariaSistema='" + getNmTpAvariaSistema() + "'" +
            ", causaAvariaSistema='" + getCausaAvariaSistema() + "'" +
            ", statusResolucao='" + getStatusResolucao() + "'" +
            ", tempoServicoDisponivel='" + getTempoServicoDisponivel() + "'" +
            ", qtdDiametroCondutaAdutoraAguaBruta=" + getQtdDiametroCondutaAdutoraAguaBruta() +
            ", qtdComprimentoCondutaAdutoraAguaBruta=" + getQtdComprimentoCondutaAdutoraAguaBruta() +
            ", qtdDiametroCondutaAdutoraAguaTratada=" + getQtdDiametroCondutaAdutoraAguaTratada() +
            ", qtdComprimentoCondutaAdutoraAguaTratada=" + getQtdComprimentoCondutaAdutoraAguaTratada() +
            ", descMaterialUtilizadoCondutas='" + getDescMaterialUtilizadoCondutas() + "'" +
            ", qtdReservatoriosApoiados=" + getQtdReservatoriosApoiados() +
            ", qtdCapacidadeReservatoriosApoiados=" + getQtdCapacidadeReservatoriosApoiados() +
            ", qtdReservatoriosElevados=" + getQtdReservatoriosElevados() +
            ", qtdCapacidadeReservatoriosElevados=" + getQtdCapacidadeReservatoriosElevados() +
            ", alturaReservatoriosElevados=" + getAlturaReservatoriosElevados() +
            ", nmTpTratamentoAgua='" + getNmTpTratamentoAgua() + "'" +
            ", nmTpTratamentoPadraoUtilizado='" + getNmTpTratamentoPadraoUtilizado() + "'" +
            ", nmTpTratamentoBasicoUtilizado='" + getNmTpTratamentoBasicoUtilizado() + "'" +
            ", existeAvariaSistemaTratamento='" + getExisteAvariaSistemaTratamento() + "'" +
            ", existeMotivoAusenciaTratamento='" + getExisteMotivoAusenciaTratamento() + "'" +
            ", nmEquipamentosComAvaria='" + getNmEquipamentosComAvaria() + "'" +
            ", caudalDoSistema=" + getCaudalDoSistema() +
            ", qtdConsumoPercaptaLitrosHomemDia=" + getQtdConsumoPercaptaLitrosHomemDia() +
            ", qtdDotacaoPercapta=" + getQtdDotacaoPercapta() +
            ", qtdDiariaHorasServicoSistema=" + getQtdDiariaHorasServicoSistema() +
            ", esquema='" + getEsquema() + "'" +
            ", nmModeloBombaManualUtilizada='" + getNmModeloBombaManualUtilizada() + "'" +
            ", nmTpBombaEnergia='" + getNmTpBombaEnergia() + "'" +
            "}";
    }
}
