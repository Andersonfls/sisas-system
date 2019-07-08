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

import com.minea.sisas.domain.MunicipiosAtendidos;
import com.minea.sisas.domain.*; // for static metamodels
import com.minea.sisas.repository.MunicipiosAtendidosRepository;
import com.minea.sisas.service.dto.MunicipiosAtendidosCriteria;

import com.minea.sisas.service.dto.MunicipiosAtendidosDTO;
import com.minea.sisas.service.mapper.MunicipiosAtendidosMapper;

/**
 * Service for executing complex queries for MunicipiosAtendidos entities in the database.
 * The main input is a {@link MunicipiosAtendidosCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MunicipiosAtendidosDTO} or a {@link Page} of {@link MunicipiosAtendidosDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MunicipiosAtendidosQueryService extends QueryService<MunicipiosAtendidos> {

    private final Logger log = LoggerFactory.getLogger(MunicipiosAtendidosQueryService.class);


    private final MunicipiosAtendidosRepository municipiosAtendidosRepository;

    private final MunicipiosAtendidosMapper municipiosAtendidosMapper;

    public MunicipiosAtendidosQueryService(MunicipiosAtendidosRepository municipiosAtendidosRepository, MunicipiosAtendidosMapper municipiosAtendidosMapper) {
        this.municipiosAtendidosRepository = municipiosAtendidosRepository;
        this.municipiosAtendidosMapper = municipiosAtendidosMapper;
    }

    /**
     * Return a {@link List} of {@link MunicipiosAtendidosDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MunicipiosAtendidosDTO> findByCriteria(MunicipiosAtendidosCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<MunicipiosAtendidos> specification = createSpecification(criteria);
        return municipiosAtendidosMapper.toDto(municipiosAtendidosRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MunicipiosAtendidosDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MunicipiosAtendidosDTO> findByCriteria(MunicipiosAtendidosCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<MunicipiosAtendidos> specification = createSpecification(criteria);
        final Page<MunicipiosAtendidos> result = municipiosAtendidosRepository.findAll(specification, page);
        return result.map(municipiosAtendidosMapper::toDto);
    }

    /**
     * Function to convert MunicipiosAtendidosCriteria to a {@link Specifications}
     */
    private Specifications<MunicipiosAtendidos> createSpecification(MunicipiosAtendidosCriteria criteria) {
        Specifications<MunicipiosAtendidos> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MunicipiosAtendidos_.id));
            }
            if (criteria.getIdMunicipioId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdMunicipioId(), MunicipiosAtendidos_.idMunicipio, Municipio_.id));
            }
            if (criteria.getEntidadeGestoraId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getEntidadeGestoraId(), MunicipiosAtendidos_.entidadeGestoras, EntidadeGestora_.id));
            }
        }
        return specification;
    }

}
