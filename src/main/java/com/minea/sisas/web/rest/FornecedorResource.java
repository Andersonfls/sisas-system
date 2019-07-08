package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.service.FornecedorService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.FornecedorDTO;
import com.minea.sisas.service.dto.FornecedorCriteria;
import com.minea.sisas.service.FornecedorQueryService;
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
 * REST controller for managing Fornecedor.
 */
@RestController
@RequestMapping("/api")
public class FornecedorResource {

    private final Logger log = LoggerFactory.getLogger(FornecedorResource.class);

    private static final String ENTITY_NAME = "fornecedor";

    private final FornecedorService fornecedorService;

    private final FornecedorQueryService fornecedorQueryService;

    public FornecedorResource(FornecedorService fornecedorService, FornecedorQueryService fornecedorQueryService) {
        this.fornecedorService = fornecedorService;
        this.fornecedorQueryService = fornecedorQueryService;
    }

    /**
     * POST  /fornecedors : Create a new fornecedor.
     *
     * @param fornecedorDTO the fornecedorDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fornecedorDTO, or with status 400 (Bad Request) if the fornecedor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fornecedors")
    @Timed
    public ResponseEntity<FornecedorDTO> createFornecedor(@Valid @RequestBody FornecedorDTO fornecedorDTO) throws URISyntaxException {
        log.debug("REST request to save Fornecedor : {}", fornecedorDTO);
        if (fornecedorDTO.getId() != null) {
            throw new BadRequestAlertException("A new fornecedor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FornecedorDTO result = fornecedorService.save(fornecedorDTO);
        return ResponseEntity.created(new URI("/api/fornecedors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fornecedors : Updates an existing fornecedor.
     *
     * @param fornecedorDTO the fornecedorDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fornecedorDTO,
     * or with status 400 (Bad Request) if the fornecedorDTO is not valid,
     * or with status 500 (Internal Server Error) if the fornecedorDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fornecedors")
    @Timed
    public ResponseEntity<FornecedorDTO> updateFornecedor(@Valid @RequestBody FornecedorDTO fornecedorDTO) throws URISyntaxException {
        log.debug("REST request to update Fornecedor : {}", fornecedorDTO);
        if (fornecedorDTO.getId() == null) {
            return createFornecedor(fornecedorDTO);
        }
        FornecedorDTO result = fornecedorService.save(fornecedorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fornecedorDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fornecedors : get all the fornecedors.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of fornecedors in body
     */
    @GetMapping("/fornecedors")
    @Timed
    public ResponseEntity<List<FornecedorDTO>> getAllFornecedors(FornecedorCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Fornecedors by criteria: {}", criteria);
        Page<FornecedorDTO> page = fornecedorQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fornecedors");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /fornecedors/:id : get the "id" fornecedor.
     *
     * @param id the id of the fornecedorDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fornecedorDTO, or with status 404 (Not Found)
     */
    @GetMapping("/fornecedors/{id}")
    @Timed
    public ResponseEntity<FornecedorDTO> getFornecedor(@PathVariable Long id) {
        log.debug("REST request to get Fornecedor : {}", id);
        FornecedorDTO fornecedorDTO = fornecedorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fornecedorDTO));
    }

    /**
     * DELETE  /fornecedors/:id : delete the "id" fornecedor.
     *
     * @param id the id of the fornecedorDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fornecedors/{id}")
    @Timed
    public ResponseEntity<Void> deleteFornecedor(@PathVariable Long id) {
        log.debug("REST request to delete Fornecedor : {}", id);
        fornecedorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
