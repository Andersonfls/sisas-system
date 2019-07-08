package com.minea.sisas.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Projectos.class)
public abstract class Projectos_ {

	public static volatile SingularAttribute<Projectos, String> textoProjectos;
	public static volatile SingularAttribute<Projectos, String> resumoTextoProjectos;
	public static volatile SetAttribute<Projectos, Inicio> inicios;
	public static volatile SingularAttribute<Projectos, String> nmProjectos;
	public static volatile SingularAttribute<Projectos, Situacao> idSituacao;
	public static volatile SingularAttribute<Projectos, Long> id;

}

