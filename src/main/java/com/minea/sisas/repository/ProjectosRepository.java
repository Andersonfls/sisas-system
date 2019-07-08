package com.minea.sisas.repository;

import com.minea.sisas.domain.Projectos;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Projectos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectosRepository extends JpaRepository<Projectos, Long>, JpaSpecificationExecutor<Projectos> {

}
