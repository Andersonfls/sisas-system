package com.minea.sisas.repository;

import com.minea.sisas.domain.Fases;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Fases entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FasesRepository extends JpaRepository<Fases, Long>, JpaSpecificationExecutor<Fases> {

}
