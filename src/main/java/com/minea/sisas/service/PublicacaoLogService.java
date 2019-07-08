package com.minea.sisas.service;

import com.minea.sisas.domain.PublicacaoLog;
import com.minea.sisas.repository.PublicacaoLogRepository;
import com.minea.sisas.service.dto.PublicacaoLogDTO;
import com.minea.sisas.service.mapper.PublicacaoLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing PublicacaoLog.
 */
@Service
@Transactional
public class PublicacaoLogService {

    private final Logger log = LoggerFactory.getLogger(PublicacaoLogService.class);

    private final PublicacaoLogRepository publicacaoLogRepository;

    private final PublicacaoLogMapper publicacaoLogMapper;

    public PublicacaoLogService(PublicacaoLogRepository publicacaoLogRepository, PublicacaoLogMapper publicacaoLogMapper) {
        this.publicacaoLogRepository = publicacaoLogRepository;
        this.publicacaoLogMapper = publicacaoLogMapper;
    }

    /**
     * Save a publicacaoLog.
     *
     * @param publicacaoLogDTO the entity to save
     * @return the persisted entity
     */
    public PublicacaoLogDTO save(PublicacaoLogDTO publicacaoLogDTO) {
        log.debug("Request to save PublicacaoLog : {}", publicacaoLogDTO);
        PublicacaoLog publicacaoLog = publicacaoLogMapper.toEntity(publicacaoLogDTO);
        publicacaoLog = publicacaoLogRepository.save(publicacaoLog);
        return publicacaoLogMapper.toDto(publicacaoLog);
    }

    /**
     * Get all the publicacaoLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<PublicacaoLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PublicacaoLogs");
        return publicacaoLogRepository.findAll(pageable)
            .map(publicacaoLogMapper::toDto);
    }

    /**
     * Get one publicacaoLog by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public PublicacaoLogDTO findOne(Long id) {
        log.debug("Request to get PublicacaoLog : {}", id);
        PublicacaoLog publicacaoLog = publicacaoLogRepository.findOne(id);
        return publicacaoLogMapper.toDto(publicacaoLog);
    }

    /**
     * Delete the publicacaoLog by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete PublicacaoLog : {}", id);
        publicacaoLogRepository.delete(id);
    }
}
