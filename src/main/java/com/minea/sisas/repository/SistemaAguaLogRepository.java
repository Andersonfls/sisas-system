package com.minea.sisas.repository;

import com.minea.sisas.domain.SistemaAguaLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the SistemaAguaLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SistemaAguaLogRepository extends JpaRepository<SistemaAguaLog, Long>, JpaSpecificationExecutor<SistemaAguaLog> {
    @Query("select s from SistemaAguaLog s where LOWER(s.id) like LOWER(concat(:nome,'%'))" +
        "or LOWER(s.dtLog) like LOWER(CONCAT(:nome,'%'))" +
        "or LOWER(s.log) like LOWER(CONCAT(:nome,'%'))"+
        "or LOWER(s.acao) like LOWER(CONCAT(:nome,'%'))"+
        "or LOWER(s.idSistemaAgua) like LOWER(CONCAT(:nome,'%'))"+
        "or LOWER(s.idUsuario) like LOWER(CONCAT(:nome,'%'))")
    Page buscarPorNome(@Param("nome") String nome, Pageable pageable);

    @Modifying
    @Query("delete from SistemaAguaLog s where s.idUsuario = ?1")
    void deleteLogByUserId(Long idUsuario);

}
