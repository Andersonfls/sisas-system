package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.IndicadorProducaoLog;
import com.minea.sisas.domain.IndicadorProducao;
import com.minea.sisas.repository.IndicadorProducaoLogRepository;
import com.minea.sisas.service.IndicadorProducaoLogService;
import com.minea.sisas.service.dto.IndicadorProducaoLogDTO;
import com.minea.sisas.service.mapper.IndicadorProducaoLogMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.IndicadorProducaoLogQueryService;

import com.minea.sisas.web.rest.IndicadorProducaoLogResource;
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
 * Test class for the IndicadorProducaoLogResource REST controller.
 *
 * @see IndicadorProducaoLogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class IndicadorProducaoLogResourceIntTest {

    private static final Long DEFAULT_ID_INDICADOR_PRODUCAO_LOG = 1L;
    private static final Long UPDATED_ID_INDICADOR_PRODUCAO_LOG = 2L;

    private static final String DEFAULT_ACAO = "AAAAAAAAAA";
    private static final String UPDATED_ACAO = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_USUARIO = 1L;
    private static final Long UPDATED_ID_USUARIO = 2L;

    private static final String DEFAULT_LOG = "AAAAAAAAAA";
    private static final String UPDATED_LOG = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DT_LOG = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_LOG = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private IndicadorProducaoLogRepository indicadorProducaoLogRepository;

    @Autowired
    private IndicadorProducaoLogMapper indicadorProducaoLogMapper;

    @Autowired
    private IndicadorProducaoLogService indicadorProducaoLogService;

    @Autowired
    private IndicadorProducaoLogQueryService indicadorProducaoLogQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restIndicadorProducaoLogMockMvc;

    private IndicadorProducaoLog indicadorProducaoLog;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IndicadorProducaoLogResource indicadorProducaoLogResource = new IndicadorProducaoLogResource(indicadorProducaoLogService, indicadorProducaoLogQueryService, indicadorProducaoLogRepository);
        this.restIndicadorProducaoLogMockMvc = MockMvcBuilders.standaloneSetup(indicadorProducaoLogResource)
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
    public static IndicadorProducaoLog createEntity(EntityManager em) {
        IndicadorProducaoLog indicadorProducaoLog = new IndicadorProducaoLog()
            .acao(DEFAULT_ACAO)
            .idUsuario(DEFAULT_ID_USUARIO)
            .log(DEFAULT_LOG)
            .dtLog(DEFAULT_DT_LOG);
        // Add required entity
        IndicadorProducao idIndicadorProducao = IndicadorProducaoResourceIntTest.createEntity(em);
        em.persist(idIndicadorProducao);
        em.flush();
        indicadorProducaoLog.setIdIndicadorProducao(idIndicadorProducao);
        return indicadorProducaoLog;
    }

    @Before
    public void initTest() {
        indicadorProducaoLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createIndicadorProducaoLog() throws Exception {
        int databaseSizeBeforeCreate = indicadorProducaoLogRepository.findAll().size();

        // Create the IndicadorProducaoLog
        IndicadorProducaoLogDTO indicadorProducaoLogDTO = indicadorProducaoLogMapper.toDto(indicadorProducaoLog);
        restIndicadorProducaoLogMockMvc.perform(post("/api/indicador-producao-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoLogDTO)))
            .andExpect(status().isCreated());

        // Validate the IndicadorProducaoLog in the database
        List<IndicadorProducaoLog> indicadorProducaoLogList = indicadorProducaoLogRepository.findAll();
        assertThat(indicadorProducaoLogList).hasSize(databaseSizeBeforeCreate + 1);
        IndicadorProducaoLog testIndicadorProducaoLog = indicadorProducaoLogList.get(indicadorProducaoLogList.size() - 1);
        assertThat(testIndicadorProducaoLog.getAcao()).isEqualTo(DEFAULT_ACAO);
        assertThat(testIndicadorProducaoLog.getIdUsuario()).isEqualTo(DEFAULT_ID_USUARIO);
        assertThat(testIndicadorProducaoLog.getLog()).isEqualTo(DEFAULT_LOG);
        assertThat(testIndicadorProducaoLog.getDtLog()).isEqualTo(DEFAULT_DT_LOG);
    }

    @Test
    @Transactional
    public void createIndicadorProducaoLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = indicadorProducaoLogRepository.findAll().size();

        // Create the IndicadorProducaoLog with an existing ID
        indicadorProducaoLog.setId(1L);
        IndicadorProducaoLogDTO indicadorProducaoLogDTO = indicadorProducaoLogMapper.toDto(indicadorProducaoLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIndicadorProducaoLogMockMvc.perform(post("/api/indicador-producao-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IndicadorProducaoLog in the database
        List<IndicadorProducaoLog> indicadorProducaoLogList = indicadorProducaoLogRepository.findAll();
        assertThat(indicadorProducaoLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdIndicadorProducaoLogIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoLogRepository.findAll().size();
        // set the field null

        // Create the IndicadorProducaoLog, which fails.
        IndicadorProducaoLogDTO indicadorProducaoLogDTO = indicadorProducaoLogMapper.toDto(indicadorProducaoLog);

        restIndicadorProducaoLogMockMvc.perform(post("/api/indicador-producao-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoLogDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducaoLog> indicadorProducaoLogList = indicadorProducaoLogRepository.findAll();
        assertThat(indicadorProducaoLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAcaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoLogRepository.findAll().size();
        // set the field null
        indicadorProducaoLog.setAcao(null);

        // Create the IndicadorProducaoLog, which fails.
        IndicadorProducaoLogDTO indicadorProducaoLogDTO = indicadorProducaoLogMapper.toDto(indicadorProducaoLog);

        restIndicadorProducaoLogMockMvc.perform(post("/api/indicador-producao-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoLogDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducaoLog> indicadorProducaoLogList = indicadorProducaoLogRepository.findAll();
        assertThat(indicadorProducaoLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdUsuarioIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoLogRepository.findAll().size();
        // set the field null
        indicadorProducaoLog.setIdUsuario(null);

        // Create the IndicadorProducaoLog, which fails.
        IndicadorProducaoLogDTO indicadorProducaoLogDTO = indicadorProducaoLogMapper.toDto(indicadorProducaoLog);

        restIndicadorProducaoLogMockMvc.perform(post("/api/indicador-producao-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoLogDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducaoLog> indicadorProducaoLogList = indicadorProducaoLogRepository.findAll();
        assertThat(indicadorProducaoLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLogIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoLogRepository.findAll().size();
        // set the field null
        indicadorProducaoLog.setLog(null);

        // Create the IndicadorProducaoLog, which fails.
        IndicadorProducaoLogDTO indicadorProducaoLogDTO = indicadorProducaoLogMapper.toDto(indicadorProducaoLog);

        restIndicadorProducaoLogMockMvc.perform(post("/api/indicador-producao-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoLogDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducaoLog> indicadorProducaoLogList = indicadorProducaoLogRepository.findAll();
        assertThat(indicadorProducaoLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDtLogIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicadorProducaoLogRepository.findAll().size();
        // set the field null
        indicadorProducaoLog.setDtLog(null);

        // Create the IndicadorProducaoLog, which fails.
        IndicadorProducaoLogDTO indicadorProducaoLogDTO = indicadorProducaoLogMapper.toDto(indicadorProducaoLog);

        restIndicadorProducaoLogMockMvc.perform(post("/api/indicador-producao-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoLogDTO)))
            .andExpect(status().isBadRequest());

        List<IndicadorProducaoLog> indicadorProducaoLogList = indicadorProducaoLogRepository.findAll();
        assertThat(indicadorProducaoLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaoLogs() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get all the indicadorProducaoLogList
        restIndicadorProducaoLogMockMvc.perform(get("/api/indicador-producao-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(indicadorProducaoLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].idIndicadorProducaoLog").value(hasItem(DEFAULT_ID_INDICADOR_PRODUCAO_LOG.intValue())))
            .andExpect(jsonPath("$.[*].acao").value(hasItem(DEFAULT_ACAO.toString())))
            .andExpect(jsonPath("$.[*].idUsuario").value(hasItem(DEFAULT_ID_USUARIO.intValue())))
            .andExpect(jsonPath("$.[*].log").value(hasItem(DEFAULT_LOG.toString())))
            .andExpect(jsonPath("$.[*].dtLog").value(hasItem(DEFAULT_DT_LOG.toString())));
    }

    @Test
    @Transactional
    public void getIndicadorProducaoLog() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get the indicadorProducaoLog
        restIndicadorProducaoLogMockMvc.perform(get("/api/indicador-producao-logs/{id}", indicadorProducaoLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(indicadorProducaoLog.getId().intValue()))
            .andExpect(jsonPath("$.idIndicadorProducaoLog").value(DEFAULT_ID_INDICADOR_PRODUCAO_LOG.intValue()))
            .andExpect(jsonPath("$.acao").value(DEFAULT_ACAO.toString()))
            .andExpect(jsonPath("$.idUsuario").value(DEFAULT_ID_USUARIO.intValue()))
            .andExpect(jsonPath("$.log").value(DEFAULT_LOG.toString()))
            .andExpect(jsonPath("$.dtLog").value(DEFAULT_DT_LOG.toString()));
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaoLogsByIdIndicadorProducaoLogIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get all the indicadorProducaoLogList where idIndicadorProducaoLog equals to DEFAULT_ID_INDICADOR_PRODUCAO_LOG
        defaultIndicadorProducaoLogShouldBeFound("idIndicadorProducaoLog.equals=" + DEFAULT_ID_INDICADOR_PRODUCAO_LOG);

        // Get all the indicadorProducaoLogList where idIndicadorProducaoLog equals to UPDATED_ID_INDICADOR_PRODUCAO_LOG
        defaultIndicadorProducaoLogShouldNotBeFound("idIndicadorProducaoLog.equals=" + UPDATED_ID_INDICADOR_PRODUCAO_LOG);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaoLogsByIdIndicadorProducaoLogIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get all the indicadorProducaoLogList where idIndicadorProducaoLog in DEFAULT_ID_INDICADOR_PRODUCAO_LOG or UPDATED_ID_INDICADOR_PRODUCAO_LOG
        defaultIndicadorProducaoLogShouldBeFound("idIndicadorProducaoLog.in=" + DEFAULT_ID_INDICADOR_PRODUCAO_LOG + "," + UPDATED_ID_INDICADOR_PRODUCAO_LOG);

        // Get all the indicadorProducaoLogList where idIndicadorProducaoLog equals to UPDATED_ID_INDICADOR_PRODUCAO_LOG
        defaultIndicadorProducaoLogShouldNotBeFound("idIndicadorProducaoLog.in=" + UPDATED_ID_INDICADOR_PRODUCAO_LOG);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaoLogsByIdIndicadorProducaoLogIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get all the indicadorProducaoLogList where idIndicadorProducaoLog is not null
        defaultIndicadorProducaoLogShouldBeFound("idIndicadorProducaoLog.specified=true");

        // Get all the indicadorProducaoLogList where idIndicadorProducaoLog is null
        defaultIndicadorProducaoLogShouldNotBeFound("idIndicadorProducaoLog.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaoLogsByIdIndicadorProducaoLogIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get all the indicadorProducaoLogList where idIndicadorProducaoLog greater than or equals to DEFAULT_ID_INDICADOR_PRODUCAO_LOG
        defaultIndicadorProducaoLogShouldBeFound("idIndicadorProducaoLog.greaterOrEqualThan=" + DEFAULT_ID_INDICADOR_PRODUCAO_LOG);

        // Get all the indicadorProducaoLogList where idIndicadorProducaoLog greater than or equals to UPDATED_ID_INDICADOR_PRODUCAO_LOG
        defaultIndicadorProducaoLogShouldNotBeFound("idIndicadorProducaoLog.greaterOrEqualThan=" + UPDATED_ID_INDICADOR_PRODUCAO_LOG);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaoLogsByIdIndicadorProducaoLogIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get all the indicadorProducaoLogList where idIndicadorProducaoLog less than or equals to DEFAULT_ID_INDICADOR_PRODUCAO_LOG
        defaultIndicadorProducaoLogShouldNotBeFound("idIndicadorProducaoLog.lessThan=" + DEFAULT_ID_INDICADOR_PRODUCAO_LOG);

        // Get all the indicadorProducaoLogList where idIndicadorProducaoLog less than or equals to UPDATED_ID_INDICADOR_PRODUCAO_LOG
        defaultIndicadorProducaoLogShouldBeFound("idIndicadorProducaoLog.lessThan=" + UPDATED_ID_INDICADOR_PRODUCAO_LOG);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaoLogsByAcaoIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get all the indicadorProducaoLogList where acao equals to DEFAULT_ACAO
        defaultIndicadorProducaoLogShouldBeFound("acao.equals=" + DEFAULT_ACAO);

        // Get all the indicadorProducaoLogList where acao equals to UPDATED_ACAO
        defaultIndicadorProducaoLogShouldNotBeFound("acao.equals=" + UPDATED_ACAO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaoLogsByAcaoIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get all the indicadorProducaoLogList where acao in DEFAULT_ACAO or UPDATED_ACAO
        defaultIndicadorProducaoLogShouldBeFound("acao.in=" + DEFAULT_ACAO + "," + UPDATED_ACAO);

        // Get all the indicadorProducaoLogList where acao equals to UPDATED_ACAO
        defaultIndicadorProducaoLogShouldNotBeFound("acao.in=" + UPDATED_ACAO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaoLogsByAcaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get all the indicadorProducaoLogList where acao is not null
        defaultIndicadorProducaoLogShouldBeFound("acao.specified=true");

        // Get all the indicadorProducaoLogList where acao is null
        defaultIndicadorProducaoLogShouldNotBeFound("acao.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaoLogsByIdUsuarioIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get all the indicadorProducaoLogList where idUsuario equals to DEFAULT_ID_USUARIO
        defaultIndicadorProducaoLogShouldBeFound("idUsuario.equals=" + DEFAULT_ID_USUARIO);

        // Get all the indicadorProducaoLogList where idUsuario equals to UPDATED_ID_USUARIO
        defaultIndicadorProducaoLogShouldNotBeFound("idUsuario.equals=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaoLogsByIdUsuarioIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get all the indicadorProducaoLogList where idUsuario in DEFAULT_ID_USUARIO or UPDATED_ID_USUARIO
        defaultIndicadorProducaoLogShouldBeFound("idUsuario.in=" + DEFAULT_ID_USUARIO + "," + UPDATED_ID_USUARIO);

        // Get all the indicadorProducaoLogList where idUsuario equals to UPDATED_ID_USUARIO
        defaultIndicadorProducaoLogShouldNotBeFound("idUsuario.in=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaoLogsByIdUsuarioIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get all the indicadorProducaoLogList where idUsuario is not null
        defaultIndicadorProducaoLogShouldBeFound("idUsuario.specified=true");

        // Get all the indicadorProducaoLogList where idUsuario is null
        defaultIndicadorProducaoLogShouldNotBeFound("idUsuario.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaoLogsByIdUsuarioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get all the indicadorProducaoLogList where idUsuario greater than or equals to DEFAULT_ID_USUARIO
        defaultIndicadorProducaoLogShouldBeFound("idUsuario.greaterOrEqualThan=" + DEFAULT_ID_USUARIO);

        // Get all the indicadorProducaoLogList where idUsuario greater than or equals to UPDATED_ID_USUARIO
        defaultIndicadorProducaoLogShouldNotBeFound("idUsuario.greaterOrEqualThan=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaoLogsByIdUsuarioIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get all the indicadorProducaoLogList where idUsuario less than or equals to DEFAULT_ID_USUARIO
        defaultIndicadorProducaoLogShouldNotBeFound("idUsuario.lessThan=" + DEFAULT_ID_USUARIO);

        // Get all the indicadorProducaoLogList where idUsuario less than or equals to UPDATED_ID_USUARIO
        defaultIndicadorProducaoLogShouldBeFound("idUsuario.lessThan=" + UPDATED_ID_USUARIO);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaoLogsByLogIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get all the indicadorProducaoLogList where log equals to DEFAULT_LOG
        defaultIndicadorProducaoLogShouldBeFound("log.equals=" + DEFAULT_LOG);

        // Get all the indicadorProducaoLogList where log equals to UPDATED_LOG
        defaultIndicadorProducaoLogShouldNotBeFound("log.equals=" + UPDATED_LOG);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaoLogsByLogIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get all the indicadorProducaoLogList where log in DEFAULT_LOG or UPDATED_LOG
        defaultIndicadorProducaoLogShouldBeFound("log.in=" + DEFAULT_LOG + "," + UPDATED_LOG);

        // Get all the indicadorProducaoLogList where log equals to UPDATED_LOG
        defaultIndicadorProducaoLogShouldNotBeFound("log.in=" + UPDATED_LOG);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaoLogsByLogIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get all the indicadorProducaoLogList where log is not null
        defaultIndicadorProducaoLogShouldBeFound("log.specified=true");

        // Get all the indicadorProducaoLogList where log is null
        defaultIndicadorProducaoLogShouldNotBeFound("log.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaoLogsByDtLogIsEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get all the indicadorProducaoLogList where dtLog equals to DEFAULT_DT_LOG
        defaultIndicadorProducaoLogShouldBeFound("dtLog.equals=" + DEFAULT_DT_LOG);

        // Get all the indicadorProducaoLogList where dtLog equals to UPDATED_DT_LOG
        defaultIndicadorProducaoLogShouldNotBeFound("dtLog.equals=" + UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaoLogsByDtLogIsInShouldWork() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get all the indicadorProducaoLogList where dtLog in DEFAULT_DT_LOG or UPDATED_DT_LOG
        defaultIndicadorProducaoLogShouldBeFound("dtLog.in=" + DEFAULT_DT_LOG + "," + UPDATED_DT_LOG);

        // Get all the indicadorProducaoLogList where dtLog equals to UPDATED_DT_LOG
        defaultIndicadorProducaoLogShouldNotBeFound("dtLog.in=" + UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaoLogsByDtLogIsNullOrNotNull() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get all the indicadorProducaoLogList where dtLog is not null
        defaultIndicadorProducaoLogShouldBeFound("dtLog.specified=true");

        // Get all the indicadorProducaoLogList where dtLog is null
        defaultIndicadorProducaoLogShouldNotBeFound("dtLog.specified=false");
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaoLogsByDtLogIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get all the indicadorProducaoLogList where dtLog greater than or equals to DEFAULT_DT_LOG
        defaultIndicadorProducaoLogShouldBeFound("dtLog.greaterOrEqualThan=" + DEFAULT_DT_LOG);

        // Get all the indicadorProducaoLogList where dtLog greater than or equals to UPDATED_DT_LOG
        defaultIndicadorProducaoLogShouldNotBeFound("dtLog.greaterOrEqualThan=" + UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void getAllIndicadorProducaoLogsByDtLogIsLessThanSomething() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);

        // Get all the indicadorProducaoLogList where dtLog less than or equals to DEFAULT_DT_LOG
        defaultIndicadorProducaoLogShouldNotBeFound("dtLog.lessThan=" + DEFAULT_DT_LOG);

        // Get all the indicadorProducaoLogList where dtLog less than or equals to UPDATED_DT_LOG
        defaultIndicadorProducaoLogShouldBeFound("dtLog.lessThan=" + UPDATED_DT_LOG);
    }


    @Test
    @Transactional
    public void getAllIndicadorProducaoLogsByIdIndicadorProducaoIsEqualToSomething() throws Exception {
        // Initialize the database
        IndicadorProducao idIndicadorProducao = IndicadorProducaoResourceIntTest.createEntity(em);
        em.persist(idIndicadorProducao);
        em.flush();
        indicadorProducaoLog.setIdIndicadorProducao(idIndicadorProducao);
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);
        Long idIndicadorProducaoId = idIndicadorProducao.getId();

        // Get all the indicadorProducaoLogList where idIndicadorProducao equals to idIndicadorProducaoId
        defaultIndicadorProducaoLogShouldBeFound("idIndicadorProducaoId.equals=" + idIndicadorProducaoId);

        // Get all the indicadorProducaoLogList where idIndicadorProducao equals to idIndicadorProducaoId + 1
        defaultIndicadorProducaoLogShouldNotBeFound("idIndicadorProducaoId.equals=" + (idIndicadorProducaoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultIndicadorProducaoLogShouldBeFound(String filter) throws Exception {
        restIndicadorProducaoLogMockMvc.perform(get("/api/indicador-producao-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(indicadorProducaoLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].idIndicadorProducaoLog").value(hasItem(DEFAULT_ID_INDICADOR_PRODUCAO_LOG.intValue())))
            .andExpect(jsonPath("$.[*].acao").value(hasItem(DEFAULT_ACAO.toString())))
            .andExpect(jsonPath("$.[*].idUsuario").value(hasItem(DEFAULT_ID_USUARIO.intValue())))
            .andExpect(jsonPath("$.[*].log").value(hasItem(DEFAULT_LOG.toString())))
            .andExpect(jsonPath("$.[*].dtLog").value(hasItem(DEFAULT_DT_LOG.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultIndicadorProducaoLogShouldNotBeFound(String filter) throws Exception {
        restIndicadorProducaoLogMockMvc.perform(get("/api/indicador-producao-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingIndicadorProducaoLog() throws Exception {
        // Get the indicadorProducaoLog
        restIndicadorProducaoLogMockMvc.perform(get("/api/indicador-producao-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIndicadorProducaoLog() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);
        int databaseSizeBeforeUpdate = indicadorProducaoLogRepository.findAll().size();

        // Update the indicadorProducaoLog
        IndicadorProducaoLog updatedIndicadorProducaoLog = indicadorProducaoLogRepository.findOne(indicadorProducaoLog.getId());
        // Disconnect from session so that the updates on updatedIndicadorProducaoLog are not directly saved in db
        em.detach(updatedIndicadorProducaoLog);
        updatedIndicadorProducaoLog
            .acao(UPDATED_ACAO)
            .idUsuario(UPDATED_ID_USUARIO)
            .log(UPDATED_LOG)
            .dtLog(UPDATED_DT_LOG);
        IndicadorProducaoLogDTO indicadorProducaoLogDTO = indicadorProducaoLogMapper.toDto(updatedIndicadorProducaoLog);

        restIndicadorProducaoLogMockMvc.perform(put("/api/indicador-producao-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoLogDTO)))
            .andExpect(status().isOk());

        // Validate the IndicadorProducaoLog in the database
        List<IndicadorProducaoLog> indicadorProducaoLogList = indicadorProducaoLogRepository.findAll();
        assertThat(indicadorProducaoLogList).hasSize(databaseSizeBeforeUpdate);
        IndicadorProducaoLog testIndicadorProducaoLog = indicadorProducaoLogList.get(indicadorProducaoLogList.size() - 1);
        assertThat(testIndicadorProducaoLog.getAcao()).isEqualTo(UPDATED_ACAO);
        assertThat(testIndicadorProducaoLog.getIdUsuario()).isEqualTo(UPDATED_ID_USUARIO);
        assertThat(testIndicadorProducaoLog.getLog()).isEqualTo(UPDATED_LOG);
        assertThat(testIndicadorProducaoLog.getDtLog()).isEqualTo(UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void updateNonExistingIndicadorProducaoLog() throws Exception {
        int databaseSizeBeforeUpdate = indicadorProducaoLogRepository.findAll().size();

        // Create the IndicadorProducaoLog
        IndicadorProducaoLogDTO indicadorProducaoLogDTO = indicadorProducaoLogMapper.toDto(indicadorProducaoLog);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restIndicadorProducaoLogMockMvc.perform(put("/api/indicador-producao-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicadorProducaoLogDTO)))
            .andExpect(status().isCreated());

        // Validate the IndicadorProducaoLog in the database
        List<IndicadorProducaoLog> indicadorProducaoLogList = indicadorProducaoLogRepository.findAll();
        assertThat(indicadorProducaoLogList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteIndicadorProducaoLog() throws Exception {
        // Initialize the database
        indicadorProducaoLogRepository.saveAndFlush(indicadorProducaoLog);
        int databaseSizeBeforeDelete = indicadorProducaoLogRepository.findAll().size();

        // Get the indicadorProducaoLog
        restIndicadorProducaoLogMockMvc.perform(delete("/api/indicador-producao-logs/{id}", indicadorProducaoLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<IndicadorProducaoLog> indicadorProducaoLogList = indicadorProducaoLogRepository.findAll();
        assertThat(indicadorProducaoLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IndicadorProducaoLog.class);
        IndicadorProducaoLog indicadorProducaoLog1 = new IndicadorProducaoLog();
        indicadorProducaoLog1.setId(1L);
        IndicadorProducaoLog indicadorProducaoLog2 = new IndicadorProducaoLog();
        indicadorProducaoLog2.setId(indicadorProducaoLog1.getId());
        assertThat(indicadorProducaoLog1).isEqualTo(indicadorProducaoLog2);
        indicadorProducaoLog2.setId(2L);
        assertThat(indicadorProducaoLog1).isNotEqualTo(indicadorProducaoLog2);
        indicadorProducaoLog1.setId(null);
        assertThat(indicadorProducaoLog1).isNotEqualTo(indicadorProducaoLog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IndicadorProducaoLogDTO.class);
        IndicadorProducaoLogDTO indicadorProducaoLogDTO1 = new IndicadorProducaoLogDTO();
        indicadorProducaoLogDTO1.setId(1L);
        IndicadorProducaoLogDTO indicadorProducaoLogDTO2 = new IndicadorProducaoLogDTO();
        assertThat(indicadorProducaoLogDTO1).isNotEqualTo(indicadorProducaoLogDTO2);
        indicadorProducaoLogDTO2.setId(indicadorProducaoLogDTO1.getId());
        assertThat(indicadorProducaoLogDTO1).isEqualTo(indicadorProducaoLogDTO2);
        indicadorProducaoLogDTO2.setId(2L);
        assertThat(indicadorProducaoLogDTO1).isNotEqualTo(indicadorProducaoLogDTO2);
        indicadorProducaoLogDTO1.setId(null);
        assertThat(indicadorProducaoLogDTO1).isNotEqualTo(indicadorProducaoLogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(indicadorProducaoLogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(indicadorProducaoLogMapper.fromId(null)).isNull();
    }
}
