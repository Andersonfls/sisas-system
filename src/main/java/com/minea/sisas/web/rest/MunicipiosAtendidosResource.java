package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.service.MunicipiosAtendidosService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.MunicipiosAtendidosDTO;
import com.minea.sisas.service.dto.MunicipiosAtendidosCriteria;
import com.minea.sisas.service.MunicipiosAtendidosQueryService;
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
 * REST controller for managing MunicipiosAtendidos.
 */
@RestController
@RequestMapping("/api")
public class MunicipiosAtendidosResource {

    private final Logger log = LoggerFactory.getLogger(MunicipiosAtendidosResource.class);

    private static final String ENTITY_NAME = "municipiosAtendidos";

    private final MunicipiosAtendidosService municipiosAtendidosService;

    private final MunicipiosAtendidosQueryService municipiosAtendidosQueryService;

    public MunicipiosAtendidosResource(MunicipiosAtendidosService municipiosAtendidosService, MunicipiosAtendidosQueryService municipiosAtendidosQueryService) {
        this.municipiosAtendidosService = municipiosAtendidosService;
        this.municipiosAtendidosQueryService = municipiosAtendidosQueryService;
    }

    /**
     * POST  /municipios-atendidos : Create a new municipiosAtendidos.
     *
     * @param municipiosAtendidosDTO the municipiosAtendidosDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new municipiosAtendidosDTO, or with status 400 (Bad Request) if the municipiosAtendidos has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/municipios-atendidos")
    @Timed
    public ResponseEntity<MunicipiosAtendidosDTO> createMunicipiosAtendidos(@Valid @RequestBody MunicipiosAtendidosDTO municipiosAtendidosDTO) throws URISyntaxException {
        log.debug("REST request to save MunicipiosAtendidos : {}", municipiosAtendidosDTO);
        if (municipiosAtendidosDTO.getId() != null) {
            throw new BadRequestAlertException("A new municipiosAtendidos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MunicipiosAtendidosDTO result = municipiosAtendidosService.save(municipiosAtendidosDTO);
        return ResponseEntity.created(new URI("/api/municipios-atendidos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /municipios-atendidos : Updates an existing municipiosAtendidos.
     *
     * @param municipiosAtendidosDTO the municipiosAtendidosDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated municipiosAtendidosDTO,
     * or with status 400 (Bad Request) if the municipiosAtendidosDTO is not valid,
     * or with status 500 (Internal Server Error) if the municipiosAtendidosDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/municipios-atendidos")
    @Timed
    public ResponseEntity<MunicipiosAtendidosDTO> updateMunicipiosAtendidos(@Valid @RequestBody MunicipiosAtendidosDTO municipiosAtendidosDTO) throws URISyntaxException {
        log.debug("REST request to update MunicipiosAtendidos : {}", municipiosAtendidosDTO);
        if (municipiosAtendidosDTO.getId() == null) {
            return createMunicipiosAtendidos(municipiosAtendidosDTO);
        }
        MunicipiosAtendidosDTO result = municipiosAtendidosService.save(municipiosAtendidosDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, municipiosAtendidosDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /municipios-atendidos : get all the municipiosAtendidos.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of municipiosAtendidos in body
     */
    @GetMapping("/municipios-atendidos")
    @Timed
    public ResponseEntity<List<MunicipiosAtendidosDTO>> getAllMunicipiosAtendidos(MunicipiosAtendidosCriteria criteria, Pageable pageable) {
        log.debug("REST request to get MunicipiosAtendidos by criteria: {}", criteria);
        Page<MunicipiosAtendidosDTO> page = municipiosAtendidosQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/municipios-atendidos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /municipios-atendidos/:id : get the "id" municipiosAtendidos.
     *
     * @param id the id of the municipiosAtendidosDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the municipiosAtendidosDTO, or with status 404 (Not Found)
     */
    @GetMapping("/municipios-atendidos/{id}")
    @Timed
    public ResponseEntity<MunicipiosAtendidosDTO> getMunicipiosAtendidos(@PathVariable Long id) {
        log.debug("REST request to get MunicipiosAtendidos : {}", id);
        MunicipiosAtendidosDTO municipiosAtendidosDTO = municipiosAtendidosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(municipiosAtendidosDTO));
    }

    /**
     * DELETE  /municipios-atendidos/:id : delete the "id" municipiosAtendidos.
     *
     * @param id the id of the municipiosAtendidosDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/municipios-atendidos/{id}")
    @Timed
    public ResponseEntity<Void> deleteMunicipiosAtendidos(@PathVariable Long id) {
        log.debug("REST request to delete MunicipiosAtendidos : {}", id);
        municipiosAtendidosService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
