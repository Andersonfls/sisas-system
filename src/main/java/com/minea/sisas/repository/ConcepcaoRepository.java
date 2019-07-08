package com.minea.sisas.repository;

import com.minea.sisas.domain.Concepcao;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Concepcao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConcepcaoRepository extends JpaRepository<Concepcao, Long>, JpaSpecificationExecutor<Concepcao> {

}
