package com.minea.sisas.repository;

import com.minea.sisas.domain.ProgramasProjectosLog;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ProgramasProjectosLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProgramasProjectosLogRepository extends JpaRepository<ProgramasProjectosLog, Long>, JpaSpecificationExecutor<ProgramasProjectosLog> {

}
