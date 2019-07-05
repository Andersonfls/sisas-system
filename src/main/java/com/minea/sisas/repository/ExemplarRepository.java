package com.minea.sisas.repository;

import com.minea.sisas.domain.Exemplar;
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
public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {

    @Query("select e from Exemplar e where e.status ='0'")
    Page<Exemplar> exemplarDisponivel(Pageable pageable);

    @Query("select e from Exemplar e where e.obra.id = :obraId and e.status = '1'")
    List<Exemplar> findByObraId(@Param("obraId") Long obraId);

    @Query("select e from Exemplar e where e.obra.id = :obraId order by e.nome")
    Page<Exemplar> findByObraIdPage(@Param("obraId") Long obraId, Pageable pageable);

    @Query("select e from Exemplar e where e.obra.id = :obraId")
    List<Exemplar> findByObraIdReserva(@Param("obraId") Long obraId);

}
