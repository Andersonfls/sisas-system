package com.minea.sisas.web.rest;

import com.minea.sisas.service.MapasTematicosService;
import com.minea.sisas.service.dto.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing Mapas Tematicos.
 */
@RestController
@RequestMapping("/api")
public class MapasTematicosResource {

    private final MapasTematicosService mapasTematicosService;

    public MapasTematicosResource(MapasTematicosService mapasTematicosService) {
        this.mapasTematicosService = mapasTematicosService;
    }

    //PROVINCIAL
    @GetMapping("mapas/cobertura-servicos-agua-provincial")
    public List<MapasDTO> getPorcentagemCoberturaServicosAguaProvincial() {
        return this.mapasTematicosService.montaListaDadosPorcentagemCoberturaServicosAguaProvincial();
    }

    @GetMapping("mapas/sistemas-agua-provincial")
    public List<MapasDTO> getPorcentagemSistemasAguaProvincial() {
        return this.mapasTematicosService.montaListaDadosPorcentagemSistemasAguaProvincial();
    }

    //HUAMBO
    @GetMapping("mapas/cobertura-servicos-agua-huambo-provincial")
    public List<MapasDTO> getPorcentagemCoberturaServicosAguaHuamboProvincial() {
        return this.mapasTematicosService.montaListaDadosPorcentagemCoberturaServicosAguaMunicipios(10l);
    }

    @GetMapping("mapas/sistemas-agua-huambo-provincial")
    public List<MapasDTO> getPorcentagemSistemasAguaHuamboProvincial() {
        return this.mapasTematicosService.montaListaDadosPorcentagemSistemasAguaPorProvincia(10l);
    }

    //HUILA
    @GetMapping("mapas/cobertura-servicos-agua-huila-provincial")
    public List<MapasDTO> getPorcentagemCoberturaServicosAguaHuilaProvincial() {
        return this.mapasTematicosService.montaListaDadosPorcentagemCoberturaServicosAguaMunicipios(15l);
    }

    @GetMapping("mapas/sistemas-agua-huila-provincial")
    public List<MapasDTO> getPorcentagemSistemasAguaHuilaProvincial() {
        return this.mapasTematicosService.montaListaDadosPorcentagemSistemasAguaPorProvincia(15l);
    }

    //KUANZA NORTE
    @GetMapping("mapas/cobertura-servicos-agua-kuanza-provincial")
    public List<MapasDTO> getPorcentagemCoberturaServicosAguaKuanzaProvincial() {
        return this.mapasTematicosService.montaListaDadosPorcentagemCoberturaServicosAguaMunicipios(5l);
    }

    @GetMapping("mapas/sistemas-agua-kuanza-provincial")
    public List<MapasDTO> getPorcentagemSistemasAguaKuanzaProvincial() {
        return this.mapasTematicosService.montaListaDadosPorcentagemSistemasAguaPorProvincia(5l);
    }

    //MUNICIPAL
    @GetMapping("mapas/cobertura-servicos-agua-municipal")
    public List<MapasDTO> getPorcentagemCoberturaServicosAguaMunicipal() {
        return this.mapasTematicosService.montaListaDadosPorcentagemCoberturaServicosAguaMunicipal();
    }

    @GetMapping("mapas/sistemas-agua-municipal")
    public List<MapasDTO> getPorcentagemSistemasAguaMunicipal() {
        return this.mapasTematicosService.montaListaDadosPorcentagemSistemasAguaMunicipal();
    }
}
