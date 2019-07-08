package com.minea.sisas.service;

import com.minea.sisas.domain.Fases;
import com.minea.sisas.repository.FasesRepository;
import com.minea.sisas.service.dto.FasesDTO;
import com.minea.sisas.service.mapper.FasesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Fases.
 */
@Service
@Transactional
public class FasesService {

    private final Logger log = LoggerFactory.getLogger(FasesService.class);

    private final FasesRepository fasesRepository;

    private final FasesMapper fasesMapper;

    public FasesService(FasesRepository fasesRepository, FasesMapper fasesMapper) {
        this.fasesRepository = fasesRepository;
        this.fasesMapper = fasesMapper;
    }

    /**
     * Save a fases.
     *
     * @param fasesDTO the entity to save
     * @return the persisted entity
     */
    public FasesDTO save(FasesDTO fasesDTO) {
        log.debug("Request to save Fases : {}", fasesDTO);
        Fases fases = fasesMapper.toEntity(fasesDTO);
        fases = fasesRepository.save(fases);
        return fasesMapper.toDto(fases);
    }

    /**
     * Get all the fases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<FasesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Fases");
        return fasesRepository.findAll(pageable)
            .map(fasesMapper::toDto);
    }

    /**
     * Get one fases by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public FasesDTO findOne(Long id) {
        log.debug("Request to get Fases : {}", id);
        Fases fases = fasesRepository.findOne(id);
        return fasesMapper.toDto(fases);
    }

    /**
     * Delete the fases by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Fases : {}", id);
        fasesRepository.delete(id);
    }
}
