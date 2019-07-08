package com.minea.sisas.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contactos.class)
public abstract class Contactos_ {

	public static volatile SingularAttribute<Contactos, String> textoContactos;
	public static volatile SingularAttribute<Contactos, String> resumoTextoContactos;
	public static volatile SingularAttribute<Contactos, Long> idContactos;
	public static volatile SetAttribute<Contactos, Inicio> inicios;
	public static volatile SingularAttribute<Contactos, Situacao> idSituacao;
	public static volatile SingularAttribute<Contactos, Long> id;
	public static volatile SingularAttribute<Contactos, LocalDate> dtLancamento;
	public static volatile SingularAttribute<Contactos, LocalDate> dtUltimaAlteracao;
	public static volatile SingularAttribute<Contactos, String> nmContactos;

}

