package com.minea.sisas.service;

import com.minea.sisas.domain.Adjudicacao;
import com.minea.sisas.repository.AdjudicacaoRepository;
import com.minea.sisas.service.dto.AdjudicacaoDTO;
import com.minea.sisas.service.mapper.AdjudicacaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Adjudicacao.
 */
@Service
@Transactional
public class AdjudicacaoService {

    private final Logger log = LoggerFactory.getLogger(AdjudicacaoService.class);

    private final AdjudicacaoRepository adjudicacaoRepository;

    private final AdjudicacaoMapper adjudicacaoMapper;

    public AdjudicacaoService(AdjudicacaoRepository adjudicacaoRepository, AdjudicacaoMapper adjudicacaoMapper) {
        this.adjudicacaoRepository = adjudicacaoRepository;
        this.adjudicacaoMapper = adjudicacaoMapper;
    }

    /**
     * Save a adjudicacao.
     *
     * @param adjudicacaoDTO the entity to save
     * @return the persisted entity
     */
    public AdjudicacaoDTO save(AdjudicacaoDTO adjudicacaoDTO) {
        log.debug("Request to save Adjudicacao : {}", adjudicacaoDTO);
        Adjudicacao adjudicacao = adjudicacaoMapper.toEntity(adjudicacaoDTO);
        adjudicacao = adjudicacaoRepository.save(adjudicacao);
        return adjudicacaoMapper.toDto(adjudicacao);
    }

    /**
     * Get all the adjudicacaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AdjudicacaoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Adjudicacaos");
        return adjudicacaoRepository.findAll(pageable)
            .map(adjudicacaoMapper::toDto);
    }

    /**
     * Get one adjudicacao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public AdjudicacaoDTO findOne(Long id) {
        log.debug("Request to get Adjudicacao : {}", id);
        Adjudicacao adjudicacao = adjudicacaoRepository.findOne(id);
        return adjudicacaoMapper.toDto(adjudicacao);
    }

    /**
     * Delete the adjudicacao by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Adjudicacao : {}", id);
        adjudicacaoRepository.delete(id);
    }
}
