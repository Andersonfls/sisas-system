package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.Adjudicacao;
import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.repository.AdjudicacaoRepository;
import com.minea.sisas.service.AdjudicacaoService;
import com.minea.sisas.service.dto.AdjudicacaoDTO;
import com.minea.sisas.service.mapper.AdjudicacaoMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.AdjudicacaoQueryService;

import com.minea.sisas.web.rest.AdjudicacaoResource;
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
 * Test class for the AdjudicacaoResource REST controller.
 *
 * @see AdjudicacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class AdjudicacaoResourceIntTest {

    private static final Long DEFAULT_ID_ADJUDICACAO = 1L;
    private static final Long UPDATED_ID_ADJUDICACAO = 2L;

    private static final String DEFAULT_TIPO_CONCURSO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_CONCURSO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DT_LANCAMENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_LANCAMENTO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_COMUNICAO_ADJUDICACAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_COMUNICAO_ADJUDICACAO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_PRESTACAO_GARANT_BOA_EXEC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_PRESTACAO_GARANT_BOA_EXEC = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_SUBMISSAO_MINUT_CONTRATO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_SUBMISSAO_MINUT_CONTRATO = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private AdjudicacaoRepository adjudicacaoRepository;

    @Autowired
    private AdjudicacaoMapper adjudicacaoMapper;

    @Autowired
    private AdjudicacaoService adjudicacaoService;

    @Autowired
    private AdjudicacaoQueryService adjudicacaoQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAdjudicacaoMockMvc;

    private Adjudicacao adjudicacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdjudicacaoResource adjudicacaoResource = new AdjudicacaoResource(adjudicacaoService, adjudicacaoQueryService, null
        );
        this.restAdjudicacaoMockMvc = MockMvcBuilders.standaloneSetup(adjudicacaoResource)
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
    public static Adjudicacao createEntity(EntityManager em) {
        Adjudicacao adjudicacao = new Adjudicacao()
            .tipoConcurso(DEFAULT_TIPO_CONCURSO)
            .dtLancamento(DEFAULT_DT_LANCAMENTO)
            .dtComunicaoAdjudicacao(DEFAULT_DT_COMUNICAO_ADJUDICACAO)
            .dtPrestacaoGarantBoaExec(DEFAULT_DT_PRESTACAO_GARANT_BOA_EXEC)
            .dtSubmissaoMinutContrato(DEFAULT_DT_SUBMISSAO_MINUT_CONTRATO);
        // Add required entity
        ProgramasProjectos idProgramasProjectos = ProgramasProjectosResourceIntTest.createEntity(em);
        em.persist(idProgramasProjectos);
        em.flush();
        adjudicacao.setProgramasProjectos(idProgramasProjectos);
        return adjudicacao;
    }

    @Before
    public void initTest() {
        adjudicacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdjudicacao() throws Exception {
        int databaseSizeBeforeCreate = adjudicacaoRepository.findAll().size();

        // Create the Adjudicacao
        AdjudicacaoDTO adjudicacaoDTO = adjudicacaoMapper.toDto(adjudicacao);
        restAdjudicacaoMockMvc.perform(post("/api/adjudicacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adjudicacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Adjudicacao in the database
        List<Adjudicacao> adjudicacaoList = adjudicacaoRepository.findAll();
        assertThat(adjudicacaoList).hasSize(databaseSizeBeforeCreate + 1);
        Adjudicacao testAdjudicacao = adjudicacaoList.get(adjudicacaoList.size() - 1);
        assertThat(testAdjudicacao.getTipoConcurso()).isEqualTo(DEFAULT_TIPO_CONCURSO);
        assertThat(testAdjudicacao.getDtLancamento()).isEqualTo(DEFAULT_DT_LANCAMENTO);
        assertThat(testAdjudicacao.getDtComunicaoAdjudicacao()).isEqualTo(DEFAULT_DT_COMUNICAO_ADJUDICACAO);
        assertThat(testAdjudicacao.getDtPrestacaoGarantBoaExec()).isEqualTo(DEFAULT_DT_PRESTACAO_GARANT_BOA_EXEC);
        assertThat(testAdjudicacao.getDtSubmissaoMinutContrato()).isEqualTo(DEFAULT_DT_SUBMISSAO_MINUT_CONTRATO);
    }

    @Test
    @Transactional
    public void createAdjudicacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adjudicacaoRepository.findAll().size();

        // Create the Adjudicacao with an existing ID
        adjudicacao.setId(1L);
        AdjudicacaoDTO adjudicacaoDTO = adjudicacaoMapper.toDto(adjudicacao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdjudicacaoMockMvc.perform(post("/api/adjudicacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adjudicacaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Adjudicacao in the database
        List<Adjudicacao> adjudicacaoList = adjudicacaoRepository.findAll();
        assertThat(adjudicacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdAdjudicacaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = adjudicacaoRepository.findAll().size();
        // set the field null

        // Create the Adjudicacao, which fails.
        AdjudicacaoDTO adjudicacaoDTO = adjudicacaoMapper.toDto(adjudicacao);

        restAdjudicacaoMockMvc.perform(post("/api/adjudicacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adjudicacaoDTO)))
            .andExpect(status().isBadRequest());

        List<Adjudicacao> adjudicacaoList = adjudicacaoRepository.findAll();
        assertThat(adjudicacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTipoConcursoIsRequired() throws Exception {
        int databaseSizeBeforeTest = adjudicacaoRepository.findAll().size();
        // set the field null
        adjudicacao.setTipoConcurso(null);

        // Create the Adjudicacao, which fails.
        AdjudicacaoDTO adjudicacaoDTO = adjudicacaoMapper.toDto(adjudicacao);

        restAdjudicacaoMockMvc.perform(post("/api/adjudicacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adjudicacaoDTO)))
            .andExpect(status().isBadRequest());

        List<Adjudicacao> adjudicacaoList = adjudicacaoRepository.findAll();
        assertThat(adjudicacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDtLancamentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = adjudicacaoRepository.findAll().size();
        // set the field null
        adjudicacao.setDtLancamento(null);

        // Create the Adjudicacao, which fails.
        AdjudicacaoDTO adjudicacaoDTO = adjudicacaoMapper.toDto(adjudicacao);

        restAdjudicacaoMockMvc.perform(post("/api/adjudicacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adjudicacaoDTO)))
            .andExpect(status().isBadRequest());

        List<Adjudicacao> adjudicacaoList = adjudicacaoRepository.findAll();
        assertThat(adjudicacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAdjudicacaos() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList
        restAdjudicacaoMockMvc.perform(get("/api/adjudicacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adjudicacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].idAdjudicacao").value(hasItem(DEFAULT_ID_ADJUDICACAO.intValue())))
            .andExpect(jsonPath("$.[*].tipoConcurso").value(hasItem(DEFAULT_TIPO_CONCURSO.toString())))
            .andExpect(jsonPath("$.[*].dtLancamento").value(hasItem(DEFAULT_DT_LANCAMENTO.toString())))
            .andExpect(jsonPath("$.[*].dtComunicaoAdjudicacao").value(hasItem(DEFAULT_DT_COMUNICAO_ADJUDICACAO.toString())))
            .andExpect(jsonPath("$.[*].dtPrestacaoGarantBoaExec").value(hasItem(DEFAULT_DT_PRESTACAO_GARANT_BOA_EXEC.toString())))
            .andExpect(jsonPath("$.[*].dtSubmissaoMinutContrato").value(hasItem(DEFAULT_DT_SUBMISSAO_MINUT_CONTRATO.toString())));
    }

    @Test
    @Transactional
    public void getAdjudicacao() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get the adjudicacao
        restAdjudicacaoMockMvc.perform(get("/api/adjudicacaos/{id}", adjudicacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(adjudicacao.getId().intValue()))
            .andExpect(jsonPath("$.idAdjudicacao").value(DEFAULT_ID_ADJUDICACAO.intValue()))
            .andExpect(jsonPath("$.tipoConcurso").value(DEFAULT_TIPO_CONCURSO.toString()))
            .andExpect(jsonPath("$.dtLancamento").value(DEFAULT_DT_LANCAMENTO.toString()))
            .andExpect(jsonPath("$.dtComunicaoAdjudicacao").value(DEFAULT_DT_COMUNICAO_ADJUDICACAO.toString()))
            .andExpect(jsonPath("$.dtPrestacaoGarantBoaExec").value(DEFAULT_DT_PRESTACAO_GARANT_BOA_EXEC.toString()))
            .andExpect(jsonPath("$.dtSubmissaoMinutContrato").value(DEFAULT_DT_SUBMISSAO_MINUT_CONTRATO.toString()));
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByIdAdjudicacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where idAdjudicacao equals to DEFAULT_ID_ADJUDICACAO
        defaultAdjudicacaoShouldBeFound("idAdjudicacao.equals=" + DEFAULT_ID_ADJUDICACAO);

        // Get all the adjudicacaoList where idAdjudicacao equals to UPDATED_ID_ADJUDICACAO
        defaultAdjudicacaoShouldNotBeFound("idAdjudicacao.equals=" + UPDATED_ID_ADJUDICACAO);
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByIdAdjudicacaoIsInShouldWork() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where idAdjudicacao in DEFAULT_ID_ADJUDICACAO or UPDATED_ID_ADJUDICACAO
        defaultAdjudicacaoShouldBeFound("idAdjudicacao.in=" + DEFAULT_ID_ADJUDICACAO + "," + UPDATED_ID_ADJUDICACAO);

        // Get all the adjudicacaoList where idAdjudicacao equals to UPDATED_ID_ADJUDICACAO
        defaultAdjudicacaoShouldNotBeFound("idAdjudicacao.in=" + UPDATED_ID_ADJUDICACAO);
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByIdAdjudicacaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where idAdjudicacao is not null
        defaultAdjudicacaoShouldBeFound("idAdjudicacao.specified=true");

        // Get all the adjudicacaoList where idAdjudicacao is null
        defaultAdjudicacaoShouldNotBeFound("idAdjudicacao.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByIdAdjudicacaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where idAdjudicacao greater than or equals to DEFAULT_ID_ADJUDICACAO
        defaultAdjudicacaoShouldBeFound("idAdjudicacao.greaterOrEqualThan=" + DEFAULT_ID_ADJUDICACAO);

        // Get all the adjudicacaoList where idAdjudicacao greater than or equals to UPDATED_ID_ADJUDICACAO
        defaultAdjudicacaoShouldNotBeFound("idAdjudicacao.greaterOrEqualThan=" + UPDATED_ID_ADJUDICACAO);
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByIdAdjudicacaoIsLessThanSomething() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where idAdjudicacao less than or equals to DEFAULT_ID_ADJUDICACAO
        defaultAdjudicacaoShouldNotBeFound("idAdjudicacao.lessThan=" + DEFAULT_ID_ADJUDICACAO);

        // Get all the adjudicacaoList where idAdjudicacao less than or equals to UPDATED_ID_ADJUDICACAO
        defaultAdjudicacaoShouldBeFound("idAdjudicacao.lessThan=" + UPDATED_ID_ADJUDICACAO);
    }


    @Test
    @Transactional
    public void getAllAdjudicacaosByTipoConcursoIsEqualToSomething() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where tipoConcurso equals to DEFAULT_TIPO_CONCURSO
        defaultAdjudicacaoShouldBeFound("tipoConcurso.equals=" + DEFAULT_TIPO_CONCURSO);

        // Get all the adjudicacaoList where tipoConcurso equals to UPDATED_TIPO_CONCURSO
        defaultAdjudicacaoShouldNotBeFound("tipoConcurso.equals=" + UPDATED_TIPO_CONCURSO);
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByTipoConcursoIsInShouldWork() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where tipoConcurso in DEFAULT_TIPO_CONCURSO or UPDATED_TIPO_CONCURSO
        defaultAdjudicacaoShouldBeFound("tipoConcurso.in=" + DEFAULT_TIPO_CONCURSO + "," + UPDATED_TIPO_CONCURSO);

        // Get all the adjudicacaoList where tipoConcurso equals to UPDATED_TIPO_CONCURSO
        defaultAdjudicacaoShouldNotBeFound("tipoConcurso.in=" + UPDATED_TIPO_CONCURSO);
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByTipoConcursoIsNullOrNotNull() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where tipoConcurso is not null
        defaultAdjudicacaoShouldBeFound("tipoConcurso.specified=true");

        // Get all the adjudicacaoList where tipoConcurso is null
        defaultAdjudicacaoShouldNotBeFound("tipoConcurso.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByDtLancamentoIsEqualToSomething() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where dtLancamento equals to DEFAULT_DT_LANCAMENTO
        defaultAdjudicacaoShouldBeFound("dtLancamento.equals=" + DEFAULT_DT_LANCAMENTO);

        // Get all the adjudicacaoList where dtLancamento equals to UPDATED_DT_LANCAMENTO
        defaultAdjudicacaoShouldNotBeFound("dtLancamento.equals=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByDtLancamentoIsInShouldWork() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where dtLancamento in DEFAULT_DT_LANCAMENTO or UPDATED_DT_LANCAMENTO
        defaultAdjudicacaoShouldBeFound("dtLancamento.in=" + DEFAULT_DT_LANCAMENTO + "," + UPDATED_DT_LANCAMENTO);

        // Get all the adjudicacaoList where dtLancamento equals to UPDATED_DT_LANCAMENTO
        defaultAdjudicacaoShouldNotBeFound("dtLancamento.in=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByDtLancamentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where dtLancamento is not null
        defaultAdjudicacaoShouldBeFound("dtLancamento.specified=true");

        // Get all the adjudicacaoList where dtLancamento is null
        defaultAdjudicacaoShouldNotBeFound("dtLancamento.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByDtLancamentoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where dtLancamento greater than or equals to DEFAULT_DT_LANCAMENTO
        defaultAdjudicacaoShouldBeFound("dtLancamento.greaterOrEqualThan=" + DEFAULT_DT_LANCAMENTO);

        // Get all the adjudicacaoList where dtLancamento greater than or equals to UPDATED_DT_LANCAMENTO
        defaultAdjudicacaoShouldNotBeFound("dtLancamento.greaterOrEqualThan=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByDtLancamentoIsLessThanSomething() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where dtLancamento less than or equals to DEFAULT_DT_LANCAMENTO
        defaultAdjudicacaoShouldNotBeFound("dtLancamento.lessThan=" + DEFAULT_DT_LANCAMENTO);

        // Get all the adjudicacaoList where dtLancamento less than or equals to UPDATED_DT_LANCAMENTO
        defaultAdjudicacaoShouldBeFound("dtLancamento.lessThan=" + UPDATED_DT_LANCAMENTO);
    }


    @Test
    @Transactional
    public void getAllAdjudicacaosByDtComunicaoAdjudicacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where dtComunicaoAdjudicacao equals to DEFAULT_DT_COMUNICAO_ADJUDICACAO
        defaultAdjudicacaoShouldBeFound("dtComunicaoAdjudicacao.equals=" + DEFAULT_DT_COMUNICAO_ADJUDICACAO);

        // Get all the adjudicacaoList where dtComunicaoAdjudicacao equals to UPDATED_DT_COMUNICAO_ADJUDICACAO
        defaultAdjudicacaoShouldNotBeFound("dtComunicaoAdjudicacao.equals=" + UPDATED_DT_COMUNICAO_ADJUDICACAO);
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByDtComunicaoAdjudicacaoIsInShouldWork() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where dtComunicaoAdjudicacao in DEFAULT_DT_COMUNICAO_ADJUDICACAO or UPDATED_DT_COMUNICAO_ADJUDICACAO
        defaultAdjudicacaoShouldBeFound("dtComunicaoAdjudicacao.in=" + DEFAULT_DT_COMUNICAO_ADJUDICACAO + "," + UPDATED_DT_COMUNICAO_ADJUDICACAO);

        // Get all the adjudicacaoList where dtComunicaoAdjudicacao equals to UPDATED_DT_COMUNICAO_ADJUDICACAO
        defaultAdjudicacaoShouldNotBeFound("dtComunicaoAdjudicacao.in=" + UPDATED_DT_COMUNICAO_ADJUDICACAO);
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByDtComunicaoAdjudicacaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where dtComunicaoAdjudicacao is not null
        defaultAdjudicacaoShouldBeFound("dtComunicaoAdjudicacao.specified=true");

        // Get all the adjudicacaoList where dtComunicaoAdjudicacao is null
        defaultAdjudicacaoShouldNotBeFound("dtComunicaoAdjudicacao.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByDtComunicaoAdjudicacaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where dtComunicaoAdjudicacao greater than or equals to DEFAULT_DT_COMUNICAO_ADJUDICACAO
        defaultAdjudicacaoShouldBeFound("dtComunicaoAdjudicacao.greaterOrEqualThan=" + DEFAULT_DT_COMUNICAO_ADJUDICACAO);

        // Get all the adjudicacaoList where dtComunicaoAdjudicacao greater than or equals to UPDATED_DT_COMUNICAO_ADJUDICACAO
        defaultAdjudicacaoShouldNotBeFound("dtComunicaoAdjudicacao.greaterOrEqualThan=" + UPDATED_DT_COMUNICAO_ADJUDICACAO);
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByDtComunicaoAdjudicacaoIsLessThanSomething() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where dtComunicaoAdjudicacao less than or equals to DEFAULT_DT_COMUNICAO_ADJUDICACAO
        defaultAdjudicacaoShouldNotBeFound("dtComunicaoAdjudicacao.lessThan=" + DEFAULT_DT_COMUNICAO_ADJUDICACAO);

        // Get all the adjudicacaoList where dtComunicaoAdjudicacao less than or equals to UPDATED_DT_COMUNICAO_ADJUDICACAO
        defaultAdjudicacaoShouldBeFound("dtComunicaoAdjudicacao.lessThan=" + UPDATED_DT_COMUNICAO_ADJUDICACAO);
    }


    @Test
    @Transactional
    public void getAllAdjudicacaosByDtPrestacaoGarantBoaExecIsEqualToSomething() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where dtPrestacaoGarantBoaExec equals to DEFAULT_DT_PRESTACAO_GARANT_BOA_EXEC
        defaultAdjudicacaoShouldBeFound("dtPrestacaoGarantBoaExec.equals=" + DEFAULT_DT_PRESTACAO_GARANT_BOA_EXEC);

        // Get all the adjudicacaoList where dtPrestacaoGarantBoaExec equals to UPDATED_DT_PRESTACAO_GARANT_BOA_EXEC
        defaultAdjudicacaoShouldNotBeFound("dtPrestacaoGarantBoaExec.equals=" + UPDATED_DT_PRESTACAO_GARANT_BOA_EXEC);
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByDtPrestacaoGarantBoaExecIsInShouldWork() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where dtPrestacaoGarantBoaExec in DEFAULT_DT_PRESTACAO_GARANT_BOA_EXEC or UPDATED_DT_PRESTACAO_GARANT_BOA_EXEC
        defaultAdjudicacaoShouldBeFound("dtPrestacaoGarantBoaExec.in=" + DEFAULT_DT_PRESTACAO_GARANT_BOA_EXEC + "," + UPDATED_DT_PRESTACAO_GARANT_BOA_EXEC);

        // Get all the adjudicacaoList where dtPrestacaoGarantBoaExec equals to UPDATED_DT_PRESTACAO_GARANT_BOA_EXEC
        defaultAdjudicacaoShouldNotBeFound("dtPrestacaoGarantBoaExec.in=" + UPDATED_DT_PRESTACAO_GARANT_BOA_EXEC);
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByDtPrestacaoGarantBoaExecIsNullOrNotNull() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where dtPrestacaoGarantBoaExec is not null
        defaultAdjudicacaoShouldBeFound("dtPrestacaoGarantBoaExec.specified=true");

        // Get all the adjudicacaoList where dtPrestacaoGarantBoaExec is null
        defaultAdjudicacaoShouldNotBeFound("dtPrestacaoGarantBoaExec.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByDtPrestacaoGarantBoaExecIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where dtPrestacaoGarantBoaExec greater than or equals to DEFAULT_DT_PRESTACAO_GARANT_BOA_EXEC
        defaultAdjudicacaoShouldBeFound("dtPrestacaoGarantBoaExec.greaterOrEqualThan=" + DEFAULT_DT_PRESTACAO_GARANT_BOA_EXEC);

        // Get all the adjudicacaoList where dtPrestacaoGarantBoaExec greater than or equals to UPDATED_DT_PRESTACAO_GARANT_BOA_EXEC
        defaultAdjudicacaoShouldNotBeFound("dtPrestacaoGarantBoaExec.greaterOrEqualThan=" + UPDATED_DT_PRESTACAO_GARANT_BOA_EXEC);
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByDtPrestacaoGarantBoaExecIsLessThanSomething() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where dtPrestacaoGarantBoaExec less than or equals to DEFAULT_DT_PRESTACAO_GARANT_BOA_EXEC
        defaultAdjudicacaoShouldNotBeFound("dtPrestacaoGarantBoaExec.lessThan=" + DEFAULT_DT_PRESTACAO_GARANT_BOA_EXEC);

        // Get all the adjudicacaoList where dtPrestacaoGarantBoaExec less than or equals to UPDATED_DT_PRESTACAO_GARANT_BOA_EXEC
        defaultAdjudicacaoShouldBeFound("dtPrestacaoGarantBoaExec.lessThan=" + UPDATED_DT_PRESTACAO_GARANT_BOA_EXEC);
    }


    @Test
    @Transactional
    public void getAllAdjudicacaosByDtSubmissaoMinutContratoIsEqualToSomething() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where dtSubmissaoMinutContrato equals to DEFAULT_DT_SUBMISSAO_MINUT_CONTRATO
        defaultAdjudicacaoShouldBeFound("dtSubmissaoMinutContrato.equals=" + DEFAULT_DT_SUBMISSAO_MINUT_CONTRATO);

        // Get all the adjudicacaoList where dtSubmissaoMinutContrato equals to UPDATED_DT_SUBMISSAO_MINUT_CONTRATO
        defaultAdjudicacaoShouldNotBeFound("dtSubmissaoMinutContrato.equals=" + UPDATED_DT_SUBMISSAO_MINUT_CONTRATO);
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByDtSubmissaoMinutContratoIsInShouldWork() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where dtSubmissaoMinutContrato in DEFAULT_DT_SUBMISSAO_MINUT_CONTRATO or UPDATED_DT_SUBMISSAO_MINUT_CONTRATO
        defaultAdjudicacaoShouldBeFound("dtSubmissaoMinutContrato.in=" + DEFAULT_DT_SUBMISSAO_MINUT_CONTRATO + "," + UPDATED_DT_SUBMISSAO_MINUT_CONTRATO);

        // Get all the adjudicacaoList where dtSubmissaoMinutContrato equals to UPDATED_DT_SUBMISSAO_MINUT_CONTRATO
        defaultAdjudicacaoShouldNotBeFound("dtSubmissaoMinutContrato.in=" + UPDATED_DT_SUBMISSAO_MINUT_CONTRATO);
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByDtSubmissaoMinutContratoIsNullOrNotNull() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where dtSubmissaoMinutContrato is not null
        defaultAdjudicacaoShouldBeFound("dtSubmissaoMinutContrato.specified=true");

        // Get all the adjudicacaoList where dtSubmissaoMinutContrato is null
        defaultAdjudicacaoShouldNotBeFound("dtSubmissaoMinutContrato.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByDtSubmissaoMinutContratoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where dtSubmissaoMinutContrato greater than or equals to DEFAULT_DT_SUBMISSAO_MINUT_CONTRATO
        defaultAdjudicacaoShouldBeFound("dtSubmissaoMinutContrato.greaterOrEqualThan=" + DEFAULT_DT_SUBMISSAO_MINUT_CONTRATO);

        // Get all the adjudicacaoList where dtSubmissaoMinutContrato greater than or equals to UPDATED_DT_SUBMISSAO_MINUT_CONTRATO
        defaultAdjudicacaoShouldNotBeFound("dtSubmissaoMinutContrato.greaterOrEqualThan=" + UPDATED_DT_SUBMISSAO_MINUT_CONTRATO);
    }

    @Test
    @Transactional
    public void getAllAdjudicacaosByDtSubmissaoMinutContratoIsLessThanSomething() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);

        // Get all the adjudicacaoList where dtSubmissaoMinutContrato less than or equals to DEFAULT_DT_SUBMISSAO_MINUT_CONTRATO
        defaultAdjudicacaoShouldNotBeFound("dtSubmissaoMinutContrato.lessThan=" + DEFAULT_DT_SUBMISSAO_MINUT_CONTRATO);

        // Get all the adjudicacaoList where dtSubmissaoMinutContrato less than or equals to UPDATED_DT_SUBMISSAO_MINUT_CONTRATO
        defaultAdjudicacaoShouldBeFound("dtSubmissaoMinutContrato.lessThan=" + UPDATED_DT_SUBMISSAO_MINUT_CONTRATO);
    }


    @Test
    @Transactional
    public void getAllAdjudicacaosByIdProgramasProjectosIsEqualToSomething() throws Exception {
        // Initialize the database
        ProgramasProjectos idProgramasProjectos = ProgramasProjectosResourceIntTest.createEntity(em);
        em.persist(idProgramasProjectos);
        em.flush();
        adjudicacao.setProgramasProjectos(idProgramasProjectos);
        adjudicacaoRepository.saveAndFlush(adjudicacao);
        Long idProgramasProjectosId = idProgramasProjectos.getId();

        // Get all the adjudicacaoList where idProgramasProjectos equals to idProgramasProjectosId
        defaultAdjudicacaoShouldBeFound("idProgramasProjectosId.equals=" + idProgramasProjectosId);

        // Get all the adjudicacaoList where idProgramasProjectos equals to idProgramasProjectosId + 1
        defaultAdjudicacaoShouldNotBeFound("idProgramasProjectosId.equals=" + (idProgramasProjectosId + 1));
    }


    @Test
    @Transactional
    public void getAllAdjudicacaosByIdSistemaAguaIsEqualToSomething() throws Exception {
        // Initialize the database
        SistemaAgua idSistemaAgua = SistemaAguaResourceIntTest.createEntity(em);
        em.persist(idSistemaAgua);
        em.flush();
        adjudicacao.setIdSistemaAgua(idSistemaAgua);
        adjudicacaoRepository.saveAndFlush(adjudicacao);
        Long idSistemaAguaId = idSistemaAgua.getId();

        // Get all the adjudicacaoList where idSistemaAgua equals to idSistemaAguaId
        defaultAdjudicacaoShouldBeFound("idSistemaAguaId.equals=" + idSistemaAguaId);

        // Get all the adjudicacaoList where idSistemaAgua equals to idSistemaAguaId + 1
        defaultAdjudicacaoShouldNotBeFound("idSistemaAguaId.equals=" + (idSistemaAguaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultAdjudicacaoShouldBeFound(String filter) throws Exception {
        restAdjudicacaoMockMvc.perform(get("/api/adjudicacaos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adjudicacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].idAdjudicacao").value(hasItem(DEFAULT_ID_ADJUDICACAO.intValue())))
            .andExpect(jsonPath("$.[*].tipoConcurso").value(hasItem(DEFAULT_TIPO_CONCURSO.toString())))
            .andExpect(jsonPath("$.[*].dtLancamento").value(hasItem(DEFAULT_DT_LANCAMENTO.toString())))
            .andExpect(jsonPath("$.[*].dtComunicaoAdjudicacao").value(hasItem(DEFAULT_DT_COMUNICAO_ADJUDICACAO.toString())))
            .andExpect(jsonPath("$.[*].dtPrestacaoGarantBoaExec").value(hasItem(DEFAULT_DT_PRESTACAO_GARANT_BOA_EXEC.toString())))
            .andExpect(jsonPath("$.[*].dtSubmissaoMinutContrato").value(hasItem(DEFAULT_DT_SUBMISSAO_MINUT_CONTRATO.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultAdjudicacaoShouldNotBeFound(String filter) throws Exception {
        restAdjudicacaoMockMvc.perform(get("/api/adjudicacaos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingAdjudicacao() throws Exception {
        // Get the adjudicacao
        restAdjudicacaoMockMvc.perform(get("/api/adjudicacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdjudicacao() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);
        int databaseSizeBeforeUpdate = adjudicacaoRepository.findAll().size();

        // Update the adjudicacao
        Adjudicacao updatedAdjudicacao = adjudicacaoRepository.findOne(adjudicacao.getId());
        // Disconnect from session so that the updates on updatedAdjudicacao are not directly saved in db
        em.detach(updatedAdjudicacao);
        updatedAdjudicacao
            .tipoConcurso(UPDATED_TIPO_CONCURSO)
            .dtLancamento(UPDATED_DT_LANCAMENTO)
            .dtComunicaoAdjudicacao(UPDATED_DT_COMUNICAO_ADJUDICACAO)
            .dtPrestacaoGarantBoaExec(UPDATED_DT_PRESTACAO_GARANT_BOA_EXEC)
            .dtSubmissaoMinutContrato(UPDATED_DT_SUBMISSAO_MINUT_CONTRATO);
        AdjudicacaoDTO adjudicacaoDTO = adjudicacaoMapper.toDto(updatedAdjudicacao);

        restAdjudicacaoMockMvc.perform(put("/api/adjudicacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adjudicacaoDTO)))
            .andExpect(status().isOk());

        // Validate the Adjudicacao in the database
        List<Adjudicacao> adjudicacaoList = adjudicacaoRepository.findAll();
        assertThat(adjudicacaoList).hasSize(databaseSizeBeforeUpdate);
        Adjudicacao testAdjudicacao = adjudicacaoList.get(adjudicacaoList.size() - 1);
        assertThat(testAdjudicacao.getTipoConcurso()).isEqualTo(UPDATED_TIPO_CONCURSO);
        assertThat(testAdjudicacao.getDtLancamento()).isEqualTo(UPDATED_DT_LANCAMENTO);
        assertThat(testAdjudicacao.getDtComunicaoAdjudicacao()).isEqualTo(UPDATED_DT_COMUNICAO_ADJUDICACAO);
        assertThat(testAdjudicacao.getDtPrestacaoGarantBoaExec()).isEqualTo(UPDATED_DT_PRESTACAO_GARANT_BOA_EXEC);
        assertThat(testAdjudicacao.getDtSubmissaoMinutContrato()).isEqualTo(UPDATED_DT_SUBMISSAO_MINUT_CONTRATO);
    }

    @Test
    @Transactional
    public void updateNonExistingAdjudicacao() throws Exception {
        int databaseSizeBeforeUpdate = adjudicacaoRepository.findAll().size();

        // Create the Adjudicacao
        AdjudicacaoDTO adjudicacaoDTO = adjudicacaoMapper.toDto(adjudicacao);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAdjudicacaoMockMvc.perform(put("/api/adjudicacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adjudicacaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Adjudicacao in the database
        List<Adjudicacao> adjudicacaoList = adjudicacaoRepository.findAll();
        assertThat(adjudicacaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAdjudicacao() throws Exception {
        // Initialize the database
        adjudicacaoRepository.saveAndFlush(adjudicacao);
        int databaseSizeBeforeDelete = adjudicacaoRepository.findAll().size();

        // Get the adjudicacao
        restAdjudicacaoMockMvc.perform(delete("/api/adjudicacaos/{id}", adjudicacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Adjudicacao> adjudicacaoList = adjudicacaoRepository.findAll();
        assertThat(adjudicacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Adjudicacao.class);
        Adjudicacao adjudicacao1 = new Adjudicacao();
        adjudicacao1.setId(1L);
        Adjudicacao adjudicacao2 = new Adjudicacao();
        adjudicacao2.setId(adjudicacao1.getId());
        assertThat(adjudicacao1).isEqualTo(adjudicacao2);
        adjudicacao2.setId(2L);
        assertThat(adjudicacao1).isNotEqualTo(adjudicacao2);
        adjudicacao1.setId(null);
        assertThat(adjudicacao1).isNotEqualTo(adjudicacao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjudicacaoDTO.class);
        AdjudicacaoDTO adjudicacaoDTO1 = new AdjudicacaoDTO();
        adjudicacaoDTO1.setId(1L);
        AdjudicacaoDTO adjudicacaoDTO2 = new AdjudicacaoDTO();
        assertThat(adjudicacaoDTO1).isNotEqualTo(adjudicacaoDTO2);
        adjudicacaoDTO2.setId(adjudicacaoDTO1.getId());
        assertThat(adjudicacaoDTO1).isEqualTo(adjudicacaoDTO2);
        adjudicacaoDTO2.setId(2L);
        assertThat(adjudicacaoDTO1).isNotEqualTo(adjudicacaoDTO2);
        adjudicacaoDTO1.setId(null);
        assertThat(adjudicacaoDTO1).isNotEqualTo(adjudicacaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(adjudicacaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(adjudicacaoMapper.fromId(null)).isNull();
    }
}
