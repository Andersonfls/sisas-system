package com.minea.sisas.service;

import com.minea.sisas.domain.EntidadeGestora;
import com.minea.sisas.repository.EntidadeGestoraRepository;
import com.minea.sisas.service.dto.EntidadeGestoraDTO;
import com.minea.sisas.service.mapper.EntidadeGestoraMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing EntidadeGestora.
 */
@Service
@Transactional
public class EntidadeGestoraService {

    private final Logger log = LoggerFactory.getLogger(EntidadeGestoraService.class);

    private final EntidadeGestoraRepository entidadeGestoraRepository;

    private final EntidadeGestoraMapper entidadeGestoraMapper;

    public EntidadeGestoraService(EntidadeGestoraRepository entidadeGestoraRepository, EntidadeGestoraMapper entidadeGestoraMapper) {
        this.entidadeGestoraRepository = entidadeGestoraRepository;
        this.entidadeGestoraMapper = entidadeGestoraMapper;
    }

    /**
     * Save a entidadeGestora.
     *
     * @param entidadeGestoraDTO the entity to save
     * @return the persisted entity
     */
    public EntidadeGestoraDTO save(EntidadeGestoraDTO entidadeGestoraDTO) {
        log.debug("Request to save EntidadeGestora : {}", entidadeGestoraDTO);
        EntidadeGestora entidadeGestora = entidadeGestoraMapper.toEntity(entidadeGestoraDTO);
        entidadeGestora = entidadeGestoraRepository.save(entidadeGestora);
        return entidadeGestoraMapper.toDto(entidadeGestora);
    }

    /**
     * Get all the entidadeGestoras.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<EntidadeGestoraDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EntidadeGestoras");
        return entidadeGestoraRepository.findAll(pageable)
            .map(entidadeGestoraMapper::toDto);
    }

    /**
     * Get one entidadeGestora by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public EntidadeGestoraDTO findOne(Long id) {
        log.debug("Request to get EntidadeGestora : {}", id);
        EntidadeGestora entidadeGestora = entidadeGestoraRepository.findOne(id);
        return entidadeGestoraMapper.toDto(entidadeGestora);
    }

    /**
     * Delete the entidadeGestora by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete EntidadeGestora : {}", id);
        entidadeGestoraRepository.delete(id);
    }
}
