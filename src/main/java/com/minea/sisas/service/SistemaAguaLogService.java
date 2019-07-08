package com.minea.sisas.service;

import com.minea.sisas.domain.SistemaAguaLog;
import com.minea.sisas.repository.SistemaAguaLogRepository;
import com.minea.sisas.service.dto.SistemaAguaLogDTO;
import com.minea.sisas.service.mapper.SistemaAguaLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing SistemaAguaLog.
 */
@Service
@Transactional
public class SistemaAguaLogService {

    private final Logger log = LoggerFactory.getLogger(SistemaAguaLogService.class);

    private final SistemaAguaLogRepository sistemaAguaLogRepository;

    private final SistemaAguaLogMapper sistemaAguaLogMapper;

    public SistemaAguaLogService(SistemaAguaLogRepository sistemaAguaLogRepository, SistemaAguaLogMapper sistemaAguaLogMapper) {
        this.sistemaAguaLogRepository = sistemaAguaLogRepository;
        this.sistemaAguaLogMapper = sistemaAguaLogMapper;
    }

    /**
     * Save a sistemaAguaLog.
     *
     * @param sistemaAguaLogDTO the entity to save
     * @return the persisted entity
     */
    public SistemaAguaLogDTO save(SistemaAguaLogDTO sistemaAguaLogDTO) {
        log.debug("Request to save SistemaAguaLog : {}", sistemaAguaLogDTO);
        SistemaAguaLog sistemaAguaLog = sistemaAguaLogMapper.toEntity(sistemaAguaLogDTO);
        sistemaAguaLog = sistemaAguaLogRepository.save(sistemaAguaLog);
        return sistemaAguaLogMapper.toDto(sistemaAguaLog);
    }

    /**
     * Get all the sistemaAguaLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<SistemaAguaLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SistemaAguaLogs");
        return sistemaAguaLogRepository.findAll(pageable)
            .map(sistemaAguaLogMapper::toDto);
    }

    /**
     * Get one sistemaAguaLog by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public SistemaAguaLogDTO findOne(Long id) {
        log.debug("Request to get SistemaAguaLog : {}", id);
        SistemaAguaLog sistemaAguaLog = sistemaAguaLogRepository.findOne(id);
        return sistemaAguaLogMapper.toDto(sistemaAguaLog);
    }

    /**
     * Delete the sistemaAguaLog by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete SistemaAguaLog : {}", id);
        sistemaAguaLogRepository.delete(id);
    }
}
