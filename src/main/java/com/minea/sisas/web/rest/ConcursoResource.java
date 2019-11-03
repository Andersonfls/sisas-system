package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.service.ConcursoService;
import com.minea.sisas.service.ProgramasProjectosService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.ConcursoDTO;
import com.minea.sisas.service.dto.ConcursoCriteria;
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
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing Concurso.
 */
@RestController
@RequestMapping("/api")
public class ConcursoResource {

    private final Logger log = LoggerFactory.getLogger(ConcursoResource.class);

    private static final String ENTITY_NAME = "concurso";

    private final ConcursoService concursoService;

    private final ProgramasProjectosService programasProjectosService;

    //private final ConcursoQueryService concursoQueryService;

    public ConcursoResource(ConcursoService concursoService, ProgramasProjectosService programasProjectosService) {
        this.concursoService = concursoService;
       // this.concursoQueryService = concursoQueryService;
        this.programasProjectosService = programasProjectosService;
    }

    /**
     * POST  /concursos : Create a new concurso.
     *
     * @param concursoDTO the concursoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new concursoDTO, or with status 400 (Bad Request) if the concurso has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/concursos")
    @Timed
    public ResponseEntity<ConcursoDTO> createConcurso(@Valid @RequestBody ConcursoDTO concursoDTO) throws URISyntaxException {
        log.debug("REST request to save Concurso : {}", concursoDTO);
        if (concursoDTO.getId() != null) {
            throw new BadRequestAlertException("A new concurso cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (Objects.nonNull(this.programasProjectosService)){
            concursoDTO.setProgramasProjectos(this.programasProjectosService.findOne(concursoDTO.getProgramasProjectos().getId()));
        }
        ConcursoDTO result = concursoService.save(concursoDTO);
        return ResponseEntity.created(new URI("/api/concursos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /concursos : Updates an existing concurso.
     *
     * @param concursoDTO the concursoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated concursoDTO,
     * or with status 400 (Bad Request) if the concursoDTO is not valid,
     * or with status 500 (Internal Server Error) if the concursoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/concursos")
    @Timed
    public ResponseEntity<ConcursoDTO> updateConcurso(@Valid @RequestBody ConcursoDTO concursoDTO) throws URISyntaxException {
        log.debug("REST request to update Concurso : {}", concursoDTO);
        if (concursoDTO.getId() == null) {
            return createConcurso(concursoDTO);
        }
        if (Objects.nonNull(this.programasProjectosService)){
            concursoDTO.setProgramasProjectos(this.programasProjectosService.findOne(concursoDTO.getProgramasProjectos().getId()));
        }
        ConcursoDTO result = concursoService.save(concursoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, concursoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /concursos : get all the concursos.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of concursos in body
     */
    @GetMapping("/concursos")
    @Timed
    public ResponseEntity<List<ConcursoDTO>> getAllConcursos(ConcursoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Concursos by criteria: {}", criteria);
        Page<ConcursoDTO> page = null;//concursoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/concursos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /concursos/:id : get the "id" concurso.
     *
     * @param id the id of the concursoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the concursoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/concursos/{id}")
    @Timed
    public ResponseEntity<ConcursoDTO> getConcurso(@PathVariable Long id) {
        log.debug("REST request to get Concurso : {}", id);
        ConcursoDTO concursoDTO = concursoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(concursoDTO));
    }


    /**
     * GET  /concursos/programas-projectos/:id : get the "id" concurso.
     *
     * @param id the id of the concursoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the concursoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/concursos/programas-projectos/{id}")
    @Timed
    public ResponseEntity<ConcursoDTO> getConcursoByProgramasProjectos(@PathVariable Long id) {
        log.debug("REST request to get Concurso : {}", id);
        ConcursoDTO concursoDTO = concursoService.findOneByProgramasProjectos(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(concursoDTO));
    }

    /**
     * DELETE  /concursos/:id : delete the "id" concurso.
     *
     * @param id the id of the concursoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/concursos/{id}")
    @Timed
    public ResponseEntity<Void> deleteConcurso(@PathVariable Long id) {
        log.debug("REST request to delete Concurso : {}", id);
        concursoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
