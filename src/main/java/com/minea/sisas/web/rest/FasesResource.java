package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.service.FasesService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.FasesDTO;
import com.minea.sisas.service.dto.FasesCriteria;
import com.minea.sisas.service.FasesQueryService;
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
 * REST controller for managing Fases.
 */
@RestController
@RequestMapping("/api")
public class FasesResource {

    private final Logger log = LoggerFactory.getLogger(FasesResource.class);

    private static final String ENTITY_NAME = "fases";

    private final FasesService fasesService;

    private final FasesQueryService fasesQueryService;

    public FasesResource(FasesService fasesService, FasesQueryService fasesQueryService) {
        this.fasesService = fasesService;
        this.fasesQueryService = fasesQueryService;
    }

    /**
     * POST  /fases : Create a new fases.
     *
     * @param fasesDTO the fasesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fasesDTO, or with status 400 (Bad Request) if the fases has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fases")
    @Timed
    public ResponseEntity<FasesDTO> createFases(@Valid @RequestBody FasesDTO fasesDTO) throws URISyntaxException {
        log.debug("REST request to save Fases : {}", fasesDTO);
        if (fasesDTO.getId() != null) {
            throw new BadRequestAlertException("A new fases cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FasesDTO result = fasesService.save(fasesDTO);
        return ResponseEntity.created(new URI("/api/fases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fases : Updates an existing fases.
     *
     * @param fasesDTO the fasesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fasesDTO,
     * or with status 400 (Bad Request) if the fasesDTO is not valid,
     * or with status 500 (Internal Server Error) if the fasesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fases")
    @Timed
    public ResponseEntity<FasesDTO> updateFases(@Valid @RequestBody FasesDTO fasesDTO) throws URISyntaxException {
        log.debug("REST request to update Fases : {}", fasesDTO);
        if (fasesDTO.getId() == null) {
            return createFases(fasesDTO);
        }
        FasesDTO result = fasesService.save(fasesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fasesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fases : get all the fases.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of fases in body
     */
    @GetMapping("/fases")
    @Timed
    public ResponseEntity<List<FasesDTO>> getAllFases(FasesCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Fases by criteria: {}", criteria);
        Page<FasesDTO> page = fasesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fases");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /fases/:id : get the "id" fases.
     *
     * @param id the id of the fasesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fasesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/fases/{id}")
    @Timed
    public ResponseEntity<FasesDTO> getFases(@PathVariable Long id) {
        log.debug("REST request to get Fases : {}", id);
        FasesDTO fasesDTO = fasesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fasesDTO));
    }

    /**
     * DELETE  /fases/:id : delete the "id" fases.
     *
     * @param id the id of the fasesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fases/{id}")
    @Timed
    public ResponseEntity<Void> deleteFases(@PathVariable Long id) {
        log.debug("REST request to delete Fases : {}", id);
        fasesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
