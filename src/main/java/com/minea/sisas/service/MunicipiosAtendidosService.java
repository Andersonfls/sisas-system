package com.minea.sisas.service;

import com.minea.sisas.domain.MunicipiosAtendidos;
import com.minea.sisas.repository.MunicipiosAtendidosRepository;
import com.minea.sisas.service.dto.MunicipiosAtendidosDTO;
import com.minea.sisas.service.mapper.MunicipiosAtendidosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing MunicipiosAtendidos.
 */
@Service
@Transactional
public class MunicipiosAtendidosService {

    private final Logger log = LoggerFactory.getLogger(MunicipiosAtendidosService.class);

    private final MunicipiosAtendidosRepository municipiosAtendidosRepository;

    private final MunicipiosAtendidosMapper municipiosAtendidosMapper;

    public MunicipiosAtendidosService(MunicipiosAtendidosRepository municipiosAtendidosRepository, MunicipiosAtendidosMapper municipiosAtendidosMapper) {
        this.municipiosAtendidosRepository = municipiosAtendidosRepository;
        this.municipiosAtendidosMapper = municipiosAtendidosMapper;
    }

    /**
     * Save a municipiosAtendidos.
     *
     * @param municipiosAtendidosDTO the entity to save
     * @return the persisted entity
     */
    public MunicipiosAtendidosDTO save(MunicipiosAtendidosDTO municipiosAtendidosDTO) {
        log.debug("Request to save MunicipiosAtendidos : {}", municipiosAtendidosDTO);
        MunicipiosAtendidos municipiosAtendidos = municipiosAtendidosMapper.toEntity(municipiosAtendidosDTO);
        municipiosAtendidos = municipiosAtendidosRepository.save(municipiosAtendidos);
        return municipiosAtendidosMapper.toDto(municipiosAtendidos);
    }

    /**
     * Get all the municipiosAtendidos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<MunicipiosAtendidosDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MunicipiosAtendidos");
        return municipiosAtendidosRepository.findAll(pageable)
            .map(municipiosAtendidosMapper::toDto);
    }

    /**
     * Get one municipiosAtendidos by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public MunicipiosAtendidosDTO findOne(Long id) {
        log.debug("Request to get MunicipiosAtendidos : {}", id);
        MunicipiosAtendidos municipiosAtendidos = municipiosAtendidosRepository.findOne(id);
        return municipiosAtendidosMapper.toDto(municipiosAtendidos);
    }

    /**
     * Delete the municipiosAtendidos by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete MunicipiosAtendidos : {}", id);
        municipiosAtendidosRepository.delete(id);
    }
}
