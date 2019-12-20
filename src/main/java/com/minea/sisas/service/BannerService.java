package com.minea.sisas.service;

import com.minea.sisas.domain.Banner;
import com.minea.sisas.domain.NoticiasPortal;
import com.minea.sisas.repository.BannerRepository;
import com.minea.sisas.repository.NoticiasPortalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Banner.
 */
@Service
@Transactional
public class BannerService {

    private final Logger log = LoggerFactory.getLogger(BannerService.class);

    private final BannerRepository bannerRepository;

    public BannerService(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    /**
     * Save a Banner.
     *
     * @param banner the entity to save
     * @return the persisted entity
     */
    public Banner save(Banner banner) {
        log.debug("Request to save NoticiasPortal : {}", banner);
        return bannerRepository.save(banner);
    }

    /**
     * Get all the Banners.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Banner> findAll(Pageable pageable) {
        log.debug("Request to get all NoticiasPortals");
        return bannerRepository.findAll(pageable);
    }

    /**
     * Get one banner by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Banner findOne(Long id) {
        log.debug("Request to get Banner : {}", id);
        return bannerRepository.findOne(id);
    }

    /**
     * Delete the Banner by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Banner : {}", id);
        bannerRepository.delete(id);
    }
}
