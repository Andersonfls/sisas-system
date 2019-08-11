package com.minea.sisas.service;


import com.minea.sisas.domain.Fornecedor_;
import com.minea.sisas.domain.Fornecedor;
import com.minea.sisas.repository.FornecedorRepository;
import com.minea.sisas.service.dto.FornecedorCriteria;
import com.minea.sisas.service.dto.FornecedorDTO;
import com.minea.sisas.service.mapper.FornecedorMapper;
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
 * Service for executing complex queries for Fornecedor entities in the database.
 * The main input is a {@link FornecedorCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FornecedorDTO} or a {@link Page} of {@link FornecedorDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FornecedorQueryService extends QueryService<Fornecedor> {

    private final Logger log = LoggerFactory.getLogger(FornecedorQueryService.class);


    private final FornecedorRepository fornecedorRepository;

    private final FornecedorMapper fornecedorMapper;

    public FornecedorQueryService(FornecedorRepository fornecedorRepository, FornecedorMapper fornecedorMapper) {
        this.fornecedorRepository = fornecedorRepository;
        this.fornecedorMapper = fornecedorMapper;
    }

    /**
     * Return a {@link List} of {@link FornecedorDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FornecedorDTO> findByCriteria(FornecedorCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Fornecedor> specification = createSpecification(criteria);
        return fornecedorMapper.toDto(fornecedorRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FornecedorDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FornecedorDTO> findByCriteria(FornecedorCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Fornecedor> specification = createSpecification(criteria);
        final Page<Fornecedor> result = fornecedorRepository.findAll(specification, page);
        return result.map(fornecedorMapper::toDto);
    }

    /**
     * Function to convert FornecedorCriteria to a {@link Specifications}
     */
    private Specifications<Fornecedor> createSpecification(FornecedorCriteria criteria) {
        Specifications<Fornecedor> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Fornecedor_.id));
            }
            if (criteria.getNmFornecedor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmFornecedor(), Fornecedor_.nmFornecedor));
            }
            if (criteria.getNumContribuinte() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumContribuinte(), Fornecedor_.numContribuinte));
            }
            if (criteria.getEndereco() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEndereco(), Fornecedor_.endereco));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Fornecedor_.email));
            }
            if (criteria.getEspecialidade() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEspecialidade(), Fornecedor_.especialidade));
            }
            if (criteria.getContato() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContato(), Fornecedor_.contato));
            }
        }
        return specification;
    }

}
