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

import com.minea.sisas.domain.PublicacaoLog;
import com.minea.sisas.domain.*; // for static metamodels
import com.minea.sisas.repository.PublicacaoLogRepository;
import com.minea.sisas.service.dto.PublicacaoLogCriteria;

import com.minea.sisas.service.dto.PublicacaoLogDTO;
import com.minea.sisas.service.mapper.PublicacaoLogMapper;

/**
 * Service for executing complex queries for PublicacaoLog entities in the database.
 * The main input is a {@link PublicacaoLogCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PublicacaoLogDTO} or a {@link Page} of {@link PublicacaoLogDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PublicacaoLogQueryService extends QueryService<PublicacaoLog> {

    private final Logger log = LoggerFactory.getLogger(PublicacaoLogQueryService.class);


    private final PublicacaoLogRepository publicacaoLogRepository;

    private final PublicacaoLogMapper publicacaoLogMapper;

    public PublicacaoLogQueryService(PublicacaoLogRepository publicacaoLogRepository, PublicacaoLogMapper publicacaoLogMapper) {
        this.publicacaoLogRepository = publicacaoLogRepository;
        this.publicacaoLogMapper = publicacaoLogMapper;
    }

    /**
     * Return a {@link List} of {@link PublicacaoLogDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PublicacaoLogDTO> findByCriteria(PublicacaoLogCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<PublicacaoLog> specification = createSpecification(criteria);
        return publicacaoLogMapper.toDto(publicacaoLogRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PublicacaoLogDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PublicacaoLogDTO> findByCriteria(PublicacaoLogCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<PublicacaoLog> specification = createSpecification(criteria);
        final Page<PublicacaoLog> result = publicacaoLogRepository.findAll(specification, page);
        return result.map(publicacaoLogMapper::toDto);
    }

    /**
     * Function to convert PublicacaoLogCriteria to a {@link Specifications}
     */
    private Specifications<PublicacaoLog> createSpecification(PublicacaoLogCriteria criteria) {
        Specifications<PublicacaoLog> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PublicacaoLog_.id));
            }
            if (criteria.getAcao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAcao(), PublicacaoLog_.acao));
            }
            if (criteria.getIdUsuario() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuario(), PublicacaoLog_.idUsuario));
            }
            if (criteria.getLog() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLog(), PublicacaoLog_.log));
            }
            if (criteria.getDtLog() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtLog(), PublicacaoLog_.dtLog));
            }
        }
        return specification;
    }

}
