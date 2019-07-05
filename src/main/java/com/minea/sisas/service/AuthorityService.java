package com.minea.sisas.service;

import com.minea.sisas.domain.Authority;
import com.minea.sisas.repository.AuthorityRepository;
import com.minea.sisas.repository.UserRepository;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Authority.
 */
@Service
@Transactional
public class AuthorityService {

    private final Logger log = LoggerFactory.getLogger(AuthorityService.class);

    private final AuthorityRepository authorityRepository;

    private final UserRepository userRepository;

    public AuthorityService(AuthorityRepository authorityRepository, UserRepository userRepository) {
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
    }

    /**
     * Save a authority.
     *
     * @param authority the entity to save
     * @return the persisted entity
     */
    public Authority save(Authority authority) {
        log.debug("Request to save Authority : {}", authority);
        if (authority.getOldName() != null && !authority.getName().equals(authority.getOldName())) {
            if (userRepository.countByAuthorities_Name(authority.getOldName()) > 0) {
                throw new BadRequestAlertException("Perfil já está vinculado a um ou mais usuários.", "authority", "perfil_vinculado");
            }
            Authority oldAuthority = new Authority();
            oldAuthority.setName(authority.getOldName());
            authorityRepository.delete(oldAuthority);
        }
        Authority novo = authorityRepository.save(authority);
        novo.setOldName(novo.getName());
        return novo;
    }

    /**
     * Get all the authorities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Authority> findAll(Pageable pageable) {
        log.debug("Request to get all Authorities");
        return authorityRepository.findAll(pageable);
    }

    /**
     * Get one authority by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Authority findOne(String id) {
        log.debug("Request to get Authority : {}", id);
        return authorityRepository.findOne(id);
    }

    /**
     * Delete the authority by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Authority : {}", id);
        authorityRepository.delete(id);
    }
}
