package com.minea.sisas.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Empreitada.class)
public abstract class Empreitada_ {

	public static volatile SingularAttribute<Empreitada, BigDecimal> numExtensaoCondAdutMat;
	public static volatile SingularAttribute<Empreitada, BigDecimal> numCaixasVisitas;
	public static volatile SingularAttribute<Empreitada, SistemaAgua> idSistemaAgua;
	public static volatile SingularAttribute<Empreitada, String> tipoEmpreitada;
	public static volatile SingularAttribute<Empreitada, BigDecimal> numLigacoesTorneiraQuintal;
	public static volatile SingularAttribute<Empreitada, BigDecimal> numCaprmazenamento;
	public static volatile SingularAttribute<Empreitada, BigDecimal> numLigacoesDomiciliares;
	public static volatile SingularAttribute<Empreitada, BigDecimal> numLatrinas;
	public static volatile SingularAttribute<Empreitada, LocalDate> dtLancamento;
	public static volatile SingularAttribute<Empreitada, BigDecimal> numChafarisNovos;
	public static volatile SingularAttribute<Empreitada, Long> idEmpreitada;
	public static volatile SingularAttribute<Empreitada, BigDecimal> numCapacidadeTratamentoEta;
	public static volatile SingularAttribute<Empreitada, BigDecimal> numEstacoesElevatorias;
	public static volatile SingularAttribute<Empreitada, BigDecimal> numExtensaoRedeMat;
	public static volatile SingularAttribute<Empreitada, BigDecimal> numExtensaoCondutasElelMat;
	public static volatile SingularAttribute<Empreitada, BigDecimal> numExtensaoRedeMaterial;
	public static volatile SingularAttribute<Empreitada, BigDecimal> numChafarisReabilitar;
	public static volatile SingularAttribute<Empreitada, Long> id;
	public static volatile SingularAttribute<Empreitada, ProgramasProjectos> idProgramasProjectos;
	public static volatile SingularAttribute<Empreitada, Contrato> idContrato;
	public static volatile SingularAttribute<Empreitada, BigDecimal> numCapacidadeCaptacaoEta;
	public static volatile SingularAttribute<Empreitada, BigDecimal> numLigacoes;
	public static volatile SingularAttribute<Empreitada, BigDecimal> numCapacidadeCaptacao;

}

