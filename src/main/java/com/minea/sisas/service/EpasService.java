package com.minea.sisas.service;

import com.minea.sisas.domain.Epas;
import com.minea.sisas.repository.EpasRepository;
import com.minea.sisas.service.dto.EpasDTO;
import com.minea.sisas.service.mapper.EpasMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Epas.
 */
@Service
@Transactional
public class EpasService {

    private final Logger log = LoggerFactory.getLogger(EpasService.class);

    private final EpasRepository epasRepository;

    private final EpasMapper epasMapper;

    public EpasService(EpasRepository epasRepository, EpasMapper epasMapper) {
        this.epasRepository = epasRepository;
        this.epasMapper = epasMapper;
    }

    /**
     * Save a epas.
     *
     * @param epasDTO the entity to save
     * @return the persisted entity
     */
    public EpasDTO save(EpasDTO epasDTO) {
        log.debug("Request to save Epas : {}", epasDTO);
        Epas epas = epasMapper.toEntity(epasDTO);
        epas = epasRepository.save(epas);
        return epasMapper.toDto(epas);
    }

    /**
     * Get all the epass.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<EpasDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Epass");
        return epasRepository.findAll(pageable)
            .map(epasMapper::toDto);
    }

    /**
     * Get one epas by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Epas findOne(Long id) {
        log.debug("Request to get Epas : {}", id);
        return epasRepository.findOne(id);
    }

    /**
     * Delete the epas by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Epas : {}", id);
        epasRepository.delete(id);
    }
}
