package com.minea.sisas.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EntidadeGestora.class)
public abstract class EntidadeGestora_ {

	public static volatile SingularAttribute<EntidadeGestora, String> endereco;
	public static volatile SingularAttribute<EntidadeGestora, Long> idEntidadeGestora;
	public static volatile SingularAttribute<EntidadeGestora, Long> tpModeloGestao;
	public static volatile SingularAttribute<EntidadeGestora, MunicipiosAtendidos> idMunicipioAtendido;
	public static volatile SingularAttribute<EntidadeGestora, String> contactos;
	public static volatile SingularAttribute<EntidadeGestora, Long> id;
	public static volatile SingularAttribute<EntidadeGestora, String> nmEntidadeGestora;
	public static volatile SingularAttribute<EntidadeGestora, BigDecimal> numPopulacaoAreaAtendimento;
	public static volatile SingularAttribute<EntidadeGestora, Long> tpFormaJuridica;
	public static volatile SingularAttribute<EntidadeGestora, String> email;
	public static volatile SingularAttribute<EntidadeGestora, Long> numRecursosHumanos;
	public static volatile SingularAttribute<EntidadeGestora, LocalDate> dtConstituicao;

}

