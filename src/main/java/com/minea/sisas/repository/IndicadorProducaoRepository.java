package com.minea.sisas.repository;

import com.minea.sisas.domain.IndicadorProducao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the IndicadorProducao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IndicadorProducaoRepository extends JpaRepository<IndicadorProducao, Long>, JpaSpecificationExecutor<IndicadorProducao> {
    @Query("select i from IndicadorProducao i where i.status = true AND LOWER(i.idUsuario) like LOWER(concat(:nome,'%'))" +
        "or LOWER(i.comuna) like LOWER(CONCAT(:nome,'%'))" +
        "or LOWER(i.situacao) like LOWER(CONCAT(:nome,'%'))"+
        "or LOWER(i.sistemaAgua) like LOWER(CONCAT(:nome,'%'))")
    Page buscarPorNome(@Param("nome") String nome, Pageable pageable);

    IndicadorProducao findByIdAndStatusIsTrue(Long id);

    Page<IndicadorProducao> findAllByStatusIsTrue(Pageable pageable);
}
