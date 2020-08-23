package com.minea.sisas.repository;

import com.minea.sisas.domain.SegurancaLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the SegurancaLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SegurancaLogRepository extends JpaRepository<SegurancaLog, Long>, JpaSpecificationExecutor<SegurancaLog> {

    @Query("select s from SegurancaLog s where LOWER(s.id) like LOWER(concat(:nome,'%'))" +
        "or LOWER(s.dtLog) like LOWER(CONCAT(:nome,'%'))" +
        "or LOWER(s.log) like LOWER(CONCAT(:nome,'%'))"+
        "or LOWER(s.acao) like LOWER(CONCAT(:nome,'%'))"+
        "or LOWER(s.idUsuario) like LOWER(CONCAT(:nome,'%'))")
    Page buscarPorNome(@Param("nome") String nome, Pageable pageable);

    @Modifying
    @Query(value = "delete from SegurancaLog s where s.idUsuarioAlterado = ?1 or s.idUsuario = ?1")
    void deleteLogByUserId(Long idUsuario);

}
