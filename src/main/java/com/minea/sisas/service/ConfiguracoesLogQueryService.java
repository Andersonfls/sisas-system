package com.minea.sisas.service;

import com.minea.sisas.domain.ConfiguracoesLog_;
import com.minea.sisas.domain.ConfiguracoesLog;
import com.minea.sisas.repository.ConfiguracoesLogRepository;
import com.minea.sisas.service.dto.ConfiguracoesLogCriteria;
import com.minea.sisas.service.dto.ConfiguracoesLogDTO;
import com.minea.sisas.service.mapper.ConfiguracoesLogMapper;
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
 * Service for executing complex queries for ConfiguracoesLog entities in the database.
 * The main input is a {@link ConfiguracoesLogCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ConfiguracoesLogDTO} or a {@link Page} of {@link ConfiguracoesLogDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ConfiguracoesLogQueryService extends QueryService<ConfiguracoesLog> {

    private final Logger log = LoggerFactory.getLogger(ConfiguracoesLogQueryService.class);


    private final ConfiguracoesLogRepository configuracoesLogRepository;

    private final ConfiguracoesLogMapper configuracoesLogMapper;

    public ConfiguracoesLogQueryService(ConfiguracoesLogRepository configuracoesLogRepository, ConfiguracoesLogMapper configuracoesLogMapper) {
        this.configuracoesLogRepository = configuracoesLogRepository;
        this.configuracoesLogMapper = configuracoesLogMapper;
    }

    /**
     * Return a {@link List} of {@link ConfiguracoesLogDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ConfiguracoesLogDTO> findByCriteria(ConfiguracoesLogCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<ConfiguracoesLog> specification = createSpecification(criteria);
        return configuracoesLogMapper.toDto(configuracoesLogRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ConfiguracoesLogDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ConfiguracoesLogDTO> findByCriteria(ConfiguracoesLogCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<ConfiguracoesLog> specification = createSpecification(criteria);
        final Page<ConfiguracoesLog> result = configuracoesLogRepository.findAll(specification, page);
        return result.map(configuracoesLogMapper::toDto);
    }

    /**
     * Function to convert ConfiguracoesLogCriteria to a {@link Specifications}
     */
    private Specifications<ConfiguracoesLog> createSpecification(ConfiguracoesLogCriteria criteria) {
        Specifications<ConfiguracoesLog> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ConfiguracoesLog_.id));
            }
            if (criteria.getAcao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAcao(), ConfiguracoesLog_.acao));
            }
            if (criteria.getIdUsuario() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIdUsuario(), ConfiguracoesLog_.idUsuario));
            }
            if (criteria.getLog() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLog(), ConfiguracoesLog_.log));
            }
            if (criteria.getDtLog() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDtLog(), ConfiguracoesLog_.dtLog));
            }
        }
        return specification;
    }

}
