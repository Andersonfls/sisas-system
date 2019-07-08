package com.minea.sisas.repository;

import com.minea.sisas.domain.IndicadorProducao;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the IndicadorProducao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IndicadorProducaoRepository extends JpaRepository<IndicadorProducao, Long>, JpaSpecificationExecutor<IndicadorProducao> {

}
