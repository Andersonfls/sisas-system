package com.minea.sisas.service;

import com.minea.sisas.domain.Projectos;
import com.minea.sisas.repository.ProjectosRepository;
import com.minea.sisas.service.dto.ProjectosDTO;
import com.minea.sisas.service.mapper.ProjectosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Projectos.
 */
@Service
@Transactional
public class ProjectosService {

    private final Logger log = LoggerFactory.getLogger(ProjectosService.class);

    private final ProjectosRepository projectosRepository;

    private final ProjectosMapper projectosMapper;

    public ProjectosService(ProjectosRepository projectosRepository, ProjectosMapper projectosMapper) {
        this.projectosRepository = projectosRepository;
        this.projectosMapper = projectosMapper;
    }

    /**
     * Save a projectos.
     *
     * @param projectosDTO the entity to save
     * @return the persisted entity
     */
    public ProjectosDTO save(ProjectosDTO projectosDTO) {
        log.debug("Request to save Projectos : {}", projectosDTO);
        Projectos projectos = projectosMapper.toEntity(projectosDTO);
        projectos = projectosRepository.save(projectos);
        return projectosMapper.toDto(projectos);
    }

    /**
     * Get all the projectos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ProjectosDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Projectos");
        return projectosRepository.findAll(pageable)
            .map(projectosMapper::toDto);
    }

    /**
     * Get one projectos by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public ProjectosDTO findOne(Long id) {
        log.debug("Request to get Projectos : {}", id);
        Projectos projectos = projectosRepository.findOne(id);
        return projectosMapper.toDto(projectos);
    }

    /**
     * Delete the projectos by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Projectos : {}", id);
        projectosRepository.delete(id);
    }
}
