package com.minea.sisas.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProgramasProjectos.class)
public abstract class ProgramasProjectos_ {

	public static volatile SetAttribute<ProgramasProjectos, ProgramasProjectosLog> programasProjectosLogs;
	public static volatile SetAttribute<ProgramasProjectos, Concepcao> concepcaos;
	public static volatile SingularAttribute<ProgramasProjectos, String> tipoFinanciamento;
	public static volatile SingularAttribute<ProgramasProjectos, String> especialidade;
	public static volatile SetAttribute<ProgramasProjectos, Concurso> concursos;
	public static volatile SingularAttribute<ProgramasProjectos, Long> idUsuario;
	public static volatile SingularAttribute<ProgramasProjectos, LocalDate> dtLancamento;
	public static volatile SingularAttribute<ProgramasProjectos, Comuna> idComuna;
	public static volatile SingularAttribute<ProgramasProjectos, String> nmDesignacaoProjeto;
	public static volatile SingularAttribute<ProgramasProjectos, String> nmDescricaoProjeto;
	public static volatile SetAttribute<ProgramasProjectos, Empreitada> empreitadas;
	public static volatile SetAttribute<ProgramasProjectos, Contrato> contratoes;
	public static volatile SetAttribute<ProgramasProjectos, Execucao> execucaos;
	public static volatile SingularAttribute<ProgramasProjectos, Long> id;
	public static volatile SingularAttribute<ProgramasProjectos, LocalDate> dtUltimaAlteracao;
	public static volatile SingularAttribute<ProgramasProjectos, Long> idSaaAssociado;
	public static volatile SingularAttribute<ProgramasProjectos, Long> idProgramasProjectos;
	public static volatile SetAttribute<ProgramasProjectos, Adjudicacao> adjudicacaos;

}

