package com.minea.sisas.service;

import com.minea.sisas.domain.NoticiasPortal;
import com.minea.sisas.repository.NoticiasPortalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing NoticiasPortal.
 */
@Service
@Transactional
public class NoticiasPortalService {

    private final Logger log = LoggerFactory.getLogger(NoticiasPortalService.class);

    private final NoticiasPortalRepository noticiasPortalRepository;

    public NoticiasPortalService(NoticiasPortalRepository noticiasPortalRepository) {
        this.noticiasPortalRepository = noticiasPortalRepository;
    }

    /**
     * Save a noticiasPortal.
     *
     * @param noticiasPortal the entity to save
     * @return the persisted entity
     */
    public NoticiasPortal save(NoticiasPortal noticiasPortal) {
        log.debug("Request to save NoticiasPortal : {}", noticiasPortal);
        return noticiasPortalRepository.save(noticiasPortal);
    }

    /**
     * Get all the noticiasPortals.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<NoticiasPortal> findAll(Pageable pageable) {
        log.debug("Request to get all NoticiasPortals");
        return noticiasPortalRepository.findAll(pageable);
    }

    /**
     * Get one noticiasPortal by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public NoticiasPortal findOne(Long id) {
        log.debug("Request to get NoticiasPortal : {}", id);
        return noticiasPortalRepository.findOne(id);
    }

    /**
     * Delete the noticiasPortal by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete NoticiasPortal : {}", id);
        noticiasPortalRepository.delete(id);
    }
}
