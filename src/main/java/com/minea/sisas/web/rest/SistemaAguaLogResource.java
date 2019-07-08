package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.service.SistemaAguaLogService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.SistemaAguaLogDTO;
import com.minea.sisas.service.dto.SistemaAguaLogCriteria;
import com.minea.sisas.service.SistemaAguaLogQueryService;
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
 * REST controller for managing SistemaAguaLog.
 */
@RestController
@RequestMapping("/api")
public class SistemaAguaLogResource {

    private final Logger log = LoggerFactory.getLogger(SistemaAguaLogResource.class);

    private static final String ENTITY_NAME = "sistemaAguaLog";

    private final SistemaAguaLogService sistemaAguaLogService;

    private final SistemaAguaLogQueryService sistemaAguaLogQueryService;

    public SistemaAguaLogResource(SistemaAguaLogService sistemaAguaLogService, SistemaAguaLogQueryService sistemaAguaLogQueryService) {
        this.sistemaAguaLogService = sistemaAguaLogService;
        this.sistemaAguaLogQueryService = sistemaAguaLogQueryService;
    }

    /**
     * POST  /sistema-agua-logs : Create a new sistemaAguaLog.
     *
     * @param sistemaAguaLogDTO the sistemaAguaLogDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sistemaAguaLogDTO, or with status 400 (Bad Request) if the sistemaAguaLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sistema-agua-logs")
    @Timed
    public ResponseEntity<SistemaAguaLogDTO> createSistemaAguaLog(@Valid @RequestBody SistemaAguaLogDTO sistemaAguaLogDTO) throws URISyntaxException {
        log.debug("REST request to save SistemaAguaLog : {}", sistemaAguaLogDTO);
        if (sistemaAguaLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new sistemaAguaLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SistemaAguaLogDTO result = sistemaAguaLogService.save(sistemaAguaLogDTO);
        return ResponseEntity.created(new URI("/api/sistema-agua-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sistema-agua-logs : Updates an existing sistemaAguaLog.
     *
     * @param sistemaAguaLogDTO the sistemaAguaLogDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sistemaAguaLogDTO,
     * or with status 400 (Bad Request) if the sistemaAguaLogDTO is not valid,
     * or with status 500 (Internal Server Error) if the sistemaAguaLogDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sistema-agua-logs")
    @Timed
    public ResponseEntity<SistemaAguaLogDTO> updateSistemaAguaLog(@Valid @RequestBody SistemaAguaLogDTO sistemaAguaLogDTO) throws URISyntaxException {
        log.debug("REST request to update SistemaAguaLog : {}", sistemaAguaLogDTO);
        if (sistemaAguaLogDTO.getId() == null) {
            return createSistemaAguaLog(sistemaAguaLogDTO);
        }
        SistemaAguaLogDTO result = sistemaAguaLogService.save(sistemaAguaLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sistemaAguaLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sistema-agua-logs : get all the sistemaAguaLogs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of sistemaAguaLogs in body
     */
    @GetMapping("/sistema-agua-logs")
    @Timed
    public ResponseEntity<List<SistemaAguaLogDTO>> getAllSistemaAguaLogs(SistemaAguaLogCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SistemaAguaLogs by criteria: {}", criteria);
        Page<SistemaAguaLogDTO> page = sistemaAguaLogQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sistema-agua-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /sistema-agua-logs/:id : get the "id" sistemaAguaLog.
     *
     * @param id the id of the sistemaAguaLogDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sistemaAguaLogDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sistema-agua-logs/{id}")
    @Timed
    public ResponseEntity<SistemaAguaLogDTO> getSistemaAguaLog(@PathVariable Long id) {
        log.debug("REST request to get SistemaAguaLog : {}", id);
        SistemaAguaLogDTO sistemaAguaLogDTO = sistemaAguaLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(sistemaAguaLogDTO));
    }

    /**
     * DELETE  /sistema-agua-logs/:id : delete the "id" sistemaAguaLog.
     *
     * @param id the id of the sistemaAguaLogDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sistema-agua-logs/{id}")
    @Timed
    public ResponseEntity<Void> deleteSistemaAguaLog(@PathVariable Long id) {
        log.debug("REST request to delete SistemaAguaLog : {}", id);
        sistemaAguaLogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
