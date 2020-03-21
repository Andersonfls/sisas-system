package com.minea.sisas.web.rest;

import com.minea.sisas.repository.ComunaRepository;
import com.minea.sisas.repository.MunicipioRepository;
import com.minea.sisas.repository.ProvinciaRepository;
import com.minea.sisas.repository.RelatorioRepository;
import com.minea.sisas.service.ProvinciaQueryService;
import com.minea.sisas.service.RelatorioService;
import com.minea.sisas.service.dto.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing Provincia.
 */
@RestController
@RequestMapping("/api")
public class RelatoriosResource {

    private final RelatorioService relatorioService;

    private final ProvinciaQueryService provinciaQueryService;

    private final ProvinciaRepository provinciaRepository;

    private final MunicipioRepository municipioRepository;

    private final ComunaRepository comunaRepository;

    private final RelatorioRepository relatorioRepository;

    public RelatoriosResource(RelatorioService relatorioService, ProvinciaQueryService provinciaQueryService, ProvinciaRepository provinciaRepository,
                              MunicipioRepository municipioRepository, ComunaRepository comunaRepository, RelatorioRepository relatorioRepository) {
        this.provinciaQueryService = provinciaQueryService;
        this.provinciaRepository = provinciaRepository;
        this.municipioRepository = municipioRepository;
        this.comunaRepository = comunaRepository;
        this.relatorioService = relatorioService;
        this.relatorioRepository = relatorioRepository;
    }

    @GetMapping("relatorios/provincias/relatorio")
    public List<SectorAguaDadosDTO> getAllDadosProvincias() {

        return this.relatorioService.montaListaEstáticaParaTeste();
    }

    //RELATORIO COBERTURA DE SERVICOS DE AGUA
    @GetMapping("relatorios/provincias/relatorio-saneamento")
    public List<SectorAguaSaneamentoDadosDTO> getAllDadosAguaAmbito() {
        return this.relatorioService.montaListaDadosPorAmbito();
    }

    @GetMapping("relatorios/provincias/relatorio-saneamento-provincia")
    public List<SectorAguaSaneamentoDadosDTO> getAllDadosAguaProvincia() {
        return this.relatorioService.montaListaDadosPorProvincia();
    }

    @GetMapping("relatorios/provincias/relatorio-saneamento-municipio")
    public List<SectorAguaSaneamentoDadosDTO> getAllDadosAguaMunicipio() {
        return this.relatorioService.montaListaDadosPorMunicipio();
    }

    @GetMapping("relatorios/provincias/relatorio-saneamento-comuna")
    public List<SectorAguaSaneamentoDadosDTO> getAllDadosAguaComuna() {
        return this.relatorioService.montaListaDadosPorComuna();
    }
    //RELATORIO COBERTURA DE SERVICOS DE AGUA


    @GetMapping("relatorios/provincias/relatorio-indicador-producao")
    public List<IndicadorProducaoProvinciaDTO> getAllDadosIndicadorProducao() {
        List<IndicadorProducaoProvinciaDTO> retorno = new ArrayList<>();
        retorno.add(new IndicadorProducaoProvinciaDTO("Total de População coberta pelas Infra Estruturas", "hab",60,0,0, 0,0,8,0,9,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N.º de fontanários/ chafarizes operacionais","N.º",67,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Média do número de horas de Paragem","horas/dia",34,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Média do número de horas de distribuição por dia","horas/dia",43,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Horas de interrupção do sistema por falha de energia (Rede Eléctrica + Gerador)","horas/dia",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Volume de Água Captada","m3",0,0, 0,0,43,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Volume de Água Tratada","m3",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Volume Água Distribuida","m3",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Consumo Total de Energia","kwh",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Consumo Total de Combustivel","litros",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Consumo de Hipoclorito de Cálcio","kg",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Consumo de Sulfato de Aluminio","kg",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Consumo de Hidróxido de Cálcio (Cal)","kg",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Anomalias Reparadas - Captações e ETAS","kg",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Anomalias Reparadas - Adutoras","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Anomalias Reparadas - Rede de Distribuição","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Anomalias Reparadas - Ramais","N.º",0,0, 0,0,423,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Trabalhos executados - manutenção curativa","N.º",42,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Trabalhos executados - manutenção preventiva","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Trabalhos de Manutenção Verificados/ Solicitados","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N.º reservatórios Lavados","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Total de funcionários","N.º",0,0, 0,0,0,0,42,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Total de Funcionários Efectivos","N.º",42,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Total de funcionários Contratados","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Total de Funcionários de Outras Entidades","N.º",0,0, 42,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Novas ligações - novos contratos","N.º",0,0, 42,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Novas ligações domésticas - novos contratos","N.º",0,0, 0,0,65,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Ligações ilegais regularizadas","N.º",0,42, 0,0,0,76,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Ligações fechadas","N.º",0,0, 0,0,0,76,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N.º de Cortes","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N. de religações","N.º",0,0, 0,0,8,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Total ligações activas","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N.º de ligações domésticas activas","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N.º de ligações facturadas com base em leituras reais","N.º",23,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N.º de ligações facturadas com base em estimativas/ avença","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Volume Água Facturada","m3",0,0, 0,32,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Volume total facturado ligações domésticas","m3",23,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Volume facturado com base em leituras reais","m3",0,0, 0,0,0,32,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Total Valor Facturado","AOA",0,32, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Facturas canceladas e/ou Notas de Crédito","AOA",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Valor Real facturado","AOA",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Total Valor Cobrado","AOA",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N.º Reclamações","AOA",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N.º reclamações respondidas =< 5 dias","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N.º reclamações respondidas 5>dias<20","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N.º reclamações respondidas >=20dias","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Custos - Pessoal","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Custos - FSE","AOA",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Custos - Energia","AOA",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Custos - Manutenção","AOA",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Custos - Reagentes","AOA",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Custos - Destino Final de Lamas","AOA",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Custos Operacionais - OPEX","AOA",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Custos - Amortização Anual do Investimento do Operador - CAPEX","AOA",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Custos Totais - CAPEX+OPEX","AOA",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N.º de Captações","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N.º de ETAS","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N.º Reservatórios","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N.º de Estações Elevatórias","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Comprimento das Adutoras","KM",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Comprimento das Redes","KM",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("Comprimento de Ramais","KM",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N. de Acções de formação - MO - Planeadas","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N. de Acções de formação - MMS - Planeadas","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N. de Acções de formação - CMP - Planeadas","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N. de Acções de formação - SOFTWARES FORNECIDOS - Planeadas","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N.º Total de Acções de Formação planeadas","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N. de Acções de formação - MO - Realizadas","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N. de Acções de formação - MMS - Realizadas","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N. de Acções de formação - CMP - Realizadas","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N. de Acções de formação - SOFTWARES FORNECIDOS - Realizadas","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N.º Total de Acções de Formação Realizadas","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N. de Manuais MO- Previstos","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N. de Manuais MMS - Previstos","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N. de Manuais CMP - Previstos","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N.º Total de Manuais Previstos","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N. de Acções de Manuais - MO- Realizados","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N. de Manuais - MMS - Realizados","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N. de Manuais - CMP - Realizados","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));
        retorno.add(new IndicadorProducaoProvinciaDTO("N.º Total de Manuais Realizados","N.º",0,0, 0,0,0,0,0,0,0,0,0,0));

        return retorno;
    }

    @GetMapping("relatorios/provincias/relatorio-agua-chafarizes")
    public List<FuncAguaChafarizesDadosDTO> getAllDadosAguaChafarizes() {
        return this.relatorioService.funcionamentoServicosAguaChafarizesMunicipal();
    }


    //CONCLUIDO
    // ESTATÍSTICA DE INQUÉRITOS PREENCHIDOS
    @GetMapping("/relatorios/inqueritos-preenchidos")
    public List<InqueritosPreenchidosDadosDTO> getAllDadosInqueritosPreenchidos() {
        return this.relatorioService.montaListaInqueritoAguas();
    }

    //NOVOS RELATORIOS PARA VERSAO FINAL
    // COBERTURA SECTOR DE AGUAS (Nivel Provincial)
    @GetMapping("/relatorios/cobertura-sector-agua-provincial")
    public List<CoberturaSectorAguaDTO> getDadosCoberturaSectorAguaProvincial() {
        return this.relatorioService.coberturaSectorAguasProvincial();
    }
    @GetMapping("/relatorios/cobertura-sector-agua-municipal")
    public List<CoberturaSectorAguaDTO> getDadosCoberturaSectorAguaMunicipal() {
        return this.relatorioService.coberturaSectorAguasMunicipal();
    }
    @GetMapping("/relatorios/cobertura-sector-agua-comunal")
    public List<CoberturaSectorAguaDTO> getDadosCoberturaSectorAguaComunal() {
        return this.relatorioService.coberturaSectorAguasComunal();
    }

    // BENEFICIARIOS BOMBA MECANICA (Nivel Provincial)
    @GetMapping("/relatorios/benef-bomba-mecanica-provincial")
    public List<BeneficiariosBmbMecanicaDTO> getDadosBeneficiariosBmbMecanicaProvincial() {
        return this.relatorioService.beneficiariosAguaBmbMecanicaProvincial();
    }

    // FUNCIONAMENTO SISTEMA DE AGUA E CHAFARIZES
    @GetMapping("/relatorios/func-agua-chaf-municipal")
    public List<FuncAguaChafarizesDadosDTO> getDadosFuncAguaChafarizesMunicipal() {
        return this.relatorioService.funcionamentoServicosAguaChafarizesMunicipal();
    }

    // DASHBOARD
    @GetMapping("/relatorios/dashboard-principal")
    public List<DashboardDTO> getDadosDashboardPrincipal() {
        return this.relatorioService.dashboard();
    }

    // TRATAMENTO DE SISTEMAS DE ÁGUA
    @GetMapping("/relatorios/trat-sistemas-agua")
    public List<TratamentoSistemasAguaDadosDTO> getDadosTratamentoSistemasAguaProvincial() {
        return this.relatorioService.tratamentoSistemasAguasProvincial();
    }

    // TRATAMENTO DE SISTEMAS DE ÁGUA MUNICIPAL
    @GetMapping("/relatorios/trat-sistemas-agua-municipal")
    public List<TratamentoSistemasAguaDadosDTO> getDadosTratamentoSistemasAguaMunicipal() {
        return this.relatorioService.tratamentoSistemasAguasMunicipal();
    }

    // TRATAMENTO DE SISTEMAS DE ÁGUA COMUNAL
    @GetMapping("/relatorios/trat-sistemas-agua-comunal")
    public List<TratamentoSistemasAguaDadosDTO> getDadosTratamentoSistemasAguaComunal() {
        return this.relatorioService.tratamentoSistemasAguasComunal();
    }

    // FUNCIONAMENTO SISTEMA DE AGUA
    @GetMapping("/relatorios/func-sis-agua-provincial")
    public List<FuncSistemasAguaDTO> getDadosFuncAguaProvincial() {
        return this.relatorioService.funcionamentoServicosAguaProvincial();
    }
    @GetMapping("/relatorios/func-sis-agua-municipal")
    public List<FuncSistemasAguaDTO> getDadosFuncAguaMunicipal() {
        return this.relatorioService.funcionamentoServicosAguaMunicipal();
    }
    @GetMapping("/relatorios/func-sis-agua-comunal")
    public List<FuncSistemasAguaDTO> getDadosFuncAguaComunal() {
        return this.relatorioService.funcionamentoServicosAguaComunal();
    }

    // BENEFICIARIOS DE AGUA POR FONTE SUBTERRANEA E POR TIPO DE BOMBA
    @GetMapping("/relatorios/ben-agua-ft-subt-tp-bmb-comunal")
    public List<BeneAguaFtSubterraneaTpBomba> getDadosBenefAguaSubtTpBombaComunal() {
        return this.relatorioService.beneficiariosFtSubtTpBombaComunal();
    }

    @GetMapping("/relatorios/ben-agua-ft-subt-tp-bmb-municipal")
    public List<BeneAguaFtSubterraneaTpBomba> getDadosBenefAguaSubtTpBombaMunicipal() {
        return this.relatorioService.beneficiariosFtSubtTpBombaMunicipal();
    }

    @GetMapping("/relatorios/ben-agua-ft-subt-tp-bmb-man-provincial")
    public List<BeneAguaFtSubterraneaTpBombaManual> getDadosBenefAguaSubtTpBombaManualProvincial() {
        return this.relatorioService.beneficiariosFtSubtTpBombaManualProvincial();
    }
}
