package com.minea.sisas.repository;

import com.minea.sisas.domain.Banner;
import com.minea.sisas.domain.NoticiasPortal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the Banner entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {

}
