package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.ProjectosQueryService;
import com.minea.sisas.service.ProjectosService;
import com.minea.sisas.service.dto.ProjectosCriteria;
import com.minea.sisas.service.dto.ProjectosDTO;
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
 * REST controller for managing Projectos.
 */
@RestController
@RequestMapping("/api")
public class ProjectosResource {

    private final Logger log = LoggerFactory.getLogger(ProjectosResource.class);

    private static final String ENTITY_NAME = "projectos";

    private final ProjectosService projectosService;

    private final ProjectosQueryService projectosQueryService;

    public ProjectosResource(ProjectosService projectosService, ProjectosQueryService projectosQueryService) {
        this.projectosService = projectosService;
        this.projectosQueryService = projectosQueryService;
    }

    /**
     * POST  /projectos : Create a new projectos.
     *
     * @param projectosDTO the projectosDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectosDTO, or with status 400 (Bad Request) if the projectos has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/projectos")
    @Timed
    public ResponseEntity<ProjectosDTO> createProjectos(@Valid @RequestBody ProjectosDTO projectosDTO) throws URISyntaxException {
        log.debug("REST request to save Projectos : {}", projectosDTO);
        if (projectosDTO.getId() != null) {
            throw new BadRequestAlertException("A new projectos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectosDTO result = projectosService.save(projectosDTO);
        return ResponseEntity.created(new URI("/api/projectos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /projectos : Updates an existing projectos.
     *
     * @param projectosDTO the projectosDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectosDTO,
     * or with status 400 (Bad Request) if the projectosDTO is not valid,
     * or with status 500 (Internal Server Error) if the projectosDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/projectos")
    @Timed
    public ResponseEntity<ProjectosDTO> updateProjectos(@Valid @RequestBody ProjectosDTO projectosDTO) throws URISyntaxException {
        log.debug("REST request to update Projectos : {}", projectosDTO);
        if (projectosDTO.getId() == null) {
            return createProjectos(projectosDTO);
        }
        ProjectosDTO result = projectosService.save(projectosDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projectosDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /projectos : get all the projectos.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of projectos in body
     */
    @GetMapping("/projectos")
    @Timed
    public ResponseEntity<List<ProjectosDTO>> getAllProjectos(ProjectosCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Projectos by criteria: {}", criteria);
        Page<ProjectosDTO> page = projectosQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/projectos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /projectos/:id : get the "id" projectos.
     *
     * @param id the id of the projectosDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectosDTO, or with status 404 (Not Found)
     */
    @GetMapping("/projectos/{id}")
    @Timed
    public ResponseEntity<ProjectosDTO> getProjectos(@PathVariable Long id) {
        log.debug("REST request to get Projectos : {}", id);
        ProjectosDTO projectosDTO = projectosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(projectosDTO));
    }

    /**
     * DELETE  /projectos/:id : delete the "id" projectos.
     *
     * @param id the id of the projectosDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/projectos/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjectos(@PathVariable Long id) {
        log.debug("REST request to delete Projectos : {}", id);
        projectosService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
