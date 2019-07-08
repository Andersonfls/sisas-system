package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.domain.Situacao;
import com.minea.sisas.domain.Comuna;
import com.minea.sisas.domain.Adjudicacao;
import com.minea.sisas.domain.Concepcao;
import com.minea.sisas.domain.Concurso;
import com.minea.sisas.domain.Contrato;
import com.minea.sisas.domain.Empreitada;
import com.minea.sisas.domain.Execucao;
import com.minea.sisas.domain.IndicadorProducao;
import com.minea.sisas.domain.SistemaAguaLog;
import com.minea.sisas.repository.SistemaAguaRepository;
import com.minea.sisas.service.SistemaAguaService;
import com.minea.sisas.service.dto.SistemaAguaDTO;
import com.minea.sisas.service.mapper.SistemaAguaMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.SistemaAguaQueryService;

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
 * Test class for the SistemaAguaResource REST controller.
 *
 * @see SistemaAguaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class SistemaAguaResourceIntTest {

    private static final Long DEFAULT_ID_SISTEMA_AGUA = 1L;
    private static final Long UPDATED_ID_SISTEMA_AGUA = 2L;

    private static final Long DEFAULT_ID_USUARIO = 1L;
    private static final Long UPDATED_ID_USUARIO = 2L;

    private static final String DEFAULT_NM_INQUERIDOR = "AAAAAAAAAA";
    private static final String UPDATED_NM_INQUERIDOR = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DT_LANCAMENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_LANCAMENTO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_ULTIMA_ALTERACAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_ULTIMA_ALTERACAO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NM_LOCALIDADE = "AAAAAAAAAA";
    private static final String UPDATED_NM_LOCALIDADE = "BBBBBBBBBB";

    private static final Long DEFAULT_QTD_POPULACAO_ACTUAL = 1L;
    private static final Long UPDATED_QTD_POPULACAO_ACTUAL = 2L;

    private static final Long DEFAULT_QTD_CASAS_LOCALIDADE = 1L;
    private static final Long UPDATED_QTD_CASAS_LOCALIDADE = 2L;

    private static final String DEFAULT_NM_TP_COMUNA_ALDEIA = "AAAAAAAAAA";
    private static final String UPDATED_NM_TP_COMUNA_ALDEIA = "BBBBBBBBBB";

    private static final String DEFAULT_NM_TP_AREA = "AAAAAAAAAA";
    private static final String UPDATED_NM_TP_AREA = "BBBBBBBBBB";

    private static final Long DEFAULT_POSSUI_SISTEMA_AGUA = 1L;
    private static final Long UPDATED_POSSUI_SISTEMA_AGUA = 2L;

    private static final String DEFAULT_NM_SISTEMA_AGUA = "AAAAAAAAAA";
    private static final String UPDATED_NM_SISTEMA_AGUA = "BBBBBBBBBB";

    private static final String DEFAULT_NM_FONTE_AGUA = "AAAAAAAAAA";
    private static final String UPDATED_NM_FONTE_AGUA = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_LATITUDE = new BigDecimal(1);
    private static final BigDecimal UPDATED_LATITUDE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LONGITUDE = new BigDecimal(1);
    private static final BigDecimal UPDATED_LONGITUDE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ALTITUDE = new BigDecimal(1);
    private static final BigDecimal UPDATED_ALTITUDE = new BigDecimal(2);

    private static final String DEFAULT_NM_TP_FONTE = "AAAAAAAAAA";
    private static final String UPDATED_NM_TP_FONTE = "BBBBBBBBBB";

    private static final String DEFAULT_NM_FONTE_AGUA_UTILIZADA = "AAAAAAAAAA";
    private static final String UPDATED_NM_FONTE_AGUA_UTILIZADA = "BBBBBBBBBB";

    private static final String DEFAULT_NM_TIPO_BOMBA = "AAAAAAAAAA";
    private static final String UPDATED_NM_TIPO_BOMBA = "BBBBBBBBBB";

    private static final Long DEFAULT_QTD_CASAS_AGUA_LIGADA = 1L;
    private static final Long UPDATED_QTD_CASAS_AGUA_LIGADA = 2L;

    private static final Long DEFAULT_QTD_CHAFARISES_FUNCIONANDO = 1L;
    private static final Long UPDATED_QTD_CHAFARISES_FUNCIONANDO = 2L;

    private static final Long DEFAULT_QTD_CONTADORES_LIGADOS = 1L;
    private static final Long UPDATED_QTD_CONTADORES_LIGADOS = 2L;

    private static final Long DEFAULT_QTD_BEBEDOUROS = 1L;
    private static final Long UPDATED_QTD_BEBEDOUROS = 2L;

    private static final Long DEFAULT_QTD_HABITANTES_ACESSO_SERVICO_AGUA = 1L;
    private static final Long UPDATED_QTD_HABITANTES_ACESSO_SERVICO_AGUA = 2L;

    private static final Long DEFAULT_ANO_CONSTRUCAO_SISTEMA = 1L;
    private static final Long UPDATED_ANO_CONSTRUCAO_SISTEMA = 2L;

    private static final String DEFAULT_NM_TP_AVARIA_SISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_NM_TP_AVARIA_SISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_CAUSA_AVARIA_SISTEMA = "AAAAAAAAAA";
    private static final String UPDATED_CAUSA_AVARIA_SISTEMA = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_RESOLUCAO = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_RESOLUCAO = "BBBBBBBBBB";

    private static final String DEFAULT_TEMPO_SERVICO_DISPONIVEL = "AAAAAAAAAA";
    private static final String UPDATED_TEMPO_SERVICO_DISPONIVEL = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_BRUTA = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_BRUTA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_BRUTA = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_BRUTA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_TRATADA = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_TRATADA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_TRATADA = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_TRATADA = new BigDecimal(2);

    private static final String DEFAULT_DESC_MATERIAL_UTILIZADO_CONDUTAS = "AAAAAAAAAA";
    private static final String UPDATED_DESC_MATERIAL_UTILIZADO_CONDUTAS = "BBBBBBBBBB";

    private static final Long DEFAULT_QTD_RESERVATORIOS_APOIADOS = 1L;
    private static final Long UPDATED_QTD_RESERVATORIOS_APOIADOS = 2L;

    private static final Long DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS = 1L;
    private static final Long UPDATED_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS = 2L;

    private static final Long DEFAULT_QTD_RESERVATORIOS_ELEVADOS = 1L;
    private static final Long UPDATED_QTD_RESERVATORIOS_ELEVADOS = 2L;

    private static final Long DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS = 1L;
    private static final Long UPDATED_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS = 2L;

    private static final BigDecimal DEFAULT_ALTURA_RESERVATORIOS_ELEVADOS = new BigDecimal(1);
    private static final BigDecimal UPDATED_ALTURA_RESERVATORIOS_ELEVADOS = new BigDecimal(2);

    private static final String DEFAULT_NM_TP_TRATAMENTO_AGUA = "AAAAAAAAAA";
    private static final String UPDATED_NM_TP_TRATAMENTO_AGUA = "BBBBBBBBBB";

    private static final String DEFAULT_NM_TP_TRATAMENTO_PADRAO_UTILIZADO = "AAAAAAAAAA";
    private static final String UPDATED_NM_TP_TRATAMENTO_PADRAO_UTILIZADO = "BBBBBBBBBB";

    private static final String DEFAULT_NM_TP_TRATAMENTO_BASICO_UTILIZADO = "AAAAAAAAAA";
    private static final String UPDATED_NM_TP_TRATAMENTO_BASICO_UTILIZADO = "BBBBBBBBBB";

    private static final String DEFAULT_EXISTE_AVARIA_SISTEMA_TRATAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_EXISTE_AVARIA_SISTEMA_TRATAMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_EXISTE_MOTIVO_AUSENCIA_TRATAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_EXISTE_MOTIVO_AUSENCIA_TRATAMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_NM_EQUIPAMENTOS_COM_AVARIA = "AAAAAAAAAA";
    private static final String UPDATED_NM_EQUIPAMENTOS_COM_AVARIA = "BBBBBBBBBB";

    private static final Long DEFAULT_CAUDAL_DO_SISTEMA = 1L;
    private static final Long UPDATED_CAUDAL_DO_SISTEMA = 2L;

    private static final BigDecimal DEFAULT_QTD_CONSUMO_PERCAPTA_LITROS_HOMEM_DIA = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_CONSUMO_PERCAPTA_LITROS_HOMEM_DIA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTD_DOTACAO_PERCAPTA = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_DOTACAO_PERCAPTA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTD_DIARIA_HORAS_SERVICO_SISTEMA = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTD_DIARIA_HORAS_SERVICO_SISTEMA = new BigDecimal(2);

    private static final String DEFAULT_ESQUEMA = "AAAAAAAAAA";
    private static final String UPDATED_ESQUEMA = "BBBBBBBBBB";

    private static final String DEFAULT_NM_MODELO_BOMBA_MANUAL_UTILIZADA = "AAAAAAAAAA";
    private static final String UPDATED_NM_MODELO_BOMBA_MANUAL_UTILIZADA = "BBBBBBBBBB";

    private static final String DEFAULT_NM_TP_BOMBA_ENERGIA = "AAAAAAAAAA";
    private static final String UPDATED_NM_TP_BOMBA_ENERGIA = "BBBBBBBBBB";

    @Autowired
    private SistemaAguaRepository sistemaAguaRepository;

    @Autowired
    private SistemaAguaMapper sistemaAguaMapper;

    @Autowired
    private SistemaAguaService sistemaAguaService;

    @Autowired
    private SistemaAguaQueryService sistemaAguaQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSistemaAguaMockMvc;

    private SistemaAgua sistemaAgua;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SistemaAguaResource sistemaAguaResource = new SistemaAguaResource(sistemaAguaService, sistemaAguaQueryService);
        this.restSistemaAguaMockMvc = MockMvcBuilders.standaloneSetup(sistemaAguaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SistemaAgua createEntity(EntityManager em) {
        SistemaAgua sistemaAgua = new SistemaAgua()
            .idUsuario(DEFAULT_ID_USUARIO)
            .nmInqueridor(DEFAULT_NM_INQUERIDOR)
            .dtLancamento(DEFAULT_DT_LANCAMENTO)
            .dtUltimaAlteracao(DEFAULT_DT_ULTIMA_ALTERACAO)
            .nmLocalidade(DEFAULT_NM_LOCALIDADE)
            .qtdPopulacaoActual(DEFAULT_QTD_POPULACAO_ACTUAL)
            .qtdCasasLocalidade(DEFAULT_QTD_CASAS_LOCALIDADE)
            .nmTpComunaAldeia(DEFAULT_NM_TP_COMUNA_ALDEIA)
            .nmTpArea(DEFAULT_NM_TP_AREA)
            .possuiSistemaAgua(DEFAULT_POSSUI_SISTEMA_AGUA)
            .nmSistemaAgua(DEFAULT_NM_SISTEMA_AGUA)
            .nmFonteAgua(DEFAULT_NM_FONTE_AGUA)
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE)
            .altitude(DEFAULT_ALTITUDE)
            .nmTpFonte(DEFAULT_NM_TP_FONTE)
            .nmFonteAguaUtilizada(DEFAULT_NM_FONTE_AGUA_UTILIZADA)
            .nmTipoBomba(DEFAULT_NM_TIPO_BOMBA)
            .qtdCasasAguaLigada(DEFAULT_QTD_CASAS_AGUA_LIGADA)
            .qtdChafarisesFuncionando(DEFAULT_QTD_CHAFARISES_FUNCIONANDO)
            .qtdContadoresLigados(DEFAULT_QTD_CONTADORES_LIGADOS)
            .qtdBebedouros(DEFAULT_QTD_BEBEDOUROS)
            .qtdHabitantesAcessoServicoAgua(DEFAULT_QTD_HABITANTES_ACESSO_SERVICO_AGUA)
            .anoConstrucaoSistema(DEFAULT_ANO_CONSTRUCAO_SISTEMA)
            .nmTpAvariaSistema(DEFAULT_NM_TP_AVARIA_SISTEMA)
            .causaAvariaSistema(DEFAULT_CAUSA_AVARIA_SISTEMA)
            .statusResolucao(DEFAULT_STATUS_RESOLUCAO)
            .tempoServicoDisponivel(DEFAULT_TEMPO_SERVICO_DISPONIVEL)
            .qtdDiametroCondutaAdutoraAguaBruta(DEFAULT_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_BRUTA)
            .qtdComprimentoCondutaAdutoraAguaBruta(DEFAULT_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_BRUTA)
            .qtdDiametroCondutaAdutoraAguaTratada(DEFAULT_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_TRATADA)
            .qtdComprimentoCondutaAdutoraAguaTratada(DEFAULT_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_TRATADA)
            .descMaterialUtilizadoCondutas(DEFAULT_DESC_MATERIAL_UTILIZADO_CONDUTAS)
            .qtdReservatoriosApoiados(DEFAULT_QTD_RESERVATORIOS_APOIADOS)
            .qtdCapacidadeReservatoriosApoiados(DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS)
            .qtdReservatoriosElevados(DEFAULT_QTD_RESERVATORIOS_ELEVADOS)
            .qtdCapacidadeReservatoriosElevados(DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS)
            .alturaReservatoriosElevados(DEFAULT_ALTURA_RESERVATORIOS_ELEVADOS)
            .nmTpTratamentoAgua(DEFAULT_NM_TP_TRATAMENTO_AGUA)
            .nmTpTratamentoPadraoUtilizado(DEFAULT_NM_TP_TRATAMENTO_PADRAO_UTILIZADO)
            .nmTpTratamentoBasicoUtilizado(DEFAULT_NM_TP_TRATAMENTO_BASICO_UTILIZADO)
            .existeAvariaSistemaTratamento(DEFAULT_EXISTE_AVARIA_SISTEMA_TRATAMENTO)
            .existeMotivoAusenciaTratamento(DEFAULT_EXISTE_MOTIVO_AUSENCIA_TRATAMENTO)
            .nmEquipamentosComAvaria(DEFAULT_NM_EQUIPAMENTOS_COM_AVARIA)
            .caudalDoSistema(DEFAULT_CAUDAL_DO_SISTEMA)
            .qtdConsumoPercaptaLitrosHomemDia(DEFAULT_QTD_CONSUMO_PERCAPTA_LITROS_HOMEM_DIA)
            .qtdDotacaoPercapta(DEFAULT_QTD_DOTACAO_PERCAPTA)
            .qtdDiariaHorasServicoSistema(DEFAULT_QTD_DIARIA_HORAS_SERVICO_SISTEMA)
            .esquema(DEFAULT_ESQUEMA)
            .nmModeloBombaManualUtilizada(DEFAULT_NM_MODELO_BOMBA_MANUAL_UTILIZADA)
            .nmTpBombaEnergia(DEFAULT_NM_TP_BOMBA_ENERGIA);
        // Add required entity
        Situacao idSituacao = SituacaoResourceIntTest.createEntity(em);
        em.persist(idSituacao);
        em.flush();
        sistemaAgua.setSituacao(idSituacao);
        // Add required entity
        Comuna idComuna = ComunaResourceIntTest.createEntity(em);
        em.persist(idComuna);
        em.flush();
        sistemaAgua.setComuna(idComuna);
        return sistemaAgua;
    }

    @Before
    public void initTest() {
        sistemaAgua = createEntity(em);
    }

    @Test
    @Transactional
    public void createSistemaAgua() throws Exception {
        int databaseSizeBeforeCreate = sistemaAguaRepository.findAll().size();

        // Create the SistemaAgua
        SistemaAguaDTO sistemaAguaDTO = sistemaAguaMapper.toDto(sistemaAgua);
        restSistemaAguaMockMvc.perform(post("/api/sistema-aguas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaDTO)))
            .andExpect(status().isCreated());

        // Validate the SistemaAgua in the database
        List<SistemaAgua> sistemaAguaList = sistemaAguaRepository.findAll();
        assertThat(sistemaAguaList).hasSize(databaseSizeBeforeCreate + 1);
        SistemaAgua testSistemaAgua = sistemaAguaList.get(sistemaAguaList.size() - 1);
        assertThat(testSistemaAgua.getIdUsuario()).isEqualTo(DEFAULT_ID_USUARIO);
        assertThat(testSistemaAgua.getNmInqueridor()).isEqualTo(DEFAULT_NM_INQUERIDOR);
        assertThat(testSistemaAgua.getDtLancamento()).isEqualTo(DEFAULT_DT_LANCAMENTO);
        assertThat(testSistemaAgua.getDtUltimaAlteracao()).isEqualTo(DEFAULT_DT_ULTIMA_ALTERACAO);
        assertThat(testSistemaAgua.getNmLocalidade()).isEqualTo(DEFAULT_NM_LOCALIDADE);
        assertThat(testSistemaAgua.getQtdPopulacaoActual()).isEqualTo(DEFAULT_QTD_POPULACAO_ACTUAL);
        assertThat(testSistemaAgua.getQtdCasasLocalidade()).isEqualTo(DEFAULT_QTD_CASAS_LOCALIDADE);
        assertThat(testSistemaAgua.getNmTpComunaAldeia()).isEqualTo(DEFAULT_NM_TP_COMUNA_ALDEIA);
        assertThat(testSistemaAgua.getNmTpArea()).isEqualTo(DEFAULT_NM_TP_AREA);
        assertThat(testSistemaAgua.getPossuiSistemaAgua()).isEqualTo(DEFAULT_POSSUI_SISTEMA_AGUA);
        assertThat(testSistemaAgua.getNmSistemaAgua()).isEqualTo(DEFAULT_NM_SISTEMA_AGUA);
        assertThat(testSistemaAgua.getNmFonteAgua()).isEqualTo(DEFAULT_NM_FONTE_AGUA);
        assertThat(testSistemaAgua.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testSistemaAgua.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testSistemaAgua.getAltitude()).isEqualTo(DEFAULT_ALTITUDE);
        assertThat(testSistemaAgua.getNmTpFonte()).isEqualTo(DEFAULT_NM_TP_FONTE);
        assertThat(testSistemaAgua.getNmFonteAguaUtilizada()).isEqualTo(DEFAULT_NM_FONTE_AGUA_UTILIZADA);
        assertThat(testSistemaAgua.getNmTipoBomba()).isEqualTo(DEFAULT_NM_TIPO_BOMBA);
        assertThat(testSistemaAgua.getQtdCasasAguaLigada()).isEqualTo(DEFAULT_QTD_CASAS_AGUA_LIGADA);
        assertThat(testSistemaAgua.getQtdChafarisesFuncionando()).isEqualTo(DEFAULT_QTD_CHAFARISES_FUNCIONANDO);
        assertThat(testSistemaAgua.getQtdContadoresLigados()).isEqualTo(DEFAULT_QTD_CONTADORES_LIGADOS);
        assertThat(testSistemaAgua.getQtdBebedouros()).isEqualTo(DEFAULT_QTD_BEBEDOUROS);
        assertThat(testSistemaAgua.getQtdHabitantesAcessoServicoAgua()).isEqualTo(DEFAULT_QTD_HABITANTES_ACESSO_SERVICO_AGUA);
        assertThat(testSistemaAgua.getAnoConstrucaoSistema()).isEqualTo(DEFAULT_ANO_CONSTRUCAO_SISTEMA);
        assertThat(testSistemaAgua.getNmTpAvariaSistema()).isEqualTo(DEFAULT_NM_TP_AVARIA_SISTEMA);
        assertThat(testSistemaAgua.getCausaAvariaSistema()).isEqualTo(DEFAULT_CAUSA_AVARIA_SISTEMA);
        assertThat(testSistemaAgua.getStatusResolucao()).isEqualTo(DEFAULT_STATUS_RESOLUCAO);
        assertThat(testSistemaAgua.getTempoServicoDisponivel()).isEqualTo(DEFAULT_TEMPO_SERVICO_DISPONIVEL);
        assertThat(testSistemaAgua.getQtdDiametroCondutaAdutoraAguaBruta()).isEqualTo(DEFAULT_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_BRUTA);
        assertThat(testSistemaAgua.getQtdComprimentoCondutaAdutoraAguaBruta()).isEqualTo(DEFAULT_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_BRUTA);
        assertThat(testSistemaAgua.getQtdDiametroCondutaAdutoraAguaTratada()).isEqualTo(DEFAULT_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_TRATADA);
        assertThat(testSistemaAgua.getQtdComprimentoCondutaAdutoraAguaTratada()).isEqualTo(DEFAULT_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_TRATADA);
        assertThat(testSistemaAgua.getDescMaterialUtilizadoCondutas()).isEqualTo(DEFAULT_DESC_MATERIAL_UTILIZADO_CONDUTAS);
        assertThat(testSistemaAgua.getQtdReservatoriosApoiados()).isEqualTo(DEFAULT_QTD_RESERVATORIOS_APOIADOS);
        assertThat(testSistemaAgua.getQtdCapacidadeReservatoriosApoiados()).isEqualTo(DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS);
        assertThat(testSistemaAgua.getQtdReservatoriosElevados()).isEqualTo(DEFAULT_QTD_RESERVATORIOS_ELEVADOS);
        assertThat(testSistemaAgua.getQtdCapacidadeReservatoriosElevados()).isEqualTo(DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS);
        assertThat(testSistemaAgua.getAlturaReservatoriosElevados()).isEqualTo(DEFAULT_ALTURA_RESERVATORIOS_ELEVADOS);
        assertThat(testSistemaAgua.getNmTpTratamentoAgua()).isEqualTo(DEFAULT_NM_TP_TRATAMENTO_AGUA);
        assertThat(testSistemaAgua.getNmTpTratamentoPadraoUtilizado()).isEqualTo(DEFAULT_NM_TP_TRATAMENTO_PADRAO_UTILIZADO);
        assertThat(testSistemaAgua.getNmTpTratamentoBasicoUtilizado()).isEqualTo(DEFAULT_NM_TP_TRATAMENTO_BASICO_UTILIZADO);
        assertThat(testSistemaAgua.getExisteAvariaSistemaTratamento()).isEqualTo(DEFAULT_EXISTE_AVARIA_SISTEMA_TRATAMENTO);
        assertThat(testSistemaAgua.getExisteMotivoAusenciaTratamento()).isEqualTo(DEFAULT_EXISTE_MOTIVO_AUSENCIA_TRATAMENTO);
        assertThat(testSistemaAgua.getNmEquipamentosComAvaria()).isEqualTo(DEFAULT_NM_EQUIPAMENTOS_COM_AVARIA);
        assertThat(testSistemaAgua.getCaudalDoSistema()).isEqualTo(DEFAULT_CAUDAL_DO_SISTEMA);
        assertThat(testSistemaAgua.getQtdConsumoPercaptaLitrosHomemDia()).isEqualTo(DEFAULT_QTD_CONSUMO_PERCAPTA_LITROS_HOMEM_DIA);
        assertThat(testSistemaAgua.getQtdDotacaoPercapta()).isEqualTo(DEFAULT_QTD_DOTACAO_PERCAPTA);
        assertThat(testSistemaAgua.getQtdDiariaHorasServicoSistema()).isEqualTo(DEFAULT_QTD_DIARIA_HORAS_SERVICO_SISTEMA);
        assertThat(testSistemaAgua.getEsquema()).isEqualTo(DEFAULT_ESQUEMA);
        assertThat(testSistemaAgua.getNmModeloBombaManualUtilizada()).isEqualTo(DEFAULT_NM_MODELO_BOMBA_MANUAL_UTILIZADA);
        assertThat(testSistemaAgua.getNmTpBombaEnergia()).isEqualTo(DEFAULT_NM_TP_BOMBA_ENERGIA);
    }

    @Test
    @Transactional
    public void createSistemaAguaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sistemaAguaRepository.findAll().size();

        // Create the SistemaAgua with an existing ID
        sistemaAgua.setId(1L);
        SistemaAguaDTO sistemaAguaDTO = sistemaAguaMapper.toDto(sistemaAgua);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSistemaAguaMockMvc.perform(post("/api/sistema-aguas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SistemaAgua in the database
        List<SistemaAgua> sistemaAguaList = sistemaAguaRepository.findAll();
        assertThat(sistemaAguaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdSistemaAguaIsRequired() throws Exception {
        int databaseSizeBeforeTest = sistemaAguaRepository.findAll().size();
        // set the field null

        // Create the SistemaAgua, which fails.
        SistemaAguaDTO sistemaAguaDTO = sistemaAguaMapper.toDto(sistemaAgua);

        restSistemaAguaMockMvc.perform(post("/api/sistema-aguas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaDTO)))
            .andExpect(status().isBadRequest());

        List<SistemaAgua> sistemaAguaList = sistemaAguaRepository.findAll();
        assertThat(sistemaAguaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdUsuarioIsRequired() throws Exception {
        int databaseSizeBeforeTest = sistemaAguaRepository.findAll().size();
        // set the field null
        sistemaAgua.setIdUsuario(null);

        // Create the SistemaAgua, which fails.
        SistemaAguaDTO sistemaAguaDTO = sistemaAguaMapper.toDto(sistemaAgua);

        restSistemaAguaMockMvc.perform(post("/api/sistema-aguas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaDTO)))
            .andExpect(status().isBadRequest());

        List<SistemaAgua> sistemaAguaList = sistemaAguaRepository.findAll();
        assertThat(sistemaAguaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNmInqueridorIsRequired() throws Exception {
        int databaseSizeBeforeTest = sistemaAguaRepository.findAll().size();
        // set the field null
        sistemaAgua.setNmInqueridor(null);

        // Create the SistemaAgua, which fails.
        SistemaAguaDTO sistemaAguaDTO = sistemaAguaMapper.toDto(sistemaAgua);

        restSistemaAguaMockMvc.perform(post("/api/sistema-aguas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaDTO)))
            .andExpect(status().isBadRequest());

        List<SistemaAgua> sistemaAguaList = sistemaAguaRepository.findAll();
        assertThat(sistemaAguaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDtLancamentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = sistemaAguaRepository.findAll().size();
        // set the field null
        sistemaAgua.setDtLancamento(null);

        // Create the SistemaAgua, which fails.
        SistemaAguaDTO sistemaAguaDTO = sistemaAguaMapper.toDto(sistemaAgua);

        restSistemaAguaMockMvc.perform(post("/api/sistema-aguas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaDTO)))
            .andExpect(status().isBadRequest());

        List<SistemaAgua> sistemaAguaList = sistemaAguaRepository.findAll();
        assertThat(sistemaAguaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNmSistemaAguaIsRequired() throws Exception {
        int databaseSizeBeforeTest = sistemaAguaRepository.findAll().size();
        // set the field null
        sistemaAgua.setNmSistemaAgua(null);

        // Create the SistemaAgua, which fails.
        SistemaAguaDTO sistemaAguaDTO = sistemaAguaMapper.toDto(sistemaAgua);

        restSistemaAguaMockMvc.perform(post("/api/sistema-aguas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaDTO)))
            .andExpect(status().isBadRequest());

        List<SistemaAgua> sistemaAguaList = sistemaAguaRepository.findAll();
        assertThat(sistemaAguaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNmFonteAguaIsRequired() throws Exception {
        int databaseSizeBeforeTest = sistemaAguaRepository.findAll().size();
        // set the field null
        sistemaAgua.setNmFonteAgua(null);

        // Create the SistemaAgua, which fails.
        SistemaAguaDTO sistemaAguaDTO = sistemaAguaMapper.toDto(sistemaAgua);

        restSistemaAguaMockMvc.perform(post("/api/sistema-aguas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaDTO)))
            .andExpect(status().isBadRequest());

        List<SistemaAgua> sistemaAguaList = sistemaAguaRepository.findAll();
        assertThat(sistemaAguaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNmFonteAguaUtilizadaIsRequired() throws Exception {
        int databaseSizeBeforeTest = sistemaAguaRepository.findAll().size();
        // set the field null
        sistemaAgua.setNmFonteAguaUtilizada(null);

        // Create the SistemaAgua, which fails.
        SistemaAguaDTO sistemaAguaDTO = sistemaAguaMapper.toDto(sistemaAgua);

        restSistemaAguaMockMvc.perform(post("/api/sistema-aguas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaDTO)))
            .andExpect(status().isBadRequest());

        List<SistemaAgua> sistemaAguaList = sistemaAguaRepository.findAll();
        assertThat(sistemaAguaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNmTipoBombaIsRequired() throws Exception {
        int databaseSizeBeforeTest = sistemaAguaRepository.findAll().size();
        // set the field null
        sistemaAgua.setNmTipoBomba(null);

        // Create the SistemaAgua, which fails.
        SistemaAguaDTO sistemaAguaDTO = sistemaAguaMapper.toDto(sistemaAgua);

        restSistemaAguaMockMvc.perform(post("/api/sistema-aguas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaDTO)))
            .andExpect(status().isBadRequest());

        List<SistemaAgua> sistemaAguaList = sistemaAguaRepository.findAll();
        assertThat(sistemaAguaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNmTpTratamentoAguaIsRequired() throws Exception {
        int databaseSizeBeforeTest = sistemaAguaRepository.findAll().size();
        // set the field null
        sistemaAgua.setNmTpTratamentoAgua(null);

        // Create the SistemaAgua, which fails.
        SistemaAguaDTO sistemaAguaDTO = sistemaAguaMapper.toDto(sistemaAgua);

        restSistemaAguaMockMvc.perform(post("/api/sistema-aguas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaDTO)))
            .andExpect(status().isBadRequest());

        List<SistemaAgua> sistemaAguaList = sistemaAguaRepository.findAll();
        assertThat(sistemaAguaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNmTpTratamentoPadraoUtilizadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = sistemaAguaRepository.findAll().size();
        // set the field null
        sistemaAgua.setNmTpTratamentoPadraoUtilizado(null);

        // Create the SistemaAgua, which fails.
        SistemaAguaDTO sistemaAguaDTO = sistemaAguaMapper.toDto(sistemaAgua);

        restSistemaAguaMockMvc.perform(post("/api/sistema-aguas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaDTO)))
            .andExpect(status().isBadRequest());

        List<SistemaAgua> sistemaAguaList = sistemaAguaRepository.findAll();
        assertThat(sistemaAguaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNmTpTratamentoBasicoUtilizadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = sistemaAguaRepository.findAll().size();
        // set the field null
        sistemaAgua.setNmTpTratamentoBasicoUtilizado(null);

        // Create the SistemaAgua, which fails.
        SistemaAguaDTO sistemaAguaDTO = sistemaAguaMapper.toDto(sistemaAgua);

        restSistemaAguaMockMvc.perform(post("/api/sistema-aguas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaDTO)))
            .andExpect(status().isBadRequest());

        List<SistemaAgua> sistemaAguaList = sistemaAguaRepository.findAll();
        assertThat(sistemaAguaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExisteAvariaSistemaTratamentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = sistemaAguaRepository.findAll().size();
        // set the field null
        sistemaAgua.setExisteAvariaSistemaTratamento(null);

        // Create the SistemaAgua, which fails.
        SistemaAguaDTO sistemaAguaDTO = sistemaAguaMapper.toDto(sistemaAgua);

        restSistemaAguaMockMvc.perform(post("/api/sistema-aguas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaDTO)))
            .andExpect(status().isBadRequest());

        List<SistemaAgua> sistemaAguaList = sistemaAguaRepository.findAll();
        assertThat(sistemaAguaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExisteMotivoAusenciaTratamentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = sistemaAguaRepository.findAll().size();
        // set the field null
        sistemaAgua.setExisteMotivoAusenciaTratamento(null);

        // Create the SistemaAgua, which fails.
        SistemaAguaDTO sistemaAguaDTO = sistemaAguaMapper.toDto(sistemaAgua);

        restSistemaAguaMockMvc.perform(post("/api/sistema-aguas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaDTO)))
            .andExpect(status().isBadRequest());

        List<SistemaAgua> sistemaAguaList = sistemaAguaRepository.findAll();
        assertThat(sistemaAguaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNmEquipamentosComAvariaIsRequired() throws Exception {
        int databaseSizeBeforeTest = sistemaAguaRepository.findAll().size();
        // set the field null
        sistemaAgua.setNmEquipamentosComAvaria(null);

        // Create the SistemaAgua, which fails.
        SistemaAguaDTO sistemaAguaDTO = sistemaAguaMapper.toDto(sistemaAgua);

        restSistemaAguaMockMvc.perform(post("/api/sistema-aguas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaDTO)))
            .andExpect(status().isBadRequest());

        List<SistemaAgua> sistemaAguaList = sistemaAguaRepository.findAll();
        assertThat(sistemaAguaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEsquemaIsRequired() throws Exception {
        int databaseSizeBeforeTest = sistemaAguaRepository.findAll().size();
        // set the field null
        sistemaAgua.setEsquema(null);

        // Create the SistemaAgua, which fails.
        SistemaAguaDTO sistemaAguaDTO = sistemaAguaMapper.toDto(sistemaAgua);

        restSistemaAguaMockMvc.perform(post("/api/sistema-aguas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaDTO)))
            .andExpect(status().isBadRequest());

        List<SistemaAgua> sistemaAguaList = sistemaAguaRepository.findAll();
        assertThat(sistemaAguaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNmModeloBombaManualUtilizadaIsRequired() throws Exception {
        int databaseSizeBeforeTest = sistemaAguaRepository.findAll().size();
        // set the field null
        sistemaAgua.setNmModeloBombaManualUtilizada(null);

        // Create the SistemaAgua, which fails.
        SistemaAguaDTO sistemaAguaDTO = sistemaAguaMapper.toDto(sistemaAgua);

        restSistemaAguaMockMvc.perform(post("/api/sistema-aguas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaDTO)))
            .andExpect(status().isBadRequest());

        List<SistemaAgua> sistemaAguaList = sistemaAguaRepository.findAll();
        assertThat(sistemaAguaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSistemaAguas() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList
        restSistemaAguaMockMvc.perform(get("/api/sistema-aguas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sistemaAgua.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSistemaAgua").value(hasItem(DEFAULT_ID_SISTEMA_AGUA.intValue())))
            .andExpect(jsonPath("$.[*].idUsuario").value(hasItem(DEFAULT_ID_USUARIO.intValue())))
            .andExpect(jsonPath("$.[*].nmInqueridor").value(hasItem(DEFAULT_NM_INQUERIDOR.toString())))
            .andExpect(jsonPath("$.[*].dtLancamento").value(hasItem(DEFAULT_DT_LANCAMENTO.toString())))
            .andExpect(jsonPath("$.[*].dtUltimaAlteracao").value(hasItem(DEFAULT_DT_ULTIMA_ALTERACAO.toString())))
            .andExpect(jsonPath("$.[*].nmLocalidade").value(hasItem(DEFAULT_NM_LOCALIDADE.toString())))
            .andExpect(jsonPath("$.[*].qtdPopulacaoActual").value(hasItem(DEFAULT_QTD_POPULACAO_ACTUAL.intValue())))
            .andExpect(jsonPath("$.[*].qtdCasasLocalidade").value(hasItem(DEFAULT_QTD_CASAS_LOCALIDADE.intValue())))
            .andExpect(jsonPath("$.[*].nmTpComunaAldeia").value(hasItem(DEFAULT_NM_TP_COMUNA_ALDEIA.toString())))
            .andExpect(jsonPath("$.[*].nmTpArea").value(hasItem(DEFAULT_NM_TP_AREA.toString())))
            .andExpect(jsonPath("$.[*].possuiSistemaAgua").value(hasItem(DEFAULT_POSSUI_SISTEMA_AGUA.intValue())))
            .andExpect(jsonPath("$.[*].nmSistemaAgua").value(hasItem(DEFAULT_NM_SISTEMA_AGUA.toString())))
            .andExpect(jsonPath("$.[*].nmFonteAgua").value(hasItem(DEFAULT_NM_FONTE_AGUA.toString())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.intValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.intValue())))
            .andExpect(jsonPath("$.[*].altitude").value(hasItem(DEFAULT_ALTITUDE.intValue())))
            .andExpect(jsonPath("$.[*].nmTpFonte").value(hasItem(DEFAULT_NM_TP_FONTE.toString())))
            .andExpect(jsonPath("$.[*].nmFonteAguaUtilizada").value(hasItem(DEFAULT_NM_FONTE_AGUA_UTILIZADA.toString())))
            .andExpect(jsonPath("$.[*].nmTipoBomba").value(hasItem(DEFAULT_NM_TIPO_BOMBA.toString())))
            .andExpect(jsonPath("$.[*].qtdCasasAguaLigada").value(hasItem(DEFAULT_QTD_CASAS_AGUA_LIGADA.intValue())))
            .andExpect(jsonPath("$.[*].qtdChafarisesFuncionando").value(hasItem(DEFAULT_QTD_CHAFARISES_FUNCIONANDO.intValue())))
            .andExpect(jsonPath("$.[*].qtdContadoresLigados").value(hasItem(DEFAULT_QTD_CONTADORES_LIGADOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdBebedouros").value(hasItem(DEFAULT_QTD_BEBEDOUROS.intValue())))
            .andExpect(jsonPath("$.[*].qtdHabitantesAcessoServicoAgua").value(hasItem(DEFAULT_QTD_HABITANTES_ACESSO_SERVICO_AGUA.intValue())))
            .andExpect(jsonPath("$.[*].anoConstrucaoSistema").value(hasItem(DEFAULT_ANO_CONSTRUCAO_SISTEMA.intValue())))
            .andExpect(jsonPath("$.[*].nmTpAvariaSistema").value(hasItem(DEFAULT_NM_TP_AVARIA_SISTEMA.toString())))
            .andExpect(jsonPath("$.[*].causaAvariaSistema").value(hasItem(DEFAULT_CAUSA_AVARIA_SISTEMA.toString())))
            .andExpect(jsonPath("$.[*].statusResolucao").value(hasItem(DEFAULT_STATUS_RESOLUCAO.toString())))
            .andExpect(jsonPath("$.[*].tempoServicoDisponivel").value(hasItem(DEFAULT_TEMPO_SERVICO_DISPONIVEL.toString())))
            .andExpect(jsonPath("$.[*].qtdDiametroCondutaAdutoraAguaBruta").value(hasItem(DEFAULT_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_BRUTA.intValue())))
            .andExpect(jsonPath("$.[*].qtdComprimentoCondutaAdutoraAguaBruta").value(hasItem(DEFAULT_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_BRUTA.intValue())))
            .andExpect(jsonPath("$.[*].qtdDiametroCondutaAdutoraAguaTratada").value(hasItem(DEFAULT_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_TRATADA.intValue())))
            .andExpect(jsonPath("$.[*].qtdComprimentoCondutaAdutoraAguaTratada").value(hasItem(DEFAULT_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_TRATADA.intValue())))
            .andExpect(jsonPath("$.[*].descMaterialUtilizadoCondutas").value(hasItem(DEFAULT_DESC_MATERIAL_UTILIZADO_CONDUTAS.toString())))
            .andExpect(jsonPath("$.[*].qtdReservatoriosApoiados").value(hasItem(DEFAULT_QTD_RESERVATORIOS_APOIADOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdCapacidadeReservatoriosApoiados").value(hasItem(DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdReservatoriosElevados").value(hasItem(DEFAULT_QTD_RESERVATORIOS_ELEVADOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdCapacidadeReservatoriosElevados").value(hasItem(DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS.intValue())))
            .andExpect(jsonPath("$.[*].alturaReservatoriosElevados").value(hasItem(DEFAULT_ALTURA_RESERVATORIOS_ELEVADOS.intValue())))
            .andExpect(jsonPath("$.[*].nmTpTratamentoAgua").value(hasItem(DEFAULT_NM_TP_TRATAMENTO_AGUA.toString())))
            .andExpect(jsonPath("$.[*].nmTpTratamentoPadraoUtilizado").value(hasItem(DEFAULT_NM_TP_TRATAMENTO_PADRAO_UTILIZADO.toString())))
            .andExpect(jsonPath("$.[*].nmTpTratamentoBasicoUtilizado").value(hasItem(DEFAULT_NM_TP_TRATAMENTO_BASICO_UTILIZADO.toString())))
            .andExpect(jsonPath("$.[*].existeAvariaSistemaTratamento").value(hasItem(DEFAULT_EXISTE_AVARIA_SISTEMA_TRATAMENTO.toString())))
            .andExpect(jsonPath("$.[*].existeMotivoAusenciaTratamento").value(hasItem(DEFAULT_EXISTE_MOTIVO_AUSENCIA_TRATAMENTO.toString())))
            .andExpect(jsonPath("$.[*].nmEquipamentosComAvaria").value(hasItem(DEFAULT_NM_EQUIPAMENTOS_COM_AVARIA.toString())))
            .andExpect(jsonPath("$.[*].caudalDoSistema").value(hasItem(DEFAULT_CAUDAL_DO_SISTEMA.intValue())))
            .andExpect(jsonPath("$.[*].qtdConsumoPercaptaLitrosHomemDia").value(hasItem(DEFAULT_QTD_CONSUMO_PERCAPTA_LITROS_HOMEM_DIA.intValue())))
            .andExpect(jsonPath("$.[*].qtdDotacaoPercapta").value(hasItem(DEFAULT_QTD_DOTACAO_PERCAPTA.intValue())))
            .andExpect(jsonPath("$.[*].qtdDiariaHorasServicoSistema").value(hasItem(DEFAULT_QTD_DIARIA_HORAS_SERVICO_SISTEMA.intValue())))
            .andExpect(jsonPath("$.[*].esquema").value(hasItem(DEFAULT_ESQUEMA.toString())))
            .andExpect(jsonPath("$.[*].nmModeloBombaManualUtilizada").value(hasItem(DEFAULT_NM_MODELO_BOMBA_MANUAL_UTILIZADA.toString())))
            .andExpect(jsonPath("$.[*].nmTpBombaEnergia").value(hasItem(DEFAULT_NM_TP_BOMBA_ENERGIA.toString())));
    }

    @Test
    @Transactional
    public void getSistemaAgua() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get the sistemaAgua
        restSistemaAguaMockMvc.perform(get("/api/sistema-aguas/{id}", sistemaAgua.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sistemaAgua.getId().intValue()))
            .andExpect(jsonPath("$.idSistemaAgua").value(DEFAULT_ID_SISTEMA_AGUA.intValue()))
            .andExpect(jsonPath("$.idUsuario").value(DEFAULT_ID_USUARIO.intValue()))
            .andExpect(jsonPath("$.nmInqueridor").value(DEFAULT_NM_INQUERIDOR.toString()))
            .andExpect(jsonPath("$.dtLancamento").value(DEFAULT_DT_LANCAMENTO.toString()))
            .andExpect(jsonPath("$.dtUltimaAlteracao").value(DEFAULT_DT_ULTIMA_ALTERACAO.toString()))
            .andExpect(jsonPath("$.nmLocalidade").value(DEFAULT_NM_LOCALIDADE.toString()))
            .andExpect(jsonPath("$.qtdPopulacaoActual").value(DEFAULT_QTD_POPULACAO_ACTUAL.intValue()))
            .andExpect(jsonPath("$.qtdCasasLocalidade").value(DEFAULT_QTD_CASAS_LOCALIDADE.intValue()))
            .andExpect(jsonPath("$.nmTpComunaAldeia").value(DEFAULT_NM_TP_COMUNA_ALDEIA.toString()))
            .andExpect(jsonPath("$.nmTpArea").value(DEFAULT_NM_TP_AREA.toString()))
            .andExpect(jsonPath("$.possuiSistemaAgua").value(DEFAULT_POSSUI_SISTEMA_AGUA.intValue()))
            .andExpect(jsonPath("$.nmSistemaAgua").value(DEFAULT_NM_SISTEMA_AGUA.toString()))
            .andExpect(jsonPath("$.nmFonteAgua").value(DEFAULT_NM_FONTE_AGUA.toString()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.intValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.intValue()))
            .andExpect(jsonPath("$.altitude").value(DEFAULT_ALTITUDE.intValue()))
            .andExpect(jsonPath("$.nmTpFonte").value(DEFAULT_NM_TP_FONTE.toString()))
            .andExpect(jsonPath("$.nmFonteAguaUtilizada").value(DEFAULT_NM_FONTE_AGUA_UTILIZADA.toString()))
            .andExpect(jsonPath("$.nmTipoBomba").value(DEFAULT_NM_TIPO_BOMBA.toString()))
            .andExpect(jsonPath("$.qtdCasasAguaLigada").value(DEFAULT_QTD_CASAS_AGUA_LIGADA.intValue()))
            .andExpect(jsonPath("$.qtdChafarisesFuncionando").value(DEFAULT_QTD_CHAFARISES_FUNCIONANDO.intValue()))
            .andExpect(jsonPath("$.qtdContadoresLigados").value(DEFAULT_QTD_CONTADORES_LIGADOS.intValue()))
            .andExpect(jsonPath("$.qtdBebedouros").value(DEFAULT_QTD_BEBEDOUROS.intValue()))
            .andExpect(jsonPath("$.qtdHabitantesAcessoServicoAgua").value(DEFAULT_QTD_HABITANTES_ACESSO_SERVICO_AGUA.intValue()))
            .andExpect(jsonPath("$.anoConstrucaoSistema").value(DEFAULT_ANO_CONSTRUCAO_SISTEMA.intValue()))
            .andExpect(jsonPath("$.nmTpAvariaSistema").value(DEFAULT_NM_TP_AVARIA_SISTEMA.toString()))
            .andExpect(jsonPath("$.causaAvariaSistema").value(DEFAULT_CAUSA_AVARIA_SISTEMA.toString()))
            .andExpect(jsonPath("$.statusResolucao").value(DEFAULT_STATUS_RESOLUCAO.toString()))
            .andExpect(jsonPath("$.tempoServicoDisponivel").value(DEFAULT_TEMPO_SERVICO_DISPONIVEL.toString()))
            .andExpect(jsonPath("$.qtdDiametroCondutaAdutoraAguaBruta").value(DEFAULT_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_BRUTA.intValue()))
            .andExpect(jsonPath("$.qtdComprimentoCondutaAdutoraAguaBruta").value(DEFAULT_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_BRUTA.intValue()))
            .andExpect(jsonPath("$.qtdDiametroCondutaAdutoraAguaTratada").value(DEFAULT_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_TRATADA.intValue()))
            .andExpect(jsonPath("$.qtdComprimentoCondutaAdutoraAguaTratada").value(DEFAULT_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_TRATADA.intValue()))
            .andExpect(jsonPath("$.descMaterialUtilizadoCondutas").value(DEFAULT_DESC_MATERIAL_UTILIZADO_CONDUTAS.toString()))
            .andExpect(jsonPath("$.qtdReservatoriosApoiados").value(DEFAULT_QTD_RESERVATORIOS_APOIADOS.intValue()))
            .andExpect(jsonPath("$.qtdCapacidadeReservatoriosApoiados").value(DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS.intValue()))
            .andExpect(jsonPath("$.qtdReservatoriosElevados").value(DEFAULT_QTD_RESERVATORIOS_ELEVADOS.intValue()))
            .andExpect(jsonPath("$.qtdCapacidadeReservatoriosElevados").value(DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS.intValue()))
            .andExpect(jsonPath("$.alturaReservatoriosElevados").value(DEFAULT_ALTURA_RESERVATORIOS_ELEVADOS.intValue()))
            .andExpect(jsonPath("$.nmTpTratamentoAgua").value(DEFAULT_NM_TP_TRATAMENTO_AGUA.toString()))
            .andExpect(jsonPath("$.nmTpTratamentoPadraoUtilizado").value(DEFAULT_NM_TP_TRATAMENTO_PADRAO_UTILIZADO.toString()))
            .andExpect(jsonPath("$.nmTpTratamentoBasicoUtilizado").value(DEFAULT_NM_TP_TRATAMENTO_BASICO_UTILIZADO.toString()))
            .andExpect(jsonPath("$.existeAvariaSistemaTratamento").value(DEFAULT_EXISTE_AVARIA_SISTEMA_TRATAMENTO.toString()))
            .andExpect(jsonPath("$.existeMotivoAusenciaTratamento").value(DEFAULT_EXISTE_MOTIVO_AUSENCIA_TRATAMENTO.toString()))
            .andExpect(jsonPath("$.nmEquipamentosComAvaria").value(DEFAULT_NM_EQUIPAMENTOS_COM_AVARIA.toString()))
            .andExpect(jsonPath("$.caudalDoSistema").value(DEFAULT_CAUDAL_DO_SISTEMA.intValue()))
            .andExpect(jsonPath("$.qtdConsumoPercaptaLitrosHomemDia").value(DEFAULT_QTD_CONSUMO_PERCAPTA_LITROS_HOMEM_DIA.intValue()))
            .andExpect(jsonPath("$.qtdDotacaoPercapta").value(DEFAULT_QTD_DOTACAO_PERCAPTA.intValue()))
            .andExpect(jsonPath("$.qtdDiariaHorasServicoSistema").value(DEFAULT_QTD_DIARIA_HORAS_SERVICO_SISTEMA.intValue()))
            .andExpect(jsonPath("$.esquema").value(DEFAULT_ESQUEMA.toString()))
            .andExpect(jsonPath("$.nmModeloBombaManualUtilizada").value(DEFAULT_NM_MODELO_BOMBA_MANUAL_UTILIZADA.toString()))
            .andExpect(jsonPath("$.nmTpBombaEnergia").value(DEFAULT_NM_TP_BOMBA_ENERGIA.toString()));
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByIdSistemaAguaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where idSistemaAgua equals to DEFAULT_ID_SISTEMA_AGUA
        defaultSistemaAguaShouldBeFound("idSistemaAgua.equals=" + DEFAULT_ID_SISTEMA_AGUA);

        // Get all the sistemaAguaList where idSistemaAgua equals to UPDATED_ID_SISTEMA_AGUA
        defaultSistemaAguaShouldNotBeFound("idSistemaAgua.equals=" + UPDATED_ID_SISTEMA_AGUA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByIdSistemaAguaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where idSistemaAgua in DEFAULT_ID_SISTEMA_AGUA or UPDATED_ID_SISTEMA_AGUA
        defaultSistemaAguaShouldBeFound("idSistemaAgua.in=" + DEFAULT_ID_SISTEMA_AGUA + "," + UPDATED_ID_SISTEMA_AGUA);

        // Get all the sistemaAguaList where idSistemaAgua equals to UPDATED_ID_SISTEMA_AGUA
        defaultSistemaAguaShouldNotBeFound("idSistemaAgua.in=" + UPDATED_ID_SISTEMA_AGUA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByIdSistemaAguaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where idSistemaAgua is not null
        defaultSistemaAguaShouldBeFound("idSistemaAgua.specified=true");

        // Get all the sistemaAguaList where idSistemaAgua is null
        defaultSistemaAguaShouldNotBeFound("idSistemaAgua.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByIdSistemaAguaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where idSistemaAgua greater than or equals to DEFAULT_ID_SISTEMA_AGUA
        defaultSistemaAguaShouldBeFound("idSistemaAgua.greaterOrEqualThan=" + DEFAULT_ID_SISTEMA_AGUA);

        // Get all the sistemaAguaList where idSistemaAgua greater than or equals to UPDATED_ID_SISTEMA_AGUA
        defaultSistemaAguaShouldNotBeFound("idSistemaAgua.greaterOrEqualThan=" + UPDATED_ID_SISTEMA_AGUA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByIdSistemaAguaIsLessThanSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where idSistemaAgua less than or equals to DEFAULT_ID_SISTEMA_AGUA
        defaultSistemaAguaShouldNotBeFound("idSistemaAgua.lessThan=" + DEFAULT_ID_SISTEMA_AGUA);

        // Get all the sistemaAguaList where idSistemaAgua less than or equals to UPDATED_ID_SISTEMA_AGUA
        defaultSistemaAguaShouldBeFound("idSistemaAgua.lessThan=" + UPDATED_ID_SISTEMA_AGUA);
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByIdUsuarioIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where idUsuario equals to DEFAULT_ID_USUARIO
        defaultSistemaAguaShouldBeFound("idUsuario.equals=" + DEFAULT_ID_USUARIO);

        // Get all the sistemaAguaList where idUsuario equals to UPDATED_ID_USUARIO
        defaultSistemaAguaShouldNotBeFound("idUsuario.equals=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByIdUsuarioIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where idUsuario in DEFAULT_ID_USUARIO or UPDATED_ID_USUARIO
        defaultSistemaAguaShouldBeFound("idUsuario.in=" + DEFAULT_ID_USUARIO + "," + UPDATED_ID_USUARIO);

        // Get all the sistemaAguaList where idUsuario equals to UPDATED_ID_USUARIO
        defaultSistemaAguaShouldNotBeFound("idUsuario.in=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByIdUsuarioIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where idUsuario is not null
        defaultSistemaAguaShouldBeFound("idUsuario.specified=true");

        // Get all the sistemaAguaList where idUsuario is null
        defaultSistemaAguaShouldNotBeFound("idUsuario.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByIdUsuarioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where idUsuario greater than or equals to DEFAULT_ID_USUARIO
        defaultSistemaAguaShouldBeFound("idUsuario.greaterOrEqualThan=" + DEFAULT_ID_USUARIO);

        // Get all the sistemaAguaList where idUsuario greater than or equals to UPDATED_ID_USUARIO
        defaultSistemaAguaShouldNotBeFound("idUsuario.greaterOrEqualThan=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByIdUsuarioIsLessThanSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where idUsuario less than or equals to DEFAULT_ID_USUARIO
        defaultSistemaAguaShouldNotBeFound("idUsuario.lessThan=" + DEFAULT_ID_USUARIO);

        // Get all the sistemaAguaList where idUsuario less than or equals to UPDATED_ID_USUARIO
        defaultSistemaAguaShouldBeFound("idUsuario.lessThan=" + UPDATED_ID_USUARIO);
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByNmInqueridorIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmInqueridor equals to DEFAULT_NM_INQUERIDOR
        defaultSistemaAguaShouldBeFound("nmInqueridor.equals=" + DEFAULT_NM_INQUERIDOR);

        // Get all the sistemaAguaList where nmInqueridor equals to UPDATED_NM_INQUERIDOR
        defaultSistemaAguaShouldNotBeFound("nmInqueridor.equals=" + UPDATED_NM_INQUERIDOR);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmInqueridorIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmInqueridor in DEFAULT_NM_INQUERIDOR or UPDATED_NM_INQUERIDOR
        defaultSistemaAguaShouldBeFound("nmInqueridor.in=" + DEFAULT_NM_INQUERIDOR + "," + UPDATED_NM_INQUERIDOR);

        // Get all the sistemaAguaList where nmInqueridor equals to UPDATED_NM_INQUERIDOR
        defaultSistemaAguaShouldNotBeFound("nmInqueridor.in=" + UPDATED_NM_INQUERIDOR);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmInqueridorIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmInqueridor is not null
        defaultSistemaAguaShouldBeFound("nmInqueridor.specified=true");

        // Get all the sistemaAguaList where nmInqueridor is null
        defaultSistemaAguaShouldNotBeFound("nmInqueridor.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByDtLancamentoIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where dtLancamento equals to DEFAULT_DT_LANCAMENTO
        defaultSistemaAguaShouldBeFound("dtLancamento.equals=" + DEFAULT_DT_LANCAMENTO);

        // Get all the sistemaAguaList where dtLancamento equals to UPDATED_DT_LANCAMENTO
        defaultSistemaAguaShouldNotBeFound("dtLancamento.equals=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByDtLancamentoIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where dtLancamento in DEFAULT_DT_LANCAMENTO or UPDATED_DT_LANCAMENTO
        defaultSistemaAguaShouldBeFound("dtLancamento.in=" + DEFAULT_DT_LANCAMENTO + "," + UPDATED_DT_LANCAMENTO);

        // Get all the sistemaAguaList where dtLancamento equals to UPDATED_DT_LANCAMENTO
        defaultSistemaAguaShouldNotBeFound("dtLancamento.in=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByDtLancamentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where dtLancamento is not null
        defaultSistemaAguaShouldBeFound("dtLancamento.specified=true");

        // Get all the sistemaAguaList where dtLancamento is null
        defaultSistemaAguaShouldNotBeFound("dtLancamento.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByDtLancamentoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where dtLancamento greater than or equals to DEFAULT_DT_LANCAMENTO
        defaultSistemaAguaShouldBeFound("dtLancamento.greaterOrEqualThan=" + DEFAULT_DT_LANCAMENTO);

        // Get all the sistemaAguaList where dtLancamento greater than or equals to UPDATED_DT_LANCAMENTO
        defaultSistemaAguaShouldNotBeFound("dtLancamento.greaterOrEqualThan=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByDtLancamentoIsLessThanSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where dtLancamento less than or equals to DEFAULT_DT_LANCAMENTO
        defaultSistemaAguaShouldNotBeFound("dtLancamento.lessThan=" + DEFAULT_DT_LANCAMENTO);

        // Get all the sistemaAguaList where dtLancamento less than or equals to UPDATED_DT_LANCAMENTO
        defaultSistemaAguaShouldBeFound("dtLancamento.lessThan=" + UPDATED_DT_LANCAMENTO);
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByDtUltimaAlteracaoIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where dtUltimaAlteracao equals to DEFAULT_DT_ULTIMA_ALTERACAO
        defaultSistemaAguaShouldBeFound("dtUltimaAlteracao.equals=" + DEFAULT_DT_ULTIMA_ALTERACAO);

        // Get all the sistemaAguaList where dtUltimaAlteracao equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultSistemaAguaShouldNotBeFound("dtUltimaAlteracao.equals=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByDtUltimaAlteracaoIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where dtUltimaAlteracao in DEFAULT_DT_ULTIMA_ALTERACAO or UPDATED_DT_ULTIMA_ALTERACAO
        defaultSistemaAguaShouldBeFound("dtUltimaAlteracao.in=" + DEFAULT_DT_ULTIMA_ALTERACAO + "," + UPDATED_DT_ULTIMA_ALTERACAO);

        // Get all the sistemaAguaList where dtUltimaAlteracao equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultSistemaAguaShouldNotBeFound("dtUltimaAlteracao.in=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByDtUltimaAlteracaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where dtUltimaAlteracao is not null
        defaultSistemaAguaShouldBeFound("dtUltimaAlteracao.specified=true");

        // Get all the sistemaAguaList where dtUltimaAlteracao is null
        defaultSistemaAguaShouldNotBeFound("dtUltimaAlteracao.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByDtUltimaAlteracaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where dtUltimaAlteracao greater than or equals to DEFAULT_DT_ULTIMA_ALTERACAO
        defaultSistemaAguaShouldBeFound("dtUltimaAlteracao.greaterOrEqualThan=" + DEFAULT_DT_ULTIMA_ALTERACAO);

        // Get all the sistemaAguaList where dtUltimaAlteracao greater than or equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultSistemaAguaShouldNotBeFound("dtUltimaAlteracao.greaterOrEqualThan=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByDtUltimaAlteracaoIsLessThanSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where dtUltimaAlteracao less than or equals to DEFAULT_DT_ULTIMA_ALTERACAO
        defaultSistemaAguaShouldNotBeFound("dtUltimaAlteracao.lessThan=" + DEFAULT_DT_ULTIMA_ALTERACAO);

        // Get all the sistemaAguaList where dtUltimaAlteracao less than or equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultSistemaAguaShouldBeFound("dtUltimaAlteracao.lessThan=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByNmLocalidadeIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmLocalidade equals to DEFAULT_NM_LOCALIDADE
        defaultSistemaAguaShouldBeFound("nmLocalidade.equals=" + DEFAULT_NM_LOCALIDADE);

        // Get all the sistemaAguaList where nmLocalidade equals to UPDATED_NM_LOCALIDADE
        defaultSistemaAguaShouldNotBeFound("nmLocalidade.equals=" + UPDATED_NM_LOCALIDADE);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmLocalidadeIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmLocalidade in DEFAULT_NM_LOCALIDADE or UPDATED_NM_LOCALIDADE
        defaultSistemaAguaShouldBeFound("nmLocalidade.in=" + DEFAULT_NM_LOCALIDADE + "," + UPDATED_NM_LOCALIDADE);

        // Get all the sistemaAguaList where nmLocalidade equals to UPDATED_NM_LOCALIDADE
        defaultSistemaAguaShouldNotBeFound("nmLocalidade.in=" + UPDATED_NM_LOCALIDADE);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmLocalidadeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmLocalidade is not null
        defaultSistemaAguaShouldBeFound("nmLocalidade.specified=true");

        // Get all the sistemaAguaList where nmLocalidade is null
        defaultSistemaAguaShouldNotBeFound("nmLocalidade.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdPopulacaoActualIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdPopulacaoActual equals to DEFAULT_QTD_POPULACAO_ACTUAL
        defaultSistemaAguaShouldBeFound("qtdPopulacaoActual.equals=" + DEFAULT_QTD_POPULACAO_ACTUAL);

        // Get all the sistemaAguaList where qtdPopulacaoActual equals to UPDATED_QTD_POPULACAO_ACTUAL
        defaultSistemaAguaShouldNotBeFound("qtdPopulacaoActual.equals=" + UPDATED_QTD_POPULACAO_ACTUAL);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdPopulacaoActualIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdPopulacaoActual in DEFAULT_QTD_POPULACAO_ACTUAL or UPDATED_QTD_POPULACAO_ACTUAL
        defaultSistemaAguaShouldBeFound("qtdPopulacaoActual.in=" + DEFAULT_QTD_POPULACAO_ACTUAL + "," + UPDATED_QTD_POPULACAO_ACTUAL);

        // Get all the sistemaAguaList where qtdPopulacaoActual equals to UPDATED_QTD_POPULACAO_ACTUAL
        defaultSistemaAguaShouldNotBeFound("qtdPopulacaoActual.in=" + UPDATED_QTD_POPULACAO_ACTUAL);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdPopulacaoActualIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdPopulacaoActual is not null
        defaultSistemaAguaShouldBeFound("qtdPopulacaoActual.specified=true");

        // Get all the sistemaAguaList where qtdPopulacaoActual is null
        defaultSistemaAguaShouldNotBeFound("qtdPopulacaoActual.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdPopulacaoActualIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdPopulacaoActual greater than or equals to DEFAULT_QTD_POPULACAO_ACTUAL
        defaultSistemaAguaShouldBeFound("qtdPopulacaoActual.greaterOrEqualThan=" + DEFAULT_QTD_POPULACAO_ACTUAL);

        // Get all the sistemaAguaList where qtdPopulacaoActual greater than or equals to UPDATED_QTD_POPULACAO_ACTUAL
        defaultSistemaAguaShouldNotBeFound("qtdPopulacaoActual.greaterOrEqualThan=" + UPDATED_QTD_POPULACAO_ACTUAL);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdPopulacaoActualIsLessThanSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdPopulacaoActual less than or equals to DEFAULT_QTD_POPULACAO_ACTUAL
        defaultSistemaAguaShouldNotBeFound("qtdPopulacaoActual.lessThan=" + DEFAULT_QTD_POPULACAO_ACTUAL);

        // Get all the sistemaAguaList where qtdPopulacaoActual less than or equals to UPDATED_QTD_POPULACAO_ACTUAL
        defaultSistemaAguaShouldBeFound("qtdPopulacaoActual.lessThan=" + UPDATED_QTD_POPULACAO_ACTUAL);
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByQtdCasasLocalidadeIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdCasasLocalidade equals to DEFAULT_QTD_CASAS_LOCALIDADE
        defaultSistemaAguaShouldBeFound("qtdCasasLocalidade.equals=" + DEFAULT_QTD_CASAS_LOCALIDADE);

        // Get all the sistemaAguaList where qtdCasasLocalidade equals to UPDATED_QTD_CASAS_LOCALIDADE
        defaultSistemaAguaShouldNotBeFound("qtdCasasLocalidade.equals=" + UPDATED_QTD_CASAS_LOCALIDADE);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdCasasLocalidadeIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdCasasLocalidade in DEFAULT_QTD_CASAS_LOCALIDADE or UPDATED_QTD_CASAS_LOCALIDADE
        defaultSistemaAguaShouldBeFound("qtdCasasLocalidade.in=" + DEFAULT_QTD_CASAS_LOCALIDADE + "," + UPDATED_QTD_CASAS_LOCALIDADE);

        // Get all the sistemaAguaList where qtdCasasLocalidade equals to UPDATED_QTD_CASAS_LOCALIDADE
        defaultSistemaAguaShouldNotBeFound("qtdCasasLocalidade.in=" + UPDATED_QTD_CASAS_LOCALIDADE);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdCasasLocalidadeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdCasasLocalidade is not null
        defaultSistemaAguaShouldBeFound("qtdCasasLocalidade.specified=true");

        // Get all the sistemaAguaList where qtdCasasLocalidade is null
        defaultSistemaAguaShouldNotBeFound("qtdCasasLocalidade.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdCasasLocalidadeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdCasasLocalidade greater than or equals to DEFAULT_QTD_CASAS_LOCALIDADE
        defaultSistemaAguaShouldBeFound("qtdCasasLocalidade.greaterOrEqualThan=" + DEFAULT_QTD_CASAS_LOCALIDADE);

        // Get all the sistemaAguaList where qtdCasasLocalidade greater than or equals to UPDATED_QTD_CASAS_LOCALIDADE
        defaultSistemaAguaShouldNotBeFound("qtdCasasLocalidade.greaterOrEqualThan=" + UPDATED_QTD_CASAS_LOCALIDADE);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdCasasLocalidadeIsLessThanSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdCasasLocalidade less than or equals to DEFAULT_QTD_CASAS_LOCALIDADE
        defaultSistemaAguaShouldNotBeFound("qtdCasasLocalidade.lessThan=" + DEFAULT_QTD_CASAS_LOCALIDADE);

        // Get all the sistemaAguaList where qtdCasasLocalidade less than or equals to UPDATED_QTD_CASAS_LOCALIDADE
        defaultSistemaAguaShouldBeFound("qtdCasasLocalidade.lessThan=" + UPDATED_QTD_CASAS_LOCALIDADE);
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpComunaAldeiaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpComunaAldeia equals to DEFAULT_NM_TP_COMUNA_ALDEIA
        defaultSistemaAguaShouldBeFound("nmTpComunaAldeia.equals=" + DEFAULT_NM_TP_COMUNA_ALDEIA);

        // Get all the sistemaAguaList where nmTpComunaAldeia equals to UPDATED_NM_TP_COMUNA_ALDEIA
        defaultSistemaAguaShouldNotBeFound("nmTpComunaAldeia.equals=" + UPDATED_NM_TP_COMUNA_ALDEIA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpComunaAldeiaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpComunaAldeia in DEFAULT_NM_TP_COMUNA_ALDEIA or UPDATED_NM_TP_COMUNA_ALDEIA
        defaultSistemaAguaShouldBeFound("nmTpComunaAldeia.in=" + DEFAULT_NM_TP_COMUNA_ALDEIA + "," + UPDATED_NM_TP_COMUNA_ALDEIA);

        // Get all the sistemaAguaList where nmTpComunaAldeia equals to UPDATED_NM_TP_COMUNA_ALDEIA
        defaultSistemaAguaShouldNotBeFound("nmTpComunaAldeia.in=" + UPDATED_NM_TP_COMUNA_ALDEIA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpComunaAldeiaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpComunaAldeia is not null
        defaultSistemaAguaShouldBeFound("nmTpComunaAldeia.specified=true");

        // Get all the sistemaAguaList where nmTpComunaAldeia is null
        defaultSistemaAguaShouldNotBeFound("nmTpComunaAldeia.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpAreaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpArea equals to DEFAULT_NM_TP_AREA
        defaultSistemaAguaShouldBeFound("nmTpArea.equals=" + DEFAULT_NM_TP_AREA);

        // Get all the sistemaAguaList where nmTpArea equals to UPDATED_NM_TP_AREA
        defaultSistemaAguaShouldNotBeFound("nmTpArea.equals=" + UPDATED_NM_TP_AREA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpAreaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpArea in DEFAULT_NM_TP_AREA or UPDATED_NM_TP_AREA
        defaultSistemaAguaShouldBeFound("nmTpArea.in=" + DEFAULT_NM_TP_AREA + "," + UPDATED_NM_TP_AREA);

        // Get all the sistemaAguaList where nmTpArea equals to UPDATED_NM_TP_AREA
        defaultSistemaAguaShouldNotBeFound("nmTpArea.in=" + UPDATED_NM_TP_AREA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpAreaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpArea is not null
        defaultSistemaAguaShouldBeFound("nmTpArea.specified=true");

        // Get all the sistemaAguaList where nmTpArea is null
        defaultSistemaAguaShouldNotBeFound("nmTpArea.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByPossuiSistemaAguaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where possuiSistemaAgua equals to DEFAULT_POSSUI_SISTEMA_AGUA
        defaultSistemaAguaShouldBeFound("possuiSistemaAgua.equals=" + DEFAULT_POSSUI_SISTEMA_AGUA);

        // Get all the sistemaAguaList where possuiSistemaAgua equals to UPDATED_POSSUI_SISTEMA_AGUA
        defaultSistemaAguaShouldNotBeFound("possuiSistemaAgua.equals=" + UPDATED_POSSUI_SISTEMA_AGUA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByPossuiSistemaAguaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where possuiSistemaAgua in DEFAULT_POSSUI_SISTEMA_AGUA or UPDATED_POSSUI_SISTEMA_AGUA
        defaultSistemaAguaShouldBeFound("possuiSistemaAgua.in=" + DEFAULT_POSSUI_SISTEMA_AGUA + "," + UPDATED_POSSUI_SISTEMA_AGUA);

        // Get all the sistemaAguaList where possuiSistemaAgua equals to UPDATED_POSSUI_SISTEMA_AGUA
        defaultSistemaAguaShouldNotBeFound("possuiSistemaAgua.in=" + UPDATED_POSSUI_SISTEMA_AGUA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByPossuiSistemaAguaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where possuiSistemaAgua is not null
        defaultSistemaAguaShouldBeFound("possuiSistemaAgua.specified=true");

        // Get all the sistemaAguaList where possuiSistemaAgua is null
        defaultSistemaAguaShouldNotBeFound("possuiSistemaAgua.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByPossuiSistemaAguaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where possuiSistemaAgua greater than or equals to DEFAULT_POSSUI_SISTEMA_AGUA
        defaultSistemaAguaShouldBeFound("possuiSistemaAgua.greaterOrEqualThan=" + DEFAULT_POSSUI_SISTEMA_AGUA);

        // Get all the sistemaAguaList where possuiSistemaAgua greater than or equals to UPDATED_POSSUI_SISTEMA_AGUA
        defaultSistemaAguaShouldNotBeFound("possuiSistemaAgua.greaterOrEqualThan=" + UPDATED_POSSUI_SISTEMA_AGUA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByPossuiSistemaAguaIsLessThanSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where possuiSistemaAgua less than or equals to DEFAULT_POSSUI_SISTEMA_AGUA
        defaultSistemaAguaShouldNotBeFound("possuiSistemaAgua.lessThan=" + DEFAULT_POSSUI_SISTEMA_AGUA);

        // Get all the sistemaAguaList where possuiSistemaAgua less than or equals to UPDATED_POSSUI_SISTEMA_AGUA
        defaultSistemaAguaShouldBeFound("possuiSistemaAgua.lessThan=" + UPDATED_POSSUI_SISTEMA_AGUA);
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByNmSistemaAguaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmSistemaAgua equals to DEFAULT_NM_SISTEMA_AGUA
        defaultSistemaAguaShouldBeFound("nmSistemaAgua.equals=" + DEFAULT_NM_SISTEMA_AGUA);

        // Get all the sistemaAguaList where nmSistemaAgua equals to UPDATED_NM_SISTEMA_AGUA
        defaultSistemaAguaShouldNotBeFound("nmSistemaAgua.equals=" + UPDATED_NM_SISTEMA_AGUA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmSistemaAguaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmSistemaAgua in DEFAULT_NM_SISTEMA_AGUA or UPDATED_NM_SISTEMA_AGUA
        defaultSistemaAguaShouldBeFound("nmSistemaAgua.in=" + DEFAULT_NM_SISTEMA_AGUA + "," + UPDATED_NM_SISTEMA_AGUA);

        // Get all the sistemaAguaList where nmSistemaAgua equals to UPDATED_NM_SISTEMA_AGUA
        defaultSistemaAguaShouldNotBeFound("nmSistemaAgua.in=" + UPDATED_NM_SISTEMA_AGUA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmSistemaAguaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmSistemaAgua is not null
        defaultSistemaAguaShouldBeFound("nmSistemaAgua.specified=true");

        // Get all the sistemaAguaList where nmSistemaAgua is null
        defaultSistemaAguaShouldNotBeFound("nmSistemaAgua.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmFonteAguaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmFonteAgua equals to DEFAULT_NM_FONTE_AGUA
        defaultSistemaAguaShouldBeFound("nmFonteAgua.equals=" + DEFAULT_NM_FONTE_AGUA);

        // Get all the sistemaAguaList where nmFonteAgua equals to UPDATED_NM_FONTE_AGUA
        defaultSistemaAguaShouldNotBeFound("nmFonteAgua.equals=" + UPDATED_NM_FONTE_AGUA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmFonteAguaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmFonteAgua in DEFAULT_NM_FONTE_AGUA or UPDATED_NM_FONTE_AGUA
        defaultSistemaAguaShouldBeFound("nmFonteAgua.in=" + DEFAULT_NM_FONTE_AGUA + "," + UPDATED_NM_FONTE_AGUA);

        // Get all the sistemaAguaList where nmFonteAgua equals to UPDATED_NM_FONTE_AGUA
        defaultSistemaAguaShouldNotBeFound("nmFonteAgua.in=" + UPDATED_NM_FONTE_AGUA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmFonteAguaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmFonteAgua is not null
        defaultSistemaAguaShouldBeFound("nmFonteAgua.specified=true");

        // Get all the sistemaAguaList where nmFonteAgua is null
        defaultSistemaAguaShouldNotBeFound("nmFonteAgua.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByLatitudeIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where latitude equals to DEFAULT_LATITUDE
        defaultSistemaAguaShouldBeFound("latitude.equals=" + DEFAULT_LATITUDE);

        // Get all the sistemaAguaList where latitude equals to UPDATED_LATITUDE
        defaultSistemaAguaShouldNotBeFound("latitude.equals=" + UPDATED_LATITUDE);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByLatitudeIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where latitude in DEFAULT_LATITUDE or UPDATED_LATITUDE
        defaultSistemaAguaShouldBeFound("latitude.in=" + DEFAULT_LATITUDE + "," + UPDATED_LATITUDE);

        // Get all the sistemaAguaList where latitude equals to UPDATED_LATITUDE
        defaultSistemaAguaShouldNotBeFound("latitude.in=" + UPDATED_LATITUDE);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByLatitudeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where latitude is not null
        defaultSistemaAguaShouldBeFound("latitude.specified=true");

        // Get all the sistemaAguaList where latitude is null
        defaultSistemaAguaShouldNotBeFound("latitude.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByLongitudeIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where longitude equals to DEFAULT_LONGITUDE
        defaultSistemaAguaShouldBeFound("longitude.equals=" + DEFAULT_LONGITUDE);

        // Get all the sistemaAguaList where longitude equals to UPDATED_LONGITUDE
        defaultSistemaAguaShouldNotBeFound("longitude.equals=" + UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByLongitudeIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where longitude in DEFAULT_LONGITUDE or UPDATED_LONGITUDE
        defaultSistemaAguaShouldBeFound("longitude.in=" + DEFAULT_LONGITUDE + "," + UPDATED_LONGITUDE);

        // Get all the sistemaAguaList where longitude equals to UPDATED_LONGITUDE
        defaultSistemaAguaShouldNotBeFound("longitude.in=" + UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByLongitudeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where longitude is not null
        defaultSistemaAguaShouldBeFound("longitude.specified=true");

        // Get all the sistemaAguaList where longitude is null
        defaultSistemaAguaShouldNotBeFound("longitude.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByAltitudeIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where altitude equals to DEFAULT_ALTITUDE
        defaultSistemaAguaShouldBeFound("altitude.equals=" + DEFAULT_ALTITUDE);

        // Get all the sistemaAguaList where altitude equals to UPDATED_ALTITUDE
        defaultSistemaAguaShouldNotBeFound("altitude.equals=" + UPDATED_ALTITUDE);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByAltitudeIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where altitude in DEFAULT_ALTITUDE or UPDATED_ALTITUDE
        defaultSistemaAguaShouldBeFound("altitude.in=" + DEFAULT_ALTITUDE + "," + UPDATED_ALTITUDE);

        // Get all the sistemaAguaList where altitude equals to UPDATED_ALTITUDE
        defaultSistemaAguaShouldNotBeFound("altitude.in=" + UPDATED_ALTITUDE);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByAltitudeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where altitude is not null
        defaultSistemaAguaShouldBeFound("altitude.specified=true");

        // Get all the sistemaAguaList where altitude is null
        defaultSistemaAguaShouldNotBeFound("altitude.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpFonteIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpFonte equals to DEFAULT_NM_TP_FONTE
        defaultSistemaAguaShouldBeFound("nmTpFonte.equals=" + DEFAULT_NM_TP_FONTE);

        // Get all the sistemaAguaList where nmTpFonte equals to UPDATED_NM_TP_FONTE
        defaultSistemaAguaShouldNotBeFound("nmTpFonte.equals=" + UPDATED_NM_TP_FONTE);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpFonteIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpFonte in DEFAULT_NM_TP_FONTE or UPDATED_NM_TP_FONTE
        defaultSistemaAguaShouldBeFound("nmTpFonte.in=" + DEFAULT_NM_TP_FONTE + "," + UPDATED_NM_TP_FONTE);

        // Get all the sistemaAguaList where nmTpFonte equals to UPDATED_NM_TP_FONTE
        defaultSistemaAguaShouldNotBeFound("nmTpFonte.in=" + UPDATED_NM_TP_FONTE);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpFonteIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpFonte is not null
        defaultSistemaAguaShouldBeFound("nmTpFonte.specified=true");

        // Get all the sistemaAguaList where nmTpFonte is null
        defaultSistemaAguaShouldNotBeFound("nmTpFonte.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmFonteAguaUtilizadaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmFonteAguaUtilizada equals to DEFAULT_NM_FONTE_AGUA_UTILIZADA
        defaultSistemaAguaShouldBeFound("nmFonteAguaUtilizada.equals=" + DEFAULT_NM_FONTE_AGUA_UTILIZADA);

        // Get all the sistemaAguaList where nmFonteAguaUtilizada equals to UPDATED_NM_FONTE_AGUA_UTILIZADA
        defaultSistemaAguaShouldNotBeFound("nmFonteAguaUtilizada.equals=" + UPDATED_NM_FONTE_AGUA_UTILIZADA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmFonteAguaUtilizadaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmFonteAguaUtilizada in DEFAULT_NM_FONTE_AGUA_UTILIZADA or UPDATED_NM_FONTE_AGUA_UTILIZADA
        defaultSistemaAguaShouldBeFound("nmFonteAguaUtilizada.in=" + DEFAULT_NM_FONTE_AGUA_UTILIZADA + "," + UPDATED_NM_FONTE_AGUA_UTILIZADA);

        // Get all the sistemaAguaList where nmFonteAguaUtilizada equals to UPDATED_NM_FONTE_AGUA_UTILIZADA
        defaultSistemaAguaShouldNotBeFound("nmFonteAguaUtilizada.in=" + UPDATED_NM_FONTE_AGUA_UTILIZADA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmFonteAguaUtilizadaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmFonteAguaUtilizada is not null
        defaultSistemaAguaShouldBeFound("nmFonteAguaUtilizada.specified=true");

        // Get all the sistemaAguaList where nmFonteAguaUtilizada is null
        defaultSistemaAguaShouldNotBeFound("nmFonteAguaUtilizada.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTipoBombaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTipoBomba equals to DEFAULT_NM_TIPO_BOMBA
        defaultSistemaAguaShouldBeFound("nmTipoBomba.equals=" + DEFAULT_NM_TIPO_BOMBA);

        // Get all the sistemaAguaList where nmTipoBomba equals to UPDATED_NM_TIPO_BOMBA
        defaultSistemaAguaShouldNotBeFound("nmTipoBomba.equals=" + UPDATED_NM_TIPO_BOMBA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTipoBombaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTipoBomba in DEFAULT_NM_TIPO_BOMBA or UPDATED_NM_TIPO_BOMBA
        defaultSistemaAguaShouldBeFound("nmTipoBomba.in=" + DEFAULT_NM_TIPO_BOMBA + "," + UPDATED_NM_TIPO_BOMBA);

        // Get all the sistemaAguaList where nmTipoBomba equals to UPDATED_NM_TIPO_BOMBA
        defaultSistemaAguaShouldNotBeFound("nmTipoBomba.in=" + UPDATED_NM_TIPO_BOMBA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTipoBombaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTipoBomba is not null
        defaultSistemaAguaShouldBeFound("nmTipoBomba.specified=true");

        // Get all the sistemaAguaList where nmTipoBomba is null
        defaultSistemaAguaShouldNotBeFound("nmTipoBomba.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdCasasAguaLigadaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdCasasAguaLigada equals to DEFAULT_QTD_CASAS_AGUA_LIGADA
        defaultSistemaAguaShouldBeFound("qtdCasasAguaLigada.equals=" + DEFAULT_QTD_CASAS_AGUA_LIGADA);

        // Get all the sistemaAguaList where qtdCasasAguaLigada equals to UPDATED_QTD_CASAS_AGUA_LIGADA
        defaultSistemaAguaShouldNotBeFound("qtdCasasAguaLigada.equals=" + UPDATED_QTD_CASAS_AGUA_LIGADA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdCasasAguaLigadaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdCasasAguaLigada in DEFAULT_QTD_CASAS_AGUA_LIGADA or UPDATED_QTD_CASAS_AGUA_LIGADA
        defaultSistemaAguaShouldBeFound("qtdCasasAguaLigada.in=" + DEFAULT_QTD_CASAS_AGUA_LIGADA + "," + UPDATED_QTD_CASAS_AGUA_LIGADA);

        // Get all the sistemaAguaList where qtdCasasAguaLigada equals to UPDATED_QTD_CASAS_AGUA_LIGADA
        defaultSistemaAguaShouldNotBeFound("qtdCasasAguaLigada.in=" + UPDATED_QTD_CASAS_AGUA_LIGADA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdCasasAguaLigadaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdCasasAguaLigada is not null
        defaultSistemaAguaShouldBeFound("qtdCasasAguaLigada.specified=true");

        // Get all the sistemaAguaList where qtdCasasAguaLigada is null
        defaultSistemaAguaShouldNotBeFound("qtdCasasAguaLigada.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdCasasAguaLigadaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdCasasAguaLigada greater than or equals to DEFAULT_QTD_CASAS_AGUA_LIGADA
        defaultSistemaAguaShouldBeFound("qtdCasasAguaLigada.greaterOrEqualThan=" + DEFAULT_QTD_CASAS_AGUA_LIGADA);

        // Get all the sistemaAguaList where qtdCasasAguaLigada greater than or equals to UPDATED_QTD_CASAS_AGUA_LIGADA
        defaultSistemaAguaShouldNotBeFound("qtdCasasAguaLigada.greaterOrEqualThan=" + UPDATED_QTD_CASAS_AGUA_LIGADA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdCasasAguaLigadaIsLessThanSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdCasasAguaLigada less than or equals to DEFAULT_QTD_CASAS_AGUA_LIGADA
        defaultSistemaAguaShouldNotBeFound("qtdCasasAguaLigada.lessThan=" + DEFAULT_QTD_CASAS_AGUA_LIGADA);

        // Get all the sistemaAguaList where qtdCasasAguaLigada less than or equals to UPDATED_QTD_CASAS_AGUA_LIGADA
        defaultSistemaAguaShouldBeFound("qtdCasasAguaLigada.lessThan=" + UPDATED_QTD_CASAS_AGUA_LIGADA);
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByQtdChafarisesFuncionandoIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdChafarisesFuncionando equals to DEFAULT_QTD_CHAFARISES_FUNCIONANDO
        defaultSistemaAguaShouldBeFound("qtdChafarisesFuncionando.equals=" + DEFAULT_QTD_CHAFARISES_FUNCIONANDO);

        // Get all the sistemaAguaList where qtdChafarisesFuncionando equals to UPDATED_QTD_CHAFARISES_FUNCIONANDO
        defaultSistemaAguaShouldNotBeFound("qtdChafarisesFuncionando.equals=" + UPDATED_QTD_CHAFARISES_FUNCIONANDO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdChafarisesFuncionandoIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdChafarisesFuncionando in DEFAULT_QTD_CHAFARISES_FUNCIONANDO or UPDATED_QTD_CHAFARISES_FUNCIONANDO
        defaultSistemaAguaShouldBeFound("qtdChafarisesFuncionando.in=" + DEFAULT_QTD_CHAFARISES_FUNCIONANDO + "," + UPDATED_QTD_CHAFARISES_FUNCIONANDO);

        // Get all the sistemaAguaList where qtdChafarisesFuncionando equals to UPDATED_QTD_CHAFARISES_FUNCIONANDO
        defaultSistemaAguaShouldNotBeFound("qtdChafarisesFuncionando.in=" + UPDATED_QTD_CHAFARISES_FUNCIONANDO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdChafarisesFuncionandoIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdChafarisesFuncionando is not null
        defaultSistemaAguaShouldBeFound("qtdChafarisesFuncionando.specified=true");

        // Get all the sistemaAguaList where qtdChafarisesFuncionando is null
        defaultSistemaAguaShouldNotBeFound("qtdChafarisesFuncionando.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdChafarisesFuncionandoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdChafarisesFuncionando greater than or equals to DEFAULT_QTD_CHAFARISES_FUNCIONANDO
        defaultSistemaAguaShouldBeFound("qtdChafarisesFuncionando.greaterOrEqualThan=" + DEFAULT_QTD_CHAFARISES_FUNCIONANDO);

        // Get all the sistemaAguaList where qtdChafarisesFuncionando greater than or equals to UPDATED_QTD_CHAFARISES_FUNCIONANDO
        defaultSistemaAguaShouldNotBeFound("qtdChafarisesFuncionando.greaterOrEqualThan=" + UPDATED_QTD_CHAFARISES_FUNCIONANDO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdChafarisesFuncionandoIsLessThanSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdChafarisesFuncionando less than or equals to DEFAULT_QTD_CHAFARISES_FUNCIONANDO
        defaultSistemaAguaShouldNotBeFound("qtdChafarisesFuncionando.lessThan=" + DEFAULT_QTD_CHAFARISES_FUNCIONANDO);

        // Get all the sistemaAguaList where qtdChafarisesFuncionando less than or equals to UPDATED_QTD_CHAFARISES_FUNCIONANDO
        defaultSistemaAguaShouldBeFound("qtdChafarisesFuncionando.lessThan=" + UPDATED_QTD_CHAFARISES_FUNCIONANDO);
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByQtdContadoresLigadosIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdContadoresLigados equals to DEFAULT_QTD_CONTADORES_LIGADOS
        defaultSistemaAguaShouldBeFound("qtdContadoresLigados.equals=" + DEFAULT_QTD_CONTADORES_LIGADOS);

        // Get all the sistemaAguaList where qtdContadoresLigados equals to UPDATED_QTD_CONTADORES_LIGADOS
        defaultSistemaAguaShouldNotBeFound("qtdContadoresLigados.equals=" + UPDATED_QTD_CONTADORES_LIGADOS);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdContadoresLigadosIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdContadoresLigados in DEFAULT_QTD_CONTADORES_LIGADOS or UPDATED_QTD_CONTADORES_LIGADOS
        defaultSistemaAguaShouldBeFound("qtdContadoresLigados.in=" + DEFAULT_QTD_CONTADORES_LIGADOS + "," + UPDATED_QTD_CONTADORES_LIGADOS);

        // Get all the sistemaAguaList where qtdContadoresLigados equals to UPDATED_QTD_CONTADORES_LIGADOS
        defaultSistemaAguaShouldNotBeFound("qtdContadoresLigados.in=" + UPDATED_QTD_CONTADORES_LIGADOS);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdContadoresLigadosIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdContadoresLigados is not null
        defaultSistemaAguaShouldBeFound("qtdContadoresLigados.specified=true");

        // Get all the sistemaAguaList where qtdContadoresLigados is null
        defaultSistemaAguaShouldNotBeFound("qtdContadoresLigados.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdContadoresLigadosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdContadoresLigados greater than or equals to DEFAULT_QTD_CONTADORES_LIGADOS
        defaultSistemaAguaShouldBeFound("qtdContadoresLigados.greaterOrEqualThan=" + DEFAULT_QTD_CONTADORES_LIGADOS);

        // Get all the sistemaAguaList where qtdContadoresLigados greater than or equals to UPDATED_QTD_CONTADORES_LIGADOS
        defaultSistemaAguaShouldNotBeFound("qtdContadoresLigados.greaterOrEqualThan=" + UPDATED_QTD_CONTADORES_LIGADOS);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdContadoresLigadosIsLessThanSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdContadoresLigados less than or equals to DEFAULT_QTD_CONTADORES_LIGADOS
        defaultSistemaAguaShouldNotBeFound("qtdContadoresLigados.lessThan=" + DEFAULT_QTD_CONTADORES_LIGADOS);

        // Get all the sistemaAguaList where qtdContadoresLigados less than or equals to UPDATED_QTD_CONTADORES_LIGADOS
        defaultSistemaAguaShouldBeFound("qtdContadoresLigados.lessThan=" + UPDATED_QTD_CONTADORES_LIGADOS);
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByQtdBebedourosIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdBebedouros equals to DEFAULT_QTD_BEBEDOUROS
        defaultSistemaAguaShouldBeFound("qtdBebedouros.equals=" + DEFAULT_QTD_BEBEDOUROS);

        // Get all the sistemaAguaList where qtdBebedouros equals to UPDATED_QTD_BEBEDOUROS
        defaultSistemaAguaShouldNotBeFound("qtdBebedouros.equals=" + UPDATED_QTD_BEBEDOUROS);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdBebedourosIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdBebedouros in DEFAULT_QTD_BEBEDOUROS or UPDATED_QTD_BEBEDOUROS
        defaultSistemaAguaShouldBeFound("qtdBebedouros.in=" + DEFAULT_QTD_BEBEDOUROS + "," + UPDATED_QTD_BEBEDOUROS);

        // Get all the sistemaAguaList where qtdBebedouros equals to UPDATED_QTD_BEBEDOUROS
        defaultSistemaAguaShouldNotBeFound("qtdBebedouros.in=" + UPDATED_QTD_BEBEDOUROS);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdBebedourosIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdBebedouros is not null
        defaultSistemaAguaShouldBeFound("qtdBebedouros.specified=true");

        // Get all the sistemaAguaList where qtdBebedouros is null
        defaultSistemaAguaShouldNotBeFound("qtdBebedouros.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdBebedourosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdBebedouros greater than or equals to DEFAULT_QTD_BEBEDOUROS
        defaultSistemaAguaShouldBeFound("qtdBebedouros.greaterOrEqualThan=" + DEFAULT_QTD_BEBEDOUROS);

        // Get all the sistemaAguaList where qtdBebedouros greater than or equals to UPDATED_QTD_BEBEDOUROS
        defaultSistemaAguaShouldNotBeFound("qtdBebedouros.greaterOrEqualThan=" + UPDATED_QTD_BEBEDOUROS);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdBebedourosIsLessThanSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdBebedouros less than or equals to DEFAULT_QTD_BEBEDOUROS
        defaultSistemaAguaShouldNotBeFound("qtdBebedouros.lessThan=" + DEFAULT_QTD_BEBEDOUROS);

        // Get all the sistemaAguaList where qtdBebedouros less than or equals to UPDATED_QTD_BEBEDOUROS
        defaultSistemaAguaShouldBeFound("qtdBebedouros.lessThan=" + UPDATED_QTD_BEBEDOUROS);
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByQtdHabitantesAcessoServicoAguaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdHabitantesAcessoServicoAgua equals to DEFAULT_QTD_HABITANTES_ACESSO_SERVICO_AGUA
        defaultSistemaAguaShouldBeFound("qtdHabitantesAcessoServicoAgua.equals=" + DEFAULT_QTD_HABITANTES_ACESSO_SERVICO_AGUA);

        // Get all the sistemaAguaList where qtdHabitantesAcessoServicoAgua equals to UPDATED_QTD_HABITANTES_ACESSO_SERVICO_AGUA
        defaultSistemaAguaShouldNotBeFound("qtdHabitantesAcessoServicoAgua.equals=" + UPDATED_QTD_HABITANTES_ACESSO_SERVICO_AGUA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdHabitantesAcessoServicoAguaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdHabitantesAcessoServicoAgua in DEFAULT_QTD_HABITANTES_ACESSO_SERVICO_AGUA or UPDATED_QTD_HABITANTES_ACESSO_SERVICO_AGUA
        defaultSistemaAguaShouldBeFound("qtdHabitantesAcessoServicoAgua.in=" + DEFAULT_QTD_HABITANTES_ACESSO_SERVICO_AGUA + "," + UPDATED_QTD_HABITANTES_ACESSO_SERVICO_AGUA);

        // Get all the sistemaAguaList where qtdHabitantesAcessoServicoAgua equals to UPDATED_QTD_HABITANTES_ACESSO_SERVICO_AGUA
        defaultSistemaAguaShouldNotBeFound("qtdHabitantesAcessoServicoAgua.in=" + UPDATED_QTD_HABITANTES_ACESSO_SERVICO_AGUA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdHabitantesAcessoServicoAguaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdHabitantesAcessoServicoAgua is not null
        defaultSistemaAguaShouldBeFound("qtdHabitantesAcessoServicoAgua.specified=true");

        // Get all the sistemaAguaList where qtdHabitantesAcessoServicoAgua is null
        defaultSistemaAguaShouldNotBeFound("qtdHabitantesAcessoServicoAgua.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdHabitantesAcessoServicoAguaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdHabitantesAcessoServicoAgua greater than or equals to DEFAULT_QTD_HABITANTES_ACESSO_SERVICO_AGUA
        defaultSistemaAguaShouldBeFound("qtdHabitantesAcessoServicoAgua.greaterOrEqualThan=" + DEFAULT_QTD_HABITANTES_ACESSO_SERVICO_AGUA);

        // Get all the sistemaAguaList where qtdHabitantesAcessoServicoAgua greater than or equals to UPDATED_QTD_HABITANTES_ACESSO_SERVICO_AGUA
        defaultSistemaAguaShouldNotBeFound("qtdHabitantesAcessoServicoAgua.greaterOrEqualThan=" + UPDATED_QTD_HABITANTES_ACESSO_SERVICO_AGUA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdHabitantesAcessoServicoAguaIsLessThanSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdHabitantesAcessoServicoAgua less than or equals to DEFAULT_QTD_HABITANTES_ACESSO_SERVICO_AGUA
        defaultSistemaAguaShouldNotBeFound("qtdHabitantesAcessoServicoAgua.lessThan=" + DEFAULT_QTD_HABITANTES_ACESSO_SERVICO_AGUA);

        // Get all the sistemaAguaList where qtdHabitantesAcessoServicoAgua less than or equals to UPDATED_QTD_HABITANTES_ACESSO_SERVICO_AGUA
        defaultSistemaAguaShouldBeFound("qtdHabitantesAcessoServicoAgua.lessThan=" + UPDATED_QTD_HABITANTES_ACESSO_SERVICO_AGUA);
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByAnoConstrucaoSistemaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where anoConstrucaoSistema equals to DEFAULT_ANO_CONSTRUCAO_SISTEMA
        defaultSistemaAguaShouldBeFound("anoConstrucaoSistema.equals=" + DEFAULT_ANO_CONSTRUCAO_SISTEMA);

        // Get all the sistemaAguaList where anoConstrucaoSistema equals to UPDATED_ANO_CONSTRUCAO_SISTEMA
        defaultSistemaAguaShouldNotBeFound("anoConstrucaoSistema.equals=" + UPDATED_ANO_CONSTRUCAO_SISTEMA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByAnoConstrucaoSistemaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where anoConstrucaoSistema in DEFAULT_ANO_CONSTRUCAO_SISTEMA or UPDATED_ANO_CONSTRUCAO_SISTEMA
        defaultSistemaAguaShouldBeFound("anoConstrucaoSistema.in=" + DEFAULT_ANO_CONSTRUCAO_SISTEMA + "," + UPDATED_ANO_CONSTRUCAO_SISTEMA);

        // Get all the sistemaAguaList where anoConstrucaoSistema equals to UPDATED_ANO_CONSTRUCAO_SISTEMA
        defaultSistemaAguaShouldNotBeFound("anoConstrucaoSistema.in=" + UPDATED_ANO_CONSTRUCAO_SISTEMA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByAnoConstrucaoSistemaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where anoConstrucaoSistema is not null
        defaultSistemaAguaShouldBeFound("anoConstrucaoSistema.specified=true");

        // Get all the sistemaAguaList where anoConstrucaoSistema is null
        defaultSistemaAguaShouldNotBeFound("anoConstrucaoSistema.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByAnoConstrucaoSistemaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where anoConstrucaoSistema greater than or equals to DEFAULT_ANO_CONSTRUCAO_SISTEMA
        defaultSistemaAguaShouldBeFound("anoConstrucaoSistema.greaterOrEqualThan=" + DEFAULT_ANO_CONSTRUCAO_SISTEMA);

        // Get all the sistemaAguaList where anoConstrucaoSistema greater than or equals to UPDATED_ANO_CONSTRUCAO_SISTEMA
        defaultSistemaAguaShouldNotBeFound("anoConstrucaoSistema.greaterOrEqualThan=" + UPDATED_ANO_CONSTRUCAO_SISTEMA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByAnoConstrucaoSistemaIsLessThanSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where anoConstrucaoSistema less than or equals to DEFAULT_ANO_CONSTRUCAO_SISTEMA
        defaultSistemaAguaShouldNotBeFound("anoConstrucaoSistema.lessThan=" + DEFAULT_ANO_CONSTRUCAO_SISTEMA);

        // Get all the sistemaAguaList where anoConstrucaoSistema less than or equals to UPDATED_ANO_CONSTRUCAO_SISTEMA
        defaultSistemaAguaShouldBeFound("anoConstrucaoSistema.lessThan=" + UPDATED_ANO_CONSTRUCAO_SISTEMA);
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpAvariaSistemaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpAvariaSistema equals to DEFAULT_NM_TP_AVARIA_SISTEMA
        defaultSistemaAguaShouldBeFound("nmTpAvariaSistema.equals=" + DEFAULT_NM_TP_AVARIA_SISTEMA);

        // Get all the sistemaAguaList where nmTpAvariaSistema equals to UPDATED_NM_TP_AVARIA_SISTEMA
        defaultSistemaAguaShouldNotBeFound("nmTpAvariaSistema.equals=" + UPDATED_NM_TP_AVARIA_SISTEMA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpAvariaSistemaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpAvariaSistema in DEFAULT_NM_TP_AVARIA_SISTEMA or UPDATED_NM_TP_AVARIA_SISTEMA
        defaultSistemaAguaShouldBeFound("nmTpAvariaSistema.in=" + DEFAULT_NM_TP_AVARIA_SISTEMA + "," + UPDATED_NM_TP_AVARIA_SISTEMA);

        // Get all the sistemaAguaList where nmTpAvariaSistema equals to UPDATED_NM_TP_AVARIA_SISTEMA
        defaultSistemaAguaShouldNotBeFound("nmTpAvariaSistema.in=" + UPDATED_NM_TP_AVARIA_SISTEMA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpAvariaSistemaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpAvariaSistema is not null
        defaultSistemaAguaShouldBeFound("nmTpAvariaSistema.specified=true");

        // Get all the sistemaAguaList where nmTpAvariaSistema is null
        defaultSistemaAguaShouldNotBeFound("nmTpAvariaSistema.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByCausaAvariaSistemaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where causaAvariaSistema equals to DEFAULT_CAUSA_AVARIA_SISTEMA
        defaultSistemaAguaShouldBeFound("causaAvariaSistema.equals=" + DEFAULT_CAUSA_AVARIA_SISTEMA);

        // Get all the sistemaAguaList where causaAvariaSistema equals to UPDATED_CAUSA_AVARIA_SISTEMA
        defaultSistemaAguaShouldNotBeFound("causaAvariaSistema.equals=" + UPDATED_CAUSA_AVARIA_SISTEMA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByCausaAvariaSistemaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where causaAvariaSistema in DEFAULT_CAUSA_AVARIA_SISTEMA or UPDATED_CAUSA_AVARIA_SISTEMA
        defaultSistemaAguaShouldBeFound("causaAvariaSistema.in=" + DEFAULT_CAUSA_AVARIA_SISTEMA + "," + UPDATED_CAUSA_AVARIA_SISTEMA);

        // Get all the sistemaAguaList where causaAvariaSistema equals to UPDATED_CAUSA_AVARIA_SISTEMA
        defaultSistemaAguaShouldNotBeFound("causaAvariaSistema.in=" + UPDATED_CAUSA_AVARIA_SISTEMA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByCausaAvariaSistemaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where causaAvariaSistema is not null
        defaultSistemaAguaShouldBeFound("causaAvariaSistema.specified=true");

        // Get all the sistemaAguaList where causaAvariaSistema is null
        defaultSistemaAguaShouldNotBeFound("causaAvariaSistema.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByStatusResolucaoIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where statusResolucao equals to DEFAULT_STATUS_RESOLUCAO
        defaultSistemaAguaShouldBeFound("statusResolucao.equals=" + DEFAULT_STATUS_RESOLUCAO);

        // Get all the sistemaAguaList where statusResolucao equals to UPDATED_STATUS_RESOLUCAO
        defaultSistemaAguaShouldNotBeFound("statusResolucao.equals=" + UPDATED_STATUS_RESOLUCAO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByStatusResolucaoIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where statusResolucao in DEFAULT_STATUS_RESOLUCAO or UPDATED_STATUS_RESOLUCAO
        defaultSistemaAguaShouldBeFound("statusResolucao.in=" + DEFAULT_STATUS_RESOLUCAO + "," + UPDATED_STATUS_RESOLUCAO);

        // Get all the sistemaAguaList where statusResolucao equals to UPDATED_STATUS_RESOLUCAO
        defaultSistemaAguaShouldNotBeFound("statusResolucao.in=" + UPDATED_STATUS_RESOLUCAO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByStatusResolucaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where statusResolucao is not null
        defaultSistemaAguaShouldBeFound("statusResolucao.specified=true");

        // Get all the sistemaAguaList where statusResolucao is null
        defaultSistemaAguaShouldNotBeFound("statusResolucao.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByTempoServicoDisponivelIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where tempoServicoDisponivel equals to DEFAULT_TEMPO_SERVICO_DISPONIVEL
        defaultSistemaAguaShouldBeFound("tempoServicoDisponivel.equals=" + DEFAULT_TEMPO_SERVICO_DISPONIVEL);

        // Get all the sistemaAguaList where tempoServicoDisponivel equals to UPDATED_TEMPO_SERVICO_DISPONIVEL
        defaultSistemaAguaShouldNotBeFound("tempoServicoDisponivel.equals=" + UPDATED_TEMPO_SERVICO_DISPONIVEL);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByTempoServicoDisponivelIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where tempoServicoDisponivel in DEFAULT_TEMPO_SERVICO_DISPONIVEL or UPDATED_TEMPO_SERVICO_DISPONIVEL
        defaultSistemaAguaShouldBeFound("tempoServicoDisponivel.in=" + DEFAULT_TEMPO_SERVICO_DISPONIVEL + "," + UPDATED_TEMPO_SERVICO_DISPONIVEL);

        // Get all the sistemaAguaList where tempoServicoDisponivel equals to UPDATED_TEMPO_SERVICO_DISPONIVEL
        defaultSistemaAguaShouldNotBeFound("tempoServicoDisponivel.in=" + UPDATED_TEMPO_SERVICO_DISPONIVEL);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByTempoServicoDisponivelIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where tempoServicoDisponivel is not null
        defaultSistemaAguaShouldBeFound("tempoServicoDisponivel.specified=true");

        // Get all the sistemaAguaList where tempoServicoDisponivel is null
        defaultSistemaAguaShouldNotBeFound("tempoServicoDisponivel.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdDiametroCondutaAdutoraAguaBrutaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdDiametroCondutaAdutoraAguaBruta equals to DEFAULT_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_BRUTA
        defaultSistemaAguaShouldBeFound("qtdDiametroCondutaAdutoraAguaBruta.equals=" + DEFAULT_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_BRUTA);

        // Get all the sistemaAguaList where qtdDiametroCondutaAdutoraAguaBruta equals to UPDATED_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_BRUTA
        defaultSistemaAguaShouldNotBeFound("qtdDiametroCondutaAdutoraAguaBruta.equals=" + UPDATED_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_BRUTA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdDiametroCondutaAdutoraAguaBrutaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdDiametroCondutaAdutoraAguaBruta in DEFAULT_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_BRUTA or UPDATED_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_BRUTA
        defaultSistemaAguaShouldBeFound("qtdDiametroCondutaAdutoraAguaBruta.in=" + DEFAULT_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_BRUTA + "," + UPDATED_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_BRUTA);

        // Get all the sistemaAguaList where qtdDiametroCondutaAdutoraAguaBruta equals to UPDATED_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_BRUTA
        defaultSistemaAguaShouldNotBeFound("qtdDiametroCondutaAdutoraAguaBruta.in=" + UPDATED_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_BRUTA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdDiametroCondutaAdutoraAguaBrutaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdDiametroCondutaAdutoraAguaBruta is not null
        defaultSistemaAguaShouldBeFound("qtdDiametroCondutaAdutoraAguaBruta.specified=true");

        // Get all the sistemaAguaList where qtdDiametroCondutaAdutoraAguaBruta is null
        defaultSistemaAguaShouldNotBeFound("qtdDiametroCondutaAdutoraAguaBruta.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdComprimentoCondutaAdutoraAguaBrutaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdComprimentoCondutaAdutoraAguaBruta equals to DEFAULT_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_BRUTA
        defaultSistemaAguaShouldBeFound("qtdComprimentoCondutaAdutoraAguaBruta.equals=" + DEFAULT_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_BRUTA);

        // Get all the sistemaAguaList where qtdComprimentoCondutaAdutoraAguaBruta equals to UPDATED_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_BRUTA
        defaultSistemaAguaShouldNotBeFound("qtdComprimentoCondutaAdutoraAguaBruta.equals=" + UPDATED_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_BRUTA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdComprimentoCondutaAdutoraAguaBrutaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdComprimentoCondutaAdutoraAguaBruta in DEFAULT_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_BRUTA or UPDATED_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_BRUTA
        defaultSistemaAguaShouldBeFound("qtdComprimentoCondutaAdutoraAguaBruta.in=" + DEFAULT_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_BRUTA + "," + UPDATED_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_BRUTA);

        // Get all the sistemaAguaList where qtdComprimentoCondutaAdutoraAguaBruta equals to UPDATED_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_BRUTA
        defaultSistemaAguaShouldNotBeFound("qtdComprimentoCondutaAdutoraAguaBruta.in=" + UPDATED_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_BRUTA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdComprimentoCondutaAdutoraAguaBrutaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdComprimentoCondutaAdutoraAguaBruta is not null
        defaultSistemaAguaShouldBeFound("qtdComprimentoCondutaAdutoraAguaBruta.specified=true");

        // Get all the sistemaAguaList where qtdComprimentoCondutaAdutoraAguaBruta is null
        defaultSistemaAguaShouldNotBeFound("qtdComprimentoCondutaAdutoraAguaBruta.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdDiametroCondutaAdutoraAguaTratadaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdDiametroCondutaAdutoraAguaTratada equals to DEFAULT_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_TRATADA
        defaultSistemaAguaShouldBeFound("qtdDiametroCondutaAdutoraAguaTratada.equals=" + DEFAULT_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_TRATADA);

        // Get all the sistemaAguaList where qtdDiametroCondutaAdutoraAguaTratada equals to UPDATED_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_TRATADA
        defaultSistemaAguaShouldNotBeFound("qtdDiametroCondutaAdutoraAguaTratada.equals=" + UPDATED_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_TRATADA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdDiametroCondutaAdutoraAguaTratadaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdDiametroCondutaAdutoraAguaTratada in DEFAULT_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_TRATADA or UPDATED_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_TRATADA
        defaultSistemaAguaShouldBeFound("qtdDiametroCondutaAdutoraAguaTratada.in=" + DEFAULT_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_TRATADA + "," + UPDATED_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_TRATADA);

        // Get all the sistemaAguaList where qtdDiametroCondutaAdutoraAguaTratada equals to UPDATED_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_TRATADA
        defaultSistemaAguaShouldNotBeFound("qtdDiametroCondutaAdutoraAguaTratada.in=" + UPDATED_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_TRATADA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdDiametroCondutaAdutoraAguaTratadaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdDiametroCondutaAdutoraAguaTratada is not null
        defaultSistemaAguaShouldBeFound("qtdDiametroCondutaAdutoraAguaTratada.specified=true");

        // Get all the sistemaAguaList where qtdDiametroCondutaAdutoraAguaTratada is null
        defaultSistemaAguaShouldNotBeFound("qtdDiametroCondutaAdutoraAguaTratada.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdComprimentoCondutaAdutoraAguaTratadaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdComprimentoCondutaAdutoraAguaTratada equals to DEFAULT_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_TRATADA
        defaultSistemaAguaShouldBeFound("qtdComprimentoCondutaAdutoraAguaTratada.equals=" + DEFAULT_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_TRATADA);

        // Get all the sistemaAguaList where qtdComprimentoCondutaAdutoraAguaTratada equals to UPDATED_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_TRATADA
        defaultSistemaAguaShouldNotBeFound("qtdComprimentoCondutaAdutoraAguaTratada.equals=" + UPDATED_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_TRATADA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdComprimentoCondutaAdutoraAguaTratadaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdComprimentoCondutaAdutoraAguaTratada in DEFAULT_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_TRATADA or UPDATED_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_TRATADA
        defaultSistemaAguaShouldBeFound("qtdComprimentoCondutaAdutoraAguaTratada.in=" + DEFAULT_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_TRATADA + "," + UPDATED_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_TRATADA);

        // Get all the sistemaAguaList where qtdComprimentoCondutaAdutoraAguaTratada equals to UPDATED_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_TRATADA
        defaultSistemaAguaShouldNotBeFound("qtdComprimentoCondutaAdutoraAguaTratada.in=" + UPDATED_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_TRATADA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdComprimentoCondutaAdutoraAguaTratadaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdComprimentoCondutaAdutoraAguaTratada is not null
        defaultSistemaAguaShouldBeFound("qtdComprimentoCondutaAdutoraAguaTratada.specified=true");

        // Get all the sistemaAguaList where qtdComprimentoCondutaAdutoraAguaTratada is null
        defaultSistemaAguaShouldNotBeFound("qtdComprimentoCondutaAdutoraAguaTratada.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByDescMaterialUtilizadoCondutasIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where descMaterialUtilizadoCondutas equals to DEFAULT_DESC_MATERIAL_UTILIZADO_CONDUTAS
        defaultSistemaAguaShouldBeFound("descMaterialUtilizadoCondutas.equals=" + DEFAULT_DESC_MATERIAL_UTILIZADO_CONDUTAS);

        // Get all the sistemaAguaList where descMaterialUtilizadoCondutas equals to UPDATED_DESC_MATERIAL_UTILIZADO_CONDUTAS
        defaultSistemaAguaShouldNotBeFound("descMaterialUtilizadoCondutas.equals=" + UPDATED_DESC_MATERIAL_UTILIZADO_CONDUTAS);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByDescMaterialUtilizadoCondutasIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where descMaterialUtilizadoCondutas in DEFAULT_DESC_MATERIAL_UTILIZADO_CONDUTAS or UPDATED_DESC_MATERIAL_UTILIZADO_CONDUTAS
        defaultSistemaAguaShouldBeFound("descMaterialUtilizadoCondutas.in=" + DEFAULT_DESC_MATERIAL_UTILIZADO_CONDUTAS + "," + UPDATED_DESC_MATERIAL_UTILIZADO_CONDUTAS);

        // Get all the sistemaAguaList where descMaterialUtilizadoCondutas equals to UPDATED_DESC_MATERIAL_UTILIZADO_CONDUTAS
        defaultSistemaAguaShouldNotBeFound("descMaterialUtilizadoCondutas.in=" + UPDATED_DESC_MATERIAL_UTILIZADO_CONDUTAS);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByDescMaterialUtilizadoCondutasIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where descMaterialUtilizadoCondutas is not null
        defaultSistemaAguaShouldBeFound("descMaterialUtilizadoCondutas.specified=true");

        // Get all the sistemaAguaList where descMaterialUtilizadoCondutas is null
        defaultSistemaAguaShouldNotBeFound("descMaterialUtilizadoCondutas.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdReservatoriosApoiadosIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdReservatoriosApoiados equals to DEFAULT_QTD_RESERVATORIOS_APOIADOS
        defaultSistemaAguaShouldBeFound("qtdReservatoriosApoiados.equals=" + DEFAULT_QTD_RESERVATORIOS_APOIADOS);

        // Get all the sistemaAguaList where qtdReservatoriosApoiados equals to UPDATED_QTD_RESERVATORIOS_APOIADOS
        defaultSistemaAguaShouldNotBeFound("qtdReservatoriosApoiados.equals=" + UPDATED_QTD_RESERVATORIOS_APOIADOS);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdReservatoriosApoiadosIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdReservatoriosApoiados in DEFAULT_QTD_RESERVATORIOS_APOIADOS or UPDATED_QTD_RESERVATORIOS_APOIADOS
        defaultSistemaAguaShouldBeFound("qtdReservatoriosApoiados.in=" + DEFAULT_QTD_RESERVATORIOS_APOIADOS + "," + UPDATED_QTD_RESERVATORIOS_APOIADOS);

        // Get all the sistemaAguaList where qtdReservatoriosApoiados equals to UPDATED_QTD_RESERVATORIOS_APOIADOS
        defaultSistemaAguaShouldNotBeFound("qtdReservatoriosApoiados.in=" + UPDATED_QTD_RESERVATORIOS_APOIADOS);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdReservatoriosApoiadosIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdReservatoriosApoiados is not null
        defaultSistemaAguaShouldBeFound("qtdReservatoriosApoiados.specified=true");

        // Get all the sistemaAguaList where qtdReservatoriosApoiados is null
        defaultSistemaAguaShouldNotBeFound("qtdReservatoriosApoiados.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdReservatoriosApoiadosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdReservatoriosApoiados greater than or equals to DEFAULT_QTD_RESERVATORIOS_APOIADOS
        defaultSistemaAguaShouldBeFound("qtdReservatoriosApoiados.greaterOrEqualThan=" + DEFAULT_QTD_RESERVATORIOS_APOIADOS);

        // Get all the sistemaAguaList where qtdReservatoriosApoiados greater than or equals to UPDATED_QTD_RESERVATORIOS_APOIADOS
        defaultSistemaAguaShouldNotBeFound("qtdReservatoriosApoiados.greaterOrEqualThan=" + UPDATED_QTD_RESERVATORIOS_APOIADOS);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdReservatoriosApoiadosIsLessThanSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdReservatoriosApoiados less than or equals to DEFAULT_QTD_RESERVATORIOS_APOIADOS
        defaultSistemaAguaShouldNotBeFound("qtdReservatoriosApoiados.lessThan=" + DEFAULT_QTD_RESERVATORIOS_APOIADOS);

        // Get all the sistemaAguaList where qtdReservatoriosApoiados less than or equals to UPDATED_QTD_RESERVATORIOS_APOIADOS
        defaultSistemaAguaShouldBeFound("qtdReservatoriosApoiados.lessThan=" + UPDATED_QTD_RESERVATORIOS_APOIADOS);
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByQtdCapacidadeReservatoriosApoiadosIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdCapacidadeReservatoriosApoiados equals to DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS
        defaultSistemaAguaShouldBeFound("qtdCapacidadeReservatoriosApoiados.equals=" + DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS);

        // Get all the sistemaAguaList where qtdCapacidadeReservatoriosApoiados equals to UPDATED_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS
        defaultSistemaAguaShouldNotBeFound("qtdCapacidadeReservatoriosApoiados.equals=" + UPDATED_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdCapacidadeReservatoriosApoiadosIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdCapacidadeReservatoriosApoiados in DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS or UPDATED_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS
        defaultSistemaAguaShouldBeFound("qtdCapacidadeReservatoriosApoiados.in=" + DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS + "," + UPDATED_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS);

        // Get all the sistemaAguaList where qtdCapacidadeReservatoriosApoiados equals to UPDATED_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS
        defaultSistemaAguaShouldNotBeFound("qtdCapacidadeReservatoriosApoiados.in=" + UPDATED_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdCapacidadeReservatoriosApoiadosIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdCapacidadeReservatoriosApoiados is not null
        defaultSistemaAguaShouldBeFound("qtdCapacidadeReservatoriosApoiados.specified=true");

        // Get all the sistemaAguaList where qtdCapacidadeReservatoriosApoiados is null
        defaultSistemaAguaShouldNotBeFound("qtdCapacidadeReservatoriosApoiados.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdCapacidadeReservatoriosApoiadosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdCapacidadeReservatoriosApoiados greater than or equals to DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS
        defaultSistemaAguaShouldBeFound("qtdCapacidadeReservatoriosApoiados.greaterOrEqualThan=" + DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS);

        // Get all the sistemaAguaList where qtdCapacidadeReservatoriosApoiados greater than or equals to UPDATED_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS
        defaultSistemaAguaShouldNotBeFound("qtdCapacidadeReservatoriosApoiados.greaterOrEqualThan=" + UPDATED_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdCapacidadeReservatoriosApoiadosIsLessThanSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdCapacidadeReservatoriosApoiados less than or equals to DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS
        defaultSistemaAguaShouldNotBeFound("qtdCapacidadeReservatoriosApoiados.lessThan=" + DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS);

        // Get all the sistemaAguaList where qtdCapacidadeReservatoriosApoiados less than or equals to UPDATED_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS
        defaultSistemaAguaShouldBeFound("qtdCapacidadeReservatoriosApoiados.lessThan=" + UPDATED_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS);
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByQtdReservatoriosElevadosIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdReservatoriosElevados equals to DEFAULT_QTD_RESERVATORIOS_ELEVADOS
        defaultSistemaAguaShouldBeFound("qtdReservatoriosElevados.equals=" + DEFAULT_QTD_RESERVATORIOS_ELEVADOS);

        // Get all the sistemaAguaList where qtdReservatoriosElevados equals to UPDATED_QTD_RESERVATORIOS_ELEVADOS
        defaultSistemaAguaShouldNotBeFound("qtdReservatoriosElevados.equals=" + UPDATED_QTD_RESERVATORIOS_ELEVADOS);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdReservatoriosElevadosIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdReservatoriosElevados in DEFAULT_QTD_RESERVATORIOS_ELEVADOS or UPDATED_QTD_RESERVATORIOS_ELEVADOS
        defaultSistemaAguaShouldBeFound("qtdReservatoriosElevados.in=" + DEFAULT_QTD_RESERVATORIOS_ELEVADOS + "," + UPDATED_QTD_RESERVATORIOS_ELEVADOS);

        // Get all the sistemaAguaList where qtdReservatoriosElevados equals to UPDATED_QTD_RESERVATORIOS_ELEVADOS
        defaultSistemaAguaShouldNotBeFound("qtdReservatoriosElevados.in=" + UPDATED_QTD_RESERVATORIOS_ELEVADOS);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdReservatoriosElevadosIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdReservatoriosElevados is not null
        defaultSistemaAguaShouldBeFound("qtdReservatoriosElevados.specified=true");

        // Get all the sistemaAguaList where qtdReservatoriosElevados is null
        defaultSistemaAguaShouldNotBeFound("qtdReservatoriosElevados.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdReservatoriosElevadosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdReservatoriosElevados greater than or equals to DEFAULT_QTD_RESERVATORIOS_ELEVADOS
        defaultSistemaAguaShouldBeFound("qtdReservatoriosElevados.greaterOrEqualThan=" + DEFAULT_QTD_RESERVATORIOS_ELEVADOS);

        // Get all the sistemaAguaList where qtdReservatoriosElevados greater than or equals to UPDATED_QTD_RESERVATORIOS_ELEVADOS
        defaultSistemaAguaShouldNotBeFound("qtdReservatoriosElevados.greaterOrEqualThan=" + UPDATED_QTD_RESERVATORIOS_ELEVADOS);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdReservatoriosElevadosIsLessThanSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdReservatoriosElevados less than or equals to DEFAULT_QTD_RESERVATORIOS_ELEVADOS
        defaultSistemaAguaShouldNotBeFound("qtdReservatoriosElevados.lessThan=" + DEFAULT_QTD_RESERVATORIOS_ELEVADOS);

        // Get all the sistemaAguaList where qtdReservatoriosElevados less than or equals to UPDATED_QTD_RESERVATORIOS_ELEVADOS
        defaultSistemaAguaShouldBeFound("qtdReservatoriosElevados.lessThan=" + UPDATED_QTD_RESERVATORIOS_ELEVADOS);
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByQtdCapacidadeReservatoriosElevadosIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdCapacidadeReservatoriosElevados equals to DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS
        defaultSistemaAguaShouldBeFound("qtdCapacidadeReservatoriosElevados.equals=" + DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS);

        // Get all the sistemaAguaList where qtdCapacidadeReservatoriosElevados equals to UPDATED_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS
        defaultSistemaAguaShouldNotBeFound("qtdCapacidadeReservatoriosElevados.equals=" + UPDATED_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdCapacidadeReservatoriosElevadosIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdCapacidadeReservatoriosElevados in DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS or UPDATED_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS
        defaultSistemaAguaShouldBeFound("qtdCapacidadeReservatoriosElevados.in=" + DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS + "," + UPDATED_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS);

        // Get all the sistemaAguaList where qtdCapacidadeReservatoriosElevados equals to UPDATED_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS
        defaultSistemaAguaShouldNotBeFound("qtdCapacidadeReservatoriosElevados.in=" + UPDATED_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdCapacidadeReservatoriosElevadosIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdCapacidadeReservatoriosElevados is not null
        defaultSistemaAguaShouldBeFound("qtdCapacidadeReservatoriosElevados.specified=true");

        // Get all the sistemaAguaList where qtdCapacidadeReservatoriosElevados is null
        defaultSistemaAguaShouldNotBeFound("qtdCapacidadeReservatoriosElevados.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdCapacidadeReservatoriosElevadosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdCapacidadeReservatoriosElevados greater than or equals to DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS
        defaultSistemaAguaShouldBeFound("qtdCapacidadeReservatoriosElevados.greaterOrEqualThan=" + DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS);

        // Get all the sistemaAguaList where qtdCapacidadeReservatoriosElevados greater than or equals to UPDATED_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS
        defaultSistemaAguaShouldNotBeFound("qtdCapacidadeReservatoriosElevados.greaterOrEqualThan=" + UPDATED_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdCapacidadeReservatoriosElevadosIsLessThanSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdCapacidadeReservatoriosElevados less than or equals to DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS
        defaultSistemaAguaShouldNotBeFound("qtdCapacidadeReservatoriosElevados.lessThan=" + DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS);

        // Get all the sistemaAguaList where qtdCapacidadeReservatoriosElevados less than or equals to UPDATED_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS
        defaultSistemaAguaShouldBeFound("qtdCapacidadeReservatoriosElevados.lessThan=" + UPDATED_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS);
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByAlturaReservatoriosElevadosIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where alturaReservatoriosElevados equals to DEFAULT_ALTURA_RESERVATORIOS_ELEVADOS
        defaultSistemaAguaShouldBeFound("alturaReservatoriosElevados.equals=" + DEFAULT_ALTURA_RESERVATORIOS_ELEVADOS);

        // Get all the sistemaAguaList where alturaReservatoriosElevados equals to UPDATED_ALTURA_RESERVATORIOS_ELEVADOS
        defaultSistemaAguaShouldNotBeFound("alturaReservatoriosElevados.equals=" + UPDATED_ALTURA_RESERVATORIOS_ELEVADOS);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByAlturaReservatoriosElevadosIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where alturaReservatoriosElevados in DEFAULT_ALTURA_RESERVATORIOS_ELEVADOS or UPDATED_ALTURA_RESERVATORIOS_ELEVADOS
        defaultSistemaAguaShouldBeFound("alturaReservatoriosElevados.in=" + DEFAULT_ALTURA_RESERVATORIOS_ELEVADOS + "," + UPDATED_ALTURA_RESERVATORIOS_ELEVADOS);

        // Get all the sistemaAguaList where alturaReservatoriosElevados equals to UPDATED_ALTURA_RESERVATORIOS_ELEVADOS
        defaultSistemaAguaShouldNotBeFound("alturaReservatoriosElevados.in=" + UPDATED_ALTURA_RESERVATORIOS_ELEVADOS);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByAlturaReservatoriosElevadosIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where alturaReservatoriosElevados is not null
        defaultSistemaAguaShouldBeFound("alturaReservatoriosElevados.specified=true");

        // Get all the sistemaAguaList where alturaReservatoriosElevados is null
        defaultSistemaAguaShouldNotBeFound("alturaReservatoriosElevados.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpTratamentoAguaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpTratamentoAgua equals to DEFAULT_NM_TP_TRATAMENTO_AGUA
        defaultSistemaAguaShouldBeFound("nmTpTratamentoAgua.equals=" + DEFAULT_NM_TP_TRATAMENTO_AGUA);

        // Get all the sistemaAguaList where nmTpTratamentoAgua equals to UPDATED_NM_TP_TRATAMENTO_AGUA
        defaultSistemaAguaShouldNotBeFound("nmTpTratamentoAgua.equals=" + UPDATED_NM_TP_TRATAMENTO_AGUA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpTratamentoAguaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpTratamentoAgua in DEFAULT_NM_TP_TRATAMENTO_AGUA or UPDATED_NM_TP_TRATAMENTO_AGUA
        defaultSistemaAguaShouldBeFound("nmTpTratamentoAgua.in=" + DEFAULT_NM_TP_TRATAMENTO_AGUA + "," + UPDATED_NM_TP_TRATAMENTO_AGUA);

        // Get all the sistemaAguaList where nmTpTratamentoAgua equals to UPDATED_NM_TP_TRATAMENTO_AGUA
        defaultSistemaAguaShouldNotBeFound("nmTpTratamentoAgua.in=" + UPDATED_NM_TP_TRATAMENTO_AGUA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpTratamentoAguaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpTratamentoAgua is not null
        defaultSistemaAguaShouldBeFound("nmTpTratamentoAgua.specified=true");

        // Get all the sistemaAguaList where nmTpTratamentoAgua is null
        defaultSistemaAguaShouldNotBeFound("nmTpTratamentoAgua.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpTratamentoPadraoUtilizadoIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpTratamentoPadraoUtilizado equals to DEFAULT_NM_TP_TRATAMENTO_PADRAO_UTILIZADO
        defaultSistemaAguaShouldBeFound("nmTpTratamentoPadraoUtilizado.equals=" + DEFAULT_NM_TP_TRATAMENTO_PADRAO_UTILIZADO);

        // Get all the sistemaAguaList where nmTpTratamentoPadraoUtilizado equals to UPDATED_NM_TP_TRATAMENTO_PADRAO_UTILIZADO
        defaultSistemaAguaShouldNotBeFound("nmTpTratamentoPadraoUtilizado.equals=" + UPDATED_NM_TP_TRATAMENTO_PADRAO_UTILIZADO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpTratamentoPadraoUtilizadoIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpTratamentoPadraoUtilizado in DEFAULT_NM_TP_TRATAMENTO_PADRAO_UTILIZADO or UPDATED_NM_TP_TRATAMENTO_PADRAO_UTILIZADO
        defaultSistemaAguaShouldBeFound("nmTpTratamentoPadraoUtilizado.in=" + DEFAULT_NM_TP_TRATAMENTO_PADRAO_UTILIZADO + "," + UPDATED_NM_TP_TRATAMENTO_PADRAO_UTILIZADO);

        // Get all the sistemaAguaList where nmTpTratamentoPadraoUtilizado equals to UPDATED_NM_TP_TRATAMENTO_PADRAO_UTILIZADO
        defaultSistemaAguaShouldNotBeFound("nmTpTratamentoPadraoUtilizado.in=" + UPDATED_NM_TP_TRATAMENTO_PADRAO_UTILIZADO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpTratamentoPadraoUtilizadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpTratamentoPadraoUtilizado is not null
        defaultSistemaAguaShouldBeFound("nmTpTratamentoPadraoUtilizado.specified=true");

        // Get all the sistemaAguaList where nmTpTratamentoPadraoUtilizado is null
        defaultSistemaAguaShouldNotBeFound("nmTpTratamentoPadraoUtilizado.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpTratamentoBasicoUtilizadoIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpTratamentoBasicoUtilizado equals to DEFAULT_NM_TP_TRATAMENTO_BASICO_UTILIZADO
        defaultSistemaAguaShouldBeFound("nmTpTratamentoBasicoUtilizado.equals=" + DEFAULT_NM_TP_TRATAMENTO_BASICO_UTILIZADO);

        // Get all the sistemaAguaList where nmTpTratamentoBasicoUtilizado equals to UPDATED_NM_TP_TRATAMENTO_BASICO_UTILIZADO
        defaultSistemaAguaShouldNotBeFound("nmTpTratamentoBasicoUtilizado.equals=" + UPDATED_NM_TP_TRATAMENTO_BASICO_UTILIZADO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpTratamentoBasicoUtilizadoIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpTratamentoBasicoUtilizado in DEFAULT_NM_TP_TRATAMENTO_BASICO_UTILIZADO or UPDATED_NM_TP_TRATAMENTO_BASICO_UTILIZADO
        defaultSistemaAguaShouldBeFound("nmTpTratamentoBasicoUtilizado.in=" + DEFAULT_NM_TP_TRATAMENTO_BASICO_UTILIZADO + "," + UPDATED_NM_TP_TRATAMENTO_BASICO_UTILIZADO);

        // Get all the sistemaAguaList where nmTpTratamentoBasicoUtilizado equals to UPDATED_NM_TP_TRATAMENTO_BASICO_UTILIZADO
        defaultSistemaAguaShouldNotBeFound("nmTpTratamentoBasicoUtilizado.in=" + UPDATED_NM_TP_TRATAMENTO_BASICO_UTILIZADO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpTratamentoBasicoUtilizadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpTratamentoBasicoUtilizado is not null
        defaultSistemaAguaShouldBeFound("nmTpTratamentoBasicoUtilizado.specified=true");

        // Get all the sistemaAguaList where nmTpTratamentoBasicoUtilizado is null
        defaultSistemaAguaShouldNotBeFound("nmTpTratamentoBasicoUtilizado.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByExisteAvariaSistemaTratamentoIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where existeAvariaSistemaTratamento equals to DEFAULT_EXISTE_AVARIA_SISTEMA_TRATAMENTO
        defaultSistemaAguaShouldBeFound("existeAvariaSistemaTratamento.equals=" + DEFAULT_EXISTE_AVARIA_SISTEMA_TRATAMENTO);

        // Get all the sistemaAguaList where existeAvariaSistemaTratamento equals to UPDATED_EXISTE_AVARIA_SISTEMA_TRATAMENTO
        defaultSistemaAguaShouldNotBeFound("existeAvariaSistemaTratamento.equals=" + UPDATED_EXISTE_AVARIA_SISTEMA_TRATAMENTO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByExisteAvariaSistemaTratamentoIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where existeAvariaSistemaTratamento in DEFAULT_EXISTE_AVARIA_SISTEMA_TRATAMENTO or UPDATED_EXISTE_AVARIA_SISTEMA_TRATAMENTO
        defaultSistemaAguaShouldBeFound("existeAvariaSistemaTratamento.in=" + DEFAULT_EXISTE_AVARIA_SISTEMA_TRATAMENTO + "," + UPDATED_EXISTE_AVARIA_SISTEMA_TRATAMENTO);

        // Get all the sistemaAguaList where existeAvariaSistemaTratamento equals to UPDATED_EXISTE_AVARIA_SISTEMA_TRATAMENTO
        defaultSistemaAguaShouldNotBeFound("existeAvariaSistemaTratamento.in=" + UPDATED_EXISTE_AVARIA_SISTEMA_TRATAMENTO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByExisteAvariaSistemaTratamentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where existeAvariaSistemaTratamento is not null
        defaultSistemaAguaShouldBeFound("existeAvariaSistemaTratamento.specified=true");

        // Get all the sistemaAguaList where existeAvariaSistemaTratamento is null
        defaultSistemaAguaShouldNotBeFound("existeAvariaSistemaTratamento.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByExisteMotivoAusenciaTratamentoIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where existeMotivoAusenciaTratamento equals to DEFAULT_EXISTE_MOTIVO_AUSENCIA_TRATAMENTO
        defaultSistemaAguaShouldBeFound("existeMotivoAusenciaTratamento.equals=" + DEFAULT_EXISTE_MOTIVO_AUSENCIA_TRATAMENTO);

        // Get all the sistemaAguaList where existeMotivoAusenciaTratamento equals to UPDATED_EXISTE_MOTIVO_AUSENCIA_TRATAMENTO
        defaultSistemaAguaShouldNotBeFound("existeMotivoAusenciaTratamento.equals=" + UPDATED_EXISTE_MOTIVO_AUSENCIA_TRATAMENTO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByExisteMotivoAusenciaTratamentoIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where existeMotivoAusenciaTratamento in DEFAULT_EXISTE_MOTIVO_AUSENCIA_TRATAMENTO or UPDATED_EXISTE_MOTIVO_AUSENCIA_TRATAMENTO
        defaultSistemaAguaShouldBeFound("existeMotivoAusenciaTratamento.in=" + DEFAULT_EXISTE_MOTIVO_AUSENCIA_TRATAMENTO + "," + UPDATED_EXISTE_MOTIVO_AUSENCIA_TRATAMENTO);

        // Get all the sistemaAguaList where existeMotivoAusenciaTratamento equals to UPDATED_EXISTE_MOTIVO_AUSENCIA_TRATAMENTO
        defaultSistemaAguaShouldNotBeFound("existeMotivoAusenciaTratamento.in=" + UPDATED_EXISTE_MOTIVO_AUSENCIA_TRATAMENTO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByExisteMotivoAusenciaTratamentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where existeMotivoAusenciaTratamento is not null
        defaultSistemaAguaShouldBeFound("existeMotivoAusenciaTratamento.specified=true");

        // Get all the sistemaAguaList where existeMotivoAusenciaTratamento is null
        defaultSistemaAguaShouldNotBeFound("existeMotivoAusenciaTratamento.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmEquipamentosComAvariaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmEquipamentosComAvaria equals to DEFAULT_NM_EQUIPAMENTOS_COM_AVARIA
        defaultSistemaAguaShouldBeFound("nmEquipamentosComAvaria.equals=" + DEFAULT_NM_EQUIPAMENTOS_COM_AVARIA);

        // Get all the sistemaAguaList where nmEquipamentosComAvaria equals to UPDATED_NM_EQUIPAMENTOS_COM_AVARIA
        defaultSistemaAguaShouldNotBeFound("nmEquipamentosComAvaria.equals=" + UPDATED_NM_EQUIPAMENTOS_COM_AVARIA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmEquipamentosComAvariaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmEquipamentosComAvaria in DEFAULT_NM_EQUIPAMENTOS_COM_AVARIA or UPDATED_NM_EQUIPAMENTOS_COM_AVARIA
        defaultSistemaAguaShouldBeFound("nmEquipamentosComAvaria.in=" + DEFAULT_NM_EQUIPAMENTOS_COM_AVARIA + "," + UPDATED_NM_EQUIPAMENTOS_COM_AVARIA);

        // Get all the sistemaAguaList where nmEquipamentosComAvaria equals to UPDATED_NM_EQUIPAMENTOS_COM_AVARIA
        defaultSistemaAguaShouldNotBeFound("nmEquipamentosComAvaria.in=" + UPDATED_NM_EQUIPAMENTOS_COM_AVARIA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmEquipamentosComAvariaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmEquipamentosComAvaria is not null
        defaultSistemaAguaShouldBeFound("nmEquipamentosComAvaria.specified=true");

        // Get all the sistemaAguaList where nmEquipamentosComAvaria is null
        defaultSistemaAguaShouldNotBeFound("nmEquipamentosComAvaria.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByCaudalDoSistemaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where caudalDoSistema equals to DEFAULT_CAUDAL_DO_SISTEMA
        defaultSistemaAguaShouldBeFound("caudalDoSistema.equals=" + DEFAULT_CAUDAL_DO_SISTEMA);

        // Get all the sistemaAguaList where caudalDoSistema equals to UPDATED_CAUDAL_DO_SISTEMA
        defaultSistemaAguaShouldNotBeFound("caudalDoSistema.equals=" + UPDATED_CAUDAL_DO_SISTEMA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByCaudalDoSistemaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where caudalDoSistema in DEFAULT_CAUDAL_DO_SISTEMA or UPDATED_CAUDAL_DO_SISTEMA
        defaultSistemaAguaShouldBeFound("caudalDoSistema.in=" + DEFAULT_CAUDAL_DO_SISTEMA + "," + UPDATED_CAUDAL_DO_SISTEMA);

        // Get all the sistemaAguaList where caudalDoSistema equals to UPDATED_CAUDAL_DO_SISTEMA
        defaultSistemaAguaShouldNotBeFound("caudalDoSistema.in=" + UPDATED_CAUDAL_DO_SISTEMA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByCaudalDoSistemaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where caudalDoSistema is not null
        defaultSistemaAguaShouldBeFound("caudalDoSistema.specified=true");

        // Get all the sistemaAguaList where caudalDoSistema is null
        defaultSistemaAguaShouldNotBeFound("caudalDoSistema.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByCaudalDoSistemaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where caudalDoSistema greater than or equals to DEFAULT_CAUDAL_DO_SISTEMA
        defaultSistemaAguaShouldBeFound("caudalDoSistema.greaterOrEqualThan=" + DEFAULT_CAUDAL_DO_SISTEMA);

        // Get all the sistemaAguaList where caudalDoSistema greater than or equals to UPDATED_CAUDAL_DO_SISTEMA
        defaultSistemaAguaShouldNotBeFound("caudalDoSistema.greaterOrEqualThan=" + UPDATED_CAUDAL_DO_SISTEMA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByCaudalDoSistemaIsLessThanSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where caudalDoSistema less than or equals to DEFAULT_CAUDAL_DO_SISTEMA
        defaultSistemaAguaShouldNotBeFound("caudalDoSistema.lessThan=" + DEFAULT_CAUDAL_DO_SISTEMA);

        // Get all the sistemaAguaList where caudalDoSistema less than or equals to UPDATED_CAUDAL_DO_SISTEMA
        defaultSistemaAguaShouldBeFound("caudalDoSistema.lessThan=" + UPDATED_CAUDAL_DO_SISTEMA);
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByQtdConsumoPercaptaLitrosHomemDiaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdConsumoPercaptaLitrosHomemDia equals to DEFAULT_QTD_CONSUMO_PERCAPTA_LITROS_HOMEM_DIA
        defaultSistemaAguaShouldBeFound("qtdConsumoPercaptaLitrosHomemDia.equals=" + DEFAULT_QTD_CONSUMO_PERCAPTA_LITROS_HOMEM_DIA);

        // Get all the sistemaAguaList where qtdConsumoPercaptaLitrosHomemDia equals to UPDATED_QTD_CONSUMO_PERCAPTA_LITROS_HOMEM_DIA
        defaultSistemaAguaShouldNotBeFound("qtdConsumoPercaptaLitrosHomemDia.equals=" + UPDATED_QTD_CONSUMO_PERCAPTA_LITROS_HOMEM_DIA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdConsumoPercaptaLitrosHomemDiaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdConsumoPercaptaLitrosHomemDia in DEFAULT_QTD_CONSUMO_PERCAPTA_LITROS_HOMEM_DIA or UPDATED_QTD_CONSUMO_PERCAPTA_LITROS_HOMEM_DIA
        defaultSistemaAguaShouldBeFound("qtdConsumoPercaptaLitrosHomemDia.in=" + DEFAULT_QTD_CONSUMO_PERCAPTA_LITROS_HOMEM_DIA + "," + UPDATED_QTD_CONSUMO_PERCAPTA_LITROS_HOMEM_DIA);

        // Get all the sistemaAguaList where qtdConsumoPercaptaLitrosHomemDia equals to UPDATED_QTD_CONSUMO_PERCAPTA_LITROS_HOMEM_DIA
        defaultSistemaAguaShouldNotBeFound("qtdConsumoPercaptaLitrosHomemDia.in=" + UPDATED_QTD_CONSUMO_PERCAPTA_LITROS_HOMEM_DIA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdConsumoPercaptaLitrosHomemDiaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdConsumoPercaptaLitrosHomemDia is not null
        defaultSistemaAguaShouldBeFound("qtdConsumoPercaptaLitrosHomemDia.specified=true");

        // Get all the sistemaAguaList where qtdConsumoPercaptaLitrosHomemDia is null
        defaultSistemaAguaShouldNotBeFound("qtdConsumoPercaptaLitrosHomemDia.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdDotacaoPercaptaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdDotacaoPercapta equals to DEFAULT_QTD_DOTACAO_PERCAPTA
        defaultSistemaAguaShouldBeFound("qtdDotacaoPercapta.equals=" + DEFAULT_QTD_DOTACAO_PERCAPTA);

        // Get all the sistemaAguaList where qtdDotacaoPercapta equals to UPDATED_QTD_DOTACAO_PERCAPTA
        defaultSistemaAguaShouldNotBeFound("qtdDotacaoPercapta.equals=" + UPDATED_QTD_DOTACAO_PERCAPTA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdDotacaoPercaptaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdDotacaoPercapta in DEFAULT_QTD_DOTACAO_PERCAPTA or UPDATED_QTD_DOTACAO_PERCAPTA
        defaultSistemaAguaShouldBeFound("qtdDotacaoPercapta.in=" + DEFAULT_QTD_DOTACAO_PERCAPTA + "," + UPDATED_QTD_DOTACAO_PERCAPTA);

        // Get all the sistemaAguaList where qtdDotacaoPercapta equals to UPDATED_QTD_DOTACAO_PERCAPTA
        defaultSistemaAguaShouldNotBeFound("qtdDotacaoPercapta.in=" + UPDATED_QTD_DOTACAO_PERCAPTA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdDotacaoPercaptaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdDotacaoPercapta is not null
        defaultSistemaAguaShouldBeFound("qtdDotacaoPercapta.specified=true");

        // Get all the sistemaAguaList where qtdDotacaoPercapta is null
        defaultSistemaAguaShouldNotBeFound("qtdDotacaoPercapta.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdDiariaHorasServicoSistemaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdDiariaHorasServicoSistema equals to DEFAULT_QTD_DIARIA_HORAS_SERVICO_SISTEMA
        defaultSistemaAguaShouldBeFound("qtdDiariaHorasServicoSistema.equals=" + DEFAULT_QTD_DIARIA_HORAS_SERVICO_SISTEMA);

        // Get all the sistemaAguaList where qtdDiariaHorasServicoSistema equals to UPDATED_QTD_DIARIA_HORAS_SERVICO_SISTEMA
        defaultSistemaAguaShouldNotBeFound("qtdDiariaHorasServicoSistema.equals=" + UPDATED_QTD_DIARIA_HORAS_SERVICO_SISTEMA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdDiariaHorasServicoSistemaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdDiariaHorasServicoSistema in DEFAULT_QTD_DIARIA_HORAS_SERVICO_SISTEMA or UPDATED_QTD_DIARIA_HORAS_SERVICO_SISTEMA
        defaultSistemaAguaShouldBeFound("qtdDiariaHorasServicoSistema.in=" + DEFAULT_QTD_DIARIA_HORAS_SERVICO_SISTEMA + "," + UPDATED_QTD_DIARIA_HORAS_SERVICO_SISTEMA);

        // Get all the sistemaAguaList where qtdDiariaHorasServicoSistema equals to UPDATED_QTD_DIARIA_HORAS_SERVICO_SISTEMA
        defaultSistemaAguaShouldNotBeFound("qtdDiariaHorasServicoSistema.in=" + UPDATED_QTD_DIARIA_HORAS_SERVICO_SISTEMA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByQtdDiariaHorasServicoSistemaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where qtdDiariaHorasServicoSistema is not null
        defaultSistemaAguaShouldBeFound("qtdDiariaHorasServicoSistema.specified=true");

        // Get all the sistemaAguaList where qtdDiariaHorasServicoSistema is null
        defaultSistemaAguaShouldNotBeFound("qtdDiariaHorasServicoSistema.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByEsquemaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where esquema equals to DEFAULT_ESQUEMA
        defaultSistemaAguaShouldBeFound("esquema.equals=" + DEFAULT_ESQUEMA);

        // Get all the sistemaAguaList where esquema equals to UPDATED_ESQUEMA
        defaultSistemaAguaShouldNotBeFound("esquema.equals=" + UPDATED_ESQUEMA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByEsquemaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where esquema in DEFAULT_ESQUEMA or UPDATED_ESQUEMA
        defaultSistemaAguaShouldBeFound("esquema.in=" + DEFAULT_ESQUEMA + "," + UPDATED_ESQUEMA);

        // Get all the sistemaAguaList where esquema equals to UPDATED_ESQUEMA
        defaultSistemaAguaShouldNotBeFound("esquema.in=" + UPDATED_ESQUEMA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByEsquemaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where esquema is not null
        defaultSistemaAguaShouldBeFound("esquema.specified=true");

        // Get all the sistemaAguaList where esquema is null
        defaultSistemaAguaShouldNotBeFound("esquema.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmModeloBombaManualUtilizadaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmModeloBombaManualUtilizada equals to DEFAULT_NM_MODELO_BOMBA_MANUAL_UTILIZADA
        defaultSistemaAguaShouldBeFound("nmModeloBombaManualUtilizada.equals=" + DEFAULT_NM_MODELO_BOMBA_MANUAL_UTILIZADA);

        // Get all the sistemaAguaList where nmModeloBombaManualUtilizada equals to UPDATED_NM_MODELO_BOMBA_MANUAL_UTILIZADA
        defaultSistemaAguaShouldNotBeFound("nmModeloBombaManualUtilizada.equals=" + UPDATED_NM_MODELO_BOMBA_MANUAL_UTILIZADA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmModeloBombaManualUtilizadaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmModeloBombaManualUtilizada in DEFAULT_NM_MODELO_BOMBA_MANUAL_UTILIZADA or UPDATED_NM_MODELO_BOMBA_MANUAL_UTILIZADA
        defaultSistemaAguaShouldBeFound("nmModeloBombaManualUtilizada.in=" + DEFAULT_NM_MODELO_BOMBA_MANUAL_UTILIZADA + "," + UPDATED_NM_MODELO_BOMBA_MANUAL_UTILIZADA);

        // Get all the sistemaAguaList where nmModeloBombaManualUtilizada equals to UPDATED_NM_MODELO_BOMBA_MANUAL_UTILIZADA
        defaultSistemaAguaShouldNotBeFound("nmModeloBombaManualUtilizada.in=" + UPDATED_NM_MODELO_BOMBA_MANUAL_UTILIZADA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmModeloBombaManualUtilizadaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmModeloBombaManualUtilizada is not null
        defaultSistemaAguaShouldBeFound("nmModeloBombaManualUtilizada.specified=true");

        // Get all the sistemaAguaList where nmModeloBombaManualUtilizada is null
        defaultSistemaAguaShouldNotBeFound("nmModeloBombaManualUtilizada.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpBombaEnergiaIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpBombaEnergia equals to DEFAULT_NM_TP_BOMBA_ENERGIA
        defaultSistemaAguaShouldBeFound("nmTpBombaEnergia.equals=" + DEFAULT_NM_TP_BOMBA_ENERGIA);

        // Get all the sistemaAguaList where nmTpBombaEnergia equals to UPDATED_NM_TP_BOMBA_ENERGIA
        defaultSistemaAguaShouldNotBeFound("nmTpBombaEnergia.equals=" + UPDATED_NM_TP_BOMBA_ENERGIA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpBombaEnergiaIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpBombaEnergia in DEFAULT_NM_TP_BOMBA_ENERGIA or UPDATED_NM_TP_BOMBA_ENERGIA
        defaultSistemaAguaShouldBeFound("nmTpBombaEnergia.in=" + DEFAULT_NM_TP_BOMBA_ENERGIA + "," + UPDATED_NM_TP_BOMBA_ENERGIA);

        // Get all the sistemaAguaList where nmTpBombaEnergia equals to UPDATED_NM_TP_BOMBA_ENERGIA
        defaultSistemaAguaShouldNotBeFound("nmTpBombaEnergia.in=" + UPDATED_NM_TP_BOMBA_ENERGIA);
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByNmTpBombaEnergiaIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);

        // Get all the sistemaAguaList where nmTpBombaEnergia is not null
        defaultSistemaAguaShouldBeFound("nmTpBombaEnergia.specified=true");

        // Get all the sistemaAguaList where nmTpBombaEnergia is null
        defaultSistemaAguaShouldNotBeFound("nmTpBombaEnergia.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguasByIdSituacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        Situacao idSituacao = SituacaoResourceIntTest.createEntity(em);
        em.persist(idSituacao);
        em.flush();
        sistemaAgua.setSituacao(idSituacao);
        sistemaAguaRepository.saveAndFlush(sistemaAgua);
        Long idSituacaoId = idSituacao.getId();

        // Get all the sistemaAguaList where idSituacao equals to situacao
        defaultSistemaAguaShouldBeFound("situacao.equals=" + idSituacaoId);

        // Get all the sistemaAguaList where idSituacao equals to situacao + 1
        defaultSistemaAguaShouldNotBeFound("situacao.equals=" + (idSituacaoId + 1));
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByIdComunaIsEqualToSomething() throws Exception {
        // Initialize the database
        Comuna idComuna = ComunaResourceIntTest.createEntity(em);
        em.persist(idComuna);
        em.flush();
        sistemaAgua.setComuna(idComuna);
        sistemaAguaRepository.saveAndFlush(sistemaAgua);
        Long idComunaId = idComuna.getId();

        // Get all the sistemaAguaList where idComuna equals to comuna
        defaultSistemaAguaShouldBeFound("comuna.equals=" + idComunaId);

        // Get all the sistemaAguaList where idComuna equals to comuna + 1
        defaultSistemaAguaShouldNotBeFound("comuna.equals=" + (idComunaId + 1));
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByAdjudicacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        Adjudicacao adjudicacao = AdjudicacaoResourceIntTest.createEntity(em);
        em.persist(adjudicacao);
        em.flush();
        sistemaAguaRepository.saveAndFlush(sistemaAgua);
        Long adjudicacaoId = adjudicacao.getId();

        // Get all the sistemaAguaList where adjudicacao equals to adjudicacaoId
        defaultSistemaAguaShouldBeFound("adjudicacaoId.equals=" + adjudicacaoId);

        // Get all the sistemaAguaList where adjudicacao equals to adjudicacaoId + 1
        defaultSistemaAguaShouldNotBeFound("adjudicacaoId.equals=" + (adjudicacaoId + 1));
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByConcepcaoIsEqualToSomething() throws Exception {
        // Initialize the database
        Concepcao concepcao = ConcepcaoResourceIntTest.createEntity(em);
        em.persist(concepcao);
        em.flush();
        sistemaAguaRepository.saveAndFlush(sistemaAgua);
        Long concepcaoId = concepcao.getId();

        // Get all the sistemaAguaList where concepcao equals to concepcaoId
        defaultSistemaAguaShouldBeFound("concepcaoId.equals=" + concepcaoId);

        // Get all the sistemaAguaList where concepcao equals to concepcaoId + 1
        defaultSistemaAguaShouldNotBeFound("concepcaoId.equals=" + (concepcaoId + 1));
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByConcursoIsEqualToSomething() throws Exception {
        // Initialize the database
        Concurso concurso = ConcursoResourceIntTest.createEntity(em);
        em.persist(concurso);
        em.flush();
        sistemaAguaRepository.saveAndFlush(sistemaAgua);
        Long concursoId = concurso.getId();

        // Get all the sistemaAguaList where concurso equals to concursoId
        defaultSistemaAguaShouldBeFound("concursoId.equals=" + concursoId);

        // Get all the sistemaAguaList where concurso equals to concursoId + 1
        defaultSistemaAguaShouldNotBeFound("concursoId.equals=" + (concursoId + 1));
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByContratoIsEqualToSomething() throws Exception {
        // Initialize the database
        Contrato contrato = ContratoResourceIntTest.createEntity(em);
        em.persist(contrato);
        em.flush();
        sistemaAguaRepository.saveAndFlush(sistemaAgua);
        Long contratoId = contrato.getId();

        // Get all the sistemaAguaList where contrato equals to contratoId
        defaultSistemaAguaShouldBeFound("contratoId.equals=" + contratoId);

        // Get all the sistemaAguaList where contrato equals to contratoId + 1
        defaultSistemaAguaShouldNotBeFound("contratoId.equals=" + (contratoId + 1));
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByEmpreitadaIsEqualToSomething() throws Exception {
        // Initialize the database
        Empreitada empreitada = EmpreitadaResourceIntTest.createEntity(em);
        em.persist(empreitada);
        em.flush();
        sistemaAguaRepository.saveAndFlush(sistemaAgua);
        Long empreitadaId = empreitada.getId();

        // Get all the sistemaAguaList where empreitada equals to empreitadaId
        defaultSistemaAguaShouldBeFound("empreitadaId.equals=" + empreitadaId);

        // Get all the sistemaAguaList where empreitada equals to empreitadaId + 1
        defaultSistemaAguaShouldNotBeFound("empreitadaId.equals=" + (empreitadaId + 1));
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByExecucaoIsEqualToSomething() throws Exception {
        // Initialize the database
        Execucao execucao = ExecucaoResourceIntTest.createEntity(em);
        em.persist(execucao);
        em.flush();
        sistemaAguaRepository.saveAndFlush(sistemaAgua);
        Long execucaoId = execucao.getId();

        // Get all the sistemaAguaList where execucao equals to execucaoId
        defaultSistemaAguaShouldBeFound("execucaoId.equals=" + execucaoId);

        // Get all the sistemaAguaList where execucao equals to execucaoId + 1
        defaultSistemaAguaShouldNotBeFound("execucaoId.equals=" + (execucaoId + 1));
    }


    @Test
    @Transactional
    public void getAllSistemaAguasByIndicadorProducaoIsEqualToSomething() throws Exception {
        // Initialize the database
        IndicadorProducao indicadorProducao = IndicadorProducaoResourceIntTest.createEntity(em);
        em.persist(indicadorProducao);
        em.flush();
        sistemaAguaRepository.saveAndFlush(sistemaAgua);
        Long indicadorProducaoId = indicadorProducao.getId();

        // Get all the sistemaAguaList where indicadorProducao equals to indicadorProducaoId
        defaultSistemaAguaShouldBeFound("indicadorProducaoId.equals=" + indicadorProducaoId);

        // Get all the sistemaAguaList where indicadorProducao equals to indicadorProducaoId + 1
        defaultSistemaAguaShouldNotBeFound("indicadorProducaoId.equals=" + (indicadorProducaoId + 1));
    }


    @Test
    @Transactional
    public void getAllSistemaAguasBySistemaAguaLogIsEqualToSomething() throws Exception {
        // Initialize the database
        SistemaAguaLog sistemaAguaLog = SistemaAguaLogResourceIntTest.createEntity(em);
        em.persist(sistemaAguaLog);
        em.flush();
        sistemaAguaRepository.saveAndFlush(sistemaAgua);
        Long sistemaAguaLogId = sistemaAguaLog.getId();

        // Get all the sistemaAguaList where sistemaAguaLog equals to sistemaAguaLogId
        defaultSistemaAguaShouldBeFound("sistemaAguaLogId.equals=" + sistemaAguaLogId);

        // Get all the sistemaAguaList where sistemaAguaLog equals to sistemaAguaLogId + 1
        defaultSistemaAguaShouldNotBeFound("sistemaAguaLogId.equals=" + (sistemaAguaLogId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultSistemaAguaShouldBeFound(String filter) throws Exception {
        restSistemaAguaMockMvc.perform(get("/api/sistema-aguas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sistemaAgua.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSistemaAgua").value(hasItem(DEFAULT_ID_SISTEMA_AGUA.intValue())))
            .andExpect(jsonPath("$.[*].idUsuario").value(hasItem(DEFAULT_ID_USUARIO.intValue())))
            .andExpect(jsonPath("$.[*].nmInqueridor").value(hasItem(DEFAULT_NM_INQUERIDOR.toString())))
            .andExpect(jsonPath("$.[*].dtLancamento").value(hasItem(DEFAULT_DT_LANCAMENTO.toString())))
            .andExpect(jsonPath("$.[*].dtUltimaAlteracao").value(hasItem(DEFAULT_DT_ULTIMA_ALTERACAO.toString())))
            .andExpect(jsonPath("$.[*].nmLocalidade").value(hasItem(DEFAULT_NM_LOCALIDADE.toString())))
            .andExpect(jsonPath("$.[*].qtdPopulacaoActual").value(hasItem(DEFAULT_QTD_POPULACAO_ACTUAL.intValue())))
            .andExpect(jsonPath("$.[*].qtdCasasLocalidade").value(hasItem(DEFAULT_QTD_CASAS_LOCALIDADE.intValue())))
            .andExpect(jsonPath("$.[*].nmTpComunaAldeia").value(hasItem(DEFAULT_NM_TP_COMUNA_ALDEIA.toString())))
            .andExpect(jsonPath("$.[*].nmTpArea").value(hasItem(DEFAULT_NM_TP_AREA.toString())))
            .andExpect(jsonPath("$.[*].possuiSistemaAgua").value(hasItem(DEFAULT_POSSUI_SISTEMA_AGUA.intValue())))
            .andExpect(jsonPath("$.[*].nmSistemaAgua").value(hasItem(DEFAULT_NM_SISTEMA_AGUA.toString())))
            .andExpect(jsonPath("$.[*].nmFonteAgua").value(hasItem(DEFAULT_NM_FONTE_AGUA.toString())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.intValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.intValue())))
            .andExpect(jsonPath("$.[*].altitude").value(hasItem(DEFAULT_ALTITUDE.intValue())))
            .andExpect(jsonPath("$.[*].nmTpFonte").value(hasItem(DEFAULT_NM_TP_FONTE.toString())))
            .andExpect(jsonPath("$.[*].nmFonteAguaUtilizada").value(hasItem(DEFAULT_NM_FONTE_AGUA_UTILIZADA.toString())))
            .andExpect(jsonPath("$.[*].nmTipoBomba").value(hasItem(DEFAULT_NM_TIPO_BOMBA.toString())))
            .andExpect(jsonPath("$.[*].qtdCasasAguaLigada").value(hasItem(DEFAULT_QTD_CASAS_AGUA_LIGADA.intValue())))
            .andExpect(jsonPath("$.[*].qtdChafarisesFuncionando").value(hasItem(DEFAULT_QTD_CHAFARISES_FUNCIONANDO.intValue())))
            .andExpect(jsonPath("$.[*].qtdContadoresLigados").value(hasItem(DEFAULT_QTD_CONTADORES_LIGADOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdBebedouros").value(hasItem(DEFAULT_QTD_BEBEDOUROS.intValue())))
            .andExpect(jsonPath("$.[*].qtdHabitantesAcessoServicoAgua").value(hasItem(DEFAULT_QTD_HABITANTES_ACESSO_SERVICO_AGUA.intValue())))
            .andExpect(jsonPath("$.[*].anoConstrucaoSistema").value(hasItem(DEFAULT_ANO_CONSTRUCAO_SISTEMA.intValue())))
            .andExpect(jsonPath("$.[*].nmTpAvariaSistema").value(hasItem(DEFAULT_NM_TP_AVARIA_SISTEMA.toString())))
            .andExpect(jsonPath("$.[*].causaAvariaSistema").value(hasItem(DEFAULT_CAUSA_AVARIA_SISTEMA.toString())))
            .andExpect(jsonPath("$.[*].statusResolucao").value(hasItem(DEFAULT_STATUS_RESOLUCAO.toString())))
            .andExpect(jsonPath("$.[*].tempoServicoDisponivel").value(hasItem(DEFAULT_TEMPO_SERVICO_DISPONIVEL.toString())))
            .andExpect(jsonPath("$.[*].qtdDiametroCondutaAdutoraAguaBruta").value(hasItem(DEFAULT_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_BRUTA.intValue())))
            .andExpect(jsonPath("$.[*].qtdComprimentoCondutaAdutoraAguaBruta").value(hasItem(DEFAULT_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_BRUTA.intValue())))
            .andExpect(jsonPath("$.[*].qtdDiametroCondutaAdutoraAguaTratada").value(hasItem(DEFAULT_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_TRATADA.intValue())))
            .andExpect(jsonPath("$.[*].qtdComprimentoCondutaAdutoraAguaTratada").value(hasItem(DEFAULT_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_TRATADA.intValue())))
            .andExpect(jsonPath("$.[*].descMaterialUtilizadoCondutas").value(hasItem(DEFAULT_DESC_MATERIAL_UTILIZADO_CONDUTAS.toString())))
            .andExpect(jsonPath("$.[*].qtdReservatoriosApoiados").value(hasItem(DEFAULT_QTD_RESERVATORIOS_APOIADOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdCapacidadeReservatoriosApoiados").value(hasItem(DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdReservatoriosElevados").value(hasItem(DEFAULT_QTD_RESERVATORIOS_ELEVADOS.intValue())))
            .andExpect(jsonPath("$.[*].qtdCapacidadeReservatoriosElevados").value(hasItem(DEFAULT_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS.intValue())))
            .andExpect(jsonPath("$.[*].alturaReservatoriosElevados").value(hasItem(DEFAULT_ALTURA_RESERVATORIOS_ELEVADOS.intValue())))
            .andExpect(jsonPath("$.[*].nmTpTratamentoAgua").value(hasItem(DEFAULT_NM_TP_TRATAMENTO_AGUA.toString())))
            .andExpect(jsonPath("$.[*].nmTpTratamentoPadraoUtilizado").value(hasItem(DEFAULT_NM_TP_TRATAMENTO_PADRAO_UTILIZADO.toString())))
            .andExpect(jsonPath("$.[*].nmTpTratamentoBasicoUtilizado").value(hasItem(DEFAULT_NM_TP_TRATAMENTO_BASICO_UTILIZADO.toString())))
            .andExpect(jsonPath("$.[*].existeAvariaSistemaTratamento").value(hasItem(DEFAULT_EXISTE_AVARIA_SISTEMA_TRATAMENTO.toString())))
            .andExpect(jsonPath("$.[*].existeMotivoAusenciaTratamento").value(hasItem(DEFAULT_EXISTE_MOTIVO_AUSENCIA_TRATAMENTO.toString())))
            .andExpect(jsonPath("$.[*].nmEquipamentosComAvaria").value(hasItem(DEFAULT_NM_EQUIPAMENTOS_COM_AVARIA.toString())))
            .andExpect(jsonPath("$.[*].caudalDoSistema").value(hasItem(DEFAULT_CAUDAL_DO_SISTEMA.intValue())))
            .andExpect(jsonPath("$.[*].qtdConsumoPercaptaLitrosHomemDia").value(hasItem(DEFAULT_QTD_CONSUMO_PERCAPTA_LITROS_HOMEM_DIA.intValue())))
            .andExpect(jsonPath("$.[*].qtdDotacaoPercapta").value(hasItem(DEFAULT_QTD_DOTACAO_PERCAPTA.intValue())))
            .andExpect(jsonPath("$.[*].qtdDiariaHorasServicoSistema").value(hasItem(DEFAULT_QTD_DIARIA_HORAS_SERVICO_SISTEMA.intValue())))
            .andExpect(jsonPath("$.[*].esquema").value(hasItem(DEFAULT_ESQUEMA.toString())))
            .andExpect(jsonPath("$.[*].nmModeloBombaManualUtilizada").value(hasItem(DEFAULT_NM_MODELO_BOMBA_MANUAL_UTILIZADA.toString())))
            .andExpect(jsonPath("$.[*].nmTpBombaEnergia").value(hasItem(DEFAULT_NM_TP_BOMBA_ENERGIA.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultSistemaAguaShouldNotBeFound(String filter) throws Exception {
        restSistemaAguaMockMvc.perform(get("/api/sistema-aguas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingSistemaAgua() throws Exception {
        // Get the sistemaAgua
        restSistemaAguaMockMvc.perform(get("/api/sistema-aguas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSistemaAgua() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);
        int databaseSizeBeforeUpdate = sistemaAguaRepository.findAll().size();

        // Update the sistemaAgua
        SistemaAgua updatedSistemaAgua = sistemaAguaRepository.findOne(sistemaAgua.getId());
        // Disconnect from session so that the updates on updatedSistemaAgua are not directly saved in db
        em.detach(updatedSistemaAgua);
        updatedSistemaAgua
            .idUsuario(UPDATED_ID_USUARIO)
            .nmInqueridor(UPDATED_NM_INQUERIDOR)
            .dtLancamento(UPDATED_DT_LANCAMENTO)
            .dtUltimaAlteracao(UPDATED_DT_ULTIMA_ALTERACAO)
            .nmLocalidade(UPDATED_NM_LOCALIDADE)
            .qtdPopulacaoActual(UPDATED_QTD_POPULACAO_ACTUAL)
            .qtdCasasLocalidade(UPDATED_QTD_CASAS_LOCALIDADE)
            .nmTpComunaAldeia(UPDATED_NM_TP_COMUNA_ALDEIA)
            .nmTpArea(UPDATED_NM_TP_AREA)
            .possuiSistemaAgua(UPDATED_POSSUI_SISTEMA_AGUA)
            .nmSistemaAgua(UPDATED_NM_SISTEMA_AGUA)
            .nmFonteAgua(UPDATED_NM_FONTE_AGUA)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE)
            .altitude(UPDATED_ALTITUDE)
            .nmTpFonte(UPDATED_NM_TP_FONTE)
            .nmFonteAguaUtilizada(UPDATED_NM_FONTE_AGUA_UTILIZADA)
            .nmTipoBomba(UPDATED_NM_TIPO_BOMBA)
            .qtdCasasAguaLigada(UPDATED_QTD_CASAS_AGUA_LIGADA)
            .qtdChafarisesFuncionando(UPDATED_QTD_CHAFARISES_FUNCIONANDO)
            .qtdContadoresLigados(UPDATED_QTD_CONTADORES_LIGADOS)
            .qtdBebedouros(UPDATED_QTD_BEBEDOUROS)
            .qtdHabitantesAcessoServicoAgua(UPDATED_QTD_HABITANTES_ACESSO_SERVICO_AGUA)
            .anoConstrucaoSistema(UPDATED_ANO_CONSTRUCAO_SISTEMA)
            .nmTpAvariaSistema(UPDATED_NM_TP_AVARIA_SISTEMA)
            .causaAvariaSistema(UPDATED_CAUSA_AVARIA_SISTEMA)
            .statusResolucao(UPDATED_STATUS_RESOLUCAO)
            .tempoServicoDisponivel(UPDATED_TEMPO_SERVICO_DISPONIVEL)
            .qtdDiametroCondutaAdutoraAguaBruta(UPDATED_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_BRUTA)
            .qtdComprimentoCondutaAdutoraAguaBruta(UPDATED_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_BRUTA)
            .qtdDiametroCondutaAdutoraAguaTratada(UPDATED_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_TRATADA)
            .qtdComprimentoCondutaAdutoraAguaTratada(UPDATED_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_TRATADA)
            .descMaterialUtilizadoCondutas(UPDATED_DESC_MATERIAL_UTILIZADO_CONDUTAS)
            .qtdReservatoriosApoiados(UPDATED_QTD_RESERVATORIOS_APOIADOS)
            .qtdCapacidadeReservatoriosApoiados(UPDATED_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS)
            .qtdReservatoriosElevados(UPDATED_QTD_RESERVATORIOS_ELEVADOS)
            .qtdCapacidadeReservatoriosElevados(UPDATED_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS)
            .alturaReservatoriosElevados(UPDATED_ALTURA_RESERVATORIOS_ELEVADOS)
            .nmTpTratamentoAgua(UPDATED_NM_TP_TRATAMENTO_AGUA)
            .nmTpTratamentoPadraoUtilizado(UPDATED_NM_TP_TRATAMENTO_PADRAO_UTILIZADO)
            .nmTpTratamentoBasicoUtilizado(UPDATED_NM_TP_TRATAMENTO_BASICO_UTILIZADO)
            .existeAvariaSistemaTratamento(UPDATED_EXISTE_AVARIA_SISTEMA_TRATAMENTO)
            .existeMotivoAusenciaTratamento(UPDATED_EXISTE_MOTIVO_AUSENCIA_TRATAMENTO)
            .nmEquipamentosComAvaria(UPDATED_NM_EQUIPAMENTOS_COM_AVARIA)
            .caudalDoSistema(UPDATED_CAUDAL_DO_SISTEMA)
            .qtdConsumoPercaptaLitrosHomemDia(UPDATED_QTD_CONSUMO_PERCAPTA_LITROS_HOMEM_DIA)
            .qtdDotacaoPercapta(UPDATED_QTD_DOTACAO_PERCAPTA)
            .qtdDiariaHorasServicoSistema(UPDATED_QTD_DIARIA_HORAS_SERVICO_SISTEMA)
            .esquema(UPDATED_ESQUEMA)
            .nmModeloBombaManualUtilizada(UPDATED_NM_MODELO_BOMBA_MANUAL_UTILIZADA)
            .nmTpBombaEnergia(UPDATED_NM_TP_BOMBA_ENERGIA);
        SistemaAguaDTO sistemaAguaDTO = sistemaAguaMapper.toDto(updatedSistemaAgua);

        restSistemaAguaMockMvc.perform(put("/api/sistema-aguas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaDTO)))
            .andExpect(status().isOk());

        // Validate the SistemaAgua in the database
        List<SistemaAgua> sistemaAguaList = sistemaAguaRepository.findAll();
        assertThat(sistemaAguaList).hasSize(databaseSizeBeforeUpdate);
        SistemaAgua testSistemaAgua = sistemaAguaList.get(sistemaAguaList.size() - 1);
        assertThat(testSistemaAgua.getIdUsuario()).isEqualTo(UPDATED_ID_USUARIO);
        assertThat(testSistemaAgua.getNmInqueridor()).isEqualTo(UPDATED_NM_INQUERIDOR);
        assertThat(testSistemaAgua.getDtLancamento()).isEqualTo(UPDATED_DT_LANCAMENTO);
        assertThat(testSistemaAgua.getDtUltimaAlteracao()).isEqualTo(UPDATED_DT_ULTIMA_ALTERACAO);
        assertThat(testSistemaAgua.getNmLocalidade()).isEqualTo(UPDATED_NM_LOCALIDADE);
        assertThat(testSistemaAgua.getQtdPopulacaoActual()).isEqualTo(UPDATED_QTD_POPULACAO_ACTUAL);
        assertThat(testSistemaAgua.getQtdCasasLocalidade()).isEqualTo(UPDATED_QTD_CASAS_LOCALIDADE);
        assertThat(testSistemaAgua.getNmTpComunaAldeia()).isEqualTo(UPDATED_NM_TP_COMUNA_ALDEIA);
        assertThat(testSistemaAgua.getNmTpArea()).isEqualTo(UPDATED_NM_TP_AREA);
        assertThat(testSistemaAgua.getPossuiSistemaAgua()).isEqualTo(UPDATED_POSSUI_SISTEMA_AGUA);
        assertThat(testSistemaAgua.getNmSistemaAgua()).isEqualTo(UPDATED_NM_SISTEMA_AGUA);
        assertThat(testSistemaAgua.getNmFonteAgua()).isEqualTo(UPDATED_NM_FONTE_AGUA);
        assertThat(testSistemaAgua.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testSistemaAgua.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testSistemaAgua.getAltitude()).isEqualTo(UPDATED_ALTITUDE);
        assertThat(testSistemaAgua.getNmTpFonte()).isEqualTo(UPDATED_NM_TP_FONTE);
        assertThat(testSistemaAgua.getNmFonteAguaUtilizada()).isEqualTo(UPDATED_NM_FONTE_AGUA_UTILIZADA);
        assertThat(testSistemaAgua.getNmTipoBomba()).isEqualTo(UPDATED_NM_TIPO_BOMBA);
        assertThat(testSistemaAgua.getQtdCasasAguaLigada()).isEqualTo(UPDATED_QTD_CASAS_AGUA_LIGADA);
        assertThat(testSistemaAgua.getQtdChafarisesFuncionando()).isEqualTo(UPDATED_QTD_CHAFARISES_FUNCIONANDO);
        assertThat(testSistemaAgua.getQtdContadoresLigados()).isEqualTo(UPDATED_QTD_CONTADORES_LIGADOS);
        assertThat(testSistemaAgua.getQtdBebedouros()).isEqualTo(UPDATED_QTD_BEBEDOUROS);
        assertThat(testSistemaAgua.getQtdHabitantesAcessoServicoAgua()).isEqualTo(UPDATED_QTD_HABITANTES_ACESSO_SERVICO_AGUA);
        assertThat(testSistemaAgua.getAnoConstrucaoSistema()).isEqualTo(UPDATED_ANO_CONSTRUCAO_SISTEMA);
        assertThat(testSistemaAgua.getNmTpAvariaSistema()).isEqualTo(UPDATED_NM_TP_AVARIA_SISTEMA);
        assertThat(testSistemaAgua.getCausaAvariaSistema()).isEqualTo(UPDATED_CAUSA_AVARIA_SISTEMA);
        assertThat(testSistemaAgua.getStatusResolucao()).isEqualTo(UPDATED_STATUS_RESOLUCAO);
        assertThat(testSistemaAgua.getTempoServicoDisponivel()).isEqualTo(UPDATED_TEMPO_SERVICO_DISPONIVEL);
        assertThat(testSistemaAgua.getQtdDiametroCondutaAdutoraAguaBruta()).isEqualTo(UPDATED_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_BRUTA);
        assertThat(testSistemaAgua.getQtdComprimentoCondutaAdutoraAguaBruta()).isEqualTo(UPDATED_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_BRUTA);
        assertThat(testSistemaAgua.getQtdDiametroCondutaAdutoraAguaTratada()).isEqualTo(UPDATED_QTD_DIAMETRO_CONDUTA_ADUTORA_AGUA_TRATADA);
        assertThat(testSistemaAgua.getQtdComprimentoCondutaAdutoraAguaTratada()).isEqualTo(UPDATED_QTD_COMPRIMENTO_CONDUTA_ADUTORA_AGUA_TRATADA);
        assertThat(testSistemaAgua.getDescMaterialUtilizadoCondutas()).isEqualTo(UPDATED_DESC_MATERIAL_UTILIZADO_CONDUTAS);
        assertThat(testSistemaAgua.getQtdReservatoriosApoiados()).isEqualTo(UPDATED_QTD_RESERVATORIOS_APOIADOS);
        assertThat(testSistemaAgua.getQtdCapacidadeReservatoriosApoiados()).isEqualTo(UPDATED_QTD_CAPACIDADE_RESERVATORIOS_APOIADOS);
        assertThat(testSistemaAgua.getQtdReservatoriosElevados()).isEqualTo(UPDATED_QTD_RESERVATORIOS_ELEVADOS);
        assertThat(testSistemaAgua.getQtdCapacidadeReservatoriosElevados()).isEqualTo(UPDATED_QTD_CAPACIDADE_RESERVATORIOS_ELEVADOS);
        assertThat(testSistemaAgua.getAlturaReservatoriosElevados()).isEqualTo(UPDATED_ALTURA_RESERVATORIOS_ELEVADOS);
        assertThat(testSistemaAgua.getNmTpTratamentoAgua()).isEqualTo(UPDATED_NM_TP_TRATAMENTO_AGUA);
        assertThat(testSistemaAgua.getNmTpTratamentoPadraoUtilizado()).isEqualTo(UPDATED_NM_TP_TRATAMENTO_PADRAO_UTILIZADO);
        assertThat(testSistemaAgua.getNmTpTratamentoBasicoUtilizado()).isEqualTo(UPDATED_NM_TP_TRATAMENTO_BASICO_UTILIZADO);
        assertThat(testSistemaAgua.getExisteAvariaSistemaTratamento()).isEqualTo(UPDATED_EXISTE_AVARIA_SISTEMA_TRATAMENTO);
        assertThat(testSistemaAgua.getExisteMotivoAusenciaTratamento()).isEqualTo(UPDATED_EXISTE_MOTIVO_AUSENCIA_TRATAMENTO);
        assertThat(testSistemaAgua.getNmEquipamentosComAvaria()).isEqualTo(UPDATED_NM_EQUIPAMENTOS_COM_AVARIA);
        assertThat(testSistemaAgua.getCaudalDoSistema()).isEqualTo(UPDATED_CAUDAL_DO_SISTEMA);
        assertThat(testSistemaAgua.getQtdConsumoPercaptaLitrosHomemDia()).isEqualTo(UPDATED_QTD_CONSUMO_PERCAPTA_LITROS_HOMEM_DIA);
        assertThat(testSistemaAgua.getQtdDotacaoPercapta()).isEqualTo(UPDATED_QTD_DOTACAO_PERCAPTA);
        assertThat(testSistemaAgua.getQtdDiariaHorasServicoSistema()).isEqualTo(UPDATED_QTD_DIARIA_HORAS_SERVICO_SISTEMA);
        assertThat(testSistemaAgua.getEsquema()).isEqualTo(UPDATED_ESQUEMA);
        assertThat(testSistemaAgua.getNmModeloBombaManualUtilizada()).isEqualTo(UPDATED_NM_MODELO_BOMBA_MANUAL_UTILIZADA);
        assertThat(testSistemaAgua.getNmTpBombaEnergia()).isEqualTo(UPDATED_NM_TP_BOMBA_ENERGIA);
    }

    @Test
    @Transactional
    public void updateNonExistingSistemaAgua() throws Exception {
        int databaseSizeBeforeUpdate = sistemaAguaRepository.findAll().size();

        // Create the SistemaAgua
        SistemaAguaDTO sistemaAguaDTO = sistemaAguaMapper.toDto(sistemaAgua);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSistemaAguaMockMvc.perform(put("/api/sistema-aguas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaDTO)))
            .andExpect(status().isCreated());

        // Validate the SistemaAgua in the database
        List<SistemaAgua> sistemaAguaList = sistemaAguaRepository.findAll();
        assertThat(sistemaAguaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSistemaAgua() throws Exception {
        // Initialize the database
        sistemaAguaRepository.saveAndFlush(sistemaAgua);
        int databaseSizeBeforeDelete = sistemaAguaRepository.findAll().size();

        // Get the sistemaAgua
        restSistemaAguaMockMvc.perform(delete("/api/sistema-aguas/{id}", sistemaAgua.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SistemaAgua> sistemaAguaList = sistemaAguaRepository.findAll();
        assertThat(sistemaAguaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SistemaAgua.class);
        SistemaAgua sistemaAgua1 = new SistemaAgua();
        sistemaAgua1.setId(1L);
        SistemaAgua sistemaAgua2 = new SistemaAgua();
        sistemaAgua2.setId(sistemaAgua1.getId());
        assertThat(sistemaAgua1).isEqualTo(sistemaAgua2);
        sistemaAgua2.setId(2L);
        assertThat(sistemaAgua1).isNotEqualTo(sistemaAgua2);
        sistemaAgua1.setId(null);
        assertThat(sistemaAgua1).isNotEqualTo(sistemaAgua2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SistemaAguaDTO.class);
        SistemaAguaDTO sistemaAguaDTO1 = new SistemaAguaDTO();
        sistemaAguaDTO1.setId(1L);
        SistemaAguaDTO sistemaAguaDTO2 = new SistemaAguaDTO();
        assertThat(sistemaAguaDTO1).isNotEqualTo(sistemaAguaDTO2);
        sistemaAguaDTO2.setId(sistemaAguaDTO1.getId());
        assertThat(sistemaAguaDTO1).isEqualTo(sistemaAguaDTO2);
        sistemaAguaDTO2.setId(2L);
        assertThat(sistemaAguaDTO1).isNotEqualTo(sistemaAguaDTO2);
        sistemaAguaDTO1.setId(null);
        assertThat(sistemaAguaDTO1).isNotEqualTo(sistemaAguaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sistemaAguaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sistemaAguaMapper.fromId(null)).isNull();
    }
}
