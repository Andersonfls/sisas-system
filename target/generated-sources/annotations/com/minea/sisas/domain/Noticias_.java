package com.minea.sisas.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Noticias.class)
public abstract class Noticias_ {

	public static volatile SingularAttribute<Noticias, String> textoNoticias;
	public static volatile SingularAttribute<Noticias, String> tituloNoticias;
	public static volatile SetAttribute<Noticias, Inicio> inicios;
	public static volatile SingularAttribute<Noticias, String> resumoTextoNoticias;
	public static volatile SingularAttribute<Noticias, Situacao> idSituacao;
	public static volatile SingularAttribute<Noticias, Long> id;

}

