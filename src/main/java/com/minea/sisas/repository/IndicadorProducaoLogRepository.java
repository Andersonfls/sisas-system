package com.minea.sisas.repository;

import com.minea.sisas.domain.IndicadorProducaoLog;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the IndicadorProducaoLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IndicadorProducaoLogRepository extends JpaRepository<IndicadorProducaoLog, Long>, JpaSpecificationExecutor<IndicadorProducaoLog> {

}
