package com.minea.sisas.service;

import com.minea.sisas.domain.RelatoriosLog;
import com.minea.sisas.repository.RelatoriosLogRepository;
import com.minea.sisas.service.dto.RelatoriosLogDTO;
import com.minea.sisas.service.mapper.RelatoriosLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing RelatoriosLog.
 */
@Service
@Transactional
public class RelatoriosLogService {

    private final Logger log = LoggerFactory.getLogger(RelatoriosLogService.class);

    private final RelatoriosLogRepository relatoriosLogRepository;

    private final RelatoriosLogMapper relatoriosLogMapper;

    public RelatoriosLogService(RelatoriosLogRepository relatoriosLogRepository, RelatoriosLogMapper relatoriosLogMapper) {
        this.relatoriosLogRepository = relatoriosLogRepository;
        this.relatoriosLogMapper = relatoriosLogMapper;
    }

    /**
     * Save a relatoriosLog.
     *
     * @param relatoriosLogDTO the entity to save
     * @return the persisted entity
     */
    public RelatoriosLogDTO save(RelatoriosLogDTO relatoriosLogDTO) {
        log.debug("Request to save RelatoriosLog : {}", relatoriosLogDTO);
        RelatoriosLog relatoriosLog = relatoriosLogMapper.toEntity(relatoriosLogDTO);
        relatoriosLog = relatoriosLogRepository.save(relatoriosLog);
        return relatoriosLogMapper.toDto(relatoriosLog);
    }

    /**
     * Get all the relatoriosLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<RelatoriosLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RelatoriosLogs");
        return relatoriosLogRepository.findAll(pageable)
            .map(relatoriosLogMapper::toDto);
    }

    /**
     * Get one relatoriosLog by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public RelatoriosLogDTO findOne(Long id) {
        log.debug("Request to get RelatoriosLog : {}", id);
        RelatoriosLog relatoriosLog = relatoriosLogRepository.findOne(id);
        return relatoriosLogMapper.toDto(relatoriosLog);
    }

    /**
     * Delete the relatoriosLog by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete RelatoriosLog : {}", id);
        relatoriosLogRepository.delete(id);
    }
}
