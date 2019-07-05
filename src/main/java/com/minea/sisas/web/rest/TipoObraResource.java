package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.TipoObra;
import com.minea.sisas.repository.TipoObraRepository;
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
public class TipoObraResource {

    private final Logger log = LoggerFactory.getLogger(TipoObraResource.class);

    private static final String ENTITY_NAME = "tipoObra";

    private final TipoObraRepository tipoObraRepository;

    public TipoObraResource(TipoObraRepository tipoObraRepository) {
        this.tipoObraRepository = tipoObraRepository;
    }

    /**
     * POST  /tipoObra : Create a new tipoObra.
     *
     * @param tipoObra the tipoObra to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoObra, or with status 400 (Bad Request) if the tipoObra has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-obras")
    @Timed
    public ResponseEntity<TipoObra> createTipoObra(@Valid @RequestBody TipoObra tipoObra) throws URISyntaxException {
        log.debug("REST request to save Tipo Obra : {}", tipoObra);
        if (tipoObra.getId() != null) {
            throw new BadRequestAlertException("A new tipo obra cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoObra result = tipoObraRepository.save(tipoObra);
        return ResponseEntity.created(new URI("/api/tipo-obras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);

    }

    /**
     * PUT  /tipoObras : Updates an existing tipoObra.
     *
     * @param tipoObra the tipoObra to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoObra,
     * or with status 400 (Bad Request) if the tipoObra is not valid,
     * or with status 500 (Internal Server Error) if the tipoObra couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-obras")
    @Timed
    public ResponseEntity<TipoObra> updateTipoObra(@RequestBody TipoObra tipoObra) throws URISyntaxException {
        log.debug("REST request to update TipoObras : {}", tipoObra);
        if (tipoObra.getId() == null) {
            return createTipoObra(tipoObra);
        }
        TipoObra result = tipoObraRepository.save(tipoObra);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoObra.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipoObras : get all the tipo-obras.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tipoObras in body
     */
    @GetMapping("/tipo-obras")
    @Timed
    public ResponseEntity<List<TipoObra>> getAllTipoObras(Pageable pageable) {
        log.debug("REST request to get a page of Tipo Obras");
        Page<TipoObra> page = tipoObraRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tipo-obras");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /*
    *   Filtro de tipoObras atráves do atributo nome
    */
    @GetMapping("/tipo-obras/nomeFiltro")
    public ResponseEntity<List<TipoObra>> getByNome(@RequestParam(value = "nome") String nome, Pageable pageable) {
        Page<TipoObra> page = tipoObraRepository.buscarPorNome(nome, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/tipo-obras");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tipoObras/:id : get the "id" tipoObra.
     *
     * @param id the id of the tipoObra to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoObra, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-obras/{id}")
    @Timed
    public ResponseEntity<TipoObra> getTipoObra(@PathVariable Long id) {
        log.debug("REST request to get Tipo Obra : {}", id);
        TipoObra tipoObra = tipoObraRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tipoObra));
    }

    /**
     * DELETE  /tipoObras/:id : delete the "id" tipoObra.
     *
     * @param id the id of the tipoObra to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-obras/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoObra(@PathVariable Long id) {
        log.debug("REST request to delete TipoObra : {}", id);
        tipoObraRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
