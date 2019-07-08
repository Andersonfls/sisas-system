package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.service.EntidadeGestoraService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.EntidadeGestoraDTO;
import com.minea.sisas.service.dto.EntidadeGestoraCriteria;
import com.minea.sisas.service.EntidadeGestoraQueryService;
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
 * REST controller for managing EntidadeGestora.
 */
@RestController
@RequestMapping("/api")
public class EntidadeGestoraResource {

    private final Logger log = LoggerFactory.getLogger(EntidadeGestoraResource.class);

    private static final String ENTITY_NAME = "entidadeGestora";

    private final EntidadeGestoraService entidadeGestoraService;

    private final EntidadeGestoraQueryService entidadeGestoraQueryService;

    public EntidadeGestoraResource(EntidadeGestoraService entidadeGestoraService, EntidadeGestoraQueryService entidadeGestoraQueryService) {
        this.entidadeGestoraService = entidadeGestoraService;
        this.entidadeGestoraQueryService = entidadeGestoraQueryService;
    }

    /**
     * POST  /entidade-gestoras : Create a new entidadeGestora.
     *
     * @param entidadeGestoraDTO the entidadeGestoraDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new entidadeGestoraDTO, or with status 400 (Bad Request) if the entidadeGestora has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/entidade-gestoras")
    @Timed
    public ResponseEntity<EntidadeGestoraDTO> createEntidadeGestora(@Valid @RequestBody EntidadeGestoraDTO entidadeGestoraDTO) throws URISyntaxException {
        log.debug("REST request to save EntidadeGestora : {}", entidadeGestoraDTO);
        if (entidadeGestoraDTO.getId() != null) {
            throw new BadRequestAlertException("A new entidadeGestora cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EntidadeGestoraDTO result = entidadeGestoraService.save(entidadeGestoraDTO);
        return ResponseEntity.created(new URI("/api/entidade-gestoras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /entidade-gestoras : Updates an existing entidadeGestora.
     *
     * @param entidadeGestoraDTO the entidadeGestoraDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated entidadeGestoraDTO,
     * or with status 400 (Bad Request) if the entidadeGestoraDTO is not valid,
     * or with status 500 (Internal Server Error) if the entidadeGestoraDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/entidade-gestoras")
    @Timed
    public ResponseEntity<EntidadeGestoraDTO> updateEntidadeGestora(@Valid @RequestBody EntidadeGestoraDTO entidadeGestoraDTO) throws URISyntaxException {
        log.debug("REST request to update EntidadeGestora : {}", entidadeGestoraDTO);
        if (entidadeGestoraDTO.getId() == null) {
            return createEntidadeGestora(entidadeGestoraDTO);
        }
        EntidadeGestoraDTO result = entidadeGestoraService.save(entidadeGestoraDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, entidadeGestoraDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /entidade-gestoras : get all the entidadeGestoras.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of entidadeGestoras in body
     */
    @GetMapping("/entidade-gestoras")
    @Timed
    public ResponseEntity<List<EntidadeGestoraDTO>> getAllEntidadeGestoras(EntidadeGestoraCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EntidadeGestoras by criteria: {}", criteria);
        Page<EntidadeGestoraDTO> page = entidadeGestoraQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/entidade-gestoras");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /entidade-gestoras/:id : get the "id" entidadeGestora.
     *
     * @param id the id of the entidadeGestoraDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the entidadeGestoraDTO, or with status 404 (Not Found)
     */
    @GetMapping("/entidade-gestoras/{id}")
    @Timed
    public ResponseEntity<EntidadeGestoraDTO> getEntidadeGestora(@PathVariable Long id) {
        log.debug("REST request to get EntidadeGestora : {}", id);
        EntidadeGestoraDTO entidadeGestoraDTO = entidadeGestoraService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(entidadeGestoraDTO));
    }

    /**
     * DELETE  /entidade-gestoras/:id : delete the "id" entidadeGestora.
     *
     * @param id the id of the entidadeGestoraDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/entidade-gestoras/{id}")
    @Timed
    public ResponseEntity<Void> deleteEntidadeGestora(@PathVariable Long id) {
        log.debug("REST request to delete EntidadeGestora : {}", id);
        entidadeGestoraService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
