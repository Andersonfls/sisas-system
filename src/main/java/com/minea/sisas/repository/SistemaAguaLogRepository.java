package com.minea.sisas.repository;

import com.minea.sisas.domain.SistemaAguaLog;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the SistemaAguaLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SistemaAguaLogRepository extends JpaRepository<SistemaAguaLog, Long>, JpaSpecificationExecutor<SistemaAguaLog> {

}
