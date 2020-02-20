package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.ArquivosPortal;
import com.minea.sisas.service.ArquivosPortalService;
import com.minea.sisas.service.dto.ArquivosPortalDTO;
import com.minea.sisas.web.rest.errors.BadRequestAlertException;
import com.minea.sisas.web.rest.util.HeaderUtil;
import com.minea.sisas.web.rest.util.PaginationUtil;
import com.minea.sisas.web.rest.util.TipoArquivoEnum;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ArquivosPortal.
 */
@RestController
@RequestMapping("/api")
public class ArquivosPortalResource {

    private final Logger log = LoggerFactory.getLogger(ArquivosPortalResource.class);

    private static final String ENTITY_NAME = "arquivosPortal";

    private final ArquivosPortalService arquivosPortalService;

    public ArquivosPortalResource(ArquivosPortalService arquivosPortalService) {
        this.arquivosPortalService = arquivosPortalService;
    }

    /**
     * POST  /arquivosPortal : Create a new arquivosPortal.
     *
     * @param arquivosPortal the banner to create
     * @return the ResponseEntity with status 201 (Created) and with body the new banner, or with status 400 (Bad Request) if the banner has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/arquivos-portals")
    @Timed
    public ResponseEntity<ArquivosPortal> createArquivo(@RequestBody ArquivosPortalDTO arquivosPortal) throws URISyntaxException {
        log.debug("REST request to save ArquivosPortal : {}", arquivosPortal);
        if (arquivosPortal.getId() != null) {
            throw new BadRequestAlertException("A new ArquivosPortal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        arquivosPortal.setDataInclusao(LocalDate.now());
        ArquivosPortal result = arquivosPortalService.save(arquivosPortal);
        return ResponseEntity.created(new URI("/api/arquivosPortals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /banners : Updates an existing Banner.
     *
     * @param arquivosPortal the Banner to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated Banner,
     * or with status 400 (Bad Request) if the Banner is not valid,
     * or with status 500 (Internal Server Error) if the Banner couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/arquivos-portals")
    @Timed
    public ResponseEntity<ArquivosPortal> updateArquivo(@RequestBody ArquivosPortalDTO arquivosPortal) throws URISyntaxException {
        log.debug("REST request to update Banner : {}", arquivosPortal);
        if (arquivosPortal.getId() == null) {
            return createArquivo(arquivosPortal);
        }
        arquivosPortal.setDataAlteracao(LocalDate.now());
        ArquivosPortal result = arquivosPortalService.save(arquivosPortal);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, arquivosPortal.getId().toString()))
            .body(result);
    }

    /**
     * GET  /banners : get all the Banners.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of Banners in body
     */
    @GetMapping("/arquivos-portals")
    @Timed
    public ResponseEntity<List<ArquivosPortal>> getAllArquivos(Pageable pageable) {
        log.debug("REST request to get a page of ArquivosPortals");
        return new ResponseEntity<>(this.arquivosPortalService.findAll(pageable), null, HttpStatus.OK);
    }

    @GetMapping("/arquivos-portals/publicacoes")
    @Timed
    public ResponseEntity<List<ArquivosPortalDTO>> getAllArquivosPublicacoes() {
        log.debug("REST request to get a page of ArquivosPortals");
        return new ResponseEntity<>(this.arquivosPortalService.findAllDto(TipoArquivoEnum.PUBLICACAO.getCodigo()), null, HttpStatus.OK);
    }

    @GetMapping("/arquivos-portals/publicacoes-inicial")
    @Timed
    public ResponseEntity<List<ArquivosPortalDTO>> getAllArquivosPublicacoesInicial() {
        log.debug("REST request to get a page of ArquivosPortals");
        return new ResponseEntity<>(this.arquivosPortalService.findAllDto(TipoArquivoEnum.PUBLICACAO_INICIAL.getCodigo()), null, HttpStatus.OK);
    }

    @GetMapping("/arquivos-portals/projectos")
    @Timed
    public ResponseEntity<List<ArquivosPortalDTO>> getAllArquivosProjectos() {
        log.debug("REST request to get a page of ArquivosPortals");
        return new ResponseEntity<>(this.arquivosPortalService.findAllDto(TipoArquivoEnum.PROJECTOS.getCodigo()), null, HttpStatus.OK);
    }
    /**
     * GET  /arquivosPortals/:id : get the "id" Banner.
     *
     * @param id the id of the Banner to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the Banner, or with status 404 (Not Found)
     */
    @GetMapping("/arquivos-portals/{id}")
    @Timed
    public ResponseEntity<ArquivosPortalDTO> getArquivo(@PathVariable Long id) {
        log.debug("REST request to get Banner : {}", id);
        ArquivosPortalDTO banner = arquivosPortalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(banner));
    }

    /**
     * DELETE  /arquivosPortals/:id : delete the "id" Banner.
     *
     * @param id the id of the Banner to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/arquivos-portals/{id}")
    @Timed
    public ResponseEntity<Void> deleteArquivo(@PathVariable Long id) {
        log.debug("REST request to delete Banner : {}", id);
        arquivosPortalService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
