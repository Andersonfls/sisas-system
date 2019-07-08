package com.minea.sisas.repository;

import com.minea.sisas.domain.Inicio;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Inicio entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InicioRepository extends JpaRepository<Inicio, Long>, JpaSpecificationExecutor<Inicio> {

}
