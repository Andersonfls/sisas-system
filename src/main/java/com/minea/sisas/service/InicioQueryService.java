package com.minea.sisas.service;


import com.minea.sisas.domain.*;
import com.minea.sisas.domain.Inicio;
import com.minea.sisas.repository.InicioRepository;
import com.minea.sisas.service.dto.InicioCriteria;
import com.minea.sisas.service.dto.InicioDTO;
import com.minea.sisas.service.mapper.InicioMapper;
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
 * Service for executing complex queries for Inicio entities in the database.
 * The main input is a {@link InicioCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link InicioDTO} or a {@link Page} of {@link InicioDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class InicioQueryService extends QueryService<Inicio> {

    private final Logger log = LoggerFactory.getLogger(InicioQueryService.class);


    private final InicioRepository inicioRepository;

    private final InicioMapper inicioMapper;

    public InicioQueryService(InicioRepository inicioRepository, InicioMapper inicioMapper) {
        this.inicioRepository = inicioRepository;
        this.inicioMapper = inicioMapper;
    }

    /**
     * Return a {@link List} of {@link InicioDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<InicioDTO> findByCriteria(InicioCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Inicio> specification = createSpecification(criteria);
        return inicioMapper.toDto(inicioRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link InicioDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<InicioDTO> findByCriteria(InicioCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Inicio> specification = createSpecification(criteria);
        final Page<Inicio> result = inicioRepository.findAll(specification, page);
        return result.map(inicioMapper::toDto);
    }

    /**
     * Function to convert InicioCriteria to a {@link Specifications}
     */
    private Specifications<Inicio> createSpecification(InicioCriteria criteria) {
        Specifications<Inicio> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Inicio_.id));
            }
            if (criteria.getDestaques() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDestaques(), Inicio_.destaques));
            }
            if (criteria.getUltimasNoticias() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUltimasNoticias(), Inicio_.ultimasNoticias));
            }
            if (criteria.getPublicacoes() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPublicacoes(), Inicio_.publicacoes));
            }
            if (criteria.getUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUrl(), Inicio_.url));
            }
            if (criteria.getAlt() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAlt(), Inicio_.alt));
            }
            if (criteria.getIdSituacaoId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdSituacaoId(), Inicio_.idSituacao, Situacao_.id));
            }
            if (criteria.getIdSobreDnaId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdSobreDnaId(), Inicio_.idSobreDna, SobreDna_.id));
            }
            if (criteria.getIdNoticiasId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdNoticiasId(), Inicio_.idNoticias, Noticias_.id));
            }
            if (criteria.getIdProjectosId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdProjectosId(), Inicio_.idProjectos, Projectos_.id));
            }
            if (criteria.getIdPublicacaoId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdPublicacaoId(), Inicio_.idPublicacao, Publicacao_.id));
            }
            if (criteria.getIdContactosId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdContactosId(), Inicio_.idContactos, Contactos_.id));
            }
        }
        return specification;
    }

}
