package com.minea.sisas.service;

import com.minea.sisas.domain.Contrato;
import com.minea.sisas.repository.ContratoRepository;
import com.minea.sisas.service.dto.ContratoDTO;
import com.minea.sisas.service.mapper.ContratoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


/**
 * Service Implementation for managing Contrato.
 */
@Service
@Transactional
public class ContratoService {

    private final Logger log = LoggerFactory.getLogger(ContratoService.class);

    private final ContratoRepository contratoRepository;

    private final ContratoMapper contratoMapper;

    public ContratoService(ContratoRepository contratoRepository, ContratoMapper contratoMapper) {
        this.contratoRepository = contratoRepository;
        this.contratoMapper = contratoMapper;
    }

    /**
     * Save a contrato.
     *
     * @param contratoDTO the entity to save
     * @return the persisted entity
     */
    public ContratoDTO save(ContratoDTO contratoDTO) {
        log.debug("Request to save Contrato : {}", contratoDTO);
        Contrato contrato = contratoMapper.toEntity(contratoDTO);
        contrato = contratoRepository.save(contrato);
        return contratoMapper.toDto(contrato);
    }

    /**
     * Get all the contratoes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ContratoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Contratoes");
        return contratoRepository.findAll(pageable)
            .map(contratoMapper::toDto);
    }

    /**
     * Get one contrato by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public ContratoDTO findOne(Long id) {
        log.debug("Request to get Contrato : {}", id);
        Contrato contrato = contratoRepository.findOne(id);
        return contratoMapper.toDto(contrato);
    }

    @Transactional(readOnly = true)
    public ContratoDTO findOneByProgramasProjectos(Long id) {
        log.debug("Request to get Contrato : {}", id);
        Contrato contrato = contratoRepository.findByProgramasProjectosId(id);
        if (Objects.isNull(contrato)) {
            contrato = new Contrato();
        }
        return contratoMapper.toDto(contrato);
    }

    /**
     * Delete the contrato by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Contrato : {}", id);
        contratoRepository.delete(id);
    }
}
