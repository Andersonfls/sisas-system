package com.minea.sisas.repository;

import com.minea.sisas.domain.ProgramasProjectos;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ProgramasProjectos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProgramasProjectosRepository extends JpaRepository<ProgramasProjectos, Long>, JpaSpecificationExecutor<ProgramasProjectos> {

}
