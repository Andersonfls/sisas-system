package com.minea.sisas.repository;

import com.minea.sisas.domain.Publicacao;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Publicacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PublicacaoRepository extends JpaRepository<Publicacao, Long>, JpaSpecificationExecutor<Publicacao> {

}
