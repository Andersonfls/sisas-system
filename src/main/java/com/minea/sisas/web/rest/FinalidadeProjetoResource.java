package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.FinalidadeProjeto;
import com.minea.sisas.repository.FinalidadeProjetoRepository;
import com.minea.sisas.service.FinalidadeProjetoService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.FinalidadeProjetoDTO;
import com.minea.sisas.service.dto.FinalidadeProjetoCriteria;
import com.minea.sisas.service.FinalidadeProjetoQueryService;
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
 * REST controller for managing FinalidadeProjeto.
 */
@RestController
@RequestMapping("/api")
public class FinalidadeProjetoResource {

    private final Logger log = LoggerFactory.getLogger(FinalidadeProjetoResource.class);

    private static final String ENTITY_NAME = "finalidadeProjeto";

    private final FinalidadeProjetoService finalidadeProjetoService;

    private final FinalidadeProjetoQueryService finalidadeProjetoQueryService;

    private final FinalidadeProjetoRepository finalidadeProjetoRepository;

    public FinalidadeProjetoResource(FinalidadeProjetoService finalidadeProjetoService, FinalidadeProjetoQueryService finalidadeProjetoQueryService, FinalidadeProjetoRepository finalidadeProjetoRepository) {
        this.finalidadeProjetoService = finalidadeProjetoService;
        this.finalidadeProjetoQueryService = finalidadeProjetoQueryService;
        this.finalidadeProjetoRepository= finalidadeProjetoRepository;
    }

    /**
     * POST  /finalidade-projetos : Create a new finalidadeProjeto.
     *
     * @param finalidadeProjetoDTO the finalidadeProjetoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new finalidadeProjetoDTO, or with status 400 (Bad Request) if the finalidadeProjeto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/finalidade-projetos")
    @Timed
    public ResponseEntity<FinalidadeProjetoDTO> createFinalidadeProjeto(@Valid @RequestBody FinalidadeProjetoDTO finalidadeProjetoDTO) throws URISyntaxException {
        log.debug("REST request to save FinalidadeProjeto : {}", finalidadeProjetoDTO);
        if (finalidadeProjetoDTO.getId() != null) {
            throw new BadRequestAlertException("A new finalidade-projeto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FinalidadeProjetoDTO result = finalidadeProjetoService.save(finalidadeProjetoDTO);
        return ResponseEntity.created(new URI("/api/finalidade-projetos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /finalidade-projetos : Updates an existing finalidadeProjeto.
     *
     * @param finalidadeProjetoDTO the finalidadeProjetoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated finalidadeProjetoDTO,
     * or with status 400 (Bad Request) if the finalidadeProjetoDTO is not valid,
     * or with status 500 (Internal Server Error) if the finalidadeProjetoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/finalidade-projetos")
    @Timed
    public ResponseEntity<FinalidadeProjetoDTO> updateFinalidadeProjeto(@Valid @RequestBody FinalidadeProjetoDTO finalidadeProjetoDTO) throws URISyntaxException {
        log.debug("REST request to update FinalidadeProjeto : {}", finalidadeProjetoDTO);
        if (finalidadeProjetoDTO.getId() == null) {
            return createFinalidadeProjeto(finalidadeProjetoDTO);
        }
        FinalidadeProjetoDTO result = finalidadeProjetoService.save(finalidadeProjetoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, finalidadeProjetoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /finalidade-projetos : get all the finalidadeProjetos.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of finalidadeProjetos in body
     */
    @GetMapping("/finalidade-projetos")
    @Timed
    public ResponseEntity<List<FinalidadeProjetoDTO>> getAllFinalidadeProjetos(FinalidadeProjetoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get FinalidadeProjetos by criteria: {}", criteria);
        Page<FinalidadeProjetoDTO> page = finalidadeProjetoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/finalidade-projetos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /*
     *  Filtro de usuários atráves do atributo nome
     */
    @GetMapping("/finalidade-projetos/nomeFiltro")
    public ResponseEntity<List<FinalidadeProjetoDTO>> getByNome(@RequestParam(value = "nome") String nome, Pageable pageable) {
        Page<FinalidadeProjetoDTO> page = finalidadeProjetoRepository.buscarPorNome(nome, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/finalidade-projetos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    /**
     * GET  /finalidade-projetos/:id : get the "id" finalidadeProjeto.
     *
     * @param id the id of the finalidadeProjetoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the finalidadeProjetoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/finalidade-projetos/{id}")
    @Timed
    public ResponseEntity<FinalidadeProjeto> getFinalidadeProjeto(@PathVariable Long id) {
        log.debug("REST request to get FinalidadeProjeto : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(finalidadeProjetoService.findOne(id)));
    }

    /**
     * DELETE  /finalidade-projetos/:id : delete the "id" finalidadeProjeto.
     *
     * @param id the id of the finalidadeProjetoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/finalidade-projetos/{id}")
    @Timed
    public ResponseEntity<Void> deleteFinalidadeProjeto(@PathVariable Long id) {
        log.debug("REST request to delete FinalidadeProjeto : {}", id);
        finalidadeProjetoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
