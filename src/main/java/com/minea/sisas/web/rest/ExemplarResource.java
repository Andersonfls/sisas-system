package com.minea.sisas.web.rest;


import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.Espaco;
import com.minea.sisas.domain.Exemplar;
import com.minea.sisas.domain.enumeration.StatusExemplar;
import com.minea.sisas.repository.EspacoRepository;
import com.minea.sisas.repository.ExemplarRepository;
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
public class ExemplarResource {

    private final Logger log = LoggerFactory.getLogger(ExemplarResource.class);

    private static final String ENTITY_NAME = "exemplar";

    private final ExemplarRepository exemplarRepository;

    private final EspacoRepository espacoRepository;

    public ExemplarResource(ExemplarRepository exemplarRepository, EspacoRepository espacoRepository) {
        this.exemplarRepository = exemplarRepository;
        this.espacoRepository = espacoRepository;
    }

    /**
     * POST  /exemplar : Create a new exemplar.
     *
     * @param exemplar the exemplar to create
     * @return the ResponseEntity with status 201 (Created) and with body the new exemplar, or with status 400 (Bad Request) if the exemplar has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/exemplares")
    @Timed
    public ResponseEntity<Exemplar> createExemplar(@Valid @RequestBody Exemplar exemplar) throws URISyntaxException {
        log.debug("REST request to save Exemplar : {}", exemplar);
        if (exemplar.getId() != null) {
            throw new BadRequestAlertException("A new exemplar cannot already have an ID", ENTITY_NAME, "idexists");
        }

        exemplar.setStatus(StatusExemplar.DISPONIVEL);

        String nomeEspaco = null;

        if(exemplar.getEspaco().getCodEspacoPai() == null) {
            nomeEspaco = exemplar.getEspaco().getNome();
            exemplar.setNomeLocalizacao(nomeEspaco);
        } else {
            nomeEspaco = formarPai(exemplar.getEspaco());
            exemplar.setNomeLocalizacao(nomeEspaco);
        }

        Exemplar result = exemplarRepository.save(exemplar);
        return ResponseEntity.created(new URI("/api/exemplar/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    public String formarPai(Espaco espaco) {

        Espaco espaco2 = espaco;
        String nome = "";
        Boolean teste = true;
        Long idPai;

        while(teste) {
            idPai = espaco2.getCodEspacoPai();
            if(idPai != null) {
                nome =  espaco2.getNome() + "-" + nome;
                espaco2 = espacoRepository.findOne(idPai);
            } else {
                teste = false;
                nome = espaco2.getNome() + "-" + nome;
            }
        }

        return nome;
    }

    /**
     * PUT  /exemplares : Updates an existing exemplar.
     *
     * @param exemplar the exemplar to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated exemplar,
     * or with status 400 (Bad Request) if the exemplar is not valid,
     * or with status 500 (Internal Server Error) if the autor couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/exemplares")
    @Timed
    public ResponseEntity<Exemplar> updateExemplar(@RequestBody Exemplar exemplar) throws URISyntaxException {
        log.debug("REST request to update exemplar : {}", exemplar);
        if (exemplar.getId() == null) {
            return createExemplar(exemplar);
        }

        String nomeEspaco = null;

        if(exemplar.getEspaco().getCodEspacoPai() == null) {
            nomeEspaco = exemplar.getEspaco().getNome();
            exemplar.setNomeLocalizacao(nomeEspaco);
        } else {
            nomeEspaco = formarPai(exemplar.getEspaco());
            exemplar.setNomeLocalizacao(nomeEspaco);
        }

        Exemplar result = exemplarRepository.save(exemplar);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, exemplar.getId().toString()))
            .body(result);
    }

    /**
     * GET  /exemplares : get all the exemplares.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of exemplar in body
     */
    @GetMapping("/exemplares")
    @Timed
    public ResponseEntity<List<Exemplar>> getAllExemplar(Pageable pageable) {
        log.debug("REST request to get all Exemplar");
        Page<Exemplar> page = exemplarRepository.findAll((pageable));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/exemplar");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /exemplares : get all the exemplares.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of exemplar in body
     */
    @GetMapping("/exemplares/disponivel")
    @Timed
    public ResponseEntity<List<Exemplar>> getAllExemplarDisponivel(Pageable pageable) {
        log.debug("REST request to get all Exemplar");
        Page<Exemplar> page = exemplarRepository.exemplarDisponivel((pageable));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/exemplar");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /autorObras : get all the exemplares.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of autorObras in body
     */
    @GetMapping("/exemplares/obraId/{id}")
    @Timed
    public List<Exemplar> getAllExemplaresObraId(@PathVariable Long id) {
        log.debug("REST request to get all obraId by Status: {}", id);
        return exemplarRepository.findByObraId(id);
    }

    /**
     * GET  /autorObras : get all the exemplares.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of autorObras in body
     */
    @GetMapping("/exemplares/page")
    @Timed
    public ResponseEntity<List<Exemplar>> getAllExemplaresObraIdPage(Long obraId, Pageable pageable) {
        log.debug("REST request to get all obraId by Status: {}", obraId);
        Page<Exemplar> page = exemplarRepository.findByObraIdPage(obraId,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/exemplar");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /exemplares/:id : get the "id" exemplar.
     *
     * @param id the id of the autor to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the exemplar, or with status 404 (Not Found)
     */
    @GetMapping("/exemplares/{id}")
    @Timed
    public ResponseEntity<Exemplar> getExemplar(@PathVariable Long id) {
        log.debug("REST request to get Exemplar : {}", id);
        Exemplar exemplar = exemplarRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(exemplar));
    }

    /**
     * DELETE  /exemplares/:id : delete the "id" exemplar.
     *
     * @param id the id of the exemplar to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/exemplares/{id}")
    @Timed
    public ResponseEntity<Void> deleteExemplar(@PathVariable Long id) {
        log.debug("REST request to delete Exemplar : {}", id);
        exemplarRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
