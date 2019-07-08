package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.ConfiguracoesLog;
import com.minea.sisas.repository.ConfiguracoesLogRepository;
import com.minea.sisas.service.ConfiguracoesLogService;
import com.minea.sisas.service.dto.ConfiguracoesLogDTO;
import com.minea.sisas.service.mapper.ConfiguracoesLogMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.ConfiguracoesLogQueryService;

import com.minea.sisas.web.rest.ConfiguracoesLogResource;
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
 * Test class for the ConfiguracoesLogResource REST controller.
 *
 * @see ConfiguracoesLogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class ConfiguracoesLogResourceIntTest {

    private static final Long DEFAULT_ID_CONFIGURACOES_LOG = 1L;
    private static final Long UPDATED_ID_CONFIGURACOES_LOG = 2L;

    private static final String DEFAULT_ACAO = "AAAAAAAAAA";
    private static final String UPDATED_ACAO = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_USUARIO = 1L;
    private static final Long UPDATED_ID_USUARIO = 2L;

    private static final String DEFAULT_LOG = "AAAAAAAAAA";
    private static final String UPDATED_LOG = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DT_LOG = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_LOG = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private ConfiguracoesLogRepository configuracoesLogRepository;

    @Autowired
    private ConfiguracoesLogMapper configuracoesLogMapper;

    @Autowired
    private ConfiguracoesLogService configuracoesLogService;

    @Autowired
    private ConfiguracoesLogQueryService configuracoesLogQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConfiguracoesLogMockMvc;

    private ConfiguracoesLog configuracoesLog;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConfiguracoesLogResource configuracoesLogResource = new ConfiguracoesLogResource(configuracoesLogService, configuracoesLogQueryService);
        this.restConfiguracoesLogMockMvc = MockMvcBuilders.standaloneSetup(configuracoesLogResource)
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
    public static ConfiguracoesLog createEntity(EntityManager em) {
        ConfiguracoesLog configuracoesLog = new ConfiguracoesLog()
            .acao(DEFAULT_ACAO)
            .idUsuario(DEFAULT_ID_USUARIO)
            .log(DEFAULT_LOG)
            .dtLog(DEFAULT_DT_LOG);
        return configuracoesLog;
    }

    @Before
    public void initTest() {
        configuracoesLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createConfiguracoesLog() throws Exception {
        int databaseSizeBeforeCreate = configuracoesLogRepository.findAll().size();

        // Create the ConfiguracoesLog
        ConfiguracoesLogDTO configuracoesLogDTO = configuracoesLogMapper.toDto(configuracoesLog);
        restConfiguracoesLogMockMvc.perform(post("/api/configuracoes-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configuracoesLogDTO)))
            .andExpect(status().isCreated());

        // Validate the ConfiguracoesLog in the database
        List<ConfiguracoesLog> configuracoesLogList = configuracoesLogRepository.findAll();
        assertThat(configuracoesLogList).hasSize(databaseSizeBeforeCreate + 1);
        ConfiguracoesLog testConfiguracoesLog = configuracoesLogList.get(configuracoesLogList.size() - 1);
        assertThat(testConfiguracoesLog.getAcao()).isEqualTo(DEFAULT_ACAO);
        assertThat(testConfiguracoesLog.getIdUsuario()).isEqualTo(DEFAULT_ID_USUARIO);
        assertThat(testConfiguracoesLog.getLog()).isEqualTo(DEFAULT_LOG);
        assertThat(testConfiguracoesLog.getDtLog()).isEqualTo(DEFAULT_DT_LOG);
    }

    @Test
    @Transactional
    public void createConfiguracoesLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = configuracoesLogRepository.findAll().size();

        // Create the ConfiguracoesLog with an existing ID
        configuracoesLog.setId(1L);
        ConfiguracoesLogDTO configuracoesLogDTO = configuracoesLogMapper.toDto(configuracoesLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConfiguracoesLogMockMvc.perform(post("/api/configuracoes-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configuracoesLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ConfiguracoesLog in the database
        List<ConfiguracoesLog> configuracoesLogList = configuracoesLogRepository.findAll();
        assertThat(configuracoesLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdConfiguracoesLogIsRequired() throws Exception {
        int databaseSizeBeforeTest = configuracoesLogRepository.findAll().size();
        // set the field null

        // Create the ConfiguracoesLog, which fails.
        ConfiguracoesLogDTO configuracoesLogDTO = configuracoesLogMapper.toDto(configuracoesLog);

        restConfiguracoesLogMockMvc.perform(post("/api/configuracoes-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configuracoesLogDTO)))
            .andExpect(status().isBadRequest());

        List<ConfiguracoesLog> configuracoesLogList = configuracoesLogRepository.findAll();
        assertThat(configuracoesLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAcaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = configuracoesLogRepository.findAll().size();
        // set the field null
        configuracoesLog.setAcao(null);

        // Create the ConfiguracoesLog, which fails.
        ConfiguracoesLogDTO configuracoesLogDTO = configuracoesLogMapper.toDto(configuracoesLog);

        restConfiguracoesLogMockMvc.perform(post("/api/configuracoes-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configuracoesLogDTO)))
            .andExpect(status().isBadRequest());

        List<ConfiguracoesLog> configuracoesLogList = configuracoesLogRepository.findAll();
        assertThat(configuracoesLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdUsuarioIsRequired() throws Exception {
        int databaseSizeBeforeTest = configuracoesLogRepository.findAll().size();
        // set the field null
        configuracoesLog.setIdUsuario(null);

        // Create the ConfiguracoesLog, which fails.
        ConfiguracoesLogDTO configuracoesLogDTO = configuracoesLogMapper.toDto(configuracoesLog);

        restConfiguracoesLogMockMvc.perform(post("/api/configuracoes-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configuracoesLogDTO)))
            .andExpect(status().isBadRequest());

        List<ConfiguracoesLog> configuracoesLogList = configuracoesLogRepository.findAll();
        assertThat(configuracoesLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLogIsRequired() throws Exception {
        int databaseSizeBeforeTest = configuracoesLogRepository.findAll().size();
        // set the field null
        configuracoesLog.setLog(null);

        // Create the ConfiguracoesLog, which fails.
        ConfiguracoesLogDTO configuracoesLogDTO = configuracoesLogMapper.toDto(configuracoesLog);

        restConfiguracoesLogMockMvc.perform(post("/api/configuracoes-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configuracoesLogDTO)))
            .andExpect(status().isBadRequest());

        List<ConfiguracoesLog> configuracoesLogList = configuracoesLogRepository.findAll();
        assertThat(configuracoesLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDtLogIsRequired() throws Exception {
        int databaseSizeBeforeTest = configuracoesLogRepository.findAll().size();
        // set the field null
        configuracoesLog.setDtLog(null);

        // Create the ConfiguracoesLog, which fails.
        ConfiguracoesLogDTO configuracoesLogDTO = configuracoesLogMapper.toDto(configuracoesLog);

        restConfiguracoesLogMockMvc.perform(post("/api/configuracoes-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configuracoesLogDTO)))
            .andExpect(status().isBadRequest());

        List<ConfiguracoesLog> configuracoesLogList = configuracoesLogRepository.findAll();
        assertThat(configuracoesLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllConfiguracoesLogs() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get all the configuracoesLogList
        restConfiguracoesLogMockMvc.perform(get("/api/configuracoes-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(configuracoesLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].idConfiguracoesLog").value(hasItem(DEFAULT_ID_CONFIGURACOES_LOG.intValue())))
            .andExpect(jsonPath("$.[*].acao").value(hasItem(DEFAULT_ACAO.toString())))
            .andExpect(jsonPath("$.[*].idUsuario").value(hasItem(DEFAULT_ID_USUARIO.intValue())))
            .andExpect(jsonPath("$.[*].log").value(hasItem(DEFAULT_LOG.toString())))
            .andExpect(jsonPath("$.[*].dtLog").value(hasItem(DEFAULT_DT_LOG.toString())));
    }

    @Test
    @Transactional
    public void getConfiguracoesLog() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get the configuracoesLog
        restConfiguracoesLogMockMvc.perform(get("/api/configuracoes-logs/{id}", configuracoesLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(configuracoesLog.getId().intValue()))
            .andExpect(jsonPath("$.idConfiguracoesLog").value(DEFAULT_ID_CONFIGURACOES_LOG.intValue()))
            .andExpect(jsonPath("$.acao").value(DEFAULT_ACAO.toString()))
            .andExpect(jsonPath("$.idUsuario").value(DEFAULT_ID_USUARIO.intValue()))
            .andExpect(jsonPath("$.log").value(DEFAULT_LOG.toString()))
            .andExpect(jsonPath("$.dtLog").value(DEFAULT_DT_LOG.toString()));
    }

    @Test
    @Transactional
    public void getAllConfiguracoesLogsByIdConfiguracoesLogIsEqualToSomething() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get all the configuracoesLogList where idConfiguracoesLog equals to DEFAULT_ID_CONFIGURACOES_LOG
        defaultConfiguracoesLogShouldBeFound("idConfiguracoesLog.equals=" + DEFAULT_ID_CONFIGURACOES_LOG);

        // Get all the configuracoesLogList where idConfiguracoesLog equals to UPDATED_ID_CONFIGURACOES_LOG
        defaultConfiguracoesLogShouldNotBeFound("idConfiguracoesLog.equals=" + UPDATED_ID_CONFIGURACOES_LOG);
    }

    @Test
    @Transactional
    public void getAllConfiguracoesLogsByIdConfiguracoesLogIsInShouldWork() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get all the configuracoesLogList where idConfiguracoesLog in DEFAULT_ID_CONFIGURACOES_LOG or UPDATED_ID_CONFIGURACOES_LOG
        defaultConfiguracoesLogShouldBeFound("idConfiguracoesLog.in=" + DEFAULT_ID_CONFIGURACOES_LOG + "," + UPDATED_ID_CONFIGURACOES_LOG);

        // Get all the configuracoesLogList where idConfiguracoesLog equals to UPDATED_ID_CONFIGURACOES_LOG
        defaultConfiguracoesLogShouldNotBeFound("idConfiguracoesLog.in=" + UPDATED_ID_CONFIGURACOES_LOG);
    }

    @Test
    @Transactional
    public void getAllConfiguracoesLogsByIdConfiguracoesLogIsNullOrNotNull() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get all the configuracoesLogList where idConfiguracoesLog is not null
        defaultConfiguracoesLogShouldBeFound("idConfiguracoesLog.specified=true");

        // Get all the configuracoesLogList where idConfiguracoesLog is null
        defaultConfiguracoesLogShouldNotBeFound("idConfiguracoesLog.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfiguracoesLogsByIdConfiguracoesLogIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get all the configuracoesLogList where idConfiguracoesLog greater than or equals to DEFAULT_ID_CONFIGURACOES_LOG
        defaultConfiguracoesLogShouldBeFound("idConfiguracoesLog.greaterOrEqualThan=" + DEFAULT_ID_CONFIGURACOES_LOG);

        // Get all the configuracoesLogList where idConfiguracoesLog greater than or equals to UPDATED_ID_CONFIGURACOES_LOG
        defaultConfiguracoesLogShouldNotBeFound("idConfiguracoesLog.greaterOrEqualThan=" + UPDATED_ID_CONFIGURACOES_LOG);
    }

    @Test
    @Transactional
    public void getAllConfiguracoesLogsByIdConfiguracoesLogIsLessThanSomething() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get all the configuracoesLogList where idConfiguracoesLog less than or equals to DEFAULT_ID_CONFIGURACOES_LOG
        defaultConfiguracoesLogShouldNotBeFound("idConfiguracoesLog.lessThan=" + DEFAULT_ID_CONFIGURACOES_LOG);

        // Get all the configuracoesLogList where idConfiguracoesLog less than or equals to UPDATED_ID_CONFIGURACOES_LOG
        defaultConfiguracoesLogShouldBeFound("idConfiguracoesLog.lessThan=" + UPDATED_ID_CONFIGURACOES_LOG);
    }


    @Test
    @Transactional
    public void getAllConfiguracoesLogsByAcaoIsEqualToSomething() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get all the configuracoesLogList where acao equals to DEFAULT_ACAO
        defaultConfiguracoesLogShouldBeFound("acao.equals=" + DEFAULT_ACAO);

        // Get all the configuracoesLogList where acao equals to UPDATED_ACAO
        defaultConfiguracoesLogShouldNotBeFound("acao.equals=" + UPDATED_ACAO);
    }

    @Test
    @Transactional
    public void getAllConfiguracoesLogsByAcaoIsInShouldWork() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get all the configuracoesLogList where acao in DEFAULT_ACAO or UPDATED_ACAO
        defaultConfiguracoesLogShouldBeFound("acao.in=" + DEFAULT_ACAO + "," + UPDATED_ACAO);

        // Get all the configuracoesLogList where acao equals to UPDATED_ACAO
        defaultConfiguracoesLogShouldNotBeFound("acao.in=" + UPDATED_ACAO);
    }

    @Test
    @Transactional
    public void getAllConfiguracoesLogsByAcaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get all the configuracoesLogList where acao is not null
        defaultConfiguracoesLogShouldBeFound("acao.specified=true");

        // Get all the configuracoesLogList where acao is null
        defaultConfiguracoesLogShouldNotBeFound("acao.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfiguracoesLogsByIdUsuarioIsEqualToSomething() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get all the configuracoesLogList where idUsuario equals to DEFAULT_ID_USUARIO
        defaultConfiguracoesLogShouldBeFound("idUsuario.equals=" + DEFAULT_ID_USUARIO);

        // Get all the configuracoesLogList where idUsuario equals to UPDATED_ID_USUARIO
        defaultConfiguracoesLogShouldNotBeFound("idUsuario.equals=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllConfiguracoesLogsByIdUsuarioIsInShouldWork() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get all the configuracoesLogList where idUsuario in DEFAULT_ID_USUARIO or UPDATED_ID_USUARIO
        defaultConfiguracoesLogShouldBeFound("idUsuario.in=" + DEFAULT_ID_USUARIO + "," + UPDATED_ID_USUARIO);

        // Get all the configuracoesLogList where idUsuario equals to UPDATED_ID_USUARIO
        defaultConfiguracoesLogShouldNotBeFound("idUsuario.in=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllConfiguracoesLogsByIdUsuarioIsNullOrNotNull() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get all the configuracoesLogList where idUsuario is not null
        defaultConfiguracoesLogShouldBeFound("idUsuario.specified=true");

        // Get all the configuracoesLogList where idUsuario is null
        defaultConfiguracoesLogShouldNotBeFound("idUsuario.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfiguracoesLogsByIdUsuarioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get all the configuracoesLogList where idUsuario greater than or equals to DEFAULT_ID_USUARIO
        defaultConfiguracoesLogShouldBeFound("idUsuario.greaterOrEqualThan=" + DEFAULT_ID_USUARIO);

        // Get all the configuracoesLogList where idUsuario greater than or equals to UPDATED_ID_USUARIO
        defaultConfiguracoesLogShouldNotBeFound("idUsuario.greaterOrEqualThan=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllConfiguracoesLogsByIdUsuarioIsLessThanSomething() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get all the configuracoesLogList where idUsuario less than or equals to DEFAULT_ID_USUARIO
        defaultConfiguracoesLogShouldNotBeFound("idUsuario.lessThan=" + DEFAULT_ID_USUARIO);

        // Get all the configuracoesLogList where idUsuario less than or equals to UPDATED_ID_USUARIO
        defaultConfiguracoesLogShouldBeFound("idUsuario.lessThan=" + UPDATED_ID_USUARIO);
    }


    @Test
    @Transactional
    public void getAllConfiguracoesLogsByLogIsEqualToSomething() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get all the configuracoesLogList where log equals to DEFAULT_LOG
        defaultConfiguracoesLogShouldBeFound("log.equals=" + DEFAULT_LOG);

        // Get all the configuracoesLogList where log equals to UPDATED_LOG
        defaultConfiguracoesLogShouldNotBeFound("log.equals=" + UPDATED_LOG);
    }

    @Test
    @Transactional
    public void getAllConfiguracoesLogsByLogIsInShouldWork() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get all the configuracoesLogList where log in DEFAULT_LOG or UPDATED_LOG
        defaultConfiguracoesLogShouldBeFound("log.in=" + DEFAULT_LOG + "," + UPDATED_LOG);

        // Get all the configuracoesLogList where log equals to UPDATED_LOG
        defaultConfiguracoesLogShouldNotBeFound("log.in=" + UPDATED_LOG);
    }

    @Test
    @Transactional
    public void getAllConfiguracoesLogsByLogIsNullOrNotNull() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get all the configuracoesLogList where log is not null
        defaultConfiguracoesLogShouldBeFound("log.specified=true");

        // Get all the configuracoesLogList where log is null
        defaultConfiguracoesLogShouldNotBeFound("log.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfiguracoesLogsByDtLogIsEqualToSomething() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get all the configuracoesLogList where dtLog equals to DEFAULT_DT_LOG
        defaultConfiguracoesLogShouldBeFound("dtLog.equals=" + DEFAULT_DT_LOG);

        // Get all the configuracoesLogList where dtLog equals to UPDATED_DT_LOG
        defaultConfiguracoesLogShouldNotBeFound("dtLog.equals=" + UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void getAllConfiguracoesLogsByDtLogIsInShouldWork() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get all the configuracoesLogList where dtLog in DEFAULT_DT_LOG or UPDATED_DT_LOG
        defaultConfiguracoesLogShouldBeFound("dtLog.in=" + DEFAULT_DT_LOG + "," + UPDATED_DT_LOG);

        // Get all the configuracoesLogList where dtLog equals to UPDATED_DT_LOG
        defaultConfiguracoesLogShouldNotBeFound("dtLog.in=" + UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void getAllConfiguracoesLogsByDtLogIsNullOrNotNull() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get all the configuracoesLogList where dtLog is not null
        defaultConfiguracoesLogShouldBeFound("dtLog.specified=true");

        // Get all the configuracoesLogList where dtLog is null
        defaultConfiguracoesLogShouldNotBeFound("dtLog.specified=false");
    }

    @Test
    @Transactional
    public void getAllConfiguracoesLogsByDtLogIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get all the configuracoesLogList where dtLog greater than or equals to DEFAULT_DT_LOG
        defaultConfiguracoesLogShouldBeFound("dtLog.greaterOrEqualThan=" + DEFAULT_DT_LOG);

        // Get all the configuracoesLogList where dtLog greater than or equals to UPDATED_DT_LOG
        defaultConfiguracoesLogShouldNotBeFound("dtLog.greaterOrEqualThan=" + UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void getAllConfiguracoesLogsByDtLogIsLessThanSomething() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);

        // Get all the configuracoesLogList where dtLog less than or equals to DEFAULT_DT_LOG
        defaultConfiguracoesLogShouldNotBeFound("dtLog.lessThan=" + DEFAULT_DT_LOG);

        // Get all the configuracoesLogList where dtLog less than or equals to UPDATED_DT_LOG
        defaultConfiguracoesLogShouldBeFound("dtLog.lessThan=" + UPDATED_DT_LOG);
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultConfiguracoesLogShouldBeFound(String filter) throws Exception {
        restConfiguracoesLogMockMvc.perform(get("/api/configuracoes-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(configuracoesLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].idConfiguracoesLog").value(hasItem(DEFAULT_ID_CONFIGURACOES_LOG.intValue())))
            .andExpect(jsonPath("$.[*].acao").value(hasItem(DEFAULT_ACAO.toString())))
            .andExpect(jsonPath("$.[*].idUsuario").value(hasItem(DEFAULT_ID_USUARIO.intValue())))
            .andExpect(jsonPath("$.[*].log").value(hasItem(DEFAULT_LOG.toString())))
            .andExpect(jsonPath("$.[*].dtLog").value(hasItem(DEFAULT_DT_LOG.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultConfiguracoesLogShouldNotBeFound(String filter) throws Exception {
        restConfiguracoesLogMockMvc.perform(get("/api/configuracoes-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingConfiguracoesLog() throws Exception {
        // Get the configuracoesLog
        restConfiguracoesLogMockMvc.perform(get("/api/configuracoes-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConfiguracoesLog() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);
        int databaseSizeBeforeUpdate = configuracoesLogRepository.findAll().size();

        // Update the configuracoesLog
        ConfiguracoesLog updatedConfiguracoesLog = configuracoesLogRepository.findOne(configuracoesLog.getId());
        // Disconnect from session so that the updates on updatedConfiguracoesLog are not directly saved in db
        em.detach(updatedConfiguracoesLog);
        updatedConfiguracoesLog
            .acao(UPDATED_ACAO)
            .idUsuario(UPDATED_ID_USUARIO)
            .log(UPDATED_LOG)
            .dtLog(UPDATED_DT_LOG);
        ConfiguracoesLogDTO configuracoesLogDTO = configuracoesLogMapper.toDto(updatedConfiguracoesLog);

        restConfiguracoesLogMockMvc.perform(put("/api/configuracoes-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configuracoesLogDTO)))
            .andExpect(status().isOk());

        // Validate the ConfiguracoesLog in the database
        List<ConfiguracoesLog> configuracoesLogList = configuracoesLogRepository.findAll();
        assertThat(configuracoesLogList).hasSize(databaseSizeBeforeUpdate);
        ConfiguracoesLog testConfiguracoesLog = configuracoesLogList.get(configuracoesLogList.size() - 1);
        assertThat(testConfiguracoesLog.getAcao()).isEqualTo(UPDATED_ACAO);
        assertThat(testConfiguracoesLog.getIdUsuario()).isEqualTo(UPDATED_ID_USUARIO);
        assertThat(testConfiguracoesLog.getLog()).isEqualTo(UPDATED_LOG);
        assertThat(testConfiguracoesLog.getDtLog()).isEqualTo(UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void updateNonExistingConfiguracoesLog() throws Exception {
        int databaseSizeBeforeUpdate = configuracoesLogRepository.findAll().size();

        // Create the ConfiguracoesLog
        ConfiguracoesLogDTO configuracoesLogDTO = configuracoesLogMapper.toDto(configuracoesLog);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restConfiguracoesLogMockMvc.perform(put("/api/configuracoes-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configuracoesLogDTO)))
            .andExpect(status().isCreated());

        // Validate the ConfiguracoesLog in the database
        List<ConfiguracoesLog> configuracoesLogList = configuracoesLogRepository.findAll();
        assertThat(configuracoesLogList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteConfiguracoesLog() throws Exception {
        // Initialize the database
        configuracoesLogRepository.saveAndFlush(configuracoesLog);
        int databaseSizeBeforeDelete = configuracoesLogRepository.findAll().size();

        // Get the configuracoesLog
        restConfiguracoesLogMockMvc.perform(delete("/api/configuracoes-logs/{id}", configuracoesLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ConfiguracoesLog> configuracoesLogList = configuracoesLogRepository.findAll();
        assertThat(configuracoesLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConfiguracoesLog.class);
        ConfiguracoesLog configuracoesLog1 = new ConfiguracoesLog();
        configuracoesLog1.setId(1L);
        ConfiguracoesLog configuracoesLog2 = new ConfiguracoesLog();
        configuracoesLog2.setId(configuracoesLog1.getId());
        assertThat(configuracoesLog1).isEqualTo(configuracoesLog2);
        configuracoesLog2.setId(2L);
        assertThat(configuracoesLog1).isNotEqualTo(configuracoesLog2);
        configuracoesLog1.setId(null);
        assertThat(configuracoesLog1).isNotEqualTo(configuracoesLog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConfiguracoesLogDTO.class);
        ConfiguracoesLogDTO configuracoesLogDTO1 = new ConfiguracoesLogDTO();
        configuracoesLogDTO1.setId(1L);
        ConfiguracoesLogDTO configuracoesLogDTO2 = new ConfiguracoesLogDTO();
        assertThat(configuracoesLogDTO1).isNotEqualTo(configuracoesLogDTO2);
        configuracoesLogDTO2.setId(configuracoesLogDTO1.getId());
        assertThat(configuracoesLogDTO1).isEqualTo(configuracoesLogDTO2);
        configuracoesLogDTO2.setId(2L);
        assertThat(configuracoesLogDTO1).isNotEqualTo(configuracoesLogDTO2);
        configuracoesLogDTO1.setId(null);
        assertThat(configuracoesLogDTO1).isNotEqualTo(configuracoesLogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(configuracoesLogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(configuracoesLogMapper.fromId(null)).isNull();
    }
}
