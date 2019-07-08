package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.service.ContactosService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.ContactosDTO;
import com.minea.sisas.service.dto.ContactosCriteria;
import com.minea.sisas.service.ContactosQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Contactos.
 */
@RestController
@RequestMapping("/api")
public class ContactosResource {

    private final Logger log = LoggerFactory.getLogger(ContactosResource.class);

    private static final String ENTITY_NAME = "contactos";

    private final ContactosService contactosService;

    private final ContactosQueryService contactosQueryService;

    public ContactosResource(ContactosService contactosService, ContactosQueryService contactosQueryService) {
        this.contactosService = contactosService;
        this.contactosQueryService = contactosQueryService;
    }

    /**
     * POST  /contactos : Create a new contactos.
     *
     * @param contactosDTO the contactosDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new contactosDTO, or with status 400 (Bad Request) if the contactos has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/contactos")
    @Timed
    public ResponseEntity<ContactosDTO> createContactos(@Valid @RequestBody ContactosDTO contactosDTO) throws URISyntaxException {
        log.debug("REST request to save Contactos : {}", contactosDTO);
        if (contactosDTO.getId() != null) {
            throw new BadRequestAlertException("A new contactos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContactosDTO result = contactosService.save(contactosDTO);
        return ResponseEntity.created(new URI("/api/contactos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /contactos : Updates an existing contactos.
     *
     * @param contactosDTO the contactosDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated contactosDTO,
     * or with status 400 (Bad Request) if the contactosDTO is not valid,
     * or with status 500 (Internal Server Error) if the contactosDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/contactos")
    @Timed
    public ResponseEntity<ContactosDTO> updateContactos(@Valid @RequestBody ContactosDTO contactosDTO) throws URISyntaxException {
        log.debug("REST request to update Contactos : {}", contactosDTO);
        if (contactosDTO.getId() == null) {
            return createContactos(contactosDTO);
        }
        ContactosDTO result = contactosService.save(contactosDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, contactosDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /contactos : get all the contactos.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of contactos in body
     */
    @GetMapping("/contactos")
    @Timed
    public ResponseEntity<List<ContactosDTO>> getAllContactos(ContactosCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Contactos by criteria: {}", criteria);
        Page<ContactosDTO> page = contactosQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/contactos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /contactos/:id : get the "id" contactos.
     *
     * @param id the id of the contactosDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the contactosDTO, or with status 404 (Not Found)
     */
    @GetMapping("/contactos/{id}")
    @Timed
    public ResponseEntity<ContactosDTO> getContactos(@PathVariable Long id) {
        log.debug("REST request to get Contactos : {}", id);
        ContactosDTO contactosDTO = contactosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(contactosDTO));
    }

    /**
     * DELETE  /contactos/:id : delete the "id" contactos.
     *
     * @param id the id of the contactosDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/contactos/{id}")
    @Timed
    public ResponseEntity<Void> deleteContactos(@PathVariable Long id) {
        log.debug("REST request to delete Contactos : {}", id);
        contactosService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
