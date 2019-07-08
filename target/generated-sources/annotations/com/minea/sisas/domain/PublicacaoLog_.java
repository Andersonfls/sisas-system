package com.minea.sisas.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PublicacaoLog.class)
public abstract class PublicacaoLog_ {

	public static volatile SingularAttribute<PublicacaoLog, LocalDate> dtLog;
	public static volatile SingularAttribute<PublicacaoLog, String> log;
	public static volatile SingularAttribute<PublicacaoLog, Long> idUsuario;
	public static volatile SingularAttribute<PublicacaoLog, Long> id;
	public static volatile SingularAttribute<PublicacaoLog, String> acao;

}

