package com.minea.sisas.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SistemaAgua.class)
public abstract class SistemaAgua_ {

	public static volatile SingularAttribute<SistemaAgua, Situacao> situacao;
	public static volatile SingularAttribute<SistemaAgua, String> nmTpComunaAldeia;
	public static volatile SingularAttribute<SistemaAgua, Long> qtdBebedouros;
	public static volatile SingularAttribute<SistemaAgua, BigDecimal> qtdDiametroCondutaAdutoraAguaBruta;
	public static volatile SingularAttribute<SistemaAgua, String> nmFonteAguaUtilizada;
	public static volatile SingularAttribute<SistemaAgua, Long> anoConstrucaoSistema;
	public static volatile SingularAttribute<SistemaAgua, String> nmEquipamentosComAvaria;
	public static volatile SingularAttribute<SistemaAgua, String> nmTpTratamentoAgua;
	public static volatile SingularAttribute<SistemaAgua, Long> id;
	public static volatile SingularAttribute<SistemaAgua, String> nmTpFonte;
	public static volatile SingularAttribute<SistemaAgua, BigDecimal> qtdDiametroCondutaAdutoraAguaTratada;
	public static volatile SingularAttribute<SistemaAgua, String> nmTpTratamentoPadraoUtilizado;
	public static volatile SingularAttribute<SistemaAgua, String> statusResolucao;
	public static volatile SingularAttribute<SistemaAgua, BigDecimal> longitude;
	public static volatile SingularAttribute<SistemaAgua, Long> qtdCasasLocalidade;
	public static volatile SingularAttribute<SistemaAgua, String> nmTpArea;
	public static volatile SingularAttribute<SistemaAgua, Long> qtdContadoresLigados;
	public static volatile SingularAttribute<SistemaAgua, String> existeMotivoAusenciaTratamento;
	public static volatile SingularAttribute<SistemaAgua, BigDecimal> qtdComprimentoCondutaAdutoraAguaTratada;
	public static volatile SingularAttribute<SistemaAgua, String> esquema;
	public static volatile SingularAttribute<SistemaAgua, String> nmInqueridor;
	public static volatile SingularAttribute<SistemaAgua, String> tempoServicoDisponivel;
	public static volatile SingularAttribute<SistemaAgua, String> nmFonteAgua;
	public static volatile SingularAttribute<SistemaAgua, String> nmModeloBombaManualUtilizada;
	public static volatile SingularAttribute<SistemaAgua, Long> qtdChafarisesFuncionando;
	public static volatile SingularAttribute<SistemaAgua, BigDecimal> altitude;
	public static volatile SingularAttribute<SistemaAgua, BigDecimal> qtdConsumoPercaptaLitrosHomemDia;
	public static volatile SingularAttribute<SistemaAgua, Long> idUsuario;
	public static volatile SingularAttribute<SistemaAgua, BigDecimal> latitude;
	public static volatile SingularAttribute<SistemaAgua, String> nmTpAvariaSistema;
	public static volatile SingularAttribute<SistemaAgua, String> descMaterialUtilizadoCondutas;
	public static volatile SingularAttribute<SistemaAgua, String> causaAvariaSistema;
	public static volatile SingularAttribute<SistemaAgua, BigDecimal> qtdDiariaHorasServicoSistema;
	public static volatile SingularAttribute<SistemaAgua, Long> qtdPopulacaoActual;
	public static volatile SingularAttribute<SistemaAgua, Long> qtdCapacidadeReservatoriosApoiados;
	public static volatile SingularAttribute<SistemaAgua, Long> possuiSistemaAgua;
	public static volatile SingularAttribute<SistemaAgua, String> existeAvariaSistemaTratamento;
	public static volatile SingularAttribute<SistemaAgua, BigDecimal> qtdDotacaoPercapta;
	public static volatile SingularAttribute<SistemaAgua, Long> qtdCasasAguaLigada;
	public static volatile SingularAttribute<SistemaAgua, String> nmTpBombaEnergia;
	public static volatile SingularAttribute<SistemaAgua, Long> caudalDoSistema;
	public static volatile SingularAttribute<SistemaAgua, Long> qtdHabitantesAcessoServicoAgua;
	public static volatile SingularAttribute<SistemaAgua, LocalDate> dtLancamento;
	public static volatile SingularAttribute<SistemaAgua, Long> qtdReservatoriosElevados;
	public static volatile SingularAttribute<SistemaAgua, BigDecimal> qtdComprimentoCondutaAdutoraAguaBruta;
	public static volatile SingularAttribute<SistemaAgua, BigDecimal> alturaReservatoriosElevados;
	public static volatile SingularAttribute<SistemaAgua, String> nmSistemaAgua;
	public static volatile SingularAttribute<SistemaAgua, Long> qtdReservatoriosApoiados;
	public static volatile SingularAttribute<SistemaAgua, Long> qtdCapacidadeReservatoriosElevados;
	public static volatile SingularAttribute<SistemaAgua, Comuna> comuna;
	public static volatile SingularAttribute<SistemaAgua, String> nmTpTratamentoBasicoUtilizado;
	public static volatile SingularAttribute<SistemaAgua, String> nmLocalidade;
	public static volatile SingularAttribute<SistemaAgua, LocalDate> dtUltimaAlteracao;
	public static volatile SingularAttribute<SistemaAgua, String> nmTipoBomba;

}

