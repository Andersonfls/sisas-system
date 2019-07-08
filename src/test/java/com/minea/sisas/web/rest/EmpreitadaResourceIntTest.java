package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.Empreitada;
import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.domain.Contrato;
import com.minea.sisas.repository.EmpreitadaRepository;
import com.minea.sisas.service.EmpreitadaService;
import com.minea.sisas.service.dto.EmpreitadaDTO;
import com.minea.sisas.service.mapper.EmpreitadaMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.EmpreitadaQueryService;

import com.minea.sisas.web.rest.EmpreitadaResource;
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
 * Test class for the EmpreitadaResource REST controller.
 *
 * @see EmpreitadaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class EmpreitadaResourceIntTest {

    private static final Long DEFAULT_ID_EMPREITADA = 1L;
    private static final Long UPDATED_ID_EMPREITADA = 2L;

    private static final String DEFAULT_TIPO_EMPREITADA = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_EMPREITADA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DT_LANCAMENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_LANCAMENTO = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_NUM_CAPACIDADE_CAPTACAO = new BigDecimal(1);
    private static final BigDecimal UPDATED_NUM_CAPACIDADE_CAPTACAO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_NUM_CAPACIDADE_CAPTACAO_ETA = new BigDecimal(1);
    private static final BigDecimal UPDATED_NUM_CAPACIDADE_CAPTACAO_ETA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_NUM_EXTENSAO_COND_ADUT_MAT = new BigDecimal(1);
    private static final BigDecimal UPDATED_NUM_EXTENSAO_COND_ADUT_MAT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_NUM_CAPRMAZENAMENTO = new BigDecimal(1);
    private static final BigDecimal UPDATED_NUM_CAPRMAZENAMENTO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_NUM_EXTENSAO_REDE_MAT = new BigDecimal(1);
    private static final BigDecimal UPDATED_NUM_EXTENSAO_REDE_MAT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_NUM_LIGACOES_DOMICILIARES = new BigDecimal(1);
    private static final BigDecimal UPDATED_NUM_LIGACOES_DOMICILIARES = new BigDecimal(2);

    private static final BigDecimal DEFAULT_NUM_LIGACOES_TORNEIRA_QUINTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_NUM_LIGACOES_TORNEIRA_QUINTAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_NUM_CHAFARIS_NOVOS = new BigDecimal(1);
    private static final BigDecimal UPDATED_NUM_CHAFARIS_NOVOS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_NUM_CHAFARIS_REABILITAR = new BigDecimal(1);
    private static final BigDecimal UPDATED_NUM_CHAFARIS_REABILITAR = new BigDecimal(2);

    private static final BigDecimal DEFAULT_NUM_CAPACIDADE_TRATAMENTO_ETA = new BigDecimal(1);
    private static final BigDecimal UPDATED_NUM_CAPACIDADE_TRATAMENTO_ETA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_NUM_EXTENSAO_REDE_MATERIAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_NUM_EXTENSAO_REDE_MATERIAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_NUM_EXTENSAO_CONDUTAS_ELEL_MAT = new BigDecimal(1);
    private static final BigDecimal UPDATED_NUM_EXTENSAO_CONDUTAS_ELEL_MAT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_NUM_LIGACOES = new BigDecimal(1);
    private static final BigDecimal UPDATED_NUM_LIGACOES = new BigDecimal(2);

    private static final BigDecimal DEFAULT_NUM_CAIXAS_VISITAS = new BigDecimal(1);
    private static final BigDecimal UPDATED_NUM_CAIXAS_VISITAS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_NUM_ESTACOES_ELEVATORIAS = new BigDecimal(1);
    private static final BigDecimal UPDATED_NUM_ESTACOES_ELEVATORIAS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_NUM_LATRINAS = new BigDecimal(1);
    private static final BigDecimal UPDATED_NUM_LATRINAS = new BigDecimal(2);

    @Autowired
    private EmpreitadaRepository empreitadaRepository;

    @Autowired
    private EmpreitadaMapper empreitadaMapper;

    @Autowired
    private EmpreitadaService empreitadaService;

    @Autowired
    private EmpreitadaQueryService empreitadaQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEmpreitadaMockMvc;

    private Empreitada empreitada;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EmpreitadaResource empreitadaResource = new EmpreitadaResource(empreitadaService, empreitadaQueryService);
        this.restEmpreitadaMockMvc = MockMvcBuilders.standaloneSetup(empreitadaResource)
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
    public static Empreitada createEntity(EntityManager em) {
        Empreitada empreitada = new Empreitada()
            .idEmpreitada(DEFAULT_ID_EMPREITADA)
            .tipoEmpreitada(DEFAULT_TIPO_EMPREITADA)
            .dtLancamento(DEFAULT_DT_LANCAMENTO)
            .numCapacidadeCaptacao(DEFAULT_NUM_CAPACIDADE_CAPTACAO)
            .numCapacidadeCaptacaoEta(DEFAULT_NUM_CAPACIDADE_CAPTACAO_ETA)
            .numExtensaoCondAdutMat(DEFAULT_NUM_EXTENSAO_COND_ADUT_MAT)
            .numCaprmazenamento(DEFAULT_NUM_CAPRMAZENAMENTO)
            .numExtensaoRedeMat(DEFAULT_NUM_EXTENSAO_REDE_MAT)
            .numLigacoesDomiciliares(DEFAULT_NUM_LIGACOES_DOMICILIARES)
            .numLigacoesTorneiraQuintal(DEFAULT_NUM_LIGACOES_TORNEIRA_QUINTAL)
            .numChafarisNovos(DEFAULT_NUM_CHAFARIS_NOVOS)
            .numChafarisReabilitar(DEFAULT_NUM_CHAFARIS_REABILITAR)
            .numCapacidadeTratamentoEta(DEFAULT_NUM_CAPACIDADE_TRATAMENTO_ETA)
            .numExtensaoRedeMaterial(DEFAULT_NUM_EXTENSAO_REDE_MATERIAL)
            .numExtensaoCondutasElelMat(DEFAULT_NUM_EXTENSAO_CONDUTAS_ELEL_MAT)
            .numLigacoes(DEFAULT_NUM_LIGACOES)
            .numCaixasVisitas(DEFAULT_NUM_CAIXAS_VISITAS)
            .numEstacoesElevatorias(DEFAULT_NUM_ESTACOES_ELEVATORIAS)
            .numLatrinas(DEFAULT_NUM_LATRINAS);
        // Add required entity
        ProgramasProjectos idProgramasProjectos = ProgramasProjectosResourceIntTest.createEntity(em);
        em.persist(idProgramasProjectos);
        em.flush();
        empreitada.setIdProgramasProjectos(idProgramasProjectos);
        return empreitada;
    }

    @Before
    public void initTest() {
        empreitada = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmpreitada() throws Exception {
        int databaseSizeBeforeCreate = empreitadaRepository.findAll().size();

        // Create the Empreitada
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(empreitada);
        restEmpreitadaMockMvc.perform(post("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isCreated());

        // Validate the Empreitada in the database
        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeCreate + 1);
        Empreitada testEmpreitada = empreitadaList.get(empreitadaList.size() - 1);
        assertThat(testEmpreitada.getIdEmpreitada()).isEqualTo(DEFAULT_ID_EMPREITADA);
        assertThat(testEmpreitada.getTipoEmpreitada()).isEqualTo(DEFAULT_TIPO_EMPREITADA);
        assertThat(testEmpreitada.getDtLancamento()).isEqualTo(DEFAULT_DT_LANCAMENTO);
        assertThat(testEmpreitada.getNumCapacidadeCaptacao()).isEqualTo(DEFAULT_NUM_CAPACIDADE_CAPTACAO);
        assertThat(testEmpreitada.getNumCapacidadeCaptacaoEta()).isEqualTo(DEFAULT_NUM_CAPACIDADE_CAPTACAO_ETA);
        assertThat(testEmpreitada.getNumExtensaoCondAdutMat()).isEqualTo(DEFAULT_NUM_EXTENSAO_COND_ADUT_MAT);
        assertThat(testEmpreitada.getNumCaprmazenamento()).isEqualTo(DEFAULT_NUM_CAPRMAZENAMENTO);
        assertThat(testEmpreitada.getNumExtensaoRedeMat()).isEqualTo(DEFAULT_NUM_EXTENSAO_REDE_MAT);
        assertThat(testEmpreitada.getNumLigacoesDomiciliares()).isEqualTo(DEFAULT_NUM_LIGACOES_DOMICILIARES);
        assertThat(testEmpreitada.getNumLigacoesTorneiraQuintal()).isEqualTo(DEFAULT_NUM_LIGACOES_TORNEIRA_QUINTAL);
        assertThat(testEmpreitada.getNumChafarisNovos()).isEqualTo(DEFAULT_NUM_CHAFARIS_NOVOS);
        assertThat(testEmpreitada.getNumChafarisReabilitar()).isEqualTo(DEFAULT_NUM_CHAFARIS_REABILITAR);
        assertThat(testEmpreitada.getNumCapacidadeTratamentoEta()).isEqualTo(DEFAULT_NUM_CAPACIDADE_TRATAMENTO_ETA);
        assertThat(testEmpreitada.getNumExtensaoRedeMaterial()).isEqualTo(DEFAULT_NUM_EXTENSAO_REDE_MATERIAL);
        assertThat(testEmpreitada.getNumExtensaoCondutasElelMat()).isEqualTo(DEFAULT_NUM_EXTENSAO_CONDUTAS_ELEL_MAT);
        assertThat(testEmpreitada.getNumLigacoes()).isEqualTo(DEFAULT_NUM_LIGACOES);
        assertThat(testEmpreitada.getNumCaixasVisitas()).isEqualTo(DEFAULT_NUM_CAIXAS_VISITAS);
        assertThat(testEmpreitada.getNumEstacoesElevatorias()).isEqualTo(DEFAULT_NUM_ESTACOES_ELEVATORIAS);
        assertThat(testEmpreitada.getNumLatrinas()).isEqualTo(DEFAULT_NUM_LATRINAS);
    }

    @Test
    @Transactional
    public void createEmpreitadaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = empreitadaRepository.findAll().size();

        // Create the Empreitada with an existing ID
        empreitada.setId(1L);
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(empreitada);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmpreitadaMockMvc.perform(post("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Empreitada in the database
        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdEmpreitadaIsRequired() throws Exception {
        int databaseSizeBeforeTest = empreitadaRepository.findAll().size();
        // set the field null
        empreitada.setIdEmpreitada(null);

        // Create the Empreitada, which fails.
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(empreitada);

        restEmpreitadaMockMvc.perform(post("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isBadRequest());

        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTipoEmpreitadaIsRequired() throws Exception {
        int databaseSizeBeforeTest = empreitadaRepository.findAll().size();
        // set the field null
        empreitada.setTipoEmpreitada(null);

        // Create the Empreitada, which fails.
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(empreitada);

        restEmpreitadaMockMvc.perform(post("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isBadRequest());

        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDtLancamentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = empreitadaRepository.findAll().size();
        // set the field null
        empreitada.setDtLancamento(null);

        // Create the Empreitada, which fails.
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(empreitada);

        restEmpreitadaMockMvc.perform(post("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isBadRequest());

        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumCapacidadeCaptacaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = empreitadaRepository.findAll().size();
        // set the field null
        empreitada.setNumCapacidadeCaptacao(null);

        // Create the Empreitada, which fails.
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(empreitada);

        restEmpreitadaMockMvc.perform(post("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isBadRequest());

        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumCapacidadeCaptacaoEtaIsRequired() throws Exception {
        int databaseSizeBeforeTest = empreitadaRepository.findAll().size();
        // set the field null
        empreitada.setNumCapacidadeCaptacaoEta(null);

        // Create the Empreitada, which fails.
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(empreitada);

        restEmpreitadaMockMvc.perform(post("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isBadRequest());

        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumExtensaoCondAdutMatIsRequired() throws Exception {
        int databaseSizeBeforeTest = empreitadaRepository.findAll().size();
        // set the field null
        empreitada.setNumExtensaoCondAdutMat(null);

        // Create the Empreitada, which fails.
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(empreitada);

        restEmpreitadaMockMvc.perform(post("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isBadRequest());

        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumCaprmazenamentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = empreitadaRepository.findAll().size();
        // set the field null
        empreitada.setNumCaprmazenamento(null);

        // Create the Empreitada, which fails.
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(empreitada);

        restEmpreitadaMockMvc.perform(post("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isBadRequest());

        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumExtensaoRedeMatIsRequired() throws Exception {
        int databaseSizeBeforeTest = empreitadaRepository.findAll().size();
        // set the field null
        empreitada.setNumExtensaoRedeMat(null);

        // Create the Empreitada, which fails.
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(empreitada);

        restEmpreitadaMockMvc.perform(post("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isBadRequest());

        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumLigacoesDomiciliaresIsRequired() throws Exception {
        int databaseSizeBeforeTest = empreitadaRepository.findAll().size();
        // set the field null
        empreitada.setNumLigacoesDomiciliares(null);

        // Create the Empreitada, which fails.
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(empreitada);

        restEmpreitadaMockMvc.perform(post("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isBadRequest());

        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumLigacoesTorneiraQuintalIsRequired() throws Exception {
        int databaseSizeBeforeTest = empreitadaRepository.findAll().size();
        // set the field null
        empreitada.setNumLigacoesTorneiraQuintal(null);

        // Create the Empreitada, which fails.
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(empreitada);

        restEmpreitadaMockMvc.perform(post("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isBadRequest());

        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumChafarisNovosIsRequired() throws Exception {
        int databaseSizeBeforeTest = empreitadaRepository.findAll().size();
        // set the field null
        empreitada.setNumChafarisNovos(null);

        // Create the Empreitada, which fails.
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(empreitada);

        restEmpreitadaMockMvc.perform(post("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isBadRequest());

        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumChafarisReabilitarIsRequired() throws Exception {
        int databaseSizeBeforeTest = empreitadaRepository.findAll().size();
        // set the field null
        empreitada.setNumChafarisReabilitar(null);

        // Create the Empreitada, which fails.
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(empreitada);

        restEmpreitadaMockMvc.perform(post("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isBadRequest());

        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumCapacidadeTratamentoEtaIsRequired() throws Exception {
        int databaseSizeBeforeTest = empreitadaRepository.findAll().size();
        // set the field null
        empreitada.setNumCapacidadeTratamentoEta(null);

        // Create the Empreitada, which fails.
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(empreitada);

        restEmpreitadaMockMvc.perform(post("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isBadRequest());

        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumExtensaoRedeMaterialIsRequired() throws Exception {
        int databaseSizeBeforeTest = empreitadaRepository.findAll().size();
        // set the field null
        empreitada.setNumExtensaoRedeMaterial(null);

        // Create the Empreitada, which fails.
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(empreitada);

        restEmpreitadaMockMvc.perform(post("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isBadRequest());

        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumExtensaoCondutasElelMatIsRequired() throws Exception {
        int databaseSizeBeforeTest = empreitadaRepository.findAll().size();
        // set the field null
        empreitada.setNumExtensaoCondutasElelMat(null);

        // Create the Empreitada, which fails.
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(empreitada);

        restEmpreitadaMockMvc.perform(post("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isBadRequest());

        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumLigacoesIsRequired() throws Exception {
        int databaseSizeBeforeTest = empreitadaRepository.findAll().size();
        // set the field null
        empreitada.setNumLigacoes(null);

        // Create the Empreitada, which fails.
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(empreitada);

        restEmpreitadaMockMvc.perform(post("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isBadRequest());

        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumCaixasVisitasIsRequired() throws Exception {
        int databaseSizeBeforeTest = empreitadaRepository.findAll().size();
        // set the field null
        empreitada.setNumCaixasVisitas(null);

        // Create the Empreitada, which fails.
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(empreitada);

        restEmpreitadaMockMvc.perform(post("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isBadRequest());

        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumEstacoesElevatoriasIsRequired() throws Exception {
        int databaseSizeBeforeTest = empreitadaRepository.findAll().size();
        // set the field null
        empreitada.setNumEstacoesElevatorias(null);

        // Create the Empreitada, which fails.
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(empreitada);

        restEmpreitadaMockMvc.perform(post("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isBadRequest());

        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumLatrinasIsRequired() throws Exception {
        int databaseSizeBeforeTest = empreitadaRepository.findAll().size();
        // set the field null
        empreitada.setNumLatrinas(null);

        // Create the Empreitada, which fails.
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(empreitada);

        restEmpreitadaMockMvc.perform(post("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isBadRequest());

        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEmpreitadas() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList
        restEmpreitadaMockMvc.perform(get("/api/empreitadas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(empreitada.getId().intValue())))
            .andExpect(jsonPath("$.[*].idEmpreitada").value(hasItem(DEFAULT_ID_EMPREITADA.intValue())))
            .andExpect(jsonPath("$.[*].tipoEmpreitada").value(hasItem(DEFAULT_TIPO_EMPREITADA.toString())))
            .andExpect(jsonPath("$.[*].dtLancamento").value(hasItem(DEFAULT_DT_LANCAMENTO.toString())))
            .andExpect(jsonPath("$.[*].numCapacidadeCaptacao").value(hasItem(DEFAULT_NUM_CAPACIDADE_CAPTACAO.intValue())))
            .andExpect(jsonPath("$.[*].numCapacidadeCaptacaoEta").value(hasItem(DEFAULT_NUM_CAPACIDADE_CAPTACAO_ETA.intValue())))
            .andExpect(jsonPath("$.[*].numExtensaoCondAdutMat").value(hasItem(DEFAULT_NUM_EXTENSAO_COND_ADUT_MAT.intValue())))
            .andExpect(jsonPath("$.[*].numCaprmazenamento").value(hasItem(DEFAULT_NUM_CAPRMAZENAMENTO.intValue())))
            .andExpect(jsonPath("$.[*].numExtensaoRedeMat").value(hasItem(DEFAULT_NUM_EXTENSAO_REDE_MAT.intValue())))
            .andExpect(jsonPath("$.[*].numLigacoesDomiciliares").value(hasItem(DEFAULT_NUM_LIGACOES_DOMICILIARES.intValue())))
            .andExpect(jsonPath("$.[*].numLigacoesTorneiraQuintal").value(hasItem(DEFAULT_NUM_LIGACOES_TORNEIRA_QUINTAL.intValue())))
            .andExpect(jsonPath("$.[*].numChafarisNovos").value(hasItem(DEFAULT_NUM_CHAFARIS_NOVOS.intValue())))
            .andExpect(jsonPath("$.[*].numChafarisReabilitar").value(hasItem(DEFAULT_NUM_CHAFARIS_REABILITAR.intValue())))
            .andExpect(jsonPath("$.[*].numCapacidadeTratamentoEta").value(hasItem(DEFAULT_NUM_CAPACIDADE_TRATAMENTO_ETA.intValue())))
            .andExpect(jsonPath("$.[*].numExtensaoRedeMaterial").value(hasItem(DEFAULT_NUM_EXTENSAO_REDE_MATERIAL.intValue())))
            .andExpect(jsonPath("$.[*].numExtensaoCondutasElelMat").value(hasItem(DEFAULT_NUM_EXTENSAO_CONDUTAS_ELEL_MAT.intValue())))
            .andExpect(jsonPath("$.[*].numLigacoes").value(hasItem(DEFAULT_NUM_LIGACOES.intValue())))
            .andExpect(jsonPath("$.[*].numCaixasVisitas").value(hasItem(DEFAULT_NUM_CAIXAS_VISITAS.intValue())))
            .andExpect(jsonPath("$.[*].numEstacoesElevatorias").value(hasItem(DEFAULT_NUM_ESTACOES_ELEVATORIAS.intValue())))
            .andExpect(jsonPath("$.[*].numLatrinas").value(hasItem(DEFAULT_NUM_LATRINAS.intValue())));
    }

    @Test
    @Transactional
    public void getEmpreitada() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get the empreitada
        restEmpreitadaMockMvc.perform(get("/api/empreitadas/{id}", empreitada.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(empreitada.getId().intValue()))
            .andExpect(jsonPath("$.idEmpreitada").value(DEFAULT_ID_EMPREITADA.intValue()))
            .andExpect(jsonPath("$.tipoEmpreitada").value(DEFAULT_TIPO_EMPREITADA.toString()))
            .andExpect(jsonPath("$.dtLancamento").value(DEFAULT_DT_LANCAMENTO.toString()))
            .andExpect(jsonPath("$.numCapacidadeCaptacao").value(DEFAULT_NUM_CAPACIDADE_CAPTACAO.intValue()))
            .andExpect(jsonPath("$.numCapacidadeCaptacaoEta").value(DEFAULT_NUM_CAPACIDADE_CAPTACAO_ETA.intValue()))
            .andExpect(jsonPath("$.numExtensaoCondAdutMat").value(DEFAULT_NUM_EXTENSAO_COND_ADUT_MAT.intValue()))
            .andExpect(jsonPath("$.numCaprmazenamento").value(DEFAULT_NUM_CAPRMAZENAMENTO.intValue()))
            .andExpect(jsonPath("$.numExtensaoRedeMat").value(DEFAULT_NUM_EXTENSAO_REDE_MAT.intValue()))
            .andExpect(jsonPath("$.numLigacoesDomiciliares").value(DEFAULT_NUM_LIGACOES_DOMICILIARES.intValue()))
            .andExpect(jsonPath("$.numLigacoesTorneiraQuintal").value(DEFAULT_NUM_LIGACOES_TORNEIRA_QUINTAL.intValue()))
            .andExpect(jsonPath("$.numChafarisNovos").value(DEFAULT_NUM_CHAFARIS_NOVOS.intValue()))
            .andExpect(jsonPath("$.numChafarisReabilitar").value(DEFAULT_NUM_CHAFARIS_REABILITAR.intValue()))
            .andExpect(jsonPath("$.numCapacidadeTratamentoEta").value(DEFAULT_NUM_CAPACIDADE_TRATAMENTO_ETA.intValue()))
            .andExpect(jsonPath("$.numExtensaoRedeMaterial").value(DEFAULT_NUM_EXTENSAO_REDE_MATERIAL.intValue()))
            .andExpect(jsonPath("$.numExtensaoCondutasElelMat").value(DEFAULT_NUM_EXTENSAO_CONDUTAS_ELEL_MAT.intValue()))
            .andExpect(jsonPath("$.numLigacoes").value(DEFAULT_NUM_LIGACOES.intValue()))
            .andExpect(jsonPath("$.numCaixasVisitas").value(DEFAULT_NUM_CAIXAS_VISITAS.intValue()))
            .andExpect(jsonPath("$.numEstacoesElevatorias").value(DEFAULT_NUM_ESTACOES_ELEVATORIAS.intValue()))
            .andExpect(jsonPath("$.numLatrinas").value(DEFAULT_NUM_LATRINAS.intValue()));
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByIdEmpreitadaIsEqualToSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where idEmpreitada equals to DEFAULT_ID_EMPREITADA
        defaultEmpreitadaShouldBeFound("idEmpreitada.equals=" + DEFAULT_ID_EMPREITADA);

        // Get all the empreitadaList where idEmpreitada equals to UPDATED_ID_EMPREITADA
        defaultEmpreitadaShouldNotBeFound("idEmpreitada.equals=" + UPDATED_ID_EMPREITADA);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByIdEmpreitadaIsInShouldWork() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where idEmpreitada in DEFAULT_ID_EMPREITADA or UPDATED_ID_EMPREITADA
        defaultEmpreitadaShouldBeFound("idEmpreitada.in=" + DEFAULT_ID_EMPREITADA + "," + UPDATED_ID_EMPREITADA);

        // Get all the empreitadaList where idEmpreitada equals to UPDATED_ID_EMPREITADA
        defaultEmpreitadaShouldNotBeFound("idEmpreitada.in=" + UPDATED_ID_EMPREITADA);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByIdEmpreitadaIsNullOrNotNull() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where idEmpreitada is not null
        defaultEmpreitadaShouldBeFound("idEmpreitada.specified=true");

        // Get all the empreitadaList where idEmpreitada is null
        defaultEmpreitadaShouldNotBeFound("idEmpreitada.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByIdEmpreitadaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where idEmpreitada greater than or equals to DEFAULT_ID_EMPREITADA
        defaultEmpreitadaShouldBeFound("idEmpreitada.greaterOrEqualThan=" + DEFAULT_ID_EMPREITADA);

        // Get all the empreitadaList where idEmpreitada greater than or equals to UPDATED_ID_EMPREITADA
        defaultEmpreitadaShouldNotBeFound("idEmpreitada.greaterOrEqualThan=" + UPDATED_ID_EMPREITADA);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByIdEmpreitadaIsLessThanSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where idEmpreitada less than or equals to DEFAULT_ID_EMPREITADA
        defaultEmpreitadaShouldNotBeFound("idEmpreitada.lessThan=" + DEFAULT_ID_EMPREITADA);

        // Get all the empreitadaList where idEmpreitada less than or equals to UPDATED_ID_EMPREITADA
        defaultEmpreitadaShouldBeFound("idEmpreitada.lessThan=" + UPDATED_ID_EMPREITADA);
    }


    @Test
    @Transactional
    public void getAllEmpreitadasByTipoEmpreitadaIsEqualToSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where tipoEmpreitada equals to DEFAULT_TIPO_EMPREITADA
        defaultEmpreitadaShouldBeFound("tipoEmpreitada.equals=" + DEFAULT_TIPO_EMPREITADA);

        // Get all the empreitadaList where tipoEmpreitada equals to UPDATED_TIPO_EMPREITADA
        defaultEmpreitadaShouldNotBeFound("tipoEmpreitada.equals=" + UPDATED_TIPO_EMPREITADA);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByTipoEmpreitadaIsInShouldWork() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where tipoEmpreitada in DEFAULT_TIPO_EMPREITADA or UPDATED_TIPO_EMPREITADA
        defaultEmpreitadaShouldBeFound("tipoEmpreitada.in=" + DEFAULT_TIPO_EMPREITADA + "," + UPDATED_TIPO_EMPREITADA);

        // Get all the empreitadaList where tipoEmpreitada equals to UPDATED_TIPO_EMPREITADA
        defaultEmpreitadaShouldNotBeFound("tipoEmpreitada.in=" + UPDATED_TIPO_EMPREITADA);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByTipoEmpreitadaIsNullOrNotNull() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where tipoEmpreitada is not null
        defaultEmpreitadaShouldBeFound("tipoEmpreitada.specified=true");

        // Get all the empreitadaList where tipoEmpreitada is null
        defaultEmpreitadaShouldNotBeFound("tipoEmpreitada.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByDtLancamentoIsEqualToSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where dtLancamento equals to DEFAULT_DT_LANCAMENTO
        defaultEmpreitadaShouldBeFound("dtLancamento.equals=" + DEFAULT_DT_LANCAMENTO);

        // Get all the empreitadaList where dtLancamento equals to UPDATED_DT_LANCAMENTO
        defaultEmpreitadaShouldNotBeFound("dtLancamento.equals=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByDtLancamentoIsInShouldWork() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where dtLancamento in DEFAULT_DT_LANCAMENTO or UPDATED_DT_LANCAMENTO
        defaultEmpreitadaShouldBeFound("dtLancamento.in=" + DEFAULT_DT_LANCAMENTO + "," + UPDATED_DT_LANCAMENTO);

        // Get all the empreitadaList where dtLancamento equals to UPDATED_DT_LANCAMENTO
        defaultEmpreitadaShouldNotBeFound("dtLancamento.in=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByDtLancamentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where dtLancamento is not null
        defaultEmpreitadaShouldBeFound("dtLancamento.specified=true");

        // Get all the empreitadaList where dtLancamento is null
        defaultEmpreitadaShouldNotBeFound("dtLancamento.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByDtLancamentoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where dtLancamento greater than or equals to DEFAULT_DT_LANCAMENTO
        defaultEmpreitadaShouldBeFound("dtLancamento.greaterOrEqualThan=" + DEFAULT_DT_LANCAMENTO);

        // Get all the empreitadaList where dtLancamento greater than or equals to UPDATED_DT_LANCAMENTO
        defaultEmpreitadaShouldNotBeFound("dtLancamento.greaterOrEqualThan=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByDtLancamentoIsLessThanSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where dtLancamento less than or equals to DEFAULT_DT_LANCAMENTO
        defaultEmpreitadaShouldNotBeFound("dtLancamento.lessThan=" + DEFAULT_DT_LANCAMENTO);

        // Get all the empreitadaList where dtLancamento less than or equals to UPDATED_DT_LANCAMENTO
        defaultEmpreitadaShouldBeFound("dtLancamento.lessThan=" + UPDATED_DT_LANCAMENTO);
    }


    @Test
    @Transactional
    public void getAllEmpreitadasByNumCapacidadeCaptacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numCapacidadeCaptacao equals to DEFAULT_NUM_CAPACIDADE_CAPTACAO
        defaultEmpreitadaShouldBeFound("numCapacidadeCaptacao.equals=" + DEFAULT_NUM_CAPACIDADE_CAPTACAO);

        // Get all the empreitadaList where numCapacidadeCaptacao equals to UPDATED_NUM_CAPACIDADE_CAPTACAO
        defaultEmpreitadaShouldNotBeFound("numCapacidadeCaptacao.equals=" + UPDATED_NUM_CAPACIDADE_CAPTACAO);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumCapacidadeCaptacaoIsInShouldWork() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numCapacidadeCaptacao in DEFAULT_NUM_CAPACIDADE_CAPTACAO or UPDATED_NUM_CAPACIDADE_CAPTACAO
        defaultEmpreitadaShouldBeFound("numCapacidadeCaptacao.in=" + DEFAULT_NUM_CAPACIDADE_CAPTACAO + "," + UPDATED_NUM_CAPACIDADE_CAPTACAO);

        // Get all the empreitadaList where numCapacidadeCaptacao equals to UPDATED_NUM_CAPACIDADE_CAPTACAO
        defaultEmpreitadaShouldNotBeFound("numCapacidadeCaptacao.in=" + UPDATED_NUM_CAPACIDADE_CAPTACAO);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumCapacidadeCaptacaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numCapacidadeCaptacao is not null
        defaultEmpreitadaShouldBeFound("numCapacidadeCaptacao.specified=true");

        // Get all the empreitadaList where numCapacidadeCaptacao is null
        defaultEmpreitadaShouldNotBeFound("numCapacidadeCaptacao.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumCapacidadeCaptacaoEtaIsEqualToSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numCapacidadeCaptacaoEta equals to DEFAULT_NUM_CAPACIDADE_CAPTACAO_ETA
        defaultEmpreitadaShouldBeFound("numCapacidadeCaptacaoEta.equals=" + DEFAULT_NUM_CAPACIDADE_CAPTACAO_ETA);

        // Get all the empreitadaList where numCapacidadeCaptacaoEta equals to UPDATED_NUM_CAPACIDADE_CAPTACAO_ETA
        defaultEmpreitadaShouldNotBeFound("numCapacidadeCaptacaoEta.equals=" + UPDATED_NUM_CAPACIDADE_CAPTACAO_ETA);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumCapacidadeCaptacaoEtaIsInShouldWork() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numCapacidadeCaptacaoEta in DEFAULT_NUM_CAPACIDADE_CAPTACAO_ETA or UPDATED_NUM_CAPACIDADE_CAPTACAO_ETA
        defaultEmpreitadaShouldBeFound("numCapacidadeCaptacaoEta.in=" + DEFAULT_NUM_CAPACIDADE_CAPTACAO_ETA + "," + UPDATED_NUM_CAPACIDADE_CAPTACAO_ETA);

        // Get all the empreitadaList where numCapacidadeCaptacaoEta equals to UPDATED_NUM_CAPACIDADE_CAPTACAO_ETA
        defaultEmpreitadaShouldNotBeFound("numCapacidadeCaptacaoEta.in=" + UPDATED_NUM_CAPACIDADE_CAPTACAO_ETA);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumCapacidadeCaptacaoEtaIsNullOrNotNull() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numCapacidadeCaptacaoEta is not null
        defaultEmpreitadaShouldBeFound("numCapacidadeCaptacaoEta.specified=true");

        // Get all the empreitadaList where numCapacidadeCaptacaoEta is null
        defaultEmpreitadaShouldNotBeFound("numCapacidadeCaptacaoEta.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumExtensaoCondAdutMatIsEqualToSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numExtensaoCondAdutMat equals to DEFAULT_NUM_EXTENSAO_COND_ADUT_MAT
        defaultEmpreitadaShouldBeFound("numExtensaoCondAdutMat.equals=" + DEFAULT_NUM_EXTENSAO_COND_ADUT_MAT);

        // Get all the empreitadaList where numExtensaoCondAdutMat equals to UPDATED_NUM_EXTENSAO_COND_ADUT_MAT
        defaultEmpreitadaShouldNotBeFound("numExtensaoCondAdutMat.equals=" + UPDATED_NUM_EXTENSAO_COND_ADUT_MAT);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumExtensaoCondAdutMatIsInShouldWork() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numExtensaoCondAdutMat in DEFAULT_NUM_EXTENSAO_COND_ADUT_MAT or UPDATED_NUM_EXTENSAO_COND_ADUT_MAT
        defaultEmpreitadaShouldBeFound("numExtensaoCondAdutMat.in=" + DEFAULT_NUM_EXTENSAO_COND_ADUT_MAT + "," + UPDATED_NUM_EXTENSAO_COND_ADUT_MAT);

        // Get all the empreitadaList where numExtensaoCondAdutMat equals to UPDATED_NUM_EXTENSAO_COND_ADUT_MAT
        defaultEmpreitadaShouldNotBeFound("numExtensaoCondAdutMat.in=" + UPDATED_NUM_EXTENSAO_COND_ADUT_MAT);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumExtensaoCondAdutMatIsNullOrNotNull() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numExtensaoCondAdutMat is not null
        defaultEmpreitadaShouldBeFound("numExtensaoCondAdutMat.specified=true");

        // Get all the empreitadaList where numExtensaoCondAdutMat is null
        defaultEmpreitadaShouldNotBeFound("numExtensaoCondAdutMat.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumCaprmazenamentoIsEqualToSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numCaprmazenamento equals to DEFAULT_NUM_CAPRMAZENAMENTO
        defaultEmpreitadaShouldBeFound("numCaprmazenamento.equals=" + DEFAULT_NUM_CAPRMAZENAMENTO);

        // Get all the empreitadaList where numCaprmazenamento equals to UPDATED_NUM_CAPRMAZENAMENTO
        defaultEmpreitadaShouldNotBeFound("numCaprmazenamento.equals=" + UPDATED_NUM_CAPRMAZENAMENTO);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumCaprmazenamentoIsInShouldWork() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numCaprmazenamento in DEFAULT_NUM_CAPRMAZENAMENTO or UPDATED_NUM_CAPRMAZENAMENTO
        defaultEmpreitadaShouldBeFound("numCaprmazenamento.in=" + DEFAULT_NUM_CAPRMAZENAMENTO + "," + UPDATED_NUM_CAPRMAZENAMENTO);

        // Get all the empreitadaList where numCaprmazenamento equals to UPDATED_NUM_CAPRMAZENAMENTO
        defaultEmpreitadaShouldNotBeFound("numCaprmazenamento.in=" + UPDATED_NUM_CAPRMAZENAMENTO);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumCaprmazenamentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numCaprmazenamento is not null
        defaultEmpreitadaShouldBeFound("numCaprmazenamento.specified=true");

        // Get all the empreitadaList where numCaprmazenamento is null
        defaultEmpreitadaShouldNotBeFound("numCaprmazenamento.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumExtensaoRedeMatIsEqualToSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numExtensaoRedeMat equals to DEFAULT_NUM_EXTENSAO_REDE_MAT
        defaultEmpreitadaShouldBeFound("numExtensaoRedeMat.equals=" + DEFAULT_NUM_EXTENSAO_REDE_MAT);

        // Get all the empreitadaList where numExtensaoRedeMat equals to UPDATED_NUM_EXTENSAO_REDE_MAT
        defaultEmpreitadaShouldNotBeFound("numExtensaoRedeMat.equals=" + UPDATED_NUM_EXTENSAO_REDE_MAT);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumExtensaoRedeMatIsInShouldWork() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numExtensaoRedeMat in DEFAULT_NUM_EXTENSAO_REDE_MAT or UPDATED_NUM_EXTENSAO_REDE_MAT
        defaultEmpreitadaShouldBeFound("numExtensaoRedeMat.in=" + DEFAULT_NUM_EXTENSAO_REDE_MAT + "," + UPDATED_NUM_EXTENSAO_REDE_MAT);

        // Get all the empreitadaList where numExtensaoRedeMat equals to UPDATED_NUM_EXTENSAO_REDE_MAT
        defaultEmpreitadaShouldNotBeFound("numExtensaoRedeMat.in=" + UPDATED_NUM_EXTENSAO_REDE_MAT);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumExtensaoRedeMatIsNullOrNotNull() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numExtensaoRedeMat is not null
        defaultEmpreitadaShouldBeFound("numExtensaoRedeMat.specified=true");

        // Get all the empreitadaList where numExtensaoRedeMat is null
        defaultEmpreitadaShouldNotBeFound("numExtensaoRedeMat.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumLigacoesDomiciliaresIsEqualToSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numLigacoesDomiciliares equals to DEFAULT_NUM_LIGACOES_DOMICILIARES
        defaultEmpreitadaShouldBeFound("numLigacoesDomiciliares.equals=" + DEFAULT_NUM_LIGACOES_DOMICILIARES);

        // Get all the empreitadaList where numLigacoesDomiciliares equals to UPDATED_NUM_LIGACOES_DOMICILIARES
        defaultEmpreitadaShouldNotBeFound("numLigacoesDomiciliares.equals=" + UPDATED_NUM_LIGACOES_DOMICILIARES);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumLigacoesDomiciliaresIsInShouldWork() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numLigacoesDomiciliares in DEFAULT_NUM_LIGACOES_DOMICILIARES or UPDATED_NUM_LIGACOES_DOMICILIARES
        defaultEmpreitadaShouldBeFound("numLigacoesDomiciliares.in=" + DEFAULT_NUM_LIGACOES_DOMICILIARES + "," + UPDATED_NUM_LIGACOES_DOMICILIARES);

        // Get all the empreitadaList where numLigacoesDomiciliares equals to UPDATED_NUM_LIGACOES_DOMICILIARES
        defaultEmpreitadaShouldNotBeFound("numLigacoesDomiciliares.in=" + UPDATED_NUM_LIGACOES_DOMICILIARES);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumLigacoesDomiciliaresIsNullOrNotNull() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numLigacoesDomiciliares is not null
        defaultEmpreitadaShouldBeFound("numLigacoesDomiciliares.specified=true");

        // Get all the empreitadaList where numLigacoesDomiciliares is null
        defaultEmpreitadaShouldNotBeFound("numLigacoesDomiciliares.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumLigacoesTorneiraQuintalIsEqualToSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numLigacoesTorneiraQuintal equals to DEFAULT_NUM_LIGACOES_TORNEIRA_QUINTAL
        defaultEmpreitadaShouldBeFound("numLigacoesTorneiraQuintal.equals=" + DEFAULT_NUM_LIGACOES_TORNEIRA_QUINTAL);

        // Get all the empreitadaList where numLigacoesTorneiraQuintal equals to UPDATED_NUM_LIGACOES_TORNEIRA_QUINTAL
        defaultEmpreitadaShouldNotBeFound("numLigacoesTorneiraQuintal.equals=" + UPDATED_NUM_LIGACOES_TORNEIRA_QUINTAL);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumLigacoesTorneiraQuintalIsInShouldWork() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numLigacoesTorneiraQuintal in DEFAULT_NUM_LIGACOES_TORNEIRA_QUINTAL or UPDATED_NUM_LIGACOES_TORNEIRA_QUINTAL
        defaultEmpreitadaShouldBeFound("numLigacoesTorneiraQuintal.in=" + DEFAULT_NUM_LIGACOES_TORNEIRA_QUINTAL + "," + UPDATED_NUM_LIGACOES_TORNEIRA_QUINTAL);

        // Get all the empreitadaList where numLigacoesTorneiraQuintal equals to UPDATED_NUM_LIGACOES_TORNEIRA_QUINTAL
        defaultEmpreitadaShouldNotBeFound("numLigacoesTorneiraQuintal.in=" + UPDATED_NUM_LIGACOES_TORNEIRA_QUINTAL);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumLigacoesTorneiraQuintalIsNullOrNotNull() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numLigacoesTorneiraQuintal is not null
        defaultEmpreitadaShouldBeFound("numLigacoesTorneiraQuintal.specified=true");

        // Get all the empreitadaList where numLigacoesTorneiraQuintal is null
        defaultEmpreitadaShouldNotBeFound("numLigacoesTorneiraQuintal.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumChafarisNovosIsEqualToSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numChafarisNovos equals to DEFAULT_NUM_CHAFARIS_NOVOS
        defaultEmpreitadaShouldBeFound("numChafarisNovos.equals=" + DEFAULT_NUM_CHAFARIS_NOVOS);

        // Get all the empreitadaList where numChafarisNovos equals to UPDATED_NUM_CHAFARIS_NOVOS
        defaultEmpreitadaShouldNotBeFound("numChafarisNovos.equals=" + UPDATED_NUM_CHAFARIS_NOVOS);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumChafarisNovosIsInShouldWork() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numChafarisNovos in DEFAULT_NUM_CHAFARIS_NOVOS or UPDATED_NUM_CHAFARIS_NOVOS
        defaultEmpreitadaShouldBeFound("numChafarisNovos.in=" + DEFAULT_NUM_CHAFARIS_NOVOS + "," + UPDATED_NUM_CHAFARIS_NOVOS);

        // Get all the empreitadaList where numChafarisNovos equals to UPDATED_NUM_CHAFARIS_NOVOS
        defaultEmpreitadaShouldNotBeFound("numChafarisNovos.in=" + UPDATED_NUM_CHAFARIS_NOVOS);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumChafarisNovosIsNullOrNotNull() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numChafarisNovos is not null
        defaultEmpreitadaShouldBeFound("numChafarisNovos.specified=true");

        // Get all the empreitadaList where numChafarisNovos is null
        defaultEmpreitadaShouldNotBeFound("numChafarisNovos.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumChafarisReabilitarIsEqualToSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numChafarisReabilitar equals to DEFAULT_NUM_CHAFARIS_REABILITAR
        defaultEmpreitadaShouldBeFound("numChafarisReabilitar.equals=" + DEFAULT_NUM_CHAFARIS_REABILITAR);

        // Get all the empreitadaList where numChafarisReabilitar equals to UPDATED_NUM_CHAFARIS_REABILITAR
        defaultEmpreitadaShouldNotBeFound("numChafarisReabilitar.equals=" + UPDATED_NUM_CHAFARIS_REABILITAR);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumChafarisReabilitarIsInShouldWork() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numChafarisReabilitar in DEFAULT_NUM_CHAFARIS_REABILITAR or UPDATED_NUM_CHAFARIS_REABILITAR
        defaultEmpreitadaShouldBeFound("numChafarisReabilitar.in=" + DEFAULT_NUM_CHAFARIS_REABILITAR + "," + UPDATED_NUM_CHAFARIS_REABILITAR);

        // Get all the empreitadaList where numChafarisReabilitar equals to UPDATED_NUM_CHAFARIS_REABILITAR
        defaultEmpreitadaShouldNotBeFound("numChafarisReabilitar.in=" + UPDATED_NUM_CHAFARIS_REABILITAR);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumChafarisReabilitarIsNullOrNotNull() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numChafarisReabilitar is not null
        defaultEmpreitadaShouldBeFound("numChafarisReabilitar.specified=true");

        // Get all the empreitadaList where numChafarisReabilitar is null
        defaultEmpreitadaShouldNotBeFound("numChafarisReabilitar.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumCapacidadeTratamentoEtaIsEqualToSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numCapacidadeTratamentoEta equals to DEFAULT_NUM_CAPACIDADE_TRATAMENTO_ETA
        defaultEmpreitadaShouldBeFound("numCapacidadeTratamentoEta.equals=" + DEFAULT_NUM_CAPACIDADE_TRATAMENTO_ETA);

        // Get all the empreitadaList where numCapacidadeTratamentoEta equals to UPDATED_NUM_CAPACIDADE_TRATAMENTO_ETA
        defaultEmpreitadaShouldNotBeFound("numCapacidadeTratamentoEta.equals=" + UPDATED_NUM_CAPACIDADE_TRATAMENTO_ETA);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumCapacidadeTratamentoEtaIsInShouldWork() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numCapacidadeTratamentoEta in DEFAULT_NUM_CAPACIDADE_TRATAMENTO_ETA or UPDATED_NUM_CAPACIDADE_TRATAMENTO_ETA
        defaultEmpreitadaShouldBeFound("numCapacidadeTratamentoEta.in=" + DEFAULT_NUM_CAPACIDADE_TRATAMENTO_ETA + "," + UPDATED_NUM_CAPACIDADE_TRATAMENTO_ETA);

        // Get all the empreitadaList where numCapacidadeTratamentoEta equals to UPDATED_NUM_CAPACIDADE_TRATAMENTO_ETA
        defaultEmpreitadaShouldNotBeFound("numCapacidadeTratamentoEta.in=" + UPDATED_NUM_CAPACIDADE_TRATAMENTO_ETA);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumCapacidadeTratamentoEtaIsNullOrNotNull() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numCapacidadeTratamentoEta is not null
        defaultEmpreitadaShouldBeFound("numCapacidadeTratamentoEta.specified=true");

        // Get all the empreitadaList where numCapacidadeTratamentoEta is null
        defaultEmpreitadaShouldNotBeFound("numCapacidadeTratamentoEta.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumExtensaoRedeMaterialIsEqualToSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numExtensaoRedeMaterial equals to DEFAULT_NUM_EXTENSAO_REDE_MATERIAL
        defaultEmpreitadaShouldBeFound("numExtensaoRedeMaterial.equals=" + DEFAULT_NUM_EXTENSAO_REDE_MATERIAL);

        // Get all the empreitadaList where numExtensaoRedeMaterial equals to UPDATED_NUM_EXTENSAO_REDE_MATERIAL
        defaultEmpreitadaShouldNotBeFound("numExtensaoRedeMaterial.equals=" + UPDATED_NUM_EXTENSAO_REDE_MATERIAL);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumExtensaoRedeMaterialIsInShouldWork() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numExtensaoRedeMaterial in DEFAULT_NUM_EXTENSAO_REDE_MATERIAL or UPDATED_NUM_EXTENSAO_REDE_MATERIAL
        defaultEmpreitadaShouldBeFound("numExtensaoRedeMaterial.in=" + DEFAULT_NUM_EXTENSAO_REDE_MATERIAL + "," + UPDATED_NUM_EXTENSAO_REDE_MATERIAL);

        // Get all the empreitadaList where numExtensaoRedeMaterial equals to UPDATED_NUM_EXTENSAO_REDE_MATERIAL
        defaultEmpreitadaShouldNotBeFound("numExtensaoRedeMaterial.in=" + UPDATED_NUM_EXTENSAO_REDE_MATERIAL);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumExtensaoRedeMaterialIsNullOrNotNull() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numExtensaoRedeMaterial is not null
        defaultEmpreitadaShouldBeFound("numExtensaoRedeMaterial.specified=true");

        // Get all the empreitadaList where numExtensaoRedeMaterial is null
        defaultEmpreitadaShouldNotBeFound("numExtensaoRedeMaterial.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumExtensaoCondutasElelMatIsEqualToSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numExtensaoCondutasElelMat equals to DEFAULT_NUM_EXTENSAO_CONDUTAS_ELEL_MAT
        defaultEmpreitadaShouldBeFound("numExtensaoCondutasElelMat.equals=" + DEFAULT_NUM_EXTENSAO_CONDUTAS_ELEL_MAT);

        // Get all the empreitadaList where numExtensaoCondutasElelMat equals to UPDATED_NUM_EXTENSAO_CONDUTAS_ELEL_MAT
        defaultEmpreitadaShouldNotBeFound("numExtensaoCondutasElelMat.equals=" + UPDATED_NUM_EXTENSAO_CONDUTAS_ELEL_MAT);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumExtensaoCondutasElelMatIsInShouldWork() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numExtensaoCondutasElelMat in DEFAULT_NUM_EXTENSAO_CONDUTAS_ELEL_MAT or UPDATED_NUM_EXTENSAO_CONDUTAS_ELEL_MAT
        defaultEmpreitadaShouldBeFound("numExtensaoCondutasElelMat.in=" + DEFAULT_NUM_EXTENSAO_CONDUTAS_ELEL_MAT + "," + UPDATED_NUM_EXTENSAO_CONDUTAS_ELEL_MAT);

        // Get all the empreitadaList where numExtensaoCondutasElelMat equals to UPDATED_NUM_EXTENSAO_CONDUTAS_ELEL_MAT
        defaultEmpreitadaShouldNotBeFound("numExtensaoCondutasElelMat.in=" + UPDATED_NUM_EXTENSAO_CONDUTAS_ELEL_MAT);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumExtensaoCondutasElelMatIsNullOrNotNull() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numExtensaoCondutasElelMat is not null
        defaultEmpreitadaShouldBeFound("numExtensaoCondutasElelMat.specified=true");

        // Get all the empreitadaList where numExtensaoCondutasElelMat is null
        defaultEmpreitadaShouldNotBeFound("numExtensaoCondutasElelMat.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumLigacoesIsEqualToSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numLigacoes equals to DEFAULT_NUM_LIGACOES
        defaultEmpreitadaShouldBeFound("numLigacoes.equals=" + DEFAULT_NUM_LIGACOES);

        // Get all the empreitadaList where numLigacoes equals to UPDATED_NUM_LIGACOES
        defaultEmpreitadaShouldNotBeFound("numLigacoes.equals=" + UPDATED_NUM_LIGACOES);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumLigacoesIsInShouldWork() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numLigacoes in DEFAULT_NUM_LIGACOES or UPDATED_NUM_LIGACOES
        defaultEmpreitadaShouldBeFound("numLigacoes.in=" + DEFAULT_NUM_LIGACOES + "," + UPDATED_NUM_LIGACOES);

        // Get all the empreitadaList where numLigacoes equals to UPDATED_NUM_LIGACOES
        defaultEmpreitadaShouldNotBeFound("numLigacoes.in=" + UPDATED_NUM_LIGACOES);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumLigacoesIsNullOrNotNull() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numLigacoes is not null
        defaultEmpreitadaShouldBeFound("numLigacoes.specified=true");

        // Get all the empreitadaList where numLigacoes is null
        defaultEmpreitadaShouldNotBeFound("numLigacoes.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumCaixasVisitasIsEqualToSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numCaixasVisitas equals to DEFAULT_NUM_CAIXAS_VISITAS
        defaultEmpreitadaShouldBeFound("numCaixasVisitas.equals=" + DEFAULT_NUM_CAIXAS_VISITAS);

        // Get all the empreitadaList where numCaixasVisitas equals to UPDATED_NUM_CAIXAS_VISITAS
        defaultEmpreitadaShouldNotBeFound("numCaixasVisitas.equals=" + UPDATED_NUM_CAIXAS_VISITAS);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumCaixasVisitasIsInShouldWork() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numCaixasVisitas in DEFAULT_NUM_CAIXAS_VISITAS or UPDATED_NUM_CAIXAS_VISITAS
        defaultEmpreitadaShouldBeFound("numCaixasVisitas.in=" + DEFAULT_NUM_CAIXAS_VISITAS + "," + UPDATED_NUM_CAIXAS_VISITAS);

        // Get all the empreitadaList where numCaixasVisitas equals to UPDATED_NUM_CAIXAS_VISITAS
        defaultEmpreitadaShouldNotBeFound("numCaixasVisitas.in=" + UPDATED_NUM_CAIXAS_VISITAS);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumCaixasVisitasIsNullOrNotNull() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numCaixasVisitas is not null
        defaultEmpreitadaShouldBeFound("numCaixasVisitas.specified=true");

        // Get all the empreitadaList where numCaixasVisitas is null
        defaultEmpreitadaShouldNotBeFound("numCaixasVisitas.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumEstacoesElevatoriasIsEqualToSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numEstacoesElevatorias equals to DEFAULT_NUM_ESTACOES_ELEVATORIAS
        defaultEmpreitadaShouldBeFound("numEstacoesElevatorias.equals=" + DEFAULT_NUM_ESTACOES_ELEVATORIAS);

        // Get all the empreitadaList where numEstacoesElevatorias equals to UPDATED_NUM_ESTACOES_ELEVATORIAS
        defaultEmpreitadaShouldNotBeFound("numEstacoesElevatorias.equals=" + UPDATED_NUM_ESTACOES_ELEVATORIAS);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumEstacoesElevatoriasIsInShouldWork() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numEstacoesElevatorias in DEFAULT_NUM_ESTACOES_ELEVATORIAS or UPDATED_NUM_ESTACOES_ELEVATORIAS
        defaultEmpreitadaShouldBeFound("numEstacoesElevatorias.in=" + DEFAULT_NUM_ESTACOES_ELEVATORIAS + "," + UPDATED_NUM_ESTACOES_ELEVATORIAS);

        // Get all the empreitadaList where numEstacoesElevatorias equals to UPDATED_NUM_ESTACOES_ELEVATORIAS
        defaultEmpreitadaShouldNotBeFound("numEstacoesElevatorias.in=" + UPDATED_NUM_ESTACOES_ELEVATORIAS);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumEstacoesElevatoriasIsNullOrNotNull() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numEstacoesElevatorias is not null
        defaultEmpreitadaShouldBeFound("numEstacoesElevatorias.specified=true");

        // Get all the empreitadaList where numEstacoesElevatorias is null
        defaultEmpreitadaShouldNotBeFound("numEstacoesElevatorias.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumLatrinasIsEqualToSomething() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numLatrinas equals to DEFAULT_NUM_LATRINAS
        defaultEmpreitadaShouldBeFound("numLatrinas.equals=" + DEFAULT_NUM_LATRINAS);

        // Get all the empreitadaList where numLatrinas equals to UPDATED_NUM_LATRINAS
        defaultEmpreitadaShouldNotBeFound("numLatrinas.equals=" + UPDATED_NUM_LATRINAS);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumLatrinasIsInShouldWork() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numLatrinas in DEFAULT_NUM_LATRINAS or UPDATED_NUM_LATRINAS
        defaultEmpreitadaShouldBeFound("numLatrinas.in=" + DEFAULT_NUM_LATRINAS + "," + UPDATED_NUM_LATRINAS);

        // Get all the empreitadaList where numLatrinas equals to UPDATED_NUM_LATRINAS
        defaultEmpreitadaShouldNotBeFound("numLatrinas.in=" + UPDATED_NUM_LATRINAS);
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByNumLatrinasIsNullOrNotNull() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);

        // Get all the empreitadaList where numLatrinas is not null
        defaultEmpreitadaShouldBeFound("numLatrinas.specified=true");

        // Get all the empreitadaList where numLatrinas is null
        defaultEmpreitadaShouldNotBeFound("numLatrinas.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmpreitadasByIdProgramasProjectosIsEqualToSomething() throws Exception {
        // Initialize the database
        ProgramasProjectos idProgramasProjectos = ProgramasProjectosResourceIntTest.createEntity(em);
        em.persist(idProgramasProjectos);
        em.flush();
        empreitada.setIdProgramasProjectos(idProgramasProjectos);
        empreitadaRepository.saveAndFlush(empreitada);
        Long idProgramasProjectosId = idProgramasProjectos.getId();

        // Get all the empreitadaList where idProgramasProjectos equals to idProgramasProjectosId
        defaultEmpreitadaShouldBeFound("idProgramasProjectosId.equals=" + idProgramasProjectosId);

        // Get all the empreitadaList where idProgramasProjectos equals to idProgramasProjectosId + 1
        defaultEmpreitadaShouldNotBeFound("idProgramasProjectosId.equals=" + (idProgramasProjectosId + 1));
    }


    @Test
    @Transactional
    public void getAllEmpreitadasByIdSistemaAguaIsEqualToSomething() throws Exception {
        // Initialize the database
        SistemaAgua idSistemaAgua = SistemaAguaResourceIntTest.createEntity(em);
        em.persist(idSistemaAgua);
        em.flush();
        empreitada.setIdSistemaAgua(idSistemaAgua);
        empreitadaRepository.saveAndFlush(empreitada);
        Long idSistemaAguaId = idSistemaAgua.getId();

        // Get all the empreitadaList where idSistemaAgua equals to idSistemaAguaId
        defaultEmpreitadaShouldBeFound("idSistemaAguaId.equals=" + idSistemaAguaId);

        // Get all the empreitadaList where idSistemaAgua equals to idSistemaAguaId + 1
        defaultEmpreitadaShouldNotBeFound("idSistemaAguaId.equals=" + (idSistemaAguaId + 1));
    }


    @Test
    @Transactional
    public void getAllEmpreitadasByIdContratoIsEqualToSomething() throws Exception {
        // Initialize the database
        Contrato idContrato = ContratoResourceIntTest.createEntity(em);
        em.persist(idContrato);
        em.flush();
        empreitada.setIdContrato(idContrato);
        empreitadaRepository.saveAndFlush(empreitada);
        Long idContratoId = idContrato.getId();

        // Get all the empreitadaList where idContrato equals to idContratoId
        defaultEmpreitadaShouldBeFound("idContratoId.equals=" + idContratoId);

        // Get all the empreitadaList where idContrato equals to idContratoId + 1
        defaultEmpreitadaShouldNotBeFound("idContratoId.equals=" + (idContratoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultEmpreitadaShouldBeFound(String filter) throws Exception {
        restEmpreitadaMockMvc.perform(get("/api/empreitadas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(empreitada.getId().intValue())))
            .andExpect(jsonPath("$.[*].idEmpreitada").value(hasItem(DEFAULT_ID_EMPREITADA.intValue())))
            .andExpect(jsonPath("$.[*].tipoEmpreitada").value(hasItem(DEFAULT_TIPO_EMPREITADA.toString())))
            .andExpect(jsonPath("$.[*].dtLancamento").value(hasItem(DEFAULT_DT_LANCAMENTO.toString())))
            .andExpect(jsonPath("$.[*].numCapacidadeCaptacao").value(hasItem(DEFAULT_NUM_CAPACIDADE_CAPTACAO.intValue())))
            .andExpect(jsonPath("$.[*].numCapacidadeCaptacaoEta").value(hasItem(DEFAULT_NUM_CAPACIDADE_CAPTACAO_ETA.intValue())))
            .andExpect(jsonPath("$.[*].numExtensaoCondAdutMat").value(hasItem(DEFAULT_NUM_EXTENSAO_COND_ADUT_MAT.intValue())))
            .andExpect(jsonPath("$.[*].numCaprmazenamento").value(hasItem(DEFAULT_NUM_CAPRMAZENAMENTO.intValue())))
            .andExpect(jsonPath("$.[*].numExtensaoRedeMat").value(hasItem(DEFAULT_NUM_EXTENSAO_REDE_MAT.intValue())))
            .andExpect(jsonPath("$.[*].numLigacoesDomiciliares").value(hasItem(DEFAULT_NUM_LIGACOES_DOMICILIARES.intValue())))
            .andExpect(jsonPath("$.[*].numLigacoesTorneiraQuintal").value(hasItem(DEFAULT_NUM_LIGACOES_TORNEIRA_QUINTAL.intValue())))
            .andExpect(jsonPath("$.[*].numChafarisNovos").value(hasItem(DEFAULT_NUM_CHAFARIS_NOVOS.intValue())))
            .andExpect(jsonPath("$.[*].numChafarisReabilitar").value(hasItem(DEFAULT_NUM_CHAFARIS_REABILITAR.intValue())))
            .andExpect(jsonPath("$.[*].numCapacidadeTratamentoEta").value(hasItem(DEFAULT_NUM_CAPACIDADE_TRATAMENTO_ETA.intValue())))
            .andExpect(jsonPath("$.[*].numExtensaoRedeMaterial").value(hasItem(DEFAULT_NUM_EXTENSAO_REDE_MATERIAL.intValue())))
            .andExpect(jsonPath("$.[*].numExtensaoCondutasElelMat").value(hasItem(DEFAULT_NUM_EXTENSAO_CONDUTAS_ELEL_MAT.intValue())))
            .andExpect(jsonPath("$.[*].numLigacoes").value(hasItem(DEFAULT_NUM_LIGACOES.intValue())))
            .andExpect(jsonPath("$.[*].numCaixasVisitas").value(hasItem(DEFAULT_NUM_CAIXAS_VISITAS.intValue())))
            .andExpect(jsonPath("$.[*].numEstacoesElevatorias").value(hasItem(DEFAULT_NUM_ESTACOES_ELEVATORIAS.intValue())))
            .andExpect(jsonPath("$.[*].numLatrinas").value(hasItem(DEFAULT_NUM_LATRINAS.intValue())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultEmpreitadaShouldNotBeFound(String filter) throws Exception {
        restEmpreitadaMockMvc.perform(get("/api/empreitadas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingEmpreitada() throws Exception {
        // Get the empreitada
        restEmpreitadaMockMvc.perform(get("/api/empreitadas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmpreitada() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);
        int databaseSizeBeforeUpdate = empreitadaRepository.findAll().size();

        // Update the empreitada
        Empreitada updatedEmpreitada = empreitadaRepository.findOne(empreitada.getId());
        // Disconnect from session so that the updates on updatedEmpreitada are not directly saved in db
        em.detach(updatedEmpreitada);
        updatedEmpreitada
            .idEmpreitada(UPDATED_ID_EMPREITADA)
            .tipoEmpreitada(UPDATED_TIPO_EMPREITADA)
            .dtLancamento(UPDATED_DT_LANCAMENTO)
            .numCapacidadeCaptacao(UPDATED_NUM_CAPACIDADE_CAPTACAO)
            .numCapacidadeCaptacaoEta(UPDATED_NUM_CAPACIDADE_CAPTACAO_ETA)
            .numExtensaoCondAdutMat(UPDATED_NUM_EXTENSAO_COND_ADUT_MAT)
            .numCaprmazenamento(UPDATED_NUM_CAPRMAZENAMENTO)
            .numExtensaoRedeMat(UPDATED_NUM_EXTENSAO_REDE_MAT)
            .numLigacoesDomiciliares(UPDATED_NUM_LIGACOES_DOMICILIARES)
            .numLigacoesTorneiraQuintal(UPDATED_NUM_LIGACOES_TORNEIRA_QUINTAL)
            .numChafarisNovos(UPDATED_NUM_CHAFARIS_NOVOS)
            .numChafarisReabilitar(UPDATED_NUM_CHAFARIS_REABILITAR)
            .numCapacidadeTratamentoEta(UPDATED_NUM_CAPACIDADE_TRATAMENTO_ETA)
            .numExtensaoRedeMaterial(UPDATED_NUM_EXTENSAO_REDE_MATERIAL)
            .numExtensaoCondutasElelMat(UPDATED_NUM_EXTENSAO_CONDUTAS_ELEL_MAT)
            .numLigacoes(UPDATED_NUM_LIGACOES)
            .numCaixasVisitas(UPDATED_NUM_CAIXAS_VISITAS)
            .numEstacoesElevatorias(UPDATED_NUM_ESTACOES_ELEVATORIAS)
            .numLatrinas(UPDATED_NUM_LATRINAS);
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(updatedEmpreitada);

        restEmpreitadaMockMvc.perform(put("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isOk());

        // Validate the Empreitada in the database
        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeUpdate);
        Empreitada testEmpreitada = empreitadaList.get(empreitadaList.size() - 1);
        assertThat(testEmpreitada.getIdEmpreitada()).isEqualTo(UPDATED_ID_EMPREITADA);
        assertThat(testEmpreitada.getTipoEmpreitada()).isEqualTo(UPDATED_TIPO_EMPREITADA);
        assertThat(testEmpreitada.getDtLancamento()).isEqualTo(UPDATED_DT_LANCAMENTO);
        assertThat(testEmpreitada.getNumCapacidadeCaptacao()).isEqualTo(UPDATED_NUM_CAPACIDADE_CAPTACAO);
        assertThat(testEmpreitada.getNumCapacidadeCaptacaoEta()).isEqualTo(UPDATED_NUM_CAPACIDADE_CAPTACAO_ETA);
        assertThat(testEmpreitada.getNumExtensaoCondAdutMat()).isEqualTo(UPDATED_NUM_EXTENSAO_COND_ADUT_MAT);
        assertThat(testEmpreitada.getNumCaprmazenamento()).isEqualTo(UPDATED_NUM_CAPRMAZENAMENTO);
        assertThat(testEmpreitada.getNumExtensaoRedeMat()).isEqualTo(UPDATED_NUM_EXTENSAO_REDE_MAT);
        assertThat(testEmpreitada.getNumLigacoesDomiciliares()).isEqualTo(UPDATED_NUM_LIGACOES_DOMICILIARES);
        assertThat(testEmpreitada.getNumLigacoesTorneiraQuintal()).isEqualTo(UPDATED_NUM_LIGACOES_TORNEIRA_QUINTAL);
        assertThat(testEmpreitada.getNumChafarisNovos()).isEqualTo(UPDATED_NUM_CHAFARIS_NOVOS);
        assertThat(testEmpreitada.getNumChafarisReabilitar()).isEqualTo(UPDATED_NUM_CHAFARIS_REABILITAR);
        assertThat(testEmpreitada.getNumCapacidadeTratamentoEta()).isEqualTo(UPDATED_NUM_CAPACIDADE_TRATAMENTO_ETA);
        assertThat(testEmpreitada.getNumExtensaoRedeMaterial()).isEqualTo(UPDATED_NUM_EXTENSAO_REDE_MATERIAL);
        assertThat(testEmpreitada.getNumExtensaoCondutasElelMat()).isEqualTo(UPDATED_NUM_EXTENSAO_CONDUTAS_ELEL_MAT);
        assertThat(testEmpreitada.getNumLigacoes()).isEqualTo(UPDATED_NUM_LIGACOES);
        assertThat(testEmpreitada.getNumCaixasVisitas()).isEqualTo(UPDATED_NUM_CAIXAS_VISITAS);
        assertThat(testEmpreitada.getNumEstacoesElevatorias()).isEqualTo(UPDATED_NUM_ESTACOES_ELEVATORIAS);
        assertThat(testEmpreitada.getNumLatrinas()).isEqualTo(UPDATED_NUM_LATRINAS);
    }

    @Test
    @Transactional
    public void updateNonExistingEmpreitada() throws Exception {
        int databaseSizeBeforeUpdate = empreitadaRepository.findAll().size();

        // Create the Empreitada
        EmpreitadaDTO empreitadaDTO = empreitadaMapper.toDto(empreitada);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEmpreitadaMockMvc.perform(put("/api/empreitadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empreitadaDTO)))
            .andExpect(status().isCreated());

        // Validate the Empreitada in the database
        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEmpreitada() throws Exception {
        // Initialize the database
        empreitadaRepository.saveAndFlush(empreitada);
        int databaseSizeBeforeDelete = empreitadaRepository.findAll().size();

        // Get the empreitada
        restEmpreitadaMockMvc.perform(delete("/api/empreitadas/{id}", empreitada.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Empreitada> empreitadaList = empreitadaRepository.findAll();
        assertThat(empreitadaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Empreitada.class);
        Empreitada empreitada1 = new Empreitada();
        empreitada1.setId(1L);
        Empreitada empreitada2 = new Empreitada();
        empreitada2.setId(empreitada1.getId());
        assertThat(empreitada1).isEqualTo(empreitada2);
        empreitada2.setId(2L);
        assertThat(empreitada1).isNotEqualTo(empreitada2);
        empreitada1.setId(null);
        assertThat(empreitada1).isNotEqualTo(empreitada2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmpreitadaDTO.class);
        EmpreitadaDTO empreitadaDTO1 = new EmpreitadaDTO();
        empreitadaDTO1.setId(1L);
        EmpreitadaDTO empreitadaDTO2 = new EmpreitadaDTO();
        assertThat(empreitadaDTO1).isNotEqualTo(empreitadaDTO2);
        empreitadaDTO2.setId(empreitadaDTO1.getId());
        assertThat(empreitadaDTO1).isEqualTo(empreitadaDTO2);
        empreitadaDTO2.setId(2L);
        assertThat(empreitadaDTO1).isNotEqualTo(empreitadaDTO2);
        empreitadaDTO1.setId(null);
        assertThat(empreitadaDTO1).isNotEqualTo(empreitadaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(empreitadaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(empreitadaMapper.fromId(null)).isNull();
    }
}
