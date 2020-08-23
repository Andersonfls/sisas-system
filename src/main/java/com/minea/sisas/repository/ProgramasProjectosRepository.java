package com.minea.sisas.repository;

import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.service.dto.ProgramasProjectosDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.time.LocalDate;
import java.util.List;


/**
 * Spring Data JPA repository for the ProgramasProjectos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProgramasProjectosRepository extends JpaRepository<ProgramasProjectos, Long>, JpaSpecificationExecutor<ProgramasProjectos> {
    @Query("select p from ProgramasProjectos p where p.status = true AND LOWER(p.nmDescricaoProjeto) like LOWER(concat(:nome,'%'))" +
        "or LOWER(p.nmDesignacaoProjeto) like LOWER(CONCAT(:nome,'%'))" +
        "or LOWER(p.usuario) like LOWER(CONCAT(:nome,'%'))"+
        "or LOWER(p.idSaaAssociado) like LOWER(CONCAT(:nome,'%'))"+
        "or LOWER(p.tipoFinanciamento) like LOWER(CONCAT(:nome,'%'))"+
        "or LOWER(p.comuna) like LOWER(CONCAT(:nome,'%'))")
    List<ProgramasProjectos> buscarPorNome(@Param("nome") String nome);

    Page<ProgramasProjectos> findAllByStatusIsTrue(Pageable pageable);

    @Query("select p from ProgramasProjectos p where p.status =1 and p.provincia.id = :provinciaId")
    Page<ProgramasProjectos> findAllByStatusIsTrueAndProvinciaId(@Param("provinciaId") Long provinciaId, Pageable pageable);

    //PROVINCIA
    List<ProgramasProjectos> findAllByProvinciaNmProvinciaEquals(String nmProvincia);

    //MUNICIPIO
    List<ProgramasProjectos> findAllByMunicipioNmMunicipioEquals(String nmMunicipio);

    //COMUNA
    List<ProgramasProjectos> findAllByComunaNmComunaEquals(String nmComuna);

    //PERIODO
    @Query(value = "from ProgramasProjectos t where t.dtLancamento BETWEEN :startDate AND :endDate")
    public List<ProgramasProjectos> getAllBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate")LocalDate endDate);
}
