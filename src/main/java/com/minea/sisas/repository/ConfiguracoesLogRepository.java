package com.minea.sisas.repository;

import com.minea.sisas.domain.ConfiguracoesLog;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ConfiguracoesLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConfiguracoesLogRepository extends JpaRepository<ConfiguracoesLog, Long>, JpaSpecificationExecutor<ConfiguracoesLog> {

    @Modifying
    @Query("delete from ConfiguracoesLog s where s.idUsuario = ?1")
    void deleteLogByUserId(Long idUsuario);
}
