package com.minea.sisas.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Publicacao.class)
public abstract class Publicacao_ {

	public static volatile SingularAttribute<Publicacao, String> textoPublicacao;
	public static volatile SingularAttribute<Publicacao, Situacao> situacao;
	public static volatile SingularAttribute<Publicacao, String> resumoTextoPublicacao;
	public static volatile SingularAttribute<Publicacao, Long> id;
	public static volatile SingularAttribute<Publicacao, String> tituloPublicacao;

}

