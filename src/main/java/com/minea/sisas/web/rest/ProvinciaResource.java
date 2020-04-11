package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.Comuna;
import com.minea.sisas.domain.Municipio;
import com.minea.sisas.domain.Provincia;
import com.minea.sisas.repository.ComunaRepository;
import com.minea.sisas.repository.MunicipioRepository;
import com.minea.sisas.repository.ProvinciaRepository;
import com.minea.sisas.security.AuthoritiesConstants;
import com.minea.sisas.service.ProvinciaService;
import com.minea.sisas.service.dto.*;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.ProvinciaQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.checkerframework.checker.units.qual.Time;
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

import java.util.*;
import java.util.stream.Collectors;

/**
 * REST controller for managing Provincia.
 */
@RestController
@RequestMapping("/api")
public class ProvinciaResource {

    private final Logger log = LoggerFactory.getLogger(ProvinciaResource.class);

    private static final String ENTITY_NAME = "provincia";

    private final ProvinciaService provinciaService;

    private final ProvinciaQueryService provinciaQueryService;

    private final ProvinciaRepository provinciaRepository;

    private final MunicipioRepository municipioRepository;

    private final ComunaRepository comunaRepository;

    public ProvinciaResource(ProvinciaService provinciaService, ProvinciaQueryService provinciaQueryService, ProvinciaRepository provinciaRepository,
                             MunicipioRepository municipioRepository, ComunaRepository comunaRepository) {
        this.provinciaService = provinciaService;
        this.provinciaQueryService = provinciaQueryService;
        this.provinciaRepository = provinciaRepository;
        this.municipioRepository = municipioRepository;
        this.comunaRepository = comunaRepository;
    }

    /**
     * POST  /provincias : Create a new provincia.
     *
     * @param provinciaDTO the provinciaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new provinciaDTO, or with status 400 (Bad Request) if the provincia has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/provincias")
    @Timed
    public ResponseEntity<ProvinciaDTO> createProvincia(@Valid @RequestBody ProvinciaDTO provinciaDTO) throws URISyntaxException {
        log.debug("REST request to save Provincia : {}", provinciaDTO);
        if (provinciaDTO.getId() != null) {
            throw new BadRequestAlertException("A new provincia cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProvinciaDTO result = provinciaService.save(provinciaDTO);
        return ResponseEntity.created(new URI("/api/provincias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /provincias : Updates an existing provincia.
     *
     * @param provinciaDTO the provinciaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated provinciaDTO,
     * or with status 400 (Bad Request) if the provinciaDTO is not valid,
     * or with status 500 (Internal Server Error) if the provinciaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/provincias")
    @Timed
    public ResponseEntity<ProvinciaDTO> updateProvincia(@Valid @RequestBody ProvinciaDTO provinciaDTO) throws URISyntaxException {
        log.debug("REST request to update Provincia : {}", provinciaDTO);
        if (provinciaDTO.getId() == null) {
            return createProvincia(provinciaDTO);
        }
        ProvinciaDTO result = provinciaService.save(provinciaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, provinciaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /provincias : get all the provincias.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of provincias in body
     */
    @GetMapping("/provincias")
    @Timed
    public ResponseEntity<List<ProvinciaDTO>> getAllProvincias(ProvinciaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Provincias by criteria: {}", criteria);
        Page<ProvinciaDTO> page = provinciaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/provincias");
        List<ProvinciaDTO> content = page.getContent();
        content = content.stream().sorted(Comparator.comparing(ProvinciaDTO::getNmProvincia)).collect(Collectors.toList());
        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }

    @GetMapping("/provincias/nivel-usuario")
    @Timed
    public ResponseEntity<List<ProvinciaDTO>> getAllProvinciasPorNivelUsuario(ProvinciaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Provincias by criteria: {}", criteria);
        Page<ProvinciaDTO> page = provinciaService.findAllPorNivel(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/provincias/nivel-usuario");
        List<ProvinciaDTO> content = page.getContent();
        content = content.stream().sorted(Comparator.comparing(ProvinciaDTO::getNmProvincia)).collect(Collectors.toList());
        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }
    /**
     * GET  /provincias/:id : get the "id" provincia.
     *
     * @param id the id of the provinciaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the provinciaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/provincias/{id}")
    @Timed
    public ResponseEntity<ProvinciaDTO> getProvincia(@PathVariable Long id) {
        log.debug("REST request to get Provincia : {}", id);
        ProvinciaDTO provinciaDTO = provinciaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(provinciaDTO));
    }

    @GetMapping("/provincias/relatorio-indicador-producao/{ano}")
    @Timed
    public ResponseEntity<List <IndicadorProducaoProvinciaDTO>> getNomeCampos(@PathVariable String ano) {
        log.debug("REST request to get Ano : {}", ano);
        List<IndicadorProducaoProvinciaDTO> listaDto = provinciaService.getNomeCampo(ano);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(listaDto));
    }

    /**
     * DELETE  /provincias/:id : delete the "id" provincia.
     *
     * @param id the id of the provinciaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/provincias/{id}")
    @Timed
    public ResponseEntity<Void> deleteProvincia(@PathVariable Long id) {
        log.debug("REST request to delete Provincia : {}", id);
        provinciaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
