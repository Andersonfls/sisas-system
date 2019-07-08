package com.minea.sisas.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contrato.class)
public abstract class Contrato_ {

	public static volatile SingularAttribute<Contrato, SistemaAgua> idSistemaAgua;
	public static volatile SingularAttribute<Contrato, LocalDate> dtRecepcaoProvisoria;
	public static volatile SingularAttribute<Contrato, String> tipoEmpreitada;
	public static volatile SingularAttribute<Contrato, BigDecimal> prazoExecucao;
	public static volatile SingularAttribute<Contrato, String> nmResposavelAntProjeto;
	public static volatile SingularAttribute<Contrato, LocalDate> dtAdiantamento;
	public static volatile SingularAttribute<Contrato, LocalDate> dtRecepcaoDefinitiva;
	public static volatile SingularAttribute<Contrato, LocalDate> dtLancamento;
	public static volatile SingularAttribute<Contrato, LocalDate> dtRecepcaoComicionamento;
	public static volatile SingularAttribute<Contrato, LocalDate> dtInicio;
	public static volatile SingularAttribute<Contrato, BigDecimal> valorContrato;
	public static volatile SingularAttribute<Contrato, String> nmEmpresaAdjudicitaria;
	public static volatile SingularAttribute<Contrato, LocalDate> dtFinalizacaoProcessoHomologAprov;
	public static volatile SetAttribute<Contrato, Empreitada> empreitadas;
	public static volatile SetAttribute<Contrato, Execucao> execucaos;
	public static volatile SingularAttribute<Contrato, BigDecimal> valorAdiantamento;
	public static volatile SingularAttribute<Contrato, LocalDate> dtAssinatura;
	public static volatile SingularAttribute<Contrato, Long> id;
	public static volatile SingularAttribute<Contrato, String> nmResposavelProjeto;
	public static volatile SingularAttribute<Contrato, Long> idContrato;
	public static volatile SingularAttribute<Contrato, ProgramasProjectos> idProgramasProjectos;
	public static volatile SingularAttribute<Contrato, String> tipoMoeda;

}

