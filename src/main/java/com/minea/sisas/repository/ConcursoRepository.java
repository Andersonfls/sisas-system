package com.minea.sisas.repository;

import com.minea.sisas.domain.Concurso;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Concurso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConcursoRepository extends JpaRepository<Concurso, Long>, JpaSpecificationExecutor<Concurso> {

    Concurso findByProgramasProjectosId(long id);
}
