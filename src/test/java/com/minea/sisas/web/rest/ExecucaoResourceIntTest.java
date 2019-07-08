package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.Execucao;
import com.minea.sisas.domain.Situacao;
import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.domain.SistemaAgua;
import com.minea.sisas.domain.Contrato;
import com.minea.sisas.repository.ExecucaoRepository;
import com.minea.sisas.service.ExecucaoService;
import com.minea.sisas.service.dto.ExecucaoDTO;
import com.minea.sisas.service.mapper.ExecucaoMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.ExecucaoQueryService;

import com.minea.sisas.web.rest.ExecucaoResource;
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
 * Test class for the ExecucaoResource REST controller.
 *
 * @see ExecucaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class ExecucaoResourceIntTest {

    private static final Long DEFAULT_ID_EXECUCAO = 1L;
    private static final Long UPDATED_ID_EXECUCAO = 2L;

    private static final String DEFAULT_TIPO_EMPREITADA = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_EMPREITADA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DT_LANCAMENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_LANCAMENTO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_PERIDO_REFERENCIA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_PERIDO_REFERENCIA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_FIM_REFERENCIA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_FIM_REFERENCIA = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_VALOR_FACTURADO_PERIODO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALOR_FACTURADO_PERIODO = new BigDecimal(2);

    private static final LocalDate DEFAULT_DT_FACTURA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_FACTURA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NUM_FACTURA = "AAAAAAAAAA";
    private static final String UPDATED_NUM_FACTURA = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_TX_CAMBIO = new BigDecimal(1);
    private static final BigDecimal UPDATED_TX_CAMBIO = new BigDecimal(2);

    private static final String DEFAULT_CONSTRANGIMENTO = "AAAAAAAAAA";
    private static final String UPDATED_CONSTRANGIMENTO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_VALOR_PAGO_PERIODO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALOR_PAGO_PERIODO = new BigDecimal(2);

    @Autowired
    private ExecucaoRepository execucaoRepository;

    @Autowired
    private ExecucaoMapper execucaoMapper;

    @Autowired
    private ExecucaoService execucaoService;

    @Autowired
    private ExecucaoQueryService execucaoQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restExecucaoMockMvc;

    private Execucao execucao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExecucaoResource execucaoResource = new ExecucaoResource(execucaoService, execucaoQueryService);
        this.restExecucaoMockMvc = MockMvcBuilders.standaloneSetup(execucaoResource)
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
    public static Execucao createEntity(EntityManager em) {
        Execucao execucao = new Execucao()
            .tipoEmpreitada(DEFAULT_TIPO_EMPREITADA)
            .dtLancamento(DEFAULT_DT_LANCAMENTO)
            .dtPeridoReferencia(DEFAULT_DT_PERIDO_REFERENCIA)
            .dtFimReferencia(DEFAULT_DT_FIM_REFERENCIA)
            .valorFacturadoPeriodo(DEFAULT_VALOR_FACTURADO_PERIODO)
            .dtFactura(DEFAULT_DT_FACTURA)
            .numFactura(DEFAULT_NUM_FACTURA)
            .txCambio(DEFAULT_TX_CAMBIO)
            .constrangimento(DEFAULT_CONSTRANGIMENTO)
            .valorPagoPeriodo(DEFAULT_VALOR_PAGO_PERIODO);
        // Add required entity
        Situacao idSituacao = SituacaoResourceIntTest.createEntity(em);
        em.persist(idSituacao);
        em.flush();
        execucao.setIdSituacao(idSituacao);
        // Add required entity
        ProgramasProjectos idProgramasProjectos = ProgramasProjectosResourceIntTest.createEntity(em);
        em.persist(idProgramasProjectos);
        em.flush();
        execucao.setIdProgramasProjectos(idProgramasProjectos);
        return execucao;
    }

    @Before
    public void initTest() {
        execucao = createEntity(em);
    }

    @Test
    @Transactional
    public void createExecucao() throws Exception {
        int databaseSizeBeforeCreate = execucaoRepository.findAll().size();

        // Create the Execucao
        ExecucaoDTO execucaoDTO = execucaoMapper.toDto(execucao);
        restExecucaoMockMvc.perform(post("/api/execucaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(execucaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Execucao in the database
        List<Execucao> execucaoList = execucaoRepository.findAll();
        assertThat(execucaoList).hasSize(databaseSizeBeforeCreate + 1);
        Execucao testExecucao = execucaoList.get(execucaoList.size() - 1);
        assertThat(testExecucao.getTipoEmpreitada()).isEqualTo(DEFAULT_TIPO_EMPREITADA);
        assertThat(testExecucao.getDtLancamento()).isEqualTo(DEFAULT_DT_LANCAMENTO);
        assertThat(testExecucao.getDtPeridoReferencia()).isEqualTo(DEFAULT_DT_PERIDO_REFERENCIA);
        assertThat(testExecucao.getDtFimReferencia()).isEqualTo(DEFAULT_DT_FIM_REFERENCIA);
        assertThat(testExecucao.getValorFacturadoPeriodo()).isEqualTo(DEFAULT_VALOR_FACTURADO_PERIODO);
        assertThat(testExecucao.getDtFactura()).isEqualTo(DEFAULT_DT_FACTURA);
        assertThat(testExecucao.getNumFactura()).isEqualTo(DEFAULT_NUM_FACTURA);
        assertThat(testExecucao.getTxCambio()).isEqualTo(DEFAULT_TX_CAMBIO);
        assertThat(testExecucao.getConstrangimento()).isEqualTo(DEFAULT_CONSTRANGIMENTO);
        assertThat(testExecucao.getValorPagoPeriodo()).isEqualTo(DEFAULT_VALOR_PAGO_PERIODO);
    }

    @Test
    @Transactional
    public void createExecucaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = execucaoRepository.findAll().size();

        // Create the Execucao with an existing ID
        execucao.setId(1L);
        ExecucaoDTO execucaoDTO = execucaoMapper.toDto(execucao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExecucaoMockMvc.perform(post("/api/execucaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(execucaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Execucao in the database
        List<Execucao> execucaoList = execucaoRepository.findAll();
        assertThat(execucaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdExecucaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = execucaoRepository.findAll().size();
        // set the field null

        // Create the Execucao, which fails.
        ExecucaoDTO execucaoDTO = execucaoMapper.toDto(execucao);

        restExecucaoMockMvc.perform(post("/api/execucaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(execucaoDTO)))
            .andExpect(status().isBadRequest());

        List<Execucao> execucaoList = execucaoRepository.findAll();
        assertThat(execucaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTipoEmpreitadaIsRequired() throws Exception {
        int databaseSizeBeforeTest = execucaoRepository.findAll().size();
        // set the field null
        execucao.setTipoEmpreitada(null);

        // Create the Execucao, which fails.
        ExecucaoDTO execucaoDTO = execucaoMapper.toDto(execucao);

        restExecucaoMockMvc.perform(post("/api/execucaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(execucaoDTO)))
            .andExpect(status().isBadRequest());

        List<Execucao> execucaoList = execucaoRepository.findAll();
        assertThat(execucaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDtLancamentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = execucaoRepository.findAll().size();
        // set the field null
        execucao.setDtLancamento(null);

        // Create the Execucao, which fails.
        ExecucaoDTO execucaoDTO = execucaoMapper.toDto(execucao);

        restExecucaoMockMvc.perform(post("/api/execucaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(execucaoDTO)))
            .andExpect(status().isBadRequest());

        List<Execucao> execucaoList = execucaoRepository.findAll();
        assertThat(execucaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDtPeridoReferenciaIsRequired() throws Exception {
        int databaseSizeBeforeTest = execucaoRepository.findAll().size();
        // set the field null
        execucao.setDtPeridoReferencia(null);

        // Create the Execucao, which fails.
        ExecucaoDTO execucaoDTO = execucaoMapper.toDto(execucao);

        restExecucaoMockMvc.perform(post("/api/execucaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(execucaoDTO)))
            .andExpect(status().isBadRequest());

        List<Execucao> execucaoList = execucaoRepository.findAll();
        assertThat(execucaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDtFimReferenciaIsRequired() throws Exception {
        int databaseSizeBeforeTest = execucaoRepository.findAll().size();
        // set the field null
        execucao.setDtFimReferencia(null);

        // Create the Execucao, which fails.
        ExecucaoDTO execucaoDTO = execucaoMapper.toDto(execucao);

        restExecucaoMockMvc.perform(post("/api/execucaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(execucaoDTO)))
            .andExpect(status().isBadRequest());

        List<Execucao> execucaoList = execucaoRepository.findAll();
        assertThat(execucaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValorFacturadoPeriodoIsRequired() throws Exception {
        int databaseSizeBeforeTest = execucaoRepository.findAll().size();
        // set the field null
        execucao.setValorFacturadoPeriodo(null);

        // Create the Execucao, which fails.
        ExecucaoDTO execucaoDTO = execucaoMapper.toDto(execucao);

        restExecucaoMockMvc.perform(post("/api/execucaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(execucaoDTO)))
            .andExpect(status().isBadRequest());

        List<Execucao> execucaoList = execucaoRepository.findAll();
        assertThat(execucaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDtFacturaIsRequired() throws Exception {
        int databaseSizeBeforeTest = execucaoRepository.findAll().size();
        // set the field null
        execucao.setDtFactura(null);

        // Create the Execucao, which fails.
        ExecucaoDTO execucaoDTO = execucaoMapper.toDto(execucao);

        restExecucaoMockMvc.perform(post("/api/execucaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(execucaoDTO)))
            .andExpect(status().isBadRequest());

        List<Execucao> execucaoList = execucaoRepository.findAll();
        assertThat(execucaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumFacturaIsRequired() throws Exception {
        int databaseSizeBeforeTest = execucaoRepository.findAll().size();
        // set the field null
        execucao.setNumFactura(null);

        // Create the Execucao, which fails.
        ExecucaoDTO execucaoDTO = execucaoMapper.toDto(execucao);

        restExecucaoMockMvc.perform(post("/api/execucaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(execucaoDTO)))
            .andExpect(status().isBadRequest());

        List<Execucao> execucaoList = execucaoRepository.findAll();
        assertThat(execucaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValorPagoPeriodoIsRequired() throws Exception {
        int databaseSizeBeforeTest = execucaoRepository.findAll().size();
        // set the field null
        execucao.setValorPagoPeriodo(null);

        // Create the Execucao, which fails.
        ExecucaoDTO execucaoDTO = execucaoMapper.toDto(execucao);

        restExecucaoMockMvc.perform(post("/api/execucaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(execucaoDTO)))
            .andExpect(status().isBadRequest());

        List<Execucao> execucaoList = execucaoRepository.findAll();
        assertThat(execucaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllExecucaos() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList
        restExecucaoMockMvc.perform(get("/api/execucaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(execucao.getId().intValue())))
            .andExpect(jsonPath("$.[*].idExecucao").value(hasItem(DEFAULT_ID_EXECUCAO.intValue())))
            .andExpect(jsonPath("$.[*].tipoEmpreitada").value(hasItem(DEFAULT_TIPO_EMPREITADA.toString())))
            .andExpect(jsonPath("$.[*].dtLancamento").value(hasItem(DEFAULT_DT_LANCAMENTO.toString())))
            .andExpect(jsonPath("$.[*].dtPeridoReferencia").value(hasItem(DEFAULT_DT_PERIDO_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].dtFimReferencia").value(hasItem(DEFAULT_DT_FIM_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].valorFacturadoPeriodo").value(hasItem(DEFAULT_VALOR_FACTURADO_PERIODO.intValue())))
            .andExpect(jsonPath("$.[*].dtFactura").value(hasItem(DEFAULT_DT_FACTURA.toString())))
            .andExpect(jsonPath("$.[*].numFactura").value(hasItem(DEFAULT_NUM_FACTURA.toString())))
            .andExpect(jsonPath("$.[*].txCambio").value(hasItem(DEFAULT_TX_CAMBIO.intValue())))
            .andExpect(jsonPath("$.[*].constrangimento").value(hasItem(DEFAULT_CONSTRANGIMENTO.toString())))
            .andExpect(jsonPath("$.[*].valorPagoPeriodo").value(hasItem(DEFAULT_VALOR_PAGO_PERIODO.intValue())));
    }

    @Test
    @Transactional
    public void getExecucao() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get the execucao
        restExecucaoMockMvc.perform(get("/api/execucaos/{id}", execucao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(execucao.getId().intValue()))
            .andExpect(jsonPath("$.idExecucao").value(DEFAULT_ID_EXECUCAO.intValue()))
            .andExpect(jsonPath("$.tipoEmpreitada").value(DEFAULT_TIPO_EMPREITADA.toString()))
            .andExpect(jsonPath("$.dtLancamento").value(DEFAULT_DT_LANCAMENTO.toString()))
            .andExpect(jsonPath("$.dtPeridoReferencia").value(DEFAULT_DT_PERIDO_REFERENCIA.toString()))
            .andExpect(jsonPath("$.dtFimReferencia").value(DEFAULT_DT_FIM_REFERENCIA.toString()))
            .andExpect(jsonPath("$.valorFacturadoPeriodo").value(DEFAULT_VALOR_FACTURADO_PERIODO.intValue()))
            .andExpect(jsonPath("$.dtFactura").value(DEFAULT_DT_FACTURA.toString()))
            .andExpect(jsonPath("$.numFactura").value(DEFAULT_NUM_FACTURA.toString()))
            .andExpect(jsonPath("$.txCambio").value(DEFAULT_TX_CAMBIO.intValue()))
            .andExpect(jsonPath("$.constrangimento").value(DEFAULT_CONSTRANGIMENTO.toString()))
            .andExpect(jsonPath("$.valorPagoPeriodo").value(DEFAULT_VALOR_PAGO_PERIODO.intValue()));
    }

    @Test
    @Transactional
    public void getAllExecucaosByIdExecucaoIsEqualToSomething() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where idExecucao equals to DEFAULT_ID_EXECUCAO
        defaultExecucaoShouldBeFound("idExecucao.equals=" + DEFAULT_ID_EXECUCAO);

        // Get all the execucaoList where idExecucao equals to UPDATED_ID_EXECUCAO
        defaultExecucaoShouldNotBeFound("idExecucao.equals=" + UPDATED_ID_EXECUCAO);
    }

    @Test
    @Transactional
    public void getAllExecucaosByIdExecucaoIsInShouldWork() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where idExecucao in DEFAULT_ID_EXECUCAO or UPDATED_ID_EXECUCAO
        defaultExecucaoShouldBeFound("idExecucao.in=" + DEFAULT_ID_EXECUCAO + "," + UPDATED_ID_EXECUCAO);

        // Get all the execucaoList where idExecucao equals to UPDATED_ID_EXECUCAO
        defaultExecucaoShouldNotBeFound("idExecucao.in=" + UPDATED_ID_EXECUCAO);
    }

    @Test
    @Transactional
    public void getAllExecucaosByIdExecucaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where idExecucao is not null
        defaultExecucaoShouldBeFound("idExecucao.specified=true");

        // Get all the execucaoList where idExecucao is null
        defaultExecucaoShouldNotBeFound("idExecucao.specified=false");
    }

    @Test
    @Transactional
    public void getAllExecucaosByIdExecucaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where idExecucao greater than or equals to DEFAULT_ID_EXECUCAO
        defaultExecucaoShouldBeFound("idExecucao.greaterOrEqualThan=" + DEFAULT_ID_EXECUCAO);

        // Get all the execucaoList where idExecucao greater than or equals to UPDATED_ID_EXECUCAO
        defaultExecucaoShouldNotBeFound("idExecucao.greaterOrEqualThan=" + UPDATED_ID_EXECUCAO);
    }

    @Test
    @Transactional
    public void getAllExecucaosByIdExecucaoIsLessThanSomething() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where idExecucao less than or equals to DEFAULT_ID_EXECUCAO
        defaultExecucaoShouldNotBeFound("idExecucao.lessThan=" + DEFAULT_ID_EXECUCAO);

        // Get all the execucaoList where idExecucao less than or equals to UPDATED_ID_EXECUCAO
        defaultExecucaoShouldBeFound("idExecucao.lessThan=" + UPDATED_ID_EXECUCAO);
    }


    @Test
    @Transactional
    public void getAllExecucaosByTipoEmpreitadaIsEqualToSomething() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where tipoEmpreitada equals to DEFAULT_TIPO_EMPREITADA
        defaultExecucaoShouldBeFound("tipoEmpreitada.equals=" + DEFAULT_TIPO_EMPREITADA);

        // Get all the execucaoList where tipoEmpreitada equals to UPDATED_TIPO_EMPREITADA
        defaultExecucaoShouldNotBeFound("tipoEmpreitada.equals=" + UPDATED_TIPO_EMPREITADA);
    }

    @Test
    @Transactional
    public void getAllExecucaosByTipoEmpreitadaIsInShouldWork() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where tipoEmpreitada in DEFAULT_TIPO_EMPREITADA or UPDATED_TIPO_EMPREITADA
        defaultExecucaoShouldBeFound("tipoEmpreitada.in=" + DEFAULT_TIPO_EMPREITADA + "," + UPDATED_TIPO_EMPREITADA);

        // Get all the execucaoList where tipoEmpreitada equals to UPDATED_TIPO_EMPREITADA
        defaultExecucaoShouldNotBeFound("tipoEmpreitada.in=" + UPDATED_TIPO_EMPREITADA);
    }

    @Test
    @Transactional
    public void getAllExecucaosByTipoEmpreitadaIsNullOrNotNull() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where tipoEmpreitada is not null
        defaultExecucaoShouldBeFound("tipoEmpreitada.specified=true");

        // Get all the execucaoList where tipoEmpreitada is null
        defaultExecucaoShouldNotBeFound("tipoEmpreitada.specified=false");
    }

    @Test
    @Transactional
    public void getAllExecucaosByDtLancamentoIsEqualToSomething() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where dtLancamento equals to DEFAULT_DT_LANCAMENTO
        defaultExecucaoShouldBeFound("dtLancamento.equals=" + DEFAULT_DT_LANCAMENTO);

        // Get all the execucaoList where dtLancamento equals to UPDATED_DT_LANCAMENTO
        defaultExecucaoShouldNotBeFound("dtLancamento.equals=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllExecucaosByDtLancamentoIsInShouldWork() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where dtLancamento in DEFAULT_DT_LANCAMENTO or UPDATED_DT_LANCAMENTO
        defaultExecucaoShouldBeFound("dtLancamento.in=" + DEFAULT_DT_LANCAMENTO + "," + UPDATED_DT_LANCAMENTO);

        // Get all the execucaoList where dtLancamento equals to UPDATED_DT_LANCAMENTO
        defaultExecucaoShouldNotBeFound("dtLancamento.in=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllExecucaosByDtLancamentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where dtLancamento is not null
        defaultExecucaoShouldBeFound("dtLancamento.specified=true");

        // Get all the execucaoList where dtLancamento is null
        defaultExecucaoShouldNotBeFound("dtLancamento.specified=false");
    }

    @Test
    @Transactional
    public void getAllExecucaosByDtLancamentoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where dtLancamento greater than or equals to DEFAULT_DT_LANCAMENTO
        defaultExecucaoShouldBeFound("dtLancamento.greaterOrEqualThan=" + DEFAULT_DT_LANCAMENTO);

        // Get all the execucaoList where dtLancamento greater than or equals to UPDATED_DT_LANCAMENTO
        defaultExecucaoShouldNotBeFound("dtLancamento.greaterOrEqualThan=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllExecucaosByDtLancamentoIsLessThanSomething() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where dtLancamento less than or equals to DEFAULT_DT_LANCAMENTO
        defaultExecucaoShouldNotBeFound("dtLancamento.lessThan=" + DEFAULT_DT_LANCAMENTO);

        // Get all the execucaoList where dtLancamento less than or equals to UPDATED_DT_LANCAMENTO
        defaultExecucaoShouldBeFound("dtLancamento.lessThan=" + UPDATED_DT_LANCAMENTO);
    }


    @Test
    @Transactional
    public void getAllExecucaosByDtPeridoReferenciaIsEqualToSomething() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where dtPeridoReferencia equals to DEFAULT_DT_PERIDO_REFERENCIA
        defaultExecucaoShouldBeFound("dtPeridoReferencia.equals=" + DEFAULT_DT_PERIDO_REFERENCIA);

        // Get all the execucaoList where dtPeridoReferencia equals to UPDATED_DT_PERIDO_REFERENCIA
        defaultExecucaoShouldNotBeFound("dtPeridoReferencia.equals=" + UPDATED_DT_PERIDO_REFERENCIA);
    }

    @Test
    @Transactional
    public void getAllExecucaosByDtPeridoReferenciaIsInShouldWork() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where dtPeridoReferencia in DEFAULT_DT_PERIDO_REFERENCIA or UPDATED_DT_PERIDO_REFERENCIA
        defaultExecucaoShouldBeFound("dtPeridoReferencia.in=" + DEFAULT_DT_PERIDO_REFERENCIA + "," + UPDATED_DT_PERIDO_REFERENCIA);

        // Get all the execucaoList where dtPeridoReferencia equals to UPDATED_DT_PERIDO_REFERENCIA
        defaultExecucaoShouldNotBeFound("dtPeridoReferencia.in=" + UPDATED_DT_PERIDO_REFERENCIA);
    }

    @Test
    @Transactional
    public void getAllExecucaosByDtPeridoReferenciaIsNullOrNotNull() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where dtPeridoReferencia is not null
        defaultExecucaoShouldBeFound("dtPeridoReferencia.specified=true");

        // Get all the execucaoList where dtPeridoReferencia is null
        defaultExecucaoShouldNotBeFound("dtPeridoReferencia.specified=false");
    }

    @Test
    @Transactional
    public void getAllExecucaosByDtPeridoReferenciaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where dtPeridoReferencia greater than or equals to DEFAULT_DT_PERIDO_REFERENCIA
        defaultExecucaoShouldBeFound("dtPeridoReferencia.greaterOrEqualThan=" + DEFAULT_DT_PERIDO_REFERENCIA);

        // Get all the execucaoList where dtPeridoReferencia greater than or equals to UPDATED_DT_PERIDO_REFERENCIA
        defaultExecucaoShouldNotBeFound("dtPeridoReferencia.greaterOrEqualThan=" + UPDATED_DT_PERIDO_REFERENCIA);
    }

    @Test
    @Transactional
    public void getAllExecucaosByDtPeridoReferenciaIsLessThanSomething() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where dtPeridoReferencia less than or equals to DEFAULT_DT_PERIDO_REFERENCIA
        defaultExecucaoShouldNotBeFound("dtPeridoReferencia.lessThan=" + DEFAULT_DT_PERIDO_REFERENCIA);

        // Get all the execucaoList where dtPeridoReferencia less than or equals to UPDATED_DT_PERIDO_REFERENCIA
        defaultExecucaoShouldBeFound("dtPeridoReferencia.lessThan=" + UPDATED_DT_PERIDO_REFERENCIA);
    }


    @Test
    @Transactional
    public void getAllExecucaosByDtFimReferenciaIsEqualToSomething() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where dtFimReferencia equals to DEFAULT_DT_FIM_REFERENCIA
        defaultExecucaoShouldBeFound("dtFimReferencia.equals=" + DEFAULT_DT_FIM_REFERENCIA);

        // Get all the execucaoList where dtFimReferencia equals to UPDATED_DT_FIM_REFERENCIA
        defaultExecucaoShouldNotBeFound("dtFimReferencia.equals=" + UPDATED_DT_FIM_REFERENCIA);
    }

    @Test
    @Transactional
    public void getAllExecucaosByDtFimReferenciaIsInShouldWork() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where dtFimReferencia in DEFAULT_DT_FIM_REFERENCIA or UPDATED_DT_FIM_REFERENCIA
        defaultExecucaoShouldBeFound("dtFimReferencia.in=" + DEFAULT_DT_FIM_REFERENCIA + "," + UPDATED_DT_FIM_REFERENCIA);

        // Get all the execucaoList where dtFimReferencia equals to UPDATED_DT_FIM_REFERENCIA
        defaultExecucaoShouldNotBeFound("dtFimReferencia.in=" + UPDATED_DT_FIM_REFERENCIA);
    }

    @Test
    @Transactional
    public void getAllExecucaosByDtFimReferenciaIsNullOrNotNull() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where dtFimReferencia is not null
        defaultExecucaoShouldBeFound("dtFimReferencia.specified=true");

        // Get all the execucaoList where dtFimReferencia is null
        defaultExecucaoShouldNotBeFound("dtFimReferencia.specified=false");
    }

    @Test
    @Transactional
    public void getAllExecucaosByDtFimReferenciaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where dtFimReferencia greater than or equals to DEFAULT_DT_FIM_REFERENCIA
        defaultExecucaoShouldBeFound("dtFimReferencia.greaterOrEqualThan=" + DEFAULT_DT_FIM_REFERENCIA);

        // Get all the execucaoList where dtFimReferencia greater than or equals to UPDATED_DT_FIM_REFERENCIA
        defaultExecucaoShouldNotBeFound("dtFimReferencia.greaterOrEqualThan=" + UPDATED_DT_FIM_REFERENCIA);
    }

    @Test
    @Transactional
    public void getAllExecucaosByDtFimReferenciaIsLessThanSomething() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where dtFimReferencia less than or equals to DEFAULT_DT_FIM_REFERENCIA
        defaultExecucaoShouldNotBeFound("dtFimReferencia.lessThan=" + DEFAULT_DT_FIM_REFERENCIA);

        // Get all the execucaoList where dtFimReferencia less than or equals to UPDATED_DT_FIM_REFERENCIA
        defaultExecucaoShouldBeFound("dtFimReferencia.lessThan=" + UPDATED_DT_FIM_REFERENCIA);
    }


    @Test
    @Transactional
    public void getAllExecucaosByValorFacturadoPeriodoIsEqualToSomething() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where valorFacturadoPeriodo equals to DEFAULT_VALOR_FACTURADO_PERIODO
        defaultExecucaoShouldBeFound("valorFacturadoPeriodo.equals=" + DEFAULT_VALOR_FACTURADO_PERIODO);

        // Get all the execucaoList where valorFacturadoPeriodo equals to UPDATED_VALOR_FACTURADO_PERIODO
        defaultExecucaoShouldNotBeFound("valorFacturadoPeriodo.equals=" + UPDATED_VALOR_FACTURADO_PERIODO);
    }

    @Test
    @Transactional
    public void getAllExecucaosByValorFacturadoPeriodoIsInShouldWork() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where valorFacturadoPeriodo in DEFAULT_VALOR_FACTURADO_PERIODO or UPDATED_VALOR_FACTURADO_PERIODO
        defaultExecucaoShouldBeFound("valorFacturadoPeriodo.in=" + DEFAULT_VALOR_FACTURADO_PERIODO + "," + UPDATED_VALOR_FACTURADO_PERIODO);

        // Get all the execucaoList where valorFacturadoPeriodo equals to UPDATED_VALOR_FACTURADO_PERIODO
        defaultExecucaoShouldNotBeFound("valorFacturadoPeriodo.in=" + UPDATED_VALOR_FACTURADO_PERIODO);
    }

    @Test
    @Transactional
    public void getAllExecucaosByValorFacturadoPeriodoIsNullOrNotNull() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where valorFacturadoPeriodo is not null
        defaultExecucaoShouldBeFound("valorFacturadoPeriodo.specified=true");

        // Get all the execucaoList where valorFacturadoPeriodo is null
        defaultExecucaoShouldNotBeFound("valorFacturadoPeriodo.specified=false");
    }

    @Test
    @Transactional
    public void getAllExecucaosByDtFacturaIsEqualToSomething() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where dtFactura equals to DEFAULT_DT_FACTURA
        defaultExecucaoShouldBeFound("dtFactura.equals=" + DEFAULT_DT_FACTURA);

        // Get all the execucaoList where dtFactura equals to UPDATED_DT_FACTURA
        defaultExecucaoShouldNotBeFound("dtFactura.equals=" + UPDATED_DT_FACTURA);
    }

    @Test
    @Transactional
    public void getAllExecucaosByDtFacturaIsInShouldWork() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where dtFactura in DEFAULT_DT_FACTURA or UPDATED_DT_FACTURA
        defaultExecucaoShouldBeFound("dtFactura.in=" + DEFAULT_DT_FACTURA + "," + UPDATED_DT_FACTURA);

        // Get all the execucaoList where dtFactura equals to UPDATED_DT_FACTURA
        defaultExecucaoShouldNotBeFound("dtFactura.in=" + UPDATED_DT_FACTURA);
    }

    @Test
    @Transactional
    public void getAllExecucaosByDtFacturaIsNullOrNotNull() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where dtFactura is not null
        defaultExecucaoShouldBeFound("dtFactura.specified=true");

        // Get all the execucaoList where dtFactura is null
        defaultExecucaoShouldNotBeFound("dtFactura.specified=false");
    }

    @Test
    @Transactional
    public void getAllExecucaosByDtFacturaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where dtFactura greater than or equals to DEFAULT_DT_FACTURA
        defaultExecucaoShouldBeFound("dtFactura.greaterOrEqualThan=" + DEFAULT_DT_FACTURA);

        // Get all the execucaoList where dtFactura greater than or equals to UPDATED_DT_FACTURA
        defaultExecucaoShouldNotBeFound("dtFactura.greaterOrEqualThan=" + UPDATED_DT_FACTURA);
    }

    @Test
    @Transactional
    public void getAllExecucaosByDtFacturaIsLessThanSomething() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where dtFactura less than or equals to DEFAULT_DT_FACTURA
        defaultExecucaoShouldNotBeFound("dtFactura.lessThan=" + DEFAULT_DT_FACTURA);

        // Get all the execucaoList where dtFactura less than or equals to UPDATED_DT_FACTURA
        defaultExecucaoShouldBeFound("dtFactura.lessThan=" + UPDATED_DT_FACTURA);
    }


    @Test
    @Transactional
    public void getAllExecucaosByNumFacturaIsEqualToSomething() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where numFactura equals to DEFAULT_NUM_FACTURA
        defaultExecucaoShouldBeFound("numFactura.equals=" + DEFAULT_NUM_FACTURA);

        // Get all the execucaoList where numFactura equals to UPDATED_NUM_FACTURA
        defaultExecucaoShouldNotBeFound("numFactura.equals=" + UPDATED_NUM_FACTURA);
    }

    @Test
    @Transactional
    public void getAllExecucaosByNumFacturaIsInShouldWork() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where numFactura in DEFAULT_NUM_FACTURA or UPDATED_NUM_FACTURA
        defaultExecucaoShouldBeFound("numFactura.in=" + DEFAULT_NUM_FACTURA + "," + UPDATED_NUM_FACTURA);

        // Get all the execucaoList where numFactura equals to UPDATED_NUM_FACTURA
        defaultExecucaoShouldNotBeFound("numFactura.in=" + UPDATED_NUM_FACTURA);
    }

    @Test
    @Transactional
    public void getAllExecucaosByNumFacturaIsNullOrNotNull() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where numFactura is not null
        defaultExecucaoShouldBeFound("numFactura.specified=true");

        // Get all the execucaoList where numFactura is null
        defaultExecucaoShouldNotBeFound("numFactura.specified=false");
    }

    @Test
    @Transactional
    public void getAllExecucaosByTxCambioIsEqualToSomething() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where txCambio equals to DEFAULT_TX_CAMBIO
        defaultExecucaoShouldBeFound("txCambio.equals=" + DEFAULT_TX_CAMBIO);

        // Get all the execucaoList where txCambio equals to UPDATED_TX_CAMBIO
        defaultExecucaoShouldNotBeFound("txCambio.equals=" + UPDATED_TX_CAMBIO);
    }

    @Test
    @Transactional
    public void getAllExecucaosByTxCambioIsInShouldWork() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where txCambio in DEFAULT_TX_CAMBIO or UPDATED_TX_CAMBIO
        defaultExecucaoShouldBeFound("txCambio.in=" + DEFAULT_TX_CAMBIO + "," + UPDATED_TX_CAMBIO);

        // Get all the execucaoList where txCambio equals to UPDATED_TX_CAMBIO
        defaultExecucaoShouldNotBeFound("txCambio.in=" + UPDATED_TX_CAMBIO);
    }

    @Test
    @Transactional
    public void getAllExecucaosByTxCambioIsNullOrNotNull() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where txCambio is not null
        defaultExecucaoShouldBeFound("txCambio.specified=true");

        // Get all the execucaoList where txCambio is null
        defaultExecucaoShouldNotBeFound("txCambio.specified=false");
    }

    @Test
    @Transactional
    public void getAllExecucaosByConstrangimentoIsEqualToSomething() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where constrangimento equals to DEFAULT_CONSTRANGIMENTO
        defaultExecucaoShouldBeFound("constrangimento.equals=" + DEFAULT_CONSTRANGIMENTO);

        // Get all the execucaoList where constrangimento equals to UPDATED_CONSTRANGIMENTO
        defaultExecucaoShouldNotBeFound("constrangimento.equals=" + UPDATED_CONSTRANGIMENTO);
    }

    @Test
    @Transactional
    public void getAllExecucaosByConstrangimentoIsInShouldWork() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where constrangimento in DEFAULT_CONSTRANGIMENTO or UPDATED_CONSTRANGIMENTO
        defaultExecucaoShouldBeFound("constrangimento.in=" + DEFAULT_CONSTRANGIMENTO + "," + UPDATED_CONSTRANGIMENTO);

        // Get all the execucaoList where constrangimento equals to UPDATED_CONSTRANGIMENTO
        defaultExecucaoShouldNotBeFound("constrangimento.in=" + UPDATED_CONSTRANGIMENTO);
    }

    @Test
    @Transactional
    public void getAllExecucaosByConstrangimentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where constrangimento is not null
        defaultExecucaoShouldBeFound("constrangimento.specified=true");

        // Get all the execucaoList where constrangimento is null
        defaultExecucaoShouldNotBeFound("constrangimento.specified=false");
    }

    @Test
    @Transactional
    public void getAllExecucaosByValorPagoPeriodoIsEqualToSomething() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where valorPagoPeriodo equals to DEFAULT_VALOR_PAGO_PERIODO
        defaultExecucaoShouldBeFound("valorPagoPeriodo.equals=" + DEFAULT_VALOR_PAGO_PERIODO);

        // Get all the execucaoList where valorPagoPeriodo equals to UPDATED_VALOR_PAGO_PERIODO
        defaultExecucaoShouldNotBeFound("valorPagoPeriodo.equals=" + UPDATED_VALOR_PAGO_PERIODO);
    }

    @Test
    @Transactional
    public void getAllExecucaosByValorPagoPeriodoIsInShouldWork() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where valorPagoPeriodo in DEFAULT_VALOR_PAGO_PERIODO or UPDATED_VALOR_PAGO_PERIODO
        defaultExecucaoShouldBeFound("valorPagoPeriodo.in=" + DEFAULT_VALOR_PAGO_PERIODO + "," + UPDATED_VALOR_PAGO_PERIODO);

        // Get all the execucaoList where valorPagoPeriodo equals to UPDATED_VALOR_PAGO_PERIODO
        defaultExecucaoShouldNotBeFound("valorPagoPeriodo.in=" + UPDATED_VALOR_PAGO_PERIODO);
    }

    @Test
    @Transactional
    public void getAllExecucaosByValorPagoPeriodoIsNullOrNotNull() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);

        // Get all the execucaoList where valorPagoPeriodo is not null
        defaultExecucaoShouldBeFound("valorPagoPeriodo.specified=true");

        // Get all the execucaoList where valorPagoPeriodo is null
        defaultExecucaoShouldNotBeFound("valorPagoPeriodo.specified=false");
    }

    @Test
    @Transactional
    public void getAllExecucaosByIdSituacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        Situacao idSituacao = SituacaoResourceIntTest.createEntity(em);
        em.persist(idSituacao);
        em.flush();
        execucao.setIdSituacao(idSituacao);
        execucaoRepository.saveAndFlush(execucao);
        Long idSituacaoId = idSituacao.getId();

        // Get all the execucaoList where idSituacao equals to situacao
        defaultExecucaoShouldBeFound("situacao.equals=" + idSituacaoId);

        // Get all the execucaoList where idSituacao equals to situacao + 1
        defaultExecucaoShouldNotBeFound("situacao.equals=" + (idSituacaoId + 1));
    }


    @Test
    @Transactional
    public void getAllExecucaosByIdProgramasProjectosIsEqualToSomething() throws Exception {
        // Initialize the database
        ProgramasProjectos idProgramasProjectos = ProgramasProjectosResourceIntTest.createEntity(em);
        em.persist(idProgramasProjectos);
        em.flush();
        execucao.setIdProgramasProjectos(idProgramasProjectos);
        execucaoRepository.saveAndFlush(execucao);
        Long idProgramasProjectosId = idProgramasProjectos.getId();

        // Get all the execucaoList where idProgramasProjectos equals to idProgramasProjectosId
        defaultExecucaoShouldBeFound("idProgramasProjectosId.equals=" + idProgramasProjectosId);

        // Get all the execucaoList where idProgramasProjectos equals to idProgramasProjectosId + 1
        defaultExecucaoShouldNotBeFound("idProgramasProjectosId.equals=" + (idProgramasProjectosId + 1));
    }


    @Test
    @Transactional
    public void getAllExecucaosByIdSistemaAguaIsEqualToSomething() throws Exception {
        // Initialize the database
        SistemaAgua idSistemaAgua = SistemaAguaResourceIntTest.createEntity(em);
        em.persist(idSistemaAgua);
        em.flush();
        execucao.setIdSistemaAgua(idSistemaAgua);
        execucaoRepository.saveAndFlush(execucao);
        Long idSistemaAguaId = idSistemaAgua.getId();

        // Get all the execucaoList where idSistemaAgua equals to idSistemaAguaId
        defaultExecucaoShouldBeFound("idSistemaAguaId.equals=" + idSistemaAguaId);

        // Get all the execucaoList where idSistemaAgua equals to idSistemaAguaId + 1
        defaultExecucaoShouldNotBeFound("idSistemaAguaId.equals=" + (idSistemaAguaId + 1));
    }


    @Test
    @Transactional
    public void getAllExecucaosByIdContratoIsEqualToSomething() throws Exception {
        // Initialize the database
        Contrato idContrato = ContratoResourceIntTest.createEntity(em);
        em.persist(idContrato);
        em.flush();
        execucao.setIdContrato(idContrato);
        execucaoRepository.saveAndFlush(execucao);
        Long idContratoId = idContrato.getId();

        // Get all the execucaoList where idContrato equals to idContratoId
        defaultExecucaoShouldBeFound("idContratoId.equals=" + idContratoId);

        // Get all the execucaoList where idContrato equals to idContratoId + 1
        defaultExecucaoShouldNotBeFound("idContratoId.equals=" + (idContratoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultExecucaoShouldBeFound(String filter) throws Exception {
        restExecucaoMockMvc.perform(get("/api/execucaos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(execucao.getId().intValue())))
            .andExpect(jsonPath("$.[*].idExecucao").value(hasItem(DEFAULT_ID_EXECUCAO.intValue())))
            .andExpect(jsonPath("$.[*].tipoEmpreitada").value(hasItem(DEFAULT_TIPO_EMPREITADA.toString())))
            .andExpect(jsonPath("$.[*].dtLancamento").value(hasItem(DEFAULT_DT_LANCAMENTO.toString())))
            .andExpect(jsonPath("$.[*].dtPeridoReferencia").value(hasItem(DEFAULT_DT_PERIDO_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].dtFimReferencia").value(hasItem(DEFAULT_DT_FIM_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].valorFacturadoPeriodo").value(hasItem(DEFAULT_VALOR_FACTURADO_PERIODO.intValue())))
            .andExpect(jsonPath("$.[*].dtFactura").value(hasItem(DEFAULT_DT_FACTURA.toString())))
            .andExpect(jsonPath("$.[*].numFactura").value(hasItem(DEFAULT_NUM_FACTURA.toString())))
            .andExpect(jsonPath("$.[*].txCambio").value(hasItem(DEFAULT_TX_CAMBIO.intValue())))
            .andExpect(jsonPath("$.[*].constrangimento").value(hasItem(DEFAULT_CONSTRANGIMENTO.toString())))
            .andExpect(jsonPath("$.[*].valorPagoPeriodo").value(hasItem(DEFAULT_VALOR_PAGO_PERIODO.intValue())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultExecucaoShouldNotBeFound(String filter) throws Exception {
        restExecucaoMockMvc.perform(get("/api/execucaos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingExecucao() throws Exception {
        // Get the execucao
        restExecucaoMockMvc.perform(get("/api/execucaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExecucao() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);
        int databaseSizeBeforeUpdate = execucaoRepository.findAll().size();

        // Update the execucao
        Execucao updatedExecucao = execucaoRepository.findOne(execucao.getId());
        // Disconnect from session so that the updates on updatedExecucao are not directly saved in db
        em.detach(updatedExecucao);
        updatedExecucao
            .tipoEmpreitada(UPDATED_TIPO_EMPREITADA)
            .dtLancamento(UPDATED_DT_LANCAMENTO)
            .dtPeridoReferencia(UPDATED_DT_PERIDO_REFERENCIA)
            .dtFimReferencia(UPDATED_DT_FIM_REFERENCIA)
            .valorFacturadoPeriodo(UPDATED_VALOR_FACTURADO_PERIODO)
            .dtFactura(UPDATED_DT_FACTURA)
            .numFactura(UPDATED_NUM_FACTURA)
            .txCambio(UPDATED_TX_CAMBIO)
            .constrangimento(UPDATED_CONSTRANGIMENTO)
            .valorPagoPeriodo(UPDATED_VALOR_PAGO_PERIODO);
        ExecucaoDTO execucaoDTO = execucaoMapper.toDto(updatedExecucao);

        restExecucaoMockMvc.perform(put("/api/execucaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(execucaoDTO)))
            .andExpect(status().isOk());

        // Validate the Execucao in the database
        List<Execucao> execucaoList = execucaoRepository.findAll();
        assertThat(execucaoList).hasSize(databaseSizeBeforeUpdate);
        Execucao testExecucao = execucaoList.get(execucaoList.size() - 1);
        assertThat(testExecucao.getTipoEmpreitada()).isEqualTo(UPDATED_TIPO_EMPREITADA);
        assertThat(testExecucao.getDtLancamento()).isEqualTo(UPDATED_DT_LANCAMENTO);
        assertThat(testExecucao.getDtPeridoReferencia()).isEqualTo(UPDATED_DT_PERIDO_REFERENCIA);
        assertThat(testExecucao.getDtFimReferencia()).isEqualTo(UPDATED_DT_FIM_REFERENCIA);
        assertThat(testExecucao.getValorFacturadoPeriodo()).isEqualTo(UPDATED_VALOR_FACTURADO_PERIODO);
        assertThat(testExecucao.getDtFactura()).isEqualTo(UPDATED_DT_FACTURA);
        assertThat(testExecucao.getNumFactura()).isEqualTo(UPDATED_NUM_FACTURA);
        assertThat(testExecucao.getTxCambio()).isEqualTo(UPDATED_TX_CAMBIO);
        assertThat(testExecucao.getConstrangimento()).isEqualTo(UPDATED_CONSTRANGIMENTO);
        assertThat(testExecucao.getValorPagoPeriodo()).isEqualTo(UPDATED_VALOR_PAGO_PERIODO);
    }

    @Test
    @Transactional
    public void updateNonExistingExecucao() throws Exception {
        int databaseSizeBeforeUpdate = execucaoRepository.findAll().size();

        // Create the Execucao
        ExecucaoDTO execucaoDTO = execucaoMapper.toDto(execucao);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restExecucaoMockMvc.perform(put("/api/execucaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(execucaoDTO)))
            .andExpect(status().isCreated());

        // Validate the Execucao in the database
        List<Execucao> execucaoList = execucaoRepository.findAll();
        assertThat(execucaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteExecucao() throws Exception {
        // Initialize the database
        execucaoRepository.saveAndFlush(execucao);
        int databaseSizeBeforeDelete = execucaoRepository.findAll().size();

        // Get the execucao
        restExecucaoMockMvc.perform(delete("/api/execucaos/{id}", execucao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Execucao> execucaoList = execucaoRepository.findAll();
        assertThat(execucaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Execucao.class);
        Execucao execucao1 = new Execucao();
        execucao1.setId(1L);
        Execucao execucao2 = new Execucao();
        execucao2.setId(execucao1.getId());
        assertThat(execucao1).isEqualTo(execucao2);
        execucao2.setId(2L);
        assertThat(execucao1).isNotEqualTo(execucao2);
        execucao1.setId(null);
        assertThat(execucao1).isNotEqualTo(execucao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExecucaoDTO.class);
        ExecucaoDTO execucaoDTO1 = new ExecucaoDTO();
        execucaoDTO1.setId(1L);
        ExecucaoDTO execucaoDTO2 = new ExecucaoDTO();
        assertThat(execucaoDTO1).isNotEqualTo(execucaoDTO2);
        execucaoDTO2.setId(execucaoDTO1.getId());
        assertThat(execucaoDTO1).isEqualTo(execucaoDTO2);
        execucaoDTO2.setId(2L);
        assertThat(execucaoDTO1).isNotEqualTo(execucaoDTO2);
        execucaoDTO1.setId(null);
        assertThat(execucaoDTO1).isNotEqualTo(execucaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(execucaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(execucaoMapper.fromId(null)).isNull();
    }
}
