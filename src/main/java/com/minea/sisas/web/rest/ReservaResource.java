package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.domain.Exemplar;
import com.minea.sisas.domain.Reserva;
import com.minea.sisas.domain.enumeration.StatusExemplar;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReservaResource {

    private final Logger log = LoggerFactory.getLogger(ReservaResource.class);

    private static final String ENTITY_NAME = "reserva";

    private final ReservaRepository reservaRepository;

    private final ExemplarRepository exemplarRepository;

    public ReservaResource(ReservaRepository reservaRepository, ExemplarRepository exemplarRepository) {
        this.reservaRepository = reservaRepository;
        this.exemplarRepository = exemplarRepository;
    }

    /**
     * POST  /Reserva : Create a new reserva.
     *
     * @param reserva the reserva to create
     * @return the ResponseEntity with status 201 (Created) and with body the new reserva, or with status 400 (Bad Request) if the reserva has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/reservas")
    @Timed
    public ResponseEntity<Reserva> createReserva(@Valid @RequestBody Reserva reserva) throws URISyntaxException {
        log.debug("REST request to save Reserva : {}", reserva);
        if (reserva.getId() != null) {
            throw new BadRequestAlertException("A new reserva cannot already have an ID", ENTITY_NAME, "idexists");
        }
        reserva.setNomeUsuario(reserva.getUser().getNome());
        reserva.setNomeObra(reserva.getObra().getNome());

        List<Exemplar> exemplares = exemplarRepository.findByObraId(reserva.getObra().getId());

        if (!exemplares.isEmpty()) {
            exemplares.get(0).setStatus(StatusExemplar.RESERVADO);
            exemplarRepository.save(exemplares.get(0));
            reserva.setExemplarId(exemplares.get(0).getId());
            reservaRepository.save(reserva);
        }

        Reserva result = reservaRepository.save(reserva);
        return ResponseEntity.created(new URI("/api/reserva/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);

    }

    /**
     * PUT  /reservas : Updates an existing reserva.
     *
     * @param reserva the reserva to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated reserva,
     * or with status 400 (Bad Request) if the reserva is not valid,
     * or with status 500 (Internal Server Error) if the reserva couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/reservas")
    @Timed
    public ResponseEntity<Reserva> updateReserva(@RequestBody Reserva reserva) throws URISyntaxException {
        log.debug("REST request to update reserva : {}", reserva);
        if (reserva.getId() == null) {
            return createReserva(reserva);
        }

        reserva.setNomeUsuario(reserva.getUser().getNome());
        reserva.setNomeObra(reserva.getObra().getNome());

        if (reserva.getStatus() == false) {
            if (reserva.getExemplarId() != null) {
                Exemplar exemplar = exemplarRepository.findOne(reserva.getExemplarId());
                exemplar.setStatus(StatusExemplar.DISPONIVEL);
                exemplarRepository.save(exemplar);
            }
        }

        if (reserva.getStatus() == true) {
            if (reserva.getExemplarId() != null) {
                Exemplar exemplar = exemplarRepository.findOne(reserva.getExemplarId());
                exemplar.setStatus(StatusExemplar.RESERVADO);
                exemplarRepository.save(exemplar);
            }
        }

        Reserva result = reservaRepository.save(reserva);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, reserva.getId().toString()))
            .body(result);
    }

    /**
     * GET  /reservas : get all the reservas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of reservas in body
     */
    @GetMapping("/reservas")
    @Timed
    public ResponseEntity<List<Reserva>> getAllReservas(Pageable pageable) {
        log.debug("REST request to get all Reserva");
        Page<Reserva> page = reservaRepository.findAll((pageable));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/reserva");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /*
    *   Método para contar a quantidade de reservas
    */
    @GetMapping("/reservas/dashboard")
    @Timed
    public ResponseEntity<Integer> getReservas() {
        final Integer page = reservaRepository.buscarReserva();;
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(page));
    }

    /**
     * GET  /Reservas/:id : get the "id" reserva.
     *
     * @param id the id of the autor to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the reserva, or with status 404 (Not Found)
     */
    @GetMapping("/reservas/userId/{id}")
    @Timed
    public ResponseEntity<Integer> getReservaUser(@PathVariable Long id) {
        log.debug("REST request to get Reserva : {}", id);
        final Integer page = reservaRepository.buscarReservaUser(id);;
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(page));
    }

    /**
     * GET  /Reservas/:id : get the "id" reserva.
     *
     * @param id the id of the autor to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the reserva, or with status 404 (Not Found)
     */
    @GetMapping("/reservasPage/userId/{id}")
    @Timed
    public ResponseEntity<List<Reserva>> getReservaUserPage(@PathVariable Long id, Pageable pageable) {
        log.debug("REST request to get Reserva : {}", id);
        Page<Reserva> page = reservaRepository.buscarReservaUserPage(id, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/reserva");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /*
    *   Filtro de reservas atráves do atributo nome
    */
    @GetMapping("/reservas/nomeFiltro")
    public ResponseEntity<List<Reserva>> getByNomeUsuario(@RequestParam(value = "nomeUsuario") String nomeUsuario, Pageable pageable) {
        Page<Reserva> page = reservaRepository.buscarPorNome(nomeUsuario, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "api/reserva");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /*
    *   Filtro de reservas atráves do atributo nome
    */
    @GetMapping("/reservas/obraFiltro")
    public ResponseEntity<List<Reserva>> getByNomeObra(@RequestParam(value = "nomeObra") String nomeObra, Pageable pageable) {
        Page<Reserva> page = reservaRepository.buscarPorObra(nomeObra, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "api/reserva");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /*
      Filtro de autores automático, atráves do nome
   */
    @GetMapping("/reservas/dataFiltro")
    public ResponseEntity<List<Reserva>> getByNome(String dataReserva, Pageable pageable) {

        String dataVencimentoSemEspacos = dataReserva.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        LocalDate dataInicioConvert = LocalDate.parse(dataVencimentoSemEspacos, formatter);

        Page<Reserva> page = reservaRepository.buscarPorData(dataInicioConvert, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/reserva");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /reservas/:id : get the "id" reserva.
     *
     * @param id the id of the reserva to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the reserva, or with status 404 (Not Found)
     */
    @GetMapping("/reservas/{id}")
    @Timed
    public ResponseEntity<Reserva> getReserva(@PathVariable Long id) {
        log.debug("REST request to get Reserva : {}", id);
        Reserva reserva = reservaRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(reserva));
    }

    /**
     * DELETE  /reservas/:id : delete the "id" reserva.
     *
     * @param id the id of the reserva to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/reservas/{id}")
    @Timed
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        log.debug("REST request to delete Reserva : {}", id);

        Reserva reserva = reservaRepository.findOne(id);

        if (reserva.getExemplarId() != null) {
            Exemplar exemplar = exemplarRepository.findOne(reserva.getExemplarId());
            exemplar.setStatus(StatusExemplar.DISPONIVEL);
            exemplarRepository.save(exemplar);
        }

        reservaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
