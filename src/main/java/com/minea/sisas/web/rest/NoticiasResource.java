package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.service.NoticiasService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.NoticiasDTO;
import com.minea.sisas.service.dto.NoticiasCriteria;
import com.minea.sisas.service.NoticiasQueryService;
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
 * REST controller for managing Noticias.
 */
@RestController
@RequestMapping("/api")
public class NoticiasResource {

    private final Logger log = LoggerFactory.getLogger(NoticiasResource.class);

    private static final String ENTITY_NAME = "noticias";

    private final NoticiasService noticiasService;

    private final NoticiasQueryService noticiasQueryService;

    public NoticiasResource(NoticiasService noticiasService, NoticiasQueryService noticiasQueryService) {
        this.noticiasService = noticiasService;
        this.noticiasQueryService = noticiasQueryService;
    }

    /**
     * POST  /noticias : Create a new noticias.
     *
     * @param noticiasDTO the noticiasDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new noticiasDTO, or with status 400 (Bad Request) if the noticias has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/noticias")
    @Timed
    public ResponseEntity<NoticiasDTO> createNoticias(@Valid @RequestBody NoticiasDTO noticiasDTO) throws URISyntaxException {
        log.debug("REST request to save Noticias : {}", noticiasDTO);
        if (noticiasDTO.getId() != null) {
            throw new BadRequestAlertException("A new noticias cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NoticiasDTO result = noticiasService.save(noticiasDTO);
        return ResponseEntity.created(new URI("/api/noticias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /noticias : Updates an existing noticias.
     *
     * @param noticiasDTO the noticiasDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated noticiasDTO,
     * or with status 400 (Bad Request) if the noticiasDTO is not valid,
     * or with status 500 (Internal Server Error) if the noticiasDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/noticias")
    @Timed
    public ResponseEntity<NoticiasDTO> updateNoticias(@Valid @RequestBody NoticiasDTO noticiasDTO) throws URISyntaxException {
        log.debug("REST request to update Noticias : {}", noticiasDTO);
        if (noticiasDTO.getId() == null) {
            return createNoticias(noticiasDTO);
        }
        NoticiasDTO result = noticiasService.save(noticiasDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, noticiasDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /noticias : get all the noticias.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of noticias in body
     */
    @GetMapping("/noticias")
    @Timed
    public ResponseEntity<List<NoticiasDTO>> getAllNoticias(NoticiasCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Noticias by criteria: {}", criteria);
        Page<NoticiasDTO> page = noticiasQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/noticias");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /noticias/:id : get the "id" noticias.
     *
     * @param id the id of the noticiasDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the noticiasDTO, or with status 404 (Not Found)
     */
    @GetMapping("/noticias/{id}")
    @Timed
    public ResponseEntity<NoticiasDTO> getNoticias(@PathVariable Long id) {
        log.debug("REST request to get Noticias : {}", id);
        NoticiasDTO noticiasDTO = noticiasService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(noticiasDTO));
    }

    /**
     * DELETE  /noticias/:id : delete the "id" noticias.
     *
     * @param id the id of the noticiasDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/noticias/{id}")
    @Timed
    public ResponseEntity<Void> deleteNoticias(@PathVariable Long id) {
        log.debug("REST request to delete Noticias : {}", id);
        noticiasService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
