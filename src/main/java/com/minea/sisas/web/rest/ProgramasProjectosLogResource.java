package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.ProgramasProjectosLog;
import com.minea.sisas.repository.ProgramasProjectosLogRepository;
import com.minea.sisas.repository.ProgramasProjectosRepository;
import com.minea.sisas.service.ProgramasProjectosLogService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.ProgramasProjectosLogDTO;
import com.minea.sisas.service.dto.ProgramasProjectosLogCriteria;
import com.minea.sisas.service.ProgramasProjectosLogQueryService;
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
 * REST controller for managing ProgramasProjectosLog.
 */
@RestController
@RequestMapping("/api")
public class ProgramasProjectosLogResource {

    private final Logger log = LoggerFactory.getLogger(ProgramasProjectosLogResource.class);

    private static final String ENTITY_NAME = "programasProjectosLog";

    private final ProgramasProjectosLogService programasProjectosLogService;

    private final ProgramasProjectosLogQueryService programasProjectosLogQueryService;

    private final ProgramasProjectosLogRepository programasProjectosLogRepository;

    public ProgramasProjectosLogResource(ProgramasProjectosLogService programasProjectosLogService, ProgramasProjectosLogQueryService programasProjectosLogQueryService, ProgramasProjectosLogRepository programasProjectosLogRepository) {
        this.programasProjectosLogService = programasProjectosLogService;
        this.programasProjectosLogQueryService = programasProjectosLogQueryService;
        this.programasProjectosLogRepository=programasProjectosLogRepository;
    }

    /**
     * POST  /programas-projectos-logs : Create a new programasProjectosLog.
     *
     * @param programasProjectosLogDTO the programasProjectosLogDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new programasProjectosLogDTO, or with status 400 (Bad Request) if the programasProjectosLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/programas-projectos-logs")
    @Timed
    public ResponseEntity<ProgramasProjectosLogDTO> createProgramasProjectosLog(@Valid @RequestBody ProgramasProjectosLogDTO programasProjectosLogDTO) throws URISyntaxException {
        log.debug("REST request to save ProgramasProjectosLog : {}", programasProjectosLogDTO);
        if (programasProjectosLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new programasProjectosLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProgramasProjectosLogDTO result = programasProjectosLogService.save(programasProjectosLogDTO);
        return ResponseEntity.created(new URI("/api/programas-projectos-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /programas-projectos-logs : Updates an existing programasProjectosLog.
     *
     * @param programasProjectosLogDTO the programasProjectosLogDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated programasProjectosLogDTO,
     * or with status 400 (Bad Request) if the programasProjectosLogDTO is not valid,
     * or with status 500 (Internal Server Error) if the programasProjectosLogDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/programas-projectos-logs")
    @Timed
    public ResponseEntity<ProgramasProjectosLogDTO> updateProgramasProjectosLog(@Valid @RequestBody ProgramasProjectosLogDTO programasProjectosLogDTO) throws URISyntaxException {
        log.debug("REST request to update ProgramasProjectosLog : {}", programasProjectosLogDTO);
        if (programasProjectosLogDTO.getId() == null) {
            return createProgramasProjectosLog(programasProjectosLogDTO);
        }
        ProgramasProjectosLogDTO result = programasProjectosLogService.save(programasProjectosLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, programasProjectosLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /programas-projectos-logs : get all the programasProjectosLogs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of programasProjectosLogs in body
     */
    @GetMapping("/programas-projectos-logs")
    @Timed
    public ResponseEntity<List<ProgramasProjectosLog>> getAllProgramasProjectosLogs(ProgramasProjectosLogCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ProgramasProjectosLogs by criteria: {}", criteria);
        Page<ProgramasProjectosLog> page = programasProjectosLogQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/programas-projectos-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/programas-projectos-logs/nomeFiltro")
    public ResponseEntity<List<ProgramasProjectosLog>> getByNome(@RequestParam(value = "nome") String nome, Pageable pageable) {
        Page<ProgramasProjectosLog> page = programasProjectosLogRepository.buscarPorNome(nome, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/programas-projectos-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /programas-projectos-logs/:id : get the "id" programasProjectosLog.
     *
     * @param id the id of the programasProjectosLogDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the programasProjectosLogDTO, or with status 404 (Not Found)
     */
    @GetMapping("/programas-projectos-logs/{id}")
    @Timed
    public ResponseEntity<ProgramasProjectosLog> getProgramasProjectosLog(@PathVariable Long id) {
        log.debug("REST request to get ProgramasProjectosLog : {}", id);
        ProgramasProjectosLog programasProjectosLog = programasProjectosLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(programasProjectosLog));
    }

    /**
     * DELETE  /programas-projectos-logs/:id : delete the "id" programasProjectosLog.
     *
     * @param id the id of the programasProjectosLogDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/programas-projectos-logs/{id}")
    @Timed
    public ResponseEntity<Void> deleteProgramasProjectosLog(@PathVariable Long id) {
        log.debug("REST request to delete ProgramasProjectosLog : {}", id);
        programasProjectosLogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
