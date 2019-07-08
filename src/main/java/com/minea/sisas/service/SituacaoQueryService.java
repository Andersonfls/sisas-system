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

import com.minea.sisas.domain.Situacao;
import com.minea.sisas.domain.*; // for static metamodels
import com.minea.sisas.repository.SituacaoRepository;
import com.minea.sisas.service.dto.SituacaoCriteria;

import com.minea.sisas.service.dto.SituacaoDTO;
import com.minea.sisas.service.mapper.SituacaoMapper;

/**
 * Service for executing complex queries for Situacao entities in the database.
 * The main input is a {@link SituacaoCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SituacaoDTO} or a {@link Page} of {@link SituacaoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SituacaoQueryService extends QueryService<Situacao> {

    private final Logger log = LoggerFactory.getLogger(SituacaoQueryService.class);


    private final SituacaoRepository situacaoRepository;

    private final SituacaoMapper situacaoMapper;

    public SituacaoQueryService(SituacaoRepository situacaoRepository, SituacaoMapper situacaoMapper) {
        this.situacaoRepository = situacaoRepository;
        this.situacaoMapper = situacaoMapper;
    }

    /**
     * Return a {@link List} of {@link SituacaoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SituacaoDTO> findByCriteria(SituacaoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Situacao> specification = createSpecification(criteria);
        return situacaoMapper.toDto(situacaoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SituacaoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SituacaoDTO> findByCriteria(SituacaoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Situacao> specification = createSpecification(criteria);
        final Page<Situacao> result = situacaoRepository.findAll(specification, page);
        return result.map(situacaoMapper::toDto);
    }

    /**
     * Function to convert SituacaoCriteria to a {@link Specifications}
     */
    private Specifications<Situacao> createSpecification(SituacaoCriteria criteria) {
        Specifications<Situacao> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Situacao_.id));
            }
            if (criteria.getNmSituacao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmSituacao(), Situacao_.nmSituacao));
            }
        }
        return specification;
    }

}
