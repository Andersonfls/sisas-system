package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.Emprestimo;
import com.minea.sisas.domain.Exemplar;
import com.minea.sisas.domain.Reserva;
import com.minea.sisas.domain.enumeration.StatusExemplar;
import com.minea.sisas.repository.EmprestimoRepository;
import com.minea.sisas.repository.ExemplarRepository;
import com.minea.sisas.repository.ReservaRepository;
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
public class EmprestimoResource {

    private final Logger log = LoggerFactory.getLogger(AutorResource.class);

    private static final String ENTITY_NAME = "emprestimo";

    private final EmprestimoRepository emprestimoRepository;

    private final ExemplarRepository exemplarRepository;

    private final ReservaRepository reservaRepository;

    public EmprestimoResource(EmprestimoRepository emprestimoRepository, ExemplarRepository exemplarRepository, ReservaRepository reservaRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.exemplarRepository = exemplarRepository;
        this.reservaRepository = reservaRepository;
    }

    /**
     * POST  /emprestimo : Create a new emprestimo.
     *
     * @param emprestimo the emprestimo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new emprestimo, or with status 400 (Bad Request) if the emprestimo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/emprestimos")
    @Timed
    public ResponseEntity<Emprestimo> createEmprestimo(@Valid @RequestBody Emprestimo emprestimo) throws URISyntaxException {
        log.debug("REST request to save Emprestimo : {}", emprestimo);
        if (emprestimo.getId() != null) {
            throw new BadRequestAlertException("A new emprestimo cannot already have an ID", ENTITY_NAME, "idexists");
        }

        emprestimo.setUserName(emprestimo.getUser().getNome());

        emprestimo.setStatus(true);

        Exemplar exemplar = exemplarRepository.findOne(emprestimo.getExemplar().getId());
        exemplar.setStatus(StatusExemplar.EMPRESTADO);
        exemplarRepository.save(exemplar);

        Emprestimo result = emprestimoRepository.save(emprestimo);
        return ResponseEntity.created(new URI("/api/emprestimo/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);

    }

    /**
     * PUT  /emprestimos : Updates an existing emprestimo.
     *
     * @param emprestimo the autor to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated autor,
     * or with status 400 (Bad Request) if the autor is not valid,
     * or with status 500 (Internal Server Error) if the autor couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/emprestimos")
    @Timed
    public ResponseEntity<Emprestimo> updateEmprestimo(@RequestBody Emprestimo emprestimo) throws URISyntaxException {
        log.debug("REST request to update autor : {}", emprestimo);
        if (emprestimo.getId() == null) {
            return createEmprestimo(emprestimo);
        }

        emprestimo.setUserName(emprestimo.getUser().getNome());

        Exemplar exemplar = exemplarRepository.findOne(emprestimo.getExemplar().getId());

        if (emprestimo.getStatus() == false) {
            List<Reserva> reservas = reservaRepository.findByObraId(exemplar.getObra().getId());

            if(!reservas.isEmpty()) {
                reservas.get(0).setExemplarId(exemplar.getId());
                reservaRepository.save(reservas.get(0));
                exemplar.setStatus(StatusExemplar.RESERVADO);
                exemplarRepository.save(exemplar);
            } else {
                exemplar.setStatus(StatusExemplar.DISPONIVEL);
                exemplarRepository.save(exemplar);
            }
        }
        if (emprestimo.getStatus() == true) {
            exemplar.setStatus(StatusExemplar.EMPRESTADO);
            exemplarRepository.save(exemplar);
        }

        Emprestimo result = emprestimoRepository.save(emprestimo);

        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, emprestimo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /autores : get all the emprestimos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of emprestimos in body
     */
    @GetMapping("/emprestimos")
    @Timed
    public ResponseEntity<List<Emprestimo>> getAllEmprestimos(Pageable pageable) {
        log.debug("REST request to get all Emprestimos");
        Page<Emprestimo> page = emprestimoRepository.findAll((pageable));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/emprestimo");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /*
    *   Método para contar a quantidade de empréstimos de ROLE_ADMIN
    */
    @GetMapping("/emprestimos/dashboard")
    @Timed
    public ResponseEntity<Integer> getEmprestimos() {
        final Integer page = emprestimoRepository.buscarEmprestimo();;
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(page));
    }


    /*
    *   Método para contar a quantidade de empréstimos de ROLE_USER
    *  @param userId the id of the autor to retrieve
    */
    @GetMapping("/emprestimos/devolucao/userId/{userId}")
    @Timed
    public ResponseEntity<Integer> getDevolucoes(@PathVariable Long userId) {
        final Integer page = emprestimoRepository.buscarDevolucaoUser(userId);;
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(page));
    }

    /**
     * GET  /emprestimos/:id : get the "id" emprestimo.
     *
     * @param id the id of the autor to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the emprestimo, or with status 404 (Not Found)
     */
    @GetMapping("/emprestimos/userId/{id}")
    @Timed
    public ResponseEntity<Integer> getEmprestimosUser(@PathVariable Long id) {
        log.debug("REST request to get Emprestimo : {}", id);
        final Integer page = emprestimoRepository.buscarEmprestimoUser(id);;
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(page));
    }


    /**
     * GET  /Reservas/:id : get the "id" reserva.
     *
     * @param id the id of the autor to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the reserva, or with status 404 (Not Found)
     */
    @GetMapping("/emprestimosPage/userId/{id}")
    @Timed
    public ResponseEntity<List<Emprestimo>> getEmprestimoUserPage(@PathVariable Long id, Pageable pageable) {
        log.debug("REST request to get Reserva : {}", id);
        Page<Emprestimo> page = emprestimoRepository.buscarEmprestimoUserPage(id, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/emprestimo");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /emprestimos/:id : get the "id" emprestimo.
     *
     * @param id the id of the autor to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the emprestimo, or with status 404 (Not Found)
     */
    @GetMapping("/emprestimos/{id}")
    @Timed
    public ResponseEntity<Emprestimo> getEmprestimo(@PathVariable Long id) {
        log.debug("REST request to get Emprestimo : {}", id);
        Emprestimo emprestimo = emprestimoRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(emprestimo));
    }

    /*
    *   Filtro de emprestimos atráves do atributo nome
    */
    @GetMapping("/emprestimos/nomeFiltro")
    public ResponseEntity<List<Emprestimo>> getByNomeUsuario(@RequestParam(value = "userName") String userName, Pageable pageable) {
        Page<Emprestimo> page = emprestimoRepository.buscarPorNome(userName, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "api/emprestimo");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /*
   *   Filtro de emprestimos atráves do atributo dataEmprestimo
   */
    @GetMapping("/emprestimos/dataEmprestimo")
    public ResponseEntity<List<Emprestimo>> getByDataEmprestimo(@RequestParam(value = "dataEmprestimo") String dataEmprestimo, Pageable pageable) {
        Page<Emprestimo> page = emprestimoRepository.buscarPorDataEmprestimo(dataEmprestimo, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "api/emprestimo");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /*
   *   Filtro de emprestimos atráves do atributo dataPrev
   */
    @GetMapping("/emprestimos/dataPrev")
    public ResponseEntity<List<Emprestimo>> getByDataPrev(@RequestParam(value = "dataPrev") String dataPrev, Pageable pageable) {
        Page<Emprestimo> page = emprestimoRepository.buscarPorDataPrev(dataPrev, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "api/emprestimo");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /emprestimos/:id : get the "id" emprestimo.
     *
     * @param exemplarId the id of the autor to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the emprestimo, or with status 404 (Not Found)
     */
    @GetMapping("/emprestimos/exemplar/{exemplarId}")
    @Timed
    public ResponseEntity<Emprestimo> getEmprestimoExemplar(@PathVariable Long exemplarId) {
        log.debug("REST request to get Emprestimo : {}", exemplarId);
        Emprestimo emprestimo = emprestimoRepository.buscarEmprestimoExemplar(exemplarId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(emprestimo));
    }

    /**
     * DELETE  /emprestimos/:id : delete the "id" emprestimo.
     *
     * @param id the id of the autor to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/emprestimos/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmprestimo(@PathVariable Long id) {
        log.debug("REST request to delete Emprestimo : {}", id);

        Emprestimo emprestimo = emprestimoRepository.findOne(id);

        Exemplar exemplar = exemplarRepository.findOne(emprestimo.getExemplar().getId());
        exemplar.setStatus(StatusExemplar.DISPONIVEL);
        exemplarRepository.save(exemplar);

        emprestimoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
