package com.minea.sisas.service;

import com.minea.sisas.domain.Comuna;
import com.minea.sisas.repository.ComunaRepository;
import com.minea.sisas.service.dto.ComunaDTO;
import com.minea.sisas.service.mapper.ComunaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Comuna.
 */
@Service
@Transactional
public class ComunaService {

    private final Logger log = LoggerFactory.getLogger(ComunaService.class);

    private final ComunaRepository comunaRepository;

    private final ComunaMapper comunaMapper;

    public ComunaService(ComunaRepository comunaRepository, ComunaMapper comunaMapper) {
        this.comunaRepository = comunaRepository;
        this.comunaMapper = comunaMapper;
    }

    /**
     * Save a comuna.
     *
     * @param comunaDTO the entity to save
     * @return the persisted entity
     */
    public ComunaDTO save(ComunaDTO comunaDTO) {
        log.debug("Request to save Comuna : {}", comunaDTO);
        Comuna comuna = comunaMapper.toEntity(comunaDTO);
        comuna = comunaRepository.save(comuna);
        return comunaMapper.toDto(comuna);
    }

    /**
     * Get all the comunas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ComunaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Comunas");
        return comunaRepository.findAll(pageable)
            .map(comunaMapper::toDto);
    }

    /**
     * Get one comuna by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Comuna findOne(Long id) {
        log.debug("Request to get Comuna : {}", id);
        return comunaRepository.findOne(id);
    }

    /**
     * Delete the comuna by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Comuna : {}", id);
        comunaRepository.delete(id);
    }
}
