package com.minea.sisas.service;

import com.minea.sisas.domain.Situacao;
import com.minea.sisas.repository.SituacaoRepository;
import com.minea.sisas.service.dto.SituacaoDTO;
import com.minea.sisas.service.mapper.SituacaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Situacao.
 */
@Service
@Transactional
public class SituacaoService {

    private final Logger log = LoggerFactory.getLogger(SituacaoService.class);

    private final SituacaoRepository situacaoRepository;

    private final SituacaoMapper situacaoMapper;

    public SituacaoService(SituacaoRepository situacaoRepository, SituacaoMapper situacaoMapper) {
        this.situacaoRepository = situacaoRepository;
        this.situacaoMapper = situacaoMapper;
    }

    /**
     * Save a situacao.
     *
     * @param situacaoDTO the entity to save
     * @return the persisted entity
     */
    public SituacaoDTO save(SituacaoDTO situacaoDTO) {
        log.debug("Request to save Situacao : {}", situacaoDTO);
        Situacao situacao = situacaoMapper.toEntity(situacaoDTO);
        situacao = situacaoRepository.save(situacao);
        return situacaoMapper.toDto(situacao);
    }

    /**
     * Get all the situacaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<SituacaoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Situacaos");
        return situacaoRepository.findAll(pageable)
            .map(situacaoMapper::toDto);
    }

    /**
     * Get one situacao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public SituacaoDTO findOne(Long id) {
        log.debug("Request to get Situacao : {}", id);
        Situacao situacao = situacaoRepository.findOne(id);
        return situacaoMapper.toDto(situacao);
    }

    /**
     * Delete the situacao by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Situacao : {}", id);
        situacaoRepository.delete(id);
    }
}
