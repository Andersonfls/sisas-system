package com.minea.sisas.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Concurso.class)
public abstract class Concurso_ {

	public static volatile SingularAttribute<Concurso, SistemaAgua> idSistemaAgua;
	public static volatile SingularAttribute<Concurso, LocalDate> dtNegociacao;
	public static volatile SingularAttribute<Concurso, String> tipoConcurso;
	public static volatile SingularAttribute<Concurso, LocalDate> dtAberturaProposta;
	public static volatile SingularAttribute<Concurso, Long> id;
	public static volatile SingularAttribute<Concurso, LocalDate> dtLancamento;
	public static volatile SingularAttribute<Concurso, LocalDate> dtUltimaAlteracao;
	public static volatile SingularAttribute<Concurso, LocalDate> dtConclusaoAvaliacaoRelPrel;
	public static volatile SingularAttribute<Concurso, LocalDate> dtEntregaProposta;
	public static volatile SingularAttribute<Concurso, LocalDate> dtAprovRelAvalFinal;
	public static volatile SingularAttribute<Concurso, ProgramasProjectos> idProgramasProjectos;

}

