package com.minea.sisas.service;

import java.util.List;

import com.minea.sisas.service.mapper.ContratoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.minea.sisas.domain.Contrato;
import com.minea.sisas.domain.*; // for static metamodels
import com.minea.sisas.repository.ContratoRepository;
import com.minea.sisas.service.dto.ContratoCriteria;

import com.minea.sisas.service.dto.ContratoDTO;
import com.minea.sisas.service.mapper.ContratoMapper;

/**
 * Service for executing complex queries for Contrato entities in the database.
 * The main input is a {@link ContratoCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ContratoDTO} or a {@link Page} of {@link ContratoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ContratoQueryService extends QueryService<Contrato> {

    private final Logger log = LoggerFactory.getLogger(ContratoQueryService.class);


    private final ContratoRepository contratoRepository;

    private final ContratoMapper contratoMapper;

    public ContratoQueryService(ContratoRepository contratoRepository, ContratoMapper contratoMapper) {
        this.contratoRepository = contratoRepository;
        this.contratoMapper = contratoMapper;
    }

    /**
     * Return a {@link List} of {@link ContratoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ContratoDTO> findByCriteria(ContratoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Contrato> specification = createSpecification(criteria);
        return contratoMapper.toDto(contratoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ContratoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ContratoDTO> findByCriteria(ContratoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Contrato> specification = createSpecification(criteria);
        final Page<Contrato> result = contratoRepository.findAll(specification, page);
        return result.map(contratoMapper::toDto);
    }

    /**
     * Function to convert ContratoCriteria to a {@link Specifications}
     */
    private Specifications<Contrato> createSpecification(ContratoCriteria criteria) {
        Specifications<Contrato> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Contrato_.id));
            }
            if (criteria.getTipoEmpreitada() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTipoEmpreitada(), Contrato_.tipoEmpreitada));
            }
            if (criteria.getDtLancamento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtLancamento(), Contrato_.dtLancamento));
            }
            if (criteria.getNmEmpresaAdjudicitaria() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmEmpresaAdjudicitaria(), Contrato_.nmEmpresaAdjudicitaria));
            }
            if (criteria.getValorContrato() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValorContrato(), Contrato_.valorContrato));
            }
            if (criteria.getDtAssinatura() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtAssinatura(), Contrato_.dtAssinatura));
            }
            if (criteria.getDtFinalizacaoProcessoHomologAprov() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtFinalizacaoProcessoHomologAprov(), Contrato_.dtFinalizacaoProcessoHomologAprov));
            }
            if (criteria.getTipoMoeda() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTipoMoeda(), Contrato_.tipoMoeda));
            }
            if (criteria.getValorAdiantamento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValorAdiantamento(), Contrato_.valorAdiantamento));
            }
            if (criteria.getDtAdiantamento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtAdiantamento(), Contrato_.dtAdiantamento));
            }
            if (criteria.getDtInicio() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtInicio(), Contrato_.dtInicio));
            }
            if (criteria.getPrazoExecucao() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrazoExecucao(), Contrato_.prazoExecucao));
            }
            if (criteria.getDtRecepcaoProvisoria() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtRecepcaoProvisoria(), Contrato_.dtRecepcaoProvisoria));
            }
            if (criteria.getDtRecepcaoDefinitiva() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtRecepcaoDefinitiva(), Contrato_.dtRecepcaoDefinitiva));
            }
            if (criteria.getDtRecepcaoComicionamento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtRecepcaoComicionamento(), Contrato_.dtRecepcaoComicionamento));
            }
            if (criteria.getNmResposavelAntProjeto() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmResposavelAntProjeto(), Contrato_.nmResposavelAntProjeto));
            }
            if (criteria.getNmResposavelProjeto() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmResposavelProjeto(), Contrato_.nmResposavelProjeto));
            }
            if (criteria.getIdSistemaAguaId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdSistemaAguaId(), Contrato_.idSistemaAgua, SistemaAgua_.id));
            }
            if (criteria.getEmpreitadaId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getEmpreitadaId(), Contrato_.empreitadas, Empreitada_.id));
            }
            if (criteria.getExecucaoId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getExecucaoId(), Contrato_.execucaos, Execucao_.id));
            }
        }
        return specification;
    }

}
