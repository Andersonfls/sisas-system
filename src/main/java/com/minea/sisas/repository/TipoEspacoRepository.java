package com.minea.sisas.repository;

import com.minea.sisas.domain.TipoEspaco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the TipoEspaco entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoEspacoRepository extends JpaRepository<TipoEspaco, Long> {

    @Query("select t from TipoEspaco t where LOWER(t.nome) like LOWER(concat('%',:nome,'%'))")
    Page buscarPorNome(@Param("nome") String nome, Pageable pageable);

    @Query("select t from TipoEspaco t where t.idTipoEspacoPai = ?1")
    List<TipoEspaco> findByTipoEspacoIdPai(Long idTipoEspaco);
}
