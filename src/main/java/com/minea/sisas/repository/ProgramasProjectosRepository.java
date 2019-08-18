package com.minea.sisas.repository;

import com.minea.sisas.domain.ProgramasProjectos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ProgramasProjectos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProgramasProjectosRepository extends JpaRepository<ProgramasProjectos, Long>, JpaSpecificationExecutor<ProgramasProjectos> {
    @Query("select p from ProgramasProjectos p where LOWER(p.nmDescricaoProjeto) like LOWER(concat(:nome,'%'))" +
        "or LOWER(p.nmDesignacaoProjeto) like LOWER(CONCAT(:nome,'%'))" +
        "or LOWER(p.usuario) like LOWER(CONCAT(:nome,'%'))"+
        "or LOWER(p.idSaaAssociado) like LOWER(CONCAT(:nome,'%'))"+
        "or LOWER(p.tipoFinanciamento) like LOWER(CONCAT(:nome,'%'))"+
        "or LOWER(p.especialidade) like LOWER(CONCAT(:nome,'%'))"+
        "or LOWER(p.comuna) like LOWER(CONCAT(:nome,'%'))")
    Page buscarPorNome(@Param("nome") String nome, Pageable pageable);
}
