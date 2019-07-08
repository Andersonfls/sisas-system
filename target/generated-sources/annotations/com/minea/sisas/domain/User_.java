package com.minea.sisas.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends com.minea.sisas.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<User, String> telefone;
	public static volatile SingularAttribute<User, Instant> resetDate;
	public static volatile SingularAttribute<User, String> enderecoUf;
	public static volatile SingularAttribute<User, String> brAssociado;
	public static volatile SingularAttribute<User, Integer> enderecoLote;
	public static volatile SingularAttribute<User, String> login;
	public static volatile SingularAttribute<User, Boolean> modToten;
	public static volatile SingularAttribute<User, String> resetKey;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> imageUrl;
	public static volatile SingularAttribute<User, Boolean> modBalcao;
	public static volatile SingularAttribute<User, String> celular;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> enderecoCidade;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, Boolean> modMobile;
	public static volatile SingularAttribute<User, String> enderecoCep;
	public static volatile SingularAttribute<User, String> nome;
	public static volatile SingularAttribute<User, String> nfAssociado;
	public static volatile SingularAttribute<User, Boolean> perfilAdm;
	public static volatile SingularAttribute<User, String> activationKey;
	public static volatile SetAttribute<User, Authority> authorities;
	public static volatile SingularAttribute<User, String> enderecoLogadouro;
	public static volatile SingularAttribute<User, String> langKey;
	public static volatile SingularAttribute<User, String> rfAssociado;
	public static volatile SingularAttribute<User, String> enderecoComplemento;
	public static volatile SingularAttribute<User, Boolean> modConfig;
	public static volatile SingularAttribute<User, String> enderecoBairro;
	public static volatile SingularAttribute<User, String> qrAssociado;
	public static volatile SingularAttribute<User, Boolean> activated;

}

