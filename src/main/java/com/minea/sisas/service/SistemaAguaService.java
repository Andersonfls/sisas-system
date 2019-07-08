package com.minea.sisas.service;

import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.repository.SistemaAguaRepository;
import com.minea.sisas.repository.UserRepository;
import com.minea.sisas.security.SecurityUtils;
import com.minea.sisas.service.dto.SistemaAguaDTO;
import com.minea.sisas.service.mapper.SistemaAguaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;


/**
 * Service Implementation for managing SistemaAgua.
 */
@Service
@Transactional
public class SistemaAguaService {

    private final Logger log = LoggerFactory.getLogger(SistemaAguaService.class);

    private final SistemaAguaRepository sistemaAguaRepository;

    private final SistemaAguaMapper sistemaAguaMapper;

    @Autowired
    private UserRepository userRepository;

    public SistemaAguaService(SistemaAguaRepository sistemaAguaRepository, SistemaAguaMapper sistemaAguaMapper) {
        this.sistemaAguaRepository = sistemaAguaRepository;
        this.sistemaAguaMapper = sistemaAguaMapper;
    }

    /**
     * Save a sistemaAgua.
     *
     * @param sistemaAguaDTO the entity to save
     * @return the persisted entity
     */
    public SistemaAguaDTO save(SistemaAguaDTO sistemaAguaDTO) {
        log.debug("Request to save SistemaAgua : {}", sistemaAguaDTO);
        SistemaAgua sistemaAgua = sistemaAguaMapper.toEntity(sistemaAguaDTO);
        if(Objects.isNull(sistemaAgua.getIdUsuario()))
            sistemaAgua.setIdUsuario(userRepository.findByIdEquals(SecurityUtils.getCurrentUserId()));
        sistemaAgua.setDtUltimaAlteracao(LocalDate.now());
        sistemaAgua = sistemaAguaRepository.save(sistemaAgua);
        return sistemaAguaMapper.toDto(sistemaAgua);
    }

    /**
     * Get all the sistemaAguas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<SistemaAguaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SistemaAguas");
        return sistemaAguaRepository.findAll(pageable)
            .map(sistemaAguaMapper::toDto);
    }

    /**
     * Get one sistemaAgua by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public SistemaAguaDTO findOne(Long id) {
        log.debug("Request to get SistemaAgua : {}", id);
        SistemaAgua sistemaAgua = sistemaAguaRepository.findOne(id);
        return sistemaAguaMapper.toDto(sistemaAgua);
    }

    /**
     * Delete the sistemaAgua by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete SistemaAgua : {}", id);
        sistemaAguaRepository.delete(id);
    }
}
