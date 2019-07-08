package com.minea.sisas.service;

import com.minea.sisas.domain.IndicadorProducaoLog_;
import com.minea.sisas.domain.IndicadorProducao_;
import com.minea.sisas.domain.IndicadorProducaoLog;
import com.minea.sisas.repository.IndicadorProducaoLogRepository;
import com.minea.sisas.service.dto.IndicadorProducaoLogCriteria;
import com.minea.sisas.service.dto.IndicadorProducaoLogDTO;
import com.minea.sisas.service.mapper.IndicadorProducaoLogMapper;
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
 * Service for executing complex queries for IndicadorProducaoLog entities in the database.
 * The main input is a {@link IndicadorProducaoLogCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link IndicadorProducaoLogDTO} or a {@link Page} of {@link IndicadorProducaoLogDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class IndicadorProducaoLogQueryService extends QueryService<IndicadorProducaoLog> {

    private final Logger log = LoggerFactory.getLogger(IndicadorProducaoLogQueryService.class);


    private final IndicadorProducaoLogRepository indicadorProducaoLogRepository;

    private final IndicadorProducaoLogMapper indicadorProducaoLogMapper;

    public IndicadorProducaoLogQueryService(IndicadorProducaoLogRepository indicadorProducaoLogRepository, IndicadorProducaoLogMapper indicadorProducaoLogMapper) {
        this.indicadorProducaoLogRepository = indicadorProducaoLogRepository;
        this.indicadorProducaoLogMapper = indicadorProducaoLogMapper;
    }

    /**
     * Return a {@link List} of {@link IndicadorProducaoLogDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<IndicadorProducaoLogDTO> findByCriteria(IndicadorProducaoLogCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<IndicadorProducaoLog> specification = createSpecification(criteria);
        return indicadorProducaoLogMapper.toDto(indicadorProducaoLogRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link IndicadorProducaoLogDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<IndicadorProducaoLogDTO> findByCriteria(IndicadorProducaoLogCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<IndicadorProducaoLog> specification = createSpecification(criteria);
        final Page<IndicadorProducaoLog> result = indicadorProducaoLogRepository.findAll(specification, page);
        return result.map(indicadorProducaoLogMapper::toDto);
    }

    /**
     * Function to convert IndicadorProducaoLogCriteria to a {@link Specifications}
     */
    private Specifications<IndicadorProducaoLog> createSpecification(IndicadorProducaoLogCriteria criteria) {
        Specifications<IndicadorProducaoLog> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), IndicadorProducaoLog_.id));
            }
            if (criteria.getAcao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAcao(), IndicadorProducaoLog_.acao));
            }
            if (criteria.getIdUsuario() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuario(), IndicadorProducaoLog_.idUsuario));
            }
            if (criteria.getLog() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLog(), IndicadorProducaoLog_.log));
            }
            if (criteria.getDtLog() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtLog(), IndicadorProducaoLog_.dtLog));
            }
            if (criteria.getIdIndicadorProducaoId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdIndicadorProducaoId(), IndicadorProducaoLog_.idIndicadorProducao, IndicadorProducao_.id));
            }
        }
        return specification;
    }

}
