package com.minea.sisas.repository;

import com.minea.sisas.domain.MunicipiosAtendidos;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MunicipiosAtendidos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MunicipiosAtendidosRepository extends JpaRepository<MunicipiosAtendidos, Long>, JpaSpecificationExecutor<MunicipiosAtendidos> {

}
