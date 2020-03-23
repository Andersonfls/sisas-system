package com.minea.sisas.service;

import com.minea.sisas.domain.RelatoriosLog;
import com.minea.sisas.domain.SegurancaLog;
import com.minea.sisas.repository.RelatoriosLogRepository;
import com.minea.sisas.repository.SegurancaLogRepository;
import com.minea.sisas.service.dto.RelatoriosLogDTO;
import com.minea.sisas.service.dto.SegurancaLogDTO;
import com.minea.sisas.service.mapper.RelatoriosLogMapper;
import com.minea.sisas.service.mapper.SegurancaLogMapper;
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
public class SegurancaLogService {

    private final Logger log = LoggerFactory.getLogger(SegurancaLogService.class);

    private final SegurancaLogRepository segurancaLogRepository;

    private final SegurancaLogMapper segurancaLogMapper;

    public SegurancaLogService(SegurancaLogRepository segurancaLogRepository, SegurancaLogMapper segurancaLogMapper) {
        this.segurancaLogRepository = segurancaLogRepository;
        this.segurancaLogMapper = segurancaLogMapper;
    }

    /**
     * Save a segurancaLog.
     *
     * @param segurancaLogDTO the entity to save
     * @return the persisted entity
     */
    public SegurancaLogDTO save(SegurancaLogDTO segurancaLogDTO) {
        log.debug("Request to save RelatoriosLog : {}", segurancaLogDTO);
        SegurancaLog segurancaLog = segurancaLogMapper.toEntity(segurancaLogDTO);
        segurancaLog.setIdUsuarioAlterado(segurancaLogDTO.getIdUsuarioAlterado());
        segurancaLog = segurancaLogRepository.save(segurancaLog);
        return segurancaLogMapper.toDto(segurancaLog);
    }

    /**
     * Get all the segurancaLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<SegurancaLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SegurancaLogs");
        return segurancaLogRepository.findAll(pageable)
            .map(segurancaLogMapper::toDto);
    }

    /**
     * Get one segurancaLog by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public SegurancaLogDTO findOne(Long id) {
        log.debug("Request to get SegurancaLog : {}", id);
        SegurancaLog segurancaLog = segurancaLogRepository.findOne(id);
        return segurancaLogMapper.toDto(segurancaLog);
    }

    /**
     * Delete the segurancaLog by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete SegurancaLog : {}", id);
        segurancaLogRepository.delete(id);
    }
}
