package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.Especialidades;
import com.minea.sisas.repository.EspecialidadesRepository;
import com.minea.sisas.security.AuthoritiesConstants;
import com.minea.sisas.service.EspecialidadesService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.EspecialidadesDTO;
import com.minea.sisas.service.dto.EspecialidadesCriteria;
import com.minea.sisas.service.EspecialidadesQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Especialidades.
 */
@RestController
@RequestMapping("/api")
public class EspecialidadesResource {

    private final Logger log = LoggerFactory.getLogger(EspecialidadesResource.class);

    private static final String ENTITY_NAME = "especialidades";

    private final EspecialidadesService especialidadesService;

    private final EspecialidadesQueryService especialidadesQueryService;

    private final EspecialidadesRepository especialidadesRepository;

    public EspecialidadesResource(EspecialidadesService especialidadesService, EspecialidadesQueryService especialidadesQueryService, EspecialidadesRepository especialidadesRepository) {
        this.especialidadesService = especialidadesService;
        this.especialidadesQueryService = especialidadesQueryService;
        this.especialidadesRepository= especialidadesRepository;
    }

    /**
     * POST  /especialidades : Create a new especialidades.
     *
     * @param especialidadesDTO the especialidadesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new especialidadesDTO, or with status 400 (Bad Request) if the especialidades has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/especialidades")
    @Timed
    public ResponseEntity<EspecialidadesDTO> createEspecialidades(@Valid @RequestBody EspecialidadesDTO especialidadesDTO) throws URISyntaxException {
        log.debug("REST request to save Especialidades : {}", especialidadesDTO);
        if (especialidadesDTO.getId() != null) {
            throw new BadRequestAlertException("A new especialidades cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EspecialidadesDTO result = especialidadesService.save(especialidadesDTO);
        return ResponseEntity.created(new URI("/api/especialidades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /especialidades : Updates an existing especialidades.
     *
     * @param especialidadesDTO the especialidadesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated especialidadesDTO,
     * or with status 400 (Bad Request) if the especialidadesDTO is not valid,
     * or with status 500 (Internal Server Error) if the especialidadesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/especialidades")
    @Timed
    public ResponseEntity<EspecialidadesDTO> updateEspecialidades(@Valid @RequestBody EspecialidadesDTO especialidadesDTO) throws URISyntaxException {
        log.debug("REST request to update Especialidades : {}", especialidadesDTO);
        if (especialidadesDTO.getId() == null) {
            return createEspecialidades(especialidadesDTO);
        }
        EspecialidadesDTO result = especialidadesService.save(especialidadesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, especialidadesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /especialidades : get all the especialidades.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of especialidades in body
     */
    @GetMapping("/especialidades")
    @Timed
    public ResponseEntity<List<EspecialidadesDTO>> getAllEspecialidadess(EspecialidadesCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Especialidadess by criteria: {}", criteria);
        Page<EspecialidadesDTO> page = especialidadesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/especialidades");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /*
     *  Filtro de usuários atráves do atributo nome
     */
    @GetMapping("/especialidades/nomeFiltro")
    public ResponseEntity<List<EspecialidadesDTO>> getByNome(@RequestParam(value = "nome") String nome, Pageable pageable) {
        Page<EspecialidadesDTO> page = especialidadesRepository.buscarPorNome(nome, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/especialidades");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    /**
     * GET  /especialidades/:id : get the "id" especialidades.
     *
     * @param id the id of the especialidadesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the especialidadesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/especialidades/{id}")
    @Timed
    public ResponseEntity<Especialidades> getEspecialidades(@PathVariable Long id) {
        log.debug("REST request to get Especialidades : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(especialidadesService.findOne(id)));
    }

    /**
     * DELETE  /especialidades/:id : delete the "id" especialidades.
     *
     * @param id the id of the especialidadesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/especialidades/{id}")
    @Timed
    public ResponseEntity<Void> deleteEspecialidades(@PathVariable Long id) {
        log.debug("REST request to delete Especialidades : {}", id);
        especialidadesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
