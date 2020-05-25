package com.minea.sisas.repository;

import com.minea.sisas.domain.IndicadorProducao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.time.LocalDate;
import java.util.List;


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

    //PROVINCIA
    List<IndicadorProducao> findAllByProvinciaNmProvinciaEquals(String nmProvincia);

    //PERIODO
    @Query("select i from IndicadorProducao i where year(i.dtLancamento) = ?1")
    List<IndicadorProducao> getAllByYear(Integer ano);

    //ANO e Provincia
    @Query("select i from IndicadorProducao i where year(i.dtLancamento) = ?1 and i.provincia.id = ?2")
    List<IndicadorProducao> getAllByYearAndProvincia(Integer ano, Long idProvincia);

    @Query("select i from IndicadorProducao i where i.status =1 and i.provincia.id = :provinciaId")
    Page<IndicadorProducao> findAllByStatusIsTrueAndProvinciaId(@Param("provinciaId") Long provinciaId, Pageable pageable);
}
