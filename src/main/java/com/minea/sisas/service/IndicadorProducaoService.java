package com.minea.sisas.service;

import com.minea.sisas.domain.IndicadorProducao;
import com.minea.sisas.repository.IndicadorProducaoRepository;
import com.minea.sisas.service.dto.IndicadorProducaoDTO;
import com.minea.sisas.service.mapper.IndicadorProducaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing IndicadorProducao.
 */
@Service
@Transactional
public class IndicadorProducaoService {

    private final Logger log = LoggerFactory.getLogger(IndicadorProducaoService.class);

    private final IndicadorProducaoRepository indicadorProducaoRepository;

    private final IndicadorProducaoMapper indicadorProducaoMapper;

    public IndicadorProducaoService(IndicadorProducaoRepository indicadorProducaoRepository, IndicadorProducaoMapper indicadorProducaoMapper) {
        this.indicadorProducaoRepository = indicadorProducaoRepository;
        this.indicadorProducaoMapper = indicadorProducaoMapper;
    }

    /**
     * Save a indicadorProducao.
     *
     * @param indicadorProducaoDTO the entity to save
     * @return the persisted entity
     */
    public IndicadorProducaoDTO save(IndicadorProducaoDTO indicadorProducaoDTO) {
        log.debug("Request to save IndicadorProducao : {}", indicadorProducaoDTO);
        IndicadorProducao indicadorProducao = indicadorProducaoMapper.toEntity(indicadorProducaoDTO);
        indicadorProducao = indicadorProducaoRepository.save(indicadorProducao);
        return indicadorProducaoMapper.toDto(indicadorProducao);
    }

    /**
     * Get all the indicadorProducaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<IndicadorProducaoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all IndicadorProducaos");
        return indicadorProducaoRepository.findAll(pageable)
            .map(indicadorProducaoMapper::toDto);
    }

    /**
     * Get one indicadorProducao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public IndicadorProducaoDTO findOne(Long id) {
        log.debug("Request to get IndicadorProducao : {}", id);
        IndicadorProducao indicadorProducao = indicadorProducaoRepository.findOne(id);
        return indicadorProducaoMapper.toDto(indicadorProducao);
    }

    /**
     * Delete the indicadorProducao by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete IndicadorProducao : {}", id);
        indicadorProducaoRepository.delete(id);
    }
}
