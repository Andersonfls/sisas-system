package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.ProgramasProjectosLog;
import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.repository.ProgramasProjectosLogRepository;
import com.minea.sisas.service.ProgramasProjectosLogService;
import com.minea.sisas.service.dto.ProgramasProjectosLogDTO;
import com.minea.sisas.service.mapper.ProgramasProjectosLogMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.ProgramasProjectosLogQueryService;

import com.minea.sisas.web.rest.ProgramasProjectosLogResource;
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
 * Test class for the ProgramasProjectosLogResource REST controller.
 *
 * @see ProgramasProjectosLogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class ProgramasProjectosLogResourceIntTest {

    private static final Long DEFAULT_ID_PROGRAMAS_PROJECTOS_LOG = 1L;
    private static final Long UPDATED_ID_PROGRAMAS_PROJECTOS_LOG = 2L;

    private static final String DEFAULT_ACAO = "AAAAAAAAAA";
    private static final String UPDATED_ACAO = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_USUARIO = 1L;
    private static final Long UPDATED_ID_USUARIO = 2L;

    private static final String DEFAULT_LOG = "AAAAAAAAAA";
    private static final String UPDATED_LOG = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DT_LOG = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_LOG = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private ProgramasProjectosLogRepository programasProjectosLogRepository;

    @Autowired
    private ProgramasProjectosLogMapper programasProjectosLogMapper;

    @Autowired
    private ProgramasProjectosLogService programasProjectosLogService;

    @Autowired
    private ProgramasProjectosLogQueryService programasProjectosLogQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProgramasProjectosLogMockMvc;

    private ProgramasProjectosLog programasProjectosLog;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProgramasProjectosLogResource programasProjectosLogResource = new ProgramasProjectosLogResource(programasProjectosLogService, programasProjectosLogQueryService);
        this.restProgramasProjectosLogMockMvc = MockMvcBuilders.standaloneSetup(programasProjectosLogResource)
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
    public static ProgramasProjectosLog createEntity(EntityManager em) {
        ProgramasProjectosLog programasProjectosLog = new ProgramasProjectosLog()
            .acao(DEFAULT_ACAO)
            .idUsuario(DEFAULT_ID_USUARIO)
            .log(DEFAULT_LOG)
            .dtLog(DEFAULT_DT_LOG);
        // Add required entity
        ProgramasProjectos idProgramasProjectos = ProgramasProjectosResourceIntTest.createEntity(em);
        em.persist(idProgramasProjectos);
        em.flush();
        programasProjectosLog.setIdProgramasProjectos(idProgramasProjectos);
        return programasProjectosLog;
    }

    @Before
    public void initTest() {
        programasProjectosLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createProgramasProjectosLog() throws Exception {
        int databaseSizeBeforeCreate = programasProjectosLogRepository.findAll().size();

        // Create the ProgramasProjectosLog
        ProgramasProjectosLogDTO programasProjectosLogDTO = programasProjectosLogMapper.toDto(programasProjectosLog);
        restProgramasProjectosLogMockMvc.perform(post("/api/programas-projectos-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programasProjectosLogDTO)))
            .andExpect(status().isCreated());

        // Validate the ProgramasProjectosLog in the database
        List<ProgramasProjectosLog> programasProjectosLogList = programasProjectosLogRepository.findAll();
        assertThat(programasProjectosLogList).hasSize(databaseSizeBeforeCreate + 1);
        ProgramasProjectosLog testProgramasProjectosLog = programasProjectosLogList.get(programasProjectosLogList.size() - 1);
        assertThat(testProgramasProjectosLog.getAcao()).isEqualTo(DEFAULT_ACAO);
        assertThat(testProgramasProjectosLog.getIdUsuario()).isEqualTo(DEFAULT_ID_USUARIO);
        assertThat(testProgramasProjectosLog.getLog()).isEqualTo(DEFAULT_LOG);
        assertThat(testProgramasProjectosLog.getDtLog()).isEqualTo(DEFAULT_DT_LOG);
    }

    @Test
    @Transactional
    public void createProgramasProjectosLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = programasProjectosLogRepository.findAll().size();

        // Create the ProgramasProjectosLog with an existing ID
        programasProjectosLog.setId(1L);
        ProgramasProjectosLogDTO programasProjectosLogDTO = programasProjectosLogMapper.toDto(programasProjectosLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProgramasProjectosLogMockMvc.perform(post("/api/programas-projectos-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programasProjectosLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProgramasProjectosLog in the database
        List<ProgramasProjectosLog> programasProjectosLogList = programasProjectosLogRepository.findAll();
        assertThat(programasProjectosLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdProgramasProjectosLogIsRequired() throws Exception {
        int databaseSizeBeforeTest = programasProjectosLogRepository.findAll().size();
        // set the field null

        // Create the ProgramasProjectosLog, which fails.
        ProgramasProjectosLogDTO programasProjectosLogDTO = programasProjectosLogMapper.toDto(programasProjectosLog);

        restProgramasProjectosLogMockMvc.perform(post("/api/programas-projectos-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programasProjectosLogDTO)))
            .andExpect(status().isBadRequest());

        List<ProgramasProjectosLog> programasProjectosLogList = programasProjectosLogRepository.findAll();
        assertThat(programasProjectosLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAcaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = programasProjectosLogRepository.findAll().size();
        // set the field null
        programasProjectosLog.setAcao(null);

        // Create the ProgramasProjectosLog, which fails.
        ProgramasProjectosLogDTO programasProjectosLogDTO = programasProjectosLogMapper.toDto(programasProjectosLog);

        restProgramasProjectosLogMockMvc.perform(post("/api/programas-projectos-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programasProjectosLogDTO)))
            .andExpect(status().isBadRequest());

        List<ProgramasProjectosLog> programasProjectosLogList = programasProjectosLogRepository.findAll();
        assertThat(programasProjectosLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdUsuarioIsRequired() throws Exception {
        int databaseSizeBeforeTest = programasProjectosLogRepository.findAll().size();
        // set the field null
        programasProjectosLog.setIdUsuario(null);

        // Create the ProgramasProjectosLog, which fails.
        ProgramasProjectosLogDTO programasProjectosLogDTO = programasProjectosLogMapper.toDto(programasProjectosLog);

        restProgramasProjectosLogMockMvc.perform(post("/api/programas-projectos-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programasProjectosLogDTO)))
            .andExpect(status().isBadRequest());

        List<ProgramasProjectosLog> programasProjectosLogList = programasProjectosLogRepository.findAll();
        assertThat(programasProjectosLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLogIsRequired() throws Exception {
        int databaseSizeBeforeTest = programasProjectosLogRepository.findAll().size();
        // set the field null
        programasProjectosLog.setLog(null);

        // Create the ProgramasProjectosLog, which fails.
        ProgramasProjectosLogDTO programasProjectosLogDTO = programasProjectosLogMapper.toDto(programasProjectosLog);

        restProgramasProjectosLogMockMvc.perform(post("/api/programas-projectos-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programasProjectosLogDTO)))
            .andExpect(status().isBadRequest());

        List<ProgramasProjectosLog> programasProjectosLogList = programasProjectosLogRepository.findAll();
        assertThat(programasProjectosLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDtLogIsRequired() throws Exception {
        int databaseSizeBeforeTest = programasProjectosLogRepository.findAll().size();
        // set the field null
        programasProjectosLog.setDtLog(null);

        // Create the ProgramasProjectosLog, which fails.
        ProgramasProjectosLogDTO programasProjectosLogDTO = programasProjectosLogMapper.toDto(programasProjectosLog);

        restProgramasProjectosLogMockMvc.perform(post("/api/programas-projectos-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programasProjectosLogDTO)))
            .andExpect(status().isBadRequest());

        List<ProgramasProjectosLog> programasProjectosLogList = programasProjectosLogRepository.findAll();
        assertThat(programasProjectosLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosLogs() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get all the programasProjectosLogList
        restProgramasProjectosLogMockMvc.perform(get("/api/programas-projectos-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(programasProjectosLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].idProgramasProjectosLog").value(hasItem(DEFAULT_ID_PROGRAMAS_PROJECTOS_LOG.intValue())))
            .andExpect(jsonPath("$.[*].acao").value(hasItem(DEFAULT_ACAO.toString())))
            .andExpect(jsonPath("$.[*].idUsuario").value(hasItem(DEFAULT_ID_USUARIO.intValue())))
            .andExpect(jsonPath("$.[*].log").value(hasItem(DEFAULT_LOG.toString())))
            .andExpect(jsonPath("$.[*].dtLog").value(hasItem(DEFAULT_DT_LOG.toString())));
    }

    @Test
    @Transactional
    public void getProgramasProjectosLog() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get the programasProjectosLog
        restProgramasProjectosLogMockMvc.perform(get("/api/programas-projectos-logs/{id}", programasProjectosLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(programasProjectosLog.getId().intValue()))
            .andExpect(jsonPath("$.idProgramasProjectosLog").value(DEFAULT_ID_PROGRAMAS_PROJECTOS_LOG.intValue()))
            .andExpect(jsonPath("$.acao").value(DEFAULT_ACAO.toString()))
            .andExpect(jsonPath("$.idUsuario").value(DEFAULT_ID_USUARIO.intValue()))
            .andExpect(jsonPath("$.log").value(DEFAULT_LOG.toString()))
            .andExpect(jsonPath("$.dtLog").value(DEFAULT_DT_LOG.toString()));
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosLogsByIdProgramasProjectosLogIsEqualToSomething() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get all the programasProjectosLogList where idProgramasProjectosLog equals to DEFAULT_ID_PROGRAMAS_PROJECTOS_LOG
        defaultProgramasProjectosLogShouldBeFound("idProgramasProjectosLog.equals=" + DEFAULT_ID_PROGRAMAS_PROJECTOS_LOG);

        // Get all the programasProjectosLogList where idProgramasProjectosLog equals to UPDATED_ID_PROGRAMAS_PROJECTOS_LOG
        defaultProgramasProjectosLogShouldNotBeFound("idProgramasProjectosLog.equals=" + UPDATED_ID_PROGRAMAS_PROJECTOS_LOG);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosLogsByIdProgramasProjectosLogIsInShouldWork() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get all the programasProjectosLogList where idProgramasProjectosLog in DEFAULT_ID_PROGRAMAS_PROJECTOS_LOG or UPDATED_ID_PROGRAMAS_PROJECTOS_LOG
        defaultProgramasProjectosLogShouldBeFound("idProgramasProjectosLog.in=" + DEFAULT_ID_PROGRAMAS_PROJECTOS_LOG + "," + UPDATED_ID_PROGRAMAS_PROJECTOS_LOG);

        // Get all the programasProjectosLogList where idProgramasProjectosLog equals to UPDATED_ID_PROGRAMAS_PROJECTOS_LOG
        defaultProgramasProjectosLogShouldNotBeFound("idProgramasProjectosLog.in=" + UPDATED_ID_PROGRAMAS_PROJECTOS_LOG);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosLogsByIdProgramasProjectosLogIsNullOrNotNull() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get all the programasProjectosLogList where idProgramasProjectosLog is not null
        defaultProgramasProjectosLogShouldBeFound("idProgramasProjectosLog.specified=true");

        // Get all the programasProjectosLogList where idProgramasProjectosLog is null
        defaultProgramasProjectosLogShouldNotBeFound("idProgramasProjectosLog.specified=false");
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosLogsByIdProgramasProjectosLogIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get all the programasProjectosLogList where idProgramasProjectosLog greater than or equals to DEFAULT_ID_PROGRAMAS_PROJECTOS_LOG
        defaultProgramasProjectosLogShouldBeFound("idProgramasProjectosLog.greaterOrEqualThan=" + DEFAULT_ID_PROGRAMAS_PROJECTOS_LOG);

        // Get all the programasProjectosLogList where idProgramasProjectosLog greater than or equals to UPDATED_ID_PROGRAMAS_PROJECTOS_LOG
        defaultProgramasProjectosLogShouldNotBeFound("idProgramasProjectosLog.greaterOrEqualThan=" + UPDATED_ID_PROGRAMAS_PROJECTOS_LOG);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosLogsByIdProgramasProjectosLogIsLessThanSomething() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get all the programasProjectosLogList where idProgramasProjectosLog less than or equals to DEFAULT_ID_PROGRAMAS_PROJECTOS_LOG
        defaultProgramasProjectosLogShouldNotBeFound("idProgramasProjectosLog.lessThan=" + DEFAULT_ID_PROGRAMAS_PROJECTOS_LOG);

        // Get all the programasProjectosLogList where idProgramasProjectosLog less than or equals to UPDATED_ID_PROGRAMAS_PROJECTOS_LOG
        defaultProgramasProjectosLogShouldBeFound("idProgramasProjectosLog.lessThan=" + UPDATED_ID_PROGRAMAS_PROJECTOS_LOG);
    }


    @Test
    @Transactional
    public void getAllProgramasProjectosLogsByAcaoIsEqualToSomething() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get all the programasProjectosLogList where acao equals to DEFAULT_ACAO
        defaultProgramasProjectosLogShouldBeFound("acao.equals=" + DEFAULT_ACAO);

        // Get all the programasProjectosLogList where acao equals to UPDATED_ACAO
        defaultProgramasProjectosLogShouldNotBeFound("acao.equals=" + UPDATED_ACAO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosLogsByAcaoIsInShouldWork() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get all the programasProjectosLogList where acao in DEFAULT_ACAO or UPDATED_ACAO
        defaultProgramasProjectosLogShouldBeFound("acao.in=" + DEFAULT_ACAO + "," + UPDATED_ACAO);

        // Get all the programasProjectosLogList where acao equals to UPDATED_ACAO
        defaultProgramasProjectosLogShouldNotBeFound("acao.in=" + UPDATED_ACAO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosLogsByAcaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get all the programasProjectosLogList where acao is not null
        defaultProgramasProjectosLogShouldBeFound("acao.specified=true");

        // Get all the programasProjectosLogList where acao is null
        defaultProgramasProjectosLogShouldNotBeFound("acao.specified=false");
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosLogsByIdUsuarioIsEqualToSomething() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get all the programasProjectosLogList where idUsuario equals to DEFAULT_ID_USUARIO
        defaultProgramasProjectosLogShouldBeFound("idUsuario.equals=" + DEFAULT_ID_USUARIO);

        // Get all the programasProjectosLogList where idUsuario equals to UPDATED_ID_USUARIO
        defaultProgramasProjectosLogShouldNotBeFound("idUsuario.equals=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosLogsByIdUsuarioIsInShouldWork() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get all the programasProjectosLogList where idUsuario in DEFAULT_ID_USUARIO or UPDATED_ID_USUARIO
        defaultProgramasProjectosLogShouldBeFound("idUsuario.in=" + DEFAULT_ID_USUARIO + "," + UPDATED_ID_USUARIO);

        // Get all the programasProjectosLogList where idUsuario equals to UPDATED_ID_USUARIO
        defaultProgramasProjectosLogShouldNotBeFound("idUsuario.in=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosLogsByIdUsuarioIsNullOrNotNull() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get all the programasProjectosLogList where idUsuario is not null
        defaultProgramasProjectosLogShouldBeFound("idUsuario.specified=true");

        // Get all the programasProjectosLogList where idUsuario is null
        defaultProgramasProjectosLogShouldNotBeFound("idUsuario.specified=false");
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosLogsByIdUsuarioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get all the programasProjectosLogList where idUsuario greater than or equals to DEFAULT_ID_USUARIO
        defaultProgramasProjectosLogShouldBeFound("idUsuario.greaterOrEqualThan=" + DEFAULT_ID_USUARIO);

        // Get all the programasProjectosLogList where idUsuario greater than or equals to UPDATED_ID_USUARIO
        defaultProgramasProjectosLogShouldNotBeFound("idUsuario.greaterOrEqualThan=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosLogsByIdUsuarioIsLessThanSomething() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get all the programasProjectosLogList where idUsuario less than or equals to DEFAULT_ID_USUARIO
        defaultProgramasProjectosLogShouldNotBeFound("idUsuario.lessThan=" + DEFAULT_ID_USUARIO);

        // Get all the programasProjectosLogList where idUsuario less than or equals to UPDATED_ID_USUARIO
        defaultProgramasProjectosLogShouldBeFound("idUsuario.lessThan=" + UPDATED_ID_USUARIO);
    }


    @Test
    @Transactional
    public void getAllProgramasProjectosLogsByLogIsEqualToSomething() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get all the programasProjectosLogList where log equals to DEFAULT_LOG
        defaultProgramasProjectosLogShouldBeFound("log.equals=" + DEFAULT_LOG);

        // Get all the programasProjectosLogList where log equals to UPDATED_LOG
        defaultProgramasProjectosLogShouldNotBeFound("log.equals=" + UPDATED_LOG);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosLogsByLogIsInShouldWork() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get all the programasProjectosLogList where log in DEFAULT_LOG or UPDATED_LOG
        defaultProgramasProjectosLogShouldBeFound("log.in=" + DEFAULT_LOG + "," + UPDATED_LOG);

        // Get all the programasProjectosLogList where log equals to UPDATED_LOG
        defaultProgramasProjectosLogShouldNotBeFound("log.in=" + UPDATED_LOG);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosLogsByLogIsNullOrNotNull() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get all the programasProjectosLogList where log is not null
        defaultProgramasProjectosLogShouldBeFound("log.specified=true");

        // Get all the programasProjectosLogList where log is null
        defaultProgramasProjectosLogShouldNotBeFound("log.specified=false");
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosLogsByDtLogIsEqualToSomething() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get all the programasProjectosLogList where dtLog equals to DEFAULT_DT_LOG
        defaultProgramasProjectosLogShouldBeFound("dtLog.equals=" + DEFAULT_DT_LOG);

        // Get all the programasProjectosLogList where dtLog equals to UPDATED_DT_LOG
        defaultProgramasProjectosLogShouldNotBeFound("dtLog.equals=" + UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosLogsByDtLogIsInShouldWork() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get all the programasProjectosLogList where dtLog in DEFAULT_DT_LOG or UPDATED_DT_LOG
        defaultProgramasProjectosLogShouldBeFound("dtLog.in=" + DEFAULT_DT_LOG + "," + UPDATED_DT_LOG);

        // Get all the programasProjectosLogList where dtLog equals to UPDATED_DT_LOG
        defaultProgramasProjectosLogShouldNotBeFound("dtLog.in=" + UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosLogsByDtLogIsNullOrNotNull() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get all the programasProjectosLogList where dtLog is not null
        defaultProgramasProjectosLogShouldBeFound("dtLog.specified=true");

        // Get all the programasProjectosLogList where dtLog is null
        defaultProgramasProjectosLogShouldNotBeFound("dtLog.specified=false");
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosLogsByDtLogIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get all the programasProjectosLogList where dtLog greater than or equals to DEFAULT_DT_LOG
        defaultProgramasProjectosLogShouldBeFound("dtLog.greaterOrEqualThan=" + DEFAULT_DT_LOG);

        // Get all the programasProjectosLogList where dtLog greater than or equals to UPDATED_DT_LOG
        defaultProgramasProjectosLogShouldNotBeFound("dtLog.greaterOrEqualThan=" + UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosLogsByDtLogIsLessThanSomething() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);

        // Get all the programasProjectosLogList where dtLog less than or equals to DEFAULT_DT_LOG
        defaultProgramasProjectosLogShouldNotBeFound("dtLog.lessThan=" + DEFAULT_DT_LOG);

        // Get all the programasProjectosLogList where dtLog less than or equals to UPDATED_DT_LOG
        defaultProgramasProjectosLogShouldBeFound("dtLog.lessThan=" + UPDATED_DT_LOG);
    }


    @Test
    @Transactional
    public void getAllProgramasProjectosLogsByIdProgramasProjectosIsEqualToSomething() throws Exception {
        // Initialize the database
        ProgramasProjectos idProgramasProjectos = ProgramasProjectosResourceIntTest.createEntity(em);
        em.persist(idProgramasProjectos);
        em.flush();
        programasProjectosLog.setIdProgramasProjectos(idProgramasProjectos);
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);
        Long idProgramasProjectosId = idProgramasProjectos.getId();

        // Get all the programasProjectosLogList where idProgramasProjectos equals to idProgramasProjectosId
        defaultProgramasProjectosLogShouldBeFound("idProgramasProjectosId.equals=" + idProgramasProjectosId);

        // Get all the programasProjectosLogList where idProgramasProjectos equals to idProgramasProjectosId + 1
        defaultProgramasProjectosLogShouldNotBeFound("idProgramasProjectosId.equals=" + (idProgramasProjectosId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultProgramasProjectosLogShouldBeFound(String filter) throws Exception {
        restProgramasProjectosLogMockMvc.perform(get("/api/programas-projectos-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(programasProjectosLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].idProgramasProjectosLog").value(hasItem(DEFAULT_ID_PROGRAMAS_PROJECTOS_LOG.intValue())))
            .andExpect(jsonPath("$.[*].acao").value(hasItem(DEFAULT_ACAO.toString())))
            .andExpect(jsonPath("$.[*].idUsuario").value(hasItem(DEFAULT_ID_USUARIO.intValue())))
            .andExpect(jsonPath("$.[*].log").value(hasItem(DEFAULT_LOG.toString())))
            .andExpect(jsonPath("$.[*].dtLog").value(hasItem(DEFAULT_DT_LOG.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultProgramasProjectosLogShouldNotBeFound(String filter) throws Exception {
        restProgramasProjectosLogMockMvc.perform(get("/api/programas-projectos-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingProgramasProjectosLog() throws Exception {
        // Get the programasProjectosLog
        restProgramasProjectosLogMockMvc.perform(get("/api/programas-projectos-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProgramasProjectosLog() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);
        int databaseSizeBeforeUpdate = programasProjectosLogRepository.findAll().size();

        // Update the programasProjectosLog
        ProgramasProjectosLog updatedProgramasProjectosLog = programasProjectosLogRepository.findOne(programasProjectosLog.getId());
        // Disconnect from session so that the updates on updatedProgramasProjectosLog are not directly saved in db
        em.detach(updatedProgramasProjectosLog);
        updatedProgramasProjectosLog
            .acao(UPDATED_ACAO)
            .idUsuario(UPDATED_ID_USUARIO)
            .log(UPDATED_LOG)
            .dtLog(UPDATED_DT_LOG);
        ProgramasProjectosLogDTO programasProjectosLogDTO = programasProjectosLogMapper.toDto(updatedProgramasProjectosLog);

        restProgramasProjectosLogMockMvc.perform(put("/api/programas-projectos-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programasProjectosLogDTO)))
            .andExpect(status().isOk());

        // Validate the ProgramasProjectosLog in the database
        List<ProgramasProjectosLog> programasProjectosLogList = programasProjectosLogRepository.findAll();
        assertThat(programasProjectosLogList).hasSize(databaseSizeBeforeUpdate);
        ProgramasProjectosLog testProgramasProjectosLog = programasProjectosLogList.get(programasProjectosLogList.size() - 1);
        assertThat(testProgramasProjectosLog.getAcao()).isEqualTo(UPDATED_ACAO);
        assertThat(testProgramasProjectosLog.getIdUsuario()).isEqualTo(UPDATED_ID_USUARIO);
        assertThat(testProgramasProjectosLog.getLog()).isEqualTo(UPDATED_LOG);
        assertThat(testProgramasProjectosLog.getDtLog()).isEqualTo(UPDATED_DT_LOG);
    }

    @Test
    @Transactional
    public void updateNonExistingProgramasProjectosLog() throws Exception {
        int databaseSizeBeforeUpdate = programasProjectosLogRepository.findAll().size();

        // Create the ProgramasProjectosLog
        ProgramasProjectosLogDTO programasProjectosLogDTO = programasProjectosLogMapper.toDto(programasProjectosLog);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProgramasProjectosLogMockMvc.perform(put("/api/programas-projectos-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programasProjectosLogDTO)))
            .andExpect(status().isCreated());

        // Validate the ProgramasProjectosLog in the database
        List<ProgramasProjectosLog> programasProjectosLogList = programasProjectosLogRepository.findAll();
        assertThat(programasProjectosLogList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteProgramasProjectosLog() throws Exception {
        // Initialize the database
        programasProjectosLogRepository.saveAndFlush(programasProjectosLog);
        int databaseSizeBeforeDelete = programasProjectosLogRepository.findAll().size();

        // Get the programasProjectosLog
        restProgramasProjectosLogMockMvc.perform(delete("/api/programas-projectos-logs/{id}", programasProjectosLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProgramasProjectosLog> programasProjectosLogList = programasProjectosLogRepository.findAll();
        assertThat(programasProjectosLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProgramasProjectosLog.class);
        ProgramasProjectosLog programasProjectosLog1 = new ProgramasProjectosLog();
        programasProjectosLog1.setId(1L);
        ProgramasProjectosLog programasProjectosLog2 = new ProgramasProjectosLog();
        programasProjectosLog2.setId(programasProjectosLog1.getId());
        assertThat(programasProjectosLog1).isEqualTo(programasProjectosLog2);
        programasProjectosLog2.setId(2L);
        assertThat(programasProjectosLog1).isNotEqualTo(programasProjectosLog2);
        programasProjectosLog1.setId(null);
        assertThat(programasProjectosLog1).isNotEqualTo(programasProjectosLog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProgramasProjectosLogDTO.class);
        ProgramasProjectosLogDTO programasProjectosLogDTO1 = new ProgramasProjectosLogDTO();
        programasProjectosLogDTO1.setId(1L);
        ProgramasProjectosLogDTO programasProjectosLogDTO2 = new ProgramasProjectosLogDTO();
        assertThat(programasProjectosLogDTO1).isNotEqualTo(programasProjectosLogDTO2);
        programasProjectosLogDTO2.setId(programasProjectosLogDTO1.getId());
        assertThat(programasProjectosLogDTO1).isEqualTo(programasProjectosLogDTO2);
        programasProjectosLogDTO2.setId(2L);
        assertThat(programasProjectosLogDTO1).isNotEqualTo(programasProjectosLogDTO2);
        programasProjectosLogDTO1.setId(null);
        assertThat(programasProjectosLogDTO1).isNotEqualTo(programasProjectosLogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(programasProjectosLogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(programasProjectosLogMapper.fromId(null)).isNull();
    }
}
