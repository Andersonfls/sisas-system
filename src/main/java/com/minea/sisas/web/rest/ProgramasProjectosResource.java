package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.repository.ProgramasProjectosRepository;
import com.minea.sisas.service.ProgramasProjectosService;
import com.minea.sisas.service.dto.ComunaDTO;
import com.minea.sisas.service.dto.ProgramasProjectosLogDTO;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.ProgramasProjectosDTO;
import com.minea.sisas.service.dto.ProgramasProjectosCriteria;
import com.minea.sisas.service.ProgramasProjectosQueryService;
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

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing ProgramasProjectos.
 */
@RestController
@RequestMapping("/api")
public class ProgramasProjectosResource {

    private final Logger log = LoggerFactory.getLogger(ProgramasProjectosResource.class);

    private static final String ENTITY_NAME = "programasProjectos";

    private final ProgramasProjectosService programasProjectosService;

    private final ProgramasProjectosQueryService programasProjectosQueryService;

    private final ProgramasProjectosRepository programasProjectosRepository;

    public ProgramasProjectosResource(ProgramasProjectosService programasProjectosService, ProgramasProjectosQueryService programasProjectosQueryService, ProgramasProjectosRepository programasProjectosRepository) {
        this.programasProjectosService = programasProjectosService;
        this.programasProjectosQueryService = programasProjectosQueryService;
        this.programasProjectosRepository=programasProjectosRepository;
    }

    /**
     * POST  /programas-projectos : Create a new programasProjectos.
     *
     * @param programasProjectos the programasProjectosDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new programasProjectosDTO, or with status 400 (Bad Request) if the programasProjectos has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/programas-projectos")
    @Timed
    public ResponseEntity<ProgramasProjectos> createProgramasProjectos(@Valid @RequestBody ProgramasProjectos programasProjectos) throws URISyntaxException {
        log.debug("REST request to save ProgramasProjectos : {}", programasProjectos);
        if (programasProjectos.getId() != null) {
            throw new BadRequestAlertException("A new programasProjectos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        programasProjectos.setStatus(true);
        ProgramasProjectos result = programasProjectosService.save(programasProjectos);
        return ResponseEntity.created(new URI("/api/programas-projectos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /programas-projectos : Updates an existing programasProjectos.
     *
     * @param programasProjectos the programasProjectosDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated programasProjectosDTO,
     * or with status 400 (Bad Request) if the programasProjectosDTO is not valid,
     * or with status 500 (Internal Server Error) if the programasProjectosDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/programas-projectos")
    @Timed
    public ResponseEntity<ProgramasProjectos> updateProgramasProjectos(@Valid @RequestBody ProgramasProjectos programasProjectos) throws URISyntaxException {
        log.debug("REST request to update ProgramasProjectos : {}", programasProjectos);
        if (programasProjectos.getId() == null) {
            return createProgramasProjectos(programasProjectos);
        }
        programasProjectos.setStatus(true);
        ProgramasProjectos result = programasProjectosService.save(programasProjectos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, programasProjectos.getId().toString()))
            .body(result);
    }

    /**
     * GET  /programas-projectos : get all the programasProjectos.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of programasProjectos in body
     */
    @GetMapping("/programas-projectos")
    @Timed
    public ResponseEntity<List<ProgramasProjectosDTO>> getAllProgramasProjectos(Pageable pageable) {
        log.debug("REST request to get ProgramasProjectos by criteria: {}");
        Page<ProgramasProjectosDTO> page = programasProjectosService.findAllStatusTrue(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/programas-projectos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /*
     *  Filtro de usuários atráves do atributo nome
     */
    @GetMapping("/programas-projectos/nomeFiltro")
    public ResponseEntity<List<ProgramasProjectos>> getByNome(@RequestParam(value = "nome") String nome, Pageable pageable) {
        Page<ProgramasProjectos> page = programasProjectosRepository.buscarPorNome(nome, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/programas-projectos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /programas-projectos/:id : get the "id" programasProjectos.
     *
     * @param id the id of the programasProjectosDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the programasProjectosDTO, or with status 404 (Not Found)
     */
    @GetMapping("/programas-projectos/{id}")
    @Timed
    public ResponseEntity<ProgramasProjectos> getProgramasProjectos(@PathVariable Long id) {
        log.debug("REST request to get ProgramasProjectos : {}", id);
        ProgramasProjectos programasProjectos = programasProjectosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(programasProjectos));
    }

    @GetMapping("/programas-projectos/municipioFiltro")
    public ResponseEntity<List<ProgramasProjectos>> getByMunicipio(@RequestParam(value = "nome") String nome) {
        List<ProgramasProjectos> page = programasProjectosRepository.findAllByMunicipioNmMunicipioEquals(nome);
        return new ResponseEntity<>(page, null, HttpStatus.OK);
    }

    @GetMapping("/programas-projectos/provinciaFiltro")
    public ResponseEntity<List<ProgramasProjectos>> getByProvincia(@RequestParam(value = "nome") String nome) {
        List<ProgramasProjectos> page = programasProjectosRepository.findAllByProvinciaNmProvinciaEquals(nome);
        return new ResponseEntity<>(page, null, HttpStatus.OK);
    }

    @GetMapping("/programas-projectos/comunaFiltro")
    public ResponseEntity<List<ProgramasProjectos>> getByComuna(@RequestParam(value = "nome") String nome) {
        List<ProgramasProjectos> page = programasProjectosRepository.findAllByComunaNmComunaEquals(nome);
        return new ResponseEntity<>(page, null, HttpStatus.OK);
    }

    @GetMapping("/programas-projectos/periodoFiltro")
    public ResponseEntity<List<ProgramasProjectos>> getByPeriodo(@RequestParam(value = "dtInicial") String dtInicial, @RequestParam(value = "dtFinal") String dtFinal) throws ParseException {
        List<ProgramasProjectos> page = programasProjectosRepository.getAllBetweenDates(LocalDate.parse(dtInicial), LocalDate.parse(dtFinal));
        return new ResponseEntity<>(page, null, HttpStatus.OK);
    }

    /**
     * DELETE  /programas-projectos/:id : delete the "id" programasProjectos.
     *
     * @param id the id of the programasProjectosDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/programas-projectos/{id}")
    @Timed
    public ResponseEntity<Void> deleteProgramasProjectos(@PathVariable Long id) {
        log.debug("REST request to delete ProgramasProjectos : {}", id);
        ProgramasProjectos programasProjectos = this.programasProjectosRepository.findOne(id);
        if(Objects.nonNull(programasProjectos)) {
            programasProjectos.setStatus(false);
            this.programasProjectosRepository.save(programasProjectos);
        }
        // programasProjectosService.delete(id); regra mudada para exlusao lógica
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
