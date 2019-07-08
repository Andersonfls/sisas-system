package com.minea.sisas.service;

import com.minea.sisas.domain.SobreDna;
import com.minea.sisas.repository.SobreDnaRepository;
import com.minea.sisas.service.dto.SobreDnaDTO;
import com.minea.sisas.service.mapper.SobreDnaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing SobreDna.
 */
@Service
@Transactional
public class SobreDnaService {

    private final Logger log = LoggerFactory.getLogger(SobreDnaService.class);

    private final SobreDnaRepository sobreDnaRepository;

    private final SobreDnaMapper sobreDnaMapper;

    public SobreDnaService(SobreDnaRepository sobreDnaRepository, SobreDnaMapper sobreDnaMapper) {
        this.sobreDnaRepository = sobreDnaRepository;
        this.sobreDnaMapper = sobreDnaMapper;
    }

    /**
     * Save a sobreDna.
     *
     * @param sobreDnaDTO the entity to save
     * @return the persisted entity
     */
    public SobreDnaDTO save(SobreDnaDTO sobreDnaDTO) {
        log.debug("Request to save SobreDna : {}", sobreDnaDTO);
        SobreDna sobreDna = sobreDnaMapper.toEntity(sobreDnaDTO);
        sobreDna = sobreDnaRepository.save(sobreDna);
        return sobreDnaMapper.toDto(sobreDna);
    }

    /**
     * Get all the sobreDnas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<SobreDnaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SobreDnas");
        return sobreDnaRepository.findAll(pageable)
            .map(sobreDnaMapper::toDto);
    }

    /**
     * Get one sobreDna by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public SobreDnaDTO findOne(Long id) {
        log.debug("Request to get SobreDna : {}", id);
        SobreDna sobreDna = sobreDnaRepository.findOne(id);
        return sobreDnaMapper.toDto(sobreDna);
    }

    /**
     * Delete the sobreDna by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete SobreDna : {}", id);
        sobreDnaRepository.delete(id);
    }
}
