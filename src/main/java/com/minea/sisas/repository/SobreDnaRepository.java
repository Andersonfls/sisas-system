package com.minea.sisas.repository;

import com.minea.sisas.domain.SobreDna;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the SobreDna entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SobreDnaRepository extends JpaRepository<SobreDna, Long>, JpaSpecificationExecutor<SobreDna> {

}
