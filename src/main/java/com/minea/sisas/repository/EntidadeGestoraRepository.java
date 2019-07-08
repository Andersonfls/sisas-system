package com.minea.sisas.repository;

import com.minea.sisas.domain.EntidadeGestora;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the EntidadeGestora entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntidadeGestoraRepository extends JpaRepository<EntidadeGestora, Long>, JpaSpecificationExecutor<EntidadeGestora> {

}
