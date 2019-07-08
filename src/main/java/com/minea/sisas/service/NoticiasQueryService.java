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

import com.minea.sisas.domain.Noticias;
import com.minea.sisas.domain.*; // for static metamodels
import com.minea.sisas.repository.NoticiasRepository;
import com.minea.sisas.service.dto.NoticiasCriteria;

import com.minea.sisas.service.dto.NoticiasDTO;
import com.minea.sisas.service.mapper.NoticiasMapper;

/**
 * Service for executing complex queries for Noticias entities in the database.
 * The main input is a {@link NoticiasCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NoticiasDTO} or a {@link Page} of {@link NoticiasDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NoticiasQueryService extends QueryService<Noticias> {

    private final Logger log = LoggerFactory.getLogger(NoticiasQueryService.class);


    private final NoticiasRepository noticiasRepository;

    private final NoticiasMapper noticiasMapper;

    public NoticiasQueryService(NoticiasRepository noticiasRepository, NoticiasMapper noticiasMapper) {
        this.noticiasRepository = noticiasRepository;
        this.noticiasMapper = noticiasMapper;
    }

    /**
     * Return a {@link List} of {@link NoticiasDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NoticiasDTO> findByCriteria(NoticiasCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Noticias> specification = createSpecification(criteria);
        return noticiasMapper.toDto(noticiasRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NoticiasDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NoticiasDTO> findByCriteria(NoticiasCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Noticias> specification = createSpecification(criteria);
        final Page<Noticias> result = noticiasRepository.findAll(specification, page);
        return result.map(noticiasMapper::toDto);
    }

    /**
     * Function to convert NoticiasCriteria to a {@link Specifications}
     */
    private Specifications<Noticias> createSpecification(NoticiasCriteria criteria) {
        Specifications<Noticias> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Noticias_.id));
            }
            if (criteria.getTituloNoticias() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTituloNoticias(), Noticias_.tituloNoticias));
            }
            if (criteria.getTextoNoticias() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTextoNoticias(), Noticias_.textoNoticias));
            }
            if (criteria.getResumoTextoNoticias() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResumoTextoNoticias(), Noticias_.resumoTextoNoticias));
            }
            if (criteria.getIdSituacaoId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdSituacaoId(), Noticias_.idSituacao, Situacao_.id));
            }
            if (criteria.getInicioId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getInicioId(), Noticias_.inicios, Inicio_.id));
            }
        }
        return specification;
    }

}
