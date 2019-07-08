package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.RelatoriosLog;
import com.minea.sisas.repository.RelatoriosLogRepository;
import com.minea.sisas.service.RelatoriosLogService;
import com.minea.sisas.service.dto.RelatoriosLogDTO;
import com.minea.sisas.service.mapper.RelatoriosLogMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.RelatoriosLogQueryService;

import com.minea.sisas.web.rest.RelatoriosLogResource;
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
 * Test class for the RelatoriosLogResource REST controller.
 *
 * @see RelatoriosLogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class RelatoriosLogResourceIntTest {

    private static final Long DEFAULT_ID_RELATORIOS_LOG = 1L;
    private static final Long UPDATED_ID_RELATORIOS_LOG = 2L;

    private static final String DEFAULT_ACAO = "AAAAAAAAAA";
    private static final String UPDATED_ACAO = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_USUARIO = 1L;
    private static final Long UPDATED_ID_USUARIO = 2L;

    private static final String DEFAULT_LOG = "AAAAAAAAAA";
    private static final String UPDATED_LOG = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DT_LOG = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_LOG = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private RelatoriosLogRepository relatoriosLogRepository;

    @Autowired
    private RelatoriosLogMapper relatoriosLogMapper;

    @Autowired
    private RelatoriosLogService relatoriosLogService;

    @Autowired
    private RelatoriosLogQueryService relatoriosLogQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRelatoriosLogMockMvc;

    private RelatoriosLog relatoriosLog;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RelatoriosLogResource relatoriosLogResource = new RelatoriosLogResource(relatoriosLogService, relatoriosLogQueryService);
        this.restRelatoriosLogMockMvc = MockMvcBuilders.standaloneSetup(relatoriosLogResource)
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
    public static RelatoriosLog createEntity(EntityManager em) {
        RelatoriosLog relatoriosLog = new RelatoriosLog()
            .acao(DEFAULT_ACAO)
            .idUsuario(DEFAULT_ID_USUARIO)
            .log(DEFAULT_LOG)
            .dtLog(DEFAULT_DT_LOG);
        return relatoriosLog;
    }

    @Before
    public void initTest() {
        relatoriosLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createRelatoriosLog() throws Exception {
        int databaseSizeBeforeCreate = relatoriosLogRepository.findAll().size();

        // Create the RelatoriosLog
        RelatoriosLogDTO relatoriosLogDTO = relatoriosLogMapper.toDto(relatoriosLog);
        restRelatoriosLogMockMvc.perform(post("/api/relatorios-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relatoriosLogDTO)))
            .andExpect(status().isCreated());

        // Validate the RelatoriosLog in the database
        List<RelatoriosLog> relatoriosLogList = relatoriosLogRepository.findAll();
        assertThat(relatoriosLogList).hasSize(databaseSizeBeforeCreate + 1);
        RelatoriosLog testRelatoriosLog = relatoriosLogList.get(relatoriosLogList.size() - 1);
        assertThat(testRelatoriosLog.getAcao()).isEqualTo(DEFAULT_ACAO);
        assertThat(testRelatoriosLog.getIdUsuario()).isEqualTo(DEFAULT_ID_USUARIO);
        assertThat(testRelatoriosLog.getLog()).isEqualTo(DEFAULT_LOG);
        assertThat(testRelatoriosLog.getDtLog()).isEqualTo(DEFAULT_DT_LOG);
    }

    @Test
    @Transactional
    public void createRelatoriosLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = relatoriosLogRepository.findAll().size();

        // Create the RelatoriosLog with an existing ID
        relatoriosLog.setId(1L);
        RelatoriosLogDTO relatoriosLogDTO = relatoriosLogMapper.toDto(relatoriosLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRelatoriosLogMockMvc.perform(post("/api/relatorios-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relatoriosLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RelatoriosLog in the database
        List<RelatoriosLog> relatoriosLogList = relatoriosLogRepository.findAll();
        assertThat(relatoriosLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdRelatoriosLogIsRequired() throws Exception {
        int databaseSizeBeforeTest = relatoriosLogRepository.findAll().size();
        // set the field null

        // Create the RelatoriosLog, which fails.
        RelatoriosLogDTO relatoriosLogDTO = relatoriosLogMapper.toDto(relatoriosLog);

        restRelatoriosLogMockMvc.perform(post("/api/relatorios-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relatoriosLogDTO)))
            .andExpect(status().isBadRequest());

        List<RelatoriosLog> relatoriosLogList = relatoriosLogRepository.findAll();
        assertThat(relatoriosLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAcaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = relatoriosLogRepository.findAll().size();
        // set the field null
        relatoriosLog.setAcao(null);

        // Create the RelatoriosLog, which fails.
        RelatoriosLogDTO relatoriosLogDTO = relatoriosLogMapper.toDto(relatoriosLog);

        restRelatoriosLogMockMvc.perform(post("/api/relatorios-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relatoriosLogDTO)))
            .andExpect(status().isBadRequest());

        List<RelatoriosLog> relatoriosLogList = relatoriosLogRepository.findAll();
        assertThat(relatoriosLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdUsuarioIsRequired() throws Exception {
        int databaseSizeBeforeTest = relatoriosLogRepository.findAll().size();
        // set the field null
        relatoriosLog.setIdUsuario(null);

        // Create the RelatoriosLog, which fails.
        RelatoriosLogDTO relatoriosLogDTO = relatoriosLogMapper.toDto(relatoriosLog);

        restRelatoriosLogMockMvc.perform(post("/api/relatorios-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relatoriosLogDTO)))
            .andExpect(status().isBadRequest());

        List<RelatoriosLog> relatoriosLogList = relatoriosLogRepository.findAll();
        assertThat(relatoriosLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLogIsRequired() throws Exception {
        int databaseSizeBeforeTest = relatoriosLogRepository.findAll().size();
        // set the field null
        relatoriosLog.setLog(null);

        // Create the RelatoriosLog, which fails.
        RelatoriosLogDTO relatoriosLogDTO = relatoriosLogMapper.toDto(relatoriosLog);

        restRelatoriosLogMockMvc.perform(post("/api/relatorios-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relatoriosLogDTO)))
            .andExpect(status().isBadRequest());

        List<RelatoriosLog> relatoriosLogList = relatoriosLogRepository.findAll();
        assertThat(relatoriosLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDtLogIsRequired() throws Exception {
        int databaseSizeBeforeTest = relatoriosLogRepository.findAll().size();
        // set the field null
        relatoriosLog.setDtLog(null);

        // Create the RelatoriosLog, which fails.
        RelatoriosLogDTO relatoriosLogDTO = relatoriosLogMapper.toDto(relatoriosLog);

        restRelatoriosLogMockMvc.perform(post("/api/relatorios-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relatoriosLogDTO)))
            .andExpect(status().isBadRequest());

        List<RelatoriosLog> relatoriosLogList = relatoriosLogRepository.findAll();
        assertThat(relatoriosLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRelatoriosLogs() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get all the relatoriosLogList
        restRelatoriosLogMockMvc.perform(get("/api/relatorios-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relatoriosLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].idRelatoriosLog").value(hasItem(DEFAULT_ID_RELATORIOS_LOG.intValue())))
            .andExpect(jsonPath("$.[*].acao").value(hasItem(DEFAULT_ACAO.toString())))
            .andExpect(jsonPath("$.[*].idUsuario").value(hasItem(DEFAULT_ID_USUARIO.intValue())))
            .andExpect(jsonPath("$.[*].log").value(hasItem(DEFAULT_LOG.toString())))
            .andExpect(jsonPath("$.[*].dtLog").value(hasItem(DEFAULT_DT_LOG.toString())));
    }

    @Test
    @Transactional
    public void getRelatoriosLog() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get the relatoriosLog
        restRelatoriosLogMockMvc.perform(get("/api/relatorios-logs/{id}", relatoriosLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(relatoriosLog.getId().intValue()))
            .andExpect(jsonPath("$.idRelatoriosLog").value(DEFAULT_ID_RELATORIOS_LOG.intValue()))
            .andExpect(jsonPath("$.acao").value(DEFAULT_ACAO.toString()))
            .andExpect(jsonPath("$.idUsuario").value(DEFAULT_ID_USUARIO.intValue()))
            .andExpect(jsonPath("$.log").value(DEFAULT_LOG.toString()))
            .andExpect(jsonPath("$.dtLog").value(DEFAULT_DT_LOG.toString()));
    }

    @Test
    @Transactional
    public void getAllRelatoriosLogsByIdRelatoriosLogIsEqualToSomething() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get all the relatoriosLogList where idRelatoriosLog equals to DEFAULT_ID_RELATORIOS_LOG
        defaultRelatoriosLogShouldBeFound("idRelatoriosLog.equals=" + DEFAULT_ID_RELATORIOS_LOG);

        // Get all the relatoriosLogList where idRelatoriosLog equals to UPDATED_ID_RELATORIOS_LOG
        defaultRelatoriosLogShouldNotBeFound("idRelatoriosLog.equals=" + UPDATED_ID_RELATORIOS_LOG);
    }

    @Test
    @Transactional
    public void getAllRelatoriosLogsByIdRelatoriosLogIsInShouldWork() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get all the relatoriosLogList where idRelatoriosLog in DEFAULT_ID_RELATORIOS_LOG or UPDATED_ID_RELATORIOS_LOG
        defaultRelatoriosLogShouldBeFound("idRelatoriosLog.in=" + DEFAULT_ID_RELATORIOS_LOG + "," + UPDATED_ID_RELATORIOS_LOG);

        // Get all the relatoriosLogList where idRelatoriosLog equals to UPDATED_ID_RELATORIOS_LOG
        defaultRelatoriosLogShouldNotBeFound("idRelatoriosLog.in=" + UPDATED_ID_RELATORIOS_LOG);
    }

    @Test
    @Transactional
    public void getAllRelatoriosLogsByIdRelatoriosLogIsNullOrNotNull() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get all the relatoriosLogList where idRelatoriosLog is not null
        defaultRelatoriosLogShouldBeFound("idRelatoriosLog.specified=true");

        // Get all the relatoriosLogList where idRelatoriosLog is null
        defaultRelatoriosLogShouldNotBeFound("idRelatoriosLog.specified=false");
    }

    @Test
    @Transactional
    public void getAllRelatoriosLogsByIdRelatoriosLogIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get all the relatoriosLogList where idRelatoriosLog greater than or equals to DEFAULT_ID_RELATORIOS_LOG
        defaultRelatoriosLogShouldBeFound("idRelatoriosLog.greaterOrEqualThan=" + DEFAULT_ID_RELATORIOS_LOG);

        // Get all the relatoriosLogList where idRelatoriosLog greater than or equals to UPDATED_ID_RELATORIOS_LOG
        defaultRelatoriosLogShouldNotBeFound("idRelatoriosLog.greaterOrEqualThan=" + UPDATED_ID_RELATORIOS_LOG);
    }

    @Test
    @Transactional
    public void getAllRelatoriosLogsByIdRelatoriosLogIsLessThanSomething() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get all the relatoriosLogList where idRelatoriosLog less than or equals to DEFAULT_ID_RELATORIOS_LOG
        defaultRelatoriosLogShouldNotBeFound("idRelatoriosLog.lessThan=" + DEFAULT_ID_RELATORIOS_LOG);

        // Get all the relatoriosLogList where idRelatoriosLog less than or equals to UPDATED_ID_RELATORIOS_LOG
        defaultRelatoriosLogShouldBeFound("idRelatoriosLog.lessThan=" + UPDATED_ID_RELATORIOS_LOG);
    }


    @Test
    @Transactional
    public void getAllRelatoriosLogsByAcaoIsEqualToSomething() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get all the relatoriosLogList where acao equals to DEFAULT_ACAO
        defaultRelatoriosLogShouldBeFound("acao.equals=" + DEFAULT_ACAO);

        // Get all the relatoriosLogList where acao equals to UPDATED_ACAO
        defaultRelatoriosLogShouldNotBeFound("acao.equals=" + UPDATED_ACAO);
    }

    @Test
    @Transactional
    public void getAllRelatoriosLogsByAcaoIsInShouldWork() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get all the relatoriosLogList where acao in DEFAULT_ACAO or UPDATED_ACAO
        defaultRelatoriosLogShouldBeFound("acao.in=" + DEFAULT_ACAO + "," + UPDATED_ACAO);

        // Get all the relatoriosLogList where acao equals to UPDATED_ACAO
        defaultRelatoriosLogShouldNotBeFound("acao.in=" + UPDATED_ACAO);
    }

    @Test
    @Transactional
    public void getAllRelatoriosLogsByAcaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get all the relatoriosLogList where acao is not null
        defaultRelatoriosLogShouldBeFound("acao.specified=true");

        // Get all the relatoriosLogList where acao is null
        defaultRelatoriosLogShouldNotBeFound("acao.specified=false");
    }

    @Test
    @Transactional
    public void getAllRelatoriosLogsByIdUsuarioIsEqualToSomething() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get all the relatoriosLogList where idUsuario equals to DEFAULT_ID_USUARIO
        defaultRelatoriosLogShouldBeFound("idUsuario.equals=" + DEFAULT_ID_USUARIO);

        // Get all the relatoriosLogList where idUsuario equals to UPDATED_ID_USUARIO
        defaultRelatoriosLogShouldNotBeFound("idUsuario.equals=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllRelatoriosLogsByIdUsuarioIsInShouldWork() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get all the relatoriosLogList where idUsuario in DEFAULT_ID_USUARIO or UPDATED_ID_USUARIO
        defaultRelatoriosLogShouldBeFound("idUsuario.in=" + DEFAULT_ID_USUARIO + "," + UPDATED_ID_USUARIO);

        // Get all the relatoriosLogList where idUsuario equals to UPDATED_ID_USUARIO
        defaultRelatoriosLogShouldNotBeFound("idUsuario.in=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllRelatoriosLogsByIdUsuarioIsNullOrNotNull() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get all the relatoriosLogList where idUsuario is not null
        defaultRelatoriosLogShouldBeFound("idUsuario.specified=true");

        // Get all the relatoriosLogList where idUsuario is null
        defaultRelatoriosLogShouldNotBeFound("idUsuario.specified=false");
    }

    @Test
    @Transactional
    public void getAllRelatoriosLogsByIdUsuarioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get all the relatoriosLogList where idUsuario greater than or equals to DEFAULT_ID_USUARIO
        defaultRelatoriosLogShouldBeFound("idUsuario.greaterOrEqualThan=" + DEFAULT_ID_USUARIO);

        // Get all the relatoriosLogList where idUsuario greater than or equals to UPDATED_ID_USUARIO
        defaultRelatoriosLogShouldNotBeFound("idUsuario.greaterOrEqualThan=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllRelatoriosLogsByIdUsuarioIsLessThanSomething() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get all the relatoriosLogList where idUsuario less than or equals to DEFAULT_ID_USUARIO
        defaultRelatoriosLogShouldNotBeFound("idUsuario.lessThan=" + DEFAULT_ID_USUARIO);

        // Get all the relatoriosLogList where idUsuario less than or equals to UPDATED_ID_USUARIO
        defaultRelatoriosLogShouldBeFound("idUsuario.lessThan=" + UPDATED_ID_USUARIO);
    }


    @Test
    @Transactional
    public void getAllRelatoriosLogsByLogIsEqualToSomething() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get all the relatoriosLogList where log equals to DEFAULT_LOG
        defaultRelatoriosLogShouldBeFound("log.equals=" + DEFAULT_LOG);

        // Get all the relatoriosLogList where log equals to UPDATED_LOG
        defaultRelatoriosLogShouldNotBeFound("log.equals=" + UPDATED_LOG);
    }

    @Test
    @Transactional
    public void getAllRelatoriosLogsByLogIsInShouldWork() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get all the relatoriosLogList where log in DEFAULT_LOG or UPDATED_LOG
        defaultRelatoriosLogShouldBeFound("log.in=" + DEFAULT_LOG + "," + UPDATED_LOG);

        // Get all the relatoriosLogList where log equals to UPDATED_LOG
        defaultRelatoriosLogShouldNotBeFound("log.in=" + UPDATED_LOG);
    }

    @Test
    @Transactional
    public void getAllRelatoriosLogsByLogIsNullOrNotNull() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get all the relatoriosLogList where log is not null
        defaultRelatoriosLogShouldBeFound("log.specified=true");

        // Get all the relatoriosLogList where log is null
        defaultRelatoriosLogShouldNotBeFound("log.specified=false");
    }

    @Test
    @Transactional
    public void getAllRelatoriosLogsByDtLogIsEqualToSomething() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get all the relatoriosLogList where dtLog equals to DEFAULT_DT_LOG
        defaultRelatoriosLogShouldBeFound("dtLog.equals=" + DEFAULT_DT_LOG);

        // Get all the relatoriosLogList where dtLog equals to UPDATED_DT_LOG
        defaultRelatoriosLogShouldNotBeFound("dtLog.equals=" + UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void getAllRelatoriosLogsByDtLogIsInShouldWork() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get all the relatoriosLogList where dtLog in DEFAULT_DT_LOG or UPDATED_DT_LOG
        defaultRelatoriosLogShouldBeFound("dtLog.in=" + DEFAULT_DT_LOG + "," + UPDATED_DT_LOG);

        // Get all the relatoriosLogList where dtLog equals to UPDATED_DT_LOG
        defaultRelatoriosLogShouldNotBeFound("dtLog.in=" + UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void getAllRelatoriosLogsByDtLogIsNullOrNotNull() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get all the relatoriosLogList where dtLog is not null
        defaultRelatoriosLogShouldBeFound("dtLog.specified=true");

        // Get all the relatoriosLogList where dtLog is null
        defaultRelatoriosLogShouldNotBeFound("dtLog.specified=false");
    }

    @Test
    @Transactional
    public void getAllRelatoriosLogsByDtLogIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get all the relatoriosLogList where dtLog greater than or equals to DEFAULT_DT_LOG
        defaultRelatoriosLogShouldBeFound("dtLog.greaterOrEqualThan=" + DEFAULT_DT_LOG);

        // Get all the relatoriosLogList where dtLog greater than or equals to UPDATED_DT_LOG
        defaultRelatoriosLogShouldNotBeFound("dtLog.greaterOrEqualThan=" + UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void getAllRelatoriosLogsByDtLogIsLessThanSomething() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);

        // Get all the relatoriosLogList where dtLog less than or equals to DEFAULT_DT_LOG
        defaultRelatoriosLogShouldNotBeFound("dtLog.lessThan=" + DEFAULT_DT_LOG);

        // Get all the relatoriosLogList where dtLog less than or equals to UPDATED_DT_LOG
        defaultRelatoriosLogShouldBeFound("dtLog.lessThan=" + UPDATED_DT_LOG);
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultRelatoriosLogShouldBeFound(String filter) throws Exception {
        restRelatoriosLogMockMvc.perform(get("/api/relatorios-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relatoriosLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].idRelatoriosLog").value(hasItem(DEFAULT_ID_RELATORIOS_LOG.intValue())))
            .andExpect(jsonPath("$.[*].acao").value(hasItem(DEFAULT_ACAO.toString())))
            .andExpect(jsonPath("$.[*].idUsuario").value(hasItem(DEFAULT_ID_USUARIO.intValue())))
            .andExpect(jsonPath("$.[*].log").value(hasItem(DEFAULT_LOG.toString())))
            .andExpect(jsonPath("$.[*].dtLog").value(hasItem(DEFAULT_DT_LOG.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultRelatoriosLogShouldNotBeFound(String filter) throws Exception {
        restRelatoriosLogMockMvc.perform(get("/api/relatorios-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingRelatoriosLog() throws Exception {
        // Get the relatoriosLog
        restRelatoriosLogMockMvc.perform(get("/api/relatorios-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRelatoriosLog() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);
        int databaseSizeBeforeUpdate = relatoriosLogRepository.findAll().size();

        // Update the relatoriosLog
        RelatoriosLog updatedRelatoriosLog = relatoriosLogRepository.findOne(relatoriosLog.getId());
        // Disconnect from session so that the updates on updatedRelatoriosLog are not directly saved in db
        em.detach(updatedRelatoriosLog);
        updatedRelatoriosLog
            .acao(UPDATED_ACAO)
            .idUsuario(UPDATED_ID_USUARIO)
            .log(UPDATED_LOG)
            .dtLog(UPDATED_DT_LOG);
        RelatoriosLogDTO relatoriosLogDTO = relatoriosLogMapper.toDto(updatedRelatoriosLog);

        restRelatoriosLogMockMvc.perform(put("/api/relatorios-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relatoriosLogDTO)))
            .andExpect(status().isOk());

        // Validate the RelatoriosLog in the database
        List<RelatoriosLog> relatoriosLogList = relatoriosLogRepository.findAll();
        assertThat(relatoriosLogList).hasSize(databaseSizeBeforeUpdate);
        RelatoriosLog testRelatoriosLog = relatoriosLogList.get(relatoriosLogList.size() - 1);
        assertThat(testRelatoriosLog.getAcao()).isEqualTo(UPDATED_ACAO);
        assertThat(testRelatoriosLog.getIdUsuario()).isEqualTo(UPDATED_ID_USUARIO);
        assertThat(testRelatoriosLog.getLog()).isEqualTo(UPDATED_LOG);
        assertThat(testRelatoriosLog.getDtLog()).isEqualTo(UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void updateNonExistingRelatoriosLog() throws Exception {
        int databaseSizeBeforeUpdate = relatoriosLogRepository.findAll().size();

        // Create the RelatoriosLog
        RelatoriosLogDTO relatoriosLogDTO = relatoriosLogMapper.toDto(relatoriosLog);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRelatoriosLogMockMvc.perform(put("/api/relatorios-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relatoriosLogDTO)))
            .andExpect(status().isCreated());

        // Validate the RelatoriosLog in the database
        List<RelatoriosLog> relatoriosLogList = relatoriosLogRepository.findAll();
        assertThat(relatoriosLogList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRelatoriosLog() throws Exception {
        // Initialize the database
        relatoriosLogRepository.saveAndFlush(relatoriosLog);
        int databaseSizeBeforeDelete = relatoriosLogRepository.findAll().size();

        // Get the relatoriosLog
        restRelatoriosLogMockMvc.perform(delete("/api/relatorios-logs/{id}", relatoriosLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RelatoriosLog> relatoriosLogList = relatoriosLogRepository.findAll();
        assertThat(relatoriosLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelatoriosLog.class);
        RelatoriosLog relatoriosLog1 = new RelatoriosLog();
        relatoriosLog1.setId(1L);
        RelatoriosLog relatoriosLog2 = new RelatoriosLog();
        relatoriosLog2.setId(relatoriosLog1.getId());
        assertThat(relatoriosLog1).isEqualTo(relatoriosLog2);
        relatoriosLog2.setId(2L);
        assertThat(relatoriosLog1).isNotEqualTo(relatoriosLog2);
        relatoriosLog1.setId(null);
        assertThat(relatoriosLog1).isNotEqualTo(relatoriosLog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelatoriosLogDTO.class);
        RelatoriosLogDTO relatoriosLogDTO1 = new RelatoriosLogDTO();
        relatoriosLogDTO1.setId(1L);
        RelatoriosLogDTO relatoriosLogDTO2 = new RelatoriosLogDTO();
        assertThat(relatoriosLogDTO1).isNotEqualTo(relatoriosLogDTO2);
        relatoriosLogDTO2.setId(relatoriosLogDTO1.getId());
        assertThat(relatoriosLogDTO1).isEqualTo(relatoriosLogDTO2);
        relatoriosLogDTO2.setId(2L);
        assertThat(relatoriosLogDTO1).isNotEqualTo(relatoriosLogDTO2);
        relatoriosLogDTO1.setId(null);
        assertThat(relatoriosLogDTO1).isNotEqualTo(relatoriosLogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(relatoriosLogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(relatoriosLogMapper.fromId(null)).isNull();
    }
}
