package com.minea.sisas.web.rest;

import com.minea.sisas.SisasApp;

import com.minea.sisas.domain.EntidadeGestora;
import com.minea.sisas.domain.MunicipiosAtendidos;
import com.minea.sisas.repository.EntidadeGestoraRepository;
import com.minea.sisas.service.EntidadeGestoraService;
import com.minea.sisas.service.dto.EntidadeGestoraDTO;
import com.minea.sisas.service.mapper.EntidadeGestoraMapper;
import com.minea.sisas.web.rest.errors.ExceptionTranslator;
import com.minea.sisas.service.EntidadeGestoraQueryService;

import com.minea.sisas.web.rest.EntidadeGestoraResource;
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
 * Test class for the EntidadeGestoraResource REST controller.
 *
 * @see EntidadeGestoraResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SisasApp.class)
public class EntidadeGestoraResourceIntTest {

    private static final Long DEFAULT_ID_ENTIDADE_GESTORA = 1L;
    private static final Long UPDATED_ID_ENTIDADE_GESTORA = 2L;

    private static final String DEFAULT_NM_ENTIDADE_GESTORA = "AAAAAAAAAA";
    private static final String UPDATED_NM_ENTIDADE_GESTORA = "BBBBBBBBBB";

    private static final Long DEFAULT_TP_FORMA_JURIDICA = 1L;
    private static final Long UPDATED_TP_FORMA_JURIDICA = 2L;

    private static final LocalDate DEFAULT_DT_CONSTITUICAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_CONSTITUICAO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ENDERECO = "AAAAAAAAAA";
    private static final String UPDATED_ENDERECO = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACTOS = "AAAAAAAAAA";
    private static final String UPDATED_CONTACTOS = "BBBBBBBBBB";

    private static final Long DEFAULT_TP_MODELO_GESTAO = 1L;
    private static final Long UPDATED_TP_MODELO_GESTAO = 2L;

    private static final Long DEFAULT_NUM_RECURSOS_HUMANOS = 1L;
    private static final Long UPDATED_NUM_RECURSOS_HUMANOS = 2L;

    private static final BigDecimal DEFAULT_NUM_POPULACAO_AREA_ATENDIMENTO = new BigDecimal(1);
    private static final BigDecimal UPDATED_NUM_POPULACAO_AREA_ATENDIMENTO = new BigDecimal(2);

    @Autowired
    private EntidadeGestoraRepository entidadeGestoraRepository;

    @Autowired
    private EntidadeGestoraMapper entidadeGestoraMapper;

    @Autowired
    private EntidadeGestoraService entidadeGestoraService;

    @Autowired
    private EntidadeGestoraQueryService entidadeGestoraQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEntidadeGestoraMockMvc;

    private EntidadeGestora entidadeGestora;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EntidadeGestoraResource entidadeGestoraResource = new EntidadeGestoraResource(entidadeGestoraService, entidadeGestoraQueryService);
        this.restEntidadeGestoraMockMvc = MockMvcBuilders.standaloneSetup(entidadeGestoraResource)
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
    public static EntidadeGestora createEntity(EntityManager em) {
        EntidadeGestora entidadeGestora = new EntidadeGestora()
            .idEntidadeGestora(DEFAULT_ID_ENTIDADE_GESTORA)
            .nmEntidadeGestora(DEFAULT_NM_ENTIDADE_GESTORA)
            .tpFormaJuridica(DEFAULT_TP_FORMA_JURIDICA)
            .dtConstituicao(DEFAULT_DT_CONSTITUICAO)
            .endereco(DEFAULT_ENDERECO)
            .email(DEFAULT_EMAIL)
            .contactos(DEFAULT_CONTACTOS)
            .tpModeloGestao(DEFAULT_TP_MODELO_GESTAO)
            .numRecursosHumanos(DEFAULT_NUM_RECURSOS_HUMANOS)
            .numPopulacaoAreaAtendimento(DEFAULT_NUM_POPULACAO_AREA_ATENDIMENTO);
        // Add required entity
        MunicipiosAtendidos idMunicipioAtendido = MunicipiosAtendidosResourceIntTest.createEntity(em);
        em.persist(idMunicipioAtendido);
        em.flush();
        entidadeGestora.setIdMunicipioAtendido(idMunicipioAtendido);
        return entidadeGestora;
    }

    @Before
    public void initTest() {
        entidadeGestora = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntidadeGestora() throws Exception {
        int databaseSizeBeforeCreate = entidadeGestoraRepository.findAll().size();

        // Create the EntidadeGestora
        EntidadeGestoraDTO entidadeGestoraDTO = entidadeGestoraMapper.toDto(entidadeGestora);
        restEntidadeGestoraMockMvc.perform(post("/api/entidade-gestoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entidadeGestoraDTO)))
            .andExpect(status().isCreated());

        // Validate the EntidadeGestora in the database
        List<EntidadeGestora> entidadeGestoraList = entidadeGestoraRepository.findAll();
        assertThat(entidadeGestoraList).hasSize(databaseSizeBeforeCreate + 1);
        EntidadeGestora testEntidadeGestora = entidadeGestoraList.get(entidadeGestoraList.size() - 1);
        assertThat(testEntidadeGestora.getIdEntidadeGestora()).isEqualTo(DEFAULT_ID_ENTIDADE_GESTORA);
        assertThat(testEntidadeGestora.getNmEntidadeGestora()).isEqualTo(DEFAULT_NM_ENTIDADE_GESTORA);
        assertThat(testEntidadeGestora.getTpFormaJuridica()).isEqualTo(DEFAULT_TP_FORMA_JURIDICA);
        assertThat(testEntidadeGestora.getDtConstituicao()).isEqualTo(DEFAULT_DT_CONSTITUICAO);
        assertThat(testEntidadeGestora.getEndereco()).isEqualTo(DEFAULT_ENDERECO);
        assertThat(testEntidadeGestora.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testEntidadeGestora.getContactos()).isEqualTo(DEFAULT_CONTACTOS);
        assertThat(testEntidadeGestora.getTpModeloGestao()).isEqualTo(DEFAULT_TP_MODELO_GESTAO);
        assertThat(testEntidadeGestora.getNumRecursosHumanos()).isEqualTo(DEFAULT_NUM_RECURSOS_HUMANOS);
        assertThat(testEntidadeGestora.getNumPopulacaoAreaAtendimento()).isEqualTo(DEFAULT_NUM_POPULACAO_AREA_ATENDIMENTO);
    }

    @Test
    @Transactional
    public void createEntidadeGestoraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entidadeGestoraRepository.findAll().size();

        // Create the EntidadeGestora with an existing ID
        entidadeGestora.setId(1L);
        EntidadeGestoraDTO entidadeGestoraDTO = entidadeGestoraMapper.toDto(entidadeGestora);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntidadeGestoraMockMvc.perform(post("/api/entidade-gestoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entidadeGestoraDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntidadeGestora in the database
        List<EntidadeGestora> entidadeGestoraList = entidadeGestoraRepository.findAll();
        assertThat(entidadeGestoraList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdEntidadeGestoraIsRequired() throws Exception {
        int databaseSizeBeforeTest = entidadeGestoraRepository.findAll().size();
        // set the field null
        entidadeGestora.setIdEntidadeGestora(null);

        // Create the EntidadeGestora, which fails.
        EntidadeGestoraDTO entidadeGestoraDTO = entidadeGestoraMapper.toDto(entidadeGestora);

        restEntidadeGestoraMockMvc.perform(post("/api/entidade-gestoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entidadeGestoraDTO)))
            .andExpect(status().isBadRequest());

        List<EntidadeGestora> entidadeGestoraList = entidadeGestoraRepository.findAll();
        assertThat(entidadeGestoraList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNmEntidadeGestoraIsRequired() throws Exception {
        int databaseSizeBeforeTest = entidadeGestoraRepository.findAll().size();
        // set the field null
        entidadeGestora.setNmEntidadeGestora(null);

        // Create the EntidadeGestora, which fails.
        EntidadeGestoraDTO entidadeGestoraDTO = entidadeGestoraMapper.toDto(entidadeGestora);

        restEntidadeGestoraMockMvc.perform(post("/api/entidade-gestoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entidadeGestoraDTO)))
            .andExpect(status().isBadRequest());

        List<EntidadeGestora> entidadeGestoraList = entidadeGestoraRepository.findAll();
        assertThat(entidadeGestoraList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTpFormaJuridicaIsRequired() throws Exception {
        int databaseSizeBeforeTest = entidadeGestoraRepository.findAll().size();
        // set the field null
        entidadeGestora.setTpFormaJuridica(null);

        // Create the EntidadeGestora, which fails.
        EntidadeGestoraDTO entidadeGestoraDTO = entidadeGestoraMapper.toDto(entidadeGestora);

        restEntidadeGestoraMockMvc.perform(post("/api/entidade-gestoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entidadeGestoraDTO)))
            .andExpect(status().isBadRequest());

        List<EntidadeGestora> entidadeGestoraList = entidadeGestoraRepository.findAll();
        assertThat(entidadeGestoraList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnderecoIsRequired() throws Exception {
        int databaseSizeBeforeTest = entidadeGestoraRepository.findAll().size();
        // set the field null
        entidadeGestora.setEndereco(null);

        // Create the EntidadeGestora, which fails.
        EntidadeGestoraDTO entidadeGestoraDTO = entidadeGestoraMapper.toDto(entidadeGestora);

        restEntidadeGestoraMockMvc.perform(post("/api/entidade-gestoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entidadeGestoraDTO)))
            .andExpect(status().isBadRequest());

        List<EntidadeGestora> entidadeGestoraList = entidadeGestoraRepository.findAll();
        assertThat(entidadeGestoraList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTpModeloGestaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = entidadeGestoraRepository.findAll().size();
        // set the field null
        entidadeGestora.setTpModeloGestao(null);

        // Create the EntidadeGestora, which fails.
        EntidadeGestoraDTO entidadeGestoraDTO = entidadeGestoraMapper.toDto(entidadeGestora);

        restEntidadeGestoraMockMvc.perform(post("/api/entidade-gestoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entidadeGestoraDTO)))
            .andExpect(status().isBadRequest());

        List<EntidadeGestora> entidadeGestoraList = entidadeGestoraRepository.findAll();
        assertThat(entidadeGestoraList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumRecursosHumanosIsRequired() throws Exception {
        int databaseSizeBeforeTest = entidadeGestoraRepository.findAll().size();
        // set the field null
        entidadeGestora.setNumRecursosHumanos(null);

        // Create the EntidadeGestora, which fails.
        EntidadeGestoraDTO entidadeGestoraDTO = entidadeGestoraMapper.toDto(entidadeGestora);

        restEntidadeGestoraMockMvc.perform(post("/api/entidade-gestoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entidadeGestoraDTO)))
            .andExpect(status().isBadRequest());

        List<EntidadeGestora> entidadeGestoraList = entidadeGestoraRepository.findAll();
        assertThat(entidadeGestoraList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestoras() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList
        restEntidadeGestoraMockMvc.perform(get("/api/entidade-gestoras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entidadeGestora.getId().intValue())))
            .andExpect(jsonPath("$.[*].idEntidadeGestora").value(hasItem(DEFAULT_ID_ENTIDADE_GESTORA.intValue())))
            .andExpect(jsonPath("$.[*].nmEntidadeGestora").value(hasItem(DEFAULT_NM_ENTIDADE_GESTORA.toString())))
            .andExpect(jsonPath("$.[*].tpFormaJuridica").value(hasItem(DEFAULT_TP_FORMA_JURIDICA.intValue())))
            .andExpect(jsonPath("$.[*].dtConstituicao").value(hasItem(DEFAULT_DT_CONSTITUICAO.toString())))
            .andExpect(jsonPath("$.[*].endereco").value(hasItem(DEFAULT_ENDERECO.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].contactos").value(hasItem(DEFAULT_CONTACTOS.toString())))
            .andExpect(jsonPath("$.[*].tpModeloGestao").value(hasItem(DEFAULT_TP_MODELO_GESTAO.intValue())))
            .andExpect(jsonPath("$.[*].numRecursosHumanos").value(hasItem(DEFAULT_NUM_RECURSOS_HUMANOS.intValue())))
            .andExpect(jsonPath("$.[*].numPopulacaoAreaAtendimento").value(hasItem(DEFAULT_NUM_POPULACAO_AREA_ATENDIMENTO.intValue())));
    }

    @Test
    @Transactional
    public void getEntidadeGestora() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get the entidadeGestora
        restEntidadeGestoraMockMvc.perform(get("/api/entidade-gestoras/{id}", entidadeGestora.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(entidadeGestora.getId().intValue()))
            .andExpect(jsonPath("$.idEntidadeGestora").value(DEFAULT_ID_ENTIDADE_GESTORA.intValue()))
            .andExpect(jsonPath("$.nmEntidadeGestora").value(DEFAULT_NM_ENTIDADE_GESTORA.toString()))
            .andExpect(jsonPath("$.tpFormaJuridica").value(DEFAULT_TP_FORMA_JURIDICA.intValue()))
            .andExpect(jsonPath("$.dtConstituicao").value(DEFAULT_DT_CONSTITUICAO.toString()))
            .andExpect(jsonPath("$.endereco").value(DEFAULT_ENDERECO.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.contactos").value(DEFAULT_CONTACTOS.toString()))
            .andExpect(jsonPath("$.tpModeloGestao").value(DEFAULT_TP_MODELO_GESTAO.intValue()))
            .andExpect(jsonPath("$.numRecursosHumanos").value(DEFAULT_NUM_RECURSOS_HUMANOS.intValue()))
            .andExpect(jsonPath("$.numPopulacaoAreaAtendimento").value(DEFAULT_NUM_POPULACAO_AREA_ATENDIMENTO.intValue()));
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByIdEntidadeGestoraIsEqualToSomething() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where idEntidadeGestora equals to DEFAULT_ID_ENTIDADE_GESTORA
        defaultEntidadeGestoraShouldBeFound("idEntidadeGestora.equals=" + DEFAULT_ID_ENTIDADE_GESTORA);

        // Get all the entidadeGestoraList where idEntidadeGestora equals to UPDATED_ID_ENTIDADE_GESTORA
        defaultEntidadeGestoraShouldNotBeFound("idEntidadeGestora.equals=" + UPDATED_ID_ENTIDADE_GESTORA);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByIdEntidadeGestoraIsInShouldWork() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where idEntidadeGestora in DEFAULT_ID_ENTIDADE_GESTORA or UPDATED_ID_ENTIDADE_GESTORA
        defaultEntidadeGestoraShouldBeFound("idEntidadeGestora.in=" + DEFAULT_ID_ENTIDADE_GESTORA + "," + UPDATED_ID_ENTIDADE_GESTORA);

        // Get all the entidadeGestoraList where idEntidadeGestora equals to UPDATED_ID_ENTIDADE_GESTORA
        defaultEntidadeGestoraShouldNotBeFound("idEntidadeGestora.in=" + UPDATED_ID_ENTIDADE_GESTORA);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByIdEntidadeGestoraIsNullOrNotNull() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where idEntidadeGestora is not null
        defaultEntidadeGestoraShouldBeFound("idEntidadeGestora.specified=true");

        // Get all the entidadeGestoraList where idEntidadeGestora is null
        defaultEntidadeGestoraShouldNotBeFound("idEntidadeGestora.specified=false");
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByIdEntidadeGestoraIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where idEntidadeGestora greater than or equals to DEFAULT_ID_ENTIDADE_GESTORA
        defaultEntidadeGestoraShouldBeFound("idEntidadeGestora.greaterOrEqualThan=" + DEFAULT_ID_ENTIDADE_GESTORA);

        // Get all the entidadeGestoraList where idEntidadeGestora greater than or equals to UPDATED_ID_ENTIDADE_GESTORA
        defaultEntidadeGestoraShouldNotBeFound("idEntidadeGestora.greaterOrEqualThan=" + UPDATED_ID_ENTIDADE_GESTORA);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByIdEntidadeGestoraIsLessThanSomething() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where idEntidadeGestora less than or equals to DEFAULT_ID_ENTIDADE_GESTORA
        defaultEntidadeGestoraShouldNotBeFound("idEntidadeGestora.lessThan=" + DEFAULT_ID_ENTIDADE_GESTORA);

        // Get all the entidadeGestoraList where idEntidadeGestora less than or equals to UPDATED_ID_ENTIDADE_GESTORA
        defaultEntidadeGestoraShouldBeFound("idEntidadeGestora.lessThan=" + UPDATED_ID_ENTIDADE_GESTORA);
    }


    @Test
    @Transactional
    public void getAllEntidadeGestorasByNmEntidadeGestoraIsEqualToSomething() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where nmEntidadeGestora equals to DEFAULT_NM_ENTIDADE_GESTORA
        defaultEntidadeGestoraShouldBeFound("nmEntidadeGestora.equals=" + DEFAULT_NM_ENTIDADE_GESTORA);

        // Get all the entidadeGestoraList where nmEntidadeGestora equals to UPDATED_NM_ENTIDADE_GESTORA
        defaultEntidadeGestoraShouldNotBeFound("nmEntidadeGestora.equals=" + UPDATED_NM_ENTIDADE_GESTORA);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByNmEntidadeGestoraIsInShouldWork() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where nmEntidadeGestora in DEFAULT_NM_ENTIDADE_GESTORA or UPDATED_NM_ENTIDADE_GESTORA
        defaultEntidadeGestoraShouldBeFound("nmEntidadeGestora.in=" + DEFAULT_NM_ENTIDADE_GESTORA + "," + UPDATED_NM_ENTIDADE_GESTORA);

        // Get all the entidadeGestoraList where nmEntidadeGestora equals to UPDATED_NM_ENTIDADE_GESTORA
        defaultEntidadeGestoraShouldNotBeFound("nmEntidadeGestora.in=" + UPDATED_NM_ENTIDADE_GESTORA);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByNmEntidadeGestoraIsNullOrNotNull() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where nmEntidadeGestora is not null
        defaultEntidadeGestoraShouldBeFound("nmEntidadeGestora.specified=true");

        // Get all the entidadeGestoraList where nmEntidadeGestora is null
        defaultEntidadeGestoraShouldNotBeFound("nmEntidadeGestora.specified=false");
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByTpFormaJuridicaIsEqualToSomething() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where tpFormaJuridica equals to DEFAULT_TP_FORMA_JURIDICA
        defaultEntidadeGestoraShouldBeFound("tpFormaJuridica.equals=" + DEFAULT_TP_FORMA_JURIDICA);

        // Get all the entidadeGestoraList where tpFormaJuridica equals to UPDATED_TP_FORMA_JURIDICA
        defaultEntidadeGestoraShouldNotBeFound("tpFormaJuridica.equals=" + UPDATED_TP_FORMA_JURIDICA);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByTpFormaJuridicaIsInShouldWork() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where tpFormaJuridica in DEFAULT_TP_FORMA_JURIDICA or UPDATED_TP_FORMA_JURIDICA
        defaultEntidadeGestoraShouldBeFound("tpFormaJuridica.in=" + DEFAULT_TP_FORMA_JURIDICA + "," + UPDATED_TP_FORMA_JURIDICA);

        // Get all the entidadeGestoraList where tpFormaJuridica equals to UPDATED_TP_FORMA_JURIDICA
        defaultEntidadeGestoraShouldNotBeFound("tpFormaJuridica.in=" + UPDATED_TP_FORMA_JURIDICA);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByTpFormaJuridicaIsNullOrNotNull() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where tpFormaJuridica is not null
        defaultEntidadeGestoraShouldBeFound("tpFormaJuridica.specified=true");

        // Get all the entidadeGestoraList where tpFormaJuridica is null
        defaultEntidadeGestoraShouldNotBeFound("tpFormaJuridica.specified=false");
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByTpFormaJuridicaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where tpFormaJuridica greater than or equals to DEFAULT_TP_FORMA_JURIDICA
        defaultEntidadeGestoraShouldBeFound("tpFormaJuridica.greaterOrEqualThan=" + DEFAULT_TP_FORMA_JURIDICA);

        // Get all the entidadeGestoraList where tpFormaJuridica greater than or equals to UPDATED_TP_FORMA_JURIDICA
        defaultEntidadeGestoraShouldNotBeFound("tpFormaJuridica.greaterOrEqualThan=" + UPDATED_TP_FORMA_JURIDICA);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByTpFormaJuridicaIsLessThanSomething() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where tpFormaJuridica less than or equals to DEFAULT_TP_FORMA_JURIDICA
        defaultEntidadeGestoraShouldNotBeFound("tpFormaJuridica.lessThan=" + DEFAULT_TP_FORMA_JURIDICA);

        // Get all the entidadeGestoraList where tpFormaJuridica less than or equals to UPDATED_TP_FORMA_JURIDICA
        defaultEntidadeGestoraShouldBeFound("tpFormaJuridica.lessThan=" + UPDATED_TP_FORMA_JURIDICA);
    }


    @Test
    @Transactional
    public void getAllEntidadeGestorasByDtConstituicaoIsEqualToSomething() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where dtConstituicao equals to DEFAULT_DT_CONSTITUICAO
        defaultEntidadeGestoraShouldBeFound("dtConstituicao.equals=" + DEFAULT_DT_CONSTITUICAO);

        // Get all the entidadeGestoraList where dtConstituicao equals to UPDATED_DT_CONSTITUICAO
        defaultEntidadeGestoraShouldNotBeFound("dtConstituicao.equals=" + UPDATED_DT_CONSTITUICAO);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByDtConstituicaoIsInShouldWork() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where dtConstituicao in DEFAULT_DT_CONSTITUICAO or UPDATED_DT_CONSTITUICAO
        defaultEntidadeGestoraShouldBeFound("dtConstituicao.in=" + DEFAULT_DT_CONSTITUICAO + "," + UPDATED_DT_CONSTITUICAO);

        // Get all the entidadeGestoraList where dtConstituicao equals to UPDATED_DT_CONSTITUICAO
        defaultEntidadeGestoraShouldNotBeFound("dtConstituicao.in=" + UPDATED_DT_CONSTITUICAO);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByDtConstituicaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where dtConstituicao is not null
        defaultEntidadeGestoraShouldBeFound("dtConstituicao.specified=true");

        // Get all the entidadeGestoraList where dtConstituicao is null
        defaultEntidadeGestoraShouldNotBeFound("dtConstituicao.specified=false");
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByDtConstituicaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where dtConstituicao greater than or equals to DEFAULT_DT_CONSTITUICAO
        defaultEntidadeGestoraShouldBeFound("dtConstituicao.greaterOrEqualThan=" + DEFAULT_DT_CONSTITUICAO);

        // Get all the entidadeGestoraList where dtConstituicao greater than or equals to UPDATED_DT_CONSTITUICAO
        defaultEntidadeGestoraShouldNotBeFound("dtConstituicao.greaterOrEqualThan=" + UPDATED_DT_CONSTITUICAO);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByDtConstituicaoIsLessThanSomething() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where dtConstituicao less than or equals to DEFAULT_DT_CONSTITUICAO
        defaultEntidadeGestoraShouldNotBeFound("dtConstituicao.lessThan=" + DEFAULT_DT_CONSTITUICAO);

        // Get all the entidadeGestoraList where dtConstituicao less than or equals to UPDATED_DT_CONSTITUICAO
        defaultEntidadeGestoraShouldBeFound("dtConstituicao.lessThan=" + UPDATED_DT_CONSTITUICAO);
    }


    @Test
    @Transactional
    public void getAllEntidadeGestorasByEnderecoIsEqualToSomething() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where endereco equals to DEFAULT_ENDERECO
        defaultEntidadeGestoraShouldBeFound("endereco.equals=" + DEFAULT_ENDERECO);

        // Get all the entidadeGestoraList where endereco equals to UPDATED_ENDERECO
        defaultEntidadeGestoraShouldNotBeFound("endereco.equals=" + UPDATED_ENDERECO);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByEnderecoIsInShouldWork() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where endereco in DEFAULT_ENDERECO or UPDATED_ENDERECO
        defaultEntidadeGestoraShouldBeFound("endereco.in=" + DEFAULT_ENDERECO + "," + UPDATED_ENDERECO);

        // Get all the entidadeGestoraList where endereco equals to UPDATED_ENDERECO
        defaultEntidadeGestoraShouldNotBeFound("endereco.in=" + UPDATED_ENDERECO);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByEnderecoIsNullOrNotNull() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where endereco is not null
        defaultEntidadeGestoraShouldBeFound("endereco.specified=true");

        // Get all the entidadeGestoraList where endereco is null
        defaultEntidadeGestoraShouldNotBeFound("endereco.specified=false");
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where email equals to DEFAULT_EMAIL
        defaultEntidadeGestoraShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the entidadeGestoraList where email equals to UPDATED_EMAIL
        defaultEntidadeGestoraShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultEntidadeGestoraShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the entidadeGestoraList where email equals to UPDATED_EMAIL
        defaultEntidadeGestoraShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where email is not null
        defaultEntidadeGestoraShouldBeFound("email.specified=true");

        // Get all the entidadeGestoraList where email is null
        defaultEntidadeGestoraShouldNotBeFound("email.specified=false");
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByContactosIsEqualToSomething() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where contactos equals to DEFAULT_CONTACTOS
        defaultEntidadeGestoraShouldBeFound("contactos.equals=" + DEFAULT_CONTACTOS);

        // Get all the entidadeGestoraList where contactos equals to UPDATED_CONTACTOS
        defaultEntidadeGestoraShouldNotBeFound("contactos.equals=" + UPDATED_CONTACTOS);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByContactosIsInShouldWork() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where contactos in DEFAULT_CONTACTOS or UPDATED_CONTACTOS
        defaultEntidadeGestoraShouldBeFound("contactos.in=" + DEFAULT_CONTACTOS + "," + UPDATED_CONTACTOS);

        // Get all the entidadeGestoraList where contactos equals to UPDATED_CONTACTOS
        defaultEntidadeGestoraShouldNotBeFound("contactos.in=" + UPDATED_CONTACTOS);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByContactosIsNullOrNotNull() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where contactos is not null
        defaultEntidadeGestoraShouldBeFound("contactos.specified=true");

        // Get all the entidadeGestoraList where contactos is null
        defaultEntidadeGestoraShouldNotBeFound("contactos.specified=false");
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByTpModeloGestaoIsEqualToSomething() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where tpModeloGestao equals to DEFAULT_TP_MODELO_GESTAO
        defaultEntidadeGestoraShouldBeFound("tpModeloGestao.equals=" + DEFAULT_TP_MODELO_GESTAO);

        // Get all the entidadeGestoraList where tpModeloGestao equals to UPDATED_TP_MODELO_GESTAO
        defaultEntidadeGestoraShouldNotBeFound("tpModeloGestao.equals=" + UPDATED_TP_MODELO_GESTAO);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByTpModeloGestaoIsInShouldWork() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where tpModeloGestao in DEFAULT_TP_MODELO_GESTAO or UPDATED_TP_MODELO_GESTAO
        defaultEntidadeGestoraShouldBeFound("tpModeloGestao.in=" + DEFAULT_TP_MODELO_GESTAO + "," + UPDATED_TP_MODELO_GESTAO);

        // Get all the entidadeGestoraList where tpModeloGestao equals to UPDATED_TP_MODELO_GESTAO
        defaultEntidadeGestoraShouldNotBeFound("tpModeloGestao.in=" + UPDATED_TP_MODELO_GESTAO);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByTpModeloGestaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where tpModeloGestao is not null
        defaultEntidadeGestoraShouldBeFound("tpModeloGestao.specified=true");

        // Get all the entidadeGestoraList where tpModeloGestao is null
        defaultEntidadeGestoraShouldNotBeFound("tpModeloGestao.specified=false");
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByTpModeloGestaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where tpModeloGestao greater than or equals to DEFAULT_TP_MODELO_GESTAO
        defaultEntidadeGestoraShouldBeFound("tpModeloGestao.greaterOrEqualThan=" + DEFAULT_TP_MODELO_GESTAO);

        // Get all the entidadeGestoraList where tpModeloGestao greater than or equals to UPDATED_TP_MODELO_GESTAO
        defaultEntidadeGestoraShouldNotBeFound("tpModeloGestao.greaterOrEqualThan=" + UPDATED_TP_MODELO_GESTAO);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByTpModeloGestaoIsLessThanSomething() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where tpModeloGestao less than or equals to DEFAULT_TP_MODELO_GESTAO
        defaultEntidadeGestoraShouldNotBeFound("tpModeloGestao.lessThan=" + DEFAULT_TP_MODELO_GESTAO);

        // Get all the entidadeGestoraList where tpModeloGestao less than or equals to UPDATED_TP_MODELO_GESTAO
        defaultEntidadeGestoraShouldBeFound("tpModeloGestao.lessThan=" + UPDATED_TP_MODELO_GESTAO);
    }


    @Test
    @Transactional
    public void getAllEntidadeGestorasByNumRecursosHumanosIsEqualToSomething() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where numRecursosHumanos equals to DEFAULT_NUM_RECURSOS_HUMANOS
        defaultEntidadeGestoraShouldBeFound("numRecursosHumanos.equals=" + DEFAULT_NUM_RECURSOS_HUMANOS);

        // Get all the entidadeGestoraList where numRecursosHumanos equals to UPDATED_NUM_RECURSOS_HUMANOS
        defaultEntidadeGestoraShouldNotBeFound("numRecursosHumanos.equals=" + UPDATED_NUM_RECURSOS_HUMANOS);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByNumRecursosHumanosIsInShouldWork() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where numRecursosHumanos in DEFAULT_NUM_RECURSOS_HUMANOS or UPDATED_NUM_RECURSOS_HUMANOS
        defaultEntidadeGestoraShouldBeFound("numRecursosHumanos.in=" + DEFAULT_NUM_RECURSOS_HUMANOS + "," + UPDATED_NUM_RECURSOS_HUMANOS);

        // Get all the entidadeGestoraList where numRecursosHumanos equals to UPDATED_NUM_RECURSOS_HUMANOS
        defaultEntidadeGestoraShouldNotBeFound("numRecursosHumanos.in=" + UPDATED_NUM_RECURSOS_HUMANOS);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByNumRecursosHumanosIsNullOrNotNull() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where numRecursosHumanos is not null
        defaultEntidadeGestoraShouldBeFound("numRecursosHumanos.specified=true");

        // Get all the entidadeGestoraList where numRecursosHumanos is null
        defaultEntidadeGestoraShouldNotBeFound("numRecursosHumanos.specified=false");
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByNumRecursosHumanosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where numRecursosHumanos greater than or equals to DEFAULT_NUM_RECURSOS_HUMANOS
        defaultEntidadeGestoraShouldBeFound("numRecursosHumanos.greaterOrEqualThan=" + DEFAULT_NUM_RECURSOS_HUMANOS);

        // Get all the entidadeGestoraList where numRecursosHumanos greater than or equals to UPDATED_NUM_RECURSOS_HUMANOS
        defaultEntidadeGestoraShouldNotBeFound("numRecursosHumanos.greaterOrEqualThan=" + UPDATED_NUM_RECURSOS_HUMANOS);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByNumRecursosHumanosIsLessThanSomething() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where numRecursosHumanos less than or equals to DEFAULT_NUM_RECURSOS_HUMANOS
        defaultEntidadeGestoraShouldNotBeFound("numRecursosHumanos.lessThan=" + DEFAULT_NUM_RECURSOS_HUMANOS);

        // Get all the entidadeGestoraList where numRecursosHumanos less than or equals to UPDATED_NUM_RECURSOS_HUMANOS
        defaultEntidadeGestoraShouldBeFound("numRecursosHumanos.lessThan=" + UPDATED_NUM_RECURSOS_HUMANOS);
    }


    @Test
    @Transactional
    public void getAllEntidadeGestorasByNumPopulacaoAreaAtendimentoIsEqualToSomething() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where numPopulacaoAreaAtendimento equals to DEFAULT_NUM_POPULACAO_AREA_ATENDIMENTO
        defaultEntidadeGestoraShouldBeFound("numPopulacaoAreaAtendimento.equals=" + DEFAULT_NUM_POPULACAO_AREA_ATENDIMENTO);

        // Get all the entidadeGestoraList where numPopulacaoAreaAtendimento equals to UPDATED_NUM_POPULACAO_AREA_ATENDIMENTO
        defaultEntidadeGestoraShouldNotBeFound("numPopulacaoAreaAtendimento.equals=" + UPDATED_NUM_POPULACAO_AREA_ATENDIMENTO);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByNumPopulacaoAreaAtendimentoIsInShouldWork() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where numPopulacaoAreaAtendimento in DEFAULT_NUM_POPULACAO_AREA_ATENDIMENTO or UPDATED_NUM_POPULACAO_AREA_ATENDIMENTO
        defaultEntidadeGestoraShouldBeFound("numPopulacaoAreaAtendimento.in=" + DEFAULT_NUM_POPULACAO_AREA_ATENDIMENTO + "," + UPDATED_NUM_POPULACAO_AREA_ATENDIMENTO);

        // Get all the entidadeGestoraList where numPopulacaoAreaAtendimento equals to UPDATED_NUM_POPULACAO_AREA_ATENDIMENTO
        defaultEntidadeGestoraShouldNotBeFound("numPopulacaoAreaAtendimento.in=" + UPDATED_NUM_POPULACAO_AREA_ATENDIMENTO);
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByNumPopulacaoAreaAtendimentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);

        // Get all the entidadeGestoraList where numPopulacaoAreaAtendimento is not null
        defaultEntidadeGestoraShouldBeFound("numPopulacaoAreaAtendimento.specified=true");

        // Get all the entidadeGestoraList where numPopulacaoAreaAtendimento is null
        defaultEntidadeGestoraShouldNotBeFound("numPopulacaoAreaAtendimento.specified=false");
    }

    @Test
    @Transactional
    public void getAllEntidadeGestorasByIdMunicipioAtendidoIsEqualToSomething() throws Exception {
        // Initialize the database
        MunicipiosAtendidos idMunicipioAtendido = MunicipiosAtendidosResourceIntTest.createEntity(em);
        em.persist(idMunicipioAtendido);
        em.flush();
        entidadeGestora.setIdMunicipioAtendido(idMunicipioAtendido);
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);
        Long idMunicipioAtendidoId = idMunicipioAtendido.getId();

        // Get all the entidadeGestoraList where idMunicipioAtendido equals to idMunicipioAtendidoId
        defaultEntidadeGestoraShouldBeFound("idMunicipioAtendidoId.equals=" + idMunicipioAtendidoId);

        // Get all the entidadeGestoraList where idMunicipioAtendido equals to idMunicipioAtendidoId + 1
        defaultEntidadeGestoraShouldNotBeFound("idMunicipioAtendidoId.equals=" + (idMunicipioAtendidoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultEntidadeGestoraShouldBeFound(String filter) throws Exception {
        restEntidadeGestoraMockMvc.perform(get("/api/entidade-gestoras?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entidadeGestora.getId().intValue())))
            .andExpect(jsonPath("$.[*].idEntidadeGestora").value(hasItem(DEFAULT_ID_ENTIDADE_GESTORA.intValue())))
            .andExpect(jsonPath("$.[*].nmEntidadeGestora").value(hasItem(DEFAULT_NM_ENTIDADE_GESTORA.toString())))
            .andExpect(jsonPath("$.[*].tpFormaJuridica").value(hasItem(DEFAULT_TP_FORMA_JURIDICA.intValue())))
            .andExpect(jsonPath("$.[*].dtConstituicao").value(hasItem(DEFAULT_DT_CONSTITUICAO.toString())))
            .andExpect(jsonPath("$.[*].endereco").value(hasItem(DEFAULT_ENDERECO.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].contactos").value(hasItem(DEFAULT_CONTACTOS.toString())))
            .andExpect(jsonPath("$.[*].tpModeloGestao").value(hasItem(DEFAULT_TP_MODELO_GESTAO.intValue())))
            .andExpect(jsonPath("$.[*].numRecursosHumanos").value(hasItem(DEFAULT_NUM_RECURSOS_HUMANOS.intValue())))
            .andExpect(jsonPath("$.[*].numPopulacaoAreaAtendimento").value(hasItem(DEFAULT_NUM_POPULACAO_AREA_ATENDIMENTO.intValue())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultEntidadeGestoraShouldNotBeFound(String filter) throws Exception {
        restEntidadeGestoraMockMvc.perform(get("/api/entidade-gestoras?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingEntidadeGestora() throws Exception {
        // Get the entidadeGestora
        restEntidadeGestoraMockMvc.perform(get("/api/entidade-gestoras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntidadeGestora() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);
        int databaseSizeBeforeUpdate = entidadeGestoraRepository.findAll().size();

        // Update the entidadeGestora
        EntidadeGestora updatedEntidadeGestora = entidadeGestoraRepository.findOne(entidadeGestora.getId());
        // Disconnect from session so that the updates on updatedEntidadeGestora are not directly saved in db
        em.detach(updatedEntidadeGestora);
        updatedEntidadeGestora
            .idEntidadeGestora(UPDATED_ID_ENTIDADE_GESTORA)
            .nmEntidadeGestora(UPDATED_NM_ENTIDADE_GESTORA)
            .tpFormaJuridica(UPDATED_TP_FORMA_JURIDICA)
            .dtConstituicao(UPDATED_DT_CONSTITUICAO)
            .endereco(UPDATED_ENDERECO)
            .email(UPDATED_EMAIL)
            .contactos(UPDATED_CONTACTOS)
            .tpModeloGestao(UPDATED_TP_MODELO_GESTAO)
            .numRecursosHumanos(UPDATED_NUM_RECURSOS_HUMANOS)
            .numPopulacaoAreaAtendimento(UPDATED_NUM_POPULACAO_AREA_ATENDIMENTO);
        EntidadeGestoraDTO entidadeGestoraDTO = entidadeGestoraMapper.toDto(updatedEntidadeGestora);

        restEntidadeGestoraMockMvc.perform(put("/api/entidade-gestoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entidadeGestoraDTO)))
            .andExpect(status().isOk());

        // Validate the EntidadeGestora in the database
        List<EntidadeGestora> entidadeGestoraList = entidadeGestoraRepository.findAll();
        assertThat(entidadeGestoraList).hasSize(databaseSizeBeforeUpdate);
        EntidadeGestora testEntidadeGestora = entidadeGestoraList.get(entidadeGestoraList.size() - 1);
        assertThat(testEntidadeGestora.getIdEntidadeGestora()).isEqualTo(UPDATED_ID_ENTIDADE_GESTORA);
        assertThat(testEntidadeGestora.getNmEntidadeGestora()).isEqualTo(UPDATED_NM_ENTIDADE_GESTORA);
        assertThat(testEntidadeGestora.getTpFormaJuridica()).isEqualTo(UPDATED_TP_FORMA_JURIDICA);
        assertThat(testEntidadeGestora.getDtConstituicao()).isEqualTo(UPDATED_DT_CONSTITUICAO);
        assertThat(testEntidadeGestora.getEndereco()).isEqualTo(UPDATED_ENDERECO);
        assertThat(testEntidadeGestora.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEntidadeGestora.getContactos()).isEqualTo(UPDATED_CONTACTOS);
        assertThat(testEntidadeGestora.getTpModeloGestao()).isEqualTo(UPDATED_TP_MODELO_GESTAO);
        assertThat(testEntidadeGestora.getNumRecursosHumanos()).isEqualTo(UPDATED_NUM_RECURSOS_HUMANOS);
        assertThat(testEntidadeGestora.getNumPopulacaoAreaAtendimento()).isEqualTo(UPDATED_NUM_POPULACAO_AREA_ATENDIMENTO);
    }

    @Test
    @Transactional
    public void updateNonExistingEntidadeGestora() throws Exception {
        int databaseSizeBeforeUpdate = entidadeGestoraRepository.findAll().size();

        // Create the EntidadeGestora
        EntidadeGestoraDTO entidadeGestoraDTO = entidadeGestoraMapper.toDto(entidadeGestora);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEntidadeGestoraMockMvc.perform(put("/api/entidade-gestoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entidadeGestoraDTO)))
            .andExpect(status().isCreated());

        // Validate the EntidadeGestora in the database
        List<EntidadeGestora> entidadeGestoraList = entidadeGestoraRepository.findAll();
        assertThat(entidadeGestoraList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEntidadeGestora() throws Exception {
        // Initialize the database
        entidadeGestoraRepository.saveAndFlush(entidadeGestora);
        int databaseSizeBeforeDelete = entidadeGestoraRepository.findAll().size();

        // Get the entidadeGestora
        restEntidadeGestoraMockMvc.perform(delete("/api/entidade-gestoras/{id}", entidadeGestora.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EntidadeGestora> entidadeGestoraList = entidadeGestoraRepository.findAll();
        assertThat(entidadeGestoraList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntidadeGestora.class);
        EntidadeGestora entidadeGestora1 = new EntidadeGestora();
        entidadeGestora1.setId(1L);
        EntidadeGestora entidadeGestora2 = new EntidadeGestora();
        entidadeGestora2.setId(entidadeGestora1.getId());
        assertThat(entidadeGestora1).isEqualTo(entidadeGestora2);
        entidadeGestora2.setId(2L);
        assertThat(entidadeGestora1).isNotEqualTo(entidadeGestora2);
        entidadeGestora1.setId(null);
        assertThat(entidadeGestora1).isNotEqualTo(entidadeGestora2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntidadeGestoraDTO.class);
        EntidadeGestoraDTO entidadeGestoraDTO1 = new EntidadeGestoraDTO();
        entidadeGestoraDTO1.setId(1L);
        EntidadeGestoraDTO entidadeGestoraDTO2 = new EntidadeGestoraDTO();
        assertThat(entidadeGestoraDTO1).isNotEqualTo(entidadeGestoraDTO2);
        entidadeGestoraDTO2.setId(entidadeGestoraDTO1.getId());
        assertThat(entidadeGestoraDTO1).isEqualTo(entidadeGestoraDTO2);
        entidadeGestoraDTO2.setId(2L);
        assertThat(entidadeGestoraDTO1).isNotEqualTo(entidadeGestoraDTO2);
        entidadeGestoraDTO1.setId(null);
        assertThat(entidadeGestoraDTO1).isNotEqualTo(entidadeGestoraDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(entidadeGestoraMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(entidadeGestoraMapper.fromId(null)).isNull();
    }
}
