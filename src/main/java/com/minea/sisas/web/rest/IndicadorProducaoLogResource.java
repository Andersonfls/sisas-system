package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.repository.IndicadorProducaoLogRepository;
import com.minea.sisas.service.IndicadorProducaoLogService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.IndicadorProducaoLogDTO;
import com.minea.sisas.service.dto.IndicadorProducaoLogCriteria;
import com.minea.sisas.service.IndicadorProducaoLogQueryService;
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
 * REST controller for managing IndicadorProducaoLog.
 */
@RestController
@RequestMapping("/api")
public class IndicadorProducaoLogResource {

    private final Logger log = LoggerFactory.getLogger(IndicadorProducaoLogResource.class);

    private static final String ENTITY_NAME = "indicadorProducaoLog";

    private final IndicadorProducaoLogService indicadorProducaoLogService;

    private final IndicadorProducaoLogQueryService indicadorProducaoLogQueryService;

    private final IndicadorProducaoLogRepository indicadorProducaoLogRepository;

    public IndicadorProducaoLogResource(IndicadorProducaoLogService indicadorProducaoLogService, IndicadorProducaoLogQueryService indicadorProducaoLogQueryService, IndicadorProducaoLogRepository indicadorProducaoLogRepository) {
        this.indicadorProducaoLogService = indicadorProducaoLogService;
        this.indicadorProducaoLogQueryService = indicadorProducaoLogQueryService;
        this.indicadorProducaoLogRepository=indicadorProducaoLogRepository;
    }

    /**
     * POST  /indicador-producao-logs : Create a new indicadorProducaoLog.
     *
     * @param indicadorProducaoLogDTO the indicadorProducaoLogDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new indicadorProducaoLogDTO, or with status 400 (Bad Request) if the indicadorProducaoLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/indicador-producao-logs")
    @Timed
    public ResponseEntity<IndicadorProducaoLogDTO> createIndicadorProducaoLog(@Valid @RequestBody IndicadorProducaoLogDTO indicadorProducaoLogDTO) throws URISyntaxException {
        log.debug("REST request to save IndicadorProducaoLog : {}", indicadorProducaoLogDTO);
        if (indicadorProducaoLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new indicadorProducaoLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IndicadorProducaoLogDTO result = indicadorProducaoLogService.save(indicadorProducaoLogDTO);
        return ResponseEntity.created(new URI("/api/indicador-producao-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /indicador-producao-logs : Updates an existing indicadorProducaoLog.
     *
     * @param indicadorProducaoLogDTO the indicadorProducaoLogDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated indicadorProducaoLogDTO,
     * or with status 400 (Bad Request) if the indicadorProducaoLogDTO is not valid,
     * or with status 500 (Internal Server Error) if the indicadorProducaoLogDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/indicador-producao-logs")
    @Timed
    public ResponseEntity<IndicadorProducaoLogDTO> updateIndicadorProducaoLog(@Valid @RequestBody IndicadorProducaoLogDTO indicadorProducaoLogDTO) throws URISyntaxException {
        log.debug("REST request to update IndicadorProducaoLog : {}", indicadorProducaoLogDTO);
        if (indicadorProducaoLogDTO.getId() == null) {
            return createIndicadorProducaoLog(indicadorProducaoLogDTO);
        }
        IndicadorProducaoLogDTO result = indicadorProducaoLogService.save(indicadorProducaoLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, indicadorProducaoLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /indicador-producao-logs : get all the indicadorProducaoLogs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of indicadorProducaoLogs in body
     */
    @GetMapping("/indicador-producao-logs")
    @Timed
    public ResponseEntity<List<IndicadorProducaoLogDTO>> getAllIndicadorProducaoLogs(IndicadorProducaoLogCriteria criteria, Pageable pageable) {
        log.debug("REST request to get IndicadorProducaoLogs by criteria: {}", criteria);
        Page<IndicadorProducaoLogDTO> page = indicadorProducaoLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/indicador-producao-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /*
     *  Filtro de usuários atráves do atributo nome
     */
    @GetMapping("/indicador-producao-logs/nomeFiltro")
    public ResponseEntity<List<IndicadorProducaoLogDTO>> getByNome(@RequestParam(value = "nome") String nome, Pageable pageable) {
        Page<IndicadorProducaoLogDTO> page = indicadorProducaoLogRepository.buscarPorNome(nome, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/indicador-producao-log");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /indicador-producao-logs/:id : get the "id" indicadorProducaoLog.
     *
     * @param id the id of the indicadorProducaoLogDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the indicadorProducaoLogDTO, or with status 404 (Not Found)
     */
    @GetMapping("/indicador-producao-logs/{id}")
    @Timed
    public ResponseEntity<IndicadorProducaoLogDTO> getIndicadorProducaoLog(@PathVariable Long id) {
        log.debug("REST request to get IndicadorProducaoLog : {}", id);
        IndicadorProducaoLogDTO indicadorProducaoLogDTO = indicadorProducaoLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(indicadorProducaoLogDTO));
    }

    /**
     * DELETE  /indicador-producao-logs/:id : delete the "id" indicadorProducaoLog.
     *
     * @param id the id of the indicadorProducaoLogDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/indicador-producao-logs/{id}")
    @Timed
    public ResponseEntity<Void> deleteIndicadorProducaoLog(@PathVariable Long id) {
        log.debug("REST request to delete IndicadorProducaoLog : {}", id);
        indicadorProducaoLogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
