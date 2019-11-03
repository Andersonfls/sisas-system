package com.minea.sisas.service;

import com.minea.sisas.domain.Concurso;
import com.minea.sisas.repository.ConcursoRepository;
import com.minea.sisas.service.dto.ConcursoDTO;
import com.minea.sisas.service.mapper.ConcursoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Concurso.
 */
@Service
@Transactional
public class ConcursoService {

    private final Logger log = LoggerFactory.getLogger(ConcursoService.class);

    private final ConcursoRepository concursoRepository;

    private final ConcursoMapper concursoMapper;

    public ConcursoService(ConcursoRepository concursoRepository, ConcursoMapper concursoMapper) {
        this.concursoRepository = concursoRepository;
        this.concursoMapper = concursoMapper;
    }

    /**
     * Save a concurso.
     *
     * @param concursoDTO the entity to save
     * @return the persisted entity
     */
    public ConcursoDTO save(ConcursoDTO concursoDTO) {
        log.debug("Request to save Concurso : {}", concursoDTO);
        Concurso concurso = concursoMapper.toEntity(concursoDTO);
        concurso = concursoRepository.save(concurso);
        return concursoMapper.toDto(concurso);
    }

    /**
     * Get all the concursos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ConcursoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Concursos");
        return concursoRepository.findAll(pageable)
            .map(concursoMapper::toDto);
    }

    /**
     * Get one concurso by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public ConcursoDTO findOne(Long id) {
        log.debug("Request to get Concurso : {}", id);
        Concurso concurso = concursoRepository.findOne(id);
        return concursoMapper.toDto(concurso);
    }

    @Transactional(readOnly = true)
    public ConcursoDTO findOneByProgramasProjectos(Long id) {
        log.debug("Request to get Concurso : {}", id);
        Concurso concurso = concursoRepository.findByProgramasProjectosId(id);
        return concursoMapper.toDto(concurso);
    }

    /**
     * Delete the concurso by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Concurso : {}", id);
        concursoRepository.delete(id);
    }
}
