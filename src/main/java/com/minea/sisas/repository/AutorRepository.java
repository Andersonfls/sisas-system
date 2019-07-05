package com.minea.sisas.repository;


import com.minea.sisas.domain.Autor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Autor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("select a from Autor a order by a.nome")
    Page<Autor> buscarAutor(Pageable pageable);

    @Query("select a from Autor a where a.status='1' order by a.nome")
    Page<Autor> buscarAutorStatus(Pageable pageable);

    @Query("select a from Autor a where LOWER(a.nome) like LOWER(concat('%',:nome,'%'))")
    Page buscarPorNome(@Param("nome") String nome, Pageable pageable);
}
