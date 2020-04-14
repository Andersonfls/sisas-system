package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.service.ExecucaoService;
import com.minea.sisas.service.dto.ConcursoDTO;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.ExecucaoDTO;
import com.minea.sisas.service.dto.ExecucaoCriteria;
import com.minea.sisas.service.ExecucaoQueryService;
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
 * REST controller for managing Execucao.
 */
@RestController
@RequestMapping("/api")
public class ExecucaoResource {

    private final Logger log = LoggerFactory.getLogger(ExecucaoResource.class);

    private static final String ENTITY_NAME = "execucao";

    private final ExecucaoService execucaoService;

    private final ExecucaoQueryService execucaoQueryService;

    public ExecucaoResource(ExecucaoService execucaoService, ExecucaoQueryService execucaoQueryService) {
        this.execucaoService = execucaoService;
        this.execucaoQueryService = execucaoQueryService;
    }

    /**
     * POST  /execucaos : Create a new execucao.
     *
     * @param execucaoDTO the execucaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new execucaoDTO, or with status 400 (Bad Request) if the execucao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/execucaos")
    @Timed
    public ResponseEntity<ExecucaoDTO> createExecucao(@Valid @RequestBody ExecucaoDTO execucaoDTO) throws URISyntaxException {
        log.debug("REST request to save Execucao : {}", execucaoDTO);
        if (execucaoDTO.getId() != null) {
            throw new BadRequestAlertException("A new execucao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExecucaoDTO result = execucaoService.save(execucaoDTO);
        return ResponseEntity.created(new URI("/api/execucaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /execucaos : Updates an existing execucao.
     *
     * @param execucaoDTO the execucaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated execucaoDTO,
     * or with status 400 (Bad Request) if the execucaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the execucaoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/execucaos")
    @Timed
    public ResponseEntity<ExecucaoDTO> updateExecucao(@Valid @RequestBody ExecucaoDTO execucaoDTO) throws URISyntaxException {
        log.debug("REST request to update Execucao : {}", execucaoDTO);
        if (execucaoDTO.getId() == null) {
            return createExecucao(execucaoDTO);
        }
        ExecucaoDTO result = execucaoService.save(execucaoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, execucaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /execucaos : get all the execucaos.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of execucaos in body
     */
    @GetMapping("/execucaos")
    @Timed
    public ResponseEntity<List<ExecucaoDTO>> getAllExecucaos(ExecucaoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Execucaos by criteria: {}", criteria);
        Page<ExecucaoDTO> page = execucaoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/execucaos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /concursos/programas-projectos/:id : get the "id" concurso.
     *
     * @param id the id of the concursoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the concursoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/execucaos/programas-projectos/{id}")
    @Timed
    public ResponseEntity<ExecucaoDTO> getExecucaoByProgramasProjectos(@PathVariable Long id) {
        log.debug("REST request to get Execucao : {}", id);
        ExecucaoDTO execucaoDTO = execucaoService.findOneByProgramasProjectos(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(execucaoDTO));
    }


    /**
     * GET  /execucaos/:id : get the "id" execucao.
     *
     * @param id the id of the execucaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the execucaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/execucaos/{id}")
    @Timed
    public ResponseEntity<ExecucaoDTO> getExecucao(@PathVariable Long id) {
        log.debug("REST request to get Execucao : {}", id);
        ExecucaoDTO execucaoDTO = execucaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(execucaoDTO));
    }

    /**
     * DELETE  /execucaos/:id : delete the "id" execucao.
     *
     * @param id the id of the execucaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/execucaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteExecucao(@PathVariable Long id) {
        log.debug("REST request to delete Execucao : {}", id);
        execucaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
