package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.SobreDna;
import com.minea.sisas.service.SobreDnaService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.SobreDnaCriteria;
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
 * REST controller for managing SobreDna.
 */
@RestController
@RequestMapping("/api")
public class SobreDnaResource {

    private final Logger log = LoggerFactory.getLogger(SobreDnaResource.class);

    private static final String ENTITY_NAME = "sobreDna";

    private final SobreDnaService sobreDnaService;

    public SobreDnaResource(SobreDnaService sobreDnaService) {
        this.sobreDnaService = sobreDnaService;
    }

    /**
     * POST  /sobre-dnas : Create a new sobreDna.
     *
     * @param sobreDna the sobreDnaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sobreDnaDTO, or with status 400 (Bad Request) if the sobreDna has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sobre-dnas")
    @Timed
    public ResponseEntity<SobreDna> createSobreDna(@Valid @RequestBody SobreDna sobreDna) throws URISyntaxException {
        log.debug("REST request to save SobreDna : {}", sobreDna);
        if (sobreDna.getId() != null) {
            throw new BadRequestAlertException("A new sobreDna cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SobreDna result = sobreDnaService.save(sobreDna);
        return ResponseEntity.created(new URI("/api/sobre-dnas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sobre-dnas : Updates an existing sobreDna.
     *
     * @param sobreDna the sobreDnaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sobreDnaDTO,
     * or with status 400 (Bad Request) if the sobreDnaDTO is not valid,
     * or with status 500 (Internal Server Error) if the sobreDnaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sobre-dnas")
    @Timed
    public ResponseEntity<SobreDna> updateSobreDna(@Valid @RequestBody SobreDna sobreDna) throws URISyntaxException {
        log.debug("REST request to update SobreDna : {}", sobreDna);
        if (sobreDna.getId() == null) {
            return createSobreDna(sobreDna);
        }
        SobreDna result = sobreDnaService.save(sobreDna);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sobreDna.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sobre-dnas : get all the sobreDnas.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of sobreDnas in body
     */
    @GetMapping("/sobre-dnas")
    @Timed
    public ResponseEntity<List<SobreDna>> getAllSobreDnas(SobreDnaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SobreDnas by criteria: {}", criteria);
        Page<SobreDna> page = sobreDnaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sobre-dnas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /sobre-dnas/:id : get the "id" sobreDna.
     *
     * @param id the id of the sobreDnaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sobreDnaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sobre-dnas/{id}")
    @Timed
    public ResponseEntity<SobreDna> getSobreDna(@PathVariable Long id) {
        log.debug("REST request to get SobreDna : {}", id);
        SobreDna sobreDna = sobreDnaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(sobreDna));
    }

    /**
     * DELETE  /sobre-dnas/:id : delete the "id" sobreDna.
     *
     * @param id the id of the sobreDnaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sobre-dnas/{id}")
    @Timed
    public ResponseEntity<Void> deleteSobreDna(@PathVariable Long id) {
        log.debug("REST request to delete SobreDna : {}", id);
        sobreDnaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
