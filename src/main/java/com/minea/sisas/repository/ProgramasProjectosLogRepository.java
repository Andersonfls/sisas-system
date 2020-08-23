package com.minea.sisas.repository;

import com.minea.sisas.domain.ProgramasProjectosLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ProgramasProjectosLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProgramasProjectosLogRepository extends JpaRepository<ProgramasProjectosLog, Long>, JpaSpecificationExecutor<ProgramasProjectosLog> {
    @Query("select p from ProgramasProjectosLog p where LOWER(p.usuario) like LOWER(concat(:nome,'%'))" +
        "or LOWER(p.acao) like LOWER(CONCAT(:nome,'%'))" +
        "or LOWER(p.usuario) like LOWER(CONCAT(:nome,'%'))"+
        "or LOWER(p.dtLog) like LOWER(CONCAT(:nome,'%'))"+
        "or LOWER(p.log) like LOWER(CONCAT(:nome,'%'))"+
        "or LOWER(p.programasProjectos) like LOWER(CONCAT(:nome,'%'))")
    Page buscarPorNome(@Param("nome") String nome, Pageable pageable);

    @Modifying
    @Query("delete from ProgramasProjectosLog s where s.usuario.id = ?1")
    void deleteLogByUserId(Long idUsuario);
}
