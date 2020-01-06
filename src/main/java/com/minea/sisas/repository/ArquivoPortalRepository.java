package com.minea.sisas.repository;

import com.minea.sisas.domain.ArquivoPortal;
import com.minea.sisas.domain.Concurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the Concurso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArquivoPortalRepository extends JpaRepository<ArquivoPortal, Long>, JpaSpecificationExecutor<ArquivoPortal> {

}
