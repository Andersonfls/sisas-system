package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.Espaco;
import com.minea.sisas.repository.EspacoRepository;
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
public class EspacoResource {

    private final Logger log = LoggerFactory.getLogger(EspacoResource.class);

    private static final String ENTITY_NAME = "espaco";

    private final EspacoRepository espacoRepository;

    public EspacoResource(EspacoRepository espacoRepository) {
        this.espacoRepository = espacoRepository;
    }


    /**
     * POST  /Espaco : Create a new espaco.
     *
     * @param espaco the espaco to create
     * @return the ResponseEntity with status 201 (Created) and with body the new espaco, or with status 400 (Bad Request) if the espaco has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/espacos")
    @Timed
    public ResponseEntity<Espaco> createEspaco(@Valid @RequestBody Espaco espaco) throws URISyntaxException {
        log.debug("REST request to save Espaco : {}", espaco);
        if (espaco.getId() != null) {
            throw new BadRequestAlertException("A new espaco cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Espaco result = espacoRepository.save(espaco);
        return ResponseEntity.created(new URI("/api/espaco/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);

    }

    /**
     * PUT  /espacos : Updates an existing espaco.
     *
     * @param espaco the espaco to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated espaco,
     * or with status 400 (Bad Request) if the espaco is not valid,
     * or with status 500 (Internal Server Error) if the espaco couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/espacos")
    @Timed
    public ResponseEntity<Espaco> updateEspaco(@RequestBody Espaco espaco) throws URISyntaxException {
        log.debug("REST request to update espaco : {}", espaco);
        if (espaco.getId() == null) {
            return createEspaco(espaco);
        }

        List<Espaco> espacosFilhos = espacoRepository.findByEspacoIdPai(espaco.getId());

        if(!espacosFilhos.isEmpty()) {
            for(int i = 0; i < espacosFilhos.size(); i ++) {
                espacosFilhos.get(i).setNomeEspacoPai(espaco.getNome());
                espacoRepository.save(espacosFilhos.get(i));
            }
        }

        Espaco result = espacoRepository.save(espaco);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, espaco.getId().toString()))
            .body(result);
    }

    /**
     * GET  /espacos : get all the espacos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of espacos in body
     */
    @GetMapping("/espacos")
    @Timed
    public ResponseEntity<List<Espaco>> getAllEspaco(Pageable pageable) {
        log.debug("REST request to get all Espaco");
        Page<Espaco> page = espacoRepository.findAll((pageable));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/espaco");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /autorObras : get all the exemplares.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of autorObras in body
     */
    @GetMapping("/espacos/idTipoEspacoPai/{id}")
    @Timed
    public ResponseEntity<List<Espaco>> getAllEspacosTipoEspacoPaiId(@PathVariable Long id, Pageable pageable) {
        log.debug("REST request to get all idTipoEspacoPai by Status: {}", id);
        Page<Espaco> page = espacoRepository.findByTipoEspacoPaiId(id, pageable);;
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/espaco");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /espacos/:id : get the "id" espaco.
     *
     * @param id the id of the espaco to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the espaco, or with status 404 (Not Found)
     */
    @GetMapping("/espacos/{id}")
    @Timed
    public ResponseEntity<Espaco> getEspacos(@PathVariable Long id) {
        log.debug("REST request to get Espaco : {}", id);
        Espaco espaco = espacoRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(espaco));
    }

    /**
     * DELETE  /espacos/:id : delete the "id" espaco.
     *
     * @param id the id of the espaco to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/espacos/{id}")
    @Timed
    public ResponseEntity<Void> deleteEspaco(@PathVariable Long id) {
        log.debug("REST request to delete Espaco : {}", id);
        espacoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
