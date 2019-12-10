package com.minea.sisas.repository;

import com.minea.sisas.domain.Comuna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the Comuna entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Long>, JpaSpecificationExecutor<Comuna> {

    @Query("select c from Comuna c where LOWER(c.nmComuna) like LOWER(concat(:nome,'%'))" +
        "or LOWER(c.municipio.nmMunicipio) like LOWER(CONCAT(:nome,'%'))")
    Page buscarPorNome(@Param("nome") String nome, Pageable pageable);

    @Query("select count(c) from Comuna c where c.municipio.id = :id ")
    Integer quantidadeComunasPorMunicipio(@Param("id") Long id);

    @Query("select c from Comuna c where c.municipio.id in (?1) order by c.nmComuna")
    List<Comuna> findByMunicipioId(Long municipioId);

}
