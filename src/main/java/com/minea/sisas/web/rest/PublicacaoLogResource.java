package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.service.PublicacaoLogService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.PublicacaoLogDTO;
import com.minea.sisas.service.dto.PublicacaoLogCriteria;
import com.minea.sisas.service.PublicacaoLogQueryService;
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
 * REST controller for managing PublicacaoLog.
 */
@RestController
@RequestMapping("/api")
public class PublicacaoLogResource {

    private final Logger log = LoggerFactory.getLogger(PublicacaoLogResource.class);

    private static final String ENTITY_NAME = "publicacaoLog";

    private final PublicacaoLogService publicacaoLogService;

    private final PublicacaoLogQueryService publicacaoLogQueryService;

    public PublicacaoLogResource(PublicacaoLogService publicacaoLogService, PublicacaoLogQueryService publicacaoLogQueryService) {
        this.publicacaoLogService = publicacaoLogService;
        this.publicacaoLogQueryService = publicacaoLogQueryService;
    }

    /**
     * POST  /publicacao-logs : Create a new publicacaoLog.
     *
     * @param publicacaoLogDTO the publicacaoLogDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new publicacaoLogDTO, or with status 400 (Bad Request) if the publicacaoLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/publicacao-logs")
    @Timed
    public ResponseEntity<PublicacaoLogDTO> createPublicacaoLog(@Valid @RequestBody PublicacaoLogDTO publicacaoLogDTO) throws URISyntaxException {
        log.debug("REST request to save PublicacaoLog : {}", publicacaoLogDTO);
        if (publicacaoLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new publicacaoLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PublicacaoLogDTO result = publicacaoLogService.save(publicacaoLogDTO);
        return ResponseEntity.created(new URI("/api/publicacao-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /publicacao-logs : Updates an existing publicacaoLog.
     *
     * @param publicacaoLogDTO the publicacaoLogDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated publicacaoLogDTO,
     * or with status 400 (Bad Request) if the publicacaoLogDTO is not valid,
     * or with status 500 (Internal Server Error) if the publicacaoLogDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/publicacao-logs")
    @Timed
    public ResponseEntity<PublicacaoLogDTO> updatePublicacaoLog(@Valid @RequestBody PublicacaoLogDTO publicacaoLogDTO) throws URISyntaxException {
        log.debug("REST request to update PublicacaoLog : {}", publicacaoLogDTO);
        if (publicacaoLogDTO.getId() == null) {
            return createPublicacaoLog(publicacaoLogDTO);
        }
        PublicacaoLogDTO result = publicacaoLogService.save(publicacaoLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, publicacaoLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /publicacao-logs : get all the publicacaoLogs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of publicacaoLogs in body
     */
    @GetMapping("/publicacao-logs")
    @Timed
    public ResponseEntity<List<PublicacaoLogDTO>> getAllPublicacaoLogs(PublicacaoLogCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PublicacaoLogs by criteria: {}", criteria);
        Page<PublicacaoLogDTO> page = publicacaoLogQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/publicacao-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /publicacao-logs/:id : get the "id" publicacaoLog.
     *
     * @param id the id of the publicacaoLogDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the publicacaoLogDTO, or with status 404 (Not Found)
     */
    @GetMapping("/publicacao-logs/{id}")
    @Timed
    public ResponseEntity<PublicacaoLogDTO> getPublicacaoLog(@PathVariable Long id) {
        log.debug("REST request to get PublicacaoLog : {}", id);
        PublicacaoLogDTO publicacaoLogDTO = publicacaoLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(publicacaoLogDTO));
    }

    /**
     * DELETE  /publicacao-logs/:id : delete the "id" publicacaoLog.
     *
     * @param id the id of the publicacaoLogDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/publicacao-logs/{id}")
    @Timed
    public ResponseEntity<Void> deletePublicacaoLog(@PathVariable Long id) {
        log.debug("REST request to delete PublicacaoLog : {}", id);
        publicacaoLogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
