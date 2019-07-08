package com.minea.sisas.service;

import com.minea.sisas.domain.Fornecedor;
import com.minea.sisas.repository.FornecedorRepository;
import com.minea.sisas.service.dto.FornecedorDTO;
import com.minea.sisas.service.mapper.FornecedorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Fornecedor.
 */
@Service
@Transactional
public class FornecedorService {

    private final Logger log = LoggerFactory.getLogger(FornecedorService.class);

    private final FornecedorRepository fornecedorRepository;

    private final FornecedorMapper fornecedorMapper;

    public FornecedorService(FornecedorRepository fornecedorRepository, FornecedorMapper fornecedorMapper) {
        this.fornecedorRepository = fornecedorRepository;
        this.fornecedorMapper = fornecedorMapper;
    }

    /**
     * Save a fornecedor.
     *
     * @param fornecedorDTO the entity to save
     * @return the persisted entity
     */
    public FornecedorDTO save(FornecedorDTO fornecedorDTO) {
        log.debug("Request to save Fornecedor : {}", fornecedorDTO);
        Fornecedor fornecedor = fornecedorMapper.toEntity(fornecedorDTO);
        fornecedor = fornecedorRepository.save(fornecedor);
        return fornecedorMapper.toDto(fornecedor);
    }

    /**
     * Get all the fornecedors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<FornecedorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Fornecedors");
        return fornecedorRepository.findAll(pageable)
            .map(fornecedorMapper::toDto);
    }

    /**
     * Get one fornecedor by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public FornecedorDTO findOne(Long id) {
        log.debug("Request to get Fornecedor : {}", id);
        Fornecedor fornecedor = fornecedorRepository.findOne(id);
        return fornecedorMapper.toDto(fornecedor);
    }

    /**
     * Delete the fornecedor by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Fornecedor : {}", id);
        fornecedorRepository.delete(id);
    }
}
