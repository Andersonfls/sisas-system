package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.Concurso;
import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.repository.ConcursoRepository;
import com.minea.sisas.service.ConcursoService;
import com.minea.sisas.service.dto.ConcursoDTO;
import com.minea.sisas.service.mapper.ConcursoMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;

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
 * Test class for the ConcursoResource REST controller.
 *
 * @see ConcursoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class ConcursoResourceIntTest {

    private static final Long DEFAULT_ID_CONCURSO = 1L;
    private static final Long UPDATED_ID_CONCURSO = 2L;

    private static final String DEFAULT_TIPO_CONCURSO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_CONCURSO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DT_LANCAMENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_LANCAMENTO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_ULTIMA_ALTERACAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_ULTIMA_ALTERACAO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_ENTREGA_PROPOSTA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_ENTREGA_PROPOSTA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_ABERTURA_PROPOSTA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_ABERTURA_PROPOSTA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_CONCLUSAO_AVALIACAO_REL_PREL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_CONCLUSAO_AVALIACAO_REL_PREL = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_NEGOCIACAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_NEGOCIACAO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_APROV_REL_AVAL_FINAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_APROV_REL_AVAL_FINAL = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private ConcursoRepository concursoRepository;

    @Autowired
    private ConcursoMapper concursoMapper;

    @Autowired
    private ConcursoService concursoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConcursoMockMvc;

    private Concurso concurso;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConcursoResource concursoResource = new ConcursoResource(concursoService, null);
        this.restConcursoMockMvc = MockMvcBuilders.standaloneSetup(concursoResource)
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
    public static Concurso createEntity(EntityManager em) {
        Concurso concurso = new Concurso()
            .tipoConcurso(DEFAULT_TIPO_CONCURSO)
            .dtLancamento(DEFAULT_DT_LANCAMENTO)
            .dtUltimaAlteracao(DEFAULT_DT_ULTIMA_ALTERACAO)
            .dtEntregaProposta(DEFAULT_DT_ENTREGA_PROPOSTA)
            .dtAberturaProposta(DEFAULT_DT_ABERTURA_PROPOSTA)
            .dtConclusaoAvaliacaoRelPrel(DEFAULT_DT_CONCLUSAO_AVALIACAO_REL_PREL)
            .dtNegociacao(DEFAULT_DT_NEGOCIACAO)
            .dtAprovRelAvalFinal(DEFAULT_DT_APROV_REL_AVAL_FINAL);
        // Add required entity
        ProgramasProjectos idProgramasProjectos = ProgramasProjectosResourceIntTest.createEntity(em);
        em.persist(idProgramasProjectos);
        em.flush();
        concurso.setProgramasProjectos(idProgramasProjectos);
        return concurso;
    }

    @Before
    public void initTest() {
        concurso = createEntity(em);
    }

    @Test
    @Transactional
    public void createConcurso() throws Exception {
        int databaseSizeBeforeCreate = concursoRepository.findAll().size();

        // Create the Concurso
        ConcursoDTO concursoDTO = concursoMapper.toDto(concurso);
        restConcursoMockMvc.perform(post("/api/concursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(concursoDTO)))
            .andExpect(status().isCreated());

        // Validate the Concurso in the database
        List<Concurso> concursoList = concursoRepository.findAll();
        assertThat(concursoList).hasSize(databaseSizeBeforeCreate + 1);
        Concurso testConcurso = concursoList.get(concursoList.size() - 1);
        assertThat(testConcurso.getTipoConcurso()).isEqualTo(DEFAULT_TIPO_CONCURSO);
        assertThat(testConcurso.getDtLancamento()).isEqualTo(DEFAULT_DT_LANCAMENTO);
        assertThat(testConcurso.getDtUltimaAlteracao()).isEqualTo(DEFAULT_DT_ULTIMA_ALTERACAO);
        assertThat(testConcurso.getDtEntregaProposta()).isEqualTo(DEFAULT_DT_ENTREGA_PROPOSTA);
        assertThat(testConcurso.getDtAberturaProposta()).isEqualTo(DEFAULT_DT_ABERTURA_PROPOSTA);
        assertThat(testConcurso.getDtConclusaoAvaliacaoRelPrel()).isEqualTo(DEFAULT_DT_CONCLUSAO_AVALIACAO_REL_PREL);
        assertThat(testConcurso.getDtNegociacao()).isEqualTo(DEFAULT_DT_NEGOCIACAO);
        assertThat(testConcurso.getDtAprovRelAvalFinal()).isEqualTo(DEFAULT_DT_APROV_REL_AVAL_FINAL);
    }

    @Test
    @Transactional
    public void createConcursoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = concursoRepository.findAll().size();

        // Create the Concurso with an existing ID
        concurso.setId(1L);
        ConcursoDTO concursoDTO = concursoMapper.toDto(concurso);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConcursoMockMvc.perform(post("/api/concursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(concursoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Concurso in the database
        List<Concurso> concursoList = concursoRepository.findAll();
        assertThat(concursoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdConcursoIsRequired() throws Exception {
        int databaseSizeBeforeTest = concursoRepository.findAll().size();
        // set the field null

        // Create the Concurso, which fails.
        ConcursoDTO concursoDTO = concursoMapper.toDto(concurso);

        restConcursoMockMvc.perform(post("/api/concursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(concursoDTO)))
            .andExpect(status().isBadRequest());

        List<Concurso> concursoList = concursoRepository.findAll();
        assertThat(concursoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTipoConcursoIsRequired() throws Exception {
        int databaseSizeBeforeTest = concursoRepository.findAll().size();
        // set the field null
        concurso.setTipoConcurso(null);

        // Create the Concurso, which fails.
        ConcursoDTO concursoDTO = concursoMapper.toDto(concurso);

        restConcursoMockMvc.perform(post("/api/concursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(concursoDTO)))
            .andExpect(status().isBadRequest());

        List<Concurso> concursoList = concursoRepository.findAll();
        assertThat(concursoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDtLancamentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = concursoRepository.findAll().size();
        // set the field null
        concurso.setDtLancamento(null);

        // Create the Concurso, which fails.
        ConcursoDTO concursoDTO = concursoMapper.toDto(concurso);

        restConcursoMockMvc.perform(post("/api/concursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(concursoDTO)))
            .andExpect(status().isBadRequest());

        List<Concurso> concursoList = concursoRepository.findAll();
        assertThat(concursoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllConcursos() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList
        restConcursoMockMvc.perform(get("/api/concursos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(concurso.getId().intValue())))
            .andExpect(jsonPath("$.[*].idConcurso").value(hasItem(DEFAULT_ID_CONCURSO.intValue())))
            .andExpect(jsonPath("$.[*].tipoConcurso").value(hasItem(DEFAULT_TIPO_CONCURSO.toString())))
            .andExpect(jsonPath("$.[*].dtLancamento").value(hasItem(DEFAULT_DT_LANCAMENTO.toString())))
            .andExpect(jsonPath("$.[*].dtUltimaAlteracao").value(hasItem(DEFAULT_DT_ULTIMA_ALTERACAO.toString())))
            .andExpect(jsonPath("$.[*].dtEntregaProposta").value(hasItem(DEFAULT_DT_ENTREGA_PROPOSTA.toString())))
            .andExpect(jsonPath("$.[*].dtAberturaProposta").value(hasItem(DEFAULT_DT_ABERTURA_PROPOSTA.toString())))
            .andExpect(jsonPath("$.[*].dtConclusaoAvaliacaoRelPrel").value(hasItem(DEFAULT_DT_CONCLUSAO_AVALIACAO_REL_PREL.toString())))
            .andExpect(jsonPath("$.[*].dtNegociacao").value(hasItem(DEFAULT_DT_NEGOCIACAO.toString())))
            .andExpect(jsonPath("$.[*].dtAprovRelAvalFinal").value(hasItem(DEFAULT_DT_APROV_REL_AVAL_FINAL.toString())));
    }

    @Test
    @Transactional
    public void getConcurso() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get the concurso
        restConcursoMockMvc.perform(get("/api/concursos/{id}", concurso.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(concurso.getId().intValue()))
            .andExpect(jsonPath("$.idConcurso").value(DEFAULT_ID_CONCURSO.intValue()))
            .andExpect(jsonPath("$.tipoConcurso").value(DEFAULT_TIPO_CONCURSO.toString()))
            .andExpect(jsonPath("$.dtLancamento").value(DEFAULT_DT_LANCAMENTO.toString()))
            .andExpect(jsonPath("$.dtUltimaAlteracao").value(DEFAULT_DT_ULTIMA_ALTERACAO.toString()))
            .andExpect(jsonPath("$.dtEntregaProposta").value(DEFAULT_DT_ENTREGA_PROPOSTA.toString()))
            .andExpect(jsonPath("$.dtAberturaProposta").value(DEFAULT_DT_ABERTURA_PROPOSTA.toString()))
            .andExpect(jsonPath("$.dtConclusaoAvaliacaoRelPrel").value(DEFAULT_DT_CONCLUSAO_AVALIACAO_REL_PREL.toString()))
            .andExpect(jsonPath("$.dtNegociacao").value(DEFAULT_DT_NEGOCIACAO.toString()))
            .andExpect(jsonPath("$.dtAprovRelAvalFinal").value(DEFAULT_DT_APROV_REL_AVAL_FINAL.toString()));
    }

    @Test
    @Transactional
    public void getAllConcursosByIdConcursoIsEqualToSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where idConcurso equals to DEFAULT_ID_CONCURSO
        defaultConcursoShouldBeFound("idConcurso.equals=" + DEFAULT_ID_CONCURSO);

        // Get all the concursoList where idConcurso equals to UPDATED_ID_CONCURSO
        defaultConcursoShouldNotBeFound("idConcurso.equals=" + UPDATED_ID_CONCURSO);
    }

    @Test
    @Transactional
    public void getAllConcursosByIdConcursoIsInShouldWork() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where idConcurso in DEFAULT_ID_CONCURSO or UPDATED_ID_CONCURSO
        defaultConcursoShouldBeFound("idConcurso.in=" + DEFAULT_ID_CONCURSO + "," + UPDATED_ID_CONCURSO);

        // Get all the concursoList where idConcurso equals to UPDATED_ID_CONCURSO
        defaultConcursoShouldNotBeFound("idConcurso.in=" + UPDATED_ID_CONCURSO);
    }

    @Test
    @Transactional
    public void getAllConcursosByIdConcursoIsNullOrNotNull() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where idConcurso is not null
        defaultConcursoShouldBeFound("idConcurso.specified=true");

        // Get all the concursoList where idConcurso is null
        defaultConcursoShouldNotBeFound("idConcurso.specified=false");
    }

    @Test
    @Transactional
    public void getAllConcursosByIdConcursoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where idConcurso greater than or equals to DEFAULT_ID_CONCURSO
        defaultConcursoShouldBeFound("idConcurso.greaterOrEqualThan=" + DEFAULT_ID_CONCURSO);

        // Get all the concursoList where idConcurso greater than or equals to UPDATED_ID_CONCURSO
        defaultConcursoShouldNotBeFound("idConcurso.greaterOrEqualThan=" + UPDATED_ID_CONCURSO);
    }

    @Test
    @Transactional
    public void getAllConcursosByIdConcursoIsLessThanSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where idConcurso less than or equals to DEFAULT_ID_CONCURSO
        defaultConcursoShouldNotBeFound("idConcurso.lessThan=" + DEFAULT_ID_CONCURSO);

        // Get all the concursoList where idConcurso less than or equals to UPDATED_ID_CONCURSO
        defaultConcursoShouldBeFound("idConcurso.lessThan=" + UPDATED_ID_CONCURSO);
    }


    @Test
    @Transactional
    public void getAllConcursosByTipoConcursoIsEqualToSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where tipoConcurso equals to DEFAULT_TIPO_CONCURSO
        defaultConcursoShouldBeFound("tipoConcurso.equals=" + DEFAULT_TIPO_CONCURSO);

        // Get all the concursoList where tipoConcurso equals to UPDATED_TIPO_CONCURSO
        defaultConcursoShouldNotBeFound("tipoConcurso.equals=" + UPDATED_TIPO_CONCURSO);
    }

    @Test
    @Transactional
    public void getAllConcursosByTipoConcursoIsInShouldWork() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where tipoConcurso in DEFAULT_TIPO_CONCURSO or UPDATED_TIPO_CONCURSO
        defaultConcursoShouldBeFound("tipoConcurso.in=" + DEFAULT_TIPO_CONCURSO + "," + UPDATED_TIPO_CONCURSO);

        // Get all the concursoList where tipoConcurso equals to UPDATED_TIPO_CONCURSO
        defaultConcursoShouldNotBeFound("tipoConcurso.in=" + UPDATED_TIPO_CONCURSO);
    }

    @Test
    @Transactional
    public void getAllConcursosByTipoConcursoIsNullOrNotNull() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where tipoConcurso is not null
        defaultConcursoShouldBeFound("tipoConcurso.specified=true");

        // Get all the concursoList where tipoConcurso is null
        defaultConcursoShouldNotBeFound("tipoConcurso.specified=false");
    }

    @Test
    @Transactional
    public void getAllConcursosByDtLancamentoIsEqualToSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtLancamento equals to DEFAULT_DT_LANCAMENTO
        defaultConcursoShouldBeFound("dtLancamento.equals=" + DEFAULT_DT_LANCAMENTO);

        // Get all the concursoList where dtLancamento equals to UPDATED_DT_LANCAMENTO
        defaultConcursoShouldNotBeFound("dtLancamento.equals=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllConcursosByDtLancamentoIsInShouldWork() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtLancamento in DEFAULT_DT_LANCAMENTO or UPDATED_DT_LANCAMENTO
        defaultConcursoShouldBeFound("dtLancamento.in=" + DEFAULT_DT_LANCAMENTO + "," + UPDATED_DT_LANCAMENTO);

        // Get all the concursoList where dtLancamento equals to UPDATED_DT_LANCAMENTO
        defaultConcursoShouldNotBeFound("dtLancamento.in=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllConcursosByDtLancamentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtLancamento is not null
        defaultConcursoShouldBeFound("dtLancamento.specified=true");

        // Get all the concursoList where dtLancamento is null
        defaultConcursoShouldNotBeFound("dtLancamento.specified=false");
    }

    @Test
    @Transactional
    public void getAllConcursosByDtLancamentoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtLancamento greater than or equals to DEFAULT_DT_LANCAMENTO
        defaultConcursoShouldBeFound("dtLancamento.greaterOrEqualThan=" + DEFAULT_DT_LANCAMENTO);

        // Get all the concursoList where dtLancamento greater than or equals to UPDATED_DT_LANCAMENTO
        defaultConcursoShouldNotBeFound("dtLancamento.greaterOrEqualThan=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllConcursosByDtLancamentoIsLessThanSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtLancamento less than or equals to DEFAULT_DT_LANCAMENTO
        defaultConcursoShouldNotBeFound("dtLancamento.lessThan=" + DEFAULT_DT_LANCAMENTO);

        // Get all the concursoList where dtLancamento less than or equals to UPDATED_DT_LANCAMENTO
        defaultConcursoShouldBeFound("dtLancamento.lessThan=" + UPDATED_DT_LANCAMENTO);
    }


    @Test
    @Transactional
    public void getAllConcursosByDtUltimaAlteracaoIsEqualToSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtUltimaAlteracao equals to DEFAULT_DT_ULTIMA_ALTERACAO
        defaultConcursoShouldBeFound("dtUltimaAlteracao.equals=" + DEFAULT_DT_ULTIMA_ALTERACAO);

        // Get all the concursoList where dtUltimaAlteracao equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultConcursoShouldNotBeFound("dtUltimaAlteracao.equals=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }

    @Test
    @Transactional
    public void getAllConcursosByDtUltimaAlteracaoIsInShouldWork() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtUltimaAlteracao in DEFAULT_DT_ULTIMA_ALTERACAO or UPDATED_DT_ULTIMA_ALTERACAO
        defaultConcursoShouldBeFound("dtUltimaAlteracao.in=" + DEFAULT_DT_ULTIMA_ALTERACAO + "," + UPDATED_DT_ULTIMA_ALTERACAO);

        // Get all the concursoList where dtUltimaAlteracao equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultConcursoShouldNotBeFound("dtUltimaAlteracao.in=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }

    @Test
    @Transactional
    public void getAllConcursosByDtUltimaAlteracaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtUltimaAlteracao is not null
        defaultConcursoShouldBeFound("dtUltimaAlteracao.specified=true");

        // Get all the concursoList where dtUltimaAlteracao is null
        defaultConcursoShouldNotBeFound("dtUltimaAlteracao.specified=false");
    }

    @Test
    @Transactional
    public void getAllConcursosByDtUltimaAlteracaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtUltimaAlteracao greater than or equals to DEFAULT_DT_ULTIMA_ALTERACAO
        defaultConcursoShouldBeFound("dtUltimaAlteracao.greaterOrEqualThan=" + DEFAULT_DT_ULTIMA_ALTERACAO);

        // Get all the concursoList where dtUltimaAlteracao greater than or equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultConcursoShouldNotBeFound("dtUltimaAlteracao.greaterOrEqualThan=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }

    @Test
    @Transactional
    public void getAllConcursosByDtUltimaAlteracaoIsLessThanSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtUltimaAlteracao less than or equals to DEFAULT_DT_ULTIMA_ALTERACAO
        defaultConcursoShouldNotBeFound("dtUltimaAlteracao.lessThan=" + DEFAULT_DT_ULTIMA_ALTERACAO);

        // Get all the concursoList where dtUltimaAlteracao less than or equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultConcursoShouldBeFound("dtUltimaAlteracao.lessThan=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }


    @Test
    @Transactional
    public void getAllConcursosByDtEntregaPropostaIsEqualToSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtEntregaProposta equals to DEFAULT_DT_ENTREGA_PROPOSTA
        defaultConcursoShouldBeFound("dtEntregaProposta.equals=" + DEFAULT_DT_ENTREGA_PROPOSTA);

        // Get all the concursoList where dtEntregaProposta equals to UPDATED_DT_ENTREGA_PROPOSTA
        defaultConcursoShouldNotBeFound("dtEntregaProposta.equals=" + UPDATED_DT_ENTREGA_PROPOSTA);
    }

    @Test
    @Transactional
    public void getAllConcursosByDtEntregaPropostaIsInShouldWork() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtEntregaProposta in DEFAULT_DT_ENTREGA_PROPOSTA or UPDATED_DT_ENTREGA_PROPOSTA
        defaultConcursoShouldBeFound("dtEntregaProposta.in=" + DEFAULT_DT_ENTREGA_PROPOSTA + "," + UPDATED_DT_ENTREGA_PROPOSTA);

        // Get all the concursoList where dtEntregaProposta equals to UPDATED_DT_ENTREGA_PROPOSTA
        defaultConcursoShouldNotBeFound("dtEntregaProposta.in=" + UPDATED_DT_ENTREGA_PROPOSTA);
    }

    @Test
    @Transactional
    public void getAllConcursosByDtEntregaPropostaIsNullOrNotNull() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtEntregaProposta is not null
        defaultConcursoShouldBeFound("dtEntregaProposta.specified=true");

        // Get all the concursoList where dtEntregaProposta is null
        defaultConcursoShouldNotBeFound("dtEntregaProposta.specified=false");
    }

    @Test
    @Transactional
    public void getAllConcursosByDtEntregaPropostaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtEntregaProposta greater than or equals to DEFAULT_DT_ENTREGA_PROPOSTA
        defaultConcursoShouldBeFound("dtEntregaProposta.greaterOrEqualThan=" + DEFAULT_DT_ENTREGA_PROPOSTA);

        // Get all the concursoList where dtEntregaProposta greater than or equals to UPDATED_DT_ENTREGA_PROPOSTA
        defaultConcursoShouldNotBeFound("dtEntregaProposta.greaterOrEqualThan=" + UPDATED_DT_ENTREGA_PROPOSTA);
    }

    @Test
    @Transactional
    public void getAllConcursosByDtEntregaPropostaIsLessThanSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtEntregaProposta less than or equals to DEFAULT_DT_ENTREGA_PROPOSTA
        defaultConcursoShouldNotBeFound("dtEntregaProposta.lessThan=" + DEFAULT_DT_ENTREGA_PROPOSTA);

        // Get all the concursoList where dtEntregaProposta less than or equals to UPDATED_DT_ENTREGA_PROPOSTA
        defaultConcursoShouldBeFound("dtEntregaProposta.lessThan=" + UPDATED_DT_ENTREGA_PROPOSTA);
    }


    @Test
    @Transactional
    public void getAllConcursosByDtAberturaPropostaIsEqualToSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtAberturaProposta equals to DEFAULT_DT_ABERTURA_PROPOSTA
        defaultConcursoShouldBeFound("dtAberturaProposta.equals=" + DEFAULT_DT_ABERTURA_PROPOSTA);

        // Get all the concursoList where dtAberturaProposta equals to UPDATED_DT_ABERTURA_PROPOSTA
        defaultConcursoShouldNotBeFound("dtAberturaProposta.equals=" + UPDATED_DT_ABERTURA_PROPOSTA);
    }

    @Test
    @Transactional
    public void getAllConcursosByDtAberturaPropostaIsInShouldWork() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtAberturaProposta in DEFAULT_DT_ABERTURA_PROPOSTA or UPDATED_DT_ABERTURA_PROPOSTA
        defaultConcursoShouldBeFound("dtAberturaProposta.in=" + DEFAULT_DT_ABERTURA_PROPOSTA + "," + UPDATED_DT_ABERTURA_PROPOSTA);

        // Get all the concursoList where dtAberturaProposta equals to UPDATED_DT_ABERTURA_PROPOSTA
        defaultConcursoShouldNotBeFound("dtAberturaProposta.in=" + UPDATED_DT_ABERTURA_PROPOSTA);
    }

    @Test
    @Transactional
    public void getAllConcursosByDtAberturaPropostaIsNullOrNotNull() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtAberturaProposta is not null
        defaultConcursoShouldBeFound("dtAberturaProposta.specified=true");

        // Get all the concursoList where dtAberturaProposta is null
        defaultConcursoShouldNotBeFound("dtAberturaProposta.specified=false");
    }

    @Test
    @Transactional
    public void getAllConcursosByDtAberturaPropostaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtAberturaProposta greater than or equals to DEFAULT_DT_ABERTURA_PROPOSTA
        defaultConcursoShouldBeFound("dtAberturaProposta.greaterOrEqualThan=" + DEFAULT_DT_ABERTURA_PROPOSTA);

        // Get all the concursoList where dtAberturaProposta greater than or equals to UPDATED_DT_ABERTURA_PROPOSTA
        defaultConcursoShouldNotBeFound("dtAberturaProposta.greaterOrEqualThan=" + UPDATED_DT_ABERTURA_PROPOSTA);
    }

    @Test
    @Transactional
    public void getAllConcursosByDtAberturaPropostaIsLessThanSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtAberturaProposta less than or equals to DEFAULT_DT_ABERTURA_PROPOSTA
        defaultConcursoShouldNotBeFound("dtAberturaProposta.lessThan=" + DEFAULT_DT_ABERTURA_PROPOSTA);

        // Get all the concursoList where dtAberturaProposta less than or equals to UPDATED_DT_ABERTURA_PROPOSTA
        defaultConcursoShouldBeFound("dtAberturaProposta.lessThan=" + UPDATED_DT_ABERTURA_PROPOSTA);
    }


    @Test
    @Transactional
    public void getAllConcursosByDtConclusaoAvaliacaoRelPrelIsEqualToSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtConclusaoAvaliacaoRelPrel equals to DEFAULT_DT_CONCLUSAO_AVALIACAO_REL_PREL
        defaultConcursoShouldBeFound("dtConclusaoAvaliacaoRelPrel.equals=" + DEFAULT_DT_CONCLUSAO_AVALIACAO_REL_PREL);

        // Get all the concursoList where dtConclusaoAvaliacaoRelPrel equals to UPDATED_DT_CONCLUSAO_AVALIACAO_REL_PREL
        defaultConcursoShouldNotBeFound("dtConclusaoAvaliacaoRelPrel.equals=" + UPDATED_DT_CONCLUSAO_AVALIACAO_REL_PREL);
    }

    @Test
    @Transactional
    public void getAllConcursosByDtConclusaoAvaliacaoRelPrelIsInShouldWork() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtConclusaoAvaliacaoRelPrel in DEFAULT_DT_CONCLUSAO_AVALIACAO_REL_PREL or UPDATED_DT_CONCLUSAO_AVALIACAO_REL_PREL
        defaultConcursoShouldBeFound("dtConclusaoAvaliacaoRelPrel.in=" + DEFAULT_DT_CONCLUSAO_AVALIACAO_REL_PREL + "," + UPDATED_DT_CONCLUSAO_AVALIACAO_REL_PREL);

        // Get all the concursoList where dtConclusaoAvaliacaoRelPrel equals to UPDATED_DT_CONCLUSAO_AVALIACAO_REL_PREL
        defaultConcursoShouldNotBeFound("dtConclusaoAvaliacaoRelPrel.in=" + UPDATED_DT_CONCLUSAO_AVALIACAO_REL_PREL);
    }

    @Test
    @Transactional
    public void getAllConcursosByDtConclusaoAvaliacaoRelPrelIsNullOrNotNull() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtConclusaoAvaliacaoRelPrel is not null
        defaultConcursoShouldBeFound("dtConclusaoAvaliacaoRelPrel.specified=true");

        // Get all the concursoList where dtConclusaoAvaliacaoRelPrel is null
        defaultConcursoShouldNotBeFound("dtConclusaoAvaliacaoRelPrel.specified=false");
    }

    @Test
    @Transactional
    public void getAllConcursosByDtConclusaoAvaliacaoRelPrelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtConclusaoAvaliacaoRelPrel greater than or equals to DEFAULT_DT_CONCLUSAO_AVALIACAO_REL_PREL
        defaultConcursoShouldBeFound("dtConclusaoAvaliacaoRelPrel.greaterOrEqualThan=" + DEFAULT_DT_CONCLUSAO_AVALIACAO_REL_PREL);

        // Get all the concursoList where dtConclusaoAvaliacaoRelPrel greater than or equals to UPDATED_DT_CONCLUSAO_AVALIACAO_REL_PREL
        defaultConcursoShouldNotBeFound("dtConclusaoAvaliacaoRelPrel.greaterOrEqualThan=" + UPDATED_DT_CONCLUSAO_AVALIACAO_REL_PREL);
    }

    @Test
    @Transactional
    public void getAllConcursosByDtConclusaoAvaliacaoRelPrelIsLessThanSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtConclusaoAvaliacaoRelPrel less than or equals to DEFAULT_DT_CONCLUSAO_AVALIACAO_REL_PREL
        defaultConcursoShouldNotBeFound("dtConclusaoAvaliacaoRelPrel.lessThan=" + DEFAULT_DT_CONCLUSAO_AVALIACAO_REL_PREL);

        // Get all the concursoList where dtConclusaoAvaliacaoRelPrel less than or equals to UPDATED_DT_CONCLUSAO_AVALIACAO_REL_PREL
        defaultConcursoShouldBeFound("dtConclusaoAvaliacaoRelPrel.lessThan=" + UPDATED_DT_CONCLUSAO_AVALIACAO_REL_PREL);
    }


    @Test
    @Transactional
    public void getAllConcursosByDtNegociacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtNegociacao equals to DEFAULT_DT_NEGOCIACAO
        defaultConcursoShouldBeFound("dtNegociacao.equals=" + DEFAULT_DT_NEGOCIACAO);

        // Get all the concursoList where dtNegociacao equals to UPDATED_DT_NEGOCIACAO
        defaultConcursoShouldNotBeFound("dtNegociacao.equals=" + UPDATED_DT_NEGOCIACAO);
    }

    @Test
    @Transactional
    public void getAllConcursosByDtNegociacaoIsInShouldWork() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtNegociacao in DEFAULT_DT_NEGOCIACAO or UPDATED_DT_NEGOCIACAO
        defaultConcursoShouldBeFound("dtNegociacao.in=" + DEFAULT_DT_NEGOCIACAO + "," + UPDATED_DT_NEGOCIACAO);

        // Get all the concursoList where dtNegociacao equals to UPDATED_DT_NEGOCIACAO
        defaultConcursoShouldNotBeFound("dtNegociacao.in=" + UPDATED_DT_NEGOCIACAO);
    }

    @Test
    @Transactional
    public void getAllConcursosByDtNegociacaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtNegociacao is not null
        defaultConcursoShouldBeFound("dtNegociacao.specified=true");

        // Get all the concursoList where dtNegociacao is null
        defaultConcursoShouldNotBeFound("dtNegociacao.specified=false");
    }

    @Test
    @Transactional
    public void getAllConcursosByDtNegociacaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtNegociacao greater than or equals to DEFAULT_DT_NEGOCIACAO
        defaultConcursoShouldBeFound("dtNegociacao.greaterOrEqualThan=" + DEFAULT_DT_NEGOCIACAO);

        // Get all the concursoList where dtNegociacao greater than or equals to UPDATED_DT_NEGOCIACAO
        defaultConcursoShouldNotBeFound("dtNegociacao.greaterOrEqualThan=" + UPDATED_DT_NEGOCIACAO);
    }

    @Test
    @Transactional
    public void getAllConcursosByDtNegociacaoIsLessThanSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtNegociacao less than or equals to DEFAULT_DT_NEGOCIACAO
        defaultConcursoShouldNotBeFound("dtNegociacao.lessThan=" + DEFAULT_DT_NEGOCIACAO);

        // Get all the concursoList where dtNegociacao less than or equals to UPDATED_DT_NEGOCIACAO
        defaultConcursoShouldBeFound("dtNegociacao.lessThan=" + UPDATED_DT_NEGOCIACAO);
    }


    @Test
    @Transactional
    public void getAllConcursosByDtAprovRelAvalFinalIsEqualToSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtAprovRelAvalFinal equals to DEFAULT_DT_APROV_REL_AVAL_FINAL
        defaultConcursoShouldBeFound("dtAprovRelAvalFinal.equals=" + DEFAULT_DT_APROV_REL_AVAL_FINAL);

        // Get all the concursoList where dtAprovRelAvalFinal equals to UPDATED_DT_APROV_REL_AVAL_FINAL
        defaultConcursoShouldNotBeFound("dtAprovRelAvalFinal.equals=" + UPDATED_DT_APROV_REL_AVAL_FINAL);
    }

    @Test
    @Transactional
    public void getAllConcursosByDtAprovRelAvalFinalIsInShouldWork() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtAprovRelAvalFinal in DEFAULT_DT_APROV_REL_AVAL_FINAL or UPDATED_DT_APROV_REL_AVAL_FINAL
        defaultConcursoShouldBeFound("dtAprovRelAvalFinal.in=" + DEFAULT_DT_APROV_REL_AVAL_FINAL + "," + UPDATED_DT_APROV_REL_AVAL_FINAL);

        // Get all the concursoList where dtAprovRelAvalFinal equals to UPDATED_DT_APROV_REL_AVAL_FINAL
        defaultConcursoShouldNotBeFound("dtAprovRelAvalFinal.in=" + UPDATED_DT_APROV_REL_AVAL_FINAL);
    }

    @Test
    @Transactional
    public void getAllConcursosByDtAprovRelAvalFinalIsNullOrNotNull() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtAprovRelAvalFinal is not null
        defaultConcursoShouldBeFound("dtAprovRelAvalFinal.specified=true");

        // Get all the concursoList where dtAprovRelAvalFinal is null
        defaultConcursoShouldNotBeFound("dtAprovRelAvalFinal.specified=false");
    }

    @Test
    @Transactional
    public void getAllConcursosByDtAprovRelAvalFinalIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtAprovRelAvalFinal greater than or equals to DEFAULT_DT_APROV_REL_AVAL_FINAL
        defaultConcursoShouldBeFound("dtAprovRelAvalFinal.greaterOrEqualThan=" + DEFAULT_DT_APROV_REL_AVAL_FINAL);

        // Get all the concursoList where dtAprovRelAvalFinal greater than or equals to UPDATED_DT_APROV_REL_AVAL_FINAL
        defaultConcursoShouldNotBeFound("dtAprovRelAvalFinal.greaterOrEqualThan=" + UPDATED_DT_APROV_REL_AVAL_FINAL);
    }

    @Test
    @Transactional
    public void getAllConcursosByDtAprovRelAvalFinalIsLessThanSomething() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);

        // Get all the concursoList where dtAprovRelAvalFinal less than or equals to DEFAULT_DT_APROV_REL_AVAL_FINAL
        defaultConcursoShouldNotBeFound("dtAprovRelAvalFinal.lessThan=" + DEFAULT_DT_APROV_REL_AVAL_FINAL);

        // Get all the concursoList where dtAprovRelAvalFinal less than or equals to UPDATED_DT_APROV_REL_AVAL_FINAL
        defaultConcursoShouldBeFound("dtAprovRelAvalFinal.lessThan=" + UPDATED_DT_APROV_REL_AVAL_FINAL);
    }


    @Test
    @Transactional
    public void getAllConcursosByIdProgramasProjectosIsEqualToSomething() throws Exception {
        // Initialize the database
        ProgramasProjectos idProgramasProjectos = ProgramasProjectosResourceIntTest.createEntity(em);
        em.persist(idProgramasProjectos);
        em.flush();
        concurso.setProgramasProjectos(idProgramasProjectos);
        concursoRepository.saveAndFlush(concurso);
        Long idProgramasProjectosId = idProgramasProjectos.getId();

        // Get all the concursoList where idProgramasProjectos equals to idProgramasProjectosId
        defaultConcursoShouldBeFound("idProgramasProjectosId.equals=" + idProgramasProjectosId);

        // Get all the concursoList where idProgramasProjectos equals to idProgramasProjectosId + 1
        defaultConcursoShouldNotBeFound("idProgramasProjectosId.equals=" + (idProgramasProjectosId + 1));
    }


    @Test
    @Transactional
    public void getAllConcursosByIdSistemaAguaIsEqualToSomething() throws Exception {
        // Initialize the database
        SistemaAgua idSistemaAgua = SistemaAguaResourceIntTest.createEntity(em);
        em.persist(idSistemaAgua);
        em.flush();
        concurso.setIdSistemaAgua(idSistemaAgua);
        concursoRepository.saveAndFlush(concurso);
        Long idSistemaAguaId = idSistemaAgua.getId();

        // Get all the concursoList where idSistemaAgua equals to idSistemaAguaId
        defaultConcursoShouldBeFound("idSistemaAguaId.equals=" + idSistemaAguaId);

        // Get all the concursoList where idSistemaAgua equals to idSistemaAguaId + 1
        defaultConcursoShouldNotBeFound("idSistemaAguaId.equals=" + (idSistemaAguaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultConcursoShouldBeFound(String filter) throws Exception {
        restConcursoMockMvc.perform(get("/api/concursos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(concurso.getId().intValue())))
            .andExpect(jsonPath("$.[*].idConcurso").value(hasItem(DEFAULT_ID_CONCURSO.intValue())))
            .andExpect(jsonPath("$.[*].tipoConcurso").value(hasItem(DEFAULT_TIPO_CONCURSO.toString())))
            .andExpect(jsonPath("$.[*].dtLancamento").value(hasItem(DEFAULT_DT_LANCAMENTO.toString())))
            .andExpect(jsonPath("$.[*].dtUltimaAlteracao").value(hasItem(DEFAULT_DT_ULTIMA_ALTERACAO.toString())))
            .andExpect(jsonPath("$.[*].dtEntregaProposta").value(hasItem(DEFAULT_DT_ENTREGA_PROPOSTA.toString())))
            .andExpect(jsonPath("$.[*].dtAberturaProposta").value(hasItem(DEFAULT_DT_ABERTURA_PROPOSTA.toString())))
            .andExpect(jsonPath("$.[*].dtConclusaoAvaliacaoRelPrel").value(hasItem(DEFAULT_DT_CONCLUSAO_AVALIACAO_REL_PREL.toString())))
            .andExpect(jsonPath("$.[*].dtNegociacao").value(hasItem(DEFAULT_DT_NEGOCIACAO.toString())))
            .andExpect(jsonPath("$.[*].dtAprovRelAvalFinal").value(hasItem(DEFAULT_DT_APROV_REL_AVAL_FINAL.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultConcursoShouldNotBeFound(String filter) throws Exception {
        restConcursoMockMvc.perform(get("/api/concursos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingConcurso() throws Exception {
        // Get the concurso
        restConcursoMockMvc.perform(get("/api/concursos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConcurso() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);
        int databaseSizeBeforeUpdate = concursoRepository.findAll().size();

        // Update the concurso
        Concurso updatedConcurso = concursoRepository.findOne(concurso.getId());
        // Disconnect from session so that the updates on updatedConcurso are not directly saved in db
        em.detach(updatedConcurso);
        updatedConcurso
            .tipoConcurso(UPDATED_TIPO_CONCURSO)
            .dtLancamento(UPDATED_DT_LANCAMENTO)
            .dtUltimaAlteracao(UPDATED_DT_ULTIMA_ALTERACAO)
            .dtEntregaProposta(UPDATED_DT_ENTREGA_PROPOSTA)
            .dtAberturaProposta(UPDATED_DT_ABERTURA_PROPOSTA)
            .dtConclusaoAvaliacaoRelPrel(UPDATED_DT_CONCLUSAO_AVALIACAO_REL_PREL)
            .dtNegociacao(UPDATED_DT_NEGOCIACAO)
            .dtAprovRelAvalFinal(UPDATED_DT_APROV_REL_AVAL_FINAL);
        ConcursoDTO concursoDTO = concursoMapper.toDto(updatedConcurso);

        restConcursoMockMvc.perform(put("/api/concursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(concursoDTO)))
            .andExpect(status().isOk());

        // Validate the Concurso in the database
        List<Concurso> concursoList = concursoRepository.findAll();
        assertThat(concursoList).hasSize(databaseSizeBeforeUpdate);
        Concurso testConcurso = concursoList.get(concursoList.size() - 1);
        assertThat(testConcurso.getTipoConcurso()).isEqualTo(UPDATED_TIPO_CONCURSO);
        assertThat(testConcurso.getDtLancamento()).isEqualTo(UPDATED_DT_LANCAMENTO);
        assertThat(testConcurso.getDtUltimaAlteracao()).isEqualTo(UPDATED_DT_ULTIMA_ALTERACAO);
        assertThat(testConcurso.getDtEntregaProposta()).isEqualTo(UPDATED_DT_ENTREGA_PROPOSTA);
        assertThat(testConcurso.getDtAberturaProposta()).isEqualTo(UPDATED_DT_ABERTURA_PROPOSTA);
        assertThat(testConcurso.getDtConclusaoAvaliacaoRelPrel()).isEqualTo(UPDATED_DT_CONCLUSAO_AVALIACAO_REL_PREL);
        assertThat(testConcurso.getDtNegociacao()).isEqualTo(UPDATED_DT_NEGOCIACAO);
        assertThat(testConcurso.getDtAprovRelAvalFinal()).isEqualTo(UPDATED_DT_APROV_REL_AVAL_FINAL);
    }

    @Test
    @Transactional
    public void updateNonExistingConcurso() throws Exception {
        int databaseSizeBeforeUpdate = concursoRepository.findAll().size();

        // Create the Concurso
        ConcursoDTO concursoDTO = concursoMapper.toDto(concurso);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restConcursoMockMvc.perform(put("/api/concursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(concursoDTO)))
            .andExpect(status().isCreated());

        // Validate the Concurso in the database
        List<Concurso> concursoList = concursoRepository.findAll();
        assertThat(concursoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteConcurso() throws Exception {
        // Initialize the database
        concursoRepository.saveAndFlush(concurso);
        int databaseSizeBeforeDelete = concursoRepository.findAll().size();

        // Get the concurso
        restConcursoMockMvc.perform(delete("/api/concursos/{id}", concurso.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Concurso> concursoList = concursoRepository.findAll();
        assertThat(concursoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Concurso.class);
        Concurso concurso1 = new Concurso();
        concurso1.setId(1L);
        Concurso concurso2 = new Concurso();
        concurso2.setId(concurso1.getId());
        assertThat(concurso1).isEqualTo(concurso2);
        concurso2.setId(2L);
        assertThat(concurso1).isNotEqualTo(concurso2);
        concurso1.setId(null);
        assertThat(concurso1).isNotEqualTo(concurso2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConcursoDTO.class);
        ConcursoDTO concursoDTO1 = new ConcursoDTO();
        concursoDTO1.setId(1L);
        ConcursoDTO concursoDTO2 = new ConcursoDTO();
        assertThat(concursoDTO1).isNotEqualTo(concursoDTO2);
        concursoDTO2.setId(concursoDTO1.getId());
        assertThat(concursoDTO1).isEqualTo(concursoDTO2);
        concursoDTO2.setId(2L);
        assertThat(concursoDTO1).isNotEqualTo(concursoDTO2);
        concursoDTO1.setId(null);
        assertThat(concursoDTO1).isNotEqualTo(concursoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(concursoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(concursoMapper.fromId(null)).isNull();
    }
}
