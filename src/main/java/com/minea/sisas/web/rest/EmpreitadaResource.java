package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.service.EmpreitadaService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.EmpreitadaDTO;
import com.minea.sisas.service.dto.EmpreitadaCriteria;
import com.minea.sisas.service.EmpreitadaQueryService;
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
 * REST controller for managing Empreitada.
 */
@RestController
@RequestMapping("/api")
public class EmpreitadaResource {

    private final Logger log = LoggerFactory.getLogger(EmpreitadaResource.class);

    private static final String ENTITY_NAME = "empreitada";

    private final EmpreitadaService empreitadaService;

    private final EmpreitadaQueryService empreitadaQueryService;

    public EmpreitadaResource(EmpreitadaService empreitadaService, EmpreitadaQueryService empreitadaQueryService) {
        this.empreitadaService = empreitadaService;
        this.empreitadaQueryService = empreitadaQueryService;
    }

    /**
     * POST  /empreitadas : Create a new empreitada.
     *
     * @param empreitadaDTO the empreitadaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new empreitadaDTO, or with status 400 (Bad Request) if the empreitada has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/empreitadas")
    @Timed
    public ResponseEntity<EmpreitadaDTO> createEmpreitada(@Valid @RequestBody EmpreitadaDTO empreitadaDTO) throws URISyntaxException {
        log.debug("REST request to save Empreitada : {}", empreitadaDTO);
        if (empreitadaDTO.getId() != null) {
            throw new BadRequestAlertException("A new empreitada cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmpreitadaDTO result = empreitadaService.save(empreitadaDTO);
        return ResponseEntity.created(new URI("/api/empreitadas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /empreitadas : Updates an existing empreitada.
     *
     * @param empreitadaDTO the empreitadaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated empreitadaDTO,
     * or with status 400 (Bad Request) if the empreitadaDTO is not valid,
     * or with status 500 (Internal Server Error) if the empreitadaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/empreitadas")
    @Timed
    public ResponseEntity<EmpreitadaDTO> updateEmpreitada(@Valid @RequestBody EmpreitadaDTO empreitadaDTO) throws URISyntaxException {
        log.debug("REST request to update Empreitada : {}", empreitadaDTO);
        if (empreitadaDTO.getId() == null) {
            return createEmpreitada(empreitadaDTO);
        }
        EmpreitadaDTO result = empreitadaService.save(empreitadaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, empreitadaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /empreitadas : get all the empreitadas.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of empreitadas in body
     */
    @GetMapping("/empreitadas")
    @Timed
    public ResponseEntity<List<EmpreitadaDTO>> getAllEmpreitadas(EmpreitadaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Empreitadas by criteria: {}", criteria);
        Page<EmpreitadaDTO> page = empreitadaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/empreitadas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /empreitadas/:id : get the "id" empreitada.
     *
     * @param id the id of the empreitadaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the empreitadaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/empreitadas/{id}")
    @Timed
    public ResponseEntity<EmpreitadaDTO> getEmpreitada(@PathVariable Long id) {
        log.debug("REST request to get Empreitada : {}", id);
        EmpreitadaDTO empreitadaDTO = empreitadaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(empreitadaDTO));
    }

    /**
     * DELETE  /empreitadas/:id : delete the "id" empreitada.
     *
     * @param id the id of the empreitadaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/empreitadas/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmpreitada(@PathVariable Long id) {
        log.debug("REST request to delete Empreitada : {}", id);
        empreitadaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
