package com.minea.sisas.service;

import com.minea.sisas.domain.Contactos;
import com.minea.sisas.repository.ContactosRepository;
import com.minea.sisas.service.dto.ContactosDTO;
import com.minea.sisas.service.mapper.ContactosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Contactos.
 */
@Service
@Transactional
public class ContactosService {

    private final Logger log = LoggerFactory.getLogger(ContactosService.class);

    private final ContactosRepository contactosRepository;

    private final ContactosMapper contactosMapper;

    public ContactosService(ContactosRepository contactosRepository, ContactosMapper contactosMapper) {
        this.contactosRepository = contactosRepository;
        this.contactosMapper = contactosMapper;
    }

    /**
     * Save a contactos.
     *
     * @param contactosDTO the entity to save
     * @return the persisted entity
     */
    public ContactosDTO save(ContactosDTO contactosDTO) {
        log.debug("Request to save Contactos : {}", contactosDTO);
        Contactos contactos = contactosMapper.toEntity(contactosDTO);
        contactos = contactosRepository.save(contactos);
        return contactosMapper.toDto(contactos);
    }

    /**
     * Get all the contactos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ContactosDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Contactos");
        return contactosRepository.findAll(pageable)
            .map(contactosMapper::toDto);
    }

    /**
     * Get one contactos by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public ContactosDTO findOne(Long id) {
        log.debug("Request to get Contactos : {}", id);
        Contactos contactos = contactosRepository.findOne(id);
        return contactosMapper.toDto(contactos);
    }

    /**
     * Delete the contactos by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Contactos : {}", id);
        contactosRepository.delete(id);
    }
}
