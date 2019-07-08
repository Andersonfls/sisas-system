package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.service.SituacaoService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.SituacaoDTO;
import com.minea.sisas.service.dto.SituacaoCriteria;
import com.minea.sisas.service.SituacaoQueryService;
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
 * REST controller for managing Situacao.
 */
@RestController
@RequestMapping("/api")
public class SituacaoResource {

    private final Logger log = LoggerFactory.getLogger(SituacaoResource.class);

    private static final String ENTITY_NAME = "situacao";

    private final SituacaoService situacaoService;

    private final SituacaoQueryService situacaoQueryService;

    public SituacaoResource(SituacaoService situacaoService, SituacaoQueryService situacaoQueryService) {
        this.situacaoService = situacaoService;
        this.situacaoQueryService = situacaoQueryService;
    }

    /**
     * POST  /situacaos : Create a new situacao.
     *
     * @param situacaoDTO the situacaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new situacaoDTO, or with status 400 (Bad Request) if the situacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/situacaos")
    @Timed
    public ResponseEntity<SituacaoDTO> createSituacao(@Valid @RequestBody SituacaoDTO situacaoDTO) throws URISyntaxException {
        log.debug("REST request to save Situacao : {}", situacaoDTO);
        if (situacaoDTO.getId() != null) {
            throw new BadRequestAlertException("A new situacao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SituacaoDTO result = situacaoService.save(situacaoDTO);
        return ResponseEntity.created(new URI("/api/situacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /situacaos : Updates an existing situacao.
     *
     * @param situacaoDTO the situacaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated situacaoDTO,
     * or with status 400 (Bad Request) if the situacaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the situacaoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/situacaos")
    @Timed
    public ResponseEntity<SituacaoDTO> updateSituacao(@Valid @RequestBody SituacaoDTO situacaoDTO) throws URISyntaxException {
        log.debug("REST request to update Situacao : {}", situacaoDTO);
        if (situacaoDTO.getId() == null) {
            return createSituacao(situacaoDTO);
        }
        SituacaoDTO result = situacaoService.save(situacaoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, situacaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /situacaos : get all the situacaos.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of situacaos in body
     */
    @GetMapping("/situacaos")
    @Timed
    public ResponseEntity<List<SituacaoDTO>> getAllSituacaos(SituacaoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Situacaos by criteria: {}", criteria);
        Page<SituacaoDTO> page = situacaoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/situacaos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /situacaos/:id : get the "id" situacao.
     *
     * @param id the id of the situacaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the situacaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/situacaos/{id}")
    @Timed
    public ResponseEntity<SituacaoDTO> getSituacao(@PathVariable Long id) {
        log.debug("REST request to get Situacao : {}", id);
        SituacaoDTO situacaoDTO = situacaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(situacaoDTO));
    }

    /**
     * DELETE  /situacaos/:id : delete the "id" situacao.
     *
     * @param id the id of the situacaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/situacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteSituacao(@PathVariable Long id) {
        log.debug("REST request to delete Situacao : {}", id);
        situacaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
