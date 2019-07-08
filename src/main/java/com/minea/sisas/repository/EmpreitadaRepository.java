package com.minea.sisas.repository;

import com.minea.sisas.domain.Empreitada;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Empreitada entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmpreitadaRepository extends JpaRepository<Empreitada, Long>, JpaSpecificationExecutor<Empreitada> {

}
