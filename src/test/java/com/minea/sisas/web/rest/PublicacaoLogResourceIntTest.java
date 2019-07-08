package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.PublicacaoLog;
import com.minea.sisas.repository.PublicacaoLogRepository;
import com.minea.sisas.service.PublicacaoLogService;
import com.minea.sisas.service.dto.PublicacaoLogDTO;
import com.minea.sisas.service.mapper.PublicacaoLogMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.PublicacaoLogQueryService;

import com.minea.sisas.web.rest.PublicacaoLogResource;
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
 * Test class for the PublicacaoLogResource REST controller.
 *
 * @see PublicacaoLogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class PublicacaoLogResourceIntTest {

    private static final Long DEFAULT_ID_PUBLICACAO_LOG = 1L;
    private static final Long UPDATED_ID_PUBLICACAO_LOG = 2L;

    private static final String DEFAULT_ACAO = "AAAAAAAAAA";
    private static final String UPDATED_ACAO = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_USUARIO = 1L;
    private static final Long UPDATED_ID_USUARIO = 2L;

    private static final String DEFAULT_LOG = "AAAAAAAAAA";
    private static final String UPDATED_LOG = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DT_LOG = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_LOG = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private PublicacaoLogRepository publicacaoLogRepository;

    @Autowired
    private PublicacaoLogMapper publicacaoLogMapper;

    @Autowired
    private PublicacaoLogService publicacaoLogService;

    @Autowired
    private PublicacaoLogQueryService publicacaoLogQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPublicacaoLogMockMvc;

    private PublicacaoLog publicacaoLog;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PublicacaoLogResource publicacaoLogResource = new PublicacaoLogResource(publicacaoLogService, publicacaoLogQueryService);
        this.restPublicacaoLogMockMvc = MockMvcBuilders.standaloneSetup(publicacaoLogResource)
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
    public static PublicacaoLog createEntity(EntityManager em) {
        PublicacaoLog publicacaoLog = new PublicacaoLog()
            .acao(DEFAULT_ACAO)
            .idUsuario(DEFAULT_ID_USUARIO)
            .log(DEFAULT_LOG)
            .dtLog(DEFAULT_DT_LOG);
        return publicacaoLog;
    }

    @Before
    public void initTest() {
        publicacaoLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createPublicacaoLog() throws Exception {
        int databaseSizeBeforeCreate = publicacaoLogRepository.findAll().size();

        // Create the PublicacaoLog
        PublicacaoLogDTO publicacaoLogDTO = publicacaoLogMapper.toDto(publicacaoLog);
        restPublicacaoLogMockMvc.perform(post("/api/publicacao-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(publicacaoLogDTO)))
            .andExpect(status().isCreated());

        // Validate the PublicacaoLog in the database
        List<PublicacaoLog> publicacaoLogList = publicacaoLogRepository.findAll();
        assertThat(publicacaoLogList).hasSize(databaseSizeBeforeCreate + 1);
        PublicacaoLog testPublicacaoLog = publicacaoLogList.get(publicacaoLogList.size() - 1);
        assertThat(testPublicacaoLog.getAcao()).isEqualTo(DEFAULT_ACAO);
        assertThat(testPublicacaoLog.getIdUsuario()).isEqualTo(DEFAULT_ID_USUARIO);
        assertThat(testPublicacaoLog.getLog()).isEqualTo(DEFAULT_LOG);
        assertThat(testPublicacaoLog.getDtLog()).isEqualTo(DEFAULT_DT_LOG);
    }

    @Test
    @Transactional
    public void createPublicacaoLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = publicacaoLogRepository.findAll().size();

        // Create the PublicacaoLog with an existing ID
        publicacaoLog.setId(1L);
        PublicacaoLogDTO publicacaoLogDTO = publicacaoLogMapper.toDto(publicacaoLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPublicacaoLogMockMvc.perform(post("/api/publicacao-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(publicacaoLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PublicacaoLog in the database
        List<PublicacaoLog> publicacaoLogList = publicacaoLogRepository.findAll();
        assertThat(publicacaoLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdPublicacaoLogIsRequired() throws Exception {
        int databaseSizeBeforeTest = publicacaoLogRepository.findAll().size();
        // set the field null

        // Create the PublicacaoLog, which fails.
        PublicacaoLogDTO publicacaoLogDTO = publicacaoLogMapper.toDto(publicacaoLog);

        restPublicacaoLogMockMvc.perform(post("/api/publicacao-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(publicacaoLogDTO)))
            .andExpect(status().isBadRequest());

        List<PublicacaoLog> publicacaoLogList = publicacaoLogRepository.findAll();
        assertThat(publicacaoLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAcaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = publicacaoLogRepository.findAll().size();
        // set the field null
        publicacaoLog.setAcao(null);

        // Create the PublicacaoLog, which fails.
        PublicacaoLogDTO publicacaoLogDTO = publicacaoLogMapper.toDto(publicacaoLog);

        restPublicacaoLogMockMvc.perform(post("/api/publicacao-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(publicacaoLogDTO)))
            .andExpect(status().isBadRequest());

        List<PublicacaoLog> publicacaoLogList = publicacaoLogRepository.findAll();
        assertThat(publicacaoLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdUsuarioIsRequired() throws Exception {
        int databaseSizeBeforeTest = publicacaoLogRepository.findAll().size();
        // set the field null
        publicacaoLog.setIdUsuario(null);

        // Create the PublicacaoLog, which fails.
        PublicacaoLogDTO publicacaoLogDTO = publicacaoLogMapper.toDto(publicacaoLog);

        restPublicacaoLogMockMvc.perform(post("/api/publicacao-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(publicacaoLogDTO)))
            .andExpect(status().isBadRequest());

        List<PublicacaoLog> publicacaoLogList = publicacaoLogRepository.findAll();
        assertThat(publicacaoLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLogIsRequired() throws Exception {
        int databaseSizeBeforeTest = publicacaoLogRepository.findAll().size();
        // set the field null
        publicacaoLog.setLog(null);

        // Create the PublicacaoLog, which fails.
        PublicacaoLogDTO publicacaoLogDTO = publicacaoLogMapper.toDto(publicacaoLog);

        restPublicacaoLogMockMvc.perform(post("/api/publicacao-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(publicacaoLogDTO)))
            .andExpect(status().isBadRequest());

        List<PublicacaoLog> publicacaoLogList = publicacaoLogRepository.findAll();
        assertThat(publicacaoLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDtLogIsRequired() throws Exception {
        int databaseSizeBeforeTest = publicacaoLogRepository.findAll().size();
        // set the field null
        publicacaoLog.setDtLog(null);

        // Create the PublicacaoLog, which fails.
        PublicacaoLogDTO publicacaoLogDTO = publicacaoLogMapper.toDto(publicacaoLog);

        restPublicacaoLogMockMvc.perform(post("/api/publicacao-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(publicacaoLogDTO)))
            .andExpect(status().isBadRequest());

        List<PublicacaoLog> publicacaoLogList = publicacaoLogRepository.findAll();
        assertThat(publicacaoLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPublicacaoLogs() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get all the publicacaoLogList
        restPublicacaoLogMockMvc.perform(get("/api/publicacao-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(publicacaoLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPublicacaoLog").value(hasItem(DEFAULT_ID_PUBLICACAO_LOG.intValue())))
            .andExpect(jsonPath("$.[*].acao").value(hasItem(DEFAULT_ACAO.toString())))
            .andExpect(jsonPath("$.[*].idUsuario").value(hasItem(DEFAULT_ID_USUARIO.intValue())))
            .andExpect(jsonPath("$.[*].log").value(hasItem(DEFAULT_LOG.toString())))
            .andExpect(jsonPath("$.[*].dtLog").value(hasItem(DEFAULT_DT_LOG.toString())));
    }

    @Test
    @Transactional
    public void getPublicacaoLog() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get the publicacaoLog
        restPublicacaoLogMockMvc.perform(get("/api/publicacao-logs/{id}", publicacaoLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(publicacaoLog.getId().intValue()))
            .andExpect(jsonPath("$.idPublicacaoLog").value(DEFAULT_ID_PUBLICACAO_LOG.intValue()))
            .andExpect(jsonPath("$.acao").value(DEFAULT_ACAO.toString()))
            .andExpect(jsonPath("$.idUsuario").value(DEFAULT_ID_USUARIO.intValue()))
            .andExpect(jsonPath("$.log").value(DEFAULT_LOG.toString()))
            .andExpect(jsonPath("$.dtLog").value(DEFAULT_DT_LOG.toString()));
    }

    @Test
    @Transactional
    public void getAllPublicacaoLogsByIdPublicacaoLogIsEqualToSomething() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get all the publicacaoLogList where idPublicacaoLog equals to DEFAULT_ID_PUBLICACAO_LOG
        defaultPublicacaoLogShouldBeFound("idPublicacaoLog.equals=" + DEFAULT_ID_PUBLICACAO_LOG);

        // Get all the publicacaoLogList where idPublicacaoLog equals to UPDATED_ID_PUBLICACAO_LOG
        defaultPublicacaoLogShouldNotBeFound("idPublicacaoLog.equals=" + UPDATED_ID_PUBLICACAO_LOG);
    }

    @Test
    @Transactional
    public void getAllPublicacaoLogsByIdPublicacaoLogIsInShouldWork() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get all the publicacaoLogList where idPublicacaoLog in DEFAULT_ID_PUBLICACAO_LOG or UPDATED_ID_PUBLICACAO_LOG
        defaultPublicacaoLogShouldBeFound("idPublicacaoLog.in=" + DEFAULT_ID_PUBLICACAO_LOG + "," + UPDATED_ID_PUBLICACAO_LOG);

        // Get all the publicacaoLogList where idPublicacaoLog equals to UPDATED_ID_PUBLICACAO_LOG
        defaultPublicacaoLogShouldNotBeFound("idPublicacaoLog.in=" + UPDATED_ID_PUBLICACAO_LOG);
    }

    @Test
    @Transactional
    public void getAllPublicacaoLogsByIdPublicacaoLogIsNullOrNotNull() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get all the publicacaoLogList where idPublicacaoLog is not null
        defaultPublicacaoLogShouldBeFound("idPublicacaoLog.specified=true");

        // Get all the publicacaoLogList where idPublicacaoLog is null
        defaultPublicacaoLogShouldNotBeFound("idPublicacaoLog.specified=false");
    }

    @Test
    @Transactional
    public void getAllPublicacaoLogsByIdPublicacaoLogIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get all the publicacaoLogList where idPublicacaoLog greater than or equals to DEFAULT_ID_PUBLICACAO_LOG
        defaultPublicacaoLogShouldBeFound("idPublicacaoLog.greaterOrEqualThan=" + DEFAULT_ID_PUBLICACAO_LOG);

        // Get all the publicacaoLogList where idPublicacaoLog greater than or equals to UPDATED_ID_PUBLICACAO_LOG
        defaultPublicacaoLogShouldNotBeFound("idPublicacaoLog.greaterOrEqualThan=" + UPDATED_ID_PUBLICACAO_LOG);
    }

    @Test
    @Transactional
    public void getAllPublicacaoLogsByIdPublicacaoLogIsLessThanSomething() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get all the publicacaoLogList where idPublicacaoLog less than or equals to DEFAULT_ID_PUBLICACAO_LOG
        defaultPublicacaoLogShouldNotBeFound("idPublicacaoLog.lessThan=" + DEFAULT_ID_PUBLICACAO_LOG);

        // Get all the publicacaoLogList where idPublicacaoLog less than or equals to UPDATED_ID_PUBLICACAO_LOG
        defaultPublicacaoLogShouldBeFound("idPublicacaoLog.lessThan=" + UPDATED_ID_PUBLICACAO_LOG);
    }


    @Test
    @Transactional
    public void getAllPublicacaoLogsByAcaoIsEqualToSomething() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get all the publicacaoLogList where acao equals to DEFAULT_ACAO
        defaultPublicacaoLogShouldBeFound("acao.equals=" + DEFAULT_ACAO);

        // Get all the publicacaoLogList where acao equals to UPDATED_ACAO
        defaultPublicacaoLogShouldNotBeFound("acao.equals=" + UPDATED_ACAO);
    }

    @Test
    @Transactional
    public void getAllPublicacaoLogsByAcaoIsInShouldWork() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get all the publicacaoLogList where acao in DEFAULT_ACAO or UPDATED_ACAO
        defaultPublicacaoLogShouldBeFound("acao.in=" + DEFAULT_ACAO + "," + UPDATED_ACAO);

        // Get all the publicacaoLogList where acao equals to UPDATED_ACAO
        defaultPublicacaoLogShouldNotBeFound("acao.in=" + UPDATED_ACAO);
    }

    @Test
    @Transactional
    public void getAllPublicacaoLogsByAcaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get all the publicacaoLogList where acao is not null
        defaultPublicacaoLogShouldBeFound("acao.specified=true");

        // Get all the publicacaoLogList where acao is null
        defaultPublicacaoLogShouldNotBeFound("acao.specified=false");
    }

    @Test
    @Transactional
    public void getAllPublicacaoLogsByIdUsuarioIsEqualToSomething() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get all the publicacaoLogList where idUsuario equals to DEFAULT_ID_USUARIO
        defaultPublicacaoLogShouldBeFound("idUsuario.equals=" + DEFAULT_ID_USUARIO);

        // Get all the publicacaoLogList where idUsuario equals to UPDATED_ID_USUARIO
        defaultPublicacaoLogShouldNotBeFound("idUsuario.equals=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllPublicacaoLogsByIdUsuarioIsInShouldWork() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get all the publicacaoLogList where idUsuario in DEFAULT_ID_USUARIO or UPDATED_ID_USUARIO
        defaultPublicacaoLogShouldBeFound("idUsuario.in=" + DEFAULT_ID_USUARIO + "," + UPDATED_ID_USUARIO);

        // Get all the publicacaoLogList where idUsuario equals to UPDATED_ID_USUARIO
        defaultPublicacaoLogShouldNotBeFound("idUsuario.in=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllPublicacaoLogsByIdUsuarioIsNullOrNotNull() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get all the publicacaoLogList where idUsuario is not null
        defaultPublicacaoLogShouldBeFound("idUsuario.specified=true");

        // Get all the publicacaoLogList where idUsuario is null
        defaultPublicacaoLogShouldNotBeFound("idUsuario.specified=false");
    }

    @Test
    @Transactional
    public void getAllPublicacaoLogsByIdUsuarioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get all the publicacaoLogList where idUsuario greater than or equals to DEFAULT_ID_USUARIO
        defaultPublicacaoLogShouldBeFound("idUsuario.greaterOrEqualThan=" + DEFAULT_ID_USUARIO);

        // Get all the publicacaoLogList where idUsuario greater than or equals to UPDATED_ID_USUARIO
        defaultPublicacaoLogShouldNotBeFound("idUsuario.greaterOrEqualThan=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllPublicacaoLogsByIdUsuarioIsLessThanSomething() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get all the publicacaoLogList where idUsuario less than or equals to DEFAULT_ID_USUARIO
        defaultPublicacaoLogShouldNotBeFound("idUsuario.lessThan=" + DEFAULT_ID_USUARIO);

        // Get all the publicacaoLogList where idUsuario less than or equals to UPDATED_ID_USUARIO
        defaultPublicacaoLogShouldBeFound("idUsuario.lessThan=" + UPDATED_ID_USUARIO);
    }


    @Test
    @Transactional
    public void getAllPublicacaoLogsByLogIsEqualToSomething() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get all the publicacaoLogList where log equals to DEFAULT_LOG
        defaultPublicacaoLogShouldBeFound("log.equals=" + DEFAULT_LOG);

        // Get all the publicacaoLogList where log equals to UPDATED_LOG
        defaultPublicacaoLogShouldNotBeFound("log.equals=" + UPDATED_LOG);
    }

    @Test
    @Transactional
    public void getAllPublicacaoLogsByLogIsInShouldWork() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get all the publicacaoLogList where log in DEFAULT_LOG or UPDATED_LOG
        defaultPublicacaoLogShouldBeFound("log.in=" + DEFAULT_LOG + "," + UPDATED_LOG);

        // Get all the publicacaoLogList where log equals to UPDATED_LOG
        defaultPublicacaoLogShouldNotBeFound("log.in=" + UPDATED_LOG);
    }

    @Test
    @Transactional
    public void getAllPublicacaoLogsByLogIsNullOrNotNull() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get all the publicacaoLogList where log is not null
        defaultPublicacaoLogShouldBeFound("log.specified=true");

        // Get all the publicacaoLogList where log is null
        defaultPublicacaoLogShouldNotBeFound("log.specified=false");
    }

    @Test
    @Transactional
    public void getAllPublicacaoLogsByDtLogIsEqualToSomething() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get all the publicacaoLogList where dtLog equals to DEFAULT_DT_LOG
        defaultPublicacaoLogShouldBeFound("dtLog.equals=" + DEFAULT_DT_LOG);

        // Get all the publicacaoLogList where dtLog equals to UPDATED_DT_LOG
        defaultPublicacaoLogShouldNotBeFound("dtLog.equals=" + UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void getAllPublicacaoLogsByDtLogIsInShouldWork() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get all the publicacaoLogList where dtLog in DEFAULT_DT_LOG or UPDATED_DT_LOG
        defaultPublicacaoLogShouldBeFound("dtLog.in=" + DEFAULT_DT_LOG + "," + UPDATED_DT_LOG);

        // Get all the publicacaoLogList where dtLog equals to UPDATED_DT_LOG
        defaultPublicacaoLogShouldNotBeFound("dtLog.in=" + UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void getAllPublicacaoLogsByDtLogIsNullOrNotNull() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get all the publicacaoLogList where dtLog is not null
        defaultPublicacaoLogShouldBeFound("dtLog.specified=true");

        // Get all the publicacaoLogList where dtLog is null
        defaultPublicacaoLogShouldNotBeFound("dtLog.specified=false");
    }

    @Test
    @Transactional
    public void getAllPublicacaoLogsByDtLogIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get all the publicacaoLogList where dtLog greater than or equals to DEFAULT_DT_LOG
        defaultPublicacaoLogShouldBeFound("dtLog.greaterOrEqualThan=" + DEFAULT_DT_LOG);

        // Get all the publicacaoLogList where dtLog greater than or equals to UPDATED_DT_LOG
        defaultPublicacaoLogShouldNotBeFound("dtLog.greaterOrEqualThan=" + UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void getAllPublicacaoLogsByDtLogIsLessThanSomething() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);

        // Get all the publicacaoLogList where dtLog less than or equals to DEFAULT_DT_LOG
        defaultPublicacaoLogShouldNotBeFound("dtLog.lessThan=" + DEFAULT_DT_LOG);

        // Get all the publicacaoLogList where dtLog less than or equals to UPDATED_DT_LOG
        defaultPublicacaoLogShouldBeFound("dtLog.lessThan=" + UPDATED_DT_LOG);
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPublicacaoLogShouldBeFound(String filter) throws Exception {
        restPublicacaoLogMockMvc.perform(get("/api/publicacao-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(publicacaoLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPublicacaoLog").value(hasItem(DEFAULT_ID_PUBLICACAO_LOG.intValue())))
            .andExpect(jsonPath("$.[*].acao").value(hasItem(DEFAULT_ACAO.toString())))
            .andExpect(jsonPath("$.[*].idUsuario").value(hasItem(DEFAULT_ID_USUARIO.intValue())))
            .andExpect(jsonPath("$.[*].log").value(hasItem(DEFAULT_LOG.toString())))
            .andExpect(jsonPath("$.[*].dtLog").value(hasItem(DEFAULT_DT_LOG.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPublicacaoLogShouldNotBeFound(String filter) throws Exception {
        restPublicacaoLogMockMvc.perform(get("/api/publicacao-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingPublicacaoLog() throws Exception {
        // Get the publicacaoLog
        restPublicacaoLogMockMvc.perform(get("/api/publicacao-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePublicacaoLog() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);
        int databaseSizeBeforeUpdate = publicacaoLogRepository.findAll().size();

        // Update the publicacaoLog
        PublicacaoLog updatedPublicacaoLog = publicacaoLogRepository.findOne(publicacaoLog.getId());
        // Disconnect from session so that the updates on updatedPublicacaoLog are not directly saved in db
        em.detach(updatedPublicacaoLog);
        updatedPublicacaoLog
            .acao(UPDATED_ACAO)
            .idUsuario(UPDATED_ID_USUARIO)
            .log(UPDATED_LOG)
            .dtLog(UPDATED_DT_LOG);
        PublicacaoLogDTO publicacaoLogDTO = publicacaoLogMapper.toDto(updatedPublicacaoLog);

        restPublicacaoLogMockMvc.perform(put("/api/publicacao-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(publicacaoLogDTO)))
            .andExpect(status().isOk());

        // Validate the PublicacaoLog in the database
        List<PublicacaoLog> publicacaoLogList = publicacaoLogRepository.findAll();
        assertThat(publicacaoLogList).hasSize(databaseSizeBeforeUpdate);
        PublicacaoLog testPublicacaoLog = publicacaoLogList.get(publicacaoLogList.size() - 1);
        assertThat(testPublicacaoLog.getAcao()).isEqualTo(UPDATED_ACAO);
        assertThat(testPublicacaoLog.getIdUsuario()).isEqualTo(UPDATED_ID_USUARIO);
        assertThat(testPublicacaoLog.getLog()).isEqualTo(UPDATED_LOG);
        assertThat(testPublicacaoLog.getDtLog()).isEqualTo(UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void updateNonExistingPublicacaoLog() throws Exception {
        int databaseSizeBeforeUpdate = publicacaoLogRepository.findAll().size();

        // Create the PublicacaoLog
        PublicacaoLogDTO publicacaoLogDTO = publicacaoLogMapper.toDto(publicacaoLog);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPublicacaoLogMockMvc.perform(put("/api/publicacao-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(publicacaoLogDTO)))
            .andExpect(status().isCreated());

        // Validate the PublicacaoLog in the database
        List<PublicacaoLog> publicacaoLogList = publicacaoLogRepository.findAll();
        assertThat(publicacaoLogList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePublicacaoLog() throws Exception {
        // Initialize the database
        publicacaoLogRepository.saveAndFlush(publicacaoLog);
        int databaseSizeBeforeDelete = publicacaoLogRepository.findAll().size();

        // Get the publicacaoLog
        restPublicacaoLogMockMvc.perform(delete("/api/publicacao-logs/{id}", publicacaoLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PublicacaoLog> publicacaoLogList = publicacaoLogRepository.findAll();
        assertThat(publicacaoLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PublicacaoLog.class);
        PublicacaoLog publicacaoLog1 = new PublicacaoLog();
        publicacaoLog1.setId(1L);
        PublicacaoLog publicacaoLog2 = new PublicacaoLog();
        publicacaoLog2.setId(publicacaoLog1.getId());
        assertThat(publicacaoLog1).isEqualTo(publicacaoLog2);
        publicacaoLog2.setId(2L);
        assertThat(publicacaoLog1).isNotEqualTo(publicacaoLog2);
        publicacaoLog1.setId(null);
        assertThat(publicacaoLog1).isNotEqualTo(publicacaoLog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PublicacaoLogDTO.class);
        PublicacaoLogDTO publicacaoLogDTO1 = new PublicacaoLogDTO();
        publicacaoLogDTO1.setId(1L);
        PublicacaoLogDTO publicacaoLogDTO2 = new PublicacaoLogDTO();
        assertThat(publicacaoLogDTO1).isNotEqualTo(publicacaoLogDTO2);
        publicacaoLogDTO2.setId(publicacaoLogDTO1.getId());
        assertThat(publicacaoLogDTO1).isEqualTo(publicacaoLogDTO2);
        publicacaoLogDTO2.setId(2L);
        assertThat(publicacaoLogDTO1).isNotEqualTo(publicacaoLogDTO2);
        publicacaoLogDTO1.setId(null);
        assertThat(publicacaoLogDTO1).isNotEqualTo(publicacaoLogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(publicacaoLogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(publicacaoLogMapper.fromId(null)).isNull();
    }
}
