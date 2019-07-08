package com.minea.sisas.repository;

import com.minea.sisas.domain.Comuna;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Comuna entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Long>, JpaSpecificationExecutor<Comuna> {

}
