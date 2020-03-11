package com.minea.sisas.repository;

import com.minea.sisas.domain.SistemaAgua;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


/**
 * Spring Data JPA repository for the SistemaAgua entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SistemaAguaRepository extends JpaRepository<SistemaAgua, Long>, JpaSpecificationExecutor<SistemaAgua> {

    Page<SistemaAgua> findAllByStatusIsTrue(Pageable pageable);

    @Query("select s from SistemaAgua s where s.status =1 and s.provincia.id = :provinciaId")
    Page<SistemaAgua> findAllByStatusIsTrueAndProvinciaId(@Param("provinciaId") Long provinciaId, Pageable pageable);

    @Query("select s from SistemaAgua s where LOWER(s.nmSistemaAgua) like LOWER(concat(:nome,'%')) " +
        "or LOWER(s.nmFonteAgua) like LOWER(concat(:nome,'%'))" +
        "or LOWER(s.nmLocalidade) like LOWER(concat(:nome,'%'))" +
        "or LOWER(s.nmTpArea) like LOWER(concat(:nome,'%'))")
    Page buscarPorNome(@Param("nome") String nome, Pageable pageable);

    //PROVINCIA
    List<SistemaAgua> findAllByProvinciaNmProvinciaEquals(String nmProvincia);

    //MUNICIPIO
    List<SistemaAgua> findAllByMunicipioNmMunicipioEquals(String nmMunicipio);

    //COMUNA
    List<SistemaAgua> findAllByComunaNmComunaEquals(String nmComuna);

    //PERIODO
    @Query(value = "from SistemaAgua t where t.dtLancamento BETWEEN :startDate AND :endDate")
    public List<SistemaAgua> getAllBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate")LocalDate endDate);
}
