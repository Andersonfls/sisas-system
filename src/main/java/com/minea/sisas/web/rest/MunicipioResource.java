package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.Municipio;
import com.minea.sisas.repository.MunicipioRepository;
import com.minea.sisas.service.MunicipioQueryService;
import com.minea.sisas.service.MunicipioService;
import com.minea.sisas.service.dto.MunicipioCriteria;
import com.minea.sisas.service.dto.MunicipioDTO;
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
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing Municipio.
 */
@RestController
@RequestMapping("/api")
public class MunicipioResource {

    private final Logger log = LoggerFactory.getLogger(MunicipioResource.class);

    private static final String ENTITY_NAME = "municipio";

    private final MunicipioService municipioService;

    private final MunicipioQueryService municipioQueryService;

    private final MunicipioRepository municipioRepository;

    public MunicipioResource(MunicipioService municipioService, MunicipioQueryService municipioQueryService, MunicipioRepository municipioRepository) {
        this.municipioService = municipioService;
        this.municipioQueryService = municipioQueryService;
        this.municipioRepository= municipioRepository;
    }

    /**
     * POST  /municipios : Create a new municipio.
     *
     * @param municipioDTO the municipioDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new municipioDTO, or with status 400 (Bad Request) if the municipio has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/municipios")
    @Timed
    public ResponseEntity<MunicipioDTO> createMunicipio(@Valid @RequestBody MunicipioDTO municipioDTO) throws URISyntaxException {
        log.debug("REST request to save Municipio : {}", municipioDTO);
        if (municipioDTO.getId() != null) {
            throw new BadRequestAlertException("A new municipio cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MunicipioDTO result = municipioService.save(municipioDTO);
        return ResponseEntity.created(new URI("/api/municipios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /municipios : Updates an existing municipio.
     *
     * @param municipioDTO the municipioDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated municipioDTO,
     * or with status 400 (Bad Request) if the municipioDTO is not valid,
     * or with status 500 (Internal Server Error) if the municipioDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/municipios")
    @Timed
    public ResponseEntity<MunicipioDTO> updateMunicipio(@Valid @RequestBody MunicipioDTO municipioDTO) throws URISyntaxException {
        log.debug("REST request to update Municipio : {}", municipioDTO);
        if (municipioDTO.getId() == null) {
            return createMunicipio(municipioDTO);
        }
        MunicipioDTO result = municipioService.save(municipioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, municipioDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /municipios : get all the municipios.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of municipios in body
     */
    @GetMapping("/municipios")
    @Timed
    public ResponseEntity<List<MunicipioDTO>> getAllMunicipios(MunicipioCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Municipios by criteria: {}", criteria);
        Page<MunicipioDTO> page = municipioQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/municipios");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/municipios/nomeFiltro")
    public ResponseEntity<List<Municipio>> getByNome(@RequestParam(value = "nome") String nome, Pageable pageable) {
        List<Municipio> page = municipioRepository.buscarPorNome(nome);
     //   HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(null, "/api/sistema-aguas");
        return new ResponseEntity<>(page, null, HttpStatus.OK);
    }

    /**
     * GET  /municipios/:id : get the "id" municipio.
     *
     * @param id the id of the municipioDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the municipioDTO, or with status 404 (Not Found)
     */
    @GetMapping("/municipios/{id}")
    @Timed
    public ResponseEntity<MunicipioDTO> getMunicipio(@PathVariable Long id) {
        log.debug("REST request to get Municipio : {}", id);
        MunicipioDTO municipioDTO = municipioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(municipioDTO));
    }

    @GetMapping("/municipios-nome/{nome}")
    @Timed
    public ResponseEntity<MunicipioDTO> getMunicipioByName(@PathVariable String nome) {
        log.debug("REST request to get Municipio : {}", nome);
        MunicipioDTO municipioDTO = municipioService.findOneByName(nome);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(municipioDTO));
    }

    @GetMapping("/municipios/municipioByProvincia")
    public List<Municipio> getAllMunicipioByProvincia(Long provinciaId) {
        log.debug("REST request to get all Disciplina");
        return municipioRepository.findByProvinciaId(provinciaId);
    }

    /**
     * DELETE  /municipios/:id : delete the "id" municipio.
     *
     * @param id the id of the municipioDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/municipios/{id}")
    @Timed
    public ResponseEntity<Void> deleteMunicipio(@PathVariable Long id) {
        log.debug("REST request to delete Municipio : {}", id);
        municipioService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
