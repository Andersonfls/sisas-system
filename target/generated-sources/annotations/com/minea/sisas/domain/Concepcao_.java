package com.minea.sisas.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Concepcao.class)
public abstract class Concepcao_ {

	public static volatile SingularAttribute<Concepcao, SistemaAgua> idSistemaAgua;
	public static volatile SingularAttribute<Concepcao, LocalDate> dtElaboracaoCon;
	public static volatile SingularAttribute<Concepcao, LocalDate> dtAprovacaoCon;
	public static volatile SingularAttribute<Concepcao, String> tipoConcurso;
	public static volatile SingularAttribute<Concepcao, Long> id;
	public static volatile SingularAttribute<Concepcao, LocalDate> dtLancamento;
	public static volatile SingularAttribute<Concepcao, LocalDate> dtUltimaAlteracao;
	public static volatile SingularAttribute<Concepcao, ProgramasProjectos> idProgramasProjectos;

}

