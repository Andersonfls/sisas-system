package com.minea.sisas.repository;

import com.minea.sisas.domain.Epas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Epas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EpasRepository extends JpaRepository<Epas, Long>, JpaSpecificationExecutor<Epas> {

    @Query("select e from Epas e where LOWER(e.nmEpas) like LOWER(concat(:nome,'%'))" +
        "or LOWER(e.municipio.nmMunicipio) like LOWER(CONCAT(:nome,'%'))")
    Page buscarPorNome(@Param("nome") String nome, Pageable pageable);

    @Query("select count(e) from Epas e where e.municipio.id = :id ")
    Integer quantidadeEpassPorMunicipio(@Param("id") Long id);

}
