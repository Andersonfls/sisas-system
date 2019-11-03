package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.Contrato;
import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.domain.Empreitada;
import com.minea.sisas.domain.Execucao;
import com.minea.sisas.repository.ContratoRepository;
import com.minea.sisas.service.ContratoService;
import com.minea.sisas.service.dto.ContratoDTO;
import com.minea.sisas.service.mapper.ContratoMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.ContratoQueryService;

import com.minea.sisas.web.rest.ContratoResource;
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
 * Test class for the ContratoResource REST controller.
 *
 * @see ContratoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class ContratoResourceIntTest {

    private static final Long DEFAULT_ID_CONTRATO = 1L;
    private static final Long UPDATED_ID_CONTRATO = 2L;

    private static final String DEFAULT_TIPO_EMPREITADA = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_EMPREITADA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DT_LANCAMENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_LANCAMENTO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NM_EMPRESA_ADJUDICITARIA = "AAAAAAAAAA";
    private static final String UPDATED_NM_EMPRESA_ADJUDICITARIA = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_VALOR_CONTRATO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALOR_CONTRATO = new BigDecimal(2);

    private static final LocalDate DEFAULT_DT_ASSINATURA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_ASSINATURA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TIPO_MOEDA = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_MOEDA = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_VALOR_ADIANTAMENTO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALOR_ADIANTAMENTO = new BigDecimal(2);

    private static final LocalDate DEFAULT_DT_ADIANTAMENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_ADIANTAMENTO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_INICIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_INICIO = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_PRAZO_EXECUCAO = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRAZO_EXECUCAO = new BigDecimal(2);

    private static final LocalDate DEFAULT_DT_RECEPCAO_PROVISORIA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_RECEPCAO_PROVISORIA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_RECEPCAO_DEFINITIVA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_RECEPCAO_DEFINITIVA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_RECEPCAO_COMICIONAMENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_RECEPCAO_COMICIONAMENTO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NM_RESPOSAVEL_ANT_PROJETO = "AAAAAAAAAA";
    private static final String UPDATED_NM_RESPOSAVEL_ANT_PROJETO = "BBBBBBBBBB";

    private static final String DEFAULT_NM_RESPOSAVEL_PROJETO = "AAAAAAAAAA";
    private static final String UPDATED_NM_RESPOSAVEL_PROJETO = "BBBBBBBBBB";

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private ContratoMapper contratoMapper;

    @Autowired
    private ContratoService contratoService;

    @Autowired
    private ContratoQueryService contratoQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restContratoMockMvc;

    private Contrato contrato;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContratoResource contratoResource = new ContratoResource(contratoService, contratoQueryService, null);
        this.restContratoMockMvc = MockMvcBuilders.standaloneSetup(contratoResource)
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
    public static Contrato createEntity(EntityManager em) {
        Contrato contrato = new Contrato()
            .tipoEmpreitada(DEFAULT_TIPO_EMPREITADA)
            .dtLancamento(DEFAULT_DT_LANCAMENTO)
            .nmEmpresaAdjudicitaria(DEFAULT_NM_EMPRESA_ADJUDICITARIA)
            .valorContrato(DEFAULT_VALOR_CONTRATO)
            .dtAssinatura(DEFAULT_DT_ASSINATURA)
            .dtFinalizacaoProcessoHomologAprov(DEFAULT_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV)
            .tipoMoeda(DEFAULT_TIPO_MOEDA)
            .valorAdiantamento(DEFAULT_VALOR_ADIANTAMENTO)
            .dtAdiantamento(DEFAULT_DT_ADIANTAMENTO)
            .dtInicio(DEFAULT_DT_INICIO)
            .prazoExecucao(DEFAULT_PRAZO_EXECUCAO)
            .dtRecepcaoProvisoria(DEFAULT_DT_RECEPCAO_PROVISORIA)
            .dtRecepcaoDefinitiva(DEFAULT_DT_RECEPCAO_DEFINITIVA)
            .dtRecepcaoComicionamento(DEFAULT_DT_RECEPCAO_COMICIONAMENTO)
            .nmResposavelAntProjeto(DEFAULT_NM_RESPOSAVEL_ANT_PROJETO)
            .nmResposavelProjeto(DEFAULT_NM_RESPOSAVEL_PROJETO);
        // Add required entity
        ProgramasProjectos idProgramasProjectos = ProgramasProjectosResourceIntTest.createEntity(em);
        em.persist(idProgramasProjectos);
        em.flush();
        contrato.setProgramasProjectos(idProgramasProjectos);
        // Add required entity
        SistemaAgua idSistemaAgua = SistemaAguaResourceIntTest.createEntity(em);
        em.persist(idSistemaAgua);
        em.flush();
        contrato.setIdSistemaAgua(idSistemaAgua);
        return contrato;
    }

    @Before
    public void initTest() {
        contrato = createEntity(em);
    }

    @Test
    @Transactional
    public void createContrato() throws Exception {
        int databaseSizeBeforeCreate = contratoRepository.findAll().size();

        // Create the Contrato
        ContratoDTO contratoDTO = contratoMapper.toDto(contrato);
        restContratoMockMvc.perform(post("/api/contratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoDTO)))
            .andExpect(status().isCreated());

        // Validate the Contrato in the database
        List<Contrato> contratoList = contratoRepository.findAll();
        assertThat(contratoList).hasSize(databaseSizeBeforeCreate + 1);
        Contrato testContrato = contratoList.get(contratoList.size() - 1);
        assertThat(testContrato.getTipoEmpreitada()).isEqualTo(DEFAULT_TIPO_EMPREITADA);
        assertThat(testContrato.getDtLancamento()).isEqualTo(DEFAULT_DT_LANCAMENTO);
        assertThat(testContrato.getNmEmpresaAdjudicitaria()).isEqualTo(DEFAULT_NM_EMPRESA_ADJUDICITARIA);
        assertThat(testContrato.getValorContrato()).isEqualTo(DEFAULT_VALOR_CONTRATO);
        assertThat(testContrato.getDtAssinatura()).isEqualTo(DEFAULT_DT_ASSINATURA);
        assertThat(testContrato.getDtFinalizacaoProcessoHomologAprov()).isEqualTo(DEFAULT_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV);
        assertThat(testContrato.getTipoMoeda()).isEqualTo(DEFAULT_TIPO_MOEDA);
        assertThat(testContrato.getValorAdiantamento()).isEqualTo(DEFAULT_VALOR_ADIANTAMENTO);
        assertThat(testContrato.getDtAdiantamento()).isEqualTo(DEFAULT_DT_ADIANTAMENTO);
        assertThat(testContrato.getDtInicio()).isEqualTo(DEFAULT_DT_INICIO);
        assertThat(testContrato.getPrazoExecucao()).isEqualTo(DEFAULT_PRAZO_EXECUCAO);
        assertThat(testContrato.getDtRecepcaoProvisoria()).isEqualTo(DEFAULT_DT_RECEPCAO_PROVISORIA);
        assertThat(testContrato.getDtRecepcaoDefinitiva()).isEqualTo(DEFAULT_DT_RECEPCAO_DEFINITIVA);
        assertThat(testContrato.getDtRecepcaoComicionamento()).isEqualTo(DEFAULT_DT_RECEPCAO_COMICIONAMENTO);
        assertThat(testContrato.getNmResposavelAntProjeto()).isEqualTo(DEFAULT_NM_RESPOSAVEL_ANT_PROJETO);
        assertThat(testContrato.getNmResposavelProjeto()).isEqualTo(DEFAULT_NM_RESPOSAVEL_PROJETO);
    }

    @Test
    @Transactional
    public void createContratoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contratoRepository.findAll().size();

        // Create the Contrato with an existing ID
        contrato.setId(1L);
        ContratoDTO contratoDTO = contratoMapper.toDto(contrato);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContratoMockMvc.perform(post("/api/contratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Contrato in the database
        List<Contrato> contratoList = contratoRepository.findAll();
        assertThat(contratoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdContratoIsRequired() throws Exception {
        int databaseSizeBeforeTest = contratoRepository.findAll().size();
        // set the field null

        // Create the Contrato, which fails.
        ContratoDTO contratoDTO = contratoMapper.toDto(contrato);

        restContratoMockMvc.perform(post("/api/contratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoDTO)))
            .andExpect(status().isBadRequest());

        List<Contrato> contratoList = contratoRepository.findAll();
        assertThat(contratoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTipoEmpreitadaIsRequired() throws Exception {
        int databaseSizeBeforeTest = contratoRepository.findAll().size();
        // set the field null
        contrato.setTipoEmpreitada(null);

        // Create the Contrato, which fails.
        ContratoDTO contratoDTO = contratoMapper.toDto(contrato);

        restContratoMockMvc.perform(post("/api/contratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoDTO)))
            .andExpect(status().isBadRequest());

        List<Contrato> contratoList = contratoRepository.findAll();
        assertThat(contratoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDtLancamentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = contratoRepository.findAll().size();
        // set the field null
        contrato.setDtLancamento(null);

        // Create the Contrato, which fails.
        ContratoDTO contratoDTO = contratoMapper.toDto(contrato);

        restContratoMockMvc.perform(post("/api/contratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoDTO)))
            .andExpect(status().isBadRequest());

        List<Contrato> contratoList = contratoRepository.findAll();
        assertThat(contratoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValorContratoIsRequired() throws Exception {
        int databaseSizeBeforeTest = contratoRepository.findAll().size();
        // set the field null
        contrato.setValorContrato(null);

        // Create the Contrato, which fails.
        ContratoDTO contratoDTO = contratoMapper.toDto(contrato);

        restContratoMockMvc.perform(post("/api/contratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoDTO)))
            .andExpect(status().isBadRequest());

        List<Contrato> contratoList = contratoRepository.findAll();
        assertThat(contratoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTipoMoedaIsRequired() throws Exception {
        int databaseSizeBeforeTest = contratoRepository.findAll().size();
        // set the field null
        contrato.setTipoMoeda(null);

        // Create the Contrato, which fails.
        ContratoDTO contratoDTO = contratoMapper.toDto(contrato);

        restContratoMockMvc.perform(post("/api/contratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoDTO)))
            .andExpect(status().isBadRequest());

        List<Contrato> contratoList = contratoRepository.findAll();
        assertThat(contratoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValorAdiantamentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = contratoRepository.findAll().size();
        // set the field null
        contrato.setValorAdiantamento(null);

        // Create the Contrato, which fails.
        ContratoDTO contratoDTO = contratoMapper.toDto(contrato);

        restContratoMockMvc.perform(post("/api/contratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoDTO)))
            .andExpect(status().isBadRequest());

        List<Contrato> contratoList = contratoRepository.findAll();
        assertThat(contratoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllContratoes() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList
        restContratoMockMvc.perform(get("/api/contratoes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contrato.getId().intValue())))
            .andExpect(jsonPath("$.[*].idContrato").value(hasItem(DEFAULT_ID_CONTRATO.intValue())))
            .andExpect(jsonPath("$.[*].tipoEmpreitada").value(hasItem(DEFAULT_TIPO_EMPREITADA.toString())))
            .andExpect(jsonPath("$.[*].dtLancamento").value(hasItem(DEFAULT_DT_LANCAMENTO.toString())))
            .andExpect(jsonPath("$.[*].nmEmpresaAdjudicitaria").value(hasItem(DEFAULT_NM_EMPRESA_ADJUDICITARIA.toString())))
            .andExpect(jsonPath("$.[*].valorContrato").value(hasItem(DEFAULT_VALOR_CONTRATO.intValue())))
            .andExpect(jsonPath("$.[*].dtAssinatura").value(hasItem(DEFAULT_DT_ASSINATURA.toString())))
            .andExpect(jsonPath("$.[*].dtFinalizacaoProcessoHomologAprov").value(hasItem(DEFAULT_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV.toString())))
            .andExpect(jsonPath("$.[*].tipoMoeda").value(hasItem(DEFAULT_TIPO_MOEDA.toString())))
            .andExpect(jsonPath("$.[*].valorAdiantamento").value(hasItem(DEFAULT_VALOR_ADIANTAMENTO.intValue())))
            .andExpect(jsonPath("$.[*].dtAdiantamento").value(hasItem(DEFAULT_DT_ADIANTAMENTO.toString())))
            .andExpect(jsonPath("$.[*].dtInicio").value(hasItem(DEFAULT_DT_INICIO.toString())))
            .andExpect(jsonPath("$.[*].prazoExecucao").value(hasItem(DEFAULT_PRAZO_EXECUCAO.intValue())))
            .andExpect(jsonPath("$.[*].dtRecepcaoProvisoria").value(hasItem(DEFAULT_DT_RECEPCAO_PROVISORIA.toString())))
            .andExpect(jsonPath("$.[*].dtRecepcaoDefinitiva").value(hasItem(DEFAULT_DT_RECEPCAO_DEFINITIVA.toString())))
            .andExpect(jsonPath("$.[*].dtRecepcaoComicionamento").value(hasItem(DEFAULT_DT_RECEPCAO_COMICIONAMENTO.toString())))
            .andExpect(jsonPath("$.[*].nmResposavelAntProjeto").value(hasItem(DEFAULT_NM_RESPOSAVEL_ANT_PROJETO.toString())))
            .andExpect(jsonPath("$.[*].nmResposavelProjeto").value(hasItem(DEFAULT_NM_RESPOSAVEL_PROJETO.toString())));
    }

    @Test
    @Transactional
    public void getContrato() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get the contrato
        restContratoMockMvc.perform(get("/api/contratoes/{id}", contrato.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contrato.getId().intValue()))
            .andExpect(jsonPath("$.idContrato").value(DEFAULT_ID_CONTRATO.intValue()))
            .andExpect(jsonPath("$.tipoEmpreitada").value(DEFAULT_TIPO_EMPREITADA.toString()))
            .andExpect(jsonPath("$.dtLancamento").value(DEFAULT_DT_LANCAMENTO.toString()))
            .andExpect(jsonPath("$.nmEmpresaAdjudicitaria").value(DEFAULT_NM_EMPRESA_ADJUDICITARIA.toString()))
            .andExpect(jsonPath("$.valorContrato").value(DEFAULT_VALOR_CONTRATO.intValue()))
            .andExpect(jsonPath("$.dtAssinatura").value(DEFAULT_DT_ASSINATURA.toString()))
            .andExpect(jsonPath("$.dtFinalizacaoProcessoHomologAprov").value(DEFAULT_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV.toString()))
            .andExpect(jsonPath("$.tipoMoeda").value(DEFAULT_TIPO_MOEDA.toString()))
            .andExpect(jsonPath("$.valorAdiantamento").value(DEFAULT_VALOR_ADIANTAMENTO.intValue()))
            .andExpect(jsonPath("$.dtAdiantamento").value(DEFAULT_DT_ADIANTAMENTO.toString()))
            .andExpect(jsonPath("$.dtInicio").value(DEFAULT_DT_INICIO.toString()))
            .andExpect(jsonPath("$.prazoExecucao").value(DEFAULT_PRAZO_EXECUCAO.intValue()))
            .andExpect(jsonPath("$.dtRecepcaoProvisoria").value(DEFAULT_DT_RECEPCAO_PROVISORIA.toString()))
            .andExpect(jsonPath("$.dtRecepcaoDefinitiva").value(DEFAULT_DT_RECEPCAO_DEFINITIVA.toString()))
            .andExpect(jsonPath("$.dtRecepcaoComicionamento").value(DEFAULT_DT_RECEPCAO_COMICIONAMENTO.toString()))
            .andExpect(jsonPath("$.nmResposavelAntProjeto").value(DEFAULT_NM_RESPOSAVEL_ANT_PROJETO.toString()))
            .andExpect(jsonPath("$.nmResposavelProjeto").value(DEFAULT_NM_RESPOSAVEL_PROJETO.toString()));
    }

    @Test
    @Transactional
    public void getAllContratoesByIdContratoIsEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where idContrato equals to DEFAULT_ID_CONTRATO
        defaultContratoShouldBeFound("idContrato.equals=" + DEFAULT_ID_CONTRATO);

        // Get all the contratoList where idContrato equals to UPDATED_ID_CONTRATO
        defaultContratoShouldNotBeFound("idContrato.equals=" + UPDATED_ID_CONTRATO);
    }

    @Test
    @Transactional
    public void getAllContratoesByIdContratoIsInShouldWork() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where idContrato in DEFAULT_ID_CONTRATO or UPDATED_ID_CONTRATO
        defaultContratoShouldBeFound("idContrato.in=" + DEFAULT_ID_CONTRATO + "," + UPDATED_ID_CONTRATO);

        // Get all the contratoList where idContrato equals to UPDATED_ID_CONTRATO
        defaultContratoShouldNotBeFound("idContrato.in=" + UPDATED_ID_CONTRATO);
    }

    @Test
    @Transactional
    public void getAllContratoesByIdContratoIsNullOrNotNull() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where idContrato is not null
        defaultContratoShouldBeFound("idContrato.specified=true");

        // Get all the contratoList where idContrato is null
        defaultContratoShouldNotBeFound("idContrato.specified=false");
    }

    @Test
    @Transactional
    public void getAllContratoesByIdContratoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where idContrato greater than or equals to DEFAULT_ID_CONTRATO
        defaultContratoShouldBeFound("idContrato.greaterOrEqualThan=" + DEFAULT_ID_CONTRATO);

        // Get all the contratoList where idContrato greater than or equals to UPDATED_ID_CONTRATO
        defaultContratoShouldNotBeFound("idContrato.greaterOrEqualThan=" + UPDATED_ID_CONTRATO);
    }

    @Test
    @Transactional
    public void getAllContratoesByIdContratoIsLessThanSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where idContrato less than or equals to DEFAULT_ID_CONTRATO
        defaultContratoShouldNotBeFound("idContrato.lessThan=" + DEFAULT_ID_CONTRATO);

        // Get all the contratoList where idContrato less than or equals to UPDATED_ID_CONTRATO
        defaultContratoShouldBeFound("idContrato.lessThan=" + UPDATED_ID_CONTRATO);
    }


    @Test
    @Transactional
    public void getAllContratoesByTipoEmpreitadaIsEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where tipoEmpreitada equals to DEFAULT_TIPO_EMPREITADA
        defaultContratoShouldBeFound("tipoEmpreitada.equals=" + DEFAULT_TIPO_EMPREITADA);

        // Get all the contratoList where tipoEmpreitada equals to UPDATED_TIPO_EMPREITADA
        defaultContratoShouldNotBeFound("tipoEmpreitada.equals=" + UPDATED_TIPO_EMPREITADA);
    }

    @Test
    @Transactional
    public void getAllContratoesByTipoEmpreitadaIsInShouldWork() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where tipoEmpreitada in DEFAULT_TIPO_EMPREITADA or UPDATED_TIPO_EMPREITADA
        defaultContratoShouldBeFound("tipoEmpreitada.in=" + DEFAULT_TIPO_EMPREITADA + "," + UPDATED_TIPO_EMPREITADA);

        // Get all the contratoList where tipoEmpreitada equals to UPDATED_TIPO_EMPREITADA
        defaultContratoShouldNotBeFound("tipoEmpreitada.in=" + UPDATED_TIPO_EMPREITADA);
    }

    @Test
    @Transactional
    public void getAllContratoesByTipoEmpreitadaIsNullOrNotNull() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where tipoEmpreitada is not null
        defaultContratoShouldBeFound("tipoEmpreitada.specified=true");

        // Get all the contratoList where tipoEmpreitada is null
        defaultContratoShouldNotBeFound("tipoEmpreitada.specified=false");
    }

    @Test
    @Transactional
    public void getAllContratoesByDtLancamentoIsEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtLancamento equals to DEFAULT_DT_LANCAMENTO
        defaultContratoShouldBeFound("dtLancamento.equals=" + DEFAULT_DT_LANCAMENTO);

        // Get all the contratoList where dtLancamento equals to UPDATED_DT_LANCAMENTO
        defaultContratoShouldNotBeFound("dtLancamento.equals=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtLancamentoIsInShouldWork() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtLancamento in DEFAULT_DT_LANCAMENTO or UPDATED_DT_LANCAMENTO
        defaultContratoShouldBeFound("dtLancamento.in=" + DEFAULT_DT_LANCAMENTO + "," + UPDATED_DT_LANCAMENTO);

        // Get all the contratoList where dtLancamento equals to UPDATED_DT_LANCAMENTO
        defaultContratoShouldNotBeFound("dtLancamento.in=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtLancamentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtLancamento is not null
        defaultContratoShouldBeFound("dtLancamento.specified=true");

        // Get all the contratoList where dtLancamento is null
        defaultContratoShouldNotBeFound("dtLancamento.specified=false");
    }

    @Test
    @Transactional
    public void getAllContratoesByDtLancamentoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtLancamento greater than or equals to DEFAULT_DT_LANCAMENTO
        defaultContratoShouldBeFound("dtLancamento.greaterOrEqualThan=" + DEFAULT_DT_LANCAMENTO);

        // Get all the contratoList where dtLancamento greater than or equals to UPDATED_DT_LANCAMENTO
        defaultContratoShouldNotBeFound("dtLancamento.greaterOrEqualThan=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtLancamentoIsLessThanSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtLancamento less than or equals to DEFAULT_DT_LANCAMENTO
        defaultContratoShouldNotBeFound("dtLancamento.lessThan=" + DEFAULT_DT_LANCAMENTO);

        // Get all the contratoList where dtLancamento less than or equals to UPDATED_DT_LANCAMENTO
        defaultContratoShouldBeFound("dtLancamento.lessThan=" + UPDATED_DT_LANCAMENTO);
    }


    @Test
    @Transactional
    public void getAllContratoesByNmEmpresaAdjudicitariaIsEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where nmEmpresaAdjudicitaria equals to DEFAULT_NM_EMPRESA_ADJUDICITARIA
        defaultContratoShouldBeFound("nmEmpresaAdjudicitaria.equals=" + DEFAULT_NM_EMPRESA_ADJUDICITARIA);

        // Get all the contratoList where nmEmpresaAdjudicitaria equals to UPDATED_NM_EMPRESA_ADJUDICITARIA
        defaultContratoShouldNotBeFound("nmEmpresaAdjudicitaria.equals=" + UPDATED_NM_EMPRESA_ADJUDICITARIA);
    }

    @Test
    @Transactional
    public void getAllContratoesByNmEmpresaAdjudicitariaIsInShouldWork() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where nmEmpresaAdjudicitaria in DEFAULT_NM_EMPRESA_ADJUDICITARIA or UPDATED_NM_EMPRESA_ADJUDICITARIA
        defaultContratoShouldBeFound("nmEmpresaAdjudicitaria.in=" + DEFAULT_NM_EMPRESA_ADJUDICITARIA + "," + UPDATED_NM_EMPRESA_ADJUDICITARIA);

        // Get all the contratoList where nmEmpresaAdjudicitaria equals to UPDATED_NM_EMPRESA_ADJUDICITARIA
        defaultContratoShouldNotBeFound("nmEmpresaAdjudicitaria.in=" + UPDATED_NM_EMPRESA_ADJUDICITARIA);
    }

    @Test
    @Transactional
    public void getAllContratoesByNmEmpresaAdjudicitariaIsNullOrNotNull() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where nmEmpresaAdjudicitaria is not null
        defaultContratoShouldBeFound("nmEmpresaAdjudicitaria.specified=true");

        // Get all the contratoList where nmEmpresaAdjudicitaria is null
        defaultContratoShouldNotBeFound("nmEmpresaAdjudicitaria.specified=false");
    }

    @Test
    @Transactional
    public void getAllContratoesByValorContratoIsEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where valorContrato equals to DEFAULT_VALOR_CONTRATO
        defaultContratoShouldBeFound("valorContrato.equals=" + DEFAULT_VALOR_CONTRATO);

        // Get all the contratoList where valorContrato equals to UPDATED_VALOR_CONTRATO
        defaultContratoShouldNotBeFound("valorContrato.equals=" + UPDATED_VALOR_CONTRATO);
    }

    @Test
    @Transactional
    public void getAllContratoesByValorContratoIsInShouldWork() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where valorContrato in DEFAULT_VALOR_CONTRATO or UPDATED_VALOR_CONTRATO
        defaultContratoShouldBeFound("valorContrato.in=" + DEFAULT_VALOR_CONTRATO + "," + UPDATED_VALOR_CONTRATO);

        // Get all the contratoList where valorContrato equals to UPDATED_VALOR_CONTRATO
        defaultContratoShouldNotBeFound("valorContrato.in=" + UPDATED_VALOR_CONTRATO);
    }

    @Test
    @Transactional
    public void getAllContratoesByValorContratoIsNullOrNotNull() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where valorContrato is not null
        defaultContratoShouldBeFound("valorContrato.specified=true");

        // Get all the contratoList where valorContrato is null
        defaultContratoShouldNotBeFound("valorContrato.specified=false");
    }

    @Test
    @Transactional
    public void getAllContratoesByDtAssinaturaIsEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtAssinatura equals to DEFAULT_DT_ASSINATURA
        defaultContratoShouldBeFound("dtAssinatura.equals=" + DEFAULT_DT_ASSINATURA);

        // Get all the contratoList where dtAssinatura equals to UPDATED_DT_ASSINATURA
        defaultContratoShouldNotBeFound("dtAssinatura.equals=" + UPDATED_DT_ASSINATURA);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtAssinaturaIsInShouldWork() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtAssinatura in DEFAULT_DT_ASSINATURA or UPDATED_DT_ASSINATURA
        defaultContratoShouldBeFound("dtAssinatura.in=" + DEFAULT_DT_ASSINATURA + "," + UPDATED_DT_ASSINATURA);

        // Get all the contratoList where dtAssinatura equals to UPDATED_DT_ASSINATURA
        defaultContratoShouldNotBeFound("dtAssinatura.in=" + UPDATED_DT_ASSINATURA);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtAssinaturaIsNullOrNotNull() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtAssinatura is not null
        defaultContratoShouldBeFound("dtAssinatura.specified=true");

        // Get all the contratoList where dtAssinatura is null
        defaultContratoShouldNotBeFound("dtAssinatura.specified=false");
    }

    @Test
    @Transactional
    public void getAllContratoesByDtAssinaturaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtAssinatura greater than or equals to DEFAULT_DT_ASSINATURA
        defaultContratoShouldBeFound("dtAssinatura.greaterOrEqualThan=" + DEFAULT_DT_ASSINATURA);

        // Get all the contratoList where dtAssinatura greater than or equals to UPDATED_DT_ASSINATURA
        defaultContratoShouldNotBeFound("dtAssinatura.greaterOrEqualThan=" + UPDATED_DT_ASSINATURA);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtAssinaturaIsLessThanSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtAssinatura less than or equals to DEFAULT_DT_ASSINATURA
        defaultContratoShouldNotBeFound("dtAssinatura.lessThan=" + DEFAULT_DT_ASSINATURA);

        // Get all the contratoList where dtAssinatura less than or equals to UPDATED_DT_ASSINATURA
        defaultContratoShouldBeFound("dtAssinatura.lessThan=" + UPDATED_DT_ASSINATURA);
    }


    @Test
    @Transactional
    public void getAllContratoesByDtFinalizacaoProcessoHomologAprovIsEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtFinalizacaoProcessoHomologAprov equals to DEFAULT_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV
        defaultContratoShouldBeFound("dtFinalizacaoProcessoHomologAprov.equals=" + DEFAULT_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV);

        // Get all the contratoList where dtFinalizacaoProcessoHomologAprov equals to UPDATED_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV
        defaultContratoShouldNotBeFound("dtFinalizacaoProcessoHomologAprov.equals=" + UPDATED_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtFinalizacaoProcessoHomologAprovIsInShouldWork() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtFinalizacaoProcessoHomologAprov in DEFAULT_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV or UPDATED_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV
        defaultContratoShouldBeFound("dtFinalizacaoProcessoHomologAprov.in=" + DEFAULT_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV + "," + UPDATED_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV);

        // Get all the contratoList where dtFinalizacaoProcessoHomologAprov equals to UPDATED_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV
        defaultContratoShouldNotBeFound("dtFinalizacaoProcessoHomologAprov.in=" + UPDATED_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtFinalizacaoProcessoHomologAprovIsNullOrNotNull() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtFinalizacaoProcessoHomologAprov is not null
        defaultContratoShouldBeFound("dtFinalizacaoProcessoHomologAprov.specified=true");

        // Get all the contratoList where dtFinalizacaoProcessoHomologAprov is null
        defaultContratoShouldNotBeFound("dtFinalizacaoProcessoHomologAprov.specified=false");
    }

    @Test
    @Transactional
    public void getAllContratoesByDtFinalizacaoProcessoHomologAprovIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtFinalizacaoProcessoHomologAprov greater than or equals to DEFAULT_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV
        defaultContratoShouldBeFound("dtFinalizacaoProcessoHomologAprov.greaterOrEqualThan=" + DEFAULT_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV);

        // Get all the contratoList where dtFinalizacaoProcessoHomologAprov greater than or equals to UPDATED_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV
        defaultContratoShouldNotBeFound("dtFinalizacaoProcessoHomologAprov.greaterOrEqualThan=" + UPDATED_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtFinalizacaoProcessoHomologAprovIsLessThanSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtFinalizacaoProcessoHomologAprov less than or equals to DEFAULT_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV
        defaultContratoShouldNotBeFound("dtFinalizacaoProcessoHomologAprov.lessThan=" + DEFAULT_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV);

        // Get all the contratoList where dtFinalizacaoProcessoHomologAprov less than or equals to UPDATED_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV
        defaultContratoShouldBeFound("dtFinalizacaoProcessoHomologAprov.lessThan=" + UPDATED_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV);
    }


    @Test
    @Transactional
    public void getAllContratoesByTipoMoedaIsEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where tipoMoeda equals to DEFAULT_TIPO_MOEDA
        defaultContratoShouldBeFound("tipoMoeda.equals=" + DEFAULT_TIPO_MOEDA);

        // Get all the contratoList where tipoMoeda equals to UPDATED_TIPO_MOEDA
        defaultContratoShouldNotBeFound("tipoMoeda.equals=" + UPDATED_TIPO_MOEDA);
    }

    @Test
    @Transactional
    public void getAllContratoesByTipoMoedaIsInShouldWork() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where tipoMoeda in DEFAULT_TIPO_MOEDA or UPDATED_TIPO_MOEDA
        defaultContratoShouldBeFound("tipoMoeda.in=" + DEFAULT_TIPO_MOEDA + "," + UPDATED_TIPO_MOEDA);

        // Get all the contratoList where tipoMoeda equals to UPDATED_TIPO_MOEDA
        defaultContratoShouldNotBeFound("tipoMoeda.in=" + UPDATED_TIPO_MOEDA);
    }

    @Test
    @Transactional
    public void getAllContratoesByTipoMoedaIsNullOrNotNull() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where tipoMoeda is not null
        defaultContratoShouldBeFound("tipoMoeda.specified=true");

        // Get all the contratoList where tipoMoeda is null
        defaultContratoShouldNotBeFound("tipoMoeda.specified=false");
    }

    @Test
    @Transactional
    public void getAllContratoesByValorAdiantamentoIsEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where valorAdiantamento equals to DEFAULT_VALOR_ADIANTAMENTO
        defaultContratoShouldBeFound("valorAdiantamento.equals=" + DEFAULT_VALOR_ADIANTAMENTO);

        // Get all the contratoList where valorAdiantamento equals to UPDATED_VALOR_ADIANTAMENTO
        defaultContratoShouldNotBeFound("valorAdiantamento.equals=" + UPDATED_VALOR_ADIANTAMENTO);
    }

    @Test
    @Transactional
    public void getAllContratoesByValorAdiantamentoIsInShouldWork() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where valorAdiantamento in DEFAULT_VALOR_ADIANTAMENTO or UPDATED_VALOR_ADIANTAMENTO
        defaultContratoShouldBeFound("valorAdiantamento.in=" + DEFAULT_VALOR_ADIANTAMENTO + "," + UPDATED_VALOR_ADIANTAMENTO);

        // Get all the contratoList where valorAdiantamento equals to UPDATED_VALOR_ADIANTAMENTO
        defaultContratoShouldNotBeFound("valorAdiantamento.in=" + UPDATED_VALOR_ADIANTAMENTO);
    }

    @Test
    @Transactional
    public void getAllContratoesByValorAdiantamentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where valorAdiantamento is not null
        defaultContratoShouldBeFound("valorAdiantamento.specified=true");

        // Get all the contratoList where valorAdiantamento is null
        defaultContratoShouldNotBeFound("valorAdiantamento.specified=false");
    }

    @Test
    @Transactional
    public void getAllContratoesByDtAdiantamentoIsEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtAdiantamento equals to DEFAULT_DT_ADIANTAMENTO
        defaultContratoShouldBeFound("dtAdiantamento.equals=" + DEFAULT_DT_ADIANTAMENTO);

        // Get all the contratoList where dtAdiantamento equals to UPDATED_DT_ADIANTAMENTO
        defaultContratoShouldNotBeFound("dtAdiantamento.equals=" + UPDATED_DT_ADIANTAMENTO);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtAdiantamentoIsInShouldWork() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtAdiantamento in DEFAULT_DT_ADIANTAMENTO or UPDATED_DT_ADIANTAMENTO
        defaultContratoShouldBeFound("dtAdiantamento.in=" + DEFAULT_DT_ADIANTAMENTO + "," + UPDATED_DT_ADIANTAMENTO);

        // Get all the contratoList where dtAdiantamento equals to UPDATED_DT_ADIANTAMENTO
        defaultContratoShouldNotBeFound("dtAdiantamento.in=" + UPDATED_DT_ADIANTAMENTO);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtAdiantamentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtAdiantamento is not null
        defaultContratoShouldBeFound("dtAdiantamento.specified=true");

        // Get all the contratoList where dtAdiantamento is null
        defaultContratoShouldNotBeFound("dtAdiantamento.specified=false");
    }

    @Test
    @Transactional
    public void getAllContratoesByDtAdiantamentoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtAdiantamento greater than or equals to DEFAULT_DT_ADIANTAMENTO
        defaultContratoShouldBeFound("dtAdiantamento.greaterOrEqualThan=" + DEFAULT_DT_ADIANTAMENTO);

        // Get all the contratoList where dtAdiantamento greater than or equals to UPDATED_DT_ADIANTAMENTO
        defaultContratoShouldNotBeFound("dtAdiantamento.greaterOrEqualThan=" + UPDATED_DT_ADIANTAMENTO);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtAdiantamentoIsLessThanSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtAdiantamento less than or equals to DEFAULT_DT_ADIANTAMENTO
        defaultContratoShouldNotBeFound("dtAdiantamento.lessThan=" + DEFAULT_DT_ADIANTAMENTO);

        // Get all the contratoList where dtAdiantamento less than or equals to UPDATED_DT_ADIANTAMENTO
        defaultContratoShouldBeFound("dtAdiantamento.lessThan=" + UPDATED_DT_ADIANTAMENTO);
    }


    @Test
    @Transactional
    public void getAllContratoesByDtInicioIsEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtInicio equals to DEFAULT_DT_INICIO
        defaultContratoShouldBeFound("dtInicio.equals=" + DEFAULT_DT_INICIO);

        // Get all the contratoList where dtInicio equals to UPDATED_DT_INICIO
        defaultContratoShouldNotBeFound("dtInicio.equals=" + UPDATED_DT_INICIO);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtInicioIsInShouldWork() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtInicio in DEFAULT_DT_INICIO or UPDATED_DT_INICIO
        defaultContratoShouldBeFound("dtInicio.in=" + DEFAULT_DT_INICIO + "," + UPDATED_DT_INICIO);

        // Get all the contratoList where dtInicio equals to UPDATED_DT_INICIO
        defaultContratoShouldNotBeFound("dtInicio.in=" + UPDATED_DT_INICIO);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtInicioIsNullOrNotNull() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtInicio is not null
        defaultContratoShouldBeFound("dtInicio.specified=true");

        // Get all the contratoList where dtInicio is null
        defaultContratoShouldNotBeFound("dtInicio.specified=false");
    }

    @Test
    @Transactional
    public void getAllContratoesByDtInicioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtInicio greater than or equals to DEFAULT_DT_INICIO
        defaultContratoShouldBeFound("dtInicio.greaterOrEqualThan=" + DEFAULT_DT_INICIO);

        // Get all the contratoList where dtInicio greater than or equals to UPDATED_DT_INICIO
        defaultContratoShouldNotBeFound("dtInicio.greaterOrEqualThan=" + UPDATED_DT_INICIO);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtInicioIsLessThanSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtInicio less than or equals to DEFAULT_DT_INICIO
        defaultContratoShouldNotBeFound("dtInicio.lessThan=" + DEFAULT_DT_INICIO);

        // Get all the contratoList where dtInicio less than or equals to UPDATED_DT_INICIO
        defaultContratoShouldBeFound("dtInicio.lessThan=" + UPDATED_DT_INICIO);
    }


    @Test
    @Transactional
    public void getAllContratoesByPrazoExecucaoIsEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where prazoExecucao equals to DEFAULT_PRAZO_EXECUCAO
        defaultContratoShouldBeFound("prazoExecucao.equals=" + DEFAULT_PRAZO_EXECUCAO);

        // Get all the contratoList where prazoExecucao equals to UPDATED_PRAZO_EXECUCAO
        defaultContratoShouldNotBeFound("prazoExecucao.equals=" + UPDATED_PRAZO_EXECUCAO);
    }

    @Test
    @Transactional
    public void getAllContratoesByPrazoExecucaoIsInShouldWork() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where prazoExecucao in DEFAULT_PRAZO_EXECUCAO or UPDATED_PRAZO_EXECUCAO
        defaultContratoShouldBeFound("prazoExecucao.in=" + DEFAULT_PRAZO_EXECUCAO + "," + UPDATED_PRAZO_EXECUCAO);

        // Get all the contratoList where prazoExecucao equals to UPDATED_PRAZO_EXECUCAO
        defaultContratoShouldNotBeFound("prazoExecucao.in=" + UPDATED_PRAZO_EXECUCAO);
    }

    @Test
    @Transactional
    public void getAllContratoesByPrazoExecucaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where prazoExecucao is not null
        defaultContratoShouldBeFound("prazoExecucao.specified=true");

        // Get all the contratoList where prazoExecucao is null
        defaultContratoShouldNotBeFound("prazoExecucao.specified=false");
    }

    @Test
    @Transactional
    public void getAllContratoesByDtRecepcaoProvisoriaIsEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtRecepcaoProvisoria equals to DEFAULT_DT_RECEPCAO_PROVISORIA
        defaultContratoShouldBeFound("dtRecepcaoProvisoria.equals=" + DEFAULT_DT_RECEPCAO_PROVISORIA);

        // Get all the contratoList where dtRecepcaoProvisoria equals to UPDATED_DT_RECEPCAO_PROVISORIA
        defaultContratoShouldNotBeFound("dtRecepcaoProvisoria.equals=" + UPDATED_DT_RECEPCAO_PROVISORIA);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtRecepcaoProvisoriaIsInShouldWork() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtRecepcaoProvisoria in DEFAULT_DT_RECEPCAO_PROVISORIA or UPDATED_DT_RECEPCAO_PROVISORIA
        defaultContratoShouldBeFound("dtRecepcaoProvisoria.in=" + DEFAULT_DT_RECEPCAO_PROVISORIA + "," + UPDATED_DT_RECEPCAO_PROVISORIA);

        // Get all the contratoList where dtRecepcaoProvisoria equals to UPDATED_DT_RECEPCAO_PROVISORIA
        defaultContratoShouldNotBeFound("dtRecepcaoProvisoria.in=" + UPDATED_DT_RECEPCAO_PROVISORIA);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtRecepcaoProvisoriaIsNullOrNotNull() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtRecepcaoProvisoria is not null
        defaultContratoShouldBeFound("dtRecepcaoProvisoria.specified=true");

        // Get all the contratoList where dtRecepcaoProvisoria is null
        defaultContratoShouldNotBeFound("dtRecepcaoProvisoria.specified=false");
    }

    @Test
    @Transactional
    public void getAllContratoesByDtRecepcaoProvisoriaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtRecepcaoProvisoria greater than or equals to DEFAULT_DT_RECEPCAO_PROVISORIA
        defaultContratoShouldBeFound("dtRecepcaoProvisoria.greaterOrEqualThan=" + DEFAULT_DT_RECEPCAO_PROVISORIA);

        // Get all the contratoList where dtRecepcaoProvisoria greater than or equals to UPDATED_DT_RECEPCAO_PROVISORIA
        defaultContratoShouldNotBeFound("dtRecepcaoProvisoria.greaterOrEqualThan=" + UPDATED_DT_RECEPCAO_PROVISORIA);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtRecepcaoProvisoriaIsLessThanSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtRecepcaoProvisoria less than or equals to DEFAULT_DT_RECEPCAO_PROVISORIA
        defaultContratoShouldNotBeFound("dtRecepcaoProvisoria.lessThan=" + DEFAULT_DT_RECEPCAO_PROVISORIA);

        // Get all the contratoList where dtRecepcaoProvisoria less than or equals to UPDATED_DT_RECEPCAO_PROVISORIA
        defaultContratoShouldBeFound("dtRecepcaoProvisoria.lessThan=" + UPDATED_DT_RECEPCAO_PROVISORIA);
    }


    @Test
    @Transactional
    public void getAllContratoesByDtRecepcaoDefinitivaIsEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtRecepcaoDefinitiva equals to DEFAULT_DT_RECEPCAO_DEFINITIVA
        defaultContratoShouldBeFound("dtRecepcaoDefinitiva.equals=" + DEFAULT_DT_RECEPCAO_DEFINITIVA);

        // Get all the contratoList where dtRecepcaoDefinitiva equals to UPDATED_DT_RECEPCAO_DEFINITIVA
        defaultContratoShouldNotBeFound("dtRecepcaoDefinitiva.equals=" + UPDATED_DT_RECEPCAO_DEFINITIVA);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtRecepcaoDefinitivaIsInShouldWork() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtRecepcaoDefinitiva in DEFAULT_DT_RECEPCAO_DEFINITIVA or UPDATED_DT_RECEPCAO_DEFINITIVA
        defaultContratoShouldBeFound("dtRecepcaoDefinitiva.in=" + DEFAULT_DT_RECEPCAO_DEFINITIVA + "," + UPDATED_DT_RECEPCAO_DEFINITIVA);

        // Get all the contratoList where dtRecepcaoDefinitiva equals to UPDATED_DT_RECEPCAO_DEFINITIVA
        defaultContratoShouldNotBeFound("dtRecepcaoDefinitiva.in=" + UPDATED_DT_RECEPCAO_DEFINITIVA);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtRecepcaoDefinitivaIsNullOrNotNull() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtRecepcaoDefinitiva is not null
        defaultContratoShouldBeFound("dtRecepcaoDefinitiva.specified=true");

        // Get all the contratoList where dtRecepcaoDefinitiva is null
        defaultContratoShouldNotBeFound("dtRecepcaoDefinitiva.specified=false");
    }

    @Test
    @Transactional
    public void getAllContratoesByDtRecepcaoDefinitivaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtRecepcaoDefinitiva greater than or equals to DEFAULT_DT_RECEPCAO_DEFINITIVA
        defaultContratoShouldBeFound("dtRecepcaoDefinitiva.greaterOrEqualThan=" + DEFAULT_DT_RECEPCAO_DEFINITIVA);

        // Get all the contratoList where dtRecepcaoDefinitiva greater than or equals to UPDATED_DT_RECEPCAO_DEFINITIVA
        defaultContratoShouldNotBeFound("dtRecepcaoDefinitiva.greaterOrEqualThan=" + UPDATED_DT_RECEPCAO_DEFINITIVA);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtRecepcaoDefinitivaIsLessThanSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtRecepcaoDefinitiva less than or equals to DEFAULT_DT_RECEPCAO_DEFINITIVA
        defaultContratoShouldNotBeFound("dtRecepcaoDefinitiva.lessThan=" + DEFAULT_DT_RECEPCAO_DEFINITIVA);

        // Get all the contratoList where dtRecepcaoDefinitiva less than or equals to UPDATED_DT_RECEPCAO_DEFINITIVA
        defaultContratoShouldBeFound("dtRecepcaoDefinitiva.lessThan=" + UPDATED_DT_RECEPCAO_DEFINITIVA);
    }


    @Test
    @Transactional
    public void getAllContratoesByDtRecepcaoComicionamentoIsEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtRecepcaoComicionamento equals to DEFAULT_DT_RECEPCAO_COMICIONAMENTO
        defaultContratoShouldBeFound("dtRecepcaoComicionamento.equals=" + DEFAULT_DT_RECEPCAO_COMICIONAMENTO);

        // Get all the contratoList where dtRecepcaoComicionamento equals to UPDATED_DT_RECEPCAO_COMICIONAMENTO
        defaultContratoShouldNotBeFound("dtRecepcaoComicionamento.equals=" + UPDATED_DT_RECEPCAO_COMICIONAMENTO);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtRecepcaoComicionamentoIsInShouldWork() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtRecepcaoComicionamento in DEFAULT_DT_RECEPCAO_COMICIONAMENTO or UPDATED_DT_RECEPCAO_COMICIONAMENTO
        defaultContratoShouldBeFound("dtRecepcaoComicionamento.in=" + DEFAULT_DT_RECEPCAO_COMICIONAMENTO + "," + UPDATED_DT_RECEPCAO_COMICIONAMENTO);

        // Get all the contratoList where dtRecepcaoComicionamento equals to UPDATED_DT_RECEPCAO_COMICIONAMENTO
        defaultContratoShouldNotBeFound("dtRecepcaoComicionamento.in=" + UPDATED_DT_RECEPCAO_COMICIONAMENTO);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtRecepcaoComicionamentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtRecepcaoComicionamento is not null
        defaultContratoShouldBeFound("dtRecepcaoComicionamento.specified=true");

        // Get all the contratoList where dtRecepcaoComicionamento is null
        defaultContratoShouldNotBeFound("dtRecepcaoComicionamento.specified=false");
    }

    @Test
    @Transactional
    public void getAllContratoesByDtRecepcaoComicionamentoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtRecepcaoComicionamento greater than or equals to DEFAULT_DT_RECEPCAO_COMICIONAMENTO
        defaultContratoShouldBeFound("dtRecepcaoComicionamento.greaterOrEqualThan=" + DEFAULT_DT_RECEPCAO_COMICIONAMENTO);

        // Get all the contratoList where dtRecepcaoComicionamento greater than or equals to UPDATED_DT_RECEPCAO_COMICIONAMENTO
        defaultContratoShouldNotBeFound("dtRecepcaoComicionamento.greaterOrEqualThan=" + UPDATED_DT_RECEPCAO_COMICIONAMENTO);
    }

    @Test
    @Transactional
    public void getAllContratoesByDtRecepcaoComicionamentoIsLessThanSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where dtRecepcaoComicionamento less than or equals to DEFAULT_DT_RECEPCAO_COMICIONAMENTO
        defaultContratoShouldNotBeFound("dtRecepcaoComicionamento.lessThan=" + DEFAULT_DT_RECEPCAO_COMICIONAMENTO);

        // Get all the contratoList where dtRecepcaoComicionamento less than or equals to UPDATED_DT_RECEPCAO_COMICIONAMENTO
        defaultContratoShouldBeFound("dtRecepcaoComicionamento.lessThan=" + UPDATED_DT_RECEPCAO_COMICIONAMENTO);
    }


    @Test
    @Transactional
    public void getAllContratoesByNmResposavelAntProjetoIsEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where nmResposavelAntProjeto equals to DEFAULT_NM_RESPOSAVEL_ANT_PROJETO
        defaultContratoShouldBeFound("nmResposavelAntProjeto.equals=" + DEFAULT_NM_RESPOSAVEL_ANT_PROJETO);

        // Get all the contratoList where nmResposavelAntProjeto equals to UPDATED_NM_RESPOSAVEL_ANT_PROJETO
        defaultContratoShouldNotBeFound("nmResposavelAntProjeto.equals=" + UPDATED_NM_RESPOSAVEL_ANT_PROJETO);
    }

    @Test
    @Transactional
    public void getAllContratoesByNmResposavelAntProjetoIsInShouldWork() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where nmResposavelAntProjeto in DEFAULT_NM_RESPOSAVEL_ANT_PROJETO or UPDATED_NM_RESPOSAVEL_ANT_PROJETO
        defaultContratoShouldBeFound("nmResposavelAntProjeto.in=" + DEFAULT_NM_RESPOSAVEL_ANT_PROJETO + "," + UPDATED_NM_RESPOSAVEL_ANT_PROJETO);

        // Get all the contratoList where nmResposavelAntProjeto equals to UPDATED_NM_RESPOSAVEL_ANT_PROJETO
        defaultContratoShouldNotBeFound("nmResposavelAntProjeto.in=" + UPDATED_NM_RESPOSAVEL_ANT_PROJETO);
    }

    @Test
    @Transactional
    public void getAllContratoesByNmResposavelAntProjetoIsNullOrNotNull() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where nmResposavelAntProjeto is not null
        defaultContratoShouldBeFound("nmResposavelAntProjeto.specified=true");

        // Get all the contratoList where nmResposavelAntProjeto is null
        defaultContratoShouldNotBeFound("nmResposavelAntProjeto.specified=false");
    }

    @Test
    @Transactional
    public void getAllContratoesByNmResposavelProjetoIsEqualToSomething() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where nmResposavelProjeto equals to DEFAULT_NM_RESPOSAVEL_PROJETO
        defaultContratoShouldBeFound("nmResposavelProjeto.equals=" + DEFAULT_NM_RESPOSAVEL_PROJETO);

        // Get all the contratoList where nmResposavelProjeto equals to UPDATED_NM_RESPOSAVEL_PROJETO
        defaultContratoShouldNotBeFound("nmResposavelProjeto.equals=" + UPDATED_NM_RESPOSAVEL_PROJETO);
    }

    @Test
    @Transactional
    public void getAllContratoesByNmResposavelProjetoIsInShouldWork() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where nmResposavelProjeto in DEFAULT_NM_RESPOSAVEL_PROJETO or UPDATED_NM_RESPOSAVEL_PROJETO
        defaultContratoShouldBeFound("nmResposavelProjeto.in=" + DEFAULT_NM_RESPOSAVEL_PROJETO + "," + UPDATED_NM_RESPOSAVEL_PROJETO);

        // Get all the contratoList where nmResposavelProjeto equals to UPDATED_NM_RESPOSAVEL_PROJETO
        defaultContratoShouldNotBeFound("nmResposavelProjeto.in=" + UPDATED_NM_RESPOSAVEL_PROJETO);
    }

    @Test
    @Transactional
    public void getAllContratoesByNmResposavelProjetoIsNullOrNotNull() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);

        // Get all the contratoList where nmResposavelProjeto is not null
        defaultContratoShouldBeFound("nmResposavelProjeto.specified=true");

        // Get all the contratoList where nmResposavelProjeto is null
        defaultContratoShouldNotBeFound("nmResposavelProjeto.specified=false");
    }

    @Test
    @Transactional
    public void getAllContratoesByIdProgramasProjectosIsEqualToSomething() throws Exception {
        // Initialize the database
        ProgramasProjectos idProgramasProjectos = ProgramasProjectosResourceIntTest.createEntity(em);
        em.persist(idProgramasProjectos);
        em.flush();
        contrato.setProgramasProjectos(idProgramasProjectos);
        contratoRepository.saveAndFlush(contrato);
        Long idProgramasProjectosId = idProgramasProjectos.getId();

        // Get all the contratoList where idProgramasProjectos equals to idProgramasProjectosId
        defaultContratoShouldBeFound("idProgramasProjectosId.equals=" + idProgramasProjectosId);

        // Get all the contratoList where idProgramasProjectos equals to idProgramasProjectosId + 1
        defaultContratoShouldNotBeFound("idProgramasProjectosId.equals=" + (idProgramasProjectosId + 1));
    }


    @Test
    @Transactional
    public void getAllContratoesByIdSistemaAguaIsEqualToSomething() throws Exception {
        // Initialize the database
        SistemaAgua idSistemaAgua = SistemaAguaResourceIntTest.createEntity(em);
        em.persist(idSistemaAgua);
        em.flush();
        contrato.setIdSistemaAgua(idSistemaAgua);
        contratoRepository.saveAndFlush(contrato);
        Long idSistemaAguaId = idSistemaAgua.getId();

        // Get all the contratoList where idSistemaAgua equals to idSistemaAguaId
        defaultContratoShouldBeFound("idSistemaAguaId.equals=" + idSistemaAguaId);

        // Get all the contratoList where idSistemaAgua equals to idSistemaAguaId + 1
        defaultContratoShouldNotBeFound("idSistemaAguaId.equals=" + (idSistemaAguaId + 1));
    }


    @Test
    @Transactional
    public void getAllContratoesByEmpreitadaIsEqualToSomething() throws Exception {
        // Initialize the database
        Empreitada empreitada = EmpreitadaResourceIntTest.createEntity(em);
        em.persist(empreitada);
        em.flush();
        contrato.addEmpreitada(empreitada);
        contratoRepository.saveAndFlush(contrato);
        Long empreitadaId = empreitada.getId();

        // Get all the contratoList where empreitada equals to empreitadaId
        defaultContratoShouldBeFound("empreitadaId.equals=" + empreitadaId);

        // Get all the contratoList where empreitada equals to empreitadaId + 1
        defaultContratoShouldNotBeFound("empreitadaId.equals=" + (empreitadaId + 1));
    }


    @Test
    @Transactional
    public void getAllContratoesByExecucaoIsEqualToSomething() throws Exception {
        // Initialize the database
        Execucao execucao = ExecucaoResourceIntTest.createEntity(em);
        em.persist(execucao);
        em.flush();
        contrato.addExecucao(execucao);
        contratoRepository.saveAndFlush(contrato);
        Long execucaoId = execucao.getId();

        // Get all the contratoList where execucao equals to execucaoId
        defaultContratoShouldBeFound("execucaoId.equals=" + execucaoId);

        // Get all the contratoList where execucao equals to execucaoId + 1
        defaultContratoShouldNotBeFound("execucaoId.equals=" + (execucaoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultContratoShouldBeFound(String filter) throws Exception {
        restContratoMockMvc.perform(get("/api/contratoes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contrato.getId().intValue())))
            .andExpect(jsonPath("$.[*].idContrato").value(hasItem(DEFAULT_ID_CONTRATO.intValue())))
            .andExpect(jsonPath("$.[*].tipoEmpreitada").value(hasItem(DEFAULT_TIPO_EMPREITADA.toString())))
            .andExpect(jsonPath("$.[*].dtLancamento").value(hasItem(DEFAULT_DT_LANCAMENTO.toString())))
            .andExpect(jsonPath("$.[*].nmEmpresaAdjudicitaria").value(hasItem(DEFAULT_NM_EMPRESA_ADJUDICITARIA.toString())))
            .andExpect(jsonPath("$.[*].valorContrato").value(hasItem(DEFAULT_VALOR_CONTRATO.intValue())))
            .andExpect(jsonPath("$.[*].dtAssinatura").value(hasItem(DEFAULT_DT_ASSINATURA.toString())))
            .andExpect(jsonPath("$.[*].dtFinalizacaoProcessoHomologAprov").value(hasItem(DEFAULT_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV.toString())))
            .andExpect(jsonPath("$.[*].tipoMoeda").value(hasItem(DEFAULT_TIPO_MOEDA.toString())))
            .andExpect(jsonPath("$.[*].valorAdiantamento").value(hasItem(DEFAULT_VALOR_ADIANTAMENTO.intValue())))
            .andExpect(jsonPath("$.[*].dtAdiantamento").value(hasItem(DEFAULT_DT_ADIANTAMENTO.toString())))
            .andExpect(jsonPath("$.[*].dtInicio").value(hasItem(DEFAULT_DT_INICIO.toString())))
            .andExpect(jsonPath("$.[*].prazoExecucao").value(hasItem(DEFAULT_PRAZO_EXECUCAO.intValue())))
            .andExpect(jsonPath("$.[*].dtRecepcaoProvisoria").value(hasItem(DEFAULT_DT_RECEPCAO_PROVISORIA.toString())))
            .andExpect(jsonPath("$.[*].dtRecepcaoDefinitiva").value(hasItem(DEFAULT_DT_RECEPCAO_DEFINITIVA.toString())))
            .andExpect(jsonPath("$.[*].dtRecepcaoComicionamento").value(hasItem(DEFAULT_DT_RECEPCAO_COMICIONAMENTO.toString())))
            .andExpect(jsonPath("$.[*].nmResposavelAntProjeto").value(hasItem(DEFAULT_NM_RESPOSAVEL_ANT_PROJETO.toString())))
            .andExpect(jsonPath("$.[*].nmResposavelProjeto").value(hasItem(DEFAULT_NM_RESPOSAVEL_PROJETO.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultContratoShouldNotBeFound(String filter) throws Exception {
        restContratoMockMvc.perform(get("/api/contratoes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingContrato() throws Exception {
        // Get the contrato
        restContratoMockMvc.perform(get("/api/contratoes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContrato() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);
        int databaseSizeBeforeUpdate = contratoRepository.findAll().size();

        // Update the contrato
        Contrato updatedContrato = contratoRepository.findOne(contrato.getId());
        // Disconnect from session so that the updates on updatedContrato are not directly saved in db
        em.detach(updatedContrato);
        updatedContrato
            .tipoEmpreitada(UPDATED_TIPO_EMPREITADA)
            .dtLancamento(UPDATED_DT_LANCAMENTO)
            .nmEmpresaAdjudicitaria(UPDATED_NM_EMPRESA_ADJUDICITARIA)
            .valorContrato(UPDATED_VALOR_CONTRATO)
            .dtAssinatura(UPDATED_DT_ASSINATURA)
            .dtFinalizacaoProcessoHomologAprov(UPDATED_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV)
            .tipoMoeda(UPDATED_TIPO_MOEDA)
            .valorAdiantamento(UPDATED_VALOR_ADIANTAMENTO)
            .dtAdiantamento(UPDATED_DT_ADIANTAMENTO)
            .dtInicio(UPDATED_DT_INICIO)
            .prazoExecucao(UPDATED_PRAZO_EXECUCAO)
            .dtRecepcaoProvisoria(UPDATED_DT_RECEPCAO_PROVISORIA)
            .dtRecepcaoDefinitiva(UPDATED_DT_RECEPCAO_DEFINITIVA)
            .dtRecepcaoComicionamento(UPDATED_DT_RECEPCAO_COMICIONAMENTO)
            .nmResposavelAntProjeto(UPDATED_NM_RESPOSAVEL_ANT_PROJETO)
            .nmResposavelProjeto(UPDATED_NM_RESPOSAVEL_PROJETO);
        ContratoDTO contratoDTO = contratoMapper.toDto(updatedContrato);

        restContratoMockMvc.perform(put("/api/contratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoDTO)))
            .andExpect(status().isOk());

        // Validate the Contrato in the database
        List<Contrato> contratoList = contratoRepository.findAll();
        assertThat(contratoList).hasSize(databaseSizeBeforeUpdate);
        Contrato testContrato = contratoList.get(contratoList.size() - 1);
        assertThat(testContrato.getTipoEmpreitada()).isEqualTo(UPDATED_TIPO_EMPREITADA);
        assertThat(testContrato.getDtLancamento()).isEqualTo(UPDATED_DT_LANCAMENTO);
        assertThat(testContrato.getNmEmpresaAdjudicitaria()).isEqualTo(UPDATED_NM_EMPRESA_ADJUDICITARIA);
        assertThat(testContrato.getValorContrato()).isEqualTo(UPDATED_VALOR_CONTRATO);
        assertThat(testContrato.getDtAssinatura()).isEqualTo(UPDATED_DT_ASSINATURA);
        assertThat(testContrato.getDtFinalizacaoProcessoHomologAprov()).isEqualTo(UPDATED_DT_FINALIZACAO_PROCESSO_HOMOLOG_APROV);
        assertThat(testContrato.getTipoMoeda()).isEqualTo(UPDATED_TIPO_MOEDA);
        assertThat(testContrato.getValorAdiantamento()).isEqualTo(UPDATED_VALOR_ADIANTAMENTO);
        assertThat(testContrato.getDtAdiantamento()).isEqualTo(UPDATED_DT_ADIANTAMENTO);
        assertThat(testContrato.getDtInicio()).isEqualTo(UPDATED_DT_INICIO);
        assertThat(testContrato.getPrazoExecucao()).isEqualTo(UPDATED_PRAZO_EXECUCAO);
        assertThat(testContrato.getDtRecepcaoProvisoria()).isEqualTo(UPDATED_DT_RECEPCAO_PROVISORIA);
        assertThat(testContrato.getDtRecepcaoDefinitiva()).isEqualTo(UPDATED_DT_RECEPCAO_DEFINITIVA);
        assertThat(testContrato.getDtRecepcaoComicionamento()).isEqualTo(UPDATED_DT_RECEPCAO_COMICIONAMENTO);
        assertThat(testContrato.getNmResposavelAntProjeto()).isEqualTo(UPDATED_NM_RESPOSAVEL_ANT_PROJETO);
        assertThat(testContrato.getNmResposavelProjeto()).isEqualTo(UPDATED_NM_RESPOSAVEL_PROJETO);
    }

    @Test
    @Transactional
    public void updateNonExistingContrato() throws Exception {
        int databaseSizeBeforeUpdate = contratoRepository.findAll().size();

        // Create the Contrato
        ContratoDTO contratoDTO = contratoMapper.toDto(contrato);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restContratoMockMvc.perform(put("/api/contratoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratoDTO)))
            .andExpect(status().isCreated());

        // Validate the Contrato in the database
        List<Contrato> contratoList = contratoRepository.findAll();
        assertThat(contratoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteContrato() throws Exception {
        // Initialize the database
        contratoRepository.saveAndFlush(contrato);
        int databaseSizeBeforeDelete = contratoRepository.findAll().size();

        // Get the contrato
        restContratoMockMvc.perform(delete("/api/contratoes/{id}", contrato.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Contrato> contratoList = contratoRepository.findAll();
        assertThat(contratoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contrato.class);
        Contrato contrato1 = new Contrato();
        contrato1.setId(1L);
        Contrato contrato2 = new Contrato();
        contrato2.setId(contrato1.getId());
        assertThat(contrato1).isEqualTo(contrato2);
        contrato2.setId(2L);
        assertThat(contrato1).isNotEqualTo(contrato2);
        contrato1.setId(null);
        assertThat(contrato1).isNotEqualTo(contrato2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContratoDTO.class);
        ContratoDTO contratoDTO1 = new ContratoDTO();
        contratoDTO1.setId(1L);
        ContratoDTO contratoDTO2 = new ContratoDTO();
        assertThat(contratoDTO1).isNotEqualTo(contratoDTO2);
        contratoDTO2.setId(contratoDTO1.getId());
        assertThat(contratoDTO1).isEqualTo(contratoDTO2);
        contratoDTO2.setId(2L);
        assertThat(contratoDTO1).isNotEqualTo(contratoDTO2);
        contratoDTO1.setId(null);
        assertThat(contratoDTO1).isNotEqualTo(contratoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(contratoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(contratoMapper.fromId(null)).isNull();
    }
}
