package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.service.ContratoService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.ContratoDTO;
import com.minea.sisas.service.dto.ContratoCriteria;
import com.minea.sisas.service.ContratoQueryService;
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
 * REST controller for managing Contrato.
 */
@RestController
@RequestMapping("/api")
public class ContratoResource {

    private final Logger log = LoggerFactory.getLogger(ContratoResource.class);

    private static final String ENTITY_NAME = "contrato";

    private final ContratoService contratoService;

    private final ContratoQueryService contratoQueryService;

    public ContratoResource(ContratoService contratoService, ContratoQueryService contratoQueryService) {
        this.contratoService = contratoService;
        this.contratoQueryService = contratoQueryService;
    }

    /**
     * POST  /contratoes : Create a new contrato.
     *
     * @param contratoDTO the contratoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new contratoDTO, or with status 400 (Bad Request) if the contrato has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/contratoes")
    @Timed
    public ResponseEntity<ContratoDTO> createContrato(@Valid @RequestBody ContratoDTO contratoDTO) throws URISyntaxException {
        log.debug("REST request to save Contrato : {}", contratoDTO);
        if (contratoDTO.getId() != null) {
            throw new BadRequestAlertException("A new contrato cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContratoDTO result = contratoService.save(contratoDTO);
        return ResponseEntity.created(new URI("/api/contratoes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /contratoes : Updates an existing contrato.
     *
     * @param contratoDTO the contratoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated contratoDTO,
     * or with status 400 (Bad Request) if the contratoDTO is not valid,
     * or with status 500 (Internal Server Error) if the contratoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/contratoes")
    @Timed
    public ResponseEntity<ContratoDTO> updateContrato(@Valid @RequestBody ContratoDTO contratoDTO) throws URISyntaxException {
        log.debug("REST request to update Contrato : {}", contratoDTO);
        if (contratoDTO.getId() == null) {
            return createContrato(contratoDTO);
        }
        ContratoDTO result = contratoService.save(contratoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, contratoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /contratoes : get all the contratoes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of contratoes in body
     */
    @GetMapping("/contratoes")
    @Timed
    public ResponseEntity<List<ContratoDTO>> getAllContratoes(ContratoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Contratoes by criteria: {}", criteria);
        Page<ContratoDTO> page = contratoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/contratoes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /contratoes/:id : get the "id" contrato.
     *
     * @param id the id of the contratoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the contratoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/contratoes/{id}")
    @Timed
    public ResponseEntity<ContratoDTO> getContrato(@PathVariable Long id) {
        log.debug("REST request to get Contrato : {}", id);
        ContratoDTO contratoDTO = contratoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(contratoDTO));
    }

    /**
     * DELETE  /contratoes/:id : delete the "id" contrato.
     *
     * @param id the id of the contratoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/contratoes/{id}")
    @Timed
    public ResponseEntity<Void> deleteContrato(@PathVariable Long id) {
        log.debug("REST request to delete Contrato : {}", id);
        contratoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
