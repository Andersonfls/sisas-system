package com.minea.sisas.service;


import java.util.List;

import com.minea.sisas.service.mapper.FinalidadeProjetoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.minea.sisas.domain.FinalidadeProjeto;
import com.minea.sisas.domain.*; // for static metamodels
import com.minea.sisas.repository.FinalidadeProjetoRepository;
import com.minea.sisas.service.dto.FinalidadeProjetoCriteria;

import com.minea.sisas.service.dto.FinalidadeProjetoDTO;
import com.minea.sisas.service.mapper.FinalidadeProjetoMapper;

/**
 * Service for executing complex queries for FinalidadeProjeto entities in the database.
 * The main input is a {@link FinalidadeProjetoCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FinalidadeProjetoDTO} or a {@link Page} of {@link FinalidadeProjetoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FinalidadeProjetoQueryService extends QueryService<FinalidadeProjeto> {

    private final Logger log = LoggerFactory.getLogger(FinalidadeProjetoQueryService.class);


    private final FinalidadeProjetoRepository finalidadeProjetoRepository;

    private final FinalidadeProjetoMapper finalidadeProjetoMapper;

    public FinalidadeProjetoQueryService(FinalidadeProjetoRepository finalidadeProjetoRepository, FinalidadeProjetoMapper finalidadeProjetoMapper) {
        this.finalidadeProjetoRepository = finalidadeProjetoRepository;
        this.finalidadeProjetoMapper = finalidadeProjetoMapper;
    }

    /**
     * Return a {@link List} of {@link FinalidadeProjetoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FinalidadeProjetoDTO> findByCriteria(FinalidadeProjetoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<FinalidadeProjeto> specification = createSpecification(criteria);
        return finalidadeProjetoMapper.toDto(finalidadeProjetoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FinalidadeProjetoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FinalidadeProjetoDTO> findByCriteria(FinalidadeProjetoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<FinalidadeProjeto> specification = createSpecification(criteria);
        final Page<FinalidadeProjeto> result = finalidadeProjetoRepository.findAll(specification, page);
        return result.map(finalidadeProjetoMapper::toDto);
    }

    /**
     * Function to convert FinalidadeProjetoCriteria to a {@link Specifications}
     */
    private Specifications<FinalidadeProjeto> createSpecification(FinalidadeProjetoCriteria criteria) {
        Specifications<FinalidadeProjeto> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), FinalidadeProjeto_.id));
            }
            if (criteria.getNmFinalidade() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmFinalidade(), FinalidadeProjeto_.nmFinalidade));
            }
        }
        return specification;
    }

}
