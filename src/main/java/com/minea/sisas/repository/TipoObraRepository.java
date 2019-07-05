package com.minea.sisas.repository;

import com.minea.sisas.domain.TipoObra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Area entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoObraRepository extends JpaRepository<TipoObra, Long> {

    @Query("select t from TipoObra t where LOWER(t.nome) like LOWER(concat('%',:nome,'%'))")
    Page buscarPorNome(@Param("nome") String nome, Pageable pageable);

}
