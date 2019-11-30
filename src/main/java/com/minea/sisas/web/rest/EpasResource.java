package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.Epas;
import com.minea.sisas.repository.EpasRepository;
import com.minea.sisas.security.AuthoritiesConstants;
import com.minea.sisas.service.EpasService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.EpasDTO;
import com.minea.sisas.service.dto.EpasCriteria;
import com.minea.sisas.service.EpasQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Epas.
 */
@RestController
@RequestMapping("/api")
public class EpasResource {

    private final Logger log = LoggerFactory.getLogger(EpasResource.class);

    private static final String ENTITY_NAME = "epas";

    private final EpasService epasService;

    private final EpasQueryService epasQueryService;

    private final EpasRepository epasRepository;

    public EpasResource(EpasService epasService, EpasQueryService epasQueryService, EpasRepository epasRepository) {
        this.epasService = epasService;
        this.epasQueryService = epasQueryService;
        this.epasRepository= epasRepository;
    }

    /**
     * POST  /epas : Create a new epas.
     *
     * @param epasDTO the epasDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new epasDTO, or with status 400 (Bad Request) if the epas has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/epas")
    @Timed
    public ResponseEntity<EpasDTO> createEpas(@Valid @RequestBody EpasDTO epasDTO) throws URISyntaxException {
        log.debug("REST request to save Epas : {}", epasDTO);
        if (epasDTO.getId() != null) {
            throw new BadRequestAlertException("A new epas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EpasDTO result = epasService.save(epasDTO);
        return ResponseEntity.created(new URI("/api/epas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /epas : Updates an existing epas.
     *
     * @param epasDTO the epasDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated epasDTO,
     * or with status 400 (Bad Request) if the epasDTO is not valid,
     * or with status 500 (Internal Server Error) if the epasDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/epas")
    @Timed
    public ResponseEntity<EpasDTO> updateEpas(@Valid @RequestBody EpasDTO epasDTO) throws URISyntaxException {
        log.debug("REST request to update Epas : {}", epasDTO);
        if (epasDTO.getId() == null) {
            return createEpas(epasDTO);
        }
        EpasDTO result = epasService.save(epasDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, epasDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /epas : get all the epas.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of epas in body
     */
    @GetMapping("/epas")
    @Timed
    public ResponseEntity<List<EpasDTO>> getAllEpass(EpasCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Epass by criteria: {}", criteria);
        Page<EpasDTO> page = epasQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/epas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /*
     *  Filtro de usuários atráves do atributo nome
     */
    @GetMapping("/epas/nomeFiltro")
    public ResponseEntity<List<EpasDTO>> getByNome(@RequestParam(value = "nome") String nome, Pageable pageable) {
        Page<EpasDTO> page = epasRepository.buscarPorNome(nome, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/epas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    /**
     * GET  /epas/:id : get the "id" epas.
     *
     * @param id the id of the epasDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the epasDTO, or with status 404 (Not Found)
     */
    @GetMapping("/epas/{id}")
    @Timed
    public ResponseEntity<Epas> getEpas(@PathVariable Long id) {
        log.debug("REST request to get Epas : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(epasService.findOne(id)));
    }

    /**
     * DELETE  /epas/:id : delete the "id" epas.
     *
     * @param id the id of the epasDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/epas/{id}")
    @Timed
    public ResponseEntity<Void> deleteEpas(@PathVariable Long id) {
        log.debug("REST request to delete Epas : {}", id);
        epasService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
