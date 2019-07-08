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

import com.minea.sisas.domain.SistemaAguaLog;
import com.minea.sisas.domain.*; // for static metamodels
import com.minea.sisas.repository.SistemaAguaLogRepository;
import com.minea.sisas.service.dto.SistemaAguaLogCriteria;

import com.minea.sisas.service.dto.SistemaAguaLogDTO;
import com.minea.sisas.service.mapper.SistemaAguaLogMapper;

/**
 * Service for executing complex queries for SistemaAguaLog entities in the database.
 * The main input is a {@link SistemaAguaLogCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SistemaAguaLogDTO} or a {@link Page} of {@link SistemaAguaLogDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SistemaAguaLogQueryService extends QueryService<SistemaAguaLog> {

    private final Logger log = LoggerFactory.getLogger(SistemaAguaLogQueryService.class);


    private final SistemaAguaLogRepository sistemaAguaLogRepository;

    private final SistemaAguaLogMapper sistemaAguaLogMapper;

    public SistemaAguaLogQueryService(SistemaAguaLogRepository sistemaAguaLogRepository, SistemaAguaLogMapper sistemaAguaLogMapper) {
        this.sistemaAguaLogRepository = sistemaAguaLogRepository;
        this.sistemaAguaLogMapper = sistemaAguaLogMapper;
    }

    /**
     * Return a {@link List} of {@link SistemaAguaLogDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SistemaAguaLogDTO> findByCriteria(SistemaAguaLogCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<SistemaAguaLog> specification = createSpecification(criteria);
        return sistemaAguaLogMapper.toDto(sistemaAguaLogRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SistemaAguaLogDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SistemaAguaLogDTO> findByCriteria(SistemaAguaLogCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<SistemaAguaLog> specification = createSpecification(criteria);
        final Page<SistemaAguaLog> result = sistemaAguaLogRepository.findAll(specification, page);
        return result.map(sistemaAguaLogMapper::toDto);
    }

    /**
     * Function to convert SistemaAguaLogCriteria to a {@link Specifications}
     */
    private Specifications<SistemaAguaLog> createSpecification(SistemaAguaLogCriteria criteria) {
        Specifications<SistemaAguaLog> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SistemaAguaLog_.id));
            }
            if (criteria.getAcao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAcao(), SistemaAguaLog_.acao));
            }
            if (criteria.getIdUsuario() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuario(), SistemaAguaLog_.idUsuario));
            }
            if (criteria.getLog() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLog(), SistemaAguaLog_.log));
            }
            if (criteria.getDtLog() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtLog(), SistemaAguaLog_.dtLog));
            }
            if (criteria.getIdSistemaAguaId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdSistemaAguaId(), SistemaAguaLog_.idSistemaAgua, SistemaAgua_.id));
            }
        }
        return specification;
    }

}
