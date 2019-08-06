package com.minea.sisas.repository;

import com.minea.sisas.domain.Municipio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Municipio entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long>, JpaSpecificationExecutor<Municipio> {
    @Query("select m from Municipio m where LOWER(m.nmMunicipio) like LOWER(concat(:nome,'%')) " +
        "or LOWER(m.provincia.nmProvincia) like LOWER(concat(:nome,'%'))")
    Page buscarPorNome(@Param("nome") String nome, Pageable pageable);
}
