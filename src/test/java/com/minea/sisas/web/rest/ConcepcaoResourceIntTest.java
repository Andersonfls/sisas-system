package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.Concepcao;
import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.repository.ConcepcaoRepository;
import com.minea.sisas.service.ConcepcaoService;
import com.minea.sisas.service.dto.ConcepcaoDTO;
import com.minea.sisas.service.mapper.ConcepcaoMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.ConcepcaoQueryService;

import com.minea.sisas.web.rest.ConcepcaoResource;
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
 * Test class for the ConcepcaoResource REST controller.
 *
 * @see ConcepcaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class ConcepcaoResourceIntTest {

    private static final Long DEFAULT_ID_CONCEPCAO = 1L;
    private static final Long UPDATED_ID_CONCEPCAO = 2L;

    private static final String DEFAULT_TIPO_CONCURSO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_CONCURSO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DT_LANCAMENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_LANCAMENTO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_ULTIMA_ALTERACAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_ULTIMA_ALTERACAO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_ELABORACAO_CON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_ELABORACAO_CON = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_APROVACAO_CON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_APROVACAO_CON = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private ConcepcaoRepository concepcaoRepository;

    @Autowired
    private ConcepcaoMapper concepcaoMapper;

    @Autowired
    private ConcepcaoService concepcaoService;

    @Autowired
    private ConcepcaoQueryService concepcaoQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConcepcaoMockMvc;

    private Concepcao concepcao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConcepcaoResource concepcaoResource = new ConcepcaoResource(concepcaoService, concepcaoQueryService);
        this.restConcepcaoMockMvc = MockMvcBuilders.standaloneSetup(concepcaoResource)
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
    public static Concepcao createEntity(EntityManager em) {
        Concepcao concepcao = new Concepcao()
            .tipoConcurso(DEFAULT_TIPO_CONCURSO)
            .dtLancamento(DEFAULT_DT_LANCAMENTO)
            .dtUltimaAlteracao(DEFAULT_DT_ULTIMA_ALTERACAO)
            .dtElaboracaoCon(DEFAULT_DT_ELABORACAO_CON)
            .dtAprovacaoCon(DEFAULT_DT_APROVACAO_CON);
        // Add required entity
        ProgramasProjectos idProgramasProjectos = ProgramasProjectosResourceIntTest.createEntity(em);
        em.persist(idProgramasProjectos);
        em.flush();
        concepcao.setIdProgramasProjectos(idProgramasProjectos);
        return concepcao;
    }

    @Before
    public void initTest() {
        concepcao = createEntity(em);
    }

    @Test
    @Transactional
    public void createConcepcao() throws Exception {
        int databaseSizeBeforeCreate = concepcaoRepository.findAll().size();

        // Create the Concepcao
        ConcepcaoDTO concepcaoDTO = concepcaoMapper.toDto(concepcao);
        restConcepcaoMockMvc.perform(post("/api/concepcaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(concepcaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Concepcao in the database
        List<Concepcao> concepcaoList = concepcaoRepository.findAll();
        assertThat(concepcaoList).hasSize(databaseSizeBeforeCreate + 1);
        Concepcao testConcepcao = concepcaoList.get(concepcaoList.size() - 1);
        assertThat(testConcepcao.getTipoConcurso()).isEqualTo(DEFAULT_TIPO_CONCURSO);
        assertThat(testConcepcao.getDtLancamento()).isEqualTo(DEFAULT_DT_LANCAMENTO);
        assertThat(testConcepcao.getDtUltimaAlteracao()).isEqualTo(DEFAULT_DT_ULTIMA_ALTERACAO);
        assertThat(testConcepcao.getDtElaboracaoCon()).isEqualTo(DEFAULT_DT_ELABORACAO_CON);
        assertThat(testConcepcao.getDtAprovacaoCon()).isEqualTo(DEFAULT_DT_APROVACAO_CON);
    }

    @Test
    @Transactional
    public void createConcepcaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = concepcaoRepository.findAll().size();

        // Create the Concepcao with an existing ID
        concepcao.setId(1L);
        ConcepcaoDTO concepcaoDTO = concepcaoMapper.toDto(concepcao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConcepcaoMockMvc.perform(post("/api/concepcaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(concepcaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Concepcao in the database
        List<Concepcao> concepcaoList = concepcaoRepository.findAll();
        assertThat(concepcaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdConcepcaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = concepcaoRepository.findAll().size();
        // set the field null

        // Create the Concepcao, which fails.
        ConcepcaoDTO concepcaoDTO = concepcaoMapper.toDto(concepcao);

        restConcepcaoMockMvc.perform(post("/api/concepcaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(concepcaoDTO)))
            .andExpect(status().isBadRequest());

        List<Concepcao> concepcaoList = concepcaoRepository.findAll();
        assertThat(concepcaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTipoConcursoIsRequired() throws Exception {
        int databaseSizeBeforeTest = concepcaoRepository.findAll().size();
        // set the field null
        concepcao.setTipoConcurso(null);

        // Create the Concepcao, which fails.
        ConcepcaoDTO concepcaoDTO = concepcaoMapper.toDto(concepcao);

        restConcepcaoMockMvc.perform(post("/api/concepcaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(concepcaoDTO)))
            .andExpect(status().isBadRequest());

        List<Concepcao> concepcaoList = concepcaoRepository.findAll();
        assertThat(concepcaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDtLancamentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = concepcaoRepository.findAll().size();
        // set the field null
        concepcao.setDtLancamento(null);

        // Create the Concepcao, which fails.
        ConcepcaoDTO concepcaoDTO = concepcaoMapper.toDto(concepcao);

        restConcepcaoMockMvc.perform(post("/api/concepcaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(concepcaoDTO)))
            .andExpect(status().isBadRequest());

        List<Concepcao> concepcaoList = concepcaoRepository.findAll();
        assertThat(concepcaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllConcepcaos() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList
        restConcepcaoMockMvc.perform(get("/api/concepcaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(concepcao.getId().intValue())))
            .andExpect(jsonPath("$.[*].idConcepcao").value(hasItem(DEFAULT_ID_CONCEPCAO.intValue())))
            .andExpect(jsonPath("$.[*].tipoConcurso").value(hasItem(DEFAULT_TIPO_CONCURSO.toString())))
            .andExpect(jsonPath("$.[*].dtLancamento").value(hasItem(DEFAULT_DT_LANCAMENTO.toString())))
            .andExpect(jsonPath("$.[*].dtUltimaAlteracao").value(hasItem(DEFAULT_DT_ULTIMA_ALTERACAO.toString())))
            .andExpect(jsonPath("$.[*].dtElaboracaoCon").value(hasItem(DEFAULT_DT_ELABORACAO_CON.toString())))
            .andExpect(jsonPath("$.[*].dtAprovacaoCon").value(hasItem(DEFAULT_DT_APROVACAO_CON.toString())));
    }

    @Test
    @Transactional
    public void getConcepcao() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get the concepcao
        restConcepcaoMockMvc.perform(get("/api/concepcaos/{id}", concepcao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(concepcao.getId().intValue()))
            .andExpect(jsonPath("$.idConcepcao").value(DEFAULT_ID_CONCEPCAO.intValue()))
            .andExpect(jsonPath("$.tipoConcurso").value(DEFAULT_TIPO_CONCURSO.toString()))
            .andExpect(jsonPath("$.dtLancamento").value(DEFAULT_DT_LANCAMENTO.toString()))
            .andExpect(jsonPath("$.dtUltimaAlteracao").value(DEFAULT_DT_ULTIMA_ALTERACAO.toString()))
            .andExpect(jsonPath("$.dtElaboracaoCon").value(DEFAULT_DT_ELABORACAO_CON.toString()))
            .andExpect(jsonPath("$.dtAprovacaoCon").value(DEFAULT_DT_APROVACAO_CON.toString()));
    }

    @Test
    @Transactional
    public void getAllConcepcaosByIdConcepcaoIsEqualToSomething() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where idConcepcao equals to DEFAULT_ID_CONCEPCAO
        defaultConcepcaoShouldBeFound("idConcepcao.equals=" + DEFAULT_ID_CONCEPCAO);

        // Get all the concepcaoList where idConcepcao equals to UPDATED_ID_CONCEPCAO
        defaultConcepcaoShouldNotBeFound("idConcepcao.equals=" + UPDATED_ID_CONCEPCAO);
    }

    @Test
    @Transactional
    public void getAllConcepcaosByIdConcepcaoIsInShouldWork() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where idConcepcao in DEFAULT_ID_CONCEPCAO or UPDATED_ID_CONCEPCAO
        defaultConcepcaoShouldBeFound("idConcepcao.in=" + DEFAULT_ID_CONCEPCAO + "," + UPDATED_ID_CONCEPCAO);

        // Get all the concepcaoList where idConcepcao equals to UPDATED_ID_CONCEPCAO
        defaultConcepcaoShouldNotBeFound("idConcepcao.in=" + UPDATED_ID_CONCEPCAO);
    }

    @Test
    @Transactional
    public void getAllConcepcaosByIdConcepcaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where idConcepcao is not null
        defaultConcepcaoShouldBeFound("idConcepcao.specified=true");

        // Get all the concepcaoList where idConcepcao is null
        defaultConcepcaoShouldNotBeFound("idConcepcao.specified=false");
    }

    @Test
    @Transactional
    public void getAllConcepcaosByIdConcepcaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where idConcepcao greater than or equals to DEFAULT_ID_CONCEPCAO
        defaultConcepcaoShouldBeFound("idConcepcao.greaterOrEqualThan=" + DEFAULT_ID_CONCEPCAO);

        // Get all the concepcaoList where idConcepcao greater than or equals to UPDATED_ID_CONCEPCAO
        defaultConcepcaoShouldNotBeFound("idConcepcao.greaterOrEqualThan=" + UPDATED_ID_CONCEPCAO);
    }

    @Test
    @Transactional
    public void getAllConcepcaosByIdConcepcaoIsLessThanSomething() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where idConcepcao less than or equals to DEFAULT_ID_CONCEPCAO
        defaultConcepcaoShouldNotBeFound("idConcepcao.lessThan=" + DEFAULT_ID_CONCEPCAO);

        // Get all the concepcaoList where idConcepcao less than or equals to UPDATED_ID_CONCEPCAO
        defaultConcepcaoShouldBeFound("idConcepcao.lessThan=" + UPDATED_ID_CONCEPCAO);
    }


    @Test
    @Transactional
    public void getAllConcepcaosByTipoConcursoIsEqualToSomething() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where tipoConcurso equals to DEFAULT_TIPO_CONCURSO
        defaultConcepcaoShouldBeFound("tipoConcurso.equals=" + DEFAULT_TIPO_CONCURSO);

        // Get all the concepcaoList where tipoConcurso equals to UPDATED_TIPO_CONCURSO
        defaultConcepcaoShouldNotBeFound("tipoConcurso.equals=" + UPDATED_TIPO_CONCURSO);
    }

    @Test
    @Transactional
    public void getAllConcepcaosByTipoConcursoIsInShouldWork() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where tipoConcurso in DEFAULT_TIPO_CONCURSO or UPDATED_TIPO_CONCURSO
        defaultConcepcaoShouldBeFound("tipoConcurso.in=" + DEFAULT_TIPO_CONCURSO + "," + UPDATED_TIPO_CONCURSO);

        // Get all the concepcaoList where tipoConcurso equals to UPDATED_TIPO_CONCURSO
        defaultConcepcaoShouldNotBeFound("tipoConcurso.in=" + UPDATED_TIPO_CONCURSO);
    }

    @Test
    @Transactional
    public void getAllConcepcaosByTipoConcursoIsNullOrNotNull() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where tipoConcurso is not null
        defaultConcepcaoShouldBeFound("tipoConcurso.specified=true");

        // Get all the concepcaoList where tipoConcurso is null
        defaultConcepcaoShouldNotBeFound("tipoConcurso.specified=false");
    }

    @Test
    @Transactional
    public void getAllConcepcaosByDtLancamentoIsEqualToSomething() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where dtLancamento equals to DEFAULT_DT_LANCAMENTO
        defaultConcepcaoShouldBeFound("dtLancamento.equals=" + DEFAULT_DT_LANCAMENTO);

        // Get all the concepcaoList where dtLancamento equals to UPDATED_DT_LANCAMENTO
        defaultConcepcaoShouldNotBeFound("dtLancamento.equals=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllConcepcaosByDtLancamentoIsInShouldWork() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where dtLancamento in DEFAULT_DT_LANCAMENTO or UPDATED_DT_LANCAMENTO
        defaultConcepcaoShouldBeFound("dtLancamento.in=" + DEFAULT_DT_LANCAMENTO + "," + UPDATED_DT_LANCAMENTO);

        // Get all the concepcaoList where dtLancamento equals to UPDATED_DT_LANCAMENTO
        defaultConcepcaoShouldNotBeFound("dtLancamento.in=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllConcepcaosByDtLancamentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where dtLancamento is not null
        defaultConcepcaoShouldBeFound("dtLancamento.specified=true");

        // Get all the concepcaoList where dtLancamento is null
        defaultConcepcaoShouldNotBeFound("dtLancamento.specified=false");
    }

    @Test
    @Transactional
    public void getAllConcepcaosByDtLancamentoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where dtLancamento greater than or equals to DEFAULT_DT_LANCAMENTO
        defaultConcepcaoShouldBeFound("dtLancamento.greaterOrEqualThan=" + DEFAULT_DT_LANCAMENTO);

        // Get all the concepcaoList where dtLancamento greater than or equals to UPDATED_DT_LANCAMENTO
        defaultConcepcaoShouldNotBeFound("dtLancamento.greaterOrEqualThan=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllConcepcaosByDtLancamentoIsLessThanSomething() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where dtLancamento less than or equals to DEFAULT_DT_LANCAMENTO
        defaultConcepcaoShouldNotBeFound("dtLancamento.lessThan=" + DEFAULT_DT_LANCAMENTO);

        // Get all the concepcaoList where dtLancamento less than or equals to UPDATED_DT_LANCAMENTO
        defaultConcepcaoShouldBeFound("dtLancamento.lessThan=" + UPDATED_DT_LANCAMENTO);
    }


    @Test
    @Transactional
    public void getAllConcepcaosByDtUltimaAlteracaoIsEqualToSomething() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where dtUltimaAlteracao equals to DEFAULT_DT_ULTIMA_ALTERACAO
        defaultConcepcaoShouldBeFound("dtUltimaAlteracao.equals=" + DEFAULT_DT_ULTIMA_ALTERACAO);

        // Get all the concepcaoList where dtUltimaAlteracao equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultConcepcaoShouldNotBeFound("dtUltimaAlteracao.equals=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }

    @Test
    @Transactional
    public void getAllConcepcaosByDtUltimaAlteracaoIsInShouldWork() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where dtUltimaAlteracao in DEFAULT_DT_ULTIMA_ALTERACAO or UPDATED_DT_ULTIMA_ALTERACAO
        defaultConcepcaoShouldBeFound("dtUltimaAlteracao.in=" + DEFAULT_DT_ULTIMA_ALTERACAO + "," + UPDATED_DT_ULTIMA_ALTERACAO);

        // Get all the concepcaoList where dtUltimaAlteracao equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultConcepcaoShouldNotBeFound("dtUltimaAlteracao.in=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }

    @Test
    @Transactional
    public void getAllConcepcaosByDtUltimaAlteracaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where dtUltimaAlteracao is not null
        defaultConcepcaoShouldBeFound("dtUltimaAlteracao.specified=true");

        // Get all the concepcaoList where dtUltimaAlteracao is null
        defaultConcepcaoShouldNotBeFound("dtUltimaAlteracao.specified=false");
    }

    @Test
    @Transactional
    public void getAllConcepcaosByDtUltimaAlteracaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where dtUltimaAlteracao greater than or equals to DEFAULT_DT_ULTIMA_ALTERACAO
        defaultConcepcaoShouldBeFound("dtUltimaAlteracao.greaterOrEqualThan=" + DEFAULT_DT_ULTIMA_ALTERACAO);

        // Get all the concepcaoList where dtUltimaAlteracao greater than or equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultConcepcaoShouldNotBeFound("dtUltimaAlteracao.greaterOrEqualThan=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }

    @Test
    @Transactional
    public void getAllConcepcaosByDtUltimaAlteracaoIsLessThanSomething() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where dtUltimaAlteracao less than or equals to DEFAULT_DT_ULTIMA_ALTERACAO
        defaultConcepcaoShouldNotBeFound("dtUltimaAlteracao.lessThan=" + DEFAULT_DT_ULTIMA_ALTERACAO);

        // Get all the concepcaoList where dtUltimaAlteracao less than or equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultConcepcaoShouldBeFound("dtUltimaAlteracao.lessThan=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }


    @Test
    @Transactional
    public void getAllConcepcaosByDtElaboracaoConIsEqualToSomething() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where dtElaboracaoCon equals to DEFAULT_DT_ELABORACAO_CON
        defaultConcepcaoShouldBeFound("dtElaboracaoCon.equals=" + DEFAULT_DT_ELABORACAO_CON);

        // Get all the concepcaoList where dtElaboracaoCon equals to UPDATED_DT_ELABORACAO_CON
        defaultConcepcaoShouldNotBeFound("dtElaboracaoCon.equals=" + UPDATED_DT_ELABORACAO_CON);
    }

    @Test
    @Transactional
    public void getAllConcepcaosByDtElaboracaoConIsInShouldWork() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where dtElaboracaoCon in DEFAULT_DT_ELABORACAO_CON or UPDATED_DT_ELABORACAO_CON
        defaultConcepcaoShouldBeFound("dtElaboracaoCon.in=" + DEFAULT_DT_ELABORACAO_CON + "," + UPDATED_DT_ELABORACAO_CON);

        // Get all the concepcaoList where dtElaboracaoCon equals to UPDATED_DT_ELABORACAO_CON
        defaultConcepcaoShouldNotBeFound("dtElaboracaoCon.in=" + UPDATED_DT_ELABORACAO_CON);
    }

    @Test
    @Transactional
    public void getAllConcepcaosByDtElaboracaoConIsNullOrNotNull() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where dtElaboracaoCon is not null
        defaultConcepcaoShouldBeFound("dtElaboracaoCon.specified=true");

        // Get all the concepcaoList where dtElaboracaoCon is null
        defaultConcepcaoShouldNotBeFound("dtElaboracaoCon.specified=false");
    }

    @Test
    @Transactional
    public void getAllConcepcaosByDtElaboracaoConIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where dtElaboracaoCon greater than or equals to DEFAULT_DT_ELABORACAO_CON
        defaultConcepcaoShouldBeFound("dtElaboracaoCon.greaterOrEqualThan=" + DEFAULT_DT_ELABORACAO_CON);

        // Get all the concepcaoList where dtElaboracaoCon greater than or equals to UPDATED_DT_ELABORACAO_CON
        defaultConcepcaoShouldNotBeFound("dtElaboracaoCon.greaterOrEqualThan=" + UPDATED_DT_ELABORACAO_CON);
    }

    @Test
    @Transactional
    public void getAllConcepcaosByDtElaboracaoConIsLessThanSomething() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where dtElaboracaoCon less than or equals to DEFAULT_DT_ELABORACAO_CON
        defaultConcepcaoShouldNotBeFound("dtElaboracaoCon.lessThan=" + DEFAULT_DT_ELABORACAO_CON);

        // Get all the concepcaoList where dtElaboracaoCon less than or equals to UPDATED_DT_ELABORACAO_CON
        defaultConcepcaoShouldBeFound("dtElaboracaoCon.lessThan=" + UPDATED_DT_ELABORACAO_CON);
    }


    @Test
    @Transactional
    public void getAllConcepcaosByDtAprovacaoConIsEqualToSomething() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where dtAprovacaoCon equals to DEFAULT_DT_APROVACAO_CON
        defaultConcepcaoShouldBeFound("dtAprovacaoCon.equals=" + DEFAULT_DT_APROVACAO_CON);

        // Get all the concepcaoList where dtAprovacaoCon equals to UPDATED_DT_APROVACAO_CON
        defaultConcepcaoShouldNotBeFound("dtAprovacaoCon.equals=" + UPDATED_DT_APROVACAO_CON);
    }

    @Test
    @Transactional
    public void getAllConcepcaosByDtAprovacaoConIsInShouldWork() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where dtAprovacaoCon in DEFAULT_DT_APROVACAO_CON or UPDATED_DT_APROVACAO_CON
        defaultConcepcaoShouldBeFound("dtAprovacaoCon.in=" + DEFAULT_DT_APROVACAO_CON + "," + UPDATED_DT_APROVACAO_CON);

        // Get all the concepcaoList where dtAprovacaoCon equals to UPDATED_DT_APROVACAO_CON
        defaultConcepcaoShouldNotBeFound("dtAprovacaoCon.in=" + UPDATED_DT_APROVACAO_CON);
    }

    @Test
    @Transactional
    public void getAllConcepcaosByDtAprovacaoConIsNullOrNotNull() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where dtAprovacaoCon is not null
        defaultConcepcaoShouldBeFound("dtAprovacaoCon.specified=true");

        // Get all the concepcaoList where dtAprovacaoCon is null
        defaultConcepcaoShouldNotBeFound("dtAprovacaoCon.specified=false");
    }

    @Test
    @Transactional
    public void getAllConcepcaosByDtAprovacaoConIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where dtAprovacaoCon greater than or equals to DEFAULT_DT_APROVACAO_CON
        defaultConcepcaoShouldBeFound("dtAprovacaoCon.greaterOrEqualThan=" + DEFAULT_DT_APROVACAO_CON);

        // Get all the concepcaoList where dtAprovacaoCon greater than or equals to UPDATED_DT_APROVACAO_CON
        defaultConcepcaoShouldNotBeFound("dtAprovacaoCon.greaterOrEqualThan=" + UPDATED_DT_APROVACAO_CON);
    }

    @Test
    @Transactional
    public void getAllConcepcaosByDtAprovacaoConIsLessThanSomething() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);

        // Get all the concepcaoList where dtAprovacaoCon less than or equals to DEFAULT_DT_APROVACAO_CON
        defaultConcepcaoShouldNotBeFound("dtAprovacaoCon.lessThan=" + DEFAULT_DT_APROVACAO_CON);

        // Get all the concepcaoList where dtAprovacaoCon less than or equals to UPDATED_DT_APROVACAO_CON
        defaultConcepcaoShouldBeFound("dtAprovacaoCon.lessThan=" + UPDATED_DT_APROVACAO_CON);
    }


    @Test
    @Transactional
    public void getAllConcepcaosByIdProgramasProjectosIsEqualToSomething() throws Exception {
        // Initialize the database
        ProgramasProjectos idProgramasProjectos = ProgramasProjectosResourceIntTest.createEntity(em);
        em.persist(idProgramasProjectos);
        em.flush();
        concepcao.setIdProgramasProjectos(idProgramasProjectos);
        concepcaoRepository.saveAndFlush(concepcao);
        Long idProgramasProjectosId = idProgramasProjectos.getId();

        // Get all the concepcaoList where idProgramasProjectos equals to idProgramasProjectosId
        defaultConcepcaoShouldBeFound("idProgramasProjectosId.equals=" + idProgramasProjectosId);

        // Get all the concepcaoList where idProgramasProjectos equals to idProgramasProjectosId + 1
        defaultConcepcaoShouldNotBeFound("idProgramasProjectosId.equals=" + (idProgramasProjectosId + 1));
    }


    @Test
    @Transactional
    public void getAllConcepcaosByIdSistemaAguaIsEqualToSomething() throws Exception {
        // Initialize the database
        SistemaAgua idSistemaAgua = SistemaAguaResourceIntTest.createEntity(em);
        em.persist(idSistemaAgua);
        em.flush();
        concepcao.setIdSistemaAgua(idSistemaAgua);
        concepcaoRepository.saveAndFlush(concepcao);
        Long idSistemaAguaId = idSistemaAgua.getId();

        // Get all the concepcaoList where idSistemaAgua equals to idSistemaAguaId
        defaultConcepcaoShouldBeFound("idSistemaAguaId.equals=" + idSistemaAguaId);

        // Get all the concepcaoList where idSistemaAgua equals to idSistemaAguaId + 1
        defaultConcepcaoShouldNotBeFound("idSistemaAguaId.equals=" + (idSistemaAguaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultConcepcaoShouldBeFound(String filter) throws Exception {
        restConcepcaoMockMvc.perform(get("/api/concepcaos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(concepcao.getId().intValue())))
            .andExpect(jsonPath("$.[*].idConcepcao").value(hasItem(DEFAULT_ID_CONCEPCAO.intValue())))
            .andExpect(jsonPath("$.[*].tipoConcurso").value(hasItem(DEFAULT_TIPO_CONCURSO.toString())))
            .andExpect(jsonPath("$.[*].dtLancamento").value(hasItem(DEFAULT_DT_LANCAMENTO.toString())))
            .andExpect(jsonPath("$.[*].dtUltimaAlteracao").value(hasItem(DEFAULT_DT_ULTIMA_ALTERACAO.toString())))
            .andExpect(jsonPath("$.[*].dtElaboracaoCon").value(hasItem(DEFAULT_DT_ELABORACAO_CON.toString())))
            .andExpect(jsonPath("$.[*].dtAprovacaoCon").value(hasItem(DEFAULT_DT_APROVACAO_CON.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultConcepcaoShouldNotBeFound(String filter) throws Exception {
        restConcepcaoMockMvc.perform(get("/api/concepcaos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingConcepcao() throws Exception {
        // Get the concepcao
        restConcepcaoMockMvc.perform(get("/api/concepcaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConcepcao() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);
        int databaseSizeBeforeUpdate = concepcaoRepository.findAll().size();

        // Update the concepcao
        Concepcao updatedConcepcao = concepcaoRepository.findOne(concepcao.getId());
        // Disconnect from session so that the updates on updatedConcepcao are not directly saved in db
        em.detach(updatedConcepcao);
        updatedConcepcao
            .tipoConcurso(UPDATED_TIPO_CONCURSO)
            .dtLancamento(UPDATED_DT_LANCAMENTO)
            .dtUltimaAlteracao(UPDATED_DT_ULTIMA_ALTERACAO)
            .dtElaboracaoCon(UPDATED_DT_ELABORACAO_CON)
            .dtAprovacaoCon(UPDATED_DT_APROVACAO_CON);
        ConcepcaoDTO concepcaoDTO = concepcaoMapper.toDto(updatedConcepcao);

        restConcepcaoMockMvc.perform(put("/api/concepcaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(concepcaoDTO)))
            .andExpect(status().isOk());

        // Validate the Concepcao in the database
        List<Concepcao> concepcaoList = concepcaoRepository.findAll();
        assertThat(concepcaoList).hasSize(databaseSizeBeforeUpdate);
        Concepcao testConcepcao = concepcaoList.get(concepcaoList.size() - 1);
        assertThat(testConcepcao.getTipoConcurso()).isEqualTo(UPDATED_TIPO_CONCURSO);
        assertThat(testConcepcao.getDtLancamento()).isEqualTo(UPDATED_DT_LANCAMENTO);
        assertThat(testConcepcao.getDtUltimaAlteracao()).isEqualTo(UPDATED_DT_ULTIMA_ALTERACAO);
        assertThat(testConcepcao.getDtElaboracaoCon()).isEqualTo(UPDATED_DT_ELABORACAO_CON);
        assertThat(testConcepcao.getDtAprovacaoCon()).isEqualTo(UPDATED_DT_APROVACAO_CON);
    }

    @Test
    @Transactional
    public void updateNonExistingConcepcao() throws Exception {
        int databaseSizeBeforeUpdate = concepcaoRepository.findAll().size();

        // Create the Concepcao
        ConcepcaoDTO concepcaoDTO = concepcaoMapper.toDto(concepcao);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restConcepcaoMockMvc.perform(put("/api/concepcaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(concepcaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Concepcao in the database
        List<Concepcao> concepcaoList = concepcaoRepository.findAll();
        assertThat(concepcaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteConcepcao() throws Exception {
        // Initialize the database
        concepcaoRepository.saveAndFlush(concepcao);
        int databaseSizeBeforeDelete = concepcaoRepository.findAll().size();

        // Get the concepcao
        restConcepcaoMockMvc.perform(delete("/api/concepcaos/{id}", concepcao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Concepcao> concepcaoList = concepcaoRepository.findAll();
        assertThat(concepcaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Concepcao.class);
        Concepcao concepcao1 = new Concepcao();
        concepcao1.setId(1L);
        Concepcao concepcao2 = new Concepcao();
        concepcao2.setId(concepcao1.getId());
        assertThat(concepcao1).isEqualTo(concepcao2);
        concepcao2.setId(2L);
        assertThat(concepcao1).isNotEqualTo(concepcao2);
        concepcao1.setId(null);
        assertThat(concepcao1).isNotEqualTo(concepcao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConcepcaoDTO.class);
        ConcepcaoDTO concepcaoDTO1 = new ConcepcaoDTO();
        concepcaoDTO1.setId(1L);
        ConcepcaoDTO concepcaoDTO2 = new ConcepcaoDTO();
        assertThat(concepcaoDTO1).isNotEqualTo(concepcaoDTO2);
        concepcaoDTO2.setId(concepcaoDTO1.getId());
        assertThat(concepcaoDTO1).isEqualTo(concepcaoDTO2);
        concepcaoDTO2.setId(2L);
        assertThat(concepcaoDTO1).isNotEqualTo(concepcaoDTO2);
        concepcaoDTO1.setId(null);
        assertThat(concepcaoDTO1).isNotEqualTo(concepcaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(concepcaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(concepcaoMapper.fromId(null)).isNull();
    }
}
