package com.minea.sisas.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProgramasProjectosLog.class)
public abstract class ProgramasProjectosLog_ {

	public static volatile SingularAttribute<ProgramasProjectosLog, LocalDate> dtLog;
	public static volatile SingularAttribute<ProgramasProjectosLog, String> log;
	public static volatile SingularAttribute<ProgramasProjectosLog, Long> idUsuario;
	public static volatile SingularAttribute<ProgramasProjectosLog, Long> id;
	public static volatile SingularAttribute<ProgramasProjectosLog, String> acao;
	public static volatile SingularAttribute<ProgramasProjectosLog, ProgramasProjectos> idProgramasProjectos;

}

