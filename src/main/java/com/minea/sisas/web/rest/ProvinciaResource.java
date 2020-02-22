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

//    @GetMapping("/provincias/relatorio")
//    public List<SectorAguaDadosDTO> getAllDadosProvincias() {
////        log.debug("REST request to get Provincias by criteria: {}");
////        List<SectorAguaDadosDTO> retorno = new ArrayList<>();
////        List<Provincia> list = provinciaRepository.findAll();
////
////        if (Objects.nonNull(list)) {
////            list.stream().forEach(i -> {
////                SectorAguaDadosDTO dado = new SectorAguaDadosDTO();
////                dado.setNomeProvincia(i.getNmProvincia());
////                List<Municipio> municipios = this.municipioRepository.findAllByProvinciaId(i.getId());
////                if (Objects.nonNull(municipios)) {
////                    dado.setMunicipios(municipios.size());
////                    Integer comunas = 0;
////                    for (Municipio mun: municipios) {
////                        comunas += this.comunaRepository.quantidadeComunasPorMunicipio(mun.getId());
////                    }
////                    dado.setComunas(comunas);
////                }
////                retorno.add(dado);
////            });
////        }
////
////        return retorno.stream().sorted((p1, p2) -> p1.getNomeProvincia().compareTo(p2.getNomeProvincia())).collect(Collectors.toList());
//        return montaListaEstáticaParaTeste();
//    }

//    private List<SectorAguaDadosDTO> montaListaEstáticaParaTeste(){
//        List<SectorAguaDadosDTO> retorno = new ArrayList<>();
//
//        retorno.add(new SectorAguaDadosDTO("Bengo", 8, 20, 140, 271942l, 104045l, 38));
//        retorno.add(new SectorAguaDadosDTO("Benguela", 9, 38, 415, 1597296l, 1256251l, 79));
//        retorno.add(new SectorAguaDadosDTO("Bié", 9, 39, 396, 901120l, 119327l, 13));
//        retorno.add(new SectorAguaDadosDTO("Cabinda", 4, 12, 47, 349501l, 90860l, 26));
//        retorno.add(new SectorAguaDadosDTO("Cunene", 6, 31, 140, 507559l, 69387l, 14));
//        retorno.add(new SectorAguaDadosDTO("Huambo", 11, 36, 140, 1239776l, 276844l, 22));
//        retorno.add(new SectorAguaDadosDTO("Huíla", 14, 33, 140, 1683569l, 503533l, 30));
//        retorno.add(new SectorAguaDadosDTO("Kuando Kubango", 9, 24, 140, 306214l, 32456l, 11));
//        retorno.add(new SectorAguaDadosDTO("Kuanza Norte", 10, 14, 140, 291250l, 103952l, 36));
//        retorno.add(new SectorAguaDadosDTO("Kuanza Sul", 12, 51, 140, 1036517l, 274980l, 27));
//        retorno.add(new SectorAguaDadosDTO("Luanda", 9, 29, 140, 4749423l, 1851281l, 39));
//        retorno.add(new SectorAguaDadosDTO("Luanda Norte", 9, 24, 140, 604976l, 222375l, 37));
//        retorno.add(new SectorAguaDadosDTO("Luanda Sul", 4, 14, 140, 300318l, 50334l, 17));
//        retorno.add(new SectorAguaDadosDTO("Malanje", 14, 51, 140, 598097l, 128478l, 21));
//        retorno.add(new SectorAguaDadosDTO("Moxico", 9, 29, 140, 444233l, 135841l, 31));
//        retorno.add(new SectorAguaDadosDTO("Namibe", 5, 14, 140, 289144l, 184006l, 64));
//        retorno.add(new SectorAguaDadosDTO("Uíge", 16, 47, 140, 890821l, 214186l, 24));
//        retorno.add(new SectorAguaDadosDTO("Zaire", 6, 25, 140, 306122l, 41631l, 14));
//
//        return retorno;
//    }

//    @GetMapping("/provincias/relatorio-saneamento")
//    public List<SectorAguaSaneamentoDadosDTO> getAllDadosAguaSaneamento() {
//        return montaListaEstáticaParaTesteSaneamento();
//    }
//
//    private List<SectorAguaSaneamentoDadosDTO> montaListaEstáticaParaTesteSaneamento(){
//        List<SectorAguaSaneamentoDadosDTO> retorno = new ArrayList<>();
//
//        retorno.add(new SectorAguaSaneamentoDadosDTO("NACIONAL", 16367878l, 6033618l, 37, 3791035l, 23, 100));
//        retorno.add(new SectorAguaSaneamentoDadosDTO("URBANA", 8969846l, 3092073l, 31, 1438965l, 14, 55));
//        retorno.add(new SectorAguaSaneamentoDadosDTO("RURAL", 7398033l, 2941545l, 46, 2352070l, 37, 45));
//        return retorno;
//    }


//    @GetMapping("/provincias/relatorio-agua-chafarizes")
//    public List<FuncAguaChafarizesDadosDTO> getAllDadosAguaChafarizes() {
//        return montaListaEstáticaParaTesteAguaChafarizes();
//    }

//    private List<FuncAguaChafarizesDadosDTO> montaListaEstáticaParaTesteAguaChafarizes(){
//        List<FuncAguaChafarizesDadosDTO> retorno = new ArrayList<>();
//
//        retorno.add(new FuncAguaChafarizesDadosDTO("Bengo", 140, 98, 42, 100, 0, 226, 188, 38, 0, 0));
//        retorno.add(new FuncAguaChafarizesDadosDTO("Benguela", 415, 411, 4, 100, 0, 896, 606, 290, 0, 0));
//        retorno.add(new FuncAguaChafarizesDadosDTO("Bié", 396, 329, 67, 100, 0, 140, 131, 9, 0, 0));
//        retorno.add(new FuncAguaChafarizesDadosDTO("Cabinda", 47, 32, 15, 100, 0, 369, 236, 133, 0, 0));
//        retorno.add(new FuncAguaChafarizesDadosDTO("Cunene", 348, 293, 55, 100, 0, 56, 20, 36, 0, 0));
//        retorno.add(new FuncAguaChafarizesDadosDTO("Huambo", 551, 417, 134, 100, 0, 562, 525, 37, 0, 0));
//        retorno.add(new FuncAguaChafarizesDadosDTO("Hulia", 979, 840, 139, 100, 0, 370, 349, 21, 0, 0));
//        retorno.add(new FuncAguaChafarizesDadosDTO("Kuando Kubango", 39, 33, 6, 100, 0, 64, 59, 5, 0, 0));
//        retorno.add(new FuncAguaChafarizesDadosDTO("Kuanza Norte", 90, 71, 19, 100, 0, 255, 196, 59, 0, 0));
//        retorno.add(new FuncAguaChafarizesDadosDTO("Kuanza Sul", 42, 31, 11, 100, 0, 210, 135, 75, 0, 0));
//        retorno.add(new FuncAguaChafarizesDadosDTO("Luanda", 36, 22, 14, 100, 0, 822, 515, 307, 0, 0));
//        retorno.add(new FuncAguaChafarizesDadosDTO("Luanda Norte", 38, 38, 0, 100, 0, 182, 126, 56, 0, 0));
//        retorno.add(new FuncAguaChafarizesDadosDTO("Luanda Sul", 25, 15, 10, 100, 0, 91, 84, 7, 0, 0));
//        retorno.add(new FuncAguaChafarizesDadosDTO("Malanje", 548, 233, 315, 100, 0, 114, 67, 47, 0, 0));
//        retorno.add(new FuncAguaChafarizesDadosDTO("Moxico", 165, 125, 40, 100, 0, 190, 143, 47, 0, 0));
//        retorno.add(new FuncAguaChafarizesDadosDTO("Namibe", 173, 110, 63, 100, 0, 322, 297, 25, 0, 0));
//        retorno.add(new FuncAguaChafarizesDadosDTO("Uíge", 137, 76, 61, 100, 0, 525, 326, 199, 0, 0));
//        retorno.add(new FuncAguaChafarizesDadosDTO("Zaíre", 17, 14, 3, 100, 0, 76, 73, 3, 0, 0));
//        return retorno;
//    }
//
//    @GetMapping("/provincias/nomeFiltro")
//    public ResponseEntity<List<ProvinciaDTO>> getByNome(@RequestParam(value = "nome") String nome, Pageable pageable) {
//        Page<ProvinciaDTO> page = provinciaRepository.buscarPorNome(nome, pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/provincias");
//        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
//    }

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
