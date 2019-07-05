package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.Midia;
import com.minea.sisas.repository.MidiaRepository;
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
public class MidiaResource {

    private final Logger log = LoggerFactory.getLogger(MidiaResource.class);

    private static final String ENTITY_NAME = "midia";

    private final MidiaRepository midiaRepository;

    public MidiaResource(MidiaRepository midiaRepository) {
        this.midiaRepository = midiaRepository;
    }

    /**
     * POST  /midia : Create a new midia.
     *
     * @param midia the midia to create
     * @return the ResponseEntity with status 201 (Created) and with body the new midia, or with status 400 (Bad Request) if the midia has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/midias")
    @Timed
    public ResponseEntity<Midia> createMidia(@Valid @RequestBody Midia midia) throws URISyntaxException {
        log.debug("REST request to save Midia : {}", midia);
        if (midia.getId() != null) {
            throw new BadRequestAlertException("A new midia cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Midia result = midiaRepository.save(midia);
        return ResponseEntity.created(new URI("/api/midia/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);

    }

    /**
     * PUT  /midias : Updates an existing midia.
     *
     * @param midia the midia to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated midia,
     * or with status 400 (Bad Request) if the midia is not valid,
     * or with status 500 (Internal Server Error) if the midia couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/midias")
    @Timed
    public ResponseEntity<Midia> updateMidia(@RequestBody Midia midia) throws URISyntaxException {
        log.debug("REST request to update Midias : {}", midia);
        if (midia.getId() == null) {
            return createMidia(midia);
        }
        Midia result = midiaRepository.save(midia);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, midia.getId().toString()))
            .body(result);
    }

    /**
     * GET  /midias : get all the midias.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of midias in body
     */
    @GetMapping("/midias")
    @Timed
    public ResponseEntity<List<Midia>> getAllMidias(Pageable pageable) {
        log.debug("REST request to get all Midias");
        Page<Midia> page = midiaRepository.findAll((pageable));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/midia");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    /*
        Filtro de midias atráves do atributo nome
    */
    @GetMapping("/midias/nomeFiltro")
    public ResponseEntity<List<Midia>> getByNome(@RequestParam(value = "nome") String nome, Pageable pageable) {
        Page<Midia> page = midiaRepository.buscarPorNome(nome, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/midias");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /midias/:id : get the "id" midia.
     *
     * @param id the id of the midia to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the midia, or with status 404 (Not Found)
     */
    @GetMapping("/midias/{id}")
    @Timed
    public ResponseEntity<Midia> getMidia(@PathVariable Long id) {
        log.debug("REST request to get Midia : {}", id);
        Midia midia = midiaRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(midia));
    }

    /**
     * DELETE  /midias/:id : delete the "id" midia.
     *
     * @param id the id of the midia to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/midias/{id}")
    @Timed
    public ResponseEntity<Void> deleteMidia(@PathVariable Long id) {
        log.debug("REST request to delete Midia : {}", id);
        midiaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
