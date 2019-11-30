package com.minea.sisas.service;


import java.util.List;

import com.minea.sisas.service.mapper.EpasMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.minea.sisas.domain.Epas;
import com.minea.sisas.domain.*; // for static metamodels
import com.minea.sisas.repository.EpasRepository;
import com.minea.sisas.service.dto.EpasCriteria;

import com.minea.sisas.service.dto.EpasDTO;
import com.minea.sisas.service.mapper.EpasMapper;

/**
 * Service for executing complex queries for Epas entities in the database.
 * The main input is a {@link EpasCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EpasDTO} or a {@link Page} of {@link EpasDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EpasQueryService extends QueryService<Epas> {

    private final Logger log = LoggerFactory.getLogger(EpasQueryService.class);


    private final EpasRepository epasRepository;

    private final EpasMapper epasMapper;

    public EpasQueryService(EpasRepository epasRepository, EpasMapper epasMapper) {
        this.epasRepository = epasRepository;
        this.epasMapper = epasMapper;
    }

    /**
     * Return a {@link List} of {@link EpasDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EpasDTO> findByCriteria(EpasCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Epas> specification = createSpecification(criteria);
        return epasMapper.toDto(epasRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EpasDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EpasDTO> findByCriteria(EpasCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Epas> specification = createSpecification(criteria);
        final Page<Epas> result = epasRepository.findAll(specification, page);
        return result.map(epasMapper::toDto);
    }

    /**
     * Function to convert EpasCriteria to a {@link Specifications}
     */
    private Specifications<Epas> createSpecification(EpasCriteria criteria) {
        Specifications<Epas> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Epas_.id));
            }
            if (criteria.getNmEpas() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmEpas(), Epas_.nmEpas));
            }
            if (criteria.getMunicipio() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getMunicipio(), Epas_.municipio, Municipio_.id));
            }
        }
        return specification;
    }

}
