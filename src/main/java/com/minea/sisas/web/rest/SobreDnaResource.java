package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.service.SobreDnaService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.SobreDnaDTO;
import com.minea.sisas.service.dto.SobreDnaCriteria;
import com.minea.sisas.service.SobreDnaQueryService;
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

    private final SobreDnaQueryService sobreDnaQueryService;

    public SobreDnaResource(SobreDnaService sobreDnaService, SobreDnaQueryService sobreDnaQueryService) {
        this.sobreDnaService = sobreDnaService;
        this.sobreDnaQueryService = sobreDnaQueryService;
    }

    /**
     * POST  /sobre-dnas : Create a new sobreDna.
     *
     * @param sobreDnaDTO the sobreDnaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sobreDnaDTO, or with status 400 (Bad Request) if the sobreDna has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sobre-dnas")
    @Timed
    public ResponseEntity<SobreDnaDTO> createSobreDna(@Valid @RequestBody SobreDnaDTO sobreDnaDTO) throws URISyntaxException {
        log.debug("REST request to save SobreDna : {}", sobreDnaDTO);
        if (sobreDnaDTO.getId() != null) {
            throw new BadRequestAlertException("A new sobreDna cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SobreDnaDTO result = sobreDnaService.save(sobreDnaDTO);
        return ResponseEntity.created(new URI("/api/sobre-dnas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sobre-dnas : Updates an existing sobreDna.
     *
     * @param sobreDnaDTO the sobreDnaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sobreDnaDTO,
     * or with status 400 (Bad Request) if the sobreDnaDTO is not valid,
     * or with status 500 (Internal Server Error) if the sobreDnaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sobre-dnas")
    @Timed
    public ResponseEntity<SobreDnaDTO> updateSobreDna(@Valid @RequestBody SobreDnaDTO sobreDnaDTO) throws URISyntaxException {
        log.debug("REST request to update SobreDna : {}", sobreDnaDTO);
        if (sobreDnaDTO.getId() == null) {
            return createSobreDna(sobreDnaDTO);
        }
        SobreDnaDTO result = sobreDnaService.save(sobreDnaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sobreDnaDTO.getId().toString()))
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
    public ResponseEntity<List<SobreDnaDTO>> getAllSobreDnas(SobreDnaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SobreDnas by criteria: {}", criteria);
        Page<SobreDnaDTO> page = sobreDnaQueryService.findByCriteria(criteria, pageable);
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
    public ResponseEntity<SobreDnaDTO> getSobreDna(@PathVariable Long id) {
        log.debug("REST request to get SobreDna : {}", id);
        SobreDnaDTO sobreDnaDTO = sobreDnaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(sobreDnaDTO));
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
