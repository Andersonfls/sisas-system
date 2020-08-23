package com.minea.sisas.repository;

import com.minea.sisas.domain.PublicacaoLog;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PublicacaoLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PublicacaoLogRepository extends JpaRepository<PublicacaoLog, Long>, JpaSpecificationExecutor<PublicacaoLog> {

    @Modifying
    @Query("delete from PublicacaoLog s where s.idUsuario = ?1")
    void deleteLogByUserId(Long idUsuario);
}
