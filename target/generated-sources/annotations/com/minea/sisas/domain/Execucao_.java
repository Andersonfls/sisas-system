package com.minea.sisas.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Execucao.class)
public abstract class Execucao_ {

	public static volatile SingularAttribute<Execucao, SistemaAgua> idSistemaAgua;
	public static volatile SingularAttribute<Execucao, String> tipoEmpreitada;
	public static volatile SingularAttribute<Execucao, BigDecimal> valorPagoPeriodo;
	public static volatile SingularAttribute<Execucao, LocalDate> dtFimReferencia;
	public static volatile SingularAttribute<Execucao, LocalDate> dtLancamento;
	public static volatile SingularAttribute<Execucao, String> numFactura;
	public static volatile SingularAttribute<Execucao, BigDecimal> valorFacturadoPeriodo;
	public static volatile SingularAttribute<Execucao, LocalDate> dtFactura;
	public static volatile SingularAttribute<Execucao, BigDecimal> txCambio;
	public static volatile SingularAttribute<Execucao, LocalDate> dtPeridoReferencia;
	public static volatile SingularAttribute<Execucao, String> constrangimento;
	public static volatile SingularAttribute<Execucao, Situacao> idSituacao;
	public static volatile SingularAttribute<Execucao, Long> id;
	public static volatile SingularAttribute<Execucao, ProgramasProjectos> idProgramasProjectos;
	public static volatile SingularAttribute<Execucao, Contrato> idContrato;

}

