package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.service.RelatoriosLogService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.RelatoriosLogDTO;
import com.minea.sisas.service.dto.RelatoriosLogCriteria;
import com.minea.sisas.service.RelatoriosLogQueryService;
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
 * REST controller for managing RelatoriosLog.
 */
@RestController
@RequestMapping("/api")
public class RelatoriosLogResource {

    private final Logger log = LoggerFactory.getLogger(RelatoriosLogResource.class);

    private static final String ENTITY_NAME = "relatoriosLog";

    private final RelatoriosLogService relatoriosLogService;

    private final RelatoriosLogQueryService relatoriosLogQueryService;

    public RelatoriosLogResource(RelatoriosLogService relatoriosLogService, RelatoriosLogQueryService relatoriosLogQueryService) {
        this.relatoriosLogService = relatoriosLogService;
        this.relatoriosLogQueryService = relatoriosLogQueryService;
    }

    /**
     * POST  /relatorios-logs : Create a new relatoriosLog.
     *
     * @param relatoriosLogDTO the relatoriosLogDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new relatoriosLogDTO, or with status 400 (Bad Request) if the relatoriosLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/relatorios-logs")
    @Timed
    public ResponseEntity<RelatoriosLogDTO> createRelatoriosLog(@Valid @RequestBody RelatoriosLogDTO relatoriosLogDTO) throws URISyntaxException {
        log.debug("REST request to save RelatoriosLog : {}", relatoriosLogDTO);
        if (relatoriosLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new relatoriosLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RelatoriosLogDTO result = relatoriosLogService.save(relatoriosLogDTO);
        return ResponseEntity.created(new URI("/api/relatorios-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /relatorios-logs : Updates an existing relatoriosLog.
     *
     * @param relatoriosLogDTO the relatoriosLogDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated relatoriosLogDTO,
     * or with status 400 (Bad Request) if the relatoriosLogDTO is not valid,
     * or with status 500 (Internal Server Error) if the relatoriosLogDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/relatorios-logs")
    @Timed
    public ResponseEntity<RelatoriosLogDTO> updateRelatoriosLog(@Valid @RequestBody RelatoriosLogDTO relatoriosLogDTO) throws URISyntaxException {
        log.debug("REST request to update RelatoriosLog : {}", relatoriosLogDTO);
        if (relatoriosLogDTO.getId() == null) {
            return createRelatoriosLog(relatoriosLogDTO);
        }
        RelatoriosLogDTO result = relatoriosLogService.save(relatoriosLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, relatoriosLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /relatorios-logs : get all the relatoriosLogs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of relatoriosLogs in body
     */
    @GetMapping("/relatorios-logs")
    @Timed
    public ResponseEntity<List<RelatoriosLogDTO>> getAllRelatoriosLogs(RelatoriosLogCriteria criteria, Pageable pageable) {
        log.debug("REST request to get RelatoriosLogs by criteria: {}", criteria);
        Page<RelatoriosLogDTO> page = relatoriosLogQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/relatorios-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /relatorios-logs/:id : get the "id" relatoriosLog.
     *
     * @param id the id of the relatoriosLogDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the relatoriosLogDTO, or with status 404 (Not Found)
     */
    @GetMapping("/relatorios-logs/{id}")
    @Timed
    public ResponseEntity<RelatoriosLogDTO> getRelatoriosLog(@PathVariable Long id) {
        log.debug("REST request to get RelatoriosLog : {}", id);
        RelatoriosLogDTO relatoriosLogDTO = relatoriosLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(relatoriosLogDTO));
    }

    /**
     * DELETE  /relatorios-logs/:id : delete the "id" relatoriosLog.
     *
     * @param id the id of the relatoriosLogDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/relatorios-logs/{id}")
    @Timed
    public ResponseEntity<Void> deleteRelatoriosLog(@PathVariable Long id) {
        log.debug("REST request to delete RelatoriosLog : {}", id);
        relatoriosLogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
