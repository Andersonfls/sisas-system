package com.minea.sisas.repository;

import com.minea.sisas.domain.Execucao;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Execucao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExecucaoRepository extends JpaRepository<Execucao, Long>, JpaSpecificationExecutor<Execucao> {

}
