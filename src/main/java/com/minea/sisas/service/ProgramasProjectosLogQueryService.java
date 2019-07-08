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

import com.minea.sisas.domain.ProgramasProjectosLog;
import com.minea.sisas.domain.*; // for static metamodels
import com.minea.sisas.repository.ProgramasProjectosLogRepository;
import com.minea.sisas.service.dto.ProgramasProjectosLogCriteria;

import com.minea.sisas.service.dto.ProgramasProjectosLogDTO;
import com.minea.sisas.service.mapper.ProgramasProjectosLogMapper;

/**
 * Service for executing complex queries for ProgramasProjectosLog entities in the database.
 * The main input is a {@link ProgramasProjectosLogCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProgramasProjectosLogDTO} or a {@link Page} of {@link ProgramasProjectosLogDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProgramasProjectosLogQueryService extends QueryService<ProgramasProjectosLog> {

    private final Logger log = LoggerFactory.getLogger(ProgramasProjectosLogQueryService.class);


    private final ProgramasProjectosLogRepository programasProjectosLogRepository;

    private final ProgramasProjectosLogMapper programasProjectosLogMapper;

    public ProgramasProjectosLogQueryService(ProgramasProjectosLogRepository programasProjectosLogRepository, ProgramasProjectosLogMapper programasProjectosLogMapper) {
        this.programasProjectosLogRepository = programasProjectosLogRepository;
        this.programasProjectosLogMapper = programasProjectosLogMapper;
    }

    /**
     * Return a {@link List} of {@link ProgramasProjectosLogDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProgramasProjectosLogDTO> findByCriteria(ProgramasProjectosLogCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<ProgramasProjectosLog> specification = createSpecification(criteria);
        return programasProjectosLogMapper.toDto(programasProjectosLogRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProgramasProjectosLogDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProgramasProjectosLogDTO> findByCriteria(ProgramasProjectosLogCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<ProgramasProjectosLog> specification = createSpecification(criteria);
        final Page<ProgramasProjectosLog> result = programasProjectosLogRepository.findAll(specification, page);
        return result.map(programasProjectosLogMapper::toDto);
    }

    /**
     * Function to convert ProgramasProjectosLogCriteria to a {@link Specifications}
     */
    private Specifications<ProgramasProjectosLog> createSpecification(ProgramasProjectosLogCriteria criteria) {
        Specifications<ProgramasProjectosLog> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ProgramasProjectosLog_.id));
            }
            if (criteria.getAcao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAcao(), ProgramasProjectosLog_.acao));
            }
            if (criteria.getIdUsuario() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuario(), ProgramasProjectosLog_.idUsuario));
            }
            if (criteria.getLog() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLog(), ProgramasProjectosLog_.log));
            }
            if (criteria.getDtLog() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtLog(), ProgramasProjectosLog_.dtLog));
            }
            if (criteria.getIdProgramasProjectosId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdProgramasProjectosId(), ProgramasProjectosLog_.idProgramasProjectos, ProgramasProjectos_.id));
            }
        }
        return specification;
    }

}
