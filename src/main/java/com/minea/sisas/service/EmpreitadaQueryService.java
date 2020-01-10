package com.minea.sisas.service;

import java.util.List;

import com.minea.sisas.service.mapper.EmpreitadaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.minea.sisas.domain.Empreitada;
import com.minea.sisas.domain.*; // for static metamodels
import com.minea.sisas.repository.EmpreitadaRepository;
import com.minea.sisas.service.dto.EmpreitadaCriteria;

import com.minea.sisas.service.dto.EmpreitadaDTO;
import com.minea.sisas.service.mapper.EmpreitadaMapper;

/**
 * Service for executing complex queries for Empreitada entities in the database.
 * The main input is a {@link EmpreitadaCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EmpreitadaDTO} or a {@link Page} of {@link EmpreitadaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EmpreitadaQueryService extends QueryService<Empreitada> {

    private final Logger log = LoggerFactory.getLogger(EmpreitadaQueryService.class);


    private final EmpreitadaRepository empreitadaRepository;

    private final EmpreitadaMapper empreitadaMapper;

    public EmpreitadaQueryService(EmpreitadaRepository empreitadaRepository, EmpreitadaMapper empreitadaMapper) {
        this.empreitadaRepository = empreitadaRepository;
        this.empreitadaMapper = empreitadaMapper;
    }

    /**
     * Return a {@link List} of {@link EmpreitadaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EmpreitadaDTO> findByCriteria(EmpreitadaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Empreitada> specification = createSpecification(criteria);
        return empreitadaMapper.toDto(empreitadaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EmpreitadaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EmpreitadaDTO> findByCriteria(EmpreitadaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Empreitada> specification = createSpecification(criteria);
        final Page<Empreitada> result = empreitadaRepository.findAll(specification, page);
        return result.map(empreitadaMapper::toDto);
    }

    /**
     * Function to convert EmpreitadaCriteria to a {@link Specifications}
     */
    private Specifications<Empreitada> createSpecification(EmpreitadaCriteria criteria) {
        Specifications<Empreitada> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Empreitada_.id));
            }
            if (criteria.getTipoEmpreitada() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTipoEmpreitada(), Empreitada_.tipoEmpreitada));
            }
            if (criteria.getDtLancamento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtLancamento(), Empreitada_.dtLancamento));
            }
            if (criteria.getNumCapacidadeCaptacao() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumCapacidadeCaptacao(), Empreitada_.numCapacidadeCaptacao));
            }
            if (criteria.getNumCapacidadeCaptacaoEta() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumCapacidadeCaptacaoEta(), Empreitada_.numCapacidadeCaptacaoEta));
            }
            if (criteria.getNumExtensaoCondAdutMat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumExtensaoCondAdutMat(), Empreitada_.numExtensaoCondAdutMat));
            }
            if (criteria.getNumCaprmazenamento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumCaprmazenamento(), Empreitada_.numCaprmazenamento));
            }
            if (criteria.getNumExtensaoRedeMat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumExtensaoRedeMat(), Empreitada_.numExtensaoRedeMat));
            }
            if (criteria.getNumLigacoesDomiciliares() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumLigacoesDomiciliares(), Empreitada_.numLigacoesDomiciliares));
            }
            if (criteria.getNumLigacoesTorneiraQuintal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumLigacoesTorneiraQuintal(), Empreitada_.numLigacoesTorneiraQuintal));
            }
            if (criteria.getNumChafarisNovos() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumChafarisNovos(), Empreitada_.numChafarisNovos));
            }
            if (criteria.getNumChafarisReabilitar() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumChafarisReabilitar(), Empreitada_.numChafarisReabilitar));
            }
            if (criteria.getNumCapacidadeTratamentoEta() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumCapacidadeTratamentoEta(), Empreitada_.numCapacidadeTratamentoEta));
            }
            if (criteria.getNumExtensaoRedeMaterial() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumExtensaoRedeMaterial(), Empreitada_.numExtensaoRedeMaterial));
            }
            if (criteria.getNumExtensaoCondutasElelMat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumExtensaoCondutasElelMat(), Empreitada_.numExtensaoCondutasElelMat));
            }
            if (criteria.getNumLigacoes() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumLigacoes(), Empreitada_.numLigacoes));
            }
            if (criteria.getNumCaixasVisitas() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumCaixasVisitas(), Empreitada_.numCaixasVisitas));
            }
            if (criteria.getNumEstacoesElevatorias() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumEstacoesElevatorias(), Empreitada_.numEstacoesElevatorias));
            }
            if (criteria.getNumLatrinas() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumLatrinas(), Empreitada_.numLatrinas));
            }
            if (criteria.getIdProgramasProjectosId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdProgramasProjectosId(), Empreitada_.idProgramasProjectos, ProgramasProjectos_.id));
            }
            if (criteria.getIdSistemaAguaId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdSistemaAguaId(), Empreitada_.idSistemaAgua, SistemaAgua_.id));
            }
            if (criteria.getIdContratoId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdContratoId(), Empreitada_.idContrato, Contrato_.id));
            }
        }
        return specification;
    }

}
