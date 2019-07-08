package com.minea.sisas.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConfiguracoesLog.class)
public abstract class ConfiguracoesLog_ {

	public static volatile SingularAttribute<ConfiguracoesLog, LocalDate> dtLog;
	public static volatile SingularAttribute<ConfiguracoesLog, String> log;
	public static volatile SingularAttribute<ConfiguracoesLog, Long> idUsuario;
	public static volatile SingularAttribute<ConfiguracoesLog, Long> id;
	public static volatile SingularAttribute<ConfiguracoesLog, String> acao;

}

