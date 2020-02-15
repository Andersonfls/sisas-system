package com.minea.sisas.service;

import com.minea.sisas.domain.SobreDna;
import com.minea.sisas.repository.SobreDnaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing SobreDna.
 */
@Service
@Transactional
public class SobreDnaService {

    private final Logger log = LoggerFactory.getLogger(SobreDnaService.class);

    private final SobreDnaRepository sobreDnaRepository;

    public SobreDnaService(SobreDnaRepository sobreDnaRepository) {
        this.sobreDnaRepository = sobreDnaRepository;
    }

    /**
     * Save a sobreDna.
     *
     * @param sobreDna the entity to save
     * @return the persisted entity
     */
    public SobreDna save(SobreDna sobreDna) {
        log.debug("Request to save SobreDna : {}", sobreDna);
        return sobreDnaRepository.save(sobreDna);
    }

    /**
     * Get all the sobreDnas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<SobreDna> findAll(Pageable pageable) {
        log.debug("Request to get all SobreDnas");
        return sobreDnaRepository.findAll(pageable);
    }

    /**
     * Get one sobreDna by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public SobreDna findOne(Long id) {
        log.debug("Request to get SobreDna : {}", id);
        return sobreDnaRepository.findOne(id);
    }

    /**
     * Delete the sobreDna by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete SobreDna : {}", id);
        sobreDnaRepository.delete(id);
    }
}
