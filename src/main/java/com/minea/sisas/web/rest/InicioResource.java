package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.service.InicioService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.InicioDTO;
import com.minea.sisas.service.dto.InicioCriteria;
import com.minea.sisas.service.InicioQueryService;
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
 * REST controller for managing Inicio.
 */
@RestController
@RequestMapping("/api")
public class InicioResource {

    private final Logger log = LoggerFactory.getLogger(InicioResource.class);

    private static final String ENTITY_NAME = "inicio";

    private final InicioService inicioService;

    private final InicioQueryService inicioQueryService;

    public InicioResource(InicioService inicioService, InicioQueryService inicioQueryService) {
        this.inicioService = inicioService;
        this.inicioQueryService = inicioQueryService;
    }

    /**
     * POST  /inicios : Create a new inicio.
     *
     * @param inicioDTO the inicioDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new inicioDTO, or with status 400 (Bad Request) if the inicio has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/inicios")
    @Timed
    public ResponseEntity<InicioDTO> createInicio(@Valid @RequestBody InicioDTO inicioDTO) throws URISyntaxException {
        log.debug("REST request to save Inicio : {}", inicioDTO);
        if (inicioDTO.getId() != null) {
            throw new BadRequestAlertException("A new inicio cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InicioDTO result = inicioService.save(inicioDTO);
        return ResponseEntity.created(new URI("/api/inicios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /inicios : Updates an existing inicio.
     *
     * @param inicioDTO the inicioDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated inicioDTO,
     * or with status 400 (Bad Request) if the inicioDTO is not valid,
     * or with status 500 (Internal Server Error) if the inicioDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/inicios")
    @Timed
    public ResponseEntity<InicioDTO> updateInicio(@Valid @RequestBody InicioDTO inicioDTO) throws URISyntaxException {
        log.debug("REST request to update Inicio : {}", inicioDTO);
        if (inicioDTO.getId() == null) {
            return createInicio(inicioDTO);
        }
        InicioDTO result = inicioService.save(inicioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, inicioDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /inicios : get all the inicios.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of inicios in body
     */
    @GetMapping("/inicios")
    @Timed
    public ResponseEntity<List<InicioDTO>> getAllInicios(InicioCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Inicios by criteria: {}", criteria);
        Page<InicioDTO> page = inicioQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/inicios");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /inicios/:id : get the "id" inicio.
     *
     * @param id the id of the inicioDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the inicioDTO, or with status 404 (Not Found)
     */
    @GetMapping("/inicios/{id}")
    @Timed
    public ResponseEntity<InicioDTO> getInicio(@PathVariable Long id) {
        log.debug("REST request to get Inicio : {}", id);
        InicioDTO inicioDTO = inicioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(inicioDTO));
    }

    /**
     * DELETE  /inicios/:id : delete the "id" inicio.
     *
     * @param id the id of the inicioDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/inicios/{id}")
    @Timed
    public ResponseEntity<Void> deleteInicio(@PathVariable Long id) {
        log.debug("REST request to delete Inicio : {}", id);
        inicioService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
