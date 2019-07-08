package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;
import com.minea.sisas.domain.*;
import com.minea.sisas.repository.InicioRepository;
import com.minea.sisas.service.InicioQueryService;
import com.minea.sisas.service.InicioService;
import com.minea.sisas.service.dto.InicioDTO;
import com.minea.sisas.service.mapper.InicioMapper;
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
import java.util.List;

import static com.minea.sisas.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the InicioResource REST controller.
 *
 * @see InicioResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class InicioResourceIntTest {

    private static final Long DEFAULT_ID_INICIO = 1L;
    private static final Long UPDATED_ID_INICIO = 2L;

    private static final Long DEFAULT_DESTAQUES = 1L;
    private static final Long UPDATED_DESTAQUES = 2L;

    private static final Long DEFAULT_ULTIMAS_NOTICIAS = 1L;
    private static final Long UPDATED_ULTIMAS_NOTICIAS = 2L;

    private static final Long DEFAULT_PUBLICACOES = 1L;
    private static final Long UPDATED_PUBLICACOES = 2L;

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_ALT = "AAAAAAAAAA";
    private static final String UPDATED_ALT = "BBBBBBBBBB";

    @Autowired
    private InicioRepository inicioRepository;

    @Autowired
    private InicioMapper inicioMapper;

    @Autowired
    private InicioService inicioService;

    @Autowired
    private InicioQueryService inicioQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restInicioMockMvc;

    private Inicio inicio;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InicioResource inicioResource = new InicioResource(inicioService, inicioQueryService);
        this.restInicioMockMvc = MockMvcBuilders.standaloneSetup(inicioResource)
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
    public static Inicio createEntity(EntityManager em) {
        Inicio inicio = new Inicio()
            .destaques(DEFAULT_DESTAQUES)
            .ultimasNoticias(DEFAULT_ULTIMAS_NOTICIAS)
            .publicacoes(DEFAULT_PUBLICACOES)
            .url(DEFAULT_URL)
            .alt(DEFAULT_ALT);
        // Add required entity
        Situacao idSituacao = SituacaoResourceIntTest.createEntity(em);
        em.persist(idSituacao);
        em.flush();
        inicio.setIdSituacao(idSituacao);
        // Add required entity
        SobreDna idSobreDna = SobreDnaResourceIntTest.createEntity(em);
        em.persist(idSobreDna);
        em.flush();
        inicio.setIdSobreDna(idSobreDna);
        // Add required entity
        Noticias idNoticias = NoticiasResourceIntTest.createEntity(em);
        em.persist(idNoticias);
        em.flush();
        inicio.setIdNoticias(idNoticias);
        // Add required entity
        Projectos idProjectos = ProjectosResourceIntTest.createEntity(em);
        em.persist(idProjectos);
        em.flush();
        inicio.setIdProjectos(idProjectos);
        // Add required entity
        Publicacao idPublicacao = PublicacaoResourceIntTest.createEntity(em);
        em.persist(idPublicacao);
        em.flush();
        inicio.setIdPublicacao(idPublicacao);
        // Add required entity
        Contactos idContactos = ContactosResourceIntTest.createEntity(em);
        em.persist(idContactos);
        em.flush();
        inicio.setIdContactos(idContactos);
        return inicio;
    }

    @Before
    public void initTest() {
        inicio = createEntity(em);
    }

    @Test
    @Transactional
    public void createInicio() throws Exception {
        int databaseSizeBeforeCreate = inicioRepository.findAll().size();

        // Create the Inicio
        InicioDTO inicioDTO = inicioMapper.toDto(inicio);
        restInicioMockMvc.perform(post("/api/inicios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inicioDTO)))
            .andExpect(status().isCreated());

        // Validate the Inicio in the database
        List<Inicio> inicioList = inicioRepository.findAll();
        assertThat(inicioList).hasSize(databaseSizeBeforeCreate + 1);
        Inicio testInicio = inicioList.get(inicioList.size() - 1);
        assertThat(testInicio.getDestaques()).isEqualTo(DEFAULT_DESTAQUES);
        assertThat(testInicio.getUltimasNoticias()).isEqualTo(DEFAULT_ULTIMAS_NOTICIAS);
        assertThat(testInicio.getPublicacoes()).isEqualTo(DEFAULT_PUBLICACOES);
        assertThat(testInicio.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testInicio.getAlt()).isEqualTo(DEFAULT_ALT);
    }

    @Test
    @Transactional
    public void createInicioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inicioRepository.findAll().size();

        // Create the Inicio with an existing ID
        inicio.setId(1L);
        InicioDTO inicioDTO = inicioMapper.toDto(inicio);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInicioMockMvc.perform(post("/api/inicios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inicioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Inicio in the database
        List<Inicio> inicioList = inicioRepository.findAll();
        assertThat(inicioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdInicioIsRequired() throws Exception {
        int databaseSizeBeforeTest = inicioRepository.findAll().size();
        // set the field null

        // Create the Inicio, which fails.
        InicioDTO inicioDTO = inicioMapper.toDto(inicio);

        restInicioMockMvc.perform(post("/api/inicios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inicioDTO)))
            .andExpect(status().isBadRequest());

        List<Inicio> inicioList = inicioRepository.findAll();
        assertThat(inicioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDestaquesIsRequired() throws Exception {
        int databaseSizeBeforeTest = inicioRepository.findAll().size();
        // set the field null
        inicio.setDestaques(null);

        // Create the Inicio, which fails.
        InicioDTO inicioDTO = inicioMapper.toDto(inicio);

        restInicioMockMvc.perform(post("/api/inicios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inicioDTO)))
            .andExpect(status().isBadRequest());

        List<Inicio> inicioList = inicioRepository.findAll();
        assertThat(inicioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUltimasNoticiasIsRequired() throws Exception {
        int databaseSizeBeforeTest = inicioRepository.findAll().size();
        // set the field null
        inicio.setUltimasNoticias(null);

        // Create the Inicio, which fails.
        InicioDTO inicioDTO = inicioMapper.toDto(inicio);

        restInicioMockMvc.perform(post("/api/inicios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inicioDTO)))
            .andExpect(status().isBadRequest());

        List<Inicio> inicioList = inicioRepository.findAll();
        assertThat(inicioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPublicacoesIsRequired() throws Exception {
        int databaseSizeBeforeTest = inicioRepository.findAll().size();
        // set the field null
        inicio.setPublicacoes(null);

        // Create the Inicio, which fails.
        InicioDTO inicioDTO = inicioMapper.toDto(inicio);

        restInicioMockMvc.perform(post("/api/inicios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inicioDTO)))
            .andExpect(status().isBadRequest());

        List<Inicio> inicioList = inicioRepository.findAll();
        assertThat(inicioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = inicioRepository.findAll().size();
        // set the field null
        inicio.setUrl(null);

        // Create the Inicio, which fails.
        InicioDTO inicioDTO = inicioMapper.toDto(inicio);

        restInicioMockMvc.perform(post("/api/inicios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inicioDTO)))
            .andExpect(status().isBadRequest());

        List<Inicio> inicioList = inicioRepository.findAll();
        assertThat(inicioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAltIsRequired() throws Exception {
        int databaseSizeBeforeTest = inicioRepository.findAll().size();
        // set the field null
        inicio.setAlt(null);

        // Create the Inicio, which fails.
        InicioDTO inicioDTO = inicioMapper.toDto(inicio);

        restInicioMockMvc.perform(post("/api/inicios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inicioDTO)))
            .andExpect(status().isBadRequest());

        List<Inicio> inicioList = inicioRepository.findAll();
        assertThat(inicioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInicios() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList
        restInicioMockMvc.perform(get("/api/inicios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inicio.getId().intValue())))
            .andExpect(jsonPath("$.[*].idInicio").value(hasItem(DEFAULT_ID_INICIO.intValue())))
            .andExpect(jsonPath("$.[*].destaques").value(hasItem(DEFAULT_DESTAQUES.intValue())))
            .andExpect(jsonPath("$.[*].ultimasNoticias").value(hasItem(DEFAULT_ULTIMAS_NOTICIAS.intValue())))
            .andExpect(jsonPath("$.[*].publicacoes").value(hasItem(DEFAULT_PUBLICACOES.intValue())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())))
            .andExpect(jsonPath("$.[*].alt").value(hasItem(DEFAULT_ALT.toString())));
    }

    @Test
    @Transactional
    public void getInicio() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get the inicio
        restInicioMockMvc.perform(get("/api/inicios/{id}", inicio.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(inicio.getId().intValue()))
            .andExpect(jsonPath("$.idInicio").value(DEFAULT_ID_INICIO.intValue()))
            .andExpect(jsonPath("$.destaques").value(DEFAULT_DESTAQUES.intValue()))
            .andExpect(jsonPath("$.ultimasNoticias").value(DEFAULT_ULTIMAS_NOTICIAS.intValue()))
            .andExpect(jsonPath("$.publicacoes").value(DEFAULT_PUBLICACOES.intValue()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()))
            .andExpect(jsonPath("$.alt").value(DEFAULT_ALT.toString()));
    }

    @Test
    @Transactional
    public void getAllIniciosByIdInicioIsEqualToSomething() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where idInicio equals to DEFAULT_ID_INICIO
        defaultInicioShouldBeFound("idInicio.equals=" + DEFAULT_ID_INICIO);

        // Get all the inicioList where idInicio equals to UPDATED_ID_INICIO
        defaultInicioShouldNotBeFound("idInicio.equals=" + UPDATED_ID_INICIO);
    }

    @Test
    @Transactional
    public void getAllIniciosByIdInicioIsInShouldWork() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where idInicio in DEFAULT_ID_INICIO or UPDATED_ID_INICIO
        defaultInicioShouldBeFound("idInicio.in=" + DEFAULT_ID_INICIO + "," + UPDATED_ID_INICIO);

        // Get all the inicioList where idInicio equals to UPDATED_ID_INICIO
        defaultInicioShouldNotBeFound("idInicio.in=" + UPDATED_ID_INICIO);
    }

    @Test
    @Transactional
    public void getAllIniciosByIdInicioIsNullOrNotNull() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where idInicio is not null
        defaultInicioShouldBeFound("idInicio.specified=true");

        // Get all the inicioList where idInicio is null
        defaultInicioShouldNotBeFound("idInicio.specified=false");
    }

    @Test
    @Transactional
    public void getAllIniciosByIdInicioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where idInicio greater than or equals to DEFAULT_ID_INICIO
        defaultInicioShouldBeFound("idInicio.greaterOrEqualThan=" + DEFAULT_ID_INICIO);

        // Get all the inicioList where idInicio greater than or equals to UPDATED_ID_INICIO
        defaultInicioShouldNotBeFound("idInicio.greaterOrEqualThan=" + UPDATED_ID_INICIO);
    }

    @Test
    @Transactional
    public void getAllIniciosByIdInicioIsLessThanSomething() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where idInicio less than or equals to DEFAULT_ID_INICIO
        defaultInicioShouldNotBeFound("idInicio.lessThan=" + DEFAULT_ID_INICIO);

        // Get all the inicioList where idInicio less than or equals to UPDATED_ID_INICIO
        defaultInicioShouldBeFound("idInicio.lessThan=" + UPDATED_ID_INICIO);
    }


    @Test
    @Transactional
    public void getAllIniciosByDestaquesIsEqualToSomething() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where destaques equals to DEFAULT_DESTAQUES
        defaultInicioShouldBeFound("destaques.equals=" + DEFAULT_DESTAQUES);

        // Get all the inicioList where destaques equals to UPDATED_DESTAQUES
        defaultInicioShouldNotBeFound("destaques.equals=" + UPDATED_DESTAQUES);
    }

    @Test
    @Transactional
    public void getAllIniciosByDestaquesIsInShouldWork() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where destaques in DEFAULT_DESTAQUES or UPDATED_DESTAQUES
        defaultInicioShouldBeFound("destaques.in=" + DEFAULT_DESTAQUES + "," + UPDATED_DESTAQUES);

        // Get all the inicioList where destaques equals to UPDATED_DESTAQUES
        defaultInicioShouldNotBeFound("destaques.in=" + UPDATED_DESTAQUES);
    }

    @Test
    @Transactional
    public void getAllIniciosByDestaquesIsNullOrNotNull() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where destaques is not null
        defaultInicioShouldBeFound("destaques.specified=true");

        // Get all the inicioList where destaques is null
        defaultInicioShouldNotBeFound("destaques.specified=false");
    }

    @Test
    @Transactional
    public void getAllIniciosByDestaquesIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where destaques greater than or equals to DEFAULT_DESTAQUES
        defaultInicioShouldBeFound("destaques.greaterOrEqualThan=" + DEFAULT_DESTAQUES);

        // Get all the inicioList where destaques greater than or equals to UPDATED_DESTAQUES
        defaultInicioShouldNotBeFound("destaques.greaterOrEqualThan=" + UPDATED_DESTAQUES);
    }

    @Test
    @Transactional
    public void getAllIniciosByDestaquesIsLessThanSomething() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where destaques less than or equals to DEFAULT_DESTAQUES
        defaultInicioShouldNotBeFound("destaques.lessThan=" + DEFAULT_DESTAQUES);

        // Get all the inicioList where destaques less than or equals to UPDATED_DESTAQUES
        defaultInicioShouldBeFound("destaques.lessThan=" + UPDATED_DESTAQUES);
    }


    @Test
    @Transactional
    public void getAllIniciosByUltimasNoticiasIsEqualToSomething() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where ultimasNoticias equals to DEFAULT_ULTIMAS_NOTICIAS
        defaultInicioShouldBeFound("ultimasNoticias.equals=" + DEFAULT_ULTIMAS_NOTICIAS);

        // Get all the inicioList where ultimasNoticias equals to UPDATED_ULTIMAS_NOTICIAS
        defaultInicioShouldNotBeFound("ultimasNoticias.equals=" + UPDATED_ULTIMAS_NOTICIAS);
    }

    @Test
    @Transactional
    public void getAllIniciosByUltimasNoticiasIsInShouldWork() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where ultimasNoticias in DEFAULT_ULTIMAS_NOTICIAS or UPDATED_ULTIMAS_NOTICIAS
        defaultInicioShouldBeFound("ultimasNoticias.in=" + DEFAULT_ULTIMAS_NOTICIAS + "," + UPDATED_ULTIMAS_NOTICIAS);

        // Get all the inicioList where ultimasNoticias equals to UPDATED_ULTIMAS_NOTICIAS
        defaultInicioShouldNotBeFound("ultimasNoticias.in=" + UPDATED_ULTIMAS_NOTICIAS);
    }

    @Test
    @Transactional
    public void getAllIniciosByUltimasNoticiasIsNullOrNotNull() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where ultimasNoticias is not null
        defaultInicioShouldBeFound("ultimasNoticias.specified=true");

        // Get all the inicioList where ultimasNoticias is null
        defaultInicioShouldNotBeFound("ultimasNoticias.specified=false");
    }

    @Test
    @Transactional
    public void getAllIniciosByUltimasNoticiasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where ultimasNoticias greater than or equals to DEFAULT_ULTIMAS_NOTICIAS
        defaultInicioShouldBeFound("ultimasNoticias.greaterOrEqualThan=" + DEFAULT_ULTIMAS_NOTICIAS);

        // Get all the inicioList where ultimasNoticias greater than or equals to UPDATED_ULTIMAS_NOTICIAS
        defaultInicioShouldNotBeFound("ultimasNoticias.greaterOrEqualThan=" + UPDATED_ULTIMAS_NOTICIAS);
    }

    @Test
    @Transactional
    public void getAllIniciosByUltimasNoticiasIsLessThanSomething() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where ultimasNoticias less than or equals to DEFAULT_ULTIMAS_NOTICIAS
        defaultInicioShouldNotBeFound("ultimasNoticias.lessThan=" + DEFAULT_ULTIMAS_NOTICIAS);

        // Get all the inicioList where ultimasNoticias less than or equals to UPDATED_ULTIMAS_NOTICIAS
        defaultInicioShouldBeFound("ultimasNoticias.lessThan=" + UPDATED_ULTIMAS_NOTICIAS);
    }


    @Test
    @Transactional
    public void getAllIniciosByPublicacoesIsEqualToSomething() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where publicacoes equals to DEFAULT_PUBLICACOES
        defaultInicioShouldBeFound("publicacoes.equals=" + DEFAULT_PUBLICACOES);

        // Get all the inicioList where publicacoes equals to UPDATED_PUBLICACOES
        defaultInicioShouldNotBeFound("publicacoes.equals=" + UPDATED_PUBLICACOES);
    }

    @Test
    @Transactional
    public void getAllIniciosByPublicacoesIsInShouldWork() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where publicacoes in DEFAULT_PUBLICACOES or UPDATED_PUBLICACOES
        defaultInicioShouldBeFound("publicacoes.in=" + DEFAULT_PUBLICACOES + "," + UPDATED_PUBLICACOES);

        // Get all the inicioList where publicacoes equals to UPDATED_PUBLICACOES
        defaultInicioShouldNotBeFound("publicacoes.in=" + UPDATED_PUBLICACOES);
    }

    @Test
    @Transactional
    public void getAllIniciosByPublicacoesIsNullOrNotNull() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where publicacoes is not null
        defaultInicioShouldBeFound("publicacoes.specified=true");

        // Get all the inicioList where publicacoes is null
        defaultInicioShouldNotBeFound("publicacoes.specified=false");
    }

    @Test
    @Transactional
    public void getAllIniciosByPublicacoesIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where publicacoes greater than or equals to DEFAULT_PUBLICACOES
        defaultInicioShouldBeFound("publicacoes.greaterOrEqualThan=" + DEFAULT_PUBLICACOES);

        // Get all the inicioList where publicacoes greater than or equals to UPDATED_PUBLICACOES
        defaultInicioShouldNotBeFound("publicacoes.greaterOrEqualThan=" + UPDATED_PUBLICACOES);
    }

    @Test
    @Transactional
    public void getAllIniciosByPublicacoesIsLessThanSomething() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where publicacoes less than or equals to DEFAULT_PUBLICACOES
        defaultInicioShouldNotBeFound("publicacoes.lessThan=" + DEFAULT_PUBLICACOES);

        // Get all the inicioList where publicacoes less than or equals to UPDATED_PUBLICACOES
        defaultInicioShouldBeFound("publicacoes.lessThan=" + UPDATED_PUBLICACOES);
    }


    @Test
    @Transactional
    public void getAllIniciosByUrlIsEqualToSomething() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where url equals to DEFAULT_URL
        defaultInicioShouldBeFound("url.equals=" + DEFAULT_URL);

        // Get all the inicioList where url equals to UPDATED_URL
        defaultInicioShouldNotBeFound("url.equals=" + UPDATED_URL);
    }

    @Test
    @Transactional
    public void getAllIniciosByUrlIsInShouldWork() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where url in DEFAULT_URL or UPDATED_URL
        defaultInicioShouldBeFound("url.in=" + DEFAULT_URL + "," + UPDATED_URL);

        // Get all the inicioList where url equals to UPDATED_URL
        defaultInicioShouldNotBeFound("url.in=" + UPDATED_URL);
    }

    @Test
    @Transactional
    public void getAllIniciosByUrlIsNullOrNotNull() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where url is not null
        defaultInicioShouldBeFound("url.specified=true");

        // Get all the inicioList where url is null
        defaultInicioShouldNotBeFound("url.specified=false");
    }

    @Test
    @Transactional
    public void getAllIniciosByAltIsEqualToSomething() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where alt equals to DEFAULT_ALT
        defaultInicioShouldBeFound("alt.equals=" + DEFAULT_ALT);

        // Get all the inicioList where alt equals to UPDATED_ALT
        defaultInicioShouldNotBeFound("alt.equals=" + UPDATED_ALT);
    }

    @Test
    @Transactional
    public void getAllIniciosByAltIsInShouldWork() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where alt in DEFAULT_ALT or UPDATED_ALT
        defaultInicioShouldBeFound("alt.in=" + DEFAULT_ALT + "," + UPDATED_ALT);

        // Get all the inicioList where alt equals to UPDATED_ALT
        defaultInicioShouldNotBeFound("alt.in=" + UPDATED_ALT);
    }

    @Test
    @Transactional
    public void getAllIniciosByAltIsNullOrNotNull() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);

        // Get all the inicioList where alt is not null
        defaultInicioShouldBeFound("alt.specified=true");

        // Get all the inicioList where alt is null
        defaultInicioShouldNotBeFound("alt.specified=false");
    }

    @Test
    @Transactional
    public void getAllIniciosByIdSituacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        Situacao idSituacao = SituacaoResourceIntTest.createEntity(em);
        em.persist(idSituacao);
        em.flush();
        inicio.setIdSituacao(idSituacao);
        inicioRepository.saveAndFlush(inicio);
        Long idSituacaoId = idSituacao.getId();

        // Get all the inicioList where idSituacao equals to situacao
        defaultInicioShouldBeFound("situacao.equals=" + idSituacaoId);

        // Get all the inicioList where idSituacao equals to situacao + 1
        defaultInicioShouldNotBeFound("situacao.equals=" + (idSituacaoId + 1));
    }


    @Test
    @Transactional
    public void getAllIniciosByIdSobreDnaIsEqualToSomething() throws Exception {
        // Initialize the database
        SobreDna idSobreDna = SobreDnaResourceIntTest.createEntity(em);
        em.persist(idSobreDna);
        em.flush();
        inicio.setIdSobreDna(idSobreDna);
        inicioRepository.saveAndFlush(inicio);
        Long idSobreDnaId = idSobreDna.getId();

        // Get all the inicioList where idSobreDna equals to idSobreDnaId
        defaultInicioShouldBeFound("idSobreDnaId.equals=" + idSobreDnaId);

        // Get all the inicioList where idSobreDna equals to idSobreDnaId + 1
        defaultInicioShouldNotBeFound("idSobreDnaId.equals=" + (idSobreDnaId + 1));
    }


    @Test
    @Transactional
    public void getAllIniciosByIdNoticiasIsEqualToSomething() throws Exception {
        // Initialize the database
        Noticias idNoticias = NoticiasResourceIntTest.createEntity(em);
        em.persist(idNoticias);
        em.flush();
        inicio.setIdNoticias(idNoticias);
        inicioRepository.saveAndFlush(inicio);
        Long idNoticiasId = idNoticias.getId();

        // Get all the inicioList where idNoticias equals to idNoticiasId
        defaultInicioShouldBeFound("idNoticiasId.equals=" + idNoticiasId);

        // Get all the inicioList where idNoticias equals to idNoticiasId + 1
        defaultInicioShouldNotBeFound("idNoticiasId.equals=" + (idNoticiasId + 1));
    }


    @Test
    @Transactional
    public void getAllIniciosByIdProjectosIsEqualToSomething() throws Exception {
        // Initialize the database
        Projectos idProjectos = ProjectosResourceIntTest.createEntity(em);
        em.persist(idProjectos);
        em.flush();
        inicio.setIdProjectos(idProjectos);
        inicioRepository.saveAndFlush(inicio);
        Long idProjectosId = idProjectos.getId();

        // Get all the inicioList where idProjectos equals to idProjectosId
        defaultInicioShouldBeFound("idProjectosId.equals=" + idProjectosId);

        // Get all the inicioList where idProjectos equals to idProjectosId + 1
        defaultInicioShouldNotBeFound("idProjectosId.equals=" + (idProjectosId + 1));
    }


    @Test
    @Transactional
    public void getAllIniciosByIdPublicacaoIsEqualToSomething() throws Exception {
        // Initialize the database
        Publicacao idPublicacao = PublicacaoResourceIntTest.createEntity(em);
        em.persist(idPublicacao);
        em.flush();
        inicio.setIdPublicacao(idPublicacao);
        inicioRepository.saveAndFlush(inicio);
        Long idPublicacaoId = idPublicacao.getId();

        // Get all the inicioList where idPublicacao equals to idPublicacaoId
        defaultInicioShouldBeFound("idPublicacaoId.equals=" + idPublicacaoId);

        // Get all the inicioList where idPublicacao equals to idPublicacaoId + 1
        defaultInicioShouldNotBeFound("idPublicacaoId.equals=" + (idPublicacaoId + 1));
    }


    @Test
    @Transactional
    public void getAllIniciosByIdContactosIsEqualToSomething() throws Exception {
        // Initialize the database
        Contactos idContactos = ContactosResourceIntTest.createEntity(em);
        em.persist(idContactos);
        em.flush();
        inicio.setIdContactos(idContactos);
        inicioRepository.saveAndFlush(inicio);
        Long idContactosId = idContactos.getId();

        // Get all the inicioList where idContactos equals to idContactosId
        defaultInicioShouldBeFound("idContactosId.equals=" + idContactosId);

        // Get all the inicioList where idContactos equals to idContactosId + 1
        defaultInicioShouldNotBeFound("idContactosId.equals=" + (idContactosId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultInicioShouldBeFound(String filter) throws Exception {
        restInicioMockMvc.perform(get("/api/inicios?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inicio.getId().intValue())))
            .andExpect(jsonPath("$.[*].idInicio").value(hasItem(DEFAULT_ID_INICIO.intValue())))
            .andExpect(jsonPath("$.[*].destaques").value(hasItem(DEFAULT_DESTAQUES.intValue())))
            .andExpect(jsonPath("$.[*].ultimasNoticias").value(hasItem(DEFAULT_ULTIMAS_NOTICIAS.intValue())))
            .andExpect(jsonPath("$.[*].publicacoes").value(hasItem(DEFAULT_PUBLICACOES.intValue())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())))
            .andExpect(jsonPath("$.[*].alt").value(hasItem(DEFAULT_ALT.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultInicioShouldNotBeFound(String filter) throws Exception {
        restInicioMockMvc.perform(get("/api/inicios?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingInicio() throws Exception {
        // Get the inicio
        restInicioMockMvc.perform(get("/api/inicios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInicio() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);
        int databaseSizeBeforeUpdate = inicioRepository.findAll().size();

        // Update the inicio
        Inicio updatedInicio = inicioRepository.findOne(inicio.getId());
        // Disconnect from session so that the updates on updatedInicio are not directly saved in db
        em.detach(updatedInicio);
        updatedInicio
            .destaques(UPDATED_DESTAQUES)
            .ultimasNoticias(UPDATED_ULTIMAS_NOTICIAS)
            .publicacoes(UPDATED_PUBLICACOES)
            .url(UPDATED_URL)
            .alt(UPDATED_ALT);
        InicioDTO inicioDTO = inicioMapper.toDto(updatedInicio);

        restInicioMockMvc.perform(put("/api/inicios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inicioDTO)))
            .andExpect(status().isOk());

        // Validate the Inicio in the database
        List<Inicio> inicioList = inicioRepository.findAll();
        assertThat(inicioList).hasSize(databaseSizeBeforeUpdate);
        Inicio testInicio = inicioList.get(inicioList.size() - 1);
        assertThat(testInicio.getDestaques()).isEqualTo(UPDATED_DESTAQUES);
        assertThat(testInicio.getUltimasNoticias()).isEqualTo(UPDATED_ULTIMAS_NOTICIAS);
        assertThat(testInicio.getPublicacoes()).isEqualTo(UPDATED_PUBLICACOES);
        assertThat(testInicio.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testInicio.getAlt()).isEqualTo(UPDATED_ALT);
    }

    @Test
    @Transactional
    public void updateNonExistingInicio() throws Exception {
        int databaseSizeBeforeUpdate = inicioRepository.findAll().size();

        // Create the Inicio
        InicioDTO inicioDTO = inicioMapper.toDto(inicio);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restInicioMockMvc.perform(put("/api/inicios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inicioDTO)))
            .andExpect(status().isCreated());

        // Validate the Inicio in the database
        List<Inicio> inicioList = inicioRepository.findAll();
        assertThat(inicioList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteInicio() throws Exception {
        // Initialize the database
        inicioRepository.saveAndFlush(inicio);
        int databaseSizeBeforeDelete = inicioRepository.findAll().size();

        // Get the inicio
        restInicioMockMvc.perform(delete("/api/inicios/{id}", inicio.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Inicio> inicioList = inicioRepository.findAll();
        assertThat(inicioList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Inicio.class);
        Inicio inicio1 = new Inicio();
        inicio1.setId(1L);
        Inicio inicio2 = new Inicio();
        inicio2.setId(inicio1.getId());
        assertThat(inicio1).isEqualTo(inicio2);
        inicio2.setId(2L);
        assertThat(inicio1).isNotEqualTo(inicio2);
        inicio1.setId(null);
        assertThat(inicio1).isNotEqualTo(inicio2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InicioDTO.class);
        InicioDTO inicioDTO1 = new InicioDTO();
        inicioDTO1.setId(1L);
        InicioDTO inicioDTO2 = new InicioDTO();
        assertThat(inicioDTO1).isNotEqualTo(inicioDTO2);
        inicioDTO2.setId(inicioDTO1.getId());
        assertThat(inicioDTO1).isEqualTo(inicioDTO2);
        inicioDTO2.setId(2L);
        assertThat(inicioDTO1).isNotEqualTo(inicioDTO2);
        inicioDTO1.setId(null);
        assertThat(inicioDTO1).isNotEqualTo(inicioDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(inicioMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(inicioMapper.fromId(null)).isNull();
    }
}
