package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.Comuna;
import com.minea.sisas.repository.ComunaRepository;
import com.minea.sisas.security.AuthoritiesConstants;
import com.minea.sisas.service.ComunaService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.ComunaDTO;
import com.minea.sisas.service.dto.ComunaCriteria;
import com.minea.sisas.service.ComunaQueryService;
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
 * REST controller for managing Comuna.
 */
@RestController
@RequestMapping("/api")
public class ComunaResource {

    private final Logger log = LoggerFactory.getLogger(ComunaResource.class);

    private static final String ENTITY_NAME = "comuna";

    private final ComunaService comunaService;

    private final ComunaQueryService comunaQueryService;

    private final ComunaRepository comunaRepository;

    public ComunaResource(ComunaService comunaService, ComunaQueryService comunaQueryService, ComunaRepository comunaRepository) {
        this.comunaService = comunaService;
        this.comunaQueryService = comunaQueryService;
        this.comunaRepository= comunaRepository;
    }

    /**
     * POST  /comunas : Create a new comuna.
     *
     * @param comunaDTO the comunaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new comunaDTO, or with status 400 (Bad Request) if the comuna has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/comunas")
    @Timed
    public ResponseEntity<ComunaDTO> createComuna(@Valid @RequestBody ComunaDTO comunaDTO) throws URISyntaxException {
        log.debug("REST request to save Comuna : {}", comunaDTO);
        if (comunaDTO.getId() != null) {
            throw new BadRequestAlertException("A new comuna cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ComunaDTO result = comunaService.save(comunaDTO);
        return ResponseEntity.created(new URI("/api/comunas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /comunas : Updates an existing comuna.
     *
     * @param comunaDTO the comunaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated comunaDTO,
     * or with status 400 (Bad Request) if the comunaDTO is not valid,
     * or with status 500 (Internal Server Error) if the comunaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/comunas")
    @Timed
    public ResponseEntity<ComunaDTO> updateComuna(@Valid @RequestBody ComunaDTO comunaDTO) throws URISyntaxException {
        log.debug("REST request to update Comuna : {}", comunaDTO);
        if (comunaDTO.getId() == null) {
            return createComuna(comunaDTO);
        }
        ComunaDTO result = comunaService.save(comunaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, comunaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /comunas : get all the comunas.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of comunas in body
     */
    @GetMapping("/comunas")
    @Timed
    public ResponseEntity<List<ComunaDTO>> getAllComunas(ComunaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Comunas by criteria: {}", criteria);
        Page<ComunaDTO> page = comunaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/comunas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /*
     *  Filtro de usuários atráves do atributo nome
     */
    @GetMapping("/comunas/nomeFiltro")
    public ResponseEntity<List<ComunaDTO>> getByNome(@RequestParam(value = "nome") String nome, Pageable pageable) {
        Page<ComunaDTO> page = comunaRepository.buscarPorNome(nome, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/comunas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    /**
     * GET  /comunas/:id : get the "id" comuna.
     *
     * @param id the id of the comunaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the comunaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/comunas/{id}")
    @Timed
    public ResponseEntity<Comuna> getComuna(@PathVariable Long id) {
        log.debug("REST request to get Comuna : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(comunaService.findOne(id)));
    }

    /**
     * DELETE  /comunas/:id : delete the "id" comuna.
     *
     * @param id the id of the comunaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/comunas/{id}")
    @Timed
    public ResponseEntity<Void> deleteComuna(@PathVariable Long id) {
        log.debug("REST request to delete Comuna : {}", id);
        comunaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
