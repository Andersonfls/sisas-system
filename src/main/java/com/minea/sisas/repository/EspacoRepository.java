package com.minea.sisas.repository;

import com.minea.sisas.domain.Espaco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the Espaco entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EspacoRepository extends JpaRepository<Espaco, Long> {

    @Query("select e from Espaco e where e.codEspacoPai = ?1")
    List<Espaco> findByEspacoIdPai(Long idEspaco);

    @Query("select e from Espaco e where e.status = '1'")
    List<Espaco> findByAllList();

    @Query("select e from Espaco e where e.tipoEspaco.id = :idTipoEspacoPai and e.status = '1'")
    Page<Espaco> findByTipoEspacoPaiId(@Param("idTipoEspacoPai") Long idTipoEspacoPai, Pageable pageable);
}
