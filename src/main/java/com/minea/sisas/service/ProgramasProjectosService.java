package com.minea.sisas.service;

import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.repository.ProgramasProjectosRepository;
import com.minea.sisas.service.dto.ProgramasProjectosDTO;
import com.minea.sisas.service.mapper.ProgramasProjectosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing ProgramasProjectos.
 */
@Service
@Transactional
public class ProgramasProjectosService {

    private final Logger log = LoggerFactory.getLogger(ProgramasProjectosService.class);

    private final ProgramasProjectosRepository programasProjectosRepository;

    private final ProgramasProjectosMapper programasProjectosMapper;

    public ProgramasProjectosService(ProgramasProjectosRepository programasProjectosRepository, ProgramasProjectosMapper programasProjectosMapper) {
        this.programasProjectosRepository = programasProjectosRepository;
        this.programasProjectosMapper = programasProjectosMapper;
    }

    /**
     * Save a programasProjectos.
     *
     * @param programasProjectosDTO the entity to save
     * @return the persisted entity
     */
    public ProgramasProjectosDTO save(ProgramasProjectosDTO programasProjectosDTO) {
        log.debug("Request to save ProgramasProjectos : {}", programasProjectosDTO);
        ProgramasProjectos programasProjectos = programasProjectosMapper.toEntity(programasProjectosDTO);
        programasProjectos = programasProjectosRepository.save(programasProjectos);
        return programasProjectosMapper.toDto(programasProjectos);
    }

    /**
     * Get all the programasProjectos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ProgramasProjectosDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProgramasProjectos");
        return programasProjectosRepository.findAll(pageable)
            .map(programasProjectosMapper::toDto);
    }

    /**
     * Get one programasProjectos by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public ProgramasProjectosDTO findOne(Long id) {
        log.debug("Request to get ProgramasProjectos : {}", id);
        ProgramasProjectos programasProjectos = programasProjectosRepository.findOne(id);
        return programasProjectosMapper.toDto(programasProjectos);
    }

    /**
     * Delete the programasProjectos by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ProgramasProjectos : {}", id);
        programasProjectosRepository.delete(id);
    }
}
