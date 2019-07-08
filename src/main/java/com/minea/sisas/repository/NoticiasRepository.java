package com.minea.sisas.repository;

import com.minea.sisas.domain.Noticias;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Noticias entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NoticiasRepository extends JpaRepository<Noticias, Long>, JpaSpecificationExecutor<Noticias> {

}
