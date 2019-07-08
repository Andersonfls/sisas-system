package com.minea.sisas.repository;

import com.minea.sisas.domain.OrigemFinanciamento;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the OrigemFinanciamento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrigemFinanciamentoRepository extends JpaRepository<OrigemFinanciamento, Long>, JpaSpecificationExecutor<OrigemFinanciamento> {

}
