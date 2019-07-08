package com.minea.sisas.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.minea.sisas.domain.Projectos;
import com.minea.sisas.domain.*; // for static metamodels
import com.minea.sisas.repository.ProjectosRepository;
import com.minea.sisas.service.dto.ProjectosCriteria;

import com.minea.sisas.service.dto.ProjectosDTO;
import com.minea.sisas.service.mapper.ProjectosMapper;

/**
 * Service for executing complex queries for Projectos entities in the database.
 * The main input is a {@link ProjectosCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProjectosDTO} or a {@link Page} of {@link ProjectosDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProjectosQueryService extends QueryService<Projectos> {

    private final Logger log = LoggerFactory.getLogger(ProjectosQueryService.class);


    private final ProjectosRepository projectosRepository;

    private final ProjectosMapper projectosMapper;

    public ProjectosQueryService(ProjectosRepository projectosRepository, ProjectosMapper projectosMapper) {
        this.projectosRepository = projectosRepository;
        this.projectosMapper = projectosMapper;
    }

    /**
     * Return a {@link List} of {@link ProjectosDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProjectosDTO> findByCriteria(ProjectosCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Projectos> specification = createSpecification(criteria);
        return projectosMapper.toDto(projectosRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProjectosDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProjectosDTO> findByCriteria(ProjectosCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Projectos> specification = createSpecification(criteria);
        final Page<Projectos> result = projectosRepository.findAll(specification, page);
        return result.map(projectosMapper::toDto);
    }

    /**
     * Function to convert ProjectosCriteria to a {@link Specifications}
     */
    private Specifications<Projectos> createSpecification(ProjectosCriteria criteria) {
        Specifications<Projectos> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Projectos_.id));
            }
            if (criteria.getNmProjectos() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmProjectos(), Projectos_.nmProjectos));
            }
            if (criteria.getTextoProjectos() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTextoProjectos(), Projectos_.textoProjectos));
            }
            if (criteria.getResumoTextoProjectos() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResumoTextoProjectos(), Projectos_.resumoTextoProjectos));
            }
            if (criteria.getIdSituacaoId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdSituacaoId(), Projectos_.idSituacao, Situacao_.id));
            }
            if (criteria.getInicioId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getInicioId(), Projectos_.inicios, Inicio_.id));
            }
        }
        return specification;
    }

}
