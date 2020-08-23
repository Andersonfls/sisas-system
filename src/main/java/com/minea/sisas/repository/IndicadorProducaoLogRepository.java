package com.minea.sisas.repository;

import com.minea.sisas.domain.IndicadorProducaoLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the IndicadorProducaoLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IndicadorProducaoLogRepository extends JpaRepository<IndicadorProducaoLog, Long>, JpaSpecificationExecutor<IndicadorProducaoLog> {
    @Query("select i from IndicadorProducaoLog i where LOWER(i.idUsuario) like LOWER(concat(:nome,'%'))" +
        "or LOWER(i.id) like LOWER(CONCAT(:nome,'%'))" +
        "or LOWER(i.acao) like LOWER(CONCAT(:nome,'%'))"+
        "or LOWER(i.log) like LOWER(CONCAT(:nome,'%'))"+
        "or LOWER(i.dtLog) like LOWER(CONCAT(:nome,'%'))"+
        "or LOWER(i.idIndicadorProducao) like LOWER(CONCAT(:nome,'%'))")
    Page buscarPorNome(@Param("nome") String nome, Pageable pageable);

    @Modifying
    @Query("delete from IndicadorProducaoLog s where s.idUsuario = ?1")
    void deleteLogByUserId(Long idUsuario);
}
