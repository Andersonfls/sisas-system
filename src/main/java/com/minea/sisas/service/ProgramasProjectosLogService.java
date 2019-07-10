package com.minea.sisas.service;

import com.minea.sisas.domain.ProgramasProjectosLog;
import com.minea.sisas.repository.ProgramasProjectosLogRepository;
import com.minea.sisas.service.dto.ProgramasProjectosLogDTO;
import com.minea.sisas.service.mapper.ProgramasProjectosLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing ProgramasProjectosLog.
 */
@Service
@Transactional
public class ProgramasProjectosLogService {

    private final Logger log = LoggerFactory.getLogger(ProgramasProjectosLogService.class);

    private final ProgramasProjectosLogRepository programasProjectosLogRepository;

    private final ProgramasProjectosLogMapper programasProjectosLogMapper;

    public ProgramasProjectosLogService(ProgramasProjectosLogRepository programasProjectosLogRepository, ProgramasProjectosLogMapper programasProjectosLogMapper) {
        this.programasProjectosLogRepository = programasProjectosLogRepository;
        this.programasProjectosLogMapper = programasProjectosLogMapper;
    }

    /**
     * Save a programasProjectosLog.
     *
     * @param programasProjectosLogDTO the entity to save
     * @return the persisted entity
     */
    public ProgramasProjectosLogDTO save(ProgramasProjectosLogDTO programasProjectosLogDTO) {
        log.debug("Request to save ProgramasProjectosLog : {}", programasProjectosLogDTO);
        ProgramasProjectosLog programasProjectosLog = programasProjectosLogMapper.toEntity(programasProjectosLogDTO);
        programasProjectosLog = programasProjectosLogRepository.save(programasProjectosLog);
        return programasProjectosLogMapper.toDto(programasProjectosLog);
    }

    /**
     * Get all the programasProjectosLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ProgramasProjectosLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProgramasProjectosLogs");
        return programasProjectosLogRepository.findAll(pageable)
            .map(programasProjectosLogMapper::toDto);
    }

    /**
     * Get one programasProjectosLog by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public ProgramasProjectosLog findOne(Long id) {
        log.debug("Request to get ProgramasProjectosLog : {}", id);
        ProgramasProjectosLog programasProjectosLog = programasProjectosLogRepository.findOne(id);
        return programasProjectosLog;
    }

    /**
     * Delete the programasProjectosLog by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ProgramasProjectosLog : {}", id);
        programasProjectosLogRepository.delete(id);
    }
}
