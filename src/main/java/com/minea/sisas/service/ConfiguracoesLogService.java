package com.minea.sisas.service;

import com.minea.sisas.domain.ConfiguracoesLog;
import com.minea.sisas.repository.ConfiguracoesLogRepository;
import com.minea.sisas.service.dto.ConfiguracoesLogDTO;
import com.minea.sisas.service.mapper.ConfiguracoesLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing ConfiguracoesLog.
 */
@Service
@Transactional
public class ConfiguracoesLogService {

    private final Logger log = LoggerFactory.getLogger(ConfiguracoesLogService.class);

    private final ConfiguracoesLogRepository configuracoesLogRepository;

    private final ConfiguracoesLogMapper configuracoesLogMapper;

    public ConfiguracoesLogService(ConfiguracoesLogRepository configuracoesLogRepository, ConfiguracoesLogMapper configuracoesLogMapper) {
        this.configuracoesLogRepository = configuracoesLogRepository;
        this.configuracoesLogMapper = configuracoesLogMapper;
    }

    /**
     * Save a configuracoesLog.
     *
     * @param configuracoesLogDTO the entity to save
     * @return the persisted entity
     */
    public ConfiguracoesLogDTO save(ConfiguracoesLogDTO configuracoesLogDTO) {
        log.debug("Request to save ConfiguracoesLog : {}", configuracoesLogDTO);
        ConfiguracoesLog configuracoesLog = configuracoesLogMapper.toEntity(configuracoesLogDTO);
        configuracoesLog = configuracoesLogRepository.save(configuracoesLog);
        return configuracoesLogMapper.toDto(configuracoesLog);
    }

    /**
     * Get all the configuracoesLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ConfiguracoesLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ConfiguracoesLogs");
        return configuracoesLogRepository.findAll(pageable)
            .map(configuracoesLogMapper::toDto);
    }

    /**
     * Get one configuracoesLog by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public ConfiguracoesLogDTO findOne(Long id) {
        log.debug("Request to get ConfiguracoesLog : {}", id);
        ConfiguracoesLog configuracoesLog = configuracoesLogRepository.findOne(id);
        return configuracoesLogMapper.toDto(configuracoesLog);
    }

    /**
     * Delete the configuracoesLog by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ConfiguracoesLog : {}", id);
        configuracoesLogRepository.delete(id);
    }
}
