package com.minea.sisas.service;

import com.minea.sisas.domain.Especialidades;
import com.minea.sisas.repository.EspecialidadesRepository;
import com.minea.sisas.service.dto.EspecialidadesDTO;
import com.minea.sisas.service.mapper.EspecialidadesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Especialidades.
 */
@Service
@Transactional
public class EspecialidadesService {

    private final Logger log = LoggerFactory.getLogger(EspecialidadesService.class);

    private final EspecialidadesRepository especialidadesRepository;

    private final EspecialidadesMapper especialidadesMapper;

    public EspecialidadesService(EspecialidadesRepository especialidadesRepository, EspecialidadesMapper especialidadesMapper) {
        this.especialidadesRepository = especialidadesRepository;
        this.especialidadesMapper = especialidadesMapper;
    }

    /**
     * Save a especialidades.
     *
     * @param especialidadesDTO the entity to save
     * @return the persisted entity
     */
    public EspecialidadesDTO save(EspecialidadesDTO especialidadesDTO) {
        log.debug("Request to save Especialidades : {}", especialidadesDTO);
        Especialidades especialidades = especialidadesMapper.toEntity(especialidadesDTO);
        especialidades = especialidadesRepository.save(especialidades);
        return especialidadesMapper.toDto(especialidades);
    }

    /**
     * Get all the especialidadess.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<EspecialidadesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Especialidadess");
        return especialidadesRepository.findAll(pageable)
            .map(especialidadesMapper::toDto);
    }

    /**
     * Get one especialidades by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Especialidades findOne(Long id) {
        log.debug("Request to get Especialidades : {}", id);
        return especialidadesRepository.findOne(id);
    }

    /**
     * Delete the especialidades by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Especialidades : {}", id);
        especialidadesRepository.delete(id);
    }
}
