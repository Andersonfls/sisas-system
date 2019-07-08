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

import com.minea.sisas.domain.Publicacao;
import com.minea.sisas.domain.*; // for static metamodels
import com.minea.sisas.repository.PublicacaoRepository;
import com.minea.sisas.service.dto.PublicacaoCriteria;

import com.minea.sisas.service.dto.PublicacaoDTO;
import com.minea.sisas.service.mapper.PublicacaoMapper;

/**
 * Service for executing complex queries for Publicacao entities in the database.
 * The main input is a {@link PublicacaoCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PublicacaoDTO} or a {@link Page} of {@link PublicacaoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PublicacaoQueryService extends QueryService<Publicacao> {

    private final Logger log = LoggerFactory.getLogger(PublicacaoQueryService.class);


    private final PublicacaoRepository publicacaoRepository;

    private final PublicacaoMapper publicacaoMapper;

    public PublicacaoQueryService(PublicacaoRepository publicacaoRepository, PublicacaoMapper publicacaoMapper) {
        this.publicacaoRepository = publicacaoRepository;
        this.publicacaoMapper = publicacaoMapper;
    }

    /**
     * Return a {@link List} of {@link PublicacaoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PublicacaoDTO> findByCriteria(PublicacaoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Publicacao> specification = createSpecification(criteria);
        return publicacaoMapper.toDto(publicacaoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PublicacaoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PublicacaoDTO> findByCriteria(PublicacaoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Publicacao> specification = createSpecification(criteria);
        final Page<Publicacao> result = publicacaoRepository.findAll(specification, page);
        return result.map(publicacaoMapper::toDto);
    }

    /**
     * Function to convert PublicacaoCriteria to a {@link Specifications}
     */
    private Specifications<Publicacao> createSpecification(PublicacaoCriteria criteria) {
        Specifications<Publicacao> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Publicacao_.id));
            }
            if (criteria.getTituloPublicacao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTituloPublicacao(), Publicacao_.tituloPublicacao));
            }
            if (criteria.getTextoPublicacao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTextoPublicacao(), Publicacao_.textoPublicacao));
            }
            if (criteria.getResumoTextoPublicacao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResumoTextoPublicacao(), Publicacao_.resumoTextoPublicacao));
            }
        }
        return specification;
    }

}
