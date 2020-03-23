package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.repository.SegurancaLogRepository;
import com.minea.sisas.service.SegurancaLogService;
import com.minea.sisas.service.dto.SegurancaLogDTO;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
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
 * REST controller for managing SegurancaLog.
 */
@RestController
@RequestMapping("/api")
public class SegurancaLogResource {

    private final Logger log = LoggerFactory.getLogger(SegurancaLogResource.class);

    private static final String ENTITY_NAME = "segurancaLog";

    private final SegurancaLogService segurancaLogService;

    private final SegurancaLogRepository segurancaLogRepository;

    public SegurancaLogResource(SegurancaLogService segurancaLogService, SegurancaLogRepository segurancaLogRepository) {
        this.segurancaLogService = segurancaLogService;
        this.segurancaLogRepository = segurancaLogRepository;
    }

    /**
     * POST  /segurancas-logs : Create a new SegurancaLog.
     *
     * @param SegurancaLogDTO the SegurancaLogDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new SegurancaLogDTO, or with status 400 (Bad Request) if the SegurancaLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/segurancas-logs")
    @Timed
    public ResponseEntity<SegurancaLogDTO> createSegurancaLog(@Valid @RequestBody SegurancaLogDTO SegurancaLogDTO) throws URISyntaxException {
        log.debug("REST request to save SegurancaLog : {}", SegurancaLogDTO);
        if (SegurancaLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new SegurancaLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SegurancaLogDTO result = segurancaLogService.save(SegurancaLogDTO);
        return ResponseEntity.created(new URI("/api/segurancas-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /segurancas-logs : Updates an existing SegurancaLog.
     *
     * @param SegurancaLogDTO the SegurancaLogDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated SegurancaLogDTO,
     * or with status 400 (Bad Request) if the SegurancaLogDTO is not valid,
     * or with status 500 (Internal Server Error) if the SegurancaLogDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/segurancas-logs")
    @Timed
    public ResponseEntity<SegurancaLogDTO> updateSegurancaLog(@Valid @RequestBody SegurancaLogDTO SegurancaLogDTO) throws URISyntaxException {
        log.debug("REST request to update SegurancaLog : {}", SegurancaLogDTO);
        if (SegurancaLogDTO.getId() == null) {
            return createSegurancaLog(SegurancaLogDTO);
        }
        SegurancaLogDTO result = segurancaLogService.save(SegurancaLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, SegurancaLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /segurancas-logs : get all the SegurancaLogs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of SegurancaLogs in body
     */
    @GetMapping("/segurancas-logs")
    @Timed
    public ResponseEntity<List<SegurancaLogDTO>> getAllSegurancaLogs(Pageable pageable) {
        log.debug("REST request to get SegurancaLogs by ");
        Page<SegurancaLogDTO> page = segurancaLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/relatorios-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /relatorios-logs/:id : get the "id" SegurancaLog.
     *
     * @param id the id of the SegurancaLogDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the SegurancaLogDTO, or with status 404 (Not Found)
     */
    @GetMapping("/segurancas-logs/{id}")
    @Timed
    public ResponseEntity<SegurancaLogDTO> getSegurancaLog(@PathVariable Long id) {
        log.debug("REST request to get SegurancaLog : {}", id);
        SegurancaLogDTO SegurancaLogDTO = segurancaLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(SegurancaLogDTO));
    }

    @GetMapping("/segurancas-logs/nomeFiltro")
    public ResponseEntity<List<SegurancaLogDTO>> getByNome(@RequestParam(value = "nome") String nome, Pageable pageable) {
        Page<SegurancaLogDTO> page = segurancaLogRepository.buscarPorNome(nome, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sistema-agua-log");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
