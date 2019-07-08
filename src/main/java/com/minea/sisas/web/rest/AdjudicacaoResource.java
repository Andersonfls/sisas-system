package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.AdjudicacaoQueryService;
import com.minea.sisas.service.AdjudicacaoService;
import com.minea.sisas.service.dto.AdjudicacaoCriteria;
import com.minea.sisas.service.dto.AdjudicacaoDTO;
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
 * REST controller for managing Adjudicacao.
 */
@RestController
@RequestMapping("/api")
public class AdjudicacaoResource {

    private final Logger log = LoggerFactory.getLogger(AdjudicacaoResource.class);

    private static final String ENTITY_NAME = "adjudicacao";

    private final AdjudicacaoService adjudicacaoService;

    private final AdjudicacaoQueryService adjudicacaoQueryService;

    public AdjudicacaoResource(AdjudicacaoService adjudicacaoService, AdjudicacaoQueryService adjudicacaoQueryService) {
        this.adjudicacaoService = adjudicacaoService;
        this.adjudicacaoQueryService = adjudicacaoQueryService;
    }

    /**
     * POST  /adjudicacaos : Create a new adjudicacao.
     *
     * @param adjudicacaoDTO the adjudicacaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new adjudicacaoDTO, or with status 400 (Bad Request) if the adjudicacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/adjudicacaos")
    @Timed
    public ResponseEntity<AdjudicacaoDTO> createAdjudicacao(@Valid @RequestBody AdjudicacaoDTO adjudicacaoDTO) throws URISyntaxException {
        log.debug("REST request to save Adjudicacao : {}", adjudicacaoDTO);
        if (adjudicacaoDTO.getId() != null) {
            throw new BadRequestAlertException("A new adjudicacao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdjudicacaoDTO result = adjudicacaoService.save(adjudicacaoDTO);
        return ResponseEntity.created(new URI("/api/adjudicacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /adjudicacaos : Updates an existing adjudicacao.
     *
     * @param adjudicacaoDTO the adjudicacaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated adjudicacaoDTO,
     * or with status 400 (Bad Request) if the adjudicacaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the adjudicacaoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/adjudicacaos")
    @Timed
    public ResponseEntity<AdjudicacaoDTO> updateAdjudicacao(@Valid @RequestBody AdjudicacaoDTO adjudicacaoDTO) throws URISyntaxException {
        log.debug("REST request to update Adjudicacao : {}", adjudicacaoDTO);
        if (adjudicacaoDTO.getId() == null) {
            return createAdjudicacao(adjudicacaoDTO);
        }
        AdjudicacaoDTO result = adjudicacaoService.save(adjudicacaoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, adjudicacaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /adjudicacaos : get all the adjudicacaos.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of adjudicacaos in body
     */
    @GetMapping("/adjudicacaos")
    @Timed
    public ResponseEntity<List<AdjudicacaoDTO>> getAllAdjudicacaos(AdjudicacaoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Adjudicacaos by criteria: {}", criteria);
        Page<AdjudicacaoDTO> page = adjudicacaoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/adjudicacaos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /adjudicacaos/:id : get the "id" adjudicacao.
     *
     * @param id the id of the adjudicacaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the adjudicacaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/adjudicacaos/{id}")
    @Timed
    public ResponseEntity<AdjudicacaoDTO> getAdjudicacao(@PathVariable Long id) {
        log.debug("REST request to get Adjudicacao : {}", id);
        AdjudicacaoDTO adjudicacaoDTO = adjudicacaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(adjudicacaoDTO));
    }

    /**
     * DELETE  /adjudicacaos/:id : delete the "id" adjudicacao.
     *
     * @param id the id of the adjudicacaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/adjudicacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteAdjudicacao(@PathVariable Long id) {
        log.debug("REST request to delete Adjudicacao : {}", id);
        adjudicacaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
