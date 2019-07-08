package com.minea.sisas.service;

import com.minea.sisas.domain.IndicadorProducaoLog;
import com.minea.sisas.repository.IndicadorProducaoLogRepository;
import com.minea.sisas.service.dto.IndicadorProducaoLogDTO;
import com.minea.sisas.service.mapper.IndicadorProducaoLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing IndicadorProducaoLog.
 */
@Service
@Transactional
public class IndicadorProducaoLogService {

    private final Logger log = LoggerFactory.getLogger(IndicadorProducaoLogService.class);

    private final IndicadorProducaoLogRepository indicadorProducaoLogRepository;

    private final IndicadorProducaoLogMapper indicadorProducaoLogMapper;

    public IndicadorProducaoLogService(IndicadorProducaoLogRepository indicadorProducaoLogRepository, IndicadorProducaoLogMapper indicadorProducaoLogMapper) {
        this.indicadorProducaoLogRepository = indicadorProducaoLogRepository;
        this.indicadorProducaoLogMapper = indicadorProducaoLogMapper;
    }

    /**
     * Save a indicadorProducaoLog.
     *
     * @param indicadorProducaoLogDTO the entity to save
     * @return the persisted entity
     */
    public IndicadorProducaoLogDTO save(IndicadorProducaoLogDTO indicadorProducaoLogDTO) {
        log.debug("Request to save IndicadorProducaoLog : {}", indicadorProducaoLogDTO);
        IndicadorProducaoLog indicadorProducaoLog = indicadorProducaoLogMapper.toEntity(indicadorProducaoLogDTO);
        indicadorProducaoLog = indicadorProducaoLogRepository.save(indicadorProducaoLog);
        return indicadorProducaoLogMapper.toDto(indicadorProducaoLog);
    }

    /**
     * Get all the indicadorProducaoLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<IndicadorProducaoLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all IndicadorProducaoLogs");
        return indicadorProducaoLogRepository.findAll(pageable)
            .map(indicadorProducaoLogMapper::toDto);
    }

    /**
     * Get one indicadorProducaoLog by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public IndicadorProducaoLogDTO findOne(Long id) {
        log.debug("Request to get IndicadorProducaoLog : {}", id);
        IndicadorProducaoLog indicadorProducaoLog = indicadorProducaoLogRepository.findOne(id);
        return indicadorProducaoLogMapper.toDto(indicadorProducaoLog);
    }

    /**
     * Delete the indicadorProducaoLog by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete IndicadorProducaoLog : {}", id);
        indicadorProducaoLogRepository.delete(id);
    }
}
