package com.minea.sisas.service;

import java.util.List;

import com.minea.sisas.service.mapper.EntidadeGestoraMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.minea.sisas.domain.EntidadeGestora;
import com.minea.sisas.domain.*; // for static metamodels
import com.minea.sisas.repository.EntidadeGestoraRepository;
import com.minea.sisas.service.dto.EntidadeGestoraCriteria;

import com.minea.sisas.service.dto.EntidadeGestoraDTO;
import com.minea.sisas.service.mapper.EntidadeGestoraMapper;

/**
 * Service for executing complex queries for EntidadeGestora entities in the database.
 * The main input is a {@link EntidadeGestoraCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EntidadeGestoraDTO} or a {@link Page} of {@link EntidadeGestoraDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EntidadeGestoraQueryService extends QueryService<EntidadeGestora> {

    private final Logger log = LoggerFactory.getLogger(EntidadeGestoraQueryService.class);


    private final EntidadeGestoraRepository entidadeGestoraRepository;

    private final EntidadeGestoraMapper entidadeGestoraMapper;

    public EntidadeGestoraQueryService(EntidadeGestoraRepository entidadeGestoraRepository, EntidadeGestoraMapper entidadeGestoraMapper) {
        this.entidadeGestoraRepository = entidadeGestoraRepository;
        this.entidadeGestoraMapper = entidadeGestoraMapper;
    }

    /**
     * Return a {@link List} of {@link EntidadeGestoraDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EntidadeGestoraDTO> findByCriteria(EntidadeGestoraCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<EntidadeGestora> specification = createSpecification(criteria);
        return entidadeGestoraMapper.toDto(entidadeGestoraRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EntidadeGestoraDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EntidadeGestoraDTO> findByCriteria(EntidadeGestoraCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<EntidadeGestora> specification = createSpecification(criteria);
        final Page<EntidadeGestora> result = entidadeGestoraRepository.findAll(specification, page);
        return result.map(entidadeGestoraMapper::toDto);
    }

    /**
     * Function to convert EntidadeGestoraCriteria to a {@link Specifications}
     */
    private Specifications<EntidadeGestora> createSpecification(EntidadeGestoraCriteria criteria) {
        Specifications<EntidadeGestora> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EntidadeGestora_.id));
            }
            if (criteria.getIdEntidadeGestora() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdEntidadeGestora(), EntidadeGestora_.idEntidadeGestora));
            }
            if (criteria.getNmEntidadeGestora() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmEntidadeGestora(), EntidadeGestora_.nmEntidadeGestora));
            }
            if (criteria.getTpFormaJuridica() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTpFormaJuridica(), EntidadeGestora_.tpFormaJuridica));
            }
            if (criteria.getDtConstituicao() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtConstituicao(), EntidadeGestora_.dtConstituicao));
            }
            if (criteria.getEndereco() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEndereco(), EntidadeGestora_.endereco));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), EntidadeGestora_.email));
            }
            if (criteria.getContactos() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContactos(), EntidadeGestora_.contactos));
            }
            if (criteria.getTpModeloGestao() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTpModeloGestao(), EntidadeGestora_.tpModeloGestao));
            }
            if (criteria.getNumRecursosHumanos() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumRecursosHumanos(), EntidadeGestora_.numRecursosHumanos));
            }
            if (criteria.getNumPopulacaoAreaAtendimento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumPopulacaoAreaAtendimento(), EntidadeGestora_.numPopulacaoAreaAtendimento));
            }
            if (criteria.getIdMunicipioAtendidoId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdMunicipioAtendidoId(), EntidadeGestora_.idMunicipioAtendido, MunicipiosAtendidos_.id));
            }
        }
        return specification;
    }

}
