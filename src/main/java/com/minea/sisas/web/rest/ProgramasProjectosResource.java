package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.service.ProgramasProjectosService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.ProgramasProjectosDTO;
import com.minea.sisas.service.dto.ProgramasProjectosCriteria;
import com.minea.sisas.service.ProgramasProjectosQueryService;
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
 * REST controller for managing ProgramasProjectos.
 */
@RestController
@RequestMapping("/api")
public class ProgramasProjectosResource {

    private final Logger log = LoggerFactory.getLogger(ProgramasProjectosResource.class);

    private static final String ENTITY_NAME = "programasProjectos";

    private final ProgramasProjectosService programasProjectosService;

    private final ProgramasProjectosQueryService programasProjectosQueryService;

    public ProgramasProjectosResource(ProgramasProjectosService programasProjectosService, ProgramasProjectosQueryService programasProjectosQueryService) {
        this.programasProjectosService = programasProjectosService;
        this.programasProjectosQueryService = programasProjectosQueryService;
    }

    /**
     * POST  /programas-projectos : Create a new programasProjectos.
     *
     * @param programasProjectosDTO the programasProjectosDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new programasProjectosDTO, or with status 400 (Bad Request) if the programasProjectos has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/programas-projectos")
    @Timed
    public ResponseEntity<ProgramasProjectosDTO> createProgramasProjectos(@Valid @RequestBody ProgramasProjectosDTO programasProjectosDTO) throws URISyntaxException {
        log.debug("REST request to save ProgramasProjectos : {}", programasProjectosDTO);
        if (programasProjectosDTO.getId() != null) {
            throw new BadRequestAlertException("A new programasProjectos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProgramasProjectosDTO result = programasProjectosService.save(programasProjectosDTO);
        return ResponseEntity.created(new URI("/api/programas-projectos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /programas-projectos : Updates an existing programasProjectos.
     *
     * @param programasProjectosDTO the programasProjectosDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated programasProjectosDTO,
     * or with status 400 (Bad Request) if the programasProjectosDTO is not valid,
     * or with status 500 (Internal Server Error) if the programasProjectosDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/programas-projectos")
    @Timed
    public ResponseEntity<ProgramasProjectosDTO> updateProgramasProjectos(@Valid @RequestBody ProgramasProjectosDTO programasProjectosDTO) throws URISyntaxException {
        log.debug("REST request to update ProgramasProjectos : {}", programasProjectosDTO);
        if (programasProjectosDTO.getId() == null) {
            return createProgramasProjectos(programasProjectosDTO);
        }
        ProgramasProjectosDTO result = programasProjectosService.save(programasProjectosDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, programasProjectosDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /programas-projectos : get all the programasProjectos.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of programasProjectos in body
     */
    @GetMapping("/programas-projectos")
    @Timed
    public ResponseEntity<List<ProgramasProjectosDTO>> getAllProgramasProjectos(ProgramasProjectosCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ProgramasProjectos by criteria: {}", criteria);
        Page<ProgramasProjectosDTO> page = programasProjectosQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/programas-projectos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /programas-projectos/:id : get the "id" programasProjectos.
     *
     * @param id the id of the programasProjectosDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the programasProjectosDTO, or with status 404 (Not Found)
     */
    @GetMapping("/programas-projectos/{id}")
    @Timed
    public ResponseEntity<ProgramasProjectosDTO> getProgramasProjectos(@PathVariable Long id) {
        log.debug("REST request to get ProgramasProjectos : {}", id);
        ProgramasProjectosDTO programasProjectosDTO = programasProjectosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(programasProjectosDTO));
    }

    /**
     * DELETE  /programas-projectos/:id : delete the "id" programasProjectos.
     *
     * @param id the id of the programasProjectosDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/programas-projectos/{id}")
    @Timed
    public ResponseEntity<Void> deleteProgramasProjectos(@PathVariable Long id) {
        log.debug("REST request to delete ProgramasProjectos : {}", id);
        programasProjectosService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
