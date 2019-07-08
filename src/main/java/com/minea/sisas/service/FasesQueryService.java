package com.minea.sisas.service;


import com.minea.sisas.domain.Fases_;
import com.minea.sisas.domain.Fases;
import com.minea.sisas.repository.FasesRepository;
import com.minea.sisas.service.dto.FasesCriteria;
import com.minea.sisas.service.dto.FasesDTO;
import com.minea.sisas.service.mapper.FasesMapper;
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
 * Service for executing complex queries for Fases entities in the database.
 * The main input is a {@link FasesCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FasesDTO} or a {@link Page} of {@link FasesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FasesQueryService extends QueryService<Fases> {

    private final Logger log = LoggerFactory.getLogger(FasesQueryService.class);


    private final FasesRepository fasesRepository;

    private final FasesMapper fasesMapper;

    public FasesQueryService(FasesRepository fasesRepository, FasesMapper fasesMapper) {
        this.fasesRepository = fasesRepository;
        this.fasesMapper = fasesMapper;
    }

    /**
     * Return a {@link List} of {@link FasesDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FasesDTO> findByCriteria(FasesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Fases> specification = createSpecification(criteria);
        return fasesMapper.toDto(fasesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FasesDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FasesDTO> findByCriteria(FasesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Fases> specification = createSpecification(criteria);
        final Page<Fases> result = fasesRepository.findAll(specification, page);
        return result.map(fasesMapper::toDto);
    }

    /**
     * Function to convert FasesCriteria to a {@link Specifications}
     */
    private Specifications<Fases> createSpecification(FasesCriteria criteria) {
        Specifications<Fases> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Fases_.id));
            }
            if (criteria.getDescricaoFase() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescricaoFase(), Fases_.descricaoFase));
            }
        }
        return specification;
    }

}
