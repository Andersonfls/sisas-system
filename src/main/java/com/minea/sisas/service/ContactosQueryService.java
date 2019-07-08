package com.minea.sisas.service;

import com.minea.sisas.domain.Contactos_;
import com.minea.sisas.domain.Inicio_;
import com.minea.sisas.domain.Situacao_;
import com.minea.sisas.domain.Contactos;
import com.minea.sisas.repository.ContactosRepository;
import com.minea.sisas.service.dto.ContactosCriteria;
import com.minea.sisas.service.dto.ContactosDTO;
import com.minea.sisas.service.mapper.ContactosMapper;
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
 * Service for executing complex queries for Contactos entities in the database.
 * The main input is a {@link ContactosCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ContactosDTO} or a {@link Page} of {@link ContactosDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ContactosQueryService extends QueryService<Contactos> {

    private final Logger log = LoggerFactory.getLogger(ContactosQueryService.class);


    private final ContactosRepository contactosRepository;

    private final ContactosMapper contactosMapper;

    public ContactosQueryService(ContactosRepository contactosRepository, ContactosMapper contactosMapper) {
        this.contactosRepository = contactosRepository;
        this.contactosMapper = contactosMapper;
    }

    /**
     * Return a {@link List} of {@link ContactosDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ContactosDTO> findByCriteria(ContactosCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Contactos> specification = createSpecification(criteria);
        return contactosMapper.toDto(contactosRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ContactosDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ContactosDTO> findByCriteria(ContactosCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Contactos> specification = createSpecification(criteria);
        final Page<Contactos> result = contactosRepository.findAll(specification, page);
        return result.map(contactosMapper::toDto);
    }

    /**
     * Function to convert ContactosCriteria to a {@link Specifications}
     */
    private Specifications<Contactos> createSpecification(ContactosCriteria criteria) {
        Specifications<Contactos> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Contactos_.id));
            }
            if (criteria.getIdContactos() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdContactos(), Contactos_.idContactos));
            }
            if (criteria.getNmContactos() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNmContactos(), Contactos_.nmContactos));
            }
            if (criteria.getTextoContactos() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTextoContactos(), Contactos_.textoContactos));
            }
            if (criteria.getResumoTextoContactos() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResumoTextoContactos(), Contactos_.resumoTextoContactos));
            }
            if (criteria.getDtLancamento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtLancamento(), Contactos_.dtLancamento));
            }
            if (criteria.getDtUltimaAlteracao() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtUltimaAlteracao(), Contactos_.dtUltimaAlteracao));
            }
            if (criteria.getIdSituacaoId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getIdSituacaoId(), Contactos_.idSituacao, Situacao_.id));
            }
            if (criteria.getInicioId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getInicioId(), Contactos_.inicios, Inicio_.id));
            }
        }
        return specification;
    }

}
