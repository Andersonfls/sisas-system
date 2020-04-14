package com.minea.sisas.service;

import com.minea.sisas.domain.Empreitada;
import com.minea.sisas.domain.Execucao;
import com.minea.sisas.repository.ExecucaoRepository;
import com.minea.sisas.service.dto.EmpreitadaDTO;
import com.minea.sisas.service.dto.ExecucaoDTO;
import com.minea.sisas.service.mapper.ExecucaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


/**
 * Service Implementation for managing Execucao.
 */
@Service
@Transactional
public class ExecucaoService {

    private final Logger log = LoggerFactory.getLogger(ExecucaoService.class);

    private final ExecucaoRepository execucaoRepository;

    private final ExecucaoMapper execucaoMapper;

    public ExecucaoService(ExecucaoRepository execucaoRepository, ExecucaoMapper execucaoMapper) {
        this.execucaoRepository = execucaoRepository;
        this.execucaoMapper = execucaoMapper;
    }

    /**
     * Save a execucao.
     *
     * @param execucaoDTO the entity to save
     * @return the persisted entity
     */
    public ExecucaoDTO save(ExecucaoDTO execucaoDTO) {
        log.debug("Request to save Execucao : {}", execucaoDTO);
        Execucao execucao = execucaoMapper.toEntity(execucaoDTO);
        execucao = execucaoRepository.save(execucao);
        return execucaoMapper.toDto(execucao);
    }

    /**
     * Get all the execucaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ExecucaoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Execucaos");
        return execucaoRepository.findAll(pageable)
            .map(execucaoMapper::toDto);
    }

    /**
     * Get one execucao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public ExecucaoDTO findOne(Long id) {
        log.debug("Request to get Execucao : {}", id);
        Execucao execucao = execucaoRepository.findOne(id);
        return execucaoMapper.toDto(execucao);
    }

    @Transactional(readOnly = true)
    public ExecucaoDTO findOneByProgramasProjectos(Long id) {
        log.debug("Request to get Execucao : {}", id);
        Execucao empreitada = execucaoRepository.findByIdProgramasProjectosId(id);
        if (Objects.isNull(empreitada)) {
            empreitada = new Execucao();
        }
        return execucaoMapper.toDto(empreitada);
    }

    /**
     * Delete the execucao by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Execucao : {}", id);
        execucaoRepository.delete(id);
    }
}
