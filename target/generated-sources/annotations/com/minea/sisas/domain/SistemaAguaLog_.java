package com.minea.sisas.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SistemaAguaLog.class)
public abstract class SistemaAguaLog_ {

	public static volatile SingularAttribute<SistemaAguaLog, SistemaAgua> idSistemaAgua;
	public static volatile SingularAttribute<SistemaAguaLog, LocalDate> dtLog;
	public static volatile SingularAttribute<SistemaAguaLog, String> log;
	public static volatile SingularAttribute<SistemaAguaLog, Long> idUsuario;
	public static volatile SingularAttribute<SistemaAguaLog, Long> id;
	public static volatile SingularAttribute<SistemaAguaLog, String> acao;

}

