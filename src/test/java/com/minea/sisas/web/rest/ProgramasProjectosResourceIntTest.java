package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.ProgramasProjectos;
import com.minea.sisas.domain.Comuna;
import com.minea.sisas.domain.Adjudicacao;
import com.minea.sisas.domain.Concepcao;
import com.minea.sisas.domain.Concurso;
import com.minea.sisas.domain.Contrato;
import com.minea.sisas.domain.Empreitada;
import com.minea.sisas.domain.Execucao;
import com.minea.sisas.domain.ProgramasProjectosLog;
import com.minea.sisas.repository.ProgramasProjectosRepository;
import com.minea.sisas.service.ProgramasProjectosService;
import com.minea.sisas.service.dto.ProgramasProjectosDTO;
import com.minea.sisas.service.mapper.ProgramasProjectosMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.ProgramasProjectosQueryService;

import com.minea.sisas.web.rest.ProgramasProjectosResource;
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
 * Test class for the ProgramasProjectosResource REST controller.
 *
 * @see ProgramasProjectosResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class ProgramasProjectosResourceIntTest {

    private static final Long DEFAULT_ID_PROGRAMAS_PROJECTOS = 1L;
    private static final Long UPDATED_ID_PROGRAMAS_PROJECTOS = 2L;

    private static final LocalDate DEFAULT_DT_LANCAMENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_LANCAMENTO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_ULTIMA_ALTERACAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_ULTIMA_ALTERACAO = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ID_USUARIO = 1L;
    private static final Long UPDATED_ID_USUARIO = 2L;

    private static final String DEFAULT_NM_DESIGNACAO_PROJETO = "AAAAAAAAAA";
    private static final String UPDATED_NM_DESIGNACAO_PROJETO = "BBBBBBBBBB";

    private static final String DEFAULT_NM_DESCRICAO_PROJETO = "AAAAAAAAAA";
    private static final String UPDATED_NM_DESCRICAO_PROJETO = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_SAA_ASSOCIADO = 1L;
    private static final Long UPDATED_ID_SAA_ASSOCIADO = 2L;

    private static final String DEFAULT_TIPO_FINANCIAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_FINANCIAMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_ESPECIALIDADE = "AAAAAAAAAA";
    private static final String UPDATED_ESPECIALIDADE = "BBBBBBBBBB";

    @Autowired
    private ProgramasProjectosRepository programasProjectosRepository;

    @Autowired
    private ProgramasProjectosMapper programasProjectosMapper;

    @Autowired
    private ProgramasProjectosService programasProjectosService;

    @Autowired
    private ProgramasProjectosQueryService programasProjectosQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProgramasProjectosMockMvc;

    private ProgramasProjectos programasProjectos;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProgramasProjectosResource programasProjectosResource = new ProgramasProjectosResource(programasProjectosService, programasProjectosQueryService);
        this.restProgramasProjectosMockMvc = MockMvcBuilders.standaloneSetup(programasProjectosResource)
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
    public static ProgramasProjectos createEntity(EntityManager em) {
        ProgramasProjectos programasProjectos = new ProgramasProjectos()
            .idProgramasProjectos(DEFAULT_ID_PROGRAMAS_PROJECTOS)
            .dtLancamento(DEFAULT_DT_LANCAMENTO)
            .dtUltimaAlteracao(DEFAULT_DT_ULTIMA_ALTERACAO)
            .idUsuario(DEFAULT_ID_USUARIO)
            .nmDesignacaoProjeto(DEFAULT_NM_DESIGNACAO_PROJETO)
            .nmDescricaoProjeto(DEFAULT_NM_DESCRICAO_PROJETO)
            .idSaaAssociado(DEFAULT_ID_SAA_ASSOCIADO)
            .tipoFinanciamento(DEFAULT_TIPO_FINANCIAMENTO)
            .especialidade(DEFAULT_ESPECIALIDADE);
        // Add required entity
        Comuna idComuna = ComunaResourceIntTest.createEntity(em);
        em.persist(idComuna);
        em.flush();
        programasProjectos.setIdComuna(idComuna);
        return programasProjectos;
    }

    @Before
    public void initTest() {
        programasProjectos = createEntity(em);
    }

    @Test
    @Transactional
    public void createProgramasProjectos() throws Exception {
        int databaseSizeBeforeCreate = programasProjectosRepository.findAll().size();

        // Create the ProgramasProjectos
        ProgramasProjectosDTO programasProjectosDTO = programasProjectosMapper.toDto(programasProjectos);
        restProgramasProjectosMockMvc.perform(post("/api/programas-projectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programasProjectosDTO)))
            .andExpect(status().isCreated());

        // Validate the ProgramasProjectos in the database
        List<ProgramasProjectos> programasProjectosList = programasProjectosRepository.findAll();
        assertThat(programasProjectosList).hasSize(databaseSizeBeforeCreate + 1);
        ProgramasProjectos testProgramasProjectos = programasProjectosList.get(programasProjectosList.size() - 1);
        assertThat(testProgramasProjectos.getIdProgramasProjectos()).isEqualTo(DEFAULT_ID_PROGRAMAS_PROJECTOS);
        assertThat(testProgramasProjectos.getDtLancamento()).isEqualTo(DEFAULT_DT_LANCAMENTO);
        assertThat(testProgramasProjectos.getDtUltimaAlteracao()).isEqualTo(DEFAULT_DT_ULTIMA_ALTERACAO);
        assertThat(testProgramasProjectos.getIdUsuario()).isEqualTo(DEFAULT_ID_USUARIO);
        assertThat(testProgramasProjectos.getNmDesignacaoProjeto()).isEqualTo(DEFAULT_NM_DESIGNACAO_PROJETO);
        assertThat(testProgramasProjectos.getNmDescricaoProjeto()).isEqualTo(DEFAULT_NM_DESCRICAO_PROJETO);
        assertThat(testProgramasProjectos.getIdSaaAssociado()).isEqualTo(DEFAULT_ID_SAA_ASSOCIADO);
        assertThat(testProgramasProjectos.getTipoFinanciamento()).isEqualTo(DEFAULT_TIPO_FINANCIAMENTO);
        assertThat(testProgramasProjectos.getEspecialidade()).isEqualTo(DEFAULT_ESPECIALIDADE);
    }

    @Test
    @Transactional
    public void createProgramasProjectosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = programasProjectosRepository.findAll().size();

        // Create the ProgramasProjectos with an existing ID
        programasProjectos.setId(1L);
        ProgramasProjectosDTO programasProjectosDTO = programasProjectosMapper.toDto(programasProjectos);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProgramasProjectosMockMvc.perform(post("/api/programas-projectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programasProjectosDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProgramasProjectos in the database
        List<ProgramasProjectos> programasProjectosList = programasProjectosRepository.findAll();
        assertThat(programasProjectosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdProgramasProjectosIsRequired() throws Exception {
        int databaseSizeBeforeTest = programasProjectosRepository.findAll().size();
        // set the field null
        programasProjectos.setIdProgramasProjectos(null);

        // Create the ProgramasProjectos, which fails.
        ProgramasProjectosDTO programasProjectosDTO = programasProjectosMapper.toDto(programasProjectos);

        restProgramasProjectosMockMvc.perform(post("/api/programas-projectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programasProjectosDTO)))
            .andExpect(status().isBadRequest());

        List<ProgramasProjectos> programasProjectosList = programasProjectosRepository.findAll();
        assertThat(programasProjectosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDtLancamentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = programasProjectosRepository.findAll().size();
        // set the field null
        programasProjectos.setDtLancamento(null);

        // Create the ProgramasProjectos, which fails.
        ProgramasProjectosDTO programasProjectosDTO = programasProjectosMapper.toDto(programasProjectos);

        restProgramasProjectosMockMvc.perform(post("/api/programas-projectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programasProjectosDTO)))
            .andExpect(status().isBadRequest());

        List<ProgramasProjectos> programasProjectosList = programasProjectosRepository.findAll();
        assertThat(programasProjectosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdUsuarioIsRequired() throws Exception {
        int databaseSizeBeforeTest = programasProjectosRepository.findAll().size();
        // set the field null
        programasProjectos.setIdUsuario(null);

        // Create the ProgramasProjectos, which fails.
        ProgramasProjectosDTO programasProjectosDTO = programasProjectosMapper.toDto(programasProjectos);

        restProgramasProjectosMockMvc.perform(post("/api/programas-projectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programasProjectosDTO)))
            .andExpect(status().isBadRequest());

        List<ProgramasProjectos> programasProjectosList = programasProjectosRepository.findAll();
        assertThat(programasProjectosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNmDesignacaoProjetoIsRequired() throws Exception {
        int databaseSizeBeforeTest = programasProjectosRepository.findAll().size();
        // set the field null
        programasProjectos.setNmDesignacaoProjeto(null);

        // Create the ProgramasProjectos, which fails.
        ProgramasProjectosDTO programasProjectosDTO = programasProjectosMapper.toDto(programasProjectos);

        restProgramasProjectosMockMvc.perform(post("/api/programas-projectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programasProjectosDTO)))
            .andExpect(status().isBadRequest());

        List<ProgramasProjectos> programasProjectosList = programasProjectosRepository.findAll();
        assertThat(programasProjectosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNmDescricaoProjetoIsRequired() throws Exception {
        int databaseSizeBeforeTest = programasProjectosRepository.findAll().size();
        // set the field null
        programasProjectos.setNmDescricaoProjeto(null);

        // Create the ProgramasProjectos, which fails.
        ProgramasProjectosDTO programasProjectosDTO = programasProjectosMapper.toDto(programasProjectos);

        restProgramasProjectosMockMvc.perform(post("/api/programas-projectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programasProjectosDTO)))
            .andExpect(status().isBadRequest());

        List<ProgramasProjectos> programasProjectosList = programasProjectosRepository.findAll();
        assertThat(programasProjectosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTipoFinanciamentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = programasProjectosRepository.findAll().size();
        // set the field null
        programasProjectos.setTipoFinanciamento(null);

        // Create the ProgramasProjectos, which fails.
        ProgramasProjectosDTO programasProjectosDTO = programasProjectosMapper.toDto(programasProjectos);

        restProgramasProjectosMockMvc.perform(post("/api/programas-projectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programasProjectosDTO)))
            .andExpect(status().isBadRequest());

        List<ProgramasProjectos> programasProjectosList = programasProjectosRepository.findAll();
        assertThat(programasProjectosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectos() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList
        restProgramasProjectosMockMvc.perform(get("/api/programas-projectos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(programasProjectos.getId().intValue())))
            .andExpect(jsonPath("$.[*].idProgramasProjectos").value(hasItem(DEFAULT_ID_PROGRAMAS_PROJECTOS.intValue())))
            .andExpect(jsonPath("$.[*].dtLancamento").value(hasItem(DEFAULT_DT_LANCAMENTO.toString())))
            .andExpect(jsonPath("$.[*].dtUltimaAlteracao").value(hasItem(DEFAULT_DT_ULTIMA_ALTERACAO.toString())))
            .andExpect(jsonPath("$.[*].idUsuario").value(hasItem(DEFAULT_ID_USUARIO.intValue())))
            .andExpect(jsonPath("$.[*].nmDesignacaoProjeto").value(hasItem(DEFAULT_NM_DESIGNACAO_PROJETO.toString())))
            .andExpect(jsonPath("$.[*].nmDescricaoProjeto").value(hasItem(DEFAULT_NM_DESCRICAO_PROJETO.toString())))
            .andExpect(jsonPath("$.[*].idSaaAssociado").value(hasItem(DEFAULT_ID_SAA_ASSOCIADO.intValue())))
            .andExpect(jsonPath("$.[*].tipoFinanciamento").value(hasItem(DEFAULT_TIPO_FINANCIAMENTO.toString())))
            .andExpect(jsonPath("$.[*].especialidade").value(hasItem(DEFAULT_ESPECIALIDADE.toString())));
    }

    @Test
    @Transactional
    public void getProgramasProjectos() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get the programasProjectos
        restProgramasProjectosMockMvc.perform(get("/api/programas-projectos/{id}", programasProjectos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(programasProjectos.getId().intValue()))
            .andExpect(jsonPath("$.idProgramasProjectos").value(DEFAULT_ID_PROGRAMAS_PROJECTOS.intValue()))
            .andExpect(jsonPath("$.dtLancamento").value(DEFAULT_DT_LANCAMENTO.toString()))
            .andExpect(jsonPath("$.dtUltimaAlteracao").value(DEFAULT_DT_ULTIMA_ALTERACAO.toString()))
            .andExpect(jsonPath("$.idUsuario").value(DEFAULT_ID_USUARIO.intValue()))
            .andExpect(jsonPath("$.nmDesignacaoProjeto").value(DEFAULT_NM_DESIGNACAO_PROJETO.toString()))
            .andExpect(jsonPath("$.nmDescricaoProjeto").value(DEFAULT_NM_DESCRICAO_PROJETO.toString()))
            .andExpect(jsonPath("$.idSaaAssociado").value(DEFAULT_ID_SAA_ASSOCIADO.intValue()))
            .andExpect(jsonPath("$.tipoFinanciamento").value(DEFAULT_TIPO_FINANCIAMENTO.toString()))
            .andExpect(jsonPath("$.especialidade").value(DEFAULT_ESPECIALIDADE.toString()));
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByIdProgramasProjectosIsEqualToSomething() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where idProgramasProjectos equals to DEFAULT_ID_PROGRAMAS_PROJECTOS
        defaultProgramasProjectosShouldBeFound("idProgramasProjectos.equals=" + DEFAULT_ID_PROGRAMAS_PROJECTOS);

        // Get all the programasProjectosList where idProgramasProjectos equals to UPDATED_ID_PROGRAMAS_PROJECTOS
        defaultProgramasProjectosShouldNotBeFound("idProgramasProjectos.equals=" + UPDATED_ID_PROGRAMAS_PROJECTOS);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByIdProgramasProjectosIsInShouldWork() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where idProgramasProjectos in DEFAULT_ID_PROGRAMAS_PROJECTOS or UPDATED_ID_PROGRAMAS_PROJECTOS
        defaultProgramasProjectosShouldBeFound("idProgramasProjectos.in=" + DEFAULT_ID_PROGRAMAS_PROJECTOS + "," + UPDATED_ID_PROGRAMAS_PROJECTOS);

        // Get all the programasProjectosList where idProgramasProjectos equals to UPDATED_ID_PROGRAMAS_PROJECTOS
        defaultProgramasProjectosShouldNotBeFound("idProgramasProjectos.in=" + UPDATED_ID_PROGRAMAS_PROJECTOS);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByIdProgramasProjectosIsNullOrNotNull() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where idProgramasProjectos is not null
        defaultProgramasProjectosShouldBeFound("idProgramasProjectos.specified=true");

        // Get all the programasProjectosList where idProgramasProjectos is null
        defaultProgramasProjectosShouldNotBeFound("idProgramasProjectos.specified=false");
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByIdProgramasProjectosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where idProgramasProjectos greater than or equals to DEFAULT_ID_PROGRAMAS_PROJECTOS
        defaultProgramasProjectosShouldBeFound("idProgramasProjectos.greaterOrEqualThan=" + DEFAULT_ID_PROGRAMAS_PROJECTOS);

        // Get all the programasProjectosList where idProgramasProjectos greater than or equals to UPDATED_ID_PROGRAMAS_PROJECTOS
        defaultProgramasProjectosShouldNotBeFound("idProgramasProjectos.greaterOrEqualThan=" + UPDATED_ID_PROGRAMAS_PROJECTOS);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByIdProgramasProjectosIsLessThanSomething() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where idProgramasProjectos less than or equals to DEFAULT_ID_PROGRAMAS_PROJECTOS
        defaultProgramasProjectosShouldNotBeFound("idProgramasProjectos.lessThan=" + DEFAULT_ID_PROGRAMAS_PROJECTOS);

        // Get all the programasProjectosList where idProgramasProjectos less than or equals to UPDATED_ID_PROGRAMAS_PROJECTOS
        defaultProgramasProjectosShouldBeFound("idProgramasProjectos.lessThan=" + UPDATED_ID_PROGRAMAS_PROJECTOS);
    }


    @Test
    @Transactional
    public void getAllProgramasProjectosByDtLancamentoIsEqualToSomething() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where dtLancamento equals to DEFAULT_DT_LANCAMENTO
        defaultProgramasProjectosShouldBeFound("dtLancamento.equals=" + DEFAULT_DT_LANCAMENTO);

        // Get all the programasProjectosList where dtLancamento equals to UPDATED_DT_LANCAMENTO
        defaultProgramasProjectosShouldNotBeFound("dtLancamento.equals=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByDtLancamentoIsInShouldWork() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where dtLancamento in DEFAULT_DT_LANCAMENTO or UPDATED_DT_LANCAMENTO
        defaultProgramasProjectosShouldBeFound("dtLancamento.in=" + DEFAULT_DT_LANCAMENTO + "," + UPDATED_DT_LANCAMENTO);

        // Get all the programasProjectosList where dtLancamento equals to UPDATED_DT_LANCAMENTO
        defaultProgramasProjectosShouldNotBeFound("dtLancamento.in=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByDtLancamentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where dtLancamento is not null
        defaultProgramasProjectosShouldBeFound("dtLancamento.specified=true");

        // Get all the programasProjectosList where dtLancamento is null
        defaultProgramasProjectosShouldNotBeFound("dtLancamento.specified=false");
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByDtLancamentoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where dtLancamento greater than or equals to DEFAULT_DT_LANCAMENTO
        defaultProgramasProjectosShouldBeFound("dtLancamento.greaterOrEqualThan=" + DEFAULT_DT_LANCAMENTO);

        // Get all the programasProjectosList where dtLancamento greater than or equals to UPDATED_DT_LANCAMENTO
        defaultProgramasProjectosShouldNotBeFound("dtLancamento.greaterOrEqualThan=" + UPDATED_DT_LANCAMENTO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByDtLancamentoIsLessThanSomething() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where dtLancamento less than or equals to DEFAULT_DT_LANCAMENTO
        defaultProgramasProjectosShouldNotBeFound("dtLancamento.lessThan=" + DEFAULT_DT_LANCAMENTO);

        // Get all the programasProjectosList where dtLancamento less than or equals to UPDATED_DT_LANCAMENTO
        defaultProgramasProjectosShouldBeFound("dtLancamento.lessThan=" + UPDATED_DT_LANCAMENTO);
    }


    @Test
    @Transactional
    public void getAllProgramasProjectosByDtUltimaAlteracaoIsEqualToSomething() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where dtUltimaAlteracao equals to DEFAULT_DT_ULTIMA_ALTERACAO
        defaultProgramasProjectosShouldBeFound("dtUltimaAlteracao.equals=" + DEFAULT_DT_ULTIMA_ALTERACAO);

        // Get all the programasProjectosList where dtUltimaAlteracao equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultProgramasProjectosShouldNotBeFound("dtUltimaAlteracao.equals=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByDtUltimaAlteracaoIsInShouldWork() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where dtUltimaAlteracao in DEFAULT_DT_ULTIMA_ALTERACAO or UPDATED_DT_ULTIMA_ALTERACAO
        defaultProgramasProjectosShouldBeFound("dtUltimaAlteracao.in=" + DEFAULT_DT_ULTIMA_ALTERACAO + "," + UPDATED_DT_ULTIMA_ALTERACAO);

        // Get all the programasProjectosList where dtUltimaAlteracao equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultProgramasProjectosShouldNotBeFound("dtUltimaAlteracao.in=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByDtUltimaAlteracaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where dtUltimaAlteracao is not null
        defaultProgramasProjectosShouldBeFound("dtUltimaAlteracao.specified=true");

        // Get all the programasProjectosList where dtUltimaAlteracao is null
        defaultProgramasProjectosShouldNotBeFound("dtUltimaAlteracao.specified=false");
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByDtUltimaAlteracaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where dtUltimaAlteracao greater than or equals to DEFAULT_DT_ULTIMA_ALTERACAO
        defaultProgramasProjectosShouldBeFound("dtUltimaAlteracao.greaterOrEqualThan=" + DEFAULT_DT_ULTIMA_ALTERACAO);

        // Get all the programasProjectosList where dtUltimaAlteracao greater than or equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultProgramasProjectosShouldNotBeFound("dtUltimaAlteracao.greaterOrEqualThan=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByDtUltimaAlteracaoIsLessThanSomething() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where dtUltimaAlteracao less than or equals to DEFAULT_DT_ULTIMA_ALTERACAO
        defaultProgramasProjectosShouldNotBeFound("dtUltimaAlteracao.lessThan=" + DEFAULT_DT_ULTIMA_ALTERACAO);

        // Get all the programasProjectosList where dtUltimaAlteracao less than or equals to UPDATED_DT_ULTIMA_ALTERACAO
        defaultProgramasProjectosShouldBeFound("dtUltimaAlteracao.lessThan=" + UPDATED_DT_ULTIMA_ALTERACAO);
    }


    @Test
    @Transactional
    public void getAllProgramasProjectosByIdUsuarioIsEqualToSomething() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where idUsuario equals to DEFAULT_ID_USUARIO
        defaultProgramasProjectosShouldBeFound("idUsuario.equals=" + DEFAULT_ID_USUARIO);

        // Get all the programasProjectosList where idUsuario equals to UPDATED_ID_USUARIO
        defaultProgramasProjectosShouldNotBeFound("idUsuario.equals=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByIdUsuarioIsInShouldWork() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where idUsuario in DEFAULT_ID_USUARIO or UPDATED_ID_USUARIO
        defaultProgramasProjectosShouldBeFound("idUsuario.in=" + DEFAULT_ID_USUARIO + "," + UPDATED_ID_USUARIO);

        // Get all the programasProjectosList where idUsuario equals to UPDATED_ID_USUARIO
        defaultProgramasProjectosShouldNotBeFound("idUsuario.in=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByIdUsuarioIsNullOrNotNull() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where idUsuario is not null
        defaultProgramasProjectosShouldBeFound("idUsuario.specified=true");

        // Get all the programasProjectosList where idUsuario is null
        defaultProgramasProjectosShouldNotBeFound("idUsuario.specified=false");
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByIdUsuarioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where idUsuario greater than or equals to DEFAULT_ID_USUARIO
        defaultProgramasProjectosShouldBeFound("idUsuario.greaterOrEqualThan=" + DEFAULT_ID_USUARIO);

        // Get all the programasProjectosList where idUsuario greater than or equals to UPDATED_ID_USUARIO
        defaultProgramasProjectosShouldNotBeFound("idUsuario.greaterOrEqualThan=" + UPDATED_ID_USUARIO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByIdUsuarioIsLessThanSomething() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where idUsuario less than or equals to DEFAULT_ID_USUARIO
        defaultProgramasProjectosShouldNotBeFound("idUsuario.lessThan=" + DEFAULT_ID_USUARIO);

        // Get all the programasProjectosList where idUsuario less than or equals to UPDATED_ID_USUARIO
        defaultProgramasProjectosShouldBeFound("idUsuario.lessThan=" + UPDATED_ID_USUARIO);
    }


    @Test
    @Transactional
    public void getAllProgramasProjectosByNmDesignacaoProjetoIsEqualToSomething() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where nmDesignacaoProjeto equals to DEFAULT_NM_DESIGNACAO_PROJETO
        defaultProgramasProjectosShouldBeFound("nmDesignacaoProjeto.equals=" + DEFAULT_NM_DESIGNACAO_PROJETO);

        // Get all the programasProjectosList where nmDesignacaoProjeto equals to UPDATED_NM_DESIGNACAO_PROJETO
        defaultProgramasProjectosShouldNotBeFound("nmDesignacaoProjeto.equals=" + UPDATED_NM_DESIGNACAO_PROJETO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByNmDesignacaoProjetoIsInShouldWork() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where nmDesignacaoProjeto in DEFAULT_NM_DESIGNACAO_PROJETO or UPDATED_NM_DESIGNACAO_PROJETO
        defaultProgramasProjectosShouldBeFound("nmDesignacaoProjeto.in=" + DEFAULT_NM_DESIGNACAO_PROJETO + "," + UPDATED_NM_DESIGNACAO_PROJETO);

        // Get all the programasProjectosList where nmDesignacaoProjeto equals to UPDATED_NM_DESIGNACAO_PROJETO
        defaultProgramasProjectosShouldNotBeFound("nmDesignacaoProjeto.in=" + UPDATED_NM_DESIGNACAO_PROJETO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByNmDesignacaoProjetoIsNullOrNotNull() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where nmDesignacaoProjeto is not null
        defaultProgramasProjectosShouldBeFound("nmDesignacaoProjeto.specified=true");

        // Get all the programasProjectosList where nmDesignacaoProjeto is null
        defaultProgramasProjectosShouldNotBeFound("nmDesignacaoProjeto.specified=false");
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByNmDescricaoProjetoIsEqualToSomething() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where nmDescricaoProjeto equals to DEFAULT_NM_DESCRICAO_PROJETO
        defaultProgramasProjectosShouldBeFound("nmDescricaoProjeto.equals=" + DEFAULT_NM_DESCRICAO_PROJETO);

        // Get all the programasProjectosList where nmDescricaoProjeto equals to UPDATED_NM_DESCRICAO_PROJETO
        defaultProgramasProjectosShouldNotBeFound("nmDescricaoProjeto.equals=" + UPDATED_NM_DESCRICAO_PROJETO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByNmDescricaoProjetoIsInShouldWork() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where nmDescricaoProjeto in DEFAULT_NM_DESCRICAO_PROJETO or UPDATED_NM_DESCRICAO_PROJETO
        defaultProgramasProjectosShouldBeFound("nmDescricaoProjeto.in=" + DEFAULT_NM_DESCRICAO_PROJETO + "," + UPDATED_NM_DESCRICAO_PROJETO);

        // Get all the programasProjectosList where nmDescricaoProjeto equals to UPDATED_NM_DESCRICAO_PROJETO
        defaultProgramasProjectosShouldNotBeFound("nmDescricaoProjeto.in=" + UPDATED_NM_DESCRICAO_PROJETO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByNmDescricaoProjetoIsNullOrNotNull() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where nmDescricaoProjeto is not null
        defaultProgramasProjectosShouldBeFound("nmDescricaoProjeto.specified=true");

        // Get all the programasProjectosList where nmDescricaoProjeto is null
        defaultProgramasProjectosShouldNotBeFound("nmDescricaoProjeto.specified=false");
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByIdSaaAssociadoIsEqualToSomething() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where idSaaAssociado equals to DEFAULT_ID_SAA_ASSOCIADO
        defaultProgramasProjectosShouldBeFound("idSaaAssociado.equals=" + DEFAULT_ID_SAA_ASSOCIADO);

        // Get all the programasProjectosList where idSaaAssociado equals to UPDATED_ID_SAA_ASSOCIADO
        defaultProgramasProjectosShouldNotBeFound("idSaaAssociado.equals=" + UPDATED_ID_SAA_ASSOCIADO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByIdSaaAssociadoIsInShouldWork() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where idSaaAssociado in DEFAULT_ID_SAA_ASSOCIADO or UPDATED_ID_SAA_ASSOCIADO
        defaultProgramasProjectosShouldBeFound("idSaaAssociado.in=" + DEFAULT_ID_SAA_ASSOCIADO + "," + UPDATED_ID_SAA_ASSOCIADO);

        // Get all the programasProjectosList where idSaaAssociado equals to UPDATED_ID_SAA_ASSOCIADO
        defaultProgramasProjectosShouldNotBeFound("idSaaAssociado.in=" + UPDATED_ID_SAA_ASSOCIADO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByIdSaaAssociadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where idSaaAssociado is not null
        defaultProgramasProjectosShouldBeFound("idSaaAssociado.specified=true");

        // Get all the programasProjectosList where idSaaAssociado is null
        defaultProgramasProjectosShouldNotBeFound("idSaaAssociado.specified=false");
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByIdSaaAssociadoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where idSaaAssociado greater than or equals to DEFAULT_ID_SAA_ASSOCIADO
        defaultProgramasProjectosShouldBeFound("idSaaAssociado.greaterOrEqualThan=" + DEFAULT_ID_SAA_ASSOCIADO);

        // Get all the programasProjectosList where idSaaAssociado greater than or equals to UPDATED_ID_SAA_ASSOCIADO
        defaultProgramasProjectosShouldNotBeFound("idSaaAssociado.greaterOrEqualThan=" + UPDATED_ID_SAA_ASSOCIADO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByIdSaaAssociadoIsLessThanSomething() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where idSaaAssociado less than or equals to DEFAULT_ID_SAA_ASSOCIADO
        defaultProgramasProjectosShouldNotBeFound("idSaaAssociado.lessThan=" + DEFAULT_ID_SAA_ASSOCIADO);

        // Get all the programasProjectosList where idSaaAssociado less than or equals to UPDATED_ID_SAA_ASSOCIADO
        defaultProgramasProjectosShouldBeFound("idSaaAssociado.lessThan=" + UPDATED_ID_SAA_ASSOCIADO);
    }


    @Test
    @Transactional
    public void getAllProgramasProjectosByTipoFinanciamentoIsEqualToSomething() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where tipoFinanciamento equals to DEFAULT_TIPO_FINANCIAMENTO
        defaultProgramasProjectosShouldBeFound("tipoFinanciamento.equals=" + DEFAULT_TIPO_FINANCIAMENTO);

        // Get all the programasProjectosList where tipoFinanciamento equals to UPDATED_TIPO_FINANCIAMENTO
        defaultProgramasProjectosShouldNotBeFound("tipoFinanciamento.equals=" + UPDATED_TIPO_FINANCIAMENTO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByTipoFinanciamentoIsInShouldWork() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where tipoFinanciamento in DEFAULT_TIPO_FINANCIAMENTO or UPDATED_TIPO_FINANCIAMENTO
        defaultProgramasProjectosShouldBeFound("tipoFinanciamento.in=" + DEFAULT_TIPO_FINANCIAMENTO + "," + UPDATED_TIPO_FINANCIAMENTO);

        // Get all the programasProjectosList where tipoFinanciamento equals to UPDATED_TIPO_FINANCIAMENTO
        defaultProgramasProjectosShouldNotBeFound("tipoFinanciamento.in=" + UPDATED_TIPO_FINANCIAMENTO);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByTipoFinanciamentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where tipoFinanciamento is not null
        defaultProgramasProjectosShouldBeFound("tipoFinanciamento.specified=true");

        // Get all the programasProjectosList where tipoFinanciamento is null
        defaultProgramasProjectosShouldNotBeFound("tipoFinanciamento.specified=false");
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByEspecialidadeIsEqualToSomething() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where especialidade equals to DEFAULT_ESPECIALIDADE
        defaultProgramasProjectosShouldBeFound("especialidade.equals=" + DEFAULT_ESPECIALIDADE);

        // Get all the programasProjectosList where especialidade equals to UPDATED_ESPECIALIDADE
        defaultProgramasProjectosShouldNotBeFound("especialidade.equals=" + UPDATED_ESPECIALIDADE);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByEspecialidadeIsInShouldWork() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where especialidade in DEFAULT_ESPECIALIDADE or UPDATED_ESPECIALIDADE
        defaultProgramasProjectosShouldBeFound("especialidade.in=" + DEFAULT_ESPECIALIDADE + "," + UPDATED_ESPECIALIDADE);

        // Get all the programasProjectosList where especialidade equals to UPDATED_ESPECIALIDADE
        defaultProgramasProjectosShouldNotBeFound("especialidade.in=" + UPDATED_ESPECIALIDADE);
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByEspecialidadeIsNullOrNotNull() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);

        // Get all the programasProjectosList where especialidade is not null
        defaultProgramasProjectosShouldBeFound("especialidade.specified=true");

        // Get all the programasProjectosList where especialidade is null
        defaultProgramasProjectosShouldNotBeFound("especialidade.specified=false");
    }

    @Test
    @Transactional
    public void getAllProgramasProjectosByIdComunaIsEqualToSomething() throws Exception {
        // Initialize the database
        Comuna idComuna = ComunaResourceIntTest.createEntity(em);
        em.persist(idComuna);
        em.flush();
        programasProjectos.setIdComuna(idComuna);
        programasProjectosRepository.saveAndFlush(programasProjectos);
        Long idComunaId = idComuna.getId();

        // Get all the programasProjectosList where idComuna equals to comuna
        defaultProgramasProjectosShouldBeFound("comuna.equals=" + idComunaId);

        // Get all the programasProjectosList where idComuna equals to comuna + 1
        defaultProgramasProjectosShouldNotBeFound("comuna.equals=" + (idComunaId + 1));
    }


    @Test
    @Transactional
    public void getAllProgramasProjectosByAdjudicacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        Adjudicacao adjudicacao = AdjudicacaoResourceIntTest.createEntity(em);
        em.persist(adjudicacao);
        em.flush();
        programasProjectos.addAdjudicacao(adjudicacao);
        programasProjectosRepository.saveAndFlush(programasProjectos);
        Long adjudicacaoId = adjudicacao.getId();

        // Get all the programasProjectosList where adjudicacao equals to adjudicacaoId
        defaultProgramasProjectosShouldBeFound("adjudicacaoId.equals=" + adjudicacaoId);

        // Get all the programasProjectosList where adjudicacao equals to adjudicacaoId + 1
        defaultProgramasProjectosShouldNotBeFound("adjudicacaoId.equals=" + (adjudicacaoId + 1));
    }


    @Test
    @Transactional
    public void getAllProgramasProjectosByConcepcaoIsEqualToSomething() throws Exception {
        // Initialize the database
        Concepcao concepcao = ConcepcaoResourceIntTest.createEntity(em);
        em.persist(concepcao);
        em.flush();
        programasProjectos.addConcepcao(concepcao);
        programasProjectosRepository.saveAndFlush(programasProjectos);
        Long concepcaoId = concepcao.getId();

        // Get all the programasProjectosList where concepcao equals to concepcaoId
        defaultProgramasProjectosShouldBeFound("concepcaoId.equals=" + concepcaoId);

        // Get all the programasProjectosList where concepcao equals to concepcaoId + 1
        defaultProgramasProjectosShouldNotBeFound("concepcaoId.equals=" + (concepcaoId + 1));
    }


    @Test
    @Transactional
    public void getAllProgramasProjectosByConcursoIsEqualToSomething() throws Exception {
        // Initialize the database
        Concurso concurso = ConcursoResourceIntTest.createEntity(em);
        em.persist(concurso);
        em.flush();
        programasProjectos.addConcurso(concurso);
        programasProjectosRepository.saveAndFlush(programasProjectos);
        Long concursoId = concurso.getId();

        // Get all the programasProjectosList where concurso equals to concursoId
        defaultProgramasProjectosShouldBeFound("concursoId.equals=" + concursoId);

        // Get all the programasProjectosList where concurso equals to concursoId + 1
        defaultProgramasProjectosShouldNotBeFound("concursoId.equals=" + (concursoId + 1));
    }


    @Test
    @Transactional
    public void getAllProgramasProjectosByContratoIsEqualToSomething() throws Exception {
        // Initialize the database
        Contrato contrato = ContratoResourceIntTest.createEntity(em);
        em.persist(contrato);
        em.flush();
        programasProjectos.addContrato(contrato);
        programasProjectosRepository.saveAndFlush(programasProjectos);
        Long contratoId = contrato.getId();

        // Get all the programasProjectosList where contrato equals to contratoId
        defaultProgramasProjectosShouldBeFound("contratoId.equals=" + contratoId);

        // Get all the programasProjectosList where contrato equals to contratoId + 1
        defaultProgramasProjectosShouldNotBeFound("contratoId.equals=" + (contratoId + 1));
    }


    @Test
    @Transactional
    public void getAllProgramasProjectosByEmpreitadaIsEqualToSomething() throws Exception {
        // Initialize the database
        Empreitada empreitada = EmpreitadaResourceIntTest.createEntity(em);
        em.persist(empreitada);
        em.flush();
        programasProjectos.addEmpreitada(empreitada);
        programasProjectosRepository.saveAndFlush(programasProjectos);
        Long empreitadaId = empreitada.getId();

        // Get all the programasProjectosList where empreitada equals to empreitadaId
        defaultProgramasProjectosShouldBeFound("empreitadaId.equals=" + empreitadaId);

        // Get all the programasProjectosList where empreitada equals to empreitadaId + 1
        defaultProgramasProjectosShouldNotBeFound("empreitadaId.equals=" + (empreitadaId + 1));
    }


    @Test
    @Transactional
    public void getAllProgramasProjectosByExecucaoIsEqualToSomething() throws Exception {
        // Initialize the database
        Execucao execucao = ExecucaoResourceIntTest.createEntity(em);
        em.persist(execucao);
        em.flush();
        programasProjectos.addExecucao(execucao);
        programasProjectosRepository.saveAndFlush(programasProjectos);
        Long execucaoId = execucao.getId();

        // Get all the programasProjectosList where execucao equals to execucaoId
        defaultProgramasProjectosShouldBeFound("execucaoId.equals=" + execucaoId);

        // Get all the programasProjectosList where execucao equals to execucaoId + 1
        defaultProgramasProjectosShouldNotBeFound("execucaoId.equals=" + (execucaoId + 1));
    }


    @Test
    @Transactional
    public void getAllProgramasProjectosByProgramasProjectosLogIsEqualToSomething() throws Exception {
        // Initialize the database
        ProgramasProjectosLog programasProjectosLog = ProgramasProjectosLogResourceIntTest.createEntity(em);
        em.persist(programasProjectosLog);
        em.flush();
        programasProjectos.addProgramasProjectosLog(programasProjectosLog);
        programasProjectosRepository.saveAndFlush(programasProjectos);
        Long programasProjectosLogId = programasProjectosLog.getId();

        // Get all the programasProjectosList where programasProjectosLog equals to programasProjectosLogId
        defaultProgramasProjectosShouldBeFound("programasProjectosLogId.equals=" + programasProjectosLogId);

        // Get all the programasProjectosList where programasProjectosLog equals to programasProjectosLogId + 1
        defaultProgramasProjectosShouldNotBeFound("programasProjectosLogId.equals=" + (programasProjectosLogId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultProgramasProjectosShouldBeFound(String filter) throws Exception {
        restProgramasProjectosMockMvc.perform(get("/api/programas-projectos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(programasProjectos.getId().intValue())))
            .andExpect(jsonPath("$.[*].idProgramasProjectos").value(hasItem(DEFAULT_ID_PROGRAMAS_PROJECTOS.intValue())))
            .andExpect(jsonPath("$.[*].dtLancamento").value(hasItem(DEFAULT_DT_LANCAMENTO.toString())))
            .andExpect(jsonPath("$.[*].dtUltimaAlteracao").value(hasItem(DEFAULT_DT_ULTIMA_ALTERACAO.toString())))
            .andExpect(jsonPath("$.[*].idUsuario").value(hasItem(DEFAULT_ID_USUARIO.intValue())))
            .andExpect(jsonPath("$.[*].nmDesignacaoProjeto").value(hasItem(DEFAULT_NM_DESIGNACAO_PROJETO.toString())))
            .andExpect(jsonPath("$.[*].nmDescricaoProjeto").value(hasItem(DEFAULT_NM_DESCRICAO_PROJETO.toString())))
            .andExpect(jsonPath("$.[*].idSaaAssociado").value(hasItem(DEFAULT_ID_SAA_ASSOCIADO.intValue())))
            .andExpect(jsonPath("$.[*].tipoFinanciamento").value(hasItem(DEFAULT_TIPO_FINANCIAMENTO.toString())))
            .andExpect(jsonPath("$.[*].especialidade").value(hasItem(DEFAULT_ESPECIALIDADE.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultProgramasProjectosShouldNotBeFound(String filter) throws Exception {
        restProgramasProjectosMockMvc.perform(get("/api/programas-projectos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingProgramasProjectos() throws Exception {
        // Get the programasProjectos
        restProgramasProjectosMockMvc.perform(get("/api/programas-projectos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProgramasProjectos() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);
        int databaseSizeBeforeUpdate = programasProjectosRepository.findAll().size();

        // Update the programasProjectos
        ProgramasProjectos updatedProgramasProjectos = programasProjectosRepository.findOne(programasProjectos.getId());
        // Disconnect from session so that the updates on updatedProgramasProjectos are not directly saved in db
        em.detach(updatedProgramasProjectos);
        updatedProgramasProjectos
            .idProgramasProjectos(UPDATED_ID_PROGRAMAS_PROJECTOS)
            .dtLancamento(UPDATED_DT_LANCAMENTO)
            .dtUltimaAlteracao(UPDATED_DT_ULTIMA_ALTERACAO)
            .idUsuario(UPDATED_ID_USUARIO)
            .nmDesignacaoProjeto(UPDATED_NM_DESIGNACAO_PROJETO)
            .nmDescricaoProjeto(UPDATED_NM_DESCRICAO_PROJETO)
            .idSaaAssociado(UPDATED_ID_SAA_ASSOCIADO)
            .tipoFinanciamento(UPDATED_TIPO_FINANCIAMENTO)
            .especialidade(UPDATED_ESPECIALIDADE);
        ProgramasProjectosDTO programasProjectosDTO = programasProjectosMapper.toDto(updatedProgramasProjectos);

        restProgramasProjectosMockMvc.perform(put("/api/programas-projectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programasProjectosDTO)))
            .andExpect(status().isOk());

        // Validate the ProgramasProjectos in the database
        List<ProgramasProjectos> programasProjectosList = programasProjectosRepository.findAll();
        assertThat(programasProjectosList).hasSize(databaseSizeBeforeUpdate);
        ProgramasProjectos testProgramasProjectos = programasProjectosList.get(programasProjectosList.size() - 1);
        assertThat(testProgramasProjectos.getIdProgramasProjectos()).isEqualTo(UPDATED_ID_PROGRAMAS_PROJECTOS);
        assertThat(testProgramasProjectos.getDtLancamento()).isEqualTo(UPDATED_DT_LANCAMENTO);
        assertThat(testProgramasProjectos.getDtUltimaAlteracao()).isEqualTo(UPDATED_DT_ULTIMA_ALTERACAO);
        assertThat(testProgramasProjectos.getIdUsuario()).isEqualTo(UPDATED_ID_USUARIO);
        assertThat(testProgramasProjectos.getNmDesignacaoProjeto()).isEqualTo(UPDATED_NM_DESIGNACAO_PROJETO);
        assertThat(testProgramasProjectos.getNmDescricaoProjeto()).isEqualTo(UPDATED_NM_DESCRICAO_PROJETO);
        assertThat(testProgramasProjectos.getIdSaaAssociado()).isEqualTo(UPDATED_ID_SAA_ASSOCIADO);
        assertThat(testProgramasProjectos.getTipoFinanciamento()).isEqualTo(UPDATED_TIPO_FINANCIAMENTO);
        assertThat(testProgramasProjectos.getEspecialidade()).isEqualTo(UPDATED_ESPECIALIDADE);
    }

    @Test
    @Transactional
    public void updateNonExistingProgramasProjectos() throws Exception {
        int databaseSizeBeforeUpdate = programasProjectosRepository.findAll().size();

        // Create the ProgramasProjectos
        ProgramasProjectosDTO programasProjectosDTO = programasProjectosMapper.toDto(programasProjectos);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProgramasProjectosMockMvc.perform(put("/api/programas-projectos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programasProjectosDTO)))
            .andExpect(status().isCreated());

        // Validate the ProgramasProjectos in the database
        List<ProgramasProjectos> programasProjectosList = programasProjectosRepository.findAll();
        assertThat(programasProjectosList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteProgramasProjectos() throws Exception {
        // Initialize the database
        programasProjectosRepository.saveAndFlush(programasProjectos);
        int databaseSizeBeforeDelete = programasProjectosRepository.findAll().size();

        // Get the programasProjectos
        restProgramasProjectosMockMvc.perform(delete("/api/programas-projectos/{id}", programasProjectos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProgramasProjectos> programasProjectosList = programasProjectosRepository.findAll();
        assertThat(programasProjectosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProgramasProjectos.class);
        ProgramasProjectos programasProjectos1 = new ProgramasProjectos();
        programasProjectos1.setId(1L);
        ProgramasProjectos programasProjectos2 = new ProgramasProjectos();
        programasProjectos2.setId(programasProjectos1.getId());
        assertThat(programasProjectos1).isEqualTo(programasProjectos2);
        programasProjectos2.setId(2L);
        assertThat(programasProjectos1).isNotEqualTo(programasProjectos2);
        programasProjectos1.setId(null);
        assertThat(programasProjectos1).isNotEqualTo(programasProjectos2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProgramasProjectosDTO.class);
        ProgramasProjectosDTO programasProjectosDTO1 = new ProgramasProjectosDTO();
        programasProjectosDTO1.setId(1L);
        ProgramasProjectosDTO programasProjectosDTO2 = new ProgramasProjectosDTO();
        assertThat(programasProjectosDTO1).isNotEqualTo(programasProjectosDTO2);
        programasProjectosDTO2.setId(programasProjectosDTO1.getId());
        assertThat(programasProjectosDTO1).isEqualTo(programasProjectosDTO2);
        programasProjectosDTO2.setId(2L);
        assertThat(programasProjectosDTO1).isNotEqualTo(programasProjectosDTO2);
        programasProjectosDTO1.setId(null);
        assertThat(programasProjectosDTO1).isNotEqualTo(programasProjectosDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(programasProjectosMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(programasProjectosMapper.fromId(null)).isNull();
    }
}
