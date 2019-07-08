package com.minea.sisas.service;

import com.minea.sisas.domain.Provincia;
import com.minea.sisas.repository.ProvinciaRepository;
import com.minea.sisas.service.dto.ProvinciaDTO;
import com.minea.sisas.service.mapper.ProvinciaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Provincia.
 */
@Service
@Transactional
public class ProvinciaService {

    private final Logger log = LoggerFactory.getLogger(ProvinciaService.class);

    private final ProvinciaRepository provinciaRepository;

    private final ProvinciaMapper provinciaMapper;

    public ProvinciaService(ProvinciaRepository provinciaRepository, ProvinciaMapper provinciaMapper) {
        this.provinciaRepository = provinciaRepository;
        this.provinciaMapper = provinciaMapper;
    }

    /**
     * Save a provincia.
     *
     * @param provinciaDTO the entity to save
     * @return the persisted entity
     */
    public ProvinciaDTO save(ProvinciaDTO provinciaDTO) {
        log.debug("Request to save Provincia : {}", provinciaDTO);
        Provincia provincia = provinciaMapper.toEntity(provinciaDTO);
        provincia = provinciaRepository.save(provincia);
        return provinciaMapper.toDto(provincia);
    }

    /**
     * Get all the provincias.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ProvinciaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Provincias");
        return provinciaRepository.findAll(pageable)
            .map(provinciaMapper::toDto);
    }

    /**
     * Get one provincia by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public ProvinciaDTO findOne(Long id) {
        log.debug("Request to get Provincia : {}", id);
        Provincia provincia = provinciaRepository.findOne(id);
        return provinciaMapper.toDto(provincia);
    }

    /**
     * Delete the provincia by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Provincia : {}", id);
        provinciaRepository.delete(id);
    }
}
