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

import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.domain.*; // for static metamodels
import com.minea.sisas.repository.ProgramasProjectosRepository;
import com.minea.sisas.service.dto.ProgramasProjectosCriteria;

import com.minea.sisas.service.dto.ProgramasProjectosDTO;
import com.minea.sisas.service.mapper.ProgramasProjectosMapper;

/**
 * Service for executing complex queries for ProgramasProjectos entities in the database.
 * The main input is a {@link ProgramasProjectosCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProgramasProjectosDTO} or a {@link Page} of {@link ProgramasProjectosDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProgramasProjectosQueryService extends QueryService<ProgramasProjectos> {

    private final Logger log = LoggerFactory.getLogger(ProgramasProjectosQueryService.class);


    private final ProgramasProjectosRepository programasProjectosRepository;

    private final ProgramasProjectosMapper programasProjectosMapper;

    public ProgramasProjectosQueryService(ProgramasProjectosRepository programasProjectosRepository, ProgramasProjectosMapper programasProjectosMapper) {
        this.programasProjectosRepository = programasProjectosRepository;
        this.programasProjectosMapper = programasProjectosMapper;
    }

    /**
     * Return a {@link List} of {@link ProgramasProjectosDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProgramasProjectosDTO> findByCriteria(ProgramasProjectosCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<ProgramasProjectos> specification = createSpecification(criteria);
        return programasProjectosMapper.toDto(programasProjectosRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProgramasProjectosDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProgramasProjectos> findByCriteria(ProgramasProjectosCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<ProgramasProjectos> specification = createSpecification(criteria);
        final Page<ProgramasProjectos> result = programasProjectosRepository.findAll(specification, page);
        return result;
    }

    /**
     * Function to convert ProgramasProjectosCriteria to a {@link Specifications}
     */
    private Specifications<ProgramasProjectos> createSpecification(ProgramasProjectosCriteria criteria) {
        Specifications<ProgramasProjectos> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ProgramasProjectos_.id));
            }
            if (criteria.getDtLancamento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtLancamento(), ProgramasProjectos_.dtLancamento));
            }
            if (criteria.getDtUltimaAlteracao() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtUltimaAlteracao(), ProgramasProjectos_.dtUltimaAlteracao));
            }
            if (criteria.getNmDesignacaoProjeto() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmDesignacaoProjeto(), ProgramasProjectos_.nmDesignacaoProjeto));
            }
            if (criteria.getNmDescricaoProjeto() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmDescricaoProjeto(), ProgramasProjectos_.nmDescricaoProjeto));
            }
            if (criteria.getIdSaaAssociado() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdSaaAssociado(), ProgramasProjectos_.idSaaAssociado));
            }
            if (criteria.getTipoFinanciamento() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTipoFinanciamento(), ProgramasProjectos_.tipoFinanciamento));
            }
        }
        return specification;
    }

}
