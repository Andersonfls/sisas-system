package com.minea.sisas.service;

import com.minea.sisas.domain.Noticias;
import com.minea.sisas.repository.NoticiasRepository;
import com.minea.sisas.service.dto.NoticiasDTO;
import com.minea.sisas.service.mapper.NoticiasMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Noticias.
 */
@Service
@Transactional
public class NoticiasService {

    private final Logger log = LoggerFactory.getLogger(NoticiasService.class);

    private final NoticiasRepository noticiasRepository;

    private final NoticiasMapper noticiasMapper;

    public NoticiasService(NoticiasRepository noticiasRepository, NoticiasMapper noticiasMapper) {
        this.noticiasRepository = noticiasRepository;
        this.noticiasMapper = noticiasMapper;
    }

    /**
     * Save a noticias.
     *
     * @param noticiasDTO the entity to save
     * @return the persisted entity
     */
    public NoticiasDTO save(NoticiasDTO noticiasDTO) {
        log.debug("Request to save Noticias : {}", noticiasDTO);
        Noticias noticias = noticiasMapper.toEntity(noticiasDTO);
        noticias = noticiasRepository.save(noticias);
        return noticiasMapper.toDto(noticias);
    }

    /**
     * Get all the noticias.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<NoticiasDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Noticias");
        return noticiasRepository.findAll(pageable)
            .map(noticiasMapper::toDto);
    }

    /**
     * Get one noticias by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public NoticiasDTO findOne(Long id) {
        log.debug("Request to get Noticias : {}", id);
        Noticias noticias = noticiasRepository.findOne(id);
        return noticiasMapper.toDto(noticias);
    }

    /**
     * Delete the noticias by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Noticias : {}", id);
        noticiasRepository.delete(id);
    }
}
