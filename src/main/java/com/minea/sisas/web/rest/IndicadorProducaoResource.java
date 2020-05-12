package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.IndicadorProducao;
import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.repository.IndicadorProducaoRepository;
import com.minea.sisas.service.IndicadorProducaoService;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.service.dto.IndicadorProducaoDTO;
import com.minea.sisas.service.dto.IndicadorProducaoCriteria;
import com.minea.sisas.service.IndicadorProducaoQueryService;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing IndicadorProducao.
 */
@RestController
@RequestMapping("/api")
public class IndicadorProducaoResource {

    private final Logger log = LoggerFactory.getLogger(IndicadorProducaoResource.class);

    private static final String ENTITY_NAME = "indicadorProducao";

    private final IndicadorProducaoService indicadorProducaoService;

    private final IndicadorProducaoQueryService indicadorProducaoQueryService;

    private final IndicadorProducaoRepository indicadorProducaoRepository;

    public IndicadorProducaoResource(IndicadorProducaoService indicadorProducaoService, IndicadorProducaoQueryService indicadorProducaoQueryService, IndicadorProducaoRepository indicadorProducaoRepository) {
        this.indicadorProducaoService = indicadorProducaoService;
        this.indicadorProducaoQueryService = indicadorProducaoQueryService;
        this.indicadorProducaoRepository=indicadorProducaoRepository;
    }

    /**
     * POST  /indicador-producaos : Create a new indicadorProducao.
     *
     * @param indicadorProducaoDTO the indicadorProducaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new indicadorProducaoDTO, or with status 400 (Bad Request) if the indicadorProducao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/indicador-producaos")
    @Timed
    public ResponseEntity<IndicadorProducaoDTO> createIndicadorProducao(@Valid @RequestBody IndicadorProducaoDTO indicadorProducaoDTO) throws URISyntaxException {
        log.debug("REST request to save IndicadorProducao : {}", indicadorProducaoDTO);
        if (indicadorProducaoDTO.getId() != null) {
            throw new BadRequestAlertException("A new indicadorProducao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        indicadorProducaoDTO.setStatus(true);
        indicadorProducaoDTO.setDtUltimaAlteracao(LocalDate.now());
        IndicadorProducaoDTO result = indicadorProducaoService.save(indicadorProducaoDTO);
        return ResponseEntity.created(new URI("/api/indicador-producaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /indicador-producaos : Updates an existing indicadorProducao.
     *
     * @param indicadorProducaoDTO the indicadorProducaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated indicadorProducaoDTO,
     * or with status 400 (Bad Request) if the indicadorProducaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the indicadorProducaoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/indicador-producaos")
    @Timed
    public ResponseEntity<IndicadorProducaoDTO> updateIndicadorProducao(@Valid @RequestBody IndicadorProducaoDTO indicadorProducaoDTO) throws URISyntaxException {
        log.debug("REST request to update IndicadorProducao : {}", indicadorProducaoDTO);
        if (indicadorProducaoDTO.getId() == null) {
            return createIndicadorProducao(indicadorProducaoDTO);
        }
        indicadorProducaoDTO.setStatus(true);
        IndicadorProducaoDTO result = indicadorProducaoService.save(indicadorProducaoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, indicadorProducaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /indicador-producaos : get all the indicadorProducaos.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of indicadorProducaos in body
     */
    @GetMapping("/indicador-producaos")
    @Timed
    public ResponseEntity<List<IndicadorProducaoDTO>> getAllIndicadorProducaos(IndicadorProducaoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get IndicadorProducaos by criteria: {}", criteria);
        Page<IndicadorProducaoDTO> page = indicadorProducaoService.findAllStatusTrue(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/indicador-producaos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /*
     *  Filtro de usuários atráves do atributo nome
     */
    @GetMapping("/indicador-producaos/nomeFiltro")
    public ResponseEntity<List<IndicadorProducaoDTO>> getByNome(@RequestParam(value = "nome") String nome, Pageable pageable) {
        Page<IndicadorProducaoDTO> page = indicadorProducaoRepository.buscarPorNome(nome, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/indicador-producao");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    /**
     * GET  /indicador-producaos/:id : get the "id" indicadorProducao.
     *
     * @param id the id of the indicadorProducaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the indicadorProducaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/indicador-producaos/{id}")
    @Timed
    public ResponseEntity<IndicadorProducaoDTO> getIndicadorProducao(@PathVariable Long id) {
        log.debug("REST request to get IndicadorProducao : {}", id);
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(indicadorProducaoDTO));
    }

    @GetMapping("/indicador-producaos/last")
    @Timed
    public ResponseEntity<IndicadorProducaoDTO> getLastIndicadorProducao() {
        log.debug("REST request to get Last IndicadorProducao");
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoService.findLast();
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(indicadorProducaoDTO));
    }

    @GetMapping("/indicador-producaos/provinciaFiltro")
    public ResponseEntity<List<IndicadorProducao>> getByProvincia(@RequestParam(value = "nome") String nome) {
        List<IndicadorProducao> page = indicadorProducaoRepository.findAllByProvinciaNmProvinciaEquals(nome);
        return new ResponseEntity<>(page, null, HttpStatus.OK);
    }

    @GetMapping("/indicador-producaos/anoFiltro")
    public ResponseEntity<List<IndicadorProducao>> getByAno(@RequestParam(value = "ano") Integer ano) {
        List<IndicadorProducao> page = indicadorProducaoRepository.getAllByYear(ano);
        return new ResponseEntity<>(page, null, HttpStatus.OK);
    }

    /**
     * DELETE  /indicador-producaos/:id : delete the "id" indicadorProducao.
     *
     * @param id the id of the indicadorProducaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/indicador-producaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteIndicadorProducao(@PathVariable Long id) {
        log.debug("REST request to delete IndicadorProducao : {}", id);
        IndicadorProducao indicadorProducao = this.indicadorProducaoRepository.findByIdAndStatusIsTrue(id);
        if (Objects.nonNull(indicadorProducao)) {
            indicadorProducao.setStatus(false);
            this.indicadorProducaoRepository.save(indicadorProducao);
        }
        // indicadorProducaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
