package com.minea.sisas.repository;

import com.minea.sisas.domain.AvariaSistemaAgua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data JPA repository for the SistemaAgua entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AvariaSistemaAguaRepository extends JpaRepository<AvariaSistemaAgua, Long>, JpaSpecificationExecutor<AvariaSistemaAgua> {

    @Query("select s from AvariaSistemaAgua s where s.idSistemaAgua = :idSistemaAgua")
    List<AvariaSistemaAgua> findAllBySistemAguaId(@Param("idSistemaAgua") Long idSistemaAgua);
}
