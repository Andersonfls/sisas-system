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

import com.minea.sisas.domain.OrigemFinanciamento;
import com.minea.sisas.domain.*; // for static metamodels
import com.minea.sisas.repository.OrigemFinanciamentoRepository;
import com.minea.sisas.service.dto.OrigemFinanciamentoCriteria;

import com.minea.sisas.service.dto.OrigemFinanciamentoDTO;
import com.minea.sisas.service.mapper.OrigemFinanciamentoMapper;

/**
 * Service for executing complex queries for OrigemFinanciamento entities in the database.
 * The main input is a {@link OrigemFinanciamentoCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrigemFinanciamentoDTO} or a {@link Page} of {@link OrigemFinanciamentoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrigemFinanciamentoQueryService extends QueryService<OrigemFinanciamento> {

    private final Logger log = LoggerFactory.getLogger(OrigemFinanciamentoQueryService.class);


    private final OrigemFinanciamentoRepository origemFinanciamentoRepository;

    private final OrigemFinanciamentoMapper origemFinanciamentoMapper;

    public OrigemFinanciamentoQueryService(OrigemFinanciamentoRepository origemFinanciamentoRepository, OrigemFinanciamentoMapper origemFinanciamentoMapper) {
        this.origemFinanciamentoRepository = origemFinanciamentoRepository;
        this.origemFinanciamentoMapper = origemFinanciamentoMapper;
    }

    /**
     * Return a {@link List} of {@link OrigemFinanciamentoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrigemFinanciamentoDTO> findByCriteria(OrigemFinanciamentoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<OrigemFinanciamento> specification = createSpecification(criteria);
        return origemFinanciamentoMapper.toDto(origemFinanciamentoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OrigemFinanciamentoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrigemFinanciamentoDTO> findByCriteria(OrigemFinanciamentoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<OrigemFinanciamento> specification = createSpecification(criteria);
        final Page<OrigemFinanciamento> result = origemFinanciamentoRepository.findAll(specification, page);
        return result.map(origemFinanciamentoMapper::toDto);
    }

    /**
     * Function to convert OrigemFinanciamentoCriteria to a {@link Specifications}
     */
    private Specifications<OrigemFinanciamento> createSpecification(OrigemFinanciamentoCriteria criteria) {
        Specifications<OrigemFinanciamento> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), OrigemFinanciamento_.id));
            }
            if (criteria.getDescricaoOrigemFinanciamento() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescricaoOrigemFinanciamento(), OrigemFinanciamento_.descricaoOrigemFinanciamento));
            }
        }
        return specification;
    }

}
