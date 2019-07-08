package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.SistemaAguaLog;
import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.repository.SistemaAguaLogRepository;
import com.minea.sisas.service.SistemaAguaLogService;
import com.minea.sisas.service.dto.SistemaAguaLogDTO;
import com.minea.sisas.service.mapper.SistemaAguaLogMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.SistemaAguaLogQueryService;

import com.minea.sisas.web.rest.SistemaAguaLogResource;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.minea.sisas.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SistemaAguaLogResource REST controller.
 *
 * @see SistemaAguaLogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class SistemaAguaLogResourceIntTest {

    private static final Long DEFAULT_ID_SISTEMA_AGUA_LOG = 1L;
    private static final Long UPDATED_ID_SISTEMA_AGUA_LOG = 2L;

    private static final String DEFAULT_ACAO = "AAAAAAAAAA";
    private static final String UPDATED_ACAO = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_USUARIO = 1L;
    private static final Long UPDATED_ID_USUARIO = 2L;

    private static final String DEFAULT_LOG = "AAAAAAAAAA";
    private static final String UPDATED_LOG = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DT_LOG = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_LOG = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private SistemaAguaLogRepository sistemaAguaLogRepository;

    @Autowired
    private SistemaAguaLogMapper sistemaAguaLogMapper;

    @Autowired
    private SistemaAguaLogService sistemaAguaLogService;

    @Autowired
    private SistemaAguaLogQueryService sistemaAguaLogQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSistemaAguaLogMockMvc;

    private SistemaAguaLog sistemaAguaLog;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SistemaAguaLogResource sistemaAguaLogResource = new SistemaAguaLogResource(sistemaAguaLogService, sistemaAguaLogQueryService);
        this.restSistemaAguaLogMockMvc = MockMvcBuilders.standaloneSetup(sistemaAguaLogResource)
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
    public static SistemaAguaLog createEntity(EntityManager em) {
        SistemaAguaLog sistemaAguaLog = new SistemaAguaLog()
            .acao(DEFAULT_ACAO)
            .idUsuario(DEFAULT_ID_USUARIO)
            .log(DEFAULT_LOG)
            .dtLog(DEFAULT_DT_LOG);
        // Add required entity
        SistemaAgua idSistemaAgua = SistemaAguaResourceIntTest.createEntity(em);
        em.persist(idSistemaAgua);
        em.flush();
        sistemaAguaLog.setIdSistemaAgua(idSistemaAgua);
        return sistemaAguaLog;
    }

    @Before
    public void initTest() {
        sistemaAguaLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createSistemaAguaLog() throws Exception {
        int databaseSizeBeforeCreate = sistemaAguaLogRepository.findAll().size();

        // Create the SistemaAguaLog
        SistemaAguaLogDTO sistemaAguaLogDTO = sistemaAguaLogMapper.toDto(sistemaAguaLog);
        restSistemaAguaLogMockMvc.perform(post("/api/sistema-agua-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaLogDTO)))
            .andExpect(status().isCreated());

        // Validate the SistemaAguaLog in the database
        List<SistemaAguaLog> sistemaAguaLogList = sistemaAguaLogRepository.findAll();
        assertThat(sistemaAguaLogList).hasSize(databaseSizeBeforeCreate + 1);
        SistemaAguaLog testSistemaAguaLog = sistemaAguaLogList.get(sistemaAguaLogList.size() - 1);
        assertThat(testSistemaAguaLog.getAcao()).isEqualTo(DEFAULT_ACAO);
        assertThat(testSistemaAguaLog.getIdUsuario()).isEqualTo(DEFAULT_ID_USUARIO);
        assertThat(testSistemaAguaLog.getLog()).isEqualTo(DEFAULT_LOG);
        assertThat(testSistemaAguaLog.getDtLog()).isEqualTo(DEFAULT_DT_LOG);
    }

    @Test
    @Transactional
    public void createSistemaAguaLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sistemaAguaLogRepository.findAll().size();

        // Create the SistemaAguaLog with an existing ID
        sistemaAguaLog.setId(1L);
        SistemaAguaLogDTO sistemaAguaLogDTO = sistemaAguaLogMapper.toDto(sistemaAguaLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSistemaAguaLogMockMvc.perform(post("/api/sistema-agua-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SistemaAguaLog in the database
        List<SistemaAguaLog> sistemaAguaLogList = sistemaAguaLogRepository.findAll();
        assertThat(sistemaAguaLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdSistemaAguaLogIsRequired() throws Exception {
        int databaseSizeBeforeTest = sistemaAguaLogRepository.findAll().size();
        // set the field null

        // Create the SistemaAguaLog, which fails.
        SistemaAguaLogDTO sistemaAguaLogDTO = sistemaAguaLogMapper.toDto(sistemaAguaLog);

        restSistemaAguaLogMockMvc.perform(post("/api/sistema-agua-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaLogDTO)))
            .andExpect(status().isBadRequest());

        List<SistemaAguaLog> sistemaAguaLogList = sistemaAguaLogRepository.findAll();
        assertThat(sistemaAguaLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAcaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = sistemaAguaLogRepository.findAll().size();
        // set the field null
        sistemaAguaLog.setAcao(null);

        // Create the SistemaAguaLog, which fails.
        SistemaAguaLogDTO sistemaAguaLogDTO = sistemaAguaLogMapper.toDto(sistemaAguaLog);

        restSistemaAguaLogMockMvc.perform(post("/api/sistema-agua-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaLogDTO)))
            .andExpect(status().isBadRequest());

        List<SistemaAguaLog> sistemaAguaLogList = sistemaAguaLogRepository.findAll();
        assertThat(sistemaAguaLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdUsuarioIsRequired() throws Exception {
        int databaseSizeBeforeTest = sistemaAguaLogRepository.findAll().size();
        // set the field null
        sistemaAguaLog.setIdUsuario(null);

        // Create the SistemaAguaLog, which fails.
        SistemaAguaLogDTO sistemaAguaLogDTO = sistemaAguaLogMapper.toDto(sistemaAguaLog);

        restSistemaAguaLogMockMvc.perform(post("/api/sistema-agua-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaLogDTO)))
            .andExpect(status().isBadRequest());

        List<SistemaAguaLog> sistemaAguaLogList = sistemaAguaLogRepository.findAll();
        assertThat(sistemaAguaLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLogIsRequired() throws Exception {
        int databaseSizeBeforeTest = sistemaAguaLogRepository.findAll().size();
        // set the field null
        sistemaAguaLog.setLog(null);

        // Create the SistemaAguaLog, which fails.
        SistemaAguaLogDTO sistemaAguaLogDTO = sistemaAguaLogMapper.toDto(sistemaAguaLog);

        restSistemaAguaLogMockMvc.perform(post("/api/sistema-agua-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaLogDTO)))
            .andExpect(status().isBadRequest());

        List<SistemaAguaLog> sistemaAguaLogList = sistemaAguaLogRepository.findAll();
        assertThat(sistemaAguaLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDtLogIsRequired() throws Exception {
        int databaseSizeBeforeTest = sistemaAguaLogRepository.findAll().size();
        // set the field null
        sistemaAguaLog.setDtLog(null);

        // Create the SistemaAguaLog, which fails.
        SistemaAguaLogDTO sistemaAguaLogDTO = sistemaAguaLogMapper.toDto(sistemaAguaLog);

        restSistemaAguaLogMockMvc.perform(post("/api/sistema-agua-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaLogDTO)))
            .andExpect(status().isBadRequest());

        List<SistemaAguaLog> sistemaAguaLogList = sistemaAguaLogRepository.findAll();
        assertThat(sistemaAguaLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSistemaAguaLogs() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get all the sistemaAguaLogList
        restSistemaAguaLogMockMvc.perform(get("/api/sistema-agua-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sistemaAguaLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSistemaAguaLog").value(hasItem(DEFAULT_ID_SISTEMA_AGUA_LOG.intValue())))
            .andExpect(jsonPath("$.[*].acao").value(hasItem(DEFAULT_ACAO.toString())))
            .andExpect(jsonPath("$.[*].idUsuario").value(hasItem(DEFAULT_ID_USUARIO.intValue())))
            .andExpect(jsonPath("$.[*].log").value(hasItem(DEFAULT_LOG.toString())))
            .andExpect(jsonPath("$.[*].dtLog").value(hasItem(DEFAULT_DT_LOG.toString())));
    }

    @Test
    @Transactional
    public void getSistemaAguaLog() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get the sistemaAguaLog
        restSistemaAguaLogMockMvc.perform(get("/api/sistema-agua-logs/{id}", sistemaAguaLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sistemaAguaLog.getId().intValue()))
            .andExpect(jsonPath("$.idSistemaAguaLog").value(DEFAULT_ID_SISTEMA_AGUA_LOG.intValue()))
            .andExpect(jsonPath("$.acao").value(DEFAULT_ACAO.toString()))
            .andExpect(jsonPath("$.idUsuario").value(DEFAULT_ID_USUARIO.intValue()))
            .andExpect(jsonPath("$.log").value(DEFAULT_LOG.toString()))
            .andExpect(jsonPath("$.dtLog").value(DEFAULT_DT_LOG.toString()));
    }

    @Test
    @Transactional
    public void getAllSistemaAguaLogsByIdSistemaAguaLogIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get all the sistemaAguaLogList where idSistemaAguaLog equals to DEFAULT_ID_SISTEMA_AGUA_LOG
        defaultSistemaAguaLogShouldBeFound("idSistemaAguaLog.equals=" + DEFAULT_ID_SISTEMA_AGUA_LOG);

        // Get all the sistemaAguaLogList where idSistemaAguaLog equals to UPDATED_ID_SISTEMA_AGUA_LOG
        defaultSistemaAguaLogShouldNotBeFound("idSistemaAguaLog.equals=" + UPDATED_ID_SISTEMA_AGUA_LOG);
    }

    @Test
    @Transactional
    public void getAllSistemaAguaLogsByIdSistemaAguaLogIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get all the sistemaAguaLogList where idSistemaAguaLog in DEFAULT_ID_SISTEMA_AGUA_LOG or UPDATED_ID_SISTEMA_AGUA_LOG
        defaultSistemaAguaLogShouldBeFound("idSistemaAguaLog.in=" + DEFAULT_ID_SISTEMA_AGUA_LOG + "," + UPDATED_ID_SISTEMA_AGUA_LOG);

        // Get all the sistemaAguaLogList where idSistemaAguaLog equals to UPDATED_ID_SISTEMA_AGUA_LOG
        defaultSistemaAguaLogShouldNotBeFound("idSistemaAguaLog.in=" + UPDATED_ID_SISTEMA_AGUA_LOG);
    }

    @Test
    @Transactional
    public void getAllSistemaAguaLogsByIdSistemaAguaLogIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get all the sistemaAguaLogList where idSistemaAguaLog is not null
        defaultSistemaAguaLogShouldBeFound("idSistemaAguaLog.specified=true");

        // Get all the sistemaAguaLogList where idSistemaAguaLog is null
        defaultSistemaAguaLogShouldNotBeFound("idSistemaAguaLog.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguaLogsByIdSistemaAguaLogIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get all the sistemaAguaLogList where idSistemaAguaLog greater than or equals to DEFAULT_ID_SISTEMA_AGUA_LOG
        defaultSistemaAguaLogShouldBeFound("idSistemaAguaLog.greaterOrEqualThan=" + DEFAULT_ID_SISTEMA_AGUA_LOG);

        // Get all the sistemaAguaLogList where idSistemaAguaLog greater than or equals to UPDATED_ID_SISTEMA_AGUA_LOG
        defaultSistemaAguaLogShouldNotBeFound("idSistemaAguaLog.greaterOrEqualThan=" + UPDATED_ID_SISTEMA_AGUA_LOG);
    }

    @Test
    @Transactional
    public void getAllSistemaAguaLogsByIdSistemaAguaLogIsLessThanSomething() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get all the sistemaAguaLogList where idSistemaAguaLog less than or equals to DEFAULT_ID_SISTEMA_AGUA_LOG
        defaultSistemaAguaLogShouldNotBeFound("idSistemaAguaLog.lessThan=" + DEFAULT_ID_SISTEMA_AGUA_LOG);

        // Get all the sistemaAguaLogList where idSistemaAguaLog less than or equals to UPDATED_ID_SISTEMA_AGUA_LOG
        defaultSistemaAguaLogShouldBeFound("idSistemaAguaLog.lessThan=" + UPDATED_ID_SISTEMA_AGUA_LOG);
    }


    @Test
    @Transactional
    public void getAllSistemaAguaLogsByAcaoIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get all the sistemaAguaLogList where acao equals to DEFAULT_ACAO
        defaultSistemaAguaLogShouldBeFound("acao.equals=" + DEFAULT_ACAO);

        // Get all the sistemaAguaLogList where acao equals to UPDATED_ACAO
        defaultSistemaAguaLogShouldNotBeFound("acao.equals=" + UPDATED_ACAO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguaLogsByAcaoIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get all the sistemaAguaLogList where acao in DEFAULT_ACAO or UPDATED_ACAO
        defaultSistemaAguaLogShouldBeFound("acao.in=" + DEFAULT_ACAO + "," + UPDATED_ACAO);

        // Get all the sistemaAguaLogList where acao equals to UPDATED_ACAO
        defaultSistemaAguaLogShouldNotBeFound("acao.in=" + UPDATED_ACAO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguaLogsByAcaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get all the sistemaAguaLogList where acao is not null
        defaultSistemaAguaLogShouldBeFound("acao.specified=true");

        // Get all the sistemaAguaLogList where acao is null
        defaultSistemaAguaLogShouldNotBeFound("acao.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguaLogsByIdUsuarioIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get all the sistemaAguaLogList where idUsuario equals to DEFAULT_ID_USUARIO
        defaultSistemaAguaLogShouldBeFound("idUsuario.equals=" + DEFAULT_ID_USUARIO);

        // Get all the sistemaAguaLogList where idUsuario equals to UPDATED_ID_USUARIO
        defaultSistemaAguaLogShouldNotBeFound("idUsuario.equals=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguaLogsByIdUsuarioIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get all the sistemaAguaLogList where idUsuario in DEFAULT_ID_USUARIO or UPDATED_ID_USUARIO
        defaultSistemaAguaLogShouldBeFound("idUsuario.in=" + DEFAULT_ID_USUARIO + "," + UPDATED_ID_USUARIO);

        // Get all the sistemaAguaLogList where idUsuario equals to UPDATED_ID_USUARIO
        defaultSistemaAguaLogShouldNotBeFound("idUsuario.in=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguaLogsByIdUsuarioIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get all the sistemaAguaLogList where idUsuario is not null
        defaultSistemaAguaLogShouldBeFound("idUsuario.specified=true");

        // Get all the sistemaAguaLogList where idUsuario is null
        defaultSistemaAguaLogShouldNotBeFound("idUsuario.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguaLogsByIdUsuarioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get all the sistemaAguaLogList where idUsuario greater than or equals to DEFAULT_ID_USUARIO
        defaultSistemaAguaLogShouldBeFound("idUsuario.greaterOrEqualThan=" + DEFAULT_ID_USUARIO);

        // Get all the sistemaAguaLogList where idUsuario greater than or equals to UPDATED_ID_USUARIO
        defaultSistemaAguaLogShouldNotBeFound("idUsuario.greaterOrEqualThan=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllSistemaAguaLogsByIdUsuarioIsLessThanSomething() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get all the sistemaAguaLogList where idUsuario less than or equals to DEFAULT_ID_USUARIO
        defaultSistemaAguaLogShouldNotBeFound("idUsuario.lessThan=" + DEFAULT_ID_USUARIO);

        // Get all the sistemaAguaLogList where idUsuario less than or equals to UPDATED_ID_USUARIO
        defaultSistemaAguaLogShouldBeFound("idUsuario.lessThan=" + UPDATED_ID_USUARIO);
    }


    @Test
    @Transactional
    public void getAllSistemaAguaLogsByLogIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get all the sistemaAguaLogList where log equals to DEFAULT_LOG
        defaultSistemaAguaLogShouldBeFound("log.equals=" + DEFAULT_LOG);

        // Get all the sistemaAguaLogList where log equals to UPDATED_LOG
        defaultSistemaAguaLogShouldNotBeFound("log.equals=" + UPDATED_LOG);
    }

    @Test
    @Transactional
    public void getAllSistemaAguaLogsByLogIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get all the sistemaAguaLogList where log in DEFAULT_LOG or UPDATED_LOG
        defaultSistemaAguaLogShouldBeFound("log.in=" + DEFAULT_LOG + "," + UPDATED_LOG);

        // Get all the sistemaAguaLogList where log equals to UPDATED_LOG
        defaultSistemaAguaLogShouldNotBeFound("log.in=" + UPDATED_LOG);
    }

    @Test
    @Transactional
    public void getAllSistemaAguaLogsByLogIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get all the sistemaAguaLogList where log is not null
        defaultSistemaAguaLogShouldBeFound("log.specified=true");

        // Get all the sistemaAguaLogList where log is null
        defaultSistemaAguaLogShouldNotBeFound("log.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguaLogsByDtLogIsEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get all the sistemaAguaLogList where dtLog equals to DEFAULT_DT_LOG
        defaultSistemaAguaLogShouldBeFound("dtLog.equals=" + DEFAULT_DT_LOG);

        // Get all the sistemaAguaLogList where dtLog equals to UPDATED_DT_LOG
        defaultSistemaAguaLogShouldNotBeFound("dtLog.equals=" + UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void getAllSistemaAguaLogsByDtLogIsInShouldWork() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get all the sistemaAguaLogList where dtLog in DEFAULT_DT_LOG or UPDATED_DT_LOG
        defaultSistemaAguaLogShouldBeFound("dtLog.in=" + DEFAULT_DT_LOG + "," + UPDATED_DT_LOG);

        // Get all the sistemaAguaLogList where dtLog equals to UPDATED_DT_LOG
        defaultSistemaAguaLogShouldNotBeFound("dtLog.in=" + UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void getAllSistemaAguaLogsByDtLogIsNullOrNotNull() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get all the sistemaAguaLogList where dtLog is not null
        defaultSistemaAguaLogShouldBeFound("dtLog.specified=true");

        // Get all the sistemaAguaLogList where dtLog is null
        defaultSistemaAguaLogShouldNotBeFound("dtLog.specified=false");
    }

    @Test
    @Transactional
    public void getAllSistemaAguaLogsByDtLogIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get all the sistemaAguaLogList where dtLog greater than or equals to DEFAULT_DT_LOG
        defaultSistemaAguaLogShouldBeFound("dtLog.greaterOrEqualThan=" + DEFAULT_DT_LOG);

        // Get all the sistemaAguaLogList where dtLog greater than or equals to UPDATED_DT_LOG
        defaultSistemaAguaLogShouldNotBeFound("dtLog.greaterOrEqualThan=" + UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void getAllSistemaAguaLogsByDtLogIsLessThanSomething() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);

        // Get all the sistemaAguaLogList where dtLog less than or equals to DEFAULT_DT_LOG
        defaultSistemaAguaLogShouldNotBeFound("dtLog.lessThan=" + DEFAULT_DT_LOG);

        // Get all the sistemaAguaLogList where dtLog less than or equals to UPDATED_DT_LOG
        defaultSistemaAguaLogShouldBeFound("dtLog.lessThan=" + UPDATED_DT_LOG);
    }


    @Test
    @Transactional
    public void getAllSistemaAguaLogsByIdSistemaAguaIsEqualToSomething() throws Exception {
        // Initialize the database
        SistemaAgua idSistemaAgua = SistemaAguaResourceIntTest.createEntity(em);
        em.persist(idSistemaAgua);
        em.flush();
        sistemaAguaLog.setIdSistemaAgua(idSistemaAgua);
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);
        Long idSistemaAguaId = idSistemaAgua.getId();

        // Get all the sistemaAguaLogList where idSistemaAgua equals to idSistemaAguaId
        defaultSistemaAguaLogShouldBeFound("idSistemaAguaId.equals=" + idSistemaAguaId);

        // Get all the sistemaAguaLogList where idSistemaAgua equals to idSistemaAguaId + 1
        defaultSistemaAguaLogShouldNotBeFound("idSistemaAguaId.equals=" + (idSistemaAguaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultSistemaAguaLogShouldBeFound(String filter) throws Exception {
        restSistemaAguaLogMockMvc.perform(get("/api/sistema-agua-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sistemaAguaLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSistemaAguaLog").value(hasItem(DEFAULT_ID_SISTEMA_AGUA_LOG.intValue())))
            .andExpect(jsonPath("$.[*].acao").value(hasItem(DEFAULT_ACAO.toString())))
            .andExpect(jsonPath("$.[*].idUsuario").value(hasItem(DEFAULT_ID_USUARIO.intValue())))
            .andExpect(jsonPath("$.[*].log").value(hasItem(DEFAULT_LOG.toString())))
            .andExpect(jsonPath("$.[*].dtLog").value(hasItem(DEFAULT_DT_LOG.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultSistemaAguaLogShouldNotBeFound(String filter) throws Exception {
        restSistemaAguaLogMockMvc.perform(get("/api/sistema-agua-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingSistemaAguaLog() throws Exception {
        // Get the sistemaAguaLog
        restSistemaAguaLogMockMvc.perform(get("/api/sistema-agua-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSistemaAguaLog() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);
        int databaseSizeBeforeUpdate = sistemaAguaLogRepository.findAll().size();

        // Update the sistemaAguaLog
        SistemaAguaLog updatedSistemaAguaLog = sistemaAguaLogRepository.findOne(sistemaAguaLog.getId());
        // Disconnect from session so that the updates on updatedSistemaAguaLog are not directly saved in db
        em.detach(updatedSistemaAguaLog);
        updatedSistemaAguaLog
            .acao(UPDATED_ACAO)
            .idUsuario(UPDATED_ID_USUARIO)
            .log(UPDATED_LOG)
            .dtLog(UPDATED_DT_LOG);
        SistemaAguaLogDTO sistemaAguaLogDTO = sistemaAguaLogMapper.toDto(updatedSistemaAguaLog);

        restSistemaAguaLogMockMvc.perform(put("/api/sistema-agua-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaLogDTO)))
            .andExpect(status().isOk());

        // Validate the SistemaAguaLog in the database
        List<SistemaAguaLog> sistemaAguaLogList = sistemaAguaLogRepository.findAll();
        assertThat(sistemaAguaLogList).hasSize(databaseSizeBeforeUpdate);
        SistemaAguaLog testSistemaAguaLog = sistemaAguaLogList.get(sistemaAguaLogList.size() - 1);
        assertThat(testSistemaAguaLog.getAcao()).isEqualTo(UPDATED_ACAO);
        assertThat(testSistemaAguaLog.getIdUsuario()).isEqualTo(UPDATED_ID_USUARIO);
        assertThat(testSistemaAguaLog.getLog()).isEqualTo(UPDATED_LOG);
        assertThat(testSistemaAguaLog.getDtLog()).isEqualTo(UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void updateNonExistingSistemaAguaLog() throws Exception {
        int databaseSizeBeforeUpdate = sistemaAguaLogRepository.findAll().size();

        // Create the SistemaAguaLog
        SistemaAguaLogDTO sistemaAguaLogDTO = sistemaAguaLogMapper.toDto(sistemaAguaLog);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSistemaAguaLogMockMvc.perform(put("/api/sistema-agua-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sistemaAguaLogDTO)))
            .andExpect(status().isCreated());

        // Validate the SistemaAguaLog in the database
        List<SistemaAguaLog> sistemaAguaLogList = sistemaAguaLogRepository.findAll();
        assertThat(sistemaAguaLogList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSistemaAguaLog() throws Exception {
        // Initialize the database
        sistemaAguaLogRepository.saveAndFlush(sistemaAguaLog);
        int databaseSizeBeforeDelete = sistemaAguaLogRepository.findAll().size();

        // Get the sistemaAguaLog
        restSistemaAguaLogMockMvc.perform(delete("/api/sistema-agua-logs/{id}", sistemaAguaLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SistemaAguaLog> sistemaAguaLogList = sistemaAguaLogRepository.findAll();
        assertThat(sistemaAguaLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SistemaAguaLog.class);
        SistemaAguaLog sistemaAguaLog1 = new SistemaAguaLog();
        sistemaAguaLog1.setId(1L);
        SistemaAguaLog sistemaAguaLog2 = new SistemaAguaLog();
        sistemaAguaLog2.setId(sistemaAguaLog1.getId());
        assertThat(sistemaAguaLog1).isEqualTo(sistemaAguaLog2);
        sistemaAguaLog2.setId(2L);
        assertThat(sistemaAguaLog1).isNotEqualTo(sistemaAguaLog2);
        sistemaAguaLog1.setId(null);
        assertThat(sistemaAguaLog1).isNotEqualTo(sistemaAguaLog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SistemaAguaLogDTO.class);
        SistemaAguaLogDTO sistemaAguaLogDTO1 = new SistemaAguaLogDTO();
        sistemaAguaLogDTO1.setId(1L);
        SistemaAguaLogDTO sistemaAguaLogDTO2 = new SistemaAguaLogDTO();
        assertThat(sistemaAguaLogDTO1).isNotEqualTo(sistemaAguaLogDTO2);
        sistemaAguaLogDTO2.setId(sistemaAguaLogDTO1.getId());
        assertThat(sistemaAguaLogDTO1).isEqualTo(sistemaAguaLogDTO2);
        sistemaAguaLogDTO2.setId(2L);
        assertThat(sistemaAguaLogDTO1).isNotEqualTo(sistemaAguaLogDTO2);
        sistemaAguaLogDTO1.setId(null);
        assertThat(sistemaAguaLogDTO1).isNotEqualTo(sistemaAguaLogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sistemaAguaLogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sistemaAguaLogMapper.fromId(null)).isNull();
    }
}
