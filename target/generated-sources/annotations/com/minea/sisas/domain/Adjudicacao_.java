package com.minea.sisas.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Adjudicacao.class)
public abstract class Adjudicacao_ {

	public static volatile SingularAttribute<Adjudicacao, SistemaAgua> idSistemaAgua;
	public static volatile SingularAttribute<Adjudicacao, String> tipoConcurso;
	public static volatile SingularAttribute<Adjudicacao, Long> id;
	public static volatile SingularAttribute<Adjudicacao, LocalDate> dtLancamento;
	public static volatile SingularAttribute<Adjudicacao, LocalDate> dtComunicaoAdjudicacao;
	public static volatile SingularAttribute<Adjudicacao, LocalDate> dtSubmissaoMinutContrato;
	public static volatile SingularAttribute<Adjudicacao, ProgramasProjectos> idProgramasProjectos;
	public static volatile SingularAttribute<Adjudicacao, LocalDate> dtPrestacaoGarantBoaExec;

}

