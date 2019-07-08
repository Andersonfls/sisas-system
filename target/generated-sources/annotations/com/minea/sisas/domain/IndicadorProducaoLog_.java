package com.minea.sisas.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(IndicadorProducaoLog.class)
public abstract class IndicadorProducaoLog_ {

	public static volatile SingularAttribute<IndicadorProducaoLog, LocalDate> dtLog;
	public static volatile SingularAttribute<IndicadorProducaoLog, String> log;
	public static volatile SingularAttribute<IndicadorProducaoLog, IndicadorProducao> idIndicadorProducao;
	public static volatile SingularAttribute<IndicadorProducaoLog, Long> idUsuario;
	public static volatile SingularAttribute<IndicadorProducaoLog, Long> id;
	public static volatile SingularAttribute<IndicadorProducaoLog, String> acao;

}

