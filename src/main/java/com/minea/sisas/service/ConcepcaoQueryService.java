package com.minea.sisas.service;

import java.util.List;

import com.minea.sisas.service.mapper.ConcepcaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.minea.sisas.domain.Concepcao;
import com.minea.sisas.domain.*; // for static metamodels
import com.minea.sisas.repository.ConcepcaoRepository;
import com.minea.sisas.service.dto.ConcepcaoCriteria;

import com.minea.sisas.service.dto.ConcepcaoDTO;
import com.minea.sisas.service.mapper.ConcepcaoMapper;

/**
 * Service for executing complex queries for Concepcao entities in the database.
 * The main input is a {@link ConcepcaoCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ConcepcaoDTO} or a {@link Page} of {@link ConcepcaoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ConcepcaoQueryService extends QueryService<Concepcao> {

    private final Logger log = LoggerFactory.getLogger(ConcepcaoQueryService.class);


    private final ConcepcaoRepository concepcaoRepository;

    private final ConcepcaoMapper concepcaoMapper;

    public ConcepcaoQueryService(ConcepcaoRepository concepcaoRepository, ConcepcaoMapper concepcaoMapper) {
        this.concepcaoRepository = concepcaoRepository;
        this.concepcaoMapper = concepcaoMapper;
    }

    /**
     * Return a {@link List} of {@link ConcepcaoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ConcepcaoDTO> findByCriteria(ConcepcaoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Concepcao> specification = createSpecification(criteria);
        return concepcaoMapper.toDto(concepcaoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ConcepcaoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ConcepcaoDTO> findByCriteria(ConcepcaoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Concepcao> specification = createSpecification(criteria);
        final Page<Concepcao> result = concepcaoRepository.findAll(specification, page);
        return result.map(concepcaoMapper::toDto);
    }

    /**
     * Function to convert ConcepcaoCriteria to a {@link Specifications}
     */
    private Specifications<Concepcao> createSpecification(ConcepcaoCriteria criteria) {
        Specifications<Concepcao> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Concepcao_.id));
            }
            if (criteria.getTipoConcurso() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTipoConcurso(), Concepcao_.tipoConcurso));
            }
            if (criteria.getDtLancamento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtLancamento(), Concepcao_.dtLancamento));
            }
            if (criteria.getDtUltimaAlteracao() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtUltimaAlteracao(), Concepcao_.dtUltimaAlteracao));
            }
            if (criteria.getDtElaboracaoCon() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtElaboracaoCon(), Concepcao_.dtElaboracaoCon));
            }
            if (criteria.getDtAprovacaoCon() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtAprovacaoCon(), Concepcao_.dtAprovacaoCon));
            }
            if (criteria.getIdSistemaAguaId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdSistemaAguaId(), Concepcao_.idSistemaAgua, SistemaAgua_.id));
            }
        }
        return specification;
    }

}
