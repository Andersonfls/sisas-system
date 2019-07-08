package com.minea.sisas.repository;

import com.minea.sisas.domain.Contactos;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Contactos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContactosRepository extends JpaRepository<Contactos, Long>, JpaSpecificationExecutor<Contactos> {

}
