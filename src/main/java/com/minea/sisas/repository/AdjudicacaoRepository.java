package com.minea.sisas.repository;

import com.minea.sisas.domain.Adjudicacao;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Adjudicacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdjudicacaoRepository extends JpaRepository<Adjudicacao, Long>, JpaSpecificationExecutor<Adjudicacao> {

    Adjudicacao findByProgramasProjectosId(long id);
}
