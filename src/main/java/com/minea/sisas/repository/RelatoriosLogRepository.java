package com.minea.sisas.repository;

import com.minea.sisas.domain.RelatoriosLog;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the RelatoriosLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelatoriosLogRepository extends JpaRepository<RelatoriosLog, Long>, JpaSpecificationExecutor<RelatoriosLog> {

    @Modifying
    @Query("delete from RelatoriosLog s where s.idUsuario = ?1")
    void deleteLogByUserId(Long idUsuario);
}
