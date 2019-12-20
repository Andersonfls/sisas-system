package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.NoticiasPortal;
import com.minea.sisas.service.NoticiasPortalService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing NoticiasPortal.
 */
@RestController
@RequestMapping("/api")
public class NoticiasPortalResource {

    private final Logger log = LoggerFactory.getLogger(NoticiasPortalResource.class);

    private static final String ENTITY_NAME = "noticiasPortal";

    private final NoticiasPortalService noticiasPortalService;

    public NoticiasPortalResource(NoticiasPortalService noticiasPortalService) {
        this.noticiasPortalService = noticiasPortalService;
    }

    /**
     * POST  /noticias-portals : Create a new noticiasPortal.
     *
     * @param noticiasPortal the noticiasPortal to create
     * @return the ResponseEntity with status 201 (Created) and with body the new noticiasPortal, or with status 400 (Bad Request) if the noticiasPortal has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/noticias-portals")
    @Timed
    public ResponseEntity<NoticiasPortal> createNoticiasPortal(@RequestBody NoticiasPortal noticiasPortal) throws URISyntaxException {
        log.debug("REST request to save NoticiasPortal : {}", noticiasPortal);
        if (noticiasPortal.getId() != null) {
            throw new BadRequestAlertException("A new noticiasPortal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NoticiasPortal result = noticiasPortalService.save(noticiasPortal);
        return ResponseEntity.created(new URI("/api/noticias-portals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /noticias-portals : Updates an existing noticiasPortal.
     *
     * @param noticiasPortal the noticiasPortal to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated noticiasPortal,
     * or with status 400 (Bad Request) if the noticiasPortal is not valid,
     * or with status 500 (Internal Server Error) if the noticiasPortal couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/noticias-portals")
    @Timed
    public ResponseEntity<NoticiasPortal> updateNoticiasPortal(@RequestBody NoticiasPortal noticiasPortal) throws URISyntaxException {
        log.debug("REST request to update NoticiasPortal : {}", noticiasPortal);
        if (noticiasPortal.getId() == null) {
            return createNoticiasPortal(noticiasPortal);
        }
        NoticiasPortal result = noticiasPortalService.save(noticiasPortal);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, noticiasPortal.getId().toString()))
            .body(result);
    }

    /**
     * GET  /noticias-portals : get all the noticiasPortals.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of noticiasPortals in body
     */
    @GetMapping("/noticias-portals")
    @Timed
    public ResponseEntity<List<NoticiasPortal>> getAllNoticiasPortals(Pageable pageable) {
        log.debug("REST request to get a page of NoticiasPortals");
        Page<NoticiasPortal> page = noticiasPortalService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/noticias-portals");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /noticias-portals/:id : get the "id" noticiasPortal.
     *
     * @param id the id of the noticiasPortal to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the noticiasPortal, or with status 404 (Not Found)
     */
    @GetMapping("/noticias-portals/{id}")
    @Timed
    public ResponseEntity<NoticiasPortal> getNoticiasPortal(@PathVariable Long id) {
        log.debug("REST request to get NoticiasPortal : {}", id);
        NoticiasPortal noticiasPortal = noticiasPortalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(noticiasPortal));
    }

    /**
     * DELETE  /noticias-portals/:id : delete the "id" noticiasPortal.
     *
     * @param id the id of the noticiasPortal to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/noticias-portals/{id}")
    @Timed
    public ResponseEntity<Void> deleteNoticiasPortal(@PathVariable Long id) {
        log.debug("REST request to delete NoticiasPortal : {}", id);
        noticiasPortalService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
