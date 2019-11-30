package com.minea.sisas.repository;

import com.minea.sisas.domain.FinalidadeProjeto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the FinalidadeProjeto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FinalidadeProjetoRepository extends JpaRepository<FinalidadeProjeto, Long>, JpaSpecificationExecutor<FinalidadeProjeto> {

    @Query("select fp from FinalidadeProjeto fp where LOWER(fp.nmFinalidade) like LOWER(concat(:nome,'%'))")
    Page buscarPorNome(@Param("nome") String nome, Pageable pageable);

}
