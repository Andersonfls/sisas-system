package com.minea.sisas.service;

import com.minea.sisas.domain.OrigemFinanciamento;
import com.minea.sisas.repository.OrigemFinanciamentoRepository;
import com.minea.sisas.service.dto.OrigemFinanciamentoDTO;
import com.minea.sisas.service.mapper.OrigemFinanciamentoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing OrigemFinanciamento.
 */
@Service
@Transactional
public class OrigemFinanciamentoService {

    private final Logger log = LoggerFactory.getLogger(OrigemFinanciamentoService.class);

    private final OrigemFinanciamentoRepository origemFinanciamentoRepository;

    private final OrigemFinanciamentoMapper origemFinanciamentoMapper;

    public OrigemFinanciamentoService(OrigemFinanciamentoRepository origemFinanciamentoRepository, OrigemFinanciamentoMapper origemFinanciamentoMapper) {
        this.origemFinanciamentoRepository = origemFinanciamentoRepository;
        this.origemFinanciamentoMapper = origemFinanciamentoMapper;
    }

    /**
     * Save a origemFinanciamento.
     *
     * @param origemFinanciamentoDTO the entity to save
     * @return the persisted entity
     */
    public OrigemFinanciamentoDTO save(OrigemFinanciamentoDTO origemFinanciamentoDTO) {
        log.debug("Request to save OrigemFinanciamento : {}", origemFinanciamentoDTO);
        OrigemFinanciamento origemFinanciamento = origemFinanciamentoMapper.toEntity(origemFinanciamentoDTO);
        origemFinanciamento = origemFinanciamentoRepository.save(origemFinanciamento);
        return origemFinanciamentoMapper.toDto(origemFinanciamento);
    }

    /**
     * Get all the origemFinanciamentos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<OrigemFinanciamentoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrigemFinanciamentos");
        return origemFinanciamentoRepository.findAll(pageable)
            .map(origemFinanciamentoMapper::toDto);
    }

    /**
     * Get one origemFinanciamento by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public OrigemFinanciamentoDTO findOne(Long id) {
        log.debug("Request to get OrigemFinanciamento : {}", id);
        OrigemFinanciamento origemFinanciamento = origemFinanciamentoRepository.findOne(id);
        return origemFinanciamentoMapper.toDto(origemFinanciamento);
    }

    /**
     * Delete the origemFinanciamento by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete OrigemFinanciamento : {}", id);
        origemFinanciamentoRepository.delete(id);
    }
}
