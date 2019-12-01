package com.minea.sisas.repository;

import com.minea.sisas.domain.Especialidades;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Especialidades entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EspecialidadesRepository extends JpaRepository<Especialidades, Long>, JpaSpecificationExecutor<Especialidades> {

    @Query("select e from Especialidades e where LOWER(e.nmEspecialidade) like LOWER(concat(:nome,'%'))")
    Page buscarPorNome(@Param("nome") String nome, Pageable pageable);

}
