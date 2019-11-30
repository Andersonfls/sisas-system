package com.minea.sisas.service;

import com.minea.sisas.domain.FinalidadeProjeto;
import com.minea.sisas.repository.FinalidadeProjetoRepository;
import com.minea.sisas.service.dto.FinalidadeProjetoDTO;
import com.minea.sisas.service.mapper.FinalidadeProjetoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing FinalidadeProjeto.
 */
@Service
@Transactional
public class FinalidadeProjetoService {

    private final Logger log = LoggerFactory.getLogger(FinalidadeProjetoService.class);

    private final FinalidadeProjetoRepository finalidadeProjetoRepository;

    private final FinalidadeProjetoMapper finalidadeProjetoMapper;

    public FinalidadeProjetoService(FinalidadeProjetoRepository finalidadeProjetoRepository, FinalidadeProjetoMapper finalidadeProjetoMapper) {
        this.finalidadeProjetoRepository = finalidadeProjetoRepository;
        this.finalidadeProjetoMapper = finalidadeProjetoMapper;
    }

    /**
     * Save a finalidadeProjeto.
     *
     * @param finalidadeProjetoDTO the entity to save
     * @return the persisted entity
     */
    public FinalidadeProjetoDTO save(FinalidadeProjetoDTO finalidadeProjetoDTO) {
        log.debug("Request to save FinalidadeProjeto : {}", finalidadeProjetoDTO);
        FinalidadeProjeto finalidadeProjeto = finalidadeProjetoMapper.toEntity(finalidadeProjetoDTO);
        finalidadeProjeto = finalidadeProjetoRepository.save(finalidadeProjeto);
        return finalidadeProjetoMapper.toDto(finalidadeProjeto);
    }

    /**
     * Get all the finalidadeProjetos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<FinalidadeProjetoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FinalidadeProjetos");
        return finalidadeProjetoRepository.findAll(pageable)
            .map(finalidadeProjetoMapper::toDto);
    }

    /**
     * Get one finalidadeProjeto by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public FinalidadeProjeto findOne(Long id) {
        log.debug("Request to get FinalidadeProjeto : {}", id);
        return finalidadeProjetoRepository.findOne(id);
    }

    /**
     * Delete the finalidadeProjeto by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete FinalidadeProjeto : {}", id);
        finalidadeProjetoRepository.delete(id);
    }
}
