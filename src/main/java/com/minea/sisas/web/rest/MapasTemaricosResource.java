package com.minea.sisas.web.rest;

import com.minea.sisas.service.MapasTematicosService;
import com.minea.sisas.service.dto.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing Mapas Tematicos.
 */
@RestController
@RequestMapping("/api")
public class MapasTemaricosResource {

    private final MapasTematicosService mapasTematicosService;

    public MapasTemaricosResource(MapasTematicosService mapasTematicosService) {
        this.mapasTematicosService = mapasTematicosService;
    }

    @GetMapping("mapas/cobertura-servicos-agua-provincial")
    public List<MapasDTO> getPorcentagemCoberturaServicosAguaProvincial() {
        return this.mapasTematicosService.montaListaDadosPorcentagemCoberturaServicosAguaProvincial();
    }

    @GetMapping("mapas/sistemas-agua-provincial")
    public List<MapasDTO> getPorcentagemSistemasAguaProvincial() {
        return this.mapasTematicosService.montaListaDadosPorcentagemSistemasAguaProvincial();
    }

}
