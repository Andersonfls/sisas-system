package com.minea.sisas.repository;

import com.minea.sisas.domain.NoticiasPortal;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the NoticiasPortal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NoticiasPortalRepository extends JpaRepository<NoticiasPortal, Long> {

}
