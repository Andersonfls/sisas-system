package com.minea.sisas.repository;

import com.minea.sisas.domain.Editora;
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
public interface EditoraRepository extends JpaRepository<Editora, Long> {

    @Query("select e from Editora e where LOWER(e.nomeEditora) like LOWER(concat('%',:nomeEditora,'%'))")
    Page buscarPorNome(@Param("nomeEditora") String nomeEditora, Pageable pageable);

    @Query("select e from Editora e order by e.nomeEditora")
    Page<Editora> buscarEditora(Pageable pageable);
}
