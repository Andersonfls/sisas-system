
package com.minea.sisas.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A SistemaAgua.
 */
@Entity
@Table(name = "sistema_agua")
public class SistemaAgua implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sistema_agua", nullable = false)
    private Long id;

    @NotNull
    @JoinColumn(name = "sistema_agua_ibfk_1")
    private Long idUsuario;

    @NotNull
    @Size(max = 150)
    @Column(name = "nm_inqueridor", length = 150, nullable = false)
    private String nmInqueridor;

    @NotNull
    @Column(name = "dt_lancamento", nullable = false)
    private LocalDate dtLancamento;

    @Column(name = "dt_ultima_alteracao")
    private LocalDate dtUltimaAlteracao;

    @Size(max = 150)
    @Column(name = "nm_localidade", length = 150)
    private String nmLocalidade;

    @Column(name = "qtd_populacao_actual")
    private Long qtdPopulacaoActual;

    @Column(name = "qtd_casas_localidade")
    private Long qtdCasasLocalidade;

    @Size(max = 20)
    @Column(name = "nm_tp_comuna_aldeia", length = 20)
    private String nmTpComunaAldeia;

    @Size(max = 21)
    @Column(name = "nm_tp_area", length = 21)
    private String nmTpArea;

    @Column(name = "possui_sistema_agua")
    private Long possuiSistemaAgua;

    @Size(max = 150)
    @Column(name = "nm_sistema_agua", length = 150)
    private String nmSistemaAgua;

    @Size(max = 150)
    @Column(name = "nm_fonte_agua", length = 150)
    private String nmFonteAgua;


    @Size(max = 60)
    @Column(name = "latitude", length = 60)
    private String latitude;

    @Size(max = 60)
    @Column(name = "longitude", length = 60)
    private String longitude;

    @Size(max = 60)
    @Column(name = "altitude", length = 60)
    private String altitude;

    @Size(max = 20)
    @Column(name = "nm_tp_fonte", length = 20)
    private String nmTpFonte;

    @Size(max = 20)
    @Column(name = "nm_fonte_agua_utilizada", length = 20)
    private String nmFonteAguaUtilizada;

    @Size(max = 50)
    @Column(name = "nm_tipo_bomba", length = 50)
    private String nmTipoBomba;

    @Column(name = "qtd_casas_agua_ligada")
    private Long qtdCasasAguaLigada;

    @Column(name = "qtd_chafarises_funcionando")
    private Long qtdChafarisesFuncionando;

    //novo
    @Column(name = "qtd_chafarises_existentes")
    private Integer qtdChafarisesExistentes;

    @Column(name = "qtd_contadores_ligados")
    private Long qtdContadoresLigados;

    @Column(name = "qtd_bebedouros")
    private Long qtdBebedouros;

    @Column(name = "qtd_habitantes_acesso_servico_agua")
    private Long qtdHabitantesAcessoServicoAgua;

    @Column(name = "ano_construcao_sistema")
    private Long anoConstrucaoSistema;

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

    @Column(name = "qtd_diametro_conduta_adutora_agua_bruta", precision=10, scale=2)
    private BigDecimal qtdDiametroCondutaAdutoraAguaBruta;

    @Column(name = "qtd_comprimento_conduta_adutora_agua_bruta", precision=10, scale=2)
    private BigDecimal qtdComprimentoCondutaAdutoraAguaBruta;

    @Column(name = "qtd_diametro_conduta_adutora_agua_tratada", precision=10, scale=2)
    private BigDecimal qtdDiametroCondutaAdutoraAguaTratada;

    @Column(name = "qtd_comprimento_conduta_adutora_agua_tratada", precision=10, scale=2)
    private BigDecimal qtdComprimentoCondutaAdutoraAguaTratada;

    @Size(max = 150)
    @Column(name = "desc_material_utilizado_condutas", length = 150)
    private String descMaterialUtilizadoCondutas;

    //novo
    @Size(max = 150)
    @Column(name = "desc_material_utilizado_condutas_obs", length = 150)
    private String descMaterialUtilizadoCondutasObs;

    @Column(name = "qtd_reservatorios_apoiados")
    private Long qtdReservatoriosApoiados;

    @Column(name = "qtd_capacidade_reservatorios_apoiados")
    private Long qtdCapacidadeReservatoriosApoiados;

    @Column(name = "qtd_reservatorios_elevados")
    private Long qtdReservatoriosElevados;

    @Column(name = "qtd_capacidade_reservatorios_elevados")
    private Long qtdCapacidadeReservatoriosElevados;

    @Column(name = "altura_reservatorios_elevados", precision=10, scale=2)
    private BigDecimal alturaReservatoriosElevados;

    @Size(max = 50)
    @Column(name = "nm_tp_tratamento_agua", length = 50)
    private String nmTpTratamentoAgua;

    @Size(max = 50)
    @Column(name = "nm_tp_tratamento_padrao_utilizado", length = 50)
    private String nmTpTratamentoPadraoUtilizado;

    //novo
    @Size(max = 50)
    @Column(name = "nm_tp_tratamento_padrao_utilizado_obs", length = 50)
    private String nmTpTratamentoPadraoUtilizadoObs;

    //novo
    @Size(max = 50)
    @Column(name = "estado_funcionamento_sistema", length = 70)
    private String estadoFuncionamentoSistema;

    @Size(max = 50)
    @Column(name = "nm_tp_tratamento_basico_utilizado", length = 50)
    private String nmTpTratamentoBasicoUtilizado;

    //novo
    @Size(max = 50)
    @Column(name = "nm_tp_tratamento_basico_utilizado_obs", length = 50)
    private String nmTpTratamentoBasicoUtilizadoObs;

    @Size(max = 50)
    @Column(name = "existe_avaria_sistema_tratamento", length = 50)
    private String existeAvariaSistemaTratamento;

    @Size(max = 50)
    @Column(name = "existe_motivo_ausencia_tratamento", length = 50)
    private String existeMotivoAusenciaTratamento;

    //novo
    @Size(max = 50)
    @Column(name = "existe_motivo_ausencia_tratamento_obs", length = 50)
    private String existeMotivoAusenciaTratamentoObs;

    @Size(max = 50)
    @Column(name = "nm_equipamentos_com_avaria", length = 50)
    private String nmEquipamentosComAvaria;

    //NOVO
    @Size(max = 50)
    @Column(name = "nm_equipamentos_com_avaria_obs", length = 50)
    private String nmEquipamentosComAvariaObs;

    @Column(name = "caudal_do_sistema")
    private Long caudalDoSistema;

    @Column(name = "qtd_consumo_percapta_litros_homem_dia", precision=10, scale=2)
    private BigDecimal qtdConsumoPercaptaLitrosHomemDia;

    @Column(name = "qtd_dotacao_percapta", precision=10, scale=2)
    private BigDecimal qtdDotacaoPercapta;

    @Column(name = "qtd_diaria_horas_servico_sistema", precision=10, scale=2)
    private BigDecimal qtdDiariaHorasServicoSistema;

    @Size(max = 50)
    @Column(name = "esquema", length = 50)
    private String esquema;

    @Size(max = 50)
    @Column(name = "nm_modelo_bomba_manual_utilizada", length = 50)
    private String nmModeloBombaManualUtilizada;

    //novo
    @Size(max = 50)
    @Column(name = "nm_modelo_bomba_manual_utilizada_obs", length = 50)
    private String nmModeloBombaManualUtilizadaObs;

    @Size(max = 50)
    @Column(name = "nm_tp_bomba_energia", length = 50)
    private String nmTpBombaEnergia;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_situacao")
    private Situacao situacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_comuna")
    private Comuna comuna;

    @ManyToOne
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;

    @ManyToOne
    @JoinColumn(name = "id_municipio")
    private Municipio municipio;

    @Column(name = "motivo_avaria_sistema")
    private String motivoAvariaSistema;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public SistemaAgua idUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNmInqueridor() {
        return nmInqueridor;
    }

    public SistemaAgua nmInqueridor(String nmInqueridor) {
        this.nmInqueridor = nmInqueridor;
        return this;
    }

    public void setNmInqueridor(String nmInqueridor) {
        this.nmInqueridor = nmInqueridor;
    }

    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public SistemaAgua dtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
        return this;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public LocalDate getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }

    public SistemaAgua dtUltimaAlteracao(LocalDate dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
        return this;
    }

    public void setDtUltimaAlteracao(LocalDate dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }

    public String getNmLocalidade() {
        return nmLocalidade;
    }

    public SistemaAgua nmLocalidade(String nmLocalidade) {
        this.nmLocalidade = nmLocalidade;
        return this;
    }

    public void setNmLocalidade(String nmLocalidade) {
        this.nmLocalidade = nmLocalidade;
    }

    public Long getQtdPopulacaoActual() {
        return qtdPopulacaoActual;
    }

    public SistemaAgua qtdPopulacaoActual(Long qtdPopulacaoActual) {
        this.qtdPopulacaoActual = qtdPopulacaoActual;
        return this;
    }

    public void setQtdPopulacaoActual(Long qtdPopulacaoActual) {
        this.qtdPopulacaoActual = qtdPopulacaoActual;
    }

    public Long getQtdCasasLocalidade() {
        return qtdCasasLocalidade;
    }

    public SistemaAgua qtdCasasLocalidade(Long qtdCasasLocalidade) {
        this.qtdCasasLocalidade = qtdCasasLocalidade;
        return this;
    }

    public void setQtdCasasLocalidade(Long qtdCasasLocalidade) {
        this.qtdCasasLocalidade = qtdCasasLocalidade;
    }

    public String getNmTpComunaAldeia() {
        return nmTpComunaAldeia;
    }

    public SistemaAgua nmTpComunaAldeia(String nmTpComunaAldeia) {
        this.nmTpComunaAldeia = nmTpComunaAldeia;
        return this;
    }

    public void setNmTpComunaAldeia(String nmTpComunaAldeia) {
        this.nmTpComunaAldeia = nmTpComunaAldeia;
    }

    public String getNmTpArea() {
        return nmTpArea;
    }

    public SistemaAgua nmTpArea(String nmTpArea) {
        this.nmTpArea = nmTpArea;
        return this;
    }

    public void setNmTpArea(String nmTpArea) {
        this.nmTpArea = nmTpArea;
    }

    public Long getPossuiSistemaAgua() {
        return possuiSistemaAgua;
    }

    public SistemaAgua possuiSistemaAgua(Long possuiSistemaAgua) {
        this.possuiSistemaAgua = possuiSistemaAgua;
        return this;
    }

    public void setPossuiSistemaAgua(Long possuiSistemaAgua) {
        this.possuiSistemaAgua = possuiSistemaAgua;
    }

    public String getNmSistemaAgua() {
        return nmSistemaAgua;
    }

    public SistemaAgua nmSistemaAgua(String nmSistemaAgua) {
        this.nmSistemaAgua = nmSistemaAgua;
        return this;
    }

    public void setNmSistemaAgua(String nmSistemaAgua) {
        this.nmSistemaAgua = nmSistemaAgua;
    }

    public String getNmFonteAgua() {
        return nmFonteAgua;
    }

    public SistemaAgua nmFonteAgua(String nmFonteAgua) {
        this.nmFonteAgua = nmFonteAgua;
        return this;
    }

    public void setNmFonteAgua(String nmFonteAgua) {
        this.nmFonteAgua = nmFonteAgua;
    }

    public String getLatitude() {
        return latitude;
    }

    public SistemaAgua latitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public SistemaAgua longitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public SistemaAgua altitude(String altitude) {
        this.altitude = altitude;
        return this;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getNmTpFonte() {
        return nmTpFonte;
    }

    public SistemaAgua nmTpFonte(String nmTpFonte) {
        this.nmTpFonte = nmTpFonte;
        return this;
    }

    public void setNmTpFonte(String nmTpFonte) {
        this.nmTpFonte = nmTpFonte;
    }

    public String getNmFonteAguaUtilizada() {
        return nmFonteAguaUtilizada;
    }

    public SistemaAgua nmFonteAguaUtilizada(String nmFonteAguaUtilizada) {
        this.nmFonteAguaUtilizada = nmFonteAguaUtilizada;
        return this;
    }

    public void setNmFonteAguaUtilizada(String nmFonteAguaUtilizada) {
        this.nmFonteAguaUtilizada = nmFonteAguaUtilizada;
    }

    public String getNmTipoBomba() {
        return nmTipoBomba;
    }

    public SistemaAgua nmTipoBomba(String nmTipoBomba) {
        this.nmTipoBomba = nmTipoBomba;
        return this;
    }

    public void setNmTipoBomba(String nmTipoBomba) {
        this.nmTipoBomba = nmTipoBomba;
    }

    public Long getQtdCasasAguaLigada() {
        return qtdCasasAguaLigada;
    }

    public SistemaAgua qtdCasasAguaLigada(Long qtdCasasAguaLigada) {
        this.qtdCasasAguaLigada = qtdCasasAguaLigada;
        return this;
    }

    public void setQtdCasasAguaLigada(Long qtdCasasAguaLigada) {
        this.qtdCasasAguaLigada = qtdCasasAguaLigada;
    }

    public Long getQtdChafarisesFuncionando() {
        return qtdChafarisesFuncionando;
    }

    public SistemaAgua qtdChafarisesFuncionando(Long qtdChafarisesFuncionando) {
        this.qtdChafarisesFuncionando = qtdChafarisesFuncionando;
        return this;
    }

    public void setQtdChafarisesFuncionando(Long qtdChafarisesFuncionando) {
        this.qtdChafarisesFuncionando = qtdChafarisesFuncionando;
    }

    public Long getQtdContadoresLigados() {
        return qtdContadoresLigados;
    }

    public SistemaAgua qtdContadoresLigados(Long qtdContadoresLigados) {
        this.qtdContadoresLigados = qtdContadoresLigados;
        return this;
    }

    public void setQtdContadoresLigados(Long qtdContadoresLigados) {
        this.qtdContadoresLigados = qtdContadoresLigados;
    }

    public Long getQtdBebedouros() {
        return qtdBebedouros;
    }

    public SistemaAgua qtdBebedouros(Long qtdBebedouros) {
        this.qtdBebedouros = qtdBebedouros;
        return this;
    }

    public void setQtdBebedouros(Long qtdBebedouros) {
        this.qtdBebedouros = qtdBebedouros;
    }

    public Long getQtdHabitantesAcessoServicoAgua() {
        return qtdHabitantesAcessoServicoAgua;
    }

    public SistemaAgua qtdHabitantesAcessoServicoAgua(Long qtdHabitantesAcessoServicoAgua) {
        this.qtdHabitantesAcessoServicoAgua = qtdHabitantesAcessoServicoAgua;
        return this;
    }

    public void setQtdHabitantesAcessoServicoAgua(Long qtdHabitantesAcessoServicoAgua) {
        this.qtdHabitantesAcessoServicoAgua = qtdHabitantesAcessoServicoAgua;
    }

    public Long getAnoConstrucaoSistema() {
        return anoConstrucaoSistema;
    }

    public SistemaAgua anoConstrucaoSistema(Long anoConstrucaoSistema) {
        this.anoConstrucaoSistema = anoConstrucaoSistema;
        return this;
    }

    public void setAnoConstrucaoSistema(Long anoConstrucaoSistema) {
        this.anoConstrucaoSistema = anoConstrucaoSistema;
    }

    public String getNmTpAvariaSistema() {
        return nmTpAvariaSistema;
    }

    public SistemaAgua nmTpAvariaSistema(String nmTpAvariaSistema) {
        this.nmTpAvariaSistema = nmTpAvariaSistema;
        return this;
    }

    public void setNmTpAvariaSistema(String nmTpAvariaSistema) {
        this.nmTpAvariaSistema = nmTpAvariaSistema;
    }

    public String getCausaAvariaSistema() {
        return causaAvariaSistema;
    }

    public SistemaAgua causaAvariaSistema(String causaAvariaSistema) {
        this.causaAvariaSistema = causaAvariaSistema;
        return this;
    }

    public void setCausaAvariaSistema(String causaAvariaSistema) {
        this.causaAvariaSistema = causaAvariaSistema;
    }

    public String getStatusResolucao() {
        return statusResolucao;
    }

    public SistemaAgua statusResolucao(String statusResolucao) {
        this.statusResolucao = statusResolucao;
        return this;
    }

    public void setStatusResolucao(String statusResolucao) {
        this.statusResolucao = statusResolucao;
    }

    public String getTempoServicoDisponivel() {
        return tempoServicoDisponivel;
    }

    public SistemaAgua tempoServicoDisponivel(String tempoServicoDisponivel) {
        this.tempoServicoDisponivel = tempoServicoDisponivel;
        return this;
    }

    public void setTempoServicoDisponivel(String tempoServicoDisponivel) {
        this.tempoServicoDisponivel = tempoServicoDisponivel;
    }

    public BigDecimal getQtdDiametroCondutaAdutoraAguaBruta() {
        return qtdDiametroCondutaAdutoraAguaBruta;
    }

    public SistemaAgua qtdDiametroCondutaAdutoraAguaBruta(BigDecimal qtdDiametroCondutaAdutoraAguaBruta) {
        this.qtdDiametroCondutaAdutoraAguaBruta = qtdDiametroCondutaAdutoraAguaBruta;
        return this;
    }

    public void setQtdDiametroCondutaAdutoraAguaBruta(BigDecimal qtdDiametroCondutaAdutoraAguaBruta) {
        this.qtdDiametroCondutaAdutoraAguaBruta = qtdDiametroCondutaAdutoraAguaBruta;
    }

    public BigDecimal getQtdComprimentoCondutaAdutoraAguaBruta() {
        return qtdComprimentoCondutaAdutoraAguaBruta;
    }

    public SistemaAgua qtdComprimentoCondutaAdutoraAguaBruta(BigDecimal qtdComprimentoCondutaAdutoraAguaBruta) {
        this.qtdComprimentoCondutaAdutoraAguaBruta = qtdComprimentoCondutaAdutoraAguaBruta;
        return this;
    }

    public void setQtdComprimentoCondutaAdutoraAguaBruta(BigDecimal qtdComprimentoCondutaAdutoraAguaBruta) {
        this.qtdComprimentoCondutaAdutoraAguaBruta = qtdComprimentoCondutaAdutoraAguaBruta;
    }

    public BigDecimal getQtdDiametroCondutaAdutoraAguaTratada() {
        return qtdDiametroCondutaAdutoraAguaTratada;
    }

    public SistemaAgua qtdDiametroCondutaAdutoraAguaTratada(BigDecimal qtdDiametroCondutaAdutoraAguaTratada) {
        this.qtdDiametroCondutaAdutoraAguaTratada = qtdDiametroCondutaAdutoraAguaTratada;
        return this;
    }

    public void setQtdDiametroCondutaAdutoraAguaTratada(BigDecimal qtdDiametroCondutaAdutoraAguaTratada) {
        this.qtdDiametroCondutaAdutoraAguaTratada = qtdDiametroCondutaAdutoraAguaTratada;
    }

    public BigDecimal getQtdComprimentoCondutaAdutoraAguaTratada() {
        return qtdComprimentoCondutaAdutoraAguaTratada;
    }

    public SistemaAgua qtdComprimentoCondutaAdutoraAguaTratada(BigDecimal qtdComprimentoCondutaAdutoraAguaTratada) {
        this.qtdComprimentoCondutaAdutoraAguaTratada = qtdComprimentoCondutaAdutoraAguaTratada;
        return this;
    }

    public void setQtdComprimentoCondutaAdutoraAguaTratada(BigDecimal qtdComprimentoCondutaAdutoraAguaTratada) {
        this.qtdComprimentoCondutaAdutoraAguaTratada = qtdComprimentoCondutaAdutoraAguaTratada;
    }

    public String getDescMaterialUtilizadoCondutas() {
        return descMaterialUtilizadoCondutas;
    }

    public SistemaAgua descMaterialUtilizadoCondutas(String descMaterialUtilizadoCondutas) {
        this.descMaterialUtilizadoCondutas = descMaterialUtilizadoCondutas;
        return this;
    }

    public void setDescMaterialUtilizadoCondutas(String descMaterialUtilizadoCondutas) {
        this.descMaterialUtilizadoCondutas = descMaterialUtilizadoCondutas;
    }

    public Long getQtdReservatoriosApoiados() {
        return qtdReservatoriosApoiados;
    }

    public SistemaAgua qtdReservatoriosApoiados(Long qtdReservatoriosApoiados) {
        this.qtdReservatoriosApoiados = qtdReservatoriosApoiados;
        return this;
    }

    public void setQtdReservatoriosApoiados(Long qtdReservatoriosApoiados) {
        this.qtdReservatoriosApoiados = qtdReservatoriosApoiados;
    }

    public Long getQtdCapacidadeReservatoriosApoiados() {
        return qtdCapacidadeReservatoriosApoiados;
    }

    public SistemaAgua qtdCapacidadeReservatoriosApoiados(Long qtdCapacidadeReservatoriosApoiados) {
        this.qtdCapacidadeReservatoriosApoiados = qtdCapacidadeReservatoriosApoiados;
        return this;
    }

    public void setQtdCapacidadeReservatoriosApoiados(Long qtdCapacidadeReservatoriosApoiados) {
        this.qtdCapacidadeReservatoriosApoiados = qtdCapacidadeReservatoriosApoiados;
    }

    public Long getQtdReservatoriosElevados() {
        return qtdReservatoriosElevados;
    }

    public SistemaAgua qtdReservatoriosElevados(Long qtdReservatoriosElevados) {
        this.qtdReservatoriosElevados = qtdReservatoriosElevados;
        return this;
    }

    public void setQtdReservatoriosElevados(Long qtdReservatoriosElevados) {
        this.qtdReservatoriosElevados = qtdReservatoriosElevados;
    }

    public Long getQtdCapacidadeReservatoriosElevados() {
        return qtdCapacidadeReservatoriosElevados;
    }

    public SistemaAgua qtdCapacidadeReservatoriosElevados(Long qtdCapacidadeReservatoriosElevados) {
        this.qtdCapacidadeReservatoriosElevados = qtdCapacidadeReservatoriosElevados;
        return this;
    }

    public void setQtdCapacidadeReservatoriosElevados(Long qtdCapacidadeReservatoriosElevados) {
        this.qtdCapacidadeReservatoriosElevados = qtdCapacidadeReservatoriosElevados;
    }

    public BigDecimal getAlturaReservatoriosElevados() {
        return alturaReservatoriosElevados;
    }

    public SistemaAgua alturaReservatoriosElevados(BigDecimal alturaReservatoriosElevados) {
        this.alturaReservatoriosElevados = alturaReservatoriosElevados;
        return this;
    }

    public void setAlturaReservatoriosElevados(BigDecimal alturaReservatoriosElevados) {
        this.alturaReservatoriosElevados = alturaReservatoriosElevados;
    }

    public String getNmTpTratamentoAgua() {
        return nmTpTratamentoAgua;
    }

    public SistemaAgua nmTpTratamentoAgua(String nmTpTratamentoAgua) {
        this.nmTpTratamentoAgua = nmTpTratamentoAgua;
        return this;
    }

    public void setNmTpTratamentoAgua(String nmTpTratamentoAgua) {
        this.nmTpTratamentoAgua = nmTpTratamentoAgua;
    }

    public String getNmTpTratamentoPadraoUtilizado() {
        return nmTpTratamentoPadraoUtilizado;
    }

    public SistemaAgua nmTpTratamentoPadraoUtilizado(String nmTpTratamentoPadraoUtilizado) {
        this.nmTpTratamentoPadraoUtilizado = nmTpTratamentoPadraoUtilizado;
        return this;
    }

    public void setNmTpTratamentoPadraoUtilizado(String nmTpTratamentoPadraoUtilizado) {
        this.nmTpTratamentoPadraoUtilizado = nmTpTratamentoPadraoUtilizado;
    }

    public String getNmTpTratamentoBasicoUtilizado() {
        return nmTpTratamentoBasicoUtilizado;
    }

    public SistemaAgua nmTpTratamentoBasicoUtilizado(String nmTpTratamentoBasicoUtilizado) {
        this.nmTpTratamentoBasicoUtilizado = nmTpTratamentoBasicoUtilizado;
        return this;
    }

    public void setNmTpTratamentoBasicoUtilizado(String nmTpTratamentoBasicoUtilizado) {
        this.nmTpTratamentoBasicoUtilizado = nmTpTratamentoBasicoUtilizado;
    }

    public String getExisteAvariaSistemaTratamento() {
        return existeAvariaSistemaTratamento;
    }

    public SistemaAgua existeAvariaSistemaTratamento(String existeAvariaSistemaTratamento) {
        this.existeAvariaSistemaTratamento = existeAvariaSistemaTratamento;
        return this;
    }

    public void setExisteAvariaSistemaTratamento(String existeAvariaSistemaTratamento) {
        this.existeAvariaSistemaTratamento = existeAvariaSistemaTratamento;
    }

    public String getExisteMotivoAusenciaTratamento() {
        return existeMotivoAusenciaTratamento;
    }

    public SistemaAgua existeMotivoAusenciaTratamento(String existeMotivoAusenciaTratamento) {
        this.existeMotivoAusenciaTratamento = existeMotivoAusenciaTratamento;
        return this;
    }

    public void setExisteMotivoAusenciaTratamento(String existeMotivoAusenciaTratamento) {
        this.existeMotivoAusenciaTratamento = existeMotivoAusenciaTratamento;
    }

    public String getNmEquipamentosComAvaria() {
        return nmEquipamentosComAvaria;
    }

    public SistemaAgua nmEquipamentosComAvaria(String nmEquipamentosComAvaria) {
        this.nmEquipamentosComAvaria = nmEquipamentosComAvaria;
        return this;
    }

    public void setNmEquipamentosComAvaria(String nmEquipamentosComAvaria) {
        this.nmEquipamentosComAvaria = nmEquipamentosComAvaria;
    }

    public Long getCaudalDoSistema() {
        return caudalDoSistema;
    }

    public SistemaAgua caudalDoSistema(Long caudalDoSistema) {
        this.caudalDoSistema = caudalDoSistema;
        return this;
    }

    public void setCaudalDoSistema(Long caudalDoSistema) {
        this.caudalDoSistema = caudalDoSistema;
    }

    public BigDecimal getQtdConsumoPercaptaLitrosHomemDia() {
        return qtdConsumoPercaptaLitrosHomemDia;
    }

    public SistemaAgua qtdConsumoPercaptaLitrosHomemDia(BigDecimal qtdConsumoPercaptaLitrosHomemDia) {
        this.qtdConsumoPercaptaLitrosHomemDia = qtdConsumoPercaptaLitrosHomemDia;
        return this;
    }

    public void setQtdConsumoPercaptaLitrosHomemDia(BigDecimal qtdConsumoPercaptaLitrosHomemDia) {
        this.qtdConsumoPercaptaLitrosHomemDia = qtdConsumoPercaptaLitrosHomemDia;
    }

    public BigDecimal getQtdDotacaoPercapta() {
        return qtdDotacaoPercapta;
    }

    public SistemaAgua qtdDotacaoPercapta(BigDecimal qtdDotacaoPercapta) {
        this.qtdDotacaoPercapta = qtdDotacaoPercapta;
        return this;
    }

    public void setQtdDotacaoPercapta(BigDecimal qtdDotacaoPercapta) {
        this.qtdDotacaoPercapta = qtdDotacaoPercapta;
    }

    public BigDecimal getQtdDiariaHorasServicoSistema() {
        return qtdDiariaHorasServicoSistema;
    }

    public SistemaAgua qtdDiariaHorasServicoSistema(BigDecimal qtdDiariaHorasServicoSistema) {
        this.qtdDiariaHorasServicoSistema = qtdDiariaHorasServicoSistema;
        return this;
    }

    public void setQtdDiariaHorasServicoSistema(BigDecimal qtdDiariaHorasServicoSistema) {
        this.qtdDiariaHorasServicoSistema = qtdDiariaHorasServicoSistema;
    }

    public String getEsquema() {
        return esquema;
    }

    public SistemaAgua esquema(String esquema) {
        this.esquema = esquema;
        return this;
    }

    public void setEsquema(String esquema) {
        this.esquema = esquema;
    }

    public String getNmModeloBombaManualUtilizada() {
        return nmModeloBombaManualUtilizada;
    }

    public SistemaAgua nmModeloBombaManualUtilizada(String nmModeloBombaManualUtilizada) {
        this.nmModeloBombaManualUtilizada = nmModeloBombaManualUtilizada;
        return this;
    }

    public void setNmModeloBombaManualUtilizada(String nmModeloBombaManualUtilizada) {
        this.nmModeloBombaManualUtilizada = nmModeloBombaManualUtilizada;
    }

    public String getNmTpBombaEnergia() {
        return nmTpBombaEnergia;
    }

    public SistemaAgua nmTpBombaEnergia(String nmTpBombaEnergia) {
        this.nmTpBombaEnergia = nmTpBombaEnergia;
        return this;
    }

    public void setNmTpBombaEnergia(String nmTpBombaEnergia) {
        this.nmTpBombaEnergia = nmTpBombaEnergia;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public SistemaAgua idSituacao(Situacao situacao) {
        this.situacao = situacao;
        return this;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public SistemaAgua idComuna(Comuna comuna) {
        this.comuna = comuna;
        return this;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    //Adicionado campos PROVINCIA E MUNICIPIO

    public Provincia getProvincia() {
        return provincia;
    }

    public SistemaAgua idProvincia(Provincia provincia) {
        this.provincia = provincia;
        return this;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public SistemaAgua idMunicipio(Municipio municipio) {
        this.municipio = municipio;
        return this;
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

    public String getMotivoAvariaSistema() {
        return motivoAvariaSistema;
    }

    public void setMotivoAvariaSistema(String motivoAvariaSistema) {
        this.motivoAvariaSistema = motivoAvariaSistema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SistemaAgua sistemaAgua = (SistemaAgua) o;
        if (sistemaAgua.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sistemaAgua.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SistemaAgua{" +
            "id=" + id +
            ", idUsuario=" + idUsuario +
            ", nmInqueridor='" + nmInqueridor + '\'' +
            ", dtLancamento=" + dtLancamento +
            ", dtUltimaAlteracao=" + dtUltimaAlteracao +
            ", nmLocalidade='" + nmLocalidade + '\'' +
            ", qtdPopulacaoActual=" + qtdPopulacaoActual +
            ", qtdCasasLocalidade=" + qtdCasasLocalidade +
            ", nmTpComunaAldeia='" + nmTpComunaAldeia + '\'' +
            ", nmTpArea='" + nmTpArea + '\'' +
            ", possuiSistemaAgua=" + possuiSistemaAgua +
            ", nmSistemaAgua='" + nmSistemaAgua + '\'' +
            ", nmFonteAgua='" + nmFonteAgua + '\'' +
            ", latitude='" + latitude + '\'' +
            ", longitude='" + longitude + '\'' +
            ", altitude='" + altitude + '\'' +
            ", nmTpFonte='" + nmTpFonte + '\'' +
            ", nmFonteAguaUtilizada='" + nmFonteAguaUtilizada + '\'' +
            ", nmTipoBomba='" + nmTipoBomba + '\'' +
            ", qtdCasasAguaLigada=" + qtdCasasAguaLigada +
            ", qtdChafarisesFuncionando=" + qtdChafarisesFuncionando +
            ", qtdChafarisesExistentes=" + qtdChafarisesExistentes +
            ", qtdContadoresLigados=" + qtdContadoresLigados +
            ", qtdBebedouros=" + qtdBebedouros +
            ", qtdHabitantesAcessoServicoAgua=" + qtdHabitantesAcessoServicoAgua +
            ", anoConstrucaoSistema=" + anoConstrucaoSistema +
            ", nmTpAvariaSistema='" + nmTpAvariaSistema + '\'' +
            ", causaAvariaSistema='" + causaAvariaSistema + '\'' +
            ", statusResolucao='" + statusResolucao + '\'' +
            ", tempoServicoDisponivel='" + tempoServicoDisponivel + '\'' +
            ", qtdDiametroCondutaAdutoraAguaBruta=" + qtdDiametroCondutaAdutoraAguaBruta +
            ", qtdComprimentoCondutaAdutoraAguaBruta=" + qtdComprimentoCondutaAdutoraAguaBruta +
            ", qtdDiametroCondutaAdutoraAguaTratada=" + qtdDiametroCondutaAdutoraAguaTratada +
            ", qtdComprimentoCondutaAdutoraAguaTratada=" + qtdComprimentoCondutaAdutoraAguaTratada +
            ", descMaterialUtilizadoCondutas='" + descMaterialUtilizadoCondutas + '\'' +
            ", descMaterialUtilizadoCondutasObs='" + descMaterialUtilizadoCondutasObs + '\'' +
            ", qtdReservatoriosApoiados=" + qtdReservatoriosApoiados +
            ", qtdCapacidadeReservatoriosApoiados=" + qtdCapacidadeReservatoriosApoiados +
            ", qtdReservatoriosElevados=" + qtdReservatoriosElevados +
            ", qtdCapacidadeReservatoriosElevados=" + qtdCapacidadeReservatoriosElevados +
            ", alturaReservatoriosElevados=" + alturaReservatoriosElevados +
            ", nmTpTratamentoAgua='" + nmTpTratamentoAgua + '\'' +
            ", nmTpTratamentoPadraoUtilizado='" + nmTpTratamentoPadraoUtilizado + '\'' +
            ", nmTpTratamentoPadraoUtilizadoObs='" + nmTpTratamentoPadraoUtilizadoObs + '\'' +
            ", estadoFuncionamentoSistema='" + estadoFuncionamentoSistema + '\'' +
            ", nmTpTratamentoBasicoUtilizado='" + nmTpTratamentoBasicoUtilizado + '\'' +
            ", nmTpTratamentoBasicoUtilizadoObs='" + nmTpTratamentoBasicoUtilizadoObs + '\'' +
            ", existeAvariaSistemaTratamento='" + existeAvariaSistemaTratamento + '\'' +
            ", existeMotivoAusenciaTratamento='" + existeMotivoAusenciaTratamento + '\'' +
            ", existeMotivoAusenciaTratamentoObs='" + existeMotivoAusenciaTratamentoObs + '\'' +
            ", nmEquipamentosComAvaria='" + nmEquipamentosComAvaria + '\'' +
            ", nmEquipamentosComAvariaObs='" + nmEquipamentosComAvariaObs + '\'' +
            ", caudalDoSistema=" + caudalDoSistema +
            ", qtdConsumoPercaptaLitrosHomemDia=" + qtdConsumoPercaptaLitrosHomemDia +
            ", qtdDotacaoPercapta=" + qtdDotacaoPercapta +
            ", qtdDiariaHorasServicoSistema=" + qtdDiariaHorasServicoSistema +
            ", esquema='" + esquema + '\'' +
            ", nmModeloBombaManualUtilizada='" + nmModeloBombaManualUtilizada + '\'' +
            ", nmModeloBombaManualUtilizadaObs='" + nmModeloBombaManualUtilizadaObs + '\'' +
            ", nmTpBombaEnergia='" + nmTpBombaEnergia + '\'' +
            ", situacao=" + situacao +
            ", comuna=" + comuna +
            ", provincia=" + provincia +
            ", municipio=" + municipio +
            '}';
    }
}
