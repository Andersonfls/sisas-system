package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.AutorObra;
import com.minea.sisas.repository.AutorObraRepository;
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
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AutorObraResource {

    private final Logger log = LoggerFactory.getLogger(AutorObraResource.class);

    private static final String ENTITY_NAME = "autorObra";

    private final AutorObraRepository autorObraRepository;

    public AutorObraResource(AutorObraRepository autorObraRepository) {
        this.autorObraRepository = autorObraRepository;
    }

    /**
     * POST  /autorObra : Create a new autorObra.
     *
     * @param autorObra the autorObra to create
     * @return the ResponseEntity with status 201 (Created) and with body the new autorObra, or with status 400 (Bad Request) if the autorObra has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/autorObras")
    @Timed
    public ResponseEntity<AutorObra> createAutorObra(@Valid @RequestBody AutorObra autorObra) throws URISyntaxException {
        log.debug("REST request to save AutorObra : {}", autorObra);
        if (autorObra.getId() != null) {
            throw new BadRequestAlertException("A new autor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AutorObra result = autorObraRepository.save(autorObra);
        return ResponseEntity.created(new URI("/api/autor-obra/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);

    }

    /**
     * PUT  /autorObras : Updates an existing autorObra.
     *
     * @param autorObra the autorObra to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated autorObra,
     * or with status 400 (Bad Request) if the autorObra is not valid,
     * or with status 500 (Internal Server Error) if the autorObra couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/autorObras")
    @Timed
    public ResponseEntity<AutorObra> updateAutorObra(@RequestBody AutorObra autorObra) throws URISyntaxException {
        log.debug("REST request to update autorObra : {}", autorObra);
        if (autorObra.getId() == null) {
            return createAutorObra(autorObra);
        }
        AutorObra result = autorObraRepository.save(autorObra);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, autorObra.getId().toString()))
            .body(result);
    }

    /**
     * GET  /autorObras : get all the autorObras.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of autorObras in body
     */
    @GetMapping("/autorObras")
    @Timed
    public ResponseEntity<List<AutorObra>> getAllAutorObras(Pageable pageable) {
        log.debug("REST request to get all AutorObra");
        Page<AutorObra> page = autorObraRepository.findAll((pageable));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/autor-obra");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    /**
     * GET  /autorObras : get all the autorObras.
     *
        * @return the ResponseEntity with status 200 (OK) and the list of autorObras in body
     */
    @GetMapping("/autorObras/obraId/{id}")
    @Timed
    public List<AutorObra> getAllAutorObrasObraId(@PathVariable Long id) {
        log.debug("REST request to get all AutorId by Status: {}", id);
        return autorObraRepository.findByAutorId(id);
    }


    /**
     * GET  /autorObras/:id : get the "id" autorObra.
     *
     * @param id the id of the autor to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the autorObra, or with status 404 (Not Found)
     */
    @GetMapping("/autorObras/{id}")
    @Timed
    public ResponseEntity<AutorObra> getAutorObras(@PathVariable Long id) {
        log.debug("REST request to get AutorObra : {}", id);
        AutorObra autorObra = autorObraRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(autorObra));
    }

    /**
     * DELETE  /autorObras/:id : delete the "id" autorObra.
     *
     * @param id the id of the autorObra to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/autorObras/{id}")
    @Timed
    public ResponseEntity<Void> deleteAutorObra(@PathVariable Long id) {
        log.debug("REST request to delete AutorObra : {}", id);
        autorObraRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
