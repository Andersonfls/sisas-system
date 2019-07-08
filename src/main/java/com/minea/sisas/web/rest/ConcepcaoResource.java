package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.service.ConcepcaoService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.ConcepcaoDTO;
import com.minea.sisas.service.dto.ConcepcaoCriteria;
import com.minea.sisas.service.ConcepcaoQueryService;
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
 * REST controller for managing Concepcao.
 */
@RestController
@RequestMapping("/api")
public class ConcepcaoResource {

    private final Logger log = LoggerFactory.getLogger(ConcepcaoResource.class);

    private static final String ENTITY_NAME = "concepcao";

    private final ConcepcaoService concepcaoService;

    private final ConcepcaoQueryService concepcaoQueryService;

    public ConcepcaoResource(ConcepcaoService concepcaoService, ConcepcaoQueryService concepcaoQueryService) {
        this.concepcaoService = concepcaoService;
        this.concepcaoQueryService = concepcaoQueryService;
    }

    /**
     * POST  /concepcaos : Create a new concepcao.
     *
     * @param concepcaoDTO the concepcaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new concepcaoDTO, or with status 400 (Bad Request) if the concepcao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/concepcaos")
    @Timed
    public ResponseEntity<ConcepcaoDTO> createConcepcao(@Valid @RequestBody ConcepcaoDTO concepcaoDTO) throws URISyntaxException {
        log.debug("REST request to save Concepcao : {}", concepcaoDTO);
        if (concepcaoDTO.getId() != null) {
            throw new BadRequestAlertException("A new concepcao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConcepcaoDTO result = concepcaoService.save(concepcaoDTO);
        return ResponseEntity.created(new URI("/api/concepcaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /concepcaos : Updates an existing concepcao.
     *
     * @param concepcaoDTO the concepcaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated concepcaoDTO,
     * or with status 400 (Bad Request) if the concepcaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the concepcaoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/concepcaos")
    @Timed
    public ResponseEntity<ConcepcaoDTO> updateConcepcao(@Valid @RequestBody ConcepcaoDTO concepcaoDTO) throws URISyntaxException {
        log.debug("REST request to update Concepcao : {}", concepcaoDTO);
        if (concepcaoDTO.getId() == null) {
            return createConcepcao(concepcaoDTO);
        }
        ConcepcaoDTO result = concepcaoService.save(concepcaoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, concepcaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /concepcaos : get all the concepcaos.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of concepcaos in body
     */
    @GetMapping("/concepcaos")
    @Timed
    public ResponseEntity<List<ConcepcaoDTO>> getAllConcepcaos(ConcepcaoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Concepcaos by criteria: {}", criteria);
        Page<ConcepcaoDTO> page = concepcaoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/concepcaos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /concepcaos/:id : get the "id" concepcao.
     *
     * @param id the id of the concepcaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the concepcaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/concepcaos/{id}")
    @Timed
    public ResponseEntity<ConcepcaoDTO> getConcepcao(@PathVariable Long id) {
        log.debug("REST request to get Concepcao : {}", id);
        ConcepcaoDTO concepcaoDTO = concepcaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(concepcaoDTO));
    }

    /**
     * DELETE  /concepcaos/:id : delete the "id" concepcao.
     *
     * @param id the id of the concepcaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/concepcaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteConcepcao(@PathVariable Long id) {
        log.debug("REST request to delete Concepcao : {}", id);
        concepcaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
