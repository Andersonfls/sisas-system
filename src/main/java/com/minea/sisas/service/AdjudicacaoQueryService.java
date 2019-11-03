package com.minea.sisas.service;

import com.minea.sisas.domain.Adjudicacao_;
import com.minea.sisas.domain.ProgramasProjectos_;
import com.minea.sisas.domain.SistemaAgua_;
import com.minea.sisas.domain.Adjudicacao;
import com.minea.sisas.repository.AdjudicacaoRepository;
import com.minea.sisas.service.dto.AdjudicacaoCriteria;
import com.minea.sisas.service.dto.AdjudicacaoDTO;
import com.minea.sisas.service.mapper.AdjudicacaoMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for executing complex queries for Adjudicacao entities in the database.
 * The main input is a {@link AdjudicacaoCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AdjudicacaoDTO} or a {@link Page} of {@link AdjudicacaoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AdjudicacaoQueryService extends QueryService<Adjudicacao> {

    private final Logger log = LoggerFactory.getLogger(AdjudicacaoQueryService.class);


    private final AdjudicacaoRepository adjudicacaoRepository;

    private final AdjudicacaoMapper adjudicacaoMapper;

    public AdjudicacaoQueryService(AdjudicacaoRepository adjudicacaoRepository, AdjudicacaoMapper adjudicacaoMapper) {
        this.adjudicacaoRepository = adjudicacaoRepository;
        this.adjudicacaoMapper = adjudicacaoMapper;
    }

    /**
     * Return a {@link List} of {@link AdjudicacaoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AdjudicacaoDTO> findByCriteria(AdjudicacaoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Adjudicacao> specification = createSpecification(criteria);
        return adjudicacaoMapper.toDto(adjudicacaoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AdjudicacaoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AdjudicacaoDTO> findByCriteria(AdjudicacaoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Adjudicacao> specification = createSpecification(criteria);
        final Page<Adjudicacao> result = adjudicacaoRepository.findAll(specification, page);
        return result.map(adjudicacaoMapper::toDto);
    }

    /**
     * Function to convert AdjudicacaoCriteria to a {@link Specifications}
     */
    private Specifications<Adjudicacao> createSpecification(AdjudicacaoCriteria criteria) {
        Specifications<Adjudicacao> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Adjudicacao_.id));
            }
            if (criteria.getTipoConcurso() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTipoConcurso(), Adjudicacao_.tipoConcurso));
            }
            if (criteria.getDtLancamento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtLancamento(), Adjudicacao_.dtLancamento));
            }
            if (criteria.getDtComunicaoAdjudicacao() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtComunicaoAdjudicacao(), Adjudicacao_.dtComunicaoAdjudicacao));
            }
            if (criteria.getDtPrestacaoGarantBoaExec() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtPrestacaoGarantBoaExec(), Adjudicacao_.dtPrestacaoGarantBoaExec));
            }
            if (criteria.getDtSubmissaoMinutContrato() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtSubmissaoMinutContrato(), Adjudicacao_.dtSubmissaoMinutContrato));
            }
            if (criteria.getIdSistemaAguaId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdSistemaAguaId(), Adjudicacao_.idSistemaAgua, SistemaAgua_.id));
            }
        }
        return specification;
    }

}
