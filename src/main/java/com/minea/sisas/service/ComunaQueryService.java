package com.minea.sisas.service;


import java.util.List;

import com.minea.sisas.service.mapper.ComunaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.minea.sisas.domain.Comuna;
import com.minea.sisas.domain.*; // for static metamodels
import com.minea.sisas.repository.ComunaRepository;
import com.minea.sisas.service.dto.ComunaCriteria;

import com.minea.sisas.service.dto.ComunaDTO;
import com.minea.sisas.service.mapper.ComunaMapper;

/**
 * Service for executing complex queries for Comuna entities in the database.
 * The main input is a {@link ComunaCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ComunaDTO} or a {@link Page} of {@link ComunaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ComunaQueryService extends QueryService<Comuna> {

    private final Logger log = LoggerFactory.getLogger(ComunaQueryService.class);


    private final ComunaRepository comunaRepository;

    private final ComunaMapper comunaMapper;

    public ComunaQueryService(ComunaRepository comunaRepository, ComunaMapper comunaMapper) {
        this.comunaRepository = comunaRepository;
        this.comunaMapper = comunaMapper;
    }

    /**
     * Return a {@link List} of {@link ComunaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ComunaDTO> findByCriteria(ComunaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Comuna> specification = createSpecification(criteria);
        return comunaMapper.toDto(comunaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ComunaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ComunaDTO> findByCriteria(ComunaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Comuna> specification = createSpecification(criteria);
        final Page<Comuna> result = comunaRepository.findAll(specification, page);
        return result.map(comunaMapper::toDto);
    }

    /**
     * Function to convert ComunaCriteria to a {@link Specifications}
     */
    private Specifications<Comuna> createSpecification(ComunaCriteria criteria) {
        Specifications<Comuna> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Comuna_.id));
            }
            if (criteria.getNmComuna() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmComuna(), Comuna_.nmComuna));
            }
            if (criteria.getMunicipio() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getMunicipio(), Comuna_.municipio, Municipio_.id));
            }
        }
        return specification;
    }

}
