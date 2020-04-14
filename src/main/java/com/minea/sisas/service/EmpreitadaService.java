package com.minea.sisas.service;

import com.minea.sisas.domain.Contrato;
import com.minea.sisas.domain.Empreitada;
import com.minea.sisas.repository.EmpreitadaRepository;
import com.minea.sisas.service.dto.ContratoDTO;
import com.minea.sisas.service.dto.EmpreitadaDTO;
import com.minea.sisas.service.mapper.EmpreitadaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


/**
 * Service Implementation for managing Empreitada.
 */
@Service
@Transactional
public class EmpreitadaService {

    private final Logger log = LoggerFactory.getLogger(EmpreitadaService.class);

    private final EmpreitadaRepository empreitadaRepository;

    private final EmpreitadaMapper empreitadaMapper;

    public EmpreitadaService(EmpreitadaRepository empreitadaRepository, EmpreitadaMapper empreitadaMapper) {
        this.empreitadaRepository = empreitadaRepository;
        this.empreitadaMapper = empreitadaMapper;
    }

    /**
     * Save a empreitada.
     *
     * @param empreitadaDTO the entity to save
     * @return the persisted entity
     */
    public EmpreitadaDTO save(EmpreitadaDTO empreitadaDTO) {
        log.debug("Request to save Empreitada : {}", empreitadaDTO);
        Empreitada empreitada = empreitadaMapper.toEntity(empreitadaDTO);
        empreitada = empreitadaRepository.save(empreitada);
        return empreitadaMapper.toDto(empreitada);
    }

    /**
     * Get all the empreitadas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<EmpreitadaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Empreitadas");
        return empreitadaRepository.findAll(pageable)
            .map(empreitadaMapper::toDto);
    }

    /**
     * Get one empreitada by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public EmpreitadaDTO findOne(Long id) {
        log.debug("Request to get Empreitada : {}", id);
        Empreitada empreitada = empreitadaRepository.findOne(id);
        return empreitadaMapper.toDto(empreitada);
    }

    @Transactional(readOnly = true)
    public EmpreitadaDTO findOneByProgramasProjectos(Long id) {
        log.debug("Request to get Empreitada : {}", id);
        Empreitada empreitada = empreitadaRepository.findByIdProgramasProjectosId(id);
        if (Objects.isNull(empreitada)) {
            empreitada = new Empreitada();
        }
        return empreitadaMapper.toDto(empreitada);
    }

    /**
     * Delete the empreitada by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Empreitada : {}", id);
        empreitadaRepository.delete(id);
    }
}
