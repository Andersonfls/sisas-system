package com.minea.sisas.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.minea.sisas.repository.ComunaRepository;
import com.minea.sisas.repository.MunicipioRepository;
import com.minea.sisas.repository.ProvinciaRepository;
import com.minea.sisas.service.ProvinciaQueryService;
import com.minea.sisas.service.ProvinciaService;
import com.minea.sisas.service.RelatorioService;
import com.minea.sisas.service.dto.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Provincia.
 */
@RestController
@RequestMapping("/api")
public class RelatoriosResource {

    private final Logger log = LoggerFactory.getLogger(RelatoriosResource.class);

    private static final String ENTITY_NAME = "provincia";

    private final RelatorioService relatorioService;

    private final ProvinciaQueryService provinciaQueryService;

    private final ProvinciaRepository provinciaRepository;

    private final MunicipioRepository municipioRepository;

    private final ComunaRepository comunaRepository;

    public RelatoriosResource(RelatorioService relatorioService, ProvinciaQueryService provinciaQueryService, ProvinciaRepository provinciaRepository,
                              MunicipioRepository municipioRepository, ComunaRepository comunaRepository) {
        this.provinciaQueryService = provinciaQueryService;
        this.provinciaRepository = provinciaRepository;
        this.municipioRepository = municipioRepository;
        this.comunaRepository = comunaRepository;
        this.relatorioService = relatorioService;
    }

    @GetMapping("relatorios/provincias/relatorio")
    public List<SectorAguaDadosDTO> getAllDadosProvincias() {
//        log.debug("REST request to get Provincias by criteria: {}");
//        List<SectorAguaDadosDTO> retorno = new ArrayList<>();
//        List<Provincia> list = provinciaRepository.findAll();
//
//        if (Objects.nonNull(list)) {
//            list.stream().forEach(i -> {
//                SectorAguaDadosDTO dado = new SectorAguaDadosDTO();
//                dado.setNomeProvincia(i.getNmProvincia());
//                List<Municipio> municipios = this.municipioRepository.findAllByProvinciaId(i.getId());
//                if (Objects.nonNull(municipios)) {
//                    dado.setMunicipios(municipios.size());
//                    Integer comunas = 0;
//                    for (Municipio mun: municipios) {
//                        comunas += this.comunaRepository.quantidadeComunasPorMunicipio(mun.getId());
//                    }
//                    dado.setComunas(comunas);
//                }
//                retorno.add(dado);
//            });
//        }
//
//        return retorno.stream().sorted((p1, p2) -> p1.getNomeProvincia().compareTo(p2.getNomeProvincia())).collect(Collectors.toList());
        return this.relatorioService.montaListaEstáticaParaTeste();
    }

    @GetMapping("relatorios/provincias/relatorio-saneamento")
    public List<SectorAguaSaneamentoDadosDTO> getAllDadosAguaSaneamento() {
        return this.relatorioService.montaListaEstáticaParaTesteSaneamento();
    }

    @GetMapping("relatorios/provincias/relatorio-agua-chafarizes")
    public List<FuncAguaChafarizesDadosDTO> getAllDadosAguaChafarizes() {
        return this.relatorioService.montaListaEstáticaParaTesteAguaChafarizes();
    }

    // ESTATÍSTICA DE INQUÉRITOS PREENCHIDOS
    @GetMapping("/relatorios/inqueritos-preenchidos")
    public List<InqueritosPreenchidosDadosDTO> getAllDadosInqueritosPreenchidos() {
        return this.relatorioService.montaListaEstáticaParaTesteInqueritoAguas();
    }

    // ESTATÍSTICA DE INQUÉRITOS PREENCHIDOS2
    @GetMapping("/relatorios/inqueritos-preenchidos2")
    public List<InqueritosPreenchidosDadosDTO> getAllDadosInqueritosPreenchidos2() {
        return this.relatorioService.montaListaEstáticaParaTesteInqueritoAguas();
    }

    // TRATAMENTO DE SISTEMAS DE ÁGUA
    @GetMapping("/relatorios/trat-sistemas-agua")
    public List<TratamentoSistemasAguaDadosDTO> getAllDadosInqueritosSistemasAgua() {
        return this.relatorioService.montaListaTratamentoSistemasAguas();
    }
}
