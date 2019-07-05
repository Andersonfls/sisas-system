package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.Autor;
import com.minea.sisas.repository.AutorRepository;
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
public class AutorResource {

    private final Logger log = LoggerFactory.getLogger(AutorResource.class);

    private static final String ENTITY_NAME = "autor";

    private final AutorRepository autorRepository;

    public AutorResource(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    /**
     * POST  /autor : Create a new autor.
     *
     * @param autor the autor to create
     * @return the ResponseEntity with status 201 (Created) and with body the new autor, or with status 400 (Bad Request) if the autor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/autores")
    @Timed
    public ResponseEntity<Autor> createAutor(@Valid @RequestBody Autor autor) throws URISyntaxException {
        log.debug("REST request to save Autor : {}", autor);
        if (autor.getId() != null) {
            throw new BadRequestAlertException("A new autor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Autor result = autorRepository.save(autor);
        return ResponseEntity.created(new URI("/api/autor/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);

    }

    /**
     * PUT  /autores : Updates an existing autor.
     *
     * @param autor the autor to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated autor,
     * or with status 400 (Bad Request) if the autor is not valid,
     * or with status 500 (Internal Server Error) if the autor couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/autores")
    @Timed
    public ResponseEntity<Autor> updateAutor(@RequestBody Autor autor) throws URISyntaxException {
        log.debug("REST request to update autor : {}", autor);
        if (autor.getId() == null) {
            return createAutor(autor);
        }
        Autor result = autorRepository.save(autor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, autor.getId().toString()))
            .body(result);
    }

    /**
     * GET  /autores : get all the autores.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of autores in body
     */
    @GetMapping("/autores")
    @Timed
    public ResponseEntity<List<Autor>> getAllAutores(Pageable pageable) {
        log.debug("REST request to get all Autor");
        Page<Autor> page = autorRepository.findAll((pageable));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/autor");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /autores : get all the autores order by name.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of autores in body
     */
    @GetMapping("/autoresOrdenado")
    @Timed
    public ResponseEntity<List<Autor>> getAllAutoresOrderByName(Pageable pageable) {
        log.debug("REST request to get all Autor");
        Page<Autor> page = autorRepository.buscarAutor((pageable));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/autor");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /autores : get all the autores order by name.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of autores in body
     */
    @GetMapping("/autoresOrdenado/status")
    @Timed
    public ResponseEntity<List<Autor>> getAllAutoresOrderByNameStatus(Pageable pageable) {
        log.debug("REST request to get all Autor");
        Page<Autor> page = autorRepository.buscarAutorStatus((pageable));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/autor");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /*
    *   Filtro de autores atráves do atributo nome
    */
    @GetMapping("/autores/nomeFiltro")
    public ResponseEntity<List<Autor>> getByNome(@RequestParam(value = "nome") String nome, Pageable pageable) {
        Page<Autor> page = autorRepository.buscarPorNome(nome, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/autores");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /autores/:id : get the "id" autor.
     *
     * @param id the id of the autor to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the autor, or with status 404 (Not Found)
     */
    @GetMapping("/autores/{id}")
    @Timed
    public ResponseEntity<Autor> getAutor(@PathVariable Long id) {
        log.debug("REST request to get Autor : {}", id);
        Autor autor = autorRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(autor));
    }

    /**
     * DELETE  /autores/:id : delete the "id" autor.
     *
     * @param id the id of the autor to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/autores/{id}")
    @Timed
    public ResponseEntity<Void> deleteAutor(@PathVariable Long id) {
        log.debug("REST request to delete Autor : {}", id);
        autorRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
