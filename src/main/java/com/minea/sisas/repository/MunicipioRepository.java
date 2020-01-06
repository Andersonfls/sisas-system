package com.minea.sisas.repository;

import com.minea.sisas.domain.Municipio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the Municipio entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long>, JpaSpecificationExecutor<Municipio> {
    @Query("select m from Municipio m where LOWER(m.nmMunicipio) like LOWER(concat(:nome,'%'))")
    Page buscarPorNome(@Param("nome") String nome, Pageable pageable);

    @Query("select m from Municipio m where m.provincia.id in (?1) order by m.nmMunicipio")
    List<Municipio> findByProvinciaId(Long disciplinaId);

    List<Municipio> findAllByProvinciaId(Long id);

    @Query("select m from Municipio m where m.nmMunicipio like :nome ")
    Municipio findAllByNome(String nome);
}
