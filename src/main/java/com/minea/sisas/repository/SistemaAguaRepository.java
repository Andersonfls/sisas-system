package com.minea.sisas.repository;

import com.minea.sisas.domain.SistemaAgua;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the SistemaAgua entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SistemaAguaRepository extends JpaRepository<SistemaAgua, Long>, JpaSpecificationExecutor<SistemaAgua> {

    @Query("select s from SistemaAgua s where LOWER(s.nmSistemaAgua) like LOWER(concat(:nome,'%')) " +
        "or LOWER(s.nmLocalidade) like LOWER(concat(:nome,'%'))" +
        "or LOWER(s.nmTpArea) like LOWER(concat(:nome,'%'))")
    Page buscarPorNome(@Param("nome") String nome, Pageable pageable);
}
