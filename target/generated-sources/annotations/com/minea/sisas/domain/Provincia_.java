package com.minea.sisas.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Provincia.class)
public abstract class Provincia_ {

	public static volatile SetAttribute<Provincia, Municipio> municipios;
	public static volatile SingularAttribute<Provincia, String> nmProvincia;
	public static volatile SingularAttribute<Provincia, Long> id;

}

