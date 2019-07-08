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

import com.minea.sisas.domain.Execucao;
import com.minea.sisas.domain.*; // for static metamodels
import com.minea.sisas.repository.ExecucaoRepository;
import com.minea.sisas.service.dto.ExecucaoCriteria;

import com.minea.sisas.service.dto.ExecucaoDTO;
import com.minea.sisas.service.mapper.ExecucaoMapper;

/**
 * Service for executing complex queries for Execucao entities in the database.
 * The main input is a {@link ExecucaoCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ExecucaoDTO} or a {@link Page} of {@link ExecucaoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ExecucaoQueryService extends QueryService<Execucao> {

    private final Logger log = LoggerFactory.getLogger(ExecucaoQueryService.class);


    private final ExecucaoRepository execucaoRepository;

    private final ExecucaoMapper execucaoMapper;

    public ExecucaoQueryService(ExecucaoRepository execucaoRepository, ExecucaoMapper execucaoMapper) {
        this.execucaoRepository = execucaoRepository;
        this.execucaoMapper = execucaoMapper;
    }

    /**
     * Return a {@link List} of {@link ExecucaoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ExecucaoDTO> findByCriteria(ExecucaoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Execucao> specification = createSpecification(criteria);
        return execucaoMapper.toDto(execucaoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ExecucaoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ExecucaoDTO> findByCriteria(ExecucaoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Execucao> specification = createSpecification(criteria);
        final Page<Execucao> result = execucaoRepository.findAll(specification, page);
        return result.map(execucaoMapper::toDto);
    }

    /**
     * Function to convert ExecucaoCriteria to a {@link Specifications}
     */
    private Specifications<Execucao> createSpecification(ExecucaoCriteria criteria) {
        Specifications<Execucao> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Execucao_.id));
            }
            if (criteria.getTipoEmpreitada() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTipoEmpreitada(), Execucao_.tipoEmpreitada));
            }
            if (criteria.getDtLancamento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtLancamento(), Execucao_.dtLancamento));
            }
            if (criteria.getDtPeridoReferencia() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtPeridoReferencia(), Execucao_.dtPeridoReferencia));
            }
            if (criteria.getDtFimReferencia() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtFimReferencia(), Execucao_.dtFimReferencia));
            }
            if (criteria.getValorFacturadoPeriodo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValorFacturadoPeriodo(), Execucao_.valorFacturadoPeriodo));
            }
            if (criteria.getDtFactura() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtFactura(), Execucao_.dtFactura));
            }
            if (criteria.getNumFactura() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumFactura(), Execucao_.numFactura));
            }
            if (criteria.getTxCambio() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTxCambio(), Execucao_.txCambio));
            }
            if (criteria.getConstrangimento() != null) {
                specification = specification.and(buildStringSpecification(criteria.getConstrangimento(), Execucao_.constrangimento));
            }
            if (criteria.getValorPagoPeriodo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValorPagoPeriodo(), Execucao_.valorPagoPeriodo));
            }
            if (criteria.getIdSituacaoId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdSituacaoId(), Execucao_.idSituacao, Situacao_.id));
            }
            if (criteria.getIdProgramasProjectosId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdProgramasProjectosId(), Execucao_.idProgramasProjectos, ProgramasProjectos_.id));
            }
            if (criteria.getIdSistemaAguaId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdSistemaAguaId(), Execucao_.idSistemaAgua, SistemaAgua_.id));
            }
            if (criteria.getIdContratoId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdContratoId(), Execucao_.idContrato, Contrato_.id));
            }
        }
        return specification;
    }

}
