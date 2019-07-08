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

import com.minea.sisas.domain.Provincia;
import com.minea.sisas.domain.*; // for static metamodels
import com.minea.sisas.repository.ProvinciaRepository;
import com.minea.sisas.service.dto.ProvinciaCriteria;

import com.minea.sisas.service.dto.ProvinciaDTO;
import com.minea.sisas.service.mapper.ProvinciaMapper;

/**
 * Service for executing complex queries for Provincia entities in the database.
 * The main input is a {@link ProvinciaCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProvinciaDTO} or a {@link Page} of {@link ProvinciaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProvinciaQueryService extends QueryService<Provincia> {

    private final Logger log = LoggerFactory.getLogger(ProvinciaQueryService.class);


    private final ProvinciaRepository provinciaRepository;

    private final ProvinciaMapper provinciaMapper;

    public ProvinciaQueryService(ProvinciaRepository provinciaRepository, ProvinciaMapper provinciaMapper) {
        this.provinciaRepository = provinciaRepository;
        this.provinciaMapper = provinciaMapper;
    }

    /**
     * Return a {@link List} of {@link ProvinciaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProvinciaDTO> findByCriteria(ProvinciaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Provincia> specification = createSpecification(criteria);
        return provinciaMapper.toDto(provinciaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProvinciaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProvinciaDTO> findByCriteria(ProvinciaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Provincia> specification = createSpecification(criteria);
        final Page<Provincia> result = provinciaRepository.findAll(specification, page);
        return result.map(provinciaMapper::toDto);
    }

    /**
     * Function to convert ProvinciaCriteria to a {@link Specifications}
     */
    private Specifications<Provincia> createSpecification(ProvinciaCriteria criteria) {
        Specifications<Provincia> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Provincia_.id));
            }
            if (criteria.getNmProvincia() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmProvincia(), Provincia_.nmProvincia));
            }
            if (criteria.getMunicipioId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getMunicipioId(), Provincia_.municipios, Municipio_.id));
            }
        }
        return specification;
    }

}
