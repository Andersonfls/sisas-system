package com.minea.sisas.repository;

import com.minea.sisas.domain.AutorObra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the AutorObra entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AutorObraRepository extends JpaRepository<AutorObra, Long> {

    @Query("select a from AutorObra a where a.obra.id = :obraId")
    List<AutorObra> findByAutorId(@Param("obraId") Long obraId);
}
