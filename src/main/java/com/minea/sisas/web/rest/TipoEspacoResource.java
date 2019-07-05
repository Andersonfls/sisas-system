package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.TipoEspaco;
import com.minea.sisas.repository.TipoEspacoRepository;
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
public class TipoEspacoResource {

    private final Logger log = LoggerFactory.getLogger(TipoEspacoResource.class);

    private static final String ENTITY_NAME = "tipoEspaco";

    private final TipoEspacoRepository tipoEspacoRepository;

    public TipoEspacoResource(TipoEspacoRepository tipoEspacoRepository) {
        this.tipoEspacoRepository = tipoEspacoRepository;
    }

    /**
     * POST  /tipoEspacos : Create a new tipoEspacos.
     *
     * @param tipoEspaco the tipoEspaco to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoEspacos, or with status 400 (Bad Request) if the tipoEspacos has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipoEspacos")
    @Timed
    public ResponseEntity<TipoEspaco> createTipoEspaco(@Valid @RequestBody TipoEspaco tipoEspaco) throws URISyntaxException {
        log.debug("REST request to save TipoEspaco : {}", tipoEspaco);
        if (tipoEspaco.getId() != null) {
            throw new BadRequestAlertException("A new tipoEspaco cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoEspaco result = tipoEspacoRepository.save(tipoEspaco);
        return ResponseEntity.created(new URI("/api/tipo-espaco/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);

    }

    /**
     * PUT  /tipoEspaco : Updates an existing tipoEspaco.
     *
     * @param tipoEspaco the tipoEspaco to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoEspaco,
     * or with status 400 (Bad Request) if the tipoEspaco is not valid,
     * or with status 500 (Internal Server Error) if the tipoEspaco couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipoEspacos")
    @Timed
    public ResponseEntity<TipoEspaco> updateTipoEspaco(@RequestBody TipoEspaco tipoEspaco) throws URISyntaxException {
        log.debug("REST request to update TipoEspaco : {}", tipoEspaco);
        if (tipoEspaco.getId() == null) {
            return createTipoEspaco(tipoEspaco);
        }

        List<TipoEspaco> tipoEspacosFilhos = tipoEspacoRepository.findByTipoEspacoIdPai(tipoEspaco.getId());

        if(!tipoEspacosFilhos.isEmpty()) {
            for(int i = 0; i < tipoEspacosFilhos.size(); i ++) {
                tipoEspacosFilhos.get(i).setNomePai(tipoEspaco.getNome());
                tipoEspacoRepository.save(tipoEspacosFilhos.get(i));
            }
        }


        TipoEspaco result = tipoEspacoRepository.save(tipoEspaco);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoEspaco.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipoEspacos : get all the tipoEspacos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tipoEspacos in body
     */
    @GetMapping("/tipoEspacos")
    @Timed
    public ResponseEntity<List<TipoEspaco>> getAllTipoEspacos(Pageable pageable) {
        log.debug("REST request to get a page of Tipo Espacos");
        Page<TipoEspaco> page = tipoEspacoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tipo-espaco");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /*
      Filtro de tipo-espacos atráves do atributo nome.
   */
    @GetMapping("/tipoEspacos/nomeFiltro")
    public ResponseEntity<List<TipoEspaco>> getByNome(@RequestParam(value = "nome") String nome, Pageable pageable) {
        Page<TipoEspaco> page = tipoEspacoRepository.buscarPorNome(nome, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/tipo-espaco/nome");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tipoEspacos/:id : get the "id" tipoEspaco.
     *
     * @param id the id of the tipoEspaco to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoEspaco, or with status 404 (Not Found)
     */
    @GetMapping("/tipoEspacos/{id}")
    @Timed
    public ResponseEntity<TipoEspaco> getTipoEspaco(@PathVariable Long id) {
        log.debug("REST request to get Tipo Espaco : {}", id);
        TipoEspaco tipoEspaco = tipoEspacoRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tipoEspaco));
    }

    /**
     * DELETE  /tipoEspacos/:id : delete the "id" tipoEspaco.
     *
     * @param id the id of the tipoEspaco to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipoEspacos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoEspaco(@PathVariable Long id) {
        log.debug("REST request to delete TipoEspaco : {}", id);
        tipoEspacoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
