package com.minea.sisas.service;

import com.minea.sisas.domain.Inicio;
import com.minea.sisas.repository.InicioRepository;
import com.minea.sisas.service.dto.InicioDTO;
import com.minea.sisas.service.mapper.InicioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Inicio.
 */
@Service
@Transactional
public class InicioService {

    private final Logger log = LoggerFactory.getLogger(InicioService.class);

    private final InicioRepository inicioRepository;

    private final InicioMapper inicioMapper;

    public InicioService(InicioRepository inicioRepository, InicioMapper inicioMapper) {
        this.inicioRepository = inicioRepository;
        this.inicioMapper = inicioMapper;
    }

    /**
     * Save a inicio.
     *
     * @param inicioDTO the entity to save
     * @return the persisted entity
     */
    public InicioDTO save(InicioDTO inicioDTO) {
        log.debug("Request to save Inicio : {}", inicioDTO);
        Inicio inicio = inicioMapper.toEntity(inicioDTO);
        inicio = inicioRepository.save(inicio);
        return inicioMapper.toDto(inicio);
    }

    /**
     * Get all the inicios.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<InicioDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Inicios");
        return inicioRepository.findAll(pageable)
            .map(inicioMapper::toDto);
    }

    /**
     * Get one inicio by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public InicioDTO findOne(Long id) {
        log.debug("Request to get Inicio : {}", id);
        Inicio inicio = inicioRepository.findOne(id);
        return inicioMapper.toDto(inicio);
    }

    /**
     * Delete the inicio by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Inicio : {}", id);
        inicioRepository.delete(id);
    }
}
