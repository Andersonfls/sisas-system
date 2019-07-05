package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.Obra;
import com.minea.sisas.domain.TipoObra;
import com.minea.sisas.repository.ObraRepository;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ObraResource {

    private final Logger log = LoggerFactory.getLogger(ObraResource.class);

    private static final String ENTITY_NAME = "obra";

    private final ObraRepository obraRepository;

    public ObraResource(ObraRepository obraRepository) {
        this.obraRepository = obraRepository;
    }

    /**
     * POST  /obra : Create a new obra.
     *
     * @param obra the obra to create
     * @return the ResponseEntity with status 201 (Created) and with body the new obra, or with status 400 (Bad Request) if the obra has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/obras")
    @Timed
    public ResponseEntity<Obra> createObra(@Valid @RequestBody Obra obra) throws URISyntaxException {
        log.debug("REST request to save Obra : {}", obra);
        if (obra.getId() != null) {
            throw new BadRequestAlertException("A new obra cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Obra result = obraRepository.save(obra);
        return ResponseEntity.created(new URI("/api/obra/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);

    }

    /**
     * PUT  /obra : Updates an existing obra.
     *
     * @param obra the obra to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated obra,
     * or with status 400 (Bad Request) if the obra is not valid,
     * or with status 500 (Internal Server Error) if the obra couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/obras")
    @Timed
    public ResponseEntity<Obra> updateObra(@RequestBody Obra obra) throws URISyntaxException {
        log.debug("REST request to update Obras : {}", obra);
        if (obra.getId() == null) {
            return createObra(obra);
        }
        Obra obraImg = obraRepository.findOne(obra.getId());

        if(obraImg.getNomeArquivo() != null) {
            obra.setNomeArquivo(obraImg.getNomeArquivo());
        }
        Obra result = obraRepository.save(obra);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, obra.getId().toString()))
            .body(result);
    }

    /**
     * GET  /obra : get all the exemplares.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of autorObras in body
     */
    @GetMapping("/obras/lista")
    @Timed
    public List<Obra> getAllObraLista() {
        log.debug("REST request to get all obraId by Status: {}");
        return obraRepository.findByObraLista();
    }

    /**
     * GET  /obras : get all the obras.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of obras in body
     */
    @GetMapping("/obras")
    @Timed
    public ResponseEntity<List<Obra>> getAllObras(Pageable pageable) {
        log.debug("REST request to get all Obras");
        Page<Obra> page = obraRepository.findAll((pageable));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/obra");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /*
    *  Método para contar a quantidade de obras.
    */
    @GetMapping("/obras/dashboard")
    @Timed
    public ResponseEntity<Integer> getObras() {
        final Integer page = obraRepository.buscarObra();;
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(page));
    }

    /*
     *  Filtro de obras automático, atráves do nome
     */
//    @GetMapping("/obras/nomeFiltro")
//    public ResponseEntity<List<Obra>> getByNome(@RequestParam(value = "nome") String nome, Pageable pageable) {
//        if (!nome.isEmpty()) {
//            Page<Obra> page = obraRepository.buscarPorNome(("%"+nome+"%"), pageable);
//            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/obras");
//            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
//        }
//        return null;
//    }

    @GetMapping("/obras/nomeFiltro")
    public ResponseEntity<List<Obra>> getByNome(@RequestParam(value = "nome") String nome, Pageable pageable) {
        if (!nome.isEmpty() && !nome.equals("")) {
            List<Obra> obras = this.obraRepository
                .buscarPorNome(("%"+nome.toLowerCase()+"%")).stream()
                .map(o -> new Obra(((BigDecimal) o[0]).longValue(), ((String) o[1]),
                    ((String) o[2]),  (o[3]).toString().equalsIgnoreCase("1"), new TipoObra( ((BigDecimal) o[4]).longValue(), ((String) o[5])) ))
                .collect(Collectors.toList());
            return new ResponseEntity<>(obras, null, HttpStatus.OK);
        }
        return null;
    }

    /**
     * GET  /obras/:id : get the "id" obra.
     *
     * @param id the id of the obra to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the obra, or with status 404 (Not Found)
     */
    @GetMapping("/obras/{id}")
    @Timed
    public ResponseEntity<Obra> getObra(@PathVariable Long id) {
        log.debug("REST request to get Obra : {}", id);
        Obra obra = obraRepository.findOne(id);

        if(obra.getNomeArquivo() != null) {
            String fileDownloadUri2 = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/downloadFile/")
                .path(StringUtils.cleanPath(obra.getNomeArquivo()))
                .toUriString();

            obra.setNomeDiretorio(fileDownloadUri2);
            obra.setNomeArquivo(obra.getNomeArquivo());
            obraRepository.save(obra);
        }

        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(obra));
    }

    /**
     * DELETE  /obras/:id : delete the "id" obra.
     *
     * @param id the id of the obra to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/obras/{id}")
    @Timed
    public ResponseEntity<Void> deleteObra(@PathVariable Long id) {
        log.debug("REST request to delete Obra : {}", id);
        obraRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
