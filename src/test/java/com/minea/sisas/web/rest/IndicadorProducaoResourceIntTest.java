package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.IndicadorProducao;
import com.minea.sisas.domain.Situacao;
import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.domain.Comuna;
import com.minea.sisas.domain.IndicadorProducaoLog;
import com.minea.sisas.repository.IndicadorProducaoRepository;
import com.minea.sisas.service.IndicadorProducaoService;
import com.minea.sisas.service.dto.IndicadorProducaoDTO;
import com.minea.sisas.service.mapper.IndicadorProducaoMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.IndicadorProducaoQueryService;

import com.minea.sisas.web.rest.IndicadorProducaoResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.minea.sisas.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the IndicadorProducaoResource REST controller.
 *
 * @see IndicadorProducaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class IndicadorProducaoResourceIntTest {

    private static final Long DEFAULT_ID_INDICADOR_PRODUCAO = 1L;
    private static final Long UPDATED_ID_INDICADOR_PRODUCAO = 2L;

    private static final Long DEFAULT_ID_USUARIO = 1L;
    private static final Long UPDATED_ID_USUARIO = 2L;

    private static final LocalDate DEFAULT_DT_LANCAMENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_LANCAMENTO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_ULTIMA_ALTERACAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_ULTIMA_ALTERACAO = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_QTD_POPULACAO_COBERTA_INFRAESTRUTURA = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_POPULACAO_COBERTA_INFRAESTRUTURA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTD_FONTANARIOS_CHAFARISES_OPERACIONAIS = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_FONTANARIOS_CHAFARISES_OPERACIONAIS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTD_MEDIA_HORAS_DISTRIBUICAO_DIARIA = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_MEDIA_HORAS_DISTRIBUICAO_DIARIA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTD_MEDIA_HORAS_PARAGEM = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_MEDIA_HORAS_PARAGEM = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTD_MEDIA_HORAS_INTERRUPCAO_FALTA_ENERGIA = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_MEDIA_HORAS_INTERRUPCAO_FALTA_ENERGIA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTD_VOLUME_AGUA_CAPTADA = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_VOLUME_AGUA_CAPTADA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTD_VOLUME_AGUA_TRATADA = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_VOLUME_AGUA_TRATADA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTD_VOLUME_AGUA_DISTRIBUIDA = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_VOLUME_AGUA_DISTRIBUIDA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTD_CONSUMO_ENERGIA = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_CONSUMO_ENERGIA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTD_CONSUMO_COMBUSTIVEL = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_CONSUMO_COMBUSTIVEL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTD_CONSUMO_HIPOCLORITRO_CALCIO = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_CONSUMO_HIPOCLORITRO_CALCIO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTD_CONSUMO_SULFATO_ALUMINIO = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_CONSUMO_SULFATO_ALUMINIO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTD_CONSUMO_HIDROXIDO_CALCIO = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_CONSUMO_HIDROXIDO_CALCIO = new BigDecimal(2);

    private static final Long DEFAULT_QTD_REPARO_CAPTACAO_ETAS = 1L;
    private static final Long UPDATED_QTD_REPARO_CAPTACAO_ETAS = 2L;

    private static final Long DEFAULT_QTD_REPARO_ADUTORAS = 1L;
    private static final Long UPDATED_QTD_REPARO_ADUTORAS = 2L;

    private static final Long DEFAULT_QTD_REPARO_REDE_DISTRIBUICAO = 1L;
    private static final Long UPDATED_QTD_REPARO_REDE_DISTRIBUICAO = 2L;

    private static final Long DEFAULT_QTD_REPARO_RAMAIS = 1L;
    private static final Long UPDATED_QTD_REPARO_RAMAIS = 2L;

    private static final Long DEFAULT_QTD_MANUTENCAO_CURATIVA = 1L;
    private static final Long UPDATED_QTD_MANUTENCAO_CURATIVA = 2L;

    private static final Long DEFAULT_QTD_MANUTENCAO_PREVENTIVA = 1L;
    private static final Long UPDATED_QTD_MANUTENCAO_PREVENTIVA = 2L;

    private static final Long DEFAULT_QTD_MANUTENCAO_VERIFICADO_SOLICITADO = 1L;
    private static final Long UPDATED_QTD_MANUTENCAO_VERIFICADO_SOLICITADO = 2L;

    private static final Long DEFAULT_QTD_RESERVATORIO_LAVADO = 1L;
    private static final Long UPDATED_QTD_RESERVATORIO_LAVADO = 2L;

    private static final Long DEFAULT_QTD_FUNCIONARIOS = 1L;
    private static final Long UPDATED_QTD_FUNCIONARIOS = 2L;

    private static final Long DEFAULT_QTD_FUNCIONARIOS_EFECTIVOS = 1L;
    private static final Long UPDATED_QTD_FUNCIONARIOS_EFECTIVOS = 2L;

    private static final Long DEFAULT_QTD_FUNCIONARIOS_CONTRATADOS = 1L;
    private static final Long UPDATED_QTD_FUNCIONARIOS_CONTRATADOS = 2L;

    private static final Long DEFAULT_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES = 1L;
    private static final Long UPDATED_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES = 2L;

    private static final Long DEFAULT_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS = 1L;
    private static final Long UPDATED_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS = 2L;

    private static final Long DEFAULT_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS = 1L;
    private static final Long UPDATED_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS = 2L;

    private static final Long DEFAULT_QTD_LIGACOES_ILEGAIS_REGULARIZADAS = 1L;
    private static final Long UPDATED_QTD_LIGACOES_ILEGAIS_REGULARIZADAS = 2L;

    private static final Long DEFAULT_QTD_LIGACOES_FECHADAS = 1L;
    private static final Long UPDATED_QTD_LIGACOES_FECHADAS = 2L;

    private static final Long DEFAULT_QTD_CORTES = 1L;
    private static final Long UPDATED_QTD_CORTES = 2L;

    private static final Long DEFAULT_QTD_RELIGACOES = 1L;
    private static final Long UPDATED_QTD_RELIGACOES = 2L;

    private static final Long DEFAULT_QTD_LIGACOES_ACTIVAS = 1L;
    private static final Long UPDATED_QTD_LIGACOES_ACTIVAS = 2L;

    private static final Long DEFAULT_QTD_LIGACOES_DOMESTICAS_ACTIVAS = 1L;
    private static final Long UPDATED_QTD_LIGACOES_DOMESTICAS_ACTIVAS = 2L;

    private static final Long DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS = 1L;
    private static final Long UPDATED_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS = 2L;

    private static final Long DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA = 1L;
    private static final Long UPDATED_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA = 2L;

    private static final BigDecimal DEFAULT_QTD_VOLUME_AGUA_FACTURADA = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_VOLUME_AGUA_FACTURADA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTD_VOLUME_TOTAL_FACTURADA_LIGACOES_DOMESTICAS = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_VOLUME_TOTAL_FACTURADA_LIGACOES_DOMESTICAS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTD_VOLUME_FACTURADO_BASE_LEITURA_REAIS = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_VOLUME_FACTURADO_BASE_LEITURA_REAIS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VLR_TOTAL_FACTURADO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VLR_TOTAL_FACTURADO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VLR_FACTURAS_CANCELADAS_NOTAS_CREDITOS = new BigDecimal(1);
    private static final BigDecimal UPDATED_VLR_FACTURAS_CANCELADAS_NOTAS_CREDITOS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VLR_REAL_FACTURADO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VLR_REAL_FACTURADO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VLR_TOTAL_COBRADO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VLR_TOTAL_COBRADO = new BigDecimal(2);

    private static final Long DEFAULT_QTD_RECLAMACOES = 1L;
    private static final Long UPDATED_QTD_RECLAMACOES = 2L;

    private static final Long DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS = 1L;
    private static final Long UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS = 2L;

    private static final Long DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS = 1L;
    private static final Long UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS = 2L;

    private static final Long DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS = 1L;
    private static final Long UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS = 2L;

    private static final BigDecimal DEFAULT_VLR_CUSTO_PESSOAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_VLR_CUSTO_PESSOAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VLR_CUSTO_FSE = new BigDecimal(1);
    private static final BigDecimal UPDATED_VLR_CUSTO_FSE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VLR_CUSTO_ENERGIA = new BigDecimal(1);
    private static final BigDecimal UPDATED_VLR_CUSTO_ENERGIA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VLR_CUSTO_MANUTENCAO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VLR_CUSTO_MANUTENCAO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VLR_CUSTO_REAGENTES = new BigDecimal(1);
    private static final BigDecimal UPDATED_VLR_CUSTO_REAGENTES = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VLR_CUSTO_DESTINO_FINAL_LAMAS = new BigDecimal(1);
    private static final BigDecimal UPDATED_VLR_CUSTO_DESTINO_FINAL_LAMAS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VLR_CUSTO_OPERACIONAIS_OPEX = new BigDecimal(1);
    private static final BigDecimal UPDATED_VLR_CUSTO_OPERACIONAIS_OPEX = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VLR_CUSTO_AMORTIZA_ANUAL_INVEST_OP_CAPEX = new BigDecimal(1);
    private static final BigDecimal UPDATED_VLR_CUSTO_AMORTIZA_ANUAL_INVEST_OP_CAPEX = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VLR_CUSTO_TOTAIS_CAPEX_OPEX = new BigDecimal(1);
    private static final BigDecimal UPDATED_VLR_CUSTO_TOTAIS_CAPEX_OPEX = new BigDecimal(2);

    private static final Long DEFAULT_QTD_CAPTACOES = 1L;
    private static final Long UPDATED_QTD_CAPTACOES = 2L;

    private static final Long DEFAULT_QTD_ETAS = 1L;
    private static final Long UPDATED_QTD_ETAS = 2L;

    private static final Long DEFAULT_QTD_RESERVATORIOS = 1L;
    private static final Long UPDATED_QTD_RESERVATORIOS = 2L;

    private static final Long DEFAULT_QTD_ESTACOES_ELEVATORIAS = 1L;
    private static final Long UPDATED_QTD_ESTACOES_ELEVATORIAS = 2L;

    private static final BigDecimal DEFAULT_QTD_COMPRIMENTO_ADUTORAS = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_COMPRIMENTO_ADUTORAS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTD_COMPRIMENTO_REDES = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_COMPRIMENTO_REDES = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTD_COMPRIMENTO_RAMAIS = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_COMPRIMENTO_RAMAIS = new BigDecimal(2);

    private static final Long DEFAULT_QTD_ACOES_FORMACAO_MO_PLANEADAS = 1L;
    private static final Long UPDATED_QTD_ACOES_FORMACAO_MO_PLANEADAS = 2L;

    private static final Long DEFAULT_QTD_ACOES_FORMACAO_MMS_PLANEADAS = 1L;
    private static final Long UPDATED_QTD_ACOES_FORMACAO_MMS_PLANEADAS = 2L;

    private static final Long DEFAULT_QTD_ACOES_FORMACAO_CMP_PLANEADAS = 1L;
    private static final Long UPDATED_QTD_ACOES_FORMACAO_CMP_PLANEADAS = 2L;

    private static final Long DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS = 1L;
    private static final Long UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS = 2L;

    private static final Long DEFAULT_QTD_ACOES_FORMACAO_MO_REALIZADAS = 1L;
    private static final Long UPDATED_QTD_ACOES_FORMACAO_MO_REALIZADAS = 2L;

    private static final Long DEFAULT_QTD_ACOES_FORMACAO_MMS_REALIZADAS = 1L;
    private static final Long UPDATED_QTD_ACOES_FORMACAO_MMS_REALIZADAS = 2L;

    private static final Long DEFAULT_QTD_ACOES_FORMACAO_CMP_REALIZADAS = 1L;
    private static final Long UPDATED_QTD_ACOES_FORMACAO_CMP_REALIZADAS = 2L;

    private static final Long DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS = 1L;
    private static final Long UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS = 2L;

    private static final Long DEFAULT_QTD_ACOES_FORMACAO_REALIZADAS = 1L;
    private static final Long UPDATED_QTD_ACOES_FORMACAO_REALIZADAS = 2L;

    private static final Long DEFAULT_QTD_MANUAIS_MO_PREVISTOS = 1L;
    private static final Long UPDATED_QTD_MANUAIS_MO_PREVISTOS = 2L;

    private static final Long DEFAULT_QTD_MANUAIS_MMS_PREVISTOS = 1L;
    private static final Long UPDATED_QTD_MANUAIS_MMS_PREVISTOS = 2L;

    private static final Long DEFAULT_QTD_MANUAIS_CMP_PREVISTOS = 1L;
    private static final Long UPDATED_QTD_MANUAIS_CMP_PREVISTOS = 2L;

    private static final Long DEFAULT_QTD_MANUAIS_PREVISTOS = 1L;
    private static final Long UPDATED_QTD_MANUAIS_PREVISTOS = 2L;

    private static final Long DEFAULT_QTD_ACOES_MANUAIS_MO_REALIZADAS = 1L;
    private static final Long UPDATED_QTD_ACOES_MANUAIS_MO_REALIZADAS = 2L;

    private static final Long DEFAULT_QTD_MANUAIS_MMS_REALIZADAS = 1L;
    private static final Long UPDATED_QTD_MANUAIS_MMS_REALIZADAS = 2L;

    private static final Long DEFAULT_QTD_MANUAIS_CMP_REALIZADAS = 1L;
    private static final Long UPDATED_QTD_MANUAIS_CMP_REALIZADAS = 2L;

    private static final Long DEFAULT_QTD_MANUAIS_REALIZADOS = 1L;
    private static final Long UPDATED_QTD_MANUAIS_REALIZADOS = 2L;

    @Autowired
    private IndicadorProducaoRepository indicadorProducaoRepository;

    @Autowired
    private IndicadorProducaoMapper indicadorProducaoMapper;

    @Autowired
    private IndicadorProducaoService indicadorProducaoService;

    @Autowired
    private IndicadorProducaoQueryService indicadorProducaoQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restIndicadorProducaoMockMvc;

    private IndicadorProducao indicadorProducao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IndicadorProducaoResource indicadorProducaoResource = new IndicadorProducaoResource(indicadorProducaoService, indicadorProducaoQueryService, indicadorProducaoRepository);
        this.restIndicadorProducaoMockMvc = MockMvcBuilders.standaloneSetup(indicadorProducaoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IndicadorProducao createEntity(EntityManager em) {
        IndicadorProducao indicadorProducao = new IndicadorProducao()
            .idUsuario(DEFAULT_ID_USUARIO)
            .dtLancamento(DEFAULT_DT_LANCAMENTO)
            .dtUltimaAlteracao(DEFAULT_DT_ULTIMA_ALTERACAO)
            .qtdPopulacaoCobertaInfraestrutura(DEFAULT_QTD_POPULACAO_COBERTA_INFRAESTRUTURA)
            .qtdFontanariosChafarisesOperacionais(DEFAULT_QTD_FONTANARIOS_CHAFARISES_OPERACIONAIS)
            .qtdMediaHorasDistribuicaoDiaria(DEFAULT_QTD_MEDIA_HORAS_DISTRIBUICAO_DIARIA)
            .qtdMediaHorasParagem(DEFAULT_QTD_MEDIA_HORAS_PARAGEM)
            .qtdMediaHorasInterrupcaoFaltaEnergia(DEFAULT_QTD_MEDIA_HORAS_INTERRUPCAO_FALTA_ENERGIA)
            .qtdVolumeAguaCaptada(DEFAULT_QTD_VOLUME_AGUA_CAPTADA)
            .qtdVolumeAguaTratada(DEFAULT_QTD_VOLUME_AGUA_TRATADA)
            .qtdVolumeAguaDistribuida(DEFAULT_QTD_VOLUME_AGUA_DISTRIBUIDA)
            .qtdConsumoEnergia(DEFAULT_QTD_CONSUMO_ENERGIA)
            .qtdConsumoCombustivel(DEFAULT_QTD_CONSUMO_COMBUSTIVEL)
            .qtdConsumoHipocloritroCalcio(DEFAULT_QTD_CONSUMO_HIPOCLORITRO_CALCIO)
            .qtdConsumoSulfatoAluminio(DEFAULT_QTD_CONSUMO_SULFATO_ALUMINIO)
            .qtdConsumoHidroxidoCalcio(DEFAULT_QTD_CONSUMO_HIDROXIDO_CALCIO)
            .qtdReparoCaptacaoEtas(DEFAULT_QTD_REPARO_CAPTACAO_ETAS)
            .qtdReparoAdutoras(DEFAULT_QTD_REPARO_ADUTORAS)
            .qtdReparoRedeDistribuicao(DEFAULT_QTD_REPARO_REDE_DISTRIBUICAO)
            .qtdReparoRamais(DEFAULT_QTD_REPARO_RAMAIS)
            .qtdManutencaoCurativa(DEFAULT_QTD_MANUTENCAO_CURATIVA)
            .qtdManutencaoPreventiva(DEFAULT_QTD_MANUTENCAO_PREVENTIVA)
            .qtdManutencaoVerificadoSolicitado(DEFAULT_QTD_MANUTENCAO_VERIFICADO_SOLICITADO)
            .qtdReservatorioLavado(DEFAULT_QTD_RESERVATORIO_LAVADO)
            .qtdFuncionarios(DEFAULT_QTD_FUNCIONARIOS)
            .qtdFuncionariosEfectivos(DEFAULT_QTD_FUNCIONARIOS_EFECTIVOS)
            .qtdFuncionariosContratados(DEFAULT_QTD_FUNCIONARIOS_CONTRATADOS)
            .qtdFuncionariosOutrasEntidades(DEFAULT_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES)
            .qtdNovasLigacoesNovosContratos(DEFAULT_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS)
            .qtdNovasLigacoesDomesticasNovosContratos(DEFAULT_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS)
            .qtdLigacoesIlegaisRegularizadas(DEFAULT_QTD_LIGACOES_ILEGAIS_REGULARIZADAS)
            .qtdLigacoesFechadas(DEFAULT_QTD_LIGACOES_FECHADAS)
            .qtdCortes(DEFAULT_QTD_CORTES)
            .qtdReligacoes(DEFAULT_QTD_RELIGACOES)
            .qtdLigacoesActivas(DEFAULT_QTD_LIGACOES_ACTIVAS)
            .qtdLigacoesDomesticasActivas(DEFAULT_QTD_LIGACOES_DOMESTICAS_ACTIVAS)
            .qtdLigacoesFacturadasBaseLeiturasReais(DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS)
            .qtdLigacoesFacturadasBaseEstimativasAvenca(DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA)
            .qtdVolumeAguaFacturada(DEFAULT_QTD_VOLUME_AGUA_FACTURADA)
            .qtdVolumeTotalFacturadaLigacoesDomesticas(DEFAULT_QTD_VOLUME_TOTAL_FACTURADA_LIGACOES_DOMESTICAS)
            .qtdVolumeFacturadoBaseLeituraReais(DEFAULT_QTD_VOLUME_FACTURADO_BASE_LEITURA_REAIS)
            .vlrTotalFacturado(DEFAULT_VLR_TOTAL_FACTURADO)
            .vlrFacturasCanceladasNotasCreditos(DEFAULT_VLR_FACTURAS_CANCELADAS_NOTAS_CREDITOS)
            .vlrRealFacturado(DEFAULT_VLR_REAL_FACTURADO)
            .vlrTotalCobrado(DEFAULT_VLR_TOTAL_COBRADO)
            .qtdReclamacoes(DEFAULT_QTD_RECLAMACOES)
            .qtdReclamacoesRespondidasMenorIgualCincoDias(DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS)
            .qtdReclamacoesRespondidasMaisCincoMenosVinteDias(DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS)
            .qtdReclamacoesRespondidasMaiorIgualVinteDias(DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS)
            .vlrCustoPessoal(DEFAULT_VLR_CUSTO_PESSOAL)
            .vlrCustoFse(DEFAULT_VLR_CUSTO_FSE)
            .vlrCustoEnergia(DEFAULT_VLR_CUSTO_ENERGIA)
            .vlrCustoManutencao(DEFAULT_VLR_CUSTO_MANUTENCAO)
            .vlrCustoReagentes(DEFAULT_VLR_CUSTO_REAGENTES)
            .vlrCustoDestinoFinalLamas(DEFAULT_VLR_CUSTO_DESTINO_FINAL_LAMAS)
            .vlrCustoOperacionaisOpex(DEFAULT_VLR_CUSTO_OPERACIONAIS_OPEX)
            .vlrCustoAmortizaAnualInvestOpCapex(DEFAULT_VLR_CUSTO_AMORTIZA_ANUAL_INVEST_OP_CAPEX)
            .vlrCustoTotaisCapexOpex(DEFAULT_VLR_CUSTO_TOTAIS_CAPEX_OPEX)
            .qtdCaptacoes(DEFAULT_QTD_CAPTACOES)
            .qtdEtas(DEFAULT_QTD_ETAS)
            .qtdReservatorios(DEFAULT_QTD_RESERVATORIOS)
            .qtdEstacoesElevatorias(DEFAULT_QTD_ESTACOES_ELEVATORIAS)
            .qtdComprimentoAdutoras(DEFAULT_QTD_COMPRIMENTO_ADUTORAS)
            .qtdComprimentoRedes(DEFAULT_QTD_COMPRIMENTO_REDES)
            .qtdComprimentoRamais(DEFAULT_QTD_COMPRIMENTO_RAMAIS)
            .qtdAcoesFormacaoMoPlaneadas(DEFAULT_QTD_ACOES_FORMACAO_MO_PLANEADAS)
            .qtdAcoesFormacaoMmsPlaneadas(DEFAULT_QTD_ACOES_FORMACAO_MMS_PLANEADAS)
            .qtdAcoesFormacaoCmpPlaneadas(DEFAULT_QTD_ACOES_FORMACAO_CMP_PLANEADAS)
            .qtdAcoesFormacaoSoftwareFornecidosPlanejadas(DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS)
            .qtdAcoesFormacaoMoRealizadas(DEFAULT_QTD_ACOES_FORMACAO_MO_REALIZADAS)
            .qtdAcoesFormacaoMmsRealizadas(DEFAULT_QTD_ACOES_FORMACAO_MMS_REALIZADAS)
            .qtdAcoesFormacaoCmpRealizadas(DEFAULT_QTD_ACOES_FORMACAO_CMP_REALIZADAS)
            .qtdAcoesFormacaoSoftwareFornecidosRealizadas(DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS)
            .qtdAcoesFormacaoRealizadas(DEFAULT_QTD_ACOES_FORMACAO_REALIZADAS)
            .qtdManuaisMoPrevistos(DEFAULT_QTD_MANUAIS_MO_PREVISTOS)
            .qtdManuaisMmsPrevistos(DEFAULT_QTD_MANUAIS_MMS_PREVISTOS)
            .qtdManuaisCmpPrevistos(DEFAULT_QTD_MANUAIS_CMP_PREVISTOS)
            .qtdManuaisPrevistos(DEFAULT_QTD_MANUAIS_PREVISTOS)
            .qtdAcoesManuaisMoRealizadas(DEFAULT_QTD_ACOES_MANUAIS_MO_REALIZADAS)
            .qtdManuaisMmsRealizadas(DEFAULT_QTD_MANUAIS_MMS_REALIZADAS)
            .qtdManuaisCmpRealizadas(DEFAULT_QTD_MANUAIS_CMP_REALIZADAS)
            .qtdManuaisRealizados(DEFAULT_QTD_MANUAIS_REALIZADOS);
        // Add required entity
        Situacao idSituacao = SituacaoResourceIntTest.createEntity(em);
        em.persist(idSituacao);
        em.flush();
        indicadorProducao.setSituacao(idSituacao);
        // Add required entity
        SistemaAgua idSistemaAgua = SistemaAguaResourceIntTest.createEntity(em);
        em.persist(idSistemaAgua);
        em.flush();
        indicadorProducao.setSistemaAgua(idSistemaAgua);
        // Add required entity
        Comuna idComuna = ComunaResourceIntTest.createEntity(em);
        em.persist(idComuna);
        em.flush();
        indicadorProducao.setComuna(idComuna);
        return indicadorProducao;
    }

    @Before
    public void initTest() {
        indicadorProducao = createEntity(em);
    }

    @Test
    @Transactional
    public void createIndicadorProducao() throws Exception {
        int databaseSizeBeforeCreate = indicadorProducaoRepository.findAll().size();

        // Create the IndicadorProducao
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);
        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isCreated());

        // Validate the IndicadorProducao in the database
        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeCreate + 1);
        IndicadorProducao testIndicadorProducao = indicadorProducaoList.get(indicadorProducaoList.size() - 1);
        assertThat(testIndicadorProducao.getIdUsuario()).isEqualTo(DEFAULT_ID_USUARIO);
        assertThat(testIndicadorProducao.getDtLancamento()).isEqualTo(DEFAULT_DT_LANCAMENTO);
        assertThat(testIndicadorProducao.getDtUltimaAlteracao()).isEqualTo(DEFAULT_DT_ULTIMA_ALTERACAO);
        assertThat(testIndicadorProducao.getQtdPopulacaoCobertaInfraestrutura()).isEqualTo(DEFAULT_QTD_POPULACAO_COBERTA_INFRAESTRUTURA);
        assertThat(testIndicadorProducao.getQtdFontanariosChafarisesOperacionais()).isEqualTo(DEFAULT_QTD_FONTANARIOS_CHAFARISES_OPERACIONAIS);
        assertThat(testIndicadorProducao.getQtdMediaHorasDistribuicaoDiaria()).isEqualTo(DEFAULT_QTD_MEDIA_HORAS_DISTRIBUICAO_DIARIA);
        assertThat(testIndicadorProducao.getQtdMediaHorasParagem()).isEqualTo(DEFAULT_QTD_MEDIA_HORAS_PARAGEM);
        assertThat(testIndicadorProducao.getQtdMediaHorasInterrupcaoFaltaEnergia()).isEqualTo(DEFAULT_QTD_MEDIA_HORAS_INTERRUPCAO_FALTA_ENERGIA);
        assertThat(testIndicadorProducao.getQtdVolumeAguaCaptada()).isEqualTo(DEFAULT_QTD_VOLUME_AGUA_CAPTADA);
        assertThat(testIndicadorProducao.getQtdVolumeAguaTratada()).isEqualTo(DEFAULT_QTD_VOLUME_AGUA_TRATADA);
        assertThat(testIndicadorProducao.getQtdVolumeAguaDistribuida()).isEqualTo(DEFAULT_QTD_VOLUME_AGUA_DISTRIBUIDA);
        assertThat(testIndicadorProducao.getQtdConsumoEnergia()).isEqualTo(DEFAULT_QTD_CONSUMO_ENERGIA);
        assertThat(testIndicadorProducao.getQtdConsumoCombustivel()).isEqualTo(DEFAULT_QTD_CONSUMO_COMBUSTIVEL);
        assertThat(testIndicadorProducao.getQtdConsumoHipocloritroCalcio()).isEqualTo(DEFAULT_QTD_CONSUMO_HIPOCLORITRO_CALCIO);
        assertThat(testIndicadorProducao.getQtdConsumoSulfatoAluminio()).isEqualTo(DEFAULT_QTD_CONSUMO_SULFATO_ALUMINIO);
        assertThat(testIndicadorProducao.getQtdConsumoHidroxidoCalcio()).isEqualTo(DEFAULT_QTD_CONSUMO_HIDROXIDO_CALCIO);
        assertThat(testIndicadorProducao.getQtdReparoCaptacaoEtas()).isEqualTo(DEFAULT_QTD_REPARO_CAPTACAO_ETAS);
        assertThat(testIndicadorProducao.getQtdReparoAdutoras()).isEqualTo(DEFAULT_QTD_REPARO_ADUTORAS);
        assertThat(testIndicadorProducao.getQtdReparoRedeDistribuicao()).isEqualTo(DEFAULT_QTD_REPARO_REDE_DISTRIBUICAO);
        assertThat(testIndicadorProducao.getQtdReparoRamais()).isEqualTo(DEFAULT_QTD_REPARO_RAMAIS);
        assertThat(testIndicadorProducao.getQtdManutencaoCurativa()).isEqualTo(DEFAULT_QTD_MANUTENCAO_CURATIVA);
        assertThat(testIndicadorProducao.getQtdManutencaoPreventiva()).isEqualTo(DEFAULT_QTD_MANUTENCAO_PREVENTIVA);
        assertThat(testIndicadorProducao.getQtdManutencaoVerificadoSolicitado()).isEqualTo(DEFAULT_QTD_MANUTENCAO_VERIFICADO_SOLICITADO);
        assertThat(testIndicadorProducao.getQtdReservatorioLavado()).isEqualTo(DEFAULT_QTD_RESERVATORIO_LAVADO);
        assertThat(testIndicadorProducao.getQtdFuncionarios()).isEqualTo(DEFAULT_QTD_FUNCIONARIOS);
        assertThat(testIndicadorProducao.getQtdFuncionariosEfectivos()).isEqualTo(DEFAULT_QTD_FUNCIONARIOS_EFECTIVOS);
        assertThat(testIndicadorProducao.getQtdFuncionariosContratados()).isEqualTo(DEFAULT_QTD_FUNCIONARIOS_CONTRATADOS);
        assertThat(testIndicadorProducao.getQtdFuncionariosOutrasEntidades()).isEqualTo(DEFAULT_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES);
        assertThat(testIndicadorProducao.getQtdNovasLigacoesNovosContratos()).isEqualTo(DEFAULT_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS);
        assertThat(testIndicadorProducao.getQtdNovasLigacoesDomesticasNovosContratos()).isEqualTo(DEFAULT_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS);
        assertThat(testIndicadorProducao.getQtdLigacoesIlegaisRegularizadas()).isEqualTo(DEFAULT_QTD_LIGACOES_ILEGAIS_REGULARIZADAS);
        assertThat(testIndicadorProducao.getQtdLigacoesFechadas()).isEqualTo(DEFAULT_QTD_LIGACOES_FECHADAS);
        assertThat(testIndicadorProducao.getQtdCortes()).isEqualTo(DEFAULT_QTD_CORTES);
        assertThat(testIndicadorProducao.getQtdReligacoes()).isEqualTo(DEFAULT_QTD_RELIGACOES);
        assertThat(testIndicadorProducao.getQtdLigacoesActivas()).isEqualTo(DEFAULT_QTD_LIGACOES_ACTIVAS);
        assertThat(testIndicadorProducao.getQtdLigacoesDomesticasActivas()).isEqualTo(DEFAULT_QTD_LIGACOES_DOMESTICAS_ACTIVAS);
        assertThat(testIndicadorProducao.getQtdLigacoesFacturadasBaseLeiturasReais()).isEqualTo(DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS);
        assertThat(testIndicadorProducao.getQtdLigacoesFacturadasBaseEstimativasAvenca()).isEqualTo(DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA);
        assertThat(testIndicadorProducao.getQtdVolumeAguaFacturada()).isEqualTo(DEFAULT_QTD_VOLUME_AGUA_FACTURADA);
        assertThat(testIndicadorProducao.getQtdVolumeTotalFacturadaLigacoesDomesticas()).isEqualTo(DEFAULT_QTD_VOLUME_TOTAL_FACTURADA_LIGACOES_DOMESTICAS);
        assertThat(testIndicadorProducao.getQtdVolumeFacturadoBaseLeituraReais()).isEqualTo(DEFAULT_QTD_VOLUME_FACTURADO_BASE_LEITURA_REAIS);
        assertThat(testIndicadorProducao.getVlrTotalFacturado()).isEqualTo(DEFAULT_VLR_TOTAL_FACTURADO);
        assertThat(testIndicadorProducao.getVlrFacturasCanceladasNotasCreditos()).isEqualTo(DEFAULT_VLR_FACTURAS_CANCELADAS_NOTAS_CREDITOS);
        assertThat(testIndicadorProducao.getVlrRealFacturado()).isEqualTo(DEFAULT_VLR_REAL_FACTURADO);
        assertThat(testIndicadorProducao.getVlrTotalCobrado()).isEqualTo(DEFAULT_VLR_TOTAL_COBRADO);
        assertThat(testIndicadorProducao.getQtdReclamacoes()).isEqualTo(DEFAULT_QTD_RECLAMACOES);
        assertThat(testIndicadorProducao.getQtdReclamacoesRespondidasMenorIgualCincoDias()).isEqualTo(DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS);
        assertThat(testIndicadorProducao.getQtdReclamacoesRespondidasMaisCincoMenosVinteDias()).isEqualTo(DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS);
        assertThat(testIndicadorProducao.getQtdReclamacoesRespondidasMaiorIgualVinteDias()).isEqualTo(DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS);
        assertThat(testIndicadorProducao.getVlrCustoPessoal()).isEqualTo(DEFAULT_VLR_CUSTO_PESSOAL);
        assertThat(testIndicadorProducao.getVlrCustoFse()).isEqualTo(DEFAULT_VLR_CUSTO_FSE);
        assertThat(testIndicadorProducao.getVlrCustoEnergia()).isEqualTo(DEFAULT_VLR_CUSTO_ENERGIA);
        assertThat(testIndicadorProducao.getVlrCustoManutencao()).isEqualTo(DEFAULT_VLR_CUSTO_MANUTENCAO);
        assertThat(testIndicadorProducao.getVlrCustoReagentes()).isEqualTo(DEFAULT_VLR_CUSTO_REAGENTES);
        assertThat(testIndicadorProducao.getVlrCustoDestinoFinalLamas()).isEqualTo(DEFAULT_VLR_CUSTO_DESTINO_FINAL_LAMAS);
        assertThat(testIndicadorProducao.getVlrCustoOperacionaisOpex()).isEqualTo(DEFAULT_VLR_CUSTO_OPERACIONAIS_OPEX);
        assertThat(testIndicadorProducao.getVlrCustoAmortizaAnualInvestOpCapex()).isEqualTo(DEFAULT_VLR_CUSTO_AMORTIZA_ANUAL_INVEST_OP_CAPEX);
        assertThat(testIndicadorProducao.getVlrCustoTotaisCapexOpex()).isEqualTo(DEFAULT_VLR_CUSTO_TOTAIS_CAPEX_OPEX);
        assertThat(testIndicadorProducao.getQtdCaptacoes()).isEqualTo(DEFAULT_QTD_CAPTACOES);
        assertThat(testIndicadorProducao.getQtdEtas()).isEqualTo(DEFAULT_QTD_ETAS);
        assertThat(testIndicadorProducao.getQtdReservatorios()).isEqualTo(DEFAULT_QTD_RESERVATORIOS);
        assertThat(testIndicadorProducao.getQtdEstacoesElevatorias()).isEqualTo(DEFAULT_QTD_ESTACOES_ELEVATORIAS);
        assertThat(testIndicadorProducao.getQtdComprimentoAdutoras()).isEqualTo(DEFAULT_QTD_COMPRIMENTO_ADUTORAS);
        assertThat(testIndicadorProducao.getQtdComprimentoRedes()).isEqualTo(DEFAULT_QTD_COMPRIMENTO_REDES);
        assertThat(testIndicadorProducao.getQtdComprimentoRamais()).isEqualTo(DEFAULT_QTD_COMPRIMENTO_RAMAIS);
        assertThat(testIndicadorProducao.getQtdAcoesFormacaoMoPlaneadas()).isEqualTo(DEFAULT_QTD_ACOES_FORMACAO_MO_PLANEADAS);
        assertThat(testIndicadorProducao.getQtdAcoesFormacaoMmsPlaneadas()).isEqualTo(DEFAULT_QTD_ACOES_FORMACAO_MMS_PLANEADAS);
        assertThat(testIndicadorProducao.getQtdAcoesFormacaoCmpPlaneadas()).isEqualTo(DEFAULT_QTD_ACOES_FORMACAO_CMP_PLANEADAS);
        assertThat(testIndicadorProducao.getQtdAcoesFormacaoSoftwareFornecidosPlanejadas()).isEqualTo(DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS);
        assertThat(testIndicadorProducao.getQtdAcoesFormacaoMoRealizadas()).isEqualTo(DEFAULT_QTD_ACOES_FORMACAO_MO_REALIZADAS);
        assertThat(testIndicadorProducao.getQtdAcoesFormacaoMmsRealizadas()).isEqualTo(DEFAULT_QTD_ACOES_FORMACAO_MMS_REALIZADAS);
        assertThat(testIndicadorProducao.getQtdAcoesFormacaoCmpRealizadas()).isEqualTo(DEFAULT_QTD_ACOES_FORMACAO_CMP_REALIZADAS);
        assertThat(testIndicadorProducao.getQtdAcoesFormacaoSoftwareFornecidosRealizadas()).isEqualTo(DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS);
        assertThat(testIndicadorProducao.getQtdAcoesFormacaoRealizadas()).isEqualTo(DEFAULT_QTD_ACOES_FORMACAO_REALIZADAS);
        assertThat(testIndicadorProducao.getQtdManuaisMoPrevistos()).isEqualTo(DEFAULT_QTD_MANUAIS_MO_PREVISTOS);
        assertThat(testIndicadorProducao.getQtdManuaisMmsPrevistos()).isEqualTo(DEFAULT_QTD_MANUAIS_MMS_PREVISTOS);
        assertThat(testIndicadorProducao.getQtdManuaisCmpPrevistos()).isEqualTo(DEFAULT_QTD_MANUAIS_CMP_PREVISTOS);
        assertThat(testIndicadorProducao.getQtdManuaisPrevistos()).isEqualTo(DEFAULT_QTD_MANUAIS_PREVISTOS);
        assertThat(testIndicadorProducao.getQtdAcoesManuaisMoRealizadas()).isEqualTo(DEFAULT_QTD_ACOES_MANUAIS_MO_REALIZADAS);
        assertThat(testIndicadorProducao.getQtdManuaisMmsRealizadas()).isEqualTo(DEFAULT_QTD_MANUAIS_MMS_REALIZADAS);
        assertThat(testIndicadorProducao.getQtdManuaisCmpRealizadas()).isEqualTo(DEFAULT_QTD_MANUAIS_CMP_REALIZADAS);
        assertThat(testIndicadorProducao.getQtdManuaisRealizados()).isEqualTo(DEFAULT_QTD_MANUAIS_REALIZADOS);
    }

    @Test
    @Transactional
    public void createIndicadorProducaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = indicadorProducaoRepository.findAll().size();

        // Create the IndicadorProducao with an existing ID
        indicadorProducao.setId(1L);
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IndicadorProducao in the database
        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdIndicadorProducaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdUsuarioIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setIdUsuario(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDtLancamentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setDtLancamento(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdPopulacaoCobertaInfraestruturaIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdPopulacaoCobertaInfraestrutura(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdFontanariosChafarisesOperacionaisIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdFontanariosChafarisesOperacionais(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdMediaHorasDistribuicaoDiariaIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdMediaHorasDistribuicaoDiaria(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdMediaHorasParagemIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdMediaHorasParagem(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdMediaHorasInterrupcaoFaltaEnergiaIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdMediaHorasInterrupcaoFaltaEnergia(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdVolumeAguaCaptadaIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdVolumeAguaCaptada(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdVolumeAguaTratadaIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdVolumeAguaTratada(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdVolumeAguaDistribuidaIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdVolumeAguaDistribuida(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdConsumoEnergiaIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdConsumoEnergia(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdConsumoCombustivelIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdConsumoCombustivel(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdConsumoHipocloritroCalcioIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdConsumoHipocloritroCalcio(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdConsumoSulfatoAluminioIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdConsumoSulfatoAluminio(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdConsumoHidroxidoCalcioIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdConsumoHidroxidoCalcio(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdReparoCaptacaoEtasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdReparoCaptacaoEtas(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdReparoAdutorasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdReparoAdutoras(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdReparoRedeDistribuicaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdReparoRedeDistribuicao(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdReparoRamaisIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdReparoRamais(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdManutencaoCurativaIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdManutencaoCurativa(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdManutencaoPreventivaIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdManutencaoPreventiva(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdManutencaoVerificadoSolicitadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdManutencaoVerificadoSolicitado(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdReservatorioLavadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdReservatorioLavado(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdFuncionariosIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdFuncionarios(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdFuncionariosEfectivosIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdFuncionariosEfectivos(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdFuncionariosContratadosIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdFuncionariosContratados(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdFuncionariosOutrasEntidadesIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdFuncionariosOutrasEntidades(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdNovasLigacoesNovosContratosIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdNovasLigacoesNovosContratos(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdNovasLigacoesDomesticasNovosContratosIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdNovasLigacoesDomesticasNovosContratos(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdLigacoesIlegaisRegularizadasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdLigacoesIlegaisRegularizadas(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdLigacoesFechadasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdLigacoesFechadas(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdCortesIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdCortes(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdReligacoesIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdReligacoes(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdLigacoesActivasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdLigacoesActivas(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdLigacoesDomesticasActivasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdLigacoesDomesticasActivas(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdLigacoesFacturadasBaseLeiturasReaisIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdLigacoesFacturadasBaseLeiturasReais(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdLigacoesFacturadasBaseEstimativasAvencaIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdLigacoesFacturadasBaseEstimativasAvenca(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdVolumeAguaFacturadaIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdVolumeAguaFacturada(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdVolumeTotalFacturadaLigacoesDomesticasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdVolumeTotalFacturadaLigacoesDomesticas(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdVolumeFacturadoBaseLeituraReaisIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdVolumeFacturadoBaseLeituraReais(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVlrTotalFacturadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setVlrTotalFacturado(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVlrFacturasCanceladasNotasCreditosIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setVlrFacturasCanceladasNotasCreditos(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVlrRealFacturadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setVlrRealFacturado(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVlrTotalCobradoIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setVlrTotalCobrado(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdReclamacoesIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdReclamacoes(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVlrCustoPessoalIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setVlrCustoPessoal(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVlrCustoFseIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setVlrCustoFse(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVlrCustoEnergiaIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setVlrCustoEnergia(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVlrCustoManutencaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setVlrCustoManutencao(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVlrCustoReagentesIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setVlrCustoReagentes(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVlrCustoDestinoFinalLamasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setVlrCustoDestinoFinalLamas(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVlrCustoOperacionaisOpexIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setVlrCustoOperacionaisOpex(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVlrCustoAmortizaAnualInvestOpCapexIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setVlrCustoAmortizaAnualInvestOpCapex(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVlrCustoTotaisCapexOpexIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setVlrCustoTotaisCapexOpex(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdCaptacoesIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdCaptacoes(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdEtasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdEtas(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdReservatoriosIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdReservatorios(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdEstacoesElevatoriasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdEstacoesElevatorias(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdComprimentoAdutorasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdComprimentoAdutoras(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdComprimentoRedesIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdComprimentoRedes(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdComprimentoRamaisIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdComprimentoRamais(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdAcoesFormacaoMoPlaneadasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdAcoesFormacaoMoPlaneadas(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdAcoesFormacaoMmsPlaneadasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdAcoesFormacaoMmsPlaneadas(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdAcoesFormacaoCmpPlaneadasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdAcoesFormacaoCmpPlaneadas(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdAcoesFormacaoSoftwareFornecidosPlanejadasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdAcoesFormacaoSoftwareFornecidosPlanejadas(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdAcoesFormacaoMoRealizadasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdAcoesFormacaoMoRealizadas(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdAcoesFormacaoMmsRealizadasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdAcoesFormacaoMmsRealizadas(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdAcoesFormacaoCmpRealizadasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdAcoesFormacaoCmpRealizadas(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdAcoesFormacaoSoftwareFornecidosRealizadasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdAcoesFormacaoSoftwareFornecidosRealizadas(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdAcoesFormacaoRealizadasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdAcoesFormacaoRealizadas(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdManuaisMoPrevistosIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdManuaisMoPrevistos(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdManuaisMmsPrevistosIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdManuaisMmsPrevistos(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdManuaisCmpPrevistosIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdManuaisCmpPrevistos(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdManuaisPrevistosIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdManuaisPrevistos(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdAcoesManuaisMoRealizadasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdAcoesManuaisMoRealizadas(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdManuaisMmsRealizadasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdManuaisMmsRealizadas(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdManuaisCmpRealizadasIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdManuaisCmpRealizadas(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdManuaisRealizadosIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoRepository.findAll().size();
        // set the field null
        indicadorProducao.setQtdManuaisRealizados(null);

        // Create the IndicadorProducao, which fails.
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        restIndicadorProducaoMockMvc.perform(post("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaos() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList
        restIndicadorProducaoMockMvc.perform(get("/api/indicador-producaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(indicadorProducao.getId().intValue())))
            .andExpect(jsonPath("$.[*].idIndicadorProducao").value(hasItem(DEFAULT_ID_INDICADOR_PRODUCAO.intValue())))
            .andExpect(jsonPath("$.[*].idUsuario").value(hasItem(DEFAULT_ID_USUARIO.intValue())))
            .andExpect(jsonPath("$.[*].dtLancamento").value(hasItem(DEFAULT_DT_LANCAMENTO.toString())))
            .andExpect(jsonPath("$.[*].dtUltimaAlteracao").value(hasItem(DEFAULT_DT_ULTIMA_ALTERACAO.toString())))
            .andExpect(jsonPath("$.[*].qtdPopulacaoCobertaInfraestrutura").value(hasItem(DEFAULT_QTD_POPULACAO_COBERTA_INFRAESTRUTURA.intValue())))
            .andExpect(jsonPath("$.[*].qtdFontanariosChafarisesOperacionais").value(hasItem(DEFAULT_QTD_FONTANARIOS_CHAFARISES_OPERACIONAIS.intValue())))
            .andExpect(jsonPath("$.[*].qtdMediaHorasDistribuicaoDiaria").value(hasItem(DEFAULT_QTD_MEDIA_HORAS_DISTRIBUICAO_DIARIA.intValue())))
            .andExpect(jsonPath("$.[*].qtdMediaHorasParagem").value(hasItem(DEFAULT_QTD_MEDIA_HORAS_PARAGEM.intValue())))
            .andExpect(jsonPath("$.[*].qtdMediaHorasInterrupcaoFaltaEnergia").value(hasItem(DEFAULT_QTD_MEDIA_HORAS_INTERRUPCAO_FALTA_ENERGIA.intValue())))
            .andExpect(jsonPath("$.[*].qtdVolumeAguaCaptada").value(hasItem(DEFAULT_QTD_VOLUME_AGUA_CAPTADA.intValue())))
            .andExpect(jsonPath("$.[*].qtdVolumeAguaTratada").value(hasItem(DEFAULT_QTD_VOLUME_AGUA_TRATADA.intValue())))
            .andExpect(jsonPath("$.[*].qtdVolumeAguaDistribuida").value(hasItem(DEFAULT_QTD_VOLUME_AGUA_DISTRIBUIDA.intValue())))
            .andExpect(jsonPath("$.[*].qtdConsumoEnergia").value(hasItem(DEFAULT_QTD_CONSUMO_ENERGIA.intValue())))
            .andExpect(jsonPath("$.[*].qtdConsumoCombustivel").value(hasItem(DEFAULT_QTD_CONSUMO_COMBUSTIVEL.intValue())))
            .andExpect(jsonPath("$.[*].qtdConsumoHipocloritroCalcio").value(hasItem(DEFAULT_QTD_CONSUMO_HIPOCLORITRO_CALCIO.intValue())))
            .andExpect(jsonPath("$.[*].qtdConsumoSulfatoAluminio").value(hasItem(DEFAULT_QTD_CONSUMO_SULFATO_ALUMINIO.intValue())))
            .andExpect(jsonPath("$.[*].qtdConsumoHidroxidoCalcio").value(hasItem(DEFAULT_QTD_CONSUMO_HIDROXIDO_CALCIO.intValue())))
            .andExpect(jsonPath("$.[*].qtdReparoCaptacaoEtas").value(hasItem(DEFAULT_QTD_REPARO_CAPTACAO_ETAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdReparoAdutoras").value(hasItem(DEFAULT_QTD_REPARO_ADUTORAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdReparoRedeDistribuicao").value(hasItem(DEFAULT_QTD_REPARO_REDE_DISTRIBUICAO.intValue())))
            .andExpect(jsonPath("$.[*].qtdReparoRamais").value(hasItem(DEFAULT_QTD_REPARO_RAMAIS.intValue())))
            .andExpect(jsonPath("$.[*].qtdManutencaoCurativa").value(hasItem(DEFAULT_QTD_MANUTENCAO_CURATIVA.intValue())))
            .andExpect(jsonPath("$.[*].qtdManutencaoPreventiva").value(hasItem(DEFAULT_QTD_MANUTENCAO_PREVENTIVA.intValue())))
            .andExpect(jsonPath("$.[*].qtdManutencaoVerificadoSolicitado").value(hasItem(DEFAULT_QTD_MANUTENCAO_VERIFICADO_SOLICITADO.intValue())))
            .andExpect(jsonPath("$.[*].qtdReservatorioLavado").value(hasItem(DEFAULT_QTD_RESERVATORIO_LAVADO.intValue())))
            .andExpect(jsonPath("$.[*].qtdFuncionarios").value(hasItem(DEFAULT_QTD_FUNCIONARIOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdFuncionariosEfectivos").value(hasItem(DEFAULT_QTD_FUNCIONARIOS_EFECTIVOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdFuncionariosContratados").value(hasItem(DEFAULT_QTD_FUNCIONARIOS_CONTRATADOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdFuncionariosOutrasEntidades").value(hasItem(DEFAULT_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES.intValue())))
            .andExpect(jsonPath("$.[*].qtdNovasLigacoesNovosContratos").value(hasItem(DEFAULT_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdNovasLigacoesDomesticasNovosContratos").value(hasItem(DEFAULT_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdLigacoesIlegaisRegularizadas").value(hasItem(DEFAULT_QTD_LIGACOES_ILEGAIS_REGULARIZADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdLigacoesFechadas").value(hasItem(DEFAULT_QTD_LIGACOES_FECHADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdCortes").value(hasItem(DEFAULT_QTD_CORTES.intValue())))
            .andExpect(jsonPath("$.[*].qtdReligacoes").value(hasItem(DEFAULT_QTD_RELIGACOES.intValue())))
            .andExpect(jsonPath("$.[*].qtdLigacoesActivas").value(hasItem(DEFAULT_QTD_LIGACOES_ACTIVAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdLigacoesDomesticasActivas").value(hasItem(DEFAULT_QTD_LIGACOES_DOMESTICAS_ACTIVAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdLigacoesFacturadasBaseLeiturasReais").value(hasItem(DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS.intValue())))
            .andExpect(jsonPath("$.[*].qtdLigacoesFacturadasBaseEstimativasAvenca").value(hasItem(DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA.intValue())))
            .andExpect(jsonPath("$.[*].qtdVolumeAguaFacturada").value(hasItem(DEFAULT_QTD_VOLUME_AGUA_FACTURADA.intValue())))
            .andExpect(jsonPath("$.[*].qtdVolumeTotalFacturadaLigacoesDomesticas").value(hasItem(DEFAULT_QTD_VOLUME_TOTAL_FACTURADA_LIGACOES_DOMESTICAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdVolumeFacturadoBaseLeituraReais").value(hasItem(DEFAULT_QTD_VOLUME_FACTURADO_BASE_LEITURA_REAIS.intValue())))
            .andExpect(jsonPath("$.[*].vlrTotalFacturado").value(hasItem(DEFAULT_VLR_TOTAL_FACTURADO.intValue())))
            .andExpect(jsonPath("$.[*].vlrFacturasCanceladasNotasCreditos").value(hasItem(DEFAULT_VLR_FACTURAS_CANCELADAS_NOTAS_CREDITOS.intValue())))
            .andExpect(jsonPath("$.[*].vlrRealFacturado").value(hasItem(DEFAULT_VLR_REAL_FACTURADO.intValue())))
            .andExpect(jsonPath("$.[*].vlrTotalCobrado").value(hasItem(DEFAULT_VLR_TOTAL_COBRADO.intValue())))
            .andExpect(jsonPath("$.[*].qtdReclamacoes").value(hasItem(DEFAULT_QTD_RECLAMACOES.intValue())))
            .andExpect(jsonPath("$.[*].qtdReclamacoesRespondidasMenorIgualCincoDias").value(hasItem(DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdReclamacoesRespondidasMaisCincoMenosVinteDias").value(hasItem(DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdReclamacoesRespondidasMaiorIgualVinteDias").value(hasItem(DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS.intValue())))
            .andExpect(jsonPath("$.[*].vlrCustoPessoal").value(hasItem(DEFAULT_VLR_CUSTO_PESSOAL.intValue())))
            .andExpect(jsonPath("$.[*].vlrCustoFse").value(hasItem(DEFAULT_VLR_CUSTO_FSE.intValue())))
            .andExpect(jsonPath("$.[*].vlrCustoEnergia").value(hasItem(DEFAULT_VLR_CUSTO_ENERGIA.intValue())))
            .andExpect(jsonPath("$.[*].vlrCustoManutencao").value(hasItem(DEFAULT_VLR_CUSTO_MANUTENCAO.intValue())))
            .andExpect(jsonPath("$.[*].vlrCustoReagentes").value(hasItem(DEFAULT_VLR_CUSTO_REAGENTES.intValue())))
            .andExpect(jsonPath("$.[*].vlrCustoDestinoFinalLamas").value(hasItem(DEFAULT_VLR_CUSTO_DESTINO_FINAL_LAMAS.intValue())))
            .andExpect(jsonPath("$.[*].vlrCustoOperacionaisOpex").value(hasItem(DEFAULT_VLR_CUSTO_OPERACIONAIS_OPEX.intValue())))
            .andExpect(jsonPath("$.[*].vlrCustoAmortizaAnualInvestOpCapex").value(hasItem(DEFAULT_VLR_CUSTO_AMORTIZA_ANUAL_INVEST_OP_CAPEX.intValue())))
            .andExpect(jsonPath("$.[*].vlrCustoTotaisCapexOpex").value(hasItem(DEFAULT_VLR_CUSTO_TOTAIS_CAPEX_OPEX.intValue())))
            .andExpect(jsonPath("$.[*].qtdCaptacoes").value(hasItem(DEFAULT_QTD_CAPTACOES.intValue())))
            .andExpect(jsonPath("$.[*].qtdEtas").value(hasItem(DEFAULT_QTD_ETAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdReservatorios").value(hasItem(DEFAULT_QTD_RESERVATORIOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdEstacoesElevatorias").value(hasItem(DEFAULT_QTD_ESTACOES_ELEVATORIAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdComprimentoAdutoras").value(hasItem(DEFAULT_QTD_COMPRIMENTO_ADUTORAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdComprimentoRedes").value(hasItem(DEFAULT_QTD_COMPRIMENTO_REDES.intValue())))
            .andExpect(jsonPath("$.[*].qtdComprimentoRamais").value(hasItem(DEFAULT_QTD_COMPRIMENTO_RAMAIS.intValue())))
            .andExpect(jsonPath("$.[*].qtdAcoesFormacaoMoPlaneadas").value(hasItem(DEFAULT_QTD_ACOES_FORMACAO_MO_PLANEADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdAcoesFormacaoMmsPlaneadas").value(hasItem(DEFAULT_QTD_ACOES_FORMACAO_MMS_PLANEADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdAcoesFormacaoCmpPlaneadas").value(hasItem(DEFAULT_QTD_ACOES_FORMACAO_CMP_PLANEADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdAcoesFormacaoSoftwareFornecidosPlanejadas").value(hasItem(DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdAcoesFormacaoMoRealizadas").value(hasItem(DEFAULT_QTD_ACOES_FORMACAO_MO_REALIZADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdAcoesFormacaoMmsRealizadas").value(hasItem(DEFAULT_QTD_ACOES_FORMACAO_MMS_REALIZADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdAcoesFormacaoCmpRealizadas").value(hasItem(DEFAULT_QTD_ACOES_FORMACAO_CMP_REALIZADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdAcoesFormacaoSoftwareFornecidosRealizadas").value(hasItem(DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdAcoesFormacaoRealizadas").value(hasItem(DEFAULT_QTD_ACOES_FORMACAO_REALIZADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdManuaisMoPrevistos").value(hasItem(DEFAULT_QTD_MANUAIS_MO_PREVISTOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdManuaisMmsPrevistos").value(hasItem(DEFAULT_QTD_MANUAIS_MMS_PREVISTOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdManuaisCmpPrevistos").value(hasItem(DEFAULT_QTD_MANUAIS_CMP_PREVISTOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdManuaisPrevistos").value(hasItem(DEFAULT_QTD_MANUAIS_PREVISTOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdAcoesManuaisMoRealizadas").value(hasItem(DEFAULT_QTD_ACOES_MANUAIS_MO_REALIZADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdManuaisMmsRealizadas").value(hasItem(DEFAULT_QTD_MANUAIS_MMS_REALIZADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdManuaisCmpRealizadas").value(hasItem(DEFAULT_QTD_MANUAIS_CMP_REALIZADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdManuaisRealizados").value(hasItem(DEFAULT_QTD_MANUAIS_REALIZADOS.intValue())));
    }

    @Test
    @Transactional
    public void getIndicadorProducao() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get the indicadorProducao
        restIndicadorProducaoMockMvc.perform(get("/api/indicador-producaos/{id}", indicadorProducao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(indicadorProducao.getId().intValue()))
            .andExpect(jsonPath("$.idIndicadorProducao").value(DEFAULT_ID_INDICADOR_PRODUCAO.intValue()))
            .andExpect(jsonPath("$.idUsuario").value(DEFAULT_ID_USUARIO.intValue()))
            .andExpect(jsonPath("$.dtLancamento").value(DEFAULT_DT_LANCAMENTO.toString()))
            .andExpect(jsonPath("$.dtUltimaAlteracao").value(DEFAULT_DT_ULTIMA_ALTERACAO.toString()))
            .andExpect(jsonPath("$.qtdPopulacaoCobertaInfraestrutura").value(DEFAULT_QTD_POPULACAO_COBERTA_INFRAESTRUTURA.intValue()))
            .andExpect(jsonPath("$.qtdFontanariosChafarisesOperacionais").value(DEFAULT_QTD_FONTANARIOS_CHAFARISES_OPERACIONAIS.intValue()))
            .andExpect(jsonPath("$.qtdMediaHorasDistribuicaoDiaria").value(DEFAULT_QTD_MEDIA_HORAS_DISTRIBUICAO_DIARIA.intValue()))
            .andExpect(jsonPath("$.qtdMediaHorasParagem").value(DEFAULT_QTD_MEDIA_HORAS_PARAGEM.intValue()))
            .andExpect(jsonPath("$.qtdMediaHorasInterrupcaoFaltaEnergia").value(DEFAULT_QTD_MEDIA_HORAS_INTERRUPCAO_FALTA_ENERGIA.intValue()))
            .andExpect(jsonPath("$.qtdVolumeAguaCaptada").value(DEFAULT_QTD_VOLUME_AGUA_CAPTADA.intValue()))
            .andExpect(jsonPath("$.qtdVolumeAguaTratada").value(DEFAULT_QTD_VOLUME_AGUA_TRATADA.intValue()))
            .andExpect(jsonPath("$.qtdVolumeAguaDistribuida").value(DEFAULT_QTD_VOLUME_AGUA_DISTRIBUIDA.intValue()))
            .andExpect(jsonPath("$.qtdConsumoEnergia").value(DEFAULT_QTD_CONSUMO_ENERGIA.intValue()))
            .andExpect(jsonPath("$.qtdConsumoCombustivel").value(DEFAULT_QTD_CONSUMO_COMBUSTIVEL.intValue()))
            .andExpect(jsonPath("$.qtdConsumoHipocloritroCalcio").value(DEFAULT_QTD_CONSUMO_HIPOCLORITRO_CALCIO.intValue()))
            .andExpect(jsonPath("$.qtdConsumoSulfatoAluminio").value(DEFAULT_QTD_CONSUMO_SULFATO_ALUMINIO.intValue()))
            .andExpect(jsonPath("$.qtdConsumoHidroxidoCalcio").value(DEFAULT_QTD_CONSUMO_HIDROXIDO_CALCIO.intValue()))
            .andExpect(jsonPath("$.qtdReparoCaptacaoEtas").value(DEFAULT_QTD_REPARO_CAPTACAO_ETAS.intValue()))
            .andExpect(jsonPath("$.qtdReparoAdutoras").value(DEFAULT_QTD_REPARO_ADUTORAS.intValue()))
            .andExpect(jsonPath("$.qtdReparoRedeDistribuicao").value(DEFAULT_QTD_REPARO_REDE_DISTRIBUICAO.intValue()))
            .andExpect(jsonPath("$.qtdReparoRamais").value(DEFAULT_QTD_REPARO_RAMAIS.intValue()))
            .andExpect(jsonPath("$.qtdManutencaoCurativa").value(DEFAULT_QTD_MANUTENCAO_CURATIVA.intValue()))
            .andExpect(jsonPath("$.qtdManutencaoPreventiva").value(DEFAULT_QTD_MANUTENCAO_PREVENTIVA.intValue()))
            .andExpect(jsonPath("$.qtdManutencaoVerificadoSolicitado").value(DEFAULT_QTD_MANUTENCAO_VERIFICADO_SOLICITADO.intValue()))
            .andExpect(jsonPath("$.qtdReservatorioLavado").value(DEFAULT_QTD_RESERVATORIO_LAVADO.intValue()))
            .andExpect(jsonPath("$.qtdFuncionarios").value(DEFAULT_QTD_FUNCIONARIOS.intValue()))
            .andExpect(jsonPath("$.qtdFuncionariosEfectivos").value(DEFAULT_QTD_FUNCIONARIOS_EFECTIVOS.intValue()))
            .andExpect(jsonPath("$.qtdFuncionariosContratados").value(DEFAULT_QTD_FUNCIONARIOS_CONTRATADOS.intValue()))
            .andExpect(jsonPath("$.qtdFuncionariosOutrasEntidades").value(DEFAULT_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES.intValue()))
            .andExpect(jsonPath("$.qtdNovasLigacoesNovosContratos").value(DEFAULT_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS.intValue()))
            .andExpect(jsonPath("$.qtdNovasLigacoesDomesticasNovosContratos").value(DEFAULT_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS.intValue()))
            .andExpect(jsonPath("$.qtdLigacoesIlegaisRegularizadas").value(DEFAULT_QTD_LIGACOES_ILEGAIS_REGULARIZADAS.intValue()))
            .andExpect(jsonPath("$.qtdLigacoesFechadas").value(DEFAULT_QTD_LIGACOES_FECHADAS.intValue()))
            .andExpect(jsonPath("$.qtdCortes").value(DEFAULT_QTD_CORTES.intValue()))
            .andExpect(jsonPath("$.qtdReligacoes").value(DEFAULT_QTD_RELIGACOES.intValue()))
            .andExpect(jsonPath("$.qtdLigacoesActivas").value(DEFAULT_QTD_LIGACOES_ACTIVAS.intValue()))
            .andExpect(jsonPath("$.qtdLigacoesDomesticasActivas").value(DEFAULT_QTD_LIGACOES_DOMESTICAS_ACTIVAS.intValue()))
            .andExpect(jsonPath("$.qtdLigacoesFacturadasBaseLeiturasReais").value(DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS.intValue()))
            .andExpect(jsonPath("$.qtdLigacoesFacturadasBaseEstimativasAvenca").value(DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA.intValue()))
            .andExpect(jsonPath("$.qtdVolumeAguaFacturada").value(DEFAULT_QTD_VOLUME_AGUA_FACTURADA.intValue()))
            .andExpect(jsonPath("$.qtdVolumeTotalFacturadaLigacoesDomesticas").value(DEFAULT_QTD_VOLUME_TOTAL_FACTURADA_LIGACOES_DOMESTICAS.intValue()))
            .andExpect(jsonPath("$.qtdVolumeFacturadoBaseLeituraReais").value(DEFAULT_QTD_VOLUME_FACTURADO_BASE_LEITURA_REAIS.intValue()))
            .andExpect(jsonPath("$.vlrTotalFacturado").value(DEFAULT_VLR_TOTAL_FACTURADO.intValue()))
            .andExpect(jsonPath("$.vlrFacturasCanceladasNotasCreditos").value(DEFAULT_VLR_FACTURAS_CANCELADAS_NOTAS_CREDITOS.intValue()))
            .andExpect(jsonPath("$.vlrRealFacturado").value(DEFAULT_VLR_REAL_FACTURADO.intValue()))
            .andExpect(jsonPath("$.vlrTotalCobrado").value(DEFAULT_VLR_TOTAL_COBRADO.intValue()))
            .andExpect(jsonPath("$.qtdReclamacoes").value(DEFAULT_QTD_RECLAMACOES.intValue()))
            .andExpect(jsonPath("$.qtdReclamacoesRespondidasMenorIgualCincoDias").value(DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS.intValue()))
            .andExpect(jsonPath("$.qtdReclamacoesRespondidasMaisCincoMenosVinteDias").value(DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS.intValue()))
            .andExpect(jsonPath("$.qtdReclamacoesRespondidasMaiorIgualVinteDias").value(DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS.intValue()))
            .andExpect(jsonPath("$.vlrCustoPessoal").value(DEFAULT_VLR_CUSTO_PESSOAL.intValue()))
            .andExpect(jsonPath("$.vlrCustoFse").value(DEFAULT_VLR_CUSTO_FSE.intValue()))
            .andExpect(jsonPath("$.vlrCustoEnergia").value(DEFAULT_VLR_CUSTO_ENERGIA.intValue()))
            .andExpect(jsonPath("$.vlrCustoManutencao").value(DEFAULT_VLR_CUSTO_MANUTENCAO.intValue()))
            .andExpect(jsonPath("$.vlrCustoReagentes").value(DEFAULT_VLR_CUSTO_REAGENTES.intValue()))
            .andExpect(jsonPath("$.vlrCustoDestinoFinalLamas").value(DEFAULT_VLR_CUSTO_DESTINO_FINAL_LAMAS.intValue()))
            .andExpect(jsonPath("$.vlrCustoOperacionaisOpex").value(DEFAULT_VLR_CUSTO_OPERACIONAIS_OPEX.intValue()))
            .andExpect(jsonPath("$.vlrCustoAmortizaAnualInvestOpCapex").value(DEFAULT_VLR_CUSTO_AMORTIZA_ANUAL_INVEST_OP_CAPEX.intValue()))
            .andExpect(jsonPath("$.vlrCustoTotaisCapexOpex").value(DEFAULT_VLR_CUSTO_TOTAIS_CAPEX_OPEX.intValue()))
            .andExpect(jsonPath("$.qtdCaptacoes").value(DEFAULT_QTD_CAPTACOES.intValue()))
            .andExpect(jsonPath("$.qtdEtas").value(DEFAULT_QTD_ETAS.intValue()))
            .andExpect(jsonPath("$.qtdReservatorios").value(DEFAULT_QTD_RESERVATORIOS.intValue()))
            .andExpect(jsonPath("$.qtdEstacoesElevatorias").value(DEFAULT_QTD_ESTACOES_ELEVATORIAS.intValue()))
            .andExpect(jsonPath("$.qtdComprimentoAdutoras").value(DEFAULT_QTD_COMPRIMENTO_ADUTORAS.intValue()))
            .andExpect(jsonPath("$.qtdComprimentoRedes").value(DEFAULT_QTD_COMPRIMENTO_REDES.intValue()))
            .andExpect(jsonPath("$.qtdComprimentoRamais").value(DEFAULT_QTD_COMPRIMENTO_RAMAIS.intValue()))
            .andExpect(jsonPath("$.qtdAcoesFormacaoMoPlaneadas").value(DEFAULT_QTD_ACOES_FORMACAO_MO_PLANEADAS.intValue()))
            .andExpect(jsonPath("$.qtdAcoesFormacaoMmsPlaneadas").value(DEFAULT_QTD_ACOES_FORMACAO_MMS_PLANEADAS.intValue()))
            .andExpect(jsonPath("$.qtdAcoesFormacaoCmpPlaneadas").value(DEFAULT_QTD_ACOES_FORMACAO_CMP_PLANEADAS.intValue()))
            .andExpect(jsonPath("$.qtdAcoesFormacaoSoftwareFornecidosPlanejadas").value(DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS.intValue()))
            .andExpect(jsonPath("$.qtdAcoesFormacaoMoRealizadas").value(DEFAULT_QTD_ACOES_FORMACAO_MO_REALIZADAS.intValue()))
            .andExpect(jsonPath("$.qtdAcoesFormacaoMmsRealizadas").value(DEFAULT_QTD_ACOES_FORMACAO_MMS_REALIZADAS.intValue()))
            .andExpect(jsonPath("$.qtdAcoesFormacaoCmpRealizadas").value(DEFAULT_QTD_ACOES_FORMACAO_CMP_REALIZADAS.intValue()))
            .andExpect(jsonPath("$.qtdAcoesFormacaoSoftwareFornecidosRealizadas").value(DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS.intValue()))
            .andExpect(jsonPath("$.qtdAcoesFormacaoRealizadas").value(DEFAULT_QTD_ACOES_FORMACAO_REALIZADAS.intValue()))
            .andExpect(jsonPath("$.qtdManuaisMoPrevistos").value(DEFAULT_QTD_MANUAIS_MO_PREVISTOS.intValue()))
            .andExpect(jsonPath("$.qtdManuaisMmsPrevistos").value(DEFAULT_QTD_MANUAIS_MMS_PREVISTOS.intValue()))
            .andExpect(jsonPath("$.qtdManuaisCmpPrevistos").value(DEFAULT_QTD_MANUAIS_CMP_PREVISTOS.intValue()))
            .andExpect(jsonPath("$.qtdManuaisPrevistos").value(DEFAULT_QTD_MANUAIS_PREVISTOS.intValue()))
            .andExpect(jsonPath("$.qtdAcoesManuaisMoRealizadas").value(DEFAULT_QTD_ACOES_MANUAIS_MO_REALIZADAS.intValue()))
            .andExpect(jsonPath("$.qtdManuaisMmsRealizadas").value(DEFAULT_QTD_MANUAIS_MMS_REALIZADAS.intValue()))
            .andExpect(jsonPath("$.qtdManuaisCmpRealizadas").value(DEFAULT_QTD_MANUAIS_CMP_REALIZADAS.intValue()))
            .andExpect(jsonPath("$.qtdManuaisRealizados").value(DEFAULT_QTD_MANUAIS_REALIZADOS.intValue()));
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByIdIndicadorProducaoIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where idIndicadorProducao equals to DEFAULT_ID_INDICADOR_PRODUCAO
        defaultIndicadorProducaoShouldBeFound("idIndicadorProducao.equals=" + DEFAULT_ID_INDICADOR_PRODUCAO);

        // Get all the indicadorProducaoList where idIndicadorProducao equals to UPDATED_ID_INDICADOR_PRODUCAO
        defaultIndicadorProducaoShouldNotBeFound("idIndicadorProducao.equals=" + UPDATED_ID_INDICADOR_PRODUCAO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByIdIndicadorProducaoIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where idIndicadorProducao in DEFAULT_ID_INDICADOR_PRODUCAO or UPDATED_ID_INDICADOR_PRODUCAO
        defaultIndicadorProducaoShouldBeFound("idIndicadorProducao.in=" + DEFAULT_ID_INDICADOR_PRODUCAO + "," + UPDATED_ID_INDICADOR_PRODUCAO);

        // Get all the indicadorProducaoList where idIndicadorProducao equals to UPDATED_ID_INDICADOR_PRODUCAO
        defaultIndicadorProducaoShouldNotBeFound("idIndicadorProducao.in=" + UPDATED_ID_INDICADOR_PRODUCAO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByIdIndicadorProducaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where idIndicadorProducao is not null
        defaultIndicadorProducaoShouldBeFound("idIndicadorProducao.specified=true");

        // Get all the indicadorProducaoList where idIndicadorProducao is null
        defaultIndicadorProducaoShouldNotBeFound("idIndicadorProducao.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByIdIndicadorProducaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where idIndicadorProducao greater than or equals to DEFAULT_ID_INDICADOR_PRODUCAO
        defaultIndicadorProducaoShouldBeFound("idIndicadorProducao.greaterOrEqualThan=" + DEFAULT_ID_INDICADOR_PRODUCAO);

        // Get all the indicadorProducaoList where idIndicadorProducao greater than or equals to UPDATED_ID_INDICADOR_PRODUCAO
        defaultIndicadorProducaoShouldNotBeFound("idIndicadorProducao.greaterOrEqualThan=" + UPDATED_ID_INDICADOR_PRODUCAO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByIdIndicadorProducaoIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where idIndicadorProducao less than or equals to DEFAULT_ID_INDICADOR_PRODUCAO
        defaultIndicadorProducaoShouldNotBeFound("idIndicadorProducao.lessThan=" + DEFAULT_ID_INDICADOR_PRODUCAO);

        // Get all the indicadorProducaoList where idIndicadorProducao less than or equals to UPDATED_ID_INDICADOR_PRODUCAO
        defaultIndicadorProducaoShouldBeFound("idIndicadorProducao.lessThan=" + UPDATED_ID_INDICADOR_PRODUCAO);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByIdUsuarioIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where idUsuario equals to DEFAULT_ID_USUARIO
        defaultIndicadorProducaoShouldBeFound("idUsuario.equals=" + DEFAULT_ID_USUARIO);

        // Get all the indicadorProducaoList where idUsuario equals to UPDATED_ID_USUARIO
        defaultIndicadorProducaoShouldNotBeFound("idUsuario.equals=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByIdUsuarioIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where idUsuario in DEFAULT_ID_USUARIO or UPDATED_ID_USUARIO
        defaultIndicadorProducaoShouldBeFound("idUsuario.in=" + DEFAULT_ID_USUARIO + "," + UPDATED_ID_USUARIO);

        // Get all the indicadorProducaoList where idUsuario equals to UPDATED_ID_USUARIO
        defaultIndicadorProducaoShouldNotBeFound("idUsuario.in=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByIdUsuarioIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where idUsuario is not null
        defaultIndicadorProducaoShouldBeFound("idUsuario.specified=true");

        // Get all the indicadorProducaoList where idUsuario is null
        defaultIndicadorProducaoShouldNotBeFound("idUsuario.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByIdUsuarioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where idUsuario greater than or equals to DEFAULT_ID_USUARIO
        defaultIndicadorProducaoShouldBeFound("idUsuario.greaterOrEqualThan=" + DEFAULT_ID_USUARIO);

        // Get all the indicadorProducaoList where idUsuario greater than or equals to UPDATED_ID_USUARIO
        defaultIndicadorProducaoShouldNotBeFound("idUsuario.greaterOrEqualThan=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByIdUsuarioIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where idUsuario less than or equals to DEFAULT_ID_USUARIO
        defaultIndicadorProducaoShouldNotBeFound("idUsuario.lessThan=" + DEFAULT_ID_USUARIO);

        // Get all the indicadorProducaoList where idUsuario less than or equals to UPDATED_ID_USUARIO
        defaultIndicadorProducaoShouldBeFound("idUsuario.lessThan=" + UPDATED_ID_USUARIO);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByDtLancamentoIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where dtLancamento equals to DEFAULT_DT_LANCAMENTO
        defaultIndicadorProducaoShouldBeFound("dtLancamento.equals=" + DEFAULT_DT_LANCAMENTO);

        // Get all the indicadorProducaoList where dtLancamento equals to UPDATED_DT_LANCAMENTO
        defaultIndicadorProducaoShouldNotBeFound("dtLancamento.equals=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByDtLancamentoIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where dtLancamento in DEFAULT_DT_LANCAMENTO or UPDATED_DT_LANCAMENTO
        defaultIndicadorProducaoShouldBeFound("dtLancamento.in=" + DEFAULT_DT_LANCAMENTO + "," + UPDATED_DT_LANCAMENTO);

        // Get all the indicadorProducaoList where dtLancamento equals to UPDATED_DT_LANCAMENTO
        defaultIndicadorProducaoShouldNotBeFound("dtLancamento.in=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByDtLancamentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where dtLancamento is not null
        defaultIndicadorProducaoShouldBeFound("dtLancamento.specified=true");

        // Get all the indicadorProducaoList where dtLancamento is null
        defaultIndicadorProducaoShouldNotBeFound("dtLancamento.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByDtLancamentoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where dtLancamento greater than or equals to DEFAULT_DT_LANCAMENTO
        defaultIndicadorProducaoShouldBeFound("dtLancamento.greaterOrEqualThan=" + DEFAULT_DT_LANCAMENTO);

        // Get all the indicadorProducaoList where dtLancamento greater than or equals to UPDATED_DT_LANCAMENTO
        defaultIndicadorProducaoShouldNotBeFound("dtLancamento.greaterOrEqualThan=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByDtLancamentoIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where dtLancamento less than or equals to DEFAULT_DT_LANCAMENTO
        defaultIndicadorProducaoShouldNotBeFound("dtLancamento.lessThan=" + DEFAULT_DT_LANCAMENTO);

        // Get all the indicadorProducaoList where dtLancamento less than or equals to UPDATED_DT_LANCAMENTO
        defaultIndicadorProducaoShouldBeFound("dtLancamento.lessThan=" + UPDATED_DT_LANCAMENTO);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByDtUltimaAlteracaoIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where dtUltimaAlteracao equals to DEFAULT_DT_ULTIMA_ALTERACAO
        defaultIndicadorProducaoShouldBeFound("dtUltimaAlteracao.equals=" + DEFAULT_DT_ULTIMA_ALTERACAO);

        // Get all the indicadorProducaoList where dtUltimaAlteracao equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultIndicadorProducaoShouldNotBeFound("dtUltimaAlteracao.equals=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByDtUltimaAlteracaoIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where dtUltimaAlteracao in DEFAULT_DT_ULTIMA_ALTERACAO or UPDATED_DT_ULTIMA_ALTERACAO
        defaultIndicadorProducaoShouldBeFound("dtUltimaAlteracao.in=" + DEFAULT_DT_ULTIMA_ALTERACAO + "," + UPDATED_DT_ULTIMA_ALTERACAO);

        // Get all the indicadorProducaoList where dtUltimaAlteracao equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultIndicadorProducaoShouldNotBeFound("dtUltimaAlteracao.in=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByDtUltimaAlteracaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where dtUltimaAlteracao is not null
        defaultIndicadorProducaoShouldBeFound("dtUltimaAlteracao.specified=true");

        // Get all the indicadorProducaoList where dtUltimaAlteracao is null
        defaultIndicadorProducaoShouldNotBeFound("dtUltimaAlteracao.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByDtUltimaAlteracaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where dtUltimaAlteracao greater than or equals to DEFAULT_DT_ULTIMA_ALTERACAO
        defaultIndicadorProducaoShouldBeFound("dtUltimaAlteracao.greaterOrEqualThan=" + DEFAULT_DT_ULTIMA_ALTERACAO);

        // Get all the indicadorProducaoList where dtUltimaAlteracao greater than or equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultIndicadorProducaoShouldNotBeFound("dtUltimaAlteracao.greaterOrEqualThan=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByDtUltimaAlteracaoIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where dtUltimaAlteracao less than or equals to DEFAULT_DT_ULTIMA_ALTERACAO
        defaultIndicadorProducaoShouldNotBeFound("dtUltimaAlteracao.lessThan=" + DEFAULT_DT_ULTIMA_ALTERACAO);

        // Get all the indicadorProducaoList where dtUltimaAlteracao less than or equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultIndicadorProducaoShouldBeFound("dtUltimaAlteracao.lessThan=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdPopulacaoCobertaInfraestruturaIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdPopulacaoCobertaInfraestrutura equals to DEFAULT_QTD_POPULACAO_COBERTA_INFRAESTRUTURA
        defaultIndicadorProducaoShouldBeFound("qtdPopulacaoCobertaInfraestrutura.equals=" + DEFAULT_QTD_POPULACAO_COBERTA_INFRAESTRUTURA);

        // Get all the indicadorProducaoList where qtdPopulacaoCobertaInfraestrutura equals to UPDATED_QTD_POPULACAO_COBERTA_INFRAESTRUTURA
        defaultIndicadorProducaoShouldNotBeFound("qtdPopulacaoCobertaInfraestrutura.equals=" + UPDATED_QTD_POPULACAO_COBERTA_INFRAESTRUTURA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdPopulacaoCobertaInfraestruturaIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdPopulacaoCobertaInfraestrutura in DEFAULT_QTD_POPULACAO_COBERTA_INFRAESTRUTURA or UPDATED_QTD_POPULACAO_COBERTA_INFRAESTRUTURA
        defaultIndicadorProducaoShouldBeFound("qtdPopulacaoCobertaInfraestrutura.in=" + DEFAULT_QTD_POPULACAO_COBERTA_INFRAESTRUTURA + "," + UPDATED_QTD_POPULACAO_COBERTA_INFRAESTRUTURA);

        // Get all the indicadorProducaoList where qtdPopulacaoCobertaInfraestrutura equals to UPDATED_QTD_POPULACAO_COBERTA_INFRAESTRUTURA
        defaultIndicadorProducaoShouldNotBeFound("qtdPopulacaoCobertaInfraestrutura.in=" + UPDATED_QTD_POPULACAO_COBERTA_INFRAESTRUTURA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdPopulacaoCobertaInfraestruturaIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdPopulacaoCobertaInfraestrutura is not null
        defaultIndicadorProducaoShouldBeFound("qtdPopulacaoCobertaInfraestrutura.specified=true");

        // Get all the indicadorProducaoList where qtdPopulacaoCobertaInfraestrutura is null
        defaultIndicadorProducaoShouldNotBeFound("qtdPopulacaoCobertaInfraestrutura.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFontanariosChafarisesOperacionaisIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFontanariosChafarisesOperacionais equals to DEFAULT_QTD_FONTANARIOS_CHAFARISES_OPERACIONAIS
        defaultIndicadorProducaoShouldBeFound("qtdFontanariosChafarisesOperacionais.equals=" + DEFAULT_QTD_FONTANARIOS_CHAFARISES_OPERACIONAIS);

        // Get all the indicadorProducaoList where qtdFontanariosChafarisesOperacionais equals to UPDATED_QTD_FONTANARIOS_CHAFARISES_OPERACIONAIS
        defaultIndicadorProducaoShouldNotBeFound("qtdFontanariosChafarisesOperacionais.equals=" + UPDATED_QTD_FONTANARIOS_CHAFARISES_OPERACIONAIS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFontanariosChafarisesOperacionaisIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFontanariosChafarisesOperacionais in DEFAULT_QTD_FONTANARIOS_CHAFARISES_OPERACIONAIS or UPDATED_QTD_FONTANARIOS_CHAFARISES_OPERACIONAIS
        defaultIndicadorProducaoShouldBeFound("qtdFontanariosChafarisesOperacionais.in=" + DEFAULT_QTD_FONTANARIOS_CHAFARISES_OPERACIONAIS + "," + UPDATED_QTD_FONTANARIOS_CHAFARISES_OPERACIONAIS);

        // Get all the indicadorProducaoList where qtdFontanariosChafarisesOperacionais equals to UPDATED_QTD_FONTANARIOS_CHAFARISES_OPERACIONAIS
        defaultIndicadorProducaoShouldNotBeFound("qtdFontanariosChafarisesOperacionais.in=" + UPDATED_QTD_FONTANARIOS_CHAFARISES_OPERACIONAIS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFontanariosChafarisesOperacionaisIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFontanariosChafarisesOperacionais is not null
        defaultIndicadorProducaoShouldBeFound("qtdFontanariosChafarisesOperacionais.specified=true");

        // Get all the indicadorProducaoList where qtdFontanariosChafarisesOperacionais is null
        defaultIndicadorProducaoShouldNotBeFound("qtdFontanariosChafarisesOperacionais.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdMediaHorasDistribuicaoDiariaIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdMediaHorasDistribuicaoDiaria equals to DEFAULT_QTD_MEDIA_HORAS_DISTRIBUICAO_DIARIA
        defaultIndicadorProducaoShouldBeFound("qtdMediaHorasDistribuicaoDiaria.equals=" + DEFAULT_QTD_MEDIA_HORAS_DISTRIBUICAO_DIARIA);

        // Get all the indicadorProducaoList where qtdMediaHorasDistribuicaoDiaria equals to UPDATED_QTD_MEDIA_HORAS_DISTRIBUICAO_DIARIA
        defaultIndicadorProducaoShouldNotBeFound("qtdMediaHorasDistribuicaoDiaria.equals=" + UPDATED_QTD_MEDIA_HORAS_DISTRIBUICAO_DIARIA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdMediaHorasDistribuicaoDiariaIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdMediaHorasDistribuicaoDiaria in DEFAULT_QTD_MEDIA_HORAS_DISTRIBUICAO_DIARIA or UPDATED_QTD_MEDIA_HORAS_DISTRIBUICAO_DIARIA
        defaultIndicadorProducaoShouldBeFound("qtdMediaHorasDistribuicaoDiaria.in=" + DEFAULT_QTD_MEDIA_HORAS_DISTRIBUICAO_DIARIA + "," + UPDATED_QTD_MEDIA_HORAS_DISTRIBUICAO_DIARIA);

        // Get all the indicadorProducaoList where qtdMediaHorasDistribuicaoDiaria equals to UPDATED_QTD_MEDIA_HORAS_DISTRIBUICAO_DIARIA
        defaultIndicadorProducaoShouldNotBeFound("qtdMediaHorasDistribuicaoDiaria.in=" + UPDATED_QTD_MEDIA_HORAS_DISTRIBUICAO_DIARIA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdMediaHorasDistribuicaoDiariaIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdMediaHorasDistribuicaoDiaria is not null
        defaultIndicadorProducaoShouldBeFound("qtdMediaHorasDistribuicaoDiaria.specified=true");

        // Get all the indicadorProducaoList where qtdMediaHorasDistribuicaoDiaria is null
        defaultIndicadorProducaoShouldNotBeFound("qtdMediaHorasDistribuicaoDiaria.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdMediaHorasParagemIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdMediaHorasParagem equals to DEFAULT_QTD_MEDIA_HORAS_PARAGEM
        defaultIndicadorProducaoShouldBeFound("qtdMediaHorasParagem.equals=" + DEFAULT_QTD_MEDIA_HORAS_PARAGEM);

        // Get all the indicadorProducaoList where qtdMediaHorasParagem equals to UPDATED_QTD_MEDIA_HORAS_PARAGEM
        defaultIndicadorProducaoShouldNotBeFound("qtdMediaHorasParagem.equals=" + UPDATED_QTD_MEDIA_HORAS_PARAGEM);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdMediaHorasParagemIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdMediaHorasParagem in DEFAULT_QTD_MEDIA_HORAS_PARAGEM or UPDATED_QTD_MEDIA_HORAS_PARAGEM
        defaultIndicadorProducaoShouldBeFound("qtdMediaHorasParagem.in=" + DEFAULT_QTD_MEDIA_HORAS_PARAGEM + "," + UPDATED_QTD_MEDIA_HORAS_PARAGEM);

        // Get all the indicadorProducaoList where qtdMediaHorasParagem equals to UPDATED_QTD_MEDIA_HORAS_PARAGEM
        defaultIndicadorProducaoShouldNotBeFound("qtdMediaHorasParagem.in=" + UPDATED_QTD_MEDIA_HORAS_PARAGEM);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdMediaHorasParagemIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdMediaHorasParagem is not null
        defaultIndicadorProducaoShouldBeFound("qtdMediaHorasParagem.specified=true");

        // Get all the indicadorProducaoList where qtdMediaHorasParagem is null
        defaultIndicadorProducaoShouldNotBeFound("qtdMediaHorasParagem.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdMediaHorasInterrupcaoFaltaEnergiaIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdMediaHorasInterrupcaoFaltaEnergia equals to DEFAULT_QTD_MEDIA_HORAS_INTERRUPCAO_FALTA_ENERGIA
        defaultIndicadorProducaoShouldBeFound("qtdMediaHorasInterrupcaoFaltaEnergia.equals=" + DEFAULT_QTD_MEDIA_HORAS_INTERRUPCAO_FALTA_ENERGIA);

        // Get all the indicadorProducaoList where qtdMediaHorasInterrupcaoFaltaEnergia equals to UPDATED_QTD_MEDIA_HORAS_INTERRUPCAO_FALTA_ENERGIA
        defaultIndicadorProducaoShouldNotBeFound("qtdMediaHorasInterrupcaoFaltaEnergia.equals=" + UPDATED_QTD_MEDIA_HORAS_INTERRUPCAO_FALTA_ENERGIA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdMediaHorasInterrupcaoFaltaEnergiaIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdMediaHorasInterrupcaoFaltaEnergia in DEFAULT_QTD_MEDIA_HORAS_INTERRUPCAO_FALTA_ENERGIA or UPDATED_QTD_MEDIA_HORAS_INTERRUPCAO_FALTA_ENERGIA
        defaultIndicadorProducaoShouldBeFound("qtdMediaHorasInterrupcaoFaltaEnergia.in=" + DEFAULT_QTD_MEDIA_HORAS_INTERRUPCAO_FALTA_ENERGIA + "," + UPDATED_QTD_MEDIA_HORAS_INTERRUPCAO_FALTA_ENERGIA);

        // Get all the indicadorProducaoList where qtdMediaHorasInterrupcaoFaltaEnergia equals to UPDATED_QTD_MEDIA_HORAS_INTERRUPCAO_FALTA_ENERGIA
        defaultIndicadorProducaoShouldNotBeFound("qtdMediaHorasInterrupcaoFaltaEnergia.in=" + UPDATED_QTD_MEDIA_HORAS_INTERRUPCAO_FALTA_ENERGIA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdMediaHorasInterrupcaoFaltaEnergiaIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdMediaHorasInterrupcaoFaltaEnergia is not null
        defaultIndicadorProducaoShouldBeFound("qtdMediaHorasInterrupcaoFaltaEnergia.specified=true");

        // Get all the indicadorProducaoList where qtdMediaHorasInterrupcaoFaltaEnergia is null
        defaultIndicadorProducaoShouldNotBeFound("qtdMediaHorasInterrupcaoFaltaEnergia.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdVolumeAguaCaptadaIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdVolumeAguaCaptada equals to DEFAULT_QTD_VOLUME_AGUA_CAPTADA
        defaultIndicadorProducaoShouldBeFound("qtdVolumeAguaCaptada.equals=" + DEFAULT_QTD_VOLUME_AGUA_CAPTADA);

        // Get all the indicadorProducaoList where qtdVolumeAguaCaptada equals to UPDATED_QTD_VOLUME_AGUA_CAPTADA
        defaultIndicadorProducaoShouldNotBeFound("qtdVolumeAguaCaptada.equals=" + UPDATED_QTD_VOLUME_AGUA_CAPTADA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdVolumeAguaCaptadaIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdVolumeAguaCaptada in DEFAULT_QTD_VOLUME_AGUA_CAPTADA or UPDATED_QTD_VOLUME_AGUA_CAPTADA
        defaultIndicadorProducaoShouldBeFound("qtdVolumeAguaCaptada.in=" + DEFAULT_QTD_VOLUME_AGUA_CAPTADA + "," + UPDATED_QTD_VOLUME_AGUA_CAPTADA);

        // Get all the indicadorProducaoList where qtdVolumeAguaCaptada equals to UPDATED_QTD_VOLUME_AGUA_CAPTADA
        defaultIndicadorProducaoShouldNotBeFound("qtdVolumeAguaCaptada.in=" + UPDATED_QTD_VOLUME_AGUA_CAPTADA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdVolumeAguaCaptadaIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdVolumeAguaCaptada is not null
        defaultIndicadorProducaoShouldBeFound("qtdVolumeAguaCaptada.specified=true");

        // Get all the indicadorProducaoList where qtdVolumeAguaCaptada is null
        defaultIndicadorProducaoShouldNotBeFound("qtdVolumeAguaCaptada.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdVolumeAguaTratadaIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdVolumeAguaTratada equals to DEFAULT_QTD_VOLUME_AGUA_TRATADA
        defaultIndicadorProducaoShouldBeFound("qtdVolumeAguaTratada.equals=" + DEFAULT_QTD_VOLUME_AGUA_TRATADA);

        // Get all the indicadorProducaoList where qtdVolumeAguaTratada equals to UPDATED_QTD_VOLUME_AGUA_TRATADA
        defaultIndicadorProducaoShouldNotBeFound("qtdVolumeAguaTratada.equals=" + UPDATED_QTD_VOLUME_AGUA_TRATADA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdVolumeAguaTratadaIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdVolumeAguaTratada in DEFAULT_QTD_VOLUME_AGUA_TRATADA or UPDATED_QTD_VOLUME_AGUA_TRATADA
        defaultIndicadorProducaoShouldBeFound("qtdVolumeAguaTratada.in=" + DEFAULT_QTD_VOLUME_AGUA_TRATADA + "," + UPDATED_QTD_VOLUME_AGUA_TRATADA);

        // Get all the indicadorProducaoList where qtdVolumeAguaTratada equals to UPDATED_QTD_VOLUME_AGUA_TRATADA
        defaultIndicadorProducaoShouldNotBeFound("qtdVolumeAguaTratada.in=" + UPDATED_QTD_VOLUME_AGUA_TRATADA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdVolumeAguaTratadaIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdVolumeAguaTratada is not null
        defaultIndicadorProducaoShouldBeFound("qtdVolumeAguaTratada.specified=true");

        // Get all the indicadorProducaoList where qtdVolumeAguaTratada is null
        defaultIndicadorProducaoShouldNotBeFound("qtdVolumeAguaTratada.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdVolumeAguaDistribuidaIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdVolumeAguaDistribuida equals to DEFAULT_QTD_VOLUME_AGUA_DISTRIBUIDA
        defaultIndicadorProducaoShouldBeFound("qtdVolumeAguaDistribuida.equals=" + DEFAULT_QTD_VOLUME_AGUA_DISTRIBUIDA);

        // Get all the indicadorProducaoList where qtdVolumeAguaDistribuida equals to UPDATED_QTD_VOLUME_AGUA_DISTRIBUIDA
        defaultIndicadorProducaoShouldNotBeFound("qtdVolumeAguaDistribuida.equals=" + UPDATED_QTD_VOLUME_AGUA_DISTRIBUIDA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdVolumeAguaDistribuidaIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdVolumeAguaDistribuida in DEFAULT_QTD_VOLUME_AGUA_DISTRIBUIDA or UPDATED_QTD_VOLUME_AGUA_DISTRIBUIDA
        defaultIndicadorProducaoShouldBeFound("qtdVolumeAguaDistribuida.in=" + DEFAULT_QTD_VOLUME_AGUA_DISTRIBUIDA + "," + UPDATED_QTD_VOLUME_AGUA_DISTRIBUIDA);

        // Get all the indicadorProducaoList where qtdVolumeAguaDistribuida equals to UPDATED_QTD_VOLUME_AGUA_DISTRIBUIDA
        defaultIndicadorProducaoShouldNotBeFound("qtdVolumeAguaDistribuida.in=" + UPDATED_QTD_VOLUME_AGUA_DISTRIBUIDA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdVolumeAguaDistribuidaIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdVolumeAguaDistribuida is not null
        defaultIndicadorProducaoShouldBeFound("qtdVolumeAguaDistribuida.specified=true");

        // Get all the indicadorProducaoList where qtdVolumeAguaDistribuida is null
        defaultIndicadorProducaoShouldNotBeFound("qtdVolumeAguaDistribuida.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdConsumoEnergiaIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdConsumoEnergia equals to DEFAULT_QTD_CONSUMO_ENERGIA
        defaultIndicadorProducaoShouldBeFound("qtdConsumoEnergia.equals=" + DEFAULT_QTD_CONSUMO_ENERGIA);

        // Get all the indicadorProducaoList where qtdConsumoEnergia equals to UPDATED_QTD_CONSUMO_ENERGIA
        defaultIndicadorProducaoShouldNotBeFound("qtdConsumoEnergia.equals=" + UPDATED_QTD_CONSUMO_ENERGIA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdConsumoEnergiaIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdConsumoEnergia in DEFAULT_QTD_CONSUMO_ENERGIA or UPDATED_QTD_CONSUMO_ENERGIA
        defaultIndicadorProducaoShouldBeFound("qtdConsumoEnergia.in=" + DEFAULT_QTD_CONSUMO_ENERGIA + "," + UPDATED_QTD_CONSUMO_ENERGIA);

        // Get all the indicadorProducaoList where qtdConsumoEnergia equals to UPDATED_QTD_CONSUMO_ENERGIA
        defaultIndicadorProducaoShouldNotBeFound("qtdConsumoEnergia.in=" + UPDATED_QTD_CONSUMO_ENERGIA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdConsumoEnergiaIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdConsumoEnergia is not null
        defaultIndicadorProducaoShouldBeFound("qtdConsumoEnergia.specified=true");

        // Get all the indicadorProducaoList where qtdConsumoEnergia is null
        defaultIndicadorProducaoShouldNotBeFound("qtdConsumoEnergia.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdConsumoCombustivelIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdConsumoCombustivel equals to DEFAULT_QTD_CONSUMO_COMBUSTIVEL
        defaultIndicadorProducaoShouldBeFound("qtdConsumoCombustivel.equals=" + DEFAULT_QTD_CONSUMO_COMBUSTIVEL);

        // Get all the indicadorProducaoList where qtdConsumoCombustivel equals to UPDATED_QTD_CONSUMO_COMBUSTIVEL
        defaultIndicadorProducaoShouldNotBeFound("qtdConsumoCombustivel.equals=" + UPDATED_QTD_CONSUMO_COMBUSTIVEL);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdConsumoCombustivelIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdConsumoCombustivel in DEFAULT_QTD_CONSUMO_COMBUSTIVEL or UPDATED_QTD_CONSUMO_COMBUSTIVEL
        defaultIndicadorProducaoShouldBeFound("qtdConsumoCombustivel.in=" + DEFAULT_QTD_CONSUMO_COMBUSTIVEL + "," + UPDATED_QTD_CONSUMO_COMBUSTIVEL);

        // Get all the indicadorProducaoList where qtdConsumoCombustivel equals to UPDATED_QTD_CONSUMO_COMBUSTIVEL
        defaultIndicadorProducaoShouldNotBeFound("qtdConsumoCombustivel.in=" + UPDATED_QTD_CONSUMO_COMBUSTIVEL);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdConsumoCombustivelIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdConsumoCombustivel is not null
        defaultIndicadorProducaoShouldBeFound("qtdConsumoCombustivel.specified=true");

        // Get all the indicadorProducaoList where qtdConsumoCombustivel is null
        defaultIndicadorProducaoShouldNotBeFound("qtdConsumoCombustivel.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdConsumoHipocloritroCalcioIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdConsumoHipocloritroCalcio equals to DEFAULT_QTD_CONSUMO_HIPOCLORITRO_CALCIO
        defaultIndicadorProducaoShouldBeFound("qtdConsumoHipocloritroCalcio.equals=" + DEFAULT_QTD_CONSUMO_HIPOCLORITRO_CALCIO);

        // Get all the indicadorProducaoList where qtdConsumoHipocloritroCalcio equals to UPDATED_QTD_CONSUMO_HIPOCLORITRO_CALCIO
        defaultIndicadorProducaoShouldNotBeFound("qtdConsumoHipocloritroCalcio.equals=" + UPDATED_QTD_CONSUMO_HIPOCLORITRO_CALCIO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdConsumoHipocloritroCalcioIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdConsumoHipocloritroCalcio in DEFAULT_QTD_CONSUMO_HIPOCLORITRO_CALCIO or UPDATED_QTD_CONSUMO_HIPOCLORITRO_CALCIO
        defaultIndicadorProducaoShouldBeFound("qtdConsumoHipocloritroCalcio.in=" + DEFAULT_QTD_CONSUMO_HIPOCLORITRO_CALCIO + "," + UPDATED_QTD_CONSUMO_HIPOCLORITRO_CALCIO);

        // Get all the indicadorProducaoList where qtdConsumoHipocloritroCalcio equals to UPDATED_QTD_CONSUMO_HIPOCLORITRO_CALCIO
        defaultIndicadorProducaoShouldNotBeFound("qtdConsumoHipocloritroCalcio.in=" + UPDATED_QTD_CONSUMO_HIPOCLORITRO_CALCIO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdConsumoHipocloritroCalcioIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdConsumoHipocloritroCalcio is not null
        defaultIndicadorProducaoShouldBeFound("qtdConsumoHipocloritroCalcio.specified=true");

        // Get all the indicadorProducaoList where qtdConsumoHipocloritroCalcio is null
        defaultIndicadorProducaoShouldNotBeFound("qtdConsumoHipocloritroCalcio.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdConsumoSulfatoAluminioIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdConsumoSulfatoAluminio equals to DEFAULT_QTD_CONSUMO_SULFATO_ALUMINIO
        defaultIndicadorProducaoShouldBeFound("qtdConsumoSulfatoAluminio.equals=" + DEFAULT_QTD_CONSUMO_SULFATO_ALUMINIO);

        // Get all the indicadorProducaoList where qtdConsumoSulfatoAluminio equals to UPDATED_QTD_CONSUMO_SULFATO_ALUMINIO
        defaultIndicadorProducaoShouldNotBeFound("qtdConsumoSulfatoAluminio.equals=" + UPDATED_QTD_CONSUMO_SULFATO_ALUMINIO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdConsumoSulfatoAluminioIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdConsumoSulfatoAluminio in DEFAULT_QTD_CONSUMO_SULFATO_ALUMINIO or UPDATED_QTD_CONSUMO_SULFATO_ALUMINIO
        defaultIndicadorProducaoShouldBeFound("qtdConsumoSulfatoAluminio.in=" + DEFAULT_QTD_CONSUMO_SULFATO_ALUMINIO + "," + UPDATED_QTD_CONSUMO_SULFATO_ALUMINIO);

        // Get all the indicadorProducaoList where qtdConsumoSulfatoAluminio equals to UPDATED_QTD_CONSUMO_SULFATO_ALUMINIO
        defaultIndicadorProducaoShouldNotBeFound("qtdConsumoSulfatoAluminio.in=" + UPDATED_QTD_CONSUMO_SULFATO_ALUMINIO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdConsumoSulfatoAluminioIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdConsumoSulfatoAluminio is not null
        defaultIndicadorProducaoShouldBeFound("qtdConsumoSulfatoAluminio.specified=true");

        // Get all the indicadorProducaoList where qtdConsumoSulfatoAluminio is null
        defaultIndicadorProducaoShouldNotBeFound("qtdConsumoSulfatoAluminio.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdConsumoHidroxidoCalcioIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdConsumoHidroxidoCalcio equals to DEFAULT_QTD_CONSUMO_HIDROXIDO_CALCIO
        defaultIndicadorProducaoShouldBeFound("qtdConsumoHidroxidoCalcio.equals=" + DEFAULT_QTD_CONSUMO_HIDROXIDO_CALCIO);

        // Get all the indicadorProducaoList where qtdConsumoHidroxidoCalcio equals to UPDATED_QTD_CONSUMO_HIDROXIDO_CALCIO
        defaultIndicadorProducaoShouldNotBeFound("qtdConsumoHidroxidoCalcio.equals=" + UPDATED_QTD_CONSUMO_HIDROXIDO_CALCIO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdConsumoHidroxidoCalcioIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdConsumoHidroxidoCalcio in DEFAULT_QTD_CONSUMO_HIDROXIDO_CALCIO or UPDATED_QTD_CONSUMO_HIDROXIDO_CALCIO
        defaultIndicadorProducaoShouldBeFound("qtdConsumoHidroxidoCalcio.in=" + DEFAULT_QTD_CONSUMO_HIDROXIDO_CALCIO + "," + UPDATED_QTD_CONSUMO_HIDROXIDO_CALCIO);

        // Get all the indicadorProducaoList where qtdConsumoHidroxidoCalcio equals to UPDATED_QTD_CONSUMO_HIDROXIDO_CALCIO
        defaultIndicadorProducaoShouldNotBeFound("qtdConsumoHidroxidoCalcio.in=" + UPDATED_QTD_CONSUMO_HIDROXIDO_CALCIO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdConsumoHidroxidoCalcioIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdConsumoHidroxidoCalcio is not null
        defaultIndicadorProducaoShouldBeFound("qtdConsumoHidroxidoCalcio.specified=true");

        // Get all the indicadorProducaoList where qtdConsumoHidroxidoCalcio is null
        defaultIndicadorProducaoShouldNotBeFound("qtdConsumoHidroxidoCalcio.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReparoCaptacaoEtasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReparoCaptacaoEtas equals to DEFAULT_QTD_REPARO_CAPTACAO_ETAS
        defaultIndicadorProducaoShouldBeFound("qtdReparoCaptacaoEtas.equals=" + DEFAULT_QTD_REPARO_CAPTACAO_ETAS);

        // Get all the indicadorProducaoList where qtdReparoCaptacaoEtas equals to UPDATED_QTD_REPARO_CAPTACAO_ETAS
        defaultIndicadorProducaoShouldNotBeFound("qtdReparoCaptacaoEtas.equals=" + UPDATED_QTD_REPARO_CAPTACAO_ETAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReparoCaptacaoEtasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReparoCaptacaoEtas in DEFAULT_QTD_REPARO_CAPTACAO_ETAS or UPDATED_QTD_REPARO_CAPTACAO_ETAS
        defaultIndicadorProducaoShouldBeFound("qtdReparoCaptacaoEtas.in=" + DEFAULT_QTD_REPARO_CAPTACAO_ETAS + "," + UPDATED_QTD_REPARO_CAPTACAO_ETAS);

        // Get all the indicadorProducaoList where qtdReparoCaptacaoEtas equals to UPDATED_QTD_REPARO_CAPTACAO_ETAS
        defaultIndicadorProducaoShouldNotBeFound("qtdReparoCaptacaoEtas.in=" + UPDATED_QTD_REPARO_CAPTACAO_ETAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReparoCaptacaoEtasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReparoCaptacaoEtas is not null
        defaultIndicadorProducaoShouldBeFound("qtdReparoCaptacaoEtas.specified=true");

        // Get all the indicadorProducaoList where qtdReparoCaptacaoEtas is null
        defaultIndicadorProducaoShouldNotBeFound("qtdReparoCaptacaoEtas.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReparoCaptacaoEtasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReparoCaptacaoEtas greater than or equals to DEFAULT_QTD_REPARO_CAPTACAO_ETAS
        defaultIndicadorProducaoShouldBeFound("qtdReparoCaptacaoEtas.greaterOrEqualThan=" + DEFAULT_QTD_REPARO_CAPTACAO_ETAS);

        // Get all the indicadorProducaoList where qtdReparoCaptacaoEtas greater than or equals to UPDATED_QTD_REPARO_CAPTACAO_ETAS
        defaultIndicadorProducaoShouldNotBeFound("qtdReparoCaptacaoEtas.greaterOrEqualThan=" + UPDATED_QTD_REPARO_CAPTACAO_ETAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReparoCaptacaoEtasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReparoCaptacaoEtas less than or equals to DEFAULT_QTD_REPARO_CAPTACAO_ETAS
        defaultIndicadorProducaoShouldNotBeFound("qtdReparoCaptacaoEtas.lessThan=" + DEFAULT_QTD_REPARO_CAPTACAO_ETAS);

        // Get all the indicadorProducaoList where qtdReparoCaptacaoEtas less than or equals to UPDATED_QTD_REPARO_CAPTACAO_ETAS
        defaultIndicadorProducaoShouldBeFound("qtdReparoCaptacaoEtas.lessThan=" + UPDATED_QTD_REPARO_CAPTACAO_ETAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReparoAdutorasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReparoAdutoras equals to DEFAULT_QTD_REPARO_ADUTORAS
        defaultIndicadorProducaoShouldBeFound("qtdReparoAdutoras.equals=" + DEFAULT_QTD_REPARO_ADUTORAS);

        // Get all the indicadorProducaoList where qtdReparoAdutoras equals to UPDATED_QTD_REPARO_ADUTORAS
        defaultIndicadorProducaoShouldNotBeFound("qtdReparoAdutoras.equals=" + UPDATED_QTD_REPARO_ADUTORAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReparoAdutorasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReparoAdutoras in DEFAULT_QTD_REPARO_ADUTORAS or UPDATED_QTD_REPARO_ADUTORAS
        defaultIndicadorProducaoShouldBeFound("qtdReparoAdutoras.in=" + DEFAULT_QTD_REPARO_ADUTORAS + "," + UPDATED_QTD_REPARO_ADUTORAS);

        // Get all the indicadorProducaoList where qtdReparoAdutoras equals to UPDATED_QTD_REPARO_ADUTORAS
        defaultIndicadorProducaoShouldNotBeFound("qtdReparoAdutoras.in=" + UPDATED_QTD_REPARO_ADUTORAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReparoAdutorasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReparoAdutoras is not null
        defaultIndicadorProducaoShouldBeFound("qtdReparoAdutoras.specified=true");

        // Get all the indicadorProducaoList where qtdReparoAdutoras is null
        defaultIndicadorProducaoShouldNotBeFound("qtdReparoAdutoras.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReparoAdutorasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReparoAdutoras greater than or equals to DEFAULT_QTD_REPARO_ADUTORAS
        defaultIndicadorProducaoShouldBeFound("qtdReparoAdutoras.greaterOrEqualThan=" + DEFAULT_QTD_REPARO_ADUTORAS);

        // Get all the indicadorProducaoList where qtdReparoAdutoras greater than or equals to UPDATED_QTD_REPARO_ADUTORAS
        defaultIndicadorProducaoShouldNotBeFound("qtdReparoAdutoras.greaterOrEqualThan=" + UPDATED_QTD_REPARO_ADUTORAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReparoAdutorasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReparoAdutoras less than or equals to DEFAULT_QTD_REPARO_ADUTORAS
        defaultIndicadorProducaoShouldNotBeFound("qtdReparoAdutoras.lessThan=" + DEFAULT_QTD_REPARO_ADUTORAS);

        // Get all the indicadorProducaoList where qtdReparoAdutoras less than or equals to UPDATED_QTD_REPARO_ADUTORAS
        defaultIndicadorProducaoShouldBeFound("qtdReparoAdutoras.lessThan=" + UPDATED_QTD_REPARO_ADUTORAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReparoRedeDistribuicaoIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReparoRedeDistribuicao equals to DEFAULT_QTD_REPARO_REDE_DISTRIBUICAO
        defaultIndicadorProducaoShouldBeFound("qtdReparoRedeDistribuicao.equals=" + DEFAULT_QTD_REPARO_REDE_DISTRIBUICAO);

        // Get all the indicadorProducaoList where qtdReparoRedeDistribuicao equals to UPDATED_QTD_REPARO_REDE_DISTRIBUICAO
        defaultIndicadorProducaoShouldNotBeFound("qtdReparoRedeDistribuicao.equals=" + UPDATED_QTD_REPARO_REDE_DISTRIBUICAO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReparoRedeDistribuicaoIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReparoRedeDistribuicao in DEFAULT_QTD_REPARO_REDE_DISTRIBUICAO or UPDATED_QTD_REPARO_REDE_DISTRIBUICAO
        defaultIndicadorProducaoShouldBeFound("qtdReparoRedeDistribuicao.in=" + DEFAULT_QTD_REPARO_REDE_DISTRIBUICAO + "," + UPDATED_QTD_REPARO_REDE_DISTRIBUICAO);

        // Get all the indicadorProducaoList where qtdReparoRedeDistribuicao equals to UPDATED_QTD_REPARO_REDE_DISTRIBUICAO
        defaultIndicadorProducaoShouldNotBeFound("qtdReparoRedeDistribuicao.in=" + UPDATED_QTD_REPARO_REDE_DISTRIBUICAO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReparoRedeDistribuicaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReparoRedeDistribuicao is not null
        defaultIndicadorProducaoShouldBeFound("qtdReparoRedeDistribuicao.specified=true");

        // Get all the indicadorProducaoList where qtdReparoRedeDistribuicao is null
        defaultIndicadorProducaoShouldNotBeFound("qtdReparoRedeDistribuicao.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReparoRedeDistribuicaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReparoRedeDistribuicao greater than or equals to DEFAULT_QTD_REPARO_REDE_DISTRIBUICAO
        defaultIndicadorProducaoShouldBeFound("qtdReparoRedeDistribuicao.greaterOrEqualThan=" + DEFAULT_QTD_REPARO_REDE_DISTRIBUICAO);

        // Get all the indicadorProducaoList where qtdReparoRedeDistribuicao greater than or equals to UPDATED_QTD_REPARO_REDE_DISTRIBUICAO
        defaultIndicadorProducaoShouldNotBeFound("qtdReparoRedeDistribuicao.greaterOrEqualThan=" + UPDATED_QTD_REPARO_REDE_DISTRIBUICAO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReparoRedeDistribuicaoIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReparoRedeDistribuicao less than or equals to DEFAULT_QTD_REPARO_REDE_DISTRIBUICAO
        defaultIndicadorProducaoShouldNotBeFound("qtdReparoRedeDistribuicao.lessThan=" + DEFAULT_QTD_REPARO_REDE_DISTRIBUICAO);

        // Get all the indicadorProducaoList where qtdReparoRedeDistribuicao less than or equals to UPDATED_QTD_REPARO_REDE_DISTRIBUICAO
        defaultIndicadorProducaoShouldBeFound("qtdReparoRedeDistribuicao.lessThan=" + UPDATED_QTD_REPARO_REDE_DISTRIBUICAO);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReparoRamaisIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReparoRamais equals to DEFAULT_QTD_REPARO_RAMAIS
        defaultIndicadorProducaoShouldBeFound("qtdReparoRamais.equals=" + DEFAULT_QTD_REPARO_RAMAIS);

        // Get all the indicadorProducaoList where qtdReparoRamais equals to UPDATED_QTD_REPARO_RAMAIS
        defaultIndicadorProducaoShouldNotBeFound("qtdReparoRamais.equals=" + UPDATED_QTD_REPARO_RAMAIS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReparoRamaisIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReparoRamais in DEFAULT_QTD_REPARO_RAMAIS or UPDATED_QTD_REPARO_RAMAIS
        defaultIndicadorProducaoShouldBeFound("qtdReparoRamais.in=" + DEFAULT_QTD_REPARO_RAMAIS + "," + UPDATED_QTD_REPARO_RAMAIS);

        // Get all the indicadorProducaoList where qtdReparoRamais equals to UPDATED_QTD_REPARO_RAMAIS
        defaultIndicadorProducaoShouldNotBeFound("qtdReparoRamais.in=" + UPDATED_QTD_REPARO_RAMAIS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReparoRamaisIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReparoRamais is not null
        defaultIndicadorProducaoShouldBeFound("qtdReparoRamais.specified=true");

        // Get all the indicadorProducaoList where qtdReparoRamais is null
        defaultIndicadorProducaoShouldNotBeFound("qtdReparoRamais.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReparoRamaisIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReparoRamais greater than or equals to DEFAULT_QTD_REPARO_RAMAIS
        defaultIndicadorProducaoShouldBeFound("qtdReparoRamais.greaterOrEqualThan=" + DEFAULT_QTD_REPARO_RAMAIS);

        // Get all the indicadorProducaoList where qtdReparoRamais greater than or equals to UPDATED_QTD_REPARO_RAMAIS
        defaultIndicadorProducaoShouldNotBeFound("qtdReparoRamais.greaterOrEqualThan=" + UPDATED_QTD_REPARO_RAMAIS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReparoRamaisIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReparoRamais less than or equals to DEFAULT_QTD_REPARO_RAMAIS
        defaultIndicadorProducaoShouldNotBeFound("qtdReparoRamais.lessThan=" + DEFAULT_QTD_REPARO_RAMAIS);

        // Get all the indicadorProducaoList where qtdReparoRamais less than or equals to UPDATED_QTD_REPARO_RAMAIS
        defaultIndicadorProducaoShouldBeFound("qtdReparoRamais.lessThan=" + UPDATED_QTD_REPARO_RAMAIS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManutencaoCurativaIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManutencaoCurativa equals to DEFAULT_QTD_MANUTENCAO_CURATIVA
        defaultIndicadorProducaoShouldBeFound("qtdManutencaoCurativa.equals=" + DEFAULT_QTD_MANUTENCAO_CURATIVA);

        // Get all the indicadorProducaoList where qtdManutencaoCurativa equals to UPDATED_QTD_MANUTENCAO_CURATIVA
        defaultIndicadorProducaoShouldNotBeFound("qtdManutencaoCurativa.equals=" + UPDATED_QTD_MANUTENCAO_CURATIVA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManutencaoCurativaIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManutencaoCurativa in DEFAULT_QTD_MANUTENCAO_CURATIVA or UPDATED_QTD_MANUTENCAO_CURATIVA
        defaultIndicadorProducaoShouldBeFound("qtdManutencaoCurativa.in=" + DEFAULT_QTD_MANUTENCAO_CURATIVA + "," + UPDATED_QTD_MANUTENCAO_CURATIVA);

        // Get all the indicadorProducaoList where qtdManutencaoCurativa equals to UPDATED_QTD_MANUTENCAO_CURATIVA
        defaultIndicadorProducaoShouldNotBeFound("qtdManutencaoCurativa.in=" + UPDATED_QTD_MANUTENCAO_CURATIVA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManutencaoCurativaIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManutencaoCurativa is not null
        defaultIndicadorProducaoShouldBeFound("qtdManutencaoCurativa.specified=true");

        // Get all the indicadorProducaoList where qtdManutencaoCurativa is null
        defaultIndicadorProducaoShouldNotBeFound("qtdManutencaoCurativa.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManutencaoCurativaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManutencaoCurativa greater than or equals to DEFAULT_QTD_MANUTENCAO_CURATIVA
        defaultIndicadorProducaoShouldBeFound("qtdManutencaoCurativa.greaterOrEqualThan=" + DEFAULT_QTD_MANUTENCAO_CURATIVA);

        // Get all the indicadorProducaoList where qtdManutencaoCurativa greater than or equals to UPDATED_QTD_MANUTENCAO_CURATIVA
        defaultIndicadorProducaoShouldNotBeFound("qtdManutencaoCurativa.greaterOrEqualThan=" + UPDATED_QTD_MANUTENCAO_CURATIVA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManutencaoCurativaIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManutencaoCurativa less than or equals to DEFAULT_QTD_MANUTENCAO_CURATIVA
        defaultIndicadorProducaoShouldNotBeFound("qtdManutencaoCurativa.lessThan=" + DEFAULT_QTD_MANUTENCAO_CURATIVA);

        // Get all the indicadorProducaoList where qtdManutencaoCurativa less than or equals to UPDATED_QTD_MANUTENCAO_CURATIVA
        defaultIndicadorProducaoShouldBeFound("qtdManutencaoCurativa.lessThan=" + UPDATED_QTD_MANUTENCAO_CURATIVA);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManutencaoPreventivaIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManutencaoPreventiva equals to DEFAULT_QTD_MANUTENCAO_PREVENTIVA
        defaultIndicadorProducaoShouldBeFound("qtdManutencaoPreventiva.equals=" + DEFAULT_QTD_MANUTENCAO_PREVENTIVA);

        // Get all the indicadorProducaoList where qtdManutencaoPreventiva equals to UPDATED_QTD_MANUTENCAO_PREVENTIVA
        defaultIndicadorProducaoShouldNotBeFound("qtdManutencaoPreventiva.equals=" + UPDATED_QTD_MANUTENCAO_PREVENTIVA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManutencaoPreventivaIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManutencaoPreventiva in DEFAULT_QTD_MANUTENCAO_PREVENTIVA or UPDATED_QTD_MANUTENCAO_PREVENTIVA
        defaultIndicadorProducaoShouldBeFound("qtdManutencaoPreventiva.in=" + DEFAULT_QTD_MANUTENCAO_PREVENTIVA + "," + UPDATED_QTD_MANUTENCAO_PREVENTIVA);

        // Get all the indicadorProducaoList where qtdManutencaoPreventiva equals to UPDATED_QTD_MANUTENCAO_PREVENTIVA
        defaultIndicadorProducaoShouldNotBeFound("qtdManutencaoPreventiva.in=" + UPDATED_QTD_MANUTENCAO_PREVENTIVA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManutencaoPreventivaIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManutencaoPreventiva is not null
        defaultIndicadorProducaoShouldBeFound("qtdManutencaoPreventiva.specified=true");

        // Get all the indicadorProducaoList where qtdManutencaoPreventiva is null
        defaultIndicadorProducaoShouldNotBeFound("qtdManutencaoPreventiva.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManutencaoPreventivaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManutencaoPreventiva greater than or equals to DEFAULT_QTD_MANUTENCAO_PREVENTIVA
        defaultIndicadorProducaoShouldBeFound("qtdManutencaoPreventiva.greaterOrEqualThan=" + DEFAULT_QTD_MANUTENCAO_PREVENTIVA);

        // Get all the indicadorProducaoList where qtdManutencaoPreventiva greater than or equals to UPDATED_QTD_MANUTENCAO_PREVENTIVA
        defaultIndicadorProducaoShouldNotBeFound("qtdManutencaoPreventiva.greaterOrEqualThan=" + UPDATED_QTD_MANUTENCAO_PREVENTIVA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManutencaoPreventivaIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManutencaoPreventiva less than or equals to DEFAULT_QTD_MANUTENCAO_PREVENTIVA
        defaultIndicadorProducaoShouldNotBeFound("qtdManutencaoPreventiva.lessThan=" + DEFAULT_QTD_MANUTENCAO_PREVENTIVA);

        // Get all the indicadorProducaoList where qtdManutencaoPreventiva less than or equals to UPDATED_QTD_MANUTENCAO_PREVENTIVA
        defaultIndicadorProducaoShouldBeFound("qtdManutencaoPreventiva.lessThan=" + UPDATED_QTD_MANUTENCAO_PREVENTIVA);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManutencaoVerificadoSolicitadoIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManutencaoVerificadoSolicitado equals to DEFAULT_QTD_MANUTENCAO_VERIFICADO_SOLICITADO
        defaultIndicadorProducaoShouldBeFound("qtdManutencaoVerificadoSolicitado.equals=" + DEFAULT_QTD_MANUTENCAO_VERIFICADO_SOLICITADO);

        // Get all the indicadorProducaoList where qtdManutencaoVerificadoSolicitado equals to UPDATED_QTD_MANUTENCAO_VERIFICADO_SOLICITADO
        defaultIndicadorProducaoShouldNotBeFound("qtdManutencaoVerificadoSolicitado.equals=" + UPDATED_QTD_MANUTENCAO_VERIFICADO_SOLICITADO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManutencaoVerificadoSolicitadoIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManutencaoVerificadoSolicitado in DEFAULT_QTD_MANUTENCAO_VERIFICADO_SOLICITADO or UPDATED_QTD_MANUTENCAO_VERIFICADO_SOLICITADO
        defaultIndicadorProducaoShouldBeFound("qtdManutencaoVerificadoSolicitado.in=" + DEFAULT_QTD_MANUTENCAO_VERIFICADO_SOLICITADO + "," + UPDATED_QTD_MANUTENCAO_VERIFICADO_SOLICITADO);

        // Get all the indicadorProducaoList where qtdManutencaoVerificadoSolicitado equals to UPDATED_QTD_MANUTENCAO_VERIFICADO_SOLICITADO
        defaultIndicadorProducaoShouldNotBeFound("qtdManutencaoVerificadoSolicitado.in=" + UPDATED_QTD_MANUTENCAO_VERIFICADO_SOLICITADO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManutencaoVerificadoSolicitadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManutencaoVerificadoSolicitado is not null
        defaultIndicadorProducaoShouldBeFound("qtdManutencaoVerificadoSolicitado.specified=true");

        // Get all the indicadorProducaoList where qtdManutencaoVerificadoSolicitado is null
        defaultIndicadorProducaoShouldNotBeFound("qtdManutencaoVerificadoSolicitado.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManutencaoVerificadoSolicitadoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManutencaoVerificadoSolicitado greater than or equals to DEFAULT_QTD_MANUTENCAO_VERIFICADO_SOLICITADO
        defaultIndicadorProducaoShouldBeFound("qtdManutencaoVerificadoSolicitado.greaterOrEqualThan=" + DEFAULT_QTD_MANUTENCAO_VERIFICADO_SOLICITADO);

        // Get all the indicadorProducaoList where qtdManutencaoVerificadoSolicitado greater than or equals to UPDATED_QTD_MANUTENCAO_VERIFICADO_SOLICITADO
        defaultIndicadorProducaoShouldNotBeFound("qtdManutencaoVerificadoSolicitado.greaterOrEqualThan=" + UPDATED_QTD_MANUTENCAO_VERIFICADO_SOLICITADO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManutencaoVerificadoSolicitadoIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManutencaoVerificadoSolicitado less than or equals to DEFAULT_QTD_MANUTENCAO_VERIFICADO_SOLICITADO
        defaultIndicadorProducaoShouldNotBeFound("qtdManutencaoVerificadoSolicitado.lessThan=" + DEFAULT_QTD_MANUTENCAO_VERIFICADO_SOLICITADO);

        // Get all the indicadorProducaoList where qtdManutencaoVerificadoSolicitado less than or equals to UPDATED_QTD_MANUTENCAO_VERIFICADO_SOLICITADO
        defaultIndicadorProducaoShouldBeFound("qtdManutencaoVerificadoSolicitado.lessThan=" + UPDATED_QTD_MANUTENCAO_VERIFICADO_SOLICITADO);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReservatorioLavadoIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReservatorioLavado equals to DEFAULT_QTD_RESERVATORIO_LAVADO
        defaultIndicadorProducaoShouldBeFound("qtdReservatorioLavado.equals=" + DEFAULT_QTD_RESERVATORIO_LAVADO);

        // Get all the indicadorProducaoList where qtdReservatorioLavado equals to UPDATED_QTD_RESERVATORIO_LAVADO
        defaultIndicadorProducaoShouldNotBeFound("qtdReservatorioLavado.equals=" + UPDATED_QTD_RESERVATORIO_LAVADO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReservatorioLavadoIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReservatorioLavado in DEFAULT_QTD_RESERVATORIO_LAVADO or UPDATED_QTD_RESERVATORIO_LAVADO
        defaultIndicadorProducaoShouldBeFound("qtdReservatorioLavado.in=" + DEFAULT_QTD_RESERVATORIO_LAVADO + "," + UPDATED_QTD_RESERVATORIO_LAVADO);

        // Get all the indicadorProducaoList where qtdReservatorioLavado equals to UPDATED_QTD_RESERVATORIO_LAVADO
        defaultIndicadorProducaoShouldNotBeFound("qtdReservatorioLavado.in=" + UPDATED_QTD_RESERVATORIO_LAVADO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReservatorioLavadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReservatorioLavado is not null
        defaultIndicadorProducaoShouldBeFound("qtdReservatorioLavado.specified=true");

        // Get all the indicadorProducaoList where qtdReservatorioLavado is null
        defaultIndicadorProducaoShouldNotBeFound("qtdReservatorioLavado.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReservatorioLavadoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReservatorioLavado greater than or equals to DEFAULT_QTD_RESERVATORIO_LAVADO
        defaultIndicadorProducaoShouldBeFound("qtdReservatorioLavado.greaterOrEqualThan=" + DEFAULT_QTD_RESERVATORIO_LAVADO);

        // Get all the indicadorProducaoList where qtdReservatorioLavado greater than or equals to UPDATED_QTD_RESERVATORIO_LAVADO
        defaultIndicadorProducaoShouldNotBeFound("qtdReservatorioLavado.greaterOrEqualThan=" + UPDATED_QTD_RESERVATORIO_LAVADO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReservatorioLavadoIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReservatorioLavado less than or equals to DEFAULT_QTD_RESERVATORIO_LAVADO
        defaultIndicadorProducaoShouldNotBeFound("qtdReservatorioLavado.lessThan=" + DEFAULT_QTD_RESERVATORIO_LAVADO);

        // Get all the indicadorProducaoList where qtdReservatorioLavado less than or equals to UPDATED_QTD_RESERVATORIO_LAVADO
        defaultIndicadorProducaoShouldBeFound("qtdReservatorioLavado.lessThan=" + UPDATED_QTD_RESERVATORIO_LAVADO);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFuncionariosIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFuncionarios equals to DEFAULT_QTD_FUNCIONARIOS
        defaultIndicadorProducaoShouldBeFound("qtdFuncionarios.equals=" + DEFAULT_QTD_FUNCIONARIOS);

        // Get all the indicadorProducaoList where qtdFuncionarios equals to UPDATED_QTD_FUNCIONARIOS
        defaultIndicadorProducaoShouldNotBeFound("qtdFuncionarios.equals=" + UPDATED_QTD_FUNCIONARIOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFuncionariosIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFuncionarios in DEFAULT_QTD_FUNCIONARIOS or UPDATED_QTD_FUNCIONARIOS
        defaultIndicadorProducaoShouldBeFound("qtdFuncionarios.in=" + DEFAULT_QTD_FUNCIONARIOS + "," + UPDATED_QTD_FUNCIONARIOS);

        // Get all the indicadorProducaoList where qtdFuncionarios equals to UPDATED_QTD_FUNCIONARIOS
        defaultIndicadorProducaoShouldNotBeFound("qtdFuncionarios.in=" + UPDATED_QTD_FUNCIONARIOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFuncionariosIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFuncionarios is not null
        defaultIndicadorProducaoShouldBeFound("qtdFuncionarios.specified=true");

        // Get all the indicadorProducaoList where qtdFuncionarios is null
        defaultIndicadorProducaoShouldNotBeFound("qtdFuncionarios.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFuncionariosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFuncionarios greater than or equals to DEFAULT_QTD_FUNCIONARIOS
        defaultIndicadorProducaoShouldBeFound("qtdFuncionarios.greaterOrEqualThan=" + DEFAULT_QTD_FUNCIONARIOS);

        // Get all the indicadorProducaoList where qtdFuncionarios greater than or equals to UPDATED_QTD_FUNCIONARIOS
        defaultIndicadorProducaoShouldNotBeFound("qtdFuncionarios.greaterOrEqualThan=" + UPDATED_QTD_FUNCIONARIOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFuncionariosIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFuncionarios less than or equals to DEFAULT_QTD_FUNCIONARIOS
        defaultIndicadorProducaoShouldNotBeFound("qtdFuncionarios.lessThan=" + DEFAULT_QTD_FUNCIONARIOS);

        // Get all the indicadorProducaoList where qtdFuncionarios less than or equals to UPDATED_QTD_FUNCIONARIOS
        defaultIndicadorProducaoShouldBeFound("qtdFuncionarios.lessThan=" + UPDATED_QTD_FUNCIONARIOS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFuncionariosEfectivosIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFuncionariosEfectivos equals to DEFAULT_QTD_FUNCIONARIOS_EFECTIVOS
        defaultIndicadorProducaoShouldBeFound("qtdFuncionariosEfectivos.equals=" + DEFAULT_QTD_FUNCIONARIOS_EFECTIVOS);

        // Get all the indicadorProducaoList where qtdFuncionariosEfectivos equals to UPDATED_QTD_FUNCIONARIOS_EFECTIVOS
        defaultIndicadorProducaoShouldNotBeFound("qtdFuncionariosEfectivos.equals=" + UPDATED_QTD_FUNCIONARIOS_EFECTIVOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFuncionariosEfectivosIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFuncionariosEfectivos in DEFAULT_QTD_FUNCIONARIOS_EFECTIVOS or UPDATED_QTD_FUNCIONARIOS_EFECTIVOS
        defaultIndicadorProducaoShouldBeFound("qtdFuncionariosEfectivos.in=" + DEFAULT_QTD_FUNCIONARIOS_EFECTIVOS + "," + UPDATED_QTD_FUNCIONARIOS_EFECTIVOS);

        // Get all the indicadorProducaoList where qtdFuncionariosEfectivos equals to UPDATED_QTD_FUNCIONARIOS_EFECTIVOS
        defaultIndicadorProducaoShouldNotBeFound("qtdFuncionariosEfectivos.in=" + UPDATED_QTD_FUNCIONARIOS_EFECTIVOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFuncionariosEfectivosIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFuncionariosEfectivos is not null
        defaultIndicadorProducaoShouldBeFound("qtdFuncionariosEfectivos.specified=true");

        // Get all the indicadorProducaoList where qtdFuncionariosEfectivos is null
        defaultIndicadorProducaoShouldNotBeFound("qtdFuncionariosEfectivos.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFuncionariosEfectivosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFuncionariosEfectivos greater than or equals to DEFAULT_QTD_FUNCIONARIOS_EFECTIVOS
        defaultIndicadorProducaoShouldBeFound("qtdFuncionariosEfectivos.greaterOrEqualThan=" + DEFAULT_QTD_FUNCIONARIOS_EFECTIVOS);

        // Get all the indicadorProducaoList where qtdFuncionariosEfectivos greater than or equals to UPDATED_QTD_FUNCIONARIOS_EFECTIVOS
        defaultIndicadorProducaoShouldNotBeFound("qtdFuncionariosEfectivos.greaterOrEqualThan=" + UPDATED_QTD_FUNCIONARIOS_EFECTIVOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFuncionariosEfectivosIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFuncionariosEfectivos less than or equals to DEFAULT_QTD_FUNCIONARIOS_EFECTIVOS
        defaultIndicadorProducaoShouldNotBeFound("qtdFuncionariosEfectivos.lessThan=" + DEFAULT_QTD_FUNCIONARIOS_EFECTIVOS);

        // Get all the indicadorProducaoList where qtdFuncionariosEfectivos less than or equals to UPDATED_QTD_FUNCIONARIOS_EFECTIVOS
        defaultIndicadorProducaoShouldBeFound("qtdFuncionariosEfectivos.lessThan=" + UPDATED_QTD_FUNCIONARIOS_EFECTIVOS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFuncionariosContratadosIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFuncionariosContratados equals to DEFAULT_QTD_FUNCIONARIOS_CONTRATADOS
        defaultIndicadorProducaoShouldBeFound("qtdFuncionariosContratados.equals=" + DEFAULT_QTD_FUNCIONARIOS_CONTRATADOS);

        // Get all the indicadorProducaoList where qtdFuncionariosContratados equals to UPDATED_QTD_FUNCIONARIOS_CONTRATADOS
        defaultIndicadorProducaoShouldNotBeFound("qtdFuncionariosContratados.equals=" + UPDATED_QTD_FUNCIONARIOS_CONTRATADOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFuncionariosContratadosIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFuncionariosContratados in DEFAULT_QTD_FUNCIONARIOS_CONTRATADOS or UPDATED_QTD_FUNCIONARIOS_CONTRATADOS
        defaultIndicadorProducaoShouldBeFound("qtdFuncionariosContratados.in=" + DEFAULT_QTD_FUNCIONARIOS_CONTRATADOS + "," + UPDATED_QTD_FUNCIONARIOS_CONTRATADOS);

        // Get all the indicadorProducaoList where qtdFuncionariosContratados equals to UPDATED_QTD_FUNCIONARIOS_CONTRATADOS
        defaultIndicadorProducaoShouldNotBeFound("qtdFuncionariosContratados.in=" + UPDATED_QTD_FUNCIONARIOS_CONTRATADOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFuncionariosContratadosIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFuncionariosContratados is not null
        defaultIndicadorProducaoShouldBeFound("qtdFuncionariosContratados.specified=true");

        // Get all the indicadorProducaoList where qtdFuncionariosContratados is null
        defaultIndicadorProducaoShouldNotBeFound("qtdFuncionariosContratados.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFuncionariosContratadosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFuncionariosContratados greater than or equals to DEFAULT_QTD_FUNCIONARIOS_CONTRATADOS
        defaultIndicadorProducaoShouldBeFound("qtdFuncionariosContratados.greaterOrEqualThan=" + DEFAULT_QTD_FUNCIONARIOS_CONTRATADOS);

        // Get all the indicadorProducaoList where qtdFuncionariosContratados greater than or equals to UPDATED_QTD_FUNCIONARIOS_CONTRATADOS
        defaultIndicadorProducaoShouldNotBeFound("qtdFuncionariosContratados.greaterOrEqualThan=" + UPDATED_QTD_FUNCIONARIOS_CONTRATADOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFuncionariosContratadosIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFuncionariosContratados less than or equals to DEFAULT_QTD_FUNCIONARIOS_CONTRATADOS
        defaultIndicadorProducaoShouldNotBeFound("qtdFuncionariosContratados.lessThan=" + DEFAULT_QTD_FUNCIONARIOS_CONTRATADOS);

        // Get all the indicadorProducaoList where qtdFuncionariosContratados less than or equals to UPDATED_QTD_FUNCIONARIOS_CONTRATADOS
        defaultIndicadorProducaoShouldBeFound("qtdFuncionariosContratados.lessThan=" + UPDATED_QTD_FUNCIONARIOS_CONTRATADOS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFuncionariosOutrasEntidadesIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFuncionariosOutrasEntidades equals to DEFAULT_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES
        defaultIndicadorProducaoShouldBeFound("qtdFuncionariosOutrasEntidades.equals=" + DEFAULT_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES);

        // Get all the indicadorProducaoList where qtdFuncionariosOutrasEntidades equals to UPDATED_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES
        defaultIndicadorProducaoShouldNotBeFound("qtdFuncionariosOutrasEntidades.equals=" + UPDATED_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFuncionariosOutrasEntidadesIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFuncionariosOutrasEntidades in DEFAULT_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES or UPDATED_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES
        defaultIndicadorProducaoShouldBeFound("qtdFuncionariosOutrasEntidades.in=" + DEFAULT_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES + "," + UPDATED_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES);

        // Get all the indicadorProducaoList where qtdFuncionariosOutrasEntidades equals to UPDATED_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES
        defaultIndicadorProducaoShouldNotBeFound("qtdFuncionariosOutrasEntidades.in=" + UPDATED_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFuncionariosOutrasEntidadesIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFuncionariosOutrasEntidades is not null
        defaultIndicadorProducaoShouldBeFound("qtdFuncionariosOutrasEntidades.specified=true");

        // Get all the indicadorProducaoList where qtdFuncionariosOutrasEntidades is null
        defaultIndicadorProducaoShouldNotBeFound("qtdFuncionariosOutrasEntidades.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFuncionariosOutrasEntidadesIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFuncionariosOutrasEntidades greater than or equals to DEFAULT_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES
        defaultIndicadorProducaoShouldBeFound("qtdFuncionariosOutrasEntidades.greaterOrEqualThan=" + DEFAULT_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES);

        // Get all the indicadorProducaoList where qtdFuncionariosOutrasEntidades greater than or equals to UPDATED_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES
        defaultIndicadorProducaoShouldNotBeFound("qtdFuncionariosOutrasEntidades.greaterOrEqualThan=" + UPDATED_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdFuncionariosOutrasEntidadesIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdFuncionariosOutrasEntidades less than or equals to DEFAULT_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES
        defaultIndicadorProducaoShouldNotBeFound("qtdFuncionariosOutrasEntidades.lessThan=" + DEFAULT_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES);

        // Get all the indicadorProducaoList where qtdFuncionariosOutrasEntidades less than or equals to UPDATED_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES
        defaultIndicadorProducaoShouldBeFound("qtdFuncionariosOutrasEntidades.lessThan=" + UPDATED_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdNovasLigacoesNovosContratosIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdNovasLigacoesNovosContratos equals to DEFAULT_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS
        defaultIndicadorProducaoShouldBeFound("qtdNovasLigacoesNovosContratos.equals=" + DEFAULT_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS);

        // Get all the indicadorProducaoList where qtdNovasLigacoesNovosContratos equals to UPDATED_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS
        defaultIndicadorProducaoShouldNotBeFound("qtdNovasLigacoesNovosContratos.equals=" + UPDATED_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdNovasLigacoesNovosContratosIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdNovasLigacoesNovosContratos in DEFAULT_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS or UPDATED_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS
        defaultIndicadorProducaoShouldBeFound("qtdNovasLigacoesNovosContratos.in=" + DEFAULT_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS + "," + UPDATED_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS);

        // Get all the indicadorProducaoList where qtdNovasLigacoesNovosContratos equals to UPDATED_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS
        defaultIndicadorProducaoShouldNotBeFound("qtdNovasLigacoesNovosContratos.in=" + UPDATED_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdNovasLigacoesNovosContratosIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdNovasLigacoesNovosContratos is not null
        defaultIndicadorProducaoShouldBeFound("qtdNovasLigacoesNovosContratos.specified=true");

        // Get all the indicadorProducaoList where qtdNovasLigacoesNovosContratos is null
        defaultIndicadorProducaoShouldNotBeFound("qtdNovasLigacoesNovosContratos.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdNovasLigacoesNovosContratosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdNovasLigacoesNovosContratos greater than or equals to DEFAULT_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS
        defaultIndicadorProducaoShouldBeFound("qtdNovasLigacoesNovosContratos.greaterOrEqualThan=" + DEFAULT_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS);

        // Get all the indicadorProducaoList where qtdNovasLigacoesNovosContratos greater than or equals to UPDATED_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS
        defaultIndicadorProducaoShouldNotBeFound("qtdNovasLigacoesNovosContratos.greaterOrEqualThan=" + UPDATED_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdNovasLigacoesNovosContratosIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdNovasLigacoesNovosContratos less than or equals to DEFAULT_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS
        defaultIndicadorProducaoShouldNotBeFound("qtdNovasLigacoesNovosContratos.lessThan=" + DEFAULT_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS);

        // Get all the indicadorProducaoList where qtdNovasLigacoesNovosContratos less than or equals to UPDATED_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS
        defaultIndicadorProducaoShouldBeFound("qtdNovasLigacoesNovosContratos.lessThan=" + UPDATED_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdNovasLigacoesDomesticasNovosContratosIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdNovasLigacoesDomesticasNovosContratos equals to DEFAULT_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS
        defaultIndicadorProducaoShouldBeFound("qtdNovasLigacoesDomesticasNovosContratos.equals=" + DEFAULT_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS);

        // Get all the indicadorProducaoList where qtdNovasLigacoesDomesticasNovosContratos equals to UPDATED_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS
        defaultIndicadorProducaoShouldNotBeFound("qtdNovasLigacoesDomesticasNovosContratos.equals=" + UPDATED_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdNovasLigacoesDomesticasNovosContratosIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdNovasLigacoesDomesticasNovosContratos in DEFAULT_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS or UPDATED_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS
        defaultIndicadorProducaoShouldBeFound("qtdNovasLigacoesDomesticasNovosContratos.in=" + DEFAULT_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS + "," + UPDATED_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS);

        // Get all the indicadorProducaoList where qtdNovasLigacoesDomesticasNovosContratos equals to UPDATED_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS
        defaultIndicadorProducaoShouldNotBeFound("qtdNovasLigacoesDomesticasNovosContratos.in=" + UPDATED_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdNovasLigacoesDomesticasNovosContratosIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdNovasLigacoesDomesticasNovosContratos is not null
        defaultIndicadorProducaoShouldBeFound("qtdNovasLigacoesDomesticasNovosContratos.specified=true");

        // Get all the indicadorProducaoList where qtdNovasLigacoesDomesticasNovosContratos is null
        defaultIndicadorProducaoShouldNotBeFound("qtdNovasLigacoesDomesticasNovosContratos.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdNovasLigacoesDomesticasNovosContratosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdNovasLigacoesDomesticasNovosContratos greater than or equals to DEFAULT_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS
        defaultIndicadorProducaoShouldBeFound("qtdNovasLigacoesDomesticasNovosContratos.greaterOrEqualThan=" + DEFAULT_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS);

        // Get all the indicadorProducaoList where qtdNovasLigacoesDomesticasNovosContratos greater than or equals to UPDATED_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS
        defaultIndicadorProducaoShouldNotBeFound("qtdNovasLigacoesDomesticasNovosContratos.greaterOrEqualThan=" + UPDATED_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdNovasLigacoesDomesticasNovosContratosIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdNovasLigacoesDomesticasNovosContratos less than or equals to DEFAULT_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS
        defaultIndicadorProducaoShouldNotBeFound("qtdNovasLigacoesDomesticasNovosContratos.lessThan=" + DEFAULT_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS);

        // Get all the indicadorProducaoList where qtdNovasLigacoesDomesticasNovosContratos less than or equals to UPDATED_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS
        defaultIndicadorProducaoShouldBeFound("qtdNovasLigacoesDomesticasNovosContratos.lessThan=" + UPDATED_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesIlegaisRegularizadasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesIlegaisRegularizadas equals to DEFAULT_QTD_LIGACOES_ILEGAIS_REGULARIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesIlegaisRegularizadas.equals=" + DEFAULT_QTD_LIGACOES_ILEGAIS_REGULARIZADAS);

        // Get all the indicadorProducaoList where qtdLigacoesIlegaisRegularizadas equals to UPDATED_QTD_LIGACOES_ILEGAIS_REGULARIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesIlegaisRegularizadas.equals=" + UPDATED_QTD_LIGACOES_ILEGAIS_REGULARIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesIlegaisRegularizadasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesIlegaisRegularizadas in DEFAULT_QTD_LIGACOES_ILEGAIS_REGULARIZADAS or UPDATED_QTD_LIGACOES_ILEGAIS_REGULARIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesIlegaisRegularizadas.in=" + DEFAULT_QTD_LIGACOES_ILEGAIS_REGULARIZADAS + "," + UPDATED_QTD_LIGACOES_ILEGAIS_REGULARIZADAS);

        // Get all the indicadorProducaoList where qtdLigacoesIlegaisRegularizadas equals to UPDATED_QTD_LIGACOES_ILEGAIS_REGULARIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesIlegaisRegularizadas.in=" + UPDATED_QTD_LIGACOES_ILEGAIS_REGULARIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesIlegaisRegularizadasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesIlegaisRegularizadas is not null
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesIlegaisRegularizadas.specified=true");

        // Get all the indicadorProducaoList where qtdLigacoesIlegaisRegularizadas is null
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesIlegaisRegularizadas.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesIlegaisRegularizadasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesIlegaisRegularizadas greater than or equals to DEFAULT_QTD_LIGACOES_ILEGAIS_REGULARIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesIlegaisRegularizadas.greaterOrEqualThan=" + DEFAULT_QTD_LIGACOES_ILEGAIS_REGULARIZADAS);

        // Get all the indicadorProducaoList where qtdLigacoesIlegaisRegularizadas greater than or equals to UPDATED_QTD_LIGACOES_ILEGAIS_REGULARIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesIlegaisRegularizadas.greaterOrEqualThan=" + UPDATED_QTD_LIGACOES_ILEGAIS_REGULARIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesIlegaisRegularizadasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesIlegaisRegularizadas less than or equals to DEFAULT_QTD_LIGACOES_ILEGAIS_REGULARIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesIlegaisRegularizadas.lessThan=" + DEFAULT_QTD_LIGACOES_ILEGAIS_REGULARIZADAS);

        // Get all the indicadorProducaoList where qtdLigacoesIlegaisRegularizadas less than or equals to UPDATED_QTD_LIGACOES_ILEGAIS_REGULARIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesIlegaisRegularizadas.lessThan=" + UPDATED_QTD_LIGACOES_ILEGAIS_REGULARIZADAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesFechadasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesFechadas equals to DEFAULT_QTD_LIGACOES_FECHADAS
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesFechadas.equals=" + DEFAULT_QTD_LIGACOES_FECHADAS);

        // Get all the indicadorProducaoList where qtdLigacoesFechadas equals to UPDATED_QTD_LIGACOES_FECHADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesFechadas.equals=" + UPDATED_QTD_LIGACOES_FECHADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesFechadasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesFechadas in DEFAULT_QTD_LIGACOES_FECHADAS or UPDATED_QTD_LIGACOES_FECHADAS
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesFechadas.in=" + DEFAULT_QTD_LIGACOES_FECHADAS + "," + UPDATED_QTD_LIGACOES_FECHADAS);

        // Get all the indicadorProducaoList where qtdLigacoesFechadas equals to UPDATED_QTD_LIGACOES_FECHADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesFechadas.in=" + UPDATED_QTD_LIGACOES_FECHADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesFechadasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesFechadas is not null
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesFechadas.specified=true");

        // Get all the indicadorProducaoList where qtdLigacoesFechadas is null
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesFechadas.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesFechadasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesFechadas greater than or equals to DEFAULT_QTD_LIGACOES_FECHADAS
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesFechadas.greaterOrEqualThan=" + DEFAULT_QTD_LIGACOES_FECHADAS);

        // Get all the indicadorProducaoList where qtdLigacoesFechadas greater than or equals to UPDATED_QTD_LIGACOES_FECHADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesFechadas.greaterOrEqualThan=" + UPDATED_QTD_LIGACOES_FECHADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesFechadasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesFechadas less than or equals to DEFAULT_QTD_LIGACOES_FECHADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesFechadas.lessThan=" + DEFAULT_QTD_LIGACOES_FECHADAS);

        // Get all the indicadorProducaoList where qtdLigacoesFechadas less than or equals to UPDATED_QTD_LIGACOES_FECHADAS
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesFechadas.lessThan=" + UPDATED_QTD_LIGACOES_FECHADAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdCortesIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdCortes equals to DEFAULT_QTD_CORTES
        defaultIndicadorProducaoShouldBeFound("qtdCortes.equals=" + DEFAULT_QTD_CORTES);

        // Get all the indicadorProducaoList where qtdCortes equals to UPDATED_QTD_CORTES
        defaultIndicadorProducaoShouldNotBeFound("qtdCortes.equals=" + UPDATED_QTD_CORTES);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdCortesIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdCortes in DEFAULT_QTD_CORTES or UPDATED_QTD_CORTES
        defaultIndicadorProducaoShouldBeFound("qtdCortes.in=" + DEFAULT_QTD_CORTES + "," + UPDATED_QTD_CORTES);

        // Get all the indicadorProducaoList where qtdCortes equals to UPDATED_QTD_CORTES
        defaultIndicadorProducaoShouldNotBeFound("qtdCortes.in=" + UPDATED_QTD_CORTES);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdCortesIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdCortes is not null
        defaultIndicadorProducaoShouldBeFound("qtdCortes.specified=true");

        // Get all the indicadorProducaoList where qtdCortes is null
        defaultIndicadorProducaoShouldNotBeFound("qtdCortes.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdCortesIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdCortes greater than or equals to DEFAULT_QTD_CORTES
        defaultIndicadorProducaoShouldBeFound("qtdCortes.greaterOrEqualThan=" + DEFAULT_QTD_CORTES);

        // Get all the indicadorProducaoList where qtdCortes greater than or equals to UPDATED_QTD_CORTES
        defaultIndicadorProducaoShouldNotBeFound("qtdCortes.greaterOrEqualThan=" + UPDATED_QTD_CORTES);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdCortesIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdCortes less than or equals to DEFAULT_QTD_CORTES
        defaultIndicadorProducaoShouldNotBeFound("qtdCortes.lessThan=" + DEFAULT_QTD_CORTES);

        // Get all the indicadorProducaoList where qtdCortes less than or equals to UPDATED_QTD_CORTES
        defaultIndicadorProducaoShouldBeFound("qtdCortes.lessThan=" + UPDATED_QTD_CORTES);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReligacoesIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReligacoes equals to DEFAULT_QTD_RELIGACOES
        defaultIndicadorProducaoShouldBeFound("qtdReligacoes.equals=" + DEFAULT_QTD_RELIGACOES);

        // Get all the indicadorProducaoList where qtdReligacoes equals to UPDATED_QTD_RELIGACOES
        defaultIndicadorProducaoShouldNotBeFound("qtdReligacoes.equals=" + UPDATED_QTD_RELIGACOES);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReligacoesIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReligacoes in DEFAULT_QTD_RELIGACOES or UPDATED_QTD_RELIGACOES
        defaultIndicadorProducaoShouldBeFound("qtdReligacoes.in=" + DEFAULT_QTD_RELIGACOES + "," + UPDATED_QTD_RELIGACOES);

        // Get all the indicadorProducaoList where qtdReligacoes equals to UPDATED_QTD_RELIGACOES
        defaultIndicadorProducaoShouldNotBeFound("qtdReligacoes.in=" + UPDATED_QTD_RELIGACOES);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReligacoesIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReligacoes is not null
        defaultIndicadorProducaoShouldBeFound("qtdReligacoes.specified=true");

        // Get all the indicadorProducaoList where qtdReligacoes is null
        defaultIndicadorProducaoShouldNotBeFound("qtdReligacoes.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReligacoesIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReligacoes greater than or equals to DEFAULT_QTD_RELIGACOES
        defaultIndicadorProducaoShouldBeFound("qtdReligacoes.greaterOrEqualThan=" + DEFAULT_QTD_RELIGACOES);

        // Get all the indicadorProducaoList where qtdReligacoes greater than or equals to UPDATED_QTD_RELIGACOES
        defaultIndicadorProducaoShouldNotBeFound("qtdReligacoes.greaterOrEqualThan=" + UPDATED_QTD_RELIGACOES);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReligacoesIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReligacoes less than or equals to DEFAULT_QTD_RELIGACOES
        defaultIndicadorProducaoShouldNotBeFound("qtdReligacoes.lessThan=" + DEFAULT_QTD_RELIGACOES);

        // Get all the indicadorProducaoList where qtdReligacoes less than or equals to UPDATED_QTD_RELIGACOES
        defaultIndicadorProducaoShouldBeFound("qtdReligacoes.lessThan=" + UPDATED_QTD_RELIGACOES);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesActivasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesActivas equals to DEFAULT_QTD_LIGACOES_ACTIVAS
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesActivas.equals=" + DEFAULT_QTD_LIGACOES_ACTIVAS);

        // Get all the indicadorProducaoList where qtdLigacoesActivas equals to UPDATED_QTD_LIGACOES_ACTIVAS
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesActivas.equals=" + UPDATED_QTD_LIGACOES_ACTIVAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesActivasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesActivas in DEFAULT_QTD_LIGACOES_ACTIVAS or UPDATED_QTD_LIGACOES_ACTIVAS
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesActivas.in=" + DEFAULT_QTD_LIGACOES_ACTIVAS + "," + UPDATED_QTD_LIGACOES_ACTIVAS);

        // Get all the indicadorProducaoList where qtdLigacoesActivas equals to UPDATED_QTD_LIGACOES_ACTIVAS
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesActivas.in=" + UPDATED_QTD_LIGACOES_ACTIVAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesActivasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesActivas is not null
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesActivas.specified=true");

        // Get all the indicadorProducaoList where qtdLigacoesActivas is null
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesActivas.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesActivasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesActivas greater than or equals to DEFAULT_QTD_LIGACOES_ACTIVAS
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesActivas.greaterOrEqualThan=" + DEFAULT_QTD_LIGACOES_ACTIVAS);

        // Get all the indicadorProducaoList where qtdLigacoesActivas greater than or equals to UPDATED_QTD_LIGACOES_ACTIVAS
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesActivas.greaterOrEqualThan=" + UPDATED_QTD_LIGACOES_ACTIVAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesActivasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesActivas less than or equals to DEFAULT_QTD_LIGACOES_ACTIVAS
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesActivas.lessThan=" + DEFAULT_QTD_LIGACOES_ACTIVAS);

        // Get all the indicadorProducaoList where qtdLigacoesActivas less than or equals to UPDATED_QTD_LIGACOES_ACTIVAS
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesActivas.lessThan=" + UPDATED_QTD_LIGACOES_ACTIVAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesDomesticasActivasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesDomesticasActivas equals to DEFAULT_QTD_LIGACOES_DOMESTICAS_ACTIVAS
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesDomesticasActivas.equals=" + DEFAULT_QTD_LIGACOES_DOMESTICAS_ACTIVAS);

        // Get all the indicadorProducaoList where qtdLigacoesDomesticasActivas equals to UPDATED_QTD_LIGACOES_DOMESTICAS_ACTIVAS
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesDomesticasActivas.equals=" + UPDATED_QTD_LIGACOES_DOMESTICAS_ACTIVAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesDomesticasActivasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesDomesticasActivas in DEFAULT_QTD_LIGACOES_DOMESTICAS_ACTIVAS or UPDATED_QTD_LIGACOES_DOMESTICAS_ACTIVAS
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesDomesticasActivas.in=" + DEFAULT_QTD_LIGACOES_DOMESTICAS_ACTIVAS + "," + UPDATED_QTD_LIGACOES_DOMESTICAS_ACTIVAS);

        // Get all the indicadorProducaoList where qtdLigacoesDomesticasActivas equals to UPDATED_QTD_LIGACOES_DOMESTICAS_ACTIVAS
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesDomesticasActivas.in=" + UPDATED_QTD_LIGACOES_DOMESTICAS_ACTIVAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesDomesticasActivasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesDomesticasActivas is not null
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesDomesticasActivas.specified=true");

        // Get all the indicadorProducaoList where qtdLigacoesDomesticasActivas is null
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesDomesticasActivas.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesDomesticasActivasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesDomesticasActivas greater than or equals to DEFAULT_QTD_LIGACOES_DOMESTICAS_ACTIVAS
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesDomesticasActivas.greaterOrEqualThan=" + DEFAULT_QTD_LIGACOES_DOMESTICAS_ACTIVAS);

        // Get all the indicadorProducaoList where qtdLigacoesDomesticasActivas greater than or equals to UPDATED_QTD_LIGACOES_DOMESTICAS_ACTIVAS
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesDomesticasActivas.greaterOrEqualThan=" + UPDATED_QTD_LIGACOES_DOMESTICAS_ACTIVAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesDomesticasActivasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesDomesticasActivas less than or equals to DEFAULT_QTD_LIGACOES_DOMESTICAS_ACTIVAS
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesDomesticasActivas.lessThan=" + DEFAULT_QTD_LIGACOES_DOMESTICAS_ACTIVAS);

        // Get all the indicadorProducaoList where qtdLigacoesDomesticasActivas less than or equals to UPDATED_QTD_LIGACOES_DOMESTICAS_ACTIVAS
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesDomesticasActivas.lessThan=" + UPDATED_QTD_LIGACOES_DOMESTICAS_ACTIVAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesFacturadasBaseLeiturasReaisIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesFacturadasBaseLeiturasReais equals to DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesFacturadasBaseLeiturasReais.equals=" + DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS);

        // Get all the indicadorProducaoList where qtdLigacoesFacturadasBaseLeiturasReais equals to UPDATED_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesFacturadasBaseLeiturasReais.equals=" + UPDATED_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesFacturadasBaseLeiturasReaisIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesFacturadasBaseLeiturasReais in DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS or UPDATED_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesFacturadasBaseLeiturasReais.in=" + DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS + "," + UPDATED_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS);

        // Get all the indicadorProducaoList where qtdLigacoesFacturadasBaseLeiturasReais equals to UPDATED_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesFacturadasBaseLeiturasReais.in=" + UPDATED_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesFacturadasBaseLeiturasReaisIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesFacturadasBaseLeiturasReais is not null
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesFacturadasBaseLeiturasReais.specified=true");

        // Get all the indicadorProducaoList where qtdLigacoesFacturadasBaseLeiturasReais is null
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesFacturadasBaseLeiturasReais.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesFacturadasBaseLeiturasReaisIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesFacturadasBaseLeiturasReais greater than or equals to DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesFacturadasBaseLeiturasReais.greaterOrEqualThan=" + DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS);

        // Get all the indicadorProducaoList where qtdLigacoesFacturadasBaseLeiturasReais greater than or equals to UPDATED_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesFacturadasBaseLeiturasReais.greaterOrEqualThan=" + UPDATED_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesFacturadasBaseLeiturasReaisIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesFacturadasBaseLeiturasReais less than or equals to DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesFacturadasBaseLeiturasReais.lessThan=" + DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS);

        // Get all the indicadorProducaoList where qtdLigacoesFacturadasBaseLeiturasReais less than or equals to UPDATED_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesFacturadasBaseLeiturasReais.lessThan=" + UPDATED_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesFacturadasBaseEstimativasAvencaIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesFacturadasBaseEstimativasAvenca equals to DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesFacturadasBaseEstimativasAvenca.equals=" + DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA);

        // Get all the indicadorProducaoList where qtdLigacoesFacturadasBaseEstimativasAvenca equals to UPDATED_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesFacturadasBaseEstimativasAvenca.equals=" + UPDATED_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesFacturadasBaseEstimativasAvencaIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesFacturadasBaseEstimativasAvenca in DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA or UPDATED_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesFacturadasBaseEstimativasAvenca.in=" + DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA + "," + UPDATED_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA);

        // Get all the indicadorProducaoList where qtdLigacoesFacturadasBaseEstimativasAvenca equals to UPDATED_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesFacturadasBaseEstimativasAvenca.in=" + UPDATED_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesFacturadasBaseEstimativasAvencaIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesFacturadasBaseEstimativasAvenca is not null
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesFacturadasBaseEstimativasAvenca.specified=true");

        // Get all the indicadorProducaoList where qtdLigacoesFacturadasBaseEstimativasAvenca is null
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesFacturadasBaseEstimativasAvenca.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesFacturadasBaseEstimativasAvencaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesFacturadasBaseEstimativasAvenca greater than or equals to DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesFacturadasBaseEstimativasAvenca.greaterOrEqualThan=" + DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA);

        // Get all the indicadorProducaoList where qtdLigacoesFacturadasBaseEstimativasAvenca greater than or equals to UPDATED_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesFacturadasBaseEstimativasAvenca.greaterOrEqualThan=" + UPDATED_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdLigacoesFacturadasBaseEstimativasAvencaIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdLigacoesFacturadasBaseEstimativasAvenca less than or equals to DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA
        defaultIndicadorProducaoShouldNotBeFound("qtdLigacoesFacturadasBaseEstimativasAvenca.lessThan=" + DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA);

        // Get all the indicadorProducaoList where qtdLigacoesFacturadasBaseEstimativasAvenca less than or equals to UPDATED_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA
        defaultIndicadorProducaoShouldBeFound("qtdLigacoesFacturadasBaseEstimativasAvenca.lessThan=" + UPDATED_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdVolumeAguaFacturadaIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdVolumeAguaFacturada equals to DEFAULT_QTD_VOLUME_AGUA_FACTURADA
        defaultIndicadorProducaoShouldBeFound("qtdVolumeAguaFacturada.equals=" + DEFAULT_QTD_VOLUME_AGUA_FACTURADA);

        // Get all the indicadorProducaoList where qtdVolumeAguaFacturada equals to UPDATED_QTD_VOLUME_AGUA_FACTURADA
        defaultIndicadorProducaoShouldNotBeFound("qtdVolumeAguaFacturada.equals=" + UPDATED_QTD_VOLUME_AGUA_FACTURADA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdVolumeAguaFacturadaIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdVolumeAguaFacturada in DEFAULT_QTD_VOLUME_AGUA_FACTURADA or UPDATED_QTD_VOLUME_AGUA_FACTURADA
        defaultIndicadorProducaoShouldBeFound("qtdVolumeAguaFacturada.in=" + DEFAULT_QTD_VOLUME_AGUA_FACTURADA + "," + UPDATED_QTD_VOLUME_AGUA_FACTURADA);

        // Get all the indicadorProducaoList where qtdVolumeAguaFacturada equals to UPDATED_QTD_VOLUME_AGUA_FACTURADA
        defaultIndicadorProducaoShouldNotBeFound("qtdVolumeAguaFacturada.in=" + UPDATED_QTD_VOLUME_AGUA_FACTURADA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdVolumeAguaFacturadaIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdVolumeAguaFacturada is not null
        defaultIndicadorProducaoShouldBeFound("qtdVolumeAguaFacturada.specified=true");

        // Get all the indicadorProducaoList where qtdVolumeAguaFacturada is null
        defaultIndicadorProducaoShouldNotBeFound("qtdVolumeAguaFacturada.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdVolumeTotalFacturadaLigacoesDomesticasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdVolumeTotalFacturadaLigacoesDomesticas equals to DEFAULT_QTD_VOLUME_TOTAL_FACTURADA_LIGACOES_DOMESTICAS
        defaultIndicadorProducaoShouldBeFound("qtdVolumeTotalFacturadaLigacoesDomesticas.equals=" + DEFAULT_QTD_VOLUME_TOTAL_FACTURADA_LIGACOES_DOMESTICAS);

        // Get all the indicadorProducaoList where qtdVolumeTotalFacturadaLigacoesDomesticas equals to UPDATED_QTD_VOLUME_TOTAL_FACTURADA_LIGACOES_DOMESTICAS
        defaultIndicadorProducaoShouldNotBeFound("qtdVolumeTotalFacturadaLigacoesDomesticas.equals=" + UPDATED_QTD_VOLUME_TOTAL_FACTURADA_LIGACOES_DOMESTICAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdVolumeTotalFacturadaLigacoesDomesticasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdVolumeTotalFacturadaLigacoesDomesticas in DEFAULT_QTD_VOLUME_TOTAL_FACTURADA_LIGACOES_DOMESTICAS or UPDATED_QTD_VOLUME_TOTAL_FACTURADA_LIGACOES_DOMESTICAS
        defaultIndicadorProducaoShouldBeFound("qtdVolumeTotalFacturadaLigacoesDomesticas.in=" + DEFAULT_QTD_VOLUME_TOTAL_FACTURADA_LIGACOES_DOMESTICAS + "," + UPDATED_QTD_VOLUME_TOTAL_FACTURADA_LIGACOES_DOMESTICAS);

        // Get all the indicadorProducaoList where qtdVolumeTotalFacturadaLigacoesDomesticas equals to UPDATED_QTD_VOLUME_TOTAL_FACTURADA_LIGACOES_DOMESTICAS
        defaultIndicadorProducaoShouldNotBeFound("qtdVolumeTotalFacturadaLigacoesDomesticas.in=" + UPDATED_QTD_VOLUME_TOTAL_FACTURADA_LIGACOES_DOMESTICAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdVolumeTotalFacturadaLigacoesDomesticasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdVolumeTotalFacturadaLigacoesDomesticas is not null
        defaultIndicadorProducaoShouldBeFound("qtdVolumeTotalFacturadaLigacoesDomesticas.specified=true");

        // Get all the indicadorProducaoList where qtdVolumeTotalFacturadaLigacoesDomesticas is null
        defaultIndicadorProducaoShouldNotBeFound("qtdVolumeTotalFacturadaLigacoesDomesticas.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdVolumeFacturadoBaseLeituraReaisIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdVolumeFacturadoBaseLeituraReais equals to DEFAULT_QTD_VOLUME_FACTURADO_BASE_LEITURA_REAIS
        defaultIndicadorProducaoShouldBeFound("qtdVolumeFacturadoBaseLeituraReais.equals=" + DEFAULT_QTD_VOLUME_FACTURADO_BASE_LEITURA_REAIS);

        // Get all the indicadorProducaoList where qtdVolumeFacturadoBaseLeituraReais equals to UPDATED_QTD_VOLUME_FACTURADO_BASE_LEITURA_REAIS
        defaultIndicadorProducaoShouldNotBeFound("qtdVolumeFacturadoBaseLeituraReais.equals=" + UPDATED_QTD_VOLUME_FACTURADO_BASE_LEITURA_REAIS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdVolumeFacturadoBaseLeituraReaisIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdVolumeFacturadoBaseLeituraReais in DEFAULT_QTD_VOLUME_FACTURADO_BASE_LEITURA_REAIS or UPDATED_QTD_VOLUME_FACTURADO_BASE_LEITURA_REAIS
        defaultIndicadorProducaoShouldBeFound("qtdVolumeFacturadoBaseLeituraReais.in=" + DEFAULT_QTD_VOLUME_FACTURADO_BASE_LEITURA_REAIS + "," + UPDATED_QTD_VOLUME_FACTURADO_BASE_LEITURA_REAIS);

        // Get all the indicadorProducaoList where qtdVolumeFacturadoBaseLeituraReais equals to UPDATED_QTD_VOLUME_FACTURADO_BASE_LEITURA_REAIS
        defaultIndicadorProducaoShouldNotBeFound("qtdVolumeFacturadoBaseLeituraReais.in=" + UPDATED_QTD_VOLUME_FACTURADO_BASE_LEITURA_REAIS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdVolumeFacturadoBaseLeituraReaisIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdVolumeFacturadoBaseLeituraReais is not null
        defaultIndicadorProducaoShouldBeFound("qtdVolumeFacturadoBaseLeituraReais.specified=true");

        // Get all the indicadorProducaoList where qtdVolumeFacturadoBaseLeituraReais is null
        defaultIndicadorProducaoShouldNotBeFound("qtdVolumeFacturadoBaseLeituraReais.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrTotalFacturadoIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrTotalFacturado equals to DEFAULT_VLR_TOTAL_FACTURADO
        defaultIndicadorProducaoShouldBeFound("vlrTotalFacturado.equals=" + DEFAULT_VLR_TOTAL_FACTURADO);

        // Get all the indicadorProducaoList where vlrTotalFacturado equals to UPDATED_VLR_TOTAL_FACTURADO
        defaultIndicadorProducaoShouldNotBeFound("vlrTotalFacturado.equals=" + UPDATED_VLR_TOTAL_FACTURADO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrTotalFacturadoIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrTotalFacturado in DEFAULT_VLR_TOTAL_FACTURADO or UPDATED_VLR_TOTAL_FACTURADO
        defaultIndicadorProducaoShouldBeFound("vlrTotalFacturado.in=" + DEFAULT_VLR_TOTAL_FACTURADO + "," + UPDATED_VLR_TOTAL_FACTURADO);

        // Get all the indicadorProducaoList where vlrTotalFacturado equals to UPDATED_VLR_TOTAL_FACTURADO
        defaultIndicadorProducaoShouldNotBeFound("vlrTotalFacturado.in=" + UPDATED_VLR_TOTAL_FACTURADO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrTotalFacturadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrTotalFacturado is not null
        defaultIndicadorProducaoShouldBeFound("vlrTotalFacturado.specified=true");

        // Get all the indicadorProducaoList where vlrTotalFacturado is null
        defaultIndicadorProducaoShouldNotBeFound("vlrTotalFacturado.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrFacturasCanceladasNotasCreditosIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrFacturasCanceladasNotasCreditos equals to DEFAULT_VLR_FACTURAS_CANCELADAS_NOTAS_CREDITOS
        defaultIndicadorProducaoShouldBeFound("vlrFacturasCanceladasNotasCreditos.equals=" + DEFAULT_VLR_FACTURAS_CANCELADAS_NOTAS_CREDITOS);

        // Get all the indicadorProducaoList where vlrFacturasCanceladasNotasCreditos equals to UPDATED_VLR_FACTURAS_CANCELADAS_NOTAS_CREDITOS
        defaultIndicadorProducaoShouldNotBeFound("vlrFacturasCanceladasNotasCreditos.equals=" + UPDATED_VLR_FACTURAS_CANCELADAS_NOTAS_CREDITOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrFacturasCanceladasNotasCreditosIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrFacturasCanceladasNotasCreditos in DEFAULT_VLR_FACTURAS_CANCELADAS_NOTAS_CREDITOS or UPDATED_VLR_FACTURAS_CANCELADAS_NOTAS_CREDITOS
        defaultIndicadorProducaoShouldBeFound("vlrFacturasCanceladasNotasCreditos.in=" + DEFAULT_VLR_FACTURAS_CANCELADAS_NOTAS_CREDITOS + "," + UPDATED_VLR_FACTURAS_CANCELADAS_NOTAS_CREDITOS);

        // Get all the indicadorProducaoList where vlrFacturasCanceladasNotasCreditos equals to UPDATED_VLR_FACTURAS_CANCELADAS_NOTAS_CREDITOS
        defaultIndicadorProducaoShouldNotBeFound("vlrFacturasCanceladasNotasCreditos.in=" + UPDATED_VLR_FACTURAS_CANCELADAS_NOTAS_CREDITOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrFacturasCanceladasNotasCreditosIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrFacturasCanceladasNotasCreditos is not null
        defaultIndicadorProducaoShouldBeFound("vlrFacturasCanceladasNotasCreditos.specified=true");

        // Get all the indicadorProducaoList where vlrFacturasCanceladasNotasCreditos is null
        defaultIndicadorProducaoShouldNotBeFound("vlrFacturasCanceladasNotasCreditos.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrRealFacturadoIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrRealFacturado equals to DEFAULT_VLR_REAL_FACTURADO
        defaultIndicadorProducaoShouldBeFound("vlrRealFacturado.equals=" + DEFAULT_VLR_REAL_FACTURADO);

        // Get all the indicadorProducaoList where vlrRealFacturado equals to UPDATED_VLR_REAL_FACTURADO
        defaultIndicadorProducaoShouldNotBeFound("vlrRealFacturado.equals=" + UPDATED_VLR_REAL_FACTURADO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrRealFacturadoIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrRealFacturado in DEFAULT_VLR_REAL_FACTURADO or UPDATED_VLR_REAL_FACTURADO
        defaultIndicadorProducaoShouldBeFound("vlrRealFacturado.in=" + DEFAULT_VLR_REAL_FACTURADO + "," + UPDATED_VLR_REAL_FACTURADO);

        // Get all the indicadorProducaoList where vlrRealFacturado equals to UPDATED_VLR_REAL_FACTURADO
        defaultIndicadorProducaoShouldNotBeFound("vlrRealFacturado.in=" + UPDATED_VLR_REAL_FACTURADO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrRealFacturadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrRealFacturado is not null
        defaultIndicadorProducaoShouldBeFound("vlrRealFacturado.specified=true");

        // Get all the indicadorProducaoList where vlrRealFacturado is null
        defaultIndicadorProducaoShouldNotBeFound("vlrRealFacturado.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrTotalCobradoIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrTotalCobrado equals to DEFAULT_VLR_TOTAL_COBRADO
        defaultIndicadorProducaoShouldBeFound("vlrTotalCobrado.equals=" + DEFAULT_VLR_TOTAL_COBRADO);

        // Get all the indicadorProducaoList where vlrTotalCobrado equals to UPDATED_VLR_TOTAL_COBRADO
        defaultIndicadorProducaoShouldNotBeFound("vlrTotalCobrado.equals=" + UPDATED_VLR_TOTAL_COBRADO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrTotalCobradoIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrTotalCobrado in DEFAULT_VLR_TOTAL_COBRADO or UPDATED_VLR_TOTAL_COBRADO
        defaultIndicadorProducaoShouldBeFound("vlrTotalCobrado.in=" + DEFAULT_VLR_TOTAL_COBRADO + "," + UPDATED_VLR_TOTAL_COBRADO);

        // Get all the indicadorProducaoList where vlrTotalCobrado equals to UPDATED_VLR_TOTAL_COBRADO
        defaultIndicadorProducaoShouldNotBeFound("vlrTotalCobrado.in=" + UPDATED_VLR_TOTAL_COBRADO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrTotalCobradoIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrTotalCobrado is not null
        defaultIndicadorProducaoShouldBeFound("vlrTotalCobrado.specified=true");

        // Get all the indicadorProducaoList where vlrTotalCobrado is null
        defaultIndicadorProducaoShouldNotBeFound("vlrTotalCobrado.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReclamacoesIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReclamacoes equals to DEFAULT_QTD_RECLAMACOES
        defaultIndicadorProducaoShouldBeFound("qtdReclamacoes.equals=" + DEFAULT_QTD_RECLAMACOES);

        // Get all the indicadorProducaoList where qtdReclamacoes equals to UPDATED_QTD_RECLAMACOES
        defaultIndicadorProducaoShouldNotBeFound("qtdReclamacoes.equals=" + UPDATED_QTD_RECLAMACOES);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReclamacoesIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReclamacoes in DEFAULT_QTD_RECLAMACOES or UPDATED_QTD_RECLAMACOES
        defaultIndicadorProducaoShouldBeFound("qtdReclamacoes.in=" + DEFAULT_QTD_RECLAMACOES + "," + UPDATED_QTD_RECLAMACOES);

        // Get all the indicadorProducaoList where qtdReclamacoes equals to UPDATED_QTD_RECLAMACOES
        defaultIndicadorProducaoShouldNotBeFound("qtdReclamacoes.in=" + UPDATED_QTD_RECLAMACOES);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReclamacoesIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReclamacoes is not null
        defaultIndicadorProducaoShouldBeFound("qtdReclamacoes.specified=true");

        // Get all the indicadorProducaoList where qtdReclamacoes is null
        defaultIndicadorProducaoShouldNotBeFound("qtdReclamacoes.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReclamacoesIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReclamacoes greater than or equals to DEFAULT_QTD_RECLAMACOES
        defaultIndicadorProducaoShouldBeFound("qtdReclamacoes.greaterOrEqualThan=" + DEFAULT_QTD_RECLAMACOES);

        // Get all the indicadorProducaoList where qtdReclamacoes greater than or equals to UPDATED_QTD_RECLAMACOES
        defaultIndicadorProducaoShouldNotBeFound("qtdReclamacoes.greaterOrEqualThan=" + UPDATED_QTD_RECLAMACOES);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReclamacoesIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReclamacoes less than or equals to DEFAULT_QTD_RECLAMACOES
        defaultIndicadorProducaoShouldNotBeFound("qtdReclamacoes.lessThan=" + DEFAULT_QTD_RECLAMACOES);

        // Get all the indicadorProducaoList where qtdReclamacoes less than or equals to UPDATED_QTD_RECLAMACOES
        defaultIndicadorProducaoShouldBeFound("qtdReclamacoes.lessThan=" + UPDATED_QTD_RECLAMACOES);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReclamacoesRespondidasMenorIgualCincoDiasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMenorIgualCincoDias equals to DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS
        defaultIndicadorProducaoShouldBeFound("qtdReclamacoesRespondidasMenorIgualCincoDias.equals=" + DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMenorIgualCincoDias equals to UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS
        defaultIndicadorProducaoShouldNotBeFound("qtdReclamacoesRespondidasMenorIgualCincoDias.equals=" + UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReclamacoesRespondidasMenorIgualCincoDiasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMenorIgualCincoDias in DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS or UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS
        defaultIndicadorProducaoShouldBeFound("qtdReclamacoesRespondidasMenorIgualCincoDias.in=" + DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS + "," + UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMenorIgualCincoDias equals to UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS
        defaultIndicadorProducaoShouldNotBeFound("qtdReclamacoesRespondidasMenorIgualCincoDias.in=" + UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReclamacoesRespondidasMenorIgualCincoDiasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMenorIgualCincoDias is not null
        defaultIndicadorProducaoShouldBeFound("qtdReclamacoesRespondidasMenorIgualCincoDias.specified=true");

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMenorIgualCincoDias is null
        defaultIndicadorProducaoShouldNotBeFound("qtdReclamacoesRespondidasMenorIgualCincoDias.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReclamacoesRespondidasMenorIgualCincoDiasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMenorIgualCincoDias greater than or equals to DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS
        defaultIndicadorProducaoShouldBeFound("qtdReclamacoesRespondidasMenorIgualCincoDias.greaterOrEqualThan=" + DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMenorIgualCincoDias greater than or equals to UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS
        defaultIndicadorProducaoShouldNotBeFound("qtdReclamacoesRespondidasMenorIgualCincoDias.greaterOrEqualThan=" + UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReclamacoesRespondidasMenorIgualCincoDiasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMenorIgualCincoDias less than or equals to DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS
        defaultIndicadorProducaoShouldNotBeFound("qtdReclamacoesRespondidasMenorIgualCincoDias.lessThan=" + DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMenorIgualCincoDias less than or equals to UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS
        defaultIndicadorProducaoShouldBeFound("qtdReclamacoesRespondidasMenorIgualCincoDias.lessThan=" + UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReclamacoesRespondidasMaisCincoMenosVinteDiasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMaisCincoMenosVinteDias equals to DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS
        defaultIndicadorProducaoShouldBeFound("qtdReclamacoesRespondidasMaisCincoMenosVinteDias.equals=" + DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMaisCincoMenosVinteDias equals to UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS
        defaultIndicadorProducaoShouldNotBeFound("qtdReclamacoesRespondidasMaisCincoMenosVinteDias.equals=" + UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReclamacoesRespondidasMaisCincoMenosVinteDiasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMaisCincoMenosVinteDias in DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS or UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS
        defaultIndicadorProducaoShouldBeFound("qtdReclamacoesRespondidasMaisCincoMenosVinteDias.in=" + DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS + "," + UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMaisCincoMenosVinteDias equals to UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS
        defaultIndicadorProducaoShouldNotBeFound("qtdReclamacoesRespondidasMaisCincoMenosVinteDias.in=" + UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReclamacoesRespondidasMaisCincoMenosVinteDiasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMaisCincoMenosVinteDias is not null
        defaultIndicadorProducaoShouldBeFound("qtdReclamacoesRespondidasMaisCincoMenosVinteDias.specified=true");

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMaisCincoMenosVinteDias is null
        defaultIndicadorProducaoShouldNotBeFound("qtdReclamacoesRespondidasMaisCincoMenosVinteDias.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReclamacoesRespondidasMaisCincoMenosVinteDiasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMaisCincoMenosVinteDias greater than or equals to DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS
        defaultIndicadorProducaoShouldBeFound("qtdReclamacoesRespondidasMaisCincoMenosVinteDias.greaterOrEqualThan=" + DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMaisCincoMenosVinteDias greater than or equals to UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS
        defaultIndicadorProducaoShouldNotBeFound("qtdReclamacoesRespondidasMaisCincoMenosVinteDias.greaterOrEqualThan=" + UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReclamacoesRespondidasMaisCincoMenosVinteDiasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMaisCincoMenosVinteDias less than or equals to DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS
        defaultIndicadorProducaoShouldNotBeFound("qtdReclamacoesRespondidasMaisCincoMenosVinteDias.lessThan=" + DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMaisCincoMenosVinteDias less than or equals to UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS
        defaultIndicadorProducaoShouldBeFound("qtdReclamacoesRespondidasMaisCincoMenosVinteDias.lessThan=" + UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReclamacoesRespondidasMaiorIgualVinteDiasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMaiorIgualVinteDias equals to DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS
        defaultIndicadorProducaoShouldBeFound("qtdReclamacoesRespondidasMaiorIgualVinteDias.equals=" + DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMaiorIgualVinteDias equals to UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS
        defaultIndicadorProducaoShouldNotBeFound("qtdReclamacoesRespondidasMaiorIgualVinteDias.equals=" + UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReclamacoesRespondidasMaiorIgualVinteDiasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMaiorIgualVinteDias in DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS or UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS
        defaultIndicadorProducaoShouldBeFound("qtdReclamacoesRespondidasMaiorIgualVinteDias.in=" + DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS + "," + UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMaiorIgualVinteDias equals to UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS
        defaultIndicadorProducaoShouldNotBeFound("qtdReclamacoesRespondidasMaiorIgualVinteDias.in=" + UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReclamacoesRespondidasMaiorIgualVinteDiasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMaiorIgualVinteDias is not null
        defaultIndicadorProducaoShouldBeFound("qtdReclamacoesRespondidasMaiorIgualVinteDias.specified=true");

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMaiorIgualVinteDias is null
        defaultIndicadorProducaoShouldNotBeFound("qtdReclamacoesRespondidasMaiorIgualVinteDias.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReclamacoesRespondidasMaiorIgualVinteDiasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMaiorIgualVinteDias greater than or equals to DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS
        defaultIndicadorProducaoShouldBeFound("qtdReclamacoesRespondidasMaiorIgualVinteDias.greaterOrEqualThan=" + DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMaiorIgualVinteDias greater than or equals to UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS
        defaultIndicadorProducaoShouldNotBeFound("qtdReclamacoesRespondidasMaiorIgualVinteDias.greaterOrEqualThan=" + UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReclamacoesRespondidasMaiorIgualVinteDiasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMaiorIgualVinteDias less than or equals to DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS
        defaultIndicadorProducaoShouldNotBeFound("qtdReclamacoesRespondidasMaiorIgualVinteDias.lessThan=" + DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS);

        // Get all the indicadorProducaoList where qtdReclamacoesRespondidasMaiorIgualVinteDias less than or equals to UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS
        defaultIndicadorProducaoShouldBeFound("qtdReclamacoesRespondidasMaiorIgualVinteDias.lessThan=" + UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoPessoalIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoPessoal equals to DEFAULT_VLR_CUSTO_PESSOAL
        defaultIndicadorProducaoShouldBeFound("vlrCustoPessoal.equals=" + DEFAULT_VLR_CUSTO_PESSOAL);

        // Get all the indicadorProducaoList where vlrCustoPessoal equals to UPDATED_VLR_CUSTO_PESSOAL
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoPessoal.equals=" + UPDATED_VLR_CUSTO_PESSOAL);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoPessoalIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoPessoal in DEFAULT_VLR_CUSTO_PESSOAL or UPDATED_VLR_CUSTO_PESSOAL
        defaultIndicadorProducaoShouldBeFound("vlrCustoPessoal.in=" + DEFAULT_VLR_CUSTO_PESSOAL + "," + UPDATED_VLR_CUSTO_PESSOAL);

        // Get all the indicadorProducaoList where vlrCustoPessoal equals to UPDATED_VLR_CUSTO_PESSOAL
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoPessoal.in=" + UPDATED_VLR_CUSTO_PESSOAL);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoPessoalIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoPessoal is not null
        defaultIndicadorProducaoShouldBeFound("vlrCustoPessoal.specified=true");

        // Get all the indicadorProducaoList where vlrCustoPessoal is null
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoPessoal.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoFseIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoFse equals to DEFAULT_VLR_CUSTO_FSE
        defaultIndicadorProducaoShouldBeFound("vlrCustoFse.equals=" + DEFAULT_VLR_CUSTO_FSE);

        // Get all the indicadorProducaoList where vlrCustoFse equals to UPDATED_VLR_CUSTO_FSE
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoFse.equals=" + UPDATED_VLR_CUSTO_FSE);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoFseIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoFse in DEFAULT_VLR_CUSTO_FSE or UPDATED_VLR_CUSTO_FSE
        defaultIndicadorProducaoShouldBeFound("vlrCustoFse.in=" + DEFAULT_VLR_CUSTO_FSE + "," + UPDATED_VLR_CUSTO_FSE);

        // Get all the indicadorProducaoList where vlrCustoFse equals to UPDATED_VLR_CUSTO_FSE
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoFse.in=" + UPDATED_VLR_CUSTO_FSE);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoFseIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoFse is not null
        defaultIndicadorProducaoShouldBeFound("vlrCustoFse.specified=true");

        // Get all the indicadorProducaoList where vlrCustoFse is null
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoFse.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoEnergiaIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoEnergia equals to DEFAULT_VLR_CUSTO_ENERGIA
        defaultIndicadorProducaoShouldBeFound("vlrCustoEnergia.equals=" + DEFAULT_VLR_CUSTO_ENERGIA);

        // Get all the indicadorProducaoList where vlrCustoEnergia equals to UPDATED_VLR_CUSTO_ENERGIA
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoEnergia.equals=" + UPDATED_VLR_CUSTO_ENERGIA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoEnergiaIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoEnergia in DEFAULT_VLR_CUSTO_ENERGIA or UPDATED_VLR_CUSTO_ENERGIA
        defaultIndicadorProducaoShouldBeFound("vlrCustoEnergia.in=" + DEFAULT_VLR_CUSTO_ENERGIA + "," + UPDATED_VLR_CUSTO_ENERGIA);

        // Get all the indicadorProducaoList where vlrCustoEnergia equals to UPDATED_VLR_CUSTO_ENERGIA
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoEnergia.in=" + UPDATED_VLR_CUSTO_ENERGIA);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoEnergiaIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoEnergia is not null
        defaultIndicadorProducaoShouldBeFound("vlrCustoEnergia.specified=true");

        // Get all the indicadorProducaoList where vlrCustoEnergia is null
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoEnergia.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoManutencaoIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoManutencao equals to DEFAULT_VLR_CUSTO_MANUTENCAO
        defaultIndicadorProducaoShouldBeFound("vlrCustoManutencao.equals=" + DEFAULT_VLR_CUSTO_MANUTENCAO);

        // Get all the indicadorProducaoList where vlrCustoManutencao equals to UPDATED_VLR_CUSTO_MANUTENCAO
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoManutencao.equals=" + UPDATED_VLR_CUSTO_MANUTENCAO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoManutencaoIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoManutencao in DEFAULT_VLR_CUSTO_MANUTENCAO or UPDATED_VLR_CUSTO_MANUTENCAO
        defaultIndicadorProducaoShouldBeFound("vlrCustoManutencao.in=" + DEFAULT_VLR_CUSTO_MANUTENCAO + "," + UPDATED_VLR_CUSTO_MANUTENCAO);

        // Get all the indicadorProducaoList where vlrCustoManutencao equals to UPDATED_VLR_CUSTO_MANUTENCAO
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoManutencao.in=" + UPDATED_VLR_CUSTO_MANUTENCAO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoManutencaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoManutencao is not null
        defaultIndicadorProducaoShouldBeFound("vlrCustoManutencao.specified=true");

        // Get all the indicadorProducaoList where vlrCustoManutencao is null
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoManutencao.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoReagentesIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoReagentes equals to DEFAULT_VLR_CUSTO_REAGENTES
        defaultIndicadorProducaoShouldBeFound("vlrCustoReagentes.equals=" + DEFAULT_VLR_CUSTO_REAGENTES);

        // Get all the indicadorProducaoList where vlrCustoReagentes equals to UPDATED_VLR_CUSTO_REAGENTES
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoReagentes.equals=" + UPDATED_VLR_CUSTO_REAGENTES);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoReagentesIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoReagentes in DEFAULT_VLR_CUSTO_REAGENTES or UPDATED_VLR_CUSTO_REAGENTES
        defaultIndicadorProducaoShouldBeFound("vlrCustoReagentes.in=" + DEFAULT_VLR_CUSTO_REAGENTES + "," + UPDATED_VLR_CUSTO_REAGENTES);

        // Get all the indicadorProducaoList where vlrCustoReagentes equals to UPDATED_VLR_CUSTO_REAGENTES
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoReagentes.in=" + UPDATED_VLR_CUSTO_REAGENTES);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoReagentesIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoReagentes is not null
        defaultIndicadorProducaoShouldBeFound("vlrCustoReagentes.specified=true");

        // Get all the indicadorProducaoList where vlrCustoReagentes is null
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoReagentes.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoDestinoFinalLamasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoDestinoFinalLamas equals to DEFAULT_VLR_CUSTO_DESTINO_FINAL_LAMAS
        defaultIndicadorProducaoShouldBeFound("vlrCustoDestinoFinalLamas.equals=" + DEFAULT_VLR_CUSTO_DESTINO_FINAL_LAMAS);

        // Get all the indicadorProducaoList where vlrCustoDestinoFinalLamas equals to UPDATED_VLR_CUSTO_DESTINO_FINAL_LAMAS
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoDestinoFinalLamas.equals=" + UPDATED_VLR_CUSTO_DESTINO_FINAL_LAMAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoDestinoFinalLamasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoDestinoFinalLamas in DEFAULT_VLR_CUSTO_DESTINO_FINAL_LAMAS or UPDATED_VLR_CUSTO_DESTINO_FINAL_LAMAS
        defaultIndicadorProducaoShouldBeFound("vlrCustoDestinoFinalLamas.in=" + DEFAULT_VLR_CUSTO_DESTINO_FINAL_LAMAS + "," + UPDATED_VLR_CUSTO_DESTINO_FINAL_LAMAS);

        // Get all the indicadorProducaoList where vlrCustoDestinoFinalLamas equals to UPDATED_VLR_CUSTO_DESTINO_FINAL_LAMAS
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoDestinoFinalLamas.in=" + UPDATED_VLR_CUSTO_DESTINO_FINAL_LAMAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoDestinoFinalLamasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoDestinoFinalLamas is not null
        defaultIndicadorProducaoShouldBeFound("vlrCustoDestinoFinalLamas.specified=true");

        // Get all the indicadorProducaoList where vlrCustoDestinoFinalLamas is null
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoDestinoFinalLamas.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoOperacionaisOpexIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoOperacionaisOpex equals to DEFAULT_VLR_CUSTO_OPERACIONAIS_OPEX
        defaultIndicadorProducaoShouldBeFound("vlrCustoOperacionaisOpex.equals=" + DEFAULT_VLR_CUSTO_OPERACIONAIS_OPEX);

        // Get all the indicadorProducaoList where vlrCustoOperacionaisOpex equals to UPDATED_VLR_CUSTO_OPERACIONAIS_OPEX
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoOperacionaisOpex.equals=" + UPDATED_VLR_CUSTO_OPERACIONAIS_OPEX);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoOperacionaisOpexIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoOperacionaisOpex in DEFAULT_VLR_CUSTO_OPERACIONAIS_OPEX or UPDATED_VLR_CUSTO_OPERACIONAIS_OPEX
        defaultIndicadorProducaoShouldBeFound("vlrCustoOperacionaisOpex.in=" + DEFAULT_VLR_CUSTO_OPERACIONAIS_OPEX + "," + UPDATED_VLR_CUSTO_OPERACIONAIS_OPEX);

        // Get all the indicadorProducaoList where vlrCustoOperacionaisOpex equals to UPDATED_VLR_CUSTO_OPERACIONAIS_OPEX
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoOperacionaisOpex.in=" + UPDATED_VLR_CUSTO_OPERACIONAIS_OPEX);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoOperacionaisOpexIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoOperacionaisOpex is not null
        defaultIndicadorProducaoShouldBeFound("vlrCustoOperacionaisOpex.specified=true");

        // Get all the indicadorProducaoList where vlrCustoOperacionaisOpex is null
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoOperacionaisOpex.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoAmortizaAnualInvestOpCapexIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoAmortizaAnualInvestOpCapex equals to DEFAULT_VLR_CUSTO_AMORTIZA_ANUAL_INVEST_OP_CAPEX
        defaultIndicadorProducaoShouldBeFound("vlrCustoAmortizaAnualInvestOpCapex.equals=" + DEFAULT_VLR_CUSTO_AMORTIZA_ANUAL_INVEST_OP_CAPEX);

        // Get all the indicadorProducaoList where vlrCustoAmortizaAnualInvestOpCapex equals to UPDATED_VLR_CUSTO_AMORTIZA_ANUAL_INVEST_OP_CAPEX
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoAmortizaAnualInvestOpCapex.equals=" + UPDATED_VLR_CUSTO_AMORTIZA_ANUAL_INVEST_OP_CAPEX);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoAmortizaAnualInvestOpCapexIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoAmortizaAnualInvestOpCapex in DEFAULT_VLR_CUSTO_AMORTIZA_ANUAL_INVEST_OP_CAPEX or UPDATED_VLR_CUSTO_AMORTIZA_ANUAL_INVEST_OP_CAPEX
        defaultIndicadorProducaoShouldBeFound("vlrCustoAmortizaAnualInvestOpCapex.in=" + DEFAULT_VLR_CUSTO_AMORTIZA_ANUAL_INVEST_OP_CAPEX + "," + UPDATED_VLR_CUSTO_AMORTIZA_ANUAL_INVEST_OP_CAPEX);

        // Get all the indicadorProducaoList where vlrCustoAmortizaAnualInvestOpCapex equals to UPDATED_VLR_CUSTO_AMORTIZA_ANUAL_INVEST_OP_CAPEX
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoAmortizaAnualInvestOpCapex.in=" + UPDATED_VLR_CUSTO_AMORTIZA_ANUAL_INVEST_OP_CAPEX);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoAmortizaAnualInvestOpCapexIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoAmortizaAnualInvestOpCapex is not null
        defaultIndicadorProducaoShouldBeFound("vlrCustoAmortizaAnualInvestOpCapex.specified=true");

        // Get all the indicadorProducaoList where vlrCustoAmortizaAnualInvestOpCapex is null
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoAmortizaAnualInvestOpCapex.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoTotaisCapexOpexIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoTotaisCapexOpex equals to DEFAULT_VLR_CUSTO_TOTAIS_CAPEX_OPEX
        defaultIndicadorProducaoShouldBeFound("vlrCustoTotaisCapexOpex.equals=" + DEFAULT_VLR_CUSTO_TOTAIS_CAPEX_OPEX);

        // Get all the indicadorProducaoList where vlrCustoTotaisCapexOpex equals to UPDATED_VLR_CUSTO_TOTAIS_CAPEX_OPEX
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoTotaisCapexOpex.equals=" + UPDATED_VLR_CUSTO_TOTAIS_CAPEX_OPEX);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoTotaisCapexOpexIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoTotaisCapexOpex in DEFAULT_VLR_CUSTO_TOTAIS_CAPEX_OPEX or UPDATED_VLR_CUSTO_TOTAIS_CAPEX_OPEX
        defaultIndicadorProducaoShouldBeFound("vlrCustoTotaisCapexOpex.in=" + DEFAULT_VLR_CUSTO_TOTAIS_CAPEX_OPEX + "," + UPDATED_VLR_CUSTO_TOTAIS_CAPEX_OPEX);

        // Get all the indicadorProducaoList where vlrCustoTotaisCapexOpex equals to UPDATED_VLR_CUSTO_TOTAIS_CAPEX_OPEX
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoTotaisCapexOpex.in=" + UPDATED_VLR_CUSTO_TOTAIS_CAPEX_OPEX);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByVlrCustoTotaisCapexOpexIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where vlrCustoTotaisCapexOpex is not null
        defaultIndicadorProducaoShouldBeFound("vlrCustoTotaisCapexOpex.specified=true");

        // Get all the indicadorProducaoList where vlrCustoTotaisCapexOpex is null
        defaultIndicadorProducaoShouldNotBeFound("vlrCustoTotaisCapexOpex.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdCaptacoesIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdCaptacoes equals to DEFAULT_QTD_CAPTACOES
        defaultIndicadorProducaoShouldBeFound("qtdCaptacoes.equals=" + DEFAULT_QTD_CAPTACOES);

        // Get all the indicadorProducaoList where qtdCaptacoes equals to UPDATED_QTD_CAPTACOES
        defaultIndicadorProducaoShouldNotBeFound("qtdCaptacoes.equals=" + UPDATED_QTD_CAPTACOES);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdCaptacoesIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdCaptacoes in DEFAULT_QTD_CAPTACOES or UPDATED_QTD_CAPTACOES
        defaultIndicadorProducaoShouldBeFound("qtdCaptacoes.in=" + DEFAULT_QTD_CAPTACOES + "," + UPDATED_QTD_CAPTACOES);

        // Get all the indicadorProducaoList where qtdCaptacoes equals to UPDATED_QTD_CAPTACOES
        defaultIndicadorProducaoShouldNotBeFound("qtdCaptacoes.in=" + UPDATED_QTD_CAPTACOES);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdCaptacoesIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdCaptacoes is not null
        defaultIndicadorProducaoShouldBeFound("qtdCaptacoes.specified=true");

        // Get all the indicadorProducaoList where qtdCaptacoes is null
        defaultIndicadorProducaoShouldNotBeFound("qtdCaptacoes.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdCaptacoesIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdCaptacoes greater than or equals to DEFAULT_QTD_CAPTACOES
        defaultIndicadorProducaoShouldBeFound("qtdCaptacoes.greaterOrEqualThan=" + DEFAULT_QTD_CAPTACOES);

        // Get all the indicadorProducaoList where qtdCaptacoes greater than or equals to UPDATED_QTD_CAPTACOES
        defaultIndicadorProducaoShouldNotBeFound("qtdCaptacoes.greaterOrEqualThan=" + UPDATED_QTD_CAPTACOES);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdCaptacoesIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdCaptacoes less than or equals to DEFAULT_QTD_CAPTACOES
        defaultIndicadorProducaoShouldNotBeFound("qtdCaptacoes.lessThan=" + DEFAULT_QTD_CAPTACOES);

        // Get all the indicadorProducaoList where qtdCaptacoes less than or equals to UPDATED_QTD_CAPTACOES
        defaultIndicadorProducaoShouldBeFound("qtdCaptacoes.lessThan=" + UPDATED_QTD_CAPTACOES);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdEtasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdEtas equals to DEFAULT_QTD_ETAS
        defaultIndicadorProducaoShouldBeFound("qtdEtas.equals=" + DEFAULT_QTD_ETAS);

        // Get all the indicadorProducaoList where qtdEtas equals to UPDATED_QTD_ETAS
        defaultIndicadorProducaoShouldNotBeFound("qtdEtas.equals=" + UPDATED_QTD_ETAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdEtasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdEtas in DEFAULT_QTD_ETAS or UPDATED_QTD_ETAS
        defaultIndicadorProducaoShouldBeFound("qtdEtas.in=" + DEFAULT_QTD_ETAS + "," + UPDATED_QTD_ETAS);

        // Get all the indicadorProducaoList where qtdEtas equals to UPDATED_QTD_ETAS
        defaultIndicadorProducaoShouldNotBeFound("qtdEtas.in=" + UPDATED_QTD_ETAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdEtasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdEtas is not null
        defaultIndicadorProducaoShouldBeFound("qtdEtas.specified=true");

        // Get all the indicadorProducaoList where qtdEtas is null
        defaultIndicadorProducaoShouldNotBeFound("qtdEtas.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdEtasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdEtas greater than or equals to DEFAULT_QTD_ETAS
        defaultIndicadorProducaoShouldBeFound("qtdEtas.greaterOrEqualThan=" + DEFAULT_QTD_ETAS);

        // Get all the indicadorProducaoList where qtdEtas greater than or equals to UPDATED_QTD_ETAS
        defaultIndicadorProducaoShouldNotBeFound("qtdEtas.greaterOrEqualThan=" + UPDATED_QTD_ETAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdEtasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdEtas less than or equals to DEFAULT_QTD_ETAS
        defaultIndicadorProducaoShouldNotBeFound("qtdEtas.lessThan=" + DEFAULT_QTD_ETAS);

        // Get all the indicadorProducaoList where qtdEtas less than or equals to UPDATED_QTD_ETAS
        defaultIndicadorProducaoShouldBeFound("qtdEtas.lessThan=" + UPDATED_QTD_ETAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReservatoriosIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReservatorios equals to DEFAULT_QTD_RESERVATORIOS
        defaultIndicadorProducaoShouldBeFound("qtdReservatorios.equals=" + DEFAULT_QTD_RESERVATORIOS);

        // Get all the indicadorProducaoList where qtdReservatorios equals to UPDATED_QTD_RESERVATORIOS
        defaultIndicadorProducaoShouldNotBeFound("qtdReservatorios.equals=" + UPDATED_QTD_RESERVATORIOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReservatoriosIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReservatorios in DEFAULT_QTD_RESERVATORIOS or UPDATED_QTD_RESERVATORIOS
        defaultIndicadorProducaoShouldBeFound("qtdReservatorios.in=" + DEFAULT_QTD_RESERVATORIOS + "," + UPDATED_QTD_RESERVATORIOS);

        // Get all the indicadorProducaoList where qtdReservatorios equals to UPDATED_QTD_RESERVATORIOS
        defaultIndicadorProducaoShouldNotBeFound("qtdReservatorios.in=" + UPDATED_QTD_RESERVATORIOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReservatoriosIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReservatorios is not null
        defaultIndicadorProducaoShouldBeFound("qtdReservatorios.specified=true");

        // Get all the indicadorProducaoList where qtdReservatorios is null
        defaultIndicadorProducaoShouldNotBeFound("qtdReservatorios.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReservatoriosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReservatorios greater than or equals to DEFAULT_QTD_RESERVATORIOS
        defaultIndicadorProducaoShouldBeFound("qtdReservatorios.greaterOrEqualThan=" + DEFAULT_QTD_RESERVATORIOS);

        // Get all the indicadorProducaoList where qtdReservatorios greater than or equals to UPDATED_QTD_RESERVATORIOS
        defaultIndicadorProducaoShouldNotBeFound("qtdReservatorios.greaterOrEqualThan=" + UPDATED_QTD_RESERVATORIOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdReservatoriosIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdReservatorios less than or equals to DEFAULT_QTD_RESERVATORIOS
        defaultIndicadorProducaoShouldNotBeFound("qtdReservatorios.lessThan=" + DEFAULT_QTD_RESERVATORIOS);

        // Get all the indicadorProducaoList where qtdReservatorios less than or equals to UPDATED_QTD_RESERVATORIOS
        defaultIndicadorProducaoShouldBeFound("qtdReservatorios.lessThan=" + UPDATED_QTD_RESERVATORIOS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdEstacoesElevatoriasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdEstacoesElevatorias equals to DEFAULT_QTD_ESTACOES_ELEVATORIAS
        defaultIndicadorProducaoShouldBeFound("qtdEstacoesElevatorias.equals=" + DEFAULT_QTD_ESTACOES_ELEVATORIAS);

        // Get all the indicadorProducaoList where qtdEstacoesElevatorias equals to UPDATED_QTD_ESTACOES_ELEVATORIAS
        defaultIndicadorProducaoShouldNotBeFound("qtdEstacoesElevatorias.equals=" + UPDATED_QTD_ESTACOES_ELEVATORIAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdEstacoesElevatoriasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdEstacoesElevatorias in DEFAULT_QTD_ESTACOES_ELEVATORIAS or UPDATED_QTD_ESTACOES_ELEVATORIAS
        defaultIndicadorProducaoShouldBeFound("qtdEstacoesElevatorias.in=" + DEFAULT_QTD_ESTACOES_ELEVATORIAS + "," + UPDATED_QTD_ESTACOES_ELEVATORIAS);

        // Get all the indicadorProducaoList where qtdEstacoesElevatorias equals to UPDATED_QTD_ESTACOES_ELEVATORIAS
        defaultIndicadorProducaoShouldNotBeFound("qtdEstacoesElevatorias.in=" + UPDATED_QTD_ESTACOES_ELEVATORIAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdEstacoesElevatoriasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdEstacoesElevatorias is not null
        defaultIndicadorProducaoShouldBeFound("qtdEstacoesElevatorias.specified=true");

        // Get all the indicadorProducaoList where qtdEstacoesElevatorias is null
        defaultIndicadorProducaoShouldNotBeFound("qtdEstacoesElevatorias.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdEstacoesElevatoriasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdEstacoesElevatorias greater than or equals to DEFAULT_QTD_ESTACOES_ELEVATORIAS
        defaultIndicadorProducaoShouldBeFound("qtdEstacoesElevatorias.greaterOrEqualThan=" + DEFAULT_QTD_ESTACOES_ELEVATORIAS);

        // Get all the indicadorProducaoList where qtdEstacoesElevatorias greater than or equals to UPDATED_QTD_ESTACOES_ELEVATORIAS
        defaultIndicadorProducaoShouldNotBeFound("qtdEstacoesElevatorias.greaterOrEqualThan=" + UPDATED_QTD_ESTACOES_ELEVATORIAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdEstacoesElevatoriasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdEstacoesElevatorias less than or equals to DEFAULT_QTD_ESTACOES_ELEVATORIAS
        defaultIndicadorProducaoShouldNotBeFound("qtdEstacoesElevatorias.lessThan=" + DEFAULT_QTD_ESTACOES_ELEVATORIAS);

        // Get all the indicadorProducaoList where qtdEstacoesElevatorias less than or equals to UPDATED_QTD_ESTACOES_ELEVATORIAS
        defaultIndicadorProducaoShouldBeFound("qtdEstacoesElevatorias.lessThan=" + UPDATED_QTD_ESTACOES_ELEVATORIAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdComprimentoAdutorasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdComprimentoAdutoras equals to DEFAULT_QTD_COMPRIMENTO_ADUTORAS
        defaultIndicadorProducaoShouldBeFound("qtdComprimentoAdutoras.equals=" + DEFAULT_QTD_COMPRIMENTO_ADUTORAS);

        // Get all the indicadorProducaoList where qtdComprimentoAdutoras equals to UPDATED_QTD_COMPRIMENTO_ADUTORAS
        defaultIndicadorProducaoShouldNotBeFound("qtdComprimentoAdutoras.equals=" + UPDATED_QTD_COMPRIMENTO_ADUTORAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdComprimentoAdutorasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdComprimentoAdutoras in DEFAULT_QTD_COMPRIMENTO_ADUTORAS or UPDATED_QTD_COMPRIMENTO_ADUTORAS
        defaultIndicadorProducaoShouldBeFound("qtdComprimentoAdutoras.in=" + DEFAULT_QTD_COMPRIMENTO_ADUTORAS + "," + UPDATED_QTD_COMPRIMENTO_ADUTORAS);

        // Get all the indicadorProducaoList where qtdComprimentoAdutoras equals to UPDATED_QTD_COMPRIMENTO_ADUTORAS
        defaultIndicadorProducaoShouldNotBeFound("qtdComprimentoAdutoras.in=" + UPDATED_QTD_COMPRIMENTO_ADUTORAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdComprimentoAdutorasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdComprimentoAdutoras is not null
        defaultIndicadorProducaoShouldBeFound("qtdComprimentoAdutoras.specified=true");

        // Get all the indicadorProducaoList where qtdComprimentoAdutoras is null
        defaultIndicadorProducaoShouldNotBeFound("qtdComprimentoAdutoras.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdComprimentoRedesIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdComprimentoRedes equals to DEFAULT_QTD_COMPRIMENTO_REDES
        defaultIndicadorProducaoShouldBeFound("qtdComprimentoRedes.equals=" + DEFAULT_QTD_COMPRIMENTO_REDES);

        // Get all the indicadorProducaoList where qtdComprimentoRedes equals to UPDATED_QTD_COMPRIMENTO_REDES
        defaultIndicadorProducaoShouldNotBeFound("qtdComprimentoRedes.equals=" + UPDATED_QTD_COMPRIMENTO_REDES);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdComprimentoRedesIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdComprimentoRedes in DEFAULT_QTD_COMPRIMENTO_REDES or UPDATED_QTD_COMPRIMENTO_REDES
        defaultIndicadorProducaoShouldBeFound("qtdComprimentoRedes.in=" + DEFAULT_QTD_COMPRIMENTO_REDES + "," + UPDATED_QTD_COMPRIMENTO_REDES);

        // Get all the indicadorProducaoList where qtdComprimentoRedes equals to UPDATED_QTD_COMPRIMENTO_REDES
        defaultIndicadorProducaoShouldNotBeFound("qtdComprimentoRedes.in=" + UPDATED_QTD_COMPRIMENTO_REDES);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdComprimentoRedesIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdComprimentoRedes is not null
        defaultIndicadorProducaoShouldBeFound("qtdComprimentoRedes.specified=true");

        // Get all the indicadorProducaoList where qtdComprimentoRedes is null
        defaultIndicadorProducaoShouldNotBeFound("qtdComprimentoRedes.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdComprimentoRamaisIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdComprimentoRamais equals to DEFAULT_QTD_COMPRIMENTO_RAMAIS
        defaultIndicadorProducaoShouldBeFound("qtdComprimentoRamais.equals=" + DEFAULT_QTD_COMPRIMENTO_RAMAIS);

        // Get all the indicadorProducaoList where qtdComprimentoRamais equals to UPDATED_QTD_COMPRIMENTO_RAMAIS
        defaultIndicadorProducaoShouldNotBeFound("qtdComprimentoRamais.equals=" + UPDATED_QTD_COMPRIMENTO_RAMAIS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdComprimentoRamaisIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdComprimentoRamais in DEFAULT_QTD_COMPRIMENTO_RAMAIS or UPDATED_QTD_COMPRIMENTO_RAMAIS
        defaultIndicadorProducaoShouldBeFound("qtdComprimentoRamais.in=" + DEFAULT_QTD_COMPRIMENTO_RAMAIS + "," + UPDATED_QTD_COMPRIMENTO_RAMAIS);

        // Get all the indicadorProducaoList where qtdComprimentoRamais equals to UPDATED_QTD_COMPRIMENTO_RAMAIS
        defaultIndicadorProducaoShouldNotBeFound("qtdComprimentoRamais.in=" + UPDATED_QTD_COMPRIMENTO_RAMAIS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdComprimentoRamaisIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdComprimentoRamais is not null
        defaultIndicadorProducaoShouldBeFound("qtdComprimentoRamais.specified=true");

        // Get all the indicadorProducaoList where qtdComprimentoRamais is null
        defaultIndicadorProducaoShouldNotBeFound("qtdComprimentoRamais.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoMoPlaneadasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMoPlaneadas equals to DEFAULT_QTD_ACOES_FORMACAO_MO_PLANEADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoMoPlaneadas.equals=" + DEFAULT_QTD_ACOES_FORMACAO_MO_PLANEADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMoPlaneadas equals to UPDATED_QTD_ACOES_FORMACAO_MO_PLANEADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoMoPlaneadas.equals=" + UPDATED_QTD_ACOES_FORMACAO_MO_PLANEADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoMoPlaneadasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMoPlaneadas in DEFAULT_QTD_ACOES_FORMACAO_MO_PLANEADAS or UPDATED_QTD_ACOES_FORMACAO_MO_PLANEADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoMoPlaneadas.in=" + DEFAULT_QTD_ACOES_FORMACAO_MO_PLANEADAS + "," + UPDATED_QTD_ACOES_FORMACAO_MO_PLANEADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMoPlaneadas equals to UPDATED_QTD_ACOES_FORMACAO_MO_PLANEADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoMoPlaneadas.in=" + UPDATED_QTD_ACOES_FORMACAO_MO_PLANEADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoMoPlaneadasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMoPlaneadas is not null
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoMoPlaneadas.specified=true");

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMoPlaneadas is null
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoMoPlaneadas.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoMoPlaneadasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMoPlaneadas greater than or equals to DEFAULT_QTD_ACOES_FORMACAO_MO_PLANEADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoMoPlaneadas.greaterOrEqualThan=" + DEFAULT_QTD_ACOES_FORMACAO_MO_PLANEADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMoPlaneadas greater than or equals to UPDATED_QTD_ACOES_FORMACAO_MO_PLANEADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoMoPlaneadas.greaterOrEqualThan=" + UPDATED_QTD_ACOES_FORMACAO_MO_PLANEADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoMoPlaneadasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMoPlaneadas less than or equals to DEFAULT_QTD_ACOES_FORMACAO_MO_PLANEADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoMoPlaneadas.lessThan=" + DEFAULT_QTD_ACOES_FORMACAO_MO_PLANEADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMoPlaneadas less than or equals to UPDATED_QTD_ACOES_FORMACAO_MO_PLANEADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoMoPlaneadas.lessThan=" + UPDATED_QTD_ACOES_FORMACAO_MO_PLANEADAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoMmsPlaneadasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMmsPlaneadas equals to DEFAULT_QTD_ACOES_FORMACAO_MMS_PLANEADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoMmsPlaneadas.equals=" + DEFAULT_QTD_ACOES_FORMACAO_MMS_PLANEADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMmsPlaneadas equals to UPDATED_QTD_ACOES_FORMACAO_MMS_PLANEADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoMmsPlaneadas.equals=" + UPDATED_QTD_ACOES_FORMACAO_MMS_PLANEADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoMmsPlaneadasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMmsPlaneadas in DEFAULT_QTD_ACOES_FORMACAO_MMS_PLANEADAS or UPDATED_QTD_ACOES_FORMACAO_MMS_PLANEADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoMmsPlaneadas.in=" + DEFAULT_QTD_ACOES_FORMACAO_MMS_PLANEADAS + "," + UPDATED_QTD_ACOES_FORMACAO_MMS_PLANEADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMmsPlaneadas equals to UPDATED_QTD_ACOES_FORMACAO_MMS_PLANEADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoMmsPlaneadas.in=" + UPDATED_QTD_ACOES_FORMACAO_MMS_PLANEADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoMmsPlaneadasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMmsPlaneadas is not null
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoMmsPlaneadas.specified=true");

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMmsPlaneadas is null
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoMmsPlaneadas.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoMmsPlaneadasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMmsPlaneadas greater than or equals to DEFAULT_QTD_ACOES_FORMACAO_MMS_PLANEADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoMmsPlaneadas.greaterOrEqualThan=" + DEFAULT_QTD_ACOES_FORMACAO_MMS_PLANEADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMmsPlaneadas greater than or equals to UPDATED_QTD_ACOES_FORMACAO_MMS_PLANEADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoMmsPlaneadas.greaterOrEqualThan=" + UPDATED_QTD_ACOES_FORMACAO_MMS_PLANEADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoMmsPlaneadasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMmsPlaneadas less than or equals to DEFAULT_QTD_ACOES_FORMACAO_MMS_PLANEADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoMmsPlaneadas.lessThan=" + DEFAULT_QTD_ACOES_FORMACAO_MMS_PLANEADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMmsPlaneadas less than or equals to UPDATED_QTD_ACOES_FORMACAO_MMS_PLANEADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoMmsPlaneadas.lessThan=" + UPDATED_QTD_ACOES_FORMACAO_MMS_PLANEADAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoCmpPlaneadasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoCmpPlaneadas equals to DEFAULT_QTD_ACOES_FORMACAO_CMP_PLANEADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoCmpPlaneadas.equals=" + DEFAULT_QTD_ACOES_FORMACAO_CMP_PLANEADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoCmpPlaneadas equals to UPDATED_QTD_ACOES_FORMACAO_CMP_PLANEADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoCmpPlaneadas.equals=" + UPDATED_QTD_ACOES_FORMACAO_CMP_PLANEADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoCmpPlaneadasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoCmpPlaneadas in DEFAULT_QTD_ACOES_FORMACAO_CMP_PLANEADAS or UPDATED_QTD_ACOES_FORMACAO_CMP_PLANEADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoCmpPlaneadas.in=" + DEFAULT_QTD_ACOES_FORMACAO_CMP_PLANEADAS + "," + UPDATED_QTD_ACOES_FORMACAO_CMP_PLANEADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoCmpPlaneadas equals to UPDATED_QTD_ACOES_FORMACAO_CMP_PLANEADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoCmpPlaneadas.in=" + UPDATED_QTD_ACOES_FORMACAO_CMP_PLANEADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoCmpPlaneadasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoCmpPlaneadas is not null
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoCmpPlaneadas.specified=true");

        // Get all the indicadorProducaoList where qtdAcoesFormacaoCmpPlaneadas is null
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoCmpPlaneadas.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoCmpPlaneadasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoCmpPlaneadas greater than or equals to DEFAULT_QTD_ACOES_FORMACAO_CMP_PLANEADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoCmpPlaneadas.greaterOrEqualThan=" + DEFAULT_QTD_ACOES_FORMACAO_CMP_PLANEADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoCmpPlaneadas greater than or equals to UPDATED_QTD_ACOES_FORMACAO_CMP_PLANEADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoCmpPlaneadas.greaterOrEqualThan=" + UPDATED_QTD_ACOES_FORMACAO_CMP_PLANEADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoCmpPlaneadasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoCmpPlaneadas less than or equals to DEFAULT_QTD_ACOES_FORMACAO_CMP_PLANEADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoCmpPlaneadas.lessThan=" + DEFAULT_QTD_ACOES_FORMACAO_CMP_PLANEADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoCmpPlaneadas less than or equals to UPDATED_QTD_ACOES_FORMACAO_CMP_PLANEADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoCmpPlaneadas.lessThan=" + UPDATED_QTD_ACOES_FORMACAO_CMP_PLANEADAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoSoftwareFornecidosPlanejadasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoSoftwareFornecidosPlanejadas equals to DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoSoftwareFornecidosPlanejadas.equals=" + DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoSoftwareFornecidosPlanejadas equals to UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoSoftwareFornecidosPlanejadas.equals=" + UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoSoftwareFornecidosPlanejadasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoSoftwareFornecidosPlanejadas in DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS or UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoSoftwareFornecidosPlanejadas.in=" + DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS + "," + UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoSoftwareFornecidosPlanejadas equals to UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoSoftwareFornecidosPlanejadas.in=" + UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoSoftwareFornecidosPlanejadasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoSoftwareFornecidosPlanejadas is not null
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoSoftwareFornecidosPlanejadas.specified=true");

        // Get all the indicadorProducaoList where qtdAcoesFormacaoSoftwareFornecidosPlanejadas is null
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoSoftwareFornecidosPlanejadas.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoSoftwareFornecidosPlanejadasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoSoftwareFornecidosPlanejadas greater than or equals to DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoSoftwareFornecidosPlanejadas.greaterOrEqualThan=" + DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoSoftwareFornecidosPlanejadas greater than or equals to UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoSoftwareFornecidosPlanejadas.greaterOrEqualThan=" + UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoSoftwareFornecidosPlanejadasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoSoftwareFornecidosPlanejadas less than or equals to DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoSoftwareFornecidosPlanejadas.lessThan=" + DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoSoftwareFornecidosPlanejadas less than or equals to UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoSoftwareFornecidosPlanejadas.lessThan=" + UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoMoRealizadasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMoRealizadas equals to DEFAULT_QTD_ACOES_FORMACAO_MO_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoMoRealizadas.equals=" + DEFAULT_QTD_ACOES_FORMACAO_MO_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMoRealizadas equals to UPDATED_QTD_ACOES_FORMACAO_MO_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoMoRealizadas.equals=" + UPDATED_QTD_ACOES_FORMACAO_MO_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoMoRealizadasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMoRealizadas in DEFAULT_QTD_ACOES_FORMACAO_MO_REALIZADAS or UPDATED_QTD_ACOES_FORMACAO_MO_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoMoRealizadas.in=" + DEFAULT_QTD_ACOES_FORMACAO_MO_REALIZADAS + "," + UPDATED_QTD_ACOES_FORMACAO_MO_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMoRealizadas equals to UPDATED_QTD_ACOES_FORMACAO_MO_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoMoRealizadas.in=" + UPDATED_QTD_ACOES_FORMACAO_MO_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoMoRealizadasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMoRealizadas is not null
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoMoRealizadas.specified=true");

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMoRealizadas is null
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoMoRealizadas.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoMoRealizadasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMoRealizadas greater than or equals to DEFAULT_QTD_ACOES_FORMACAO_MO_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoMoRealizadas.greaterOrEqualThan=" + DEFAULT_QTD_ACOES_FORMACAO_MO_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMoRealizadas greater than or equals to UPDATED_QTD_ACOES_FORMACAO_MO_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoMoRealizadas.greaterOrEqualThan=" + UPDATED_QTD_ACOES_FORMACAO_MO_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoMoRealizadasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMoRealizadas less than or equals to DEFAULT_QTD_ACOES_FORMACAO_MO_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoMoRealizadas.lessThan=" + DEFAULT_QTD_ACOES_FORMACAO_MO_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMoRealizadas less than or equals to UPDATED_QTD_ACOES_FORMACAO_MO_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoMoRealizadas.lessThan=" + UPDATED_QTD_ACOES_FORMACAO_MO_REALIZADAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoMmsRealizadasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMmsRealizadas equals to DEFAULT_QTD_ACOES_FORMACAO_MMS_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoMmsRealizadas.equals=" + DEFAULT_QTD_ACOES_FORMACAO_MMS_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMmsRealizadas equals to UPDATED_QTD_ACOES_FORMACAO_MMS_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoMmsRealizadas.equals=" + UPDATED_QTD_ACOES_FORMACAO_MMS_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoMmsRealizadasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMmsRealizadas in DEFAULT_QTD_ACOES_FORMACAO_MMS_REALIZADAS or UPDATED_QTD_ACOES_FORMACAO_MMS_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoMmsRealizadas.in=" + DEFAULT_QTD_ACOES_FORMACAO_MMS_REALIZADAS + "," + UPDATED_QTD_ACOES_FORMACAO_MMS_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMmsRealizadas equals to UPDATED_QTD_ACOES_FORMACAO_MMS_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoMmsRealizadas.in=" + UPDATED_QTD_ACOES_FORMACAO_MMS_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoMmsRealizadasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMmsRealizadas is not null
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoMmsRealizadas.specified=true");

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMmsRealizadas is null
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoMmsRealizadas.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoMmsRealizadasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMmsRealizadas greater than or equals to DEFAULT_QTD_ACOES_FORMACAO_MMS_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoMmsRealizadas.greaterOrEqualThan=" + DEFAULT_QTD_ACOES_FORMACAO_MMS_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMmsRealizadas greater than or equals to UPDATED_QTD_ACOES_FORMACAO_MMS_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoMmsRealizadas.greaterOrEqualThan=" + UPDATED_QTD_ACOES_FORMACAO_MMS_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoMmsRealizadasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMmsRealizadas less than or equals to DEFAULT_QTD_ACOES_FORMACAO_MMS_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoMmsRealizadas.lessThan=" + DEFAULT_QTD_ACOES_FORMACAO_MMS_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoMmsRealizadas less than or equals to UPDATED_QTD_ACOES_FORMACAO_MMS_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoMmsRealizadas.lessThan=" + UPDATED_QTD_ACOES_FORMACAO_MMS_REALIZADAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoCmpRealizadasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoCmpRealizadas equals to DEFAULT_QTD_ACOES_FORMACAO_CMP_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoCmpRealizadas.equals=" + DEFAULT_QTD_ACOES_FORMACAO_CMP_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoCmpRealizadas equals to UPDATED_QTD_ACOES_FORMACAO_CMP_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoCmpRealizadas.equals=" + UPDATED_QTD_ACOES_FORMACAO_CMP_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoCmpRealizadasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoCmpRealizadas in DEFAULT_QTD_ACOES_FORMACAO_CMP_REALIZADAS or UPDATED_QTD_ACOES_FORMACAO_CMP_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoCmpRealizadas.in=" + DEFAULT_QTD_ACOES_FORMACAO_CMP_REALIZADAS + "," + UPDATED_QTD_ACOES_FORMACAO_CMP_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoCmpRealizadas equals to UPDATED_QTD_ACOES_FORMACAO_CMP_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoCmpRealizadas.in=" + UPDATED_QTD_ACOES_FORMACAO_CMP_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoCmpRealizadasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoCmpRealizadas is not null
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoCmpRealizadas.specified=true");

        // Get all the indicadorProducaoList where qtdAcoesFormacaoCmpRealizadas is null
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoCmpRealizadas.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoCmpRealizadasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoCmpRealizadas greater than or equals to DEFAULT_QTD_ACOES_FORMACAO_CMP_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoCmpRealizadas.greaterOrEqualThan=" + DEFAULT_QTD_ACOES_FORMACAO_CMP_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoCmpRealizadas greater than or equals to UPDATED_QTD_ACOES_FORMACAO_CMP_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoCmpRealizadas.greaterOrEqualThan=" + UPDATED_QTD_ACOES_FORMACAO_CMP_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoCmpRealizadasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoCmpRealizadas less than or equals to DEFAULT_QTD_ACOES_FORMACAO_CMP_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoCmpRealizadas.lessThan=" + DEFAULT_QTD_ACOES_FORMACAO_CMP_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoCmpRealizadas less than or equals to UPDATED_QTD_ACOES_FORMACAO_CMP_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoCmpRealizadas.lessThan=" + UPDATED_QTD_ACOES_FORMACAO_CMP_REALIZADAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoSoftwareFornecidosRealizadasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoSoftwareFornecidosRealizadas equals to DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoSoftwareFornecidosRealizadas.equals=" + DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoSoftwareFornecidosRealizadas equals to UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoSoftwareFornecidosRealizadas.equals=" + UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoSoftwareFornecidosRealizadasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoSoftwareFornecidosRealizadas in DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS or UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoSoftwareFornecidosRealizadas.in=" + DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS + "," + UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoSoftwareFornecidosRealizadas equals to UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoSoftwareFornecidosRealizadas.in=" + UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoSoftwareFornecidosRealizadasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoSoftwareFornecidosRealizadas is not null
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoSoftwareFornecidosRealizadas.specified=true");

        // Get all the indicadorProducaoList where qtdAcoesFormacaoSoftwareFornecidosRealizadas is null
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoSoftwareFornecidosRealizadas.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoSoftwareFornecidosRealizadasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoSoftwareFornecidosRealizadas greater than or equals to DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoSoftwareFornecidosRealizadas.greaterOrEqualThan=" + DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoSoftwareFornecidosRealizadas greater than or equals to UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoSoftwareFornecidosRealizadas.greaterOrEqualThan=" + UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoSoftwareFornecidosRealizadasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoSoftwareFornecidosRealizadas less than or equals to DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoSoftwareFornecidosRealizadas.lessThan=" + DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoSoftwareFornecidosRealizadas less than or equals to UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoSoftwareFornecidosRealizadas.lessThan=" + UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoRealizadasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoRealizadas equals to DEFAULT_QTD_ACOES_FORMACAO_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoRealizadas.equals=" + DEFAULT_QTD_ACOES_FORMACAO_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoRealizadas equals to UPDATED_QTD_ACOES_FORMACAO_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoRealizadas.equals=" + UPDATED_QTD_ACOES_FORMACAO_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoRealizadasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoRealizadas in DEFAULT_QTD_ACOES_FORMACAO_REALIZADAS or UPDATED_QTD_ACOES_FORMACAO_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoRealizadas.in=" + DEFAULT_QTD_ACOES_FORMACAO_REALIZADAS + "," + UPDATED_QTD_ACOES_FORMACAO_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoRealizadas equals to UPDATED_QTD_ACOES_FORMACAO_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoRealizadas.in=" + UPDATED_QTD_ACOES_FORMACAO_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoRealizadasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoRealizadas is not null
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoRealizadas.specified=true");

        // Get all the indicadorProducaoList where qtdAcoesFormacaoRealizadas is null
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoRealizadas.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoRealizadasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoRealizadas greater than or equals to DEFAULT_QTD_ACOES_FORMACAO_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoRealizadas.greaterOrEqualThan=" + DEFAULT_QTD_ACOES_FORMACAO_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoRealizadas greater than or equals to UPDATED_QTD_ACOES_FORMACAO_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoRealizadas.greaterOrEqualThan=" + UPDATED_QTD_ACOES_FORMACAO_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesFormacaoRealizadasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoRealizadas less than or equals to DEFAULT_QTD_ACOES_FORMACAO_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesFormacaoRealizadas.lessThan=" + DEFAULT_QTD_ACOES_FORMACAO_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesFormacaoRealizadas less than or equals to UPDATED_QTD_ACOES_FORMACAO_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesFormacaoRealizadas.lessThan=" + UPDATED_QTD_ACOES_FORMACAO_REALIZADAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisMoPrevistosIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisMoPrevistos equals to DEFAULT_QTD_MANUAIS_MO_PREVISTOS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisMoPrevistos.equals=" + DEFAULT_QTD_MANUAIS_MO_PREVISTOS);

        // Get all the indicadorProducaoList where qtdManuaisMoPrevistos equals to UPDATED_QTD_MANUAIS_MO_PREVISTOS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisMoPrevistos.equals=" + UPDATED_QTD_MANUAIS_MO_PREVISTOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisMoPrevistosIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisMoPrevistos in DEFAULT_QTD_MANUAIS_MO_PREVISTOS or UPDATED_QTD_MANUAIS_MO_PREVISTOS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisMoPrevistos.in=" + DEFAULT_QTD_MANUAIS_MO_PREVISTOS + "," + UPDATED_QTD_MANUAIS_MO_PREVISTOS);

        // Get all the indicadorProducaoList where qtdManuaisMoPrevistos equals to UPDATED_QTD_MANUAIS_MO_PREVISTOS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisMoPrevistos.in=" + UPDATED_QTD_MANUAIS_MO_PREVISTOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisMoPrevistosIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisMoPrevistos is not null
        defaultIndicadorProducaoShouldBeFound("qtdManuaisMoPrevistos.specified=true");

        // Get all the indicadorProducaoList where qtdManuaisMoPrevistos is null
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisMoPrevistos.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisMoPrevistosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisMoPrevistos greater than or equals to DEFAULT_QTD_MANUAIS_MO_PREVISTOS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisMoPrevistos.greaterOrEqualThan=" + DEFAULT_QTD_MANUAIS_MO_PREVISTOS);

        // Get all the indicadorProducaoList where qtdManuaisMoPrevistos greater than or equals to UPDATED_QTD_MANUAIS_MO_PREVISTOS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisMoPrevistos.greaterOrEqualThan=" + UPDATED_QTD_MANUAIS_MO_PREVISTOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisMoPrevistosIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisMoPrevistos less than or equals to DEFAULT_QTD_MANUAIS_MO_PREVISTOS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisMoPrevistos.lessThan=" + DEFAULT_QTD_MANUAIS_MO_PREVISTOS);

        // Get all the indicadorProducaoList where qtdManuaisMoPrevistos less than or equals to UPDATED_QTD_MANUAIS_MO_PREVISTOS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisMoPrevistos.lessThan=" + UPDATED_QTD_MANUAIS_MO_PREVISTOS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisMmsPrevistosIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisMmsPrevistos equals to DEFAULT_QTD_MANUAIS_MMS_PREVISTOS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisMmsPrevistos.equals=" + DEFAULT_QTD_MANUAIS_MMS_PREVISTOS);

        // Get all the indicadorProducaoList where qtdManuaisMmsPrevistos equals to UPDATED_QTD_MANUAIS_MMS_PREVISTOS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisMmsPrevistos.equals=" + UPDATED_QTD_MANUAIS_MMS_PREVISTOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisMmsPrevistosIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisMmsPrevistos in DEFAULT_QTD_MANUAIS_MMS_PREVISTOS or UPDATED_QTD_MANUAIS_MMS_PREVISTOS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisMmsPrevistos.in=" + DEFAULT_QTD_MANUAIS_MMS_PREVISTOS + "," + UPDATED_QTD_MANUAIS_MMS_PREVISTOS);

        // Get all the indicadorProducaoList where qtdManuaisMmsPrevistos equals to UPDATED_QTD_MANUAIS_MMS_PREVISTOS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisMmsPrevistos.in=" + UPDATED_QTD_MANUAIS_MMS_PREVISTOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisMmsPrevistosIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisMmsPrevistos is not null
        defaultIndicadorProducaoShouldBeFound("qtdManuaisMmsPrevistos.specified=true");

        // Get all the indicadorProducaoList where qtdManuaisMmsPrevistos is null
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisMmsPrevistos.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisMmsPrevistosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisMmsPrevistos greater than or equals to DEFAULT_QTD_MANUAIS_MMS_PREVISTOS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisMmsPrevistos.greaterOrEqualThan=" + DEFAULT_QTD_MANUAIS_MMS_PREVISTOS);

        // Get all the indicadorProducaoList where qtdManuaisMmsPrevistos greater than or equals to UPDATED_QTD_MANUAIS_MMS_PREVISTOS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisMmsPrevistos.greaterOrEqualThan=" + UPDATED_QTD_MANUAIS_MMS_PREVISTOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisMmsPrevistosIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisMmsPrevistos less than or equals to DEFAULT_QTD_MANUAIS_MMS_PREVISTOS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisMmsPrevistos.lessThan=" + DEFAULT_QTD_MANUAIS_MMS_PREVISTOS);

        // Get all the indicadorProducaoList where qtdManuaisMmsPrevistos less than or equals to UPDATED_QTD_MANUAIS_MMS_PREVISTOS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisMmsPrevistos.lessThan=" + UPDATED_QTD_MANUAIS_MMS_PREVISTOS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisCmpPrevistosIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisCmpPrevistos equals to DEFAULT_QTD_MANUAIS_CMP_PREVISTOS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisCmpPrevistos.equals=" + DEFAULT_QTD_MANUAIS_CMP_PREVISTOS);

        // Get all the indicadorProducaoList where qtdManuaisCmpPrevistos equals to UPDATED_QTD_MANUAIS_CMP_PREVISTOS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisCmpPrevistos.equals=" + UPDATED_QTD_MANUAIS_CMP_PREVISTOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisCmpPrevistosIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisCmpPrevistos in DEFAULT_QTD_MANUAIS_CMP_PREVISTOS or UPDATED_QTD_MANUAIS_CMP_PREVISTOS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisCmpPrevistos.in=" + DEFAULT_QTD_MANUAIS_CMP_PREVISTOS + "," + UPDATED_QTD_MANUAIS_CMP_PREVISTOS);

        // Get all the indicadorProducaoList where qtdManuaisCmpPrevistos equals to UPDATED_QTD_MANUAIS_CMP_PREVISTOS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisCmpPrevistos.in=" + UPDATED_QTD_MANUAIS_CMP_PREVISTOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisCmpPrevistosIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisCmpPrevistos is not null
        defaultIndicadorProducaoShouldBeFound("qtdManuaisCmpPrevistos.specified=true");

        // Get all the indicadorProducaoList where qtdManuaisCmpPrevistos is null
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisCmpPrevistos.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisCmpPrevistosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisCmpPrevistos greater than or equals to DEFAULT_QTD_MANUAIS_CMP_PREVISTOS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisCmpPrevistos.greaterOrEqualThan=" + DEFAULT_QTD_MANUAIS_CMP_PREVISTOS);

        // Get all the indicadorProducaoList where qtdManuaisCmpPrevistos greater than or equals to UPDATED_QTD_MANUAIS_CMP_PREVISTOS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisCmpPrevistos.greaterOrEqualThan=" + UPDATED_QTD_MANUAIS_CMP_PREVISTOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisCmpPrevistosIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisCmpPrevistos less than or equals to DEFAULT_QTD_MANUAIS_CMP_PREVISTOS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisCmpPrevistos.lessThan=" + DEFAULT_QTD_MANUAIS_CMP_PREVISTOS);

        // Get all the indicadorProducaoList where qtdManuaisCmpPrevistos less than or equals to UPDATED_QTD_MANUAIS_CMP_PREVISTOS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisCmpPrevistos.lessThan=" + UPDATED_QTD_MANUAIS_CMP_PREVISTOS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisPrevistosIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisPrevistos equals to DEFAULT_QTD_MANUAIS_PREVISTOS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisPrevistos.equals=" + DEFAULT_QTD_MANUAIS_PREVISTOS);

        // Get all the indicadorProducaoList where qtdManuaisPrevistos equals to UPDATED_QTD_MANUAIS_PREVISTOS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisPrevistos.equals=" + UPDATED_QTD_MANUAIS_PREVISTOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisPrevistosIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisPrevistos in DEFAULT_QTD_MANUAIS_PREVISTOS or UPDATED_QTD_MANUAIS_PREVISTOS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisPrevistos.in=" + DEFAULT_QTD_MANUAIS_PREVISTOS + "," + UPDATED_QTD_MANUAIS_PREVISTOS);

        // Get all the indicadorProducaoList where qtdManuaisPrevistos equals to UPDATED_QTD_MANUAIS_PREVISTOS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisPrevistos.in=" + UPDATED_QTD_MANUAIS_PREVISTOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisPrevistosIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisPrevistos is not null
        defaultIndicadorProducaoShouldBeFound("qtdManuaisPrevistos.specified=true");

        // Get all the indicadorProducaoList where qtdManuaisPrevistos is null
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisPrevistos.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisPrevistosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisPrevistos greater than or equals to DEFAULT_QTD_MANUAIS_PREVISTOS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisPrevistos.greaterOrEqualThan=" + DEFAULT_QTD_MANUAIS_PREVISTOS);

        // Get all the indicadorProducaoList where qtdManuaisPrevistos greater than or equals to UPDATED_QTD_MANUAIS_PREVISTOS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisPrevistos.greaterOrEqualThan=" + UPDATED_QTD_MANUAIS_PREVISTOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisPrevistosIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisPrevistos less than or equals to DEFAULT_QTD_MANUAIS_PREVISTOS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisPrevistos.lessThan=" + DEFAULT_QTD_MANUAIS_PREVISTOS);

        // Get all the indicadorProducaoList where qtdManuaisPrevistos less than or equals to UPDATED_QTD_MANUAIS_PREVISTOS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisPrevistos.lessThan=" + UPDATED_QTD_MANUAIS_PREVISTOS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesManuaisMoRealizadasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesManuaisMoRealizadas equals to DEFAULT_QTD_ACOES_MANUAIS_MO_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesManuaisMoRealizadas.equals=" + DEFAULT_QTD_ACOES_MANUAIS_MO_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesManuaisMoRealizadas equals to UPDATED_QTD_ACOES_MANUAIS_MO_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesManuaisMoRealizadas.equals=" + UPDATED_QTD_ACOES_MANUAIS_MO_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesManuaisMoRealizadasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesManuaisMoRealizadas in DEFAULT_QTD_ACOES_MANUAIS_MO_REALIZADAS or UPDATED_QTD_ACOES_MANUAIS_MO_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesManuaisMoRealizadas.in=" + DEFAULT_QTD_ACOES_MANUAIS_MO_REALIZADAS + "," + UPDATED_QTD_ACOES_MANUAIS_MO_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesManuaisMoRealizadas equals to UPDATED_QTD_ACOES_MANUAIS_MO_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesManuaisMoRealizadas.in=" + UPDATED_QTD_ACOES_MANUAIS_MO_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesManuaisMoRealizadasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesManuaisMoRealizadas is not null
        defaultIndicadorProducaoShouldBeFound("qtdAcoesManuaisMoRealizadas.specified=true");

        // Get all the indicadorProducaoList where qtdAcoesManuaisMoRealizadas is null
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesManuaisMoRealizadas.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesManuaisMoRealizadasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesManuaisMoRealizadas greater than or equals to DEFAULT_QTD_ACOES_MANUAIS_MO_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesManuaisMoRealizadas.greaterOrEqualThan=" + DEFAULT_QTD_ACOES_MANUAIS_MO_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesManuaisMoRealizadas greater than or equals to UPDATED_QTD_ACOES_MANUAIS_MO_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesManuaisMoRealizadas.greaterOrEqualThan=" + UPDATED_QTD_ACOES_MANUAIS_MO_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdAcoesManuaisMoRealizadasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdAcoesManuaisMoRealizadas less than or equals to DEFAULT_QTD_ACOES_MANUAIS_MO_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdAcoesManuaisMoRealizadas.lessThan=" + DEFAULT_QTD_ACOES_MANUAIS_MO_REALIZADAS);

        // Get all the indicadorProducaoList where qtdAcoesManuaisMoRealizadas less than or equals to UPDATED_QTD_ACOES_MANUAIS_MO_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdAcoesManuaisMoRealizadas.lessThan=" + UPDATED_QTD_ACOES_MANUAIS_MO_REALIZADAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisMmsRealizadasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisMmsRealizadas equals to DEFAULT_QTD_MANUAIS_MMS_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisMmsRealizadas.equals=" + DEFAULT_QTD_MANUAIS_MMS_REALIZADAS);

        // Get all the indicadorProducaoList where qtdManuaisMmsRealizadas equals to UPDATED_QTD_MANUAIS_MMS_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisMmsRealizadas.equals=" + UPDATED_QTD_MANUAIS_MMS_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisMmsRealizadasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisMmsRealizadas in DEFAULT_QTD_MANUAIS_MMS_REALIZADAS or UPDATED_QTD_MANUAIS_MMS_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisMmsRealizadas.in=" + DEFAULT_QTD_MANUAIS_MMS_REALIZADAS + "," + UPDATED_QTD_MANUAIS_MMS_REALIZADAS);

        // Get all the indicadorProducaoList where qtdManuaisMmsRealizadas equals to UPDATED_QTD_MANUAIS_MMS_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisMmsRealizadas.in=" + UPDATED_QTD_MANUAIS_MMS_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisMmsRealizadasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisMmsRealizadas is not null
        defaultIndicadorProducaoShouldBeFound("qtdManuaisMmsRealizadas.specified=true");

        // Get all the indicadorProducaoList where qtdManuaisMmsRealizadas is null
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisMmsRealizadas.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisMmsRealizadasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisMmsRealizadas greater than or equals to DEFAULT_QTD_MANUAIS_MMS_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisMmsRealizadas.greaterOrEqualThan=" + DEFAULT_QTD_MANUAIS_MMS_REALIZADAS);

        // Get all the indicadorProducaoList where qtdManuaisMmsRealizadas greater than or equals to UPDATED_QTD_MANUAIS_MMS_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisMmsRealizadas.greaterOrEqualThan=" + UPDATED_QTD_MANUAIS_MMS_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisMmsRealizadasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisMmsRealizadas less than or equals to DEFAULT_QTD_MANUAIS_MMS_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisMmsRealizadas.lessThan=" + DEFAULT_QTD_MANUAIS_MMS_REALIZADAS);

        // Get all the indicadorProducaoList where qtdManuaisMmsRealizadas less than or equals to UPDATED_QTD_MANUAIS_MMS_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisMmsRealizadas.lessThan=" + UPDATED_QTD_MANUAIS_MMS_REALIZADAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisCmpRealizadasIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisCmpRealizadas equals to DEFAULT_QTD_MANUAIS_CMP_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisCmpRealizadas.equals=" + DEFAULT_QTD_MANUAIS_CMP_REALIZADAS);

        // Get all the indicadorProducaoList where qtdManuaisCmpRealizadas equals to UPDATED_QTD_MANUAIS_CMP_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisCmpRealizadas.equals=" + UPDATED_QTD_MANUAIS_CMP_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisCmpRealizadasIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisCmpRealizadas in DEFAULT_QTD_MANUAIS_CMP_REALIZADAS or UPDATED_QTD_MANUAIS_CMP_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisCmpRealizadas.in=" + DEFAULT_QTD_MANUAIS_CMP_REALIZADAS + "," + UPDATED_QTD_MANUAIS_CMP_REALIZADAS);

        // Get all the indicadorProducaoList where qtdManuaisCmpRealizadas equals to UPDATED_QTD_MANUAIS_CMP_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisCmpRealizadas.in=" + UPDATED_QTD_MANUAIS_CMP_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisCmpRealizadasIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisCmpRealizadas is not null
        defaultIndicadorProducaoShouldBeFound("qtdManuaisCmpRealizadas.specified=true");

        // Get all the indicadorProducaoList where qtdManuaisCmpRealizadas is null
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisCmpRealizadas.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisCmpRealizadasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisCmpRealizadas greater than or equals to DEFAULT_QTD_MANUAIS_CMP_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisCmpRealizadas.greaterOrEqualThan=" + DEFAULT_QTD_MANUAIS_CMP_REALIZADAS);

        // Get all the indicadorProducaoList where qtdManuaisCmpRealizadas greater than or equals to UPDATED_QTD_MANUAIS_CMP_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisCmpRealizadas.greaterOrEqualThan=" + UPDATED_QTD_MANUAIS_CMP_REALIZADAS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisCmpRealizadasIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisCmpRealizadas less than or equals to DEFAULT_QTD_MANUAIS_CMP_REALIZADAS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisCmpRealizadas.lessThan=" + DEFAULT_QTD_MANUAIS_CMP_REALIZADAS);

        // Get all the indicadorProducaoList where qtdManuaisCmpRealizadas less than or equals to UPDATED_QTD_MANUAIS_CMP_REALIZADAS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisCmpRealizadas.lessThan=" + UPDATED_QTD_MANUAIS_CMP_REALIZADAS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisRealizadosIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisRealizados equals to DEFAULT_QTD_MANUAIS_REALIZADOS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisRealizados.equals=" + DEFAULT_QTD_MANUAIS_REALIZADOS);

        // Get all the indicadorProducaoList where qtdManuaisRealizados equals to UPDATED_QTD_MANUAIS_REALIZADOS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisRealizados.equals=" + UPDATED_QTD_MANUAIS_REALIZADOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisRealizadosIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisRealizados in DEFAULT_QTD_MANUAIS_REALIZADOS or UPDATED_QTD_MANUAIS_REALIZADOS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisRealizados.in=" + DEFAULT_QTD_MANUAIS_REALIZADOS + "," + UPDATED_QTD_MANUAIS_REALIZADOS);

        // Get all the indicadorProducaoList where qtdManuaisRealizados equals to UPDATED_QTD_MANUAIS_REALIZADOS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisRealizados.in=" + UPDATED_QTD_MANUAIS_REALIZADOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisRealizadosIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisRealizados is not null
        defaultIndicadorProducaoShouldBeFound("qtdManuaisRealizados.specified=true");

        // Get all the indicadorProducaoList where qtdManuaisRealizados is null
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisRealizados.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisRealizadosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisRealizados greater than or equals to DEFAULT_QTD_MANUAIS_REALIZADOS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisRealizados.greaterOrEqualThan=" + DEFAULT_QTD_MANUAIS_REALIZADOS);

        // Get all the indicadorProducaoList where qtdManuaisRealizados greater than or equals to UPDATED_QTD_MANUAIS_REALIZADOS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisRealizados.greaterOrEqualThan=" + UPDATED_QTD_MANUAIS_REALIZADOS);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaosByQtdManuaisRealizadosIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);

        // Get all the indicadorProducaoList where qtdManuaisRealizados less than or equals to DEFAULT_QTD_MANUAIS_REALIZADOS
        defaultIndicadorProducaoShouldNotBeFound("qtdManuaisRealizados.lessThan=" + DEFAULT_QTD_MANUAIS_REALIZADOS);

        // Get all the indicadorProducaoList where qtdManuaisRealizados less than or equals to UPDATED_QTD_MANUAIS_REALIZADOS
        defaultIndicadorProducaoShouldBeFound("qtdManuaisRealizados.lessThan=" + UPDATED_QTD_MANUAIS_REALIZADOS);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByIdSituacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        Situacao idSituacao = SituacaoResourceIntTest.createEntity(em);
        em.persist(idSituacao);
        em.flush();
        indicadorProducao.setSituacao(idSituacao);
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);
        Long idSituacaoId = idSituacao.getId();

        // Get all the indicadorProducaoList where idSituacao equals to situacao
        defaultIndicadorProducaoShouldBeFound("situacao.equals=" + idSituacaoId);

        // Get all the indicadorProducaoList where idSituacao equals to situacao + 1
        defaultIndicadorProducaoShouldNotBeFound("situacao.equals=" + (idSituacaoId + 1));
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByIdSistemaAguaIsEqualToSomething() throws Exception {
        // Initialize the database
        SistemaAgua idSistemaAgua = SistemaAguaResourceIntTest.createEntity(em);
        em.persist(idSistemaAgua);
        em.flush();
        indicadorProducao.setSistemaAgua(idSistemaAgua);
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);
        Long idSistemaAguaId = idSistemaAgua.getId();

        // Get all the indicadorProducaoList where idSistemaAgua equals to idSistemaAguaId
        defaultIndicadorProducaoShouldBeFound("idSistemaAguaId.equals=" + idSistemaAguaId);

        // Get all the indicadorProducaoList where idSistemaAgua equals to idSistemaAguaId + 1
        defaultIndicadorProducaoShouldNotBeFound("idSistemaAguaId.equals=" + (idSistemaAguaId + 1));
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByIdComunaIsEqualToSomething() throws Exception {
        // Initialize the database
        Comuna idComuna = ComunaResourceIntTest.createEntity(em);
        em.persist(idComuna);
        em.flush();
        indicadorProducao.setComuna(idComuna);
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);
        Long idComunaId = idComuna.getId();

        // Get all the indicadorProducaoList where idComuna equals to comuna
        defaultIndicadorProducaoShouldBeFound("comuna.equals=" + idComunaId);

        // Get all the indicadorProducaoList where idComuna equals to comuna + 1
        defaultIndicadorProducaoShouldNotBeFound("comuna.equals=" + (idComunaId + 1));
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaosByIndicadorProducaoLogIsEqualToSomething() throws Exception {
        // Initialize the database
        IndicadorProducaoLog indicadorProducaoLog = IndicadorProducaoLogResourceIntTest.createEntity(em);
        em.persist(indicadorProducaoLog);
        em.flush();
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);
        Long indicadorProducaoLogId = indicadorProducaoLog.getId();

        // Get all the indicadorProducaoList where indicadorProducaoLog equals to indicadorProducaoLogId
        defaultIndicadorProducaoShouldBeFound("indicadorProducaoLogId.equals=" + indicadorProducaoLogId);

        // Get all the indicadorProducaoList where indicadorProducaoLog equals to indicadorProducaoLogId + 1
        defaultIndicadorProducaoShouldNotBeFound("indicadorProducaoLogId.equals=" + (indicadorProducaoLogId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultIndicadorProducaoShouldBeFound(String filter) throws Exception {
        restIndicadorProducaoMockMvc.perform(get("/api/indicador-producaos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(indicadorProducao.getId().intValue())))
            .andExpect(jsonPath("$.[*].idIndicadorProducao").value(hasItem(DEFAULT_ID_INDICADOR_PRODUCAO.intValue())))
            .andExpect(jsonPath("$.[*].idUsuario").value(hasItem(DEFAULT_ID_USUARIO.intValue())))
            .andExpect(jsonPath("$.[*].dtLancamento").value(hasItem(DEFAULT_DT_LANCAMENTO.toString())))
            .andExpect(jsonPath("$.[*].dtUltimaAlteracao").value(hasItem(DEFAULT_DT_ULTIMA_ALTERACAO.toString())))
            .andExpect(jsonPath("$.[*].qtdPopulacaoCobertaInfraestrutura").value(hasItem(DEFAULT_QTD_POPULACAO_COBERTA_INFRAESTRUTURA.intValue())))
            .andExpect(jsonPath("$.[*].qtdFontanariosChafarisesOperacionais").value(hasItem(DEFAULT_QTD_FONTANARIOS_CHAFARISES_OPERACIONAIS.intValue())))
            .andExpect(jsonPath("$.[*].qtdMediaHorasDistribuicaoDiaria").value(hasItem(DEFAULT_QTD_MEDIA_HORAS_DISTRIBUICAO_DIARIA.intValue())))
            .andExpect(jsonPath("$.[*].qtdMediaHorasParagem").value(hasItem(DEFAULT_QTD_MEDIA_HORAS_PARAGEM.intValue())))
            .andExpect(jsonPath("$.[*].qtdMediaHorasInterrupcaoFaltaEnergia").value(hasItem(DEFAULT_QTD_MEDIA_HORAS_INTERRUPCAO_FALTA_ENERGIA.intValue())))
            .andExpect(jsonPath("$.[*].qtdVolumeAguaCaptada").value(hasItem(DEFAULT_QTD_VOLUME_AGUA_CAPTADA.intValue())))
            .andExpect(jsonPath("$.[*].qtdVolumeAguaTratada").value(hasItem(DEFAULT_QTD_VOLUME_AGUA_TRATADA.intValue())))
            .andExpect(jsonPath("$.[*].qtdVolumeAguaDistribuida").value(hasItem(DEFAULT_QTD_VOLUME_AGUA_DISTRIBUIDA.intValue())))
            .andExpect(jsonPath("$.[*].qtdConsumoEnergia").value(hasItem(DEFAULT_QTD_CONSUMO_ENERGIA.intValue())))
            .andExpect(jsonPath("$.[*].qtdConsumoCombustivel").value(hasItem(DEFAULT_QTD_CONSUMO_COMBUSTIVEL.intValue())))
            .andExpect(jsonPath("$.[*].qtdConsumoHipocloritroCalcio").value(hasItem(DEFAULT_QTD_CONSUMO_HIPOCLORITRO_CALCIO.intValue())))
            .andExpect(jsonPath("$.[*].qtdConsumoSulfatoAluminio").value(hasItem(DEFAULT_QTD_CONSUMO_SULFATO_ALUMINIO.intValue())))
            .andExpect(jsonPath("$.[*].qtdConsumoHidroxidoCalcio").value(hasItem(DEFAULT_QTD_CONSUMO_HIDROXIDO_CALCIO.intValue())))
            .andExpect(jsonPath("$.[*].qtdReparoCaptacaoEtas").value(hasItem(DEFAULT_QTD_REPARO_CAPTACAO_ETAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdReparoAdutoras").value(hasItem(DEFAULT_QTD_REPARO_ADUTORAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdReparoRedeDistribuicao").value(hasItem(DEFAULT_QTD_REPARO_REDE_DISTRIBUICAO.intValue())))
            .andExpect(jsonPath("$.[*].qtdReparoRamais").value(hasItem(DEFAULT_QTD_REPARO_RAMAIS.intValue())))
            .andExpect(jsonPath("$.[*].qtdManutencaoCurativa").value(hasItem(DEFAULT_QTD_MANUTENCAO_CURATIVA.intValue())))
            .andExpect(jsonPath("$.[*].qtdManutencaoPreventiva").value(hasItem(DEFAULT_QTD_MANUTENCAO_PREVENTIVA.intValue())))
            .andExpect(jsonPath("$.[*].qtdManutencaoVerificadoSolicitado").value(hasItem(DEFAULT_QTD_MANUTENCAO_VERIFICADO_SOLICITADO.intValue())))
            .andExpect(jsonPath("$.[*].qtdReservatorioLavado").value(hasItem(DEFAULT_QTD_RESERVATORIO_LAVADO.intValue())))
            .andExpect(jsonPath("$.[*].qtdFuncionarios").value(hasItem(DEFAULT_QTD_FUNCIONARIOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdFuncionariosEfectivos").value(hasItem(DEFAULT_QTD_FUNCIONARIOS_EFECTIVOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdFuncionariosContratados").value(hasItem(DEFAULT_QTD_FUNCIONARIOS_CONTRATADOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdFuncionariosOutrasEntidades").value(hasItem(DEFAULT_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES.intValue())))
            .andExpect(jsonPath("$.[*].qtdNovasLigacoesNovosContratos").value(hasItem(DEFAULT_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdNovasLigacoesDomesticasNovosContratos").value(hasItem(DEFAULT_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdLigacoesIlegaisRegularizadas").value(hasItem(DEFAULT_QTD_LIGACOES_ILEGAIS_REGULARIZADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdLigacoesFechadas").value(hasItem(DEFAULT_QTD_LIGACOES_FECHADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdCortes").value(hasItem(DEFAULT_QTD_CORTES.intValue())))
            .andExpect(jsonPath("$.[*].qtdReligacoes").value(hasItem(DEFAULT_QTD_RELIGACOES.intValue())))
            .andExpect(jsonPath("$.[*].qtdLigacoesActivas").value(hasItem(DEFAULT_QTD_LIGACOES_ACTIVAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdLigacoesDomesticasActivas").value(hasItem(DEFAULT_QTD_LIGACOES_DOMESTICAS_ACTIVAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdLigacoesFacturadasBaseLeiturasReais").value(hasItem(DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS.intValue())))
            .andExpect(jsonPath("$.[*].qtdLigacoesFacturadasBaseEstimativasAvenca").value(hasItem(DEFAULT_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA.intValue())))
            .andExpect(jsonPath("$.[*].qtdVolumeAguaFacturada").value(hasItem(DEFAULT_QTD_VOLUME_AGUA_FACTURADA.intValue())))
            .andExpect(jsonPath("$.[*].qtdVolumeTotalFacturadaLigacoesDomesticas").value(hasItem(DEFAULT_QTD_VOLUME_TOTAL_FACTURADA_LIGACOES_DOMESTICAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdVolumeFacturadoBaseLeituraReais").value(hasItem(DEFAULT_QTD_VOLUME_FACTURADO_BASE_LEITURA_REAIS.intValue())))
            .andExpect(jsonPath("$.[*].vlrTotalFacturado").value(hasItem(DEFAULT_VLR_TOTAL_FACTURADO.intValue())))
            .andExpect(jsonPath("$.[*].vlrFacturasCanceladasNotasCreditos").value(hasItem(DEFAULT_VLR_FACTURAS_CANCELADAS_NOTAS_CREDITOS.intValue())))
            .andExpect(jsonPath("$.[*].vlrRealFacturado").value(hasItem(DEFAULT_VLR_REAL_FACTURADO.intValue())))
            .andExpect(jsonPath("$.[*].vlrTotalCobrado").value(hasItem(DEFAULT_VLR_TOTAL_COBRADO.intValue())))
            .andExpect(jsonPath("$.[*].qtdReclamacoes").value(hasItem(DEFAULT_QTD_RECLAMACOES.intValue())))
            .andExpect(jsonPath("$.[*].qtdReclamacoesRespondidasMenorIgualCincoDias").value(hasItem(DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdReclamacoesRespondidasMaisCincoMenosVinteDias").value(hasItem(DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdReclamacoesRespondidasMaiorIgualVinteDias").value(hasItem(DEFAULT_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS.intValue())))
            .andExpect(jsonPath("$.[*].vlrCustoPessoal").value(hasItem(DEFAULT_VLR_CUSTO_PESSOAL.intValue())))
            .andExpect(jsonPath("$.[*].vlrCustoFse").value(hasItem(DEFAULT_VLR_CUSTO_FSE.intValue())))
            .andExpect(jsonPath("$.[*].vlrCustoEnergia").value(hasItem(DEFAULT_VLR_CUSTO_ENERGIA.intValue())))
            .andExpect(jsonPath("$.[*].vlrCustoManutencao").value(hasItem(DEFAULT_VLR_CUSTO_MANUTENCAO.intValue())))
            .andExpect(jsonPath("$.[*].vlrCustoReagentes").value(hasItem(DEFAULT_VLR_CUSTO_REAGENTES.intValue())))
            .andExpect(jsonPath("$.[*].vlrCustoDestinoFinalLamas").value(hasItem(DEFAULT_VLR_CUSTO_DESTINO_FINAL_LAMAS.intValue())))
            .andExpect(jsonPath("$.[*].vlrCustoOperacionaisOpex").value(hasItem(DEFAULT_VLR_CUSTO_OPERACIONAIS_OPEX.intValue())))
            .andExpect(jsonPath("$.[*].vlrCustoAmortizaAnualInvestOpCapex").value(hasItem(DEFAULT_VLR_CUSTO_AMORTIZA_ANUAL_INVEST_OP_CAPEX.intValue())))
            .andExpect(jsonPath("$.[*].vlrCustoTotaisCapexOpex").value(hasItem(DEFAULT_VLR_CUSTO_TOTAIS_CAPEX_OPEX.intValue())))
            .andExpect(jsonPath("$.[*].qtdCaptacoes").value(hasItem(DEFAULT_QTD_CAPTACOES.intValue())))
            .andExpect(jsonPath("$.[*].qtdEtas").value(hasItem(DEFAULT_QTD_ETAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdReservatorios").value(hasItem(DEFAULT_QTD_RESERVATORIOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdEstacoesElevatorias").value(hasItem(DEFAULT_QTD_ESTACOES_ELEVATORIAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdComprimentoAdutoras").value(hasItem(DEFAULT_QTD_COMPRIMENTO_ADUTORAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdComprimentoRedes").value(hasItem(DEFAULT_QTD_COMPRIMENTO_REDES.intValue())))
            .andExpect(jsonPath("$.[*].qtdComprimentoRamais").value(hasItem(DEFAULT_QTD_COMPRIMENTO_RAMAIS.intValue())))
            .andExpect(jsonPath("$.[*].qtdAcoesFormacaoMoPlaneadas").value(hasItem(DEFAULT_QTD_ACOES_FORMACAO_MO_PLANEADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdAcoesFormacaoMmsPlaneadas").value(hasItem(DEFAULT_QTD_ACOES_FORMACAO_MMS_PLANEADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdAcoesFormacaoCmpPlaneadas").value(hasItem(DEFAULT_QTD_ACOES_FORMACAO_CMP_PLANEADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdAcoesFormacaoSoftwareFornecidosPlanejadas").value(hasItem(DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdAcoesFormacaoMoRealizadas").value(hasItem(DEFAULT_QTD_ACOES_FORMACAO_MO_REALIZADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdAcoesFormacaoMmsRealizadas").value(hasItem(DEFAULT_QTD_ACOES_FORMACAO_MMS_REALIZADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdAcoesFormacaoCmpRealizadas").value(hasItem(DEFAULT_QTD_ACOES_FORMACAO_CMP_REALIZADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdAcoesFormacaoSoftwareFornecidosRealizadas").value(hasItem(DEFAULT_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdAcoesFormacaoRealizadas").value(hasItem(DEFAULT_QTD_ACOES_FORMACAO_REALIZADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdManuaisMoPrevistos").value(hasItem(DEFAULT_QTD_MANUAIS_MO_PREVISTOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdManuaisMmsPrevistos").value(hasItem(DEFAULT_QTD_MANUAIS_MMS_PREVISTOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdManuaisCmpPrevistos").value(hasItem(DEFAULT_QTD_MANUAIS_CMP_PREVISTOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdManuaisPrevistos").value(hasItem(DEFAULT_QTD_MANUAIS_PREVISTOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdAcoesManuaisMoRealizadas").value(hasItem(DEFAULT_QTD_ACOES_MANUAIS_MO_REALIZADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdManuaisMmsRealizadas").value(hasItem(DEFAULT_QTD_MANUAIS_MMS_REALIZADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdManuaisCmpRealizadas").value(hasItem(DEFAULT_QTD_MANUAIS_CMP_REALIZADAS.intValue())))
            .andExpect(jsonPath("$.[*].qtdManuaisRealizados").value(hasItem(DEFAULT_QTD_MANUAIS_REALIZADOS.intValue())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultIndicadorProducaoShouldNotBeFound(String filter) throws Exception {
        restIndicadorProducaoMockMvc.perform(get("/api/indicador-producaos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingIndicadorProducao() throws Exception {
        // Get the indicadorProducao
        restIndicadorProducaoMockMvc.perform(get("/api/indicador-producaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIndicadorProducao() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);
        int databaseSizeBeforeUpdate = indicadorProducaoRepository.findAll().size();

        // Update the indicadorProducao
        IndicadorProducao updatedIndicadorProducao = indicadorProducaoRepository.findOne(indicadorProducao.getId());
        // Disconnect from session so that the updates on updatedIndicadorProducao are not directly saved in db
        em.detach(updatedIndicadorProducao);
        updatedIndicadorProducao
            .idUsuario(UPDATED_ID_USUARIO)
            .dtLancamento(UPDATED_DT_LANCAMENTO)
            .dtUltimaAlteracao(UPDATED_DT_ULTIMA_ALTERACAO)
            .qtdPopulacaoCobertaInfraestrutura(UPDATED_QTD_POPULACAO_COBERTA_INFRAESTRUTURA)
            .qtdFontanariosChafarisesOperacionais(UPDATED_QTD_FONTANARIOS_CHAFARISES_OPERACIONAIS)
            .qtdMediaHorasDistribuicaoDiaria(UPDATED_QTD_MEDIA_HORAS_DISTRIBUICAO_DIARIA)
            .qtdMediaHorasParagem(UPDATED_QTD_MEDIA_HORAS_PARAGEM)
            .qtdMediaHorasInterrupcaoFaltaEnergia(UPDATED_QTD_MEDIA_HORAS_INTERRUPCAO_FALTA_ENERGIA)
            .qtdVolumeAguaCaptada(UPDATED_QTD_VOLUME_AGUA_CAPTADA)
            .qtdVolumeAguaTratada(UPDATED_QTD_VOLUME_AGUA_TRATADA)
            .qtdVolumeAguaDistribuida(UPDATED_QTD_VOLUME_AGUA_DISTRIBUIDA)
            .qtdConsumoEnergia(UPDATED_QTD_CONSUMO_ENERGIA)
            .qtdConsumoCombustivel(UPDATED_QTD_CONSUMO_COMBUSTIVEL)
            .qtdConsumoHipocloritroCalcio(UPDATED_QTD_CONSUMO_HIPOCLORITRO_CALCIO)
            .qtdConsumoSulfatoAluminio(UPDATED_QTD_CONSUMO_SULFATO_ALUMINIO)
            .qtdConsumoHidroxidoCalcio(UPDATED_QTD_CONSUMO_HIDROXIDO_CALCIO)
            .qtdReparoCaptacaoEtas(UPDATED_QTD_REPARO_CAPTACAO_ETAS)
            .qtdReparoAdutoras(UPDATED_QTD_REPARO_ADUTORAS)
            .qtdReparoRedeDistribuicao(UPDATED_QTD_REPARO_REDE_DISTRIBUICAO)
            .qtdReparoRamais(UPDATED_QTD_REPARO_RAMAIS)
            .qtdManutencaoCurativa(UPDATED_QTD_MANUTENCAO_CURATIVA)
            .qtdManutencaoPreventiva(UPDATED_QTD_MANUTENCAO_PREVENTIVA)
            .qtdManutencaoVerificadoSolicitado(UPDATED_QTD_MANUTENCAO_VERIFICADO_SOLICITADO)
            .qtdReservatorioLavado(UPDATED_QTD_RESERVATORIO_LAVADO)
            .qtdFuncionarios(UPDATED_QTD_FUNCIONARIOS)
            .qtdFuncionariosEfectivos(UPDATED_QTD_FUNCIONARIOS_EFECTIVOS)
            .qtdFuncionariosContratados(UPDATED_QTD_FUNCIONARIOS_CONTRATADOS)
            .qtdFuncionariosOutrasEntidades(UPDATED_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES)
            .qtdNovasLigacoesNovosContratos(UPDATED_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS)
            .qtdNovasLigacoesDomesticasNovosContratos(UPDATED_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS)
            .qtdLigacoesIlegaisRegularizadas(UPDATED_QTD_LIGACOES_ILEGAIS_REGULARIZADAS)
            .qtdLigacoesFechadas(UPDATED_QTD_LIGACOES_FECHADAS)
            .qtdCortes(UPDATED_QTD_CORTES)
            .qtdReligacoes(UPDATED_QTD_RELIGACOES)
            .qtdLigacoesActivas(UPDATED_QTD_LIGACOES_ACTIVAS)
            .qtdLigacoesDomesticasActivas(UPDATED_QTD_LIGACOES_DOMESTICAS_ACTIVAS)
            .qtdLigacoesFacturadasBaseLeiturasReais(UPDATED_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS)
            .qtdLigacoesFacturadasBaseEstimativasAvenca(UPDATED_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA)
            .qtdVolumeAguaFacturada(UPDATED_QTD_VOLUME_AGUA_FACTURADA)
            .qtdVolumeTotalFacturadaLigacoesDomesticas(UPDATED_QTD_VOLUME_TOTAL_FACTURADA_LIGACOES_DOMESTICAS)
            .qtdVolumeFacturadoBaseLeituraReais(UPDATED_QTD_VOLUME_FACTURADO_BASE_LEITURA_REAIS)
            .vlrTotalFacturado(UPDATED_VLR_TOTAL_FACTURADO)
            .vlrFacturasCanceladasNotasCreditos(UPDATED_VLR_FACTURAS_CANCELADAS_NOTAS_CREDITOS)
            .vlrRealFacturado(UPDATED_VLR_REAL_FACTURADO)
            .vlrTotalCobrado(UPDATED_VLR_TOTAL_COBRADO)
            .qtdReclamacoes(UPDATED_QTD_RECLAMACOES)
            .qtdReclamacoesRespondidasMenorIgualCincoDias(UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS)
            .qtdReclamacoesRespondidasMaisCincoMenosVinteDias(UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS)
            .qtdReclamacoesRespondidasMaiorIgualVinteDias(UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS)
            .vlrCustoPessoal(UPDATED_VLR_CUSTO_PESSOAL)
            .vlrCustoFse(UPDATED_VLR_CUSTO_FSE)
            .vlrCustoEnergia(UPDATED_VLR_CUSTO_ENERGIA)
            .vlrCustoManutencao(UPDATED_VLR_CUSTO_MANUTENCAO)
            .vlrCustoReagentes(UPDATED_VLR_CUSTO_REAGENTES)
            .vlrCustoDestinoFinalLamas(UPDATED_VLR_CUSTO_DESTINO_FINAL_LAMAS)
            .vlrCustoOperacionaisOpex(UPDATED_VLR_CUSTO_OPERACIONAIS_OPEX)
            .vlrCustoAmortizaAnualInvestOpCapex(UPDATED_VLR_CUSTO_AMORTIZA_ANUAL_INVEST_OP_CAPEX)
            .vlrCustoTotaisCapexOpex(UPDATED_VLR_CUSTO_TOTAIS_CAPEX_OPEX)
            .qtdCaptacoes(UPDATED_QTD_CAPTACOES)
            .qtdEtas(UPDATED_QTD_ETAS)
            .qtdReservatorios(UPDATED_QTD_RESERVATORIOS)
            .qtdEstacoesElevatorias(UPDATED_QTD_ESTACOES_ELEVATORIAS)
            .qtdComprimentoAdutoras(UPDATED_QTD_COMPRIMENTO_ADUTORAS)
            .qtdComprimentoRedes(UPDATED_QTD_COMPRIMENTO_REDES)
            .qtdComprimentoRamais(UPDATED_QTD_COMPRIMENTO_RAMAIS)
            .qtdAcoesFormacaoMoPlaneadas(UPDATED_QTD_ACOES_FORMACAO_MO_PLANEADAS)
            .qtdAcoesFormacaoMmsPlaneadas(UPDATED_QTD_ACOES_FORMACAO_MMS_PLANEADAS)
            .qtdAcoesFormacaoCmpPlaneadas(UPDATED_QTD_ACOES_FORMACAO_CMP_PLANEADAS)
            .qtdAcoesFormacaoSoftwareFornecidosPlanejadas(UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS)
            .qtdAcoesFormacaoMoRealizadas(UPDATED_QTD_ACOES_FORMACAO_MO_REALIZADAS)
            .qtdAcoesFormacaoMmsRealizadas(UPDATED_QTD_ACOES_FORMACAO_MMS_REALIZADAS)
            .qtdAcoesFormacaoCmpRealizadas(UPDATED_QTD_ACOES_FORMACAO_CMP_REALIZADAS)
            .qtdAcoesFormacaoSoftwareFornecidosRealizadas(UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS)
            .qtdAcoesFormacaoRealizadas(UPDATED_QTD_ACOES_FORMACAO_REALIZADAS)
            .qtdManuaisMoPrevistos(UPDATED_QTD_MANUAIS_MO_PREVISTOS)
            .qtdManuaisMmsPrevistos(UPDATED_QTD_MANUAIS_MMS_PREVISTOS)
            .qtdManuaisCmpPrevistos(UPDATED_QTD_MANUAIS_CMP_PREVISTOS)
            .qtdManuaisPrevistos(UPDATED_QTD_MANUAIS_PREVISTOS)
            .qtdAcoesManuaisMoRealizadas(UPDATED_QTD_ACOES_MANUAIS_MO_REALIZADAS)
            .qtdManuaisMmsRealizadas(UPDATED_QTD_MANUAIS_MMS_REALIZADAS)
            .qtdManuaisCmpRealizadas(UPDATED_QTD_MANUAIS_CMP_REALIZADAS)
            .qtdManuaisRealizados(UPDATED_QTD_MANUAIS_REALIZADOS);
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(updatedIndicadorProducao);

        restIndicadorProducaoMockMvc.perform(put("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isOk());

        // Validate the IndicadorProducao in the database
        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeUpdate);
        IndicadorProducao testIndicadorProducao = indicadorProducaoList.get(indicadorProducaoList.size() - 1);
        assertThat(testIndicadorProducao.getIdUsuario()).isEqualTo(UPDATED_ID_USUARIO);
        assertThat(testIndicadorProducao.getDtLancamento()).isEqualTo(UPDATED_DT_LANCAMENTO);
        assertThat(testIndicadorProducao.getDtUltimaAlteracao()).isEqualTo(UPDATED_DT_ULTIMA_ALTERACAO);
        assertThat(testIndicadorProducao.getQtdPopulacaoCobertaInfraestrutura()).isEqualTo(UPDATED_QTD_POPULACAO_COBERTA_INFRAESTRUTURA);
        assertThat(testIndicadorProducao.getQtdFontanariosChafarisesOperacionais()).isEqualTo(UPDATED_QTD_FONTANARIOS_CHAFARISES_OPERACIONAIS);
        assertThat(testIndicadorProducao.getQtdMediaHorasDistribuicaoDiaria()).isEqualTo(UPDATED_QTD_MEDIA_HORAS_DISTRIBUICAO_DIARIA);
        assertThat(testIndicadorProducao.getQtdMediaHorasParagem()).isEqualTo(UPDATED_QTD_MEDIA_HORAS_PARAGEM);
        assertThat(testIndicadorProducao.getQtdMediaHorasInterrupcaoFaltaEnergia()).isEqualTo(UPDATED_QTD_MEDIA_HORAS_INTERRUPCAO_FALTA_ENERGIA);
        assertThat(testIndicadorProducao.getQtdVolumeAguaCaptada()).isEqualTo(UPDATED_QTD_VOLUME_AGUA_CAPTADA);
        assertThat(testIndicadorProducao.getQtdVolumeAguaTratada()).isEqualTo(UPDATED_QTD_VOLUME_AGUA_TRATADA);
        assertThat(testIndicadorProducao.getQtdVolumeAguaDistribuida()).isEqualTo(UPDATED_QTD_VOLUME_AGUA_DISTRIBUIDA);
        assertThat(testIndicadorProducao.getQtdConsumoEnergia()).isEqualTo(UPDATED_QTD_CONSUMO_ENERGIA);
        assertThat(testIndicadorProducao.getQtdConsumoCombustivel()).isEqualTo(UPDATED_QTD_CONSUMO_COMBUSTIVEL);
        assertThat(testIndicadorProducao.getQtdConsumoHipocloritroCalcio()).isEqualTo(UPDATED_QTD_CONSUMO_HIPOCLORITRO_CALCIO);
        assertThat(testIndicadorProducao.getQtdConsumoSulfatoAluminio()).isEqualTo(UPDATED_QTD_CONSUMO_SULFATO_ALUMINIO);
        assertThat(testIndicadorProducao.getQtdConsumoHidroxidoCalcio()).isEqualTo(UPDATED_QTD_CONSUMO_HIDROXIDO_CALCIO);
        assertThat(testIndicadorProducao.getQtdReparoCaptacaoEtas()).isEqualTo(UPDATED_QTD_REPARO_CAPTACAO_ETAS);
        assertThat(testIndicadorProducao.getQtdReparoAdutoras()).isEqualTo(UPDATED_QTD_REPARO_ADUTORAS);
        assertThat(testIndicadorProducao.getQtdReparoRedeDistribuicao()).isEqualTo(UPDATED_QTD_REPARO_REDE_DISTRIBUICAO);
        assertThat(testIndicadorProducao.getQtdReparoRamais()).isEqualTo(UPDATED_QTD_REPARO_RAMAIS);
        assertThat(testIndicadorProducao.getQtdManutencaoCurativa()).isEqualTo(UPDATED_QTD_MANUTENCAO_CURATIVA);
        assertThat(testIndicadorProducao.getQtdManutencaoPreventiva()).isEqualTo(UPDATED_QTD_MANUTENCAO_PREVENTIVA);
        assertThat(testIndicadorProducao.getQtdManutencaoVerificadoSolicitado()).isEqualTo(UPDATED_QTD_MANUTENCAO_VERIFICADO_SOLICITADO);
        assertThat(testIndicadorProducao.getQtdReservatorioLavado()).isEqualTo(UPDATED_QTD_RESERVATORIO_LAVADO);
        assertThat(testIndicadorProducao.getQtdFuncionarios()).isEqualTo(UPDATED_QTD_FUNCIONARIOS);
        assertThat(testIndicadorProducao.getQtdFuncionariosEfectivos()).isEqualTo(UPDATED_QTD_FUNCIONARIOS_EFECTIVOS);
        assertThat(testIndicadorProducao.getQtdFuncionariosContratados()).isEqualTo(UPDATED_QTD_FUNCIONARIOS_CONTRATADOS);
        assertThat(testIndicadorProducao.getQtdFuncionariosOutrasEntidades()).isEqualTo(UPDATED_QTD_FUNCIONARIOS_OUTRAS_ENTIDADES);
        assertThat(testIndicadorProducao.getQtdNovasLigacoesNovosContratos()).isEqualTo(UPDATED_QTD_NOVAS_LIGACOES_NOVOS_CONTRATOS);
        assertThat(testIndicadorProducao.getQtdNovasLigacoesDomesticasNovosContratos()).isEqualTo(UPDATED_QTD_NOVAS_LIGACOES_DOMESTICAS_NOVOS_CONTRATOS);
        assertThat(testIndicadorProducao.getQtdLigacoesIlegaisRegularizadas()).isEqualTo(UPDATED_QTD_LIGACOES_ILEGAIS_REGULARIZADAS);
        assertThat(testIndicadorProducao.getQtdLigacoesFechadas()).isEqualTo(UPDATED_QTD_LIGACOES_FECHADAS);
        assertThat(testIndicadorProducao.getQtdCortes()).isEqualTo(UPDATED_QTD_CORTES);
        assertThat(testIndicadorProducao.getQtdReligacoes()).isEqualTo(UPDATED_QTD_RELIGACOES);
        assertThat(testIndicadorProducao.getQtdLigacoesActivas()).isEqualTo(UPDATED_QTD_LIGACOES_ACTIVAS);
        assertThat(testIndicadorProducao.getQtdLigacoesDomesticasActivas()).isEqualTo(UPDATED_QTD_LIGACOES_DOMESTICAS_ACTIVAS);
        assertThat(testIndicadorProducao.getQtdLigacoesFacturadasBaseLeiturasReais()).isEqualTo(UPDATED_QTD_LIGACOES_FACTURADAS_BASE_LEITURAS_REAIS);
        assertThat(testIndicadorProducao.getQtdLigacoesFacturadasBaseEstimativasAvenca()).isEqualTo(UPDATED_QTD_LIGACOES_FACTURADAS_BASE_ESTIMATIVAS_AVENCA);
        assertThat(testIndicadorProducao.getQtdVolumeAguaFacturada()).isEqualTo(UPDATED_QTD_VOLUME_AGUA_FACTURADA);
        assertThat(testIndicadorProducao.getQtdVolumeTotalFacturadaLigacoesDomesticas()).isEqualTo(UPDATED_QTD_VOLUME_TOTAL_FACTURADA_LIGACOES_DOMESTICAS);
        assertThat(testIndicadorProducao.getQtdVolumeFacturadoBaseLeituraReais()).isEqualTo(UPDATED_QTD_VOLUME_FACTURADO_BASE_LEITURA_REAIS);
        assertThat(testIndicadorProducao.getVlrTotalFacturado()).isEqualTo(UPDATED_VLR_TOTAL_FACTURADO);
        assertThat(testIndicadorProducao.getVlrFacturasCanceladasNotasCreditos()).isEqualTo(UPDATED_VLR_FACTURAS_CANCELADAS_NOTAS_CREDITOS);
        assertThat(testIndicadorProducao.getVlrRealFacturado()).isEqualTo(UPDATED_VLR_REAL_FACTURADO);
        assertThat(testIndicadorProducao.getVlrTotalCobrado()).isEqualTo(UPDATED_VLR_TOTAL_COBRADO);
        assertThat(testIndicadorProducao.getQtdReclamacoes()).isEqualTo(UPDATED_QTD_RECLAMACOES);
        assertThat(testIndicadorProducao.getQtdReclamacoesRespondidasMenorIgualCincoDias()).isEqualTo(UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MENOR_IGUAL_CINCO_DIAS);
        assertThat(testIndicadorProducao.getQtdReclamacoesRespondidasMaisCincoMenosVinteDias()).isEqualTo(UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIS_CINCO_MENOS_VINTE_DIAS);
        assertThat(testIndicadorProducao.getQtdReclamacoesRespondidasMaiorIgualVinteDias()).isEqualTo(UPDATED_QTD_RECLAMACOES_RESPONDIDAS_MAIOR_IGUAL_VINTE_DIAS);
        assertThat(testIndicadorProducao.getVlrCustoPessoal()).isEqualTo(UPDATED_VLR_CUSTO_PESSOAL);
        assertThat(testIndicadorProducao.getVlrCustoFse()).isEqualTo(UPDATED_VLR_CUSTO_FSE);
        assertThat(testIndicadorProducao.getVlrCustoEnergia()).isEqualTo(UPDATED_VLR_CUSTO_ENERGIA);
        assertThat(testIndicadorProducao.getVlrCustoManutencao()).isEqualTo(UPDATED_VLR_CUSTO_MANUTENCAO);
        assertThat(testIndicadorProducao.getVlrCustoReagentes()).isEqualTo(UPDATED_VLR_CUSTO_REAGENTES);
        assertThat(testIndicadorProducao.getVlrCustoDestinoFinalLamas()).isEqualTo(UPDATED_VLR_CUSTO_DESTINO_FINAL_LAMAS);
        assertThat(testIndicadorProducao.getVlrCustoOperacionaisOpex()).isEqualTo(UPDATED_VLR_CUSTO_OPERACIONAIS_OPEX);
        assertThat(testIndicadorProducao.getVlrCustoAmortizaAnualInvestOpCapex()).isEqualTo(UPDATED_VLR_CUSTO_AMORTIZA_ANUAL_INVEST_OP_CAPEX);
        assertThat(testIndicadorProducao.getVlrCustoTotaisCapexOpex()).isEqualTo(UPDATED_VLR_CUSTO_TOTAIS_CAPEX_OPEX);
        assertThat(testIndicadorProducao.getQtdCaptacoes()).isEqualTo(UPDATED_QTD_CAPTACOES);
        assertThat(testIndicadorProducao.getQtdEtas()).isEqualTo(UPDATED_QTD_ETAS);
        assertThat(testIndicadorProducao.getQtdReservatorios()).isEqualTo(UPDATED_QTD_RESERVATORIOS);
        assertThat(testIndicadorProducao.getQtdEstacoesElevatorias()).isEqualTo(UPDATED_QTD_ESTACOES_ELEVATORIAS);
        assertThat(testIndicadorProducao.getQtdComprimentoAdutoras()).isEqualTo(UPDATED_QTD_COMPRIMENTO_ADUTORAS);
        assertThat(testIndicadorProducao.getQtdComprimentoRedes()).isEqualTo(UPDATED_QTD_COMPRIMENTO_REDES);
        assertThat(testIndicadorProducao.getQtdComprimentoRamais()).isEqualTo(UPDATED_QTD_COMPRIMENTO_RAMAIS);
        assertThat(testIndicadorProducao.getQtdAcoesFormacaoMoPlaneadas()).isEqualTo(UPDATED_QTD_ACOES_FORMACAO_MO_PLANEADAS);
        assertThat(testIndicadorProducao.getQtdAcoesFormacaoMmsPlaneadas()).isEqualTo(UPDATED_QTD_ACOES_FORMACAO_MMS_PLANEADAS);
        assertThat(testIndicadorProducao.getQtdAcoesFormacaoCmpPlaneadas()).isEqualTo(UPDATED_QTD_ACOES_FORMACAO_CMP_PLANEADAS);
        assertThat(testIndicadorProducao.getQtdAcoesFormacaoSoftwareFornecidosPlanejadas()).isEqualTo(UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_PLANEJADAS);
        assertThat(testIndicadorProducao.getQtdAcoesFormacaoMoRealizadas()).isEqualTo(UPDATED_QTD_ACOES_FORMACAO_MO_REALIZADAS);
        assertThat(testIndicadorProducao.getQtdAcoesFormacaoMmsRealizadas()).isEqualTo(UPDATED_QTD_ACOES_FORMACAO_MMS_REALIZADAS);
        assertThat(testIndicadorProducao.getQtdAcoesFormacaoCmpRealizadas()).isEqualTo(UPDATED_QTD_ACOES_FORMACAO_CMP_REALIZADAS);
        assertThat(testIndicadorProducao.getQtdAcoesFormacaoSoftwareFornecidosRealizadas()).isEqualTo(UPDATED_QTD_ACOES_FORMACAO_SOFTWARE_FORNECIDOS_REALIZADAS);
        assertThat(testIndicadorProducao.getQtdAcoesFormacaoRealizadas()).isEqualTo(UPDATED_QTD_ACOES_FORMACAO_REALIZADAS);
        assertThat(testIndicadorProducao.getQtdManuaisMoPrevistos()).isEqualTo(UPDATED_QTD_MANUAIS_MO_PREVISTOS);
        assertThat(testIndicadorProducao.getQtdManuaisMmsPrevistos()).isEqualTo(UPDATED_QTD_MANUAIS_MMS_PREVISTOS);
        assertThat(testIndicadorProducao.getQtdManuaisCmpPrevistos()).isEqualTo(UPDATED_QTD_MANUAIS_CMP_PREVISTOS);
        assertThat(testIndicadorProducao.getQtdManuaisPrevistos()).isEqualTo(UPDATED_QTD_MANUAIS_PREVISTOS);
        assertThat(testIndicadorProducao.getQtdAcoesManuaisMoRealizadas()).isEqualTo(UPDATED_QTD_ACOES_MANUAIS_MO_REALIZADAS);
        assertThat(testIndicadorProducao.getQtdManuaisMmsRealizadas()).isEqualTo(UPDATED_QTD_MANUAIS_MMS_REALIZADAS);
        assertThat(testIndicadorProducao.getQtdManuaisCmpRealizadas()).isEqualTo(UPDATED_QTD_MANUAIS_CMP_REALIZADAS);
        assertThat(testIndicadorProducao.getQtdManuaisRealizados()).isEqualTo(UPDATED_QTD_MANUAIS_REALIZADOS);
    }

    @Test
    @Transactional
    public void updateNonExistingIndicadorProducao() throws Exception {
        int databaseSizeBeforeUpdate = indicadorProducaoRepository.findAll().size();

        // Create the IndicadorProducao
        IndicadorProducaoDTO indicadorProducaoDTO = indicadorProducaoMapper.toDto(indicadorProducao);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restIndicadorProducaoMockMvc.perform(put("/api/indicador-producaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoDTO)))
            .andExpect(status().isCreated());

        // Validate the IndicadorProducao in the database
        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteIndicadorProducao() throws Exception {
        // Initialize the database
        indicadorProducaoRepository.saveAndFlush(indicadorProducao);
        int databaseSizeBeforeDelete = indicadorProducaoRepository.findAll().size();

        // Get the indicadorProducao
        restIndicadorProducaoMockMvc.perform(delete("/api/indicador-producaos/{id}", indicadorProducao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<IndicadorProducao> indicadorProducaoList = indicadorProducaoRepository.findAll();
        assertThat(indicadorProducaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IndicadorProducao.class);
        IndicadorProducao indicadorProducao1 = new IndicadorProducao();
        indicadorProducao1.setId(1L);
        IndicadorProducao indicadorProducao2 = new IndicadorProducao();
        indicadorProducao2.setId(indicadorProducao1.getId());
        assertThat(indicadorProducao1).isEqualTo(indicadorProducao2);
        indicadorProducao2.setId(2L);
        assertThat(indicadorProducao1).isNotEqualTo(indicadorProducao2);
        indicadorProducao1.setId(null);
        assertThat(indicadorProducao1).isNotEqualTo(indicadorProducao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IndicadorProducaoDTO.class);
        IndicadorProducaoDTO indicadorProducaoDTO1 = new IndicadorProducaoDTO();
        indicadorProducaoDTO1.setId(1L);
        IndicadorProducaoDTO indicadorProducaoDTO2 = new IndicadorProducaoDTO();
        assertThat(indicadorProducaoDTO1).isNotEqualTo(indicadorProducaoDTO2);
        indicadorProducaoDTO2.setId(indicadorProducaoDTO1.getId());
        assertThat(indicadorProducaoDTO1).isEqualTo(indicadorProducaoDTO2);
        indicadorProducaoDTO2.setId(2L);
        assertThat(indicadorProducaoDTO1).isNotEqualTo(indicadorProducaoDTO2);
        indicadorProducaoDTO1.setId(null);
        assertThat(indicadorProducaoDTO1).isNotEqualTo(indicadorProducaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(indicadorProducaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(indicadorProducaoMapper.fromId(null)).isNull();
    }
}
