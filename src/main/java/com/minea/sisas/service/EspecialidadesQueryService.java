package com.minea.sisas.service;


import com.minea.sisas.domain.Especialidades;
import com.minea.sisas.domain.Especialidades_;
import com.minea.sisas.domain.Municipio_;
import com.minea.sisas.repository.EspecialidadesRepository;
import com.minea.sisas.service.dto.EspecialidadesCriteria;
import com.minea.sisas.service.dto.EspecialidadesDTO;
import com.minea.sisas.service.mapper.EspecialidadesMapper;
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
 * Service for executing complex queries for Especialidades entities in the database.
 * The main input is a {@link EspecialidadesCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EspecialidadesDTO} or a {@link Page} of {@link EspecialidadesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EspecialidadesQueryService extends QueryService<Especialidades> {

    private final Logger log = LoggerFactory.getLogger(EspecialidadesQueryService.class);


    private final EspecialidadesRepository especialidadesRepository;

    private final EspecialidadesMapper especialidadesMapper;

    public EspecialidadesQueryService(EspecialidadesRepository especialidadesRepository, EspecialidadesMapper especialidadesMapper) {
        this.especialidadesRepository = especialidadesRepository;
        this.especialidadesMapper = especialidadesMapper;
    }

    /**
     * Return a {@link List} of {@link EspecialidadesDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EspecialidadesDTO> findByCriteria(EspecialidadesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Especialidades> specification = createSpecification(criteria);
        return especialidadesMapper.toDto(especialidadesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EspecialidadesDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EspecialidadesDTO> findByCriteria(EspecialidadesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Especialidades> specification = createSpecification(criteria);
        final Page<Especialidades> result = especialidadesRepository.findAll(specification, page);
        return result.map(especialidadesMapper::toDto);
    }

    /**
     * Function to convert EspecialidadesCriteria to a {@link Specifications}
     */
    private Specifications<Especialidades> createSpecification(EspecialidadesCriteria criteria) {
        Specifications<Especialidades> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Especialidades_.id));
            }
            if (criteria.getNmEspecialidade() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmEspecialidade(), Especialidades_.nmEspecialidade));
            }
        }
        return specification;
    }

}
