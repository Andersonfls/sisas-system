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

import com.minea.sisas.domain.RelatoriosLog;
import com.minea.sisas.domain.*; // for static metamodels
import com.minea.sisas.repository.RelatoriosLogRepository;
import com.minea.sisas.service.dto.RelatoriosLogCriteria;

import com.minea.sisas.service.dto.RelatoriosLogDTO;
import com.minea.sisas.service.mapper.RelatoriosLogMapper;

/**
 * Service for executing complex queries for RelatoriosLog entities in the database.
 * The main input is a {@link RelatoriosLogCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RelatoriosLogDTO} or a {@link Page} of {@link RelatoriosLogDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RelatoriosLogQueryService extends QueryService<RelatoriosLog> {

    private final Logger log = LoggerFactory.getLogger(RelatoriosLogQueryService.class);


    private final RelatoriosLogRepository relatoriosLogRepository;

    private final RelatoriosLogMapper relatoriosLogMapper;

    public RelatoriosLogQueryService(RelatoriosLogRepository relatoriosLogRepository, RelatoriosLogMapper relatoriosLogMapper) {
        this.relatoriosLogRepository = relatoriosLogRepository;
        this.relatoriosLogMapper = relatoriosLogMapper;
    }

    /**
     * Return a {@link List} of {@link RelatoriosLogDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RelatoriosLogDTO> findByCriteria(RelatoriosLogCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<RelatoriosLog> specification = createSpecification(criteria);
        return relatoriosLogMapper.toDto(relatoriosLogRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RelatoriosLogDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RelatoriosLogDTO> findByCriteria(RelatoriosLogCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<RelatoriosLog> specification = createSpecification(criteria);
        final Page<RelatoriosLog> result = relatoriosLogRepository.findAll(specification, page);
        return result.map(relatoriosLogMapper::toDto);
    }

    /**
     * Function to convert RelatoriosLogCriteria to a {@link Specifications}
     */
    private Specifications<RelatoriosLog> createSpecification(RelatoriosLogCriteria criteria) {
        Specifications<RelatoriosLog> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), RelatoriosLog_.id));
            }
            if (criteria.getAcao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAcao(), RelatoriosLog_.acao));
            }
            if (criteria.getIdUsuario() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuario(), RelatoriosLog_.idUsuario));
            }
            if (criteria.getLog() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLog(), RelatoriosLog_.log));
            }
            if (criteria.getDtLog() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtLog(), RelatoriosLog_.dtLog));
            }
        }
        return specification;
    }

}
