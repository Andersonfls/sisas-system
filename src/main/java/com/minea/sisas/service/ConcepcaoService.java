package com.minea.sisas.service;

import com.minea.sisas.domain.Concepcao;
import com.minea.sisas.repository.ConcepcaoRepository;
import com.minea.sisas.service.dto.ConcepcaoDTO;
import com.minea.sisas.service.mapper.ConcepcaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;


/**
 * Service Implementation for managing Concepcao.
 */
@Service
@Transactional
public class ConcepcaoService {

    private final Logger log = LoggerFactory.getLogger(ConcepcaoService.class);

    private final ConcepcaoRepository concepcaoRepository;

    private final ConcepcaoMapper concepcaoMapper;

    public ConcepcaoService(ConcepcaoRepository concepcaoRepository, ConcepcaoMapper concepcaoMapper) {
        this.concepcaoRepository = concepcaoRepository;
        this.concepcaoMapper = concepcaoMapper;
    }

    /**
     * Save a concepcao.
     *
     * @param concepcaoDTO the entity to save
     * @return the persisted entity
     */
    public ConcepcaoDTO save(ConcepcaoDTO concepcaoDTO) {
        log.debug("Request to save Concepcao : {}", concepcaoDTO);
        Concepcao concepcao = concepcaoMapper.toEntity(concepcaoDTO);
        concepcao.dtUltimaAlteracao(LocalDate.now());
        concepcao = concepcaoRepository.save(concepcao);
        return concepcaoMapper.toDto(concepcao);
    }

    /**
     * Get all the concepcaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ConcepcaoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Concepcaos");
        return concepcaoRepository.findAll(pageable)
            .map(concepcaoMapper::toDto);
    }

    /**
     * Get one concepcao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public ConcepcaoDTO findOne(Long id) {
        log.debug("Request to get Concepcao : {}", id);
        Concepcao concepcao = concepcaoRepository.findOne(id);
        return concepcaoMapper.toDto(concepcao);
    }

    @Transactional(readOnly = true)
    public ConcepcaoDTO findOneByProgramaProjectoId(Long id) {
        log.debug("Request to get Concepcao : {}", id);
        Concepcao concepcao = concepcaoRepository.findByProgramasProjectosId(id);
        if (Objects.isNull(concepcao)) {
            concepcao = new Concepcao();
        }
        return concepcaoMapper.toDto(concepcao);
    }

    /**
     * Delete the concepcao by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Concepcao : {}", id);
        concepcaoRepository.delete(id);
    }
}
