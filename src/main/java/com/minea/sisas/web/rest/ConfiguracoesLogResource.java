package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.service.ConfiguracoesLogService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.ConfiguracoesLogDTO;
import com.minea.sisas.service.dto.ConfiguracoesLogCriteria;
import com.minea.sisas.service.ConfiguracoesLogQueryService;
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
 * REST controller for managing ConfiguracoesLog.
 */
@RestController
@RequestMapping("/api")
public class ConfiguracoesLogResource {

    private final Logger log = LoggerFactory.getLogger(ConfiguracoesLogResource.class);

    private static final String ENTITY_NAME = "configuracoesLog";

    private final ConfiguracoesLogService configuracoesLogService;

    private final ConfiguracoesLogQueryService configuracoesLogQueryService;

    public ConfiguracoesLogResource(ConfiguracoesLogService configuracoesLogService, ConfiguracoesLogQueryService configuracoesLogQueryService) {
        this.configuracoesLogService = configuracoesLogService;
        this.configuracoesLogQueryService = configuracoesLogQueryService;
    }

    /**
     * POST  /configuracoes-logs : Create a new configuracoesLog.
     *
     * @param configuracoesLogDTO the configuracoesLogDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new configuracoesLogDTO, or with status 400 (Bad Request) if the configuracoesLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/configuracoes-logs")
    @Timed
    public ResponseEntity<ConfiguracoesLogDTO> createConfiguracoesLog(@Valid @RequestBody ConfiguracoesLogDTO configuracoesLogDTO) throws URISyntaxException {
        log.debug("REST request to save ConfiguracoesLog : {}", configuracoesLogDTO);
        if (configuracoesLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new configuracoesLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConfiguracoesLogDTO result = configuracoesLogService.save(configuracoesLogDTO);
        return ResponseEntity.created(new URI("/api/configuracoes-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /configuracoes-logs : Updates an existing configuracoesLog.
     *
     * @param configuracoesLogDTO the configuracoesLogDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated configuracoesLogDTO,
     * or with status 400 (Bad Request) if the configuracoesLogDTO is not valid,
     * or with status 500 (Internal Server Error) if the configuracoesLogDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/configuracoes-logs")
    @Timed
    public ResponseEntity<ConfiguracoesLogDTO> updateConfiguracoesLog(@Valid @RequestBody ConfiguracoesLogDTO configuracoesLogDTO) throws URISyntaxException {
        log.debug("REST request to update ConfiguracoesLog : {}", configuracoesLogDTO);
        if (configuracoesLogDTO.getId() == null) {
            return createConfiguracoesLog(configuracoesLogDTO);
        }
        ConfiguracoesLogDTO result = configuracoesLogService.save(configuracoesLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, configuracoesLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /configuracoes-logs : get all the configuracoesLogs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of configuracoesLogs in body
     */
    @GetMapping("/configuracoes-logs")
    @Timed
    public ResponseEntity<List<ConfiguracoesLogDTO>> getAllConfiguracoesLogs(ConfiguracoesLogCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ConfiguracoesLogs by criteria: {}", criteria);
        Page<ConfiguracoesLogDTO> page = configuracoesLogQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/configuracoes-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /configuracoes-logs/:id : get the "id" configuracoesLog.
     *
     * @param id the id of the configuracoesLogDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the configuracoesLogDTO, or with status 404 (Not Found)
     */
    @GetMapping("/configuracoes-logs/{id}")
    @Timed
    public ResponseEntity<ConfiguracoesLogDTO> getConfiguracoesLog(@PathVariable Long id) {
        log.debug("REST request to get ConfiguracoesLog : {}", id);
        ConfiguracoesLogDTO configuracoesLogDTO = configuracoesLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(configuracoesLogDTO));
    }

    /**
     * DELETE  /configuracoes-logs/:id : delete the "id" configuracoesLog.
     *
     * @param id the id of the configuracoesLogDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/configuracoes-logs/{id}")
    @Timed
    public ResponseEntity<Void> deleteConfiguracoesLog(@PathVariable Long id) {
        log.debug("REST request to delete ConfiguracoesLog : {}", id);
        configuracoesLogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
